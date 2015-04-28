/*  1:   */package com.soofound.framework.util;
/*  2:   */
/*  3:   */import org.apache.log4j.Logger;
/*  4:   */import org.springframework.beans.BeansException;
/*  5:   */import org.springframework.context.ApplicationContextAware;
/*  6:   */
/*  7:   */public class SpringContextAware implements ApplicationContextAware
/*  8:   */{
/*  9:   */  private String appName;
/* 10:   */  private String logIPLocation;
/* 11:   */  
/* 12:   */  public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException
/* 13:   */  {
/* 14:14 */    org.springframework.web.context.support.XmlWebApplicationContext appContext = (org.springframework.web.context.support.XmlWebApplicationContext)applicationContext;
/* 15:15 */    SysConfigHelper.setApplicationContext(appContext);
/* 16:16 */    SysConfigHelper.setAttribute("appName", this.appName);
/* 17:17 */    SysConfigHelper.setAttribute("logIPLocation", this.logIPLocation);
/* 18:18 */    Logger.getLogger("ContextAware").info("====" + this.appName + " Starting===");
/* 19:   */  }
/* 20:   */  
/* 21:   */  public void setAppName(String appName) {
/* 22:22 */    this.appName = appName;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void setLogIPLocation(String logIPLocation) {
/* 26:26 */    this.logIPLocation = logIPLocation;
/* 27:   */  }
/* 28:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.util.SpringContextAware
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */