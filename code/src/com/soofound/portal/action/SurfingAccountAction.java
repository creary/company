/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.UserDto;
/*   4:    */import com.soofound.framework.util.DateUtil;
/*   5:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*   6:    */import com.soofound.portal.bean.SurfingUser;
/*   7:    */import com.soofound.portal.service.SurfingPolicyService;
/*   8:    */import com.soofound.portal.service.SurfingSessionService;
/*   9:    */import com.soofound.portal.service.WifiDogService;
/*  10:    */import java.util.HashMap;
/*  11:    */import java.util.List;
/*  12:    */import javax.servlet.http.HttpServletResponse;
/*  13:    */import org.springframework.beans.factory.annotation.Autowired;
/*  14:    */import org.springframework.stereotype.Controller;
/*  15:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  16:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  17:    */
/*  18:    */@Controller
/*  19:    */public class SurfingAccountAction extends com.soofound.framework.web.BaseAction<com.soofound.portal.service.SurfingAccountService>
/*  20:    */{
/*  21:    */  @Autowired
/*  22:    */  private SurfingPolicyService sps;
/*  23:    */  @Autowired
/*  24:    */  private SurfingSessionService sss;
/*  25:    */  @Autowired
/*  26:    */  private WifiDogService wds;
/*  27:    */  
/*  28:    */  @RequestMapping({"/portal/surfingAccountListJsp.do"})
/*  29:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  30:    */  {
/*  31: 31 */    return "/portal/surfing/accountList.jsp";
/*  32:    */  }
/*  33:    */  
/*  34:    */  @RequestMapping({"/portal/surfingAccountList.do"})
/*  35:    */  @ResponseBody
/*  36: 36 */  public java.util.Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.listByPage(request, model); }
/*  37:    */  
/*  38:    */  @RequestMapping({"/portal/surfingAccountReadyAdd.do"})
/*  39:    */  public String readyAdd(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  40:    */  {
/*  41: 41 */    model.addAttribute("password", ((com.soofound.portal.service.SurfingAccountService)this.service).getRandomPassword());
/*  42: 42 */    return "/portal/surfing/accountAdd.jsp";
/*  43:    */  }
/*  44:    */  
/*  45:    */  @RequestMapping({"/portal/surfingAccountSave.do"})
/*  46:    */  @ResponseBody
/*  47: 47 */  public java.util.Map<String, Object> save(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String username = request.getParameter("username");
/*  48:    */    try {
/*  49: 49 */      UserDto user = getCurrentUser(request);
/*  50: 50 */      if (((com.soofound.portal.service.SurfingAccountService)this.service).findByUsername(user.getBranchId(), username) != null) {
/*  51: 51 */        return getFailedResult("[" + username + "]已经存在");
/*  52:    */      }
/*  53: 53 */      com.soofound.portal.bean.SurfingAccount dto = new com.soofound.portal.bean.SurfingAccount();
/*  54: 54 */      dto.setUsername(username);
/*  55: 55 */      dto.setBranchId(user.getBranchId());
/*  56: 56 */      dto.setPassword(request.getParameter("password"));
/*  57: 57 */      dto.setUpSpeed(Integer.parseInt(request.getParameter("upSpeed")));
/*  58: 58 */      dto.setDownSpeed(Integer.parseInt(request.getParameter("downSpeed")));
/*  59: 59 */      dto.setFlag("db");
/*  60: 60 */      ((com.soofound.portal.service.SurfingAccountService)this.service).save(dto);
/*  61: 61 */      return getSucceedResult("增加成功");
/*  62:    */    } catch (Exception e) {
/*  63: 63 */      e.printStackTrace(); }
/*  64: 64 */    return super.getFailedResult("增加失败");
/*  65:    */  }
/*  66:    */  
/*  67:    */  @RequestMapping({"/portal/surfingAccountEdit.do"})
/*  68:    */  public String readyEdit(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  69:    */  {
/*  70: 70 */    String id = request.getParameter("id");
/*  71: 71 */    model.addAttribute("dto", ((com.soofound.portal.service.SurfingAccountService)this.service).findByID(id));
/*  72: 72 */    return "/portal/surfing/accountEdit.jsp";
/*  73:    */  }
/*  74:    */  
/*  75:    */  @RequestMapping({"/portal/surfingAccountUpdate.do"})
/*  76:    */  @ResponseBody
/*  77:    */  public java.util.Map<String, Object> update(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  78: 78 */    try { com.soofound.portal.bean.SurfingAccount dto = new com.soofound.portal.bean.SurfingAccount();
/*  79: 79 */      dto.setId(Integer.parseInt(request.getParameter("id")));
/*  80: 80 */      dto.setBranchId(request.getParameter("branchId"));
/*  81: 81 */      dto.setPassword(request.getParameter("password"));
/*  82: 82 */      dto.setUpSpeed(Integer.parseInt(request.getParameter("upSpeed")));
/*  83: 83 */      dto.setDownSpeed(Integer.parseInt(request.getParameter("downSpeed")));
/*  84: 84 */      dto.setUpdateSpeed(true);
/*  85: 85 */      ((com.soofound.portal.service.SurfingAccountService)this.service).update(dto);
/*  86: 86 */      List<SurfingUser> sus = this.wds.getSurfingUser2(request.getParameter("branchId"), request.getParameter("username"));
/*  87: 87 */      for (SurfingUser su : sus) {
/*  88: 88 */        String[] keys = su.getSsid().split("-");
/*  89: 89 */        SurfingPolicyDto bdto = this.sps.getPolicyBySSID(keys[0], keys[1]);
/*  90: 90 */        if (dto.getUpSpeed() == 0) {
/*  91: 91 */          su.setUpSpeed(bdto.getUpSpeed());
/*  92:    */        } else
/*  93: 93 */          su.setUpSpeed(dto.getUpSpeed());
/*  94: 94 */        if (dto.getDownSpeed() == 0) {
/*  95: 95 */          su.setDownSpeed(bdto.getDownSpeed());
/*  96:    */        } else
/*  97: 97 */          su.setDownSpeed(dto.getDownSpeed());
/*  98:    */      }
/*  99: 99 */      return getSucceedResult("更新成功");
/* 100:    */    } catch (Exception e) {
/* 101:101 */      e.printStackTrace(); }
/* 102:102 */    return super.getFailedResult("更新失败");
/* 103:    */  }
/* 104:    */  
/* 105:    */  @RequestMapping({"/portal/surfingAccountDelete.do"})
/* 106:    */  @ResponseBody
/* 107:    */  public java.util.Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 108:108 */    String[] ids = request.getParameterValues("checkbox");
/* 109:    */    try {
/* 110:110 */      if (ids == null)
/* 111:111 */        ids = new String[] { request.getParameter("id") };
/* 112:112 */      List<com.soofound.portal.bean.SurfingAccount> sas = ((com.soofound.portal.service.SurfingAccountService)this.service).getSurfingAccounts(ids);
/* 113:113 */      this.wds.recordOffline(sas);
/* 114:114 */      this.sss.deleteSessions(sas);
/* 115:115 */      ((com.soofound.portal.service.SurfingAccountService)this.service).delete(ids);
/* 116:116 */      return super.getSucceedResult("删除成功");
/* 117:    */    } catch (Exception e) {
/* 118:118 */      e.printStackTrace(); }
/* 119:119 */    return super.getFailedResult("删除失败");
/* 120:    */  }
/* 121:    */  
/* 122:    */  @RequestMapping({"/portal/getSequenceAccount.do"})
/* 123:    */  @ResponseBody
/* 124:    */  public java.util.Map<String, Object> getSequenceAccount(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 125:    */    try {
/* 126:126 */      String branchId = request.getParameter("branchId");
/* 127:127 */      String phone = request.getParameter("phone");
/* 128:128 */      com.soofound.portal.bean.SurfingAccount sa = ((com.soofound.portal.service.SurfingAccountService)this.service).getSequenceAccount(branchId, phone);
/* 129:129 */      if ((phone != null) && (sa.getTimes() > 0)) {
/* 130:130 */        return super.getFailedResult("用户[" + phone + "]已经存在,密码:" + sa.getPassword());
/* 131:    */      }
/* 132:132 */      java.util.Map<String, Object> result = super.getSucceedResult("操作成功");
/* 133:133 */      java.util.Map<String, String> data = new HashMap();
/* 134:134 */      data.put("user", sa.getUsername());
/* 135:135 */      data.put("password", sa.getPassword());
/* 136:136 */      data.put("sn", sa.getOpenId());
/* 137:137 */      data.put("ctime", DateUtil.getCurrentDateTime());
/* 138:138 */      result.put("data", data);
/* 139:139 */      return result;
/* 140:    */    } catch (Exception e) {
/* 141:141 */      e.printStackTrace(); }
/* 142:142 */    return super.getFailedResult("操作失败");
/* 143:    */  }
/* 144:    */  
/* 145:    */  @RequestMapping({"/portal/surfingAccountResetPassword.do"})
/* 146:    */  @ResponseBody
/* 147:    */  public java.util.Map<String, Object> resetPassword(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 148:    */    try {
/* 149:149 */      String id = request.getParameter("id");
/* 150:150 */      com.soofound.portal.bean.SurfingAccount dto = (com.soofound.portal.bean.SurfingAccount)((com.soofound.portal.service.SurfingAccountService)this.service).findByID(id);
/* 151:151 */      dto.setPassword(((com.soofound.portal.service.SurfingAccountService)this.service).getRandomPassword());
/* 152:152 */      ((com.soofound.portal.service.SurfingAccountService)this.service).updatePassword(id, dto.getPassword());
/* 153:153 */      java.util.Map<String, Object> result = super.getSucceedResult("操作成功");
/* 154:154 */      result.put("data", dto.getPassword());
/* 155:155 */      return result;
/* 156:    */    } catch (Exception e) {
/* 157:157 */      e.printStackTrace(); }
/* 158:158 */    return super.getFailedResult("操作失败");
/* 159:    */  }
/* 160:    */  
/* 162:    */  @RequestMapping({"/portal/forceOffline.do"})
/* 163:    */  @ResponseBody
/* 164:    */  public java.util.Map<String, Object> forceOffline(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 165:    */  {
/* 166:    */    try
/* 167:    */    {
/* 168:168 */      String[] ids = request.getParameterValues("checkbox");
/* 169:169 */      List<com.soofound.portal.bean.SurfingAccount> sas = ((com.soofound.portal.service.SurfingAccountService)this.service).getSurfingAccounts(ids);
/* 170:170 */      this.wds.recordOffline(sas);
/* 171:171 */      return super.getSucceedResult("操作成功");
/* 172:    */    } catch (Exception ex) {
/* 173:173 */      ex.printStackTrace(); }
/* 174:174 */    return super.getFailedResult("操作失败");
/* 175:    */  }
/* 176:    */  
/* 177:    */  @RequestMapping({"/portal/surfingAccountExport.do"})
/* 178:    */  public void export(javax.servlet.http.HttpServletRequest request, HttpServletResponse response)
/* 179:    */  {
/* 180:180 */    UserDto user = getCurrentUser(request);
/* 181:181 */    logOperation(request, "导出用户帐号");
/* 182:182 */    String fileName = ((com.soofound.portal.service.SurfingAccountService)this.service).doExport(user.getBranchId());
/* 183:183 */    super.downloadFile(response, fileName);
/* 184:    */  }
/* 185:    */  
/* 186:    */  @RequestMapping({"/wifiant/checkUser.do"})
/* 187:    */  @ResponseBody
/* 188:188 */  public java.util.Map<String, Object> checkUser(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String branchId = request.getParameter("branchId");
/* 189:189 */    String username = request.getParameter("username");
/* 190:190 */    String password = request.getParameter("password");
/* 191:191 */    com.soofound.portal.bean.SurfingAccount sa = ((com.soofound.portal.service.SurfingAccountService)this.service).findByAuth(branchId, username, password);
/* 192:192 */    if (sa == null) {
/* 193:193 */      return getFailedResult("认证失败");
/* 194:    */    }
/* 195:195 */    return getSucceedResult("认证成功");
/* 196:    */  }
/* 197:    */  
/* 203:    */  @Autowired
/* 204:    */  protected void setService(com.soofound.portal.service.SurfingAccountService service)
/* 205:    */  {
/* 206:206 */    this.service = service;
/* 207:    */  }
/* 208:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.SurfingAccountAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */