/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import java.util.List;
/*  5:   */import javax.xml.soap.SOAPBodyElement;
/*  6:   */import javax.xml.soap.SOAPException;
/*  7:   */import javax.xml.soap.SOAPFactory;
/*  8:   */
/*  9:   */public class SetParameterAttributes extends Message
/* 10:   */{
/* 11:   */  private static final long serialVersionUID = 201312221938002L;
/* 12:   */  private List<SetParameterAttributesStruct> attrs;
/* 13:   */  
/* 14:   */  public SetParameterAttributes()
/* 15:   */  {
/* 16:16 */    this.name = "SetParameterAttributes";
/* 17:17 */    this.attrs = new ArrayList();
/* 18:   */  }
/* 19:   */  
/* 20:   */  public void AddAttribute(String Name, boolean NotificationChange, int Notification, boolean AccessListChange, String[] AccessList) {
/* 21:21 */    this.attrs.add(new SetParameterAttributesStruct(Name, NotificationChange, Notification, AccessListChange, AccessList));
/* 22:   */  }
/* 23:   */  
/* 24:   */  protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
/* 25:25 */    javax.xml.soap.SOAPElement elm = body.addChildElement(spf.createName("ParameterList"));
/* 26:26 */    elm.setAttribute("SOAP-ENC:arrayType", "cwmp:SetParameterAttributesStruct[" + String.valueOf(this.attrs.size()) + "]");
/* 27:27 */    int c = this.attrs.size();
/* 28:28 */    for (int i = 0; i < c; i++) {
/* 29:29 */      javax.xml.soap.SOAPElement param = elm.addChildElement("SetParameterAttributesStruct");
/* 30:30 */      param.addChildElement("Name").setValue(((SetParameterAttributesStruct)this.attrs.get(i)).Name);
/* 31:31 */      param.addChildElement("NotificationChange").setValue(b2s(((SetParameterAttributesStruct)this.attrs.get(i)).NotificationChange));
/* 32:32 */      param.addChildElement("Notification").setValue(String.valueOf(((SetParameterAttributesStruct)this.attrs.get(i)).Notification));
/* 33:33 */      param.addChildElement("AccessListChange").setValue(b2s(((SetParameterAttributesStruct)this.attrs.get(i)).AccessListChange));
/* 34:   */      
/* 35:35 */      javax.xml.soap.SOAPElement al = param.addChildElement(spf.createName("AccessList"));
/* 36:36 */      String[] acl = ((SetParameterAttributesStruct)this.attrs.get(i)).AccessList;
/* 37:37 */      int ca = acl.length;
/* 38:38 */      al.setAttribute("SOAP-ENC:arrayType", "xsd:string[" + String.valueOf(ca) + "]");
/* 39:39 */      for (int i2 = 0; i2 < ca; i2++) {
/* 40:40 */        javax.xml.soap.SOAPElement acle = al.addChildElement("string");
/* 41:41 */        acle.setValue(acl[i2]);
/* 42:42 */        acle.setAttribute("xsi:type", "xsd:string");
/* 43:   */      }
/* 44:   */    }
/* 45:   */  }
/* 46:   */  
/* 47:   */  protected void parseBody(SOAPBodyElement body, SOAPFactory f)
/* 48:   */    throws SOAPException
/* 49:   */  {}
/* 50:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.SetParameterAttributes
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */