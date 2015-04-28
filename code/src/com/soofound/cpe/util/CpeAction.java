/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */public enum CpeAction {
/*  4: 4 */  Reboot(1, 0), 
/*  5: 5 */  GetParameterValues(2, 1), 
/*  6: 6 */  GetParameterNames(3, 0), 
/*  7: 7 */  SetParameterValues(4, 2), 
/*  8: 8 */  FactoryReset(5, 0), 
/*  9: 9 */  UpdateFirmware(6, 3), 
/* 10:10 */  UpdateConfiguration(7, 3), 
/* 11:11 */  Debug(8, 4);
/* 12:   */  
/* 13:   */  private int code;
/* 14:   */  private int paramType;
/* 15:   */  
/* 16:   */  private CpeAction(int code, int paramType) {
/* 17:17 */    this.code = code;
/* 18:18 */    this.paramType = paramType;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public int getCode() {
/* 22:22 */    return this.code;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void setCode(int code) {
/* 26:26 */    this.code = code;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public int getParamType() {
/* 30:30 */    return this.paramType;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public void setParamType(int paramType) {
/* 34:34 */    this.paramType = paramType;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public String getName() {
/* 38:38 */    return toString();
/* 39:   */  }
/* 40:   */  
/* 41:   */  public static CpeAction getAction(int code) {
/* 42:42 */    for (CpeAction ca : ) {
/* 43:43 */      if (ca.getCode() == code)
/* 44:44 */        return ca;
/* 45:   */    }
/* 46:46 */    throw new IllegalArgumentException("CpeAction doesn't exist,Code=" + code);
/* 47:   */  }
/* 48:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.CpeAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */