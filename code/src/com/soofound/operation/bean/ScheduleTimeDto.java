/*  1:   */package com.soofound.operation.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="cpe_schedule_time")
/*  6:   */public class ScheduleTimeDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ap_id", primaryKey=true)
/*  9:   */  private int apId;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="bits")
/* 11:   */  private String bits;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="times")
/* 13:   */  private String times;
/* 14:   */  
/* 15:   */  public int getApId() {
/* 16:16 */    return this.apId;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void setApId(int apId) {
/* 20:20 */    this.apId = apId;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public String getBits() {
/* 24:24 */    return this.bits;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setBits(String bits) {
/* 28:28 */    this.bits = bits;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public String getTimes() {
/* 32:32 */    return this.times;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setTimes(String times) {
/* 36:36 */    this.times = times;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public void setBitsAndTimes(String bits) {
/* 40:40 */    this.bits = bits;
/* 41:41 */    StringBuilder bitstr = new StringBuilder(200);
/* 42:42 */    char[] chars = bits.toCharArray();
/* 43:43 */    for (char c : chars) {
/* 44:44 */      String temp = Integer.toBinaryString(Integer.parseInt(c, 16));
/* 45:45 */      if (temp.length() == 1) {
/* 46:46 */        bitstr.append("000");
/* 47:47 */      } else if (temp.length() == 2) {
/* 48:48 */        bitstr.append("00");
/* 49:49 */      } else if (temp.length() == 3)
/* 50:50 */        bitstr.append("0");
/* 51:51 */      bitstr.append(temp);
/* 52:   */    }
/* 53:53 */    char[] chars2 = bitstr.toString().toCharArray();
/* 54:54 */    StringBuilder timestrs = new StringBuilder(200);
/* 55:55 */    for (int i = 0; i < chars2.length; i++) {
/* 56:56 */      if (chars2[i] == '1')
/* 57:57 */        timestrs.append("#").append(intToTime(i * 30));
/* 58:   */    }
/* 59:59 */    this.times = timestrs.substring(1);
/* 60:   */  }
/* 61:   */  
/* 62:   */  private String intToTime(int times) {
/* 63:63 */    int days = times / 1440 + 1;
/* 64:64 */    int hours = times % 1440;
/* 65:65 */    String val = days + ":";
/* 66:66 */    if (hours % 60 == 0) {
/* 67:67 */      val = val + hours / 60 + ":00";
/* 68:   */    } else
/* 69:69 */      val = val + hours / 60 + ":" + hours % 60;
/* 70:70 */    return val;
/* 71:   */  }
/* 72:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.bean.ScheduleTimeDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */