/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.BranchDto;
/*   4:    */import com.soofound.admin.bean.GroupDao;
/*   5:    */import com.soofound.admin.bean.GroupDto;
/*   6:    */import com.soofound.admin.bean.GroupMembershipBean;
/*   7:    */import com.soofound.admin.bean.UserDto;
/*   8:    */import com.soofound.admin.web.BranchService;
/*   9:    */import com.soofound.cpe.dao.RichHostDao;
/*  10:    */import com.soofound.framework.web.BaseAction;
/*  11:    */import com.soofound.project.wifibeijing.WifiBeijingDeviceTree;
/*  12:    */import javax.servlet.http.HttpSession;
/*  13:    */import org.springframework.stereotype.Controller;
/*  14:    */
/*  15:    */@Controller
/*  16:    */public final class RichHostAction extends BaseAction<HostService>
/*  17:    */{
/*  18:    */  @org.springframework.beans.factory.annotation.Autowired
/*  19:    */  private GroupDao gdao;
/*  20:    */  @org.springframework.beans.factory.annotation.Autowired
/*  21:    */  private RichHostDao rhdao;
/*  22:    */  @org.springframework.beans.factory.annotation.Autowired
/*  23:    */  private HostService hs;
/*  24:    */  @org.springframework.beans.factory.annotation.Autowired
/*  25:    */  private BranchService bs;
/*  26:    */  
/*  27:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/getTreeForOffline.do"})
/*  28:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  29:    */  public java.util.Map<String, Object> getGroupTreeForOffline(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  30:    */  {
/*  31: 31 */    WifiBeijingDeviceTree tree = new WifiBeijingDeviceTree();
/*  32: 32 */    return bornData(tree.getOfflineDeviceTree());
/*  33:    */  }
/*  34:    */  
/*  35:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/richHostListJsp.do"})
/*  36:    */  public String richhostListJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  37: 37 */    UserDto user = super.getCurrentUser(request);
/*  38: 38 */    if ("0".equals(user.getBranchId())) {
/*  39: 39 */      model.addAttribute("adminable", Boolean.valueOf(true));
/*  40:    */    } else
/*  41: 41 */      model.addAttribute("adminable", Boolean.valueOf(false));
/*  42: 42 */    if ("2".equals(request.getParameter("tag")))
/*  43: 43 */      return "/operation/host/richHostSimpleList.jsp";
/*  44: 44 */    return "/operation/host/richHostList.jsp";
/*  45:    */  }
/*  46:    */  
/*  47:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readySelectDeviceGroup.do"})
/*  48:    */  public String readySelectDeviceGroup(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  49: 49 */    String bid = request.getParameter("id");
/*  50: 50 */    BranchDto bd = (BranchDto)this.bs.findByID(bid);
/*  51: 51 */    model.addAttribute("branch", bd);
/*  52: 52 */    GroupMembershipBean dto = this.gdao.getGroupMemberByBranchId(bid);
/*  53: 53 */    if (dto == null) {
/*  54: 54 */      model.addAttribute("groupId", "0");
/*  55: 55 */      model.addAttribute("groupName", "");
/*  56:    */    } else {
/*  57: 57 */      model.addAttribute("groupId", dto.getGroupId());
/*  58: 58 */      model.addAttribute("groupName", dto.getGroupName());
/*  59:    */    }
/*  60: 60 */    return "/admin/group/deviceGroup.jsp";
/*  61:    */  }
/*  62:    */  
/*  63:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/richHostList.do"})
/*  64:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  65:    */  public java.util.Map<String, Object> richList(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  66: 66 */    try { request.getSession().setAttribute("gid", request.getParameter("gid"));
/*  67: 67 */      int pp = getPerPageRowTotal(request);
/*  68: 68 */      int cp = getCurrentPage(request);
/*  69: 69 */      return ((HostService)this.service).getRichBeans(pp, cp, getParas(request));
/*  70:    */    } catch (Exception ex) {
/*  71: 71 */      ex.printStackTrace(); }
/*  72: 72 */    return null;
/*  73:    */  }
/*  74:    */  
/*  75:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/richHostReadyEdit.do"})
/*  76:    */  public String richHostReadyEdit(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  77:    */  {
/*  78: 78 */    String id = request.getParameter("id");
/*  79: 79 */    model.addAttribute("host", this.hs.findByID(id));
/*  80: 80 */    return "/operation/host/richHostEdit.jsp";
/*  81:    */  }
/*  82:    */  
/*  83:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/richHostUpdate.do"})
/*  84:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  85:    */  public java.util.Map<String, Object> update(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  86: 86 */    try { com.soofound.cpe.bean.RichHostBean host = new com.soofound.cpe.bean.RichHostBean();
/*  87: 87 */      host.setId(Integer.parseInt(request.getParameter("id")));
/*  88: 88 */      host.setUsed(Integer.parseInt(request.getParameter("used")));
/*  89: 89 */      host.setName(request.getParameter("name"));
/*  90: 90 */      host.setCode(request.getParameter("code"));
/*  91: 91 */      host.setLocation(request.getParameter("location"));
/*  92: 92 */      this.rhdao.update(host);
/*  93: 93 */      return getSucceedResult("更新成功");
/*  94:    */    } catch (Exception ex) {
/*  95: 95 */      ex.printStackTrace(); }
/*  96: 96 */    return getFailedResult("更新失败");
/*  97:    */  }
/*  98:    */  
/*  99:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/deviceGroupOperation.do"})
/* 100:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 101:    */  public java.util.Map<String, Object> deviceGroupOperation(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 102:102 */    return getFailedResult("对不起，此处不支持对树形进行修改");
/* 103:    */  }
/* 104:    */  
/* 105:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/readyGetDeviceLayout.do"})
/* 106:    */  public String readyGetDeviceLayout(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 107:107 */    String gid = (String)request.getSession().getAttribute("gid");
/* 108:108 */    String img = this.rhdao.getLayoutImage(gid);
/* 109:109 */    GroupDto dto = this.gdao.findByID(gid);
/* 110:110 */    model.addAttribute("dto", dto);
/* 111:111 */    model.addAttribute("img", img);
/* 112:112 */    return "/operation/host/deviceLayout.jsp";
/* 113:    */  }
/* 114:    */  
/* 115:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/saveDeviceLayout.do"})
/* 116:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 117:    */  public java.util.Map<String, Object> saveDeviceLayout(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 118:118 */    try { String gid = request.getParameter("gid");
/* 119:119 */      String cover = request.getParameter("cover");
/* 120:120 */      this.rhdao.save(gid, cover);
/* 121:121 */      return getSucceedResult("更新成功");
/* 122:    */    } catch (Exception ex) {
/* 123:123 */      ex.printStackTrace(); }
/* 124:124 */    return getFailedResult("更新失败");
/* 125:    */  }
/* 126:    */  
/* 127:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/saveBranchDeviceGroup.do"})
/* 128:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 129:    */  public java.util.Map<String, Object> saveBranchDeviceGroup(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 130:    */    try {
/* 131:131 */      String branchId = request.getParameter("branchId");
/* 132:132 */      String gid = request.getParameter("gid");
/* 133:133 */      this.gdao.saveBranchDeviceGroup(branchId, gid);
/* 134:134 */      return getSucceedResult("更新成功");
/* 135:    */    } catch (Exception ex) {
/* 136:136 */      ex.printStackTrace(); }
/* 137:137 */    return getFailedResult("更新失败");
/* 138:    */  }
/* 139:    */  
/* 140:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/removeBranchDeviceGroup.do"})
/* 141:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 142:    */  public java.util.Map<String, Object> removeBranchDeviceGroup(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 143:    */    try {
/* 144:144 */      String branchId = request.getParameter("branchId");
/* 145:145 */      this.gdao.removeBranchDeviceGroup(branchId);
/* 146:146 */      return getSucceedResult("更新成功");
/* 147:    */    } catch (Exception ex) {
/* 148:148 */      ex.printStackTrace(); }
/* 149:149 */    return getFailedResult("更新失败");
/* 150:    */  }
/* 151:    */  
/* 160:    */  @org.springframework.beans.factory.annotation.Autowired
/* 161:    */  protected void setService(HostService service)
/* 162:    */  {
/* 163:163 */    this.service = service;
/* 164:    */  }
/* 165:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.RichHostAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */