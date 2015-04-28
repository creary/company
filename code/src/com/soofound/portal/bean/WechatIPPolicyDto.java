/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */import java.util.HashMap;
/*  5:   */import java.util.Map;
/*  6:   */
/*  7:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="portal_wechat_ip_policy")
/*  8:   */public final class WechatIPPolicyDto implements Persistable
/*  9:   */{
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/* 11:   */  private int id;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/* 13:   */  private String branchId;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/* 15:   */  private String name;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="img_url")
/* 17:   */  private String imgUrl;
/* 18:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="post_url")
/* 19:   */  private String postUrl;
/* 20:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ip_address")
/* 21:   */  private String ipAddress;
/* 22:   */  
/* 23:   */  public int getId()
/* 24:   */  {
/* 25:25 */    return this.id;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void setId(int id) {
/* 29:29 */    this.id = id;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public String getBranchId() {
/* 33:33 */    return this.branchId;
/* 34:   */  }
/* 35:   */  
/* 36:   */  public void setBranchId(String branchId) {
/* 37:37 */    this.branchId = branchId;
/* 38:   */  }
/* 39:   */  
/* 40:   */  public String getName() {
/* 41:41 */    return this.name;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public void setName(String name) {
/* 45:45 */    this.name = name;
/* 46:   */  }
/* 47:   */  
/* 48:   */  public String getImgUrl() {
/* 49:49 */    return this.imgUrl;
/* 50:   */  }
/* 51:   */  
/* 52:   */  public void setImgUrl(String imgUrl) {
/* 53:53 */    this.imgUrl = imgUrl;
/* 54:   */  }
/* 55:   */  
/* 56:   */  public String getPostUrl() {
/* 57:57 */    return this.postUrl;
/* 58:   */  }
/* 59:   */  
/* 60:   */  public void setPostUrl(String postUrl) {
/* 61:61 */    this.postUrl = postUrl;
/* 62:   */  }
/* 63:   */  
/* 64:   */  public String getIpAddress() {
/* 65:65 */    return this.ipAddress;
/* 66:   */  }
/* 67:   */  
/* 68:   */  public void setIpAddress(String ipAddress) {
/* 69:69 */    this.ipAddress = ipAddress;
/* 70:   */  }
/* 71:   */  
/* 72:   */  public Map<String, String> toMap() {
/* 73:73 */    Map<String, String> map = new HashMap();
/* 74:74 */    map.put("id", String.valueOf(this.id));
/* 75:75 */    map.put("name", this.name);
/* 76:76 */    map.put("title", "");
/* 77:77 */    map.put("cover", this.imgUrl);
/* 78:78 */    map.put("href", this.postUrl);
/* 79:79 */    return map;
/* 80:   */  }
/* 81:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.WechatIPPolicyDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */