/*  1:   */package com.soofound.portal.util;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */
/*  5:   */public class WriteAuthForDetect implements Runnable
/*  6:   */{
/*  7:   */  private com.soofound.portal.bean.SurfingUser user;
/*  8:   */  private org.springframework.jdbc.core.JdbcTemplate jdbc;
/*  9:   */  
/* 10:   */  public WriteAuthForDetect(com.soofound.portal.bean.SurfingUser user, org.springframework.jdbc.core.JdbcTemplate jdbc)
/* 11:   */  {
/* 12:12 */    this.user = user;
/* 13:13 */    this.jdbc = jdbc;
/* 14:   */  }
/* 15:   */  
/* 16:   */  public void run()
/* 17:   */  {
/* 18:18 */    StringBuilder sqlText = new StringBuilder(100);
/* 19:   */    try {
/* 20:20 */      String tableName = "detect_flow_" + this.user.getCpeMac().replace(":", "");
/* 21:21 */      sqlText.append("update ").append(tableName).append(" set auth = 2 where mac='");
/* 22:22 */      sqlText.append(this.user.getTerminalMac()).append("' and log_time > DATE_ADD(Now(),INTERVAL - 1 minute)");
/* 23:23 */      this.jdbc.update(sqlText.toString());
/* 24:24 */      System.out.println(sqlText.toString());
/* 25:   */    }
/* 26:   */    catch (Exception localException) {}
/* 27:   */  }
/* 28:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.util.WriteAuthForDetect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */