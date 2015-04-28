/*   1:    */package com.soofound.portal.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.annotation.PersistableAnnotation;
/*   4:    */import com.soofound.framework.jdbc.Persistable;
/*   5:    */
/*   7:    */@PersistableAnnotation(associate="portal_surfing_policy")
/*   8:    */public final class SurfingPolicyDto
/*   9:    */  implements Persistable
/*  10:    */{
/*  11:    */  @PersistableAnnotation(associate="id", primaryKey=true)
/*  12:    */  private int id;
/*  13:    */  @PersistableAnnotation(associate="branch_id")
/*  14:    */  private String branchId;
/*  15:    */  @PersistableAnnotation(associate="branch", join=true)
/*  16:    */  private String branch;
/*  17:    */  @PersistableAnnotation(associate="name")
/*  18:    */  private String name;
/*  19:    */  @PersistableAnnotation(associate="surfing_time")
/*  20:    */  private int surfingTime;
/*  21:    */  @PersistableAnnotation(associate="idle_time")
/*  22:    */  private int idleTime;
/*  23:    */  @PersistableAnnotation(associate="stay_time")
/*  24:    */  private int stayTime;
/*  25:    */  @PersistableAnnotation(associate="redirect")
/*  26:    */  private int redirect;
/*  27:    */  @PersistableAnnotation(associate="auth")
/*  28:    */  private int auth;
/*  29:    */  @PersistableAnnotation(associate="pwd_auth")
/*  30:    */  private int pwdAuth;
/*  31:    */  @PersistableAnnotation(associate="sms_auth")
/*  32:    */  private int smsAuth;
/*  33:    */  @PersistableAnnotation(associate="wechat_auth")
/*  34:    */  private int wechatAuth;
/*  35:    */  @PersistableAnnotation(associate="jump_url")
/*  36:    */  private String jumpUrl;
/*  37:    */  @PersistableAnnotation(associate="jump_to")
/*  38:    */  private int jumpTo;
/*  39:    */  @PersistableAnnotation(associate="cmcc")
/*  40:    */  private int cmcc;
/*  41:    */  @PersistableAnnotation(associate="portal_ip")
/*  42:    */  private String portalIP;
/*  43:    */  @PersistableAnnotation(associate="portal_port")
/*  44:    */  private String portalPort;
/*  45:    */  @PersistableAnnotation(associate="portal_url")
/*  46:    */  private String portalUrl;
/*  47:    */  @PersistableAnnotation(associate="one_account_device")
/*  48:    */  private int oneAccountDevice;
/*  49:    */  @PersistableAnnotation(associate="wechat_guide")
/*  50:    */  private int wechatGuide;
/*  51:    */  @PersistableAnnotation(associate="wechat_share")
/*  52:    */  private int wechatShare;
/*  53:    */  @PersistableAnnotation(associate="up_speed")
/*  54:    */  private int upSpeed;
/*  55:    */  @PersistableAnnotation(associate="down_speed")
/*  56:    */  private int downSpeed;
/*  57:    */  @PersistableAnnotation(associate="second_free")
/*  58:    */  private int secondFree;
/*  59:    */  @PersistableAnnotation(associate="validity")
/*  60:    */  private int validity;
/*  61:    */  @PersistableAnnotation(associate="sms_flag")
/*  62:    */  private int smsFlag;
/*  63:    */  @PersistableAnnotation(associate="tag")
/*  64:    */  private int tag;
/*  65:    */  @PersistableAnnotation(associate="separate_portal")
/*  66:    */  private int separatePortal;
/*  67:    */  
/*  68:    */  public int getId()
/*  69:    */  {
/*  70: 70 */    return this.id;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void setId(int id) {
/*  74: 74 */    this.id = id;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public String getBranchId() {
/*  78: 78 */    return this.branchId;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void setBranchId(String branchId) {
/*  82: 82 */    this.branchId = branchId;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public String getBranch() {
/*  86: 86 */    return this.branch;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void setBranch(String branch) {
/*  90: 90 */    this.branch = branch;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public int getSurfingTime() {
/*  94: 94 */    return this.surfingTime;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public void setSurfingTime(int surfingTime) {
/*  98: 98 */    this.surfingTime = surfingTime;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public int getIdleTime() {
/* 102:102 */    return this.idleTime;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public void setIdleTime(int idleTime) {
/* 106:106 */    this.idleTime = idleTime;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public String getName() {
/* 110:110 */    return this.name;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public void setName(String name) {
/* 114:114 */    this.name = name;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public int getAuth() {
/* 118:118 */    return this.auth;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public void setAuth(int auth) {
/* 122:122 */    this.auth = auth;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public int getPwdAuth() {
/* 126:126 */    return this.pwdAuth;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public void setPwdAuth(int pwdAuth) {
/* 130:130 */    this.pwdAuth = pwdAuth;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public int getSmsAuth() {
/* 134:134 */    return this.smsAuth;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public void setSmsAuth(int smsAuth) {
/* 138:138 */    this.smsAuth = smsAuth;
/* 139:    */  }
/* 140:    */  
/* 141:    */  public int getWechatAuth() {
/* 142:142 */    return this.wechatAuth;
/* 143:    */  }
/* 144:    */  
/* 145:    */  public void setWechatAuth(int wechatAuth) {
/* 146:146 */    this.wechatAuth = wechatAuth;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public int getCmcc() {
/* 150:150 */    return this.cmcc;
/* 151:    */  }
/* 152:    */  
/* 153:    */  public void setCmcc(int cmcc) {
/* 154:154 */    this.cmcc = cmcc;
/* 155:    */  }
/* 156:    */  
/* 157:    */  public String getPortalIP() {
/* 158:158 */    return this.portalIP;
/* 159:    */  }
/* 160:    */  
/* 161:    */  public void setPortalIP(String portalIP) {
/* 162:162 */    this.portalIP = portalIP;
/* 163:    */  }
/* 164:    */  
/* 165:    */  public String getPortalPort() {
/* 166:166 */    return this.portalPort;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void setPortalPort(String portalPort) {
/* 170:170 */    this.portalPort = portalPort;
/* 171:    */  }
/* 172:    */  
/* 173:    */  public String getPortalUrl() {
/* 174:174 */    return this.portalUrl;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public void setPortalUrl(String portalUrl) {
/* 178:178 */    this.portalUrl = portalUrl;
/* 179:    */  }
/* 180:    */  
/* 181:    */  public int getOneAccountDevice() {
/* 182:182 */    return this.oneAccountDevice;
/* 183:    */  }
/* 184:    */  
/* 185:    */  public void setOneAccountDevice(int oneAccountDevice) {
/* 186:186 */    this.oneAccountDevice = oneAccountDevice;
/* 187:    */  }
/* 188:    */  
/* 189:    */  public int getWechatGuide() {
/* 190:190 */    return this.wechatGuide;
/* 191:    */  }
/* 192:    */  
/* 193:    */  public void setWechatGuide(int wechatGuide) {
/* 194:194 */    this.wechatGuide = wechatGuide;
/* 195:    */  }
/* 196:    */  
/* 197:    */  public int getRedirect() {
/* 198:198 */    return this.redirect;
/* 199:    */  }
/* 200:    */  
/* 201:    */  public void setRedirect(int redirect) {
/* 202:202 */    this.redirect = redirect;
/* 203:    */  }
/* 204:    */  
/* 205:    */  public int getTag() {
/* 206:206 */    return this.tag;
/* 207:    */  }
/* 208:    */  
/* 209:    */  public int getValidity() {
/* 210:210 */    return this.validity;
/* 211:    */  }
/* 212:    */  
/* 213:    */  public void setValidity(int validity) {
/* 214:214 */    this.validity = validity;
/* 215:    */  }
/* 216:    */  
/* 217:    */  public String getDefaultTag() {
/* 218:218 */    if (this.tag == 1)
/* 219:219 */      return "默认";
/* 220:220 */    return "";
/* 221:    */  }
/* 222:    */  
/* 223:    */  public void setTag(int tag) {
/* 224:224 */    this.tag = tag;
/* 225:    */  }
/* 226:    */  
/* 227:    */  public String getJumpUrl() {
/* 228:228 */    return this.jumpUrl;
/* 229:    */  }
/* 230:    */  
/* 231:    */  public void setJumpUrl(String jumpUrl) {
/* 232:232 */    this.jumpUrl = jumpUrl;
/* 233:    */  }
/* 234:    */  
/* 235:    */  public int getJumpTo() {
/* 236:236 */    return this.jumpTo;
/* 237:    */  }
/* 238:    */  
/* 239:    */  public void setJumpTo(int jumpTo) {
/* 240:240 */    this.jumpTo = jumpTo;
/* 241:    */  }
/* 242:    */  
/* 243:    */  public int getStayTime() {
/* 244:244 */    return this.stayTime;
/* 245:    */  }
/* 246:    */  
/* 247:    */  public void setStayTime(int stayTime) {
/* 248:248 */    this.stayTime = stayTime;
/* 249:    */  }
/* 250:    */  
/* 251:    */  public boolean isWechatAuthOnly() {
/* 252:252 */    return (this.pwdAuth == 0) && (this.smsAuth == 0) && (this.wechatAuth == 1);
/* 253:    */  }
/* 254:    */  
/* 255:    */  public int getUpSpeed() {
/* 256:256 */    return this.upSpeed;
/* 257:    */  }
/* 258:    */  
/* 259:    */  public void setUpSpeed(int upSpeed) {
/* 260:260 */    this.upSpeed = upSpeed;
/* 261:    */  }
/* 262:    */  
/* 263:    */  public int getDownSpeed() {
/* 264:264 */    return this.downSpeed;
/* 265:    */  }
/* 266:    */  
/* 267:    */  public void setDownSpeed(int downSpeed) {
/* 268:268 */    this.downSpeed = downSpeed;
/* 269:    */  }
/* 270:    */  
/* 271:    */  public int getSecondFree() {
/* 272:272 */    return this.secondFree;
/* 273:    */  }
/* 274:    */  
/* 275:    */  public void setSecondFree(int secondFree) {
/* 276:276 */    this.secondFree = secondFree;
/* 277:    */  }
/* 278:    */  
/* 279:    */  public int getWechatShare() {
/* 280:280 */    return this.wechatShare;
/* 281:    */  }
/* 282:    */  
/* 283:    */  public void setWechatShare(int wechatShare) {
/* 284:284 */    this.wechatShare = wechatShare;
/* 285:    */  }
/* 286:    */  
/* 287:    */  public int getSmsFlag() {
/* 288:288 */    return this.smsFlag;
/* 289:    */  }
/* 290:    */  
/* 291:    */  public void setSmsFlag(int smsFlag) {
/* 292:292 */    this.smsFlag = smsFlag;
/* 293:    */  }
/* 294:    */  
/* 295:    */  public int getSeparatePortal() {
/* 296:296 */    return this.separatePortal;
/* 297:    */  }
/* 298:    */  
/* 299:    */  public void setSeparatePortal(int separatePortal) {
/* 300:300 */    this.separatePortal = separatePortal;
/* 301:    */  }
/* 302:    */  
/* 303:    */  public static SurfingPolicyDto bornDefaultPolicy() {
/* 304:304 */    SurfingPolicyDto dto = new SurfingPolicyDto();
/* 305:305 */    dto.setRedirect(1);
/* 306:306 */    dto.setName("default");
/* 307:307 */    dto.setAuth(0);
/* 308:308 */    dto.setPwdAuth(0);
/* 309:309 */    dto.setSmsAuth(0);
/* 310:310 */    dto.setSmsFlag(1);
/* 311:311 */    dto.setWechatAuth(0);
/* 312:312 */    dto.setJumpTo(1);
/* 313:313 */    dto.setIdleTime(5);
/* 314:314 */    dto.setUpSpeed(0);
/* 315:315 */    dto.setDownSpeed(0);
/* 316:316 */    dto.setSecondFree(0);
/* 317:317 */    dto.setTag(1);
/* 318:318 */    return dto;
/* 319:    */  }
/* 320:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.SurfingPolicyDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */