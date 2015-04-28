/*   1:    */package com.soofound.cpe.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.Persistable;
/*   4:    */
/*   5:    */@com.soofound.framework.annotation.PersistableAnnotation(associate="cpe_software")
/*   6:    */public final class SoftwareBean implements Persistable
/*   7:    */{
/*   8:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*   9:    */  private int id;
/*  10:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="descr")
/*  11:    */  private String descr;
/*  12:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="product_model")
/*  13:    */  private String productModel;
/*  14:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="version")
/*  15:    */  private String version;
/*  16:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="md5")
/*  17:    */  private String md5;
/*  18:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="size")
/*  19:    */  private long size;
/*  20:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="file_name")
/*  21:    */  private String fileName;
/*  22:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="upload_time")
/*  23:    */  private String uploadTime;
/*  24:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="version_code")
/*  25:    */  private int versionCode;
/*  26:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="tag")
/*  27:    */  private int tag;
/*  28:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/*  29:    */  private String branchId;
/*  30:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/*  31:    */  private String branch;
/*  32:    */  
/*  33:    */  public int getId() {
/*  34: 34 */    return this.id;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public void setId(int id) {
/*  38: 38 */    this.id = id;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public String getDescr() {
/*  42: 42 */    return this.descr;
/*  43:    */  }
/*  44:    */  
/*  45:    */  public void setDescr(String descr) {
/*  46: 46 */    this.descr = descr;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public String getProductModel() {
/*  50: 50 */    return this.productModel;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void setProductModel(String productModel) {
/*  54: 54 */    this.productModel = productModel;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public String getVersion() {
/*  58: 58 */    return this.version;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void setVersion(String version) {
/*  62: 62 */    this.version = version;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public String getMd5() {
/*  66: 66 */    return this.md5;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void setMd5(String md5) {
/*  70: 70 */    this.md5 = md5;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public long getSize() {
/*  74: 74 */    return this.size;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void setSize(long size) {
/*  78: 78 */    this.size = size;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public String getFileName() {
/*  82: 82 */    return this.fileName;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public void setFileName(String fileName) {
/*  86: 86 */    this.fileName = fileName;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public String getUploadTime() {
/*  90: 90 */    return this.uploadTime;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void setUploadTime(String uploadTime) {
/*  94: 94 */    this.uploadTime = uploadTime;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public int getVersionCode() {
/*  98: 98 */    return this.versionCode;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void setVersionCode(int versionCode) {
/* 102:102 */    this.versionCode = versionCode;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public int getTag() {
/* 106:106 */    return this.tag;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public void setTag(int tag) {
/* 110:110 */    this.tag = tag;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public String getBranchId() {
/* 114:114 */    return this.branchId;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void setBranchId(String branchId) {
/* 118:118 */    this.branchId = branchId;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public String getBranch() {
/* 122:122 */    return this.branch;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public void setBranch(String branch) {
/* 126:126 */    this.branch = branch;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public String getVersionText() {
/* 130:130 */    if ("default".equals(this.version))
/* 131:131 */      return "默认配置";
/* 132:132 */    if ("access".equals(this.version))
/* 133:133 */      return "接入配置";
/* 134:134 */    return "";
/* 135:    */  }
/* 136:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.bean.SoftwareBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */