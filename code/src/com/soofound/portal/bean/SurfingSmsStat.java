/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="surfing_sms_stat")
/*  6:   */public final class SurfingSmsStat implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/*  9:   */  private String branchId;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="pv_total")
/* 11:   */  private int pvTotal;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="pv_biz")
/* 13:   */  private int pvBiz;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="sms_used")
/* 15:   */  private int smsUsed;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="sms_remain")
/* 17:   */  private int smsRemain;
/* 18:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/* 19:   */  private String branch;
/* 20:   */  
/* 21:   */  public String getBranchId() {
/* 22:22 */    return this.branchId;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void setBranchId(String branchId) {
/* 26:26 */    this.branchId = branchId;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public int getPvTotal() {
/* 30:30 */    return this.pvTotal;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public void setPvTotal(int pvTotal) {
/* 34:34 */    this.pvTotal = pvTotal;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public int getPvBiz() {
/* 38:38 */    return this.pvBiz;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public void setPvBiz(int pvBiz) {
/* 42:42 */    this.pvBiz = pvBiz;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public int getSmsUsed() {
/* 46:46 */    return this.smsUsed;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void setSmsUsed(int smsUsed) {
/* 50:50 */    this.smsUsed = smsUsed;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public int getSmsRemain() {
/* 54:54 */    return this.smsRemain;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void setSmsRemain(int smsRemain) {
/* 58:58 */    this.smsRemain = smsRemain;
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
 * Qualified Name:     com.soofound.portal.bean.SurfingSmsStat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */