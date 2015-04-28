/*   1:    */package com.soofound.cpe.util;
/*   2:    */
/*   3:    */import com.soofound.framework.util.DateUtil;
/*   4:    */import com.soofound.framework.util.NetworkUtil;
/*   5:    */import com.soofound.framework.util.SysConfigHelper;
/*   6:    */import com.soofound.protocol.cwmp.GetParameterValuesResponse;
/*   7:    */import java.io.DataInputStream;
/*   8:    */import java.io.File;
/*   9:    */import java.io.FileInputStream;
/*  10:    */import java.io.FileOutputStream;
/*  11:    */import java.io.InputStream;
/*  12:    */import java.io.PrintStream;
/*  13:    */import java.net.InetAddress;
/*  14:    */import java.net.NetworkInterface;
/*  15:    */import java.security.MessageDigest;
/*  16:    */import java.util.Enumeration;
/*  17:    */import java.util.HashMap;
/*  18:    */import java.util.Map;
/*  19:    */import java.util.Random;
/*  20:    */import java.util.StringTokenizer;
/*  21:    */import org.springframework.util.StringUtils;
/*  22:    */
/*  24:    */public class CpeUtils
/*  25:    */{
/*  26: 26 */  public static final byte[] UDP_REQUEST = "please connect acs, thanks.\n\r".getBytes();
/*  27:    */  
/*  28:    */  public static final String PARAM_SERIAL_NO = "InternetGatewayDevice.DeviceInfo.SerialNumber";
/*  29:    */  
/*  30:    */  public static final String PARAM_SSID_0 = "InternetGatewayDevice.DeviceInfo.wireless_ssid";
/*  31:    */  public static final String PARAM_SSID_1 = "InternetGatewayDevice.DeviceInfo.wireless_ssid2";
/*  32:    */  public static final String PARAM_SSID_2 = "InternetGatewayDevice.DeviceInfo.wireless_ssid_5G";
/*  33:    */  public static final String PARAM_SSID_3 = "InternetGatewayDevice.DeviceInfo.wireless_ssid2_5G";
/*  34:    */  public static final String PARAM_LAN_AUTH = "InternetGatewayDevice.DeviceInfo.lan_auth";
/*  35:    */  public static final String PARAM_MANUFACTURER = "InternetGatewayDevice.DeviceInfo.Manufacturer";
/*  36:    */  public static final String PARAM_STUN_IP = "InternetGatewayDevice.DeviceInfo.ip_outside";
/*  37:    */  public static final String PARAM_STUN_PORT = "InternetGatewayDevice.DeviceInfo.port_outside";
/*  38:    */  public static final String PARAM_WECHAT = "InternetGatewayDevice.DeviceInfo.weixing_pass";
/*  39:    */  public static final String PARAM_ADD_MAC = "InternetGatewayDevice.DeviceInfo.add_station_mac";
/*  40:    */  public static final String PARAM_WHITE_LIST = "InternetGatewayDevice.DeviceInfo.white_list1";
/*  41:    */  public static final String PARAM_PRODUCT_CLASS = "InternetGatewayDevice.DeviceInfo.ProductClass";
/*  42:    */  public static final String PARAM_PRODUCT_MODEL = "InternetGatewayDevice.DeviceInfo.ProductModel";
/*  43:    */  public static final String PARAM_IP_ADDRESS = "InternetGatewayDevice.DeviceInfo.ip_address";
/*  44:    */  public static final String PARAM_SOFTWARE_VERSION = "InternetGatewayDevice.DeviceInfo.SoftwareVersion";
/*  45:    */  public static final String PARAM_HARDWARE_VERSION = "InternetGatewayDevice.DeviceInfo.HardwareVersion";
/*  46:    */  public static final String PARAM_WORK_MODE = "InternetGatewayDevice.DeviceInfo.work_mode";
/*  47:    */  public static final String PARAM_REDIRECT_ENABLE = "InternetGatewayDevice.DeviceInfo.http_redirect_enable";
/*  48:    */  public static final String PARAM_WECHAT_TIMEOUT = "InternetGatewayDevice.DeviceInfo.weixing_pass_interval";
/*  49:    */  public static final String PARAM_CHANNEL = "InternetGatewayDevice.DeviceInfo.wireless_channel";
/*  50:    */  public static final String PARAM_CHANNEL5 = "InternetGatewayDevice.DeviceInfo.wireless_channel_5G";
/*  51:    */  public static final String PARAM_TX_POWER = "InternetGatewayDevice.DeviceInfo.wireless_tx_power";
/*  52:    */  public static final String PARAM_DEBUG_COMMAND = "InternetGatewayDevice.DeviceInfo.debug_command";
/*  53:    */  public static final String PARAM_CPU_RATE = "InternetGatewayDevice.DeviceInfo.cpu_utilization";
/*  54:    */  public static final String PARAM_MEMORY_RATE = "InternetGatewayDevice.DeviceInfo.memory_utilization";
/*  55:    */  public static final String PARAM_FLASH_RATE = "InternetGatewayDevice.DeviceInfo.flash_utilization";
/*  56:    */  public static final String PARAM_DETECT = "InternetGatewayDevice.DeviceInfo.MonWifi";
/*  57:    */  public static final String PARAM_TRACE = "InternetGatewayDevice.DeviceInfo.MonURL";
/*  58:    */  public static final String PARAM_AD = "InternetGatewayDevice.DeviceInfo.ADbyebye";
/*  59:    */  public static final String PARAM_QOS = "InternetGatewayDevice.DeviceInfo.qos_enabled";
/*  60:    */  public static final String PARAM_QOS_TX = "InternetGatewayDevice.DeviceInfo.qos_upload";
/*  61:    */  public static final String PARAM_QOS_RX = "InternetGatewayDevice.DeviceInfo.qos_download";
/*  62:    */  public static final String PARAM_DEBUG_RESULT = "InternetGatewayDevice.DeviceInfo.debug_result";
/*  63:    */  public static final String DEBUG_RESULT = "DEBUG_RESULT:";
/*  64:    */  public static final String DEBUG = "DEBUG:";
/*  65: 65 */  public static final String[] CODES = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
/*  66:    */  
/*  69:    */  public static String convertMac(String mac)
/*  70:    */  {
/*  71: 71 */    StringBuilder macstr = new StringBuilder(200);
/*  72: 72 */    macstr.append(mac.substring(0, 2)).append(":");
/*  73: 73 */    macstr.append(mac.substring(2, 4)).append(":");
/*  74: 74 */    macstr.append(mac.substring(4, 6)).append(":");
/*  75: 75 */    macstr.append(mac.substring(6, 8)).append(":");
/*  76: 76 */    macstr.append(mac.substring(8, 10)).append(":");
/*  77: 77 */    macstr.append(mac.substring(10, 12));
/*  78: 78 */    return macstr.toString().toUpperCase();
/*  79:    */  }
/*  80:    */  
/*  81:    */  public static Map<String, String> parseFirmware(String fileName) {
/*  82: 82 */    Map<String, String> firmwares = new HashMap();
/*  83: 83 */    FileInputStream in = null;
/*  84: 84 */    DataInputStream dis = null;
/*  85:    */    try {
/*  86: 86 */      in = new FileInputStream(new File(fileName));
/*  87: 87 */      dis = new DataInputStream(in);
/*  88: 88 */      byte[] bs = new byte[64];
/*  89: 89 */      dis.read(bs, 0, bs.length);
/*  90: 90 */      String infostr = new String(bs);
/*  91: 91 */      int idx = infostr.indexOf("RW");
/*  92: 92 */      if (idx == -1)
/*  93: 93 */        idx = infostr.indexOf("AP");
/*  94: 94 */      String[] infos = infostr.substring(idx).split(" ");
/*  95: 95 */      String pm = infos[0].trim();
/*  96: 96 */      SoofacACS acs = (SoofacACS)SysConfigHelper.getBean("soofacACS");
/*  97: 97 */      if (acs.getRealm().equals("supoin")) {
/*  98: 98 */        if (pm.equals("RW2400NGHSC")) {
/*  99: 99 */          pm = "SP3190";
/* 100:100 */        } else if (pm.equals("RW2400NGHSC-M")) {
/* 101:101 */          pm = "SP3190-M";
/* 102:102 */        } else if (pm.equals("RW2400NSC"))
/* 103:103 */          pm = "SP3191";
/* 104:    */      }
/* 105:105 */      firmwares.put("productModel", pm);
/* 106:106 */      firmwares.put("version", infos[1].trim());
/* 107:107 */      firmwares.put("createTime", infos[2].trim());
/* 108:    */    } catch (Exception e) {
/* 109:109 */      e.printStackTrace();
/* 110:    */      try
/* 111:    */      {
/* 112:112 */        in.close();
/* 113:113 */        dis.close();
/* 114:    */      }
/* 115:    */      catch (Exception localException1) {}
/* 116:    */    }
/* 117:    */    finally
/* 118:    */    {
/* 119:    */      try
/* 120:    */      {
/* 121:112 */        in.close();
/* 122:113 */        dis.close();
/* 123:    */      }
/* 124:    */      catch (Exception localException2) {}
/* 125:    */    }
/* 126:117 */    return firmwares;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public static void copyFile(InputStream in, String fileName) {
/* 130:121 */    FileOutputStream fs = null;
/* 131:    */    try {
/* 132:123 */      fs = new FileOutputStream(fileName);
/* 133:124 */      byte[] buffer = new byte[1048576];
/* 134:125 */      int byteread = 0;
/* 135:126 */      while ((byteread = in.read(buffer)) != -1) {
/* 136:127 */        fs.write(buffer, 0, byteread);
/* 137:128 */        fs.flush();
/* 138:    */      }
/* 139:    */    }
/* 140:    */    catch (Exception localException)
/* 141:    */    {
/* 142:    */      try {
/* 143:134 */        fs.close();
/* 144:135 */        in.close();
/* 145:    */      }
/* 146:    */      catch (Exception localException1) {}
/* 147:    */    }
/* 148:    */    finally
/* 149:    */    {
/* 150:    */      try
/* 151:    */      {
/* 152:134 */        fs.close();
/* 153:135 */        in.close();
/* 154:    */      }
/* 155:    */      catch (Exception localException2) {}
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 159:    */  public static String getSsidText(GetParameterValuesResponse gpvr) {
/* 160:142 */    StringBuilder str = new StringBuilder(100);
/* 161:143 */    if (hasSsid(gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.wireless_ssid")))
/* 162:144 */      str.append(gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.wireless_ssid"));
/* 163:145 */    if (hasSsid(gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.wireless_ssid2")))
/* 164:146 */      str.append("/").append(gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.wireless_ssid2"));
/* 165:147 */    if (gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.HardwareVersion").endsWith("-A")) {
/* 166:148 */      if (hasSsid(gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.wireless_ssid_5G")))
/* 167:149 */        str.append("/").append(gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.wireless_ssid_5G"));
/* 168:150 */      if (hasSsid(gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.wireless_ssid2_5G")))
/* 169:151 */        str.append("/").append(gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.wireless_ssid2_5G"));
/* 170:    */    }
/* 171:153 */    return str.toString();
/* 172:    */  }
/* 173:    */  
/* 174:    */  private static boolean hasSsid(String ssid) {
/* 175:157 */    if ((ssid == null) || ("".equals(ssid)) || ("N/A".equals(ssid)))
/* 176:158 */      return false;
/* 177:159 */    return true;
/* 178:    */  }
/* 179:    */  
/* 180:    */  public static int getSoftVersionCode(String softVersion) {
/* 181:163 */    int code = 0;
/* 182:    */    try {
/* 183:165 */      String[] vs = (String[])null;
/* 184:166 */      if (softVersion.startsWith("v")) {
/* 185:167 */        vs = softVersion.substring(1).split("\\.");
/* 186:    */      } else
/* 187:169 */        vs = softVersion.split("\\.");
/* 188:170 */      for (int i = 0; i < vs.length; i++) {
/* 189:171 */        int pow = 1;
/* 190:172 */        if (i == 0) {
/* 191:173 */          pow = 1000;
/* 192:174 */        } else if (i == 1)
/* 193:175 */          pow = 100;
/* 194:176 */        code += Integer.parseInt(vs[i]) * pow;
/* 195:    */      }
/* 196:    */    } catch (Exception e) {
/* 197:179 */      System.out.println("getSoftVersionCode error:" + softVersion);
/* 198:    */    }
/* 199:181 */    return code;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public static String getRandomPassword() {
/* 203:185 */    Random random = new Random();
/* 204:186 */    StringBuilder code = new StringBuilder(100);
/* 205:187 */    for (int i = 0; i < 4; i++) {
/* 206:188 */      String str = CODES[random.nextInt(CODES.length)];
/* 207:189 */      code.append(str);
/* 208:    */    }
/* 209:191 */    return code.toString().toLowerCase();
/* 210:    */  }
/* 211:    */  
/* 212:    */  public static String getLocalIP() {
/* 213:    */    try {
/* 214:196 */      Enumeration nis = NetworkInterface.getNetworkInterfaces();
/* 215:197 */      Enumeration ips; for (; nis.hasMoreElements(); 
/* 216:    */          
/* 218:200 */          ips.hasMoreElements())
/* 219:    */      {
/* 220:198 */        NetworkInterface ni = (NetworkInterface)nis.nextElement();
/* 221:199 */        ips = ni.getInetAddresses();
/* 222:200 */        continue;
/* 223:201 */        InetAddress ia = (InetAddress)ips.nextElement();
/* 224:202 */        if ((!ia.isLoopbackAddress()) && (checkIp(ia.getHostAddress()))) {
/* 225:203 */          return ia.getHostAddress();
/* 226:    */        }
/* 227:    */      }
/* 228:206 */      return InetAddress.getLocalHost().getHostAddress();
/* 229:    */    } catch (Exception e) {}
/* 230:208 */    return "127.0.0.1";
/* 231:    */  }
/* 232:    */  
/* 233:    */  public static boolean checkIp(String ipAddress)
/* 234:    */  {
/* 235:213 */    boolean isValid = true;
/* 236:    */    try {
/* 237:215 */      StringTokenizer st = new StringTokenizer(ipAddress, ".");
/* 238:216 */      int len = st.countTokens();
/* 239:217 */      if (len != 4) { return false;
/* 240:    */      }
/* 241:219 */      int ipSegment = 0;
/* 242:220 */      for (int i = 0; i < len; i++) {
/* 243:221 */        ipSegment = Integer.parseInt(st.nextToken());
/* 244:222 */        if ((ipSegment < 0) || (ipSegment > 255)) {
/* 245:223 */          isValid = false;
/* 246:224 */          break;
/* 247:    */        }
/* 248:    */      }
/* 249:    */    } catch (Exception e) {
/* 250:228 */      return false;
/* 251:    */    }
/* 252:230 */    return isValid;
/* 253:    */  }
/* 254:    */  
/* 255:    */  public static boolean isThreshold(String name) {
/* 256:234 */    if (("KickStaRssiLow".equals(name)) || ("KickStaRssiLow_5G".equals(name)) || 
/* 257:235 */      ("AuthRssiThres".equals(name)) || ("AssocReqRssiThres".equals(name)))
/* 258:236 */      return true;
/* 259:237 */    if ((name.endsWith(".KickStaRssiLow")) || (name.endsWith(".KickStaRssiLow_5G")) || 
/* 260:238 */      (name.endsWith(".AuthRssiThres")) || (name.endsWith(".AssocReqRssiThres")))
/* 261:239 */      return true;
/* 262:240 */    return false;
/* 263:    */  }
/* 264:    */  
/* 265:    */  public static String getModeIcon(String mode, int status, long diffsec, String lastTime) {
/* 266:244 */    return getModeIcon(0, mode, status, diffsec, null, null, lastTime);
/* 267:    */  }
/* 268:    */  
/* 269:    */  public static String getDetectIcon(String detectOpen) {
/* 270:248 */    StringBuilder icon = new StringBuilder(100);
/* 271:249 */    icon.append("<i class=\"soof_icon wifi_feature_monitor");
/* 272:250 */    boolean off = (StringUtils.isEmpty(detectOpen)) || ("off".equals(detectOpen));
/* 273:251 */    if (off) {
/* 274:252 */      icon.append(" disabled");
/* 275:    */    } else
/* 276:254 */      icon.append(" normal");
/* 277:255 */    icon.append("\"></i>");
/* 278:256 */    if (off) {
/* 279:257 */      icon.append("&nbsp;已关闭");
/* 280:    */    } else
/* 281:259 */      icon.append("&nbsp;已开启");
/* 282:260 */    return icon.toString();
/* 283:    */  }
/* 284:    */  
/* 285:    */  public static String getTraceIcon(String traceOpen) {
/* 286:264 */    if (StringUtils.hasText(traceOpen)) {
/* 287:265 */      StringBuilder icon = new StringBuilder(100);
/* 288:266 */      icon.append("<i class=\"soof_icon wifi_feature_trace");
/* 289:267 */      if (("off".equals(traceOpen)) || ("ad".equals(traceOpen))) {
/* 290:268 */        icon.append(" disabled");
/* 291:    */      } else
/* 292:270 */        icon.append(" normal");
/* 293:271 */      icon.append("\"></i>");
/* 294:272 */      if (("off".equals(traceOpen)) || ("ad".equals(traceOpen))) {
/* 295:273 */        icon.append("&nbsp;已关闭");
/* 296:    */      } else
/* 297:275 */        icon.append("&nbsp;已开启");
/* 298:276 */      return icon.toString();
/* 299:    */    }
/* 300:278 */    return "";
/* 301:    */  }
/* 302:    */  
/* 303:    */  public static String getModeIcon(int id, String mode, int status, long diffsec, String traceOpen, String detectOpen, String lastTime) {
/* 304:282 */    StringBuilder icon = new StringBuilder(300);
/* 305:283 */    icon.append("<i style=\"margin-right:3px;\" class=\"soof_icon wifi_mode_");
/* 306:284 */    if (mode.equals("ROUTER")) {
/* 307:285 */      icon.append("router");
/* 308:286 */    } else if (mode.equals("AP")) {
/* 309:287 */      icon.append("ap");
/* 310:    */    } else
/* 311:289 */      icon.append("v_").append(mode.toLowerCase());
/* 312:290 */    if (status == 0) {
/* 313:291 */      icon.append(" disabled");
/* 314:292 */    } else if (status == 1) {
/* 315:293 */      icon.append(" normal");
/* 316:    */    } else
/* 317:295 */      icon.append(" warning");
/* 318:296 */    icon.append("\" title=\"");
/* 319:297 */    if (mode.equals("ROUTER")) {
/* 320:298 */      icon.append("路由模式");
/* 321:299 */    } else if (mode.equals("AP")) {
/* 322:300 */      icon.append("AP模式");
/* 323:    */    } else
/* 324:302 */      icon.append(mode);
/* 325:303 */    icon.append("\"></i>");
/* 326:304 */    if (status == 1) {
/* 327:305 */      if (StringUtils.hasText(traceOpen)) {
/* 328:306 */        icon.append("<a href=\"javascript:void(0);\" onclick=\"rl.page.open('").append(SysConfigHelper.CONTEXT_PATH);
/* 329:307 */        icon.append("cpe/readySettingTrace.do?id=").append(id).append("','trace',{modal : true, width : 1000, height: 550, scrollbars:'yes'})\" title=\"");
/* 330:308 */        if (("trace".equals(traceOpen)) || (NetworkUtil.checkIp(traceOpen))) {
/* 331:309 */          icon.append("溯源功能已开启，点击进行配置\">");
/* 332:    */        } else
/* 333:311 */          icon.append("溯源功能已关闭，点击进行配置\">");
/* 334:312 */        icon.append("<i style=\"margin-right:3px;\" class=\"soof_icon wifi_feature_trace");
/* 335:313 */        if (("trace".equals(traceOpen)) || (NetworkUtil.checkIp(traceOpen))) {
/* 336:314 */          icon.append(" normal");
/* 337:    */        } else
/* 338:316 */          icon.append(" disabled");
/* 339:317 */        icon.append("\"></i></a>");
/* 340:    */      }
/* 341:319 */      if (StringUtils.hasText(detectOpen)) {
/* 342:320 */        icon.append("<a href=\"javascript:void(0);\" onclick=\"rl.page.open('").append(SysConfigHelper.CONTEXT_PATH);
/* 343:321 */        icon.append("cpe/readySettingDetect.do?id=").append(id).append("','detect',{modal : true, width : 1000, height: 550, scrollbars:'yes'})\" title=\"");
/* 344:322 */        if (("on".equals(detectOpen)) || (NetworkUtil.checkIp(detectOpen))) {
/* 345:323 */          icon.append("探测功能已开启，点击进行配置\">");
/* 346:    */        } else
/* 347:325 */          icon.append("探测功能已关闭，点击进行配置\">");
/* 348:326 */        icon.append("<i style=\"margin-right:3px;\" class=\"soof_icon wifi_feature_monitor");
/* 349:327 */        if (("on".equals(detectOpen)) || (NetworkUtil.checkIp(detectOpen))) {
/* 350:328 */          icon.append(" normal");
/* 351:    */        } else
/* 352:330 */          icon.append(" disabled");
/* 353:331 */        icon.append("\"></i></a>");
/* 354:    */      }
/* 355:    */    }
/* 356:334 */    if (status == 0) {
/* 357:335 */      icon.append("离线,").append(lastTime);
/* 358:336 */    } else if (status == 1) {
/* 359:337 */      icon.append("在线");
/* 360:338 */      if ((!mode.equalsIgnoreCase("mikrotik")) && (!mode.equalsIgnoreCase("ubnt")) && (diffsec >= 0L))
/* 361:339 */        icon.append(",").append(DateUtil.secondToTimeString(diffsec));
/* 362:340 */    } else if (status == 2) {
/* 363:341 */      icon.append("正在重启...");
/* 364:342 */    } else if (status == 3) {
/* 365:343 */      icon.append("正在更新固件...");
/* 366:344 */    } else if (status == 4) {
/* 367:345 */      icon.append("正在恢复出产..."); }
/* 368:346 */    return icon.toString();
/* 369:    */  }
/* 370:    */  
/* 371:    */  public static String[] getTraceFlags(String traceOpen) {
/* 372:350 */    if ("off".equals(traceOpen))
/* 373:351 */      return new String[] { "off", "off" };
/* 374:352 */    if ("ad".equals(traceOpen))
/* 375:353 */      return new String[] { "on", "off" };
/* 376:354 */    return new String[] { "off", traceOpen };
/* 377:    */  }
/* 378:    */  
/* 379:    */  public static String generateAPKey(String apmac, String flashId) {
/* 380:    */    try {
/* 381:359 */      String beforeKey = flashId + "52" + apmac.replace(":", "") + "57";
/* 382:360 */      int len = beforeKey.length() / 2;
/* 383:361 */      byte[] bs = new byte[len];
/* 384:362 */      for (int i = 0; i < len; i++) {
/* 385:363 */        int loc = i * 2;
/* 386:364 */        String str3 = beforeKey.charAt(loc) + beforeKey.charAt(loc + 1);
/* 387:365 */        bs[i] = ((byte)Integer.parseInt(str3, 16));
/* 388:    */      }
/* 389:367 */      MessageDigest md = MessageDigest.getInstance("MD5");
/* 390:368 */      md.update(bs);
/* 391:369 */      byte[] enByte = md.digest();
/* 392:    */      
/* 393:371 */      StringBuilder afterKey = new StringBuilder(100);
/* 394:372 */      for (int i = 0; i < enByte.length; i++) {
/* 395:373 */        int v = enByte[i];
/* 396:374 */        if (v < 0)
/* 397:375 */          v += 256;
/* 398:376 */        String hv = Integer.toHexString(v);
/* 399:377 */        if (hv.length() == 1)
/* 400:378 */          afterKey.append("0");
/* 401:379 */        afterKey.append(hv);
/* 402:    */      }
/* 403:381 */      return afterKey.toString();
/* 404:    */    } catch (Exception ex) {
/* 405:383 */      ex.printStackTrace();
/* 406:    */    }
/* 407:385 */    return "";
/* 408:    */  }
/* 409:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.CpeUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */