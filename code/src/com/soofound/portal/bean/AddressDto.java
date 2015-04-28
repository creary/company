/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */public class AddressDto implements Persistable
/*  6:   */{
/*  7:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  8:   */  protected int id;
/*  9:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/* 10:   */  protected String name;
/* 11:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="en_name")
/* 12:   */  protected String enName;
/* 13:   */  
/* 14:   */  public int getId() {
/* 15:15 */    return this.id;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public void setId(int id) {
/* 19:19 */    this.id = id;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public String getName() {
/* 23:23 */    return this.name;
/* 24:   */  }
/* 25:   */  
/* 26:   */  public void setName(String name) {
/* 27:27 */    this.name = name;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public String getEnName() {
/* 31:31 */    return this.enName;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public void setEnName(String enName) {
/* 35:35 */    this.enName = enName;
/* 36:   */  }
/* 37:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.AddressDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */