/*  1:   */package com.soofound.portal.custom;
/*  2:   */
/*  3:   */import java.io.BufferedReader;
/*  4:   */import java.io.InputStreamReader;
/*  5:   */import java.io.OutputStream;
/*  6:   */import java.net.HttpURLConnection;
/*  7:   */import java.net.URL;
/*  8:   */
/*  9:   */public class WebServiceSmsSender
/* 10:   */  extends SmsSender
/* 11:   */{
/* 12:12 */  private static String SMS_GATEWAY_URL = "http://gateway.iems.net.cn/GsmsHttp";
/* 13:   */  
/* 14:   */  private String connectURL(String conStr, String sendsmsaddress) {
/* 15:15 */    String recString = null;
/* 16:16 */    URL url = null;
/* 17:17 */    HttpURLConnection urlConn = null;
/* 18:18 */    BufferedReader br = null;
/* 19:   */    try {
/* 20:20 */      url = new URL(sendsmsaddress);
/* 21:21 */      urlConn = (HttpURLConnection)url.openConnection();
/* 22:22 */      urlConn.setConnectTimeout(30000);
/* 23:23 */      urlConn.setReadTimeout(30000);
/* 24:24 */      urlConn.setRequestMethod("POST");
/* 25:25 */      urlConn.setDoOutput(true);
/* 26:26 */      urlConn.setDoInput(true);
/* 27:   */      
/* 28:28 */      OutputStream out = urlConn.getOutputStream();
/* 29:29 */      out.write(conStr.getBytes("GB2312"));
/* 30:30 */      out.flush();
/* 31:31 */      out.close();
/* 32:   */      
/* 33:33 */      br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GB2312"));
/* 34:34 */      StringBuffer sb = new StringBuffer();
/* 35:   */      int ch;
/* 36:36 */      while ((ch = br.read()) > -1) { int ch;
/* 37:37 */        sb.append((char)ch);
/* 38:   */      }
/* 39:39 */      recString = sb.toString();
/* 40:   */    } catch (Exception ex) {
/* 41:41 */      ex.printStackTrace();
/* 42:42 */      recString = "-107";
/* 43:   */      try
/* 44:   */      {
/* 45:45 */        urlConn.disconnect();
/* 46:46 */        br.close();
/* 47:   */      }
/* 48:   */      catch (Exception localException1) {}
/* 49:   */    }
/* 50:   */    finally
/* 51:   */    {
/* 52:   */      try
/* 53:   */      {
/* 54:45 */        urlConn.disconnect();
/* 55:46 */        br.close();
/* 56:   */      }
/* 57:   */      catch (Exception localException2) {}
/* 58:   */    }
/* 59:50 */    return recString;
/* 60:   */  }
/* 61:   */  
/* 62:   */  public boolean sendSMS(String branchId, String mobile, String content)
/* 63:   */  {
/* 64:55 */    String result = null;
/* 65:56 */    StringBuilder conStr = new StringBuilder(100);
/* 66:   */    try {
/* 67:58 */      conStr.append("username=68670:admin&password=97484672&to=").append(mobile).append("&content=").append(content);
/* 68:59 */      result = connectURL(conStr.toString(), SMS_GATEWAY_URL);
/* 69:   */    } catch (Exception e) {
/* 70:61 */      e.printStackTrace();
/* 71:62 */      result = "ERROR:UNKNOWN";
/* 72:63 */      return false;
/* 73:   */    }
/* 74:65 */    boolean ok = result.startsWith("OK:");
/* 75:66 */    if (ok)
/* 76:67 */      doRecord(branchId, mobile, content);
/* 77:68 */    return ok;
/* 78:   */  }
/* 79:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.custom.WebServiceSmsSender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */