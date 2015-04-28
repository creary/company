/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */import org.springframework.util.StringUtils;
/*  4:   */
/*  5:   */public class UserNameAttribute extends PortalAttribute {
/*  6:   */  private String username;
/*  7:   */  
/*  8:   */  public UserNameAttribute(String username) {
/*  9: 9 */    if (StringUtils.isEmpty(username))
/* 10:10 */      throw new IllegalArgumentException("username should not be null or empty");
/* 11:11 */    this.username = username;
/* 12:   */  }
/* 13:   */  
/* 14:   */  public UserNameAttribute(byte[] encoded, int offset, int length) {
/* 15:15 */    if (encoded[offset] != getType())
/* 16:16 */      throw new IllegalArgumentException("binary is not user name attribute");
/* 17:17 */    this.username = decodeText(encoded, offset, length);
/* 18:   */  }
/* 19:   */  
/* 20:   */  public String getUserName() {
/* 21:21 */    return this.username;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public int getType()
/* 25:   */  {
/* 26:26 */    return 1;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public byte[] getBytes()
/* 30:   */  {
/* 31:31 */    return encodeString(getType(), this.username.getBytes());
/* 32:   */  }
/* 33:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.UserNameAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */