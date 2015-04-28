/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import javax.xml.soap.SOAPException;
/*  4:   */import javax.xml.soap.SOAPFactory;
/*  5:   */
/*  6:   */public class Download extends Message
/*  7:   */{
/*  8:   */  private static final long serialVersionUID = 201312222046001L;
/*  9:   */  private String commandKey;
/* 10:   */  private String fileType;
/* 11:   */  private String url;
/* 12:   */  private String version;
/* 13:13 */  private long fileSize = 0L;
/* 14:14 */  private int delaySeconds = 0;
/* 15:   */  private String md5;
/* 16:   */  public static final String FT_FIRMWARE = "1. Firmware Upgrade Image";
/* 17:   */  public static final String FT_WEBCONTENT = "2. Web Content";
/* 18:   */  public static final String FT_CONFIG = "3. Vendor Configuration File";
/* 19:   */  
/* 20:   */  public Download()
/* 21:   */  {
/* 22:22 */    this.name = "Download";
/* 23:   */  }
/* 24:   */  
/* 25:   */  public Download(String commandKey, String url, String fileType) {
/* 26:26 */    this.name = "Download";
/* 27:27 */    this.commandKey = commandKey;
/* 28:28 */    this.url = url;
/* 29:29 */    this.fileType = fileType;
/* 30:   */  }
/* 31:   */  
/* 32:   */  protected void createBody(javax.xml.soap.SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
/* 33:33 */    body.addChildElement("CommandKey").setValue(this.commandKey);
/* 34:34 */    body.addChildElement("FileType").setValue(this.fileType);
/* 35:35 */    body.addChildElement("URL").setValue(this.url);
/* 36:36 */    body.addChildElement("Version").setValue(this.version);
/* 37:37 */    body.addChildElement("FileSize").setValue(String.valueOf(this.fileSize));
/* 38:38 */    body.addChildElement("DelaySeconds").setValue(String.valueOf(this.delaySeconds));
/* 39:39 */    body.addChildElement("MD5").setValue(this.md5);
/* 40:   */  }
/* 41:   */  
/* 42:   */  protected void parseBody(javax.xml.soap.SOAPBodyElement body, SOAPFactory f) throws SOAPException
/* 43:   */  {}
/* 44:   */  
/* 45:   */  public String getCommandKey() {
/* 46:46 */    return this.commandKey;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void setCommandKey(String commandKey) {
/* 50:50 */    this.commandKey = commandKey;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void setFileType(String fileType) {
/* 54:54 */    this.fileType = fileType;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void setUrl(String url) {
/* 58:58 */    this.url = url;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public void setFileSize(long fileSize) {
/* 62:62 */    this.fileSize = fileSize;
/* 63:   */  }
/* 64:   */  
/* 65:   */  public void setDelaySeconds(int delaySeconds) {
/* 66:66 */    this.delaySeconds = delaySeconds;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public void setMD5(String md5) {
/* 70:70 */    this.md5 = md5;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public void setVersion(String version) {
/* 74:74 */    this.version = version;
/* 75:   */  }
/* 76:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.Download
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */