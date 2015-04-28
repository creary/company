/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="surfing_black_white")
/*  6:   */public final class BlackWhiteDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private int id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="mac")
/* 11:   */  private String mac;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/* 13:   */  private String branchId;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="bw")
/* 15:   */  private int bw;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="reason")
/* 17:   */  private String reason;
/* 18:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/* 19:   */  private String branch;
/* 20:   */  
/* 21:   */  public int getId() {
/* 22:22 */    return this.id;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void setId(int id) {
/* 26:26 */    this.id = id;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public String getMac() {
/* 30:30 */    return this.mac;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public void setMac(String mac) {
/* 34:34 */    this.mac = mac;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public int getBw() {
/* 38:38 */    return this.bw;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public void setBw(int bw) {
/* 42:42 */    this.bw = bw;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public String getReason() {
/* 46:46 */    return this.reason;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void setReason(String reason) {
/* 50:50 */    this.reason = reason;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public String getBranchId() {
/* 54:54 */    return this.branchId;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void setBranchId(String branchId) {
/* 58:58 */    this.branchId = branchId;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public String getBranch() {
/* 62:62 */    return this.branch;
/* 63:   */  }
/* 64:   */  
/* 65:   */  public void setBranch(String branch) {
/* 66:66 */    this.branch = branch;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public String getBwText() {
/* 70:70 */    if (this.bw == 1)
/* 71:71 */      return "白名单";
/* 72:72 */    if (this.bw == 2)
/* 73:73 */      return "黑名单";
/* 74:74 */    if (this.bw == 3)
/* 75:75 */      return "免认证名单";
/* 76:76 */    if (this.bw == 4)
/* 77:77 */      return "员工名单";
/* 78:78 */    return "黑名单";
/* 79:   */  }
/* 80:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.BlackWhiteDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */