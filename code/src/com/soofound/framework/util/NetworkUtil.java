/*  1:   */package com.soofound.framework.util;
/*  2:   */
/*  3:   */import java.util.StringTokenizer;
/*  4:   */import javax.servlet.http.HttpServletRequest;
/*  5:   */
/* 13:   */public final class NetworkUtil
/* 14:   */{
/* 15:   */  public static long ipToLong(String ipAddress)
/* 16:   */  {
/* 17:17 */    int[] ipSegment = parseIp(ipAddress);
/* 18:18 */    if (ipSegment == null) { return 0L;
/* 19:   */    }
/* 20:20 */    long longIp = 0L;
/* 21:21 */    int k = 24;
/* 22:22 */    for (int i = 0; i < ipSegment.length; i++) {
/* 23:23 */      longIp += (ipSegment[i] << k);
/* 24:24 */      k -= 8;
/* 25:   */    }
/* 26:26 */    return longIp;
/* 27:   */  }
/* 28:   */  
/* 31:   */  public static String longToIp(long ip)
/* 32:   */  {
/* 33:33 */    int[] b = new int[4];
/* 34:34 */    b[0] = ((int)(ip >> 24 & 0xFF));
/* 35:35 */    b[1] = ((int)(ip >> 16 & 0xFF));
/* 36:36 */    b[2] = ((int)(ip >> 8 & 0xFF));
/* 37:37 */    b[3] = ((int)(ip & 0xFF));
/* 38:38 */    return b[0] + "." + b[1] + "." + b[2] + "." + b[3];
/* 39:   */  }
/* 40:   */  
/* 43:   */  public static int[] parseIp(String ipAddress)
/* 44:   */  {
/* 45:45 */    if (!checkIp(ipAddress)) return null;
/* 46:46 */    int[] ipSegment = new int[4];
/* 47:   */    
/* 48:48 */    StringTokenizer st = new StringTokenizer(ipAddress, ".");
/* 49:49 */    for (int i = 0; i < 4; i++)
/* 50:50 */      ipSegment[i] = Integer.parseInt(st.nextToken());
/* 51:51 */    return ipSegment;
/* 52:   */  }
/* 53:   */  
/* 56:   */  public static boolean checkIp(String ipAddress)
/* 57:   */  {
/* 58:58 */    boolean isValid = true;
/* 59:   */    try {
/* 60:60 */      StringTokenizer st = new StringTokenizer(ipAddress, ".");
/* 61:61 */      int len = st.countTokens();
/* 62:62 */      if (len != 4) { return false;
/* 63:   */      }
/* 64:64 */      int ipSegment = 0;
/* 65:65 */      for (int i = 0; i < len; i++) {
/* 66:66 */        ipSegment = Integer.parseInt(st.nextToken());
/* 67:67 */        if ((ipSegment < 0) || (ipSegment > 255)) {
/* 68:68 */          isValid = false;
/* 69:69 */          break;
/* 70:   */        }
/* 71:   */      }
/* 72:   */    } catch (Exception e) {
/* 73:73 */      return false;
/* 74:   */    }
/* 75:75 */    return isValid;
/* 76:   */  }
/* 77:   */  
/* 78:   */  public static String getIpAddress(HttpServletRequest request) {
/* 79:79 */    String ip = request.getHeader("x-forwarded-for");
/* 80:80 */    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 81:81 */      ip = request.getHeader("Proxy-Client-IP");
/* 82:   */    }
/* 83:83 */    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 84:84 */      ip = request.getHeader("WL-Proxy-Client-IP");
/* 85:   */    }
/* 86:86 */    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 87:87 */      ip = request.getRemoteAddr();
/* 88:   */    }
/* 89:89 */    return ip;
/* 90:   */  }
/* 91:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.util.NetworkUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */