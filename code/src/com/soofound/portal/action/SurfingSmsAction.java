/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.cpe.util.SoofacACS;
/*   4:    */import com.soofound.framework.util.SysConfigHelper;
/*   5:    */import com.soofound.framework.web.BaseAction;
/*   6:    */import com.soofound.portal.bean.AdvertiseDto;
/*   7:    */import com.soofound.portal.bean.PortalTemplate;
/*   8:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*   9:    */import com.soofound.portal.service.PortalInstanceService;
/*  10:    */import com.soofound.portal.service.PortalTemplateService;
/*  11:    */import com.soofound.portal.service.SurfingAccountService;
/*  12:    */import com.soofound.portal.service.SurfingPolicyService;
/*  13:    */import com.soofound.portal.util.WifiDogUtils;
/*  14:    */import java.util.HashMap;
/*  15:    */import org.springframework.stereotype.Controller;
/*  16:    */
/*  17:    */@Controller
/*  18:    */public final class SurfingSmsAction extends BaseAction<com.soofound.portal.service.SurfingSmsService>
/*  19:    */{
/*  20:    */  private static final String SHARE_HINT = "/msite/admin/tpl/soofound/sp/view/hint.jsp";
/*  21:    */  @org.springframework.beans.factory.annotation.Autowired
/*  22:    */  private SurfingPolicyService sps;
/*  23:    */  @org.springframework.beans.factory.annotation.Autowired
/*  24:    */  private PortalTemplateService pts;
/*  25:    */  @org.springframework.beans.factory.annotation.Autowired
/*  26:    */  private SoofacACS acs;
/*  27:    */  @org.springframework.beans.factory.annotation.Autowired
/*  28:    */  private com.soofound.portal.service.AdvertiseService asvc;
/*  29:    */  @org.springframework.beans.factory.annotation.Autowired
/*  30:    */  private PortalInstanceService pis;
/*  31:    */  @org.springframework.beans.factory.annotation.Autowired
/*  32:    */  private com.soofound.portal.dao.WechatShareDao wdao;
/*  33:    */  @org.springframework.beans.factory.annotation.Autowired
/*  34:    */  private SurfingAccountService sas;
/*  35:    */  
/*  36:    */  @org.springframework.web.bind.annotation.RequestMapping({"/portal/smsListJsp.do"})
/*  37:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  38:    */  {
/*  39: 39 */    return "/portal/sms/smsList.jsp";
/*  40:    */  }
/*  41:    */  
/*  42:    */  @org.springframework.web.bind.annotation.RequestMapping({"/portal/smsList.do"})
/*  43:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  44: 44 */  public java.util.Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.listByPage(request, model); }
/*  45:    */  
/*  46:    */  @org.springframework.web.bind.annotation.RequestMapping({"/portal/smsDelete.do"})
/*  47:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  48:    */  public java.util.Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  49: 49 */    return super.deleteByIDs(request, model);
/*  50:    */  }
/*  51:    */  
/*  52:    */  @org.springframework.web.bind.annotation.RequestMapping({"/portal/smsSummaryListJsp.do"})
/*  53:    */  public String summaryListJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  54: 54 */    String branchId = request.getParameter("branchId");
/*  55: 55 */    model.addAttribute("dtos", ((com.soofound.portal.service.SurfingSmsService)this.service).getSurfingSmsStats(branchId));
/*  56: 56 */    return "/portal/sms/list.jsp";
/*  57:    */  }
/*  58:    */  
/*  60:    */  @org.springframework.web.bind.annotation.RequestMapping({"/portal/readySendSms.do"})
/*  61:    */  public String readySendSms(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  62:    */  {
/*  63: 63 */    String branchId = request.getParameter("branchId");
/*  64: 64 */    model.addAttribute("accounts", this.sas.getSurfingAccountsByType(branchId, "mobile"));
/*  65: 65 */    return "/portal/sms/smsSend.jsp";
/*  66:    */  }
/*  67:    */  
/*  68:    */  @org.springframework.web.bind.annotation.RequestMapping({"/portal/getShares.do"})
/*  69:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  70: 70 */  public java.util.Map<String, Object> getShares(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String pid = request.getParameter("pid");
/*  71: 71 */    return bornData(this.wdao.getShareByPortal(pid));
/*  72:    */  }
/*  73:    */  
/*  74:    */  @org.springframework.web.bind.annotation.RequestMapping({"/portal/addShare.do"})
/*  75:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  76:    */  public java.util.Map<String, Object> addShare(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  77: 77 */    try { String pid = request.getParameter("portalId");
/*  78: 78 */      String aid = request.getParameter("radio");
/*  79: 79 */      this.wdao.addShareArticle(pid, aid);
/*  80: 80 */      return getSucceedResult("增加成功");
/*  81:    */    } catch (Exception e) {
/*  82: 82 */      e.printStackTrace(); }
/*  83: 83 */    return getFailedResult("failed");
/*  84:    */  }
/*  85:    */  
/*  86:    */  @org.springframework.web.bind.annotation.RequestMapping({"/portal/readyChooseShare.do"})
/*  87:    */  public String readyChooseShare(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  88:    */  {
/*  89: 89 */    String pid = request.getParameter("pid");
/*  90: 90 */    model.addAttribute("portal", this.pis.findByID(pid));
/*  91: 91 */    return "/portal/advertise/choosePortalShare.jsp";
/*  92:    */  }
/*  93:    */  
/*  94:    */  @org.springframework.web.bind.annotation.RequestMapping({"/portal/deleteShare.do"})
/*  95:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  96: 96 */  public java.util.Map<String, Object> deleteShare(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String id = request.getParameter("id");
/*  97: 97 */    this.wdao.delete(id);
/*  98: 98 */    return getSucceedResult("删除成功");
/*  99:    */  }
/* 100:    */  
/* 101:    */  @org.springframework.web.bind.annotation.RequestMapping({"/wifiant/readyShare.do"})
/* 102:    */  public String readyShare(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 103:103 */    boolean preview = request.getParameter("preview") != null;
/* 104:    */    try {
/* 105:105 */      String sid = request.getParameter("sid");
/* 106:106 */      com.soofound.portal.bean.WechatShareDto wsd = (com.soofound.portal.bean.WechatShareDto)this.wdao.findByID(sid);
/* 107:107 */      AdvertiseDto ad = (AdvertiseDto)this.asvc.findByID(wsd.getShareId());
/* 108:108 */      if (ad == null) {
/* 109:109 */        model.addAttribute("hint", "抱歉，该文章因违反规则已被屏蔽。");
/* 110:110 */        return "/msite/admin/tpl/soofound/sp/view/hint.jsp";
/* 111:    */      }
/* 112:112 */      model.addAttribute("share", ad);
/* 113:113 */      if (!preview) {
/* 114:114 */        String encodeMac = request.getParameter("sta");
/* 115:115 */        String mac = WifiDogUtils.decodeMac(encodeMac);
/* 116:116 */        String ssid = request.getParameter("ssid");
/* 117:117 */        model.addAttribute("sid", sid);
/* 118:118 */        model.addAttribute("sta", encodeMac);
/* 119:119 */        model.addAttribute("ssid", ssid);
/* 120:120 */        if (this.wdao.hasShared(sid, mac)) {
/* 121:121 */          model.addAttribute("viewMode", "view");
/* 122:    */        } else {
/* 123:123 */          model.addAttribute("viewMode", "share");
/* 124:124 */          com.soofound.portal.bean.ShareMac bean = new com.soofound.portal.bean.ShareMac();
/* 125:125 */          bean.setId(sid);
/* 126:126 */          bean.setMac(mac);
/* 127:127 */          this.asvc.setShareMac(bean);
/* 128:    */        }
/* 129:129 */        SurfingPolicyDto spd = this.sps.getPolicy(ssid);
/* 130:130 */        if ((spd != null) && (spd.getAuth() == 1)) {
/* 131:131 */          model.addAttribute("authString", "wifiant/signin/view.do?" + request.getQueryString());
/* 132:    */        } else
/* 133:133 */          model.addAttribute("authString", "wifiant/freeAuth.do?" + request.getQueryString());
/* 134:    */      }
/* 135:135 */      com.soofound.portal.bean.PortalInstanceDto pid = this.pis.getPortalByID(wsd.getPortalId());
/* 136:136 */      model.addAttribute("currentTplContext", SysConfigHelper.CONTEXT_PATH + pid.getPath());
/* 137:137 */      model.addAttribute("queryString", request.getQueryString());
/* 138:138 */      model.addAttribute("cover", this.acs.getAcsURL().replace(SysConfigHelper.CONTEXT_PATH, "") + ad.getCover());
/* 139:139 */      model.addAllAttributes(this.pts.getPortalTemplate(pid.getTid()).getProps());
/* 140:140 */      model.addAllAttributes(this.pts.getModuleValues());
/* 141:141 */      model.addAllAttributes(pid.getPage("global"));
/* 142:142 */      if (preview)
/* 143:143 */        return "/msite/action/shareReader.jsp";
/* 144:144 */      return pid.getPath() + "/view/share.jsp";
/* 145:    */    } catch (Exception ex) {
/* 146:146 */      model.addAttribute("hint", ex.getMessage()); }
/* 147:147 */    return "/msite/admin/tpl/soofound/sp/view/hint.jsp";
/* 148:    */  }
/* 149:    */  
/* 150:    */  @org.springframework.web.bind.annotation.RequestMapping({"/wifiant/checkShared.do"})
/* 151:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 152:    */  public java.util.Map<String, Object> checkShared(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 153:153 */    String encodeMac = request.getParameter("sta");
/* 154:154 */    boolean shared = this.asvc.isSharedMac(WifiDogUtils.decodeMac(encodeMac));
/* 155:155 */    java.util.Map<String, Object> results = new HashMap();
/* 156:156 */    java.util.Map<String, Object> data = new HashMap();
/* 157:157 */    results.put("status", Integer.valueOf(1));
/* 158:158 */    results.put("data", data);
/* 159:159 */    if (shared) {
/* 160:160 */      data.put("shared", Boolean.valueOf(true));
/* 161:161 */      data.put("url", SysConfigHelper.CONTEXT_PATH + "/wifiant/authorized/view.do?ssid=" + request.getParameter("ssid"));
/* 162:    */    } else {
/* 163:163 */      data.put("shared", Boolean.valueOf(false));
/* 164:    */    }
/* 165:165 */    return results;
/* 166:    */  }
/* 167:    */  
/* 181:    */  @org.springframework.beans.factory.annotation.Autowired
/* 182:    */  protected void setService(com.soofound.portal.service.SurfingSmsService service)
/* 183:    */  {
/* 184:184 */    this.service = service;
/* 185:    */  }
/* 186:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.SurfingSmsAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */