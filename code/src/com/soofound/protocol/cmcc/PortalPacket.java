/*   1:    */package com.soofound.protocol.cmcc;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.net.InetAddress;
/*   5:    */import java.util.ArrayList;
/*   6:    */
/*   7:    */public abstract class PortalPacket
/*   8:    */{
/*   9:    */  private static final int ATTRIBUTE_START = 32;
/*  10:    */  protected int id;
/*  11: 11 */  protected int version = 1;
/*  12:    */  protected int type;
/*  13: 13 */  protected int papChap = 0;
/*  14: 14 */  protected byte rsv = 0;
/*  15:    */  protected short serialNo;
/*  16:    */  protected short requestId;
/*  17:    */  protected String userIP;
/*  18:    */  protected int errCode;
/*  19:    */  protected int attrNum;
/*  20: 20 */  protected java.util.List<PortalAttribute> attrs = new ArrayList();
/*  21:    */  protected String remoteIP;
/*  22:    */  protected int remotePort;
/*  23:    */  
/*  24:    */  public static PortalPacket parse(byte[] buf)
/*  25:    */  {
/*  26: 26 */    return parse(buf, 0, buf.length);
/*  27:    */  }
/*  28:    */  
/*  29:    */  public static PortalPacket parse(byte[] buf, int offset, int length) {
/*  30: 30 */    java.nio.ByteBuffer bb = java.nio.ByteBuffer.wrap(buf);
/*  31: 31 */    int ver = bb.get();
/*  32: 32 */    int type = bb.get();
/*  33: 33 */    PortalPacket pp = null;
/*  34: 34 */    switch (type) {
/*  35:    */    case 1: 
/*  36: 36 */      pp = new RequestChallenge();
/*  37: 37 */      break;
/*  38:    */    case 2: 
/*  39: 39 */      pp = new AcknowledgeChallenge();
/*  40: 40 */      break;
/*  41:    */    case 3: 
/*  42: 42 */      pp = new RequestAuthentication();
/*  43: 43 */      break;
/*  44:    */    case 4: 
/*  45: 45 */      pp = new AcknowledgeAuthentication();
/*  46: 46 */      break;
/*  47:    */    case 5: 
/*  48: 48 */      pp = new RequestLogout();
/*  49: 49 */      break;
/*  50:    */    case 6: 
/*  51: 51 */      pp = new AcknowledgeLogout();
/*  52: 52 */      break;
/*  53:    */    case 7: 
/*  54: 54 */      pp = new AffirmAcknowledgeAuthentication();
/*  55: 55 */      break;
/*  56:    */    case 8: 
/*  57: 57 */      pp = new ForceLogout();
/*  58: 58 */      break;
/*  59:    */    case 9: 
/*  60: 60 */      pp = new RequestInformation();
/*  61: 61 */      break;
/*  62:    */    case 10: 
/*  63: 63 */      pp = new AcknowledgeInformation();
/*  64:    */    }
/*  65:    */    
/*  66: 66 */    pp.setVersion(ver);
/*  67: 67 */    pp.setType(type);
/*  68: 68 */    pp.setPapChap(bb.get());
/*  69: 69 */    pp.setRsv(bb.get());
/*  70: 70 */    pp.setSerialNo(bb.getShort());
/*  71: 71 */    pp.setRequestId(bb.getShort());
/*  72: 72 */    pp.setId(PortalUtils.byteShortToInt(pp.getRsv(), pp.getSerialNo()));
/*  73: 73 */    byte[] userIP = new byte[4];
/*  74: 74 */    userIP[0] = bb.get();
/*  75: 75 */    userIP[1] = bb.get();
/*  76: 76 */    userIP[2] = bb.get();
/*  77: 77 */    userIP[3] = bb.get();
/*  78:    */    try {
/*  79: 79 */      pp.setUserIP(InetAddress.getByAddress(userIP).getHostAddress());
/*  80:    */    }
/*  81:    */    catch (Exception localException) {}
/*  82: 82 */    bb.position(bb.position() + 2);
/*  83: 83 */    pp.setErrCode(bb.get());
/*  84: 84 */    pp.setAttrNum(bb.get());
/*  85:    */    
/*  86: 86 */    int attrIdx = ver == 1 ? bb.position() : 32;
/*  87: 87 */    int attrType = 0;
/*  88: 88 */    int attrLen = 0;
/*  89: 89 */    byte[] temps = bb.array();
/*  90: 90 */    for (int c = 0; c < pp.getAttrNum(); c++) {
/*  91: 91 */      attrType = temps[attrIdx];
/*  92: 92 */      attrLen = temps[(attrIdx + 1)] - 2;
/*  93:    */      
/*  94: 94 */      byte[] attrBytes = new byte[attrLen];
/*  95: 95 */      for (int j = 0; j < attrLen; j++)
/*  96: 96 */        attrBytes[j] = temps[(attrIdx + 2 + j)];
/*  97: 97 */      PortalAttribute pa = null;
/*  98: 98 */      String attstr = new String(attrBytes);
/*  99: 99 */      System.out.println(attrType + "#" + attstr);
/* 100:100 */      if (attrType == 1) {
/* 101:101 */        pa = new UserNameAttribute(attstr);
/* 102:102 */        pp.addAttribute(pa);
/* 103:103 */      } else if (attrType == 2) {
/* 104:104 */        pa = new UserPasswordAttribute(attstr);
/* 105:105 */        pp.addAttribute(pa);
/* 106:    */      }
/* 107:    */      
/* 112:112 */      attrIdx += attrLen + 2;
/* 113:    */    }
/* 114:114 */    return pp;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void setRsv(byte rsv) {
/* 118:118 */    this.rsv = rsv;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public byte getRsv() {
/* 122:122 */    return this.rsv;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public void setPapChap(int papChap) {
/* 126:126 */    this.papChap = papChap;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public void setSerialNo(short serialNo) {
/* 130:130 */    this.serialNo = serialNo;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public void setRequestId(short requestId) {
/* 134:134 */    this.requestId = requestId;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public void setUserIP(String userIP) {
/* 138:138 */    this.userIP = userIP;
/* 139:    */  }
/* 140:    */  
/* 141:    */  public void setType(int type) {
/* 142:142 */    this.type = type;
/* 143:    */  }
/* 144:    */  
/* 145:    */  public void setErrCode(int errCode) {
/* 146:146 */    this.errCode = errCode;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public void setAttrNum(int attrNum) {
/* 150:150 */    this.attrNum = attrNum;
/* 151:    */  }
/* 152:    */  
/* 153:    */  public int getPapChap() {
/* 154:154 */    return this.papChap;
/* 155:    */  }
/* 156:    */  
/* 157:    */  public short getSerialNo() {
/* 158:158 */    return this.serialNo;
/* 159:    */  }
/* 160:    */  
/* 161:    */  public short getRequestId() {
/* 162:162 */    return this.requestId;
/* 163:    */  }
/* 164:    */  
/* 165:    */  public String getUserIP() {
/* 166:166 */    return this.userIP;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public int getErrCode() {
/* 170:170 */    return this.errCode;
/* 171:    */  }
/* 172:    */  
/* 173:    */  public int getAttrNum() {
/* 174:174 */    return this.attrNum;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public void setId(int id) {
/* 178:178 */    this.id = id;
/* 179:    */  }
/* 180:    */  
/* 181:    */  public int getId() {
/* 182:182 */    return this.id;
/* 183:    */  }
/* 184:    */  
/* 185:    */  public void addAttribute(PortalAttribute pa) {
/* 186:186 */    this.attrs.add(pa);
/* 187:    */  }
/* 188:    */  
/* 189:    */  public java.util.List<PortalAttribute> getAttributes() {
/* 190:190 */    return this.attrs;
/* 191:    */  }
/* 192:    */  
/* 193:    */  public PortalAttribute findAttribute(int type) {
/* 194:194 */    for (PortalAttribute attr : this.attrs) {
/* 195:195 */      if (attr.getType() == type)
/* 196:196 */        return attr;
/* 197:    */    }
/* 198:198 */    return null;
/* 199:    */  }
/* 200:    */  
/* 201:    */  public String toString() {
/* 202:202 */    StringBuilder str = new StringBuilder();
/* 203:203 */    str.append(getStep()).append(":");
/* 204:204 */    str.append("type=").append(this.type);
/* 205:205 */    str.append(",papChap=").append(this.papChap);
/* 206:206 */    str.append(",serialNo=").append(this.serialNo);
/* 207:207 */    str.append(",rsv=").append(this.rsv);
/* 208:208 */    str.append(",requestId=").append(this.requestId);
/* 209:209 */    str.append(",userIP=").append(this.userIP);
/* 210:210 */    str.append(",errCode=").append(this.errCode);
/* 211:211 */    str.append(",attrNum=").append(this.attrNum);
/* 212:212 */    return str.toString();
/* 213:    */  }
/* 214:    */  
/* 215:    */  public int getVersion() {
/* 216:216 */    return this.version;
/* 217:    */  }
/* 218:    */  
/* 219:    */  public void setVersion(int version) {
/* 220:220 */    this.version = version;
/* 221:    */  }
/* 222:    */  
/* 223:    */  public String getRemoteIP() {
/* 224:224 */    return this.remoteIP;
/* 225:    */  }
/* 226:    */  
/* 227:    */  public void setRemoteIP(String remoteIP) {
/* 228:228 */    this.remoteIP = remoteIP;
/* 229:    */  }
/* 230:    */  
/* 231:    */  public int getRemotePort() {
/* 232:232 */    return this.remotePort;
/* 233:    */  }
/* 234:    */  
/* 235:    */  public void setRemotePort(int remotePort) {
/* 236:236 */    this.remotePort = remotePort;
/* 237:    */  }
/* 238:    */  
/* 239:    */  public byte[] getBytes() {
/* 240:240 */    if (this.userIP == null) {
/* 241:241 */      throw new IllegalStateException("userIP is null");
/* 242:    */    }
/* 243:243 */    int len = 16;
/* 244:244 */    for (PortalAttribute attr : this.attrs) {
/* 245:245 */      len += attr.getBytes().length;
/* 246:    */    }
/* 247:247 */    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(len);
/* 248:248 */    bb.put((byte)this.version);
/* 249:249 */    bb.put((byte)this.type);
/* 250:250 */    bb.put((byte)this.papChap);
/* 251:251 */    bb.put(this.rsv);
/* 252:252 */    bb.putShort(this.serialNo);
/* 253:253 */    bb.putShort(this.requestId);
/* 254:254 */    String[] ips = this.userIP.split("\\.");
/* 255:255 */    for (String ip : ips)
/* 256:256 */      bb.put((byte)Integer.parseInt(ip));
/* 257:257 */    bb.putShort((short)0);
/* 258:258 */    bb.put((byte)this.errCode);
/* 259:259 */    bb.put((byte)this.attrNum);
/* 260:260 */    for (PortalAttribute attr : this.attrs)
/* 261:261 */      bb.put(attr.getBytes());
/* 262:262 */    return bb.array();
/* 263:    */  }
/* 264:    */  
/* 265:    */  public abstract String getStep();
/* 266:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.PortalPacket
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */