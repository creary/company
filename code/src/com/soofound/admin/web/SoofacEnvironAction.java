/*   1:    */package com.soofound.admin.web;
/*   2:    */
/*   3:    */import com.soofound.framework.license.License;
/*   4:    */import com.soofound.framework.license.LicenseTool;
/*   5:    */import com.soofound.framework.util.CommonUtil;
/*   6:    */import com.soofound.framework.util.DateUtil;
/*   7:    */import com.soofound.framework.util.MySQLDump;
/*   8:    */import com.soofound.framework.util.SysConfigHelper;
/*   9:    */import com.soofound.framework.web.GenericAction;
/*  10:    */import java.io.FileInputStream;
/*  11:    */import java.util.HashMap;
/*  12:    */import java.util.Map;
/*  13:    */import javax.servlet.http.HttpServletRequest;
/*  14:    */import org.springframework.stereotype.Controller;
/*  15:    */import org.springframework.ui.ModelMap;
/*  16:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  17:    */import org.springframework.web.bind.annotation.RequestParam;
/*  18:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  19:    */import org.springframework.web.multipart.MultipartFile;
/*  20:    */
/*  21:    */@Controller
/*  22:    */public final class SoofacEnvironAction
/*  23:    */  extends GenericAction
/*  24:    */{
/*  25:    */  @RequestMapping({"/admin/getAbout.do"})
/*  26:    */  public String getAbout(HttpServletRequest request, ModelMap model)
/*  27:    */  {
/*  28: 28 */    model.addAttribute("lic", SysConfigHelper.getLicense());
/*  29: 29 */    model.addAttribute("machineID", LicenseTool.getMachineID());
/*  30: 30 */    model.addAttribute("tomcatInfo", SysConfigHelper.getAttribute("tomcatInfo"));
/*  31: 31 */    model.addAttribute("tomcatPath", System.getProperty("java.io.tmpdir").replace("temp", ""));
/*  32: 32 */    model.addAttribute("jdkVersion", System.getProperty("java.runtime.version"));
/*  33: 33 */    model.addAttribute("tomcatOS", System.getProperty("os.name"));
/*  34:    */    
/*  35: 35 */    MySQLDump mysqld = new MySQLDump();
/*  36: 36 */    Map<String, String> mysqlInfos = mysqld.getMySQLInfos();
/*  37: 37 */    for (String key : mysqlInfos.keySet()) {
/*  38: 38 */      model.addAttribute(key, mysqlInfos.get(key));
/*  39:    */    }
/*  40: 40 */    return "/admin/help/about.jsp";
/*  41:    */  }
/*  42:    */  
/*  43:    */  @RequestMapping({"/admin/checkLicense.do"})
/*  44:    */  @ResponseBody
/*  45: 45 */  public Map<String, Object> checkLicense() { License lic = SysConfigHelper.getLicense();
/*  46: 46 */    Map<String, Object> result = new HashMap();
/*  47: 47 */    result.put("status", Integer.valueOf(1));
/*  48:    */    
/*  49: 49 */    Map<String, String> licmap = new HashMap();
/*  50: 50 */    licmap.put("type", lic.getLicenseType());
/*  51: 51 */    if (lic.getLicenseType().equals("Trial")) {
/*  52: 52 */      licmap.put("descr", "试用版");
/*  53: 53 */      int diffDay = DateUtil.getDiffDays(DateUtil.getCurrentDateTime(), lic.getExpiryDate() + " 00:00:00");
/*  54: 54 */      licmap.put("avail", diffDay);
/*  55: 55 */    } else if (lic.getLicenseType().equals("Registered")) {
/*  56: 56 */      licmap.put("descr", "正式版");
/*  57: 57 */      licmap.put("avail", lic.getDeviceNumber());
/*  58:    */    }
/*  59: 59 */    int total = 100;
/*  60: 60 */    if (lic.getDeviceNumber() > total)
/*  61: 61 */      total = lic.getDeviceNumber();
/*  62: 62 */    licmap.put("total", total);
/*  63: 63 */    result.put("data", licmap);
/*  64: 64 */    return result;
/*  65:    */  }
/*  66:    */  
/*  67:    */  @RequestMapping({"/store/getMachineID.do"})
/*  68:    */  @ResponseBody
/*  69: 69 */  public Map<String, Object> getMachineID() { Map<String, Object> result = new HashMap();
/*  70: 70 */    result.put("status", Integer.valueOf(1));
/*  71: 71 */    result.put("data", LicenseTool.getMachineID());
/*  72: 72 */    return result;
/*  73:    */  }
/*  74:    */  
/*  75:    */  @RequestMapping({"/admin/readyHelp.do"})
/*  76:    */  public String readyHelp(HttpServletRequest request, ModelMap model) {
/*  77: 77 */    model.addAttribute("lic", SysConfigHelper.getLicense());
/*  78: 78 */    model.addAttribute("machineID", LicenseTool.getMachineID());
/*  79: 79 */    model.addAttribute("pcfg", SysConfigHelper.getProjectConfig());
/*  80: 80 */    return "/admin/help/helpCenter.jsp";
/*  81:    */  }
/*  82:    */  
/*  85:    */  @RequestMapping({"/admin/updateLicense.do"})
/*  86:    */  public String updateLicense(@RequestParam("xml") MultipartFile file, HttpServletRequest request, ModelMap model)
/*  87:    */  {
/*  88: 88 */    String tempFile = SysConfigHelper.getAttribute("configPath") + "license_new.xml";
/*  89: 89 */    String message = null;
/*  90:    */    try {
/*  91: 91 */      CommonUtil.copyFile(file.getInputStream(), tempFile);
/*  92:    */    } catch (Exception e) {
/*  93: 93 */      message = "上传文件失败.";
/*  94:    */    }
/*  95: 95 */    License tempLicense = null;
/*  96: 96 */    if (message == null) {
/*  97:    */      try {
/*  98: 98 */        tempLicense = LicenseTool.parseLicense(tempFile);
/*  99:    */      } catch (Exception e) {
/* 100:100 */        tempLicense = null;
/* 101:    */      }
/* 102:102 */      if (tempLicense == null)
/* 103:103 */        message = "更新失败:无效的License.";
/* 104:    */    }
/* 105:105 */    if (message == null) {
/* 106:106 */      String oldFile = SysConfigHelper.getAttribute("configPath") + "license.xml";
/* 107:    */      try {
/* 108:108 */        FileInputStream fis = new FileInputStream(tempFile);
/* 109:109 */        CommonUtil.copyFile(fis, oldFile);
/* 110:110 */        SysConfigHelper.setLicense(tempLicense);
/* 111:    */      } catch (Exception e) {
/* 112:112 */        message = "更新失败.";
/* 113:    */      }
/* 114:    */    }
/* 115:115 */    if (message == null) {
/* 116:116 */      message = "更新成功.";
/* 117:117 */      logOperation(request, "更新License");
/* 118:    */    }
/* 119:119 */    request.setAttribute("message", message);
/* 120:120 */    return "/admin/help/soofacLicense.jsp";
/* 121:    */  }
/* 122:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.SoofacEnvironAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */