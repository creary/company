/*  1:   */package com.soofound.framework.util;
/*  2:   */
/*  3:   */import com.soofound.framework.license.License;
/*  4:   */import com.soofound.framework.license.LicenseTool;
/*  5:   */import java.util.HashMap;
/*  6:   */import java.util.Map;
/*  7:   */import javax.servlet.ServletContext;
/*  8:   */import org.jdom2.Document;
/*  9:   */import org.springframework.web.context.support.XmlWebApplicationContext;
/* 10:   */
/* 11:   */public class SysConfigHelper
/* 12:   */{
/* 13:   */  public static String CONTEXT_PATH;
/* 14:14 */  private static final Map<String, String> attributes = new HashMap();
/* 15:   */  
/* 16:   */  private static XmlWebApplicationContext appContext;
/* 17:   */  
/* 18:   */  public static License license;
/* 19:   */  public static ProjectConfig pcfg;
/* 20:   */  
/* 21:   */  public static void setApplicationContext(XmlWebApplicationContext appContext)
/* 22:   */  {
/* 23:23 */    appContext = appContext;
/* 24:24 */    attributes.put("tomcatInfo", appContext.getServletContext().getServerInfo());
/* 25:   */    
/* 26:26 */    if (appContext.getServletContext().getContextPath().length() > 1) {
/* 27:27 */      String contextName = appContext.getServletContext().getContextPath().substring(1);
/* 28:28 */      attributes.put("contextName", contextName);
/* 29:   */    } else {
/* 30:30 */      attributes.put("contextName", "");
/* 31:   */    }
/* 32:32 */    String webRoot = appContext.getServletContext().getRealPath("/");
/* 33:33 */    attributes.put("sysPath", webRoot);
/* 34:34 */    attributes.put("configPath", webRoot + "/WEB-INF/config/");
/* 35:35 */    CONTEXT_PATH = appContext.getServletContext().getContextPath() + "/";
/* 36:36 */    attributes.put("imagePath", appContext.getServletContext().getContextPath() + "/resources/image/default/icon/");
/* 37:   */    
/* 38:38 */    license = LicenseTool.parseLicense(webRoot + "/WEB-INF/config/license.xml");
/* 39:39 */    LicenseTool.checkLicense(license);
/* 40:40 */    pcfg = new ProjectConfig(webRoot);
/* 41:41 */    String bootXML = license.getBoot();
/* 42:42 */    if (bootXML != null) {
/* 43:43 */      org.jdom2.Element root = SimpleXMLUtil.file2Doc(webRoot + bootXML).getRootElement();
/* 44:44 */      if (root.getChildText("loginPage") != null)
/* 45:45 */        pcfg.setLoginPage(root.getChildText("loginPage"));
/* 46:46 */      if (root.getChildText("mainPage") != null)
/* 47:47 */        pcfg.setMainPage(root.getChildText("mainPage"));
/* 48:48 */      if (root.getChildText("sysMenu") != null)
/* 49:49 */        pcfg.setSysMenu(webRoot + root.getChildText("sysMenu"));
/* 50:50 */      if (root.getChildText("logo1") != null)
/* 51:51 */        pcfg.setLogo1(root.getChildText("logo1"));
/* 52:52 */      if (root.getChildText("logo2") != null)
/* 53:53 */        pcfg.setLogo2(root.getChildText("logo2"));
/* 54:54 */      if (root.getChildText("favicon") != null)
/* 55:55 */        pcfg.setFavicon(root.getChildText("favicon"));
/* 56:56 */      if (root.getChildText("vendor") != null)
/* 57:57 */        pcfg.setVendor(root.getChildText("vendor"));
/* 58:58 */      if (root.getChildText("product") != null)
/* 59:59 */        pcfg.setProduct(root.getChildText("product"));
/* 60:60 */      if (root.getChildText("copyRight") != null)
/* 61:61 */        pcfg.setCopyRight(root.getChildText("copyRight"));
/* 62:   */    }
/* 63:   */  }
/* 64:   */  
/* 65:   */  public static void setAttribute(String key, String value) {
/* 66:66 */    attributes.put(key, value);
/* 67:   */  }
/* 68:   */  
/* 69:   */  public static Object getBean(String name) {
/* 70:70 */    return appContext.getBean(name);
/* 71:   */  }
/* 72:   */  
/* 73:   */  public static String getAttribute(String key) {
/* 74:74 */    return (String)attributes.get(key);
/* 75:   */  }
/* 76:   */  
/* 77:   */  public static void setLicense(License lic) {
/* 78:78 */    license = lic;
/* 79:   */  }
/* 80:   */  
/* 81:   */  public static License getLicense() {
/* 82:82 */    return license;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public static ProjectConfig getProjectConfig() {
/* 86:86 */    return pcfg;
/* 87:   */  }
/* 88:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.util.SysConfigHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */