/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.report.abstracts.PieReporter;
/*  4:   */
/*  5:   */public class TrafficBarReporter extends PieReporter
/*  6:   */{
/*  7:   */  protected String getSQL() {
/*  8: 8 */    StringBuilder sqlText = new StringBuilder(200);
/*  9: 9 */    if ("ap".equals(this.paras.get("flag"))) {
/* 10:10 */      sqlText.append("SELECT ap_name ap,ROUND(SUM(input_octets + output_octets)/1024/1024) val FROM view_surfing_session_history ");
/* 11:11 */      sqlText.append(getWhereDateFormat((String)this.paras.get("tag")).replace("log_time", "start_time"));
/* 12:12 */      sqlText.append(" AND branch_id LIKE '").append(this.paras.get("branchId")).append("%' GROUP BY ap ORDER BY val DESC LIMIT 10");
/* 13:   */    } else {
/* 14:14 */      sqlText.append("SELECT branch,ROUND(SUM(input_octets + output_octets)/1024/1024) val FROM view_surfing_session_history ");
/* 15:15 */      sqlText.append(getWhereDateFormat((String)this.paras.get("tag")).replace("log_time", "start_time"));
/* 16:16 */      sqlText.append(" AND branch_id LIKE '").append(this.paras.get("branchId")).append("%' GROUP BY branch ORDER BY val DESC LIMIT 10");
/* 17:   */    }
/* 18:18 */    if (this.debug)
/* 19:19 */      System.out.println("-----TrafficBarReporter------\n" + sqlText.toString());
/* 20:20 */    return sqlText.toString();
/* 21:   */  }
/* 22:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.TrafficBarReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */