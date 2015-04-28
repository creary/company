/*  1:   */package com.soofound.protocol.alipay;
/*  2:   */
/*  3:   */import java.io.UnsupportedEncodingException;
/*  4:   */import org.apache.commons.codec.digest.DigestUtils;
/*  5:   */
/* 21:   */public class MD5
/* 22:   */{
/* 23:   */  public static String sign(String text, String key, String input_charset)
/* 24:   */  {
/* 25:25 */    text = text + key;
/* 26:26 */    return DigestUtils.md5Hex(getContentBytes(text, input_charset));
/* 27:   */  }
/* 28:   */  
/* 36:   */  public static boolean verify(String text, String sign, String key, String input_charset)
/* 37:   */  {
/* 38:38 */    text = text + key;
/* 39:39 */    String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
/* 40:40 */    if (mysign.equals(sign)) {
/* 41:41 */      return true;
/* 42:   */    }
/* 43:   */    
/* 44:44 */    return false;
/* 45:   */  }
/* 46:   */  
/* 54:   */  private static byte[] getContentBytes(String content, String charset)
/* 55:   */  {
/* 56:56 */    if ((charset == null) || ("".equals(charset))) {
/* 57:57 */      return content.getBytes();
/* 58:   */    }
/* 59:   */    try {
/* 60:60 */      return content.getBytes(charset);
/* 61:   */    } catch (UnsupportedEncodingException e) {
/* 62:62 */      throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
/* 63:   */    }
/* 64:   */  }
/* 65:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.alipay.MD5
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */