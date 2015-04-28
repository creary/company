/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.report.abstracts.HorizBarReporter;
/*  4:   */
/*  5:   */public class WifiUsedBarReporter extends HorizBarReporter {
/*  6:   */  protected String getSQL() {
/*  7: 7 */    String groupBy = null;
/*  8: 8 */    if ("stayLong".equals(this.paras.get("flag"))) {
/*  9: 9 */      groupBy = "TIMESTAMPDIFF(SECOND,start_time,CASE WHEN stop_time IS NULL THEN NOW() ELSE stop_time END)";
/* 10:   */    } else
/* 11:11 */      groupBy = "ROUND(SUM(input_octets + output_octets)/1024/1024)";
/* 12:12 */    StringBuilder sqlText = new StringBuilder(200);
/* 13:13 */    sqlText.append("SELECT CONCAT(username,'(',branch,')') alias,").append(groupBy).append(" cc FROM view_surfing_session_history ");
/* 14:14 */    sqlText.append(getWhereDateFormat((String)this.paras.get("tag")).replace("log_time", "start_time")).append(" and branch_id like '");
/* 15:15 */    sqlText.append(this.paras.get("branchId")).append("%'");
/* 16:16 */    sqlText.append(" GROUP BY username ORDER BY cc DESC LIMIT ").append(this.paras.get("topn"));
/* 17:17 */    if (this.debug)
/* 18:18 */      System.out.println("---WifiUsedBarReporter---\n" + sqlText.toString());
/* 19:19 */    return sqlText.toString();
/* 20:   */  }
/* 21:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.WifiUsedBarReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */