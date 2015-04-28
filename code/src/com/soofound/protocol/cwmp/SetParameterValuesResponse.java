/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import javax.xml.soap.SOAPException;
/*  4:   */
/*  5:   */public class SetParameterValuesResponse extends Message
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 201312221945001L;
/*  8:   */  public int status;
/*  9:   */  
/* 10:   */  public SetParameterValuesResponse()
/* 11:   */  {
/* 12:12 */    this.name = "SetParameterValuesResponse";
/* 13:   */  }
/* 14:   */  
/* 15:   */  protected void createBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException
/* 16:   */  {}
/* 17:   */  
/* 18:   */  protected void parseBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException {
/* 19:19 */    this.status = Integer.parseInt(getRequestElement(spf, body, "Status"));
/* 20:   */  }
/* 21:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.SetParameterValuesResponse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */