/*   1:    */package com.soofound.protocol.alipay;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileWriter;
/*   5:    */import java.io.IOException;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.Collections;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.List;
/*  10:    */import java.util.Map;
/*  11:    */import org.apache.commons.codec.digest.DigestUtils;
/*  12:    */import org.apache.commons.httpclient.methods.multipart.FilePartSource;
/*  13:    */import org.apache.commons.httpclient.methods.multipart.PartSource;
/*  14:    */
/*  28:    */public class AlipayCore
/*  29:    */{
/*  30:    */  public static Map<String, String> paraFilter(Map<String, String> sArray)
/*  31:    */  {
/*  32: 32 */    Map<String, String> result = new HashMap();
/*  33: 33 */    if ((sArray == null) || (sArray.size() <= 0)) {
/*  34: 34 */      return result;
/*  35:    */    }
/*  36: 36 */    for (String key : sArray.keySet()) {
/*  37: 37 */      String value = (String)sArray.get(key);
/*  38: 38 */      if ((value != null) && (!value.equals("")) && (!key.equalsIgnoreCase("sign")) && 
/*  39: 39 */        (!key.equalsIgnoreCase("sign_type")))
/*  40:    */      {
/*  42: 42 */        result.put(key, value); }
/*  43:    */    }
/*  44: 44 */    return result;
/*  45:    */  }
/*  46:    */  
/*  51:    */  public static String createLinkString(Map<String, String> params)
/*  52:    */  {
/*  53: 53 */    List<String> keys = new ArrayList(params.keySet());
/*  54: 54 */    Collections.sort(keys);
/*  55: 55 */    String prestr = "";
/*  56: 56 */    for (int i = 0; i < keys.size(); i++) {
/*  57: 57 */      String key = (String)keys.get(i);
/*  58: 58 */      String value = (String)params.get(key);
/*  59: 59 */      if (i == keys.size() - 1) {
/*  60: 60 */        prestr = prestr + key + "=" + value;
/*  61:    */      } else {
/*  62: 62 */        prestr = prestr + key + "=" + value + "&";
/*  63:    */      }
/*  64:    */    }
/*  65:    */    
/*  66: 66 */    return prestr;
/*  67:    */  }
/*  68:    */  
/*  72:    */  public static void logResult(String sWord)
/*  73:    */  {
/*  74: 74 */    FileWriter writer = null;
/*  75:    */    try {
/*  76: 76 */      writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
/*  77: 77 */      writer.write(sWord);
/*  78:    */    } catch (Exception e) {
/*  79: 79 */      e.printStackTrace();
/*  80:    */      
/*  81: 81 */      if (writer != null) {
/*  82:    */        try {
/*  83: 83 */          writer.close();
/*  84:    */        } catch (IOException e) {
/*  85: 85 */          e.printStackTrace();
/*  86:    */        }
/*  87:    */      }
/*  88:    */    }
/*  89:    */    finally
/*  90:    */    {
/*  91: 81 */      if (writer != null) {
/*  92:    */        try {
/*  93: 83 */          writer.close();
/*  94:    */        } catch (IOException e) {
/*  95: 85 */          e.printStackTrace();
/*  96:    */        }
/*  97:    */      }
/*  98:    */    }
/*  99:    */  }
/* 100:    */  
/* 105:    */  public static String getAbstract(String strFilePath, String file_digest_type)
/* 106:    */    throws IOException
/* 107:    */  {
/* 108: 98 */    PartSource file = new FilePartSource(new File(strFilePath));
/* 109: 99 */    if (file_digest_type.equals("MD5")) {
/* 110:100 */      return DigestUtils.md5Hex(file.createInputStream());
/* 111:    */    }
/* 112:102 */    if (file_digest_type.equals("SHA")) {
/* 113:103 */      return DigestUtils.sha256Hex(file.createInputStream());
/* 114:    */    }
/* 115:    */    
/* 116:106 */    return "";
/* 117:    */  }
/* 118:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.alipay.AlipayCore
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */