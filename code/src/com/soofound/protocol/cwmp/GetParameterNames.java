/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import javax.xml.soap.SOAPException;
/*  4:   */
/*  5:   */public class GetParameterNames extends Message
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 201312222121001L;
/*  8:   */  public String parameterPath;
/*  9:   */  public boolean nextLevel;
/* 10:   */  
/* 11:   */  public GetParameterNames()
/* 12:   */  {
/* 13:13 */    this.name = "GetParameterNames";
/* 14:14 */    this.parameterPath = ".";
/* 15:15 */    this.nextLevel = false;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public GetParameterNames(String parameterPath, boolean nextLevel) {
/* 19:19 */    this.name = "GetParameterNames";
/* 20:20 */    this.parameterPath = parameterPath;
/* 21:21 */    this.nextLevel = nextLevel;
/* 22:   */  }
/* 23:   */  
/* 24:   */  protected void createBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException {
/* 25:25 */    body.addChildElement(spf.createName("ParameterPath")).setValue(this.parameterPath);
/* 26:26 */    body.addChildElement(spf.createName("NextLevel")).setValue(this.nextLevel ? "1" : "0");
/* 27:   */  }
/* 28:   */  
/* 29:   */  protected void parseBody(javax.xml.soap.SOAPBodyElement body, javax.xml.soap.SOAPFactory f)
/* 30:   */    throws SOAPException
/* 31:   */  {}
/* 32:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.GetParameterNames
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */