/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.HostBean;
/*   4:    */import com.soofound.cpe.web.HostService;
/*   5:    */import com.soofound.framework.util.DateUtil;
/*   6:    */import com.soofound.framework.web.BaseAction;
/*   7:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*   8:    */import com.soofound.portal.bean.SurfingUser;
/*   9:    */import com.soofound.portal.service.SurfingAccountService;
/*  10:    */import com.soofound.portal.service.SurfingPolicyService;
/*  11:    */import com.soofound.portal.service.WifiDogService;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.HashMap;
/*  14:    */import javax.servlet.http.HttpServletResponse;
/*  15:    */import org.springframework.beans.factory.annotation.Autowired;
/*  16:    */import org.springframework.stereotype.Controller;
/*  17:    */import org.springframework.util.StringUtils;
/*  18:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  19:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  20:    */
/*  21:    */@Controller
/*  22:    */public class SurfingSessionAction extends BaseAction<com.soofound.portal.service.SurfingSessionService>
/*  23:    */{
/*  24:    */  @Autowired
/*  25:    */  private HostService hostService;
/*  26:    */  @Autowired
/*  27:    */  private SurfingPolicyService sps;
/*  28:    */  @Autowired
/*  29:    */  private SurfingAccountService sas;
/*  30:    */  @Autowired
/*  31:    */  private WifiDogService wds;
/*  32:    */  
/*  33:    */  @RequestMapping({"/portal/sessionListJsp.do"})
/*  34:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  35:    */  {
/*  36: 36 */    String apid = request.getParameter("apid");
/*  37: 37 */    if (apid == null) {
/*  38: 38 */      model.addAttribute("username", request.getParameter("username"));
/*  39: 39 */      model.addAttribute("bid", request.getParameter("branchId"));
/*  40: 40 */      model.addAttribute("history", request.getParameter("history"));
/*  41: 41 */      return "/portal/surfing/sessionList.jsp";
/*  42:    */    }
/*  43: 43 */    HostBean host = (HostBean)this.hostService.findByID(apid);
/*  44: 44 */    if (host != null)
/*  45: 45 */      model.addAttribute("host", host);
/*  46: 46 */    model.addAttribute("apid", apid);
/*  47: 47 */    return "/portal/surfing/apUserList.jsp";
/*  48:    */  }
/*  49:    */  
/*  50:    */  @RequestMapping({"/portal/sessionList.do"})
/*  51:    */  @ResponseBody
/*  52: 52 */  public java.util.Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.listByPage(request, model); }
/*  53:    */  
/*  54:    */  @RequestMapping({"/portal/surfingSessionDelete.do"})
/*  55:    */  @ResponseBody
/*  56:    */  public java.util.Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  57: 57 */    doOffline(request.getParameterValues("checkbox"));
/*  58: 58 */    return super.deleteByIDs(request, model);
/*  59:    */  }
/*  60:    */  
/*  61:    */  @RequestMapping({"/portal/surfingSessionOffline.do"})
/*  62:    */  @ResponseBody
/*  63: 63 */  public java.util.Map<String, Object> forceOffline(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { doOffline(request.getParameterValues("checkbox"));
/*  64: 64 */    return super.getSucceedResult("操作成功");
/*  65:    */  }
/*  66:    */  
/*  67:    */  private void doOffline(String[] ids) {
/*  68: 68 */    java.util.List<com.soofound.portal.bean.SurfingSession> sessions = ((com.soofound.portal.service.SurfingSessionService)this.service).getSurfingSessions(ids);
/*  69: 69 */    if (sessions != null) {
/*  70: 70 */      for (com.soofound.portal.bean.SurfingSession session : sessions) {
/*  71: 71 */        SurfingUser su = this.wds.getSurfingUser(session.getBranchId(), session.getMac());
/*  72: 72 */        if (su != null) {
/*  73: 73 */          this.wds.recordOffline(su);
/*  74:    */        }
/*  75:    */      }
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  80:    */  @RequestMapping({"/custom/readySurfingAccountList.do"})
/*  81:    */  public String readySurfingAccountList(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  82:    */  {
/*  83: 83 */    String branchId = request.getParameter("branchId");
/*  84: 84 */    java.util.List<com.soofound.portal.bean.SurfingAccount> dtos = this.sas.findByBranch(branchId, null);
/*  85: 85 */    int onlineTotal = 0;
/*  86: 86 */    for (com.soofound.portal.bean.SurfingAccount dto : dtos) {
/*  87: 87 */      if (dto.getOnline() == 1)
/*  88: 88 */        onlineTotal++;
/*  89:    */    }
/*  90: 90 */    model.addAttribute("total", Integer.valueOf(dtos.size()));
/*  91: 91 */    model.addAttribute("onlineTotal", Integer.valueOf(onlineTotal));
/*  92: 92 */    return "/ucs/fly/branch/icbcDeyang/surfing/userList.jsp";
/*  93:    */  }
/*  94:    */  
/*  95:    */  @RequestMapping({"/custom/surfingAccountList.do"})
/*  96:    */  @ResponseBody
/*  97: 97 */  public java.util.Map<String, Object> surfingAccountList(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String branchId = request.getParameter("branchId");
/*  98: 98 */    int count = 0;
/*  99: 99 */    if (request.getParameter("count") != null)
/* 100:100 */      count = Integer.parseInt(request.getParameter("count"));
/* 101:101 */    int stayLong = getStayTime(branchId);
/* 102:102 */    java.util.List<com.soofound.portal.bean.SurfingSession> ssdtos = ((com.soofound.portal.service.SurfingSessionService)this.service).getUsersForStayTime(branchId);
/* 103:103 */    String online = request.getParameter("key");
/* 104:104 */    if (StringUtils.isEmpty(online))
/* 105:105 */      online = "1";
/* 106:106 */    java.util.List<com.soofound.portal.bean.SurfingAccount> dtos = this.sas.findByBranch(branchId, online);
/* 107:107 */    java.util.List<com.soofound.portal.bean.CustomSurfingUser> csus = new ArrayList(dtos.size());
/* 108:108 */    for (com.soofound.portal.bean.SurfingAccount dto : dtos) {
/* 109:109 */      com.soofound.portal.bean.SurfingSession ssdto = getSurfingSession(ssdtos, dto.getUsername());
/* 110:110 */      csus.add(bornCustomSurfingUser(dto, ssdto, stayLong));
/* 111:111 */      if ((count > 0) && (csus.size() >= count))
/* 112:    */        break;
/* 113:    */    }
/* 114:114 */    java.util.Map<String, Object> result = new HashMap();
/* 115:115 */    Object meta = new HashMap();
/* 116:116 */    ((java.util.Map)meta).put("total", Integer.valueOf(csus.size()));
/* 117:117 */    result.put("meta", meta);
/* 118:118 */    result.put("records", csus);
/* 119:119 */    return result;
/* 120:    */  }
/* 121:    */  
/* 122:    */  private com.soofound.portal.bean.CustomSurfingUser bornCustomSurfingUser(com.soofound.portal.bean.SurfingAccount dto, com.soofound.portal.bean.SurfingSession ssdto, int stayLong) {
/* 123:123 */    com.soofound.portal.bean.CustomSurfingUser csu = new com.soofound.portal.bean.CustomSurfingUser();
/* 124:124 */    csu.setId(String.valueOf(dto.getId()));
/* 125:125 */    csu.setUsername(dto.getUsername());
/* 126:126 */    if (dto.getFlag().equals("mobile")) {
/* 127:127 */      csu.setIsRandom(false);
/* 128:    */    } else
/* 129:129 */      csu.setIsRandom(true);
/* 130:130 */    if (StringUtils.hasText(dto.getNickname())) {
/* 131:131 */      csu.setRemark(dto.getNickname());
/* 132:    */    } else
/* 133:133 */      csu.setRemark("");
/* 134:134 */    if (ssdto == null) {
/* 135:135 */      csu.setOnlineText("offline");
/* 136:136 */      csu.setTimeText("暂未使用");
/* 137:137 */      csu.setUsedBarText("剩" + DateUtil.secondToTimeString(stayLong));
/* 138:138 */      csu.setUsedTimeWidth("0%");
/* 139:139 */      csu.setRxText("");
/* 140:    */    } else {
/* 141:141 */      long useTimeLong = ssdto.getSessionTime();
/* 142:142 */      if (useTimeLong >= stayLong) {
/* 143:143 */        useTimeLong = stayLong;
/* 144:144 */        csu.setExpired(true);
/* 145:    */      }
/* 146:146 */      csu.setRxText("已使用:" + ssdto.getTxText());
/* 147:147 */      StringBuilder barText = new StringBuilder(100);
/* 148:148 */      barText.append("已用").append(DateUtil.secondToTimeString(useTimeLong)).append(",");
/* 149:149 */      if (useTimeLong > stayLong)
/* 150:150 */        useTimeLong = stayLong;
/* 151:151 */      if (dto.getOnline() == 1) {
/* 152:152 */        csu.setOnlineText("online");
/* 153:    */        try {
/* 154:154 */          csu.setTimeText(ssdto.getStartTime().substring(11, 16) + "上线");
/* 155:    */        } catch (Exception e) {
/* 156:156 */          csu.setTimeText("");
/* 157:    */        }
/* 158:158 */        barText.append("剩").append(DateUtil.secondToTimeString(stayLong - useTimeLong));
/* 159:    */      } else {
/* 160:160 */        csu.setOnlineText("offline");
/* 161:    */        try {
/* 162:162 */          csu.setTimeText(ssdto.getStartTime().substring(11, 16) + "上线," + ssdto.getStopTime().substring(11, 16) + "下线");
/* 163:    */        } catch (Exception e) {
/* 164:164 */          csu.setTimeText("暂未使用.");
/* 165:165 */          csu.setRxText("");
/* 166:    */        }
/* 167:    */      }
/* 168:168 */      csu.setUsedTimeWidth((int)(useTimeLong * 100L / stayLong) + "%");
/* 169:169 */      if (useTimeLong == stayLong) {
/* 170:170 */        csu.setUsedBarText("(已到期)");
/* 171:    */      } else
/* 172:172 */        csu.setUsedBarText(barText.toString());
/* 173:    */    }
/* 174:174 */    return csu;
/* 175:    */  }
/* 176:    */  
/* 177:    */  @RequestMapping({"/custom/reactivate.do"})
/* 178:    */  @ResponseBody
/* 179:179 */  public java.util.Map<String, Object> reactivate(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { java.util.Map<String, Object> result = null;
/* 180:180 */    String branchId = request.getParameter("branchId");
/* 181:181 */    String id = request.getParameter("id");
/* 182:    */    try {
/* 183:183 */      com.soofound.portal.bean.SurfingAccount dto = (com.soofound.portal.bean.SurfingAccount)this.sas.findByID(id);
/* 184:184 */      java.util.List<com.soofound.portal.bean.SurfingAccount> sas = new ArrayList();
/* 185:185 */      sas.add(dto);
/* 186:186 */      ((com.soofound.portal.service.SurfingSessionService)this.service).deleteSessions(sas);
/* 187:187 */      result = getSucceedResult("操作成功");
/* 188:188 */      result.put("data", bornCustomSurfingUser(dto, null, getStayTime(branchId)));
/* 189:    */    } catch (Exception e) {
/* 190:190 */      result = getFailedResult("操作失败");
/* 191:191 */      result.put("data", new HashMap());
/* 192:192 */      e.printStackTrace();
/* 193:    */    }
/* 194:194 */    return result;
/* 195:    */  }
/* 196:    */  
/* 197:    */  @RequestMapping({"/custom/updateNickname.do"})
/* 198:    */  @ResponseBody
/* 199:199 */  public java.util.Map<String, Object> updateNickname(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { java.util.Map<String, Object> result = null;
/* 200:200 */    String branchId = request.getParameter("branchId");
/* 201:201 */    String id = request.getParameter("id");
/* 202:202 */    String remark = request.getParameter("remark");
/* 203:    */    try {
/* 204:204 */      this.sas.updateNickname(id, remark);
/* 205:205 */      com.soofound.portal.bean.SurfingAccount dto = (com.soofound.portal.bean.SurfingAccount)this.sas.findByID(id);
/* 206:206 */      com.soofound.portal.bean.SurfingSession ssdto = ((com.soofound.portal.service.SurfingSessionService)this.service).getUserForStayTime(dto.getBranchId(), dto.getUsername());
/* 207:207 */      result = getSucceedResult("操作成功");
/* 208:208 */      result.put("data", bornCustomSurfingUser(dto, ssdto, getStayTime(branchId)));
/* 209:    */    } catch (Exception e) {
/* 210:210 */      result = getFailedResult("操作失败");
/* 211:211 */      result.put("data", new HashMap());
/* 212:212 */      e.printStackTrace();
/* 213:    */    }
/* 214:214 */    return result;
/* 215:    */  }
/* 216:    */  
/* 217:    */  private int getStayTime(String branchId) {
/* 218:218 */    int stayLong = 0;
/* 219:219 */    SurfingPolicyDto policy = this.sps.getDefaultPolicy(branchId);
/* 220:220 */    if ((policy != null) && (policy.getStayTime() > 0)) {
/* 221:221 */      stayLong = policy.getStayTime() * 60;
/* 222:    */    } else
/* 223:223 */      stayLong = 7200;
/* 224:224 */    return stayLong;
/* 225:    */  }
/* 226:    */  
/* 227:    */  private com.soofound.portal.bean.SurfingSession getSurfingSession(java.util.List<com.soofound.portal.bean.SurfingSession> ssdtos, String username) {
/* 228:228 */    for (com.soofound.portal.bean.SurfingSession ssdto : ssdtos) {
/* 229:229 */      if (ssdto.getUsername().equals(username))
/* 230:230 */        return ssdto;
/* 231:    */    }
/* 232:232 */    return null;
/* 233:    */  }
/* 234:    */  
/* 235:    */  @RequestMapping({"/portal/surfingSessionExport.do"})
/* 236:    */  public void export(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {
/* 237:237 */    String branchId = request.getParameter("branchId");
/* 238:238 */    String history = request.getParameter("history");
/* 239:239 */    String fileName = ((com.soofound.portal.service.SurfingSessionService)this.service).doExport(branchId, StringUtils.hasText(history));
/* 240:240 */    logOperation(request, "导出用户上网日志");
/* 241:241 */    super.downloadFile(response, fileName);
/* 242:    */  }
/* 243:    */  
/* 251:    */  @Autowired
/* 252:    */  protected void setService(com.soofound.portal.service.SurfingSessionService service)
/* 253:    */  {
/* 254:254 */    this.service = service;
/* 255:    */  }
/* 256:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.SurfingSessionAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */