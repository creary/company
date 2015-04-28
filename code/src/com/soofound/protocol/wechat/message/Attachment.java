/*  1:   */package com.soofound.protocol.wechat.message;
/*  2:   */
/*  3:   */import java.io.BufferedInputStream;
/*  4:   */
/*  6:   */public class Attachment
/*  7:   */{
/*  8:   */  private String fileName;
/*  9:   */  private String fullName;
/* 10:   */  private String suffix;
/* 11:   */  private String contentLength;
/* 12:   */  private String contentType;
/* 13:   */  private BufferedInputStream fileStream;
/* 14:   */  private String error;
/* 15:   */  
/* 16:   */  public String getFullName()
/* 17:   */  {
/* 18:18 */    return this.fullName;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public void setFullName(String fullName) {
/* 22:22 */    this.fullName = fullName;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public String getFileName() {
/* 26:26 */    return this.fileName;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public void setFileName(String fileName) {
/* 30:30 */    this.fileName = fileName;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public String getContentLength() {
/* 34:34 */    return this.contentLength;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public void setContentLength(String contentLength) {
/* 38:38 */    this.contentLength = contentLength;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public String getContentType() {
/* 42:42 */    return this.contentType;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public void setContentType(String contentType) {
/* 46:46 */    this.contentType = contentType;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public BufferedInputStream getFileStream() {
/* 50:50 */    return this.fileStream;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void setFileStream(BufferedInputStream fileStream) {
/* 54:54 */    this.fileStream = fileStream;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public String getError() {
/* 58:58 */    return this.error;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public void setError(String error) {
/* 62:62 */    this.error = error;
/* 63:   */  }
/* 64:   */  
/* 65:   */  public void setSuffix(String suffix) {
/* 66:66 */    this.suffix = suffix;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public String getSuffix() {
/* 70:70 */    return this.suffix;
/* 71:   */  }
/* 72:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.message.Attachment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */