/*   1:    */package com.soofound.admin.web;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.GroupDao;
/*   4:    */import com.soofound.framework.util.SysConfigHelper;
/*   5:    */import com.soofound.portal.bean.PortalInstanceDto;
/*   6:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*   7:    */import com.soofound.portal.service.CountyService;
/*   8:    */import com.soofound.portal.service.PortalInstanceService;
/*   9:    */import com.soofound.portal.service.SurfingPolicyService;
/*  10:    */import java.util.HashMap;
/*  11:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  12:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  13:    */import org.springframework.stereotype.Controller;
/*  14:    */
/*  15:    */@Controller
/*  16:    */public class BranchAction extends com.soofound.framework.web.BaseAction<BranchService>
/*  17:    */{
/*  18:    */  @org.springframework.beans.factory.annotation.Autowired
/*  19:    */  private SurfingPolicyService sps;
/*  20:    */  @org.springframework.beans.factory.annotation.Autowired
/*  21:    */  private PortalInstanceService pis;
/*  22:    */  @org.springframework.beans.factory.annotation.Autowired
/*  23:    */  private com.soofound.cpe.util.SoofacACS acs;
/*  24:    */  @org.springframework.beans.factory.annotation.Autowired
/*  25:    */  private com.soofound.portal.service.CityService cityService;
/*  26:    */  @org.springframework.beans.factory.annotation.Autowired
/*  27:    */  private CountyService countyService;
/*  28:    */  @org.springframework.beans.factory.annotation.Autowired
/*  29:    */  @Qualifier("defaultJdbcTemplate")
/*  30:    */  private JdbcTemplate jdbcTemplate;
/*  31:    */  
/*  32:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/branchListJsp.do"})
/*  33:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  34:    */  {
/*  35: 35 */    com.soofound.admin.bean.UserDto user = getCurrentUser(request);
/*  36: 36 */    int rich = 0;
/*  37: 37 */    if (("1".equals(request.getParameter("rich"))) && ("0".equals(user.getBranchId())))
/*  38: 38 */      rich = 1;
/*  39: 39 */    model.addAttribute("rich", Integer.valueOf(rich));
/*  40: 40 */    return "/admin/branch/list.jsp";
/*  41:    */  }
/*  42:    */  
/*  43:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/branchList.do"})
/*  44:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  45: 45 */  public java.util.Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { java.util.Map<String, Object> results = new HashMap();
/*  46: 46 */    int pp = getPerPageRowTotal(request);
/*  47: 47 */    int cp = getCurrentPage(request);
/*  48: 48 */    java.util.List<com.soofound.admin.bean.BranchDto> dtos = ((BranchService)this.service).listByPage(pp, cp, getParas(request));
/*  49: 49 */    com.soofound.cpe.util.SoofacACS acs = (com.soofound.cpe.util.SoofacACS)SysConfigHelper.getBean("soofacACS");
/*  50: 50 */    if ((acs.getRealm().startsWith("wifiBeijing")) || (acs.getRealm().startsWith("qhwifi"))) {
/*  51: 51 */      GroupDao dao = new GroupDao();
/*  52: 52 */      java.util.List<com.soofound.admin.bean.GroupMembershipBean> gmbs = dao.getBranchGroupMembers();
/*  53: 53 */      for (com.soofound.admin.bean.BranchDto dto : dtos) {
/*  54: 54 */        for (com.soofound.admin.bean.GroupMembershipBean gmb : gmbs) {
/*  55: 55 */          if (dto.getId().equals(gmb.getBranchId())) {
/*  56: 56 */            dto.setLinkedGroupName(gmb.getGroupName());
/*  57: 57 */            break;
/*  58:    */          }
/*  59:    */        }
/*  60:    */      }
/*  61:    */    }
/*  62: 62 */    results.put("data", dtos);
/*  63: 63 */    results.put("page", ((BranchService)this.service).getPagination());
/*  64: 64 */    return results;
/*  65:    */  }
/*  66:    */  
/*  67:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/branchReadyAdd.do"})
/*  68:    */  public String readyAdd(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  69: 69 */    com.soofound.admin.bean.UserDto user = getCurrentUser(request);
/*  70: 70 */    model.addAttribute("parentId", getCurrentUser(request).getBranchId());
/*  71: 71 */    com.soofound.admin.bean.BranchDto bd = (com.soofound.admin.bean.BranchDto)((BranchService)this.service).findByID(user.getBranchId());
/*  72: 72 */    if (bd == null) {
/*  73: 73 */      model.addAttribute("grade", Integer.valueOf(1));
/*  74: 74 */    } else { if (bd.getGrade() == 4) {
/*  75: 75 */        model.addAttribute("message", "对不起，不能再创建下级商家了");
/*  76: 76 */        return "/common/hint.jsp";
/*  77:    */      }
/*  78: 78 */      model.addAttribute("grade", Integer.valueOf(bd.getGrade() + 1)); }
/*  79: 79 */    return "/admin/branch/add.jsp";
/*  80:    */  }
/*  81:    */  
/*  82:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/branchReadyEdit.do"})
/*  83:    */  public String readySimpleEdit(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  84: 84 */    String id = request.getParameter("id");
/*  85: 85 */    model.addAttribute("dto", ((BranchService)this.service).findByID(id));
/*  86: 86 */    return "/admin/branch/edit.jsp";
/*  87:    */  }
/*  88:    */  
/*  89:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/branchSave.do"})
/*  90:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  91:    */  public java.util.Map<String, Object> save(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  92: 92 */    try { com.soofound.admin.bean.BranchDto dto = new com.soofound.admin.bean.BranchDto();
/*  93: 93 */      dto.setParentId(request.getParameter("parentId"));
/*  94: 94 */      dto.setId(((BranchService)this.service).getNextID(dto.getParentId()));
/*  95: 95 */      dto.setName(request.getParameter("name"));
/*  96: 96 */      dto.setGrade(Integer.parseInt(request.getParameter("grade")));
/*  97: 97 */      ((BranchService)this.service).save(dto);
/*  98:    */      
/*  99: 99 */      SurfingPolicyDto spd = SurfingPolicyDto.bornDefaultPolicy();
/* 100:100 */      spd.setBranchId(dto.getId());
/* 101:101 */      this.sps.save(spd);
/* 102:    */      
/* 103:103 */      PortalInstanceDto pid = PortalInstanceDto.bornDefaultPortal();
/* 104:104 */      pid.setBranchId(dto.getId());
/* 105:105 */      this.pis.save(pid);
/* 106:    */      
/* 107:107 */      logOperation(request, "增加商家:" + dto.getName());
/* 108:108 */      return super.getSucceedResult("增加成功");
/* 109:    */    } catch (Exception ex) {
/* 110:110 */      ex.printStackTrace(); }
/* 111:111 */    return super.getSucceedResult("增加失败");
/* 112:    */  }
/* 113:    */  
/* 114:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/branchDelete.do"})
/* 115:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 116:    */  public java.util.Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 117:117 */    String branchId = request.getParameter("radio");
/* 118:118 */    com.soofound.admin.bean.UserDto user = super.getCurrentUser(request);
/* 119:119 */    if (user.getBranchId().equals(branchId))
/* 120:120 */      return super.getFailedResult("不能删除自己");
/* 121:121 */    java.util.List<com.soofound.admin.bean.BranchDto> pds = ((BranchService)this.service).findByBranch(branchId);
/* 122:122 */    if (pds.size() > 1)
/* 123:123 */      return super.getFailedResult("有下属商家，不能删除");
/* 124:124 */    com.soofound.admin.bean.BranchDto bd = (com.soofound.admin.bean.BranchDto)((BranchService)this.service).findByID(branchId);
/* 125:125 */    logOperation(request, "删除商家:" + bd.getName());
/* 126:126 */    return super.delete(request, model);
/* 127:    */  }
/* 128:    */  
/* 129:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/branchUpdate.do"})
/* 130:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 131:131 */  public java.util.Map<String, Object> simpleUpdate(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String branchId = request.getParameter("id");
/* 132:132 */    StringBuilder sqlText1 = new StringBuilder(200);
/* 133:133 */    sqlText1.append("update admin_branch set name='").append(request.getParameter("name"));
/* 134:134 */    sqlText1.append("' where id='").append(branchId).append("'");
/* 135:135 */    this.jdbcTemplate.update(sqlText1.toString());
/* 136:136 */    return super.getSucceedResult("更新成功");
/* 137:    */  }
/* 138:    */  
/* 139:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/merchant.do"})
/* 140:    */  public String merchant(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 141:141 */    String id = request.getParameter("id");
/* 142:142 */    com.soofound.admin.bean.BranchDto bd = (com.soofound.admin.bean.BranchDto)((BranchService)this.service).findByID(id);
/* 143:143 */    model.addAttribute("dto", bd);
/* 144:    */    
/* 145:145 */    String provinceBox = this.cityService.getCpePropertySelectBox(0, "provinceId", "province", bd.getProvince());
/* 146:146 */    model.addAttribute("provinceBox", provinceBox);
/* 147:    */    
/* 148:148 */    String cityBox = this.cityService.getCpePropertySelectBox(bd.getProvince() > 0 ? bd.getProvince() : 1, "cityId", "city", bd.getCity());
/* 149:149 */    model.addAttribute("cityBox", cityBox);
/* 150:    */    
/* 151:151 */    String countyBox = this.cityService.getCpePropertySelectBox(bd.getCity() > 0 ? bd.getCity() : 1, "countyId", "county", bd.getCounty());
/* 152:152 */    model.addAttribute("countyBox", countyBox);
/* 153:    */    
/* 154:154 */    String industryBox = this.countyService.getIndustrySelectBox(0, "industryId", "industry", bd.getIndustry());
/* 155:155 */    model.addAttribute("industryBox", industryBox);
/* 156:    */    
/* 157:157 */    String subIndustryBox = this.countyService.getIndustrySelectBox(bd.getIndustry() > 0 ? bd.getIndustry() : 1, "subIndustryId", "subIndustry", bd.getSubIndustry());
/* 158:158 */    model.addAttribute("subIndustryBox", subIndustryBox);
/* 159:    */    
/* 160:160 */    model.addAttribute("acsURL", this.acs.getAcsURL());
/* 161:    */    
/* 162:162 */    return "/admin/branch/merchant.jsp";
/* 163:    */  }
/* 164:    */  
/* 165:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/saveMerchant.do"})
/* 166:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 167:167 */  public java.util.Map<String, Object> saveMerchant(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String branchId = request.getParameter("id");
/* 168:    */    try {
/* 169:169 */      StringBuilder sqlText = new StringBuilder(200);
/* 170:170 */      sqlText.append("update admin_branch set name='").append(request.getParameter("name"));
/* 171:171 */      sqlText.append("',short_name='").append(request.getParameter("shortName"));
/* 172:172 */      sqlText.append("',province=").append(request.getParameter("provinceId"));
/* 173:173 */      sqlText.append(",city=").append(request.getParameter("cityId"));
/* 174:174 */      sqlText.append(",county=").append(request.getParameter("countyId"));
/* 175:175 */      sqlText.append(",address='").append(request.getParameter("address"));
/* 176:176 */      sqlText.append("',industry=").append(request.getParameter("industryId"));
/* 177:177 */      sqlText.append(",sub_industry=").append(request.getParameter("subIndustryId"));
/* 178:178 */      sqlText.append(",contact_man='").append(request.getParameter("contact_man"));
/* 179:179 */      sqlText.append("',contact_phone='").append(request.getParameter("contact_phone"));
/* 180:180 */      sqlText.append("' where id='").append(branchId).append("'");
/* 181:181 */      this.jdbcTemplate.update(sqlText.toString());
/* 182:182 */      logOperation(request, "更新商家信息");
/* 183:183 */      return super.getSucceedResult("更新成功");
/* 184:    */    } catch (Exception e) {
/* 185:185 */      e.printStackTrace();
/* 186:    */    }
/* 187:187 */    return super.getFailedResult("更新失败");
/* 188:    */  }
/* 189:    */  
/* 202:    */  @org.springframework.beans.factory.annotation.Autowired
/* 203:    */  protected void setService(BranchService service)
/* 204:    */  {
/* 205:205 */    this.service = service;
/* 206:    */  }
/* 207:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.BranchAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */