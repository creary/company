/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class AcknowledgeAuthentication extends PortalPacket {
/*  4:   */  public AcknowledgeAuthentication() {
/*  5: 5 */    this.type = 4;
/*  6:   */  }
/*  7:   */  
/*  8:   */  public AcknowledgeAuthentication(RequestAuthentication ra) {
/*  9: 9 */    this.id = PortalUtils.getPacketID();
/* 10:10 */    this.version = ra.getVersion();
/* 11:11 */    this.type = 4;
/* 12:12 */    this.userIP = ra.getUserIP();
/* 13:13 */    this.requestId = ra.getRequestId();
/* 14:14 */    this.papChap = ra.getPapChap();
/* 15:15 */    this.serialNo = ra.getSerialNo();
/* 16:16 */    this.rsv = ra.getRsv();
/* 17:   */  }
/* 18:   */  
/* 19:   */  public String getStep()
/* 20:   */  {
/* 21:21 */    return "Step.13[ AC-->>Portal ]Acknowledge_Authentication";
/* 22:   */  }
/* 23:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.AcknowledgeAuthentication
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */