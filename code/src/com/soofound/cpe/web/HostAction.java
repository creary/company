/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.UserDto;
/*   4:    */import com.soofound.cpe.util.DeviceTree;
/*   5:    */import com.soofound.operation.bean.SsidCodeDao;
/*   6:    */import com.soofound.operation.bean.SsidCodeDto;
/*   7:    */import com.soofound.portal.bean.PortalInstanceDto;
/*   8:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*   9:    */import com.soofound.portal.custom.CustomFactory;
/*  10:    */import com.soofound.portal.service.PortalInstanceService;
/*  11:    */import com.soofound.portal.service.SurfingPolicyService;
/*  12:    */import java.io.PrintStream;
/*  13:    */import java.util.ArrayList;
/*  14:    */import java.util.HashMap;
/*  15:    */import java.util.List;
/*  16:    */import org.springframework.beans.factory.annotation.Autowired;
/*  17:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  18:    */import org.springframework.stereotype.Controller;
/*  19:    */import org.springframework.util.StringUtils;
/*  20:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  21:    */
/*  22:    */@Controller
/*  23:    */public final class HostAction extends com.soofound.framework.web.BaseAction<HostService>
/*  24:    */{
/*  25:    */  @Autowired
/*  26:    */  @Qualifier("customFactory")
/*  27:    */  private CustomFactory cf;
/*  28:    */  @Autowired
/*  29:    */  private SsidCodeDao scdao;
/*  30:    */  @Autowired
/*  31:    */  private com.soofound.cpe.dao.BssidDao bdao;
/*  32:    */  @Autowired
/*  33:    */  private SurfingPolicyService sps;
/*  34:    */  @Autowired
/*  35:    */  private PortalInstanceService pis;
/*  36:    */  
/*  37:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/hostListJsp.do"})
/*  38:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  39:    */  {
/*  40: 40 */    UserDto user = super.getCurrentUser(request);
/*  41: 41 */    if ("0".equals(user.getBranchId())) {
/*  42: 42 */      model.addAttribute("adminable", Boolean.valueOf(this.cf.getDeviceTree().isAdminable()));
/*  43:    */    } else
/*  44: 44 */      model.addAttribute("adminable", Boolean.valueOf(true));
/*  45: 45 */    return "/cpe/device/groupHostList.jsp";
/*  46:    */  }
/*  47:    */  
/*  48:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/hostList.do"})
/*  49:    */  @ResponseBody
/*  50: 50 */  public java.util.Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.listByPage(request, model); }
/*  51:    */  
/*  52:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/getHosts.do"})
/*  53:    */  @ResponseBody
/*  54:    */  public java.util.Map<String, Object> getHosts(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  55: 55 */    String gid = request.getParameter("gid");
/*  56: 56 */    String branchId = request.getParameter("branchId");
/*  57: 57 */    return bornData(((HostService)this.service).findByBranch(branchId, gid));
/*  58:    */  }
/*  59:    */  
/*  60:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/getGroupTree.do"})
/*  61:    */  @ResponseBody
/*  62: 62 */  public java.util.Map<String, Object> getGroupTree(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { UserDto user = super.getCurrentUser(request);
/*  63: 63 */    if ("0".equals(user.getBranchId()))
/*  64: 64 */      return bornData(this.cf.getDeviceTree().getAdminDeviceTree());
/*  65: 65 */    return bornData(this.cf.getDeviceTree().getDeviceTree(user.getBranchId()));
/*  66:    */  }
/*  67:    */  
/*  68:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/hostDelete.do"})
/*  69:    */  @ResponseBody
/*  70: 70 */  public java.util.Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { ((HostService)this.service).setCurrentUser(super.getCurrentUser(request));
/*  71: 71 */    return super.deleteByIDs(request, model);
/*  72:    */  }
/*  73:    */  
/*  74:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readyEditName.do"})
/*  75:    */  public String readyEditName(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  76: 76 */    String apid = request.getParameter("id");
/*  77: 77 */    model.addAttribute("ap", ((HostService)this.service).findByID(apid));
/*  78: 78 */    return "/cpe/device/editName.jsp";
/*  79:    */  }
/*  80:    */  
/*  81:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/updateName.do"})
/*  82:    */  @ResponseBody
/*  83: 83 */  public java.util.Map<String, Object> updateName(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { com.soofound.cpe.bean.HostBean hb = (com.soofound.cpe.bean.HostBean)((HostService)this.service).findByID(request.getParameter("id"));
/*  84: 84 */    hb.setName(request.getParameter("name"));
/*  85: 85 */    ((HostService)this.service).updateName(hb);
/*  86: 86 */    return super.getSucceedResult("更新成功.");
/*  87:    */  }
/*  88:    */  
/*  89:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readyEditSsid.do"})
/*  90:    */  public String readyEditSsid(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  91: 91 */    String mode = request.getParameter("batchMode");
/*  92: 92 */    String hostIds = request.getParameter("id");
/*  93: 93 */    String ife = request.getParameter("ife");
/*  94: 94 */    int ifid = Integer.parseInt(ife);
/*  95: 95 */    model.addAttribute("id", hostIds);
/*  96: 96 */    model.addAttribute("ife", ife);
/*  97: 97 */    model.addAttribute("batchMode", mode);
/*  98:    */    
/*  99: 99 */    String hid = hostIds.split(",")[0];
/* 100:100 */    com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)((HostService)this.service).findByID(hid);
/* 101:101 */    List<SurfingPolicyDto> spds = this.sps.findByBranch(host.getBranchId());
/* 102:102 */    model.addAttribute("policies", spds);
/* 103:103 */    List<PortalInstanceDto> pids = this.pis.findByBranch(host.getBranchId());
/* 104:104 */    model.addAttribute("portals", pids);
/* 105:    */    
/* 106:106 */    List<SsidCodeDto> scds = this.scdao.findByBranch(host.getBranchId());
/* 107:107 */    model.addAttribute("scds", scds);
/* 108:108 */    if ("1".equals(mode)) {
/* 109:109 */      model.addAttribute("deviceNum", Integer.valueOf(hostIds.split(",").length));
/* 110:110 */      if (ifid > 200)
/* 111:111 */        return "/cpe/device/lanBatchEdit.jsp";
/* 112:112 */      return "/cpe/device/networkBatchEdit.jsp";
/* 113:    */    }
/* 114:114 */    model.addAttribute("host", host);
/* 115:115 */    model.addAttribute("ssid", this.bdao.findByKey(hostIds, ife));
/* 116:116 */    if (ifid > 200)
/* 117:117 */      return "/cpe/device/lanEdit.jsp";
/* 118:118 */    return "/cpe/device/networkEdit.jsp";
/* 119:    */  }
/* 120:    */  
/* 121:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/updateSsid.do"})
/* 122:    */  @ResponseBody
/* 123:    */  public java.util.Map<String, Object> updateSsid(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 124:124 */    boolean enable = "1".equals(request.getParameter("enable"));
/* 125:125 */    boolean usp = "1".equals(request.getParameter("useSsidPolicy"));
/* 126:126 */    java.util.Map<String, Object> mapresult = super.getSucceedResult("更新成功");
/* 127:127 */    java.util.Map<String, Object> mapresult2 = new HashMap();
/* 128:128 */    mapresult2.put("needRestart", Boolean.valueOf(false));
/* 129:129 */    mapresult.put("data", mapresult2);
/* 130:    */    try {
/* 131:131 */      String name = request.getParameter("ssid");
/* 132:132 */      if ((enable) && (name != null) && (name.indexOf(" ") > 0))
/* 133:133 */        return super.getFailedResult("SSID不能带空格，更新失败.");
/* 134:134 */      int ife = Integer.parseInt(request.getParameter("ife"));
/* 135:135 */      if ((!enable) && (ife == 0)) {
/* 136:136 */        return super.getFailedResult("第一个SSID不能关闭.");
/* 137:    */      }
/* 138:138 */      String[] hostIds = request.getParameter("id").split(",");
/* 139:139 */      com.soofound.cpe.dao.BssidDao dao = new com.soofound.cpe.dao.BssidDao();
/* 140:140 */      com.soofound.cpe.util.CpeActionExecutor cae = new com.soofound.cpe.util.CpeActionExecutor();
/* 141:141 */      List<com.soofound.cpe.bean.HostPropertyBean> props = new ArrayList();
/* 142:142 */      String portalId; String codeId; String oldName; if (enable) {
/* 143:143 */        String policyId = request.getParameter("policyId");
/* 144:144 */        portalId = request.getParameter("portalId");
/* 145:145 */        codeId = request.getParameter("codeId");
/* 146:146 */        if (StringUtils.isEmpty(policyId))
/* 147:147 */          policyId = "0";
/* 148:148 */        if (StringUtils.isEmpty(portalId))
/* 149:149 */          portalId = "0";
/* 150:150 */        if (usp) {
/* 151:151 */          SsidCodeDao cdao = new SsidCodeDao();
/* 152:152 */          SsidCodeDto scd = cdao.findByID(codeId);
/* 153:153 */          name = scd.getSsid();
/* 154:    */        }
/* 155:155 */        oldName = request.getParameter("oldSsid");
/* 156:156 */        boolean ssidModified = (StringUtils.hasText(name)) && (!name.equals(oldName));
/* 157:157 */        for (String hostId : hostIds) {
/* 158:158 */          com.soofound.cpe.bean.BssidDto dto = dao.findByKey(hostId, request.getParameter("ife"));
/* 159:159 */          if (dto == null) {
/* 160:160 */            System.out.println(hostId + "-" + request.getParameter("ife") + " find no SSID");
/* 161:    */          }
/* 162:    */          else {
/* 163:163 */            dto.setApId(Integer.parseInt(hostId));
/* 164:164 */            dto.setIfe(ife);
/* 165:165 */            if ((StringUtils.hasText(name)) && (!"N/A".equals(name)))
/* 166:166 */              dto.setName(name);
/* 167:167 */            if (!"0".equals(policyId))
/* 168:168 */              dto.setPolicyId(Integer.parseInt(policyId));
/* 169:169 */            if (!"0".equals(portalId))
/* 170:170 */              dto.setPortalId(Integer.parseInt(portalId));
/* 171:171 */            if (usp) {
/* 172:172 */              dto.setCodeId(Integer.parseInt(codeId));
/* 173:    */            } else
/* 174:174 */              dto.setCodeId(0);
/* 175:175 */            dto.setEnable(1);
/* 176:176 */            dao.updateSSid(dto);
/* 177:177 */            if (ssidModified) {
/* 178:178 */              com.soofound.cpe.bean.HostPropertyBean prop = new com.soofound.cpe.bean.HostPropertyBean();
/* 179:179 */              prop.setName(((HostService)this.service).getIfName(ife));
/* 180:180 */              prop.setValue(dto.getName());
/* 181:181 */              props.add(prop);
/* 182:    */              
/* 183:183 */              ((HostService)this.service).putCommand(dto.getApId(), cae.getSetParameterValuesString(props));
/* 184:184 */              ((HostService)this.service).putCommand(dto.getApId(), cae.getApplyString());
/* 185:185 */              com.soofound.cpe.util.CpeWaker cw = new com.soofound.cpe.util.CpeWaker();
/* 186:186 */              cw.wakeup(hostId);
/* 187:    */            }
/* 188:    */          }
/* 189:    */        }
/* 190:190 */      } else { codeId = (oldName = hostIds).length; for (portalId = 0; portalId < codeId; portalId++) { String hostId = oldName[portalId];
/* 191:191 */          com.soofound.cpe.bean.BssidDto dto = new com.soofound.cpe.bean.BssidDto();
/* 192:192 */          dto.setApId(Integer.parseInt(hostId));
/* 193:193 */          dto.setIfe(ife);
/* 194:194 */          dto.setName("N/A");
/* 195:195 */          dto.setPolicyId(0);
/* 196:196 */          dto.setPortalId(0);
/* 197:197 */          dto.setEnable(0);
/* 198:198 */          dao.updateSSid(dto);
/* 199:199 */          com.soofound.cpe.bean.HostPropertyBean prop = new com.soofound.cpe.bean.HostPropertyBean();
/* 200:200 */          prop.setName(((HostService)this.service).getIfName(ife));
/* 201:201 */          prop.setValue("N/A");
/* 202:202 */          props.add(prop);
/* 203:    */          
/* 204:204 */          ((HostService)this.service).putCommand(dto.getApId(), cae.getSetParameterValuesString(props));
/* 205:205 */          ((HostService)this.service).putCommand(dto.getApId(), cae.getApplyString());
/* 206:206 */          com.soofound.cpe.util.CpeWaker cw = new com.soofound.cpe.util.CpeWaker();
/* 207:207 */          cw.wakeup(hostId);
/* 208:    */        }
/* 209:    */      }
/* 210:    */    } catch (Exception ex) {
/* 211:211 */      ex.printStackTrace();
/* 212:212 */      return super.getFailedResult("更新失败.");
/* 213:    */    }
/* 214:214 */    return mapresult;
/* 215:    */  }
/* 216:    */  
/* 217:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/updateLan.do"})
/* 218:    */  @ResponseBody
/* 219:219 */  public java.util.Map<String, Object> updateLan(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { int enable = "1".equals(request.getParameter("enable")) ? 1 : 0;
/* 220:220 */    java.util.Map<String, Object> mapresult = super.getSucceedResult("更新成功");
/* 221:221 */    java.util.Map<String, Object> mapresult2 = new HashMap();
/* 222:222 */    mapresult2.put("needRestart", Boolean.valueOf(false));
/* 223:223 */    mapresult.put("data", mapresult2);
/* 224:    */    try {
/* 225:225 */      String[] hostIds = request.getParameter("id").split(",");
/* 226:226 */      com.soofound.cpe.dao.BssidDao dao = new com.soofound.cpe.dao.BssidDao();
/* 227:227 */      com.soofound.cpe.util.CpeActionExecutor cae = new com.soofound.cpe.util.CpeActionExecutor();
/* 228:228 */      List<com.soofound.cpe.bean.HostPropertyBean> props = new ArrayList();
/* 229:    */      
/* 230:230 */      String policyId = request.getParameter("policyId");
/* 231:231 */      String portalId = request.getParameter("portalId");
/* 232:232 */      if (StringUtils.isEmpty(policyId))
/* 233:233 */        policyId = "0";
/* 234:234 */      if (StringUtils.isEmpty(portalId)) {
/* 235:235 */        portalId = "0";
/* 236:    */      }
/* 237:237 */      for (String hostId : hostIds) {
/* 238:238 */        com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)((HostService)this.service).findByID(hostId);
/* 239:239 */        if (((host.getHardwareVersion().equals("RW2400NSC2-W")) || (host.getHardwareVersion().equals("AP5300-W"))) && (enable == 0)) {
/* 240:240 */          return super.getFailedResult("更新失败:面板不能关闭LAN口.");
/* 241:    */        }
/* 242:242 */        com.soofound.cpe.bean.BssidDto dto = dao.findByKey(hostId, request.getParameter("ife"));
/* 243:243 */        dto.setPolicyId(Integer.parseInt(policyId));
/* 244:244 */        dto.setPortalId(Integer.parseInt(portalId));
/* 245:245 */        dto.setEnable(enable);
/* 246:246 */        dao.updateSSid(dto);
/* 247:    */        
/* 248:248 */        com.soofound.cpe.bean.HostPropertyBean prop = new com.soofound.cpe.bean.HostPropertyBean();
/* 249:249 */        prop.setName("InternetGatewayDevice.DeviceInfo.lan_auth");
/* 250:250 */        prop.setValue(enable);
/* 251:251 */        props.add(prop);
/* 252:252 */        String cmdstr = cae.getSetParameterValuesString(props);
/* 253:253 */        ((HostService)this.service).putCommand(dto.getApId(), cmdstr);
/* 254:    */        
/* 255:255 */        com.soofound.cpe.util.CpeWaker cw = new com.soofound.cpe.util.CpeWaker();
/* 256:256 */        cw.wakeup(hostId);
/* 257:    */      }
/* 258:    */    } catch (Exception ex) {
/* 259:259 */      ex.printStackTrace();
/* 260:260 */      return super.getFailedResult("更新失败.");
/* 261:    */    }
/* 262:262 */    return mapresult;
/* 263:    */  }
/* 264:    */  
/* 265:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/getHostStatus.do"})
/* 266:    */  @ResponseBody
/* 267:267 */  public java.util.Map<String, Object> getHostStatus(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String[] ids = request.getParameter("id").split(",");
/* 268:268 */    int status = 1;
/* 269:269 */    for (String id : ids) {
/* 270:270 */      com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)((HostService)this.service).findByID(id);
/* 271:271 */      if (host.isFresh()) {
/* 272:272 */        status = 2;
/* 273:273 */        break; }
/* 274:274 */      if (host.getOnline() != 1) {
/* 275:275 */        status = host.getOnline();
/* 276:276 */        break;
/* 277:    */      }
/* 278:    */    }
/* 279:279 */    java.util.Map<String, Object> result = super.getSucceedResult("");
/* 280:280 */    Object data = new HashMap();
/* 281:281 */    if (status == 0) {
/* 282:282 */      ((java.util.Map)data).put("status", "offline");
/* 283:283 */      ((java.util.Map)data).put("desc", "设备不在线");
/* 284:284 */    } else if (status == 2) {
/* 285:285 */      ((java.util.Map)data).put("status", "reboot");
/* 286:286 */      ((java.util.Map)data).put("desc", "设备在重启");
/* 287:287 */    } else if (status == 3) {
/* 288:288 */      ((java.util.Map)data).put("status", "upgradeFirmware");
/* 289:289 */      ((java.util.Map)data).put("desc", "设备在更新固件");
/* 290:290 */    } else if (status == 4) {
/* 291:291 */      ((java.util.Map)data).put("status", "reset");
/* 292:292 */      ((java.util.Map)data).put("desc", "设备正在恢复出厂设置");
/* 293:    */    } else {
/* 294:294 */      ((java.util.Map)data).put("status", "ready");
/* 295:295 */      ((java.util.Map)data).put("desc", "OK");
/* 296:    */    }
/* 297:297 */    result.put("data", data);
/* 298:298 */    return result;
/* 299:    */  }
/* 300:    */  
/* 311:    */  @Autowired
/* 312:    */  protected void setService(HostService service)
/* 313:    */  {
/* 314:314 */    this.service = service;
/* 315:    */  }
/* 316:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.HostAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */