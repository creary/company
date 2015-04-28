/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import javax.xml.soap.SOAPException;
/*  4:   */
/*  5:   */public class RebootResponse extends Message
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 201312221928001L;
/*  8:   */  
/*  9:   */  public RebootResponse()
/* 10:   */  {
/* 11:11 */    this.name = "RebootResponse";
/* 12:   */  }
/* 13:   */  
/* 14:   */  protected void createBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf)
/* 15:   */    throws SOAPException
/* 16:   */  {}
/* 17:   */  
/* 18:   */  protected void parseBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory f)
/* 19:   */    throws SOAPException
/* 20:   */  {}
/* 21:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.RebootResponse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */