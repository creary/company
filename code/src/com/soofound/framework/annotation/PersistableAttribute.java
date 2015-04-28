/*  1:   */package com.soofound.framework.annotation;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */
/*  5:   */public final class PersistableAttribute
/*  6:   */{
/*  7:   */  private final java.util.List<FieldAttribute> fieldAttributes;
/*  8:   */  private String tableName;
/*  9:   */  
/* 10:   */  public PersistableAttribute()
/* 11:   */  {
/* 12:12 */    this.fieldAttributes = new ArrayList();
/* 13:   */  }
/* 14:   */  
/* 15:   */  public String getTableName() {
/* 16:16 */    return this.tableName;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void setTableName(String tableName) {
/* 20:20 */    this.tableName = tableName;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void add(FieldAttribute fieldAttribute) {
/* 24:24 */    this.fieldAttributes.add(fieldAttribute);
/* 25:   */  }
/* 26:   */  
/* 27:   */  public java.util.List<FieldAttribute> getFieldAttributes() {
/* 28:28 */    return java.util.Collections.unmodifiableList(this.fieldAttributes);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public FieldAttribute getFieldByAttribute(String attribute) {
/* 32:32 */    for (FieldAttribute fa : this.fieldAttributes) {
/* 33:33 */      if (fa.getAttribute().equals(attribute))
/* 34:34 */        return fa;
/* 35:   */    }
/* 36:36 */    return null;
/* 37:   */  }
/* 38:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.annotation.PersistableAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */