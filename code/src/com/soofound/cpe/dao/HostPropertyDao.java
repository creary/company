/*  1:   */package com.soofound.cpe.dao;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.HostPropertyBean;
/*  4:   */import com.soofound.framework.jdbc.BaseDao;
/*  5:   */import com.soofound.framework.util.CommonUtil;
/*  6:   */import java.sql.ResultSet;
/*  7:   */import java.sql.SQLException;
/*  8:   */import java.util.ArrayList;
/*  9:   */import org.springframework.dao.DataAccessException;
/* 10:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 11:   */import org.springframework.jdbc.core.ResultSetExtractor;
/* 12:   */import org.springframework.stereotype.Component;
/* 13:   */
/* 14:   */@Component
/* 15:   */public final class HostPropertyDao extends BaseDao<HostPropertyBean>
/* 16:   */{
/* 17:   */  public void clearProperties(int hostId)
/* 18:   */  {
/* 19:19 */    super.getJdbcTemplate().update("delete from cpe_host_property where host_id=" + hostId);
/* 20:   */  }
/* 21:   */  
/* 22:   */  public java.util.List<HostPropertyBean> findByHost(String hostId) {
/* 23:23 */    String sql = "select * from view_host_property where host_id=" + hostId + " order by name";
/* 24:24 */    return super.findByCriteria(sql);
/* 25:   */  }
/* 26:   */  
/* 27:   */  public java.util.List<HostPropertyBean> findByHosts(String hostIds, String pids) {
/* 28:28 */    StringBuilder sqlText = new StringBuilder(100);
/* 29:29 */    sqlText.append("select * from view_host_property where host_id in (").append(hostIds);
/* 30:30 */    sqlText.append(") and pid in (").append(pids).append(")");
/* 31:31 */    return super.findByCriteria(sqlText.toString());
/* 32:   */  }
/* 33:   */  
/* 34:   */  public java.util.List<HostPropertyBean> findByHostSsids(String hostId) {
/* 35:35 */    String sql = "select * from view_host_property where host_id=" + hostId + 
/* 36:36 */      " and name like 'InternetGatewayDevice.DeviceInfo.wireless_ssid%' order by id";
/* 37:37 */    return super.findByCriteria(sql);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public java.util.List<Integer> findByHostID(String hostId) {
/* 41:41 */    String sql = "select pid from cpe_host_property where host_id=" + hostId;
/* 42:42 */    java.util.List<Integer> flashs = (java.util.List)super.getJdbcTemplate().query(sql, new ResultSetExtractor() {
/* 43:   */      public java.util.List<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 44:44 */        java.util.List<Integer> flashs = new ArrayList();
/* 45:45 */        while (rs.next())
/* 46:46 */          flashs.add(Integer.valueOf(rs.getInt(1)));
/* 47:47 */        return flashs;
/* 48:48 */      } } );
/* 49:49 */    return flashs;
/* 50:   */  }
/* 51:   */  
/* 52:   */  public HostPropertyBean getPropertyByName(String hostId, String name) {
/* 53:53 */    String sql = "select * from view_host_property where host_id=" + hostId + " and name='" + name + "'";
/* 54:54 */    return (HostPropertyBean)super.findOne(sql);
/* 55:   */  }
/* 56:   */  
/* 57:   */  public HostPropertyBean getPropertyByID(String hostId, String propId) {
/* 58:58 */    String sql = "select * from view_host_property where host_id=" + hostId + " and pid=" + propId;
/* 59:59 */    return (HostPropertyBean)super.findOne(sql);
/* 60:   */  }
/* 61:   */  
/* 62:   */  public java.util.List<HostPropertyBean> getQos(String hostId) {
/* 63:63 */    StringBuilder sqlText = new StringBuilder(100);
/* 64:64 */    sqlText.append("SELECT * FROM view_host_property WHERE NAME LIKE 'InternetGatewayDevice.DeviceInfo.qos_%' AND host_id=").append(hostId);
/* 65:65 */    return super.findByCriteria(sqlText.toString());
/* 66:   */  }
/* 67:   */  
/* 68:   */  public void updateQos(String hostId, int pid, String[] qoss) {
/* 69:69 */    java.util.List<String> sqls = new ArrayList();
/* 70:70 */    if (CommonUtil.isEmpty(getQos(hostId))) {
/* 71:71 */      for (String qos : qoss) {
/* 72:72 */        String sql = "insert into cpe_host_property(host_id,pid,value)values(" + hostId + "," + pid + ",'" + qos + "')";
/* 73:73 */        sqls.add(sql);
/* 74:74 */        pid++;
/* 75:   */      }
/* 76:   */    } else {
/* 77:77 */      for (String qos : qoss) {
/* 78:78 */        String sql = "update cpe_host_property set value= '" + qos + "' where pid=" + pid + " and host_id=" + hostId;
/* 79:79 */        sqls.add(sql);
/* 80:80 */        pid++;
/* 81:   */      }
/* 82:   */    }
/* 83:83 */    batchUpdate(sqls);
/* 84:   */  }
/* 85:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.dao.HostPropertyDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */