/*  1:   */package com.soofound.framework.util;
/*  2:   */
/*  3:   */public class ProjectConfig
/*  4:   */{
/*  5:   */  private String product;
/*  6:   */  private String logo1;
/*  7:   */  private String logo2;
/*  8:   */  private String favicon;
/*  9:   */  private String vendor;
/* 10:   */  private String loginPage;
/* 11:   */  private String mainPage;
/* 12:   */  private String sysMenu;
/* 13:   */  private String copyRight;
/* 14:   */  
/* 15:   */  public ProjectConfig() {}
/* 16:   */  
/* 17:   */  public ProjectConfig(String root) {
/* 18:18 */    this.product = "速方WIFI营销平台";
/* 19:19 */    this.favicon = "/resources/image/default/mix/favicon_soofound.ico";
/* 20:20 */    this.logo1 = "/resources/image/default/login/logo.png";
/* 21:21 */    this.logo2 = "/resources/image/default/mui/logo_soofac.png";
/* 22:22 */    this.vendor = "广州速方软件有限公司";
/* 23:23 */    this.loginPage = "/login.jsp";
/* 24:24 */    this.mainPage = "/common/main.jsp";
/* 25:25 */    this.sysMenu = (root + "/WEB-INF/config/sysmenu.xml");
/* 26:26 */    this.copyRight = "© 2012－2014 速方软件 版权所有 粤ICP备13037676号";
/* 27:   */  }
/* 28:   */  
/* 29:   */  public String getLoginPage() {
/* 30:30 */    return this.loginPage;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public void setLoginPage(String loginPage) {
/* 34:34 */    this.loginPage = loginPage;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public String getMainPage() {
/* 38:38 */    return this.mainPage;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public void setMainPage(String mainPage) {
/* 42:42 */    this.mainPage = mainPage;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public String getSysMenu() {
/* 46:46 */    return this.sysMenu;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void setSysMenu(String sysMenu) {
/* 50:50 */    this.sysMenu = sysMenu;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public String getProduct() {
/* 54:54 */    return this.product;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void setProduct(String product) {
/* 58:58 */    this.product = product;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public String getLogo1() {
/* 62:62 */    return this.logo1;
/* 63:   */  }
/* 64:   */  
/* 65:   */  public void setLogo1(String logo1) {
/* 66:66 */    this.logo1 = logo1;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public String getLogo2() {
/* 70:70 */    return this.logo2;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public void setLogo2(String logo2) {
/* 74:74 */    this.logo2 = logo2;
/* 75:   */  }
/* 76:   */  
/* 77:   */  public String getVendor() {
/* 78:78 */    return this.vendor;
/* 79:   */  }
/* 80:   */  
/* 81:   */  public void setVendor(String vendor) {
/* 82:82 */    this.vendor = vendor;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public String getCopyRight() {
/* 86:86 */    return this.copyRight;
/* 87:   */  }
/* 88:   */  
/* 89:   */  public void setCopyRight(String copyRight) {
/* 90:90 */    this.copyRight = copyRight;
/* 91:   */  }
/* 92:   */  
/* 93:   */  public String getFavicon() {
/* 94:94 */    return this.favicon;
/* 95:   */  }
/* 96:   */  
/* 97:   */  public void setFavicon(String favicon) {
/* 98:98 */    this.favicon = favicon;
/* 99:   */  }
/* 100:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.util.ProjectConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */