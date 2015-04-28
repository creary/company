/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */public final class BranchStatDto {
/*  4:   */  private String branchId;
/*  5:   */  private String name;
/*  6:   */  private String parent;
/*  7:   */  private int grade;
/*  8:   */  private int deviceTotal;
/*  9:   */  private int onlineDeviceTotal;
/* 10:   */  
/* 11:   */  public String getBranchId() {
/* 12:12 */    return this.branchId;
/* 13:   */  }
/* 14:   */  
/* 15:   */  public void setBranchId(String branchId) {
/* 16:16 */    this.branchId = branchId;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public String getName() {
/* 20:20 */    return this.name;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void setName(String name) {
/* 24:24 */    this.name = name;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public int getGrade() {
/* 28:28 */    return this.grade;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public int getDeviceTotal() {
/* 32:32 */    return this.deviceTotal;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setDeviceTotal(int deviceTotal) {
/* 36:36 */    this.deviceTotal = deviceTotal;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public int getOnlineDeviceTotal() {
/* 40:40 */    return this.onlineDeviceTotal;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public void setOnlineDeviceTotal(int onlineDeviceTotal) {
/* 44:44 */    this.onlineDeviceTotal = onlineDeviceTotal;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void setGrade(int grade) {
/* 48:48 */    this.grade = grade;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public String getParent() {
/* 52:52 */    return this.parent;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void setParent(String parent) {
/* 56:56 */    this.parent = parent;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public String getGradeName() {
/* 60:60 */    return BranchDto.GRADE_NAMES[this.grade];
/* 61:   */  }
/* 62:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.BranchStatDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */