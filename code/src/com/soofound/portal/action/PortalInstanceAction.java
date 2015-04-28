/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.alibaba.fastjson.JSONObject;
/*   4:    */import com.soofound.admin.bean.UserDto;
/*   5:    */import com.soofound.admin.web.BranchService;
/*   6:    */import com.soofound.framework.web.BaseAction;
/*   7:    */import com.soofound.portal.bean.AdvertiseCategoryBean;
/*   8:    */import com.soofound.portal.bean.MsiteJsonBean;
/*   9:    */import com.soofound.portal.bean.PortalTemplate;
/*  10:    */import com.soofound.portal.dao.PortalInstanceDao;
/*  11:    */import com.soofound.portal.service.AdvertiseCategoryService;
/*  12:    */import com.soofound.portal.service.AdvertiseService;
/*  13:    */import com.soofound.portal.service.PortalTemplateService;
/*  14:    */import com.soofound.portal.util.WifiDogUtils;
/*  15:    */import java.io.PrintStream;
/*  16:    */import java.util.ArrayList;
/*  17:    */import java.util.Enumeration;
/*  18:    */import java.util.HashMap;
/*  19:    */import java.util.List;
/*  20:    */import java.util.Map;
/*  21:    */import org.springframework.beans.factory.annotation.Autowired;
/*  22:    */import org.springframework.stereotype.Controller;
/*  23:    */import org.springframework.web.bind.annotation.PathVariable;
/*  24:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  25:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  26:    */
/*  27:    */@Controller
/*  28:    */public class PortalInstanceAction extends BaseAction<com.soofound.portal.service.PortalInstanceService>
/*  29:    */{
/*  30:    */  private final Map<String, String> editJsps;
/*  31:    */  @Autowired
/*  32:    */  private AdvertiseService ads;
/*  33:    */  @Autowired
/*  34:    */  private AdvertiseCategoryService pcs;
/*  35:    */  @Autowired
/*  36:    */  private BranchService branchService;
/*  37:    */  @Autowired
/*  38:    */  private PortalTemplateService tplService;
/*  39:    */  
/*  40:    */  public PortalInstanceAction()
/*  41:    */  {
/*  42: 42 */    this.editJsps = new HashMap();
/*  43:    */  }
/*  44:    */  
/*  45:    */  @RequestMapping({"/portal/instanceListJsp.do"})
/*  46:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  47: 47 */    return "/portal/apface/instanceList.jsp";
/*  48:    */  }
/*  49:    */  
/*  50:    */  @RequestMapping({"/portal/instanceList.do"})
/*  51:    */  @ResponseBody
/*  52: 52 */  public Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.listByPage(request, model); }
/*  53:    */  
/*  54:    */  @RequestMapping({"/portal/instanceReadyAdd.do"})
/*  55:    */  public String readyAdd(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  56:    */  {
/*  57: 57 */    model.addAttribute("templates", this.tplService.getPortalTemplates());
/*  58: 58 */    UserDto user = getCurrentUser(request);
/*  59: 59 */    com.soofound.portal.bean.PortalInstanceDto dto = new com.soofound.portal.bean.PortalInstanceDto();
/*  60: 60 */    dto.setBranchId(user.getBranchId());
/*  61: 61 */    model.addAttribute("dto", dto);
/*  62: 62 */    model.addAttribute("title", "增加");
/*  63: 63 */    model.addAttribute("image", "add.gif");
/*  64: 64 */    model.addAttribute("action", "Save");
/*  65: 65 */    return "/portal/apface/instanceEdit.jsp";
/*  66:    */  }
/*  67:    */  
/*  68:    */  @RequestMapping({"/portal/instanceReadyEdit.do"})
/*  69:    */  public String readyEdit(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  70: 70 */    String id = request.getParameter("id");
/*  71: 71 */    model.addAttribute("dto", ((com.soofound.portal.service.PortalInstanceService)this.service).findByID(id));
/*  72: 72 */    model.addAttribute("title", "编辑");
/*  73: 73 */    model.addAttribute("image", "edit.gif");
/*  74: 74 */    model.addAttribute("action", "Update");
/*  75: 75 */    return "/portal/apface/instanceEdit.jsp";
/*  76:    */  }
/*  77:    */  
/*  78:    */  @RequestMapping({"/portal/instanceSave.do"})
/*  79:    */  @ResponseBody
/*  80: 80 */  public Map<String, Object> save(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String name = request.getParameter("name");
/*  81: 81 */    UserDto user = super.getCurrentUser(request);
/*  82: 82 */    if (((com.soofound.portal.service.PortalInstanceService)this.service).getPortalByName(user.getBranchId(), name) != null) {
/*  83: 83 */      return super.getFailedResult("保存失败,[" + name + "]已经存在.");
/*  84:    */    }
/*  85:    */    try {
/*  86: 86 */      com.soofound.portal.bean.PortalInstanceDto dto = new com.soofound.portal.bean.PortalInstanceDto();
/*  87: 87 */      dto.setName(name);
/*  88: 88 */      dto.setBranchId(user.getBranchId());
/*  89: 89 */      dto.setTid(request.getParameter("tid"));
/*  90: 90 */      dto.setTag("1".equals(request.getParameter("tag")) ? 1 : 0);
/*  91: 91 */      ((com.soofound.portal.service.PortalInstanceService)this.service).save(dto);
/*  92:    */      
/*  93: 93 */      Map<String, Object> result = super.getSucceedResult("保存成功");
/*  94: 94 */      result.put("data", dto.getId());
/*  95: 95 */      return result;
/*  96:    */    } catch (Exception ex) {
/*  97: 97 */      ex.printStackTrace(); }
/*  98: 98 */    return super.getFailedResult("保存失败");
/*  99:    */  }
/* 100:    */  
/* 101:    */  @RequestMapping({"/portal/instanceUpdate.do"})
/* 102:    */  @ResponseBody
/* 103:    */  public Map<String, Object> update(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 104:104 */    String name = request.getParameter("name");
/* 105:105 */    UserDto user = super.getCurrentUser(request);
/* 106:106 */    com.soofound.portal.bean.PortalInstanceDto dto = ((com.soofound.portal.service.PortalInstanceService)this.service).getPortalByName(user.getBranchId(), name);
/* 107:107 */    int id = Integer.parseInt(request.getParameter("id"));
/* 108:108 */    if ((dto != null) && (dto.getId() != id)) {
/* 109:109 */      return super.getFailedResult("保存失败,[" + name + "]已经存在.");
/* 110:    */    }
/* 111:111 */    return super.update(request, model);
/* 112:    */  }
/* 113:    */  
/* 114:    */  @RequestMapping({"/portal/instanceDelete.do"})
/* 115:    */  @ResponseBody
/* 116:116 */  public Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.deleteByIDs(request, model); }
/* 117:    */  
/* 118:    */  @RequestMapping({"/portal/instanceCopy.do"})
/* 119:    */  @ResponseBody
/* 120:    */  public Map<String, Object> copy(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 121:121 */    String[] ids = request.getParameterValues("checkbox");
/* 122:122 */    PortalInstanceDao dao = new PortalInstanceDao();
/* 123:123 */    dao.copyPortal(ids[0]);
/* 124:124 */    return getSucceedResult("复制成功");
/* 125:    */  }
/* 126:    */  
/* 127:    */  @RequestMapping({"/portal/{pid}/{page}/edit.do"})
/* 128:    */  public String protalEdit(@PathVariable String pid, @PathVariable String page, org.springframework.ui.ModelMap model, javax.servlet.http.HttpServletRequest request) {
/* 129:129 */    UserDto user = super.getCurrentUser(request);
/* 130:    */    try {
/* 131:131 */      com.soofound.portal.bean.PortalInstanceDto pd = (com.soofound.portal.bean.PortalInstanceDto)((com.soofound.portal.service.PortalInstanceService)this.service).findByID(pid);
/* 132:132 */      if (pd == null) {
/* 133:133 */        model.addAttribute("message", "Portal不存在");
/* 134:134 */        System.out.println(pid + "==Portal不存在==");
/* 135:135 */        return "/common/hint.jsp";
/* 136:    */      }
/* 137:137 */      model.addAllAttributes(this.tplService.getPortalTemplate(pd.getTid()).getProps());
/* 138:138 */      model.addAttribute("pts", this.tplService.getPortalTemplate(pd.getTid()).getPages());
/* 139:139 */      model.addAllAttributes(this.tplService.getModuleValues());
/* 140:140 */      if (!"share".equals(page))
/* 141:141 */        model.addAllAttributes(((com.soofound.portal.service.PortalInstanceService)this.service).getPortalPage(pid, page));
/* 142:142 */      model.addAttribute("portal", pd);
/* 143:143 */      model.addAttribute("pid", pid);
/* 144:144 */      List<AdvertiseCategoryBean> pcbs = this.pcs.getBranchCategories(user.getBranchId());
/* 145:145 */      model.addAttribute("categories", pcbs);
/* 146:146 */      String jsp = null;
/* 147:147 */      if (this.editJsps.containsKey(page)) {
/* 148:148 */        jsp = (String)this.editJsps.get(page);
/* 149:    */      } else {
/* 150:150 */        jsp = "/msite/admin/global/edit/" + page.toLowerCase() + "_edit.jsp";
/* 151:151 */        this.editJsps.put(page, jsp);
/* 152:    */      }
/* 153:153 */      return jsp;
/* 154:    */    } catch (Exception ex) {
/* 155:155 */      ex.printStackTrace(); }
/* 156:156 */    return "/common/hint.jsp";
/* 157:    */  }
/* 158:    */  
/* 159:    */  @RequestMapping({"/portal/readyPreview.do"})
/* 160:    */  public String readyPreview(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 161:    */  {
/* 162:162 */    String id = request.getParameter("id");
/* 163:163 */    com.soofound.portal.bean.PortalInstanceDto portal = (com.soofound.portal.bean.PortalInstanceDto)((com.soofound.portal.service.PortalInstanceService)this.service).findByID(id);
/* 164:164 */    model.addAttribute("id", id);
/* 165:165 */    model.addAttribute("pts", this.tplService.getPortalTemplate(portal.getTid()).getPages());
/* 166:166 */    model.addAttribute("preview", Boolean.valueOf(true));
/* 167:167 */    return "/msite/action/preview.jsp";
/* 168:    */  }
/* 169:    */  
/* 170:    */  @RequestMapping({"/portal/{pid}/{page}/preview.do"})
/* 171:    */  public String preview(@PathVariable String pid, @PathVariable String page, javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 172:    */  {
/* 173:173 */    UserDto user = super.getCurrentUser(request);
/* 174:174 */    model.addAttribute("preview", Boolean.valueOf(true));
/* 175:175 */    model.addAttribute("auth", Boolean.valueOf(true));
/* 176:176 */    model.addAttribute("pwdAuth", Boolean.valueOf(true));
/* 177:177 */    model.addAttribute("smsAuth", Boolean.valueOf(true));
/* 178:178 */    model.addAttribute("wechatAuth", Boolean.valueOf(true));
/* 179:179 */    com.soofound.portal.bean.PortalInstanceDto portal = ((com.soofound.portal.service.PortalInstanceService)this.service).getPortalByID(pid);
/* 180:180 */    model.addAllAttributes(this.tplService.getPortalTemplate(portal.getTid()).getProps());
/* 181:181 */    model.addAllAttributes(this.tplService.getModuleValues());
/* 182:182 */    model.addAttribute("pts", this.tplService.getPortalTemplate(portal.getTid()).getPages());
/* 183:183 */    model.addAllAttributes(portal.getPage(page));
/* 184:184 */    model.addAllAttributes(portal.getPage("global"));
/* 185:185 */    model.addAttribute("branch", this.branchService.findByID(user.getBranchId()));
/* 186:186 */    model.addAttribute("branchId", user.getBranchId());
/* 187:187 */    model.addAttribute("pid", Integer.valueOf(portal.getId()));
/* 188:188 */    setCategoryAndArticles(user.getBranchId(), request);
/* 189:189 */    return portal.getPath() + "view/" + page + ".jsp";
/* 190:    */  }
/* 191:    */  
/* 192:    */  @RequestMapping({"/portal/{pid}/{page}/save.do"})
/* 193:    */  @ResponseBody
/* 194:    */  public Map<String, Object> portalSave(@PathVariable String pid, @PathVariable String page, javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 195:    */    try {
/* 196:196 */      List<MsiteJsonBean> pageValues = new ArrayList();
/* 197:197 */      for (Enumeration em = request.getParameterNames(); em.hasMoreElements();) {
/* 198:198 */        String para = (String)em.nextElement();
/* 199:199 */        String value = request.getParameter(para);
/* 200:200 */        if ((!"random".equals(para)) && (value != null))
/* 201:    */        {
/* 202:202 */          MsiteJsonBean mb = new MsiteJsonBean(para, value);
/* 203:203 */          pageValues.add(mb);
/* 204:    */        } }
/* 205:205 */      if (!pageValues.isEmpty()) {
/* 206:206 */        Map<String, List<MsiteJsonBean>> result = new HashMap();
/* 207:207 */        result.put("fields", pageValues);
/* 208:208 */        int row = ((com.soofound.portal.service.PortalInstanceService)this.service).updatePortalPage(pid, page, JSONObject.toJSONString(result));
/* 209:209 */        if (row == 0)
/* 210:210 */          ((com.soofound.portal.service.PortalInstanceService)this.service).insertPortalPage(pid, page, JSONObject.toJSONString(result));
/* 211:    */      }
/* 212:212 */      logOperation(request, "更新并保存PORTAL");
/* 213:213 */      return super.getSucceedResult("保存成功.");
/* 214:    */    } catch (Exception ex) {
/* 215:215 */      ex.printStackTrace(); }
/* 216:216 */    return super.getFailedResult("保存失败.");
/* 217:    */  }
/* 218:    */  
/* 219:    */  private void setCategoryAndArticles(String branchId, javax.servlet.http.HttpServletRequest request)
/* 220:    */  {
/* 221:221 */    List<AdvertiseCategoryBean> cates = this.pcs.getBranchCategories(branchId);
/* 222:222 */    request.setAttribute("categories", cates);
/* 223:223 */    int intCid = 0;
/* 224:    */    try {
/* 225:225 */      intCid = Integer.parseInt(request.getParameter("cid"));
/* 226:    */    }
/* 227:    */    catch (Exception localException) {}
/* 228:228 */    request.setAttribute("cid", Integer.valueOf(intCid));
/* 229:229 */    request.setAttribute("branchId", branchId);
/* 230:230 */    if (!cates.isEmpty()) {
/* 231:231 */      for (AdvertiseCategoryBean cate : cates) {
/* 232:232 */        if (intCid == cate.getId()) {
/* 233:233 */          request.setAttribute("currentCategory", cate);
/* 234:234 */          break;
/* 235:    */        }
/* 236:    */      }
/* 237:    */    }
/* 238:238 */    request.setAttribute("articles", this.ads.getBranchAdvertises(branchId));
/* 239:    */  }
/* 240:    */  
/* 241:    */  @RequestMapping({"/acs/decodeURL.do"})
/* 242:    */  @ResponseBody
/* 243:243 */  public Map<String, String> decodeURL(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return WifiDogUtils.decodeURL(request.getParameter("url")); }
/* 244:    */  
/* 253:    */  @Autowired
/* 254:    */  protected void setService(com.soofound.portal.service.PortalInstanceService service)
/* 255:    */  {
/* 256:256 */    this.service = service;
/* 257:    */  }
/* 258:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.PortalInstanceAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */