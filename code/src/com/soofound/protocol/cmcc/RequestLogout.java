/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class RequestLogout extends PortalPacket {
/*  4:   */  public RequestLogout() {
/*  5: 5 */    this.type = 5;
/*  6:   */  }
/*  7:   */  
/*  8:   */  public RequestLogout(int id, String userIP) {
/*  9: 9 */    this.type = 5;
/* 10:10 */    this.userIP = userIP;
/* 11:   */    
/* 12:12 */    this.id = id;
/* 13:13 */    Object[] sv = PortalUtils.getByteShort(this.id);
/* 14:14 */    this.rsv = ((Byte)sv[0]).byteValue();
/* 15:15 */    this.serialNo = ((Short)sv[1]).shortValue();
/* 16:   */  }
/* 17:   */  
/* 18:   */  public String getStep()
/* 19:   */  {
/* 20:20 */    return "Step.2[(offline) Portal-->>AC ]Request_Logout";
/* 21:   */  }
/* 22:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.RequestLogout
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */