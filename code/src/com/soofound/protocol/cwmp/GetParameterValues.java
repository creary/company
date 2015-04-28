/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import java.util.Map;
/*  4:   */import java.util.Set;
/*  5:   */import javax.xml.soap.SOAPBodyElement;
/*  6:   */import javax.xml.soap.SOAPException;
/*  7:   */
/*  8:   */public class GetParameterValues extends Message
/*  9:   */{
/* 10:   */  private static final long serialVersionUID = 201312222125001L;
/* 11:   */  public String[] parameterNames;
/* 12:   */  
/* 13:   */  public GetParameterValues()
/* 14:   */  {
/* 15:15 */    this.name = "GetParameterValues";
/* 16:   */  }
/* 17:   */  
/* 18:   */  public GetParameterValues(String[] parameterNames) {
/* 19:19 */    this.name = "GetParameterValues";
/* 20:20 */    this.parameterNames = parameterNames;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public GetParameterValues(Map<String, String> parameters) {
/* 24:24 */    this.name = "GetParameterValues";
/* 25:25 */    parameters.keySet().toArray(this.parameterNames);
/* 26:   */  }
/* 27:   */  
/* 28:   */  public GetParameterValues(String paramName) {
/* 29:29 */    this.name = "GetParameterValues";
/* 30:30 */    this.parameterNames = new String[1];
/* 31:31 */    this.parameterNames[0] = paramName;
/* 32:   */  }
/* 33:   */  
/* 34:   */  protected void createBody(SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException {
/* 35:35 */    javax.xml.soap.SOAPElement elm = body.addChildElement(spf.createName("ParameterNames"));
/* 36:36 */    elm.setAttribute("SOAP-ENC:arrayType", "xsd:string[" + String.valueOf(this.parameterNames.length) + "]");
/* 37:37 */    for (int i = 0; i < this.parameterNames.length; i++) {
/* 38:38 */      javax.xml.soap.SOAPElement s = elm.addChildElement("string");
/* 39:39 */      s.setValue(this.parameterNames[i]);
/* 40:   */    }
/* 41:   */  }
/* 42:   */  
/* 43:   */  protected void parseBody(SOAPBodyElement body, javax.xml.soap.SOAPFactory f)
/* 44:   */    throws SOAPException
/* 45:   */  {}
/* 46:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.GetParameterValues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */