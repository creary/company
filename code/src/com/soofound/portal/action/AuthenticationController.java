/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.cpe.util.SoofacACS;
/*   4:    */import com.soofound.cpe.web.HostService;
/*   5:    */import com.soofound.framework.util.SysConfigHelper;
/*   6:    */import com.soofound.framework.web.GenericAction;
/*   7:    */import com.soofound.portal.service.SurfingPolicyService;
/*   8:    */import java.text.SimpleDateFormat;
/*   9:    */import java.util.HashMap;
/*  10:    */import org.springframework.stereotype.Controller;
/*  11:    */import org.springframework.ui.ModelMap;
/*  12:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  13:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  14:    */
/*  15:    */@Controller
/*  16:    */public class AuthenticationController extends GenericAction
/*  17:    */{
/*  18:    */  @org.springframework.beans.factory.annotation.Autowired
/*  19:    */  private HostService hostSvc;
/*  20:    */  @org.springframework.beans.factory.annotation.Autowired
/*  21:    */  private SurfingPolicyService sps;
/*  22:    */  @org.springframework.beans.factory.annotation.Autowired
/*  23:    */  private com.soofound.portal.service.SurfingAccountService sas;
/*  24:    */  @org.springframework.beans.factory.annotation.Autowired
/*  25:    */  private SoofacACS acs;
/*  26:    */  
/*  27:    */  @RequestMapping({"/acs/toWifiUserAgreement.do"})
/*  28:    */  public String toWifiUserAgreement(javax.servlet.http.HttpServletRequest request, ModelMap model)
/*  29:    */  {
/*  30: 30 */    model.addAttribute("pcfg", SysConfigHelper.getProjectConfig());
/*  31: 31 */    return "/acs/wifiUserAgreement.jsp";
/*  32:    */  }
/*  33:    */  
/*  34:    */  @RequestMapping({"/acs/getPhonePassword.do"})
/*  35:    */  @ResponseBody
/*  36: 36 */  public java.util.Map<String, Object> getPhonePassword(javax.servlet.http.HttpServletRequest request) { String mobile = request.getParameter("mobile");
/*  37: 37 */    String branchId = request.getParameter("branchId");
/*  38: 38 */    boolean fake = "2".equals(request.getParameter("smsFlag"));
/*  39:    */    try {
/*  40: 40 */      com.soofound.portal.bean.SurfingAccount sa = this.sas.findByUsername(branchId, mobile);
/*  41: 41 */      String[] ssids = request.getParameter("ssid").split("-");
/*  42: 42 */      com.soofound.portal.bean.SurfingPolicyDto spd = this.sps.getPolicyBySSID(ssids[0], ssids[1]);
/*  43: 43 */      if ((spd != null) && (spd.getValidity() > 0)) {
/*  44: 44 */        if (sa == null) {
/*  45: 45 */          sa = new com.soofound.portal.bean.SurfingAccount();
/*  46: 46 */          sa.setUsername(mobile);
/*  47: 47 */          sa.setBranchId(branchId);
/*  48: 48 */          sa.setFlag("mobile");
/*  49: 49 */          sa.setPassword(this.sas.getRandomPassword());
/*  50: 50 */          sa.setCreateTime(com.soofound.framework.util.DateUtil.getCurrentDateTime());
/*  51: 51 */          sa.setOpenId(getExpiredDate(sa.getCreateTime(), spd.getValidity()));
/*  52: 52 */          this.sas.save(sa);
/*  53:    */        }
/*  54: 54 */        boolean expired = com.soofound.framework.util.DateUtil.getDiffMinutes(sa.getCreateTime(), com.soofound.framework.util.DateUtil.getCurrentDateTime()) > spd.getValidity();
/*  55: 55 */        if (expired) {
/*  56: 56 */          sa.setPassword(this.sas.getRandomPassword());
/*  57: 57 */          sa.setCreateTime(com.soofound.framework.util.DateUtil.getCurrentDateTime());
/*  58: 58 */          sa.setOpenId(getExpiredDate(sa.getCreateTime(), spd.getValidity()));
/*  59: 59 */          this.sas.update(sa);
/*  60:    */        }
/*  61: 61 */        String smsStr = null;
/*  62: 62 */        if (this.acs.getRealm().startsWith("wifiBeijing")) {
/*  63: 63 */          com.soofound.cpe.bean.HostBean host = (com.soofound.cpe.bean.HostBean)this.hostSvc.findByID(ssids[0]);
/*  64: 64 */          smsStr = getSmsContent(host.getName(), sa.getPassword(), sa.getOpenId());
/*  65:    */        } else {
/*  66: 66 */          smsStr = getSmsContent(null, sa.getPassword(), sa.getOpenId()); }
/*  67: 67 */        sa.setSmsstr(smsStr);
/*  68: 68 */      } else if (sa == null) {
/*  69: 69 */        sa = new com.soofound.portal.bean.SurfingAccount();
/*  70: 70 */        sa.setUsername(mobile);
/*  71: 71 */        sa.setBranchId(branchId);
/*  72: 72 */        sa.setFlag("mobile");
/*  73: 73 */        sa.setPassword(this.sas.getRandomPassword());
/*  74: 74 */        this.sas.save(sa);
/*  75:    */      }
/*  76: 76 */      if (fake) {
/*  77: 77 */        java.util.Map<String, Object> result = new HashMap();
/*  78: 78 */        result.put("status", Integer.valueOf(1));
/*  79: 79 */        result.put("data", sa.getPassword());
/*  80: 80 */        return result;
/*  81:    */      }
/*  82: 82 */      this.sas.sendSMS(sa);
/*  83: 83 */      return getSucceedResult("短信已发送");
/*  84:    */    }
/*  85:    */    catch (Exception ex) {
/*  86: 86 */      ex.printStackTrace(); }
/*  87: 87 */    return getFailedResult("短信失败.");
/*  88:    */  }
/*  89:    */  
/*  90:    */  private String getExpiredDate(String startTime, int validity)
/*  91:    */  {
/*  92:    */    try {
/*  93: 93 */      java.util.Calendar cal = java.util.Calendar.getInstance();
/*  94: 94 */      cal.setTime(com.soofound.framework.util.DateUtil.datetimeFormat.parse(startTime));
/*  95: 95 */      cal.add(12, validity);
/*  96: 96 */      return com.soofound.framework.util.DateUtil.datetimeFormat.format(cal.getTime());
/*  97:    */    } catch (Exception e) {}
/*  98: 98 */    return null;
/*  99:    */  }
/* 100:    */  
/* 101:    */  private String getSmsContent(String apName, String password, String expiredTime)
/* 102:    */  {
/* 103:103 */    StringBuilder smsStr = new StringBuilder(200);
/* 104:104 */    if (apName == null) {
/* 105:105 */      smsStr.append("您本次的上网密码是").append(password).append("，本密码在");
/* 106:106 */      smsStr.append(expiredTime).append("有效期内使用。");
/* 107:    */    } else {
/* 108:108 */      smsStr.append("亲，欢迎到访").append(apName).append("，您本次的上网密码是").append(password).append("，本密码在").append(expiredTime);
/* 109:109 */      smsStr.append("有效期内不能再次申请、不能挂失，此密码不能挂失，请在有效期内妥善保存，该密码可在任何具备WiFi联网的无线终端上使用，只要在有效期内，您上网下线后可再次登陆免费使用该密码继续上网，WiFiBeijing拥有该密码的解释权。");
/* 110:    */    }
/* 111:111 */    return smsStr.toString();
/* 112:    */  }
/* 113:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.AuthenticationController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */