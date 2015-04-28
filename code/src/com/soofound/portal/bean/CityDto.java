/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.annotation.PersistableAnnotation;
/*  4:   */
/*  7:   */@PersistableAnnotation(associate="address_city")
/*  8:   */public final class CityDto
/*  9:   */  extends AddressDto
/* 10:   */{
/* 11:   */  @PersistableAnnotation(associate="province_id")
/* 12:   */  private int provinceId;
/* 13:   */  @PersistableAnnotation(associate="province", join=true)
/* 14:   */  private String province;
/* 15:   */  @PersistableAnnotation(associate="area", join=true)
/* 16:   */  private String area;
/* 17:   */  @PersistableAnnotation(associate="en_province", join=true)
/* 18:   */  private String enProvince;
/* 19:   */  @PersistableAnnotation(associate="en_area", join=true)
/* 20:   */  private String enArea;
/* 21:   */  
/* 22:   */  public int getProvinceId()
/* 23:   */  {
/* 24:24 */    return this.provinceId;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setProvinceId(int provinceId) {
/* 28:28 */    this.provinceId = provinceId;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public String getProvince() {
/* 32:32 */    return this.province;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setProvince(String province) {
/* 36:36 */    this.province = province;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public String getArea() {
/* 40:40 */    return this.area;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public void setArea(String area) {
/* 44:44 */    this.area = area;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public String getEnProvince() {
/* 48:48 */    return this.enProvince;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void setEnProvince(String enProvince) {
/* 52:52 */    this.enProvince = enProvince;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public String getEnArea() {
/* 56:56 */    return this.enArea;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public void setEnArea(String enArea) {
/* 60:60 */    this.enArea = enArea;
/* 61:   */  }
/* 62:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.CityDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */