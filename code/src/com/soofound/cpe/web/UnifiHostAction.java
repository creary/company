/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.alibaba.fastjson.JSON;
/*   4:    */import com.soofound.admin.bean.UserDto;
/*   5:    */import com.soofound.admin.web.BranchService;
/*   6:    */import com.soofound.cpe.bean.HostBean;
/*   7:    */import com.soofound.cpe.bean.HostPropertyBean;
/*   8:    */import com.soofound.cpe.bean.UnifiHostDto;
/*   9:    */import com.soofound.cpe.util.CpeActionExecutor;
/*  10:    */import com.soofound.cpe.util.CpeWaker;
/*  11:    */import com.soofound.framework.util.CommonUtil;
/*  12:    */import com.soofound.framework.util.SysConfigHelper;
/*  13:    */import com.soofound.framework.web.BaseAction;
/*  14:    */import com.soofound.portal.bean.SurfingAccount;
/*  15:    */import com.soofound.portal.bean.SurfingUser;
/*  16:    */import com.soofound.portal.dao.SurfingAccountDao;
/*  17:    */import com.soofound.portal.service.WifiDogService;
/*  18:    */import com.soofound.portal.util.WifiDogUtils;
/*  19:    */import java.io.PrintStream;
/*  20:    */import java.util.ArrayList;
/*  21:    */import java.util.HashMap;
/*  22:    */import java.util.List;
/*  23:    */import java.util.Map;
/*  24:    */import org.apache.commons.lang.StringUtils;
/*  25:    */import org.apache.commons.net.util.Base64;
/*  26:    */import org.springframework.beans.factory.annotation.Autowired;
/*  27:    */import org.springframework.stereotype.Controller;
/*  28:    */import org.springframework.ui.ModelMap;
/*  29:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  30:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  31:    */
/*  32:    */@Controller
/*  33:    */public class UnifiHostAction extends BaseAction<UnifiHostService>
/*  34:    */{
/*  35:    */  private static final String QQ_MM_KEY = "77696669616E74323031353030313038";
/*  36:    */  @Autowired
/*  37:    */  private BranchService branchService;
/*  38:    */  
/*  39:    */  @RequestMapping({"/cpe/unifiListJsp.do"})
/*  40:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, ModelMap model)
/*  41:    */  {
/*  42: 42 */    return "/cpe/struct/unifiList.jsp";
/*  43:    */  }
/*  44:    */  
/*  45:    */  @RequestMapping({"/cpe/unifiList.do"})
/*  46:    */  @ResponseBody
/*  47: 47 */  public Map<String, Object> list(javax.servlet.http.HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/*  48:    */  
/*  49:    */  @RequestMapping({"/cpe/unifiReadyAdd.do"})
/*  50:    */  public String readyAdd(javax.servlet.http.HttpServletRequest request, ModelMap model)
/*  51:    */  {
/*  52: 52 */    UserDto user = super.getCurrentUser(request);
/*  53: 53 */    model.addAttribute("branchId", user.getBranchId());
/*  54: 54 */    return "/cpe/struct/unifiAdd.jsp";
/*  55:    */  }
/*  56:    */  
/*  57:    */  @RequestMapping({"/cpe/unifiSave.do"})
/*  58:    */  @ResponseBody
/*  59: 59 */  public Map<String, Object> save(javax.servlet.http.HttpServletRequest request, ModelMap model) { String site = request.getParameter("site");
/*  60: 60 */    if (StringUtils.isEmpty(site))
/*  61: 61 */      site = request.getParameter("acip");
/*  62: 62 */    if (((UnifiHostService)this.service).findBySite(site) != null) {
/*  63: 63 */      return getFailedResult("[" + site + "]已经存在.");
/*  64:    */    }
/*  65: 65 */    return super.save(request, model);
/*  66:    */  }
/*  67:    */  
/*  68:    */  @RequestMapping({"/cpe/unifiReadyEdit.do"})
/*  69:    */  public String readyEdit(javax.servlet.http.HttpServletRequest request, ModelMap model) {
/*  70: 70 */    String id = request.getParameter("id");
/*  71: 71 */    model.addAttribute("dto", ((UnifiHostService)this.service).findByID(id));
/*  72: 72 */    return "/cpe/struct/unifiEdit.jsp";
/*  73:    */  }
/*  74:    */  
/*  75:    */  @RequestMapping({"/cpe/unifiUpdate.do"})
/*  76:    */  @ResponseBody
/*  77: 77 */  public Map<String, Object> update(javax.servlet.http.HttpServletRequest request, ModelMap model) { String site = request.getParameter("site");
/*  78: 78 */    if (StringUtils.isEmpty(site))
/*  79: 79 */      site = request.getParameter("acip");
/*  80: 80 */    UnifiHostDto dto = ((UnifiHostService)this.service).findBySite(site);
/*  81: 81 */    int id = Integer.parseInt(request.getParameter("id"));
/*  82: 82 */    if ((dto != null) && (dto.getId() != id)) {
/*  83: 83 */      return getFailedResult("[" + site + "]已经存在.");
/*  84:    */    }
/*  85: 85 */    return super.update(request, model);
/*  86:    */  }
/*  87:    */  
/*  88:    */  @RequestMapping({"/cpe/unifiDelete.do"})
/*  89:    */  @ResponseBody
/*  90: 90 */  public Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, ModelMap model) { return super.deleteByIDs(request, model); }
/*  91:    */  
/*  92:    */  @RequestMapping({"/cpe/unifiReadyActive.do"})
/*  93:    */  public String readyActive(javax.servlet.http.HttpServletRequest request, ModelMap model)
/*  94:    */  {
/*  95: 95 */    String[] ids = request.getParameterValues("checkbox");
/*  96: 96 */    UserDto user = super.getCurrentUser(request);
/*  97: 97 */    model.addAttribute("branchs", this.branchService.findByBranch(user.getBranchId()));
/*  98: 98 */    if (ids != null) {
/*  99: 99 */      model.addAttribute("ids", CommonUtil.arrayToString(ids));
/* 100:100 */      model.addAttribute("num", Integer.valueOf(ids.length));
/* 101:    */    }
/* 102:102 */    return "/cpe/struct/unifiActive.jsp";
/* 103:    */  }
/* 104:    */  
/* 105:    */  @RequestMapping({"/cpe/unifiActive.do"})
/* 106:    */  @ResponseBody
/* 107:    */  public Map<String, Object> active(javax.servlet.http.HttpServletRequest request, ModelMap model) {
/* 108:108 */    try { String ids = request.getParameter("ids");
/* 109:109 */      String branchId = request.getParameter("branchId");
/* 110:110 */      ((UnifiHostService)this.service).active(ids.split(","), branchId);
/* 111:111 */      return getSucceedResult("分配成功");
/* 112:    */    } catch (Exception ex) {
/* 113:113 */      ex.printStackTrace(); }
/* 114:114 */    return getFailedResult("分配失败");
/* 115:    */  }
/* 116:    */  
/* 118:    */  @RequestMapping({"/wifiant/tencentCheck.do"})
/* 119:    */  @ResponseBody
/* 120:    */  public String tencentCheck(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 121:    */  {
/* 122:122 */    Map<String, Object> results = new HashMap();
/* 123:123 */    int sessionId = 0;
/* 124:124 */    String params = request.getParameter("params");
/* 125:    */    try {
/* 126:126 */      byte[] qstr = WifiDogUtils.decrypt(params, "77696669616E74323031353030313038");
/* 127:127 */      Map pmap = (Map)JSON.parseObject(new String(qstr), Map.class);
/* 128:128 */      sessionId = ((Integer)pmap.get("session_id")).intValue();
/* 129:129 */      String requrl = (String)pmap.get("url");
/* 130:130 */      Map<String, String> wdps = WifiDogUtils.decodeURL(requrl.substring(requrl.indexOf("?")));
/* 131:131 */      HostService hostService = (HostService)SysConfigHelper.getBean("hostService");
/* 132:132 */      WifiDogService wds = (WifiDogService)SysConfigHelper.getBean("wifiDogService");
/* 133:133 */      HostBean host = hostService.findBySerialNumber((String)wdps.get("apmac"));
/* 134:134 */      SurfingUser user = wds.getSurfingUser(host.getBranchId(), (String)wdps.get("stamac"));
/* 135:135 */      if (user == null) {
/* 136:136 */        user = new SurfingUser();
/* 137:137 */        user.setUsername(((String)wdps.get("stamac")).replace(":", ""));
/* 138:138 */        user.setBranchId(host.getBranchId());
/* 139:139 */        user.setCpeId(host.getId());
/* 140:140 */        user.setTerminalMac((String)wdps.get("stamac"));
/* 141:141 */        user.setTerminalIP((String)wdps.get("staip"));
/* 142:142 */        user.setSsid(host.getId() + "-" + (String)wdps.get("intf"));
/* 143:143 */        user.setFlag("qq_mm");
/* 144:144 */        wds.recordOnline(user);
/* 145:    */        
/* 146:146 */        List<HostPropertyBean> hpbs = new ArrayList();
/* 147:147 */        HostPropertyBean hpb = new HostPropertyBean();
/* 148:148 */        hpb.setName("InternetGatewayDevice.DeviceInfo.add_station_mac");
/* 149:149 */        hpb.setValue((String)wdps.get("stamac"));
/* 150:150 */        hpbs.add(hpb);
/* 151:    */        
/* 152:152 */        CpeWaker cw = new CpeWaker();
/* 153:153 */        CpeActionExecutor cae = new CpeActionExecutor();
/* 154:154 */        hostService.putCommand(host.getId(), cae.getSetParameterValuesString(hpbs));
/* 155:155 */        cw.wakeup(String.valueOf(host.getId()));
/* 156:    */        
/* 157:157 */        System.out.println((String)wdps.get("stamac") + " --login from QQ_MM--");
/* 158:158 */        SurfingAccountDao dao = new SurfingAccountDao();
/* 159:159 */        SurfingAccount dto = dao.findByUsername(host.getBranchId(), user.getUsername());
/* 160:160 */        if (dto == null) {
/* 161:161 */          dto = new SurfingAccount();
/* 162:162 */          dto.setMac((String)wdps.get("stamac"));
/* 163:163 */          dto.setTimes(1);
/* 164:164 */          dto.setOnline(1);
/* 165:165 */          dto.setUsername(user.getUsername());
/* 166:166 */          dto.setBranchId(user.getBranchId());
/* 167:167 */          dto.setPassword("-");
/* 168:168 */          dto.setFlag("qq_mm");
/* 169:169 */          dao.save(dto);
/* 170:    */        }
/* 171:    */      }
/* 172:172 */      results.put("api_code", Integer.valueOf(1));
/* 173:173 */      results.put("session_id", Integer.valueOf(sessionId));
/* 174:174 */      results.put("ad_url", "http://www.wifiant.cn/softac/wifiant/tencentAd.do?p=123");
/* 175:    */    } catch (Exception e) {
/* 176:176 */      e.printStackTrace();
/* 177:177 */      results.put("api_code", Integer.valueOf(0));
/* 178:178 */      results.put("session_id", Integer.valueOf(sessionId));
/* 179:    */    }
/* 180:180 */    String returnstr = Base64.encodeBase64String(WifiDogUtils.encrypt(JSON.toJSONString(results), "77696669616E74323031353030313038"));
/* 181:181 */    return returnstr;
/* 182:    */  }
/* 183:    */  
/* 184:    */  @RequestMapping({"/wifiant/tencentAd.do"})
/* 185:    */  public String tencentAd(javax.servlet.http.HttpServletRequest request, ModelMap model) {
/* 186:186 */    System.out.println("tencentAd#" + request.getRequestURL());
/* 187:187 */    return "redirect:http://www.redwave.cc/";
/* 188:    */  }
/* 189:    */  
/* 191:    */  @Autowired
/* 192:    */  protected void setService(UnifiHostService service)
/* 193:    */  {
/* 194:194 */    this.service = service;
/* 195:    */  }
/* 196:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.UnifiHostAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */