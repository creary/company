/*   1:    */package com.soofound.portal.service;
/*   2:    */
/*   3:    */import com.soofound.cpe.util.StunServerLauncher;
/*   4:    */import com.soofound.portal.util.WifiDogUtils;
/*   5:    */import java.io.PrintStream;
/*   6:    */import org.springframework.beans.factory.annotation.Qualifier;
/*   7:    */import org.springframework.scheduling.annotation.Scheduled;
/*   8:    */import org.springframework.stereotype.Service;
/*   9:    */
/*  10:    */@Service
/*  11:    */public final class WifiDogService
/*  12:    */{
/*  13:    */  private static final int AP_TIMEOUT = 300000;
/*  14:    */  private static final int IDLE_TIMEOUT = 300000;
/*  15:    */  private static final int SURFING_TIMEOUT = 60000;
/*  16:    */  @org.springframework.beans.factory.annotation.Autowired
/*  17:    */  private com.soofound.portal.dao.SurfingSessionDao ssdao;
/*  18:    */  @org.springframework.beans.factory.annotation.Autowired
/*  19:    */  private com.soofound.portal.dao.SurfingAccountDao sadao;
/*  20:    */  @org.springframework.beans.factory.annotation.Autowired
/*  21:    */  private SurfingSessionService sss;
/*  22:    */  @org.springframework.beans.factory.annotation.Autowired
/*  23:    */  @Qualifier("defaultJdbcTemplate")
/*  24:    */  private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
/*  25:    */  @org.springframework.beans.factory.annotation.Autowired
/*  26:    */  private com.soofound.portal.util.OuiFactory ouiFac;
/*  27:    */  @org.springframework.beans.factory.annotation.Autowired
/*  28:    */  private com.soofound.cpe.web.HostService hostSvc;
/*  29:    */  @org.springframework.beans.factory.annotation.Autowired
/*  30:    */  private SurfingPolicyService sps;
/*  31:    */  @org.springframework.beans.factory.annotation.Autowired
/*  32:    */  @Qualifier("customFactory")
/*  33:    */  private com.soofound.portal.custom.CustomFactory custom;
/*  34:    */  
/*  35:    */  public com.soofound.portal.bean.SurfingUser getSurfingUser(String branchId, String mac)
/*  36:    */  {
/*  37: 37 */    return this.ssdao.getSurfingUser(branchId, mac);
/*  38:    */  }
/*  39:    */  
/*  40:    */  public java.util.List<com.soofound.portal.bean.SurfingUser> getSurfingUser2(String branchId, String username) {
/*  41: 41 */    return this.ssdao.getSurfingUser2(branchId, username);
/*  42:    */  }
/*  43:    */  
/*  44:    */  public java.util.List<com.soofound.portal.bean.SurfingUser> getSurfingUser3(int apId, int ife) {
/*  45: 45 */    return this.ssdao.getSurfingUser3(apId, ife);
/*  46:    */  }
/*  47:    */  
/*  48:    */  public java.util.List<com.soofound.portal.bean.SurfingUser> getSurfingUser4(String ipAddress) {
/*  49: 49 */    return this.ssdao.getSurfingUser4(ipAddress);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public com.soofound.portal.bean.SurfingUser getSurfingUser5(String ipAddress, String username) {
/*  53: 53 */    return this.ssdao.getSurfingUser5(ipAddress, username);
/*  54:    */  }
/*  55:    */  
/*  56:    */  public synchronized void recordRoaming(com.soofound.portal.bean.SurfingUser user) {
/*  57: 57 */    StringBuilder sqlText = new StringBuilder(200);
/*  58: 58 */    sqlText.append("update surfing_session set session_time=TIMESTAMPDIFF(SECOND,start_time,NOW()),state = 1,stop_time=NOW()");
/*  59: 59 */    sqlText.append(",input_octets=").append(user.getInputOctets());
/*  60: 60 */    sqlText.append(",output_octets=").append(user.getOutputOctets());
/*  61: 61 */    sqlText.append(",snr=").append(user.getSnr());
/*  62: 62 */    sqlText.append(",cpe_id=").append(user.getCpeId());
/*  63: 63 */    sqlText.append(",ife=").append(user.getIfe());
/*  64: 64 */    sqlText.append(" where session_id='").append(user.getSessionId()).append("'");
/*  65: 65 */    this.jdbcTemplate.update(sqlText.toString());
/*  66: 66 */    if (this.custom.getSessionStore() != null)
/*  67: 67 */      this.custom.getSessionStore().recordRoaming(user);
/*  68:    */  }
/*  69:    */  
/*  70:    */  public synchronized void recordOnline(com.soofound.portal.bean.SurfingUser user) {
/*  71: 71 */    if (user.getSessionId() == null) {
/*  72: 72 */      user.setSessionId(WifiDogUtils.getMyUUID());
/*  73:    */    }
/*  74: 74 */    java.util.List<String> sqls = new java.util.ArrayList();
/*  75: 75 */    StringBuilder sqlText1 = new StringBuilder(200);
/*  76: 76 */    sqlText1.append("delete from surfing_session where username='").append(user.getUsername()).append("' and mac='");
/*  77: 77 */    sqlText1.append(user.getTerminalMac()).append("'");
/*  78: 78 */    sqls.add(sqlText1.toString());
/*  79:    */    
/*  80: 80 */    StringBuilder sqlText2 = new StringBuilder(200);
/*  81: 81 */    sqlText2.append("insert into surfing_session(session_id,username,cpe_id,branch_id,ip_address,mac,start_time,stop_time,brand,flag,ife,url,state)values('");
/*  82: 82 */    sqlText2.append(user.getSessionId()).append("','").append(user.getUsername()).append("',").append(user.getCpeId());
/*  83: 83 */    sqlText2.append(",'").append(user.getBranchId()).append("','").append(user.getTerminalIP()).append("','").append(user.getTerminalMac());
/*  84: 84 */    sqlText2.append("',now(),now(),'").append(this.ouiFac.getBrand(user.getTerminalMac())).append("','").append(user.getFlag()).append("',");
/*  85: 85 */    sqlText2.append(getInterface(user.getSsid())).append(",'").append(user.getRoamUrl() == null ? "" : user.getRoamUrl()).append("',1)");
/*  86: 86 */    sqls.add(sqlText2.toString());
/*  87:    */    
/*  88: 88 */    com.soofound.portal.bean.SurfingAccount dto = this.sadao.findByUsername(user.getBranchId(), user.getUsername());
/*  89: 89 */    if (dto == null) {
/*  90: 90 */      dto = new com.soofound.portal.bean.SurfingAccount();
/*  91: 91 */      dto.setMac(user.getTerminalMac());
/*  92: 92 */      dto.setUsername(user.getUsername());
/*  93: 93 */      dto.setBranchId(user.getBranchId());
/*  94: 94 */      dto.setPassword("-");
/*  95: 95 */      dto.setFlag(user.getFlag());
/*  96: 96 */      this.sadao.save(dto);
/*  97:    */    }
/*  98: 98 */    StringBuilder sqlText3 = new StringBuilder(200);
/*  99: 99 */    sqlText3.append("update surfing_account set online = 1,times = times + 1,mac='").append(user.getTerminalMac());
/* 100:100 */    sqlText3.append("' where username='").append(user.getUsername()).append("' and branch_id='").append(user.getBranchId()).append("'");
/* 101:101 */    sqls.add(sqlText3.toString());
/* 102:102 */    this.jdbcTemplate.batchUpdate(com.soofound.framework.util.CommonUtil.toArray(sqls));
/* 103:    */    
/* 104:104 */    if (this.custom.getSessionStore() != null)
/* 105:105 */      this.custom.getSessionStore().recordOnline(user);
/* 106:    */  }
/* 107:    */  
/* 108:    */  private int getInterface(String ssid) {
/* 109:    */    try {
/* 110:110 */      return Integer.parseInt(ssid.split("-")[1]);
/* 111:    */    } catch (Exception e) {
/* 112:112 */      System.out.println("error SSID:" + ssid); }
/* 113:113 */    return 0;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public void clearOnlineUsers(int apId)
/* 117:    */  {
/* 118:118 */    java.util.List<com.soofound.portal.bean.SurfingSession> dtos = this.ssdao.getOnlineUsers(apId);
/* 119:119 */    for (com.soofound.portal.bean.SurfingSession dto : dtos)
/* 120:120 */      recordOffline(com.soofound.portal.bean.SurfingUser.bornFromSurfingSession(dto));
/* 121:    */  }
/* 122:    */  
/* 123:    */  public void recordIdle(com.soofound.portal.bean.SurfingUser user) {
/* 124:124 */    StringBuilder sqlText1 = new StringBuilder(200);
/* 125:125 */    sqlText1.append("update surfing_session set session_time=TIMESTAMPDIFF(SECOND,start_time,NOW())");
/* 126:126 */    sqlText1.append(",input_octets=").append(user.getInputOctets());
/* 127:127 */    sqlText1.append(",output_octets=").append(user.getOutputOctets());
/* 128:128 */    sqlText1.append(",state = 2 where session_id='").append(user.getSessionId()).append("' and state = 1");
/* 129:129 */    this.jdbcTemplate.update(sqlText1.toString());
/* 130:    */  }
/* 131:    */  
/* 132:    */  public synchronized void recordTraffic(com.soofound.portal.bean.SurfingUser user) {
/* 133:133 */    StringBuilder sqlText1 = new StringBuilder(200);
/* 134:134 */    sqlText1.append("update surfing_session set session_time=TIMESTAMPDIFF(SECOND,start_time,NOW()),state = 1,stop_time=NOW()");
/* 135:135 */    sqlText1.append(",input_octets=").append(user.getInputOctets());
/* 136:136 */    sqlText1.append(",output_octets=").append(user.getOutputOctets());
/* 137:137 */    sqlText1.append(",snr=").append(user.getSnr()).append(",ife=").append(user.getIfe());
/* 138:138 */    sqlText1.append(" where session_id='").append(user.getSessionId()).append("'");
/* 139:    */    
/* 140:140 */    StringBuilder sqlText2 = new StringBuilder(200);
/* 141:141 */    sqlText2.append("update surfing_account set online = 1 where mac='").append(user.getTerminalMac());
/* 142:142 */    sqlText2.append("' and username='").append(user.getUsername());
/* 143:143 */    sqlText2.append("' and branch_id='").append(user.getBranchId()).append("' and online = 0");
/* 144:144 */    String[] sqls = { sqlText1.toString(), sqlText2.toString() };
/* 145:145 */    this.jdbcTemplate.batchUpdate(sqls);
/* 146:    */    
/* 147:147 */    if (this.custom.getSessionStore() != null)
/* 148:148 */      this.custom.getSessionStore().recordTraffic(user);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public synchronized void recordOffline(com.soofound.portal.bean.SurfingUser user) {
/* 152:152 */    java.util.List<String> sqls = new java.util.ArrayList();
/* 153:    */    
/* 154:154 */    if ((user.getInputOctets() > 0L) && (user.getStartTime() > 0L)) {
/* 155:155 */      StringBuilder sqlText1 = new StringBuilder(200);
/* 156:156 */      sqlText1.append("INSERT INTO surfing_session_history(session_id,username,cpe_id,branch_id,ip_address,mac,start_time,stop_time,input_octets,output_octets,brand,flag)");
/* 157:157 */      sqlText1.append(" SELECT session_id,username,cpe_id,branch_id,ip_address,mac,start_time,stop_time,input_octets,output_octets,brand,flag FROM surfing_session");
/* 158:158 */      sqlText1.append(" WHERE session_id='").append(user.getSessionId()).append("' and input_octets is not null");
/* 159:159 */      sqls.add(sqlText1.toString());
/* 160:    */      
/* 161:161 */      StringBuilder sqlText2 = new StringBuilder(200);
/* 162:162 */      sqlText2.append("update surfing_session_history set session_time=TIMESTAMPDIFF(SECOND,start_time,now()),stop_time = now()");
/* 163:163 */      sqlText2.append(" where session_id='").append(user.getSessionId()).append("'");
/* 164:164 */      sqls.add(sqlText2.toString());
/* 165:    */    }
/* 166:166 */    StringBuilder sqlText3 = new StringBuilder(200);
/* 167:167 */    sqlText3.append("update surfing_account set online = 0 where mac='").append(user.getTerminalMac());
/* 168:168 */    sqlText3.append("' and username='").append(user.getUsername());
/* 169:169 */    sqlText3.append("' and branch_id='").append(user.getBranchId()).append("'");
/* 170:170 */    sqls.add(sqlText3.toString());
/* 171:    */    
/* 172:172 */    StringBuilder sqlText4 = new StringBuilder(100);
/* 173:173 */    sqlText4.append("delete from surfing_session where session_id='").append(user.getSessionId()).append("'");
/* 174:174 */    sqls.add(sqlText4.toString());
/* 175:    */    
/* 176:176 */    this.jdbcTemplate.batchUpdate(com.soofound.framework.util.CommonUtil.toArray(sqls));
/* 177:177 */    if (this.custom.getSessionStore() != null)
/* 178:178 */      this.custom.getSessionStore().recordOffline(user);
/* 179:    */  }
/* 180:    */  
/* 181:    */  public synchronized void recordOffline(java.util.List<com.soofound.portal.bean.SurfingAccount> sas) {
/* 182:182 */    java.util.List<String> sqls = new java.util.ArrayList();
/* 183:183 */    java.util.List<com.soofound.portal.bean.SurfingUser> sus = new java.util.ArrayList();
/* 184:184 */    for (com.soofound.portal.bean.SurfingAccount sa : sas) {
/* 185:185 */      StringBuilder sqlText1 = new StringBuilder(200);
/* 186:186 */      sqlText1.append("update surfing_account set online = 0 where username='").append(sa.getUsername());
/* 187:187 */      sqlText1.append("' and branch_id='").append(sa.getBranchId()).append("'");
/* 188:188 */      sqls.add(sqlText1.toString());
/* 189:    */      
/* 190:190 */      java.util.List<com.soofound.portal.bean.SurfingUser> surfingUsers = this.ssdao.getSurfingUser2(sa.getBranchId(), sa.getUsername());
/* 191:191 */      for (com.soofound.portal.bean.SurfingUser user : surfingUsers) {
/* 192:192 */        if ((user.getUsername().equals(sa.getUsername())) && (sa.getBranchId().equals(user.getBranchId()))) {
/* 193:193 */          sus.add(user);
/* 194:    */          
/* 195:195 */          StringBuilder sqlText2 = new StringBuilder(200);
/* 196:196 */          sqlText2.append("insert into surfing_session_history(session_id,username,cpe_id,branch_id,ip_address,mac,start_time,stop_time,session_time,input_octets,output_octets,snr,brand,flag)values('");
/* 197:197 */          sqlText2.append(user.getSessionId()).append("','").append(user.getUsername()).append("',").append(user.getCpeId());
/* 198:198 */          sqlText2.append(",'").append(user.getBranchId()).append("','").append(user.getTerminalIP()).append("','").append(user.getTerminalMac());
/* 199:199 */          sqlText2.append("','").append(com.soofound.framework.util.DateUtil.longToDateTime(user.getStartTime())).append("',now(),").append((com.soofound.framework.util.DateUtil.getCurrentLongDateTime() - user.getStartTime()) / 1000L);
/* 200:200 */          sqlText2.append(",").append(user.getInputOctets()).append(",").append(user.getOutputOctets()).append(",").append(user.getSnr());
/* 201:201 */          sqlText2.append(",'").append(this.ouiFac.getBrand(user.getTerminalMac())).append("','").append(user.getFlag()).append("')");
/* 202:202 */          sqls.add(sqlText2.toString());
/* 203:203 */          sqls.add("delete from surfing_session where session_id='" + user.getSessionId() + "'");
/* 204:    */        }
/* 205:    */      }
/* 206:206 */      this.jdbcTemplate.batchUpdate(com.soofound.framework.util.CommonUtil.toArray(sqls));
/* 207:    */    }
/* 208:208 */    for (com.soofound.portal.bean.SurfingUser su : sus) {
/* 209:209 */      su.setStatus(0);
/* 210:210 */      if (this.custom.getSessionStore() != null)
/* 211:211 */        this.custom.getSessionStore().recordOffline(su);
/* 212:    */    }
/* 213:    */  }
/* 214:    */  
/* 215:    */  @Scheduled(fixedRate=60000L)
/* 216:    */  public void dump() {
/* 217:217 */    java.util.List<com.soofound.portal.bean.SurfingUser> removeUsers = new java.util.ArrayList();
/* 218:218 */    java.util.List<String> sqls = new java.util.ArrayList();
/* 219:219 */    long curTime = com.soofound.framework.util.DateUtil.getCurrentLongDateTime();
/* 220:220 */    java.util.List<com.soofound.cpe.bean.HostBean> hosts = this.hostSvc.getOnlineHosts();
/* 221:221 */    java.util.List<com.soofound.portal.bean.SurfingSession> dtos = this.ssdao.getOnlineUsers(null);
/* 222:222 */    if (StunServerLauncher.STARTED) {
/* 223:223 */      for (com.soofound.cpe.bean.HostBean host : hosts) {
/* 224:224 */        long diffTime = curTime - com.soofound.framework.util.DateUtil.dateTimeToLong(host.getLastTime());
/* 225:225 */        if (diffTime > 300000L) {
/* 226:226 */          for (com.soofound.portal.bean.SurfingSession dto : dtos) {
/* 227:227 */            if (dto.getCpeId() == host.getId()) {
/* 228:228 */              removeUsers.add(com.soofound.portal.bean.SurfingUser.bornFromSurfingSession(dto));
/* 229:    */            }
/* 230:    */          }
/* 231:231 */          StringBuilder sqlText1 = new StringBuilder(200);
/* 232:232 */          sqlText1.append("insert into cpe_device_log(id,host_id,operator,message,log_time)values('").append(com.soofound.framework.util.DateUtil.getCurrentTimeAsID());
/* 233:233 */          sqlText1.append("','").append(host.getId()).append("','系统','设备下线','").append(com.soofound.framework.util.DateUtil.getCurrentDateTime()).append("')");
/* 234:234 */          sqls.add(sqlText1.toString());
/* 235:    */          
/* 236:236 */          StringBuilder sqlText2 = new StringBuilder(100);
/* 237:237 */          sqlText2.append("UPDATE cpe_host SET online=0 where id=").append(host.getId());
/* 238:238 */          sqls.add(sqlText2.toString());
/* 239:    */        }
/* 240:    */      }
/* 241:    */    }
/* 242:242 */    curTime = com.soofound.framework.util.DateUtil.getCurrentLongDateTime();
/* 243:243 */    for (com.soofound.portal.bean.SurfingSession dto : dtos) {
/* 244:    */      try {
/* 245:245 */        com.soofound.portal.bean.SurfingUser su = com.soofound.portal.bean.SurfingUser.bornFromSurfingSession(dto);
/* 246:246 */        com.soofound.portal.bean.SurfingPolicyDto spd = this.sps.getPolicy(su.getSsid());
/* 247:247 */        boolean delUser = false;
/* 248:248 */        if (spd.getStayTime() > 0) {
/* 249:249 */          com.soofound.portal.bean.SurfingSession stat = this.sss.getUserForStayTime(spd.getBranchId(), su.getUsername());
/* 250:250 */          if (stat.getSessionTime() >= spd.getStayTime() * 60) {
/* 251:251 */            removeUsers.add(su);
/* 252:252 */            delUser = true;
/* 253:    */          }
/* 254:    */        }
/* 255:255 */        if ((spd.getIdleTime() > 0) && (curTime - su.getLastUpdateTime() >= spd.getIdleTime() * 60 * 1000)) {
/* 256:256 */          removeUsers.add(su);
/* 257:257 */          delUser = true;
/* 258:    */        }
/* 259:259 */        if ((spd.getSurfingTime() > 0) && (curTime - su.getStartTime() >= spd.getSurfingTime() * 60 * 1000)) {
/* 260:260 */          removeUsers.add(su);
/* 261:261 */          delUser = true;
/* 262:    */        }
/* 263:263 */        if (curTime - su.getStartTime() >= 21600000L) {
/* 264:264 */          removeUsers.add(su);
/* 265:265 */          delUser = true;
/* 266:    */        }
/* 267:267 */        if ((!delUser) && (curTime - su.getLastUpdateTime() > 300000L)) {
/* 268:268 */          recordIdle(su);
/* 269:    */        }
/* 270:    */      } catch (Exception ex) {
/* 271:271 */        ex.printStackTrace();
/* 272:    */      }
/* 273:    */    }
/* 274:274 */    if (!removeUsers.isEmpty()) {
/* 275:275 */      java.util.List<String> sids = new java.util.ArrayList();
/* 276:276 */      for (com.soofound.portal.bean.SurfingUser su : removeUsers) {
/* 277:277 */        if (!sids.contains(su.getSessionId())) {
/* 278:278 */          recordOffline(su);
/* 279:279 */          sids.add(su.getSessionId());
/* 280:    */        }
/* 281:    */      }
/* 282:    */    }
/* 283:283 */    if (sqls.size() > 0) {
/* 284:284 */      this.jdbcTemplate.batchUpdate(com.soofound.framework.util.CommonUtil.toArray(sqls));
/* 285:    */    }
/* 286:    */  }
/* 287:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.WifiDogService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */