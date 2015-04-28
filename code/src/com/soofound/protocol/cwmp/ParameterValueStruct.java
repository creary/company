/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */public class ParameterValueStruct implements Comparable<ParameterValueStruct>
/*  4:   */{
/*  5:   */  private String name;
/*  6:   */  private String value;
/*  7:   */  private String type;
/*  8:   */  
/*  9:   */  public ParameterValueStruct() {}
/* 10:   */  
/* 11:   */  public ParameterValueStruct(String name, String value) {
/* 12:12 */    this.name = name;
/* 13:13 */    this.value = value;
/* 14:14 */    this.type = "xsd:string";
/* 15:   */  }
/* 16:   */  
/* 17:   */  public ParameterValueStruct(String name, String value, String type) {
/* 18:18 */    this.name = name;
/* 19:19 */    this.value = value;
/* 20:20 */    if (type.startsWith("xsd:")) {
/* 21:21 */      this.type = type;
/* 22:   */    } else
/* 23:23 */      this.type = ("xsd:" + type);
/* 24:   */  }
/* 25:   */  
/* 26:   */  public String getName() {
/* 27:27 */    return this.name;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void setName(String name) {
/* 31:31 */    this.name = name;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public String getValue() {
/* 35:35 */    return this.value;
/* 36:   */  }
/* 37:   */  
/* 38:   */  public void setValue(String value) {
/* 39:39 */    this.value = value;
/* 40:   */  }
/* 41:   */  
/* 42:42 */  public String getType() { return this.type; }
/* 43:   */  
/* 44:   */  public void setType(String type)
/* 45:   */  {
/* 46:46 */    this.type = type;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public int compareTo(ParameterValueStruct o) {
/* 50:50 */    if ((this.name.compareTo(o.getName()) == 0) && (this.value.compareTo(o.getValue()) == 0) && (this.type.compareTo(o.getType()) == 0)) {
/* 51:51 */      return 0;
/* 52:   */    }
/* 53:53 */    return 1;
/* 54:   */  }
/* 55:   */  
/* 57:   */  public boolean equals(Object obj)
/* 58:   */  {
/* 59:59 */    if (((obj instanceof ParameterValueStruct)) && 
/* 60:60 */      (((ParameterValueStruct)obj).getName() != null) && 
/* 61:61 */      (((ParameterValueStruct)obj).getValue() != null) && 
/* 62:62 */      (this.name != null) && 
/* 63:63 */      (this.value != null) && 
/* 64:64 */      (this.name.compareTo(((ParameterValueStruct)obj).getName()) == 0) && 
/* 65:65 */      (this.value.compareTo(((ParameterValueStruct)obj).getValue()) == 0))
/* 66:   */    {
/* 67:67 */      return true;
/* 68:   */    }
/* 69:69 */    return false;
/* 70:   */  }
/* 71:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.ParameterValueStruct
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */