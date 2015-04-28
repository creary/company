/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */public class ParameterInfoStruct
/*  4:   */{
/*  5:   */  private String name;
/*  6:   */  private boolean writable;
/*  7:   */  private String type;
/*  8:   */  
/*  9:   */  public ParameterInfoStruct() {}
/* 10:   */  
/* 11:   */  public ParameterInfoStruct(String name, boolean writable)
/* 12:   */  {
/* 13:13 */    this.name = name;
/* 14:14 */    this.writable = writable;
/* 15:15 */    this.type = "xsd:string";
/* 16:   */  }
/* 17:   */  
/* 18:   */  public ParameterInfoStruct(String name, boolean writable, String type) {
/* 19:19 */    this.name = name;
/* 20:20 */    this.writable = writable;
/* 21:21 */    this.type = type;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public String getName() {
/* 25:25 */    return this.name;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void setName(String name) {
/* 29:29 */    this.name = name;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public boolean isWritable() {
/* 33:33 */    return this.writable;
/* 34:   */  }
/* 35:   */  
/* 36:   */  public void setWritable(boolean writable) {
/* 37:37 */    this.writable = writable;
/* 38:   */  }
/* 39:   */  
/* 40:   */  public String getType() {
/* 41:41 */    return this.type;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public void setType(String type) {
/* 45:45 */    this.type = type;
/* 46:   */  }
/* 47:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.ParameterInfoStruct
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */