/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.annotation.PersistableAnnotation;
/*  4:   */
/*  7:   */@PersistableAnnotation(associate="address_county")
/*  8:   */public final class CountyDto
/*  9:   */  extends AddressDto
/* 10:   */{
/* 11:   */  @PersistableAnnotation(associate="city_id")
/* 12:   */  private int cityId;
/* 13:   */  @PersistableAnnotation(associate="city", join=true)
/* 14:   */  private String city;
/* 15:   */  @PersistableAnnotation(associate="en_city", join=true)
/* 16:   */  private String enCity;
/* 17:   */  @PersistableAnnotation(associate="province", join=true)
/* 18:   */  private String province;
/* 19:   */  @PersistableAnnotation(associate="en_province", join=true)
/* 20:   */  private String enProvince;
/* 21:   */  @PersistableAnnotation(associate="area", join=true)
/* 22:   */  private String area;
/* 23:   */  @PersistableAnnotation(associate="en_area", join=true)
/* 24:   */  private String enArea;
/* 25:   */  
/* 26:   */  public int getCityId()
/* 27:   */  {
/* 28:28 */    return this.cityId;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public void setCityId(int cityId) {
/* 32:32 */    this.cityId = cityId;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public String getCity() {
/* 36:36 */    return this.city;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public void setCity(String city) {
/* 40:40 */    this.city = city;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public String getEnCity() {
/* 44:44 */    return this.enCity;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void setEnCity(String enCity) {
/* 48:48 */    this.enCity = enCity;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public String getProvince() {
/* 52:52 */    return this.province;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void setProvince(String province) {
/* 56:56 */    this.province = province;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public String getEnProvince() {
/* 60:60 */    return this.enProvince;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public void setEnProvince(String enProvince) {
/* 64:64 */    this.enProvince = enProvince;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public String getArea() {
/* 68:68 */    return this.area;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void setArea(String area) {
/* 72:72 */    this.area = area;
/* 73:   */  }
/* 74:   */  
/* 75:   */  public String getEnArea() {
/* 76:76 */    return this.enArea;
/* 77:   */  }
/* 78:   */  
/* 79:   */  public void setEnArea(String enArea) {
/* 80:80 */    this.enArea = enArea;
/* 81:   */  }
/* 82:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.CountyDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */