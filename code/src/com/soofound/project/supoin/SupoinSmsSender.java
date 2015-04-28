/*  1:   */package com.soofound.project.supoin;
/*  2:   */
/*  3:   */import com.soofound.portal.custom.SmsSender;
/*  4:   */import java.io.BufferedReader;
/*  5:   */import java.io.InputStreamReader;
/*  6:   */import java.io.OutputStream;
/*  7:   */import java.net.HttpURLConnection;
/*  8:   */import java.net.URL;
/*  9:   */
/* 10:   */public class SupoinSmsSender extends SmsSender
/* 11:   */{
/* 12:12 */  private static String SMS_GATEWAY_URL = "http://gateway.iems.net.cn/GsmsHttp";
/* 13:   */  
/* 14:   */  private String connectURL(String conStr, String sendsmsaddress) {
/* 15:15 */    String recString = null;
/* 16:16 */    URL url = null;
/* 17:17 */    HttpURLConnection urlConn = null;
/* 18:   */    try {
/* 19:19 */      url = new URL(sendsmsaddress);
/* 20:20 */      urlConn = (HttpURLConnection)url.openConnection();
/* 21:21 */      urlConn.setConnectTimeout(30000);
/* 22:22 */      urlConn.setReadTimeout(30000);
/* 23:23 */      urlConn.setRequestMethod("POST");
/* 24:24 */      urlConn.setDoOutput(true);
/* 25:25 */      urlConn.setDoInput(true);
/* 26:   */      
/* 27:27 */      OutputStream out = urlConn.getOutputStream();
/* 28:28 */      out.write(conStr.getBytes("GB2312"));
/* 29:29 */      out.flush();
/* 30:30 */      out.close();
/* 31:   */      
/* 32:32 */      BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GB2312"));
/* 33:33 */      StringBuffer sb = new StringBuffer();
/* 34:   */      int ch;
/* 35:35 */      while ((ch = rd.read()) > -1) { int ch;
/* 36:36 */        sb.append((char)ch);
/* 37:   */      }
/* 38:38 */      recString = sb.toString();
/* 39:39 */      rd.close();
/* 40:   */    } catch (Exception e) {
/* 41:41 */      recString = "-107";
/* 42:   */    } finally {
/* 43:43 */      if (urlConn != null)
/* 44:44 */        urlConn.disconnect();
/* 45:   */    }
/* 46:46 */    return recString;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public boolean sendSMS(String branchId, String mobile, String content)
/* 50:   */  {
/* 51:51 */    String result = null;
/* 52:52 */    StringBuilder conStr = new StringBuilder(100);
/* 53:   */    try {
/* 54:54 */      conStr.append("username=68599:admin&password=08585461&to=").append(mobile).append("&content=").append(content);
/* 55:55 */      result = connectURL(conStr.toString(), SMS_GATEWAY_URL);
/* 56:   */    } catch (Exception e) {
/* 57:57 */      e.printStackTrace();
/* 58:58 */      result = "ERROR:UNKNOWN";
/* 59:59 */      return false;
/* 60:   */    }
/* 61:61 */    boolean ok = result.startsWith("OK:");
/* 62:62 */    if (ok)
/* 63:63 */      doRecord(branchId, mobile, content);
/* 64:64 */    return ok;
/* 65:   */  }
/* 66:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.project.supoin.SupoinSmsSender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */