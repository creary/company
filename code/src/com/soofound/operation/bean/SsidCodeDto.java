/*  1:   */package com.soofound.operation.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="cpe_ssid_code")
/*  6:   */public class SsidCodeDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private int id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/* 11:   */  private String name;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ssid")
/* 13:   */  private String ssid;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/* 15:   */  private String branchId;
/* 16:   */  
/* 17:   */  public int getId() {
/* 18:18 */    return this.id;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public void setId(int id) {
/* 22:22 */    this.id = id;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public String getName() {
/* 26:26 */    return this.name;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public void setName(String name) {
/* 30:30 */    this.name = name;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public String getSsid() {
/* 34:34 */    return this.ssid;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public void setSsid(String ssid) {
/* 38:38 */    this.ssid = ssid;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public String getBranchId() {
/* 42:42 */    return this.branchId;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public void setBranchId(String branchId) {
/* 46:46 */    this.branchId = branchId;
/* 47:   */  }
/* 48:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.bean.SsidCodeDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */