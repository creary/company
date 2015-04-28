/*  1:   */package com.soofound.report.concrete;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.DBUtil;
/*  4:   */import com.soofound.report.abstracts.PieReporter;
/*  5:   */import java.io.PrintStream;
/*  6:   */import java.sql.Connection;
/*  7:   */import java.sql.ResultSet;
/*  8:   */import java.sql.Statement;
/*  9:   */import java.util.ArrayList;
/* 10:   */import java.util.List;
/* 11:   */
/* 12:   */public class StayLongPieReporter extends PieReporter
/* 13:   */{
/* 14:14 */  private static final String[] LABELS = { "<15分钟", "15-30分钟", "30-60分钟", "1-2小时", "2-4小时", ">4小时" };
/* 15:   */  
/* 16:   */  public Object[][][] doReport(java.util.Map paras)
/* 17:   */  {
/* 18:18 */    this.paras = paras;
/* 19:   */    
/* 20:20 */    Object[][][] results = (Object[][][])null;
/* 21:21 */    Connection conn = null;
/* 22:22 */    Statement stat = null;
/* 23:23 */    ResultSet rs = null;
/* 24:   */    try {
/* 25:25 */      conn = getConnection();
/* 26:26 */      stat = conn.createStatement();
/* 27:27 */      rs = stat.executeQuery(getSQL());
/* 28:28 */      List<String[]> rows = new ArrayList();
/* 29:29 */      while (rs.next()) {
/* 30:30 */        String[] row = new String[2];
/* 31:31 */        row[0] = rs.getString(1);
/* 32:32 */        row[1] = rs.getString(2);
/* 33:33 */        rows.add(row);
/* 34:   */      }
/* 35:35 */      results = new Object[1][LABELS.length][2];
/* 36:36 */      for (int i = 0; i < LABELS.length; i++) {
/* 37:37 */        boolean has = false;
/* 38:38 */        for (String[] row : rows) {
/* 39:39 */          if (LABELS[i].equals(row[0])) {
/* 40:40 */            results[0][i][0] = row[0];
/* 41:41 */            results[0][i][1] = Integer.valueOf(Integer.parseInt(row[1]));
/* 42:42 */            has = true;
/* 43:43 */            break;
/* 44:   */          }
/* 45:   */        }
/* 46:46 */        if (!has)
/* 47:47 */          results[0][i] = { LABELS[i], Integer.valueOf(0) };
/* 48:   */      }
/* 49:   */    } catch (Exception e) {
/* 50:50 */      e.printStackTrace();
/* 51:   */    } finally {
/* 52:52 */      DBUtil.close(conn, stat, rs);
/* 53:   */    }
/* 54:54 */    return results;
/* 55:   */  }
/* 56:   */  
/* 57:   */  protected String getSQL()
/* 58:   */  {
/* 59:59 */    StringBuilder sqlText = new StringBuilder(200);
/* 60:60 */    sqlText.append("SELECT CASE WHEN session_time<900 THEN '<15分钟' WHEN session_time< 1800 THEN '15-30分钟' WHEN ");
/* 61:61 */    sqlText.append("session_time< 3600 THEN '30-60分钟' WHEN session_time< 7200 THEN '1-2小时' WHEN session_time< 14400 THEN '2-4小时' ELSE '>4小时' END stay_long,COUNT(*) cc FROM view_surfing_session ");
/* 62:62 */    sqlText.append(getWhereDateFormat((String)this.paras.get("tag")).replace("log_time", "start_time")).append(" AND branch_id like '").append(this.paras.get("branchId")).append("%' GROUP BY stay_long");
/* 63:63 */    if (this.debug)
/* 64:64 */      System.out.println("-----StayLongPieReporter------\n" + sqlText.toString());
/* 65:65 */    return sqlText.toString();
/* 66:   */  }
/* 67:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.concrete.StayLongPieReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */