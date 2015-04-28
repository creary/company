/*  1:   */package com.soofound.protocol.wechat.message;
/*  2:   */
/*  5:   */public enum MessageTypes
/*  6:   */{
/*  7: 7 */  TEXT("text"), 
/*  8: 8 */  LOCATION("location"), 
/*  9: 9 */  IMAGE("image"), 
/* 10:10 */  LINK("link"), 
/* 11:11 */  VOICE("voice"), 
/* 12:12 */  EVENT("event"), 
/* 13:13 */  VIDEO("video"), 
/* 14:14 */  NEWS("news"), 
/* 15:15 */  MUSIC("music");
/* 16:   */  
/* 17:   */  private String type;
/* 18:   */  
/* 19:   */  private MessageTypes(String type) {
/* 20:20 */    this.type = type;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public String getType() {
/* 24:24 */    return this.type;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setType(String type) {
/* 28:28 */    this.type = type;
/* 29:   */  }
/* 30:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.message.MessageTypes
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */