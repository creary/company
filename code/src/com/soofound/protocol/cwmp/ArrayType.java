/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import javax.xml.soap.Name;
/*  4:   */import javax.xml.soap.SOAPBody;
/*  5:   */import javax.xml.soap.SOAPBodyElement;
/*  6:   */import javax.xml.soap.SOAPException;
/*  7:   */import javax.xml.soap.SOAPHeader;
/*  8:   */
/*  9:   */public class ArrayType
/* 10:   */{
/* 11:   */  private String type;
/* 12:   */  
/* 13:   */  public String getType()
/* 14:   */  {
/* 15:15 */    return this.type;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public Name getType(SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException {
/* 19:19 */    int i = this.type.indexOf(':');
/* 20:20 */    if (i == -1) {
/* 21:21 */      return spf.createName(this.type);
/* 22:   */    }
/* 23:23 */    String prefix = this.type.substring(0, i);
/* 24:24 */    SOAPBody b = (SOAPBody)body.getParentElement();
/* 25:25 */    javax.xml.soap.SOAPEnvelope e = (javax.xml.soap.SOAPEnvelope)b.getParentElement();
/* 26:26 */    SOAPHeader h = e.getHeader();
/* 27:27 */    String uri = null;
/* 28:28 */    if (uri == null) {
/* 29:   */      try {
/* 30:30 */        uri = h.lookupNamespaceURI(prefix);
/* 31:   */      }
/* 32:   */      catch (Exception localException) {}
/* 33:33 */      if (uri == null) {
/* 34:   */        try {
/* 35:35 */          uri = e.lookupNamespaceURI(prefix);
/* 36:   */        } catch (Exception localException1) {}
/* 37:   */      }
/* 38:38 */      if (uri == null) {
/* 39:   */        try {
/* 40:40 */          uri = b.lookupNamespaceURI(prefix);
/* 41:   */        } catch (Exception localException2) {}
/* 42:   */      }
/* 43:   */    }
/* 44:44 */    return spf.createName(this.type.substring(i + 1), prefix, uri);
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void setType(String type) {
/* 48:48 */    this.type = type;
/* 49:   */  }
/* 50:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.ArrayType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */