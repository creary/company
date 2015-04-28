/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="admin_group")
/*  6:   */public final class GroupDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private String id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/* 11:   */  private String name;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="pid")
/* 13:   */  private String pid;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/* 15:   */  private String branchId;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/* 17:   */  private String branch;
/* 18:   */  private int onlineNum;
/* 19:   */  private int num;
/* 20:   */  private boolean open;
/* 21:   */  
/* 22:   */  public String getId()
/* 23:   */  {
/* 24:24 */    return this.id;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setId(String id) {
/* 28:28 */    this.id = id;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public String getPid() {
/* 32:32 */    return this.pid;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setPid(String pid) {
/* 36:36 */    this.pid = pid;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public String getName() {
/* 40:40 */    return this.name;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public void setName(String name) {
/* 44:44 */    this.name = name;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public String getBranchId() {
/* 48:48 */    return this.branchId;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void setBranchId(String branchId) {
/* 52:52 */    this.branchId = branchId;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public String getBranch() {
/* 56:56 */    return this.branch;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public void setBranch(String branch) {
/* 60:60 */    this.branch = branch;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public int getOnlineNum() {
/* 64:64 */    return this.onlineNum;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public void setOnlineNum(int onlineNum) {
/* 68:68 */    this.onlineNum = onlineNum;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public int getNum() {
/* 72:72 */    return this.num;
/* 73:   */  }
/* 74:   */  
/* 75:   */  public void setNum(int num) {
/* 76:76 */    this.num = num;
/* 77:   */  }
/* 78:   */  
/* 79:   */  public boolean isOpen() {
/* 80:80 */    return this.open;
/* 81:   */  }
/* 82:   */  
/* 83:   */  public void setOpen(boolean open) {
/* 84:84 */    this.open = open;
/* 85:   */  }
/* 86:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.GroupDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */