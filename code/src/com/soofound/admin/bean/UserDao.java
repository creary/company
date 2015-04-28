/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import java.util.List;
/*  6:   */import org.apache.commons.logging.Log;
/*  7:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  8:   */import org.springframework.stereotype.Component;
/*  9:   */
/* 10:   */@Component
/* 11:   */public final class UserDao extends BaseDao<UserDto>
/* 12:   */{
/* 13:   */  protected String getQuerySQL(java.util.Map<String, String> options)
/* 14:   */  {
/* 15:15 */    StringBuffer sqlText = new StringBuffer(100);
/* 16:16 */    sqlText.append("select * from view_admin_user where username<>'afunms' ");
/* 17:17 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 18:18 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 19:19 */    if (!CommonUtil.isEmpty((String)options.get("branch")))
/* 20:20 */      sqlText.append(" and branch like '%").append((String)options.get("branch")).append("%'");
/* 21:21 */    if (!CommonUtil.isEmpty((String)options.get("fullname")))
/* 22:22 */      sqlText.append(" and fullname like '%").append((String)options.get("fullname")).append("%'");
/* 23:23 */    if (!CommonUtil.isEmpty((String)options.get("username")))
/* 24:24 */      sqlText.append(" and username like '%").append((String)options.get("username")).append("%'");
/* 25:25 */    if (!CommonUtil.isEmpty((String)options.get("phone")))
/* 26:26 */      sqlText.append(" and phone like '%").append((String)options.get("phone")).append("%'");
/* 27:27 */    sqlText.append(" order by create_time");
/* 28:28 */    return sqlText.toString();
/* 29:   */  }
/* 30:   */  
/* 31:   */  public int update(UserDto dto)
/* 32:   */  {
/* 33:33 */    StringBuffer sqlText = new StringBuffer(100);
/* 34:34 */    sqlText.append("update admin_user set fullname='").append(dto.getFullname()).append("',email='");
/* 35:35 */    sqlText.append(dto.getEmail()).append("',phone='").append(dto.getPhone());
/* 36:36 */    if (!CommonUtil.isEmpty(dto.getPassword()))
/* 37:37 */      sqlText.append("',password='").append(dto.getPassword());
/* 38:38 */    sqlText.append("' where username='").append(dto.getUsername()).append("'");
/* 39:39 */    return saveOrUpdate(sqlText.toString());
/* 40:   */  }
/* 41:   */  
/* 42:   */  public int updatePerson(UserDto dto) {
/* 43:43 */    StringBuffer sqlText = new StringBuffer(100);
/* 44:44 */    sqlText.append("update admin_user ");
/* 45:45 */    if (CommonUtil.isEmpty(dto.getPassword())) {
/* 46:46 */      sqlText.append("set fullname='").append(dto.getFullname()).append("',email='");
/* 47:47 */      sqlText.append(dto.getEmail()).append("',phone='").append(dto.getPhone());
/* 48:   */    } else {
/* 49:49 */      sqlText.append("set password='").append(dto.getPassword());
/* 50:   */    }
/* 51:51 */    sqlText.append("' where username='").append(dto.getUsername()).append("'");
/* 52:52 */    return saveOrUpdate(sqlText.toString());
/* 53:   */  }
/* 54:   */  
/* 55:   */  public int delete(String[] usernames) {
/* 56:56 */    String[] sqls = new String[usernames.length];
/* 57:   */    try {
/* 58:58 */      for (int i = 0; i < sqls.length; i++) {
/* 59:59 */        sqls[i] = ("delete from admin_user where username='" + usernames[i] + "'");
/* 60:   */      }
/* 61:61 */      int[] results = super.getJdbcTemplate().batchUpdate(sqls);
/* 62:62 */      return results.length;
/* 63:   */    } catch (Exception e) {
/* 64:64 */      this.logger.error(e);
/* 65:   */    }
/* 66:66 */    return 0;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public UserDto findByUsername(String username, String password) {
/* 70:70 */    String sql = "select * from view_admin_user where username='" + username + "' and password='" + password + "'";
/* 71:71 */    return (UserDto)super.findOne(sql);
/* 72:   */  }
/* 73:   */  
/* 74:   */  public UserDto findByID(String username)
/* 75:   */  {
/* 76:76 */    String sql = "select * from view_admin_user where username='" + username + "'";
/* 77:77 */    return (UserDto)super.findOne(sql);
/* 78:   */  }
/* 79:   */  
/* 80:   */  public List<UserDto> findByBranch(String branchId) {
/* 81:81 */    StringBuilder sqlText = new StringBuilder();
/* 82:82 */    sqlText.append("select * from view_admin_user where branch_id='").append(branchId).append("'");
/* 83:83 */    return super.findByCriteria(sqlText.toString());
/* 84:   */  }
/* 85:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.UserDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */