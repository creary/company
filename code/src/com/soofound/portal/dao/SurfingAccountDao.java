/*   1:    */package com.soofound.portal.dao;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.BaseDao;
/*   4:    */import com.soofound.framework.util.CommonUtil;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.util.List;
/*   7:    */import java.util.Map;
/*   8:    */import org.springframework.dao.EmptyResultDataAccessException;
/*   9:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  10:    */import org.springframework.stereotype.Component;
/*  11:    */
/*  12:    */@Component
/*  13:    */public final class SurfingAccountDao extends BaseDao<com.soofound.portal.bean.SurfingAccount>
/*  14:    */{
/*  15:    */  protected String getQuerySQL(Map<String, String> options)
/*  16:    */  {
/*  17: 17 */    StringBuffer sqlText = new StringBuffer(100);
/*  18: 18 */    sqlText.append("select * from view_surfing_account where 1=1 ");
/*  19: 19 */    if (!CommonUtil.isEmpty((String)options.get("username")))
/*  20: 20 */      sqlText.append(" and username like '%").append((String)options.get("username")).append("%'");
/*  21: 21 */    if (!CommonUtil.isEmpty((String)options.get("flag")))
/*  22: 22 */      sqlText.append(" and flag = '").append((String)options.get("flag")).append("'");
/*  23: 23 */    if (!CommonUtil.isEmpty((String)options.get("mac")))
/*  24: 24 */      sqlText.append(" and mac like '%").append((String)options.get("mac")).append("%'");
/*  25: 25 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/*  26: 26 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/*  27: 27 */    if (!CommonUtil.isEmpty((String)options.get("branch")))
/*  28: 28 */      sqlText.append(" and branch like '%").append((String)options.get("branch")).append("%'");
/*  29: 29 */    sqlText.append(" order by online desc,create_time desc");
/*  30: 30 */    return sqlText.toString();
/*  31:    */  }
/*  32:    */  
/*  33:    */  public int update(com.soofound.portal.bean.SurfingAccount dto)
/*  34:    */  {
/*  35:    */    try {
/*  36: 36 */      return getJdbcTemplate().update(getUpdateSQL(dto));
/*  37:    */    } catch (Exception use) {
/*  38: 38 */      System.out.println("update nickname=" + dto.getNickname());
/*  39: 39 */      dto.setNickname(""); }
/*  40: 40 */    return getJdbcTemplate().update(getUpdateSQL(dto));
/*  41:    */  }
/*  42:    */  
/*  43:    */  public int updateTimes(com.soofound.portal.bean.SurfingAccount dto)
/*  44:    */  {
/*  45: 45 */    StringBuilder sqlText = new StringBuilder(200);
/*  46: 46 */    sqlText.append("update surfing_account set online=1,times=times + 1,mac='").append(dto.getMac()).append("' where username='");
/*  47: 47 */    sqlText.append(dto.getUsername()).append("' and branch_id='").append(dto.getBranchId()).append("'");
/*  48: 48 */    return getJdbcTemplate().update(sqlText.toString());
/*  49:    */  }
/*  50:    */  
/*  51:    */  public synchronized int save(com.soofound.portal.bean.SurfingAccount dto)
/*  52:    */  {
/*  53: 53 */    if (dto.getOpenId() == null)
/*  54: 54 */      dto.setOpenId("");
/*  55: 55 */    if (dto.getNickname() == null)
/*  56: 56 */      dto.setNickname("");
/*  57: 57 */    if (dto.getMac() == null)
/*  58: 58 */      dto.setMac("");
/*  59:    */    try {
/*  60: 60 */      StringBuilder sqlText = new StringBuilder(100);
/*  61: 61 */      sqlText.append("delete from surfing_account where username='").append(dto.getUsername()).append("' and branch_id='");
/*  62: 62 */      sqlText.append(dto.getBranchId()).append("'");
/*  63: 63 */      getJdbcTemplate().update(sqlText.toString());
/*  64:    */      
/*  65: 65 */      return getJdbcTemplate().update(getInsertSQL(dto));
/*  66:    */    } catch (Exception use) {
/*  67: 67 */      System.out.println("save nickname=" + dto.getNickname());
/*  68: 68 */      dto.setNickname(""); }
/*  69: 69 */    return getJdbcTemplate().update(getInsertSQL(dto));
/*  70:    */  }
/*  71:    */  
/*  72:    */  private String getUpdateSQL(com.soofound.portal.bean.SurfingAccount dto)
/*  73:    */  {
/*  74: 74 */    StringBuilder sqlText = new StringBuilder(200);
/*  75: 75 */    sqlText.append("update surfing_account set ");
/*  76: 76 */    if (dto.getPassword() != null)
/*  77: 77 */      sqlText.append("password='").append(dto.getPassword()).append("',");
/*  78: 78 */    if (dto.getNickname() != null)
/*  79: 79 */      sqlText.append("nickname='").append(dto.getNickname()).append("',");
/*  80: 80 */    if (dto.getOpenId() != null)
/*  81: 81 */      sqlText.append("open_id='").append(dto.getOpenId()).append("',");
/*  82: 82 */    if (dto.getFlag() != null)
/*  83: 83 */      sqlText.append("flag='").append(dto.getFlag()).append("',");
/*  84: 84 */    if ((dto.getMac() != null) && (!"null".equals(dto.getMac())))
/*  85: 85 */      sqlText.append("mac='").append(dto.getMac()).append("',");
/*  86: 86 */    if (dto.getBranchId() != null)
/*  87: 87 */      sqlText.append("branch_id='").append(dto.getBranchId()).append("',");
/*  88: 88 */    if (dto.getCreateTime() != null)
/*  89: 89 */      sqlText.append("create_time='").append(dto.getCreateTime()).append("',");
/*  90: 90 */    if (dto.getTimes() > 0)
/*  91: 91 */      sqlText.append("times=").append(dto.getTimes()).append(",");
/*  92: 92 */    if (dto.isUpdateSpeed())
/*  93: 93 */      sqlText.append("up_speed=").append(dto.getUpSpeed()).append(",down_speed=").append(dto.getDownSpeed()).append(",");
/*  94: 94 */    String sql = sqlText.substring(0, sqlText.length() - 1) + " where id=" + dto.getId();
/*  95: 95 */    return sql;
/*  96:    */  }
/*  97:    */  
/*  98:    */  private String getInsertSQL(com.soofound.portal.bean.SurfingAccount dto) {
/*  99: 99 */    StringBuilder sqlText = new StringBuilder(200);
/* 100:100 */    sqlText.append("insert into surfing_account(id,username,mac,branch_id,password,flag,open_id,nickname,create_time,times,online,up_speed,down_speed)values(");
/* 101:101 */    sqlText.append(getID("surfing_account")).append(",'").append(dto.getUsername()).append("','").append(dto.getMac()).append("','");
/* 102:102 */    sqlText.append(dto.getBranchId()).append("','").append(dto.getPassword()).append("','").append(dto.getFlag()).append("','");
/* 103:103 */    sqlText.append(dto.getOpenId()).append("','").append(dto.getNickname()).append("',now(),").append(dto.getTimes()).append(",");
/* 104:104 */    sqlText.append(dto.getOnline()).append(",0,0)");
/* 105:105 */    return sqlText.toString();
/* 106:    */  }
/* 107:    */  
/* 108:    */  public com.soofound.portal.bean.SurfingAccount findByID(String id)
/* 109:    */  {
/* 110:110 */    String sqlText = "SELECT * FROM view_surfing_account WHERE id=" + id;
/* 111:111 */    return (com.soofound.portal.bean.SurfingAccount)super.findOne(sqlText);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public List<com.soofound.portal.bean.SurfingAccount> getSurfingAccounts(String[] ids) {
/* 115:115 */    String sqlText = "SELECT * FROM view_surfing_account WHERE id in (" + CommonUtil.arrayToString(ids) + ")";
/* 116:116 */    return super.findByCriteria(sqlText);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public synchronized com.soofound.portal.bean.SurfingAccount findByUsername(String branchId, String username) {
/* 120:120 */    String sqlText = "SELECT * FROM view_surfing_account WHERE branch_id='" + branchId + "' AND username='" + username + "'";
/* 121:121 */    return (com.soofound.portal.bean.SurfingAccount)super.findOne(sqlText);
/* 122:    */  }
/* 123:    */  
/* 124:    */  public com.soofound.portal.bean.SurfingAccount findByOpenId(String openId) {
/* 125:125 */    String sqlText = "SELECT * FROM view_surfing_account WHERE open_id='" + openId + "'";
/* 126:126 */    return (com.soofound.portal.bean.SurfingAccount)super.findOne(sqlText);
/* 127:    */  }
/* 128:    */  
/* 129:    */  public com.soofound.portal.bean.SurfingAccount findByWechat(String branchId, String mac) {
/* 130:130 */    String sqlText = "SELECT * FROM view_surfing_account WHERE branch_id='" + branchId + "' AND mac='" + mac + "' AND flag = 'wechat'";
/* 131:131 */    return (com.soofound.portal.bean.SurfingAccount)super.findOne(sqlText);
/* 132:    */  }
/* 133:    */  
/* 134:    */  public void deleteOpenId(String openId) {
/* 135:135 */    String sqlText = "DELETE FROM surfing_account WHERE open_id='" + openId + "'";
/* 136:136 */    super.getJdbcTemplate().update(sqlText);
/* 137:    */  }
/* 138:    */  
/* 139:    */  public com.soofound.portal.bean.SurfingAccount findByAuth(String branchId, String username, String password) {
/* 140:140 */    StringBuilder sqlText = new StringBuilder(100);
/* 141:141 */    sqlText.append("SELECT * FROM view_surfing_account WHERE branch_id='").append(branchId).append("' and username='");
/* 142:142 */    sqlText.append(username).append("' and password='").append(password).append("'");
/* 143:143 */    return (com.soofound.portal.bean.SurfingAccount)super.findOne(sqlText.toString());
/* 144:    */  }
/* 145:    */  
/* 146:    */  public List<com.soofound.portal.bean.SurfingAccount> findByBranch(String branchId, String online) {
/* 147:147 */    StringBuilder sqlText = new StringBuilder(100);
/* 148:148 */    sqlText.append("SELECT * FROM view_surfing_account WHERE branch_id like '").append(branchId).append("%'");
/* 149:149 */    if (online != null)
/* 150:150 */      sqlText.append(" AND online = ").append(online);
/* 151:151 */    sqlText.append(" order by online desc,create_time desc");
/* 152:152 */    return super.findByCriteria(sqlText.toString());
/* 153:    */  }
/* 154:    */  
/* 155:    */  public List<com.soofound.portal.bean.SurfingAccount> getSurfingAccountsByType(String branchId, String type) {
/* 156:156 */    String sqlText = "SELECT * FROM view_surfing_account WHERE branch_id='" + branchId + "' AND flag = '" + type + "'";
/* 157:157 */    return super.findByCriteria(sqlText);
/* 158:    */  }
/* 159:    */  
/* 160:    */  public com.soofound.portal.bean.SurfingAccount findByMac(String branchId, String mac) {
/* 161:161 */    String sqlText = "SELECT * FROM view_surfing_account WHERE mac='" + mac + "' AND branch_id='" + branchId + "'";
/* 162:162 */    return (com.soofound.portal.bean.SurfingAccount)super.findOne(sqlText);
/* 163:    */  }
/* 164:    */  
/* 165:    */  public int getWifiUserCount(String branchId, String tag) {
/* 166:166 */    StringBuilder sqlText = new StringBuilder(200);
/* 167:167 */    sqlText.append("SELECT COUNT(DISTINCT mac) FROM ");
/* 168:168 */    if ("1".equals(tag)) {
/* 169:169 */      sqlText.append(" surfing_session ");
/* 170:    */    } else
/* 171:171 */      sqlText.append(" surfing_session_history ");
/* 172:172 */    sqlText.append(" WHERE branch_id like '").append(branchId).append("%'");
/* 173:173 */    if ("1".equals(tag))
/* 174:174 */      sqlText.append(" AND state > 0");
/* 175:    */    try {
/* 176:176 */      return ((Integer)getJdbcTemplate().queryForObject(sqlText.toString(), Integer.class)).intValue();
/* 177:    */    } catch (EmptyResultDataAccessException ex) {}
/* 178:178 */    return 0;
/* 179:    */  }
/* 180:    */  
/* 181:    */  public int getNextSequence(String branchId)
/* 182:    */  {
/* 183:183 */    String sql = "select sequence_id from surfing_sequence_account where branch_id='" + branchId + "'";
/* 184:    */    try {
/* 185:185 */      int seqId = ((Integer)getJdbcTemplate().queryForObject(sql, Integer.class)).intValue();
/* 186:186 */      sql = "update surfing_sequence_account set sequence_id = sequence_id + 1,log_time=now() WHERE branch_id='" + branchId + "'";
/* 187:187 */      getJdbcTemplate().update(sql);
/* 188:188 */      return seqId;
/* 189:    */    } catch (Exception e) {
/* 190:190 */      sql = "insert into surfing_sequence_account(branch_id,sequence_id,log_time)values('" + branchId + "',1,now())";
/* 191:191 */      getJdbcTemplate().update(sql); }
/* 192:192 */    return 1;
/* 193:    */  }
/* 194:    */  
/* 195:    */  public synchronized String getNextWX(String branchId)
/* 196:    */  {
/* 197:197 */    String sql = "SELECT username FROM surfing_account WHERE username LIKE 'wx%' AND flag='wechat' AND branch_id='" + branchId + "' ORDER BY username DESC limit 1";
/* 198:    */    try {
/* 199:199 */      String seqId = (String)getJdbcTemplate().queryForObject(sql, String.class);
/* 200:200 */      int nextId = Integer.parseInt(seqId.substring(2)) + 1;
/* 201:201 */      if (nextId < 10) {
/* 202:202 */        seqId = "wx000" + nextId;
/* 203:203 */      } else if (nextId < 100) {
/* 204:204 */        seqId = "wx00" + nextId;
/* 205:205 */      } else if (nextId < 1000) {
/* 206:206 */        seqId = "wx0" + nextId;
/* 207:207 */      } else if (nextId < 10000) {
/* 208:208 */        seqId = "wx" + nextId;
/* 209:    */      } else
/* 210:210 */        sql = "delete FROM surfing_account WHERE username LIKE 'wx%' AND flag='wechat' AND branch_id='" + branchId + "'";
/* 211:211 */      return "wx0001";
/* 212:    */    }
/* 213:    */    catch (Exception ex)
/* 214:    */    {
/* 215:215 */      ex.printStackTrace(); }
/* 216:216 */    return "wx0001";
/* 217:    */  }
/* 218:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.SurfingAccountDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */