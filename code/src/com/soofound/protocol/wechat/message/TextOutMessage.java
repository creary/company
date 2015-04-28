/*  1:   */package com.soofound.protocol.wechat.message;
/*  2:   */
/*  3:   */public class TextOutMessage {
/*  4:   */  private String ToUserName;
/*  5:   */  private String FromUserName;
/*  6:   */  private Long CreateTime;
/*  7: 7 */  private int FuncFlag = 0;
/*  8: 8 */  private String MsgType = "text";
/*  9:   */  private String Content;
/* 10:   */  
/* 11:   */  public String getToUserName()
/* 12:   */  {
/* 13:13 */    return this.ToUserName;
/* 14:   */  }
/* 15:   */  
/* 16:   */  public void setToUserName(String toUserName) {
/* 17:17 */    this.ToUserName = toUserName;
/* 18:   */  }
/* 19:   */  
/* 20:   */  public String getFromUserName() {
/* 21:21 */    return this.FromUserName;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public void setFromUserName(String fromUserName) {
/* 25:25 */    this.FromUserName = fromUserName;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public Long getCreateTime() {
/* 29:29 */    return this.CreateTime;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public void setCreateTime(Long createTime) {
/* 33:33 */    this.CreateTime = createTime;
/* 34:   */  }
/* 35:   */  
/* 36:   */  public int getFuncFlag() {
/* 37:37 */    return this.FuncFlag;
/* 38:   */  }
/* 39:   */  
/* 40:   */  public void setFuncFlag(int funcFlag) {
/* 41:41 */    this.FuncFlag = funcFlag;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public String getMsgType() {
/* 45:45 */    return this.MsgType;
/* 46:   */  }
/* 47:   */  
/* 48:   */  public void setMsgType(String msgType) {
/* 49:49 */    this.MsgType = msgType;
/* 50:   */  }
/* 51:   */  
/* 52:   */  public TextOutMessage() {}
/* 53:   */  
/* 54:   */  public TextOutMessage(String content)
/* 55:   */  {
/* 56:56 */    this.Content = content;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public String getContent() {
/* 60:60 */    return this.Content;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public void setContent(String content) {
/* 64:64 */    this.Content = content;
/* 65:   */  }
/* 66:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.message.TextOutMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */