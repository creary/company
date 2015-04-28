/*   1:    */package com.soofound.framework.license;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileInputStream;
/*   5:    */import java.io.UnsupportedEncodingException;
/*   6:    */import java.nio.MappedByteBuffer;
/*   7:    */import java.nio.channels.FileChannel;
/*   8:    */import java.nio.channels.FileChannel.MapMode;
/*   9:    */import java.security.MessageDigest;
/*  10:    */import javax.crypto.Cipher;
/*  11:    */import javax.crypto.SecretKey;
/*  12:    */import javax.crypto.spec.IvParameterSpec;
/*  13:    */import javax.crypto.spec.SecretKeySpec;
/*  14:    */
/*  20:    */public class DESedeCoder
/*  21:    */{
/*  22:    */  private static final String SOOFOUND = "soofound.com";
/*  23:    */  private static final String Algorithm = "DESede";
/*  24:    */  private static final String Transformation = "DESede/CBC/PKCS5Padding";
/*  25:    */  
/*  26:    */  public static String encryptMode(byte[] keybyte, byte[] ivbyte, byte[] src)
/*  27:    */  {
/*  28:    */    try
/*  29:    */    {
/*  30: 30 */      SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
/*  31:    */      
/*  32: 32 */      IvParameterSpec iv = new IvParameterSpec(ivbyte);
/*  33: 33 */      Cipher c1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
/*  34: 34 */      c1.init(1, deskey, iv);
/*  35: 35 */      return byte2hex(c1.doFinal(src));
/*  36:    */    } catch (Exception e1) {
/*  37: 37 */      e1.printStackTrace();
/*  38:    */    }
/*  39: 39 */    return null;
/*  40:    */  }
/*  41:    */  
/*  42:    */  public static String byte2hex(byte[] b)
/*  43:    */  {
/*  44: 44 */    String hs = "";
/*  45: 45 */    String stmp = "";
/*  46: 46 */    for (int n = 0; n < b.length; n++)
/*  47:    */    {
/*  48: 48 */      stmp = Integer.toHexString(b[n] & 0xFF);
/*  49: 49 */      if (stmp.length() == 1) {
/*  50: 50 */        hs = hs + "0" + stmp;
/*  51:    */      } else
/*  52: 52 */        hs = hs + stmp;
/*  53:    */    }
/*  54: 54 */    return hs;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public static String encryptMode(String key, String iv, String src) {
/*  58:    */    try {
/*  59: 59 */      return encryptMode(getKeyByte(key), getIVByte(iv), src.getBytes());
/*  60:    */    } catch (Exception e1) {
/*  61: 61 */      e1.printStackTrace();
/*  62:    */    }
/*  63: 63 */    return null;
/*  64:    */  }
/*  65:    */  
/*  66:    */  private static byte[] getKeyByte(String key) throws UnsupportedEncodingException
/*  67:    */  {
/*  68: 68 */    byte[] data = key.getBytes();
/*  69: 69 */    int len = data.length;
/*  70: 70 */    byte[] newdata = new byte[24];
/*  71: 71 */    System.arraycopy(data, 0, newdata, 0, len > 24 ? 24 : len);
/*  72: 72 */    return newdata;
/*  73:    */  }
/*  74:    */  
/*  75:    */  private static byte[] getIVByte(String iv) throws UnsupportedEncodingException {
/*  76: 76 */    byte[] data = iv.getBytes();
/*  77: 77 */    int len = data.length;
/*  78: 78 */    byte[] newdata = new byte[8];
/*  79: 79 */    System.arraycopy(data, 0, newdata, 0, len > 8 ? 8 : len);
/*  80: 80 */    return newdata;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public static String decryptMode(byte[] keybyte, byte[] ivbyte, byte[] src)
/*  84:    */  {
/*  85:    */    try {
/*  86: 86 */      SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
/*  87:    */      
/*  88: 88 */      IvParameterSpec iv = new IvParameterSpec(ivbyte);
/*  89: 89 */      Cipher c1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
/*  90: 90 */      c1.init(2, deskey, iv);
/*  91: 91 */      byte[] data = c1.doFinal(src);
/*  92: 92 */      return new String(data);
/*  93:    */    } catch (Exception e1) {
/*  94: 94 */      e1.printStackTrace();
/*  95:    */    }
/*  96: 96 */    return null;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public static byte[] hex2byte(byte[] b) {
/* 100:100 */    if (b.length % 2 != 0)
/* 101:101 */      throw new IllegalArgumentException("长度不是偶数");
/* 102:102 */    byte[] b2 = new byte[b.length / 2];
/* 103:103 */    for (int n = 0; n < b.length; n += 2) {
/* 104:104 */      String item = new String(b, n, 2);
/* 105:    */      
/* 106:106 */      b2[(n / 2)] = ((byte)Integer.parseInt(item, 16));
/* 107:    */    }
/* 108:108 */    return b2;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public static String getIV(String str) {
/* 112:112 */    byte[] strs1 = encodeBySHA1(str + "soofound.com").getBytes();
/* 113:113 */    byte[] strs2 = new byte[8];
/* 114:114 */    for (int i = 0; i < 8; i++)
/* 115:115 */      strs2[i] = strs1[i];
/* 116:116 */    return new String(strs2);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public static String encodeBySHA1(String str) {
/* 120:    */    try {
/* 121:121 */      MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
/* 122:122 */      messageDigest.update(str.getBytes());
/* 123:123 */      String result = byte2hex(messageDigest.digest());
/* 124:124 */      return result.substring(0, 24);
/* 125:    */    } catch (Exception e) {
/* 126:126 */      throw new RuntimeException(e);
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 130:    */  public static String doEncode(String machineID, String message) {
/* 131:131 */    String key = encodeBySHA1(machineID);
/* 132:132 */    String iv = getIV(machineID);
/* 133:133 */    return encryptMode(key, iv, message);
/* 134:    */  }
/* 135:    */  
/* 136:    */  public static String doDecode(String machineID, String encodeMessage) {
/* 137:137 */    String key = encodeBySHA1(machineID);
/* 138:138 */    String iv = getIV(machineID);
/* 139:139 */    return decryptMode(key.getBytes(), iv.getBytes(), hex2byte(encodeMessage.getBytes()));
/* 140:    */  }
/* 141:    */  
/* 143:    */  public static String getFileMD5String(String fileName)
/* 144:    */    throws Exception
/* 145:    */  {
/* 146:146 */    File f = new File(fileName);
/* 147:147 */    return getFileMD5String(f);
/* 148:    */  }
/* 149:    */  
/* 150:    */  public static String getFileMD5String(File file) throws Exception {
/* 151:151 */    FileInputStream in = new FileInputStream(file);
/* 152:152 */    FileChannel ch = in.getChannel();
/* 153:153 */    MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
/* 154:154 */    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
/* 155:155 */    messageDigest.update(byteBuffer);
/* 156:156 */    return byte2hex(messageDigest.digest());
/* 157:    */  }
/* 158:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.license.DESedeCoder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */