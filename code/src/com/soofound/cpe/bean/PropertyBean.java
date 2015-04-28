/*  1:   */package com.soofound.cpe.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="cpe_property")
/*  6:   */public final class PropertyBean implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  9:   */  private int id;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/* 11:   */  private String name;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="cn_name")
/* 13:   */  private String cnName;
/* 14:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="en_name")
/* 15:   */  private String enName;
/* 16:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="tag")
/* 17:   */  private int tag;
/* 18:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="descr")
/* 19:   */  private String descr;
/* 20:   */  private String boxString;
/* 21:   */  private String checked;
/* 22:   */  
/* 23:   */  public int getId()
/* 24:   */  {
/* 25:25 */    return this.id;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void setId(int id) {
/* 29:29 */    this.id = id;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public String getName() {
/* 33:33 */    return this.name;
/* 34:   */  }
/* 35:   */  
/* 36:   */  public void setName(String name) {
/* 37:37 */    this.name = name;
/* 38:   */  }
/* 39:   */  
/* 40:   */  public String getCnName() {
/* 41:41 */    return this.cnName;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public void setCnName(String cnName) {
/* 45:45 */    this.cnName = cnName;
/* 46:   */  }
/* 47:   */  
/* 48:   */  public String getEnName() {
/* 49:49 */    return this.enName;
/* 50:   */  }
/* 51:   */  
/* 52:   */  public void setEnName(String enName) {
/* 53:53 */    this.enName = enName;
/* 54:   */  }
/* 55:   */  
/* 56:   */  public String getDescr() {
/* 57:57 */    return this.descr;
/* 58:   */  }
/* 59:   */  
/* 60:   */  public void setDescr(String descr) {
/* 61:61 */    this.descr = descr;
/* 62:   */  }
/* 63:   */  
/* 64:   */  public int getTag() {
/* 65:65 */    return this.tag;
/* 66:   */  }
/* 67:   */  
/* 68:   */  public void setTag(int tag) {
/* 69:69 */    this.tag = tag;
/* 70:   */  }
/* 71:   */  
/* 72:   */  public String getBoxString() {
/* 73:73 */    return this.boxString;
/* 74:   */  }
/* 75:   */  
/* 76:   */  public void setBoxString(String boxString) {
/* 77:77 */    this.boxString = boxString;
/* 78:   */  }
/* 79:   */  
/* 80:   */  public String getChecked() {
/* 81:81 */    return this.checked;
/* 82:   */  }
/* 83:   */  
/* 84:   */  public void setChecked(String checked) {
/* 85:85 */    this.checked = checked;
/* 86:   */  }
/* 87:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.bean.PropertyBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */