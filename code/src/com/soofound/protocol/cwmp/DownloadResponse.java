/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import javax.xml.soap.SOAPException;
/*  4:   */
/*  5:   */public class DownloadResponse extends Message
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 201312222049001L;
/*  8:   */  public String startTime;
/*  9:   */  public String completeTime;
/* 10:   */  public int status;
/* 11:   */  
/* 12:   */  protected void createBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException
/* 13:   */  {}
/* 14:   */  
/* 15:   */  protected void parseBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException
/* 16:   */  {
/* 17:17 */    this.startTime = getRequestElement(spf, body, "StartTime");
/* 18:18 */    this.completeTime = getRequestElement(spf, body, "CompleteTime");
/* 19:19 */    this.status = Integer.parseInt(getRequestElement(spf, body, "Status"));
/* 20:   */  }
/* 21:   */  
/* 22:   */  public String toString() {
/* 23:23 */    String s = "DownloadResponse: Status = " + this.status + " StartTime " + this.startTime + " CompleteTime " + this.completeTime;
/* 24:24 */    return s;
/* 25:   */  }
/* 26:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.DownloadResponse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */