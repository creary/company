/*  1:   */package com.soofound.report.abstracts;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.DBUtil;
/*  4:   */import java.sql.Connection;
/*  5:   */import java.sql.ResultSet;
/*  6:   */import java.sql.Statement;
/*  7:   */import java.util.ArrayList;
/*  8:   */import java.util.List;
/*  9:   */import java.util.Map;
/* 10:   */
/* 11:   */public abstract class PieReporter extends Reporter<Object[][][]>
/* 12:   */{
/* 13:   */  protected List<String> flags;
/* 14:   */  
/* 15:   */  public Object[][][] doReport(Map paras)
/* 16:   */  {
/* 17:17 */    this.paras = paras;
/* 18:18 */    Object[][][] results = (Object[][][])null;
/* 19:19 */    Connection conn = null;
/* 20:20 */    Statement stat = null;
/* 21:21 */    ResultSet rs = null;
/* 22:   */    try {
/* 23:23 */      conn = getConnection();
/* 24:24 */      stat = conn.createStatement();
/* 25:25 */      rs = stat.executeQuery(getSQL());
/* 26:26 */      List<String[]> rows = new ArrayList();
/* 27:27 */      while (rs.next()) {
/* 28:28 */        String[] row = new String[2];
/* 29:29 */        row[0] = rs.getString(1);
/* 30:30 */        row[1] = rs.getString(2);
/* 31:31 */        rows.add(row);
/* 32:   */      }
/* 33:33 */      if (!rows.isEmpty()) {
/* 34:34 */        results = new Object[1][rows.size()][2];
/* 35:35 */        for (int i = 0; i < rows.size(); i++) {
/* 36:36 */          results[0][i][0] = ((String[])rows.get(i))[0];
/* 37:   */          try {
/* 38:38 */            results[0][i][1] = Integer.valueOf(Integer.parseInt(((String[])rows.get(i))[1]));
/* 39:   */          } catch (Exception e) {
/* 40:40 */            results[0][i][1] = Integer.valueOf(0);
/* 41:   */          }
/* 42:   */        }
/* 43:   */      }
/* 44:   */    } catch (Exception e) {
/* 45:45 */      e.printStackTrace();
/* 46:   */    } finally {
/* 47:47 */      DBUtil.close(conn, stat, rs);
/* 48:   */    }
/* 49:49 */    return results;
/* 50:   */  }
/* 51:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.abstracts.PieReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */