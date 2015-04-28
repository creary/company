/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="admin_license")
/*  6:   */public final class LicenseDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="mac", primaryKey=true)
/*  9:   */  private String mac;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="flash_id")
/* 11:   */  private String flashId;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="apkey")
/* 13:   */  private String apkey;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/* 15:   */  private String branchId;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="log_time")
/* 17:   */  private String logTime;
/* 18:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/* 19:   */  private String branch;
/* 20:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="grade", join=true)
/* 21:   */  private int grade;
/* 22:   */  
/* 23:   */  public String getMac() {
/* 24:24 */    return this.mac;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setMac(String mac) {
/* 28:28 */    this.mac = mac;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public String getBranchId() {
/* 32:32 */    return this.branchId;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setBranchId(String branchId) {
/* 36:36 */    this.branchId = branchId;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public String getBranch() {
/* 40:40 */    return this.branch;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public void setBranch(String branch) {
/* 44:44 */    this.branch = branch;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public String getLogTime() {
/* 48:48 */    return this.logTime;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void setLogTime(String logTime) {
/* 52:52 */    this.logTime = logTime;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public String getFlashId() {
/* 56:56 */    return this.flashId;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public void setFlashId(String flashId) {
/* 60:60 */    this.flashId = flashId;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public String getApkey() {
/* 64:64 */    return this.apkey;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public void setApkey(String apkey) {
/* 68:68 */    this.apkey = apkey;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public int getGrade() {
/* 72:72 */    return this.grade;
/* 73:   */  }
/* 74:   */  
/* 75:   */  public void setGrade(int grade) {
/* 76:76 */    this.grade = grade;
/* 77:   */  }
/* 78:   */  
/* 79:   */  public String getGradeName() {
/* 80:80 */    return BranchDto.GRADE_NAMES[this.grade];
/* 81:   */  }
/* 82:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.LicenseDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */