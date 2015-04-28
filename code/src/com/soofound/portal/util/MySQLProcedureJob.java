/*  1:   */package com.soofound.portal.util;
/*  2:   */
/*  3:   */import com.soofound.framework.job.ScheduleJob;
/*  4:   */import com.soofound.framework.util.SysConfigHelper;
/*  5:   */import java.util.concurrent.TimeUnit;
/*  6:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  7:   */
/*  8:   */public class MySQLProcedureJob
/*  9:   */  implements ScheduleJob
/* 10:   */{
/* 11:   */  private static final String DELETE_HISTORY_SESSION = "DELETE from surfing_session_history where start_time < DATE_ADD(NOW(),INTERVAL-15 day)";
/* 12:   */  private static final String DELETE_ADMIN_LOG = "delete from admin_log where log_time < DATE_ADD(NOW(),INTERVAL-15 day)";
/* 13:   */  private static final String DELETE_DEVICE_LOG = "delete from cpe_device_log where log_time < DATE_ADD(NOW(),INTERVAL-15 day)";
/* 14:   */  
/* 15:   */  public void run()
/* 16:   */  {
/* 17:17 */    JdbcTemplate jdbcTemplate = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 18:   */    try {
/* 19:19 */      jdbcTemplate.update("DELETE from surfing_session_history where start_time < DATE_ADD(NOW(),INTERVAL-15 day)");
/* 20:20 */      jdbcTemplate.update("delete from cpe_device_log where log_time < DATE_ADD(NOW(),INTERVAL-15 day)");
/* 21:21 */      jdbcTemplate.update("delete from admin_log where log_time < DATE_ADD(NOW(),INTERVAL-15 day)");
/* 22:   */    } catch (Exception ex) {
/* 23:23 */      ex.printStackTrace();
/* 24:   */    }
/* 25:   */  }
/* 26:   */  
/* 27:   */  public int getDelay() {
/* 28:28 */    return 10;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public int getPeriod() {
/* 32:32 */    return 60;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public TimeUnit getTimeUnit() {
/* 36:36 */    return TimeUnit.MINUTES;
/* 37:   */  }
/* 38:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.util.MySQLProcedureJob
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */