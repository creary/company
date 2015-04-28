/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="surfing_advertise_category")
/*  6:   */public final class AdvertiseCategoryBean implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private int id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/* 11:   */  private String name;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/* 13:   */  private String branchId;
/* 14:   */  
/* 15:   */  public int getId() {
/* 16:16 */    return this.id;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void setId(int id) {
/* 20:20 */    this.id = id;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public String getName() {
/* 24:24 */    return this.name;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setName(String name) {
/* 28:28 */    this.name = name;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public String getBranchId() {
/* 32:32 */    return this.branchId;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setBranchId(String branchId) {
/* 36:36 */    this.branchId = branchId;
/* 37:   */  }
/* 38:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.AdvertiseCategoryBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */