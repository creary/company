/*   1:    */package com.soofound.protocol.wechat;
/*   2:    */
/*   3:    */import com.alibaba.fastjson.JSON;
/*   4:    */import com.alibaba.fastjson.JSONArray;
/*   5:    */import com.alibaba.fastjson.JSONObject;
/*   6:    */import com.soofound.protocol.wechat.message.Articles;
/*   7:    */import com.soofound.protocol.wechat.message.Button;
/*   8:    */import com.soofound.protocol.wechat.message.UploadNews;
/*   9:    */import com.soofound.protocol.wechat.message.UserInfo;
/*  10:    */import java.io.File;
/*  11:    */import java.io.PrintStream;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.List;
/*  15:    */import java.util.Map;
/*  16:    */import org.apache.commons.lang.StringUtils;
/*  17:    */import org.apache.log4j.Logger;
/*  18:    */
/*  19:    */public class WeChatAPI
/*  20:    */{
/*  21: 21 */  private static final Logger loger = Logger.getLogger(WeChatAPI.class);
/*  22:    */  private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
/*  23:    */  private static final String USER_INFO_URI = "https://api.weixin.qq.com/cgi-bin/user/info";
/*  24:    */  private static final String USER_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get";
/*  25:    */  private static final String CUSTOM_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
/*  26:    */  private static final String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
/*  27:    */  private static final String MASS_SEND_ALL_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=";
/*  28:    */  private static final String MASS_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
/*  29:    */  private static final String UPLOAD_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=";
/*  30:    */  private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
/*  31:    */  private static final String CREATE_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
/*  32:    */  private String accessToken;
/*  33:    */  
/*  34:    */  public WeChatAPI(String appId, String appSecret)
/*  35:    */  {
/*  36: 36 */    this.accessToken = getAccessToken(appId, appSecret);
/*  37:    */  }
/*  38:    */  
/*  39:    */  private String getAccessToken(String appId, String appSecret) {
/*  40:    */    try {
/*  41: 41 */      String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential".concat("&appid=") + appId + "&secret=" + appSecret);
/*  42: 42 */      Map<String, Object> map = JSONObject.parseObject(jsonStr);
/*  43: 43 */      if (map.containsKey("errmsg")) {
/*  44: 44 */        System.out.println("appId=" + map.get("errmsg"));
/*  45: 45 */        return null;
/*  46:    */      }
/*  47: 47 */      return map.get("access_token").toString();
/*  48:    */    } catch (Exception ex) {
/*  49: 49 */      ex.printStackTrace();
/*  50:    */    }
/*  51: 51 */    return null;
/*  52:    */  }
/*  53:    */  
/*  55:    */  public UserInfo getUserInfo(String openid)
/*  56:    */  {
/*  57:    */    try
/*  58:    */    {
/*  59: 59 */      Map<String, String> params = new HashMap();
/*  60: 60 */      params.put("access_token", this.accessToken);
/*  61: 61 */      params.put("openid", openid);
/*  62: 62 */      String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/user/info", params);
/*  63: 63 */      if (StringUtils.isNotEmpty(jsonStr)) {
/*  64: 64 */        JSONObject obj = JSONObject.parseObject(jsonStr);
/*  65: 65 */        if (obj.get("errcode") != null) {
/*  66: 66 */          loger.error(obj.getString("errmsg"));
/*  67: 67 */          return null;
/*  68:    */        }
/*  69: 69 */        return (UserInfo)JSONObject.toJavaObject(obj, UserInfo.class);
/*  70:    */      }
/*  71:    */    }
/*  72:    */    catch (Exception ex) {
/*  73: 73 */      loger.error(ex);
/*  74:    */    }
/*  75: 75 */    return null;
/*  76:    */  }
/*  77:    */  
/*  80:    */  public String[] getFollwersList(String openid)
/*  81:    */  {
/*  82:    */    try
/*  83:    */    {
/*  84: 84 */      Map<String, String> params = new HashMap();
/*  85: 85 */      params.put("access_token", this.accessToken);
/*  86: 86 */      params.put("next_openid", openid);
/*  87: 87 */      String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/user/get", params);
/*  88: 88 */      if (StringUtils.isNotEmpty(jsonStr)) {
/*  89: 89 */        JSONObject obj = JSONObject.parseObject(jsonStr);
/*  90: 90 */        if (obj.get("errcode") != null) {
/*  91: 91 */          loger.error(obj.getString("errmsg"));
/*  92: 92 */          return null;
/*  93:    */        }
/*  94: 94 */        JSONObject data = (JSONObject)obj.get("data");
/*  95: 95 */        JSONArray array = data.getJSONArray("openid");
/*  96: 96 */        String[] list = new String[array.size()];
/*  97: 97 */        array.toArray(list);
/*  98: 98 */        return list;
/*  99:    */      }
/* 100:    */    } catch (Exception ex) {
/* 101:101 */      loger.error(ex);
/* 102:    */    }
/* 103:103 */    return null;
/* 104:    */  }
/* 105:    */  
/* 108:    */  public String uploadMedia(String type, File file)
/* 109:    */  {
/* 110:    */    try
/* 111:    */    {
/* 112:112 */      StringBuilder url = new StringBuilder(100);
/* 113:113 */      url.append("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=").append(this.accessToken).append("&type=").append(type);
/* 114:114 */      String jsonStr = HttpKit.upload(url.toString(), file);
/* 115:115 */      Map<String, Object> result = (Map)JSON.parseObject(jsonStr, Map.class);
/* 116:116 */      if (result.get("media_id") != null)
/* 117:117 */        return (String)result.get("media_id");
/* 118:118 */      System.out.println(result.get("errmsg"));
/* 119:    */    } catch (Exception ex) {
/* 120:120 */      loger.error(ex);
/* 121:    */    }
/* 122:122 */    return null;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public String uploadNews(List<UploadNews> news) {
/* 126:    */    try {
/* 127:127 */      Map<String, Object> newsmap = new HashMap();
/* 128:128 */      newsmap.put("articles", news);
/* 129:129 */      String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=" + this.accessToken;
/* 130:130 */      String jsonStr = HttpKit.post(url, JSONObject.toJSONString(newsmap));
/* 131:131 */      Map<String, Object> result = (Map)JSON.parseObject(jsonStr, Map.class);
/* 132:132 */      if (result.get("media_id") != null)
/* 133:133 */        return (String)result.get("media_id");
/* 134:134 */      System.out.println(result.get("errmsg"));
/* 135:    */    } catch (Exception ex) {
/* 136:136 */      loger.error(ex);
/* 137:    */    }
/* 138:138 */    return null;
/* 139:    */  }
/* 140:    */  
/* 141:    */  public int massSendAll(String groupId, String mediaId, String msgtype) {
/* 142:    */    try {
/* 143:143 */      Map<String, Object> params = new HashMap();
/* 144:144 */      Map<String, String> filterMap = new HashMap();
/* 145:145 */      filterMap.put("group_id", groupId);
/* 146:146 */      Map<String, String> mediaMap = new HashMap();
/* 147:147 */      mediaMap.put("media_id", mediaId);
/* 148:148 */      params.put("filter", filterMap);
/* 149:149 */      params.put("mpnews", mediaMap);
/* 150:150 */      params.put("msgtype", msgtype);
/* 151:151 */      String jsonMsg = JSONObject.toJSONString(params);
/* 152:152 */      String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + this.accessToken;
/* 153:153 */      String jsonStr = HttpKit.post(url, jsonMsg);
/* 154:154 */      Map<String, Object> result = (Map)JSON.parseObject(jsonStr, Map.class);
/* 155:155 */      return ((Integer)result.get("errcode")).intValue();
/* 156:    */    } catch (Exception ex) {
/* 157:157 */      loger.error(ex);
/* 158:    */    }
/* 159:159 */    return -1;
/* 160:    */  }
/* 161:    */  
/* 163:    */  private String sendMsg(Map<String, Object> message)
/* 164:    */    throws Exception
/* 165:    */  {
/* 166:166 */    return HttpKit.post("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=".concat(this.accessToken), JSONObject.toJSONString(message));
/* 167:    */  }
/* 168:    */  
/* 170:    */  public void sendText(String openId, String text)
/* 171:    */  {
/* 172:    */    try
/* 173:    */    {
/* 174:174 */      Map<String, Object> json = new HashMap();
/* 175:175 */      Map<String, Object> textObj = new HashMap();
/* 176:176 */      textObj.put("content", text);
/* 177:177 */      json.put("touser", openId);
/* 178:178 */      json.put("msgtype", "text");
/* 179:179 */      json.put("text", textObj);
/* 180:180 */      String result = sendMsg(json);
/* 181:181 */      JSONObject obj = JSONObject.parseObject(result);
/* 182:182 */      System.out.println(obj.getString("errmsg"));
/* 183:    */    } catch (Exception ex) {
/* 184:184 */      loger.error(ex);
/* 185:    */    }
/* 186:    */  }
/* 187:    */  
/* 188:    */  public void sendNew(String openId, Articles art) {
/* 189:189 */    List<Articles> articles = new ArrayList();
/* 190:190 */    articles.add(art);
/* 191:191 */    sendNews(openId, articles);
/* 192:    */  }
/* 193:    */  
/* 196:    */  public void sendNews(String openId, List<Articles> articles)
/* 197:    */  {
/* 198:    */    try
/* 199:    */    {
/* 200:200 */      Map<String, Object> json = new HashMap();
/* 201:201 */      json.put("touser", openId);
/* 202:202 */      json.put("msgtype", "news");
/* 203:203 */      Map<String, Object> articleJson = new HashMap();
/* 204:204 */      articleJson.put("articles", articles);
/* 205:205 */      json.put("news", articleJson);
/* 206:206 */      String result = sendMsg(json);
/* 207:207 */      JSONObject obj = JSONObject.parseObject(result);
/* 208:208 */      System.out.println(obj.getString("errmsg"));
/* 209:    */    } catch (Exception ex) {
/* 210:210 */      loger.error(ex);
/* 211:    */    }
/* 212:    */  }
/* 213:    */  
/* 214:    */  public void massSend(String[] openIds, String mediaId) {
/* 215:    */    try {
/* 216:216 */      Map<String, Object> params = new HashMap();
/* 217:217 */      params.put("touser", openIds);
/* 218:218 */      Map<String, String> mediaMap = new HashMap();
/* 219:219 */      mediaMap.put("media_id", mediaId);
/* 220:220 */      params.put("mpnews", mediaMap);
/* 221:221 */      params.put("msgtype", "mpnews");
/* 222:222 */      String jsonMsg = JSONObject.toJSONString(params);
/* 223:223 */      String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=" + this.accessToken;
/* 224:224 */      String jsonStr = HttpKit.post(url, jsonMsg);
/* 225:225 */      Map<String, Object> result = (Map)JSON.parseObject(jsonStr, Map.class);
/* 226:226 */      System.out.println(result.get("errmsg"));
/* 227:    */    } catch (Exception ex) {
/* 228:228 */      loger.error(ex);
/* 229:    */    }
/* 230:    */  }
/* 231:    */  
/* 232:    */  public int createMenu(List<Button> buttons) {
/* 233:    */    try {
/* 234:234 */      Map<String, Object> params = new HashMap();
/* 235:235 */      params.put("button", buttons);
/* 236:236 */      String jsonMsg = JSONObject.toJSONString(params);
/* 237:237 */      String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + this.accessToken;
/* 238:238 */      String jsonStr = HttpKit.post(url, jsonMsg);
/* 239:239 */      JSONObject obj = JSONObject.parseObject(jsonStr);
/* 240:240 */      return ((Integer)obj.get("errcode")).intValue();
/* 241:    */    } catch (Exception ex) {
/* 242:242 */      loger.error(ex);
/* 243:    */    }
/* 244:244 */    return -1;
/* 245:    */  }
/* 246:    */  
/* 247:    */  public String getAccessToken() {
/* 248:248 */    return this.accessToken;
/* 249:    */  }
/* 250:    */  
/* 251:    */  public String createQRCode(String branchId) {
/* 252:252 */    String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + this.accessToken;
/* 253:253 */    Map<String, Object> scene = new HashMap();
/* 254:254 */    Map<String, Object> scene2 = new HashMap();
/* 255:255 */    scene.put("scene_str", branchId);
/* 256:256 */    scene2.put("scene", scene);
/* 257:257 */    Map<String, Object> params = new HashMap();
/* 258:258 */    params.put("action_name", "QR_LIMIT_STR_SCENE");
/* 259:259 */    params.put("action_info", scene2);
/* 260:    */    try {
/* 261:261 */      String jsonMsg = JSONObject.toJSONString(params);
/* 262:262 */      String jsonStr = HttpKit.post(url, jsonMsg);
/* 263:263 */      JSONObject obj = JSONObject.parseObject(jsonStr);
/* 264:264 */      return (String)obj.get("ticket");
/* 265:    */    } catch (Exception ex) {
/* 266:266 */      loger.error(ex);
/* 267:    */    }
/* 268:268 */    return null;
/* 269:    */  }
/* 270:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.WeChatAPI
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */