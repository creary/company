/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import java.util.Hashtable;
/*  4:   */import java.util.Iterator;
/*  5:   */import javax.xml.soap.Name;
/*  6:   */import javax.xml.soap.SOAPBodyElement;
/*  7:   */import javax.xml.soap.SOAPElement;
/*  8:   */import javax.xml.soap.SOAPException;
/*  9:   */
/* 10:   */public class GetParameterNamesResponse extends Message
/* 11:   */{
/* 12:   */  private static final long serialVersionUID = 201312222122001L;
/* 13:   */  private Hashtable<String, ParameterInfoStruct> infos;
/* 14:   */  
/* 15:   */  public GetParameterNamesResponse()
/* 16:   */  {
/* 17:17 */    this.name = "GetParameterNamesResponse";
/* 18:18 */    this.infos = new Hashtable();
/* 19:   */  }
/* 20:   */  
/* 21:   */  protected void createBody(SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException
/* 22:   */  {}
/* 23:   */  
/* 24:   */  protected void parseBody(SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException
/* 25:   */  {
/* 26:26 */    Iterator pi = getRequestChildElement(spf, body, "ParameterList").getChildElements(spf.createName("ParameterInfoStruct"));
/* 27:27 */    Name nameKey = spf.createName("Name");
/* 28:28 */    Name nameValue = spf.createName("Writable");
/* 29:29 */    while (pi.hasNext()) {
/* 30:30 */      SOAPElement param = (SOAPElement)pi.next();
/* 31:31 */      String key = getRequestElement(param, nameKey);
/* 32:32 */      String value = getRequestElement(param, nameValue);
/* 33:33 */      if (value == null) value = "";
/* 34:34 */      Iterator ccs = param.getChildElements(nameValue);
/* 35:35 */      String type = ((SOAPElement)ccs.next()).getAttribute("xsi:type");
/* 36:   */      
/* 37:37 */      ParameterInfoStruct pis = new ParameterInfoStruct(key, "1".equals(value), type);
/* 38:38 */      this.infos.put(key, pis);
/* 39:   */    }
/* 40:   */  }
/* 41:   */  
/* 42:   */  public Hashtable<String, ParameterInfoStruct> getInfos() {
/* 43:43 */    return this.infos;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public int getSize() {
/* 47:47 */    return this.infos.size();
/* 48:   */  }
/* 49:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.GetParameterNamesResponse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */