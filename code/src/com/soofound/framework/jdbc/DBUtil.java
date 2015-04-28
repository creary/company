/*   1:    */package com.soofound.framework.jdbc;
/*   2:    */
/*   3:    */import com.soofound.framework.util.CommonUtil;
/*   4:    */import com.soofound.framework.util.SysConfigHelper;
/*   5:    */import java.sql.BatchUpdateException;
/*   6:    */import java.sql.Connection;
/*   7:    */import java.sql.ResultSet;
/*   8:    */import java.sql.SQLException;
/*   9:    */import java.sql.Statement;
/*  10:    */import java.util.ArrayList;
/*  11:    */import java.util.List;
/*  12:    */import org.apache.log4j.Logger;
/*  13:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  14:    */
/*  16:    */public class DBUtil
/*  17:    */{
/*  18: 18 */  private static Logger logger = Logger.getLogger(ConnectionManager.class);
/*  19:    */  
/*  22:    */  public static void executeBatch(List<String> sqls)
/*  23:    */  {
/*  24: 24 */    if (CommonUtil.isEmpty(sqls)) { return;
/*  25:    */    }
/*  26: 26 */    JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/*  27: 27 */    String[] arrsqls = new String[sqls.size()];
/*  28: 28 */    sqls.toArray(arrsqls);
/*  29: 29 */    jdbc.batchUpdate(arrsqls);
/*  30:    */  }
/*  31:    */  
/*  37:    */  public static List<String> executeBatch(Connection conn, List<String> sqls, boolean transaction)
/*  38:    */  {
/*  39: 39 */    Statement stmt = null;
/*  40: 40 */    List<String> errs = null;
/*  41:    */    try {
/*  42: 42 */      if (transaction)
/*  43: 43 */        conn.setAutoCommit(false);
/*  44: 44 */      stmt = conn.createStatement();
/*  45: 45 */      for (String sql : sqls)
/*  46: 46 */        stmt.addBatch(sql);
/*  47: 47 */      stmt.executeBatch();
/*  48:    */    } catch (SQLException e) {
/*  49: 49 */      e.printStackTrace();
/*  50: 50 */      if (transaction)
/*  51: 51 */        rollback(conn);
/*  52: 52 */      if ((e instanceof BatchUpdateException)) {
/*  53: 53 */        errs = new ArrayList();
/*  54: 54 */        BatchUpdateException buex = (BatchUpdateException)e;
/*  55: 55 */        int[] resultArray = buex.getUpdateCounts();
/*  56: 56 */        for (int i = 0; i < resultArray.length; i++) {
/*  57: 57 */          if (resultArray[i] == -3) {
/*  58: 58 */            errs.add("SQL执行失败:" + (String)sqls.get(i));
/*  59: 59 */            logger.error("SQL执行失败:" + (String)sqls.get(i));
/*  60:    */          }
/*  61:    */        }
/*  62:    */      }
/*  63:    */    } finally {
/*  64:    */      try {
/*  65: 65 */        conn.setAutoCommit(true);
/*  66:    */      }
/*  67:    */      catch (Exception localException1) {}
/*  68: 68 */      close(null, stmt, null);
/*  69:    */    }
/*  70: 70 */    return errs;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public static synchronized String execute(Connection conn, String sql) {
/*  74: 74 */    Statement stat = null;
/*  75: 75 */    String err = null;
/*  76:    */    try {
/*  77: 77 */      stat = conn.createStatement();
/*  78: 78 */      stat.executeUpdate(sql);
/*  79:    */    } catch (SQLException e) {
/*  80: 80 */      e.printStackTrace();
/*  81: 81 */      err = e.getMessage();
/*  82:    */    } finally {
/*  83: 83 */      close(null, stat, null);
/*  84:    */    }
/*  85: 85 */    return err;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public static synchronized String execute(String sql) {
/*  89: 89 */    Statement stat = null;
/*  90: 90 */    String err = null;
/*  91: 91 */    Connection conn = null;
/*  92:    */    try {
/*  93: 93 */      conn = ConnectionManager.getConnection();
/*  94: 94 */      stat = conn.createStatement();
/*  95: 95 */      stat.executeUpdate(sql);
/*  96:    */    } catch (Exception e) {
/*  97: 97 */      e.printStackTrace();
/*  98: 98 */      err = e.getMessage();
/*  99:    */    } finally {
/* 100:100 */      close(conn, stat, null);
/* 101:    */    }
/* 102:102 */    return err;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public static void close(Connection conn) {
/* 106:    */    try {
/* 107:107 */      if ((conn != null) && (!conn.isClosed()))
/* 108:108 */        conn.close();
/* 109:    */    } catch (SQLException se) {
/* 110:110 */      se.printStackTrace();
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 114:    */  public static void close(Connection conn, Statement stat, ResultSet rs) {
/* 115:    */    try {
/* 116:116 */      if ((conn != null) && (!conn.isClosed()))
/* 117:117 */        conn.close();
/* 118:118 */      if (stat != null)
/* 119:119 */        stat.close();
/* 120:120 */      if (rs != null)
/* 121:121 */        rs.close();
/* 122:    */    } catch (SQLException se) {
/* 123:123 */      se.printStackTrace();
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 127:    */  public static void rollback(Connection conn) {
/* 128:    */    try {
/* 129:129 */      if ((conn == null) || (conn.isClosed()))
/* 130:130 */        return;
/* 131:131 */      if (!conn.getAutoCommit())
/* 132:132 */        conn.rollback();
/* 133:    */    } catch (SQLException se) {
/* 134:134 */      se.printStackTrace();
/* 135:    */    }
/* 136:    */  }
/* 137:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.jdbc.DBUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */