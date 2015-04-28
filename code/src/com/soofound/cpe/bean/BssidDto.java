/*   1:    */package com.soofound.cpe.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.Persistable;
/*   4:    */
/*   5:    */@com.soofound.framework.annotation.PersistableAnnotation(associate="cpe_ssid")
/*   6:    */public class BssidDto implements Persistable
/*   7:    */{
/*   8:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ap_id", primaryKey=true)
/*   9:    */  private int apId;
/*  10:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ife")
/*  11:    */  private int ife;
/*  12:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/*  13:    */  private String name;
/*  14:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="portal_id")
/*  15:    */  private int portalId;
/*  16:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="policy_id")
/*  17:    */  private int policyId;
/*  18:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="code_id")
/*  19:    */  private int codeId;
/*  20:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="portal_name", join=true)
/*  21:    */  private String portalName;
/*  22:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="policy_name", join=true)
/*  23:    */  private String policyName;
/*  24:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="code_name", join=true)
/*  25:    */  private String codeName;
/*  26:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="enable")
/*  27:    */  private int enable;
/*  28:    */  
/*  29:    */  public int getApId() {
/*  30: 30 */    return this.apId;
/*  31:    */  }
/*  32:    */  
/*  33:    */  public void setApId(int apId) {
/*  34: 34 */    this.apId = apId;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public int getIfe() {
/*  38: 38 */    return this.ife;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public void setIfe(int ife) {
/*  42: 42 */    this.ife = ife;
/*  43:    */  }
/*  44:    */  
/*  45:    */  public int getPortalId() {
/*  46: 46 */    return this.portalId;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public void setPortalId(int portalId) {
/*  50: 50 */    this.portalId = portalId;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public int getPolicyId() {
/*  54: 54 */    return this.policyId;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public void setPolicyId(int policyId) {
/*  58: 58 */    this.policyId = policyId;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public String getPortalName() {
/*  62: 62 */    return this.portalName;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public void setPortalName(String portalName) {
/*  66: 66 */    this.portalName = portalName;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public String getPolicyName() {
/*  70: 70 */    return this.policyName;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void setPolicyName(String policyName) {
/*  74: 74 */    this.policyName = policyName;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public int getCodeId() {
/*  78: 78 */    return this.codeId;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void setCodeId(int codeId) {
/*  82: 82 */    this.codeId = codeId;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public String getCodeName() {
/*  86: 86 */    return this.codeName;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void setCodeName(String codeName) {
/*  90: 90 */    this.codeName = codeName;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public String getName() {
/*  94: 94 */    if ("N/A".equals(this.name))
/*  95: 95 */      return "";
/*  96: 96 */    return this.name;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public void setName(String name) {
/* 100:100 */    this.name = name;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public int getEnable() {
/* 104:104 */    return this.enable;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public void setEnable(int enable) {
/* 108:108 */    this.enable = enable;
/* 109:    */  }
/* 110:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.bean.BssidDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */