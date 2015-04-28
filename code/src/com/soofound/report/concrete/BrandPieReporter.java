/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.DBUtil;
/*  4:   */import com.soofound.framework.util.SysConfigHelper;
/*  5:   */import com.soofound.portal.custom.CustomFactory;
/*  6:   */import com.soofound.report.abstracts.PieReporter;
/*  7:   */import java.io.PrintStream;
/*  8:   */import java.sql.Connection;
/*  9:   */import java.sql.ResultSet;
/* 10:   */import java.sql.Statement;
/* 11:   */import java.util.ArrayList;
/* 12:   */import java.util.Map;
/* 13:   */
/* 14:   */public class BrandPieReporter extends PieReporter
/* 15:   */{
/* 16:   */  public Object[][][] doReport(Map paras)
/* 17:   */  {
/* 18:18 */    this.paras = paras;
/* 19:19 */    Object[][][] results = (Object[][][])null;
/* 20:20 */    Connection conn = null;
/* 21:21 */    Statement stat = null;
/* 22:22 */    ResultSet rs = null;
/* 23:   */    try {
/* 24:24 */      conn = getConnection();
/* 25:25 */      stat = conn.createStatement();
/* 26:26 */      rs = stat.executeQuery(getSQL());
/* 27:27 */      java.util.List<String[]> rows = new ArrayList();
/* 28:28 */      int otherCount = 0;
/* 29:29 */      CustomFactory fac = (CustomFactory)SysConfigHelper.getBean("customFactory");
/* 30:30 */      while (rs.next())
/* 31:31 */        if (fac.isBigBrand(rs.getString(1))) {
/* 32:32 */          String[] row = new String[2];
/* 33:33 */          row[0] = rs.getString(1);
/* 34:34 */          row[1] = rs.getString(2);
/* 35:35 */          rows.add(row);
/* 36:   */        } else {
/* 37:37 */          otherCount++;
/* 38:   */        }
/* 39:39 */      if (otherCount > 0)
/* 40:40 */        rows.add(new String[] { "其它", otherCount });
/* 41:41 */      if (!rows.isEmpty()) {
/* 42:42 */        results = new Object[1][rows.size()][2];
/* 43:43 */        for (int i = 0; i < rows.size(); i++) {
/* 44:44 */          results[0][i][0] = ((String[])rows.get(i))[0];
/* 45:45 */          results[0][i][1] = Integer.valueOf(Integer.parseInt(((String[])rows.get(i))[1]));
/* 46:   */        }
/* 47:   */      }
/* 48:   */    } catch (Exception e) {
/* 49:49 */      e.printStackTrace();
/* 50:   */    } finally {
/* 51:51 */      DBUtil.close(conn, stat, rs);
/* 52:   */    }
/* 53:53 */    return results;
/* 54:   */  }
/* 55:   */  
/* 56:   */  protected String getSQL()
/* 57:   */  {
/* 58:58 */    StringBuilder sqlText = new StringBuilder(200);
/* 59:59 */    sqlText.append("SELECT brand,COUNT(session_id) cc FROM view_surfing_session_history ").append(getWhereDateFormat((String)this.paras.get("tag")).replace("log_time", "start_time"));
/* 60:60 */    sqlText.append(" AND branch_id like '").append(this.paras.get("branchId")).append("%' AND brand IS NOT NULL GROUP BY brand ORDER BY cc DESC");
/* 61:61 */    if (this.debug)
/* 62:62 */      System.out.println("-----BrandPieReporter------\n" + sqlText.toString());
/* 63:63 */    return sqlText.toString();
/* 64:   */  }
/* 65:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.BrandPieReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */