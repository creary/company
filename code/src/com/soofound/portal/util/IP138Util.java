/*  1:   */package com.soofound.portal.util;
/*  2:   */
/*  3:   */import java.io.BufferedReader;
/*  4:   */import java.io.InputStreamReader;
/*  5:   */import java.net.URL;
/*  6:   */import java.net.URLConnection;
/*  7:   */import org.apache.log4j.Logger;
/*  8:   */
/* 11:   */public class IP138Util
/* 12:   */{
/* 13:   */  public static String getIPLocate(String ipAddress)
/* 14:   */  {
/* 15:15 */    String result = "";
/* 16:16 */    BufferedReader reader = null;
/* 17:   */    try {
/* 18:18 */      URL url = new URL("http://www.ip138.com/ips138.asp?action=2&ip=" + ipAddress);
/* 19:19 */      URLConnection conn = url.openConnection();
/* 20:20 */      conn.connect();
/* 21:21 */      reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GB2312"));
/* 22:22 */      String line = null;
/* 23:23 */      while ((line = reader.readLine()) != null) {
/* 24:24 */        int loc1 = line.indexOf("<ul class=\"ul1\">");
/* 25:25 */        if (loc1 > 0) {
/* 26:26 */          loc1 += 26;
/* 27:27 */          int loc2 = line.indexOf("</li>", loc1);
/* 28:28 */          result = line.substring(loc1, loc2);
/* 29:29 */          break;
/* 30:   */        }
/* 31:   */      }
/* 32:   */    } catch (Exception e) {
/* 33:33 */      Logger.getLogger("").info("=====Connect www.ip138.com failed=====");
/* 34:   */      try {
/* 35:35 */        reader.close();
/* 36:   */      }
/* 37:   */      catch (Exception localException1) {}
/* 38:   */    }
/* 39:39 */    return result;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public static String getMobileLocate(String mobile) {
/* 43:43 */    String result = null;
/* 44:44 */    BufferedReader reader = null;
/* 45:   */    try {
/* 46:46 */      URL url = new URL("http://www.ip138.com:8080/search.asp?action=mobile&mobile=" + mobile);
/* 47:47 */      URLConnection conn = url.openConnection();
/* 48:48 */      conn.connect();
/* 49:49 */      reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GB2312"));
/* 50:50 */      String line = null;
/* 51:51 */      boolean found = false;
/* 52:52 */      int row = 0;
/* 53:   */      
/* 54:54 */      StringBuilder str = new StringBuilder();
/* 55:55 */      while ((line = reader.readLine()) != null) {
/* 56:56 */        if (line.indexOf("卡号归属地") > 0)
/* 57:57 */          found = true;
/* 58:58 */        if (found) {
/* 59:59 */          row++;
/* 60:60 */          str.append(line);
/* 61:   */        }
/* 62:62 */        if (row > 2)
/* 63:   */          break;
/* 64:   */      }
/* 65:65 */      line = str.toString();
/* 66:66 */      int loc1 = line.indexOf("tdc2");
/* 67:67 */      int loc2 = line.indexOf("</TD>", loc1 + 5);
/* 68:68 */      line = line.substring(loc1 + 5, loc2);
/* 69:69 */      if (line.indexOf("-->") > 0) {
/* 70:70 */        result = line.substring(line.indexOf("-->") + 3).replace("&nbsp;", "");
/* 71:   */      } else
/* 72:72 */        result = line.substring(line.indexOf(">") + 1).replace("&nbsp;", "");
/* 73:   */    } catch (Exception e) {
/* 74:74 */      result = "";
/* 75:75 */      e.printStackTrace();
/* 76:   */      try {
/* 77:77 */        reader.close();
/* 78:   */      }
/* 79:   */      catch (Exception localException1) {}
/* 80:   */    }
/* 81:81 */    return result;
/* 82:   */  }
/* 83:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.util.IP138Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */