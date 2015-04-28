/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="admin_log")
/*  6:   */public final class LogDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private String id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="username")
/* 11:   */  private String username;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="operation")
/* 13:   */  private String operation;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ip")
/* 15:   */  private String ip;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="location")
/* 17:   */  private String location;
/* 18:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="log_time")
/* 19:   */  private String logTime;
/* 20:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="fullname")
/* 21:   */  private String fullname;
/* 22:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/* 23:   */  private String branch;
/* 24:   */  
/* 25:   */  public String getLogTime() {
/* 26:26 */    return this.logTime;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public void setLogTime(String logTime) {
/* 30:30 */    this.logTime = logTime;
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
/* 41:   */  public String getOperation() {
/* 42:42 */    return this.operation;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public void setOperation(String operation) {
/* 46:46 */    this.operation = operation;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public String getIp() {
/* 50:50 */    return this.ip;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void setIp(String ip) {
/* 54:54 */    this.ip = ip;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public String getUsername() {
/* 58:58 */    return this.username;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public void setUsername(String username) {
/* 62:62 */    this.username = username;
/* 63:   */  }
/* 64:   */  
/* 65:   */  public String getFullname() {
/* 66:66 */    return this.fullname;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public void setFullname(String fullname) {
/* 70:70 */    this.fullname = fullname;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public String getLocation() {
/* 74:74 */    return this.location;
/* 75:   */  }
/* 76:   */  
/* 77:   */  public void setLocation(String location) {
/* 78:78 */    this.location = location;
/* 79:   */  }
/* 80:   */  
/* 81:   */  public String getBranch() {
/* 82:82 */    return this.branch;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public void setBranch(String branch) {
/* 86:86 */    this.branch = branch;
/* 87:   */  }
/* 88:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.LogDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */