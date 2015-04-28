/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class AcknowledgeInformation extends PortalPacket {
/*  4:   */  public AcknowledgeInformation() {
/*  5: 5 */    this.type = 10;
/*  6:   */  }
/*  7:   */  
/*  8:   */  public String getStep()
/*  9:   */  {
/* 10:10 */    return "Acknowledge_Information";
/* 11:   */  }
/* 12:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.AcknowledgeInformation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */