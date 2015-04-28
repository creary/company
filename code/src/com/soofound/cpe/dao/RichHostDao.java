/*  1:   */package com.soofound.cpe.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import java.io.PrintStream;
/*  5:   */import java.sql.SQLException;
/*  6:   */import java.util.ArrayList;
/*  7:   */import java.util.List;
/*  8:   */import org.springframework.dao.DataAccessException;
/*  9:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 10:   */import org.springframework.jdbc.core.ResultSetExtractor;
/* 11:   */import org.springframework.stereotype.Component;
/* 12:   */
/* 13:   */@Component
/* 14:   */public final class RichHostDao extends BaseDao<com.soofound.cpe.bean.RichHostBean>
/* 15:   */{
/* 16:   */  public String getApmacsByBranch(String branchId)
/* 17:   */  {
/* 18:18 */    String sql = "select serial_number from cpe_host where branch_id like '" + branchId + "%'";
/* 19:19 */    return getApmacs(sql);
/* 20:   */  }
/* 21:   */  
/* 22:   */  public String getApmacsByGroup(String groupId) {
/* 23:23 */    String sql = "select serial_number from cpe_host a left join membership_ap_group b on a.id=b.ap_id where b.group_id like '" + groupId + "%'";
/* 24:24 */    return getApmacs(sql);
/* 25:   */  }
/* 26:   */  
/* 27:   */  private String getApmacs(String selectSQL) {
/* 28:28 */    List<String> apmacs = (List)super.getJdbcTemplate().query(selectSQL, new ResultSetExtractor() {
/* 29:   */      public List<String> extractData(java.sql.ResultSet rs) throws SQLException, DataAccessException {
/* 30:30 */        List<String> apmacs = new ArrayList();
/* 31:31 */        while (rs.next())
/* 32:32 */          apmacs.add(rs.getString(1));
/* 33:33 */        return apmacs;
/* 34:34 */      } } );
/* 35:35 */    StringBuilder macstr = new StringBuilder(200);
/* 36:36 */    for (String apmac : apmacs) {
/* 37:37 */      macstr.append(",'").append(apmac).append("'");
/* 38:   */    }
/* 39:39 */    if (macstr.length() > 0)
/* 40:40 */      return macstr.substring(1);
/* 41:41 */    return "";
/* 42:   */  }
/* 43:   */  
/* 44:   */  public List<com.soofound.cpe.bean.RichHostBean> findBySSID(String ssid) {
/* 45:45 */    String sql = "select * from cpe_host where ssid='" + ssid + "'";
/* 46:46 */    return super.findByCriteria(sql);
/* 47:   */  }
/* 48:   */  
/* 49:   */  public int updateSSID(int id, String ssid) {
/* 50:50 */    String sql = "update cpe_host set ssid='" + ssid + "' where id = " + id;
/* 51:51 */    return super.saveOrUpdate(sql);
/* 52:   */  }
/* 53:   */  
/* 54:   */  public int update(com.soofound.cpe.bean.RichHostBean host)
/* 55:   */  {
/* 56:56 */    StringBuilder sqlText = new StringBuilder(100);
/* 57:57 */    sqlText.append("update cpe_host set code='").append(host.getCode()).append("',location='").append(host.getLocation());
/* 58:58 */    sqlText.append("',name='").append(host.getName()).append("',used=").append(host.getUsed()).append(" where id=").append(host.getId());
/* 59:59 */    return saveOrUpdate(sqlText.toString());
/* 60:   */  }
/* 61:   */  
/* 62:   */  public String getLayoutImage(String gid) {
/* 63:63 */    String sql = "select layout_image from cpe_device_layout where gid='" + gid + "'";
/* 64:64 */    String img = (String)getJdbcTemplate().query(sql, new ResultSetExtractor() {
/* 65:   */      public String extractData(java.sql.ResultSet rs) throws SQLException, DataAccessException {
/* 66:66 */        if (rs.next())
/* 67:67 */          return rs.getString(1);
/* 68:68 */        return null;
/* 69:69 */      } } );
/* 70:70 */    return img;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public int save(String gid, String img) {
/* 74:74 */    StringBuilder sqlText = new StringBuilder(200);
/* 75:75 */    sqlText.append("update cpe_device_layout set layout_image='").append(img).append("',create_time=now() where gid='");
/* 76:76 */    sqlText.append(gid).append("'");
/* 77:77 */    int row = getJdbcTemplate().update(sqlText.toString());
/* 78:78 */    if (row == 0) {
/* 79:79 */      sqlText.setLength(0);
/* 80:80 */      sqlText.append("insert into cpe_device_layout(gid,layout_image,create_time)values('").append(gid).append("','");
/* 81:81 */      sqlText.append(img).append("',now())");
/* 82:82 */      row = getJdbcTemplate().update(sqlText.toString());
/* 83:83 */      System.out.println(sqlText.toString());
/* 84:   */    }
/* 85:85 */    return row;
/* 86:   */  }
/* 87:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.dao.RichHostDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */