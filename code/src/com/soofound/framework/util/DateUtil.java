/*   1:    */package com.soofound.framework.util;
/*   2:    */
/*   3:    */import java.text.ParseException;
/*   4:    */import java.util.Calendar;
/*   5:    */import java.util.Date;
/*   6:    */import java.util.GregorianCalendar;
/*   7:    */
/*   8:    */public final class DateUtil
/*   9:    */{
/*  10: 10 */  public static final java.text.SimpleDateFormat datetimeFormat2 = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
/*  11: 11 */  public static final java.text.SimpleDateFormat datetimeFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  12: 12 */  public static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
/*  13: 13 */  public static final java.text.SimpleDateFormat dateFormat2 = new java.text.SimpleDateFormat("yyyyMMdd");
/*  14: 14 */  public static final java.text.SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("yyyy-MM");
/*  15: 15 */  public static final java.text.SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy");
/*  16: 16 */  public static final java.text.SimpleDateFormat yyyymmFormat = new java.text.SimpleDateFormat("yyyyMM");
/*  17: 17 */  public static final java.text.SimpleDateFormat hourFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH");
/*  18: 18 */  public static final java.text.SimpleDateFormat hourFormat2 = new java.text.SimpleDateFormat("HH");
/*  19: 19 */  public static final java.text.SimpleDateFormat minuteFormat = new java.text.SimpleDateFormat("mm");
/*  20: 20 */  public static final java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("HH:mm:ss");
/*  21:    */  
/*  24:    */  public static String getCurrentDateTime2()
/*  25:    */  {
/*  26: 26 */    return datetimeFormat2.format(Calendar.getInstance().getTime());
/*  27:    */  }
/*  28:    */  
/*  29:    */  public static String getCurrentTime() {
/*  30: 30 */    return timeFormat.format(Calendar.getInstance().getTime());
/*  31:    */  }
/*  32:    */  
/*  33:    */  public static String getCurrentDateTime() {
/*  34: 34 */    return datetimeFormat.format(GregorianCalendar.getInstance().getTime());
/*  35:    */  }
/*  36:    */  
/*  37:    */  public static String getCurrentDate() {
/*  38: 38 */    return dateFormat.format(Calendar.getInstance().getTime());
/*  39:    */  }
/*  40:    */  
/*  41:    */  public static String getCurrentMonth() {
/*  42: 42 */    return monthFormat.format(Calendar.getInstance().getTime());
/*  43:    */  }
/*  44:    */  
/*  45:    */  public static String getCurrentYear() {
/*  46: 46 */    return yearFormat.format(Calendar.getInstance().getTime());
/*  47:    */  }
/*  48:    */  
/*  49:    */  public static String getCurrentHour() {
/*  50: 50 */    return hourFormat.format(Calendar.getInstance().getTime());
/*  51:    */  }
/*  52:    */  
/*  53:    */  public static int getCurrentIntHour() {
/*  54: 54 */    return Integer.parseInt(hourFormat2.format(Calendar.getInstance().getTime()));
/*  55:    */  }
/*  56:    */  
/*  57:    */  public static int getCurrentIntMinute() {
/*  58: 58 */    return Integer.parseInt(minuteFormat.format(Calendar.getInstance().getTime()));
/*  59:    */  }
/*  60:    */  
/*  61:    */  public static String getLastHour() {
/*  62: 62 */    Calendar cal = Calendar.getInstance();
/*  63: 63 */    cal.add(10, -1);
/*  64: 64 */    return hourFormat.format(cal.getTime());
/*  65:    */  }
/*  66:    */  
/*  67:    */  public static String getDay(int d) {
/*  68: 68 */    Calendar cal = Calendar.getInstance();
/*  69: 69 */    cal.add(5, d);
/*  70: 70 */    return dateFormat.format(cal.getTime());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public static String getNextDay() {
/*  74: 74 */    return getDay(1);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public static String getLastDay() {
/*  78: 78 */    return getDay(-1);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public static String getYYYYMM() {
/*  82: 82 */    return yyyymmFormat.format(Calendar.getInstance().getTime());
/*  83:    */  }
/*  84:    */  
/*  85:    */  public static String getYYYYMMDD() {
/*  86: 86 */    return dateFormat2.format(Calendar.getInstance().getTime());
/*  87:    */  }
/*  88:    */  
/*  89:    */  public static long getDiffTwoTimeToLong(String time1, String time2) {
/*  90: 90 */    if ((time1 == null) || (time2 == null))
/*  91: 91 */      return 0L;
/*  92: 92 */    return dateTimeToLong(time1) - dateTimeToLong(time2);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public static int getDiffDays(String time1, String time2) {
/*  96:    */    try {
/*  97: 97 */      long diffTime = datetimeFormat.parse(time2).getTime() - datetimeFormat.parse(time1).getTime();
/*  98: 98 */      return (int)(diffTime / 1000L / 86400L);
/*  99:    */    } catch (ParseException e) {}
/* 100:100 */    return 0;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public static int getDiffMinutes(String time1, String time2)
/* 104:    */  {
/* 105:    */    try {
/* 106:106 */      long diffTime = datetimeFormat.parse(time2).getTime() - datetimeFormat.parse(time1).getTime();
/* 107:107 */      return (int)(diffTime / 1000L / 60L);
/* 108:    */    } catch (Exception e) {}
/* 109:109 */    return 0;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public static long dateTimeToLong(String dateTime)
/* 113:    */  {
/* 114:    */    try {
/* 115:115 */      Date date = datetimeFormat.parse(dateTime);
/* 116:116 */      return date.getTime();
/* 117:    */    } catch (Exception e) {}
/* 118:118 */    return 0L;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public static String longToDateTime(long timeLong)
/* 122:    */  {
/* 123:123 */    if (timeLong == 0L) { return "";
/* 124:    */    }
/* 125:125 */    Date date = new Date(timeLong);
/* 126:126 */    return datetimeFormat.format(date);
/* 127:    */  }
/* 128:    */  
/* 129:    */  public static long getCurrentLongDateTime() {
/* 130:130 */    return new Date().getTime();
/* 131:    */  }
/* 132:    */  
/* 133:    */  public static synchronized String getCurrentTimeAsID() {
/* 134:134 */    return String.valueOf(System.nanoTime());
/* 135:    */  }
/* 136:    */  
/* 137:    */  public static long getDiffSeconds(String time1, String time2) {
/* 138:    */    try {
/* 139:139 */      long diffTime = Math.abs(datetimeFormat.parse(time2).getTime() - datetimeFormat.parse(time1).getTime());
/* 140:140 */      return diffTime / 1000L;
/* 141:    */    } catch (Exception e) {}
/* 142:142 */    return 0L;
/* 143:    */  }
/* 144:    */  
/* 145:    */  public static String microsecondToTimeString(long microsecond)
/* 146:    */  {
/* 147:147 */    long second = microsecond / 1000L;
/* 148:148 */    return secondToTimeString(second);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public static String secondToTimeString(long second) {
/* 152:152 */    StringBuffer timeStr = new StringBuffer(10);
/* 153:153 */    long hh24 = second / 3600L;
/* 154:154 */    long day = hh24 / 24L;
/* 155:155 */    long surplus = second % 3600L;
/* 156:156 */    long mi = surplus / 60L;
/* 157:157 */    if (surplus % 60L >= 30L)
/* 158:158 */      mi += 1L;
/* 159:159 */    if (day > 0L)
/* 160:160 */      hh24 -= day * 24L;
/* 161:161 */    if (day > 0L)
/* 162:162 */      timeStr.append(day).append("天");
/* 163:163 */    if (hh24 > 0L) {
/* 164:164 */      timeStr.append(hh24).append("时");
/* 165:    */    }
/* 166:166 */    timeStr.append(mi).append("分");
/* 167:167 */    return timeStr.toString();
/* 168:    */  }
/* 169:    */  
/* 170:    */  public static int getDiffHours(String time1, String time2) {
/* 171:    */    try {
/* 172:172 */      long diffTime = datetimeFormat.parse(time2).getTime() - datetimeFormat.parse(time1).getTime();
/* 173:173 */      return (int)(diffTime / 1000L / 3600L);
/* 174:    */    } catch (Exception e) {}
/* 175:175 */    return 0;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public static String getTimeSQL(String timeType)
/* 179:    */  {
/* 180:180 */    String sql = null;
/* 181:181 */    if ("lastHour".equals(timeType)) {
/* 182:182 */      sql = " log_time>=DATE_ADD(NOW(),INTERVAL-1 HOUR)";
/* 183:183 */    } else if ("yesterday".equals(timeType)) {
/* 184:184 */      sql = " DATE_FORMAT(log_time,'%Y-%m-%d')='" + getLastDay() + "'";
/* 185:185 */    } else if (("7day".equals(timeType)) || ("lastWeek".equals(timeType))) {
/* 186:186 */      sql = " log_time>=DATE_ADD(NOW(),INTERVAL-7 DAY)";
/* 187:187 */    } else if (("30day".equals(timeType)) || ("lastMonth".equals(timeType))) {
/* 188:188 */      sql = " log_time>=DATE_ADD(NOW(),INTERVAL-30 DAY)";
/* 189:189 */    } else if ("last3Months".equals(timeType)) {
/* 190:190 */      sql = " log_time>=DATE_ADD(NOW(),INTERVAL-90 DAY)";
/* 191:191 */    } else if (timeType.indexOf("#") > 0) {
/* 192:192 */      String[] times = timeType.split("#");
/* 193:193 */      sql = " log_time>='" + times[0] + "' and log_time<='" + times[1] + "'";
/* 194:    */    } else {
/* 195:195 */      sql = " DATE_FORMAT(log_time,'%Y-%m-%d')='" + getCurrentDate() + "'"; }
/* 196:196 */    return sql;
/* 197:    */  }
/* 198:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.util.DateUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */