/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="portal_template")
/*  6:   */public class PortalTemplateDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private String id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/* 11:   */  private String name;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="cover")
/* 13:   */  private String cover;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="descr")
/* 15:   */  private String descr;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="create_time")
/* 17:   */  private String createTime;
/* 18:   */  
/* 19:   */  public String getId() {
/* 20:20 */    return this.id;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void setId(String id) {
/* 24:24 */    this.id = id;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public String getName() {
/* 28:28 */    return this.name;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public void setName(String name) {
/* 32:32 */    this.name = name;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public String getCreateTime() {
/* 36:36 */    return this.createTime;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public void setCreateTime(String createTime) {
/* 40:40 */    this.createTime = createTime;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public String getCover() {
/* 44:44 */    return this.cover;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void setCover(String cover) {
/* 48:48 */    this.cover = cover;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public String getDescr() {
/* 52:52 */    return this.descr;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void setDescr(String descr) {
/* 56:56 */    this.descr = descr;
/* 57:   */  }
/* 58:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.PortalTemplateDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */