/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.Iterator;
/*  5:   */import javax.xml.soap.Name;
/*  6:   */import javax.xml.soap.SOAPBodyElement;
/*  7:   */import javax.xml.soap.SOAPElement;
/*  8:   */import javax.xml.soap.SOAPException;
/*  9:   */
/* 10:   */public class GetParameterValuesResponse extends Message
/* 11:   */{
/* 12:   */  private static final long serialVersionUID = 201312222127001L;
/* 13:   */  private java.util.Hashtable<String, ParameterValueStruct> values;
/* 14:   */  
/* 15:   */  public GetParameterValuesResponse()
/* 16:   */  {
/* 17:17 */    this.name = "GetParameterValuesResponse";
/* 18:18 */    this.values = new java.util.Hashtable();
/* 19:   */  }
/* 20:   */  
/* 21:   */  protected void createBody(SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException
/* 22:   */  {}
/* 23:   */  
/* 24:   */  protected void parseBody(SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException
/* 25:   */  {
/* 26:26 */    Iterator pi = getRequestChildElement(spf, body, "ParameterList").getChildElements(spf.createName("ParameterValueStruct"));
/* 27:27 */    Name nameKey = spf.createName("Name");
/* 28:28 */    Name nameValue = spf.createName("Value");
/* 29:29 */    while (pi.hasNext()) {
/* 30:   */      try {
/* 31:31 */        SOAPElement param = (SOAPElement)pi.next();
/* 32:32 */        String key = getRequestElement(param, nameKey);
/* 33:33 */        String value = getRequestElement(param, nameValue);
/* 34:34 */        if (value == null) value = "";
/* 35:35 */        Iterator ccs = param.getChildElements(nameValue);
/* 36:36 */        String type = ((SOAPElement)ccs.next()).getAttribute("xsi:type");
/* 37:37 */        ParameterValueStruct pvs = new ParameterValueStruct(key, value, type);
/* 38:38 */        this.values.put(key, pvs);
/* 39:   */      } catch (Exception e) {
/* 40:40 */        System.out.println("parseBody error:" + e.getMessage());
/* 41:   */      }
/* 42:   */    }
/* 43:   */  }
/* 44:   */  
/* 45:   */  public ParameterValueStruct getParam(String name) {
/* 46:46 */    return (ParameterValueStruct)this.values.get(name);
/* 47:   */  }
/* 48:   */  
/* 49:   */  public String getParamValue(String name) {
/* 50:50 */    ParameterValueStruct pvs = (ParameterValueStruct)this.values.get(name);
/* 51:51 */    if (pvs == null)
/* 52:52 */      return "";
/* 53:53 */    return pvs.getValue();
/* 54:   */  }
/* 55:   */  
/* 56:   */  public String toString() {
/* 57:57 */    StringBuffer b = new StringBuffer(1024);
/* 58:58 */    for (String k : this.values.keySet()) {
/* 59:59 */      b.append(k).append("=");
/* 60:60 */      b.append(((ParameterValueStruct)this.values.get(k)).getValue()).append("\n");
/* 61:   */    }
/* 62:62 */    return b.toString();
/* 63:   */  }
/* 64:   */  
/* 65:   */  public java.util.Hashtable<String, ParameterValueStruct> getValues() {
/* 66:66 */    return this.values;
/* 67:   */  }
/* 68:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.GetParameterValuesResponse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */