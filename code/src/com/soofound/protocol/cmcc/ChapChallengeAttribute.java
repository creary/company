/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */public class ChapChallengeAttribute extends PortalAttribute {
/*  4:   */  private byte[] challenge;
/*  5:   */  
/*  6:   */  public ChapChallengeAttribute(byte[] challenge) {
/*  7: 7 */    if (challenge.length != 16)
/*  8: 8 */      throw new IllegalArgumentException("CHAP challenge length should be 16");
/*  9: 9 */    this.challenge = challenge;
/* 10:   */  }
/* 11:   */  
/* 12:   */  public ChapChallengeAttribute(byte[] encoded, int offset, int length) {
/* 13:13 */    if (encoded[offset] != getType())
/* 14:14 */      throw new IllegalArgumentException("binary is not chap challenge attribute");
/* 15:15 */    this.challenge = decodeString(encoded, offset, length);
/* 16:   */  }
/* 17:   */  
/* 18:   */  public int getType()
/* 19:   */  {
/* 20:20 */    return 3;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public byte[] getChallenge() {
/* 24:24 */    return this.challenge;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public byte[] getBytes()
/* 28:   */  {
/* 29:29 */    return encodeString(getType(), this.challenge);
/* 30:   */  }
/* 31:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.ChapChallengeAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */