/*   1:    */package com.soofound.cpe.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.annotation.PersistableAnnotation;
/*   4:    */import com.soofound.framework.jdbc.Persistable;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.List;
/*   7:    */
/*   8:    */@PersistableAnnotation(associate="cpe_unifi")
/*   9:    */public class UnifiHostDto implements Persistable
/*  10:    */{
/*  11:    */  @PersistableAnnotation(associate="id", primaryKey=true)
/*  12:    */  private int id;
/*  13:    */  @PersistableAnnotation(associate="acip")
/*  14:    */  private String acip;
/*  15:    */  @PersistableAnnotation(associate="port")
/*  16:    */  private String port;
/*  17:    */  @PersistableAnnotation(associate="site")
/*  18:    */  private String site;
/*  19:    */  @PersistableAnnotation(associate="username")
/*  20:    */  private String username;
/*  21:    */  @PersistableAnnotation(associate="password")
/*  22:    */  private String password;
/*  23:    */  @PersistableAnnotation(associate="branch_id")
/*  24:    */  private String branchId;
/*  25:    */  @PersistableAnnotation(associate="branch", join=true)
/*  26:    */  private String branch;
/*  27:    */  @PersistableAnnotation(associate="host_id")
/*  28:    */  private int hostId;
/*  29:    */  private List<String> sites;
/*  30:    */  
/*  31:    */  public int getId()
/*  32:    */  {
/*  33: 33 */    return this.id;
/*  34:    */  }
/*  35:    */  
/*  36:    */  public void setId(int id) {
/*  37: 37 */    this.id = id;
/*  38:    */  }
/*  39:    */  
/*  40:    */  public String getAcip() {
/*  41: 41 */    return this.acip;
/*  42:    */  }
/*  43:    */  
/*  44:    */  public void setAcip(String acip) {
/*  45: 45 */    this.acip = acip;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public String getPort() {
/*  49: 49 */    return this.port;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public void setPort(String port) {
/*  53: 53 */    this.port = port;
/*  54:    */  }
/*  55:    */  
/*  56:    */  public String getSite() {
/*  57: 57 */    return this.site;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public void setSite(String site) {
/*  61: 61 */    this.site = site;
/*  62: 62 */    if (this.site != null) {
/*  63: 63 */      String[] strs = site.split(";");
/*  64: 64 */      this.sites = new ArrayList();
/*  65: 65 */      for (String str : strs)
/*  66: 66 */        this.sites.add(str);
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  70:    */  public String getUsername() {
/*  71: 71 */    return this.username;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public void setUsername(String username) {
/*  75: 75 */    this.username = username;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public String getPassword() {
/*  79: 79 */    return this.password;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public void setPassword(String password) {
/*  83: 83 */    this.password = password;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public String getBranchId() {
/*  87: 87 */    return this.branchId;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public void setBranchId(String branchId) {
/*  91: 91 */    this.branchId = branchId;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public int getHostId() {
/*  95: 95 */    return this.hostId;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public void setHostId(int hostId) {
/*  99: 99 */    this.hostId = hostId;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public List<String> getSites() {
/* 103:103 */    return this.sites;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public String getBranch() {
/* 107:107 */    return this.branch;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void setBranch(String branch) {
/* 111:111 */    this.branch = branch;
/* 112:    */  }
/* 113:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.bean.UnifiHostDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */