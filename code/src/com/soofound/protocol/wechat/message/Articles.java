/*  1:   */package com.soofound.protocol.wechat.message;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */
/*  6:   */public class Articles
/*  7:   */  implements Serializable
/*  8:   */{
/*  9:   */  private static final long serialVersionUID = 20140411111023L;
/* 10:   */  private String Title;
/* 11:   */  private String Description;
/* 12:   */  private String PicUrl;
/* 13:   */  private String Url;
/* 14:   */  
/* 15:   */  public String getTitle()
/* 16:   */  {
/* 17:17 */    return this.Title;
/* 18:   */  }
/* 19:   */  
/* 20:   */  public void setTitle(String title) {
/* 21:21 */    this.Title = title;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public String getDescription() {
/* 25:25 */    return this.Description;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void setDescription(String description) {
/* 29:29 */    this.Description = description;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public String getPicUrl() {
/* 33:33 */    return this.PicUrl;
/* 34:   */  }
/* 35:   */  
/* 36:   */  public void setPicUrl(String picUrl) {
/* 37:37 */    this.PicUrl = picUrl;
/* 38:   */  }
/* 39:   */  
/* 40:   */  public String getUrl() {
/* 41:41 */    return this.Url;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public void setUrl(String url) {
/* 45:45 */    this.Url = url;
/* 46:   */  }
/* 47:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.message.Articles
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */