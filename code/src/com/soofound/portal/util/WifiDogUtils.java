/*   1:    */package com.soofound.portal.util;
/*   2:    */
/*   3:    */import com.alibaba.fastjson.JSON;
/*   4:    */import com.alibaba.fastjson.JSONArray;
/*   5:    */import com.alibaba.fastjson.JSONObject;
/*   6:    */import com.soofound.cpe.bean.HostBean;
/*   7:    */import com.soofound.framework.util.SysConfigHelper;
/*   8:    */import java.io.BufferedReader;
/*   9:    */import java.io.File;
/*  10:    */import java.io.InputStreamReader;
/*  11:    */import java.io.PrintStream;
/*  12:    */import java.util.HashMap;
/*  13:    */import java.util.Map;
/*  14:    */import java.util.Random;
/*  15:    */import java.util.UUID;
/*  16:    */import java.util.regex.Matcher;
/*  17:    */import java.util.regex.Pattern;
/*  18:    */import javax.crypto.Cipher;
/*  19:    */import javax.crypto.spec.SecretKeySpec;
/*  20:    */import javax.servlet.http.HttpServletRequest;
/*  21:    */import org.apache.commons.codec.binary.Base64;
/*  22:    */import org.apache.commons.io.FileUtils;
/*  23:    */import org.apache.commons.lang.StringUtils;
/*  24:    */
/*  28:    */public class WifiDogUtils
/*  29:    */{
/*  30:    */  private static final String regExScript = "<script[^>]*?>[\\s\\S]*?<\\/script>";
/*  31:    */  private static final String regExStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>";
/*  32:    */  private static final String regExHtml = "<[^>]+>";
/*  33: 33 */  private static final Pattern pScript = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2);
/*  34: 34 */  private static final Pattern pStyle = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2);
/*  35: 35 */  private static final Pattern pHtml = Pattern.compile("<[^>]+>", 2);
/*  36:    */  public static final String API_HEART_BEAT = "http://121.199.34.227/site/putProductStun.do";
/*  37:    */  public static final String SMS_SENDER_CLASS = "sms_sender_class";
/*  38:    */  public static final String ERR_INFO_1 = "AP拒绝访问";
/*  39:    */  public static final String ERR_INFO_2 = "AP不在线";
/*  40:    */  public static final String ERR_INFO_3 = "您接入了未授权的AP，为避免安全风险，请先退出。";
/*  41:    */  public static final String ERR_INFO_4 = "此帐号已经在使用(在线)";
/*  42:    */  public static final String ERR_INFO_5 = "您的终端未通过认证，请先认证。";
/*  43:    */  public static final String ERR_INFO_6 = "时间或流量超过限制";
/*  44:    */  public static final String ERR_INFO_7 = "认证失败次数超过限制,请一小时后再登录.";
/*  45:    */  public static final String ERR_INFO_8 = "用户名或密码不正确";
/*  46:    */  public static final String MODULE_ROOT = "/msite/admin/module/";
/*  47:    */  public static final int LOGIN_URL_LENGTH = 165;
/*  48:    */  
/*  49:    */  public static String encodeToken(String apmac, String stamac, String staip)
/*  50:    */  {
/*  51: 51 */    String tokenStr = apmac + "_" + stamac + "_" + staip;
/*  52: 52 */    return new String(Base64.encodeBase64(tokenStr.getBytes()));
/*  53:    */  }
/*  54:    */  
/*  55:    */  public static String[] decodeToken(String tokenStr) {
/*  56: 56 */    String temp = new String(Base64.decodeBase64(tokenStr.getBytes()));
/*  57: 57 */    return temp.split("_");
/*  58:    */  }
/*  59:    */  
/*  60:    */  public static Map<String, Object> getSDTResult(Number status, String msg) {
/*  61: 61 */    Map<String, Object> result = new HashMap();
/*  62: 62 */    result.put("status", status);
/*  63: 63 */    result.put("msg", msg);
/*  64: 64 */    return result;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public static String getAdvertiseURL(HttpServletRequest request) {
/*  68: 68 */    String httpurl = request.getRequestURL().substring(0, request.getRequestURL().indexOf("acs") + 4);
/*  69: 69 */    return httpurl + "/upload/";
/*  70:    */  }
/*  71:    */  
/*  72:    */  public static String getUUID() {
/*  73: 73 */    return UUID.randomUUID().toString();
/*  74:    */  }
/*  75:    */  
/*  76:    */  public static String getMyUUID() {
/*  77: 77 */    StringBuilder uuid = new StringBuilder(100);
/*  78: 78 */    uuid.append(System.nanoTime()).append("-");
/*  79: 79 */    Random random = new Random();
/*  80: 80 */    for (int i = 0; i < 6; i++) {
/*  81: 81 */      String str = com.soofound.cpe.util.CpeUtils.CODES[random.nextInt(com.soofound.cpe.util.CpeUtils.CODES.length)];
/*  82: 82 */      uuid.append(str);
/*  83:    */    }
/*  84: 84 */    return uuid.toString();
/*  85:    */  }
/*  86:    */  
/*  87:    */  public static boolean isMobile(String mobile) {
/*  88: 88 */    if ((mobile != null) && (mobile.length() == 11) && (StringUtils.isNumeric(mobile)))
/*  89: 89 */      return true;
/*  90: 90 */    return false;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public static String getMsiteJsonContent(String jsonFile) {
/*  94: 94 */    return getMsiteJsonContent(jsonFile, true);
/*  95:    */  }
/*  96:    */  
/*  97:    */  public static String getMsiteJsonContent(String jsonFile, boolean replaceSingleQuotes) {
/*  98:    */    try {
/*  99: 99 */      File file = new File(SysConfigHelper.getAttribute("sysPath") + jsonFile);
/* 100:100 */      if (!file.exists()) {
/* 101:101 */        System.out.println(jsonFile + "----does not exists.");
/* 102:102 */        return null;
/* 103:    */      }
/* 104:104 */      String defJson = FileUtils.readFileToString(file, "UTF-8");
/* 105:105 */      defJson = defJson.replace("__dirname", getMsiteURL(jsonFile));
/* 106:106 */      if (replaceSingleQuotes) {}
/* 107:107 */      return defJson.replace("'", "''");
/* 108:    */    }
/* 109:    */    catch (Exception ex) {
/* 110:110 */      ex.printStackTrace(); }
/* 111:111 */    return null;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public static Map<String, Object> getMsiteJsonMap(String jsonFile)
/* 115:    */  {
/* 116:116 */    String jsonContent = getMsiteJsonContent(jsonFile, true);
/* 117:117 */    return (Map)JSON.parseObject(jsonContent, Map.class);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public static String getTemplatePath(String id) {
/* 121:121 */    return "/msite/admin/tpl/" + id.replace(".", "/") + "/";
/* 122:    */  }
/* 123:    */  
/* 124:    */  public static String getMsiteURL(String fullFileName) {
/* 125:125 */    int loc = fullFileName.lastIndexOf("\\");
/* 126:126 */    if (loc == -1)
/* 127:127 */      loc = fullFileName.lastIndexOf("/");
/* 128:128 */    String temp = fullFileName.substring(0, loc);
/* 129:129 */    return SysConfigHelper.CONTEXT_PATH + temp.substring(temp.indexOf("msite")).replace("\\", "/");
/* 130:    */  }
/* 131:    */  
/* 132:    */  public static Map<String, String> getProperties(String jsonContent) {
/* 133:133 */    Map<String, String> map = new HashMap();
/* 134:134 */    if (jsonContent == null)
/* 135:135 */      return map;
/* 136:    */    try {
/* 137:137 */      Map<String, Object> jsondata = (Map)JSON.parseObject(jsonContent, Map.class);
/* 138:138 */      JSONArray array = (JSONArray)jsondata.get("fields");
/* 139:139 */      if (array != null) {
/* 140:140 */        for (int i = 0; i < array.size(); i++) {
/* 141:141 */          String name = array.getJSONObject(i).getString("name");
/* 142:142 */          String value = array.getJSONObject(i).getString("value");
/* 143:143 */          map.put(name, value);
/* 144:    */        }
/* 145:    */      }
/* 146:    */    } catch (Exception ex) {
/* 147:147 */      System.out.println(jsonContent);
/* 148:148 */      ex.printStackTrace();
/* 149:    */    }
/* 150:150 */    return map;
/* 151:    */  }
/* 152:    */  
/* 153:    */  public static String encodeURL(String oldUrl) {
/* 154:154 */    if (oldUrl == null)
/* 155:155 */      return "";
/* 156:156 */    String urlstr = new String(Base64.encodeBase64(oldUrl.getBytes()));
/* 157:157 */    urlstr = urlstr.replace("+", "*").replace("/", "-").replace("=", ".");
/* 158:158 */    return urlstr;
/* 159:    */  }
/* 160:    */  
/* 161:    */  public static String decodeMac(String mac) {
/* 162:162 */    return new String(Base64.decodeBase64(mac.replace("*", "+").replace("-", "/").replace(".", "=")));
/* 163:    */  }
/* 164:    */  
/* 165:    */  public static Map<String, String> decodeURL(String oldUrl) {
/* 166:166 */    Map<String, String> params = new HashMap();
/* 167:167 */    if (oldUrl == null) {
/* 168:168 */      return params;
/* 169:    */    }
/* 170:170 */    String urlstr = null;
/* 171:171 */    if (oldUrl.length() > 165) {
/* 172:172 */      urlstr = oldUrl.substring(0, 165);
/* 173:    */    } else
/* 174:174 */      urlstr = oldUrl;
/* 175:175 */    if (urlstr.indexOf("=") == -1) {
/* 176:176 */      urlstr = new String(Base64.decodeBase64(oldUrl.replace("*", "+").replace("-", "/").replace(".", "=")));
/* 177:    */    } else
/* 178:178 */      urlstr = oldUrl;
/* 179:179 */    params.put("orgUrl", urlstr);
/* 180:180 */    String[] temps = urlstr.split("&");
/* 181:181 */    for (String temp : temps) {
/* 182:182 */      String[] strs = temp.split("=");
/* 183:183 */      if (strs.length > 1)
/* 184:184 */        if (strs[0].indexOf("mac") >= 0) {
/* 185:185 */          params.put(strs[0], strs[1].toUpperCase());
/* 186:186 */        } else if (strs[0].equals("url")) {
/* 187:187 */          String urls = null;
/* 188:188 */          int loc = strs[1].indexOf("?");
/* 189:189 */          if (loc == -1) {
/* 190:190 */            urls = strs[1];
/* 191:    */          } else
/* 192:192 */            urls = strs[1].substring(0, loc);
/* 193:193 */          if (urls.startsWith("http://")) {
/* 194:194 */            params.put(strs[0], urls);
/* 195:    */          } else {
/* 196:196 */            params.put(strs[0], "http://" + urls);
/* 197:    */          }
/* 198:198 */        } else if (!params.containsKey(strs[0])) {
/* 199:199 */          params.put(strs[0], strs[1]);
/* 200:    */        }
/* 201:    */    }
/* 202:202 */    return params;
/* 203:    */  }
/* 204:    */  
/* 205:    */  public static String getRidOfHTMLTag(String htmlStr) {
/* 206:206 */    Matcher mscript = pScript.matcher(htmlStr);
/* 207:207 */    String tempStr = mscript.replaceAll("");
/* 208:    */    
/* 209:209 */    Matcher mstyle = pStyle.matcher(tempStr);
/* 210:210 */    tempStr = mstyle.replaceAll("");
/* 211:    */    
/* 212:212 */    Matcher mhtml = pHtml.matcher(tempStr);
/* 213:213 */    tempStr = mhtml.replaceAll("");
/* 214:214 */    return tempStr;
/* 215:    */  }
/* 216:    */  
/* 217:    */  public static String getInterface(String ife) {
/* 218:218 */    if ((ife == null) || ("null".equals(ife)) || ("255".equals(ife)))
/* 219:219 */      return "0";
/* 220:220 */    return ife;
/* 221:    */  }
/* 222:    */  
/* 223:    */  public static boolean isRosAP(HostBean host) {
/* 224:224 */    return (host.getSerialNumber() != null) && (host.getSerialNumber().startsWith("AA:BB:CC"));
/* 225:    */  }
/* 226:    */  
/* 227:    */  public static void doCommand(String cmd) {
/* 228:228 */    Process pid = null;
/* 229:229 */    BufferedReader br = null;
/* 230:    */    try {
/* 231:231 */      System.out.println("---doCommand---\n" + cmd);
/* 232:232 */      pid = Runtime.getRuntime().exec(cmd);
/* 233:233 */      if (pid != null) {
/* 234:234 */        br = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
/* 235:235 */        pid.waitFor();
/* 236:    */      }
/* 237:    */    }
/* 238:    */    catch (Exception localException)
/* 239:    */    {
/* 240:    */      try {
/* 241:241 */        pid.destroy();
/* 242:242 */        br.close();
/* 243:    */      }
/* 244:    */      catch (Exception localException1) {}
/* 245:    */    }
/* 246:    */    finally
/* 247:    */    {
/* 248:    */      try
/* 249:    */      {
/* 250:241 */        pid.destroy();
/* 251:242 */        br.close();
/* 252:    */      }
/* 253:    */      catch (Exception localException2) {}
/* 254:    */    }
/* 255:    */  }
/* 256:    */  
/* 257:    */  public static byte[] encrypt(String input, String key) {
/* 258:249 */    byte[] crypted = (byte[])null;
/* 259:    */    try {
/* 260:251 */      SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
/* 261:252 */      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
/* 262:253 */      cipher.init(1, skey);
/* 263:254 */      crypted = cipher.doFinal(input.getBytes());
/* 264:    */    } catch (Exception e) {
/* 265:256 */      System.out.println(e.toString());
/* 266:    */    }
/* 267:258 */    return crypted;
/* 268:    */  }
/* 269:    */  
/* 270:    */  public static byte[] decrypt(String content, String key) {
/* 271:262 */    byte[] output = (byte[])null;
/* 272:    */    try {
/* 273:264 */      SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
/* 274:265 */      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
/* 275:266 */      cipher.init(2, skey);
/* 276:267 */      output = cipher.doFinal(Base64.decodeBase64(content.getBytes()));
/* 277:    */    } catch (Exception e) {
/* 278:269 */      System.out.println(e.toString());
/* 279:    */    }
/* 280:271 */    return output;
/* 281:    */  }
/* 282:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.util.WifiDogUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */