/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import java.util.List;
/*  5:   */import javax.xml.soap.SOAPBodyElement;
/*  6:   */import javax.xml.soap.SOAPElement;
/*  7:   */import javax.xml.soap.SOAPException;
/*  8:   */import javax.xml.soap.SOAPFactory;
/*  9:   */
/* 10:   */public class SetParameterValues extends Message
/* 11:   */{
/* 12:   */  private static final long serialVersionUID = 201312221941001L;
/* 13:   */  private List<String> names;
/* 14:   */  private List<String> values;
/* 15:   */  private List<String> types;
/* 16:16 */  public String key = "unsetCommandKey";
/* 17:   */  
/* 18:   */  public SetParameterValues() {
/* 19:19 */    this.name = "SetParameterValues";
/* 20:20 */    this.names = new ArrayList();
/* 21:21 */    this.values = new ArrayList();
/* 22:22 */    this.types = new ArrayList();
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void Merge(SetParameterValues other) {
/* 26:26 */    for (int i = 0; i < other.names.size(); i++) {
/* 27:27 */      this.names.add((String)other.names.get(i));
/* 28:28 */      this.values.add((String)other.values.get(i));
/* 29:29 */      this.types.add((String)other.types.get(i));
/* 30:   */    }
/* 31:   */  }
/* 32:   */  
/* 33:   */  protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
/* 34:34 */    SOAPElement elm = body.addChildElement(spf.createName("ParameterList"));
/* 35:35 */    elm.setAttribute("SOAP-ENC:arrayType", "cwmp:ParameterValueStruct[" + String.valueOf(this.names.size()) + "]");
/* 36:36 */    for (int i = 0; i < this.names.size(); i++) {
/* 37:37 */      SOAPElement param = elm.addChildElement("ParameterValueStruct");
/* 38:38 */      param.addChildElement("Name").setValue((String)this.names.get(i));
/* 39:39 */      SOAPElement v = param.addChildElement("Value");
/* 40:40 */      v.setValue((String)this.values.get(i));
/* 41:41 */      v.setAttribute("xsi:type", (String)this.types.get(i));
/* 42:   */    }
/* 43:43 */    body.addChildElement("ParameterKey").setValue(this.key);
/* 44:   */  }
/* 45:   */  
/* 46:   */  protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException
/* 47:   */  {}
/* 48:   */  
/* 49:   */  public void addValue(String name, String value) {
/* 50:50 */    addValue(name, value, "xsd:string");
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void addValue(String name, Integer value) {
/* 54:54 */    addValue(name, value.toString(), "xsd:unsignedInt");
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void addValue(String name, String value, String type) {
/* 58:58 */    this.names.add(name);
/* 59:59 */    this.values.add(value);
/* 60:60 */    this.types.add(type);
/* 61:   */  }
/* 62:   */  
/* 63:   */  public boolean isEmpty() {
/* 64:64 */    return this.names.isEmpty();
/* 65:   */  }
/* 66:   */  
/* 67:   */  public String toString() {
/* 68:68 */    StringBuilder s = new StringBuilder();
/* 69:69 */    s.append("SetParameterValues request:\n");
/* 70:70 */    for (int i = 0; i < this.names.size(); i++) {
/* 71:71 */      s.append('\t');
/* 72:72 */      s.append((String)this.names.get(i));
/* 73:73 */      s.append(" (");
/* 74:74 */      s.append((String)this.types.get(i));
/* 75:75 */      s.append(") '");
/* 76:76 */      s.append((String)this.values.get(i));
/* 77:77 */      s.append("'\n");
/* 78:   */    }
/* 79:79 */    return s.toString();
/* 80:   */  }
/* 81:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.SetParameterValues
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */