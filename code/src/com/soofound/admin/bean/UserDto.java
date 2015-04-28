/*   1:    */package com.soofound.admin.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.Persistable;
/*   4:    */
/*   5:    */@com.soofound.framework.annotation.PersistableAnnotation(associate="admin_user")
/*   6:    */public final class UserDto implements Persistable
/*   7:    */{
/*   8:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="username", primaryKey=true)
/*   9:    */  private String username;
/*  10:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="fullname")
/*  11:    */  private String fullname;
/*  12:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="password")
/*  13:    */  private String password;
/*  14:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/*  15:    */  private String branchId;
/*  16:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="phone")
/*  17:    */  private String phone;
/*  18:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="email")
/*  19:    */  private String email;
/*  20:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/*  21:    */  private String branch;
/*  22:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="create_time")
/*  23:    */  private String createTime;
/*  24:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="role")
/*  25:    */  private String role;
/*  26:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="role_name", join=true)
/*  27:    */  private String roleName;
/*  28:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="home", join=true)
/*  29:    */  private String home;
/*  30:    */  public String ipAddress;
/*  31:    */  
/*  32:    */  public String getFullname()
/*  33:    */  {
/*  34: 34 */    return this.fullname;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public void setFullname(String fullname) {
/*  38: 38 */    this.fullname = fullname;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public String getUsername() {
/*  42: 42 */    return this.username;
/*  43:    */  }
/*  44:    */  
/*  45:    */  public void setUsername(String username) {
/*  46: 46 */    this.username = username;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public String getPassword() {
/*  50: 50 */    return this.password;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void setPassword(String password) {
/*  54: 54 */    this.password = password;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public String getPhone() {
/*  58: 58 */    return this.phone;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void setPhone(String phone) {
/*  62: 62 */    this.phone = phone;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public String getEmail() {
/*  66: 66 */    return this.email;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void setEmail(String email) {
/*  70: 70 */    this.email = email;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public String getBranchId() {
/*  74: 74 */    return this.branchId;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void setBranchId(String branchId) {
/*  78: 78 */    this.branchId = branchId;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public String getBranch() {
/*  82: 82 */    return this.branch;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public void setBranch(String branch) {
/*  86: 86 */    this.branch = branch;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public String getRole() {
/*  90: 90 */    return this.role;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void setRole(String role) {
/*  94: 94 */    this.role = role;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public String getRoleName() {
/*  98: 98 */    return this.roleName;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void setRoleName(String roleName) {
/* 102:102 */    this.roleName = roleName;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public String getCreateTime() {
/* 106:106 */    return this.createTime;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public void setCreateTime(String createTime) {
/* 110:110 */    this.createTime = createTime;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public String getIpAddress() {
/* 114:114 */    return this.ipAddress;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void setIpAddress(String ipAddress) {
/* 118:118 */    this.ipAddress = ipAddress;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public String getHome() {
/* 122:122 */    return this.home;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public void setHome(String home) {
/* 126:126 */    this.home = home;
/* 127:    */  }
/* 128:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.UserDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */