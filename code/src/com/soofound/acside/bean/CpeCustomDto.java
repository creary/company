/*  1:   */package com.soofound.acside.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */public final class CpeCustomDto implements Persistable
/*  6:   */{
/*  7:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ap_id")
/*  8:   */  private int apid;
/*  9:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="snr")
/* 10:   */  private int snr;
/* 11:   */  
/* 12:   */  public int getApid() {
/* 13:13 */    return this.apid;
/* 14:   */  }
/* 15:   */  
/* 16:   */  public void setApid(int apid) {
/* 17:17 */    this.apid = apid;
/* 18:   */  }
/* 19:   */  
/* 20:   */  public int getSnr() {
/* 21:21 */    return this.snr;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public void setSnr(int snr) {
/* 25:25 */    this.snr = snr;
/* 26:   */  }
/* 27:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.bean.CpeCustomDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */