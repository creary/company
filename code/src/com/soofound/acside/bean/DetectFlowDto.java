/*  1:   */package com.soofound.acside.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="detect_flow_")
/*  6:   */public final class DetectFlowDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ap_mac")
/*  9:   */  private String apmac;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="alias")
/* 11:   */  private String alias;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="mac")
/* 13:   */  private String mac;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="snr")
/* 15:   */  private int snr;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="log_time")
/* 17:   */  private String logTime;
/* 18:   */  private String brand;
/* 19:   */  private String stayLong;
/* 20:   */  
/* 21:   */  public String getApmac()
/* 22:   */  {
/* 23:23 */    return this.apmac;
/* 24:   */  }
/* 25:   */  
/* 26:   */  public void setApmac(String apmac) {
/* 27:27 */    this.apmac = apmac;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public String getMac() {
/* 31:31 */    return this.mac;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public void setMac(String mac) {
/* 35:35 */    this.mac = mac;
/* 36:   */  }
/* 37:   */  
/* 38:   */  public int getSnr() {
/* 39:39 */    return this.snr;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public void setSnr(int snr) {
/* 43:43 */    this.snr = snr;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public String getLogTime() {
/* 47:47 */    return this.logTime;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public void setLogTime(String logTime) {
/* 51:51 */    this.logTime = logTime;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public String getAlias() {
/* 55:55 */    return this.alias;
/* 56:   */  }
/* 57:   */  
/* 58:   */  public void setAlias(String alias) {
/* 59:59 */    this.alias = alias;
/* 60:   */  }
/* 61:   */  
/* 62:   */  public String getBrand() {
/* 63:63 */    return this.brand;
/* 64:   */  }
/* 65:   */  
/* 66:   */  public void setBrand(String brand) {
/* 67:67 */    this.brand = brand;
/* 68:   */  }
/* 69:   */  
/* 70:   */  public String getStayLong() {
/* 71:71 */    return this.stayLong;
/* 72:   */  }
/* 73:   */  
/* 74:   */  public void setStayLong(String stayLong) {
/* 75:75 */    this.stayLong = stayLong;
/* 76:   */  }
/* 77:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.bean.DetectFlowDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */