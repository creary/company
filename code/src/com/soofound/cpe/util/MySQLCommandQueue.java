/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SysConfigHelper;
/*  4:   */import com.soofound.portal.bean.ShareMac;
/*  5:   */import java.sql.ResultSet;
/*  6:   */import java.sql.SQLException;
/*  7:   */import org.springframework.dao.DataAccessException;
/*  8:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  9:   */import org.springframework.jdbc.core.ResultSetExtractor;
/* 10:   */
/* 11:   */public class MySQLCommandQueue implements CommandQueue
/* 12:   */{
/* 13:   */  private JdbcTemplate jdbc;
/* 14:   */  
/* 15:   */  public MySQLCommandQueue()
/* 16:   */  {
/* 17:17 */    this.jdbc = ((JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate"));
/* 18:   */  }
/* 19:   */  
/* 20:   */  public void putCommand(int hostId, String code)
/* 21:   */  {
/* 22:22 */    StringBuilder sqlText = new StringBuilder(100);
/* 23:23 */    sqlText.append("insert into cpe_command_queue(ap_id,command,log_time,flag)values(").append(hostId);
/* 24:24 */    sqlText.append(",'").append(code).append("',").append(System.currentTimeMillis()).append(",0)");
/* 25:25 */    this.jdbc.update(sqlText.toString());
/* 26:   */  }
/* 27:   */  
/* 28:   */  public boolean isEmptyCommand(int hostId)
/* 29:   */  {
/* 30:30 */    String sql = "select count(*) from cpe_command_queue where ap_id = " + hostId + " and flag = 0";
/* 31:   */    try {
/* 32:32 */      return ((Integer)this.jdbc.queryForObject(sql, Integer.class)).intValue() == 0;
/* 33:   */    } catch (Exception ex) {}
/* 34:34 */    return true;
/* 35:   */  }
/* 36:   */  
/* 38:   */  public void clearCommand(int hostId)
/* 39:   */  {
/* 40:40 */    StringBuilder sqlText = new StringBuilder(100);
/* 41:41 */    sqlText.append("delete from cpe_command_queue where ap_id = ").append(hostId).append(" and command not like '%ManagementServer%'");
/* 42:42 */    this.jdbc.update(sqlText.toString());
/* 43:   */  }
/* 44:   */  
/* 45:   */  public String getCommand(int hostId)
/* 46:   */  {
/* 47:47 */    String sql = "select * from cpe_command_queue where ap_id = " + hostId + " and flag = 0 order by log_time limit 1";
/* 48:48 */    ShareMac sm = (ShareMac)this.jdbc.query(sql, new ResultSetExtractor() {
/* 49:   */      public ShareMac extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 50:50 */        if (rs.next()) {
/* 51:51 */          ShareMac sm = new ShareMac();
/* 52:52 */          sm.setMac(rs.getString("command"));
/* 53:53 */          sm.setRefreshTime(rs.getLong("log_time"));
/* 54:54 */          return sm;
/* 55:   */        }
/* 56:56 */        return null;
/* 57:   */      } } );
/* 58:58 */    if (sm == null)
/* 59:59 */      return null;
/* 60:60 */    this.jdbc.update("delete from cpe_command_queue where ap_id=" + hostId + " and log_time=" + sm.getRefreshTime());
/* 61:61 */    return sm.getMac();
/* 62:   */  }
/* 63:   */  
/* 64:   */  public void putDebugResult(int hostId, String cmdResult)
/* 65:   */  {
/* 66:66 */    StringBuilder sqlText = new StringBuilder(100);
/* 67:67 */    sqlText.append("insert into cpe_command_queue(ap_id,command,log_time,flag)values(").append(hostId);
/* 68:68 */    sqlText.append(",'").append(cmdResult).append("',").append(System.currentTimeMillis()).append(",1)");
/* 69:69 */    this.jdbc.update(sqlText.toString());
/* 70:   */  }
/* 71:   */  
/* 72:   */  public String getDebugResult(int hostId)
/* 73:   */  {
/* 74:74 */    String sql = "select command from cpe_command_queue where ap_id = " + hostId + " and flag = 1 order by log_time desc limit 1";
/* 75:   */    try {
/* 76:76 */      return (String)this.jdbc.queryForObject(sql, String.class);
/* 77:   */    } catch (Exception ex) {}
/* 78:78 */    return null;
/* 79:   */  }
/* 80:   */  
/* 82:   */  public String removeDebugResult(int hostId)
/* 83:   */  {
/* 84:84 */    String sql = "select command from cpe_command_queue where ap_id = " + hostId + " and flag = 1 order by log_time desc limit 1";
/* 85:   */    try {
/* 86:86 */      String cmd = (String)this.jdbc.queryForObject(sql, String.class);
/* 87:87 */      this.jdbc.update("delete from cpe_command_queue where ap_id=" + hostId + " and flag = 1");
/* 88:88 */      return cmd;
/* 89:   */    } catch (Exception ex) {}
/* 90:90 */    return null;
/* 91:   */  }
/* 92:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.MySQLCommandQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */