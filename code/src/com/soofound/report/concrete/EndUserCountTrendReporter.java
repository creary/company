/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.report.abstracts.TrendReporter;
/*  4:   */import java.io.PrintStream;
/*  5:   */import java.util.ArrayList;
/*  6:   */import java.util.List;
/*  7:   */import java.util.Map;
/*  8:   */
/*  9:   */public class EndUserCountTrendReporter extends TrendReporter
/* 10:   */{
/* 11:11 */  private static List<String> flags = new ArrayList();
/* 12:12 */  static { flags.add("total");
/* 13:13 */    flags.add("active");
/* 14:14 */    flags.add("db");
/* 15:15 */    flags.add("wechat");
/* 16:16 */    flags.add("mobile");
/* 17:17 */    flags.add("free");
/* 18:18 */    flags.add("thirdParty");
/* 19:   */  }
/* 20:   */  
/* 21:   */  protected String getSQL()
/* 22:   */  {
/* 23:23 */    String tag = (String)this.paras.get("tag");
/* 24:24 */    StringBuilder sqlText = new StringBuilder(200);
/* 25:25 */    sqlText.append("SELECT ").append(getSelectByDateFormat(tag)).append(",sum(total),flag FROM online_user_summary ");
/* 26:26 */    sqlText.append(getWhereDateFormat(tag)).append(" AND branch_id like '").append(this.paras.get("branchId"));
/* 27:27 */    sqlText.append("%'").append(getGroupByDateFormat(tag)).append(",flag ORDER BY log_time,flag");
/* 28:28 */    if (this.debug)
/* 29:29 */      System.out.println("----EndUserCountTrendReporter----\n" + sqlText.toString());
/* 30:30 */    return sqlText.toString();
/* 31:   */  }
/* 32:   */  
/* 33:   */  protected List<String> getFlags()
/* 34:   */  {
/* 35:35 */    return flags;
/* 36:   */  }
/* 37:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.EndUserCountTrendReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */