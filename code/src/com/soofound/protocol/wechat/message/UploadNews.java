/*  1:   */package com.soofound.protocol.wechat.message;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */
/*  6:   */public class UploadNews
/*  7:   */  implements Serializable
/*  8:   */{
/*  9:   */  private static final long serialVersionUID = 20140428182501L;
/* 10:   */  private String thumb_media_id;
/* 11:   */  private String author;
/* 12:   */  private String title;
/* 13:   */  private String content_source_url;
/* 14:   */  private String content;
/* 15:   */  private String digest;
/* 16:   */  
/* 17:   */  public String getThumb_media_id()
/* 18:   */  {
/* 19:19 */    return this.thumb_media_id;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public void setThumb_media_id(String thumb_media_id) {
/* 23:23 */    this.thumb_media_id = thumb_media_id;
/* 24:   */  }
/* 25:   */  
/* 26:   */  public String getAuthor() {
/* 27:27 */    return this.author;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void setAuthor(String author) {
/* 31:31 */    this.author = author;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public String getTitle() {
/* 35:35 */    return this.title;
/* 36:   */  }
/* 37:   */  
/* 38:   */  public void setTitle(String title) {
/* 39:39 */    this.title = title;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public String getContent_source_url() {
/* 43:43 */    return this.content_source_url;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public void setContent_source_url(String content_source_url) {
/* 47:47 */    this.content_source_url = content_source_url;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public String getContent() {
/* 51:51 */    return this.content;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public void setContent(String content) {
/* 55:55 */    this.content = content;
/* 56:   */  }
/* 57:   */  
/* 58:   */  public String getDigest() {
/* 59:59 */    return this.digest;
/* 60:   */  }
/* 61:   */  
/* 62:   */  public void setDigest(String digest) {
/* 63:63 */    this.digest = digest;
/* 64:   */  }
/* 65:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.message.UploadNews
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */