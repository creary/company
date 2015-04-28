/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="portal_wechat_guide")
/*  6:   */public final class WechatGuideDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id", primaryKey=true)
/*  9:   */  private String branchId;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="flag")
/* 11:   */  private String flag;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="page_value")
/* 13:   */  private String pageValue;
/* 14:   */  
/* 15:   */  public String getBranchId() {
/* 16:16 */    return this.branchId;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void setBranchId(String branchId) {
/* 20:20 */    this.branchId = branchId;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public String getFlag() {
/* 24:24 */    return this.flag;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setFlag(String flag) {
/* 28:28 */    this.flag = flag;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public String getPageValue() {
/* 32:32 */    return this.pageValue;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setPageValue(String pageValue) {
/* 36:36 */    this.pageValue = pageValue;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public static WechatGuideDto bornDefault() {
/* 40:40 */    WechatGuideDto dto = new WechatGuideDto();
/* 41:41 */    dto.setFlag("default");
/* 42:42 */    dto.setPageValue("");
/* 43:43 */    return dto;
/* 44:   */  }
/* 45:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.WechatGuideDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */