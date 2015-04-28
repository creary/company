/*   1:    */package com.soofound.portal.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.util.DateUtil;
/*   4:    */import com.soofound.protocol.cmcc.PortalPacket;
/*   5:    */import java.io.Serializable;
/*   6:    */
/*   8:    */public class SurfingUser
/*   9:    */  implements Serializable
/*  10:    */{
/*  11:    */  private static final long serialVersionUID = 2015031520372L;
/*  12:    */  public static final int STATUS_OFFINE = 0;
/*  13:    */  public static final int STATUS_ACCOUNTING = 1;
/*  14:    */  public static final int STATUS_IDLE = 2;
/*  15:    */  private String branchId;
/*  16:    */  private int cpeId;
/*  17:    */  private String cpeMac;
/*  18:    */  private String terminalMac;
/*  19:    */  private String terminalIP;
/*  20:    */  private String ssid;
/*  21:    */  private String sessionId;
/*  22:    */  private String username;
/*  23:    */  private long startTime;
/*  24:    */  private long lastUpdateTime;
/*  25:    */  private long sessionTime;
/*  26:    */  private int status;
/*  27:    */  private long inputOctets;
/*  28:    */  private long outputOctets;
/*  29:    */  private int snr;
/*  30:    */  private String roamUrl;
/*  31:    */  private String flag;
/*  32:    */  private String openId;
/*  33:    */  private PortalPacket pp;
/*  34:    */  private int ife;
/*  35:    */  private long upSpeed;
/*  36:    */  private long downSpeed;
/*  37:    */  
/*  38:    */  public SurfingUser()
/*  39:    */  {
/*  40: 40 */    this.startTime = DateUtil.getCurrentLongDateTime();
/*  41: 41 */    this.lastUpdateTime = DateUtil.getCurrentLongDateTime();
/*  42:    */  }
/*  43:    */  
/*  44:    */  public int getCpeId() {
/*  45: 45 */    return this.cpeId;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public void setCpeId(int cpeId) {
/*  49: 49 */    this.cpeId = cpeId;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public String getTerminalMac() {
/*  53: 53 */    return this.terminalMac;
/*  54:    */  }
/*  55:    */  
/*  56:    */  public void setTerminalMac(String terminalMac) {
/*  57: 57 */    this.terminalMac = terminalMac;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public String getTerminalIP() {
/*  61: 61 */    return this.terminalIP;
/*  62:    */  }
/*  63:    */  
/*  64:    */  public void setTerminalIP(String terminalIP) {
/*  65: 65 */    this.terminalIP = terminalIP;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public String getUsername() {
/*  69: 69 */    return this.username;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public String getSsid() {
/*  73: 73 */    return this.ssid;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void setSsid(String ssid) {
/*  77: 77 */    this.ssid = ssid;
/*  78:    */  }
/*  79:    */  
/*  80:    */  public void setUsername(String username) {
/*  81: 81 */    this.username = username;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public long getStartTime() {
/*  85: 85 */    return this.startTime;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void setStartTime(long startTime) {
/*  89: 89 */    this.startTime = startTime;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public long getLastUpdateTime() {
/*  93: 93 */    return this.lastUpdateTime;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public void setLastUpdateTime(long lastUpdateTime) {
/*  97: 97 */    this.lastUpdateTime = lastUpdateTime;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public int getStatus() {
/* 101:101 */    return this.status;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public void setStatus(int status) {
/* 105:105 */    this.status = status;
/* 106:    */  }
/* 107:    */  
/* 108:    */  public String getSessionId() {
/* 109:109 */    return this.sessionId;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public void setSessionId(String sessionId) {
/* 113:113 */    this.sessionId = sessionId;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public String getBranchId() {
/* 117:117 */    return this.branchId;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void setBranchId(String branchId) {
/* 121:121 */    this.branchId = branchId;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public long getInputOctets() {
/* 125:125 */    return this.inputOctets;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public void setInputOctets(long inputOctets) {
/* 129:129 */    this.inputOctets = inputOctets;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public long getOutputOctets() {
/* 133:133 */    return this.outputOctets;
/* 134:    */  }
/* 135:    */  
/* 136:    */  public void setOutputOctets(long outputOctets) {
/* 137:137 */    this.outputOctets = outputOctets;
/* 138:    */  }
/* 139:    */  
/* 140:    */  public long getSessionTime() {
/* 141:141 */    return this.sessionTime;
/* 142:    */  }
/* 143:    */  
/* 144:    */  public void setSessionTime(long sessionTime) {
/* 145:145 */    this.sessionTime = sessionTime;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public int getSnr() {
/* 149:149 */    return this.snr;
/* 150:    */  }
/* 151:    */  
/* 152:    */  public void setSnr(int snr) {
/* 153:153 */    this.snr = snr;
/* 154:    */  }
/* 155:    */  
/* 156:    */  public String getSessionTimeText() {
/* 157:157 */    return DateUtil.microsecondToTimeString(this.sessionTime);
/* 158:    */  }
/* 159:    */  
/* 160:    */  public String getRoamUrl() {
/* 161:161 */    return this.roamUrl;
/* 162:    */  }
/* 163:    */  
/* 164:    */  public void setRoamUrl(String roamUrl) {
/* 165:165 */    if ((roamUrl != null) && (roamUrl.length() > 300)) {
/* 166:166 */      this.roamUrl = "http://www.qq.com";
/* 167:    */    } else
/* 168:168 */      this.roamUrl = roamUrl;
/* 169:    */  }
/* 170:    */  
/* 171:    */  public String getFlag() {
/* 172:172 */    return this.flag;
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void setFlag(String flag) {
/* 176:176 */    this.flag = flag;
/* 177:    */  }
/* 178:    */  
/* 179:    */  public String getOpenId() {
/* 180:180 */    return this.openId;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public void setOpenId(String openId) {
/* 184:184 */    this.openId = openId;
/* 185:    */  }
/* 186:    */  
/* 187:    */  public PortalPacket getPacket() {
/* 188:188 */    return this.pp;
/* 189:    */  }
/* 190:    */  
/* 191:    */  public void setPacket(PortalPacket pp) {
/* 192:192 */    this.pp = pp;
/* 193:    */  }
/* 194:    */  
/* 195:    */  public long getUpSpeed() {
/* 196:196 */    return this.upSpeed;
/* 197:    */  }
/* 198:    */  
/* 199:    */  public void setUpSpeed(long upSpeed) {
/* 200:200 */    this.upSpeed = upSpeed;
/* 201:    */  }
/* 202:    */  
/* 203:    */  public long getDownSpeed() {
/* 204:204 */    return this.downSpeed;
/* 205:    */  }
/* 206:    */  
/* 207:    */  public void setDownSpeed(long downSpeed) {
/* 208:208 */    this.downSpeed = downSpeed;
/* 209:    */  }
/* 210:    */  
/* 211:    */  public int getIfe() {
/* 212:212 */    return this.ife;
/* 213:    */  }
/* 214:    */  
/* 215:    */  public void setIfe(int ife) {
/* 216:216 */    this.ife = ife;
/* 217:    */  }
/* 218:    */  
/* 219:    */  public static SurfingUser bornFromSurfingSession(SurfingSession dto) {
/* 220:220 */    SurfingUser su = new SurfingUser();
/* 221:221 */    su.setSessionId(dto.getSessionId());
/* 222:222 */    su.setUsername(dto.getUsername());
/* 223:223 */    su.setCpeId(dto.getCpeId());
/* 224:224 */    su.setTerminalIP(dto.getIpAddress());
/* 225:225 */    su.setTerminalMac(dto.getMac());
/* 226:226 */    su.setStartTime(DateUtil.dateTimeToLong(dto.getStartTime()));
/* 227:227 */    su.setInputOctets(dto.getInputOctets());
/* 228:228 */    su.setOutputOctets(dto.getOutputOctets());
/* 229:229 */    su.setBranchId(dto.getBranchId());
/* 230:230 */    su.setStatus(dto.getState());
/* 231:231 */    su.setRoamUrl(dto.getUrl());
/* 232:232 */    su.setLastUpdateTime(DateUtil.dateTimeToLong(dto.getStopTime()));
/* 233:233 */    su.setSsid(dto.getCpeId() + "-" + dto.getIfe());
/* 234:234 */    return su;
/* 235:    */  }
/* 236:    */  
/* 237:    */  public String getCpeMac() {
/* 238:238 */    return this.cpeMac;
/* 239:    */  }
/* 240:    */  
/* 241:    */  public void setCpeMac(String cpeMac) {
/* 242:242 */    this.cpeMac = cpeMac;
/* 243:    */  }
/* 244:    */  
/* 245:    */  public String getAuth() {
/* 246:246 */    StringBuilder str = new StringBuilder(100);
/* 247:247 */    str.append("Auth: 1;Up: ").append(this.upSpeed).append(";Down: ").append(this.downSpeed).append(";Intf: ").append(this.ife).append(";");
/* 248:248 */    return str.toString();
/* 249:    */  }
/* 250:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.SurfingUser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */