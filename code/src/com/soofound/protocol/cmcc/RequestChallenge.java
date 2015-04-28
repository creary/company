/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class RequestChallenge extends PortalPacket {
/*  4:   */  public RequestChallenge() {
/*  5: 5 */    this.type = 1;
/*  6:   */  }
/*  7:   */  
/*  8:   */  public RequestChallenge(String ipAddress) {
/*  9: 9 */    this.type = 1;
/* 10:   */    
/* 11:11 */    this.id = PortalUtils.getPacketID();
/* 12:12 */    Object[] sv = PortalUtils.getByteShort(this.id);
/* 13:13 */    this.rsv = ((Byte)sv[0]).byteValue();
/* 14:14 */    this.serialNo = ((Short)sv[1]).shortValue();
/* 15:   */    
/* 16:16 */    this.requestId = 0;
/* 17:17 */    this.userIP = ipAddress;
/* 18:18 */    this.errCode = 0;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public String getStep()
/* 22:   */  {
/* 23:23 */    return "Step.8[ Portal-->>AC ]Request_Challenge";
/* 24:   */  }
/* 25:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.RequestChallenge
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */