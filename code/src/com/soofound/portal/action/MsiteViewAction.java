/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.BranchDao;
/*   4:    */import com.soofound.cpe.dao.HostDao;
/*   5:    */import com.soofound.framework.web.GenericAction;
/*   6:    */import com.soofound.portal.bean.SimpleReportBean;
/*   7:    */import com.soofound.portal.service.SurfingAccountService;
/*   8:    */import com.soofound.report.concrete.APLoadTopNReporter;
/*   9:    */import com.soofound.report.concrete.APTrafficTrendReporter;
/*  10:    */import com.soofound.report.concrete.BrandPieReporter;
/*  11:    */import com.soofound.report.concrete.EndUserCountTrendReporter;
/*  12:    */import com.soofound.report.concrete.StayLongPieReporter;
/*  13:    */import java.io.PrintStream;
/*  14:    */import java.util.ArrayList;
/*  15:    */import java.util.HashMap;
/*  16:    */import java.util.List;
/*  17:    */import org.springframework.beans.factory.annotation.Autowired;
/*  18:    */import org.springframework.stereotype.Controller;
/*  19:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  20:    */
/*  21:    */@Controller
/*  22:    */public class MsiteViewAction extends GenericAction
/*  23:    */{
/*  24:    */  @Autowired
/*  25:    */  private BranchDao bdao;
/*  26:    */  @Autowired
/*  27:    */  private HostDao hdao;
/*  28:    */  @Autowired
/*  29:    */  private SurfingAccountService sas;
/*  30:    */  
/*  31:    */  @org.springframework.web.bind.annotation.RequestMapping({"/home/soofacHome.do"})
/*  32:    */  public String home(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  33:    */  {
/*  34: 34 */    com.soofound.admin.bean.UserDto user = super.getCurrentUser(request);
/*  35: 35 */    int wuc = this.sas.getWifiUserCount(user.getBranchId(), null);
/*  36: 36 */    model.addAttribute("userTotal", Integer.valueOf(wuc));
/*  37: 37 */    int wuc2 = this.sas.getWifiUserCount(user.getBranchId(), "1");
/*  38: 38 */    model.addAttribute("userOnline", Integer.valueOf(wuc2));
/*  39:    */    
/*  40: 40 */    int dt = this.hdao.getDeviceTotal(user.getBranchId(), null);
/*  41: 41 */    model.addAttribute("deviceTotal", Integer.valueOf(dt));
/*  42: 42 */    int dt2 = this.hdao.getDeviceTotal(user.getBranchId(), "1");
/*  43: 43 */    model.addAttribute("deviceOnline", Integer.valueOf(dt2));
/*  44:    */    
/*  45: 45 */    int bt = this.bdao.getBranchTotal(user.getBranchId());
/*  46: 46 */    model.addAttribute("branchTotal", Integer.valueOf(bt));
/*  47:    */    
/*  48: 48 */    return "/common/home.jsp";
/*  49:    */  }
/*  50:    */  
/*  51:    */  @org.springframework.web.bind.annotation.RequestMapping({"/report/getBrandPie.do"})
/*  52:    */  @ResponseBody
/*  53: 53 */  public java.util.Map<String, Object> getBrandPie(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { java.util.Map<String, String> paras = new HashMap();
/*  54: 54 */    paras.put("tag", getTimeTag(request));
/*  55: 55 */    paras.put("branchId", request.getParameter("branchId"));
/*  56: 56 */    BrandPieReporter rpt = new BrandPieReporter();
/*  57: 57 */    return bornData(rpt.doReport(paras));
/*  58:    */  }
/*  59:    */  
/*  60:    */  @org.springframework.web.bind.annotation.RequestMapping({"/home/getStayLongPie.do"})
/*  61:    */  @ResponseBody
/*  62: 62 */  public java.util.Map<String, Object> getStayLongs(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { java.util.Map<String, String> paras = new HashMap();
/*  63: 63 */    paras.put("tag", getTimeTag(request));
/*  64: 64 */    paras.put("branchId", request.getParameter("branchId"));
/*  65: 65 */    StayLongPieReporter rpt = new StayLongPieReporter();
/*  66: 66 */    return bornData(rpt.doReport(paras));
/*  67:    */  }
/*  68:    */  
/*  69:    */  @org.springframework.web.bind.annotation.RequestMapping({"/report/getEndUserCountTrend.do"})
/*  70:    */  @ResponseBody
/*  71: 71 */  public java.util.Map<String, Object> getEndUserCountTrend(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { java.util.Map<String, String> paras = new HashMap();
/*  72: 72 */    paras.put("branchId", request.getParameter("branchId"));
/*  73: 73 */    paras.put("tag", request.getParameter("tag"));
/*  74: 74 */    EndUserCountTrendReporter rpt = new EndUserCountTrendReporter();
/*  75: 75 */    return bornData(rpt.doReport(paras));
/*  76:    */  }
/*  77:    */  
/*  78:    */  @org.springframework.web.bind.annotation.RequestMapping({"/report/getAPTrafficTrend.do"})
/*  79:    */  @ResponseBody
/*  80: 80 */  public java.util.Map<String, Object> getAPTrafficTrend(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { java.util.Map<String, String> paras = new HashMap();
/*  81: 81 */    paras.put("branchId", request.getParameter("branchId"));
/*  82: 82 */    paras.put("tag", request.getParameter("tag"));
/*  83: 83 */    APTrafficTrendReporter rpt = new APTrafficTrendReporter();
/*  84: 84 */    return bornData(rpt.doReport(paras));
/*  85:    */  }
/*  86:    */  
/*  87:    */  @org.springframework.web.bind.annotation.RequestMapping({"/report/getAPLoadTopn.do"})
/*  88:    */  @ResponseBody
/*  89: 89 */  public java.util.Map<String, Object> getAPLoadTopn(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { java.util.Map<String, String> paras = new HashMap();
/*  90: 90 */    paras.put("branchId", request.getParameter("branchId"));
/*  91: 91 */    paras.put("flag", request.getParameter("flag"));
/*  92: 92 */    paras.put("tag", request.getParameter("tag"));
/*  93: 93 */    paras.put("topn", "10");
/*  94:    */    
/*  95: 95 */    List<SimpleReportBean> beans = new ArrayList();
/*  96: 96 */    APLoadTopNReporter rpt = new APLoadTopNReporter();
/*  97: 97 */    Object[][] objarr = rpt.doReport(paras);
/*  98: 98 */    if (objarr != null) {
/*  99: 99 */      for (Object[] item : objarr) {
/* 100:100 */        SimpleReportBean bean = new SimpleReportBean();
/* 101:101 */        bean.setId((String)item[0]);
/* 102:102 */        bean.setName((String)item[1]);
/* 103:103 */        bean.setValue(((Long)item[2]).longValue());
/* 104:104 */        beans.add(bean);
/* 105:    */      }
/* 106:    */    }
/* 107:107 */    return bornData(beans);
/* 108:    */  }
/* 109:    */  
/* 110:    */  @org.springframework.web.bind.annotation.RequestMapping({"/wifiant/gw_message.do"})
/* 111:    */  public String getGwMessage(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 112:112 */    String message = request.getParameter("message");
/* 113:113 */    if ("denied".equals(message)) {
/* 114:114 */      System.out.println(request.getRemoteAddr() + "--wifiant/gw_message--");
/* 115:115 */      model.addAttribute("error", "AP拒绝访问");
/* 116:116 */    } else { if ("logged-out".equals(message)) {
/* 117:117 */        return "redirect:http://www.163.com";
/* 118:    */      }
/* 119:119 */      model.addAttribute("error", "未知错误."); }
/* 120:120 */    return "/common/hint.jsp";
/* 121:    */  }
/* 122:    */  
/* 123:    */  private String getTimeTag(javax.servlet.http.HttpServletRequest request) {
/* 124:124 */    String tag = request.getParameter("tag");
/* 125:125 */    if (tag == null) tag = "today";
/* 126:126 */    if ("custom".equals(tag))
/* 127:127 */      tag = request.getParameter("startTime") + " 00:00:00#" + request.getParameter("endTime") + " 23:59:59";
/* 128:128 */    return tag;
/* 129:    */  }
/* 130:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.MsiteViewAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */