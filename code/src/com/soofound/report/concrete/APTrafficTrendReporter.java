/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.List;
/*  5:   */
/*  6:   */public class APTrafficTrendReporter extends com.soofound.report.abstracts.TrendReporter
/*  7:   */{
/*  8:   */  protected String getSQL()
/*  9:   */  {
/* 10:10 */    String tag = (String)this.paras.get("tag");
/* 11:11 */    String date = getWhereDateFormat(tag).replace("log_time", "start_time");
/* 12:12 */    StringBuilder sqlText = new StringBuilder(200);
/* 13:13 */    sqlText.append("SELECT ").append(getSelectByDateFormat(tag).replace("log_time", "start_time")).append(",ROUND(SUM(input_octets + output_octets)/1024/1024) cc ");
/* 14:14 */    sqlText.append("FROM ( select start_time,input_octets,output_octets,branch_id from surfing_session union select start_time,input_octets,output_octets,branch_id from surfing_session_history");
/* 15:15 */    sqlText.append(date).append(") ht ").append(date).append(" AND branch_id like '").append(this.paras.get("branchId"));
/* 16:16 */    sqlText.append("%'").append(getGroupByDateFormat(tag).replace("log_time", "start_time")).append(" ORDER BY start_time");
/* 17:17 */    if (this.debug)
/* 18:18 */      System.out.println("----EndUserCountTrendReporter----\n" + sqlText.toString());
/* 19:19 */    return sqlText.toString();
/* 20:   */  }
/* 21:   */  
/* 22:   */  protected List<String> getFlags()
/* 23:   */  {
/* 24:24 */    return null;
/* 25:   */  }
/* 26:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.APTrafficTrendReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */