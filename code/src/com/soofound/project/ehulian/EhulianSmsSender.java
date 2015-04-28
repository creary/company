/*  1:   */package com.soofound.project.ehulian;
/*  2:   */
/*  3:   */import com.soofound.portal.custom.SmsSender;
/*  4:   */import java.io.BufferedReader;
/*  5:   */import java.io.InputStreamReader;
/*  6:   */import java.io.OutputStream;
/*  7:   */import java.net.HttpURLConnection;
/*  8:   */import java.net.URL;
/*  9:   */import java.net.URLDecoder;
/* 10:   */import java.util.regex.Matcher;
/* 11:   */import java.util.regex.Pattern;
/* 12:   */
/* 13:   */public class EhulianSmsSender extends SmsSender
/* 14:   */{
/* 15:15 */  private static Pattern pattern = Pattern.compile("<int xmlns=\"http://tempuri.org/\">(.*)</int>");
/* 16:16 */  private static String SMS_GATEWAY_URL = "http://124.173.70.59:8081/SmsAndMms/mt";
/* 17:   */  
/* 18:   */  private String connectURL(String conStr, String sendsmsaddress) {
/* 19:19 */    String recString = null;
/* 20:20 */    URL url = null;
/* 21:21 */    HttpURLConnection urlConn = null;
/* 22:   */    try {
/* 23:23 */      url = new URL(sendsmsaddress);
/* 24:24 */      urlConn = (HttpURLConnection)url.openConnection();
/* 25:25 */      urlConn.setConnectTimeout(30000);
/* 26:26 */      urlConn.setReadTimeout(30000);
/* 27:27 */      urlConn.setRequestMethod("POST");
/* 28:28 */      urlConn.setDoOutput(true);
/* 29:29 */      urlConn.setDoInput(true);
/* 30:   */      
/* 31:31 */      OutputStream out = urlConn.getOutputStream();
/* 32:32 */      out.write(conStr.getBytes("UTF-8"));
/* 33:33 */      out.flush();
/* 34:34 */      out.close();
/* 35:   */      
/* 36:36 */      BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
/* 37:37 */      StringBuffer sb = new StringBuffer();
/* 38:   */      int ch;
/* 39:39 */      while ((ch = rd.read()) > -1) { int ch;
/* 40:40 */        sb.append((char)ch);
/* 41:   */      }
/* 42:42 */      recString = sb.toString().trim();
/* 43:43 */      recString = URLDecoder.decode(recString, "UTF-8");
/* 44:44 */      rd.close();
/* 45:   */    } catch (Exception e) {
/* 46:46 */      recString = "-107";
/* 47:   */    } finally {
/* 48:48 */      if (urlConn != null)
/* 49:49 */        urlConn.disconnect();
/* 50:   */    }
/* 51:51 */    return recString;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public boolean sendSMS(String branchId, String mobile, String content)
/* 55:   */  {
/* 56:56 */    String result = null;
/* 57:57 */    StringBuilder conStr = new StringBuilder(100);
/* 58:   */    try {
/* 59:59 */      conStr.append("Sn=SDK-RW-0024&Pwd=REDwave2014&mobile=").append(mobile).append("&content=").append(content);
/* 60:60 */      result = connectURL(conStr.toString(), SMS_GATEWAY_URL);
/* 61:   */      
/* 62:62 */      doRecord(branchId, mobile, content);
/* 63:   */    } catch (Exception e) {
/* 64:64 */      e.printStackTrace();
/* 65:65 */      result = "-10000";
/* 66:   */    }
/* 67:67 */    String resultok = null;
/* 68:68 */    Matcher matcher = pattern.matcher(result);
/* 69:69 */    while (matcher.find())
/* 70:70 */      resultok = matcher.group(1);
/* 71:71 */    return "0".equals(resultok);
/* 72:   */  }
/* 73:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.project.ehulian.EhulianSmsSender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */