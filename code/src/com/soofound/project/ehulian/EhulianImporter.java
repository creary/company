/*   1:    */package com.soofound.project.ehulian;
/*   2:    */
/*   3:    */import com.soofound.framework.util.CommonUtil;
/*   4:    */import com.soofound.framework.util.SysConfigHelper;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.sql.ResultSet;
/*   7:    */import java.sql.SQLException;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.HashMap;
/*  10:    */import java.util.List;
/*  11:    */import org.springframework.dao.DataAccessException;
/*  12:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  13:    */import org.springframework.jdbc.core.ResultSetExtractor;
/*  14:    */
/*  15:    */public class EhulianImporter
/*  16:    */{
/*  17:    */  public void rename()
/*  18:    */  {
/*  19: 19 */    JdbcTemplate jdbc1 = (JdbcTemplate)SysConfigHelper.getBean("jdbc1");
/*  20: 20 */    JdbcTemplate jdbc2 = (JdbcTemplate)SysConfigHelper.getBean("jdbc2");
/*  21: 21 */    JdbcTemplate jdbc3 = (JdbcTemplate)SysConfigHelper.getBean("jdbc3");
/*  22: 22 */    java.util.Map<String, String> hosts1 = doImportForRename(jdbc1);
/*  23: 23 */    java.util.Map<String, String> hosts2 = doImportForRename(jdbc2);
/*  24: 24 */    List<String> sqls = new ArrayList();
/*  25: 25 */    for (String key : hosts1.keySet()) {
/*  26: 26 */      if (!key.equals(hosts1.get(key))) {
/*  27: 27 */        String sql = "update cpe_host set name='" + (String)hosts1.get(key) + "' where serial_number='" + key + "'";
/*  28: 28 */        sqls.add(sql);
/*  29:    */      }
/*  30:    */    }
/*  31: 31 */    for (String key : hosts2.keySet()) {
/*  32: 32 */      if (!key.equals(hosts2.get(key))) {
/*  33: 33 */        String sql = "update cpe_host set name='" + (String)hosts2.get(key) + "' where serial_number='" + key + "'";
/*  34: 34 */        sqls.add(sql);
/*  35:    */      }
/*  36:    */    }
/*  37: 37 */    jdbc3.batchUpdate(CommonUtil.toArray(sqls));
/*  38:    */  }
/*  39:    */  
/*  40:    */  private java.util.Map<String, String> doImportForRename(JdbcTemplate jdbc) {
/*  41: 41 */    String sql = "select name,serial_number from cpe_host order by id";
/*  42: 42 */    java.util.Map<String, String> hosts = (java.util.Map)jdbc.query(sql, new ResultSetExtractor() {
/*  43:    */      public java.util.Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/*  44: 44 */        java.util.Map<String, String> hosts = new HashMap();
/*  45: 45 */        while (rs.next()) {
/*  46: 46 */          if (!rs.getString("serial_number").equals(rs.getString("name")))
/*  47: 47 */            hosts.put(rs.getString("serial_number"), rs.getString("name"));
/*  48:    */        }
/*  49: 49 */        return hosts;
/*  50: 50 */      } } );
/*  51: 51 */    return hosts;
/*  52:    */  }
/*  53:    */  
/*  54:    */  public void doGrouped() {
/*  55: 55 */    JdbcTemplate jdbc1 = (JdbcTemplate)SysConfigHelper.getBean("jdbc1");
/*  56: 56 */    String sql = "select * from cpe_host order by id";
/*  57: 57 */    final java.util.Map<String, String> hosts1 = (java.util.Map)jdbc1.query(sql, new ResultSetExtractor() {
/*  58:    */      public java.util.Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/*  59: 59 */        java.util.Map<String, String> hosts = new HashMap();
/*  60: 60 */        while (rs.next()) {
/*  61: 61 */          if (rs.getString("name").indexOf("吉林市三医院") != -1) {
/*  62: 62 */            hosts.put(rs.getString("id"), rs.getString("serial_number"));
/*  63:    */          }
/*  64:    */        }
/*  65: 65 */        return hosts;
/*  66: 66 */      } } );
/*  67: 67 */    JdbcTemplate jdbc2 = (JdbcTemplate)SysConfigHelper.getBean("jdbc2");
/*  68: 68 */    final java.util.Map<String, String> hosts2 = (java.util.Map)jdbc2.query(sql, new ResultSetExtractor() {
/*  69:    */      public java.util.Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/*  70: 70 */        java.util.Map<String, String> hosts = new HashMap();
/*  71: 71 */        while (rs.next())
/*  72: 72 */          hosts.put(rs.getString("serial_number"), rs.getString("id"));
/*  73: 73 */        return hosts;
/*  74:    */      }
/*  75: 75 */    });
/*  76: 76 */    String sql2 = "select group_id,ap_id from membership_ap_group order by group_id";
/*  77: 77 */    String insertSql = "insert into membership_ap_group(group_id,ap_id)values('${groupId}','${apId}');";
/*  78:    */    
/*  79: 79 */    final List<String> sqls = new ArrayList();
/*  80: 80 */    java.util.Map<String, String> groups = (java.util.Map)jdbc1.query(sql2, new ResultSetExtractor() {
/*  81:    */      public java.util.Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/*  82: 82 */        java.util.Map<String, String> hosts = new HashMap();
/*  83: 83 */        while (rs.next()) {
/*  84: 84 */          String id = (String)hosts2.get(hosts1.get(rs.getString("ap_id")));
/*  85: 85 */          if (id != null)
/*  86:    */          {
/*  89: 89 */            String sql = "insert into membership_ap_group(group_id,ap_id)values('${groupId}','${apId}');".replace("${groupId}", rs.getString("group_id")).replace("${apId}", id);
/*  90: 90 */            System.out.println(sql);
/*  91: 91 */            sqls.add(sql);
/*  92:    */          } }
/*  93: 93 */        return hosts;
/*  94:    */      }
/*  95:    */    });
/*  96:    */  }
/*  97:    */  
/*  98:    */  public void doCompare() {
/*  99: 99 */    JdbcTemplate jdbc1 = (JdbcTemplate)SysConfigHelper.getBean("jdbc1");
/* 100:100 */    String sql = "select id,serial_number from cpe_host where branch_id='00001' order by id";
/* 101:101 */    List<String> hosts1 = (List)jdbc1.query(sql, new ResultSetExtractor() {
/* 102:    */      public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 103:103 */        List<String> hosts = new ArrayList();
/* 104:104 */        while (rs.next()) {
/* 105:105 */          String snum = rs.getString("serial_number");
/* 106:106 */          hosts.add(snum);
/* 107:    */        }
/* 108:108 */        return hosts;
/* 109:109 */      } } );
/* 110:110 */    System.out.println(hosts1.size());
/* 111:111 */    JdbcTemplate jdbc3 = (JdbcTemplate)SysConfigHelper.getBean("jdbc3");
/* 112:112 */    List<String> hosts2 = (List)jdbc3.query(sql, new ResultSetExtractor() {
/* 113:    */      public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 114:114 */        List<String> hosts = new ArrayList();
/* 115:115 */        while (rs.next()) {
/* 116:116 */          String snum = rs.getString("serial_number");
/* 117:117 */          hosts.add(snum);
/* 118:    */        }
/* 119:119 */        return hosts;
/* 120:120 */      } } );
/* 121:121 */    System.out.println(hosts2.size());
/* 122:122 */    for (String mac1 : hosts1) {
/* 123:123 */      if (!hosts2.contains(mac1))
/* 124:124 */        System.out.println(mac1);
/* 125:    */    }
/* 126:    */  }
/* 127:    */  
/* 128:    */  public void reBranch() {
/* 129:129 */    String ipAddress = "111.26.201.14";
/* 130:130 */    String branchId = "00001";
/* 131:131 */    String sql = "select * from cpe_host where stun like '" + ipAddress + "-%' order by id";
/* 132:132 */    JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 133:133 */    List<String> sns = (List)jdbc.query(sql, new ResultSetExtractor() {
/* 134:    */      public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 135:135 */        List<String> sns = new ArrayList();
/* 136:136 */        while (rs.next())
/* 137:137 */          sns.add(rs.getString("serial_number"));
/* 138:138 */        return sns;
/* 139:139 */      } } );
/* 140:140 */    List<String> sqls = new ArrayList();
/* 141:141 */    for (String sn : sns) {
/* 142:142 */      String sql1 = "update cpe_host set branch_id='" + branchId + "' where serial_number='" + sn + "'";
/* 143:143 */      String sql2 = "update admin_license set branch_id='" + branchId + "' where mac='" + sn + "'";
/* 144:144 */      sqls.add(sql1);
/* 145:145 */      sqls.add(sql2);
/* 146:    */    }
/* 147:147 */    int[] rows = jdbc.batchUpdate(CommonUtil.toArray(sqls));
/* 148:148 */    System.out.println(rows.length);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public void reid() {
/* 152:    */    try {
/* 153:153 */      JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("jdbc3");
/* 154:154 */      java.util.Map<String, String> maps = (java.util.Map)jdbc.query("select * from cpe_host", new ResultSetExtractor() {
/* 155:    */        public java.util.Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 156:156 */          java.util.Map<String, String> sns = new HashMap();
/* 157:157 */          while (rs.next())
/* 158:158 */            sns.put(rs.getString("serial_number"), rs.getString("id"));
/* 159:159 */          return sns;
/* 160:160 */        } } );
/* 161:161 */      int id = 800;
/* 162:162 */      for (String sn : maps.keySet()) {
/* 163:163 */        String sql1 = "update cpe_host set id=" + id + " where serial_number='" + sn + "'";
/* 164:164 */        String sql2 = "update cpe_host_property set host_id=" + id + " where host_id=" + (String)maps.get(sn);
/* 165:165 */        String sql3 = "update cpe_ssid set ap_id=" + id + " where ap_id=" + (String)maps.get(sn);
/* 166:166 */        jdbc.batchUpdate(new String[] { sql1, sql2, sql3 });
/* 167:167 */        id++;
/* 168:    */      }
/* 169:    */    } catch (Exception e) {
/* 170:170 */      e.printStackTrace();
/* 171:    */    }
/* 172:    */  }
/* 173:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.project.ehulian.EhulianImporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */