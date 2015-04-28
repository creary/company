/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */
/*  5:   */@com.soofound.framework.annotation.PersistableAnnotation(associate="admin_role")
/*  6:   */public final class RoleDto implements Persistable
/*  7:   */{
/*  8:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="role", primaryKey=true)
/*  9:   */  private String role;
/* 10:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/* 11:   */  private String name;
/* 12:   */  @com.soofound.framework.annotation.PersistableAnnotation(associate="home")
/* 13:   */  private String home;
/* 14:   */  
/* 15:   */  public String getName() {
/* 16:16 */    return this.name;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void setName(String name) {
/* 20:20 */    this.name = name;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public String getRole() {
/* 24:24 */    return this.role;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void setRole(String role) {
/* 28:28 */    this.role = role;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public String getHome() {
/* 32:32 */    return this.home;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void setHome(String home) {
/* 36:36 */    this.home = home;
/* 37:   */  }
/* 38:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.RoleDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */