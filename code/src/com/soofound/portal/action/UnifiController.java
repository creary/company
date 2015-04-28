/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.cpe.web.HostService;
/*   4:    */import com.soofound.cpe.web.UnifiHostService;
/*   5:    */import com.soofound.framework.util.CommonUtil;
/*   6:    */import com.soofound.portal.service.AdvertiseCategoryService;
/*   7:    */import com.soofound.portal.service.AdvertiseService;
/*   8:    */import com.soofound.portal.service.SurfingPolicyService;
/*   9:    */import com.soofound.portal.util.WifiDogUtils;
/*  10:    */import java.util.List;
/*  11:    */import org.springframework.stereotype.Controller;
/*  12:    */import org.springframework.util.Assert;
/*  13:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  14:    */
/*  15:    */@Controller
/*  16:    */public class UnifiController
/*  17:    */{
/*  18:    */  @org.springframework.beans.factory.annotation.Autowired
/*  19:    */  private com.soofound.portal.service.WifiDogService wds;
/*  20:    */  @org.springframework.beans.factory.annotation.Autowired
/*  21:    */  private AdvertiseService ads;
/*  22:    */  @org.springframework.beans.factory.annotation.Autowired
/*  23:    */  private AdvertiseCategoryService pcs;
/*  24:    */  @org.springframework.beans.factory.annotation.Autowired
/*  25:    */  private UnifiHostService uhs;
/*  26:    */  @org.springframework.beans.factory.annotation.Autowired
/*  27:    */  private com.soofound.portal.service.PortalTemplateService pts;
/*  28:    */  @org.springframework.beans.factory.annotation.Autowired
/*  29:    */  private com.soofound.portal.service.PortalInstanceService pis;
/*  30:    */  @org.springframework.beans.factory.annotation.Autowired
/*  31:    */  private SurfingPolicyService sps;
/*  32:    */  @org.springframework.beans.factory.annotation.Autowired
/*  33:    */  private HostService hostService;
/*  34:    */  
/*  35:    */  @RequestMapping({"/wifiant/unifiLogin.do"})
/*  36:    */  public String unifiLogin(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  37:    */  {
/*  38: 38 */    if (request.getQueryString() == null) {
/*  39: 39 */      model.addAttribute("error", "您接入了未授权的AP，为避免安全风险，请先退出。");
/*  40: 40 */      return "/common/hint.jsp";
/*  41:    */    }
/*  42: 42 */    String stamac = request.getParameter("id");
/*  43: 43 */    String site = request.getParameter("site");
/*  44: 44 */    com.soofound.cpe.bean.UnifiHostDto uhd = this.uhs.findBySite(site);
/*  45: 45 */    if (uhd == null) {
/*  46: 46 */      System.out.println(request.getRemoteAddr() + "--invalid Unifi AC--");
/*  47: 47 */      model.addAttribute("error", "无效的UnifiAC地址");
/*  48: 48 */      return "/common/hint.jsp";
/*  49:    */    }
/*  50: 50 */    com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostService.findByID(uhd.getHostId());
/*  51: 51 */    if (host == null) {
/*  52: 52 */      System.out.println(request.getRemoteAddr() + "--invalid Unifi AP--");
/*  53: 53 */      model.addAttribute("error", "您接入了未授权的AP，为避免安全风险，请先退出。");
/*  54: 54 */      return "/common/hint.jsp";
/*  55:    */    }
/*  56: 56 */    String ssid = uhd.getHostId() + "-0";
/*  57: 57 */    StringBuilder qstr = new StringBuilder(200);
/*  58: 58 */    qstr.append("apmac=").append(host.getSerialNumber()).append("&intf=0&stamac=").append(stamac).append("&unifi=").append(uhd.getAcip()).append("@");
/*  59: 59 */    qstr.append(uhd.getUsername()).append("@").append(uhd.getPassword()).append("@").append(uhd.getPort()).append("@v3@").append(site).append("@");
/*  60: 60 */    qstr.append(stamac).append("&ssid=").append(ssid).append("&branchId=").append(host.getBranchId()).append("&url=").append(request.getParameter("url"));
/*  61: 61 */    model.addAttribute("queryString", qstr.toString());
/*  62: 62 */    com.soofound.portal.bean.PortalInstanceDto portal = this.pis.getPortal(ssid);
/*  63: 63 */    if (portal == null) {
/*  64: 64 */      System.out.println(request.getRemoteAddr() + "##SSID Not Portal(unifi):" + ssid);
/*  65: 65 */      portal = com.soofound.portal.bean.PortalInstanceDto.bornDefaultPortal();
/*  66:    */    }
/*  67: 67 */    com.soofound.portal.bean.PortalTemplate tpl = this.pts.getPortalTemplate(portal.getTid());
/*  68: 68 */    if (tpl == null) {
/*  69: 69 */      System.out.println("PortalTemplate doesn't exist:" + portal.getTid() + "/" + portal.getId());
/*  70:    */    } else
/*  71: 71 */      model.addAllAttributes(tpl.getProps());
/*  72: 72 */    model.addAttribute("auth", Boolean.valueOf(true));
/*  73: 73 */    model.addAllAttributes(this.pts.getModuleValues());
/*  74: 74 */    model.addAllAttributes(portal.getPage("global"));
/*  75: 75 */    model.addAllAttributes(portal.getPage("welcome"));
/*  76: 76 */    return portal.getPath() + "view/welcome.jsp";
/*  77:    */  }
/*  78:    */  
/*  79:    */  @RequestMapping({"/wifiant/unifiPortal.do"})
/*  80:    */  public String unifiPortal(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  81: 81 */    System.out.println("unifiPortal#" + request.getQueryString());
/*  82: 82 */    com.soofound.portal.bean.SurfingPolicyDto spd = this.sps.getPolicy(request.getParameter("ssid"));
/*  83:    */    
/*  86: 86 */    if ((spd.getJumpTo() == 2) && (spd.getJumpUrl() != null)) {
/*  87: 87 */      return "redirect:" + spd.getJumpUrl();
/*  88:    */    }
/*  89:    */    
/*  92: 92 */    if ((spd.getJumpTo() == 3) && (!CommonUtil.isEmpty(request.getParameter("url"))))
/*  93: 93 */      return "redirect:" + request.getParameter("url");
/*  94: 94 */    com.soofound.portal.bean.PortalInstanceDto portal = this.pis.getPortal(request.getParameter("ssid"));
/*  95: 95 */    if (portal == null) {
/*  96: 96 */      portal = com.soofound.portal.bean.PortalInstanceDto.bornDefaultPortal();
/*  97:    */    }
/*  98: 98 */    String[] unifis = request.getParameter("unifi").split("@");
/*  99: 99 */    String branchId = request.getParameter("branchId");
/* 100:100 */    String stamac = unifis[6].toUpperCase();
/* 101:    */    
/* 102:102 */    com.soofound.portal.bean.SurfingUser user = this.wds.getSurfingUser(branchId, stamac);
/* 103:103 */    if (user != null) {
/* 104:104 */      StringBuilder unifiCmd = new StringBuilder(100);
/* 105:105 */      unifiCmd.append("/home/lanzl/unifi/unifi-wifiant -c ").append(unifis[0]).append(" -u ").append(unifis[1]);
/* 106:106 */      unifiCmd.append(" -p ").append(unifis[2]).append(" -P ").append(unifis[3]).append(" -v ").append(unifis[4]);
/* 107:107 */      unifiCmd.append(" -s ").append(unifis[5]).append(" -a ").append(unifis[6]);
/* 108:108 */      unifiCmd.append(" -U ");
/* 109:109 */      if (user.getUpSpeed() == 0L) {
/* 110:110 */        unifiCmd.append("102400");
/* 111:    */      } else
/* 112:112 */        unifiCmd.append(user.getUpSpeed());
/* 113:113 */      unifiCmd.append(" -d ");
/* 114:114 */      if (user.getDownSpeed() == 0L) {
/* 115:115 */        unifiCmd.append("102400");
/* 116:    */      } else
/* 117:117 */        unifiCmd.append(user.getDownSpeed());
/* 118:118 */      WifiDogUtils.doCommand(unifiCmd.toString());
/* 119:    */      
/* 120:120 */      user.setFlag("unifi");
/* 121:121 */      user.setTerminalIP("");
/* 122:122 */      user.setRoamUrl(unifiCmd.toString());
/* 123:123 */      System.out.println("Unifi User#" + user.getUsername() + " online");
/* 124:124 */      this.wds.recordOnline(user);
/* 125:    */    }
/* 126:126 */    model.addAttribute("pid", Integer.valueOf(portal.getId()));
/* 127:127 */    model.addAllAttributes(this.pts.getPortalTemplate(portal.getTid()).getProps());
/* 128:128 */    model.addAllAttributes(this.pts.getModuleValues());
/* 129:129 */    model.addAllAttributes(portal.getPage("global"));
/* 130:130 */    model.addAllAttributes(portal.getPage("authorized"));
/* 131:131 */    setCategoryAndArticles(request.getParameter("branchId"), request);
/* 132:132 */    return portal.getPath() + "/view/authorized.jsp";
/* 133:    */  }
/* 134:    */  
/* 135:    */  private void setCategoryAndArticles(String branchId, javax.servlet.http.HttpServletRequest request) {
/* 136:136 */    Assert.notNull(branchId, "setCategoryAndArticles#branchId must be not null");
/* 137:137 */    List<com.soofound.portal.bean.AdvertiseCategoryBean> cates = this.pcs.getBranchCategories(branchId);
/* 138:138 */    request.setAttribute("categories", cates);
/* 139:139 */    int intCid = 0;
/* 140:    */    try {
/* 141:141 */      intCid = Integer.parseInt(request.getParameter("cid"));
/* 142:    */    }
/* 143:    */    catch (Exception localException) {}
/* 144:144 */    request.setAttribute("cid", Integer.valueOf(intCid));
/* 145:145 */    request.setAttribute("branchId", branchId);
/* 146:146 */    if (!cates.isEmpty()) {
/* 147:147 */      for (com.soofound.portal.bean.AdvertiseCategoryBean cate : cates) {
/* 148:148 */        if (intCid == cate.getId()) {
/* 149:149 */          request.setAttribute("currentCategory", cate);
/* 150:150 */          break;
/* 151:    */        }
/* 152:    */      }
/* 153:    */    }
/* 154:154 */    request.setAttribute("articles", this.ads.getBranchAdvertises(branchId));
/* 155:    */  }
/* 156:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.UnifiController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */