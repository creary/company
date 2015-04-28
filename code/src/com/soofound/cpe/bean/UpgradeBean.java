/*  1:   */package com.soofound.cpe.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="cpe_upgrade")
/*  6:   */public final class UpgradeBean implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id", primaryKey=true)
/*  9:   */  private String branchId;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="flag")
/* 11:   */  private int flag;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/* 13:   */  private String branch;
/* 14:   */  
/* 15:   */  public String getBranchId() {
/* 16:16 */    return this.branchId;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void setBranchId(String branchId) {
/* 20:20 */    this.branchId = branchId;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public int getFlag() {
/* 24:24 */    return this.flag;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setFlag(int flag) {
/* 28:28 */    this.flag = flag;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public String getBranch() {
/* 32:32 */    return this.branch;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setBranch(String branch) {
/* 36:36 */    this.branch = branch;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public String getFlagText() {
/* 40:40 */    if (this.flag == 1)
/* 41:41 */      return "是";
/* 42:42 */    return "否";
/* 43:   */  }
/* 44:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.bean.UpgradeBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */