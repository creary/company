/*   1:    */package com.soofound.portal.util;
/*   2:    */
/*   3:    */import com.soofound.cpe.util.SoofacACS;
/*   4:    */import com.soofound.framework.jdbc.ConnectionManager;
/*   5:    */import com.soofound.framework.jdbc.DBUtil;
/*   6:    */import com.soofound.framework.job.ScheduleJob;
/*   7:    */import com.soofound.framework.util.SysConfigHelper;
/*   8:    */import java.io.BufferedReader;
/*   9:    */import java.io.FileOutputStream;
/*  10:    */import java.io.InputStream;
/*  11:    */import java.io.InputStreamReader;
/*  12:    */import java.io.OutputStreamWriter;
/*  13:    */import java.sql.Connection;
/*  14:    */import java.sql.ResultSet;
/*  15:    */import java.sql.Statement;
/*  16:    */import java.text.SimpleDateFormat;
/*  17:    */import java.util.ArrayList;
/*  18:    */import java.util.Calendar;
/*  19:    */import java.util.List;
/*  20:    */import java.util.concurrent.TimeUnit;
/*  21:    */
/*  22:    */public class BackupJob implements ScheduleJob
/*  23:    */{
/*  24: 24 */  private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyyMMddHH");
/*  25: 25 */  private static final String BACK_PATH = SysConfigHelper.getAttribute("sysPath") + "/backup/";
/*  26: 26 */  private static List<String> tables = new ArrayList();
/*  27:    */  
/*  28: 28 */  static { tables.add("admin_branch");
/*  29: 29 */    tables.add("admin_user");
/*  30: 30 */    tables.add("admin_group");
/*  31: 31 */    tables.add("admin_license");
/*  32: 32 */    tables.add("membership_ap_group");
/*  33: 33 */    tables.add("membership_branch_group");
/*  34:    */    
/*  35: 35 */    tables.add("portal_instance");
/*  36: 36 */    tables.add("portal_instance_page");
/*  37: 37 */    tables.add("portal_surfing_policy");
/*  38: 38 */    tables.add("portal_template");
/*  39: 39 */    tables.add("portal_wechat_response");
/*  40: 40 */    tables.add("portal_wechat_ip_policy");
/*  41:    */    
/*  42: 42 */    tables.add("cpe_host");
/*  43: 43 */    tables.add("cpe_host_property");
/*  44: 44 */    tables.add("cpe_ssid");
/*  45: 45 */    tables.add("cpe_schedule_time");
/*  46:    */    
/*  47: 47 */    tables.add("surfing_account");
/*  48: 48 */    tables.add("surfing_advertise");
/*  49: 49 */    tables.add("surfing_advertise_category");
/*  50: 50 */    tables.add("surfing_black_white");
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void run()
/*  54:    */  {
/*  55: 55 */    Connection conn = ConnectionManager.getConnection();
/*  56: 56 */    SoofacACS acs = (SoofacACS)SysConfigHelper.getBean("soofacACS");
/*  57: 57 */    String backupFile = BACK_PATH + getCurrentDateTime() + ".sql";
/*  58: 58 */    StringBuffer cmd = new StringBuffer(100);
/*  59: 59 */    cmd.append(getMySQLHome(conn)).append("/bin/");
/*  60: 60 */    cmd.append("mysqldump --add-drop-table ");
/*  61: 61 */    cmd.append(acs.getMysqlBackupCMD());
/*  62: 62 */    for (String tn : tables)
/*  63: 63 */      cmd.append(" ").append(tn);
/*  64: 64 */    Process child = null;
/*  65: 65 */    InputStream in = null;
/*  66: 66 */    InputStreamReader insr = null;
/*  67: 67 */    BufferedReader br = null;
/*  68: 68 */    FileOutputStream fout = null;
/*  69: 69 */    OutputStreamWriter writer = null;
/*  70:    */    try {
/*  71: 71 */      child = Runtime.getRuntime().exec(cmd.toString());
/*  72: 72 */      in = child.getInputStream();
/*  73: 73 */      insr = new InputStreamReader(in, "utf8");
/*  74: 74 */      StringBuffer sb = new StringBuffer(1000);
/*  75: 75 */      br = new BufferedReader(insr);
/*  76: 76 */      String inStr = null;
/*  77: 77 */      while ((inStr = br.readLine()) != null)
/*  78: 78 */        sb.append(inStr + "\r\n");
/*  79: 79 */      fout = new FileOutputStream(backupFile);
/*  80: 80 */      writer = new OutputStreamWriter(fout, "utf8");
/*  81: 81 */      writer.write(sb.toString());
/*  82: 82 */      writer.flush();
/*  83:    */    } catch (Exception e) {
/*  84: 84 */      e.printStackTrace();
/*  85:    */      
/*  86: 86 */      DBUtil.close(conn);
/*  87:    */      try {
/*  88: 88 */        child.destroy();
/*  89: 89 */        in.close();
/*  90: 90 */        insr.close();
/*  91: 91 */        br.close();
/*  92: 92 */        writer.close();
/*  93: 93 */        fout.close();
/*  94:    */      }
/*  95:    */      catch (Exception localException1) {}
/*  96:    */    }
/*  97:    */    finally
/*  98:    */    {
/*  99: 86 */      DBUtil.close(conn);
/* 100:    */      try {
/* 101: 88 */        child.destroy();
/* 102: 89 */        in.close();
/* 103: 90 */        insr.close();
/* 104: 91 */        br.close();
/* 105: 92 */        writer.close();
/* 106: 93 */        fout.close();
/* 107:    */      }
/* 108:    */      catch (Exception localException2) {}
/* 109:    */    }
/* 110:    */  }
/* 111:    */  
/* 112:    */  private String getMySQLHome(Connection conn) {
/* 113:100 */    String mysqlHome = null;
/* 114:101 */    Statement stat = null;
/* 115:102 */    ResultSet rs = null;
/* 116:    */    try {
/* 117:104 */      stat = conn.createStatement();
/* 118:105 */      rs = stat.executeQuery("show variables where Variable_name='basedir'");
/* 119:106 */      if (rs.next())
/* 120:107 */        mysqlHome = rs.getString("Value");
/* 121:    */    } catch (Exception e) {
/* 122:109 */      e.printStackTrace();
/* 123:    */    } finally {
/* 124:111 */      DBUtil.close(null, stat, rs);
/* 125:    */    }
/* 126:113 */    return mysqlHome;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public static String getCurrentDateTime() {
/* 130:117 */    return datetimeFormat.format(Calendar.getInstance().getTime());
/* 131:    */  }
/* 132:    */  
/* 133:    */  public int getDelay() {
/* 134:121 */    return 1;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public int getPeriod() {
/* 138:125 */    return 12;
/* 139:    */  }
/* 140:    */  
/* 141:    */  public TimeUnit getTimeUnit() {
/* 142:129 */    return TimeUnit.HOURS;
/* 143:    */  }
/* 144:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.util.BackupJob
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */