/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */import java.nio.BufferUnderflowException;
/*  4:   */
/*  5:   */public class ChapPasswordAttribute extends PortalAttribute
/*  6:   */{
/*  7:   */  private byte chapIdent;
/*  8:   */  private byte[] response;
/*  9:   */  
/* 10:   */  public ChapPasswordAttribute(byte chapIdent, byte[] response) {
/* 11:11 */    if (response.length != 16)
/* 12:12 */      throw new IllegalArgumentException("CHAP response length should be 16");
/* 13:13 */    this.chapIdent = chapIdent;
/* 14:14 */    this.response = response;
/* 15:   */  }
/* 16:   */  
/* 17:   */  public ChapPasswordAttribute(byte[] encoded, int offset, int length) {
/* 18:18 */    java.nio.ByteBuffer bb = java.nio.ByteBuffer.wrap(encoded);
/* 19:19 */    bb.position(offset);
/* 20:   */    
/* 21:21 */    int type = bb.get();
/* 22:22 */    if (type != getType()) {
/* 23:23 */      throw new IllegalArgumentException("binary is not chap password attribute");
/* 24:   */    }
/* 25:25 */    int len = bb.get();
/* 26:26 */    if (len > bb.remaining()) {
/* 27:27 */      throw new BufferUnderflowException();
/* 28:   */    }
/* 29:29 */    this.chapIdent = bb.get();
/* 30:   */    
/* 31:31 */    byte[] response = new byte[16];
/* 32:32 */    bb.get(response);
/* 33:33 */    this.response = response;
/* 34:   */  }
/* 35:   */  
/* 36:   */  public int getType()
/* 37:   */  {
/* 38:38 */    return 4;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public byte getChapIdent() {
/* 42:42 */    return this.chapIdent;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public byte[] getChapResponse() {
/* 46:46 */    return this.response;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public byte[] getBytes()
/* 50:   */  {
/* 51:51 */    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(19);
/* 52:52 */    bb.put((byte)getType());
/* 53:53 */    bb.put((byte)19);
/* 54:54 */    bb.put(this.chapIdent);
/* 55:55 */    bb.put(this.response);
/* 56:56 */    return bb.array();
/* 57:   */  }
/* 58:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.ChapPasswordAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */