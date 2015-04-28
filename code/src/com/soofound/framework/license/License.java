/*   1:    */package com.soofound.framework.license;
/*   2:    */
/*   3:    */import java.text.SimpleDateFormat;
/*   4:    */
/*   5:    */public final class License {
/*   6:    */  private String developer;
/*   7:    */  private String productID;
/*   8:    */  private String vendor;
/*   9:    */  private String customer;
/*  10:    */  private String machineID;
/*  11:    */  private String licenseType;
/*  12:    */  private String expiryDate;
/*  13:    */  private String productName;
/*  14:    */  private String productType;
/*  15:    */  private String version;
/*  16:    */  private String releaseDate;
/*  17:    */  private int deviceNumber;
/*  18:    */  private String code;
/*  19:    */  private String boot;
/*  20:    */  
/*  21:    */  public License() {
/*  22: 22 */    this.vendor = "Soofound.com";
/*  23:    */  }
/*  24:    */  
/*  25:    */  public String getVendor() {
/*  26: 26 */    return this.vendor;
/*  27:    */  }
/*  28:    */  
/*  29:    */  public void setVendor(String vendor) {
/*  30: 30 */    this.vendor = vendor;
/*  31:    */  }
/*  32:    */  
/*  33:    */  public String getCustomer() {
/*  34: 34 */    return this.customer;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public void setCustomer(String customer) {
/*  38: 38 */    this.customer = customer;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public String getMachineID() {
/*  42: 42 */    return this.machineID;
/*  43:    */  }
/*  44:    */  
/*  45:    */  public void setMachineID(String machineID) {
/*  46: 46 */    this.machineID = machineID;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public String getLicenseType() {
/*  50: 50 */    return this.licenseType;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void setLicenseType(String licenseType) {
/*  54: 54 */    this.licenseType = licenseType;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public String getExpiryDate() {
/*  58: 58 */    return this.expiryDate;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void setExpiryDate(String expiryDate) {
/*  62: 62 */    this.expiryDate = expiryDate;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public String getProductName() {
/*  66: 66 */    return this.productName;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void setProductName(String productName) {
/*  70: 70 */    this.productName = productName;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public String getProductType() {
/*  74: 74 */    return this.productType;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void setProductType(String productType) {
/*  78: 78 */    this.productType = productType;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public String getVersion() {
/*  82: 82 */    return this.version;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public void setVersion(String version) {
/*  86: 86 */    this.version = version;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public String getReleaseDate() {
/*  90: 90 */    return this.releaseDate;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void setReleaseDate(String releaseDate) {
/*  94: 94 */    this.releaseDate = releaseDate;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public int getDeviceNumber() {
/*  98: 98 */    return this.deviceNumber;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void setDeviceNumber(int deviceNumber) {
/* 102:102 */    this.deviceNumber = deviceNumber;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public String getCode() {
/* 106:106 */    return this.code;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public void setCode(String code) {
/* 110:110 */    this.code = code;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public String getExpiryDays() {
/* 114:114 */    if ("Trial".equals(this.licenseType)) {
/* 115:115 */      int days = LicenseTool.getDiffDays(getCurrentDate(), this.expiryDate);
/* 116:116 */      if (days <= 0)
/* 117:117 */        return "已过期";
/* 118:118 */      return "还剩" + days + "天";
/* 119:    */    }
/* 120:120 */    return "无限制";
/* 121:    */  }
/* 122:    */  
/* 123:    */  public String getBoot() {
/* 124:124 */    return this.boot;
/* 125:    */  }
/* 126:    */  
/* 127:    */  public void setBoot(String boot) {
/* 128:128 */    this.boot = boot;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public String getProductID() {
/* 132:132 */    return this.productID;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public void setProductID(String productID) {
/* 136:136 */    this.productID = productID;
/* 137:    */  }
/* 138:    */  
/* 139:    */  public String getDeveloper() {
/* 140:140 */    return this.developer;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public void setDeveloper(String developer) {
/* 144:144 */    this.developer = developer;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public String toString() {
/* 148:148 */    StringBuffer content = new StringBuffer(200);
/* 149:149 */    content.append(this.productID).append("#");
/* 150:150 */    content.append(this.vendor).append("#");
/* 151:151 */    content.append(this.customer).append("#");
/* 152:152 */    content.append(this.machineID).append("#");
/* 153:153 */    content.append(this.productType).append("#");
/* 154:154 */    content.append(this.version).append("#");
/* 155:155 */    content.append(this.deviceNumber);
/* 156:156 */    return content.toString();
/* 157:    */  }
/* 158:    */  
/* 159:    */  public String getCurrentDate() {
/* 160:160 */    return LicenseTool.dateFormat.format(java.util.Calendar.getInstance().getTime());
/* 161:    */  }
/* 162:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.license.License
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */