/*   1:    */package com.soofound.admin.web;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.BranchDto;
/*   4:    */import com.soofound.admin.bean.LicenseDto;
/*   5:    */import com.soofound.admin.bean.UserDto;
/*   6:    */import com.soofound.cpe.bean.HostBean;
/*   7:    */import com.soofound.cpe.util.CpeUtils;
/*   8:    */import com.soofound.cpe.web.HostService;
/*   9:    */import com.soofound.framework.util.CommonUtil;
/*  10:    */import com.soofound.framework.util.DateUtil;
/*  11:    */import com.soofound.framework.web.BaseAction;
/*  12:    */import java.util.Map;
/*  13:    */import org.springframework.beans.factory.annotation.Autowired;
/*  14:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  15:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  16:    */import org.springframework.stereotype.Controller;
/*  17:    */import org.springframework.util.StringUtils;
/*  18:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  19:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  20:    */
/*  21:    */@Controller
/*  22:    */public class LicenseAction extends BaseAction<LicenseService>
/*  23:    */{
/*  24:    */  @Autowired
/*  25:    */  @Qualifier("defaultJdbcTemplate")
/*  26:    */  protected JdbcTemplate jdbcTemplate;
/*  27:    */  @Autowired
/*  28:    */  private BranchService branchService;
/*  29:    */  @Autowired
/*  30:    */  private HostService hostService;
/*  31:    */  
/*  32:    */  @RequestMapping({"/admin/licenseListJsp.do"})
/*  33:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  34:    */  {
/*  35: 35 */    return "/admin/license/list.jsp";
/*  36:    */  }
/*  37:    */  
/*  38:    */  @RequestMapping({"/admin/licenseList.do"})
/*  39:    */  @ResponseBody
/*  40: 40 */  public Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.listByPage(request, model); }
/*  41:    */  
/*  42:    */  @RequestMapping({"/admin/licenseReadyActive.do"})
/*  43:    */  public String readyActive(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  44:    */  {
/*  45: 45 */    String[] macs = request.getParameterValues("checkbox");
/*  46: 46 */    UserDto user = super.getCurrentUser(request);
/*  47: 47 */    model.addAttribute("branchs", this.branchService.findByBranch(user.getBranchId()));
/*  48: 48 */    if (macs != null) {
/*  49: 49 */      model.addAttribute("macs", CommonUtil.arrayToQuotString(macs));
/*  50: 50 */      model.addAttribute("num", Integer.valueOf(macs.length));
/*  51:    */    }
/*  52: 52 */    return "/admin/license/active.jsp";
/*  53:    */  }
/*  54:    */  
/*  55:    */  @RequestMapping({"/admin/licenseActive.do"})
/*  56:    */  @ResponseBody
/*  57:    */  public Map<String, Object> active(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  58: 58 */    try { String macs = request.getParameter("macs");
/*  59: 59 */      String branchId = request.getParameter("branchId");
/*  60:    */      
/*  61: 61 */      String[] sqls = new String[4];
/*  62: 62 */      StringBuilder sqlText1 = new StringBuilder(100);
/*  63: 63 */      sqlText1.append("update admin_license set log_time=NOW(),branch_id='").append(branchId).append("' where mac in (");
/*  64: 64 */      sqlText1.append(macs).append(")");
/*  65:    */      
/*  66: 66 */      StringBuilder sqlText2 = new StringBuilder(100);
/*  67: 67 */      sqlText2.append("update cpe_host set branch_id='").append(branchId).append("' where serial_number in (");
/*  68: 68 */      sqlText2.append(macs).append(")");
/*  69:    */      
/*  70: 70 */      StringBuilder sqlText3 = new StringBuilder(100);
/*  71: 71 */      sqlText3.append("update cpe_ssid set portal_id=0,policy_id=0 where ap_id in (select id from cpe_host where serial_number in (");
/*  72: 72 */      sqlText3.append(macs).append("))");
/*  73:    */      
/*  74: 74 */      StringBuilder sqlText4 = new StringBuilder(100);
/*  75: 75 */      sqlText4.append("delete from membership_ap_group where ap_id in (select id from cpe_host where serial_number in (");
/*  76: 76 */      sqlText4.append(macs).append("))");
/*  77:    */      
/*  78: 78 */      sqls[0] = sqlText1.toString();
/*  79: 79 */      sqls[1] = sqlText2.toString();
/*  80: 80 */      sqls[2] = sqlText3.toString();
/*  81: 81 */      sqls[3] = sqlText4.toString();
/*  82: 82 */      this.jdbcTemplate.batchUpdate(sqls);
/*  83:    */      
/*  84: 84 */      BranchDto bd = (BranchDto)this.branchService.findByID(branchId);
/*  85: 85 */      String[] tempmacs = macs.split(",");
/*  86: 86 */      for (String tempmac : tempmacs) {
/*  87: 87 */        String mac = tempmac.substring(1, 18);
/*  88: 88 */        HostBean hb = this.hostService.findBySerialNumber(mac);
/*  89: 89 */        if (hb != null)
/*  90: 90 */          hb.setBranchId(branchId);
/*  91:    */      }
/*  92: 92 */      int size = tempmacs.length;
/*  93: 93 */      super.logOperation(request, "分派" + size + "个序列号给:" + bd.getName());
/*  94: 94 */      return super.getSucceedResult("分配成功");
/*  95:    */    } catch (Exception ex) {
/*  96: 96 */      ex.printStackTrace(); }
/*  97: 97 */    return super.getFailedResult("操作失败");
/*  98:    */  }
/*  99:    */  
/* 100:    */  @RequestMapping({"/admin/licenseDelete.do"})
/* 101:    */  @ResponseBody
/* 102:    */  public Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 103:103 */    return super.deleteByIDs(request, model);
/* 104:    */  }
/* 105:    */  
/* 106:    */  @RequestMapping({"/admin/licenseReadyEdit.do"})
/* 107:    */  public String readyEdit(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 108:108 */    String id = request.getParameter("id");
/* 109:109 */    model.addAttribute("dto", ((LicenseService)this.service).findByID(id));
/* 110:110 */    return "/admin/license/edit.jsp";
/* 111:    */  }
/* 112:    */  
/* 113:    */  @RequestMapping({"/admin/licenseReadyAdd.do"})
/* 114:    */  public String readyAdd(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 115:115 */    model.addAttribute("logTime", DateUtil.getCurrentDateTime());
/* 116:116 */    return "/admin/license/add.jsp";
/* 117:    */  }
/* 118:    */  
/* 119:    */  @RequestMapping({"/admin/licenseSave.do"})
/* 120:    */  @ResponseBody
/* 121:    */  public Map<String, Object> save(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 122:122 */    try { String mac = request.getParameter("mac");
/* 123:123 */      if (mac.indexOf(":") == -1)
/* 124:124 */        mac = CpeUtils.convertMac(mac);
/* 125:125 */      if (((LicenseService)this.service).findByID(mac) != null) {
/* 126:126 */        StringBuilder str = new StringBuilder(100);
/* 127:127 */        str.append("序列号[").append(mac).append("]已经存在");
/* 128:128 */        return getFailedResult(str.toString());
/* 129:    */      }
/* 130:130 */      String flashId = request.getParameter("flashId");
/* 131:131 */      if ((StringUtils.hasText(flashId)) && (((LicenseService)this.service).findByFlashID(flashId) != null)) {
/* 132:132 */        StringBuilder str = new StringBuilder(100);
/* 133:133 */        str.append("FlashID[").append(mac).append("]已经存在");
/* 134:134 */        return getFailedResult(str.toString());
/* 135:    */      }
/* 136:136 */      return super.save(request, model);
/* 137:    */    } catch (Exception e) {
/* 138:138 */      e.printStackTrace(); }
/* 139:139 */    return super.getFailedResult("增加失败");
/* 140:    */  }
/* 141:    */  
/* 142:    */  @RequestMapping({"/admin/licenseUpdate.do"})
/* 143:    */  @ResponseBody
/* 144:    */  public Map<String, Object> update(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 145:145 */    String flashId = request.getParameter("flashId");
/* 146:146 */    LicenseDto dto = ((LicenseService)this.service).findByFlashID(flashId);
/* 147:147 */    if ((dto != null) && (dto.getFlashId().equals(flashId))) {
/* 148:148 */      return getFailedResult("[" + flashId + "]已经存在.");
/* 149:    */    }
/* 150:150 */    return super.update(request, model);
/* 151:    */  }
/* 152:    */  
/* 154:    */  @RequestMapping({"/admin/licenseReadyBatchAdd.do"})
/* 155:155 */  public String readyBatchAdd(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return "/admin/license/batch.jsp"; }
/* 156:    */  
/* 157:    */  @RequestMapping({"/admin/licenseBatchAdd.do"})
/* 158:    */  @ResponseBody
/* 159:    */  public Map<String, Object> batchAdd(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 160:160 */    String content = request.getParameter("content");
/* 161:161 */    return ((LicenseService)this.service).batchAdd(content);
/* 162:    */  }
/* 163:    */  
/* 164:    */  @RequestMapping({"/acs/checkMac.do"})
/* 165:    */  @ResponseBody
/* 166:166 */  public String checkMac(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String mac = request.getParameter("mac");
/* 167:167 */    String flashId = request.getParameter("key");
/* 168:168 */    String realm = request.getParameter("realm");
/* 169:169 */    if (realm == null)
/* 170:170 */      realm = "www.wifiant.cn";
/* 171:171 */    return ((LicenseService)this.service).checkMac(mac, flashId, realm);
/* 172:    */  }
/* 173:    */  
/* 180:    */  @Autowired
/* 181:    */  protected void setService(LicenseService service)
/* 182:    */  {
/* 183:183 */    this.service = service;
/* 184:    */  }
/* 185:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.LicenseAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */