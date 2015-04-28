/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */import java.io.UnsupportedEncodingException;
/*  4:   */import java.net.InetAddress;
/*  5:   */import java.net.UnknownHostException;
/*  6:   */import java.nio.BufferUnderflowException;
/*  7:   */
/*  8:   */public abstract class PortalAttribute
/*  9:   */{
/* 10:   */  public abstract int getType();
/* 11:   */  
/* 12:   */  public abstract byte[] getBytes();
/* 13:   */  
/* 14:   */  public static byte[] encodeString(int type, byte[] b)
/* 15:   */  {
/* 16:16 */    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(2 + b.length);
/* 17:17 */    bb.put((byte)type);
/* 18:18 */    bb.put((byte)(2 + b.length));
/* 19:19 */    bb.put(b);
/* 20:20 */    return bb.array();
/* 21:   */  }
/* 22:   */  
/* 23:   */  public static byte[] encodeInt(int type, int value) {
/* 24:24 */    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(6);
/* 25:25 */    bb.put((byte)type);
/* 26:26 */    bb.put((byte)6);
/* 27:27 */    bb.putInt(value);
/* 28:28 */    return bb.array();
/* 29:   */  }
/* 30:   */  
/* 31:   */  public static byte[] encodeIp(int type, InetAddress ip) {
/* 32:32 */    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(6);
/* 33:33 */    bb.put((byte)type);
/* 34:34 */    bb.put((byte)6);
/* 35:35 */    bb.put(ip.getAddress());
/* 36:36 */    return bb.array();
/* 37:   */  }
/* 38:   */  
/* 39:   */  public static int decodeInt(byte[] b, int offset, int length) {
/* 40:40 */    int len = b[(offset + 1)];
/* 41:41 */    if (len != 6)
/* 42:42 */      throw new IllegalArgumentException("invalid length (should be 6): " + len);
/* 43:43 */    if (len > length) {
/* 44:44 */      throw new BufferUnderflowException();
/* 45:   */    }
/* 46:46 */    java.nio.ByteBuffer bb = java.nio.ByteBuffer.wrap(b);
/* 47:47 */    bb.position(offset + 2);
/* 48:48 */    return bb.getInt();
/* 49:   */  }
/* 50:   */  
/* 51:   */  public static InetAddress decodeIp(byte[] b, int offset, int length) throws UnknownHostException {
/* 52:52 */    int len = b[(offset + 1)];
/* 53:53 */    if (len != 6)
/* 54:54 */      throw new IllegalArgumentException("invalid length (should be 6): " + len);
/* 55:55 */    if (len > length) {
/* 56:56 */      throw new BufferUnderflowException();
/* 57:   */    }
/* 58:58 */    byte[] ip = { b[(2 + offset)], b[(3 + offset)], b[(4 + offset)], b[(5 + offset)] };
/* 59:59 */    return InetAddress.getByAddress(ip);
/* 60:   */  }
/* 61:   */  
/* 62:   */  public static byte[] decodeString(byte[] b, int offset, int length) {
/* 63:63 */    int len = b[(offset + 1)];
/* 64:64 */    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(len - 2);
/* 65:65 */    bb.put(b, offset + 2, len - 2);
/* 66:66 */    return bb.array();
/* 67:   */  }
/* 68:   */  
/* 69:   */  public static String decodeText(byte[] b, int offset, int length) {
/* 70:70 */    int len = b[(offset + 1)];
/* 71:71 */    if (len < 3)
/* 72:72 */      throw new IllegalArgumentException("empty text is not allowed");
/* 73:73 */    if (len > length) {
/* 74:74 */      throw new BufferUnderflowException();
/* 75:   */    }
/* 76:   */    try {
/* 77:77 */      return new String(b, offset + 2, len - 2, "utf-8");
/* 78:   */    }
/* 79:   */    catch (UnsupportedEncodingException e) {}
/* 80:80 */    return null;
/* 81:   */  }
/* 82:   */  
/* 84:   */  public String toString()
/* 85:   */  {
/* 86:86 */    return "type=" + getType() + ", len=" + getBytes().length;
/* 87:   */  }
/* 88:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.PortalAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */