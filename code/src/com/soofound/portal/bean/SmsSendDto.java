/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="surfing_sms")
/*  6:   */public final class SmsSendDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private String id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="mobile")
/* 11:   */  private String mobile;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/* 13:   */  private String branchId;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="content")
/* 15:   */  private String content;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="log_time")
/* 17:   */  private String logTime;
/* 18:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/* 19:   */  private String branch;
/* 20:   */  
/* 21:   */  public String getId() {
/* 22:22 */    return this.id;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void setId(String id) {
/* 26:26 */    this.id = id;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public String getMobile() {
/* 30:30 */    return this.mobile;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public void setMobile(String mobile) {
/* 34:34 */    this.mobile = mobile;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public String getBranchId() {
/* 38:38 */    return this.branchId;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public void setBranchId(String branchId) {
/* 42:42 */    this.branchId = branchId;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public String getContent() {
/* 46:46 */    return this.content;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void setContent(String content) {
/* 50:50 */    this.content = content;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public String getLogTime() {
/* 54:54 */    return this.logTime;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void setLogTime(String logTime) {
/* 58:58 */    this.logTime = logTime;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public String getBranch() {
/* 62:62 */    return this.branch;
/* 63:   */  }
/* 64:   */  
/* 65:   */  public void setBranch(String branch) {
/* 66:66 */    this.branch = branch;
/* 67:   */  }
/* 68:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.SmsSendDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */