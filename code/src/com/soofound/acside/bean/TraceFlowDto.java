/*  1:   */package com.soofound.acside.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="trace_flow_")
/*  6:   */public final class TraceFlowDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ip")
/*  9:   */  private String ip;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="mac")
/* 11:   */  private String mac;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="url")
/* 13:   */  private String url;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="log_time")
/* 15:   */  private String logTime;
/* 16:   */  
/* 17:   */  public String getIp() {
/* 18:18 */    return this.ip;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public void setIp(String ip) {
/* 22:22 */    this.ip = ip;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public String getMac() {
/* 26:26 */    return this.mac;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public void setMac(String mac) {
/* 30:30 */    this.mac = mac;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public String getUrl() {
/* 34:34 */    return this.url;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public void setUrl(String url) {
/* 38:38 */    this.url = url;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public String getLogTime() {
/* 42:42 */    return this.logTime;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public void setLogTime(String logTime) {
/* 46:46 */    this.logTime = logTime;
/* 47:   */  }
/* 48:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.bean.TraceFlowDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */