/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import javax.xml.soap.SOAPElement;
/*  4:   */
/*  5:   */public class FactoryReset extends Message
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 201401062206001L;
/*  8:   */  
/*  9:   */  public FactoryReset()
/* 10:   */  {
/* 11:11 */    this.name = "FactoryReset";
/* 12:   */  }
/* 13:   */  
/* 14:   */  protected void createBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws javax.xml.soap.SOAPException {
/* 15:15 */    body.addChildElement("CommandKey").setValue(this.name);
/* 16:   */  }
/* 17:   */  
/* 18:   */  protected void parseBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory f)
/* 19:   */    throws javax.xml.soap.SOAPException
/* 20:   */  {}
/* 21:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.FactoryReset
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */