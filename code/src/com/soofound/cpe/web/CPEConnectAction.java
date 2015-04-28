/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.LicenseDto;
/*   4:    */import com.soofound.admin.web.LicenseService;
/*   5:    */import com.soofound.cpe.bean.HostBean;
/*   6:    */import com.soofound.cpe.dao.DeviceLogDao;
/*   7:    */import com.soofound.cpe.util.CpeActionExecutor;
/*   8:    */import com.soofound.cpe.util.SoofacACS;
/*   9:    */import com.soofound.cpe.util.StunServerLauncher;
/*  10:    */import com.soofound.cpe.util.UpdateHostByInformTask;
/*  11:    */import com.soofound.cpe.util.UpdateHostTask;
/*  12:    */import com.soofound.cpe.util.XMLFilterInputStream;
/*  13:    */import com.soofound.cpe.util.XMLFilterNS;
/*  14:    */import com.soofound.framework.license.License;
/*  15:    */import com.soofound.framework.util.CommonUtil;
/*  16:    */import com.soofound.framework.util.DateUtil;
/*  17:    */import com.soofound.framework.util.SysConfigHelper;
/*  18:    */import com.soofound.portal.service.WifiDogService;
/*  19:    */import com.soofound.protocol.cwmp.GetParameterValues;
/*  20:    */import com.soofound.protocol.cwmp.GetParameterValuesResponse;
/*  21:    */import com.soofound.protocol.cwmp.Inform;
/*  22:    */import com.soofound.protocol.cwmp.InformResponse;
/*  23:    */import com.soofound.protocol.cwmp.Message;
/*  24:    */import java.io.ByteArrayInputStream;
/*  25:    */import java.io.ByteArrayOutputStream;
/*  26:    */import java.io.IOException;
/*  27:    */import java.io.InputStream;
/*  28:    */import java.io.PrintStream;
/*  29:    */import java.util.NoSuchElementException;
/*  30:    */import java.util.concurrent.ExecutorService;
/*  31:    */import java.util.concurrent.Executors;
/*  32:    */import javax.mail.MessagingException;
/*  33:    */import javax.mail.internet.MimeUtility;
/*  34:    */import javax.servlet.ServletOutputStream;
/*  35:    */import javax.servlet.http.HttpServletRequest;
/*  36:    */import javax.servlet.http.HttpServletResponse;
/*  37:    */import javax.servlet.http.HttpSession;
/*  38:    */import javax.xml.soap.MessageFactory;
/*  39:    */import javax.xml.soap.MimeHeaders;
/*  40:    */import javax.xml.soap.SOAPException;
/*  41:    */import javax.xml.soap.SOAPMessage;
/*  42:    */import org.apache.log4j.Logger;
/*  43:    */import org.springframework.beans.factory.annotation.Autowired;
/*  44:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  45:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  46:    */import org.springframework.stereotype.Controller;
/*  47:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  48:    */
/*  56:    */@Controller
/*  57:    */public final class CPEConnectAction
/*  58:    */{
/*  59:    */  private static final String CHARSET_UTF8 = "text/xml; charset=UTF-8";
/*  60:    */  private static final String CONTENT_TYPE = "Content-Type";
/*  61:    */  private static final String TEXT_XML = "text/xml";
/*  62: 62 */  private ExecutorService threadPool = Executors.newCachedThreadPool();
/*  63: 63 */  private Logger loger = Logger.getLogger(CPEConnectAction.class);
/*  64: 64 */  private boolean debug = false;
/*  65:    */  private String debugObject;
/*  66:    */  
/*  67:    */  @RequestMapping({"/acs/setDebug.do"})
/*  68:    */  public void setDebug(HttpServletRequest request, HttpServletResponse response) {
/*  69: 69 */    this.debugObject = request.getParameter("obj");
/*  70:    */  }
/*  71:    */  
/*  72:    */  @RequestMapping({"/acs/connect.do"})
/*  73:    */  public void connect(HttpServletRequest request, HttpServletResponse response) {
/*  74: 74 */    XMLFilterInputStream xmlStream = null;
/*  75:    */    try {
/*  76: 76 */      xmlStream = new XMLFilterInputStream(request.getInputStream(), request.getContentLength());
/*  77: 77 */      acsConnect(request, response, xmlStream);
/*  78:    */    } catch (Exception ex) {
/*  79: 79 */      this.loger.error("CPEConnect.error#" + request.getRemoteAddr());
/*  80: 80 */      response.setStatus(204);
/*  81: 81 */      if (request.getRemoteAddr().equals(this.debugObject)) {
/*  82: 82 */        ex.printStackTrace();
/*  83:    */      }
/*  84:    */      try {
/*  85: 85 */        xmlStream.close();
/*  86:    */      } catch (Exception ex) {
/*  87: 87 */        ex.printStackTrace();
/*  88:    */      }
/*  89:    */    }
/*  90:    */    finally
/*  91:    */    {
/*  92:    */      try
/*  93:    */      {
/*  94: 85 */        xmlStream.close();
/*  95:    */      } catch (Exception ex) {
/*  96: 87 */        ex.printStackTrace();
/*  97:    */      }
/*  98:    */    } }
/*  99:    */  
/* 100:    */  @Autowired
/* 101:    */  private WifiDogService wds;
/* 102:    */  
/* 103: 94 */  private void acsConnect(HttpServletRequest request, HttpServletResponse response, XMLFilterInputStream xmlStream) throws Exception { SOAPMessage soapMsg = null;
/* 104: 95 */    response.setContentType("text/xml");
/* 105: 96 */    Inform inform = (Inform)request.getSession().getAttribute("inform");
/* 106: 97 */    HostBean host = (HostBean)request.getSession().getAttribute("host");
/* 107: 98 */    boolean cpeSentEmptyReq = true;
/* 108: 99 */    MessageFactory mf = MessageFactory.newInstance();
/* 109:    */    break label2077;
/* 110:101 */    for (;;) { cpeSentEmptyReq = false;
/* 111:    */      try {
/* 112:103 */        MimeHeaders hdrs = new MimeHeaders();
/* 113:104 */        hdrs.setHeader("Content-Type", "text/xml; charset=UTF-8");
/* 114:105 */        soapMsg = mf.createMessage(hdrs, new XMLFilterNS(xmlStream));
/* 115:106 */        Message msg = null;
/* 116:    */        try {
/* 117:108 */          msg = Message.doParse(soapMsg);
/* 118:    */        } catch (Exception e) {
/* 119:110 */          System.out.println(request.getRemoteAddr() + "==Parsing failed:==");
/* 120:111 */          e.printStackTrace();
/* 121:112 */          response.setStatus(500);
/* 122:113 */          return;
/* 123:    */        }
/* 124:115 */        String reqname = msg.getName();
/* 125:    */        try {
/* 126:117 */          if (msg.getName().equals("Inform")) {
/* 127:118 */            if (!authenticate(request, response)) {
/* 128:119 */              return;
/* 129:    */            }
/* 130:121 */            inform = (Inform)msg;
/* 131:122 */            String sn = inform.getSerialNumber();
/* 132:    */            
/* 133:124 */            request.getSession().setAttribute("inform", inform);
/* 134:125 */            if ((this.debug) || (sn.equals(this.debugObject))) {
/* 135:126 */              ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 136:127 */              soapMsg.writeTo(bos);
/* 137:128 */              String responseString = new String(bos.toByteArray(), "UTF-8");
/* 138:129 */              System.out.println(sn + "--------" + reqname);
/* 139:130 */              System.out.println(responseString);
/* 140:    */            }
/* 141:132 */            int informFlag = 0;
/* 142:133 */            if ((inform.isEvent("1 BOOT")) && (inform.isEvent("0 BOOTSTRAP"))) {
/* 143:134 */              informFlag = 1;
/* 144:135 */            } else if (inform.isEvent("0 BOOTSTRAP")) {
/* 145:136 */              informFlag = 2;
/* 146:137 */            } else if (inform.isEvent("2 PERIODIC")) {
/* 147:138 */              informFlag = 3;
/* 148:139 */            } else if (inform.isEvent("1 BOOT"))
/* 149:140 */              informFlag = 4;
/* 150:141 */            String branchId = "0";
/* 151:142 */            if (informFlag == 1) {
/* 152:143 */              boolean accessable = false;
/* 153:144 */              if (this.sfacs.getAccessControl().equals("mac")) {
/* 154:145 */                LicenseDto ld = (LicenseDto)this.licService.findByID(sn);
/* 155:146 */                if (ld != null) {
/* 156:147 */                  branchId = ld.getBranchId();
/* 157:148 */                  accessable = true;
/* 158:    */                }
/* 159:    */              } else {
/* 160:151 */                branchId = "00001";
/* 161:152 */                accessable = SysConfigHelper.getLicense().getDeviceNumber() > this.service.getDeviceTotal(branchId);
/* 162:    */              }
/* 163:154 */              if (!accessable) {
/* 164:155 */                this.loger.error("Unregistered AP access SooFac or SooFac can not accept more AP:" + inform.getSerialNumber());
/* 165:156 */                response.setStatus(204);
/* 166:157 */                return;
/* 167:    */              }
/* 168:159 */              host = this.service.findBySerialNumber(sn);
/* 169:160 */              DeviceLogDao dao = new DeviceLogDao();
/* 170:161 */              if (host == null) {
/* 171:162 */                host = new HostBean();
/* 172:163 */                host.setId(this.sfacs.getHostID());
/* 173:164 */                host.setName(sn);
/* 174:165 */                host.setSerialNumber(sn);
/* 175:166 */                host.setBranchId(branchId);
/* 176:167 */                host.setStun(inform.getStun());
/* 177:168 */                host.setIpAddress(inform.getProperty("InternetGatewayDevice.DeviceInfo.ip_address"));
/* 178:169 */                host.setSoftwareVersion(inform.getProperty("InternetGatewayDevice.DeviceInfo.SoftwareVersion"));
/* 179:170 */                host.setHardwareVersion(inform.getProperty("InternetGatewayDevice.DeviceInfo.HardwareVersion"));
/* 180:171 */                host.setProductClass(inform.getProperty("InternetGatewayDevice.DeviceInfo.ProductClass"));
/* 181:172 */                host.setChannel("");
/* 182:173 */                host.setMode("AP");
/* 183:174 */                host.setTrace("off");
/* 184:175 */                host.setOnline(2);
/* 185:176 */                host.setSsid(inform.getProperty("InternetGatewayDevice.DeviceInfo.wireless_ssid"));
/* 186:177 */                this.service.save(host);
/* 187:178 */                request.getSession().setAttribute("host", host);
/* 188:179 */                dao.writeLog(host.getId(), "系统", "设备接入");
/* 189:    */              } else {
/* 190:181 */                String sver = inform.getProperty("InternetGatewayDevice.DeviceInfo.SoftwareVersion");
/* 191:182 */                StringBuilder sqlText = new StringBuilder(100);
/* 192:183 */                sqlText.append("UPDATE cpe_host SET up_time = now(),last_time = now(),online = 2,ip_address='");
/* 193:184 */                sqlText.append(inform.getProperty("InternetGatewayDevice.DeviceInfo.ip_address")).append("',software_version='").append(sver).append("',hardware_version='");
/* 194:185 */                sqlText.append(inform.getProperty("InternetGatewayDevice.DeviceInfo.HardwareVersion")).append("' where id=").append(host.getId());
/* 195:186 */                this.jdbcTemplate.update(sqlText.toString());
/* 196:    */                
/* 197:188 */                dao.writeLog(host.getId(), "系统", "设备上线");
/* 198:    */              }
/* 199:190 */              this.threadPool.execute(new UpdateHostByInformTask(host, inform));
/* 200:191 */              CpeActionExecutor cae = new CpeActionExecutor();
/* 201:192 */              String cmd = cae.getGetParameterValuesString(host.getHardwareVersion(), 1);
/* 202:193 */              this.service.putCommand(host.getId(), cmd);
/* 203:    */            }
/* 204:195 */            else if (host == null) {
/* 205:196 */              host = this.service.findBySerialNumber(sn);
/* 206:    */            }
/* 207:198 */            if (host == null) {
/* 208:199 */              LicenseDto ld = (LicenseDto)this.licService.findByID(sn);
/* 209:200 */              if (ld == null) {
/* 210:201 */                System.out.println(request.getRemoteAddr() + "# host is null:" + sn);
/* 211:202 */                response.setStatus(204);
/* 212:    */              } else {
/* 213:204 */                host = new HostBean();
/* 214:205 */                host.setId(this.sfacs.getHostID());
/* 215:206 */                host.setName(sn);
/* 216:207 */                host.setSerialNumber(sn);
/* 217:208 */                host.setBranchId(ld.getBranchId());
/* 218:209 */                host.setStun(inform.getStun());
/* 219:210 */                host.setIpAddress(inform.getProperty("InternetGatewayDevice.DeviceInfo.ip_address"));
/* 220:211 */                host.setSoftwareVersion(inform.getProperty("InternetGatewayDevice.DeviceInfo.SoftwareVersion"));
/* 221:212 */                host.setSsid(inform.getProperty("InternetGatewayDevice.DeviceInfo.wireless_ssid"));
/* 222:213 */                host.setStun(inform.getStun());
/* 223:214 */                host.setChannel("");
/* 224:215 */                String model = inform.getProperty("InternetGatewayDevice.DeviceInfo.HardwareVersion");
/* 225:216 */                host.setHardwareVersion(model);
/* 226:217 */                host.setDetect(model.endsWith("-M") ? "off" : "");
/* 227:218 */                host.setMode("AP");
/* 228:219 */                host.setTrace("off");
/* 229:220 */                host.setOnline(1);
/* 230:221 */                this.service.save(host);
/* 231:    */                
/* 232:223 */                System.out.println("save a host from heart beat inform and ready to reboot#" + sn);
/* 233:224 */                CpeActionExecutor cae = new CpeActionExecutor();
/* 234:225 */                this.service.putCommand(host.getId(), cae.getRebootString());
/* 235:    */              }
/* 236:227 */              return;
/* 237:    */            }
/* 238:229 */            if ((informFlag == 3) && (this.service.isEmptyCommand(host.getId()))) {
/* 239:230 */              StringBuilder sqlText = new StringBuilder(100);
/* 240:231 */              if (StunServerLauncher.STARTED) {
/* 241:232 */                sqlText.append("UPDATE cpe_host SET up_time = DATE_ADD(now(),INTERVAL-").append(inform.getUptime());
/* 242:233 */                sqlText.append(" second),last_time = now(),stun = '").append(inform.getStun()).append("',online = 1");
/* 243:234 */                if (!CommonUtil.isEmpty(inform.getProperty("InternetGatewayDevice.DeviceInfo.ip_address")))
/* 244:235 */                  sqlText.append(",ip_address='").append(inform.getProperty("InternetGatewayDevice.DeviceInfo.ip_address")).append("'");
/* 245:236 */                sqlText.append(" where id=").append(host.getId());
/* 246:    */              } else {
/* 247:238 */                sqlText.append("UPDATE cpe_host SET stun = '").append(inform.getStun()).append("',online = 1");
/* 248:239 */                if (!CommonUtil.isEmpty(inform.getProperty("InternetGatewayDevice.DeviceInfo.ip_address")))
/* 249:240 */                  sqlText.append(",ip_address='").append(inform.getProperty("InternetGatewayDevice.DeviceInfo.ip_address")).append("'");
/* 250:241 */                sqlText.append(" where id=").append(host.getId());
/* 251:    */              }
/* 252:243 */              this.jdbcTemplate.update(sqlText.toString());
/* 253:    */            }
/* 254:245 */            request.getSession().setAttribute("host", host);
/* 255:246 */            ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 256:247 */            InformResponse resp = new InformResponse(inform.getId(), 1);
/* 257:248 */            resp.writeTo(baos);
/* 258:249 */            String returnString = baos.toString();
/* 259:250 */            response.setContentType("text/xml; charset=UTF-8");
/* 260:251 */            response.setContentLength(returnString.length());
/* 261:252 */            response.getOutputStream().print(returnString);
/* 262:253 */            return; }
/* 263:254 */          if (reqname.equals("GetParameterValuesResponse")) {
/* 264:255 */            GetParameterValuesResponse gpvr = (GetParameterValuesResponse)msg;
/* 265:256 */            String sn = gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.SerialNumber");
/* 266:257 */            host = this.service.findBySerialNumber(sn);
/* 267:258 */            if (host == null) {
/* 268:259 */              System.out.println("===not found===" + sn);
/* 269:260 */              response.setStatus(204);
/* 270:261 */              return;
/* 271:    */            }
/* 272:263 */            String cmd = this.service.getDebugResult(host.getId());
/* 273:264 */            if (cmd != null) {
/* 274:265 */              String result = null;
/* 275:266 */              if (cmd.startsWith("DEBUG:")) {
/* 276:267 */                result = gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.debug_result");
/* 277:268 */                this.service.putDebugResult(host.getId(), "DEBUG_RESULT:" + result);
/* 278:    */              }
/* 279:    */              
/* 280:271 */              System.out.println("-----return debug result-----");
/* 281:272 */              System.out.println(result);
/* 282:    */              
/* 283:274 */              return;
/* 284:    */            }
/* 285:276 */            DeviceLogDao dao = new DeviceLogDao();
/* 286:277 */            dao.writeLog(host.getId(), "系统", "获取设备参数");
/* 287:    */            
/* 288:279 */            this.threadPool.execute(new UpdateHostTask(host, gpvr));
/* 289:280 */          } else if (reqname.equals("SetParameterValuesResponse")) {
/* 290:281 */            String debugCmd = this.service.getDebugResult(host.getId());
/* 291:282 */            if ((host != null) && (debugCmd != null)) {
/* 292:283 */              String param = null;
/* 293:284 */              if (debugCmd.startsWith("DEBUG:"))
/* 294:285 */                param = "InternetGatewayDevice.DeviceInfo.debug_result";
/* 295:286 */              GetParameterValues gpv = new GetParameterValues(new String[] { "InternetGatewayDevice.DeviceInfo.SerialNumber", param });
/* 296:287 */              CpeActionExecutor cae = new CpeActionExecutor();
/* 297:288 */              response.getOutputStream().print(cae.toString(gpv));
/* 298:    */            }
/* 299:290 */          } else if ((reqname.equals("FactoryResetResponse")) && (host != null)) {
/* 300:291 */            this.wds.clearOnlineUsers(host.getId());
/* 301:292 */            host.setOnline(4);
/* 302:293 */            DeviceLogDao dao = new DeviceLogDao();
/* 303:294 */            dao.writeLog(host.getId(), "系统", "恢复出产设置");
/* 304:295 */            this.service.updateStatus(host);
/* 305:296 */          } else if ((reqname.equals("DownloadResponse")) && (host != null)) {
/* 306:297 */            this.wds.clearOnlineUsers(host.getId());
/* 307:298 */            host.setOnline(3);
/* 308:299 */            DeviceLogDao dao = new DeviceLogDao();
/* 309:300 */            dao.writeLog(host.getId(), "系统", "更新固件");
/* 310:301 */            this.service.updateStatus(host);
/* 311:    */          }
/* 312:    */        } catch (NoSuchElementException e) {
/* 313:304 */          this.loger.error("While parsing#1");
/* 314:305 */          return;
/* 315:    */        } catch (Exception ex1) {
/* 316:307 */          this.loger.error("IllegalArgumentException#1");
/* 317:308 */          ex1.printStackTrace();
/* 318:    */        }
/* 319:100 */        if (xmlStream.next()) {}
/* 424:    */      }
/* 425:    */      catch (SOAPException e)
/* 426:    */      {
/* 530:311 */        this.loger.error("While parsing#2");
/* 531:    */      }
/* 532:    */      catch (Exception ex2) {
/* 533:314 */        this.loger.error("IllegalArgumentException#2");
/* 534:    */      } }
/* 535:    */    label2077:
/* 536:317 */    if (cpeSentEmptyReq)
/* 537:318 */      if ((inform != null) && (host != null)) {
/* 538:319 */        String cmd = this.service.getCommand(host.getId());
/* 539:320 */        if (cmd != null) {
/* 540:321 */          if (this.debug) {
/* 541:322 */            System.out.println(DateUtil.getCurrentDateTime() + "--取出命令--" + host.getSerialNumber() + "\n");
/* 542:323 */            System.out.println(cmd);
/* 543:    */          }
/* 544:325 */          response.getOutputStream().print(cmd);
/* 545:    */        }
/* 546:    */      } else {
/* 547:328 */        response.setStatus(204);
/* 548:    */      }
/* 549:    */  }
/* 550:    */  
/* 551:    */  private boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
/* 552:333 */    String auth = request.getHeader("Authorization");
/* 553:334 */    if (auth == null) {
/* 554:335 */      response.setHeader("WWW-Authenticate", "Basic realm=\"soofac\"");
/* 555:336 */      response.setStatus(401);
/* 556:337 */      return false;
/* 557:    */    }
/* 558:339 */    if (auth.startsWith("Basic ")) {
/* 559:340 */      String userPassBase64 = auth.substring(6);
/* 560:341 */      String userPassDecoded = null;
/* 561:    */      try {
/* 562:343 */        InputStream is = MimeUtility.decode(new ByteArrayInputStream(userPassBase64.getBytes()), "base64");
/* 563:344 */        byte[] d = new byte[is.available()];
/* 564:345 */        is.read(d);
/* 565:346 */        userPassDecoded = new String(d);
/* 566:    */      } catch (MessagingException ex) {
/* 567:348 */        ex.printStackTrace();
/* 568:    */      }
/* 569:350 */      if (userPassBase64.endsWith("==")) {
/* 570:351 */        userPassDecoded = userPassDecoded.substring(0, userPassDecoded.length() - 2);
/* 571:352 */      } else if (userPassBase64.endsWith("="))
/* 572:353 */        userPassDecoded = userPassDecoded.substring(0, userPassDecoded.length() - 1);
/* 573:354 */      String[] upa = userPassDecoded.split(":");
/* 574:355 */      if ((upa[0].equals(this.sfacs.getUsername())) && (upa[1].equals(this.sfacs.getPassword())))
/* 575:356 */        return true;
/* 576:    */    }
/* 577:358 */    response.setStatus(403);
/* 578:359 */    return false;
/* 579:    */  }
/* 580:    */  
/* 581:    */  @Autowired
/* 582:    */  @Qualifier("defaultJdbcTemplate")
/* 583:    */  private JdbcTemplate jdbcTemplate;
/* 584:    */  @Autowired
/* 585:    */  private LicenseService licService;
/* 586:    */  @Autowired
/* 587:    */  private SoofacACS sfacs;
/* 588:    */  @Autowired
/* 589:    */  private HostService service;
/* 590:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.CPEConnectAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */