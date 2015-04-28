/*   1:    */package com.soofound.framework.util;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.ConnectionManager;
/*   4:    */import com.soofound.framework.jdbc.DBUtil;
/*   5:    */import java.io.BufferedReader;
/*   6:    */import java.io.FileOutputStream;
/*   7:    */import java.io.InputStream;
/*   8:    */import java.io.InputStreamReader;
/*   9:    */import java.io.OutputStreamWriter;
/*  10:    */import java.sql.Connection;
/*  11:    */import java.sql.ResultSet;
/*  12:    */import java.sql.Statement;
/*  13:    */import java.util.ArrayList;
/*  14:    */import java.util.HashMap;
/*  15:    */import java.util.List;
/*  16:    */import java.util.Map;
/*  17:    */import org.apache.commons.dbcp.BasicDataSource;
/*  18:    */
/*  19:    */public class MySQLDump
/*  20:    */{
/*  21:    */  private Connection conn;
/*  22:    */  
/*  23:    */  public MySQLDump()
/*  24:    */  {
/*  25: 25 */    this.conn = ConnectionManager.getConnection();
/*  26:    */  }
/*  27:    */  
/*  28:    */  private String getMySQLHome() {
/*  29: 29 */    String mysqlHome = null;
/*  30: 30 */    Statement stat = null;
/*  31: 31 */    ResultSet rs = null;
/*  32:    */    try {
/*  33: 33 */      stat = this.conn.createStatement();
/*  34: 34 */      rs = stat.executeQuery("show variables where Variable_name='basedir'");
/*  35: 35 */      if (rs.next())
/*  36: 36 */        mysqlHome = rs.getString("Value");
/*  37:    */    } catch (Exception e) {
/*  38: 38 */      e.printStackTrace();
/*  39:    */    } finally {
/*  40: 40 */      DBUtil.close(null, stat, rs);
/*  41:    */    }
/*  42: 42 */    return mysqlHome;
/*  43:    */  }
/*  44:    */  
/*  45:    */  private String getDBName(String url) {
/*  46: 46 */    return url.split("/")[3].split("\\?")[0];
/*  47:    */  }
/*  48:    */  
/*  49:    */  private String getDBPort(String url) {
/*  50: 50 */    return url.split("/")[2].split(":")[1];
/*  51:    */  }
/*  52:    */  
/*  55:    */  public void doDump(List<String> tableNames, String sqlFile)
/*  56:    */  {
/*  57: 57 */    BasicDataSource ds = (BasicDataSource)SysConfigHelper.getBean("DefaultDS");
/*  58: 58 */    StringBuffer cmd = new StringBuffer(100);
/*  59: 59 */    cmd.append(getMySQLHome()).append("bin/");
/*  60: 60 */    cmd.append("mysqldump --add-drop-table -u").append(ds.getUsername());
/*  61: 61 */    if (!"".equals(ds.getPassword()))
/*  62: 62 */      cmd.append(" -p").append(ds.getPassword()).append(" ");
/*  63: 63 */    cmd.append(" -P").append(getDBPort(ds.getUrl())).append(" ");
/*  64: 64 */    cmd.append(getDBName(ds.getUrl())).append(" ");
/*  65: 65 */    for (String tn : tableNames) {
/*  66: 66 */      cmd.append(tn).append(" ");
/*  67:    */    }
/*  68: 68 */    Process child = null;
/*  69: 69 */    InputStream in = null;
/*  70: 70 */    InputStreamReader insr = null;
/*  71: 71 */    BufferedReader br = null;
/*  72: 72 */    FileOutputStream fout = null;
/*  73: 73 */    OutputStreamWriter writer = null;
/*  74:    */    try {
/*  75: 75 */      child = Runtime.getRuntime().exec(cmd.toString());
/*  76: 76 */      in = child.getInputStream();
/*  77: 77 */      insr = new InputStreamReader(in, "utf8");
/*  78: 78 */      StringBuffer sb = new StringBuffer(1000);
/*  79: 79 */      br = new BufferedReader(insr);
/*  80: 80 */      String inStr = null;
/*  81: 81 */      while ((inStr = br.readLine()) != null) {
/*  82: 82 */        sb.append(inStr + "\r\n");
/*  83:    */      }
/*  84: 84 */      fout = new FileOutputStream(sqlFile);
/*  85: 85 */      writer = new OutputStreamWriter(fout, "utf8");
/*  86: 86 */      writer.write(sb.toString());
/*  87: 87 */      writer.flush();
/*  88:    */    } catch (Exception e) {
/*  89: 89 */      e.printStackTrace();
/*  90:    */      
/*  91: 91 */      DBUtil.close(this.conn);
/*  92:    */      try {
/*  93: 93 */        child.destroy();
/*  94: 94 */        in.close();
/*  95: 95 */        insr.close();
/*  96: 96 */        br.close();
/*  97: 97 */        writer.close();
/*  98: 98 */        fout.close();
/*  99:    */      }
/* 100:    */      catch (Exception localException1) {}
/* 101:    */    }
/* 102:    */    finally
/* 103:    */    {
/* 104: 91 */      DBUtil.close(this.conn);
/* 105:    */      try {
/* 106: 93 */        child.destroy();
/* 107: 94 */        in.close();
/* 108: 95 */        insr.close();
/* 109: 96 */        br.close();
/* 110: 97 */        writer.close();
/* 111: 98 */        fout.close();
/* 112:    */      }
/* 113:    */      catch (Exception localException2) {}
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void doDump(String sqlFile) {
/* 118:105 */    List<String> existTables = new ArrayList();
/* 119:106 */    Statement stat = null;
/* 120:107 */    ResultSet rs = null;
/* 121:    */    try {
/* 122:109 */      stat = this.conn.createStatement();
/* 123:110 */      rs = stat.executeQuery("show table status");
/* 124:111 */      while (rs.next()) {
/* 125:112 */        if ((!rs.getString("name").startsWith("bag_")) && 
/* 126:113 */          (!rs.getString("name").startsWith("message_")))
/* 127:114 */          existTables.add(rs.getString("name"));
/* 128:    */      }
/* 129:    */    } catch (Exception e) {
/* 130:117 */      e.printStackTrace();
/* 131:    */    } finally {
/* 132:119 */      DBUtil.close(null, stat, rs);
/* 133:    */    }
/* 134:121 */    doDump(existTables, sqlFile);
/* 135:    */  }
/* 136:    */  
/* 137:    */  public Map<String, String> getMySQLInfos() {
/* 138:125 */    Map<String, String> infos = new HashMap();
/* 139:126 */    Statement stat = null;
/* 140:127 */    ResultSet rs = null;
/* 141:    */    try {
/* 142:129 */      stat = this.conn.createStatement();
/* 143:130 */      rs = stat.executeQuery("show variables");
/* 144:131 */      while (rs.next()) {
/* 145:132 */        if (rs.getString("variable_name").equalsIgnoreCase("basedir")) {
/* 146:133 */          infos.put("mysql_install_path", rs.getString("value").replace("/", "\\"));
/* 147:134 */        } else if (rs.getString("variable_name").equalsIgnoreCase("datadir")) {
/* 148:135 */          infos.put("mysql_data_path", rs.getString("value"));
/* 149:136 */        } else if (rs.getString("variable_name").equalsIgnoreCase("version")) {
/* 150:137 */          infos.put("mysql_version", rs.getString("value"));
/* 151:138 */        } else if (rs.getString("variable_name").equalsIgnoreCase("version_compile_os"))
/* 152:139 */          infos.put("mysql_os", rs.getString("value"));
/* 153:    */      }
/* 154:141 */      rs = stat.executeQuery("show table status");
/* 155:142 */      long dbsize = 0L;
/* 156:143 */      while (rs.next())
/* 157:144 */        dbsize += rs.getLong("data_length");
/* 158:145 */      dbsize = dbsize / 1024L / 1024L;
/* 159:146 */      infos.put("mysql_data_size", dbsize + " MB");
/* 160:147 */      return infos;
/* 161:    */    } catch (Exception e) {
/* 162:149 */      e.printStackTrace();
/* 163:    */    } finally {
/* 164:151 */      DBUtil.close(this.conn, stat, rs);
/* 165:    */    }
/* 166:153 */    return infos;
/* 167:    */  }
/* 168:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.util.MySQLDump
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */