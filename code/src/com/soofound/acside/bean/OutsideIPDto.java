/*  1:   */package com.soofound.acside.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="outside_ip_address")
/*  6:   */public final class OutsideIPDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ip_address")
/*  9:   */  private String ipAddress;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="province")
/* 11:   */  private String province;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="location")
/* 13:   */  private String location;
/* 14:   */  
/* 15:   */  public String getIpAddress() {
/* 16:16 */    return this.ipAddress;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void setIpAddress(String ipAddress) {
/* 20:20 */    this.ipAddress = ipAddress;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public String getProvince() {
/* 24:24 */    return this.province;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setProvince(String province) {
/* 28:28 */    this.province = province;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public String getLocation() {
/* 32:32 */    return this.location;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setLocation(String location) {
/* 36:36 */    this.location = location;
/* 37:   */  }
/* 38:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.bean.OutsideIPDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */