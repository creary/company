/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.Iterator;
/*  6:   */import java.util.List;
/*  7:   */import javax.xml.soap.SOAPBodyElement;
/*  8:   */import javax.xml.soap.SOAPException;
/*  9:   */
/* 10:   */public class Fault extends Message
/* 11:   */{
/* 12:   */  private static final long serialVersionUID = 201312221919001L;
/* 13:   */  private String faultCode;
/* 14:   */  private String faultString;
/* 15:   */  private String faultCodeCwmp;
/* 16:   */  private String faultStringCwmp;
/* 17:   */  public List<SetParameterValueFault> setParameterValuesFaults;
/* 18:   */  
/* 19:   */  public Fault()
/* 20:   */  {
/* 21:21 */    this.name = "Fault";
/* 22:   */  }
/* 23:   */  
/* 24:   */  public Fault(String faultCode, String faultString, String id) {
/* 25:25 */    this.name = "Fault";
/* 26:26 */    this.faultCodeCwmp = faultCode;
/* 27:27 */    this.faultCode = faultCode;
/* 28:28 */    this.faultStringCwmp = faultString;
/* 29:29 */    this.faultString = faultString;
/* 30:30 */    this.id = id;
/* 31:   */  }
/* 32:   */  
/* 33:   */  protected void createBody(SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException
/* 34:   */  {}
/* 35:   */  
/* 36:   */  protected void parseBody(SOAPBodyElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException {
/* 37:   */    try {
/* 38:38 */      this.faultCode = getRequestElement(spf, body, "faultcode");
/* 39:   */    }
/* 40:   */    catch (Exception localException) {}
/* 41:   */    try {
/* 42:42 */      this.faultString = getRequestElement(spf, body, "faultstring");
/* 43:   */    }
/* 44:   */    catch (Exception localException1) {}
/* 45:45 */    javax.xml.soap.SOAPElement detail = null;
/* 46:   */    try {
/* 47:47 */      detail = getRequestChildElement(spf, body, "detail");
/* 48:   */    }
/* 49:   */    catch (Exception localException2) {}
/* 50:50 */    if (detail == null)
/* 51:51 */      detail = body;
/* 52:52 */    javax.xml.soap.SOAPElement cwmpfault = getRequestChildElement2(spf, detail, "Fault");
/* 53:53 */    this.faultCodeCwmp = getRequestElement(spf, cwmpfault, "FaultCode");
/* 54:54 */    this.faultStringCwmp = getRequestElement(spf, cwmpfault, "FaultString");
/* 55:55 */    Iterator i = cwmpfault.getChildElements(spf.createName("SetParameterValuesFault"));
/* 56:56 */    if (i.hasNext())
/* 57:57 */      this.setParameterValuesFaults = new ArrayList();
/* 58:58 */    SetParameterValueFault vf; while (i.hasNext()) {
/* 59:59 */      javax.xml.soap.SOAPElement f = (javax.xml.soap.SOAPElement)i.next();
/* 60:60 */      vf = new SetParameterValueFault(getRequestElement(spf, f, "ParameterName"), getRequestElement(spf, f, "FaultCode"), getRequestElement(spf, f, "FaultString"));
/* 61:61 */      this.setParameterValuesFaults.add(vf);
/* 62:   */    }
/* 63:63 */    if (this.setParameterValuesFaults != null) {
/* 64:64 */      for (SetParameterValueFault f : this.setParameterValuesFaults)
/* 65:65 */        System.out.println("n=" + f.parameterName + " c=" + f.faultCode + " s=" + f.faultString);
/* 66:   */    }
/* 67:   */  }
/* 68:   */  
/* 69:   */  public String toString() {
/* 70:70 */    return "FAULT: code=" + this.faultCode + " msg=" + this.faultString + " ccode=" + this.faultCodeCwmp + " cmsg=" + this.faultStringCwmp;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public String getFaultString() {
/* 74:74 */    return this.faultString;
/* 75:   */  }
/* 76:   */  
/* 77:   */  public String getFaultStringCwmp() {
/* 78:78 */    return this.faultStringCwmp;
/* 79:   */  }
/* 80:   */  
/* 81:   */  public String getFaultCode() {
/* 82:82 */    return this.faultCode;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public String getCwmpFaultCode() {
/* 86:86 */    return this.faultCodeCwmp;
/* 87:   */  }
/* 88:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.Fault
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */