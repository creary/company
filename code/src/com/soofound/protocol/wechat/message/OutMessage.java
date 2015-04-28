/*  1:   */package com.soofound.protocol.wechat.message;
/*  2:   */
/*  3:   */public class OutMessage {
/*  4:   */  protected String ToUserName;
/*  5:   */  protected String FromUserName;
/*  6:   */  protected Long CreateTime;
/*  7: 7 */  protected int FuncFlag = 0;
/*  8:   */  protected String MsgType;
/*  9:   */  
/* 10:   */  public String getToUserName() {
/* 11:11 */    return this.ToUserName;
/* 12:   */  }
/* 13:   */  
/* 14:   */  public void setToUserName(String toUserName) {
/* 15:15 */    this.ToUserName = toUserName;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public String getFromUserName() {
/* 19:19 */    return this.FromUserName;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public void setFromUserName(String fromUserName) {
/* 23:23 */    this.FromUserName = fromUserName;
/* 24:   */  }
/* 25:   */  
/* 26:   */  public Long getCreateTime() {
/* 27:27 */    return this.CreateTime;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void setCreateTime(Long createTime) {
/* 31:31 */    this.CreateTime = createTime;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public int getFuncFlag() {
/* 35:35 */    return this.FuncFlag;
/* 36:   */  }
/* 37:   */  
/* 38:   */  public void setFuncFlag(int funcFlag) {
/* 39:39 */    this.FuncFlag = funcFlag;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public String getMsgType() {
/* 43:43 */    return this.MsgType;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public void setMsgType(String msgType) {
/* 47:47 */    this.MsgType = msgType;
/* 48:   */  }
/* 49:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.message.OutMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */