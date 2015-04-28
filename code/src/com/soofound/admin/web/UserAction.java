/*   1:    */package com.soofound.admin.web;
/*   2:    */
/*   3:    */import com.soofound.acside.bean.OutsideIPDto;
/*   4:    */import com.soofound.acside.web.OutsideIPService;
/*   5:    */import com.soofound.cpe.util.SoofacACS;
/*   6:    */import com.soofound.framework.util.CommonUtil;
/*   7:    */import com.soofound.framework.util.DateUtil;
/*   8:    */import com.soofound.framework.util.NetworkUtil;
/*   9:    */import com.soofound.framework.util.ProjectConfig;
/*  10:    */import com.soofound.framework.util.SysConfigHelper;
/*  11:    */import com.soofound.portal.custom.WebServiceSmsSender;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.List;
/*  14:    */import javax.mail.internet.MimeMessage;
/*  15:    */import javax.servlet.http.HttpServletResponse;
/*  16:    */import org.apache.log4j.Logger;
/*  17:    */import org.springframework.beans.factory.annotation.Autowired;
/*  18:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  19:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  20:    */import org.springframework.mail.javamail.JavaMailSenderImpl;
/*  21:    */import org.springframework.security.core.Authentication;
/*  22:    */import org.springframework.security.core.GrantedAuthority;
/*  23:    */import org.springframework.security.core.authority.SimpleGrantedAuthority;
/*  24:    */import org.springframework.security.core.context.SecurityContext;
/*  25:    */import org.springframework.security.core.context.SecurityContextHolder;
/*  26:    */import org.springframework.security.web.authentication.WebAuthenticationDetails;
/*  27:    */import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
/*  28:    */import org.springframework.stereotype.Controller;
/*  29:    */
/*  30:    */@Controller
/*  31:    */public class UserAction extends com.soofound.framework.web.BaseAction<UserService>
/*  32:    */{
/*  33:    */  @Autowired
/*  34:    */  @Qualifier("defaultJdbcTemplate")
/*  35:    */  private JdbcTemplate jdbc;
/*  36:    */  @Autowired
/*  37:    */  private OutsideIPService ipService;
/*  38:    */  @Autowired
/*  39:    */  private BranchService branchService;
/*  40:    */  
/*  41:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/userListJsp.do"})
/*  42:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  43:    */  {
/*  44: 44 */    return "/admin/user/list.jsp";
/*  45:    */  }
/*  46:    */  
/*  47:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/userList.do"})
/*  48:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  49: 49 */  public java.util.Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.listByPage(request, model); }
/*  50:    */  
/*  51:    */  @org.springframework.web.bind.annotation.RequestMapping({"/home.do"})
/*  52:    */  public String home(javax.servlet.http.HttpServletRequest request, HttpServletResponse response, org.springframework.ui.ModelMap model)
/*  53:    */  {
/*  54: 54 */    org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
/*  55: 55 */    if (user == null) {
/*  56: 56 */      request.setAttribute("error", "系统超时");
/*  57: 57 */      return "/login.jsp";
/*  58:    */    }
/*  59: 59 */    com.soofound.admin.bean.UserDto dto = (com.soofound.admin.bean.UserDto)((UserService)this.service).findByID(user.getUsername());
/*  60: 60 */    if (dto.getRole().equals("ROLE_DUTY")) {
/*  61: 61 */      request.setAttribute("error", "对不起，你没有权限登录系统。");
/*  62: 62 */      return "/login.jsp";
/*  63:    */    }
/*  64: 64 */    dto.setIpAddress(request.getRemoteAddr());
/*  65: 65 */    javax.servlet.http.HttpSession session = request.getSession();
/*  66: 66 */    session.setAttribute("CURRENT_USER", dto);
/*  67: 67 */    model.addAttribute("pcfg", SysConfigHelper.getProjectConfig());
/*  68: 68 */    String location = "";
/*  69: 69 */    String ipAddress = NetworkUtil.getIpAddress(request);
/*  70: 70 */    if ("1".equals(SysConfigHelper.getAttribute("logIPLocation"))) {
/*  71:    */      try {
/*  72: 72 */        OutsideIPDto ipdto = this.ipService.findByID(ipAddress);
/*  73: 73 */        if (ipdto != null)
/*  74: 74 */          location = ipdto.getLocation();
/*  75:    */      } catch (Exception ex) {
/*  76: 76 */        ex.printStackTrace();
/*  77:    */      }
/*  78:    */    }
/*  79: 79 */    StringBuffer sqlText = new StringBuffer(100);
/*  80: 80 */    sqlText.append("insert into admin_log(id,username,fullname,operation,ip,location,log_time)values('");
/*  81: 81 */    sqlText.append(DateUtil.getCurrentTimeAsID()).append("','").append(dto.getUsername());
/*  82: 82 */    sqlText.append("','").append(dto.getFullname()).append("','登入','").append(ipAddress);
/*  83: 83 */    sqlText.append("','").append(location).append("',now())");
/*  84: 84 */    this.jdbc.update(sqlText.toString());
/*  85:    */    
/*  86: 86 */    return dto.getHome();
/*  87:    */  }
/*  88:    */  
/*  89:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/userDelete.do"})
/*  90:    */  @org.springframework.web.bind.annotation.ResponseBody
/*  91: 91 */  public java.util.Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { com.soofound.admin.bean.UserDto user = super.getCurrentUser(request);
/*  92: 92 */    String[] usernames = request.getParameterValues("checkbox");
/*  93: 93 */    StringBuilder dels = new StringBuilder(100);
/*  94: 94 */    for (String username : usernames) {
/*  95: 95 */      if (user.getUsername().equals(username))
/*  96: 96 */        return super.getFailedResult("不能删除自己");
/*  97: 97 */      if ("admin".equals(username))
/*  98: 98 */        return super.getFailedResult("不能删除[admin]");
/*  99: 99 */      dels.append(username).append(",");
/* 100:    */    }
/* 101:101 */    logOperation(request, "删除帐号:" + dels.toString());
/* 102:102 */    return super.deleteByIDs(request, model);
/* 103:    */  }
/* 104:    */  
/* 105:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/userReadyAdd.do"})
/* 106:    */  public String readyAdd(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 107:107 */    com.soofound.admin.bean.UserDto user = getCurrentUser(request);
/* 108:108 */    model.addAttribute("branchs", this.branchService.findByBranch(user.getBranchId()));
/* 109:109 */    return "/admin/user/add.jsp";
/* 110:    */  }
/* 111:    */  
/* 112:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/userSave.do"})
/* 113:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 114:114 */  public java.util.Map<String, Object> save(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String username = request.getParameter("username");
/* 115:115 */    if (((UserService)this.service).findByID(username) != null) {
/* 116:116 */      return getFailedResult("[" + username + "]已经存在.");
/* 117:    */    }
/* 118:118 */    String role = request.getParameter("role");
/* 119:119 */    if ("ROLE_ADMIN".equals(role)) {
/* 120:120 */      return getFailedResult("不能增加超级管理员");
/* 121:    */    }
/* 122:    */    try {
/* 123:123 */      com.soofound.admin.bean.UserDto dto = (com.soofound.admin.bean.UserDto)extractForm(((UserService)this.service).getDTOClazz(), request);
/* 124:124 */      dto.setCreateTime(DateUtil.getCurrentDateTime());
/* 125:125 */      org.springframework.security.authentication.encoding.Md5PasswordEncoder encoder = new org.springframework.security.authentication.encoding.Md5PasswordEncoder();
/* 126:126 */      String md5Pwd = encoder.encodePassword(dto.getPassword(), null);
/* 127:127 */      dto.setPassword(md5Pwd);
/* 128:128 */      dto.setRole("ROLE_MERCHANT");
/* 129:129 */      ((UserService)this.service).save(dto);
/* 130:130 */      logOperation(request, "创建用户:" + username);
/* 131:131 */      return super.getSucceedResult("增加成功");
/* 132:    */    } catch (Exception e) {
/* 133:133 */      this.logger.error(e); }
/* 134:134 */    return super.getFailedResult("增加失败");
/* 135:    */  }
/* 136:    */  
/* 137:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/userReadyEdit.do"})
/* 138:    */  public String readyEdit(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 139:    */  {
/* 140:140 */    String username = request.getParameter("id");
/* 141:141 */    com.soofound.admin.bean.UserDto user = getCurrentUser(request);
/* 142:142 */    model.addAttribute("dto", ((UserService)this.service).findByID(username));
/* 143:143 */    model.addAttribute("branchs", this.branchService.findByBranch(user.getBranchId()));
/* 144:144 */    return "/admin/user/edit.jsp";
/* 145:    */  }
/* 146:    */  
/* 147:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/userUpdate.do"})
/* 148:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 149:    */  public java.util.Map<String, Object> update(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 150:150 */    try { com.soofound.admin.bean.UserDto dto = (com.soofound.admin.bean.UserDto)extractForm(((UserService)this.service).getDTOClazz(), request);
/* 151:151 */      if (!CommonUtil.isEmpty(dto.getPassword())) {
/* 152:152 */        org.springframework.security.authentication.encoding.Md5PasswordEncoder encoder = new org.springframework.security.authentication.encoding.Md5PasswordEncoder();
/* 153:153 */        String md5Pwd = encoder.encodePassword(dto.getPassword(), null);
/* 154:154 */        dto.setPassword(md5Pwd);
/* 155:    */      }
/* 156:156 */      ((UserService)this.service).update(dto);
/* 157:157 */      return super.getSucceedResult("更新成功");
/* 158:    */    } catch (Exception e) {
/* 159:159 */      this.logger.error(e); }
/* 160:160 */    return super.getFailedResult("更新失败");
/* 161:    */  }
/* 162:    */  
/* 163:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/userReadyEditPerson.do"})
/* 164:    */  public String readyEditPerson(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 165:    */  {
/* 166:166 */    String username = request.getParameter("username");
/* 167:167 */    model.addAttribute("dto", ((UserService)this.service).findByID(username));
/* 168:168 */    return "/admin/user/person.jsp";
/* 169:    */  }
/* 170:    */  
/* 171:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/userUpdatePerson.do"})
/* 172:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 173:    */  public java.util.Map<String, Object> updatePerson(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 174:174 */    try { com.soofound.admin.bean.UserDto dto = (com.soofound.admin.bean.UserDto)extractForm(((UserService)this.service).getDTOClazz(), request);
/* 175:175 */      String oldPwd = request.getParameter("oldPwd");
/* 176:176 */      String password = request.getParameter("password");
/* 177:177 */      if (!CommonUtil.isEmpty(oldPwd)) {
/* 178:178 */        com.soofound.admin.bean.UserDto user = super.getCurrentUser(request);
/* 179:179 */        org.springframework.security.authentication.encoding.Md5PasswordEncoder encoder = new org.springframework.security.authentication.encoding.Md5PasswordEncoder();
/* 180:180 */        oldPwd = encoder.encodePassword(oldPwd, null);
/* 181:181 */        if (!oldPwd.equals(user.getPassword()))
/* 182:182 */          return super.getFailedResult("旧密码不正确");
/* 183:183 */        dto.setPassword(encoder.encodePassword(password, null));
/* 184:184 */        logOperation(request, "重置密码");
/* 185:    */      }
/* 186:186 */      ((UserService)this.service).updatePerson(dto);
/* 187:187 */      request.getSession().removeAttribute("CURRENT_USER");
/* 188:188 */      super.getCurrentUser(request);
/* 189:189 */      return super.getSucceedResult("更新成功");
/* 190:    */    } catch (Exception e) {
/* 191:191 */      e.printStackTrace(); }
/* 192:192 */    return super.getFailedResult("更新失败");
/* 193:    */  }
/* 194:    */  
/* 195:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/readySendEmailToUser.do"})
/* 196:    */  public String readySendEmail(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 197:    */  {
/* 198:198 */    String[] ids = request.getParameterValues("checkbox");
/* 199:199 */    com.soofound.admin.bean.UserDto dto = (com.soofound.admin.bean.UserDto)((UserService)this.service).findByID(ids[0]);
/* 200:200 */    model.addAttribute("dto", dto);
/* 201:    */    
/* 202:202 */    StringBuilder content = new StringBuilder(100);
/* 203:203 */    SoofacACS soofac = (SoofacACS)SysConfigHelper.getBean("soofacACS");
/* 204:204 */    content.append("云AC登录地址:").append(soofac.getAcsURL()).append("\n");
/* 205:205 */    content.append("您的用户名:").append(dto.getUsername()).append(",密码:123456");
/* 206:206 */    model.addAttribute("content", content.toString());
/* 207:    */    
/* 208:208 */    return "/admin/user/email.jsp";
/* 209:    */  }
/* 210:    */  
/* 211:    */  @org.springframework.web.bind.annotation.RequestMapping({"/admin/sendEmailToUser.do"})
/* 212:    */  @org.springframework.web.bind.annotation.ResponseBody
/* 213:213 */  public java.util.Map<String, Object> sendEmail(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { String email = request.getParameter("email");
/* 214:214 */    String phone = request.getParameter("phone");
/* 215:215 */    String content = request.getParameter("content");
/* 216:    */    
/* 217:217 */    JavaMailSenderImpl sender = (JavaMailSenderImpl)SysConfigHelper.getBean("mailSender");
/* 218:    */    try {
/* 219:219 */      MimeMessage msg = sender.createMimeMessage();
/* 220:220 */      org.springframework.mail.javamail.MimeMessageHelper helper = new org.springframework.mail.javamail.MimeMessageHelper(msg, true, "GB2312");
/* 221:221 */      helper.setFrom(sender.getUsername(), SysConfigHelper.getProjectConfig().getProduct());
/* 222:222 */      helper.setTo(email);
/* 223:223 */      helper.setSubject("用户信息");
/* 224:224 */      helper.setText(content, true);
/* 225:225 */      sender.send(msg);
/* 226:    */    } catch (Exception e) {
/* 227:227 */      e.printStackTrace();
/* 228:    */    }
/* 229:229 */    WebServiceSmsSender wsss = new WebServiceSmsSender();
/* 230:230 */    wsss.sendSMS("0", phone, content);
/* 231:231 */    return super.getSucceedResult("发送成功");
/* 232:    */  }
/* 233:    */  
/* 234:    */  @org.springframework.web.bind.annotation.RequestMapping({"/ucs/fly/portal/login.do"})
/* 235:    */  public String branchLogin(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 236:236 */    String branch = request.getParameter("branch");
/* 237:237 */    if (branch == null) {
/* 238:238 */      model.addAttribute("error", "未知分行");
/* 239:239 */      return "/common/hint.jsp";
/* 240:    */    }
/* 241:241 */    if (!"0:0:0:0:0:0:0:1".equals(request.getRemoteAddr())) {
/* 242:242 */      String captcha = request.getParameter("captcha");
/* 243:243 */      String storeCaptcha = (String)request.getSession().getAttribute("image_captcha");
/* 244:244 */      if ((storeCaptcha != null) && (!storeCaptcha.equals(captcha))) {
/* 245:245 */        model.addAttribute("error", "校验码错误");
/* 246:246 */        return "/ucs/fly/portal/" + branch + "/login.jsp";
/* 247:    */      }
/* 248:    */    }
/* 249:249 */    String username = request.getParameter("username");
/* 250:250 */    String password = request.getParameter("password");
/* 251:251 */    org.springframework.security.authentication.encoding.Md5PasswordEncoder encoder = new org.springframework.security.authentication.encoding.Md5PasswordEncoder();
/* 252:252 */    password = encoder.encodePassword(password, null);
/* 253:253 */    com.soofound.admin.bean.UserDto dto = ((UserService)this.service).findByUsername(username, password);
/* 254:254 */    if (dto == null) {
/* 255:255 */      model.addAttribute("error", "用户名或密码错误");
/* 256:256 */      return "/ucs/fly/portal/" + branch + "/login.jsp";
/* 257:    */    }
/* 258:258 */    List<GrantedAuthority> gas = new ArrayList();
/* 259:259 */    gas.add(new SimpleGrantedAuthority("ROLE_MERCHANT"));
/* 260:260 */    org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username, password, gas);
/* 261:261 */    PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(user, user.getPassword(), user.getAuthorities());
/* 262:262 */    authentication.setDetails(new WebAuthenticationDetails(request));
/* 263:263 */    SecurityContextHolder.getContext().setAuthentication(authentication);
/* 264:264 */    javax.servlet.http.HttpSession session = request.getSession(true);
/* 265:265 */    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
/* 266:266 */    session.setAttribute("CURRENT_USER", dto);
/* 267:267 */    model.addAttribute("branch", branch);
/* 268:268 */    model.addAttribute("user", dto);
/* 269:    */    try {
/* 270:270 */      OutsideIPDto ipdto = this.ipService.findByID(request.getRemoteAddr());
/* 271:271 */      StringBuffer sqlText = new StringBuffer(100);
/* 272:272 */      sqlText.append("insert into admin_log(id,username,fullname,operation,ip,location,log_time)values('");
/* 273:273 */      sqlText.append(DateUtil.getCurrentTimeAsID()).append("','").append(dto.getUsername());
/* 274:274 */      sqlText.append("','").append(dto.getFullname()).append("','登入.','").append(request.getRemoteAddr());
/* 275:275 */      sqlText.append("','").append(ipdto.getLocation()).append("','").append(DateUtil.getCurrentDateTime()).append("')");
/* 276:276 */      this.jdbc.update(sqlText.toString());
/* 277:    */    } catch (Exception ex) {
/* 278:278 */      ex.printStackTrace();
/* 279:    */    }
/* 280:280 */    return "/ucs/fly/branch/icbcDeyang/surfing/gen.jsp";
/* 281:    */  }
/* 282:    */  
/* 283:    */  @org.springframework.web.bind.annotation.RequestMapping({"/ucs/fly/portal/logout.do"})
/* 284:    */  public String branchLogout(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 285:285 */    String branch = request.getParameter("branch");
/* 286:286 */    javax.servlet.http.HttpSession session = request.getSession(true);
/* 287:287 */    session.setAttribute("SPRING_SECURITY_CONTEXT", null);
/* 288:288 */    return "/ucs/fly/portal/" + branch + "/login.jsp";
/* 289:    */  }
/* 290:    */  
/* 297:    */  @Autowired
/* 298:    */  protected void setService(UserService service)
/* 299:    */  {
/* 300:300 */    this.service = service;
/* 301:    */  }
/* 302:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.UserAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */