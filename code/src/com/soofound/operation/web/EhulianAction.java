/*   1:    */package com.soofound.operation.web;
/*   2:    */
/*   3:    */import com.alibaba.fastjson.JSON;
/*   4:    */import com.soofound.admin.bean.BranchDao;
/*   5:    */import com.soofound.cpe.util.CpeActionExecutor;
/*   6:    */import com.soofound.cpe.util.CpeUtils;
/*   7:    */import com.soofound.cpe.util.SoofacACS;
/*   8:    */import com.soofound.cpe.util.StunServerLauncher;
/*   9:    */import com.soofound.cpe.web.HostService;
/*  10:    */import com.soofound.framework.jdbc.DynamicDataSource;
/*  11:    */import com.soofound.framework.util.CommonUtil;
/*  12:    */import com.soofound.framework.util.SysConfigHelper;
/*  13:    */import com.soofound.framework.web.GenericAction;
/*  14:    */import com.soofound.portal.dao.WechatGuideDao;
/*  15:    */import com.soofound.portal.dao.WechatIPPolicyDao;
/*  16:    */import com.soofound.portal.service.WifiDogService;
/*  17:    */import com.soofound.project.ehulian.EhulianSmsSender;
/*  18:    */import com.soofound.protocol.stun.StunServer;
/*  19:    */import java.io.PrintStream;
/*  20:    */import java.net.InetAddress;
/*  21:    */import java.util.ArrayList;
/*  22:    */import java.util.HashMap;
/*  23:    */import java.util.List;
/*  24:    */import org.springframework.stereotype.Controller;
/*  25:    */import org.springframework.util.StringUtils;
/*  26:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  27:    */
/*  28:    */@Controller
/*  29:    */public class EhulianAction extends GenericAction
/*  30:    */{
/*  31:    */  @org.springframework.beans.factory.annotation.Autowired
/*  32:    */  private HostService hs;
/*  33:    */  @org.springframework.beans.factory.annotation.Autowired
/*  34:    */  private WifiDogService wds;
/*  35:    */  @org.springframework.beans.factory.annotation.Autowired
/*  36:    */  private SoofacACS acs;
/*  37:    */  @org.springframework.beans.factory.annotation.Autowired
/*  38:    */  private BranchDao bdao;
/*  39:    */  @org.springframework.beans.factory.annotation.Autowired
/*  40:    */  private WechatGuideDao wgdao;
/*  41:    */  @org.springframework.beans.factory.annotation.Autowired
/*  42:    */  private WechatIPPolicyDao wipdao;
/*  43:    */  
/*  44:    */  @org.springframework.web.bind.annotation.RequestMapping({"/acs/changeDataSource.do"})
/*  45:    */  @ResponseBody
/*  46:    */  public String changeDataSource(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  47:    */  {
/*  48: 48 */    String ds = request.getParameter("ds");
/*  49: 49 */    if (StringUtils.isEmpty(ds)) {
/*  50: 50 */      return "Fail:dataSource can't not be null";
/*  51:    */    }
/*  52: 52 */    DynamicDataSource dds = (DynamicDataSource)SysConfigHelper.getBean("defaultDS");
/*  53: 53 */    dds.setDsName(ds);
/*  54: 54 */    return "dataSource Change to [" + ds + "]";
/*  55:    */  }
/*  56:    */  
/*  57:    */  @org.springframework.web.bind.annotation.RequestMapping({"/acs/sendSms.do"})
/*  58:    */  @ResponseBody
/*  59: 59 */  public boolean sendSms(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String phone = request.getParameter("phone");
/*  60: 60 */    String content = request.getParameter("content");
/*  61: 61 */    EhulianSmsSender ess = new EhulianSmsSender();
/*  62: 62 */    boolean ok = ess.sendSMS("0", phone, content);
/*  63: 63 */    return ok;
/*  64:    */  }
/*  65:    */  
/*  66:    */  @org.springframework.web.bind.annotation.RequestMapping({"/acs/getWechatResponseHref.do"})
/*  67:    */  public String getWechatResponseHref(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  68: 68 */    String ip = getRemoteIP(request);
/*  69:    */    try {
/*  70: 70 */      com.soofound.portal.bean.WechatIPPolicyDto dto = this.wipdao.findByIP(ip);
/*  71: 71 */      if ((dto != null) && (!CommonUtil.isEmpty(dto.getPostUrl()))) {
/*  72: 72 */        return "redirect:" + dto.getPostUrl();
/*  73:    */      }
/*  74: 74 */      com.soofound.admin.bean.BranchDto bd = this.bdao.findByOpenId(request.getParameter("pwx"));
/*  75: 75 */      java.util.Map<String, String> jsons = (java.util.Map)JSON.parseObject(this.wgdao.getWechatResponse(bd.getId()), java.util.Map.class);
/*  76: 76 */      if (StringUtils.hasText((String)jsons.get("welcMsgHref")))
/*  77: 77 */        return "redirect:" + (String)jsons.get("welcMsgHref");
/*  78:    */    } catch (Exception ex) {
/*  79: 79 */      ex.printStackTrace();
/*  80:    */    }
/*  81: 81 */    return "redirect:http://www.qq.com";
/*  82:    */  }
/*  83:    */  
/*  84:    */  @org.springframework.web.bind.annotation.RequestMapping({"/acs/getWechatResponseImage.do"})
/*  85:    */  public String getWechatResponseImage(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  86: 86 */    String ip = getRemoteIP(request);
/*  87:    */    try {
/*  88: 88 */      com.soofound.portal.bean.WechatIPPolicyDto dto = this.wipdao.findByIP(ip);
/*  89: 89 */      if ((dto != null) && (!CommonUtil.isEmpty(dto.getImgUrl())))
/*  90: 90 */        return getImageURL(dto.getImgUrl());
/*  91: 91 */      com.soofound.admin.bean.BranchDto bd = this.bdao.findByOpenId(request.getParameter("pwx"));
/*  92: 92 */      java.util.Map<String, String> jsons = (java.util.Map)JSON.parseObject(this.wgdao.getWechatResponse(bd.getId()), java.util.Map.class);
/*  93: 93 */      if (StringUtils.hasText((String)jsons.get("welcMsgCover")))
/*  94: 94 */        return getImageURL((String)jsons.get("welcMsgCover"));
/*  95:    */    } catch (Exception ex) {
/*  96: 96 */      ex.printStackTrace();
/*  97:    */    }
/*  98: 98 */    return "/resources/image/default/mix/wechat_welcome.jpg";
/*  99:    */  }
/* 100:    */  
/* 101:    */  public static String getImageURL(String imgurl) {
/* 102:102 */    if (imgurl.startsWith("/softac/")) {
/* 103:103 */      return imgurl.substring(7);
/* 104:    */    }
/* 105:105 */    return imgurl.substring(3);
/* 106:    */  }
/* 107:    */  
/* 108:    */  private String getRemoteIP(javax.servlet.http.HttpServletRequest request) {
/* 109:109 */    if (this.acs.getRealm().startsWith("qhwifi"))
/* 110:110 */      return request.getParameter("rip");
/* 111:111 */    return request.getRemoteAddr();
/* 112:    */  }
/* 113:    */  
/* 114:    */  @org.springframework.web.bind.annotation.RequestMapping({"/wifiant/auth.do"})
/* 115:    */  @ResponseBody
/* 116:116 */  public java.util.Map<String, Object> auth(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { java.util.Map<String, Object> result = new HashMap();
/* 117:    */    try {
/* 118:118 */      String stamac = request.getParameter("wlanusermac");
/* 119:119 */      String apmac = request.getParameter("apmac");
/* 120:120 */      String ip = request.getParameter("wlanuserip");
/* 121:121 */      String intf = request.getParameter("apintf");
/* 122:122 */      String flag = request.getParameter("flag");
/* 123:123 */      if (flag == null)
/* 124:124 */        flag = "thirdParty";
/* 125:125 */      com.soofound.cpe.bean.HostBean host = this.hs.findBySerialNumber(apmac);
/* 126:126 */      if (host == null) {
/* 127:127 */        result.put("status", Integer.valueOf(2));
/* 128:128 */        result.put("msg", "[" + apmac + "]AP不存在");
/* 129:129 */        return result;
/* 130:    */      }
/* 131:131 */      com.soofound.portal.bean.SurfingUser user = this.wds.getSurfingUser(host.getBranchId(), stamac);
/* 132:132 */      if (user == null) {
/* 133:133 */        user = new com.soofound.portal.bean.SurfingUser();
/* 134:134 */        user.setUsername(stamac.replace(":", ""));
/* 135:135 */        user.setBranchId(host.getBranchId());
/* 136:136 */        user.setCpeId(host.getId());
/* 137:137 */        user.setTerminalMac(stamac);
/* 138:138 */        user.setTerminalIP(ip);
/* 139:139 */        user.setSsid(host.getId() + "-" + intf);
/* 140:140 */        user.setFlag(flag);
/* 141:141 */        this.wds.recordOnline(user);
/* 142:    */        
/* 143:143 */        System.out.println("ADD_MAC#" + apmac + "," + stamac);
/* 144:    */        
/* 145:145 */        List<com.soofound.cpe.bean.HostPropertyBean> hpbs = new ArrayList();
/* 146:146 */        com.soofound.cpe.bean.HostPropertyBean hpb = new com.soofound.cpe.bean.HostPropertyBean();
/* 147:147 */        hpb.setName("InternetGatewayDevice.DeviceInfo.add_station_mac");
/* 148:148 */        hpb.setValue(stamac);
/* 149:149 */        hpbs.add(hpb);
/* 150:    */        
/* 151:151 */        CpeActionExecutor cae = new CpeActionExecutor();
/* 152:152 */        this.hs.putCommand(host.getId(), cae.getSetParameterValuesString(hpbs));
/* 153:153 */        wakeup(host.getStun().split("-"));
/* 154:    */        
/* 155:155 */        com.soofound.portal.dao.SurfingAccountDao dao = new com.soofound.portal.dao.SurfingAccountDao();
/* 156:156 */        com.soofound.portal.bean.SurfingAccount dto = dao.findByUsername(host.getBranchId(), user.getUsername());
/* 157:157 */        if (dto == null) {
/* 158:158 */          dto = new com.soofound.portal.bean.SurfingAccount();
/* 159:159 */          dto.setMac(stamac);
/* 160:160 */          dto.setTimes(1);
/* 161:161 */          dto.setOnline(1);
/* 162:162 */          dto.setUsername(user.getUsername());
/* 163:163 */          dto.setBranchId(user.getBranchId());
/* 164:164 */          dto.setPassword("-");
/* 165:165 */          dto.setFlag(flag);
/* 166:166 */          dao.save(dto);
/* 167:    */        }
/* 168:    */      }
/* 169:169 */      result.put("status", Integer.valueOf(1));
/* 170:170 */      result.put("msg", "ok");
/* 171:171 */      return result;
/* 172:    */    } catch (Exception ex) {
/* 173:173 */      result.put("status", Integer.valueOf(2));
/* 174:174 */      result.put("msg", ex.getMessage());
/* 175:175 */      ex.printStackTrace();
/* 176:    */    }
/* 177:177 */    return result;
/* 178:    */  }
/* 179:    */  
/* 180:    */  private boolean wakeup(String[] stun) {
/* 181:181 */    boolean result = false;
/* 182:    */    try {
/* 183:183 */      java.net.DatagramPacket packet = new java.net.DatagramPacket(CpeUtils.UDP_REQUEST, CpeUtils.UDP_REQUEST.length);
/* 184:184 */      packet.setAddress(InetAddress.getByName(stun[0]));
/* 185:185 */      packet.setPort(Integer.parseInt(stun[1]));
/* 186:    */      
/* 187:187 */      StunServerLauncher ssl = (StunServerLauncher)SysConfigHelper.getBean("stunServer");
/* 188:188 */      ssl.getStunServer().sendUDP(packet);
/* 189:    */      
/* 190:190 */      System.out.println("#wakeUpCpe:" + stun[0] + ",port=" + stun[1]);
/* 191:191 */      Thread.sleep(500L);
/* 192:192 */      result = true;
/* 193:    */    } catch (Exception ex) {
/* 194:194 */      ex.printStackTrace();
/* 195:195 */      System.out.println("#error stun:" + stun[0] + ":" + stun[1]);
/* 196:    */    }
/* 197:197 */    return result;
/* 198:    */  }
/* 199:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.web.EhulianAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */