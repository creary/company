/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class AffirmAcknowledgeAuthentication extends PortalPacket {
/*  4:   */  public AffirmAcknowledgeAuthentication() {
/*  5: 5 */    this.type = 7;
/*  6:   */  }
/*  7:   */  
/*  8:   */  public AffirmAcknowledgeAuthentication(RequestAuthentication ra) {
/*  9: 9 */    this.type = 7;
/* 10:10 */    this.userIP = ra.getUserIP();
/* 11:11 */    this.requestId = ra.getRequestId();
/* 12:12 */    this.serialNo = ra.getSerialNo();
/* 13:13 */    this.rsv = ra.getRsv();
/* 14:14 */    this.id = ra.getId();
/* 15:   */  }
/* 16:   */  
/* 17:   */  public String getStep()
/* 18:   */  {
/* 19:19 */    return "Step.15[ AC-->>Portal ]Affirm_Acknowledge_Authentication";
/* 20:   */  }
/* 21:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.AffirmAcknowledgeAuthentication
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */