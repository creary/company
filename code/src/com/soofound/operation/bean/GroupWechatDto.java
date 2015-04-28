/*  1:   */package com.soofound.operation.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="group_wechat")
/*  6:   */public class GroupWechatDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="group_id", primaryKey=true)
/*  9:   */  private String groupId;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="img_url")
/* 11:   */  private String imgUrl;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch")
/* 13:   */  private String branch;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="post_url")
/* 15:   */  private String postUrl;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ip_address")
/* 17:   */  private String ipAddress;
/* 18:   */  
/* 19:   */  public String getGroupId() {
/* 20:20 */    return this.groupId;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void setGroupId(String groupId) {
/* 24:24 */    this.groupId = groupId;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public String getImgUrl() {
/* 28:28 */    return this.imgUrl;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public void setImgUrl(String imgUrl) {
/* 32:32 */    this.imgUrl = imgUrl;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public String getPostUrl() {
/* 36:36 */    return this.postUrl;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public void setPostUrl(String postUrl) {
/* 40:40 */    this.postUrl = postUrl;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public String getIpAddress() {
/* 44:44 */    return this.ipAddress;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void setIpAddress(String ipAddress) {
/* 48:48 */    this.ipAddress = ipAddress;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public String getBranch() {
/* 52:52 */    return this.branch;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void setBranch(String branch) {
/* 56:56 */    this.branch = branch;
/* 57:   */  }
/* 58:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.bean.GroupWechatDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */