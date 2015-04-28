/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="portal_wechat_share")
/*  6:   */public final class WechatShareDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private int id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="portal_id")
/* 11:   */  private int portalId;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="share_id")
/* 13:   */  private int shareId;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="flag")
/* 15:   */  private int flag;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="cover", join=true)
/* 17:   */  private String cover;
/* 18:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="title", join=true)
/* 19:   */  private String title;
/* 20:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="content", join=true)
/* 21:   */  private String content;
/* 22:   */  
/* 23:   */  public int getId() {
/* 24:24 */    return this.id;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setId(int id) {
/* 28:28 */    this.id = id;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public int getPortalId() {
/* 32:32 */    return this.portalId;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setPortalId(int portalId) {
/* 36:36 */    this.portalId = portalId;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public int getShareId() {
/* 40:40 */    return this.shareId;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public void setShareId(int shareId) {
/* 44:44 */    this.shareId = shareId;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public String getCover() {
/* 48:48 */    return this.cover;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void setCover(String cover) {
/* 52:52 */    this.cover = cover;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public String getTitle() {
/* 56:56 */    return this.title;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public void setTitle(String title) {
/* 60:60 */    this.title = title;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public String getContent() {
/* 64:64 */    return this.content;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public void setContent(String content) {
/* 68:68 */    this.content = content;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public int getFlag() {
/* 72:72 */    return this.flag;
/* 73:   */  }
/* 74:   */  
/* 75:   */  public void setFlag(int flag) {
/* 76:76 */    this.flag = flag;
/* 77:   */  }
/* 78:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.WechatShareDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */