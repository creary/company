/*  1:   */package com.soofound.cpe.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="cpe_device_log")
/*  6:   */public final class DeviceLogBean implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private String id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="host_id")
/* 11:   */  private int hostId;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="message")
/* 13:   */  private String message;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="log_time")
/* 15:   */  private String logTime;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="operator")
/* 17:   */  private String operator;
/* 18:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name", join=true)
/* 19:   */  private String name;
/* 20:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="serial_number", join=true)
/* 21:   */  private String serialNumber;
/* 22:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/* 23:   */  private String branch;
/* 24:   */  
/* 25:   */  public String getName() {
/* 26:26 */    return this.name;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public void setName(String name) {
/* 30:30 */    this.name = name;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public String getId() {
/* 34:34 */    return this.id;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public void setId(String id) {
/* 38:38 */    this.id = id;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public int getHostId() {
/* 42:42 */    return this.hostId;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public void setHostId(int hostId) {
/* 46:46 */    this.hostId = hostId;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public String getMessage() {
/* 50:50 */    return this.message;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void setMessage(String message) {
/* 54:54 */    this.message = message;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public String getLogTime() {
/* 58:58 */    return this.logTime;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public void setLogTime(String logTime) {
/* 62:62 */    this.logTime = logTime;
/* 63:   */  }
/* 64:   */  
/* 65:   */  public String getBranch() {
/* 66:66 */    return this.branch;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public void setBranch(String branch) {
/* 70:70 */    this.branch = branch;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public String getOperator() {
/* 74:74 */    return this.operator;
/* 75:   */  }
/* 76:   */  
/* 77:   */  public void setOperator(String operator) {
/* 78:78 */    this.operator = operator;
/* 79:   */  }
/* 80:   */  
/* 81:   */  public String getSerialNumber() {
/* 82:82 */    return this.serialNumber;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public void setSerialNumber(String serialNumber) {
/* 86:86 */    this.serialNumber = serialNumber;
/* 87:   */  }
/* 88:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.bean.DeviceLogBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */