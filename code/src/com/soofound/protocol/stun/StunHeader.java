/*   1:    */package com.soofound.protocol.stun;
/*   2:    */
/*   3:    */import java.net.InetAddress;
/*   4:    */import java.net.InetSocketAddress;
/*   5:    */import java.net.UnknownHostException;
/*   6:    */import org.apache.log4j.Logger;
/*   7:    */
/*   8:    */public class StunHeader
/*   9:    */{
/*  10: 10 */  private static final Logger logger = Logger.getLogger(StunHeader.class.getName());
/*  11:    */  
/*  12:    */  public static final int STUN_HEADER_LENGTH = 20;
/*  13:    */  
/*  14:    */  public static final int TLV_LENGTH = 4;
/*  15:    */  
/*  16:    */  public static final int ERROR_CODE_LENGTH = 4;
/*  17:    */  
/*  18:    */  public static final int BINDING_REQUEST = 1;
/*  19:    */  public static final int BINDING_RESPONSE = 257;
/*  20:    */  public static final int MAPPED_ADDRESS = 1;
/*  21:    */  public static final int MAPPED_ADDRESS_LENGTH = 8;
/*  22:    */  public static final int RESPONSE_ADDRESS = 2;
/*  23:    */  public static final int RESPONSE_ADDRESS_LENGTH = 8;
/*  24:    */  public static final int CHANGE_REQUEST = 3;
/*  25:    */  public static final int CHANGE_REQUEST_LENGTH = 4;
/*  26:    */  public static final int CHANGE_PORT_MASK = 2;
/*  27:    */  public static final int CHANGE_IP_MASK = 4;
/*  28:    */  public static final int CHANGED_ADDRESS = 5;
/*  29:    */  public static final int CHANGED_ADDRESS_LENGTH = 8;
/*  30:    */  public static final int BAD_REQUEST = 400;
/*  31:    */  public static final int GLOBAL_ERROR = 600;
/*  32:    */  
/*  33:    */  public static InetSocketAddress getAddress(byte[] request, int desiredType)
/*  34:    */  {
/*  35: 35 */    InetSocketAddress isa = null;
/*  36: 36 */    int length = request[2] << 8 & 0xFF00 | request[3] & 0xFF;
/*  37: 37 */    int offset = 20;
/*  38:    */    
/*  39: 39 */    logger.debug("Searching for type " + Integer.toHexString(desiredType));
/*  40: 40 */    while (length > 0) {
/*  41: 41 */      int type = request[(offset + 1)];
/*  42: 42 */      int attributeLength = request[(offset + 2)] << 8 & 0xFF00 | request[(offset + 3)] & 0xFF;
/*  43: 43 */      if (type != desiredType) {
/*  44: 44 */        logger.debug("Skipping type " + type);
/*  45: 45 */        offset += 4 + attributeLength;
/*  46: 46 */        length -= 4 + attributeLength;
/*  47:    */      }
/*  48:    */      else {
/*  49: 49 */        if (attributeLength != 8) {
/*  50: 50 */          logger.warn("Invalid Response Address Length " + attributeLength);
/*  51: 51 */          return null;
/*  52:    */        }
/*  53: 53 */        int port = request[(offset + 6)] << 8 & 0xFF00 | request[(offset + 7)] & 0xFF;
/*  54:    */        try
/*  55:    */        {
/*  56: 56 */          byte[] address = new byte[4];
/*  57: 57 */          address[0] = request[(offset + 8)];
/*  58: 58 */          address[1] = request[(offset + 9)];
/*  59: 59 */          address[2] = request[(offset + 10)];
/*  60: 60 */          address[3] = request[(offset + 11)];
/*  61: 61 */          ia = InetAddress.getByAddress(address);
/*  62:    */        } catch (UnknownHostException e) { InetAddress ia;
/*  63: 63 */          logger.warn("Invalid Response Address:  " + e.getMessage());
/*  64: 64 */          return null; }
/*  65:    */        InetAddress ia;
/*  66: 66 */        isa = new InetSocketAddress(ia, port);
/*  67: 67 */        logger.debug("Found Address " + isa);
/*  68: 68 */        break;
/*  69:    */      } }
/*  70: 70 */    return isa;
/*  71:    */  }
/*  72:    */  
/*  75:    */  public static int getChangeRequest(byte[] request)
/*  76:    */  {
/*  77: 77 */    int changeRequest = 0;
/*  78: 78 */    int length = request[2] << 8 & 0xFF00 | request[3] & 0xFF;
/*  79: 79 */    int offset = 20;
/*  80: 80 */    logger.debug("Searching for change request attribute");
/*  81: 81 */    while (length > 0) {
/*  82: 82 */      int type = request[(offset + 1)];
/*  83: 83 */      int attributeLength = request[(offset + 2)] << 8 & 0xFF00 | request[(offset + 3)] & 0xFF;
/*  84: 84 */      if (type != 3) {
/*  85: 85 */        logger.debug("Skipping type " + type);
/*  86: 86 */        offset += 4 + attributeLength;
/*  87: 87 */        length -= 4 + attributeLength;
/*  88:    */      }
/*  89:    */      else {
/*  90: 90 */        if (attributeLength != 4) {
/*  91: 91 */          logger.debug("Invalid Change Request Length " + attributeLength);
/*  92: 92 */          return 0;
/*  93:    */        }
/*  94: 94 */        changeRequest = request[(offset + 7)];
/*  95: 95 */        logger.debug("Found change request " + changeRequest);
/*  96: 96 */        break;
/*  97:    */      } }
/*  98: 98 */    return changeRequest;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public static void dump(String msg, byte[] data, int offset, int length) {
/* 102:102 */    logger.info(msg);
/* 103:103 */    String s = "";
/* 104:104 */    String t = "";
/* 105:105 */    char[] v = new char[1];
/* 106:106 */    for (int i = 0; i < length; i++) {
/* 107:107 */      if (i % 16 == 0) {
/* 108:108 */        if (i > 0)
/* 109:109 */          logger.info(s + "\t" + t);
/* 110:110 */        s = Integer.toHexString(i + offset) + ":  ";
/* 111:111 */        t = "";
/* 112:    */      }
/* 113:113 */      s = s + Integer.toHexString(data[i] & 0xFF) + " ";
/* 114:114 */      v[0] = ((char)(data[(i + offset)] & 0xFF));
/* 115:115 */      if ((v[0] < ' ') || (v[0] > '~')) {
/* 116:116 */        t = t + ".";
/* 117:    */      } else
/* 118:118 */        t = t + String.copyValueOf(v);
/* 119:    */    }
/* 120:120 */    logger.info(s + "\t" + t);
/* 121:    */  }
/* 122:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.stun.StunHeader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */