/*   1:    */package com.soofound.admin.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.BaseDao;
/*   4:    */import com.soofound.framework.util.CommonUtil;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.sql.SQLException;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.List;
/*   9:    */import java.util.Map;
/*  10:    */import org.springframework.dao.DataAccessException;
/*  11:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  12:    */import org.springframework.jdbc.core.ResultSetExtractor;
/*  13:    */import org.springframework.stereotype.Component;
/*  14:    */
/*  15:    */@Component
/*  16:    */public final class GroupDao extends BaseDao<GroupDto>
/*  17:    */{
/*  18:    */  private static final int CODE_LENGTH = 4;
/*  19:    */  private static final String MEMBERSHIP_SQL = "SELECT a.*,b.branch_id,b.name FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id ORDER BY branch_id,group_id";
/*  20:    */  private static final String MEMBERSHIP_SQL2 = "SELECT a.*,b.name FROM membership_branch_group a LEFT JOIN admin_group b ON a.group_id=b.id WHERE a.branch_id='${bid}' ORDER BY branch_id,group_id";
/*  21:    */  
/*  22:    */  protected String getQuerySQL(Map<String, String> options)
/*  23:    */  {
/*  24: 24 */    StringBuffer sqlText = new StringBuffer(100);
/*  25: 25 */    sqlText.append("select * from view_admin_group where 1 = 1");
/*  26: 26 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/*  27: 27 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/*  28: 28 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/*  29: 29 */      sqlText.append(" and name like '%").append((String)options.get("name")).append("%'");
/*  30: 30 */    if (!CommonUtil.isEmpty((String)options.get("branch")))
/*  31: 31 */      sqlText.append(" and branch like '%").append((String)options.get("branch")).append("%'");
/*  32: 32 */    sqlText.append(" order by id");
/*  33: 33 */    return sqlText.toString();
/*  34:    */  }
/*  35:    */  
/*  36:    */  public int update(GroupDto bgd)
/*  37:    */  {
/*  38: 38 */    StringBuffer sqlText = new StringBuffer(100);
/*  39: 39 */    sqlText.append("update admin_group set name='").append(bgd.getName());
/*  40: 40 */    sqlText.append("' where id='").append(bgd.getId()).append("'");
/*  41: 41 */    return getJdbcTemplate().update(sqlText.toString());
/*  42:    */  }
/*  43:    */  
/*  44:    */  public GroupDto findByID(String id)
/*  45:    */  {
/*  46: 46 */    StringBuilder sqlText = new StringBuilder(100);
/*  47: 47 */    sqlText.append("SELECT * FROM admin_group WHERE id = '").append(id).append("'");
/*  48: 48 */    return (GroupDto)findOne(sqlText.toString());
/*  49:    */  }
/*  50:    */  
/*  51:    */  public List<GroupDto> findByGroup(String groupId) {
/*  52: 52 */    StringBuilder sqlText = new StringBuilder(100);
/*  53: 53 */    sqlText.append("SELECT * FROM admin_group WHERE id LIKE '").append(groupId).append("%' order by id");
/*  54: 54 */    return findByCriteria(sqlText.toString());
/*  55:    */  }
/*  56:    */  
/*  57:    */  public List<GroupDto> findByBranch(String branchId) {
/*  58: 58 */    StringBuilder sqlText = new StringBuilder(100);
/*  59: 59 */    sqlText.append("select * from view_admin_group ");
/*  60: 60 */    if (branchId != null)
/*  61: 61 */      sqlText.append(" where branch_id='").append(branchId).append("'");
/*  62: 62 */    sqlText.append(" order by id");
/*  63: 63 */    return findByCriteria(sqlText.toString());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public List<GroupMembershipBean> getGroupMembership() {
/*  67: 67 */    List<GroupMembershipBean> beans = (List)super.getJdbcTemplate().query("SELECT a.*,b.branch_id,b.name FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id ORDER BY branch_id,group_id", new ResultSetExtractor() {
/*  68:    */      public List<GroupMembershipBean> extractData(java.sql.ResultSet rs) throws SQLException, DataAccessException {
/*  69: 69 */        List<GroupMembershipBean> beans = new ArrayList();
/*  70: 70 */        while (rs.next()) {
/*  71: 71 */          GroupMembershipBean bean = new GroupMembershipBean();
/*  72: 72 */          bean.setApId(rs.getInt("ap_id"));
/*  73: 73 */          bean.setBranchId(rs.getString("branch_id"));
/*  74: 74 */          bean.setGroupId(rs.getString("group_id"));
/*  75: 75 */          bean.setGroupName(rs.getString("name"));
/*  76: 76 */          beans.add(bean);
/*  77:    */        }
/*  78: 78 */        return beans;
/*  79: 79 */      } } );
/*  80: 80 */    return beans;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public GroupMembershipBean getGroupMemberByBranchId(String branchId) {
/*  84: 84 */    String sql = "SELECT a.*,b.name FROM membership_branch_group a LEFT JOIN admin_group b ON a.group_id=b.id WHERE a.branch_id='${bid}' ORDER BY branch_id,group_id".replace("${bid}", branchId);
/*  85: 85 */    GroupMembershipBean bean = (GroupMembershipBean)getJdbcTemplate().query(sql, new ResultSetExtractor() {
/*  86:    */      public GroupMembershipBean extractData(java.sql.ResultSet rs) throws SQLException, DataAccessException {
/*  87: 87 */        GroupMembershipBean bean = null;
/*  88: 88 */        if (rs.next()) {
/*  89: 89 */          bean = new GroupMembershipBean();
/*  90: 90 */          bean.setBranchId(rs.getString("branch_id"));
/*  91: 91 */          bean.setGroupId(rs.getString("group_id"));
/*  92: 92 */          bean.setGroupName(rs.getString("name"));
/*  93:    */        }
/*  94: 94 */        return bean;
/*  95: 95 */      } } );
/*  96: 96 */    return bean;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public List<GroupMembershipBean> getBranchGroupMembers() {
/* 100:100 */    String sql = "SELECT a.*,b.name FROM membership_branch_group a LEFT JOIN admin_group b ON a.group_id=b.id ORDER BY branch_id,group_id";
/* 101:101 */    List<GroupMembershipBean> beans = (List)super.getJdbcTemplate().query(sql, new ResultSetExtractor() {
/* 102:    */      public List<GroupMembershipBean> extractData(java.sql.ResultSet rs) throws SQLException, DataAccessException {
/* 103:103 */        List<GroupMembershipBean> beans = new ArrayList();
/* 104:104 */        while (rs.next()) {
/* 105:105 */          GroupMembershipBean bean = new GroupMembershipBean();
/* 106:106 */          bean.setBranchId(rs.getString("branch_id"));
/* 107:107 */          bean.setGroupId(rs.getString("group_id"));
/* 108:108 */          bean.setGroupName(rs.getString("name"));
/* 109:109 */          beans.add(bean);
/* 110:    */        }
/* 111:111 */        return beans;
/* 112:112 */      } } );
/* 113:113 */    return beans;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public int saveBranchDeviceGroup(String branchId, String gid) {
/* 117:117 */    String sql = "delete from membership_branch_group where branch_id='" + branchId + "'";
/* 118:118 */    getJdbcTemplate().update(sql);
/* 119:119 */    sql = "insert into membership_branch_group(branch_id,group_id)values('" + branchId + "','" + gid + "')";
/* 120:120 */    return getJdbcTemplate().update(sql);
/* 121:    */  }
/* 122:    */  
/* 123:    */  public int removeBranchDeviceGroup(String branchId) {
/* 124:124 */    String sql = "delete from membership_branch_group where branch_id='" + branchId + "'";
/* 125:125 */    return getJdbcTemplate().update(sql);
/* 126:    */  }
/* 127:    */  
/* 128:    */  public String getNextID(String parentID) {
/* 129:129 */    if ("0".equals(parentID)) { parentID = "";
/* 130:    */    }
/* 131:131 */    String sql = null;
/* 132:132 */    if ("".equals(parentID)) {
/* 133:133 */      sql = "SELECT MAX(SUBSTR(id,1,4)) FROM admin_group WHERE pid='' or pid='0'";
/* 134:    */    } else {
/* 135:135 */      int start = parentID.length() + 1;
/* 136:136 */      sql = "SELECT MAX(SUBSTR(id," + start + "," + 4 + ")) FROM admin_group WHERE pid='" + parentID + "'";
/* 137:    */    }
/* 138:138 */    int max = 1;
/* 139:    */    try {
/* 140:140 */      max = ((Integer)getJdbcTemplate().queryForObject(sql, Integer.class)).intValue() + 1;
/* 141:    */    } catch (Exception ex) {
/* 142:142 */      System.out.println("BranchGroupDao.getNextID.error=" + sql);
/* 143:    */    }
/* 144:144 */    return parentID + getCode(max);
/* 145:    */  }
/* 146:    */  
/* 147:    */  private String getCode(int id) {
/* 148:148 */    int len = String.valueOf(id).length();
/* 149:149 */    StringBuilder idstr = new StringBuilder(4);
/* 150:150 */    for (int i = len; i < 4; i++) {
/* 151:151 */      idstr.append("0");
/* 152:    */    }
/* 153:153 */    idstr.append(id);
/* 154:154 */    return idstr.toString();
/* 155:    */  }
/* 156:    */  
/* 157:    */  public int delete(String id) {
/* 158:158 */    String[] sqls = new String[2];
/* 159:159 */    sqls[0] = ("delete from membership_ap_group where group_id like '" + id + "%'");
/* 160:160 */    sqls[1] = ("delete from admin_group where id like '" + id + "%'");
/* 161:161 */    int[] results = getJdbcTemplate().batchUpdate(sqls);
/* 162:162 */    return results.length;
/* 163:    */  }
/* 164:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.GroupDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */