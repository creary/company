/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="admin_announcement")
/*  6:   */public final class AlarmMessage implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private int id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="title")
/* 11:   */  private String title;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="content")
/* 13:   */  private String content;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="create_time")
/* 15:   */  private String createTime;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="flag", join=true)
/* 17:   */  private int readFlag;
/* 18:   */  
/* 19:   */  public int getId() {
/* 20:20 */    return this.id;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void setId(int id) {
/* 24:24 */    this.id = id;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public String getTitle() {
/* 28:28 */    return this.title;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public void setTitle(String title) {
/* 32:32 */    this.title = title;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public String getContent() {
/* 36:36 */    return this.content;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public void setContent(String content) {
/* 40:40 */    this.content = content;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public String getCreateTime() {
/* 44:44 */    return this.createTime;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void setCreateTime(String createTime) {
/* 48:48 */    this.createTime = createTime;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public int getReadFlag() {
/* 52:52 */    return this.readFlag;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void setReadFlag(int readFlag) {
/* 56:56 */    this.readFlag = readFlag;
/* 57:   */  }
/* 58:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.AlarmMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */