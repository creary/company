/*   1:    */package com.soofound.portal.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.annotation.PersistableAnnotation;
/*   4:    */import com.soofound.framework.jdbc.Persistable;
/*   5:    */import com.soofound.framework.util.DateUtil;
/*   6:    */
/*   7:    */@PersistableAnnotation(associate="surfing_session")
/*   8:    */public class SurfingSession
/*   9:    */  implements Persistable
/*  10:    */{
/*  11:    */  @PersistableAnnotation(associate="session_id", primaryKey=true)
/*  12:    */  private String sessionId;
/*  13:    */  @PersistableAnnotation(associate="username")
/*  14:    */  private String username;
/*  15:    */  @PersistableAnnotation(associate="apmac", join=true)
/*  16:    */  private String apmac;
/*  17:    */  @PersistableAnnotation(associate="ap_name", join=true)
/*  18:    */  private String apName;
/*  19:    */  @PersistableAnnotation(associate="branch", join=true)
/*  20:    */  private String branch;
/*  21:    */  @PersistableAnnotation(associate="branch_id", join=true)
/*  22:    */  private String branchId;
/*  23:    */  @PersistableAnnotation(associate="ap_ip", join=true)
/*  24:    */  private String apIp;
/*  25:    */  @PersistableAnnotation(associate="cpe_id")
/*  26:    */  private int cpeId;
/*  27:    */  @PersistableAnnotation(associate="ip_address")
/*  28:    */  private String ipAddress;
/*  29:    */  @PersistableAnnotation(associate="mac")
/*  30:    */  private String mac;
/*  31:    */  @PersistableAnnotation(associate="start_time")
/*  32:    */  private String startTime;
/*  33:    */  @PersistableAnnotation(associate="stop_time")
/*  34:    */  private String stopTime;
/*  35:    */  @PersistableAnnotation(associate="session_time")
/*  36:    */  private int sessionTime;
/*  37:    */  @PersistableAnnotation(associate="input_octets")
/*  38:    */  private long inputOctets;
/*  39:    */  @PersistableAnnotation(associate="output_octets")
/*  40:    */  private long outputOctets;
/*  41:    */  @PersistableAnnotation(associate="snr")
/*  42:    */  private int snr;
/*  43:    */  @PersistableAnnotation(associate="ife")
/*  44:    */  private int ife;
/*  45:    */  @PersistableAnnotation(associate="flag")
/*  46:    */  private String flag;
/*  47:    */  @PersistableAnnotation(associate="brand")
/*  48:    */  private String brand;
/*  49:    */  @PersistableAnnotation(associate="state")
/*  50:    */  private int state;
/*  51:    */  @PersistableAnnotation(associate="url")
/*  52:    */  private String url;
/*  53:    */  
/*  54:    */  public String getSessionId()
/*  55:    */  {
/*  56: 56 */    return this.sessionId;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public void setSessionId(String sessionId) {
/*  60: 60 */    this.sessionId = sessionId;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public String getUsername() {
/*  64: 64 */    return this.username;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public void setUsername(String username) {
/*  68: 68 */    this.username = username;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public int getCpeId() {
/*  72: 72 */    return this.cpeId;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void setCpeId(int cpeId) {
/*  76: 76 */    this.cpeId = cpeId;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public String getIpAddress() {
/*  80: 80 */    return this.ipAddress;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void setIpAddress(String ipAddress) {
/*  84: 84 */    this.ipAddress = ipAddress;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public String getMac() {
/*  88: 88 */    return this.mac;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public void setMac(String mac) {
/*  92: 92 */    this.mac = mac;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public String getStartTime() {
/*  96: 96 */    return this.startTime;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public void setStartTime(String startTime) {
/* 100:100 */    this.startTime = startTime;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public String getStopTime() {
/* 104:104 */    return this.stopTime;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public void setStopTime(String stopTime) {
/* 108:108 */    this.stopTime = stopTime;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public int getSessionTime() {
/* 112:112 */    return this.sessionTime;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public void setSessionTime(int sessionTime) {
/* 116:116 */    this.sessionTime = sessionTime;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public long getInputOctets() {
/* 120:120 */    return this.inputOctets;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public void setInputOctets(long inputOctets) {
/* 124:124 */    this.inputOctets = inputOctets;
/* 125:    */  }
/* 126:    */  
/* 127:    */  public long getOutputOctets() {
/* 128:128 */    return this.outputOctets;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public void setOutputOctets(long outputOctets) {
/* 132:132 */    this.outputOctets = outputOctets;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public String getApName() {
/* 136:136 */    return this.apName;
/* 137:    */  }
/* 138:    */  
/* 139:    */  public void setApName(String apName) {
/* 140:140 */    this.apName = apName;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public String getApIp() {
/* 144:144 */    return this.apIp;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public void setApIp(String apIp) {
/* 148:148 */    this.apIp = apIp;
/* 149:    */  }
/* 150:    */  
/* 151:    */  public String getBranchId() {
/* 152:152 */    return this.branchId;
/* 153:    */  }
/* 154:    */  
/* 155:    */  public void setBranchId(String branchId) {
/* 156:156 */    this.branchId = branchId;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public String getBranch() {
/* 160:160 */    return this.branch;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public void setBranch(String branch) {
/* 164:164 */    this.branch = branch;
/* 165:    */  }
/* 166:    */  
/* 167:    */  public String getUpTimeText() {
/* 168:168 */    return DateUtil.secondToTimeString(this.sessionTime);
/* 169:    */  }
/* 170:    */  
/* 171:    */  public String getApmac() {
/* 172:172 */    return this.apmac;
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void setApmac(String apmac) {
/* 176:176 */    this.apmac = apmac;
/* 177:    */  }
/* 178:    */  
/* 179:    */  public int getSnr() {
/* 180:180 */    return this.snr;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public void setSnr(int snr) {
/* 184:184 */    this.snr = snr;
/* 185:    */  }
/* 186:    */  
/* 187:    */  public int getIfe() {
/* 188:188 */    return this.ife;
/* 189:    */  }
/* 190:    */  
/* 191:    */  public void setIfe(int ife) {
/* 192:192 */    this.ife = ife;
/* 193:    */  }
/* 194:    */  
/* 195:    */  public String getBrand() {
/* 196:196 */    return this.brand;
/* 197:    */  }
/* 198:    */  
/* 199:    */  public void setBrand(String brand) {
/* 200:200 */    this.brand = brand;
/* 201:    */  }
/* 202:    */  
/* 203:    */  public String getFlag() {
/* 204:204 */    return this.flag;
/* 205:    */  }
/* 206:    */  
/* 207:    */  public void setFlag(String flag) {
/* 208:208 */    this.flag = flag;
/* 209:    */  }
/* 210:    */  
/* 211:    */  public int getState() {
/* 212:212 */    return this.state;
/* 213:    */  }
/* 214:    */  
/* 215:    */  public void setState(int state) {
/* 216:216 */    this.state = state;
/* 217:    */  }
/* 218:    */  
/* 219:    */  public String getUrl() {
/* 220:220 */    return this.url;
/* 221:    */  }
/* 222:    */  
/* 223:    */  public void setUrl(String url) {
/* 224:224 */    this.url = url;
/* 225:    */  }
/* 226:    */  
/* 227:    */  public String getSnrText() {
/* 228:228 */    return this.snr + " dbm";
/* 229:    */  }
/* 230:    */  
/* 231:    */  public String getStopTimeText() {
/* 232:232 */    if (this.state == 0)
/* 233:233 */      return this.stopTime;
/* 234:234 */    return "";
/* 235:    */  }
/* 236:    */  
/* 237:    */  public String getRxText() {
/* 238:238 */    long temp = 0L;
/* 239:239 */    temp = this.inputOctets / 1024L / 1024L / 1024L;
/* 240:240 */    if (temp > 0L)
/* 241:241 */      return temp + " GB";
/* 242:242 */    temp = this.inputOctets / 1024L / 1024L;
/* 243:243 */    if (temp > 0L)
/* 244:244 */      return temp + " MB";
/* 245:245 */    temp = this.inputOctets / 1024L;
/* 246:246 */    if (temp > 0L)
/* 247:247 */      return temp + " KB";
/* 248:248 */    return this.inputOctets + " B";
/* 249:    */  }
/* 250:    */  
/* 251:    */  public String getTxText() {
/* 252:252 */    long temp = 0L;
/* 253:253 */    temp = this.outputOctets / 1024L / 1024L / 1024L;
/* 254:254 */    if (temp > 0L)
/* 255:255 */      return temp + " GB";
/* 256:256 */    temp = this.outputOctets / 1024L / 1024L;
/* 257:257 */    if (temp > 0L)
/* 258:258 */      return temp + " MB";
/* 259:259 */    temp = this.outputOctets / 1024L;
/* 260:260 */    if (temp > 0L)
/* 261:261 */      return temp + " KB";
/* 262:262 */    return this.outputOctets + " B";
/* 263:    */  }
/* 264:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.SurfingSession
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */