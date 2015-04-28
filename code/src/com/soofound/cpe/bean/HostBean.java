/*   1:    */package com.soofound.cpe.bean;
/*   2:    */
/*   3:    */import com.soofound.cpe.util.CpeUtils;
/*   4:    */import com.soofound.framework.jdbc.Persistable;
/*   5:    */import java.io.Serializable;
/*   6:    */
/*   7:    */@com.soofound.framework.annotation.PersistableAnnotation(associate="cpe_host")
/*   8:    */public class HostBean implements Persistable, Serializable
/*   9:    */{
/*  10:    */  private static final long serialVersionUID = 2015031520371L;
/*  11:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*  12:    */  protected int id;
/*  13:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/*  14:    */  protected String name;
/*  15:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="serial_number")
/*  16:    */  protected String serialNumber;
/*  17:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="product_class")
/*  18:    */  protected String productClass;
/*  19:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="software_version")
/*  20:    */  protected String softwareVersion;
/*  21:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="hardware_version")
/*  22:    */  protected String hardwareVersion;
/*  23:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ssid")
/*  24:    */  protected String ssid;
/*  25:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="ip_address")
/*  26:    */  protected String ipAddress;
/*  27:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/*  28:    */  protected String branchId;
/*  29:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="channel")
/*  30:    */  protected String channel;
/*  31:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="up_time")
/*  32:    */  protected String upTime;
/*  33:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="last_time")
/*  34:    */  protected String lastTime;
/*  35:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="online")
/*  36:    */  protected int online;
/*  37:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="stun")
/*  38:    */  protected String stun;
/*  39:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="mode")
/*  40:    */  protected String mode;
/*  41:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="detect")
/*  42:    */  protected String detect;
/*  43:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="trace")
/*  44:    */  protected String trace;
/*  45:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="group_id", join=true)
/*  46:    */  protected String groupId;
/*  47:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="diffsec", join=true)
/*  48:    */  protected long diffsec;
/*  49:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/*  50:    */  protected String branch;
/*  51:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="user_num", join=true)
/*  52:    */  protected int userNum;
/*  53:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="code")
/*  54:    */  protected String code;
/*  55:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="location")
/*  56:    */  protected String location;
/*  57:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="used")
/*  58:    */  protected int used;
/*  59:    */  public static final int STATUS_OFFLINE = 0;
/*  60:    */  public static final int STATUS_ONLINE = 1;
/*  61:    */  public static final int STATUS_REBOOT = 2;
/*  62:    */  public static final int STATUS_UPGRADE = 3;
/*  63:    */  public static final int STATUS_RESET = 4;
/*  64:    */  
/*  65:    */  public int getId()
/*  66:    */  {
/*  67: 67 */    return this.id;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public void setId(int id) {
/*  71: 71 */    this.id = id;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public String getProductClass() {
/*  75: 75 */    return this.productClass;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public void setProductClass(String productClass) {
/*  79: 79 */    this.productClass = productClass;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public String getSoftwareVersion() {
/*  83: 83 */    return this.softwareVersion;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public void setSoftwareVersion(String softwareVersion) {
/*  87: 87 */    this.softwareVersion = softwareVersion;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public String getHardwareVersion() {
/*  91: 91 */    return this.hardwareVersion;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public void setHardwareVersion(String hardwareVersion) {
/*  95: 95 */    this.hardwareVersion = hardwareVersion;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public String getBranchId() {
/*  99: 99 */    return this.branchId;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public void setBranchId(String branchId) {
/* 103:103 */    this.branchId = branchId;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public String getSsid() {
/* 107:107 */    return this.ssid;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void setSsid(String ssid) {
/* 111:111 */    this.ssid = ssid;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public String getSerialNumber() {
/* 115:115 */    return this.serialNumber;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public void setSerialNumber(String serialNumber) {
/* 119:119 */    this.serialNumber = serialNumber;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public String getMode() {
/* 123:123 */    return this.mode;
/* 124:    */  }
/* 125:    */  
/* 126:    */  public String getIpAddress() {
/* 127:127 */    return this.ipAddress;
/* 128:    */  }
/* 129:    */  
/* 130:    */  public void setIpAddress(String ipAddress) {
/* 131:131 */    this.ipAddress = ipAddress;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public String getUpTime() {
/* 135:135 */    return this.upTime;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public void setUpTime(String upTime) {
/* 139:139 */    this.upTime = upTime;
/* 140:    */  }
/* 141:    */  
/* 142:    */  public String getLastTime() {
/* 143:143 */    return this.lastTime;
/* 144:    */  }
/* 145:    */  
/* 146:    */  public void setLastTime(String lastTime) {
/* 147:147 */    this.lastTime = lastTime;
/* 148:    */  }
/* 149:    */  
/* 150:    */  public long getDiffsec() {
/* 151:151 */    return this.diffsec;
/* 152:    */  }
/* 153:    */  
/* 154:    */  public void setDiffsec(long diffsec) {
/* 155:155 */    this.diffsec = diffsec;
/* 156:    */  }
/* 157:    */  
/* 158:    */  public int getOnline() {
/* 159:159 */    return this.online;
/* 160:    */  }
/* 161:    */  
/* 162:    */  public void setOnline(int online) {
/* 163:163 */    this.online = online;
/* 164:    */  }
/* 165:    */  
/* 166:    */  public String getStun() {
/* 167:167 */    return this.stun;
/* 168:    */  }
/* 169:    */  
/* 170:    */  public void setStun(String stun) {
/* 171:171 */    this.stun = stun;
/* 172:    */  }
/* 173:    */  
/* 174:    */  public String getName() {
/* 175:175 */    return this.name;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public void setName(String name) {
/* 179:179 */    this.name = name;
/* 180:    */  }
/* 181:    */  
/* 182:    */  public String getGroupId() {
/* 183:183 */    return this.groupId;
/* 184:    */  }
/* 185:    */  
/* 186:    */  public void setGroupId(String groupId) {
/* 187:187 */    this.groupId = groupId;
/* 188:    */  }
/* 189:    */  
/* 190:    */  public String getBranch() {
/* 191:191 */    return this.branch;
/* 192:    */  }
/* 193:    */  
/* 194:    */  public void setBranch(String branch) {
/* 195:195 */    this.branch = branch;
/* 196:    */  }
/* 197:    */  
/* 198:    */  public String getChannel() {
/* 199:199 */    return this.channel;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public void setChannel(String channel) {
/* 203:203 */    this.channel = channel;
/* 204:    */  }
/* 205:    */  
/* 206:    */  public void setMode(String mode) {
/* 207:207 */    this.mode = mode;
/* 208:    */  }
/* 209:    */  
/* 210:    */  public int getUserNum() {
/* 211:211 */    return this.userNum;
/* 212:    */  }
/* 213:    */  
/* 214:    */  public void setUserNum(int userNum) {
/* 215:215 */    this.userNum = userNum;
/* 216:    */  }
/* 217:    */  
/* 218:    */  public String getDetect() {
/* 219:219 */    return this.detect;
/* 220:    */  }
/* 221:    */  
/* 222:    */  public void setDetect(String detect) {
/* 223:223 */    this.detect = detect;
/* 224:    */  }
/* 225:    */  
/* 226:    */  public String getTrace() {
/* 227:227 */    return this.trace;
/* 228:    */  }
/* 229:    */  
/* 230:    */  public void setTrace(String trace) {
/* 231:231 */    this.trace = trace;
/* 232:    */  }
/* 233:    */  
/* 234:    */  public String getCode() {
/* 235:235 */    return this.code;
/* 236:    */  }
/* 237:    */  
/* 238:    */  public void setCode(String code) {
/* 239:239 */    this.code = code;
/* 240:    */  }
/* 241:    */  
/* 242:    */  public String getLocation() {
/* 243:243 */    return this.location;
/* 244:    */  }
/* 245:    */  
/* 246:    */  public void setLocation(String location) {
/* 247:247 */    this.location = location;
/* 248:    */  }
/* 249:    */  
/* 250:    */  public int getUsed() {
/* 251:251 */    return this.used;
/* 252:    */  }
/* 253:    */  
/* 254:    */  public void setUsed(int used) {
/* 255:255 */    this.used = used;
/* 256:    */  }
/* 257:    */  
/* 258:    */  public String getInternetIP() {
/* 259:259 */    if (this.stun == null)
/* 260:260 */      return "";
/* 261:261 */    return this.stun.split("-")[0];
/* 262:    */  }
/* 263:    */  
/* 264:    */  public String getUsedText() {
/* 265:265 */    if (this.used == 0)
/* 266:266 */      return "<font color='red'>未开通</font>";
/* 267:267 */    if (this.used == 1)
/* 268:268 */      return "已开通";
/* 269:269 */    if (this.used == 2)
/* 270:270 */      return "维修";
/* 271:271 */    return "";
/* 272:    */  }
/* 273:    */  
/* 274:    */  public boolean isFresh() {
/* 275:275 */    return "".equals(this.channel);
/* 276:    */  }
/* 277:    */  
/* 278:    */  public String getStatus() {
/* 279:279 */    if (this.online == 0)
/* 280:280 */      return "error";
/* 281:281 */    if (this.online == 1)
/* 282:282 */      return "ready";
/* 283:283 */    return "processing";
/* 284:    */  }
/* 285:    */  
/* 286:    */  public String getDescription() {
/* 287:287 */    return CpeUtils.getModeIcon(this.id, this.mode, this.online, this.diffsec, this.trace, this.detect, this.lastTime);
/* 288:    */  }
/* 289:    */  
/* 290:    */  public String getImage() {
/* 291:291 */    com.soofound.portal.custom.CustomFactory cf = (com.soofound.portal.custom.CustomFactory)com.soofound.framework.util.SysConfigHelper.getBean("customFactory");
/* 292:292 */    String img = cf.getDeviceImage(this.hardwareVersion);
/* 293:293 */    if (img == null) img = "celling.jpg";
/* 294:294 */    return img;
/* 295:    */  }
/* 296:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.bean.HostBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */