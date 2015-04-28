/*  1:   */package com.soofound.report.abstracts;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.ConnectionManager;
/*  4:   */import com.soofound.framework.jdbc.DBUtil;
/*  5:   */import java.sql.Connection;
/*  6:   */import java.sql.ResultSet;
/*  7:   */import java.sql.Statement;
/*  8:   */import java.util.ArrayList;
/*  9:   */import java.util.List;
/* 10:   */import java.util.Map;
/* 11:   */
/* 12:   */public abstract class HorizBarReporter extends Reporter<Object[][]>
/* 13:   */{
/* 14:   */  public Object[][] doReport(Map paras)
/* 15:   */  {
/* 16:16 */    this.paras = paras;
/* 17:17 */    List<Object[]> items = new ArrayList();
/* 18:18 */    Object[][] results = (Object[][])null;
/* 19:19 */    Connection conn = null;
/* 20:20 */    Statement stat = null;
/* 21:21 */    ResultSet rs = null;
/* 22:   */    try {
/* 23:23 */      conn = ConnectionManager.getConnection();
/* 24:24 */      stat = conn.createStatement();
/* 25:25 */      rs = stat.executeQuery(getSQL());
/* 26:26 */      while (rs.next()) {
/* 27:27 */        Object[] item = (Object[])null;
/* 28:28 */        if (getColumnNum() == 2) {
/* 29:29 */          item = new Object[2];
/* 30:30 */          item[0] = rs.getString(1);
/* 31:31 */          item[1] = Long.valueOf(rs.getLong(2));
/* 32:   */        } else {
/* 33:33 */          item = new Object[3];
/* 34:34 */          item[0] = rs.getString(1);
/* 35:35 */          item[1] = rs.getString(2);
/* 36:36 */          item[2] = Long.valueOf(rs.getLong(3));
/* 37:   */        }
/* 38:38 */        items.add(item);
/* 39:   */      }
/* 40:40 */      if (!items.isEmpty()) {
/* 41:41 */        results = new Object[items.size()][getColumnNum()];
/* 42:42 */        items.toArray(results);
/* 43:   */      }
/* 44:   */    } catch (Exception e) {
/* 45:45 */      e.printStackTrace();
/* 46:   */    } finally {
/* 47:47 */      DBUtil.close(conn, stat, rs);
/* 48:   */    }
/* 49:49 */    return results;
/* 50:   */  }
/* 51:   */  
/* 52:   */  protected int getColumnNum() {
/* 53:53 */    return 2;
/* 54:   */  }
/* 55:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.abstracts.HorizBarReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */