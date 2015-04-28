/*  1:   */package com.soofound.framework.annotation;
/*  2:   */
/*  3:   */public final class FieldAttribute {
/*  4:   */  private boolean autoIncrement;
/*  5:   */  private boolean primaryKey;
/*  6:   */  private boolean join;
/*  7:   */  private String name;
/*  8:   */  private String attribute;
/*  9:   */  
/* 10:   */  public boolean isPrimaryKey() {
/* 11:11 */    return this.primaryKey;
/* 12:   */  }
/* 13:   */  
/* 14:   */  public void setPrimaryKey(boolean primaryKey) {
/* 15:15 */    this.primaryKey = primaryKey;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public String getAttribute() {
/* 19:19 */    return this.attribute;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public void setAttribute(String attribute) {
/* 23:23 */    this.attribute = attribute;
/* 24:   */  }
/* 25:   */  
/* 26:   */  public String getName() {
/* 27:27 */    return this.name;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void setName(String name) {
/* 31:31 */    this.name = name;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public boolean isAutoIncrement() {
/* 35:35 */    return this.autoIncrement;
/* 36:   */  }
/* 37:   */  
/* 38:   */  public void setAutoIncrement(boolean autoIncrement) {
/* 39:39 */    this.autoIncrement = autoIncrement;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public boolean isJoin() {
/* 43:43 */    return this.join;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public void setJoin(boolean join) {
/* 47:47 */    this.join = join;
/* 48:   */  }
/* 49:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.annotation.FieldAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */