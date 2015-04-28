/*   1:    */package com.soofound.portal.dao;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.BaseDao;
/*   4:    */import com.soofound.framework.util.CommonUtil;
/*   5:    */import com.soofound.portal.bean.PortalInstanceDto;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.sql.ResultSet;
/*   8:    */import java.sql.SQLException;
/*   9:    */import java.util.ArrayList;
/*  10:    */import java.util.HashMap;
/*  11:    */import java.util.List;
/*  12:    */import java.util.Map;
/*  13:    */import org.springframework.dao.DataAccessException;
/*  14:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  15:    */import org.springframework.jdbc.core.ResultSetExtractor;
/*  16:    */import org.springframework.stereotype.Component;
/*  17:    */
/*  21:    */@Component
/*  22:    */public final class PortalInstanceDao
/*  23:    */  extends BaseDao<PortalInstanceDto>
/*  24:    */{
/*  25:    */  private static final String SQL_INSERT_PAGE = "insert into portal_instance_page(pid,page,page_value)values(?,?,?)";
/*  26:    */  private static final String SQL_INSERT_PAGE2 = "insert into portal_wechat_response(branch_id,page_value)values(?,?)";
/*  27:    */  private static final String SQL_PORTAL_PAGE = "select page_value from portal_instance_page where pid=? and page=?";
/*  28:    */  private static final String SQL_PORTAL_PAGES = "select * from portal_instance_page where pid=?";
/*  29:    */  private static final String SQL_UPDATE_PAGE = "update portal_instance_page set page_value=? where page=? and pid=?";
/*  30:    */  
/*  31:    */  protected String getQuerySQL(Map<String, String> options)
/*  32:    */  {
/*  33: 33 */    StringBuffer sqlText = new StringBuffer(100);
/*  34: 34 */    sqlText.append("select * from view_portal_instance where 1=1 ");
/*  35: 35 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/*  36: 36 */      sqlText.append(" and branch_id ='").append((String)options.get("branchId")).append("'");
/*  37: 37 */    sqlText.append(" order by id desc");
/*  38: 38 */    return sqlText.toString();
/*  39:    */  }
/*  40:    */  
/*  41:    */  public PortalInstanceDto getPortalByName(String branchId, String name) {
/*  42: 42 */    StringBuilder sqlText = new StringBuilder(200);
/*  43: 43 */    sqlText.append("select * from view_portal_instance where name='").append(name).append("' and branch_id='");
/*  44: 44 */    sqlText.append(branchId).append("'");
/*  45: 45 */    return (PortalInstanceDto)super.findOne(sqlText.toString());
/*  46:    */  }
/*  47:    */  
/*  48:    */  public List<PortalInstanceDto> findByBranch(String branchId) {
/*  49: 49 */    String sql = "select * from view_portal_instance where branch_id='" + branchId + "'";
/*  50: 50 */    return super.findByCriteria(sql);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public PortalInstanceDto findByID(String id)
/*  54:    */  {
/*  55: 55 */    String sql = "select * from view_portal_instance where id = '" + id + "'";
/*  56: 56 */    return (PortalInstanceDto)super.findOne(sql);
/*  57:    */  }
/*  58:    */  
/*  59:    */  public int update(PortalInstanceDto dto)
/*  60:    */  {
/*  61: 61 */    StringBuffer sqlText = new StringBuffer(100);
/*  62: 62 */    sqlText.append("update portal_instance set name='").append(dto.getName()).append("',tag=").append(dto.getTag());
/*  63: 63 */    sqlText.append(" where id=").append(dto.getId());
/*  64: 64 */    return super.saveOrUpdate(sqlText.toString());
/*  65:    */  }
/*  66:    */  
/*  67:    */  public int insertPortalPage(String pid, String page, String value) {
/*  68:    */    try {
/*  69: 69 */      return getJdbcTemplate().update("insert into portal_instance_page(pid,page,page_value)values(?,?,?)", new Object[] { pid, page, value.replace("'", "''") });
/*  70:    */    } catch (DataAccessException ex) {
/*  71: 71 */      ex.printStackTrace(); }
/*  72: 72 */    return 0;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public int insertWechatPage(String branchId, String value)
/*  76:    */  {
/*  77:    */    try {
/*  78: 78 */      return getJdbcTemplate().update("insert into portal_wechat_response(branch_id,page_value)values(?,?)", new Object[] { branchId, value.replace("'", "''") });
/*  79:    */    } catch (DataAccessException ex) {
/*  80: 80 */      ex.printStackTrace(); }
/*  81: 81 */    return 0;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public String getPortalPage(String pid, String page)
/*  85:    */  {
/*  86:    */    try {
/*  87: 87 */      return (String)super.getJdbcTemplate().queryForObject("select page_value from portal_instance_page where pid=? and page=?", new String[] { pid, page }, String.class);
/*  88:    */    } catch (Exception ex) {
/*  89: 89 */      System.out.println("getPortalPage#" + pid + "--" + page); }
/*  90: 90 */    return null;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public Map<String, String> getPortalPages(String pid)
/*  94:    */  {
/*  95: 95 */    Map<String, String> pages = (Map)super.getJdbcTemplate().query("select * from portal_instance_page where pid=?", 
/*  96: 96 */      new String[] { pid }, new ResultSetExtractor() {
/*  97:    */        public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/*  98: 98 */          Map<String, String> pages = new HashMap();
/*  99: 99 */          while (rs.next()) {
/* 100:100 */            pages.put(rs.getString("page"), rs.getString("page_value"));
/* 101:    */          }
/* 102:102 */          return pages;
/* 103:103 */        } } );
/* 104:104 */    return pages;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public PortalInstanceDto getDefaultPortal(String branchId) {
/* 108:108 */    String sql = "select * from view_portal_instance where tag=1 and branch_id='" + branchId + "'";
/* 109:109 */    return (PortalInstanceDto)super.findOne(sql);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public int updatePortalPage(String pid, String page, String value) {
/* 113:113 */    return getJdbcTemplate().update("update portal_instance_page set page_value=? where page=? and pid=?", new Object[] { value.replace("'", "''"), page, pid });
/* 114:    */  }
/* 115:    */  
/* 116:    */  public int delete(String[] ids)
/* 117:    */  {
/* 118:118 */    List<String> sqls = new ArrayList();
/* 119:119 */    for (String id : ids) {
/* 120:120 */      sqls.add("delete from portal_instance where id=" + id);
/* 121:121 */      sqls.add("update cpe_ssid set portal_id=0 where portal_id=" + id);
/* 122:    */    }
/* 123:123 */    return batchUpdate(sqls);
/* 124:    */  }
/* 125:    */  
/* 126:    */  public int copyPortal(String pid) {
/* 127:127 */    List<String> sqls = new ArrayList();
/* 128:    */    try {
/* 129:129 */      PortalInstanceDto dto = findByID(pid);
/* 130:130 */      Map<String, String> pages = getPortalPages(pid);
/* 131:    */      
/* 132:132 */      int nid = getID("portal_instance");
/* 133:133 */      StringBuilder sqlText = new StringBuilder(100);
/* 134:134 */      sqlText.append("insert into portal_instance(id,name,branch_id,tid,tag)values(").append(nid);
/* 135:135 */      sqlText.append(",'").append(dto.getName()).append("_copy','").append(dto.getBranchId()).append("','");
/* 136:136 */      sqlText.append(dto.getTid()).append("',0)");
/* 137:137 */      sqls.add(sqlText.toString());
/* 138:138 */      for (String key : pages.keySet()) {
/* 139:139 */        StringBuilder sqlText2 = new StringBuilder(100);
/* 140:140 */        sqlText2.append("insert into portal_instance_page(pid,page,page_value) select ").append(nid);
/* 141:141 */        sqlText2.append(",page,page_value from portal_instance_page where pid=").append(pid).append(" and page='");
/* 142:142 */        sqlText2.append(key).append("'");
/* 143:143 */        sqls.add(sqlText2.toString());
/* 144:    */      }
/* 145:145 */      int[] rows = getJdbcTemplate().batchUpdate(CommonUtil.toArray(sqls));
/* 146:146 */      return rows.length;
/* 147:    */    } catch (Exception ex) {
/* 148:148 */      ex.printStackTrace();
/* 149:    */    }
/* 150:150 */    return 0;
/* 151:    */  }
/* 152:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.PortalInstanceDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */