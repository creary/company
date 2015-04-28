/*  1:   */package com.soofound.protocol.wechat.message;
/*  2:   */
/*  3:   */public class MusicOutMessage
/*  4:   */  extends OutMessage
/*  5:   */{
/*  6:   */  private String MusicUrl;
/*  7:   */  private String HQMusicUrl;
/*  8:   */  
/*  9:   */  public MusicOutMessage()
/* 10:   */  {
/* 11:11 */    this.MsgType = "music";
/* 12:   */  }
/* 13:   */  
/* 14:   */  public String getMsgType() {
/* 15:15 */    return this.MsgType;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public String getMusicUrl() {
/* 19:19 */    return this.MusicUrl;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public void setMusicUrl(String musicUrl) {
/* 23:23 */    this.MusicUrl = musicUrl;
/* 24:   */  }
/* 25:   */  
/* 26:   */  public String getHQMusicUrl() {
/* 27:27 */    return this.HQMusicUrl;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void setHQMusicUrl(String hQMusicUrl) {
/* 31:31 */    this.HQMusicUrl = hQMusicUrl;
/* 32:   */  }
/* 33:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.message.MusicOutMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */