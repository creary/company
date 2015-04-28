/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class ForceLogout extends PortalPacket {
/*  4:   */  public ForceLogout() {
/*  5: 5 */    this.type = 8;
/*  6:   */  }
/*  7:   */  
/*  8:   */  public ForceLogout(int id, String userIP) {
/*  9: 9 */    this.type = 8;
/* 10:10 */    this.userIP = userIP;
/* 11:11 */    this.id = id;
/* 12:12 */    Object[] sv = PortalUtils.getByteShort(this.id);
/* 13:13 */    this.rsv = ((Byte)sv[0]).byteValue();
/* 14:14 */    this.serialNo = ((Short)sv[1]).shortValue();
/* 15:   */  }
/* 16:   */  
/* 17:   */  public String getStep()
/* 18:   */  {
/* 19:19 */    return "Step.1[ AC-->>Portal ]Force_Logout";
/* 20:   */  }
/* 21:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.ForceLogout
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */