/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.soofound.acside.bean.CpeCustomDao;
/*   4:    */import com.soofound.admin.bean.BranchDto;
/*   5:    */import com.soofound.admin.web.BranchService;
/*   6:    */import com.soofound.cpe.bean.BssidDto;
/*   7:    */import com.soofound.cpe.bean.ConfigParamBean;
/*   8:    */import com.soofound.cpe.bean.PropertyBean;
/*   9:    */import com.soofound.cpe.bean.SoftwareBean;
/*  10:    */import com.soofound.cpe.dao.BssidDao;
/*  11:    */import com.soofound.cpe.dao.PropertyDao;
/*  12:    */import com.soofound.cpe.dao.SoftwareDao;
/*  13:    */import com.soofound.cpe.util.CpeUtils;
/*  14:    */import com.soofound.cpe.util.CpeWaker;
/*  15:    */import com.soofound.framework.web.BaseAction;
/*  16:    */import com.soofound.operation.bean.ScheduleTimeDao;
/*  17:    */import com.soofound.operation.bean.ScheduleTimeDto;
/*  18:    */import com.soofound.portal.bean.PortalInstanceDto;
/*  19:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*  20:    */import com.soofound.portal.service.PortalInstanceService;
/*  21:    */import com.soofound.portal.service.SurfingPolicyService;
/*  22:    */import java.io.PrintStream;
/*  23:    */import java.util.ArrayList;
/*  24:    */import java.util.HashMap;
/*  25:    */import java.util.Map;
/*  26:    */import org.apache.commons.lang.StringUtils;
/*  27:    */import org.springframework.beans.factory.annotation.Autowired;
/*  28:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  29:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  30:    */import org.springframework.stereotype.Controller;
/*  31:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  32:    */
/*  33:    */@Controller
/*  34:    */public class HostPropertyAction extends BaseAction<HostPropertyService>
/*  35:    */{
/*  36:    */  @Autowired
/*  37:    */  private BssidDao bdao;
/*  38:    */  @Autowired
/*  39:    */  private SurfingPolicyService sps;
/*  40:    */  @Autowired
/*  41:    */  private PortalInstanceService pis;
/*  42:    */  @Autowired
/*  43:    */  @Qualifier("defaultJdbcTemplate")
/*  44:    */  private JdbcTemplate jdbc;
/*  45:    */  @Autowired
/*  46:    */  private CpeCustomDao ccdao;
/*  47:    */  @Autowired
/*  48:    */  private BssidDao smdao;
/*  49:    */  @Autowired
/*  50:    */  private BranchService branchService;
/*  51:    */  @Autowired
/*  52:    */  private HostService hostService;
/*  53:    */  @Autowired
/*  54:    */  private SoftwareService ss;
/*  55:    */  
/*  56:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readySetting.do"})
/*  57:    */  public String readySetting(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  58:    */  {
/*  59: 59 */    boolean batchMode = "1".equals(request.getParameter("batchMode"));
/*  60:    */    try {
/*  61: 61 */      model.addAttribute("batchMode", Integer.valueOf(1));
/*  62: 62 */      String idstr = null;
/*  63: 63 */      String[] hostIds = request.getParameterValues("checkbox");
/*  64: 64 */      if (hostIds == null) {
/*  65: 65 */        idstr = request.getParameter("id");
/*  66: 66 */        model.addAttribute("deviceNum", Integer.valueOf(idstr.split(",").length));
/*  67:    */      } else {
/*  68: 68 */        idstr = com.soofound.framework.util.CommonUtil.arrayToString(hostIds);
/*  69: 69 */        model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/*  70:    */      }
/*  71: 71 */      model.addAttribute("id", idstr);
/*  72: 72 */      String[] ids = idstr.split(",");
/*  73: 73 */      com.soofound.cpe.bean.HostBean _host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(ids[0]);
/*  74: 74 */      if ((_host.getStun().equals("ros")) || (_host.getStun().equals("unifi"))) {
/*  75: 75 */        java.util.List<PortalInstanceDto> pids = this.pis.findByBranch(_host.getBranchId());
/*  76: 76 */        model.addAttribute("ssid", this.bdao.findByKey(ids[0], "0"));
/*  77: 77 */        model.addAttribute("portals", pids);
/*  78: 78 */        java.util.List<SurfingPolicyDto> spds = this.sps.findByBranch(_host.getBranchId());
/*  79: 79 */        model.addAttribute("policies", spds);
/*  80: 80 */        model.addAttribute("host", _host);
/*  81: 81 */        if (batchMode) {
/*  82: 82 */          return "/cpe/struct/rosSsidBatchEdit.jsp";
/*  83:    */        }
/*  84: 84 */        return "/cpe/struct/rosSsidEdit.jsp";
/*  85:    */      }
/*  86: 86 */      boolean allHas5G = true;
/*  87: 87 */      boolean allHasDetect = true;
/*  88: 88 */      for (int i = 0; i < ids.length; i++) {
/*  89: 89 */        com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(ids[i]);
/*  90: 90 */        if (i == 0) {
/*  91: 91 */          model.addAttribute("host", host);
/*  92: 92 */          java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = ((HostPropertyService)this.service).findByHost(host.getId());
/*  93: 93 */          model.addAttribute("productClass", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.ProductClass"));
/*  94: 94 */          model.addAttribute("productModel", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.HardwareVersion"));
/*  95: 95 */          model.addAttribute("producter", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.Manufacturer"));
/*  96: 96 */          model.addAttribute("productDescr", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.ProductModel"));
/*  97:    */        }
/*  98: 98 */        if (allHas5G)
/*  99: 99 */          allHas5G = host.getHardwareVersion().endsWith("-A");
/* 100:100 */        if (allHasDetect)
/* 101:101 */          allHasDetect = host.getHardwareVersion().endsWith("-M");
/* 102:    */      }
/* 103:103 */      model.addAttribute("has5G", Boolean.valueOf(allHas5G));
/* 104:104 */      model.addAttribute("hasDetect", Boolean.valueOf(allHasDetect));
/* 105:105 */      if (batchMode) {
/* 106:106 */        return "/cpe/device/settingBatch.jsp";
/* 107:    */      }
/* 108:108 */      String hostId = request.getParameter("id");
/* 109:109 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostId);
/* 110:110 */      model.addAttribute("hasDetect", Boolean.valueOf(host.getHardwareVersion().endsWith("-M")));
/* 111:111 */      model.addAttribute("id", hostId);
/* 112:112 */      model.addAttribute("host", host);
/* 113:113 */      java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = ((HostPropertyService)this.service).findByHost(hostId);
/* 114:114 */      if (!com.soofound.framework.util.CommonUtil.isEmpty(props)) {
/* 115:115 */        model.addAttribute("productClass", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.ProductClass"));
/* 116:116 */        model.addAttribute("productModel", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.HardwareVersion"));
/* 117:117 */        model.addAttribute("status", CpeUtils.getModeIcon(host.getMode(), host.getOnline(), host.getDiffsec(), host.getLastTime()));
/* 118:118 */        model.addAttribute("detect", CpeUtils.getDetectIcon(host.getDetect()));
/* 119:119 */        model.addAttribute("trace", CpeUtils.getTraceIcon(host.getTrace()));
/* 120:120 */        model.addAttribute("userNum", Integer.valueOf(host.getUserNum()));
/* 121:121 */        model.addAttribute("producter", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.Manufacturer"));
/* 122:122 */        model.addAttribute("productDescr", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.ProductModel"));
/* 123:123 */        model.addAttribute("ipAddress", host.getIpAddress());
/* 124:124 */        model.addAttribute("mac", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.SerialNumber"));
/* 125:125 */        model.addAttribute("version", getPropertyValue(props, "InternetGatewayDevice.DeviceInfo.SoftwareVersion"));
/* 126:126 */        BranchDto bd = this.branchService.getBranch(host.getBranchId());
/* 127:127 */        if (bd == null) {
/* 128:128 */          model.addAttribute("branch", "");
/* 129:    */        } else
/* 130:130 */          model.addAttribute("branch", bd.getName());
/* 131:    */      }
/* 132:    */    } catch (Exception e) {
/* 133:133 */      e.printStackTrace();
/* 134:    */    }
/* 135:135 */    return "/cpe/device/setting.jsp";
/* 136:    */  }
/* 137:    */  
/* 138:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readySettingTrace.do"})
/* 139:    */  public String readySettingTrace(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 140:140 */    String batchMode = request.getParameter("batchMode");
/* 141:141 */    String id = request.getParameter("id");
/* 142:142 */    model.addAttribute("batchMode", batchMode);
/* 143:143 */    model.addAttribute("id", id);
/* 144:144 */    if ("1".equals(batchMode)) {
/* 145:145 */      boolean allHasDetect = true;
/* 146:146 */      String[] hostIds = id.split(",");
/* 147:147 */      model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 148:148 */      for (String hostId : hostIds) {
/* 149:149 */        com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostId);
/* 150:150 */        if (allHasDetect)
/* 151:151 */          allHasDetect = host.getHardwareVersion().endsWith("-M");
/* 152:    */      }
/* 153:153 */      model.addAttribute("hasDetect", Boolean.valueOf(allHasDetect));
/* 154:154 */      return "/cpe/device/settingBatchTrace.jsp";
/* 155:    */    }
/* 156:156 */    com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(id);
/* 157:157 */    model.addAttribute("host", host);
/* 158:158 */    model.addAttribute("id", id);
/* 159:159 */    boolean noTrace = (StringUtils.isEmpty(host.getTrace())) || ("off".equals(host.getTrace())) || ("ad".equals(host.getTrace()));
/* 160:160 */    if (noTrace) {
/* 161:161 */      model.addAttribute("trace", Boolean.valueOf(false));
/* 162:162 */      model.addAttribute("toServer", "");
/* 163:    */    } else {
/* 164:164 */      model.addAttribute("trace", Boolean.valueOf(true));
/* 165:165 */      model.addAttribute("toServer", host.getTrace());
/* 166:    */    }
/* 167:167 */    model.addAttribute("hasDetect", Boolean.valueOf(host.getHardwareVersion().endsWith("-M")));
/* 168:168 */    return "/cpe/device/settingTrace.jsp";
/* 169:    */  }
/* 170:    */  
/* 171:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readySettingDetect.do"})
/* 172:    */  public String readySettingDetect(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 173:    */  {
/* 174:174 */    String batchMode = request.getParameter("batchMode");
/* 175:175 */    String id = request.getParameter("id");
/* 176:176 */    model.addAttribute("batchMode", batchMode);
/* 177:177 */    model.addAttribute("id", id);
/* 178:178 */    if ("1".equals(batchMode)) {
/* 179:179 */      boolean allHasDetect = true;
/* 180:180 */      String[] hostIds = id.split(",");
/* 181:181 */      for (String hostId : hostIds) {
/* 182:182 */        com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostId);
/* 183:183 */        if (allHasDetect)
/* 184:184 */          allHasDetect = host.getHardwareVersion().endsWith("-M");
/* 185:    */      }
/* 186:186 */      model.addAttribute("hasDetect", Boolean.valueOf(allHasDetect));
/* 187:187 */      model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 188:188 */      return "/cpe/device/settingBatchDetect.jsp";
/* 189:    */    }
/* 190:190 */    com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(id);
/* 191:191 */    model.addAttribute("host", host);
/* 192:192 */    model.addAttribute("id", id);
/* 193:193 */    model.addAttribute("enterSnr", Integer.valueOf(this.ccdao.getEnterSNR(host.getSerialNumber())));
/* 194:194 */    boolean turnOnDetect = true;
/* 195:195 */    if ((com.soofound.framework.util.CommonUtil.isEmpty(host.getDetect())) || ("off".equals(host.getDetect())))
/* 196:196 */      turnOnDetect = false;
/* 197:197 */    model.addAttribute("turnOnDetect", Boolean.valueOf(turnOnDetect));
/* 198:198 */    if ((com.soofound.framework.util.CommonUtil.isEmpty(host.getDetect())) || ("on".equals(host.getDetect())) || ("off".equals(host.getDetect()))) {
/* 199:199 */      model.addAttribute("toServer", "");
/* 200:    */    } else
/* 201:201 */      model.addAttribute("toServer", host.getDetect());
/* 202:202 */    model.addAttribute("hasDetect", Boolean.valueOf(host.getHardwareVersion().endsWith("-M")));
/* 203:203 */    return "/cpe/device/settingDetect.jsp";
/* 204:    */  }
/* 205:    */  
/* 206:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readySettingQos.do"})
/* 207:    */  public String readySettingQos(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 208:    */  {
/* 209:209 */    String batchMode = request.getParameter("batchMode");
/* 210:210 */    String id = request.getParameter("id");
/* 211:211 */    model.addAttribute("batchMode", batchMode);
/* 212:212 */    model.addAttribute("id", id);
/* 213:213 */    if ("1".equals(batchMode)) {
/* 214:214 */      boolean allHasDetect = true;
/* 215:215 */      String[] hostIds = id.split(",");
/* 216:216 */      for (String hostId : hostIds) {
/* 217:217 */        com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostId);
/* 218:218 */        if (allHasDetect)
/* 219:219 */          allHasDetect = host.getHardwareVersion().endsWith("-M");
/* 220:    */      }
/* 221:221 */      model.addAttribute("hasDetect", Boolean.valueOf(allHasDetect));
/* 222:222 */      model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 223:    */      
/* 224:224 */      PropertyDao pdao = new PropertyDao();
/* 225:225 */      PropertyBean pb = pdao.findByName("InternetGatewayDevice.DeviceInfo.qos_enabled");
/* 226:226 */      model.addAttribute("pid", Integer.valueOf(pb.getId()));
/* 227:227 */      return "/cpe/device/settingBatchQos.jsp";
/* 228:    */    }
/* 229:229 */    com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(id);
/* 230:230 */    model.addAttribute("host", host);
/* 231:231 */    model.addAttribute("id", id);
/* 232:    */    
/* 233:233 */    java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = ((HostPropertyService)this.service).getQos(id);
/* 234:234 */    com.soofound.cpe.bean.HostPropertyBean hpb0 = getPropertyByName(props, "InternetGatewayDevice.DeviceInfo.qos_enabled");
/* 235:235 */    com.soofound.cpe.bean.HostPropertyBean hpb1 = getPropertyByName(props, "InternetGatewayDevice.DeviceInfo.qos_upload");
/* 236:236 */    com.soofound.cpe.bean.HostPropertyBean hpb2 = getPropertyByName(props, "InternetGatewayDevice.DeviceInfo.qos_download");
/* 237:237 */    boolean turnOnQos = true;
/* 238:238 */    if ((hpb0 == null) || (hpb0.getValue() == null) || ("0".equals(hpb0.getValue())))
/* 239:239 */      turnOnQos = false;
/* 240:240 */    model.addAttribute("turnOnQos", Boolean.valueOf(turnOnQos));
/* 241:241 */    model.addAttribute("qosTx", "0");
/* 242:242 */    model.addAttribute("qosRx", "0");
/* 243:243 */    if (hpb0 == null) {
/* 244:244 */      PropertyDao pdao = new PropertyDao();
/* 245:245 */      PropertyBean pb = pdao.findByName("InternetGatewayDevice.DeviceInfo.qos_enabled");
/* 246:246 */      model.addAttribute("pid", Integer.valueOf(pb.getId()));
/* 247:    */    } else {
/* 248:248 */      model.addAttribute("pid", Integer.valueOf(hpb0.getId())); }
/* 249:249 */    if (hpb1 != null)
/* 250:250 */      model.addAttribute("qosTx", hpb1.getValue());
/* 251:251 */    if (hpb2 != null)
/* 252:252 */      model.addAttribute("qosRx", hpb2.getValue());
/* 253:253 */    model.addAttribute("hasDetect", Boolean.valueOf(host.getHardwareVersion().endsWith("-M")));
/* 254:254 */    return "/cpe/device/settingQos.jsp";
/* 255:    */  }
/* 256:    */  
/* 257:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readyReboot.do"})
/* 258:    */  public String readyReboot(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 259:    */  {
/* 260:260 */    boolean batchMode = "1".equals(request.getParameter("batchMode"));
/* 261:261 */    model.addAttribute("batchMode", request.getParameter("batchMode"));
/* 262:262 */    String id = request.getParameter("id");
/* 263:263 */    String[] hostIds = id.split(",");
/* 264:264 */    for (int i = 0; i < hostIds.length; i++) {
/* 265:265 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostIds[i]);
/* 266:266 */      String err = null;
/* 267:267 */      if (host.getOnline() == 0) {
/* 268:268 */        if (batchMode) {
/* 269:269 */          err = "操作失败：至少有1个设备不在线。";
/* 270:    */        } else
/* 271:271 */          err = "操作失败：设备不在线。";
/* 272:272 */      } else if (host.getOnline() == 2) {
/* 273:273 */        if (batchMode) {
/* 274:274 */          err = "操作失败：至少有1个设备正在重启。";
/* 275:    */        } else
/* 276:276 */          err = "操作失败：设备正在重启。";
/* 277:277 */      } else if (host.getOnline() == 4) {
/* 278:278 */        if (batchMode) {
/* 279:279 */          err = "操作失败：至少有1个设备正在恢复出产。";
/* 280:    */        } else
/* 281:281 */          err = "操作失败：设备正在恢复出产。";
/* 282:282 */      } else if (host.getOnline() == 3) {
/* 283:283 */        if (batchMode) {
/* 284:284 */          err = "操作失败：至少有1个设备正在更新固件。";
/* 285:    */        } else
/* 286:286 */          err = "操作失败：设备正在更新固件。";
/* 287:    */      }
/* 288:288 */      if (err != null) {
/* 289:289 */        model.addAttribute("message", err);
/* 290:290 */        return "/cpe/device/operationException.jsp";
/* 291:    */      }
/* 292:    */    }
/* 293:293 */    model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 294:294 */    model.addAttribute("id", id);
/* 295:295 */    if (hostIds.length == 1) {
/* 296:296 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(id);
/* 297:297 */      model.addAttribute("host", host);
/* 298:    */    }
/* 299:299 */    model.addAttribute("autoStart", request.getParameter("autoStart"));
/* 300:300 */    return "/cpe/device/reboot.jsp";
/* 301:    */  }
/* 302:    */  
/* 303:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readyReset.do"})
/* 304:    */  public String readyReset(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 305:305 */    boolean batchMode = "1".equals(request.getParameter("batchMode"));
/* 306:306 */    model.addAttribute("batchMode", request.getParameter("batchMode"));
/* 307:307 */    String id = request.getParameter("id");
/* 308:308 */    String[] hostIds = id.split(",");
/* 309:309 */    for (int i = 0; i < hostIds.length; i++) {
/* 310:310 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostIds[i]);
/* 311:311 */      String err = null;
/* 312:312 */      if (host.getOnline() == 0) {
/* 313:313 */        if (batchMode) {
/* 314:314 */          err = "操作失败：至少有1个设备不在线。";
/* 315:    */        } else
/* 316:316 */          err = "操作失败：设备不在线。";
/* 317:317 */      } else if (host.getOnline() == 2) {
/* 318:318 */        if (batchMode) {
/* 319:319 */          err = "操作失败：至少有1个设备正在重启。";
/* 320:    */        } else
/* 321:321 */          err = "操作失败：设备正在重启。";
/* 322:322 */      } else if (host.getOnline() == 4) {
/* 323:323 */        if (batchMode) {
/* 324:324 */          err = "操作失败：至少有1个设备正在恢复出产。";
/* 325:    */        } else
/* 326:326 */          err = "操作失败：设备正在恢复出产。";
/* 327:327 */      } else if (host.getOnline() == 3) {
/* 328:328 */        if (batchMode) {
/* 329:329 */          err = "操作失败：至少有1个设备正在更新固件。";
/* 330:    */        } else
/* 331:331 */          err = "操作失败：设备正在更新固件。";
/* 332:    */      }
/* 333:333 */      if (err != null) {
/* 334:334 */        model.addAttribute("message", err);
/* 335:335 */        return "/cpe/device/operationException.jsp";
/* 336:    */      }
/* 337:    */    }
/* 338:338 */    model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 339:339 */    model.addAttribute("id", id);
/* 340:340 */    if (hostIds.length == 1) {
/* 341:341 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(id);
/* 342:342 */      model.addAttribute("host", host);
/* 343:    */    }
/* 344:344 */    return "/cpe/device/reset.jsp";
/* 345:    */  }
/* 346:    */  
/* 347:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readySettingWireless.do"})
/* 348:    */  public String readySettingWireless(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 349:349 */    String mode = request.getParameter("batchMode");
/* 350:350 */    if (mode == null) mode = "0";
/* 351:351 */    boolean batchMode = "1".equals(mode);
/* 352:352 */    model.addAttribute("batchMode", mode);
/* 353:353 */    String[] hostIds = request.getParameter("id").split(",");
/* 354:354 */    model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 355:355 */    model.addAttribute("id", request.getParameter("id"));
/* 356:356 */    boolean allHas5G = true;
/* 357:357 */    boolean allHasDetect = true;
/* 358:358 */    for (String hostId : hostIds) {
/* 359:359 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostId);
/* 360:360 */      model.addAttribute("host", host);
/* 361:361 */      if (!batchMode) {
/* 362:362 */        java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = ((HostPropertyService)this.service).findByHost(hostId);
/* 363:363 */        for (com.soofound.cpe.bean.HostPropertyBean prop : props) {
/* 364:364 */          String name = prop.getName().replace("InternetGatewayDevice.DeviceInfo.", "");
/* 365:365 */          if ((prop.getValue() == null) || ("N/A".equals(prop.getValue())))
/* 366:366 */            prop.setValue("");
/* 367:367 */          model.addAttribute(name, prop.getValue());
/* 368:    */        }
/* 369:    */      }
/* 370:370 */      if (allHas5G)
/* 371:371 */        allHas5G = host.getHardwareVersion().endsWith("-A");
/* 372:372 */      if (allHasDetect)
/* 373:373 */        allHasDetect = host.getHardwareVersion().endsWith("-M");
/* 374:    */    }
/* 375:375 */    model.addAttribute("has5G", Boolean.valueOf(allHas5G));
/* 376:376 */    model.addAttribute("hasDetect", Boolean.valueOf(allHasDetect));
/* 377:377 */    if (batchMode)
/* 378:378 */      return "/cpe/device/settingBatchWireless.jsp";
/* 379:379 */    return "/cpe/device/settingWireless.jsp";
/* 380:    */  }
/* 381:    */  
/* 382:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readySettingOperation.do"})
/* 383:    */  public String readySettingOperation(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 384:384 */    String mode = request.getParameter("batchMode");
/* 385:385 */    String hostId = request.getParameter("id");
/* 386:386 */    String[] hostIds = request.getParameter("id").split(",");
/* 387:387 */    model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 388:388 */    model.addAttribute("id", hostId);
/* 389:389 */    boolean allHas5G = true;
/* 390:390 */    boolean allHasDetect = true;
/* 391:391 */    for (String hid : hostIds) {
/* 392:392 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hid);
/* 393:393 */      model.addAttribute("host", host);
/* 394:394 */      if (allHas5G)
/* 395:395 */        allHas5G = host.getHardwareVersion().endsWith("-A");
/* 396:396 */      if (allHasDetect)
/* 397:397 */        allHasDetect = host.getHardwareVersion().endsWith("-M");
/* 398:    */    }
/* 399:399 */    model.addAttribute("has5G", Boolean.valueOf(allHas5G));
/* 400:400 */    model.addAttribute("hasDetect", Boolean.valueOf(allHasDetect));
/* 401:401 */    if ("1".equals(mode)) {
/* 402:402 */      return "/cpe/device/settingBatchOperation.jsp";
/* 403:    */    }
/* 404:404 */    java.util.List<BssidDto> smds = this.smdao.findByAP(hostId);
/* 405:405 */    model.addAttribute("ssids", smds);
/* 406:406 */    return "/cpe/device/settingOperation.jsp";
/* 407:    */  }
/* 408:    */  
/* 409:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readyUpdateFirmware.do"})
/* 410:    */  public String readyUpdateFirmware(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 411:411 */    boolean batchMode = "1".equals(request.getParameter("batchMode"));
/* 412:412 */    model.addAttribute("batchMode", request.getParameter("batchMode"));
/* 413:413 */    String id = request.getParameter("id");
/* 414:414 */    String[] hostIds = id.split(",");
/* 415:415 */    for (int i = 0; i < hostIds.length; i++) {
/* 416:416 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostIds[i]);
/* 417:417 */      String err = null;
/* 418:418 */      if (host.getOnline() == 0) {
/* 419:419 */        if (batchMode) {
/* 420:420 */          err = "操作失败：至少有1个设备不在线。";
/* 421:    */        } else
/* 422:422 */          err = "操作失败：设备不在线。";
/* 423:423 */      } else if (host.getOnline() == 2) {
/* 424:424 */        if (batchMode) {
/* 425:425 */          err = "操作失败：至少有1个设备正在重启。";
/* 426:    */        } else
/* 427:427 */          err = "操作失败：设备正在重启。";
/* 428:428 */      } else if (host.getOnline() == 4) {
/* 429:429 */        if (batchMode) {
/* 430:430 */          err = "操作失败：至少有1个设备正在恢复出产。";
/* 431:    */        } else
/* 432:432 */          err = "操作失败：设备正在恢复出产。";
/* 433:433 */      } else if (host.getOnline() == 3) {
/* 434:434 */        if (batchMode) {
/* 435:435 */          err = "操作失败：至少有1个设备正在更新固件。";
/* 436:    */        } else
/* 437:437 */          err = "操作失败：设备正在更新固件。";
/* 438:    */      }
/* 439:439 */      if (err != null) {
/* 440:440 */        model.addAttribute("message", err);
/* 441:441 */        return "/cpe/device/operationException.jsp";
/* 442:    */      }
/* 443:    */    }
/* 444:444 */    model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 445:445 */    model.addAttribute("id", id);
/* 446:446 */    if (hostIds.length == 1) {
/* 447:447 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(id);
/* 448:448 */      model.addAttribute("host", host);
/* 449:    */    }
/* 450:450 */    java.util.List<SoftwareBean> sbs = this.ss.findSoftware(1, null);
/* 451:451 */    model.addAttribute("dtos", sbs);
/* 452:452 */    model.addAttribute("notNull", Boolean.valueOf(!sbs.isEmpty()));
/* 453:453 */    return "/cpe/device/firmwareUpdate.jsp";
/* 454:    */  }
/* 455:    */  
/* 456:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readyUpdateConfig.do"})
/* 457:    */  public String readyUpdateConfig(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 458:458 */    boolean batchMode = "1".equals(request.getParameter("batchMode"));
/* 459:459 */    model.addAttribute("batchMode", request.getParameter("batchMode"));
/* 460:460 */    String id = request.getParameter("id");
/* 461:461 */    String[] hostIds = id.split(",");
/* 462:462 */    for (int i = 0; i < hostIds.length; i++) {
/* 463:463 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostIds[i]);
/* 464:464 */      String err = null;
/* 465:465 */      if (host.getOnline() == 0) {
/* 466:466 */        if (batchMode) {
/* 467:467 */          err = "操作失败：至少有1个设备不在线。";
/* 468:    */        } else
/* 469:469 */          err = "操作失败：设备不在线。";
/* 470:470 */      } else if (host.getOnline() == 2) {
/* 471:471 */        if (batchMode) {
/* 472:472 */          err = "操作失败：至少有1个设备正在重启。";
/* 473:    */        } else
/* 474:474 */          err = "操作失败：设备正在重启。";
/* 475:475 */      } else if (host.getOnline() == 4) {
/* 476:476 */        if (batchMode) {
/* 477:477 */          err = "操作失败：至少有1个设备正在恢复出产。";
/* 478:    */        } else
/* 479:479 */          err = "操作失败：设备正在恢复出产。";
/* 480:480 */      } else if (host.getOnline() == 3) {
/* 481:481 */        if (batchMode) {
/* 482:482 */          err = "操作失败：至少有1个设备正在更新固件。";
/* 483:    */        } else
/* 484:484 */          err = "操作失败：设备正在更新固件。";
/* 485:    */      }
/* 486:486 */      if (err != null) {
/* 487:487 */        model.addAttribute("message", err);
/* 488:488 */        return "/cpe/device/operationException.jsp";
/* 489:    */      }
/* 490:    */    }
/* 491:491 */    com.soofound.admin.bean.UserDto user = getCurrentUser(request);
/* 492:492 */    model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 493:493 */    model.addAttribute("id", id);
/* 494:494 */    if (hostIds.length == 1) {
/* 495:495 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(id);
/* 496:496 */      model.addAttribute("host", host);
/* 497:    */    }
/* 498:498 */    java.util.List<SoftwareBean> sbs = this.ss.findSoftware(2, user.getBranchId());
/* 499:499 */    model.addAttribute("dtos", sbs);
/* 500:500 */    model.addAttribute("notNull", Boolean.valueOf(!sbs.isEmpty()));
/* 501:501 */    return "/cpe/device/configUpdate.jsp";
/* 502:    */  }
/* 503:    */  
/* 504:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/doAction.do"})
/* 505:    */  @ResponseBody
/* 506:    */  public Map<String, Object> doAction(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 507:507 */    try { com.soofound.admin.bean.UserDto user = getCurrentUser(request);
/* 508:508 */      com.soofound.cpe.dao.DeviceLogDao dao = new com.soofound.cpe.dao.DeviceLogDao();
/* 509:509 */      String mode = request.getParameter("batchMode");
/* 510:510 */      String idstr = request.getParameter("id");
/* 511:511 */      boolean batchMode = "1".equals(mode);
/* 512:512 */      String[] hostIds = idstr.split(",");
/* 513:513 */      model.addAttribute("batchMode", mode);
/* 514:514 */      model.addAttribute("id", idstr);
/* 515:515 */      String command = request.getParameter("command");
/* 516:516 */      com.soofound.cpe.bean.HostBean host = null;
/* 517:517 */      java.util.List<String> sqls = new ArrayList();
/* 518:518 */      com.soofound.cpe.util.CpeActionExecutor cae = new com.soofound.cpe.util.CpeActionExecutor();
/* 519:519 */      for (String hostId : hostIds) {
/* 520:520 */        host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostId);
/* 521:521 */        if ((host == null) || (host.getOnline() == 0)) {
/* 522:522 */          if (!batchMode)
/* 523:    */          {
/* 525:525 */            return getFailedResult("操作失败:设备不在线");
/* 526:    */          }
/* 527:527 */        } else if (host.getOnline() == 2) {
/* 528:528 */          if (!batchMode)
/* 529:    */          {
/* 531:531 */            return getFailedResult("操作失败:设备正在重启");
/* 532:    */          }
/* 533:533 */        } else if (host.getOnline() == 4) {
/* 534:534 */          if (!batchMode)
/* 535:    */          {
/* 537:537 */            return getFailedResult("操作失败:设备正在恢复出产");
/* 538:    */          }
/* 539:539 */        } else if (host.getOnline() == 3) {
/* 540:540 */          if (!batchMode)
/* 541:    */          {
/* 543:543 */            return getFailedResult("操作失败:设备正在更新固件"); }
/* 544:    */        } else {
/* 545:545 */          model.addAttribute("host", host);
/* 546:546 */          String cmdstr = null;
/* 547:547 */          String name; if ("reboot".equals(command)) {
/* 548:548 */            cmdstr = cae.getRebootString();
/* 549:549 */            host.setOnline(2);
/* 550:550 */            this.hostService.updateStatus(host);
/* 551:551 */            dao.writeLog(host.getId(), user.getFullname(), "重启设备");
/* 552:552 */            this.hostService.clearCommand(host.getId());
/* 553:553 */          } else if ("reset".equals(command)) {
/* 554:554 */            cmdstr = cae.getFactoryResetString();
/* 555:555 */            host.setOnline(4);
/* 556:556 */            this.hostService.doFactoryReset(host.getId());
/* 557:557 */            dao.writeLog(host.getId(), user.getFullname(), "恢复出产设置");
/* 558:    */            
/* 559:559 */            this.hostService.clearCommand(host.getId());
/* 560:560 */          } else if ("updateFirmware".equals(command)) {
/* 561:561 */            String fileId = request.getParameter("fileId");
/* 562:562 */            if (fileId == null) {
/* 563:563 */              String fileUrl = request.getParameter("fileUrl");
/* 564:564 */              String fileVersion = request.getParameter("fileVersion");
/* 565:565 */              String fileMd5 = request.getParameter("fileMd5");
/* 566:566 */              int fileSize = Integer.parseInt(request.getParameter("fileSize"));
/* 567:567 */              cmdstr = cae.getUpdateFirmwareString(fileUrl, fileMd5, fileSize, fileVersion);
/* 568:568 */              dao.writeLog(host.getId(), user.getFullname(), "更新固件" + fileUrl);
/* 569:    */            } else {
/* 570:570 */              SoftwareBean sb = (SoftwareBean)this.ss.findByID(fileId);
/* 571:571 */              host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostId);
/* 572:572 */              if (!isSameModel(sb.getProductModel(), host.getHardwareVersion()))
/* 573:573 */                return super.getFailedResult("操作失败:固件不支持该型号");
/* 574:574 */              cmdstr = cae.getUpdateFirmwareString(hostId, fileId);
/* 575:575 */              dao.writeLog(host.getId(), user.getFullname(), "更新固件" + sb.getVersion());
/* 576:    */            }
/* 577:577 */            host.setOnline(3);
/* 578:578 */            this.hostService.updateStatus(host);
/* 579:579 */            this.hostService.clearCommand(host.getId());
/* 580:580 */          } else if ("settingTrace".equals(command)) {
/* 581:581 */            String trace = request.getParameter("MonURLType");
/* 582:582 */            String toServer = request.getParameter("toServer");
/* 583:583 */            if ((!"off".equals(trace)) && (!"ad".equals(trace)))
/* 584:584 */              trace = toServer;
/* 585:585 */            ((HostPropertyService)this.service).turnOnTrace(hostId, trace);
/* 586:    */            
/* 587:587 */            java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = new ArrayList();
/* 588:588 */            String[] vals = CpeUtils.getTraceFlags(trace);
/* 589:589 */            com.soofound.cpe.bean.HostPropertyBean prop2 = new com.soofound.cpe.bean.HostPropertyBean();
/* 590:590 */            prop2.setName("InternetGatewayDevice.DeviceInfo.MonURL");
/* 591:591 */            prop2.setValue(vals[1]);
/* 592:592 */            props.add(prop2);
/* 593:    */            
/* 594:594 */            cmdstr = cae.getSetParameterValuesString(props);
/* 595:595 */            dao.writeLog(host.getId(), user.getFullname(), "设置溯源");
/* 596:    */          }
/* 597:597 */          else if ("settingDetect".equals(command)) {
/* 598:598 */            String detect = request.getParameter("monitor");
/* 599:599 */            String toServer = request.getParameter("toServer");
/* 600:600 */            if (com.soofound.framework.util.CommonUtil.isEmpty(detect))
/* 601:601 */              detect = "off";
/* 602:602 */            if ("on".equals(detect)) {
/* 603:603 */              if (!com.soofound.framework.util.CommonUtil.isEmpty(toServer))
/* 604:604 */                detect = toServer;
/* 605:605 */              ((HostPropertyService)this.service).turnOnDetect(hostId, detect);
/* 606:606 */              int snr = -60;
/* 607:    */              try {
/* 608:608 */                snr = Integer.parseInt(request.getParameter("enterSnr"));
/* 609:    */              } catch (Exception e) {
/* 610:610 */                snr = -60;
/* 611:    */              }
/* 612:612 */              this.ccdao.updateSNR(host.getSerialNumber(), snr);
/* 613:    */            } else {
/* 614:614 */              ((HostPropertyService)this.service).turnOnDetect(hostId, "off"); }
/* 615:615 */            java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = new ArrayList();
/* 616:616 */            com.soofound.cpe.bean.HostPropertyBean prop = new com.soofound.cpe.bean.HostPropertyBean();
/* 617:617 */            prop.setName("InternetGatewayDevice.DeviceInfo.MonWifi");
/* 618:618 */            prop.setValue(detect);
/* 619:619 */            props.add(prop);
/* 620:620 */            cmdstr = cae.getSetParameterValuesString(props);
/* 621:621 */            dao.writeLog(host.getId(), user.getFullname(), "探测");
/* 622:    */          } else { int pid;
/* 623:623 */            if ("settingQos".equals(command)) {
/* 624:624 */              String qos = request.getParameter("qos");
/* 625:625 */              if (com.soofound.framework.util.CommonUtil.isEmpty(qos))
/* 626:626 */                qos = "0";
/* 627:627 */              String qosTx = request.getParameter("qosTx");
/* 628:628 */              String qosRx = request.getParameter("qosRx");
/* 629:629 */              if (com.soofound.framework.util.CommonUtil.isEmpty(qosTx))
/* 630:630 */                qosTx = "0";
/* 631:631 */              if (com.soofound.framework.util.CommonUtil.isEmpty(qosRx))
/* 632:632 */                qosRx = "0";
/* 633:633 */              pid = Integer.parseInt(request.getParameter("pid"));
/* 634:634 */              ((HostPropertyService)this.service).turnOnQos(hostId, pid, new String[] { qos, qosTx, qosRx });
/* 635:    */              
/* 636:636 */              java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = new ArrayList();
/* 637:637 */              com.soofound.cpe.bean.HostPropertyBean prop1 = new com.soofound.cpe.bean.HostPropertyBean("InternetGatewayDevice.DeviceInfo.qos_upload", qosTx);
/* 638:638 */              props.add(prop1);
/* 639:639 */              com.soofound.cpe.bean.HostPropertyBean prop2 = new com.soofound.cpe.bean.HostPropertyBean("InternetGatewayDevice.DeviceInfo.qos_download", qosRx);
/* 640:640 */              props.add(prop2);
/* 641:641 */              com.soofound.cpe.bean.HostPropertyBean prop3 = new com.soofound.cpe.bean.HostPropertyBean("InternetGatewayDevice.DeviceInfo.qos_enabled", qos);
/* 642:642 */              props.add(prop3);
/* 643:    */              
/* 644:644 */              cmdstr = cae.getSetParameterValuesString(props);
/* 645:645 */              dao.writeLog(host.getId(), user.getFullname(), "设置QOS");
/* 646:    */            }
/* 647:647 */            else if ("updateConfig".equals(command)) {
/* 648:648 */              String fileId = request.getParameter("fileId");
/* 649:649 */              cmdstr = cae.getUpdateConfigString(hostId, fileId);
/* 650:650 */              dao.writeLog(host.getId(), user.getFullname(), "更新配置");
/* 651:    */            }
/* 652:652 */            else if ("setParameterValues".equals(command)) {
/* 653:653 */              java.util.List<com.soofound.cpe.bean.HostPropertyBean> hpbs = ((HostPropertyService)this.service).findByHost(hostId);
/* 654:654 */              java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = new ArrayList();
/* 655:655 */              for (com.soofound.cpe.bean.HostPropertyBean hpb : hpbs) {
/* 656:656 */                name = hpb.getName().replace("InternetGatewayDevice.DeviceInfo.", "");
/* 657:657 */                String val = request.getParameter(name);
/* 658:658 */                if (!com.soofound.framework.util.CommonUtil.isEmpty(val))
/* 659:    */                {
/* 661:661 */                  if ((name.indexOf("white_list") >= 0) && (val.indexOf("/") >= 0))
/* 662:662 */                    return super.getFailedResult("白名单为域名或IP，不能包含/");
/* 663:663 */                  if (val.indexOf(" ") > 0)
/* 664:664 */                    return super.getFailedResult("错误:[" + hpb.getCnName() + "]有空格");
/* 665:665 */                  if ((CpeUtils.isThreshold(name)) && (val.startsWith("-"))) {
/* 666:666 */                    return super.getFailedResult("错误:阀值不能是负数");
/* 667:    */                  }
/* 668:668 */                  if (!val.equals(hpb.getValue())) {
/* 669:669 */                    hpb.setValue(val);
/* 670:670 */                    props.add(hpb);
/* 671:671 */                    StringBuilder sqlText = new StringBuilder(200);
/* 672:672 */                    sqlText.append("update cpe_host_property set value='").append(val);
/* 673:673 */                    sqlText.append("' where host_id=").append(hostId).append(" and pid=").append(hpb.getId());
/* 674:674 */                    sqls.add(sqlText.toString());
/* 675:    */                  }
/* 676:676 */                  if (name.equals("wireless_channel")) {
/* 677:677 */                    StringBuilder sqlText = new StringBuilder(100);
/* 678:678 */                    sqlText.append("update cpe_host set channel='").append(val).append("' where id=").append(hostId);
/* 679:679 */                    sqls.add(sqlText.toString());
/* 680:    */                  }
/* 681:    */                } }
/* 682:682 */              if (props.size() > 0) {
/* 683:683 */                cmdstr = cae.getSetParameterValuesString(props);
/* 684:684 */                dao.writeLog(host.getId(), user.getFullname(), "设置参数");
/* 685:    */              }
/* 686:    */            } }
/* 687:687 */          if (cmdstr != null) {
/* 688:688 */            if (cmdstr.indexOf("ManagementServer") > 0) {
/* 689:689 */              String fileId = request.getParameter("fileId");
/* 690:690 */              SoftwareDao softDao = new SoftwareDao();
/* 691:691 */              java.util.List<ConfigParamBean> cpbs = softDao.getConfigParams(Integer.parseInt(fileId));
/* 692:692 */              java.util.List<ConfigParamBean> _cpbs; for (ConfigParamBean cpb : cpbs) {
/* 693:693 */                if (cpb.getName().indexOf("Wifiant_URL") > 0) {
/* 694:694 */                  _cpbs = new ArrayList();
/* 695:695 */                  _cpbs.add(cpb);
/* 696:696 */                  System.out.println(cae.getConfigString(_cpbs));
/* 697:697 */                  this.hostService.putCommand(host.getId(), cae.getConfigString(_cpbs));
/* 698:698 */                  break;
/* 699:    */                }
/* 700:    */              }
/* 701:701 */              this.hostService.putCommand(host.getId(), cae.getRebootString());
/* 702:702 */              java.util.List<ConfigParamBean> _cpbs = new ArrayList();
/* 703:703 */              for (ConfigParamBean cpb : cpbs) {
/* 704:704 */                if (cpb.getName().indexOf("Wifiant_URL") == -1)
/* 705:705 */                  _cpbs.add(cpb);
/* 706:    */              }
/* 707:707 */              System.out.println(cae.getConfigString(_cpbs));
/* 708:708 */              this.hostService.putCommand(host.getId(), cae.getConfigString(_cpbs));
/* 709:    */            } else {
/* 710:710 */              System.out.println(cmdstr);
/* 711:711 */              this.hostService.putCommand(host.getId(), cmdstr);
/* 712:    */            }
/* 713:713 */            CpeWaker cw = new CpeWaker();
/* 714:714 */            cw.wakeup(hostId);
/* 715:715 */            if (("setParameterValues".equals(command)) || ("settingTrace".equals(command))) {
/* 716:716 */              this.hostService.putCommand(host.getId(), cae.getApplyString());
/* 717:717 */            } else if ("reset".equals(command)) {
/* 718:718 */              this.hostService.putCommand(host.getId(), cae.getRebootString());
/* 719:719 */            } else if ("updateFirmware".equals(command))
/* 720:720 */              this.hostService.putCommand(host.getId(), cae.getGetParameterValuesString(host.getHardwareVersion(), 2));
/* 721:    */          }
/* 722:    */        }
/* 723:    */      }
/* 724:724 */      if (sqls.size() > 0)
/* 725:725 */        this.jdbc.batchUpdate(com.soofound.framework.util.CommonUtil.toArray(sqls));
/* 726:726 */      Map<String, Object> mapresult = super.getSucceedResult("操作成功");
/* 727:727 */      Object mapresult2 = new HashMap();
/* 728:728 */      ((Map)mapresult2).put("needRestart", Boolean.valueOf(false));
/* 729:729 */      mapresult.put("data", mapresult2);
/* 730:730 */      return mapresult;
/* 731:    */    } catch (Exception ex) {
/* 732:732 */      ex.printStackTrace(); }
/* 733:733 */    return super.getFailedResult("操作失败");
/* 734:    */  }
/* 735:    */  
/* 736:    */  private boolean isSameModel(String model1, String model2)
/* 737:    */  {
/* 738:738 */    if ((model1.startsWith("RW2400NGHSC")) && (model2.startsWith("RW2400NGHSC")))
/* 739:739 */      return true;
/* 740:740 */    return model1.equals(model2);
/* 741:    */  }
/* 742:    */  
/* 743:    */  private com.soofound.cpe.bean.HostPropertyBean getPropertyByName(java.util.List<com.soofound.cpe.bean.HostPropertyBean> hpbs, String name) {
/* 744:744 */    for (com.soofound.cpe.bean.HostPropertyBean hpb : hpbs) {
/* 745:745 */      if (hpb.getName().equals(name))
/* 746:746 */        return hpb;
/* 747:    */    }
/* 748:748 */    return null;
/* 749:    */  }
/* 750:    */  
/* 751:    */  private String getPropertyValue(java.util.List<com.soofound.cpe.bean.HostPropertyBean> hpbs, String name) {
/* 752:752 */    for (com.soofound.cpe.bean.HostPropertyBean hpb : hpbs) {
/* 753:753 */      if (hpb.getName().equals(name))
/* 754:754 */        return hpb.getValue();
/* 755:    */    }
/* 756:756 */    return "";
/* 757:    */  }
/* 758:    */  
/* 759:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readySettingMaintain.do"})
/* 760:    */  public String readySettingMaintain(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 761:761 */    String batchMode = request.getParameter("batchMode");
/* 762:762 */    String id = request.getParameter("id");
/* 763:763 */    model.addAttribute("batchMode", batchMode);
/* 764:764 */    model.addAttribute("id", id);
/* 765:765 */    if ("1".equals(batchMode)) {
/* 766:766 */      boolean allHasDetect = true;
/* 767:767 */      String[] hostIds = id.split(",");
/* 768:768 */      for (String hostId : hostIds) {
/* 769:769 */        com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(hostId);
/* 770:770 */        if (allHasDetect)
/* 771:771 */          allHasDetect = host.getHardwareVersion().endsWith("-M");
/* 772:    */      }
/* 773:773 */      model.addAttribute("hasDetect", Boolean.valueOf(allHasDetect));
/* 774:774 */      model.addAttribute("deviceNum", Integer.valueOf(hostIds.length));
/* 775:775 */      return "/cpe/device/settingBatchMaintain.jsp";
/* 776:    */    }
/* 777:777 */    com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(id);
/* 778:778 */    model.addAttribute("host", host);
/* 779:779 */    model.addAttribute("id", id);
/* 780:780 */    ScheduleTimeDao stdao = new ScheduleTimeDao();
/* 781:781 */    ScheduleTimeDto stdto = stdao.findByID(id);
/* 782:782 */    if (stdto == null) {
/* 783:783 */      model.addAttribute("autoReboot", "0");
/* 784:784 */      model.addAttribute("bits", "");
/* 785:    */    } else {
/* 786:786 */      model.addAttribute("autoReboot", "1");
/* 787:787 */      model.addAttribute("bits", stdto.getBits());
/* 788:    */    }
/* 789:789 */    model.addAttribute("hasDetect", Boolean.valueOf(host.getHardwareVersion().endsWith("-M")));
/* 790:790 */    return "/cpe/device/settingMaintain.jsp";
/* 791:    */  }
/* 792:    */  
/* 793:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/settingMaintain.do"})
/* 794:    */  @ResponseBody
/* 795:    */  public Map<String, Object> settingMaintain(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 796:796 */    String rebootWeekHour = request.getParameter("rebootWeekHour");
/* 797:797 */    String idstr = request.getParameter("id");
/* 798:798 */    boolean autoReboot = "1".equals(request.getParameter("autoReboot"));
/* 799:    */    try {
/* 800:800 */      ScheduleTimeDao dao = new ScheduleTimeDao();
/* 801:801 */      String[] ids = idstr.split(",");
/* 802:802 */      for (String id : ids) {
/* 803:803 */        if (autoReboot) {
/* 804:804 */          ScheduleTimeDto dto = new ScheduleTimeDto();
/* 805:805 */          dto.setApId(Integer.parseInt(id));
/* 806:806 */          dto.setBitsAndTimes(rebootWeekHour);
/* 807:807 */          dao.save(dto);
/* 808:    */        } else {
/* 809:809 */          dao.delete(id);
/* 810:    */        }
/* 811:    */      }
/* 812:812 */      return getSucceedResult("操作成功");
/* 813:    */    } catch (Exception e) {
/* 814:814 */      e.printStackTrace(); }
/* 815:815 */    return getFailedResult("操作失败");
/* 816:    */  }
/* 817:    */  
/* 837:    */  @Autowired
/* 838:    */  protected void setService(HostPropertyService service)
/* 839:    */  {
/* 840:840 */    this.service = service;
/* 841:    */  }
/* 842:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.HostPropertyAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */