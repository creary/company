/*  1:   */package com.soofound.report.abstracts;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.ConnectionManager;
/*  4:   */import com.soofound.framework.util.DateUtil;
/*  5:   */import java.sql.Connection;
/*  6:   */import java.text.SimpleDateFormat;
/*  7:   */import java.util.Map;
/*  8:   */
/*  9:   */public abstract class Reporter<T>
/* 10:   */{
/* 11:11 */  protected static final SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
/* 12:12 */  protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
/* 13:   */  protected Map paras;
/* 14:14 */  protected boolean debug = false;
/* 15:   */  
/* 16:   */  public abstract T doReport(Map paramMap);
/* 17:   */  
/* 18:   */  protected abstract String getSQL();
/* 19:   */  
/* 20:   */  protected String getSelectByDateFormat(String tag) {
/* 21:21 */    if (("7day".equals(tag)) || ("30day".equals(tag)))
/* 22:22 */      return " DATE_FORMAT(log_time,'%m-%d') log_time ";
/* 23:23 */    if (tag.indexOf("#") > 0) {
/* 24:24 */      String[] times = tag.split("#");
/* 25:25 */      int diffHours = DateUtil.getDiffHours(times[0], times[1]);
/* 26:26 */      if (diffHours > 24) {
/* 27:27 */        return " DATE_FORMAT(log_time,'%m-%d') log_time ";
/* 28:   */      }
/* 29:29 */      return " DATE_FORMAT(log_time,'%H') log_time ";
/* 30:   */    }
/* 31:31 */    return " DATE_FORMAT(log_time,'%H') log_time ";
/* 32:   */  }
/* 33:   */  
/* 34:   */  protected String getGroupByDateFormat(String tag) {
/* 35:35 */    if (("7day".equals(tag)) || ("30day".equals(tag)))
/* 36:36 */      return " Group By DATE_FORMAT(log_time,'%m-%d')";
/* 37:37 */    if (tag.indexOf("#") > 0) {
/* 38:38 */      String[] times = tag.split("#");
/* 39:39 */      int diffHours = DateUtil.getDiffHours(times[0], times[1]);
/* 40:40 */      if (diffHours > 24) {
/* 41:41 */        return " Group By DATE_FORMAT(log_time,'%m-%d')";
/* 42:   */      }
/* 43:43 */      return " Group By DATE_FORMAT(log_time,'%H')";
/* 44:   */    }
/* 45:45 */    return " Group By DATE_FORMAT(log_time,'%H')";
/* 46:   */  }
/* 47:   */  
/* 48:   */  protected String getWhereDateFormat(String tag) {
/* 49:49 */    if ("7day".equals(tag))
/* 50:50 */      return " WHERE log_time>=DATE_ADD(NOW(),INTERVAL-7 DAY)";
/* 51:51 */    if ("30day".equals(tag))
/* 52:52 */      return " WHERE log_time>=DATE_ADD(NOW(),INTERVAL-30 DAY)";
/* 53:53 */    if ("yesterday".equals(tag))
/* 54:54 */      return " WHERE DATE_FORMAT(log_time,'%Y-%m-%d')='" + DateUtil.getLastDay() + "'";
/* 55:55 */    if ("today".equals(tag))
/* 56:56 */      return " WHERE DATE_FORMAT(log_time,'%Y-%m-%d')='" + DateUtil.getCurrentDate() + "'";
/* 57:57 */    if (tag.indexOf("#") > 0) {
/* 58:58 */      String[] times = tag.split("#");
/* 59:59 */      return " WHERE log_time>='" + times[0] + "' and log_time<='" + times[1] + "'";
/* 60:   */    }
/* 61:61 */    return "";
/* 62:   */  }
/* 63:   */  
/* 64:   */  protected Connection getConnection() {
/* 65:65 */    return ConnectionManager.getConnection();
/* 66:   */  }
/* 67:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.abstracts.Reporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */