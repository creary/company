/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.report.abstracts.HorizBarReporter;
/*  4:   */
/*  5:   */public class APLoadTopNReporter extends HorizBarReporter
/*  6:   */{
/*  7:   */  protected String getSQL() {
/*  8: 8 */    String groupBy = null;
/*  9: 9 */    if ("connect".equals(this.paras.get("flag"))) {
/* 10:10 */      groupBy = "count(DISTINCT mac)";
/* 11:   */    } else
/* 12:12 */      groupBy = "ROUND(SUM(input_octets + output_octets)/1024/1024)";
/* 13:13 */    StringBuilder sqlText = new StringBuilder(200);
/* 14:14 */    sqlText.append("SELECT cpe_id,ap_name alias,").append(groupBy).append(" cc FROM view_surfing_session_history ");
/* 15:15 */    sqlText.append(getWhereDateFormat((String)this.paras.get("tag")).replace("log_time", "start_time")).append(" and branch_id like '");
/* 16:16 */    sqlText.append(this.paras.get("branchId")).append("%' GROUP BY cpe_id,ap_name ORDER BY cc DESC LIMIT ").append(this.paras.get("topn"));
/* 17:17 */    if (this.debug)
/* 18:18 */      System.out.println("---APLoadTopNReporter---\n" + sqlText.toString());
/* 19:19 */    return sqlText.toString();
/* 20:   */  }
/* 21:   */  
/* 22:   */  protected int getColumnNum() {
/* 23:23 */    return 3;
/* 24:   */  }
/* 25:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.APLoadTopNReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */