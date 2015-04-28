/*  1:   */package com.soofound.admin.web;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */import com.soofound.framework.web.BaseService;
/*  5:   */import java.util.ArrayList;
/*  6:   */import org.springframework.beans.factory.annotation.Autowired;
/*  7:   */import org.springframework.beans.factory.annotation.Qualifier;
/*  8:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  9:   */import org.springframework.stereotype.Service;
/* 10:   */
/* 11:   */@Service
/* 12:   */public final class GroupService extends BaseService<com.soofound.admin.bean.GroupDao>
/* 13:   */{
/* 14:   */  @Autowired
/* 15:   */  @Qualifier("defaultJdbcTemplate")
/* 16:   */  private JdbcTemplate jdbcTemplate;
/* 17:   */  
/* 18:   */  public int update(Persistable dto)
/* 19:   */  {
/* 20:20 */    com.soofound.admin.bean.GroupDto gd = (com.soofound.admin.bean.GroupDto)dto;
/* 21:21 */    StringBuffer sqlText = new StringBuffer(100);
/* 22:22 */    sqlText.append("update admin_group set name='").append(gd.getName()).append("' where id='").append(gd.getId()).append("'");
/* 23:23 */    return this.jdbcTemplate.update(sqlText.toString());
/* 24:   */  }
/* 25:   */  
/* 26:   */  public int updateGroup(String branchId, String gid, String[] ids) {
/* 27:27 */    java.util.List<String> sqls = new ArrayList();
/* 28:28 */    if (ids == null) {
/* 29:29 */      String sql = "delete from membership_ap_group where group_id='" + gid + "'";
/* 30:30 */      sqls.add(sql);
/* 31:   */    } else {
/* 32:32 */      for (int i = 0; i < ids.length; i++) {
/* 33:33 */        String sql1 = "DELETE FROM membership_ap_group WHERE ap_id=" + ids[i] + 
/* 34:34 */          " AND group_id IN (SELECT id FROM admin_group WHERE branch_id='" + branchId + "')";
/* 35:35 */        String sql2 = "insert into membership_ap_group(group_id,ap_id)values('" + gid + "'," + ids[i] + ")";
/* 36:36 */        sqls.add(sql1);
/* 37:37 */        sqls.add(sql2);
/* 38:   */      }
/* 39:   */    }
/* 40:40 */    String[] arrsqls = new String[sqls.size()];
/* 41:41 */    sqls.toArray(arrsqls);
/* 42:42 */    int[] results = this.jdbcTemplate.batchUpdate(arrsqls);
/* 43:43 */    return results.length;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public String getNextID(String pid) {
/* 47:47 */    return ((com.soofound.admin.bean.GroupDao)this.dao).getNextID(pid);
/* 48:   */  }
/* 49:   */  
/* 50:   */  public java.util.List<com.soofound.admin.bean.GroupDto> findByBranch(String branchId) {
/* 51:51 */    return ((com.soofound.admin.bean.GroupDao)this.dao).findByBranch(branchId);
/* 52:   */  }
/* 53:   */  
/* 54:   */  public int delete(String id) {
/* 55:55 */    return ((com.soofound.admin.bean.GroupDao)this.dao).delete(id);
/* 56:   */  }
/* 57:   */  
/* 60:   */  @Autowired
/* 61:   */  protected void setDao(com.soofound.admin.bean.GroupDao dao)
/* 62:   */  {
/* 63:63 */    this.dao = dao;
/* 64:   */  }
/* 65:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.GroupService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */