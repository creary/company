/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.soofound.acside.bean.CpeCustomDao;
/*   4:    */import com.soofound.admin.bean.UserDto;
/*   5:    */import com.soofound.cpe.bean.HostPropertyBean;
/*   6:    */import com.soofound.cpe.dao.BssidDao;
/*   7:    */import com.soofound.cpe.dao.HostPropertyDao;
/*   8:    */import com.soofound.cpe.util.CommandQueue;
/*   9:    */import com.soofound.cpe.util.ConcurrentLinkedCommandQueue;
/*  10:    */import com.soofound.cpe.util.MySQLCommandQueue;
/*  11:    */import com.soofound.cpe.util.SoofacACS;
/*  12:    */import com.soofound.framework.util.CommonUtil;
/*  13:    */import com.soofound.framework.util.DateUtil;
/*  14:    */import com.soofound.framework.util.SysConfigHelper;
/*  15:    */import com.soofound.framework.web.BaseService;
/*  16:    */import java.lang.reflect.Method;
/*  17:    */import java.util.ArrayList;
/*  18:    */import java.util.HashMap;
/*  19:    */import java.util.Iterator;
/*  20:    */import java.util.Set;
/*  21:    */import org.springframework.beans.factory.annotation.Autowired;
/*  22:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  23:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  24:    */import org.springframework.stereotype.Service;
/*  25:    */
/*  26:    */@Service
/*  27:    */public class HostService extends BaseService<com.soofound.cpe.dao.HostDao>
/*  28:    */{
/*  29:    */  private final java.util.Map<Integer, String> ifToNames;
/*  30:    */  private CommandQueue cq;
/*  31:    */  @Autowired
/*  32:    */  private BssidDao bdao;
/*  33:    */  @Autowired
/*  34:    */  private HostPropertyDao hpdao;
/*  35:    */  @Autowired
/*  36:    */  private CpeCustomDao ccdao;
/*  37:    */  @Autowired
/*  38:    */  @Qualifier("defaultJdbcTemplate")
/*  39:    */  private JdbcTemplate jdbc;
/*  40:    */  
/*  41:    */  public HostService()
/*  42:    */  {
/*  43: 43 */    this.ifToNames = new HashMap();
/*  44: 44 */    this.ifToNames.put(Integer.valueOf(0), "InternetGatewayDevice.DeviceInfo.wireless_ssid");
/*  45: 45 */    this.ifToNames.put(Integer.valueOf(1), "InternetGatewayDevice.DeviceInfo.wireless_ssid2");
/*  46: 46 */    this.ifToNames.put(Integer.valueOf(2), "InternetGatewayDevice.DeviceInfo.wireless_ssid_5G");
/*  47: 47 */    this.ifToNames.put(Integer.valueOf(3), "InternetGatewayDevice.DeviceInfo.wireless_ssid2_5G");
/*  48: 48 */    this.ifToNames.put(Integer.valueOf(255), "InternetGatewayDevice.DeviceInfo.lan_auth");
/*  49:    */    
/*  50: 50 */    SoofacACS acs = (SoofacACS)SysConfigHelper.getBean("soofacACS");
/*  51: 51 */    if (acs.getRealm().startsWith("qhwifi")) {
/*  52: 52 */      this.cq = new MySQLCommandQueue();
/*  53:    */    } else
/*  54: 54 */      this.cq = new ConcurrentLinkedCommandQueue();
/*  55:    */  }
/*  56:    */  
/*  57:    */  public int getIfByName(String name) {
/*  58: 58 */    for (Iterator localIterator = this.ifToNames.keySet().iterator(); localIterator.hasNext();) { int ife = ((Integer)localIterator.next()).intValue();
/*  59: 59 */      if (((String)this.ifToNames.get(Integer.valueOf(ife))).equals(name))
/*  60: 60 */        return ife;
/*  61:    */    }
/*  62: 62 */    return -1;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public String getIfName(int ife) {
/*  66: 66 */    return (String)this.ifToNames.get(Integer.valueOf(ife));
/*  67:    */  }
/*  68:    */  
/*  69:    */  public int delete(String[] ids)
/*  70:    */  {
/*  71: 71 */    java.util.List<com.soofound.cpe.bean.HostBean> beans = ((com.soofound.cpe.dao.HostDao)this.dao).findByIDs(ids);
/*  72: 72 */    java.util.List<String> sqls = new ArrayList(beans.size());
/*  73: 73 */    for (com.soofound.cpe.bean.HostBean bean : beans) {
/*  74: 74 */      StringBuilder logSQL = new StringBuilder();
/*  75: 75 */      logSQL.append("INSERT INTO admin_log(id,username,fullname,operation,ip,log_time)VALUES ('").append(DateUtil.getCurrentTimeAsID());
/*  76: 76 */      logSQL.append("','").append(this.currentUser.getUsername()).append("','").append(this.currentUser.getFullname()).append("','");
/*  77: 77 */      logSQL.append("删除AP[").append(bean.getSerialNumber()).append("]','").append(this.currentUser.getIpAddress()).append("',now())");
/*  78: 78 */      sqls.add(logSQL.toString());
/*  79:    */    }
/*  80: 80 */    this.jdbc.batchUpdate(CommonUtil.toArray(sqls));
/*  81: 81 */    return ((com.soofound.cpe.dao.HostDao)this.dao).delete(ids);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public java.util.List<String> getHardwares() {
/*  85: 85 */    return ((com.soofound.cpe.dao.HostDao)this.dao).getHardwares();
/*  86:    */  }
/*  87:    */  
/*  88:    */  public com.soofound.cpe.bean.HostBean findBySerialNumber(String serialNo) {
/*  89: 89 */    return ((com.soofound.cpe.dao.HostDao)this.dao).findBySerialNumber(serialNo);
/*  90:    */  }
/*  91:    */  
/*  92:    */  public com.soofound.cpe.bean.HostBean findByApkey(String apkey) {
/*  93: 93 */    return ((com.soofound.cpe.dao.HostDao)this.dao).findByApkey(apkey);
/*  94:    */  }
/*  95:    */  
/*  96:    */  public int getDeviceTotal(String branchId) {
/*  97: 97 */    return ((com.soofound.cpe.dao.HostDao)this.dao).getDeviceTotal(branchId, null);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public java.util.List<com.soofound.cpe.bean.HostBean> getOnlineHosts() {
/* 101:101 */    return ((com.soofound.cpe.dao.HostDao)this.dao).getOnlineHosts();
/* 102:    */  }
/* 103:    */  
/* 104:    */  public java.util.List<com.soofound.cpe.bean.HostBean> findByBranch(String branchId, String gid) {
/* 105:105 */    return ((com.soofound.cpe.dao.HostDao)this.dao).findByBranch(branchId, gid);
/* 106:    */  }
/* 107:    */  
/* 108:    */  public int updateStatus(com.soofound.cpe.bean.HostBean bean) {
/* 109:109 */    return ((com.soofound.cpe.dao.HostDao)this.dao).updateStatus(bean);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public java.util.Map<String, Object> getRichBeans(int pp, int cp, java.util.Map<String, String> options) {
/* 113:113 */    java.util.Map<String, Object> results = new HashMap();
/* 114:114 */    String gid = (String)options.get("gid");
/* 115:115 */    if (gid == null) { gid = "0";
/* 116:    */    }
/* 117:117 */    java.util.List<com.soofound.cpe.bean.HostBean> beans = ((com.soofound.cpe.dao.HostDao)this.dao).listByPage(pp, cp, options);
/* 118:118 */    java.util.List<com.soofound.cpe.bean.RichHostBean> rhbs = new ArrayList();
/* 119:119 */    results.put("page", ((com.soofound.cpe.dao.HostDao)this.dao).getPagination());
/* 120:120 */    results.put("data", rhbs);
/* 121:121 */    if (beans.isEmpty()) {
/* 122:122 */      return results;
/* 123:    */    }
/* 124:124 */    StringBuilder hostIds = new StringBuilder(100);
/* 125:125 */    for (com.soofound.cpe.bean.HostBean bean : beans)
/* 126:126 */      hostIds.append(",").append(bean.getId());
/* 127:127 */    String idstr = hostIds.substring(1);
/* 128:128 */    Object hpbs = this.hpdao.findByHosts(idstr, "13,17,18,22,28");
/* 129:129 */    com.soofound.cpe.bean.RichHostBean rhb; for (com.soofound.cpe.bean.HostBean bean : beans) {
/* 130:130 */      rhb = new com.soofound.cpe.bean.RichHostBean(bean);
/* 131:131 */      for (HostPropertyBean hpb : (java.util.List)hpbs) {
/* 132:132 */        if (hpb.getHostId() == bean.getId()) {
/* 133:    */          try {
/* 134:134 */            String met = "set" + hpb.getName().split("\\.")[2].replace("_", "");
/* 135:135 */            Method method = CommonUtil.lookupMethod(com.soofound.cpe.bean.RichHostBean.class, met);
/* 136:136 */            if (method != null)
/* 137:137 */              method.invoke(rhb, new Object[] { hpb.getValue() });
/* 138:    */          } catch (Exception ex) {
/* 139:139 */            ex.printStackTrace();
/* 140:    */          }
/* 141:    */        }
/* 142:    */      }
/* 143:143 */      rhbs.add(rhb);
/* 144:    */    }
/* 145:145 */    if ("2".equals(options.get("tag"))) {
/* 146:146 */      java.util.List<com.soofound.cpe.bean.BssidDto> dtos = this.bdao.findByHosts(idstr);
/* 147:147 */      for (rhb = rhbs.iterator(); rhb.hasNext(); 
/* 148:148 */          ???.hasNext())
/* 149:    */      {
/* 150:147 */        com.soofound.cpe.bean.RichHostBean rhb = (com.soofound.cpe.bean.RichHostBean)rhb.next();
/* 151:148 */        ??? = dtos.iterator();continue;com.soofound.cpe.bean.BssidDto dto = (com.soofound.cpe.bean.BssidDto)???.next();
/* 152:149 */        if ((rhb.getId() == dto.getApId()) && (dto.getIfe() == 0)) {
/* 153:150 */          rhb.setSsidName(dto.getCodeName());
/* 154:151 */          rhb.setPolicyName(dto.getPolicyName());
/* 155:152 */          rhb.setPortalName(dto.getPortalName());
/* 156:    */        }
/* 157:    */      }
/* 158:    */    }
/* 159:    */    
/* 160:157 */    return results;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public void doFactoryReset(int hostId) {
/* 164:161 */    java.util.List<String> sqls = new ArrayList();
/* 165:162 */    sqls.add("update cpe_host set online = 4,ssid='',channel='',trace='off' where id=" + hostId);
/* 166:163 */    sqls.add("update cpe_host set detect='off' where hardware_version like '%-M'");
/* 167:164 */    sqls.add("delete from cpe_ssid where ap_id=" + hostId);
/* 168:165 */    this.jdbc.batchUpdate(CommonUtil.toArray(sqls));
/* 169:    */  }
/* 170:    */  
/* 171:    */  public void updateName(com.soofound.cpe.bean.HostBean host) {
/* 172:169 */    StringBuilder sqlText = new StringBuilder(100);
/* 173:170 */    sqlText.append("update cpe_host set name='").append(host.getName());
/* 174:171 */    sqlText.append("' where id=").append(host.getId());
/* 175:172 */    this.jdbc.update(sqlText.toString());
/* 176:173 */    this.ccdao.updateAlias(host.getSerialNumber(), host.getName());
/* 177:    */  }
/* 178:    */  
/* 179:    */  public java.util.List<com.soofound.cpe.bean.HostBean> findByBranchGroup(String gid) {
/* 180:177 */    return ((com.soofound.cpe.dao.HostDao)this.dao).findByBranchGroup(gid);
/* 181:    */  }
/* 182:    */  
/* 185:    */  public void putCommand(int hostId, String command)
/* 186:    */  {
/* 187:184 */    this.cq.putCommand(hostId, command);
/* 188:    */  }
/* 189:    */  
/* 190:    */  public boolean isEmptyCommand(int hostId) {
/* 191:188 */    return this.cq.isEmptyCommand(hostId);
/* 192:    */  }
/* 193:    */  
/* 194:    */  public void clearCommand(int hostId) {
/* 195:192 */    this.cq.clearCommand(hostId);
/* 196:    */  }
/* 197:    */  
/* 198:    */  public String getCommand(int hostId) {
/* 199:196 */    return this.cq.getCommand(hostId);
/* 200:    */  }
/* 201:    */  
/* 202:    */  public void putDebugResult(int hostId, String cmdResult) {
/* 203:200 */    this.cq.putDebugResult(hostId, cmdResult);
/* 204:    */  }
/* 205:    */  
/* 206:    */  public String getDebugResult(int hostId) {
/* 207:204 */    return this.cq.getDebugResult(hostId);
/* 208:    */  }
/* 209:    */  
/* 210:    */  public String removeDebugResult(int hostId) {
/* 211:208 */    return this.cq.removeDebugResult(hostId);
/* 212:    */  }
/* 213:    */  
/* 222:    */  @Autowired
/* 223:    */  protected void setDao(com.soofound.cpe.dao.HostDao dao)
/* 224:    */  {
/* 225:222 */    this.dao = dao;
/* 226:    */  }
/* 227:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.HostService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */