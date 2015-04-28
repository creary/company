/*   1:    */package com.soofound.admin.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.BaseDao;
/*   4:    */import com.soofound.framework.util.CommonUtil;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.sql.ResultSet;
/*   7:    */import java.sql.SQLException;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.Map;
/*  10:    */import org.springframework.dao.DataAccessException;
/*  11:    */import org.springframework.dao.EmptyResultDataAccessException;
/*  12:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  13:    */import org.springframework.jdbc.core.ResultSetExtractor;
/*  14:    */import org.springframework.stereotype.Component;
/*  15:    */
/*  16:    */@Component
/*  17:    */public final class BranchDao extends BaseDao<BranchDto>
/*  18:    */{
/*  19:    */  private static final int CODE_LENGTH = 5;
/*  20:    */  
/*  21:    */  protected String getQuerySQL(Map<String, String> options)
/*  22:    */  {
/*  23: 23 */    StringBuffer sqlText = new StringBuffer(100);
/*  24: 24 */    sqlText.append("select * from view_admin_branch where 1=1 ");
/*  25: 25 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/*  26: 26 */      sqlText.append(" and name like '%").append((String)options.get("name")).append("%'");
/*  27: 27 */    if (!CommonUtil.isEmpty((String)options.get("parent")))
/*  28: 28 */      sqlText.append(" and parent like '%").append((String)options.get("parent")).append("%'");
/*  29: 29 */    if (!CommonUtil.isEmpty((String)options.get("id")))
/*  30: 30 */      sqlText.append(" and id like '").append((String)options.get("id")).append("%'");
/*  31: 31 */    sqlText.append(" order by id");
/*  32: 32 */    return sqlText.toString();
/*  33:    */  }
/*  34:    */  
/*  35:    */  public int getBranchTotal(String branchId) {
/*  36: 36 */    String sql = "select count(*) from admin_branch where id LIKE '" + branchId + "%'";
/*  37:    */    try {
/*  38: 38 */      return ((Integer)getJdbcTemplate().queryForObject(sql, Integer.class)).intValue();
/*  39:    */    } catch (EmptyResultDataAccessException ex) {}
/*  40: 40 */    return 0;
/*  41:    */  }
/*  42:    */  
/*  43:    */  public String getNextID(String parentID)
/*  44:    */  {
/*  45: 45 */    if ("0".equals(parentID)) { parentID = "";
/*  46:    */    }
/*  47: 47 */    String sql = null;
/*  48: 48 */    if ("".equals(parentID)) {
/*  49: 49 */      sql = "SELECT MAX(id) FROM admin_branch WHERE parent_id='' or parent_id='0'";
/*  50:    */    } else {
/*  51: 51 */      int start = parentID.length() + 1;
/*  52: 52 */      sql = "SELECT MAX(SUBSTR(id," + start + "," + 5 + ")) FROM admin_branch WHERE parent_id='" + parentID + "'";
/*  53:    */    }
/*  54: 54 */    int max = 1;
/*  55:    */    try {
/*  56: 56 */      max = ((Integer)getJdbcTemplate().queryForObject(sql, Integer.class)).intValue() + 1;
/*  57:    */    } catch (Exception ex) {
/*  58: 58 */      System.out.println("BranchDao.getNextID.error=" + sql);
/*  59:    */    }
/*  60: 60 */    return parentID + getCode(max);
/*  61:    */  }
/*  62:    */  
/*  63:    */  public BranchDto findByID(String id)
/*  64:    */  {
/*  65: 65 */    String sql = "select * from admin_branch where id='" + id + "'";
/*  66: 66 */    return (BranchDto)super.findOne(sql);
/*  67:    */  }
/*  68:    */  
/*  69:    */  public java.util.List<BranchDto> findByBranch(String branchId) {
/*  70: 70 */    String sql = null;
/*  71: 71 */    if ((CommonUtil.isEmpty(branchId)) || (branchId.equals("0"))) {
/*  72: 72 */      sql = "select * from admin_branch order by id";
/*  73:    */    } else
/*  74: 74 */      sql = "select * from admin_branch where id like '" + branchId + "%' order by id";
/*  75: 75 */    return super.findByCriteria(sql);
/*  76:    */  }
/*  77:    */  
/*  78:    */  public BranchDto findByName(String name) {
/*  79: 79 */    String sql = "select * from admin_branch where name='" + name + "'";
/*  80: 80 */    return (BranchDto)super.findOne(sql);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public BranchDto findByOpenId(String openId) {
/*  84: 84 */    String sql = "select * from admin_branch where open_id='" + openId + "'";
/*  85: 85 */    return (BranchDto)super.findOne(sql);
/*  86:    */  }
/*  87:    */  
/*  88:    */  public int delete(String id)
/*  89:    */  {
/*  90: 90 */    java.util.List<String> sqls = new ArrayList();
/*  91: 91 */    sqls.add("update cpe_host set branch_id = '0' where branch_id='" + id + "'");
/*  92: 92 */    sqls.add("update admin_license set branch_id='0' where branch_id='" + id + "'");
/*  93: 93 */    sqls.add("delete from admin_branch where id='" + id + "'");
/*  94: 94 */    sqls.add("delete from admin_user where branch_id='" + id + "'");
/*  95: 95 */    sqls.add("delete from surfing_account where branch_id='" + id + "'");
/*  96: 96 */    sqls.add("delete from portal_instance where branch_id = '" + id + "'");
/*  97: 97 */    sqls.add("delete from portal_surfing_policy where branch_id = '" + id + "'");
/*  98: 98 */    return super.batchUpdate(sqls);
/*  99:    */  }
/* 100:    */  
/* 101:    */  public int save(BranchDto dto)
/* 102:    */  {
/* 103:103 */    StringBuilder sqlText = new StringBuilder(100);
/* 104:104 */    sqlText.append("insert into admin_branch(id,name,public_name,parent_id,grade)values('").append(dto.getId());
/* 105:105 */    sqlText.append("','").append(dto.getName()).append("','','").append(dto.getParentId()).append("',");
/* 106:106 */    sqlText.append(dto.getGrade()).append(")");
/* 107:107 */    return super.saveOrUpdate(sqlText.toString());
/* 108:    */  }
/* 109:    */  
/* 110:    */  public java.util.List<BranchStatDto> getBranchHostNumber(String branchId, String online) {
/* 111:111 */    StringBuilder sqlText = new StringBuilder(200);
/* 112:112 */    sqlText.append("SELECT a.*,b.cc FROM view_admin_branch a LEFT JOIN (SELECT branch_id,COUNT(*) cc FROM cpe_host WHERE branch_id LIKE '");
/* 113:113 */    sqlText.append(branchId).append("%'");
/* 114:114 */    if (online != null)
/* 115:115 */      sqlText.append(" AND online = ").append(online);
/* 116:116 */    sqlText.append(" GROUP BY branch_id) b ON a.id=b.branch_id WHERE a.id LIKE '").append(branchId).append("%' ORDER BY id");
/* 117:117 */    java.util.List<BranchStatDto> bsds = (java.util.List)super.getJdbcTemplate().query(sqlText.toString(), new ResultSetExtractor() {
/* 118:    */      public java.util.List<BranchStatDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 119:119 */        java.util.List<BranchStatDto> bsds = new ArrayList();
/* 120:120 */        while (rs.next()) {
/* 121:121 */          BranchStatDto dto = new BranchStatDto();
/* 122:122 */          dto.setBranchId(rs.getString("id"));
/* 123:123 */          dto.setParent(rs.getString("parent"));
/* 124:124 */          dto.setName(rs.getString("name"));
/* 125:125 */          dto.setGrade(rs.getInt("grade"));
/* 126:126 */          dto.setDeviceTotal(rs.getInt("cc"));
/* 127:127 */          bsds.add(dto);
/* 128:    */        }
/* 129:129 */        return bsds;
/* 130:130 */      } } );
/* 131:131 */    return bsds;
/* 132:    */  }
/* 133:    */  
/* 134:    */  private String getCode(int id) {
/* 135:135 */    int len = String.valueOf(id).length();
/* 136:136 */    StringBuilder idstr = new StringBuilder(5);
/* 137:137 */    for (int i = len; i < 5; i++) {
/* 138:138 */      idstr.append("0");
/* 139:    */    }
/* 140:140 */    idstr.append(id);
/* 141:141 */    return idstr.toString();
/* 142:    */  }
/* 143:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.BranchDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */