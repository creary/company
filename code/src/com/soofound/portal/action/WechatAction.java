/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.alibaba.fastjson.JSON;
/*   4:    */import com.alibaba.fastjson.JSONArray;
/*   5:    */import com.alibaba.fastjson.JSONObject;
/*   6:    */import com.soofound.admin.bean.UserDto;
/*   7:    */import com.soofound.admin.web.BranchService;
/*   8:    */import com.soofound.cpe.util.SoofacACS;
/*   9:    */import com.soofound.framework.util.CommonUtil;
/*  10:    */import com.soofound.framework.util.DateUtil;
/*  11:    */import com.soofound.framework.util.SysConfigHelper;
/*  12:    */import com.soofound.framework.web.GenericAction;
/*  13:    */import com.soofound.portal.bean.SurfingUser;
/*  14:    */import com.soofound.portal.bean.WechatGuideDto;
/*  15:    */import com.soofound.portal.dao.WechatGuideDao;
/*  16:    */import com.soofound.portal.dao.WechatIPPolicyDao;
/*  17:    */import com.soofound.portal.service.SurfingAccountService;
/*  18:    */import com.soofound.portal.service.WifiDogService;
/*  19:    */import com.soofound.protocol.wechat.WeChatTools;
/*  20:    */import com.soofound.protocol.wechat.message.Button;
/*  21:    */import com.soofound.protocol.wechat.message.NewsOutMessage;
/*  22:    */import com.soofound.protocol.wechat.message.UserInfo;
/*  23:    */import java.io.File;
/*  24:    */import java.io.PrintStream;
/*  25:    */import java.io.PrintWriter;
/*  26:    */import java.util.ArrayList;
/*  27:    */import java.util.Collection;
/*  28:    */import java.util.HashMap;
/*  29:    */import java.util.Iterator;
/*  30:    */import java.util.List;
/*  31:    */import javax.servlet.http.HttpServletResponse;
/*  32:    */import org.springframework.beans.factory.annotation.Autowired;
/*  33:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  34:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  35:    */import org.springframework.stereotype.Controller;
/*  36:    */import org.springframework.util.StringUtils;
/*  37:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  38:    */import org.springframework.web.multipart.MultipartFile;
/*  39:    */import org.springframework.web.multipart.MultipartHttpServletRequest;
/*  40:    */
/*  41:    */@Controller
/*  42:    */public class WechatAction extends GenericAction
/*  43:    */{
/*  44:    */  private static final String CLASS_NAME = "com.soofound.protocol.wechat.message.Articles";
/*  45:    */  private static final String SHOW_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
/*  46:    */  @Autowired
/*  47:    */  @Qualifier("defaultJdbcTemplate")
/*  48:    */  private JdbcTemplate jdbcTemplate;
/*  49:    */  @Autowired
/*  50:    */  private SoofacACS acs;
/*  51:    */  @Autowired
/*  52:    */  private BranchService branchSerivce;
/*  53:    */  @Autowired
/*  54:    */  private WifiDogService wds;
/*  55:    */  @Autowired
/*  56:    */  private SurfingAccountService sas;
/*  57:    */  @Autowired
/*  58:    */  private WechatGuideDao dao;
/*  59:    */  @Autowired
/*  60:    */  private WechatIPPolicyDao wipdao;
/*  61:    */  
/*  62:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/readyEditWechatBase.do"})
/*  63:    */  public String readyEditWechatBase(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  64:    */  {
/*  65: 65 */    String branchId = request.getParameter("branchId");
/*  66: 66 */    com.soofound.admin.bean.BranchDto bd = (com.soofound.admin.bean.BranchDto)this.branchSerivce.findByID(branchId);
/*  67: 67 */    model.addAttribute("dto", bd);
/*  68: 68 */    model.addAttribute("acsURL", this.acs.getAcsURL());
/*  69: 69 */    return "/admin/branch/wechatConnect.jsp";
/*  70:    */  }
/*  71:    */  
/*  72:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/saveWechatBase.do"})
/*  73:    */  @ResponseBody
/*  74: 74 */  public java.util.Map<String, Object> saveWechatBase(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String branchId = request.getParameter("branchId");
/*  75:    */    try {
/*  76: 76 */      StringBuilder sqlText = new StringBuilder(200);
/*  77: 77 */      sqlText.append("update admin_branch set app_id='").append(request.getParameter("appId"));
/*  78: 78 */      sqlText.append("',app_secret='").append(request.getParameter("appSecret"));
/*  79: 79 */      sqlText.append("',open_id='").append(request.getParameter("openId"));
/*  80: 80 */      sqlText.append("',public_name='").append(request.getParameter("publicName"));
/*  81: 81 */      sqlText.append("' where id='").append(branchId).append("'");
/*  82: 82 */      this.jdbcTemplate.update(sqlText.toString());
/*  83: 83 */      logOperation(request, "更新微信公号信息");
/*  84: 84 */      return super.getSucceedResult("更新成功");
/*  85:    */    } catch (Exception e) {
/*  86: 86 */      e.printStackTrace();
/*  87:    */    }
/*  88: 88 */    return super.getFailedResult("更新失败");
/*  89:    */  }
/*  90:    */  
/*  91:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/readyWechatAuth.do"})
/*  92:    */  public String readyWechatAuth(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  93: 93 */    model.addAttribute("acsURL", this.acs.getAcsURL());
/*  94: 94 */    String branchId = request.getParameter("branchId");
/*  95: 95 */    com.soofound.admin.bean.BranchDto dto = (com.soofound.admin.bean.BranchDto)this.branchSerivce.findByID(branchId);
/*  96: 96 */    if (dto == null) {
/*  97: 97 */      model.addAttribute("error", "公众号配置信息不正确.");
/*  98:    */    } else {
/*  99: 99 */      model.addAttribute("dto", dto);
/* 100:100 */      com.soofound.protocol.wechat.WeChatAPI api = new com.soofound.protocol.wechat.WeChatAPI(dto.getAppId(), dto.getAppSecret());
/* 101:101 */      if (api.getAccessToken() == null) {
/* 102:102 */        model.addAttribute("error", "公众号配置信息不正确！");
/* 103:103 */        model.addAttribute("wechatPublicAvailable", Boolean.valueOf(false));
/* 104:    */      } else {
/* 105:105 */        model.addAttribute("wechatPublicAvailable", Boolean.valueOf(true));
/* 106:    */      } }
/* 107:107 */    return "/admin/branch/wechatAuth.jsp";
/* 108:    */  }
/* 109:    */  
/* 110:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/uploadLogo.do"})
/* 111:    */  @ResponseBody
/* 112:112 */  public java.util.Map<String, Object> uploadVideo(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) { response.setContentType("text/json;charset=UTF-8");
/* 113:113 */    java.util.Map<String, Object> result = new HashMap();
/* 114:    */    try {
/* 115:115 */      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
/* 116:116 */      java.util.Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
/* 117:117 */      MultipartFile mf = (MultipartFile)fileMap.values().iterator().next();
/* 118:118 */      String fileName = mf.getOriginalFilename();
/* 119:119 */      boolean picFile = (fileName.endsWith(".jpg")) || (fileName.endsWith(".png")) || (fileName.endsWith(".gif"));
/* 120:120 */      if (!picFile) {
/* 121:121 */        result.put("error", Integer.valueOf(1));
/* 122:122 */        result.put("message", "请上传图片文件");
/* 123:123 */        return result;
/* 124:    */      }
/* 125:125 */      long fileLength = mf.getSize();
/* 126:126 */      if (fileLength > 1024000L) {
/* 127:127 */        result.put("error", Integer.valueOf(1));
/* 128:128 */        result.put("message", "上传文件不能大于1M");
/* 129:129 */        return result;
/* 130:    */      }
/* 131:131 */      String branchId = request.getParameter("branchId");
/* 132:132 */      String logoFile = SysConfigHelper.getAttribute("sysPath") + "/acs/msite/" + branchId + "_" + fileName;
/* 133:133 */      File dest = new File(logoFile);
/* 134:134 */      mf.transferTo(dest);
/* 135:    */      
/* 136:136 */      com.soofound.admin.bean.BranchDto dto = (com.soofound.admin.bean.BranchDto)this.branchSerivce.findByID(branchId);
/* 137:137 */      com.soofound.protocol.wechat.WeChatAPI api = new com.soofound.protocol.wechat.WeChatAPI(dto.getAppId(), dto.getAppSecret());
/* 138:138 */      if (api.getAccessToken() == null) {
/* 139:139 */        return super.getFailedResult("没有设置微信的AppID或AppSecret，不能生成二维码");
/* 140:    */      }
/* 141:141 */      String ticketUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + api.createQRCode(branchId);
/* 142:142 */      String qrcodeFile = SysConfigHelper.getAttribute("sysPath") + "/acs/msite/" + branchId + "_qrcode.jpg";
/* 143:143 */      boolean createOk = WeChatTools.createImageFromURL(ticketUrl, qrcodeFile);
/* 144:144 */      if (createOk)
/* 145:145 */        createOk = WeChatTools.composeImages(qrcodeFile, logoFile);
/* 146:146 */      if (createOk) {
/* 147:147 */        String qrcode = SysConfigHelper.CONTEXT_PATH + "acs/msite/" + branchId + "_qrcode.jpg";
/* 148:148 */        StringBuilder sqlText = new StringBuilder(200);
/* 149:149 */        sqlText.append("update admin_branch set qr_code='").append(qrcode).append("' where id='").append(branchId).append("'");
/* 150:150 */        this.jdbcTemplate.update(sqlText.toString());
/* 151:    */        
/* 152:152 */        result.put("error", Integer.valueOf(0));
/* 153:153 */        result.put("url", qrcode);
/* 154:154 */        return result;
/* 155:    */      }
/* 156:156 */      result.put("error", Integer.valueOf(1));
/* 157:157 */      result.put("message", "生成二维码失败.");
/* 158:158 */      return result;
/* 159:    */    } catch (Exception ex) {
/* 160:160 */      result.put("error", Integer.valueOf(1));
/* 161:161 */      result.put("message", ex.getMessage());
/* 162:    */    }
/* 163:163 */    return result;
/* 164:    */  }
/* 165:    */  
/* 166:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/getQRCode.do"})
/* 167:    */  @ResponseBody
/* 168:168 */  public java.util.Map<String, Object> getQRCode(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) { java.util.Map<String, Object> result = new HashMap();
/* 169:169 */    String branchId = request.getParameter("branchId");
/* 170:170 */    com.soofound.admin.bean.BranchDto dto = (com.soofound.admin.bean.BranchDto)this.branchSerivce.findByID(branchId);
/* 171:171 */    if ((dto != null) && (StringUtils.hasText(dto.getQrCode()))) {
/* 172:172 */      result.put("status", Integer.valueOf(1));
/* 173:173 */      result.put("data", dto.getQrCode());
/* 174:    */    } else {
/* 175:175 */      result.put("status", Integer.valueOf(0));
/* 176:176 */      result.put("data", "");
/* 177:    */    }
/* 178:178 */    return result;
/* 179:    */  }
/* 180:    */  
/* 181:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/createWechatMenu.do"})
/* 182:    */  @ResponseBody
/* 183:183 */  public java.util.Map<String, Object> createWechatMenu(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) { java.util.Map<String, Object> result = new HashMap();
/* 184:184 */    String branchId = request.getParameter("branchId");
/* 185:185 */    String type = request.getParameter("type");
/* 186:186 */    com.soofound.admin.bean.BranchDto dto = (com.soofound.admin.bean.BranchDto)this.branchSerivce.findByID(branchId);
/* 187:187 */    com.soofound.protocol.wechat.WeChatAPI api = new com.soofound.protocol.wechat.WeChatAPI(dto.getAppId(), dto.getAppSecret());
/* 188:188 */    List<Button> buttons = new ArrayList();
/* 189:189 */    Button bb = new Button();
/* 190:190 */    if ("view".equals(type)) {
/* 191:191 */      bb.setType("view");
/* 192:192 */      bb.setName("一键上网");
/* 193:193 */      bb.setKey("");
/* 194:194 */      bb.setUrl(request.getParameter("jumpToUrl"));
/* 195:    */    } else {
/* 196:196 */      bb.setType("click");
/* 197:197 */      bb.setName("一键上网");
/* 198:198 */      bb.setKey("wifi");
/* 199:    */    }
/* 200:200 */    buttons.add(bb);
/* 201:201 */    int callback = api.createMenu(buttons);
/* 202:202 */    if (callback == 0) {
/* 203:203 */      result.put("status", Integer.valueOf(1));
/* 204:204 */      result.put("data", "操作成功");
/* 205:205 */      super.logOperation(request, "生成[一键上网]菜单");
/* 206:    */    } else {
/* 207:207 */      result.put("status", Integer.valueOf(0));
/* 208:208 */      result.put("data", Integer.valueOf(callback));
/* 209:    */    }
/* 210:210 */    return result;
/* 211:    */  }
/* 212:    */  
/* 213:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/readyEditWechatResponse.do"})
/* 214:    */  public String readyEditWechatResponse(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 215:215 */    UserDto user = super.getCurrentUser(request);
/* 216:216 */    String wechatResponse = this.dao.getWechatResponse(user.getBranchId());
/* 217:217 */    if (wechatResponse == null) {
/* 218:218 */      model.addAttribute("welcMsgCoverType", "default");
/* 219:219 */      model.addAttribute("welcMsgHref", "");
/* 220:220 */      model.addAttribute("welcMsgTitle", "");
/* 221:221 */      model.addAttribute("showPCAuth", "1");
/* 222:222 */      model.addAttribute("imageMsgs", "");
/* 223:    */    } else {
/* 224:224 */      java.util.Map<String, String> jsons = (java.util.Map)JSON.parseObject(wechatResponse, java.util.Map.class);
/* 225:225 */      for (String pn : jsons.keySet()) {
/* 226:226 */        model.addAttribute(pn, jsons.get(pn));
/* 227:    */      }
/* 228:    */    }
/* 229:229 */    return "/admin/branch/wechatResponse.jsp";
/* 230:    */  }
/* 231:    */  
/* 232:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/wechatPublicAvailable.do"})
/* 233:    */  @ResponseBody
/* 234:234 */  public java.util.Map<String, Object> wechatPublicAvailable(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) { String branchId = request.getParameter("branchId");
/* 235:235 */    java.util.Map<String, Object> result = new HashMap();
/* 236:236 */    result.put("status", Integer.valueOf(1));
/* 237:    */    try {
/* 238:238 */      com.soofound.admin.bean.BranchDto dto = (com.soofound.admin.bean.BranchDto)this.branchSerivce.findByID(branchId);
/* 239:239 */      com.soofound.protocol.wechat.WeChatAPI api = new com.soofound.protocol.wechat.WeChatAPI(dto.getAppId(), dto.getAppSecret());
/* 240:240 */      if (api.getAccessToken() == null)
/* 241:241 */        result.put("status", Integer.valueOf(2));
/* 242:    */    } catch (Exception ex) {
/* 243:243 */      ex.printStackTrace();
/* 244:244 */      result.put("status", Integer.valueOf(2));
/* 245:    */    }
/* 246:246 */    return result;
/* 247:    */  }
/* 248:    */  
/* 249:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/readyEditWechatGuide.do"})
/* 250:    */  public String readyEditWechatGuide(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 251:251 */    UserDto user = getCurrentUser(request);
/* 252:252 */    WechatGuideDto dto = this.dao.findByID(user.getBranchId());
/* 253:253 */    if (dto == null)
/* 254:254 */      dto = WechatGuideDto.bornDefault();
/* 255:255 */    model.addAttribute("dto", dto);
/* 256:256 */    return "/admin/branch/wechatGuideEdit.jsp";
/* 257:    */  }
/* 258:    */  
/* 259:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/updateWechatGuide.do"})
/* 260:    */  @ResponseBody
/* 261:    */  public java.util.Map<String, Object> updateWechatGuide(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 262:262 */    try { UserDto user = getCurrentUser(request);
/* 263:263 */      String guideType = request.getParameter("guideType");
/* 264:264 */      WechatGuideDto dto = new WechatGuideDto();
/* 265:265 */      dto.setBranchId(user.getBranchId());
/* 266:266 */      dto.setFlag(guideType);
/* 267:267 */      if ("default".equals(guideType)) {
/* 268:268 */        dto.setPageValue("");
/* 269:    */      } else
/* 270:270 */        dto.setPageValue(request.getParameter("customGuide"));
/* 271:271 */      this.dao.save(dto);
/* 272:272 */      return super.getSucceedResult("保存成功");
/* 273:    */    } catch (Exception e) {}
/* 274:274 */    return super.getFailedResult("保存失败");
/* 275:    */  }
/* 276:    */  
/* 277:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/updateWechatResponse.do"})
/* 278:    */  @ResponseBody
/* 279:    */  public java.util.Map<String, Object> wechatSet(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 280:280 */    java.util.Map<String, String> jsons = new HashMap();
/* 281:281 */    jsons.put("welcMsgHref", request.getParameter("welcMsgHref"));
/* 282:282 */    jsons.put("welcMsgTitle", request.getParameter("welcMsgTitle"));
/* 283:283 */    jsons.put("welcMsgCover", request.getParameter("welcMsgCover"));
/* 284:284 */    if (request.getParameter("showPCAuth") != null) {
/* 285:285 */      jsons.put("showPCAuth", "1");
/* 286:    */    } else
/* 287:287 */      jsons.put("showPCAuth", "0");
/* 288:288 */    String content = JSON.toJSONString(jsons);
/* 289:289 */    UserDto user = super.getCurrentUser(request);
/* 290:290 */    this.dao.updateWechatResponse(user.getBranchId(), content);
/* 291:291 */    return super.getSucceedResult("更新成功");
/* 292:    */  }
/* 293:    */  
/* 294:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/updateWechatIPPolicy.do"})
/* 295:    */  @ResponseBody
/* 296:296 */  public java.util.Map<String, Object> updateWechatIPPolicy(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { UserDto user = getCurrentUser(request);
/* 297:    */    try {
/* 298:298 */      com.soofound.portal.bean.WechatIPPolicyDto dto = new com.soofound.portal.bean.WechatIPPolicyDto();
/* 299:299 */      dto.setId(Integer.parseInt(request.getParameter("id")));
/* 300:300 */      dto.setBranchId(user.getBranchId());
/* 301:301 */      dto.setIpAddress(request.getParameter("ip"));
/* 302:302 */      dto.setImgUrl(request.getParameter("msgCover"));
/* 303:303 */      dto.setPostUrl(request.getParameter("msgHref"));
/* 304:304 */      dto.setName(request.getParameter("name"));
/* 305:305 */      if (dto.getId() == 0) {
/* 306:306 */        this.wipdao.save(dto);
/* 307:    */      } else
/* 308:308 */        this.wipdao.update(dto);
/* 309:309 */      return getSucceedResult("更新成功");
/* 310:    */    } catch (Exception ex) {
/* 311:311 */      ex.printStackTrace(); }
/* 312:312 */    return getFailedResult("更新失败");
/* 313:    */  }
/* 314:    */  
/* 315:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/getWechatIPPolicies.do"})
/* 316:    */  @ResponseBody
/* 317:    */  public java.util.Map<String, Object> getWechatIPPolicies(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 318:318 */    java.util.Map<String, Object> result = new HashMap();
/* 319:319 */    result.put("status", Integer.valueOf(1));
/* 320:320 */    UserDto user = getCurrentUser(request);
/* 321:    */    try {
/* 322:322 */      List<com.soofound.portal.bean.WechatIPPolicyDto> dtos = this.wipdao.getWechatIPPolicies(user.getBranchId());
/* 323:323 */      if (!CommonUtil.isEmpty(dtos)) {
/* 324:324 */        List maps = new ArrayList();
/* 325:325 */        for (com.soofound.portal.bean.WechatIPPolicyDto dto : dtos)
/* 326:326 */          maps.add(dto.toMap());
/* 327:327 */        result.put("data", maps);
/* 328:    */      }
/* 329:    */    } catch (Exception ex) {
/* 330:330 */      ex.printStackTrace();
/* 331:    */    }
/* 332:332 */    return result;
/* 333:    */  }
/* 334:    */  
/* 335:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/wechatIPPolicyDelete.do"})
/* 336:    */  @ResponseBody
/* 337:    */  public java.util.Map<String, Object> wechatIPPolicyDelete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 338:338 */    try { String id = request.getParameter("id");
/* 339:339 */      this.wipdao.deleteWechatIPPolicy(id);
/* 340:340 */      return super.getSucceedResult("删除成功");
/* 341:    */    } catch (Exception ex) {
/* 342:342 */      ex.printStackTrace(); }
/* 343:343 */    return super.getFailedResult("删除失败");
/* 344:    */  }
/* 345:    */  
/* 346:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/readyEditWechatIPPolicy.do"})
/* 347:    */  public String readyEditWechatIPPolicy(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 348:    */  {
/* 349:349 */    String id = request.getParameter("id");
/* 350:350 */    com.soofound.portal.bean.WechatIPPolicyDto dto = null;
/* 351:351 */    if (id == null) {
/* 352:352 */      dto = new com.soofound.portal.bean.WechatIPPolicyDto();
/* 353:353 */      dto.setId(0);
/* 354:354 */      dto.setImgUrl("");
/* 355:355 */      dto.setPostUrl("");
/* 356:356 */      dto.setIpAddress("");
/* 357:    */    } else {
/* 358:358 */      dto = this.wipdao.findByID(id);
/* 359:    */    }
/* 360:360 */    model.addAttribute("dto", dto);
/* 361:361 */    return "/admin/branch/wechatResponsePolicyEdit.jsp";
/* 362:    */  }
/* 363:    */  
/* 364:    */  @org.springframework.web.bind.annotation.RequestMapping({"/acs/goWechatGuide.do"})
/* 365:    */  public String goWechatGuide(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 366:366 */    String branchId = request.getParameter("branchId");
/* 367:367 */    WechatGuideDto dto = this.dao.findByID(branchId);
/* 368:368 */    if (dto == null)
/* 369:369 */      dto = WechatGuideDto.bornDefault();
/* 370:370 */    if ("default".equals(dto.getFlag())) {
/* 371:371 */      model.addAttribute("guideType", "default");
/* 372:    */    } else {
/* 373:373 */      model.addAttribute("guideType", "custom");
/* 374:374 */      model.addAttribute("customGuide", dto.getPageValue());
/* 375:    */    }
/* 376:376 */    return "/admin/help/wechatGuide.jsp";
/* 377:    */  }
/* 378:    */  
/* 379:    */  @org.springframework.web.bind.annotation.RequestMapping({"/acs/wechat.do"})
/* 380:    */  @ResponseBody
/* 381:    */  public String wechat(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {
/* 382:    */    try {
/* 383:383 */      String echostr = request.getParameter("echostr");
/* 384:384 */      if (echostr != null) {
/* 385:385 */        String token = "mywifi";
/* 386:    */        
/* 387:387 */        String signature = request.getParameter("signature");
/* 388:    */        
/* 389:389 */        String timestamp = request.getParameter("timestamp");
/* 390:    */        
/* 391:391 */        String nonce = request.getParameter("nonce");
/* 392:392 */        PrintWriter out = response.getWriter();
/* 393:393 */        if (WeChatTools.checkSignature(token, signature, timestamp, nonce)) {
/* 394:394 */          System.out.println(signature + "--" + nonce + "--" + echostr);
/* 395:395 */          out.print(echostr);
/* 396:    */        }
/* 397:397 */        out.close();
/* 398:398 */        return null;
/* 399:    */      }
/* 400:400 */      com.soofound.protocol.wechat.message.InMessage im = WeChatTools.parsingInMessage(request.getInputStream());
/* 401:401 */      if (im == null) {
/* 402:402 */        System.out.println("wechat:解析错误.");
/* 403:403 */        return "解析错误.";
/* 404:    */      }
/* 405:405 */      com.soofound.admin.bean.BranchDto branch = this.branchSerivce.findByOpenId(im.getToUserName());
/* 406:406 */      if (branch == null) {
/* 407:407 */        System.out.println("OpenId not found:" + im.getToUserName());
/* 408:408 */        return WeChatTools.getTextMessage(im.getToUserName(), im.getFromUserName(), "感谢关注.");
/* 409:    */      }
/* 410:410 */      String branchId = branch.getId();
/* 411:411 */      StringBuilder content = new StringBuilder(100);
/* 412:    */      
/* 413:413 */      boolean isScan = "SCAN".equals(im.getEvent());
/* 414:    */      
/* 415:415 */      boolean isOneKey = (im.getMsgType() != null) && (im.getMsgType().equals("event")) && ("wifi".equalsIgnoreCase(im.getEventKey()));
/* 416:    */      
/* 417:417 */      boolean isInput = ("text".equals(im.getMsgType())) && ("wifi".equalsIgnoreCase(im.getContent().trim()));
/* 418:418 */      if ((isScan) || (isOneKey) || (isInput) || ("subscribe".equals(im.getEvent()))) {
/* 419:419 */        com.soofound.portal.bean.SurfingAccount sa = getSurfingAccount(im, branchId);
/* 420:420 */        String wechatResponse = this.dao.getWechatResponse(branchId);
/* 421:421 */        List<com.soofound.protocol.wechat.message.Articles> articles = new ArrayList();
/* 422:422 */        java.util.Map<String, String> jsons = null;
/* 423:423 */        if (wechatResponse == null) {
/* 424:424 */          jsons = new HashMap();
/* 425:425 */          jsons.put("welcMsgCoverType", "default");
/* 426:426 */          jsons.put("welcMsgHref", "");
/* 427:427 */          jsons.put("welcMsgTitle", "");
/* 428:428 */          jsons.put("showPCAuth", "1");
/* 429:429 */          jsons.put("imageMsgs", "");
/* 430:    */        } else {
/* 431:431 */          jsons = (java.util.Map)JSON.parseObject(wechatResponse, java.util.Map.class); }
/* 432:432 */        com.soofound.protocol.wechat.message.Articles art1 = new com.soofound.protocol.wechat.message.Articles();
/* 433:433 */        art1.setUrl((String)jsons.get("welcMsgHref"));
/* 434:434 */        if ("1".equals(jsons.get("showPCAuth"))) {
/* 435:435 */          content.append(",如果用PC上网,请使用以下用户名/密码:").append(sa.getUsername()).append(" / ").append(sa.getPassword());
/* 436:436 */          art1.setTitle((String)jsons.get("welcMsgTitle") + content.toString());
/* 437:    */        } else {
/* 438:438 */          art1.setTitle((String)jsons.get("welcMsgTitle")); }
/* 439:439 */        if (jsons.get("welcMsgCoverType") != null) {
/* 440:440 */          if ("default".equals(jsons.get("welcMsgCoverType"))) {
/* 441:441 */            art1.setPicUrl("http://www.soofound.com/site/welcomeWechat.do?wechatOpenId=" + sa.getOpenId() + "&pwx=" + im.getToUserName() + "&r=" + System.nanoTime());
/* 442:442 */          } else if (jsons.get("customWelcMsgCover") != null) {
/* 443:443 */            String url = (String)jsons.get("customWelcMsgCover");
/* 444:444 */            if (url.indexOf("?") == -1) {
/* 445:445 */              url = url + "?wechatOpenId=" + sa.getOpenId();
/* 446:    */            } else
/* 447:447 */              url = url + "&wechatOpenId=" + sa.getOpenId();
/* 448:448 */            url = url + "&r=" + System.nanoTime() + "&pwx=" + im.getToUserName();
/* 449:449 */            art1.setPicUrl(url);
/* 450:    */          }
/* 451:451 */          articles.add(art1);
/* 452:452 */          if (StringUtils.hasText((String)jsons.get("imageMsgs"))) {
/* 453:453 */            JSONArray array = (JSONArray)JSON.parse((String)jsons.get("imageMsgs"));
/* 454:454 */            for (int i = 0; i < array.size(); i++) {
/* 455:455 */              JSONObject jo = (JSONObject)array.get(i);
/* 456:456 */              com.soofound.protocol.wechat.message.Articles art = new com.soofound.protocol.wechat.message.Articles();
/* 457:457 */              art.setDescription(jo.getString("title"));
/* 458:458 */              art.setTitle(jo.getString("title"));
/* 459:459 */              art.setPicUrl(getImageURL(jo.getString("src")));
/* 460:460 */              art.setUrl(jo.getString("href"));
/* 461:461 */              articles.add(art);
/* 462:    */            }
/* 463:    */          }
/* 464:    */        } else {
/* 465:465 */          StringBuilder picUrl = new StringBuilder(100);
/* 466:466 */          picUrl.append("http://www.soofound.com/site/wechatResponse.do?wechatOpenId=").append(sa.getOpenId());
/* 467:467 */          picUrl.append("&pwx=").append(im.getToUserName()).append("&r=").append(System.nanoTime());
/* 468:468 */          picUrl.append("&acs=").append(this.acs.getAcsURL());
/* 469:469 */          art1.setPicUrl(picUrl.toString());
/* 470:470 */          art1.setUrl(this.acs.getAcsURL() + "/acs/getWechatResponseHref.do?pwx=" + im.getToUserName() + "&r=" + System.nanoTime());
/* 471:471 */          articles.add(art1);
/* 472:    */        }
/* 473:473 */        NewsOutMessage nom = new NewsOutMessage();
/* 474:474 */        nom.setToUserName(im.getFromUserName());
/* 475:475 */        nom.setFromUserName(im.getToUserName());
/* 476:476 */        nom.setCreateTime(Long.valueOf(DateUtil.getCurrentLongDateTime()));
/* 477:477 */        nom.setArticles(articles);
/* 478:478 */        return WeChatTools.outMessageToXML(nom).replace("com.soofound.protocol.wechat.message.Articles", "item");
/* 479:    */      }
/* 480:    */      
/* 481:481 */      if ("unsubscribe".equals(im.getEvent())) {
/* 482:482 */        System.out.println(branchId + "#取消关注#" + im.getFromUserName());
/* 483:483 */        com.soofound.portal.bean.SurfingAccount sa = this.sas.findByOpenId(im.getFromUserName());
/* 484:484 */        if (sa != null) {
/* 485:485 */          SurfingUser su = this.wds.getSurfingUser(branchId, sa.getMac());
/* 486:486 */          if (su != null) {
/* 487:487 */            this.wds.recordOffline(su);
/* 488:488 */            System.out.println("取消关注下线:" + su.getUsername());
/* 489:    */          }
/* 490:    */        }
/* 491:491 */        this.sas.deleteOpenId(im.getFromUserName());
/* 492:    */      }
/* 493:    */    } catch (Exception e) {
/* 494:494 */      System.out.println(request.getRemoteAddr() + "#wechat.error");
/* 495:495 */      e.printStackTrace();
/* 496:    */    }
/* 497:497 */    return null;
/* 498:    */  }
/* 499:    */  
/* 500:    */  private String getImageURL(String imgSrc) {
/* 501:501 */    int sl = imgSrc.indexOf("/", 1) + 1;
/* 502:502 */    return this.acs.getAcsURL() + imgSrc.substring(sl);
/* 503:    */  }
/* 504:    */  
/* 505:    */  private synchronized com.soofound.portal.bean.SurfingAccount getSurfingAccount(com.soofound.protocol.wechat.message.InMessage im, String branchId) {
/* 506:506 */    String nickname = "";
/* 507:507 */    com.soofound.admin.bean.BranchDto bd = (com.soofound.admin.bean.BranchDto)this.branchSerivce.findByID(branchId);
/* 508:508 */    if (bd != null) {
/* 509:509 */      com.soofound.protocol.wechat.WeChatAPI api = new com.soofound.protocol.wechat.WeChatAPI(bd.getAppId(), bd.getAppSecret());
/* 510:510 */      UserInfo info = api.getUserInfo(im.getFromUserName());
/* 511:511 */      if (info != null)
/* 512:512 */        nickname = info.getNickname();
/* 513:    */    }
/* 514:514 */    com.soofound.portal.bean.SurfingAccount sa = this.sas.findByOpenId(im.getFromUserName());
/* 515:515 */    if (sa == null) {
/* 516:516 */      sa = new com.soofound.portal.bean.SurfingAccount();
/* 517:517 */      sa.setUsername(this.sas.getNextWX(branchId));
/* 518:518 */      sa.setOpenId(im.getFromUserName());
/* 519:519 */      sa.setNickname(nickname);
/* 520:520 */      sa.setPassword(this.sas.getRandomPassword());
/* 521:521 */      sa.setBranchId(branchId);
/* 522:522 */      sa.setFlag("wechat");
/* 523:523 */      this.sas.save(sa);
/* 524:    */    } else {
/* 525:525 */      sa.setPassword(this.sas.getRandomPassword());
/* 526:526 */      sa.setNickname(nickname);
/* 527:527 */      this.sas.update(sa);
/* 528:    */    }
/* 529:529 */    return sa;
/* 530:    */  }
/* 531:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.WechatAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */