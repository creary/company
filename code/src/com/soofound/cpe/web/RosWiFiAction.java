/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.soofound.framework.util.DateUtil;
/*   4:    */import com.soofound.framework.web.BaseAction;
/*   5:    */import com.soofound.portal.bean.SurfingAccount;
/*   6:    */import com.soofound.portal.bean.SurfingSession;
/*   7:    */import com.soofound.portal.service.SurfingAccountService;
/*   8:    */import com.soofound.portal.service.SurfingPolicyService;
/*   9:    */import com.soofound.portal.service.SurfingSessionService;
/*  10:    */import java.io.PrintStream;
/*  11:    */import java.util.HashMap;
/*  12:    */import java.util.UUID;
/*  13:    */import javax.servlet.http.HttpSession;
/*  14:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  15:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  16:    */import org.springframework.stereotype.Controller;
/*  17:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  18:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  19:    */
/*  20:    */@Controller
/*  21:    */public final class RosWiFiAction extends BaseAction<HostService>
/*  22:    */{
/*  23:    */  private static final String AUTH_DENIED = "Auth: 0";
/*  24:    */  private static final String AUTH_ALLOWED = "Auth: 1";
/*  25:    */  private static final String AUTH_ERROR = "Auth: -1";
/*  26:    */  @org.springframework.beans.factory.annotation.Autowired
/*  27:    */  private SurfingSessionService sss;
/*  28:    */  @org.springframework.beans.factory.annotation.Autowired
/*  29:    */  private SurfingAccountService sas;
/*  30:    */  @org.springframework.beans.factory.annotation.Autowired
/*  31:    */  private HostService hostService;
/*  32:    */  @org.springframework.beans.factory.annotation.Autowired
/*  33:    */  private com.soofound.portal.service.WifiDogService service;
/*  34:    */  @org.springframework.beans.factory.annotation.Autowired
/*  35:    */  @Qualifier("defaultJdbcTemplate")
/*  36:    */  protected JdbcTemplate jdbcTemplate;
/*  37:    */  @org.springframework.beans.factory.annotation.Autowired
/*  38:    */  private SurfingPolicyService sps;
/*  39:    */  
/*  40:    */  @RequestMapping({"/cpe/updateRosSsid.do"})
/*  41:    */  @ResponseBody
/*  42:    */  public java.util.Map<String, Object> updateSsid(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  43:    */  {
/*  44: 44 */    java.util.Map<String, Object> mapresult = super.getSucceedResult("更新成功");
/*  45: 45 */    java.util.Map<String, Object> mapresult2 = new HashMap();
/*  46: 46 */    mapresult2.put("needRestart", Boolean.valueOf(false));
/*  47: 47 */    mapresult.put("data", mapresult2);
/*  48:    */    
/*  49: 49 */    String policyId = request.getParameter("policyId");
/*  50: 50 */    String portalId = request.getParameter("portalId");
/*  51: 51 */    if (org.springframework.util.StringUtils.isEmpty(policyId))
/*  52: 52 */      policyId = "0";
/*  53: 53 */    if (org.springframework.util.StringUtils.isEmpty(portalId)) {
/*  54: 54 */      portalId = "0";
/*  55:    */    }
/*  56: 56 */    String[] hostIds = request.getParameter("id").split(",");
/*  57: 57 */    for (String hostId : hostIds) {
/*  58:    */      try {
/*  59: 59 */        com.soofound.cpe.bean.BssidDto dto = new com.soofound.cpe.bean.BssidDto();
/*  60: 60 */        dto.setApId(Integer.parseInt(hostId));
/*  61: 61 */        dto.setIfe(0);
/*  62: 62 */        dto.setName("MiKroTik");
/*  63: 63 */        dto.setPolicyId(Integer.parseInt(policyId));
/*  64: 64 */        dto.setPortalId(Integer.parseInt(portalId));
/*  65: 65 */        dto.setEnable(1);
/*  66:    */        
/*  67: 67 */        StringBuilder sqlText = new StringBuilder(200);
/*  68: 68 */        sqlText.append("update cpe_ssid set policy_id=").append(dto.getPolicyId()).append(",portal_id=").append(dto.getPortalId());
/*  69: 69 */        sqlText.append(",enable=1 where ap_id=").append(dto.getApId()).append(" and ife=0");
/*  70:    */        
/*  71: 71 */        this.jdbcTemplate.update(sqlText.toString());
/*  72:    */      } catch (Exception ex) {
/*  73: 73 */        ex.printStackTrace();
/*  74: 74 */        return super.getFailedResult("更新失败.");
/*  75:    */      }
/*  76:    */    }
/*  77: 77 */    return mapresult;
/*  78:    */  }
/*  79:    */  
/*  81:    */  @RequestMapping({"/wifiant/rosAuth/"})
/*  82:    */  @ResponseBody
/*  83:    */  public String auth(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  84:    */  {
/*  85: 85 */    java.util.Map<String, String> params = com.soofound.portal.util.WifiDogUtils.decodeURL(request.getQueryString());
/*  86: 86 */    String stamac = (String)params.get("stamac");
/*  87: 87 */    String staip = (String)params.get("staip");
/*  88: 88 */    if (org.springframework.util.StringUtils.isEmpty(stamac))
/*  89: 89 */      return "Auth: -1";
/*  90:    */    try {
/*  91: 91 */      com.soofound.portal.bean.SurfingUser user = this.service.getSurfingUser(null, stamac);
/*  92: 92 */      if (user == null)
/*  93: 93 */        return "Auth: 0";
/*  94: 94 */      if (user.getStatus() == 0) {
/*  95: 95 */        System.out.println("User is force to offline:" + stamac);
/*  96: 96 */        this.service.recordOffline(user);
/*  97: 97 */        return "Auth: 0" + user.getTerminalMac();
/*  98:    */      }
/*  99: 99 */      user.setTerminalIP(staip);
/* 100:100 */      String stage = (String)params.get("stage");
/* 101:101 */      if (("login".equals(stage)) && (user.getStatus() == 1)) {
/* 102:102 */        this.service.recordOnline(user);
/* 103:103 */      } else { if ("logout".equals(stage)) {
/* 104:104 */          this.service.recordOffline(user);
/* 105:105 */          return "Auth: 0" + user.getTerminalMac();
/* 106:    */        }
/* 107:107 */        long incoming = 0L;
/* 108:108 */        long outgoing = 0L;
/* 109:109 */        if (org.springframework.util.StringUtils.hasText((String)params.get("incoming")))
/* 110:110 */          incoming = Long.parseLong((String)params.get("incoming"));
/* 111:111 */        if (org.springframework.util.StringUtils.hasText((String)params.get("outgoing")))
/* 112:112 */          outgoing = Long.parseLong((String)params.get("outgoing"));
/* 113:113 */        incoming /= 1024L;
/* 114:114 */        outgoing /= 1024L;
/* 115:115 */        if ((incoming > user.getInputOctets()) || (outgoing > user.getOutputOctets())) {
/* 116:116 */          user.setInputOctets(incoming);
/* 117:117 */          user.setOutputOctets(outgoing);
/* 118:118 */          this.service.recordTraffic(user);
/* 119:    */        }
/* 120:    */      }
/* 121:    */    } catch (Exception ex) {
/* 122:122 */      ex.printStackTrace();
/* 123:    */    }
/* 124:124 */    return "Auth: 1";
/* 125:    */  }
/* 126:    */  
/* 128:    */  @RequestMapping({"/wifiant/portalAuth.do"})
/* 129:    */  @ResponseBody
/* 130:    */  public java.util.Map<String, Object> portalAuth(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 131:    */  {
/* 132:132 */    int loc = request.getQueryString().indexOf("&random=");
/* 133:133 */    String queryStr = null;
/* 134:134 */    if (loc == -1) {
/* 135:135 */      queryStr = request.getQueryString();
/* 136:    */    } else
/* 137:137 */      queryStr = request.getQueryString().substring(0, loc);
/* 138:138 */    java.util.Map<String, String> params = com.soofound.portal.util.WifiDogUtils.decodeURL(queryStr);
/* 139:139 */    String mac = (String)params.get("stamac");
/* 140:140 */    String wifiDogAddress = (String)params.get("gwip");
/* 141:141 */    String wifiDogPort = (String)params.get("gwport");
/* 142:142 */    String apmac = (String)params.get("apmac");
/* 143:143 */    String staip = (String)params.get("staip");
/* 144:144 */    String url = (String)params.get("url");
/* 145:145 */    String username = request.getParameter("username");
/* 146:146 */    String password = request.getParameter("password");
/* 147:147 */    String captcha = request.getParameter("captcha");
/* 148:    */    try {
/* 149:149 */      String error = null;
/* 150:150 */      com.soofound.cpe.bean.HostBean host = this.hostService.findBySerialNumber(apmac);
/* 151:151 */      if (host == null) {
/* 152:152 */        System.out.println(request.getRemoteAddr() + "$/wifiant/portalAuth?" + (String)params.get("orgUrl"));
/* 153:153 */        error = "您接入了未授权的AP，为避免安全风险，请先退出。";
/* 154:    */      }
/* 155:155 */      if (error != null)
/* 156:156 */        return com.soofound.portal.util.WifiDogUtils.getSDTResult(Integer.valueOf(2), error);
/* 157:157 */      if ((captcha != null) && (!captcha.equals(request.getSession().getAttribute("image_captcha"))))
/* 158:158 */        return com.soofound.portal.util.WifiDogUtils.getSDTResult(Integer.valueOf(2), "验证码不正确");
/* 159:159 */      SurfingAccount sa = this.sas.findByUsername(host.getBranchId(), username);
/* 160:160 */      if (sa == null)
/* 161:161 */        return com.soofound.portal.util.WifiDogUtils.getSDTResult(Double.valueOf(2.1D), "手机号输入错误，请重填或申请密码。");
/* 162:162 */      sa = this.sas.findByAuth(host.getBranchId(), username, password);
/* 163:163 */      if (sa == null)
/* 164:164 */        return com.soofound.portal.util.WifiDogUtils.getSDTResult(Double.valueOf(2.1D), "密码错误，请重填或申请新的密码。");
/* 165:165 */      String ssid = host.getId() + "-" + (String)params.get("intf");
/* 166:166 */      com.soofound.portal.bean.SurfingPolicyDto spd = this.sps.getPolicy(ssid);
/* 167:167 */      if (spd.getStayTime() > 0) {
/* 168:168 */        SurfingSession stat = this.sss.getUserForStayTime(host.getBranchId(), username);
/* 169:169 */        if ((stat != null) && (stat.getSessionTime() >= spd.getStayTime() * 60))
/* 170:170 */          return com.soofound.portal.util.WifiDogUtils.getSDTResult(Integer.valueOf(2), "一天内上网时间不能超过" + spd.getStayTime() + "分钟");
/* 171:    */      }
/* 172:172 */      if ((spd != null) && (spd.getValidity() > 0)) {
/* 173:173 */        boolean expired = DateUtil.getDiffMinutes(sa.getCreateTime(), DateUtil.getCurrentDateTime()) > spd.getValidity();
/* 174:174 */        if (expired)
/* 175:175 */          return com.soofound.portal.util.WifiDogUtils.getSDTResult(Double.valueOf(2.1D), "对不起，您的密码已过期，请重新申请密码。");
/* 176:    */      } else {
/* 177:177 */        System.out.println(ssid + "portal策略不对");
/* 178:    */      }
/* 179:179 */      com.soofound.portal.bean.SurfingUser user = this.service.getSurfingUser(host.getBranchId(), mac);
/* 180:180 */      if (user == null) {
/* 181:181 */        user = new com.soofound.portal.bean.SurfingUser();
/* 182:182 */        user.setTerminalMac(mac);
/* 183:    */      } else {
/* 184:184 */        this.service.recordOffline(user);
/* 185:    */      }
/* 186:186 */      sa.setMac(mac);
/* 187:187 */      this.sas.update(sa);
/* 188:    */      
/* 189:189 */      if (spd.getJumpTo() == 3)
/* 190:190 */        user.setRoamUrl((String)params.get("url"));
/* 191:191 */      user.setSsid(ssid);
/* 192:192 */      user.setUsername(username);
/* 193:193 */      user.setBranchId(host.getBranchId());
/* 194:194 */      user.setCpeId(host.getId());
/* 195:195 */      user.setTerminalMac(mac);
/* 196:196 */      user.setTerminalIP(staip);
/* 197:197 */      user.setFlag(sa.getFlag());
/* 198:198 */      user.setIfe(Integer.parseInt((String)params.get("intf")));
/* 199:199 */      StringBuilder wifiDogUrl = new StringBuilder(100);
/* 200:200 */      java.util.Map<String, Object> result = com.soofound.portal.util.WifiDogUtils.getSDTResult(Integer.valueOf(1), "认证成功");
/* 201:    */      
/* 202:202 */      wifiDogUrl.append("http://").append(wifiDogAddress).append(":").append(wifiDogPort);
/* 203:203 */      wifiDogUrl.append("/wifidog/auth?url=").append(url).append("&token=").append(UUID.randomUUID().toString());
/* 204:204 */      result.put("wifidog", wifiDogUrl.toString());
/* 205:205 */      return result;
/* 206:    */    } catch (Exception ex) {
/* 207:207 */      model.addAttribute("error", ex.getMessage());
/* 208:208 */      ex.printStackTrace();
/* 209:    */    }
/* 210:210 */    return com.soofound.portal.util.WifiDogUtils.getSDTResult(Integer.valueOf(2), "认证失败");
/* 211:    */  }
/* 212:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.RosWiFiAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */