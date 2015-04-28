/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class AcknowledgeLogout extends PortalPacket {
/*  4:   */  public AcknowledgeLogout() {
/*  5: 5 */    this.type = 6;
/*  6:   */  }
/*  7:   */  
/*  8:   */  public AcknowledgeLogout(RequestLogout rl) {
/*  9: 9 */    this.type = 6;
/* 10:10 */    this.userIP = rl.getUserIP();
/* 11:11 */    this.id = rl.getId();
/* 12:   */    
/* 13:13 */    Object[] sv = PortalUtils.getByteShort(this.id);
/* 14:14 */    this.rsv = ((Byte)sv[0]).byteValue();
/* 15:15 */    this.serialNo = ((Short)sv[1]).shortValue();
/* 16:   */  }
/* 17:   */  
/* 18:   */  public AcknowledgeLogout(ForceLogout fl) {
/* 19:19 */    this.type = 6;
/* 20:20 */    this.userIP = fl.getUserIP();
/* 21:21 */    this.id = fl.getId();
/* 22:   */    
/* 23:23 */    Object[] sv = PortalUtils.getByteShort(this.id);
/* 24:24 */    this.rsv = ((Byte)sv[0]).byteValue();
/* 25:25 */    this.serialNo = ((Short)sv[1]).shortValue();
/* 26:   */  }
/* 27:   */  
/* 28:   */  public String getStep()
/* 29:   */  {
/* 30:30 */    return "Step.3[(offline) AC-->>Portal ]Acknowledge_Logout";
/* 31:   */  }
/* 32:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.AcknowledgeLogout
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */