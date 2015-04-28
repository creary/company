/*   1:    */package com.soofound.portal.dao;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.BaseDao;
/*   4:    */import com.soofound.framework.util.CommonUtil;
/*   5:    */import com.soofound.framework.util.DateUtil;
/*   6:    */import com.soofound.portal.bean.SurfingAccount;
/*   7:    */import com.soofound.portal.bean.SurfingSession;
/*   8:    */import com.soofound.portal.bean.SurfingUser;
/*   9:    */import java.sql.ResultSet;
/*  10:    */import java.sql.SQLException;
/*  11:    */import java.util.ArrayList;
/*  12:    */import java.util.HashMap;
/*  13:    */import java.util.List;
/*  14:    */import org.springframework.dao.DataAccessException;
/*  15:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  16:    */import org.springframework.jdbc.core.ResultSetExtractor;
/*  17:    */import org.springframework.stereotype.Component;
/*  18:    */
/*  19:    */@Component
/*  20:    */public final class SurfingSessionDao extends BaseDao<SurfingSession>
/*  21:    */{
/*  22:    */  private static final String SQL_SURFING_SESSION = "SELECT * FROM view_surfing_session";
/*  23:    */  private static final String SQL_SURFING_SESSION_HISTORY = "SELECT * FROM view_surfing_session_history";
/*  24:    */  protected java.util.Map<String, String> sortColumns;
/*  25:    */  
/*  26:    */  public SurfingSessionDao()
/*  27:    */  {
/*  28: 28 */    this.sortColumns = new HashMap();
/*  29: 29 */    this.sortColumns.put("branch", "branch");
/*  30: 30 */    this.sortColumns.put("username", "username");
/*  31: 31 */    this.sortColumns.put("ipAddress", "ip_address");
/*  32: 32 */    this.sortColumns.put("mac", "mac");
/*  33: 33 */    this.sortColumns.put("apName", "ap_name");
/*  34: 34 */    this.sortColumns.put("startTime", "start_time");
/*  35: 35 */    this.sortColumns.put("stopTime", "stop_time");
/*  36: 36 */    this.sortColumns.put("upTimeText", "session_time");
/*  37: 37 */    this.sortColumns.put("txText", "output_octets");
/*  38: 38 */    this.sortColumns.put("rxText", "input_octets");
/*  39: 39 */    this.sortColumns.put("snrText", "snr");
/*  40:    */  }
/*  41:    */  
/*  42:    */  protected String getQuerySQL(java.util.Map<String, String> options)
/*  43:    */  {
/*  44: 44 */    StringBuffer sqlText = new StringBuffer(200);
/*  45: 45 */    if ("1".equals(options.get("history"))) {
/*  46: 46 */      sqlText.append("SELECT * FROM view_surfing_session_history").append(" where 1=1 ");
/*  47:    */    } else
/*  48: 48 */      sqlText.append("SELECT * FROM view_surfing_session").append(" where state > 0 ");
/*  49: 49 */    if (!CommonUtil.isEmpty((String)options.get("username"))) {
/*  50: 50 */      String username = null;
/*  51: 51 */      int loc = ((String)options.get("username")).indexOf("(");
/*  52: 52 */      if (loc == -1) {
/*  53: 53 */        username = (String)options.get("username");
/*  54:    */      } else
/*  55: 55 */        username = ((String)options.get("username")).substring(0, loc);
/*  56: 56 */      sqlText.append(" and username = '").append(username).append("'");
/*  57:    */    }
/*  58: 58 */    if (!CommonUtil.isEmpty((String)options.get("apid")))
/*  59: 59 */      sqlText.append(" and cpe_id = ").append((String)options.get("apid")).append(" and state = 1");
/*  60: 60 */    if (!CommonUtil.isEmpty((String)options.get("username2")))
/*  61: 61 */      sqlText.append(" and username like '%").append((String)options.get("username2")).append("%'");
/*  62: 62 */    if (!CommonUtil.isEmpty((String)options.get("ip")))
/*  63: 63 */      sqlText.append(" and ip_address = '").append((String)options.get("ip")).append("'");
/*  64: 64 */    if (!CommonUtil.isEmpty((String)options.get("mac")))
/*  65: 65 */      sqlText.append(" and mac like '%").append((String)options.get("mac")).append("%'");
/*  66: 66 */    if (!CommonUtil.isEmpty((String)options.get("apName"))) {
/*  67: 67 */      sqlText.append(" and (ap_name like '%").append((String)options.get("apName")).append("%'");
/*  68: 68 */      sqlText.append(" or apmac like '%").append((String)options.get("apName")).append("%')");
/*  69:    */    }
/*  70: 70 */    if (!CommonUtil.isEmpty((String)options.get("bid"))) {
/*  71: 71 */      sqlText.append(" and branch_id like '").append((String)options.get("bid")).append("%'");
/*  72: 72 */    } else if (!CommonUtil.isEmpty((String)options.get("branchId")))
/*  73: 73 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/*  74: 74 */    String sc = (String)options.get("sortColumn");
/*  75: 75 */    if ((CommonUtil.isEmpty(sc)) || ("undefined".equals(sc))) {
/*  76: 76 */      sqlText.append(" order by start_time desc");
/*  77:    */    } else {
/*  78: 78 */      sqlText.append(" order by ").append((String)this.sortColumns.get(sc));
/*  79: 79 */      if ("desc".equals(options.get("order")))
/*  80: 80 */        sqlText.append(" desc");
/*  81:    */    }
/*  82: 82 */    return sqlText.toString();
/*  83:    */  }
/*  84:    */  
/*  85:    */  public SurfingSession findByID(String sessionId)
/*  86:    */  {
/*  87: 87 */    String sqlText = "SELECT * FROM view_surfing_session where session_id='" + sessionId + "'";
/*  88: 88 */    return (SurfingSession)super.findOne(sqlText);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public List<SurfingSession> getSurfingSessions(String startTime, String stopTime) {
/*  92: 92 */    StringBuffer sqlText = new StringBuffer(200);
/*  93: 93 */    sqlText.append("SELECT * FROM view_surfing_session").append(" where start_time>='").append(startTime).append("' and start_time<='");
/*  94: 94 */    sqlText.append(stopTime).append("' ORDER BY start_time DESC");
/*  95: 95 */    return super.findByCriteria(sqlText.toString());
/*  96:    */  }
/*  97:    */  
/*  98:    */  public SurfingSession getLastSurfingSession(String branchId, String mac) {
/*  99: 99 */    StringBuffer sqlText = new StringBuffer(200);
/* 100:100 */    sqlText.append("SELECT * FROM view_surfing_session").append(" where branch_id='").append(branchId).append("' and mac='");
/* 101:101 */    sqlText.append(mac).append("' and stop_time is not null ORDER BY stop_time DESC limit 1");
/* 102:102 */    return (SurfingSession)super.findOne(sqlText.toString());
/* 103:    */  }
/* 104:    */  
/* 105:    */  public List<SurfingSession> findByCpe(String cpeId) {
/* 106:106 */    String sqlText = "SELECT * FROM view_surfing_session where cpe_id=" + cpeId;
/* 107:107 */    return super.findByCriteria(sqlText);
/* 108:    */  }
/* 109:    */  
/* 110:    */  public List<SurfingSession> getOnlineUsers(String cpeId) {
/* 111:111 */    String sqlText = "select * from view_surfing_session where state > 0";
/* 112:112 */    if (cpeId != null)
/* 113:113 */      sqlText = sqlText + " and cpe_id=" + cpeId;
/* 114:114 */    return super.findByCriteria(sqlText);
/* 115:    */  }
/* 116:    */  
/* 117:    */  public List<SurfingSession> getSurfingSessions(String[] ids) {
/* 118:118 */    String sqlText = "SELECT * FROM view_surfing_session WHERE session_id in (" + CommonUtil.arrayToQuotString(ids) + ")";
/* 119:119 */    return super.findByCriteria(sqlText);
/* 120:    */  }
/* 121:    */  
/* 122:    */  public List<SurfingSession> getSurfingSessions(String branchId, boolean history) {
/* 123:123 */    StringBuilder sqlText = new StringBuilder(200);
/* 124:124 */    sqlText.append("SELECT * FROM ");
/* 125:125 */    if (history) {
/* 126:126 */      sqlText.append(" view_surfing_session_history ");
/* 127:    */    } else
/* 128:128 */      sqlText.append(" view_surfing_session ");
/* 129:129 */    sqlText.append(" WHERE branch_id='").append(branchId).append("' order by start_time desc LIMIT 10000");
/* 130:130 */    return super.findByCriteria(sqlText.toString());
/* 131:    */  }
/* 132:    */  
/* 133:    */  public int getLastOnlineUserTotal(int hostId) {
/* 134:134 */    String sql = "SELECT COUNT(*) FROM surfing_session WHERE stop_time > DATE_ADD(NOW(),INTERVAL-10 MINUTE) AND cpe_id=" + hostId;
/* 135:135 */    return ((Integer)getJdbcTemplate().queryForObject(sql, Integer.class)).intValue();
/* 136:    */  }
/* 137:    */  
/* 138:    */  public List<SurfingSession> getUsersForStayTime(String branchId) {
/* 139:139 */    StringBuilder sqlText = new StringBuilder(200);
/* 140:140 */    sqlText.append("SELECT username,MIN(start_time) start_time,MAX(stop_time) stop_time,SUM(session_time) session_time,SUM(input_octets) input_octets,");
/* 141:141 */    sqlText.append("SUM(output_octets) output_octets FROM view_surfing_session WHERE DATE_FORMAT(start_time,'%Y-%m-%d')='");
/* 142:142 */    sqlText.append(DateUtil.getCurrentDate()).append("' AND branch_id='").append(branchId).append("' GROUP BY username ORDER by start_time desc");
/* 143:143 */    return super.findByCriteria(sqlText.toString());
/* 144:    */  }
/* 145:    */  
/* 146:    */  public SurfingSession getUserForStayTime(String branchId, String username) {
/* 147:147 */    StringBuilder sqlText = new StringBuilder(200);
/* 148:148 */    sqlText.append("SELECT username,MIN(start_time) start_time,MAX(stop_time) stop_time,SUM(session_time) session_time,SUM(input_octets) input_octets,");
/* 149:149 */    sqlText.append("SUM(output_octets) output_octets FROM view_surfing_session WHERE DATE_FORMAT(start_time,'%Y-%m-%d')='");
/* 150:150 */    sqlText.append(DateUtil.getCurrentDate()).append("' AND branch_id='").append(branchId).append("' AND username='").append(username).append("'");
/* 151:151 */    return (SurfingSession)super.findOne(sqlText.toString());
/* 152:    */  }
/* 153:    */  
/* 154:    */  public void deleteSessions(List<SurfingAccount> sas) {
/* 155:155 */    List<String> sqls = new ArrayList();
/* 156:156 */    for (SurfingAccount sa : sas) {
/* 157:157 */      StringBuilder sqlText = new StringBuilder(100);
/* 158:158 */      sqlText.append("delete from surfing_session where cpe_id in (select id from cpe_host where branch_id='");
/* 159:159 */      sqlText.append(sa.getBranchId()).append("') and username='").append(sa.getUsername()).append("'");
/* 160:160 */      sqls.add(sqlText.toString());
/* 161:    */    }
/* 162:162 */    batchUpdate(sqls);
/* 163:    */  }
/* 164:    */  
/* 165:    */  public List<String> getAPTraces(String mac, String startTime, String endTime) {
/* 166:166 */    StringBuilder sqlText = new StringBuilder(200);
/* 167:167 */    sqlText.append("SELECT DISTINCT apmac FROM view_surfing_session where mac='").append(mac).append("' and start_time>'");
/* 168:168 */    sqlText.append(startTime).append("' and start_time<'").append(endTime).append("' order by start_time desc");
/* 169:169 */    List<String> aps = (List)getJdbcTemplate().query(sqlText.toString(), new ResultSetExtractor() {
/* 170:    */      public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 171:171 */        List<String> aps = new ArrayList();
/* 172:172 */        while (rs.next())
/* 173:173 */          aps.add(rs.getString(1));
/* 174:174 */        return aps;
/* 175:175 */      } } );
/* 176:176 */    return aps;
/* 177:    */  }
/* 178:    */  
/* 179:    */  public SurfingUser getSurfingUser(String branchId, String mac) {
/* 180:180 */    String sql = null;
/* 181:181 */    if (branchId == null) {
/* 182:182 */      sql = "select * from surfing_session where mac='" + mac + "' and state > 0 limit 1";
/* 183:    */    } else
/* 184:184 */      sql = "select * from surfing_session where branch_id='" + branchId + "' and mac='" + mac + "' and state > 0 limit 1";
/* 185:185 */    return loadSurfingUser(sql);
/* 186:    */  }
/* 187:    */  
/* 188:    */  private SurfingUser loadSurfingUser(String sql) {
/* 189:189 */    SurfingUser user = (SurfingUser)getJdbcTemplate().query(sql, new ResultSetExtractor() {
/* 190:    */      public SurfingUser extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 191:191 */        SurfingUser user = null;
/* 192:192 */        if (rs.next()) {
/* 193:193 */          user = new SurfingUser();
/* 194:194 */          user.setSessionId(rs.getString("session_id"));
/* 195:195 */          user.setUsername(rs.getString("username"));
/* 196:196 */          user.setCpeId(rs.getInt("cpe_id"));
/* 197:197 */          user.setTerminalIP(rs.getString("ip_address"));
/* 198:198 */          user.setTerminalMac(rs.getString("mac"));
/* 199:199 */          user.setStartTime(DateUtil.dateTimeToLong(rs.getString("start_time")));
/* 200:200 */          user.setInputOctets(rs.getLong("input_octets"));
/* 201:201 */          user.setOutputOctets(rs.getLong("output_octets"));
/* 202:202 */          user.setBranchId(rs.getString("branch_id"));
/* 203:203 */          user.setStatus(rs.getInt("state"));
/* 204:204 */          user.setFlag(rs.getString("flag"));
/* 205:205 */          user.setRoamUrl(rs.getString("url"));
/* 206:206 */          user.setIfe(rs.getInt("ife"));
/* 207:207 */          if (rs.getString("stop_time") != null)
/* 208:208 */            user.setLastUpdateTime(DateUtil.dateTimeToLong(rs.getString("stop_time")));
/* 209:209 */          user.setSsid(rs.getInt("cpe_id") + "-" + rs.getInt("ife"));
/* 210:    */        }
/* 211:211 */        return user;
/* 212:212 */      } } );
/* 213:213 */    return user;
/* 214:    */  }
/* 215:    */  
/* 216:    */  public List<SurfingUser> getSurfingUser2(String branchId, String username) {
/* 217:217 */    List<SurfingUser> sus = new ArrayList();
/* 218:218 */    String sql = "select * from view_surfing_session where branch_id='" + branchId + "' and username='" + username + "' and state > 0";
/* 219:219 */    List<SurfingSession> dtos = findByCriteria(sql);
/* 220:220 */    if (!dtos.isEmpty()) {
/* 221:221 */      for (SurfingSession dto : dtos)
/* 222:222 */        sus.add(SurfingUser.bornFromSurfingSession(dto));
/* 223:    */    }
/* 224:224 */    return sus;
/* 225:    */  }
/* 226:    */  
/* 227:    */  public List<SurfingUser> getSurfingUser3(int apId, int ife) {
/* 228:228 */    List<SurfingUser> sus = new ArrayList();
/* 229:229 */    String sql = "select * from view_surfing_session where cpe_id=" + apId + " and ife=" + ife + " and state > 0";
/* 230:230 */    List<SurfingSession> dtos = findByCriteria(sql);
/* 231:231 */    if (!dtos.isEmpty()) {
/* 232:232 */      for (SurfingSession dto : dtos)
/* 233:233 */        sus.add(SurfingUser.bornFromSurfingSession(dto));
/* 234:    */    }
/* 235:235 */    return sus;
/* 236:    */  }
/* 237:    */  
/* 238:    */  public List<SurfingUser> getSurfingUser4(String ipAddress) {
/* 239:239 */    List<SurfingUser> sus = new ArrayList();
/* 240:240 */    String sql = "select * from view_surfing_session where ip_address='" + ipAddress + "' and state > 0";
/* 241:241 */    List<SurfingSession> dtos = findByCriteria(sql);
/* 242:242 */    if (!dtos.isEmpty()) {
/* 243:243 */      for (SurfingSession dto : dtos)
/* 244:244 */        sus.add(SurfingUser.bornFromSurfingSession(dto));
/* 245:    */    }
/* 246:246 */    return sus;
/* 247:    */  }
/* 248:    */  
/* 249:    */  public SurfingUser getSurfingUser5(String ipAddress, String username) {
/* 250:250 */    String sql = "select * from view_surfing_session where ip_address='" + ipAddress + "' and username='" + username + "' and state > 0";
/* 251:251 */    SurfingSession dto = (SurfingSession)super.findOne(sql);
/* 252:252 */    if (dto != null)
/* 253:253 */      return SurfingUser.bornFromSurfingSession(dto);
/* 254:254 */    return null;
/* 255:    */  }
/* 256:    */  
/* 257:    */  public int delete(String[] ids) {
/* 258:258 */    List<String> sqls = new ArrayList();
/* 259:259 */    for (String id : ids) {
/* 260:260 */      String sql1 = "delete from surfing_session where session_id='" + id + "'";
/* 261:261 */      String sql2 = "delete from surfing_session_history where session_id='" + id + "'";
/* 262:262 */      sqls.add(sql1);
/* 263:263 */      sqls.add(sql2);
/* 264:    */    }
/* 265:265 */    int[] results = getJdbcTemplate().batchUpdate(CommonUtil.toArray(sqls));
/* 266:266 */    return results.length;
/* 267:    */  }
/* 268:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.SurfingSessionDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */