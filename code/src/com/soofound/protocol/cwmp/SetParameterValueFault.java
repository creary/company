/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */
/*  5:   */public class SetParameterValueFault implements Serializable {
/*  6:   */  private static final long serialVersionUID = 201312231757001L;
/*  7:   */  public String parameterName;
/*  8:   */  public String faultCode;
/*  9:   */  public String faultString;
/* 10:   */  
/* 11:   */  public SetParameterValueFault(String parameterName, String faultCode, String faultString) {
/* 12:12 */    this.parameterName = parameterName;
/* 13:13 */    this.faultCode = faultCode;
/* 14:14 */    this.faultString = faultString;
/* 15:   */  }
/* 16:   */  
/* 17:   */  public String toString() {
/* 18:18 */    return "SetParameterValueFault: ParameterName=" + this.parameterName + " FaultCode=" + this.faultCode + " FaultString=" + this.faultString;
/* 19:   */  }
/* 20:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.SetParameterValueFault
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */