/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.ConnectionManager;
/*  4:   */import com.soofound.report.abstracts.PieReporter;
/*  5:   */import java.io.PrintStream;
/*  6:   */
/*  7:   */public class VisitPieReporter extends PieReporter
/*  8:   */{
/*  9:   */  protected String getSQL()
/* 10:   */  {
/* 11:11 */    StringBuilder sqlText = new StringBuilder(200);
/* 12:12 */    if ("province".equals(this.paras.get("flag"))) {
/* 13:13 */      sqlText.append("SELECT province,COUNT(mac) val FROM surfing_session ").append(getWhereDateFormat((String)this.paras.get("tag")));
/* 14:14 */      sqlText.append(" AND province <>'null' GROUP BY province ORDER BY val desc");
/* 15:   */    } else {
/* 16:16 */      sqlText.append("SELECT realm,COUNT(mac) val FROM surfing_session ").append(getWhereDateFormat((String)this.paras.get("tag")));
/* 17:17 */      sqlText.append(" AND realm <>'1' GROUP BY realm ORDER BY val desc");
/* 18:   */    }
/* 19:19 */    if (this.debug)
/* 20:20 */      System.out.println("-----VisitPieReporter------\n" + sqlText.toString());
/* 21:21 */    return sqlText.toString();
/* 22:   */  }
/* 23:   */  
/* 24:   */  protected java.sql.Connection getConnection()
/* 25:   */  {
/* 26:26 */    return ConnectionManager.getConnection("acsideDS");
/* 27:   */  }
/* 28:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.VisitPieReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */