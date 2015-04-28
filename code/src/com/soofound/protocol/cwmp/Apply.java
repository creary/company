/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import javax.xml.soap.SOAPElement;
/*  4:   */import javax.xml.soap.SOAPException;
/*  5:   */
/*  6:   */public class Apply extends Message
/*  7:   */{
/*  8:   */  private static final long serialVersionUID = 201411210958001L;
/*  9: 9 */  public String commandKey = "";
/* 10:   */  
/* 11:   */  public Apply() {
/* 12:12 */    this.name = "Apply";
/* 13:   */  }
/* 14:   */  
/* 15:   */  public Apply(String commandKey) {
/* 16:16 */    this.name = "Apply";
/* 17:17 */    this.commandKey = commandKey;
/* 18:   */  }
/* 19:   */  
/* 20:   */  protected void createBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException {
/* 21:21 */    body.addChildElement("CommandKey").setValue(this.commandKey);
/* 22:   */  }
/* 23:   */  
/* 24:   */  protected void parseBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory f)
/* 25:   */    throws SOAPException
/* 26:   */  {}
/* 27:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.Apply
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */