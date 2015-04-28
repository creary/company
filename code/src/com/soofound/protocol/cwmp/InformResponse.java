/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import javax.xml.soap.SOAPElement;
/*  4:   */
/*  5:   */public class InformResponse extends Message
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 201312221925001L;
/*  8:   */  public int maxEnvelopes;
/*  9:   */  
/* 10:   */  public InformResponse()
/* 11:   */  {
/* 12:12 */    this.name = "InformResponse";
/* 13:   */  }
/* 14:   */  
/* 15:   */  public InformResponse(String _id, int me) {
/* 16:16 */    this.name = "InformResponse";
/* 17:17 */    this.id = _id;
/* 18:18 */    this.maxEnvelopes = me;
/* 19:   */  }
/* 20:   */  
/* 21:   */  protected void createBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws javax.xml.soap.SOAPException {
/* 22:22 */    body.addChildElement(spf.createName("MaxEnvelopes")).setValue(String.valueOf(this.maxEnvelopes));
/* 23:   */  }
/* 24:   */  
/* 25:   */  protected void parseBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf)
/* 26:   */    throws javax.xml.soap.SOAPException
/* 27:   */  {}
/* 28:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.InformResponse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */