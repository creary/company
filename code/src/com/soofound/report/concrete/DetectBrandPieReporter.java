/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.ConnectionManager;
/*  4:   */import java.io.PrintStream;
/*  5:   */
/*  6:   */public class DetectBrandPieReporter extends BrandPieReporter
/*  7:   */{
/*  8:   */  protected String getSQL()
/*  9:   */  {
/* 10:10 */    StringBuilder sqlText = new StringBuilder(200);
/* 11:11 */    sqlText.append("SELECT brand alias,COUNT(*) cc FROM detect_ap_mac ").append(getWhereDateFormat((String)this.paras.get("tag")));
/* 12:12 */    sqlText.append(" AND ap_mac in (").append(this.paras.get("apmacs")).append(") ");
/* 13:13 */    if ("auth".equals(this.paras.get("flag")))
/* 14:14 */      sqlText.append(" AND auth = 2");
/* 15:15 */    if ("enter".equals(this.paras.get("flag")))
/* 16:16 */      sqlText.append("AND (auth = 1 or auth = 2)");
/* 17:17 */    sqlText.append(" GROUP BY brand");
/* 18:18 */    if (this.debug)
/* 19:19 */      System.out.println("-----DetectBrandPieReporter-----\n" + sqlText.toString());
/* 20:20 */    return sqlText.toString();
/* 21:   */  }
/* 22:   */  
/* 23:   */  protected java.sql.Connection getConnection()
/* 24:   */  {
/* 25:25 */    return ConnectionManager.getConnection("acsideDS");
/* 26:   */  }
/* 27:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.DetectBrandPieReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */