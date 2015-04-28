/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.ConnectionManager;
/*  4:   */import com.soofound.report.abstracts.TrendReporter;
/*  5:   */import java.io.PrintStream;
/*  6:   */import java.sql.Connection;
/*  7:   */import java.util.ArrayList;
/*  8:   */import java.util.List;
/*  9:   */import java.util.Map;
/* 10:   */import org.springframework.util.StringUtils;
/* 11:   */
/* 12:   */public class DetectTrendReporter
/* 13:   */  extends TrendReporter
/* 14:   */{
/* 15:15 */  private static final List<String> flags = new ArrayList();
/* 16:16 */  static { flags.add("detect");
/* 17:17 */    flags.add("enter");
/* 18:18 */    flags.add("auth");
/* 19:   */  }
/* 20:   */  
/* 21:   */  protected String getSQL()
/* 22:   */  {
/* 23:23 */    String tag = (String)this.paras.get("tag");
/* 24:24 */    StringBuilder sqlText = new StringBuilder(200);
/* 25:25 */    sqlText.append("SELECT ").append(getSelectByDateFormat(tag)).append(",SUM(total),flag FROM detect_summary ");
/* 26:26 */    sqlText.append(getWhereDateFormat(tag)).append(" AND ap_mac in (");
/* 27:27 */    if (StringUtils.hasText((String)this.paras.get("apmacs"))) {
/* 28:28 */      sqlText.append(this.paras.get("apmacs")).append(")");
/* 29:   */    } else
/* 30:30 */      sqlText.append("'soofound')");
/* 31:31 */    sqlText.append(getGroupByDateFormat(tag)).append(",flag ORDER BY log_time");
/* 32:32 */    if (this.debug)
/* 33:33 */      System.out.println("----DetectTrendReporter----\n" + sqlText.toString());
/* 34:34 */    return sqlText.toString();
/* 35:   */  }
/* 36:   */  
/* 37:   */  protected List<String> getFlags()
/* 38:   */  {
/* 39:39 */    return flags;
/* 40:   */  }
/* 41:   */  
/* 42:   */  protected Connection getConnection()
/* 43:   */  {
/* 44:44 */    return ConnectionManager.getConnection("acsideDS");
/* 45:   */  }
/* 46:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.DetectTrendReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */