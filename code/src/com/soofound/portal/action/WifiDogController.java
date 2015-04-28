/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.admin.web.BranchService;
/*   4:    */import com.soofound.cpe.bean.HostBean;
/*   5:    */import com.soofound.cpe.util.SoofacACS;
/*   6:    */import com.soofound.framework.util.CommonUtil;
/*   7:    */import com.soofound.framework.util.DateUtil;
/*   8:    */import com.soofound.framework.util.SysConfigHelper;
/*   9:    */import com.soofound.operation.cache.LookupCacheDao;
/*  10:    */import com.soofound.portal.bean.AdvertiseCategoryBean;
/*  11:    */import com.soofound.portal.bean.AdvertiseDto;
/*  12:    */import com.soofound.portal.bean.PortalInstanceDto;
/*  13:    */import com.soofound.portal.bean.PortalTemplate;
/*  14:    */import com.soofound.portal.bean.SurfingAccount;
/*  15:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*  16:    */import com.soofound.portal.bean.SurfingSession;
/*  17:    */import com.soofound.portal.bean.SurfingUser;
/*  18:    */import com.soofound.portal.bean.WechatShareDto;
/*  19:    */import com.soofound.portal.custom.CmccPortalHandler;
/*  20:    */import com.soofound.portal.custom.CustomFactory;
/*  21:    */import com.soofound.portal.dao.WechatShareDao;
/*  22:    */import com.soofound.portal.service.AdvertiseCategoryService;
/*  23:    */import com.soofound.portal.service.AdvertiseService;
/*  24:    */import com.soofound.portal.service.BlackWhiteService;
/*  25:    */import com.soofound.portal.service.PortalInstanceService;
/*  26:    */import com.soofound.portal.service.PortalTemplateService;
/*  27:    */import com.soofound.portal.service.SurfingAccountService;
/*  28:    */import com.soofound.portal.service.SurfingSessionService;
/*  29:    */import com.soofound.portal.service.WifiDogService;
/*  30:    */import com.soofound.portal.util.WifiDogUtils;
/*  31:    */import java.io.PrintStream;
/*  32:    */import java.util.List;
/*  33:    */import java.util.Map;
/*  34:    */import java.util.UUID;
/*  35:    */import javax.servlet.http.HttpServletRequest;
/*  36:    */import javax.servlet.http.HttpServletResponse;
/*  37:    */import javax.servlet.http.HttpSession;
/*  38:    */import net.jradius.client.RadiusClient;
/*  39:    */import net.jradius.dictionary.Attr_AcctSessionId;
/*  40:    */import net.jradius.dictionary.Attr_CalledStationId;
/*  41:    */import net.jradius.dictionary.Attr_FramedIPAddress;
/*  42:    */import net.jradius.dictionary.Attr_NASIdentifier;
/*  43:    */import net.jradius.dictionary.Attr_NASPortId;
/*  44:    */import net.jradius.dictionary.Attr_UserName;
/*  45:    */import net.jradius.dictionary.Attr_UserPassword;
/*  46:    */import net.jradius.packet.AccessAccept;
/*  47:    */import net.jradius.packet.AccessReject;
/*  48:    */import net.jradius.packet.AccessRequest;
/*  49:    */import net.jradius.packet.RadiusResponse;
/*  50:    */import net.jradius.packet.attribute.AttributeList;
/*  51:    */import net.jradius.packet.attribute.RadiusAttribute;
/*  52:    */import net.jradius.packet.attribute.value.AttributeValue;
/*  53:    */import org.apache.log4j.Logger;
/*  54:    */import org.springframework.beans.factory.annotation.Autowired;
/*  55:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  56:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  57:    */import org.springframework.stereotype.Controller;
/*  58:    */import org.springframework.ui.ModelMap;
/*  59:    */import org.springframework.util.StringUtils;
/*  60:    */import org.springframework.web.bind.annotation.PathVariable;
/*  61:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  62:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  63:    */
/*  72:    */@Controller
/*  73:    */public class WifiDogController
/*  74:    */{
/*  75:    */  private static final long TEN_MINUTES = 600000L;
/*  76: 76 */  private Logger loger = Logger.getLogger("WifiDog");
/*  77:    */  private static final String PING_RESULT = "Pong result=";
/*  78:    */  private static final String AUTH_DENIED = "Auth: 0";
/*  79:    */  private static final String AUTH_ALLOWED = "Auth: 1";
/*  80:    */  private static final String TR069_RESULT = "TR069";
/*  81:    */  @Autowired
/*  82:    */  @Qualifier("lookupCacheDaoProxy")
/*  83:    */  private LookupCacheDao cacheDao;
/*  84:    */  @Autowired
/*  85:    */  private WechatShareDao sdao;
/*  86:    */  @Autowired
/*  87:    */  private SoofacACS acs;
/*  88:    */  @Autowired
/*  89:    */  private AdvertiseService ads;
/*  90:    */  
/*  91:    */  @RequestMapping({"/wifiant/login/"})
/*  92:    */  public String getWelcomeView(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*  93:    */  {
/*  94: 84 */    if (request.getQueryString() == null) {
/*  95: 85 */      model.addAttribute("error", "您接入了未授权的AP，为避免安全风险，请先退出。");
/*  96: 86 */      return "/common/hint.jsp";
/*  97:    */    }
/*  98: 88 */    Map<String, String> params = WifiDogUtils.decodeURL(request.getQueryString());
/*  99: 89 */    String apmac = (String)params.get("apmac");
/* 100: 90 */    String stamac = (String)params.get("stamac");
/* 101: 91 */    HostBean host = this.cacheDao.getCacheByApmac(apmac);
/* 102: 92 */    if (host == null) {
/* 103: 93 */      System.out.println(request.getRemoteAddr() + "--invalid AP--" + (String)params.get("orgUrl"));
/* 104: 94 */      model.addAttribute("error", "您接入了未授权的AP，为避免安全风险，请先退出。");
/* 105: 95 */      return "/common/hint.jsp";
/* 106:    */    }
/* 107: 97 */    String ua = request.getHeader("user-agent");
/* 108: 98 */    String ssid = host.getId() + "-" + (String)params.get("intf");
/* 109: 99 */    SurfingPolicyDto spd = this.cacheDao.getCacheByPolicy(ssid);
/* 110:100 */    boolean isp = isIPhoneURL(ua, (String)params.get("url"));
/* 111:    */    
/* 114:104 */    SurfingUser user = this.service.getSurfingUser(host.getBranchId(), stamac);
/* 115:105 */    if ((user != null) && (user.getStatus() > 0)) {
/* 116:106 */      StringBuilder wifiDogUrl = new StringBuilder(200);
/* 117:107 */      wifiDogUrl.append("http://").append((String)params.get("gwip")).append(":2060/wifidog/auth?token=");
/* 118:108 */      wifiDogUrl.append(UUID.randomUUID().toString());
/* 119:109 */      return "redirect:" + wifiDogUrl.toString();
/* 120:    */    }
/* 121:    */    
/* 124:114 */    String branchIdAndMac = host.getBranchId() + "-" + stamac;
/* 125:115 */    if (this.bws.isBlack(branchIdAndMac)) {
/* 126:116 */      model.addAttribute("error", "你已经被列入黑名单，请联系管理员。");
/* 127:117 */      return "/common/hint.jsp";
/* 128:    */    }
/* 129:    */    
/* 132:122 */    boolean noFeeling = false;
/* 133:    */    
/* 136:126 */    if ((spd.getRedirect() == 0) || (this.bws.isWhite(branchIdAndMac))) {
/* 137:127 */      noFeeling = true;
/* 138:    */    }
/* 139:    */    
/* 142:132 */    SurfingAccount sa = null;
/* 143:133 */    if ((!noFeeling) && (spd.getSecondFree() == 1)) {
/* 144:134 */      sa = this.sas.findByMac(host.getBranchId(), stamac);
/* 145:135 */      if (sa != null)
/* 146:136 */        noFeeling = true;
/* 147:    */    }
/* 148:138 */    if (noFeeling) {
/* 149:139 */      if (user == null) {
/* 150:140 */        user = new SurfingUser();
/* 151:141 */        if (sa == null) {
/* 152:142 */          user.setUsername(stamac.replace(":", ""));
/* 153:143 */          user.setFlag("free");
/* 154:    */        } else {
/* 155:145 */          user.setUsername(sa.getUsername());
/* 156:146 */          user.setFlag(sa.getFlag());
/* 157:    */        }
/* 158:148 */        user.setBranchId(host.getBranchId());
/* 159:149 */        user.setCpeId(host.getId());
/* 160:150 */        user.setCpeMac(apmac);
/* 161:151 */        user.setTerminalMac(stamac);
/* 162:152 */        user.setTerminalIP((String)params.get("staip"));
/* 163:153 */        user.setSsid(ssid);
/* 164:154 */        if (isp) {
/* 165:155 */          user.setRoamUrl("http://www.qq.com");
/* 166:    */        } else
/* 167:157 */          user.setRoamUrl((String)params.get("url"));
/* 168:158 */        this.service.recordOnline(user);
/* 169:159 */        StringBuilder wifiDogUrl = new StringBuilder(100);
/* 170:160 */        wifiDogUrl.append("http://").append((String)params.get("gwip")).append(":2060/wifidog/auth?token=");
/* 171:161 */        wifiDogUrl.append(UUID.randomUUID().toString());
/* 172:162 */        return "redirect:" + wifiDogUrl.toString();
/* 173:    */      }
/* 174:164 */      if (isp)
/* 175:165 */        return "redirect:http://www.qq.com";
/* 176:166 */      return "redirect:" + (String)params.get("url");
/* 177:    */    }
/* 178:    */    
/* 181:171 */    int loc = ((String)params.get("orgUrl")).indexOf("wechatOpenId=");
/* 182:172 */    if (loc > 0) {
/* 183:173 */      StringBuilder qstr = new StringBuilder(100);
/* 184:174 */      qstr.append("redirect:/wifiant/wechatOnekey.do?ssid=").append(ssid).append("&");
/* 185:175 */      qstr.append(((String)params.get("orgUrl")).replace("?", "&"));
/* 186:176 */      return qstr.toString();
/* 187:    */    }
/* 188:    */    
/* 191:181 */    if ((spd.getWechatShare() == 1) && (ua != null) && (
/* 192:182 */      (ua.indexOf("MicroMessenger") > 0) || (ua.indexOf("Android") > 0) || (ua.indexOf("iPhone") > 0) || (ua.indexOf("iPad") > 0))) {
/* 193:183 */      PortalInstanceDto pid = this.pis.getPortal(ssid);
/* 194:184 */      List<WechatShareDto> wsds = this.sdao.getShareByPortal(pid.getId());
/* 195:185 */      if (wsds.size() > 0) {
/* 196:186 */        List<Integer> sids = this.sdao.getShareIds(stamac);
/* 197:187 */        WechatShareDto _wsd = null;
/* 198:188 */        for (WechatShareDto wsd : wsds) {
/* 199:189 */          if (!sids.contains(Integer.valueOf(wsd.getId()))) {
/* 200:190 */            _wsd = wsd;
/* 201:191 */            break;
/* 202:    */          }
/* 203:    */        }
/* 204:194 */        if (_wsd != null) {
/* 205:195 */          StringBuilder jurl = new StringBuilder(100);
/* 206:196 */          jurl.append("redirect:").append(this.acs.getAcsURL()).append("wifiant/readyShare.do?sta=").append(WifiDogUtils.encodeURL(stamac));
/* 207:197 */          jurl.append("&sid=").append(_wsd.getId()).append("&ssid=").append(ssid).append("&branchId=").append(host.getBranchId());
/* 208:198 */          int ul = ((String)params.get("orgUrl")).indexOf("url=");
/* 209:199 */          if (ul > 0)
/* 210:200 */            jurl.append("&").append(((String)params.get("orgUrl")).substring(0, ul));
/* 211:201 */          return jurl.toString();
/* 212:    */        }
/* 213:    */      }
/* 214:    */    }
/* 215:    */    
/* 218:208 */    if (spd.getCmcc() == 1) {
/* 219:209 */      StringBuilder urlstr = new StringBuilder(200);
/* 220:210 */      urlstr.append(spd.getPortalUrl());
/* 221:211 */      if (spd.getPortalUrl().indexOf("?") > 0) {
/* 222:212 */        urlstr.append("&");
/* 223:    */      } else
/* 224:214 */        urlstr.append("?");
/* 225:215 */      String ip = (String)params.get("staip");
/* 226:216 */      urlstr.append("wlanuserip=").append(ip).append("&wlanusermac=").append(stamac).append("&wlanacname=").append(this.acs.getRealm());
/* 227:217 */      urlstr.append("&apmac=").append(apmac).append("&apintf=").append((String)params.get("intf"));
/* 228:    */      
/* 229:219 */      System.out.println(urlstr.toString());
/* 230:220 */      return "redirect:" + urlstr.toString();
/* 231:    */    }
/* 232:222 */    StringBuilder qstr = new StringBuilder(200);
/* 233:223 */    qstr.append(((String)params.get("orgUrl")).substring(0, ((String)params.get("orgUrl")).indexOf("&url=")));
/* 234:224 */    if (((String)params.get("orgUrl")).indexOf("apmac") == -1)
/* 235:225 */      qstr.append("&apmac=").append(host.getSerialNumber());
/* 236:226 */    qstr.append("&ssid=").append(ssid).append("&branchId=").append(host.getBranchId());
/* 237:227 */    qstr.append("&url=").append((String)params.get("url"));
/* 238:228 */    model.addAttribute("queryString", qstr.toString());
/* 239:    */    
/* 240:230 */    PortalInstanceDto portal = this.pis.getPortal(ssid);
/* 241:231 */    PortalTemplate tpl = this.pts.getPortalTemplate(portal.getTid());
/* 242:232 */    model.addAllAttributes(tpl.getProps());
/* 243:233 */    model.addAllAttributes(this.pts.getModuleValues());
/* 244:234 */    model.addAllAttributes(portal.getPage("global"));
/* 245:235 */    boolean auth = spd.getAuth() == 1;
/* 246:236 */    if ((noFeeling) && (spd.getSecondFree() == 2))
/* 247:237 */      auth = false;
/* 248:238 */    model.addAttribute("auth", Boolean.valueOf(auth));
/* 249:239 */    String jsp = "welcome";
/* 250:240 */    if ((spd.getWechatGuide() == 1) && (!noFeeling))
/* 251:241 */      jsp = "guide";
/* 252:242 */    model.addAllAttributes(portal.getPage(jsp));
/* 253:243 */    jsp = portal.getPath() + "view/" + jsp + ".jsp";
/* 254:244 */    return jsp;
/* 255:    */  }
/* 256:    */  
/* 257:    */  @Autowired
/* 258:    */  private AdvertiseCategoryService pcs;
/* 259:    */  @Autowired
/* 260:    */  private BlackWhiteService bws;
/* 261:    */  
/* 262:    */  @RequestMapping({"/wifiant/portal/"})
/* 263:    */  public String portal(HttpServletRequest request, ModelMap model)
/* 264:    */  {
/* 265:249 */    Map<String, String> params = WifiDogUtils.decodeURL(request.getQueryString());
/* 266:250 */    HostBean host = this.cacheDao.getCacheByApmac((String)params.get("apmac"));
/* 267:251 */    if (host == null) {
/* 268:252 */      model.addAttribute("error", "您接入了未授权的AP，为避免安全风险，请先退出。");
/* 269:253 */      return "/common/hint.jsp";
/* 270:    */    }
/* 271:255 */    SurfingUser user = this.service.getSurfingUser(host.getBranchId(), (String)params.get("stamac"));
/* 272:256 */    if (user == null) {
/* 273:257 */      model.addAttribute("error", "您的终端未通过认证，请先认证。");
/* 274:258 */      return "/common/hint.jsp";
/* 275:    */    }
/* 276:260 */    if ((user.getRoamUrl() != null) && (user.getRoamUrl().indexOf("soofound.com") > 0)) {
/* 277:261 */      StringBuilder urlstr = new StringBuilder(100);
/* 278:262 */      urlstr.append("/acs/getWechatResponseImage.do");
/* 279:263 */      int loc = user.getRoamUrl().indexOf("?");
/* 280:264 */      if (loc > 0) {
/* 281:265 */        urlstr.append(user.getRoamUrl().substring(loc));
/* 282:    */      } else {
/* 283:267 */        System.out.println("==ex roam url==" + user.getRoamUrl());
/* 284:268 */        urlstr.append(user.getRoamUrl());
/* 285:    */      }
/* 286:270 */      return urlstr.toString();
/* 287:    */    }
/* 288:272 */    SurfingPolicyDto spd = this.cacheDao.getCacheByPolicy(user.getSsid());
/* 289:273 */    String branchIdAndMac = host.getBranchId() + "-" + (String)params.get("stamac");
/* 290:274 */    boolean noFeeling = (spd.getRedirect() == 0) || (this.bws.isWhite(branchIdAndMac));
/* 291:    */    
/* 294:278 */    if ((noFeeling) && (!CommonUtil.isEmpty(user.getRoamUrl()))) {
/* 295:279 */      String jumpUrl = "redirect:" + user.getRoamUrl();
/* 296:280 */      user.setRoamUrl(null);
/* 297:281 */      return jumpUrl;
/* 298:    */    }
/* 299:    */    
/* 302:286 */    if ((spd.getJumpTo() == 2) && (!CommonUtil.isEmpty(spd.getJumpUrl()))) {
/* 303:287 */      return "redirect:" + spd.getJumpUrl();
/* 304:    */    }
/* 305:    */    
/* 308:292 */    if ((spd.getJumpTo() == 3) && (!CommonUtil.isEmpty((String)params.get("url")))) {
/* 309:293 */      return "redirect:" + (String)params.get("url");
/* 310:    */    }
/* 311:295 */    PortalInstanceDto portal = this.pis.getPortal(user.getSsid());
/* 312:296 */    if (portal == null) {
/* 313:297 */      System.out.println(request.getRemoteAddr() + "##SSID Not Portal2:" + user.getSsid());
/* 314:298 */      portal = PortalInstanceDto.bornDefaultPortal();
/* 315:    */    }
/* 316:300 */    model.addAttribute("pid", Integer.valueOf(portal.getId()));
/* 317:301 */    model.addAllAttributes(this.pts.getPortalTemplate(portal.getTid()).getProps());
/* 318:302 */    model.addAllAttributes(this.pts.getModuleValues());
/* 319:303 */    model.addAllAttributes(portal.getPage("global"));
/* 320:304 */    model.addAllAttributes(portal.getPage("authorized"));
/* 321:305 */    setCategoryAndArticles(host.getBranchId(), request);
/* 322:306 */    return portal.getPath() + "/view/authorized.jsp";
/* 323:    */  }
/* 324:    */  
/* 325:    */  @Autowired
/* 326:    */  private PortalTemplateService pts;
/* 327:    */  @Autowired
/* 328:    */  private PortalInstanceService pis;
/* 329:    */  
/* 330:    */  @RequestMapping({"/wifiant/{page}/view.do"})
/* 331:    */  public String portalView(@PathVariable String page, HttpServletRequest request, ModelMap model)
/* 332:    */  {
/* 333:311 */    Map<String, String> params = WifiDogUtils.decodeURL(request.getQueryString());
/* 334:    */    try {
/* 335:313 */      PortalInstanceDto portal = null;
/* 336:314 */      String pid = request.getParameter("pid");
/* 337:315 */      if ("article".equals(page)) {
/* 338:316 */        portal = this.pis.getPortalByID(pid);
/* 339:317 */        AdvertiseDto ad = (AdvertiseDto)this.ads.findByID(request.getParameter("aid"));
/* 340:318 */        model.addAttribute("currentArticle", ad);
/* 341:    */      } else {
/* 342:320 */        if ((params.get("ssid") == null) || ("0".equals(params.get("ssid")))) {
/* 343:321 */          HostBean host = this.cacheDao.getCacheByApmac((String)params.get("apmac"));
/* 344:322 */          if (host != null) {
/* 345:323 */            params.put("ssid", host.getId() + "-0");
/* 346:324 */            System.out.println("ssid-->>>1#" + (String)params.get("orgUrl"));
/* 347:    */          }
/* 348:    */        }
/* 349:327 */        if (params.get("ssid") == null) {
/* 350:328 */          params.put("ssid", "0-0");
/* 351:329 */          if ("0".equals(pid)) {
/* 352:330 */            List<PortalInstanceDto> pids = this.pis.findByBranch((String)params.get("branchId"));
/* 353:331 */            if (!CommonUtil.isEmpty(pids)) {
/* 354:332 */              portal = (PortalInstanceDto)pids.get(0);
/* 355:333 */              System.out.println("ssid-->>>2#" + (String)params.get("orgUrl"));
/* 356:    */            }
/* 357:    */          } else {
/* 358:336 */            portal = this.pis.getPortalByID(pid);
/* 359:    */          }
/* 360:338 */        } else { portal = this.pis.getPortal((String)params.get("ssid")); }
/* 361:339 */        SurfingPolicyDto spd = this.cacheDao.getCacheByPolicy((String)params.get("ssid"));
/* 362:340 */        if ((spd != null) && (spd.getAuth() == 1)) {
/* 363:341 */          model.addAttribute("auth", Boolean.valueOf(true));
/* 364:342 */          model.addAttribute("pwdAuth", Boolean.valueOf(spd.getPwdAuth() == 1));
/* 365:343 */          model.addAttribute("smsAuth", Boolean.valueOf(spd.getSmsAuth() == 1));
/* 366:344 */          model.addAttribute("wechatAuth", Boolean.valueOf(spd.getWechatAuth() == 1));
/* 367:345 */          if (spd.getSmsAuth() == 1) {
/* 368:346 */            model.addAttribute("smsFlag", Integer.valueOf(spd.getSmsFlag()));
/* 369:    */          } else
/* 370:348 */            model.addAttribute("smsFlag", Integer.valueOf(1));
/* 371:    */        } else {
/* 372:350 */          model.addAttribute("auth", Boolean.valueOf(false));
/* 373:351 */          model.addAttribute("pwdAuth", Boolean.valueOf(false));
/* 374:352 */          model.addAttribute("smsAuth", Boolean.valueOf(false));
/* 375:353 */          model.addAttribute("wechatAuth", Boolean.valueOf(false));
/* 376:    */        }
/* 377:    */      }
/* 378:356 */      model.addAllAttributes(this.pts.getPortalTemplate(portal.getTid()).getProps());
/* 379:357 */      model.addAllAttributes(this.pts.getModuleValues());
/* 380:358 */      model.addAllAttributes(portal.getPage(page));
/* 381:359 */      model.addAllAttributes(portal.getPage("global"));
/* 382:360 */      model.addAttribute("queryString", params.get("orgUrl"));
/* 383:361 */      model.addAttribute("ssid", params.get("ssid"));
/* 384:362 */      model.addAttribute("branch", this.branchService.findByID((String)params.get("branchId")));
/* 385:363 */      model.addAttribute("pid", Integer.valueOf(portal.getId()));
/* 386:364 */      setCategoryAndArticles((String)params.get("branchId"), request);
/* 387:365 */      return portal.getPath() + "view/" + page + ".jsp";
/* 388:    */    } catch (Exception ex) {
/* 389:367 */      System.out.println(request.getRemoteAddr() + "==portalView.error==" + (String)params.get("orgUrl"));
/* 390:368 */      ex.printStackTrace();
/* 391:    */      
/* 392:370 */      model.addAttribute("error", "无效的SSID"); }
/* 393:371 */    return "/common/hint.jsp";
/* 394:    */  }
/* 395:    */  
/* 396:    */  @Autowired
/* 397:    */  private WifiDogService service;
/* 398:    */  @Autowired
/* 399:    */  private SurfingSessionService sss;
/* 400:    */  
/* 401:    */  @RequestMapping({"/wifiant/freeAuth.do"})
/* 402:    */  public String freeAuth(HttpServletRequest request, ModelMap model)
/* 403:    */  {
/* 404:376 */    Map<String, String> params = WifiDogUtils.decodeURL(request.getQueryString());
/* 405:377 */    String mac = (String)params.get("stamac");
/* 406:378 */    String apmac = (String)params.get("apmac");
/* 407:379 */    HostBean host = this.cacheDao.getCacheByApmac(apmac);
/* 408:380 */    if (host == null) {
/* 409:381 */      model.addAttribute("error", "您接入了未授权的AP，为避免安全风险，请先退出。");
/* 410:382 */      return "/common/hint.jsp";
/* 411:    */    }
/* 412:384 */    SurfingUser user = this.service.getSurfingUser(host.getBranchId(), mac);
/* 413:385 */    if (user != null) {
/* 414:386 */      return "/wifiant/portal/?" + request.getQueryString();
/* 415:    */    }
/* 416:388 */    SurfingAccount sa = this.sas.findByMac(host.getBranchId(), mac);
/* 417:389 */    user = new SurfingUser();
/* 418:390 */    String ssid = host.getId() + "-" + (String)params.get("intf");
/* 419:391 */    SurfingPolicyDto spd = this.cacheDao.getCacheByPolicy(ssid);
/* 420:392 */    if (spd.getJumpTo() == 3)
/* 421:393 */      user.setRoamUrl((String)params.get("url"));
/* 422:394 */    user.setSsid(ssid);
/* 423:395 */    if (sa == null) {
/* 424:396 */      user.setUsername(mac.replace(":", ""));
/* 425:397 */      user.setFlag("free");
/* 426:    */    } else {
/* 427:399 */      user.setUsername(sa.getUsername());
/* 428:400 */      user.setFlag(sa.getFlag());
/* 429:    */    }
/* 430:402 */    user.setBranchId(host.getBranchId());
/* 431:403 */    user.setCpeId(host.getId());
/* 432:404 */    user.setCpeMac(apmac);
/* 433:405 */    user.setTerminalMac(mac);
/* 434:406 */    user.setTerminalIP((String)params.get("staip"));
/* 435:407 */    if (params.get("intf") != null)
/* 436:408 */      user.setIfe(Integer.parseInt((String)params.get("intf")));
/* 437:409 */    setSpeed(user, null, spd);
/* 438:410 */    this.service.recordOnline(user);
/* 439:    */    
/* 440:412 */    request.getSession().setAttribute("wifiUser", user);
/* 441:413 */    StringBuilder wifiDogUrl = new StringBuilder(100);
/* 442:414 */    wifiDogUrl.append("http://").append((String)params.get("gwip")).append(":2060/wifidog/auth?token=");
/* 443:415 */    wifiDogUrl.append(UUID.randomUUID().toString());
/* 444:416 */    model.addAttribute("wifiDogUrl", wifiDogUrl.toString());
/* 445:417 */    return "redirect:" + wifiDogUrl.toString();
/* 446:    */  }
/* 447:    */  
/* 448:    */  @Autowired
/* 449:    */  private SurfingAccountService sas;
/* 450:    */  
/* 451:    */  @RequestMapping({"/wifiant/wechatOnekey.do"})
/* 452:    */  public String wechatOnekey(HttpServletRequest request, ModelMap model)
/* 453:    */  {
/* 454:422 */    Map<String, String> params = WifiDogUtils.decodeURL(request.getQueryString());
/* 455:423 */    String apmac = (String)params.get("apmac");
/* 456:424 */    String stamac = (String)params.get("stamac");
/* 457:425 */    HostBean host = this.cacheDao.getCacheByApmac(apmac);
/* 458:426 */    if (host == null) {
/* 459:427 */      this.loger.error("===无效AP#wechatOnekey===");
/* 460:428 */      model.addAttribute("error", "您接入了未授权的AP，为避免安全风险，请先退出。");
/* 461:429 */      return "/common/hint.jsp";
/* 462:    */    }
/* 463:431 */    if (stamac == null) {
/* 464:432 */      model.addAttribute("error", "MAC地址不存在.");
/* 465:433 */      return "/common/hint.jsp";
/* 466:    */    }
/* 467:435 */    String wechatOpenId = (String)params.get("wechatOpenId");
/* 468:    */    
/* 471:439 */    SurfingAccount sa = null;
/* 472:440 */    if ("public".equals(wechatOpenId)) {
/* 473:441 */      String username = stamac.replace(":", "");
/* 474:442 */      sa = this.sas.findByUsername(host.getBranchId(), username);
/* 475:443 */      if (sa == null) {
/* 476:444 */        sa = new SurfingAccount();
/* 477:445 */        sa.setUsername(username);
/* 478:446 */        sa.setOpenId("-");
/* 479:447 */        sa.setPassword("-");
/* 480:448 */        sa.setNickname("");
/* 481:449 */        sa.setMac(stamac);
/* 482:450 */        sa.setBranchId(host.getBranchId());
/* 483:451 */        sa.setFlag("wechat");
/* 484:452 */        this.sas.save(sa);
/* 485:    */      } else {
/* 486:454 */        sa.setFlag("wechat");
/* 487:455 */        sa.setMac(stamac);
/* 488:456 */        this.sas.update(sa);
/* 489:    */      }
/* 490:    */    } else {
/* 491:459 */      sa = this.sas.findByOpenId(wechatOpenId);
/* 492:    */    }
/* 493:461 */    if (sa == null) {
/* 494:462 */      model.addAttribute("error", "无效的OpenID:" + wechatOpenId);
/* 495:463 */      System.out.println(request.getRemoteAddr() + "#无效的OpenID:" + wechatOpenId);
/* 496:464 */      return "/common/hint.jsp";
/* 497:    */    }
/* 498:466 */    sa.setBranchId(host.getBranchId());
/* 499:467 */    sa.setFlag("wechat");
/* 500:468 */    sa.setMac(stamac);
/* 501:469 */    sa.setTimes(1);
/* 502:470 */    this.sas.update(sa);
/* 503:    */    
/* 504:472 */    SurfingUser user = this.service.getSurfingUser(host.getBranchId(), sa.getUsername());
/* 505:473 */    if (user == null) {
/* 506:474 */      user = new SurfingUser();
/* 507:475 */      if (params.get("pwx") == null) {
/* 508:476 */        user.setRoamUrl((String)params.get("url"));
/* 509:    */      } else
/* 510:478 */        user.setRoamUrl((String)params.get("url") + "?pwx=" + (String)params.get("pwx"));
/* 511:479 */      user.setUsername(sa.getUsername());
/* 512:480 */      user.setBranchId(host.getBranchId());
/* 513:481 */      user.setCpeId(host.getId());
/* 514:482 */      user.setCpeMac(apmac);
/* 515:483 */      user.setTerminalMac(stamac.toUpperCase());
/* 516:484 */      user.setTerminalIP(request.getParameter("staip"));
/* 517:485 */      user.setFlag(sa.getFlag());
/* 518:486 */      user.setSsid((String)params.get("ssid"));
/* 519:487 */      user.setIfe(Integer.parseInt(((String)params.get("ssid")).split("-")[1]));
/* 520:488 */      SurfingPolicyDto spd = this.cacheDao.getCacheByPolicy((String)params.get("ssid"));
/* 521:489 */      setSpeed(user, sa, spd);
/* 522:490 */      user.setOpenId(wechatOpenId);
/* 523:491 */      this.service.recordOnline(user);
/* 524:    */      
/* 525:493 */      StringBuilder wifiDogUrl = new StringBuilder(200);
/* 526:494 */      wifiDogUrl.append("http://").append((String)params.get("gwip")).append(":2060/wifidog/auth?token=");
/* 527:495 */      wifiDogUrl.append(UUID.randomUUID().toString());
/* 528:496 */      return "redirect:" + wifiDogUrl.toString();
/* 529:    */    }
/* 530:498 */    return "redirect:" + (String)params.get("url"); }
/* 531:    */  
/* 532:    */  @Autowired
/* 533:    */  private BranchService branchService;
/* 534:    */  @RequestMapping({"/wifiant/authenticate.do"})
/* 535:    */  @ResponseBody
/* 536:504 */  public Map<String, Object> authenticate(HttpServletRequest request, ModelMap model) { try { int loc = request.getQueryString().indexOf("&random=");
/* 537:505 */      String queryStr = null;
/* 538:506 */      if (loc == -1) {
/* 539:507 */        queryStr = request.getQueryString();
/* 540:    */      } else
/* 541:509 */        queryStr = request.getQueryString().substring(0, loc);
/* 542:510 */      Map<String, String> params = WifiDogUtils.decodeURL(queryStr);
/* 543:511 */      String mac = (String)params.get("stamac");
/* 544:512 */      String apmac = (String)params.get("apmac");
/* 545:513 */      String staip = (String)params.get("staip");
/* 546:514 */      String username = request.getParameter("username");
/* 547:515 */      String password = request.getParameter("password");
/* 548:516 */      if (username != null)
/* 549:517 */        username = username.replace("'", "");
/* 550:518 */      if (password != null)
/* 551:519 */        password = password.replace("'", "");
/* 552:520 */      String error = null;
/* 553:521 */      HostBean host = this.cacheDao.getCacheByApmac(apmac);
/* 554:522 */      if (host == null) {
/* 555:523 */        System.out.println(request.getRemoteAddr() + "$/wifiant/authenticate/" + (String)params.get("orgUrl"));
/* 556:524 */        error = "您接入了未授权的AP，为避免安全风险，请先退出。";
/* 557:    */      }
/* 558:526 */      if (error != null)
/* 559:527 */        return WifiDogUtils.getSDTResult(Integer.valueOf(2), error);
/* 560:528 */      String ssid = host.getId() + "-" + (String)params.get("intf");
/* 561:529 */      SurfingPolicyDto spd = this.cacheDao.getCacheByPolicy(ssid);
/* 562:530 */      if (spd.getOneAccountDevice() == 1) {
/* 563:531 */        List<SurfingUser> sus = this.service.getSurfingUser2(host.getBranchId(), username);
/* 564:532 */        if ((!CommonUtil.isEmpty(sus)) && (!((SurfingUser)sus.get(0)).getTerminalMac().equals(mac)))
/* 565:533 */          return WifiDogUtils.getSDTResult(Integer.valueOf(2), "此帐号已经在使用(在线)");
/* 566:    */      }
/* 567:535 */      if (spd.getStayTime() > 0) {
/* 568:536 */        SurfingSession stat = this.sss.getUserForStayTime(host.getBranchId(), username);
/* 569:537 */        if ((stat != null) && (stat.getSessionTime() >= spd.getStayTime() * 60))
/* 570:538 */          return WifiDogUtils.getSDTResult(Integer.valueOf(2), "一天内上网时间不能超过" + spd.getStayTime() + "分钟");
/* 571:    */      }
/* 572:540 */      String flag = null;
/* 573:541 */      SurfingAccount sa = null;
/* 574:    */      
/* 577:545 */      AttributeList attrs = null;
/* 578:546 */      if (this.acs.getRealm().equals("zhongxing")) {
/* 579:547 */        flag = "radius";
/* 580:548 */        CmccPortalHandler cph = (CmccPortalHandler)SysConfigHelper.getBean("zhongxingHandler");
/* 581:549 */        attrs = new AttributeList();
/* 582:550 */        attrs.add(new Attr_UserName(username));
/* 583:551 */        attrs.add(new Attr_UserPassword(password));
/* 584:552 */        attrs.add(new Attr_NASIdentifier("edras128.0.0.99"));
/* 585:553 */        attrs.add(new Attr_AcctSessionId(DateUtil.getCurrentTimeAsID()));
/* 586:554 */        attrs.add(new Attr_FramedIPAddress(staip));
/* 587:555 */        attrs.add(new Attr_CalledStationId(mac));
/* 588:556 */        attrs.add(new Attr_NASPortId(apmac));
/* 589:    */        
/* 590:558 */        AccessRequest ar = new AccessRequest(cph.getRadiusClient(), attrs);
/* 591:559 */        RadiusResponse reply = cph.getRadiusClient().authenticate(ar, null, 3);
/* 592:560 */        if ((reply instanceof AccessReject)) {
/* 593:561 */          AccessReject aj = (AccessReject)reply;
/* 594:562 */          String err = null;
/* 595:563 */          if ((aj.getAttributes() != null) && (aj.getAttributes().get("Reply-Message") != null)) {
/* 596:564 */            err = aj.getAttributes().get("Reply-Message").getValue().toString();
/* 597:    */          } else
/* 598:566 */            err = "用户名或密码错误.";
/* 599:567 */          return WifiDogUtils.getSDTResult(Double.valueOf(2.1D), err);
/* 600:    */        }
/* 601:569 */        AccessAccept aap = (AccessAccept)reply;
/* 602:570 */        attrs.add(aap.getAttributes().get("Huawei-Input-Average-Rate"));
/* 603:571 */        attrs.add(aap.getAttributes().get("Huawei-Output-Average-Rate"));
/* 604:    */      } else {
/* 605:573 */        sa = this.sas.findByAuth(host.getBranchId(), username, password);
/* 606:574 */        if (sa == null)
/* 607:575 */          return WifiDogUtils.getSDTResult(Integer.valueOf(2), "用户名或密码不正确");
/* 608:576 */        if ((sa.getFlag().equals("mobile")) && (spd.getValidity() > 0)) {
/* 609:577 */          boolean expired = DateUtil.getDiffMinutes(sa.getCreateTime(), DateUtil.getCurrentDateTime()) > spd.getValidity();
/* 610:578 */          if (expired)
/* 611:579 */            return WifiDogUtils.getSDTResult(Double.valueOf(2.1D), "对不起，您的密码已过期，请重新申请密码。");
/* 612:    */        }
/* 613:581 */        sa.setMac(mac);
/* 614:582 */        this.sas.update(sa);
/* 615:583 */        flag = sa.getFlag();
/* 616:    */      }
/* 617:585 */      SurfingUser user = this.service.getSurfingUser(host.getBranchId(), mac);
/* 618:586 */      if (user == null) {
/* 619:587 */        user = new SurfingUser();
/* 620:588 */        user.setTerminalMac(mac);
/* 621:    */      } else {
/* 622:590 */        this.service.recordOffline(user);
/* 623:591 */        user.setSessionId(null);
/* 624:    */      }
/* 625:593 */      if (spd.getJumpTo() == 3)
/* 626:594 */        user.setRoamUrl((String)params.get("url"));
/* 627:595 */      user.setSsid(ssid);
/* 628:596 */      user.setUsername(username);
/* 629:597 */      user.setBranchId(host.getBranchId());
/* 630:598 */      user.setCpeId(host.getId());
/* 631:599 */      user.setCpeMac(apmac);
/* 632:600 */      user.setTerminalMac(mac);
/* 633:601 */      user.setTerminalIP(staip);
/* 634:602 */      user.setFlag(flag);
/* 635:603 */      user.setIfe(Integer.parseInt((String)params.get("intf")));
/* 636:604 */      if (this.acs.getRealm().equals("zhongxing")) {
/* 637:605 */        user.setSessionId(WifiDogUtils.getMyUUID());
/* 638:606 */        this.cf.getAttributes().put(user.getSessionId(), attrs);
/* 639:    */      } else {
/* 640:608 */        setSpeed(user, sa, spd); }
/* 641:609 */      this.service.recordOnline(user);
/* 642:    */      
/* 643:611 */      StringBuilder wifiDogUrl = new StringBuilder(200);
/* 644:612 */      Map<String, Object> result = WifiDogUtils.getSDTResult(Integer.valueOf(1), "认证成功");
/* 645:613 */      wifiDogUrl.append("http://").append((String)params.get("gwip")).append(":2060/wifidog/auth?token=");
/* 646:614 */      wifiDogUrl.append(UUID.randomUUID().toString());
/* 647:615 */      result.put("wifidog", wifiDogUrl.toString());
/* 648:616 */      return result;
/* 649:    */    } catch (Exception ex) {
/* 650:618 */      model.addAttribute("error", ex.getMessage());
/* 651:619 */      ex.printStackTrace();
/* 652:    */    }
/* 653:621 */    return WifiDogUtils.getSDTResult(Integer.valueOf(2), "认证失败");
/* 654:    */  }
/* 655:    */  
/* 656:    */  @RequestMapping({"/wifiant/auth/"})
/* 657:    */  @ResponseBody
/* 658:626 */  public String auth(HttpServletRequest request, ModelMap model) { String stamac = request.getParameter("mac").toUpperCase();
/* 659:627 */    String apmac = request.getParameter("apmac");
/* 660:628 */    String token = request.getParameter("token");
/* 661:629 */    String stage = request.getParameter("stage");
/* 662:630 */    int ife = 0;
/* 663:631 */    if (request.getParameter("intf") != null)
/* 664:632 */      ife = Integer.parseInt(request.getParameter("intf"));
/* 665:    */    try {
/* 666:634 */      HostBean host = this.cacheDao.getCacheByApmac(apmac);
/* 667:635 */      if (host == null) {
/* 668:636 */        System.out.println(request.getRemoteAddr() + "#AP doesn't exist:" + apmac);
/* 669:637 */        return "Auth: 0";
/* 670:    */      }
/* 671:639 */      SurfingUser user = this.service.getSurfingUser(host.getBranchId(), stamac);
/* 672:640 */      if ("WXFX".equals(token)) {
/* 673:641 */        if ((user == null) && (this.ads.isSharingMac(stamac))) {
/* 674:642 */          SurfingAccount sa = this.sas.getWechatShareAccount(host.getBranchId(), stamac);
/* 675:643 */          System.out.println(sa.getUsername() + "#login by wechat share...");
/* 676:644 */          user = new SurfingUser();
/* 677:645 */          user.setUsername(sa.getUsername());
/* 678:646 */          user.setBranchId(host.getBranchId());
/* 679:647 */          user.setCpeId(host.getId());
/* 680:648 */          user.setCpeMac(apmac);
/* 681:649 */          user.setTerminalMac(stamac);
/* 682:650 */          user.setTerminalIP(request.getParameter("ip"));
/* 683:651 */          user.setFlag("wechat");
/* 684:652 */          user.setIfe(ife);
/* 685:653 */          user.setSsid(host.getId() + "-" + user.getIfe());
/* 686:654 */          this.service.recordOnline(user);
/* 687:655 */          this.ads.addShareMac(stamac);
/* 688:656 */          return "Auth: 1"; }
/* 689:657 */        if ((user != null) && (user.getStatus() == 0)) {
/* 690:658 */          System.out.println(user.getUsername() + "#logout from wechat share...");
/* 691:659 */          return "Auth: 0";
/* 692:    */        }
/* 693:    */      }
/* 694:662 */      if (user == null)
/* 695:    */      {
/* 696:664 */        return "Auth: 0";
/* 697:    */      }
/* 698:666 */      String oldSsid = user.getSsid();
/* 699:667 */      if (ife > 0)
/* 700:668 */        user.setIfe(ife);
/* 701:669 */      user.setSsid(host.getId() + "-" + user.getIfe());
/* 702:    */      
/* 705:673 */      SurfingPolicyDto spd = this.cacheDao.getCacheByPolicy(user.getSsid());
/* 706:674 */      if ("radius".equals(user.getFlag())) {
/* 707:    */        try {
/* 708:676 */          AttributeList attrs = (AttributeList)this.cf.getAttributes().get(user.getSessionId());
/* 709:677 */          if (attrs == null) break label628;
/* 710:678 */          RadiusAttribute attr1 = attrs.get("Huawei-Input-Average-Rate");
/* 711:679 */          user.setUpSpeed(Long.parseLong(attr1.getValue().toString()) / 1024L);
/* 712:680 */          RadiusAttribute attr2 = attrs.get("Huawei-Output-Average-Rate");
/* 713:681 */          user.setDownSpeed(Long.parseLong(attr2.getValue().toString()) / 1024L);
/* 714:    */        }
/* 715:    */        catch (Exception ex) {
/* 716:684 */          ex.printStackTrace();
/* 717:    */        }
/* 718:    */      } else {
/* 719:687 */        SurfingAccount sa = this.cacheDao.getCacheByAccount(user.getBranchId(), user.getUsername());
/* 720:688 */        if (spd != null)
/* 721:689 */          setSpeed(user, sa, spd); }
/* 722:    */      label628:
/* 723:691 */      if ("logout".equals(stage)) {
/* 724:692 */        System.out.println("logout#" + request.getQueryString());
/* 725:693 */        this.service.recordOffline(user);
/* 726:694 */        return "Auth: 0";
/* 727:    */      }
/* 728:696 */      long incoming = 0L;
/* 729:697 */      long outgoing = 0L;
/* 730:698 */      int snr = 0;
/* 731:699 */      if (StringUtils.hasText(request.getParameter("snr")))
/* 732:700 */        snr = Integer.parseInt(request.getParameter("snr"));
/* 733:701 */      incoming = Long.parseLong(request.getParameter("incoming"));
/* 734:702 */      outgoing = Long.parseLong(request.getParameter("outgoing"));
/* 735:703 */      if (incoming / 1024L > 2147483647L) {
/* 736:704 */        incoming = 0L;
/* 737:705 */        System.out.println("big incoming=" + request.getParameter("incoming"));
/* 738:    */      }
/* 739:707 */      if (outgoing / 1024L > 2147483647L) {
/* 740:708 */        outgoing = 0L;
/* 741:709 */        System.out.println("big outgoing=" + request.getParameter("outgoing"));
/* 742:    */      }
/* 743:711 */      user.setSnr(snr - 95);
/* 744:712 */      if ((incoming >= user.getInputOctets()) || (outgoing >= user.getOutputOctets())) {
/* 745:713 */        user.setInputOctets(incoming);
/* 746:714 */        user.setOutputOctets(outgoing);
/* 747:    */      } else {
/* 748:716 */        user.setInputOctets(user.getInputOctets() + incoming);
/* 749:717 */        user.setOutputOctets(user.getOutputOctets() + outgoing);
/* 750:    */      }
/* 751:719 */      if (user.getCpeId() == host.getId()) {
/* 752:720 */        this.service.recordTraffic(user);
/* 753:    */      } else {
/* 754:722 */        if (spd.getSeparatePortal() == 1) {
/* 755:723 */          int pid = 0;
/* 756:724 */          PortalInstanceDto oldPortal = this.pis.getPortal(oldSsid);
/* 757:725 */          if (oldPortal != null) {
/* 758:726 */            pid = oldPortal.getId();
/* 759:    */          }
/* 760:728 */          PortalInstanceDto newPortal = this.pis.getPortal(user.getSsid());
/* 761:729 */          if ((newPortal != null) && (pid != newPortal.getId())) {
/* 762:730 */            this.service.recordOffline(user);
/* 763:731 */            System.out.println(host.getName() + "#" + user.getTerminalMac() + "#因portal不一样而下线");
/* 764:732 */            if (oldPortal != null)
/* 765:733 */              System.out.println("portal#from " + oldPortal.getName() + " to " + newPortal.getName());
/* 766:734 */            System.out.println("host#from " + user.getCpeId() + " to " + host.getId());
/* 767:735 */            return "Auth: 0";
/* 768:    */          }
/* 769:    */        }
/* 770:738 */        user.setCpeId(host.getId());
/* 771:739 */        this.service.recordRoaming(user);
/* 772:    */      }
/* 773:    */      
/* 774:742 */      return user.getAuth();
/* 775:    */    } catch (Exception ex) {
/* 776:744 */      ex.printStackTrace();
/* 777:    */    }
/* 778:746 */    return "Auth: 0"; }
/* 779:    */  
/* 780:    */  @Autowired
/* 781:    */  @Qualifier("customFactory")
/* 782:750 */  private CustomFactory cf; private void setSpeed(SurfingUser user, SurfingAccount sa, SurfingPolicyDto spd) { if ((sa != null) && (sa.getDownSpeed() > 0)) {
/* 783:751 */      user.setDownSpeed(sa.getDownSpeed());
/* 784:    */    } else
/* 785:753 */      user.setDownSpeed(spd.getDownSpeed());
/* 786:754 */    if ((sa != null) && (sa.getUpSpeed() > 0)) {
/* 787:755 */      user.setUpSpeed(sa.getUpSpeed());
/* 788:    */    } else
/* 789:757 */      user.setUpSpeed(spd.getUpSpeed());
/* 790:    */  }
/* 791:    */  
/* 792:    */  private void setCategoryAndArticles(String branchId, HttpServletRequest request) {
/* 793:761 */    List<AdvertiseCategoryBean> cates = this.pcs.getBranchCategories(branchId);
/* 794:762 */    request.setAttribute("categories", cates);
/* 795:763 */    int intCid = 0;
/* 796:    */    try {
/* 797:765 */      intCid = Integer.parseInt(request.getParameter("cid"));
/* 798:    */    }
/* 799:    */    catch (Exception localException) {}
/* 800:768 */    request.setAttribute("cid", Integer.valueOf(intCid));
/* 801:769 */    request.setAttribute("branchId", branchId);
/* 802:770 */    if (!cates.isEmpty()) {
/* 803:771 */      for (AdvertiseCategoryBean cate : cates) {
/* 804:772 */        if (intCid == cate.getId()) {
/* 805:773 */          request.setAttribute("currentCategory", cate);
/* 806:774 */          break;
/* 807:    */        }
/* 808:    */      }
/* 809:    */    }
/* 810:778 */    request.setAttribute("articles", this.ads.getBranchAdvertises(branchId));
/* 811:    */  }
/* 812:    */  
/* 813:    */  private boolean isIPhoneURL(String ua, String urlstr) {
/* 814:782 */    if ((ua != null) && 
/* 815:783 */      ((ua.indexOf("CaptiveNetworkSupport") >= 0) || (ua.indexOf("iPhone") >= 0) || (ua.indexOf("iPad") >= 0)) && 
/* 816:784 */      (this.acs.isIPhoneURL(urlstr))) {
/* 817:785 */      return true;
/* 818:    */    }
/* 819:    */    
/* 820:788 */    return false;
/* 821:    */  }
/* 822:    */  
/* 823:    */  @RequestMapping({"/wifiant/ping/"})
/* 824:    */  @ResponseBody
/* 825:793 */  public String ping(HttpServletRequest request, ModelMap model) { String apmac = request.getParameter("apmac");
/* 826:794 */    HostBean host = this.cacheDao.getCacheByApmac(apmac);
/* 827:795 */    if (host.getOnline() == 0) {
/* 828:796 */      long lt = DateUtil.getCurrentLongDateTime() - DateUtil.dateTimeToLong(host.getLastTime());
/* 829:797 */      if (lt >= 600000L) {
/* 830:798 */        String sql1 = "update cpe_host set last_time = now() where serial_number='" + apmac + "'";
/* 831:799 */        StringBuffer sqlText2 = new StringBuffer(200);
/* 832:800 */        sqlText2.append("insert into cpe_device_log(id,host_id,operator,message,log_time)values('").append(DateUtil.getCurrentTimeAsID());
/* 833:801 */        sqlText2.append(host.getId()).append("','").append(host.getId()).append("','系统','重启网管协议',now())");
/* 834:    */        
/* 835:803 */        JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 836:804 */        jdbc.batchUpdate(new String[] { sql1, sqlText2.toString() });
/* 837:805 */        System.out.println(apmac + " ### restart tr069");
/* 838:806 */        return "TR069";
/* 839:    */      }
/* 840:    */    }
/* 841:809 */    return "Pong result=";
/* 842:    */  }
/* 843:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.WifiDogController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */