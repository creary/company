/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class RequestAuthentication extends PortalPacket {
/*  4:   */  public RequestAuthentication() {
/*  5: 5 */    this.type = 3;
/*  6:   */  }
/*  7:   */  
/*  8:   */  public RequestAuthentication(AcknowledgeChallenge ac) {
/*  9: 9 */    this.type = 3;
/* 10:10 */    this.userIP = ac.getUserIP();
/* 11:11 */    this.id = ac.getId();
/* 12:12 */    this.serialNo = ac.getSerialNo();
/* 13:13 */    this.rsv = ac.getRsv();
/* 14:   */    
/* 18:18 */    this.papChap = ac.getPapChap();
/* 19:19 */    this.attrNum = 2;
/* 20:20 */    this.errCode = 0;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public String getStep()
/* 24:   */  {
/* 25:25 */    return "Step.10[ Portal-->>AC ]Request_Authentication";
/* 26:   */  }
/* 27:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.RequestAuthentication
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */