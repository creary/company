/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class ReplyMessageAttribute extends PortalAttribute {
/*  4:   */  private String message;
/*  5:   */  
/*  6:   */  public ReplyMessageAttribute(String message) {
/*  7: 7 */    this.message = message;
/*  8:   */  }
/*  9:   */  
/* 10:   */  public String getMessage() {
/* 11:11 */    return this.message;
/* 12:   */  }
/* 13:   */  
/* 14:   */  public int getType()
/* 15:   */  {
/* 16:16 */    return 5;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public byte[] getBytes()
/* 20:   */  {
/* 21:21 */    return encodeString(getType(), this.message.getBytes());
/* 22:   */  }
/* 23:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.ReplyMessageAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */