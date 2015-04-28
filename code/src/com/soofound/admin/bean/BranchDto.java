/*   1:    */package com.soofound.admin.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.Persistable;
/*   4:    */
/*   5:    */@com.soofound.framework.annotation.PersistableAnnotation(associate="admin_branch")
/*   6:    */public final class BranchDto implements Persistable
/*   7:    */{
/*   8:  8 */  public static final String[] GRADE_NAMES = { "", "一级商家", "二级商家", "三级商家", "四级商家", "五级商家" };
/*   9:    */  
/*  10:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  11:    */  private String id;
/*  12:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/*  13:    */  private String name;
/*  14:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="parent_id")
/*  15:    */  private String parentId;
/*  16:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="parent", join=true)
/*  17:    */  private String parent;
/*  18:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="open_id")
/*  19:    */  private String openId;
/*  20:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="short_name")
/*  21:    */  private String shortName;
/*  22:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="public_name")
/*  23:    */  private String publicName;
/*  24:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="app_id")
/*  25:    */  private String appId;
/*  26:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="app_secret")
/*  27:    */  private String appSecret;
/*  28:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="qr_code")
/*  29:    */  private String qrCode;
/*  30:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="grade")
/*  31:    */  private int grade;
/*  32:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="province")
/*  33:    */  private int province;
/*  34:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="city")
/*  35:    */  private int city;
/*  36:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="county")
/*  37:    */  private int county;
/*  38:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="industry")
/*  39:    */  private int industry;
/*  40:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="sub_industry")
/*  41:    */  private int subIndustry;
/*  42:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="address")
/*  43:    */  private String address;
/*  44:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="contact_man")
/*  45:    */  private String contactMan;
/*  46:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="contact_phone")
/*  47:    */  private String contactPhone;
/*  48:    */  private String linkedGroupName;
/*  49:    */  
/*  50:    */  public String getId()
/*  51:    */  {
/*  52: 52 */    return this.id;
/*  53:    */  }
/*  54:    */  
/*  55:    */  public void setId(String id) {
/*  56: 56 */    this.id = id;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public String getParentId() {
/*  60: 60 */    return this.parentId;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public void setParentId(String parentId) {
/*  64: 64 */    this.parentId = parentId;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public String getParent() {
/*  68: 68 */    return this.parent;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public void setParent(String parent) {
/*  72: 72 */    this.parent = parent;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public String getName() {
/*  76: 76 */    return this.name;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void setName(String name) {
/*  80: 80 */    this.name = name;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public String getPublicName() {
/*  84: 84 */    return this.publicName;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void setPublicName(String publicName) {
/*  88: 88 */    this.publicName = publicName;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public String getAppId() {
/*  92: 92 */    return this.appId;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public void setAppId(String appId) {
/*  96: 96 */    this.appId = appId;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public String getAppSecret() {
/* 100:100 */    return this.appSecret;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public void setAppSecret(String appSecret) {
/* 104:104 */    this.appSecret = appSecret;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public String getQrCode() {
/* 108:108 */    return this.qrCode;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public void setQrCode(String qrCode) {
/* 112:112 */    this.qrCode = qrCode;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public String getOpenId() {
/* 116:116 */    return this.openId;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public void setOpenId(String openId) {
/* 120:120 */    this.openId = openId;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public int getGrade() {
/* 124:124 */    return this.grade;
/* 125:    */  }
/* 126:    */  
/* 127:    */  public void setGrade(int grade) {
/* 128:128 */    this.grade = grade;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public int getProvince() {
/* 132:132 */    return this.province;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public void setProvince(int province) {
/* 136:136 */    this.province = province;
/* 137:    */  }
/* 138:    */  
/* 139:    */  public int getCity() {
/* 140:140 */    return this.city;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public void setCity(int city) {
/* 144:144 */    this.city = city;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public int getCounty() {
/* 148:148 */    return this.county;
/* 149:    */  }
/* 150:    */  
/* 151:    */  public void setCounty(int county) {
/* 152:152 */    this.county = county;
/* 153:    */  }
/* 154:    */  
/* 155:    */  public int getIndustry() {
/* 156:156 */    return this.industry;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public void setIndustry(int industry) {
/* 160:160 */    this.industry = industry;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public int getSubIndustry() {
/* 164:164 */    return this.subIndustry;
/* 165:    */  }
/* 166:    */  
/* 167:    */  public void setSubIndustry(int subIndustry) {
/* 168:168 */    this.subIndustry = subIndustry;
/* 169:    */  }
/* 170:    */  
/* 171:    */  public String getAddress() {
/* 172:172 */    return this.address;
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void setAddress(String address) {
/* 176:176 */    this.address = address;
/* 177:    */  }
/* 178:    */  
/* 179:    */  public String getContactMan() {
/* 180:180 */    return this.contactMan;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public void setContactMan(String contactMan) {
/* 184:184 */    this.contactMan = contactMan;
/* 185:    */  }
/* 186:    */  
/* 187:    */  public String getContactPhone() {
/* 188:188 */    return this.contactPhone;
/* 189:    */  }
/* 190:    */  
/* 191:    */  public void setContactPhone(String contactPhone) {
/* 192:192 */    this.contactPhone = contactPhone;
/* 193:    */  }
/* 194:    */  
/* 195:    */  public String getShortName() {
/* 196:196 */    return this.shortName;
/* 197:    */  }
/* 198:    */  
/* 199:    */  public void setShortName(String shortName) {
/* 200:200 */    this.shortName = shortName;
/* 201:    */  }
/* 202:    */  
/* 203:    */  public String getLinkedGroupName() {
/* 204:204 */    return this.linkedGroupName;
/* 205:    */  }
/* 206:    */  
/* 207:    */  public void setLinkedGroupName(String linkedGroupName) {
/* 208:208 */    this.linkedGroupName = linkedGroupName;
/* 209:    */  }
/* 210:    */  
/* 211:    */  public String getGradeName() {
/* 212:212 */    return GRADE_NAMES[this.grade];
/* 213:    */  }
/* 214:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.BranchDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */