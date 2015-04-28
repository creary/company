/*   1:    */package com.soofound.cpe.dao;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.HostBean;
/*   4:    */import com.soofound.cpe.util.DeviceTree;
/*   5:    */import com.soofound.framework.jdbc.BaseDao;
/*   6:    */import com.soofound.framework.util.CommonUtil;
/*   7:    */import com.soofound.framework.util.SysConfigHelper;
/*   8:    */import com.soofound.portal.custom.CustomFactory;
/*   9:    */import java.io.PrintStream;
/*  10:    */import java.sql.ResultSet;
/*  11:    */import java.sql.SQLException;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.List;
/*  14:    */import java.util.Map;
/*  15:    */import org.springframework.dao.DataAccessException;
/*  16:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  17:    */import org.springframework.jdbc.core.ResultSetExtractor;
/*  18:    */import org.springframework.stereotype.Component;
/*  19:    */import org.springframework.util.StringUtils;
/*  20:    */
/*  25:    */@Component
/*  26:    */public final class HostDao
/*  27:    */  extends BaseDao<HostBean>
/*  28:    */{
/*  29:    */  private static final String SQL_HARDWARE = "SELECT DISTINCT hardware_version FROM view_cpe_host";
/*  30:    */  private static final String SQL_ONE_HOST = "SELECT * FROM cpe_host WHERE serial_number=? limit 1";
/*  31:    */  private static final String SQL_GROUP_HOST = "SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN (SELECT a.group_id,a.ap_id FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id LEFT JOIN admin_branch c ON b.branch_id=c.id WHERE branch_id='$bid') d ON a.id=d.ap_id";
/*  32:    */  private static final String SQL_GROUP_HOST2 = "SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN membership_branch_group d ON b.id=d.branch_id WHERE d.group_id='";
/*  33:    */  
/*  34:    */  protected String getQuerySQL(Map<String, String> options)
/*  35:    */  {
/*  36: 36 */    CustomFactory fac = (CustomFactory)SysConfigHelper.getBean("customFactory");
/*  37: 37 */    return fac.getDeviceTree().getQuerySQL(options);
/*  38:    */  }
/*  39:    */  
/*  40:    */  protected String getOfflineQuerySQL(Map<String, String> options) {
/*  41: 41 */    StringBuffer sqlText = new StringBuffer(100);
/*  42: 42 */    sqlText.append("select a.*,b.name branch from cpe_host a left join admin_branch b on a.branch_id=b.id");
/*  43: 43 */    sqlText.append(" where a.online = 0 ");
/*  44: 44 */    if (!CommonUtil.isEmpty((String)options.get("serialNumber")))
/*  45: 45 */      sqlText.append(" and a.serial_number like '%").append((String)options.get("serialNumber")).append("%'");
/*  46: 46 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/*  47: 47 */      sqlText.append(" and a.name like '%").append((String)options.get("name")).append("%'");
/*  48: 48 */    if (!CommonUtil.isEmpty((String)options.get("gid")))
/*  49: 49 */      sqlText.append(" and a.branch_id like '").append((String)options.get("gid")).append("%'");
/*  50: 50 */    sqlText.append(" order by branch_id,last_time desc");
/*  51: 51 */    return sqlText.toString();
/*  52:    */  }
/*  53:    */  
/*  54:    */  public int updateStatus(HostBean bean) {
/*  55: 55 */    StringBuilder sqlText = new StringBuilder(100);
/*  56: 56 */    sqlText.append("update cpe_host set online=").append(bean.getOnline());
/*  57: 57 */    sqlText.append(",up_time=now() where id=").append(bean.getId());
/*  58: 58 */    return getJdbcTemplate().update(sqlText.toString());
/*  59:    */  }
/*  60:    */  
/*  61:    */  public int update(HostBean bean)
/*  62:    */  {
/*  63: 63 */    StringBuilder sqlText = new StringBuilder(200);
/*  64: 64 */    sqlText.append("update cpe_host set up_time=now(),last_time=now(),mode='").append(bean.getMode());
/*  65: 65 */    sqlText.append("',software_version='").append(bean.getSoftwareVersion());
/*  66: 66 */    sqlText.append("',hardware_version='").append(bean.getHardwareVersion());
/*  67: 67 */    sqlText.append("',product_class='").append(bean.getProductClass());
/*  68: 68 */    sqlText.append("',ip_address='").append(bean.getIpAddress());
/*  69: 69 */    sqlText.append("',branch_id='").append(bean.getBranchId());
/*  70: 70 */    sqlText.append("' where id=").append(bean.getId());
/*  71: 71 */    return super.saveOrUpdate(sqlText.toString());
/*  72:    */  }
/*  73:    */  
/*  74:    */  public int save(HostBean bean)
/*  75:    */  {
/*  76:    */    try {
/*  77: 77 */      StringBuffer sqlText = new StringBuffer(200);
/*  78: 78 */      sqlText.append("insert into cpe_host(id,serial_number,branch_id,hardware_version,software_version,ssid,channel,name,ip_address,stun,");
/*  79: 79 */      sqlText.append("mode,up_time,last_time,online,product_class,trace,detect)VALUES(").append(bean.getId()).append(",'").append(bean.getSerialNumber()).append("','");
/*  80: 80 */      sqlText.append(bean.getBranchId()).append("','").append(bean.getHardwareVersion()).append("','").append(bean.getSoftwareVersion());
/*  81: 81 */      sqlText.append("','").append(bean.getSsid()).append("','").append(bean.getChannel()).append("','").append(bean.getName()).append("','");
/*  82: 82 */      sqlText.append(bean.getIpAddress()).append("','").append(bean.getStun()).append("','").append(bean.getMode()).append("',now(),now(),");
/*  83: 83 */      sqlText.append(bean.getOnline()).append(",'").append(bean.getProductClass()).append("','").append(bean.getTrace()).append("','");
/*  84: 84 */      sqlText.append(bean.getHardwareVersion().endsWith("-M") ? "off" : "").append("')");
/*  85: 85 */      return getJdbcTemplate().update(sqlText.toString());
/*  86:    */    } catch (Exception ex) {
/*  87: 87 */      System.out.println(bean.getSerialNumber() + " exists..."); }
/*  88: 88 */    return 0;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public HostBean findBySerialNumber(String serialNo)
/*  92:    */  {
/*  93:    */    try {
/*  94: 94 */      return (HostBean)getJdbcTemplate().queryForObject("SELECT * FROM cpe_host WHERE serial_number=? limit 1", new String[] { serialNo }, getRowMapper());
/*  95:    */    } catch (Exception ex) {}
/*  96: 96 */    return null;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public HostBean findByApkey(String apkey)
/* 100:    */  {
/* 101:101 */    String sql = "select a.* from cpe_host a,admin_license b where a.serial_number=b.mac and b.apkey='" + apkey + "'";
/* 102:    */    try {
/* 103:103 */      return (HostBean)getJdbcTemplate().queryForObject(sql, getRowMapper());
/* 104:    */    }
/* 105:    */    catch (Exception ex) {}
/* 106:106 */    return null;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public HostBean findByID(String id)
/* 110:    */  {
/* 111:111 */    String sqlText = "select * from view_cpe_host where id=" + id;
/* 112:112 */    return (HostBean)super.findOne(sqlText);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public List<HostBean> findByIDs(String[] ids) {
/* 116:116 */    String sqlText = "select * from cpe_host where id in (" + CommonUtil.arrayToString(ids) + ")";
/* 117:117 */    return super.findByCriteria(sqlText);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public List<HostBean> findByIDs(String ids) {
/* 121:121 */    String sqlText = "select * from cpe_host where id in (" + ids + ")";
/* 122:122 */    return super.findByCriteria(sqlText);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public List<String> getHardwares() {
/* 126:126 */    List<String> flashs = (List)super.getJdbcTemplate().query("SELECT DISTINCT hardware_version FROM view_cpe_host", new ResultSetExtractor() {
/* 127:    */      public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 128:128 */        List<String> flashs = new ArrayList();
/* 129:129 */        while (rs.next()) {
/* 130:130 */          if (StringUtils.hasText(rs.getString(1)))
/* 131:131 */            flashs.add(rs.getString(1));
/* 132:    */        }
/* 133:133 */        return flashs;
/* 134:134 */      } } );
/* 135:135 */    return flashs;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public int getDeviceTotal(String branchId, String online) {
/* 139:139 */    StringBuilder sqlText = new StringBuilder(100);
/* 140:140 */    sqlText.append("SELECT COUNT(*) FROM cpe_host WHERE branch_id like '").append(branchId);
/* 141:141 */    sqlText.append("%'");
/* 142:142 */    if (online != null)
/* 143:143 */      sqlText.append(" AND online = ").append(online);
/* 144:    */    try {
/* 145:145 */      return ((Integer)super.getJdbcTemplate().queryForObject(sqlText.toString(), Integer.class)).intValue();
/* 146:    */    } catch (Exception e) {}
/* 147:147 */    return 0;
/* 148:    */  }
/* 149:    */  
/* 153:    */  public List<HostBean> findByBranchGroup(String gid)
/* 154:    */  {
/* 155:155 */    return super.findByCriteria("SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN membership_branch_group d ON b.id=d.branch_id WHERE d.group_id='" + gid + "'");
/* 156:    */  }
/* 157:    */  
/* 158:    */  public List<HostBean> findByBranch(String branchId, String gid) {
/* 159:159 */    StringBuilder sqlText = new StringBuilder(200);
/* 160:160 */    sqlText.append("SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN (SELECT a.group_id,a.ap_id FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id LEFT JOIN admin_branch c ON b.branch_id=c.id WHERE branch_id='$bid') d ON a.id=d.ap_id".replace("$bid", branchId));
/* 161:161 */    sqlText.append(" where branch_id like '").append(branchId).append("%'");
/* 162:162 */    if (gid != null) {
/* 163:163 */      if ((!"ungrouped".equals(gid)) && (!"0".equals(gid))) {
/* 164:164 */        sqlText.append(" and group_id = '").append(gid).append("'");
/* 165:165 */      } else if ("ungrouped".equals(gid))
/* 166:166 */        sqlText.append(" and group_id is null");
/* 167:    */    }
/* 168:168 */    return super.findByCriteria(sqlText.toString());
/* 169:    */  }
/* 170:    */  
/* 171:    */  public HostBean findByName(String branchId, String name) {
/* 172:172 */    StringBuilder sqlText = new StringBuilder(200);
/* 173:173 */    sqlText.append("select * from cpe_host where branch_id='").append(branchId).append("' and (name like '%");
/* 174:174 */    sqlText.append(name).append("%' or serial_number like '%").append(name).append("%')");
/* 175:175 */    return (HostBean)super.findOne(sqlText.toString());
/* 176:    */  }
/* 177:    */  
/* 178:    */  public List<HostBean> getOnlineHosts() {
/* 179:179 */    String sql = "select * from cpe_host where online > 0";
/* 180:180 */    return super.findByCriteria(sql);
/* 181:    */  }
/* 182:    */  
/* 183:    */  public synchronized int getNextID() {
/* 184:184 */    Integer id = (Integer)super.getJdbcTemplate().queryForObject("select max(id) + 1 from cpe_host", Integer.class);
/* 185:185 */    if (id == null)
/* 186:186 */      return 1;
/* 187:187 */    return id.intValue();
/* 188:    */  }
/* 189:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.dao.HostDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */