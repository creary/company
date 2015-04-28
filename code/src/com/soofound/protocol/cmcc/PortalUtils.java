/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.nio.ByteBuffer;
/*  5:   */
/*  6:   */public class PortalUtils
/*  7:   */{
/*  8:   */  private static final int MAX_ID = 8388607;
/*  9:   */  private static int packetID;
/* 10:   */  private static short requestID;
/* 11:   */  
/* 12:   */  public static synchronized int getPacketID()
/* 13:   */  {
/* 14:14 */    packetID += 1;
/* 15:15 */    if (packetID == 8388607)
/* 16:16 */      packetID = 0;
/* 17:17 */    return packetID;
/* 18:   */  }
/* 19:   */  
/* 20:   */  public static synchronized short getRequestID() {
/* 21:21 */    requestID = (short)(requestID + 1);
/* 22:22 */    if (requestID == 32767)
/* 23:23 */      requestID = 0;
/* 24:24 */    return requestID;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public static synchronized Object[] getByteShort(int intval) {
/* 28:28 */    ByteBuffer bb = ByteBuffer.allocate(4);
/* 29:29 */    bb.putInt(intval);
/* 30:30 */    byte[] array = bb.array();
/* 31:   */    
/* 32:32 */    Object[] shorts = new Object[2];
/* 33:33 */    shorts[0] = Byte.valueOf(array[1]);
/* 34:34 */    shorts[1] = Short.valueOf(byteToShort(new byte[] { array[2], array[3] }));
/* 35:35 */    return shorts;
/* 36:   */  }
/* 37:   */  
/* 38:   */  private static short byteToShort(byte[] b) {
/* 39:39 */    short s = 0;
/* 40:40 */    short s0 = (short)(b[0] & 0xFF);
/* 41:41 */    short s1 = (short)(b[1] & 0xFF);
/* 42:42 */    s1 = (short)(s1 << 8);
/* 43:43 */    s = (short)(s0 | s1);
/* 44:44 */    return s;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public static synchronized int byteToInt(byte[] b) {
/* 48:48 */    int s = 0;
/* 49:49 */    int s0 = b[0] & 0xFF;
/* 50:50 */    int s1 = b[1] & 0xFF;
/* 51:51 */    int s2 = b[2] & 0xFF;
/* 52:52 */    int s3 = b[3] & 0xFF;
/* 53:53 */    s3 <<= 24;
/* 54:54 */    s2 <<= 16;
/* 55:55 */    s1 <<= 8;
/* 56:56 */    s = s0 | s1 | s2 | s3;
/* 57:57 */    return s;
/* 58:   */  }
/* 59:   */  
/* 60:   */  public static synchronized int byteShortToInt(byte b, short s) {
/* 61:61 */    ByteBuffer bb = ByteBuffer.allocate(4);
/* 62:62 */    bb.putShort(s);
/* 63:63 */    bb.put(b);
/* 64:64 */    bb.put((byte)0);
/* 65:   */    
/* 66:66 */    int intval = byteToInt(bb.array());
/* 67:67 */    return intval;
/* 68:   */  }
/* 69:   */  
/* 70:   */  public static synchronized int shortToInt(short[] s) {
/* 71:71 */    ByteBuffer bb = ByteBuffer.allocate(4);
/* 72:72 */    bb.putShort(s[1]);
/* 73:73 */    bb.putShort(s[0]);
/* 74:   */    
/* 75:75 */    int intval = byteToInt(bb.array());
/* 76:76 */    return intval;
/* 77:   */  }
/* 78:   */  
/* 79:   */  public static void main(String[] args) {
/* 80:80 */    int intval = 1;
/* 81:81 */    Object[] svs = getByteShort(intval);
/* 82:82 */    byte bv = ((Byte)svs[0]).byteValue();
/* 83:83 */    short sv = ((Short)svs[1]).shortValue();
/* 84:84 */    int s1 = byteShortToInt(bv, sv);
/* 85:85 */    System.out.println(s1);
/* 86:   */  }
/* 87:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.PortalUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */