/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class RequestInformation extends PortalPacket {
/*  4:   */  public RequestInformation() {
/*  5: 5 */    this.type = 9;
/*  6:   */  }
/*  7:   */  
/*  8:   */  public String getStep()
/*  9:   */  {
/* 10:10 */    return "Request_Information";
/* 11:   */  }
/* 12:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.RequestInformation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */