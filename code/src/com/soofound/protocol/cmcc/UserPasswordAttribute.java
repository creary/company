/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class UserPasswordAttribute extends PortalAttribute {
/*  4:   */  private String password;
/*  5:   */  
/*  6:   */  public UserPasswordAttribute(byte[] encoded, int offset, int length) {
/*  7: 7 */    if (encoded[offset] != getType()) {
/*  8: 8 */      throw new IllegalArgumentException("binary is not user password attribute");
/*  9:   */    }
/* 10:10 */    int len = encoded[(offset + 1)] - 2;
/* 11:11 */    byte[] b = new byte[len];
/* 12:12 */    for (int i = 0; i < len; i++) {
/* 13:13 */      b[i] = encoded[(offset + 2 + i)];
/* 14:   */    }
/* 15:   */    
/* 16:16 */    this.password = new String(b);
/* 17:   */  }
/* 18:   */  
/* 19:   */  public UserPasswordAttribute(String password)
/* 20:   */  {
/* 21:21 */    if (password == null) {
/* 22:22 */      this.password = "";
/* 23:   */    } else {
/* 24:24 */      this.password = password;
/* 25:   */    }
/* 26:   */  }
/* 27:   */  
/* 28:   */  public int getType() {
/* 29:29 */    return 2;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public String getPassword() {
/* 33:33 */    return this.password;
/* 34:   */  }
/* 35:   */  
/* 36:   */  public byte[] getBytes()
/* 37:   */  {
/* 38:38 */    return encodeString(getType(), this.password.getBytes());
/* 39:   */  }
/* 40:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.UserPasswordAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */