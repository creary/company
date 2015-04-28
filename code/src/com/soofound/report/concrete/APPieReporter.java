/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.report.abstracts.PieReporter;
/*  4:   */
/*  5:   */public class APPieReporter extends PieReporter
/*  6:   */{
/*  7:   */  protected String getSQL() {
/*  8: 8 */    StringBuilder sqlText = new StringBuilder(200);
/*  9: 9 */    if ("model".equals(this.paras.get("flag"))) {
/* 10:10 */      sqlText.append("SELECT hardware_version,COUNT(id) val FROM cpe_host where branch_id like'").append(this.paras.get("branchId")).append("%'");
/* 11:11 */      sqlText.append(" and hardware_version not in ('','1.2','1.2-HP') GROUP BY hardware_version");
/* 12:   */    } else {
/* 13:13 */      sqlText.append("SELECT software_version,COUNT(id) val FROM cpe_host where branch_id like'").append(this.paras.get("branchId")).append("%'");
/* 14:14 */      sqlText.append(" and online = 1 GROUP BY software_version");
/* 15:   */    }
/* 16:16 */    sqlText.append(" ORDER BY val desc");
/* 17:17 */    if (this.debug)
/* 18:18 */      System.out.println("-----APPieReporter------\n" + sqlText.toString());
/* 19:19 */    return sqlText.toString();
/* 20:   */  }
/* 21:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.APPieReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */