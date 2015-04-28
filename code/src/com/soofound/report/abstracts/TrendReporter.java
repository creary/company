/*   1:    */package com.soofound.report.abstracts;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.DBUtil;
/*   4:    */import com.soofound.framework.util.DateUtil;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.sql.Connection;
/*   7:    */import java.sql.ResultSet;
/*   8:    */import java.sql.Statement;
/*   9:    */import java.text.SimpleDateFormat;
/*  10:    */import java.util.ArrayList;
/*  11:    */import java.util.Date;
/*  12:    */import java.util.Map;
/*  13:    */
/*  14:    */public abstract class TrendReporter extends Reporter<Object[][][]>
/*  15:    */{
/*  16:    */  protected java.util.List<String> flags;
/*  17:    */  
/*  18:    */  public Object[][][] doReport(Map paras)
/*  19:    */  {
/*  20: 20 */    this.paras = paras;
/*  21: 21 */    Object[][][] results = (Object[][][])null;
/*  22: 22 */    Connection conn = null;
/*  23: 23 */    Statement stat = null;
/*  24: 24 */    ResultSet rs = null;
/*  25: 25 */    int flagSize = getFlags() == null ? 1 : getFlags().size();
/*  26: 26 */    String sql = getSQL();
/*  27:    */    try {
/*  28: 28 */      conn = getConnection();
/*  29: 29 */      stat = conn.createStatement();
/*  30: 30 */      rs = stat.executeQuery(sql);
/*  31: 31 */      java.util.List<String[]> rows = new ArrayList();
/*  32: 32 */      while (rs.next()) {
/*  33: 33 */        String[] row = new String[flagSize == 1 ? 2 : 3];
/*  34: 34 */        row[0] = rs.getString(1);
/*  35: 35 */        row[1] = rs.getString(2);
/*  36: 36 */        if (flagSize > 1)
/*  37: 37 */          row[2] = rs.getString(3);
/*  38: 38 */        rows.add(row);
/*  39:    */      }
/*  40: 40 */      java.util.List<String> timeLabels = getTimeLabels((String)paras.get("tag"));
/*  41: 41 */      results = new Object[flagSize][timeLabels.size()][2];
/*  42: 42 */      for (int j = 0; j < timeLabels.size(); j++) {
/*  43: 43 */        for (int i = 0; i < flagSize; i++) {
/*  44: 44 */          String flag = getFlags() == null ? null : (String)getFlags().get(i);
/*  45: 45 */          results[i][j][0] = timeLabels.get(j);
/*  46: 46 */          String cc = getArrayData(rows, (String)timeLabels.get(j), flag);
/*  47: 47 */          if (cc == null) {
/*  48: 48 */            results[i][j][1] = Integer.valueOf(0);
/*  49:    */          } else
/*  50: 50 */            results[i][j][1] = Long.valueOf(Long.parseLong(cc));
/*  51:    */        }
/*  52:    */      }
/*  53:    */    } catch (Exception e) {
/*  54: 54 */      e.printStackTrace();
/*  55: 55 */      System.out.println("error sql=" + sql);
/*  56:    */    } finally {
/*  57: 57 */      DBUtil.close(conn, stat, rs);
/*  58:    */    }
/*  59: 59 */    return results;
/*  60:    */  }
/*  61:    */  
/*  62:    */  protected String getArrayData(java.util.List<String[]> rows, String time, String flag) {
/*  63: 63 */    for (String[] row : rows) {
/*  64: 64 */      if (flag == null) {
/*  65: 65 */        if (row[0].equals(time)) {
/*  66: 66 */          return row[1];
/*  67:    */        }
/*  68: 68 */      } else if ((row[0].equals(time)) && (row[2].equals(flag))) {
/*  69: 69 */        return row[1];
/*  70:    */      }
/*  71:    */    }
/*  72: 72 */    return "0";
/*  73:    */  }
/*  74:    */  
/*  75:    */  public static java.util.List<String> getTimeLabels(String tag) {
/*  76: 76 */    java.util.List<String> times = new ArrayList();
/*  77: 77 */    if ("today".equals(tag)) {
/*  78: 78 */      for (int i = 0; i <= DateUtil.getCurrentIntHour(); i++) {
/*  79: 79 */        if (i < 10) {
/*  80: 80 */          times.add("0" + i);
/*  81:    */        } else
/*  82: 82 */          times.add(i);
/*  83:    */      }
/*  84: 84 */    } else if ("yesterday".equals(tag)) {
/*  85: 85 */      for (int i = 0; i < 24; i++) {
/*  86: 86 */        if (i < 10) {
/*  87: 87 */          times.add("0" + i);
/*  88:    */        } else
/*  89: 89 */          times.add(i);
/*  90:    */      }
/*  91: 91 */    } else if ("7day".equals(tag)) {
/*  92: 92 */      for (int i = 6; i >= 0; i--)
/*  93: 93 */        times.add(DateUtil.getDay(-i).substring(5));
/*  94: 94 */    } else if ("30day".equals(tag)) {
/*  95: 95 */      for (int i = 30; i >= 0; i--)
/*  96: 96 */        times.add(DateUtil.getDay(-i).substring(5));
/*  97: 97 */    } else if (tag.indexOf("#") > 0) {
/*  98: 98 */      String[] twoTimes = tag.split("#");
/*  99: 99 */      int diffHours = DateUtil.getDiffHours(twoTimes[0], twoTimes[1]);
/* 100:    */      try {
/* 101:101 */        if (diffHours > 24) {
/* 102:102 */          int diffDays = diffHours / 24;
/* 103:103 */          Date date1 = DateUtil.datetimeFormat.parse(twoTimes[0]);
/* 104:104 */          java.util.Calendar cal = java.util.Calendar.getInstance();
/* 105:105 */          cal.setTime(date1);
/* 106:106 */          times.add(dateFormat.format(cal.getTime()));
/* 107:107 */          for (int i = 0; i < diffDays; i++) {
/* 108:108 */            cal.add(5, 1);
/* 109:109 */            times.add(dateFormat.format(cal.getTime()));
/* 110:    */          }
/* 111:    */        } else {
/* 112:112 */          Date date1 = DateUtil.datetimeFormat.parse(twoTimes[0]);
/* 113:113 */          java.util.Calendar cal = java.util.Calendar.getInstance();
/* 114:114 */          cal.setTime(date1);
/* 115:115 */          for (int i = 0; i < diffHours; i++) {
/* 116:116 */            cal.add(10, 1);
/* 117:117 */            times.add(hourFormat.format(cal.getTime()));
/* 118:    */          }
/* 119:    */        }
/* 120:    */      } catch (Exception e) {
/* 121:121 */        e.printStackTrace();
/* 122:    */      }
/* 123:    */    }
/* 124:124 */    return times;
/* 125:    */  }
/* 126:    */  
/* 127:    */  protected abstract java.util.List<String> getFlags();
/* 128:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.abstracts.TrendReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */