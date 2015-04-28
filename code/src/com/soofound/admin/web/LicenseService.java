/*   1:    */package com.soofound.admin.web;
/*   2:    */
/*   3:    */import com.soofound.cpe.util.SoofacACS;
/*   4:    */import com.soofound.framework.jdbc.Persistable;
/*   5:    */import com.soofound.framework.util.DateUtil;
/*   6:    */import com.soofound.framework.util.SysConfigHelper;
/*   7:    */import com.soofound.framework.web.BaseService;
/*   8:    */import java.io.PrintStream;
/*   9:    */import java.util.ArrayList;
/*  10:    */import java.util.HashMap;
/*  11:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  12:    */import org.springframework.stereotype.Service;
/*  13:    */import org.springframework.util.StringUtils;
/*  14:    */
/*  15:    */@Service
/*  16:    */public final class LicenseService extends BaseService<com.soofound.admin.bean.LicenseDao>
/*  17:    */{
/*  18:    */  @org.springframework.beans.factory.annotation.Autowired
/*  19:    */  private com.soofound.cpe.dao.HostDao hdao;
/*  20:    */  @org.springframework.beans.factory.annotation.Autowired
/*  21:    */  private SoofacACS acs;
/*  22:    */  @org.springframework.beans.factory.annotation.Autowired
/*  23:    */  @Qualifier("defaultJdbcTemplate")
/*  24:    */  private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
/*  25:    */  
/*  26:    */  public com.soofound.admin.bean.LicenseDto findByFlashID(String flashId)
/*  27:    */  {
/*  28: 28 */    return ((com.soofound.admin.bean.LicenseDao)this.dao).findByFlashID(flashId);
/*  29:    */  }
/*  30:    */  
/*  34:    */  public int save(Persistable dto)
/*  35:    */  {
/*  36: 36 */    com.soofound.admin.bean.LicenseDto ld = (com.soofound.admin.bean.LicenseDto)dto;
/*  37: 37 */    if (ld.getMac().indexOf(":") == -1)
/*  38: 38 */      ld.setMac(com.soofound.cpe.util.CpeUtils.convertMac(ld.getMac()));
/*  39: 39 */    if (ld.getFlashId().equals("ros")) {
/*  40: 40 */      ld.setApkey("");
/*  41:    */      
/*  42: 42 */      com.soofound.cpe.bean.HostBean host = new com.soofound.cpe.bean.HostBean();
/*  43: 43 */      host.setId(this.acs.getHostID());
/*  44: 44 */      host.setSerialNumber(ld.getMac());
/*  45: 45 */      host.setBranchId("0");
/*  46: 46 */      host.setIpAddress("");
/*  47: 47 */      host.setChannel("");
/*  48: 48 */      host.setTrace("");
/*  49: 49 */      host.setOnline(1);
/*  50: 50 */      host.setUpTime(DateUtil.getCurrentDateTime());
/*  51: 51 */      host.setLastTime(DateUtil.getCurrentDateTime());
/*  52: 52 */      host.setHardwareVersion("MikroTik");
/*  53: 53 */      host.setProductClass("MikroTik");
/*  54: 54 */      host.setMode("MikroTik");
/*  55: 55 */      host.setSoftwareVersion("RouterOS");
/*  56: 56 */      host.setSsid("MikroTik");
/*  57: 57 */      host.setName("ROS-AP");
/*  58: 58 */      host.setStun("ros");
/*  59:    */      
/*  60: 60 */      this.hdao.save(host);
/*  61: 61 */      StringBuilder sqlText = new StringBuilder(200);
/*  62: 62 */      sqlText.append("insert into cpe_ssid(ap_id,ife,policy_id,portal_id,enable,name)values(").append(host.getId());
/*  63: 63 */      sqlText.append(",0,0,0,1,'").append(host.getSsid()).append("')");
/*  64: 64 */      this.jdbcTemplate.update(sqlText.toString());
/*  65:    */    } else {
/*  66: 66 */      ld.setApkey(com.soofound.cpe.util.CpeUtils.generateAPKey(ld.getMac(), getFlashIDFromIP(ld.getFlashId()))); }
/*  67: 67 */    return ((com.soofound.admin.bean.LicenseDao)this.dao).save(ld);
/*  68:    */  }
/*  69:    */  
/*  70:    */  private String getFlashIDFromIP(String ipAddress)
/*  71:    */  {
/*  72: 72 */    StringBuilder ipstr = new StringBuilder(32);
/*  73: 73 */    ipstr.append(ipAddress.replace(".", "a"));
/*  74: 74 */    for (int i = ipstr.length(); i < 16; i++) {
/*  75: 75 */      ipstr.append("0");
/*  76:    */    }
/*  77: 77 */    return ipstr.toString();
/*  78:    */  }
/*  79:    */  
/*  80:    */  public java.util.Map<String, Object> batchAdd(String content) {
/*  81: 81 */    java.util.Map<String, Object> result = new HashMap();
/*  82: 82 */    java.util.List<String> sqls = new ArrayList();
/*  83:    */    try {
/*  84: 84 */      String[] macs = content.split("\n");
/*  85: 85 */      for (String mac : macs) {
/*  86: 86 */        String[] key = mac.split("#");
/*  87: 87 */        key[0] = com.soofound.cpe.util.CpeUtils.convertMac(key[0]).toUpperCase();
/*  88: 88 */        com.soofound.admin.bean.LicenseDto dto = ((com.soofound.admin.bean.LicenseDao)this.dao).findByKey(key[0], key[1]);
/*  89: 89 */        if (dto != null) {
/*  90: 90 */          result.put("status", Integer.valueOf(2));
/*  91: 91 */          result.put("msg", "mac或flashId已经存在:" + mac);
/*  92: 92 */          return result;
/*  93:    */        }
/*  94: 94 */        StringBuilder sqlText = new StringBuilder(200);
/*  95: 95 */        sqlText.append("insert into admin_license(mac,flash_id,apkey,branch_id,log_time)values('").append(key[0]).append("','");
/*  96: 96 */        sqlText.append(key[1]).append("','").append(com.soofound.cpe.util.CpeUtils.generateAPKey(key[0], key[1])).append("','0',now())");
/*  97: 97 */        sqls.add(sqlText.toString());
/*  98:    */      }
/*  99: 99 */      String[] arrsqls = new String[sqls.size()];
/* 100:100 */      sqls.toArray(arrsqls);
/* 101:101 */      this.jdbcTemplate.batchUpdate(arrsqls);
/* 102:    */      
/* 103:103 */      result.put("status", Integer.valueOf(1));
/* 104:104 */      result.put("msg", "成功增加" + sqls.size() + "个序列号");
/* 105:105 */      return result;
/* 106:    */    } catch (Exception ex) {
/* 107:107 */      ex.printStackTrace();
/* 108:108 */      result.put("status", Integer.valueOf(2));
/* 109:109 */      result.put("msg", ex.getMessage()); }
/* 110:110 */    return result;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public String checkMac(String mac, String flashId, String realm)
/* 114:    */  {
/* 115:115 */    StringBuilder sqlText = new StringBuilder(100);
/* 116:116 */    sqlText.append("select mac from view_admin_license where mac='").append(mac).append("'");
/* 117:117 */    if (StringUtils.hasText(flashId))
/* 118:118 */      sqlText.append(" and flash_id='").append(flashId).append("'");
/* 119:119 */    org.springframework.jdbc.core.JdbcTemplate jdbc = null;
/* 120:120 */    if (realm.indexOf("wifiBeijing") > 0) {
/* 121:121 */      jdbc = (org.springframework.jdbc.core.JdbcTemplate)SysConfigHelper.getBean("wifiBeijingJdbcTemplate");
/* 122:122 */    } else if (realm.indexOf("supoin") > 0) {
/* 123:123 */      jdbc = (org.springframework.jdbc.core.JdbcTemplate)SysConfigHelper.getBean("supoinJdbcTemplate");
/* 124:    */    } else
/* 125:125 */      jdbc = this.jdbcTemplate;
/* 126:126 */    String result = null;
/* 127:    */    try {
/* 128:128 */      jdbc.queryForObject(sqlText.toString(), String.class);
/* 129:129 */      result = "mac=1";
/* 130:    */    } catch (Exception ex) {
/* 131:131 */      result = "mac=0";
/* 132:132 */      System.out.println("error#" + sqlText.toString());
/* 133:    */    }
/* 134:134 */    return result;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public com.soofound.admin.bean.LicenseDto findByKey(String apkey) {
/* 138:138 */    return ((com.soofound.admin.bean.LicenseDao)this.dao).findByKey(apkey);
/* 139:    */  }
/* 140:    */  
/* 147:    */  @org.springframework.beans.factory.annotation.Autowired
/* 148:    */  protected void setDao(com.soofound.admin.bean.LicenseDao dao)
/* 149:    */  {
/* 150:150 */    this.dao = dao;
/* 151:    */  }
/* 152:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.LicenseService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */