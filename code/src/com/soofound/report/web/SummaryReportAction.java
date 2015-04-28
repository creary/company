/*  1:   */package com.soofound.report.web;
/*  2:   */
/*  3:   */import com.soofound.framework.web.GenericAction;
/*  4:   */import com.soofound.report.concrete.APPieReporter;
/*  5:   */import com.soofound.report.concrete.EndUserBarReporter;
/*  6:   */import com.soofound.report.concrete.TrafficBarReporter;
/*  7:   */import com.soofound.report.concrete.VisitPieReporter;
/*  8:   */import com.soofound.report.concrete.WifiUsedBarReporter;
/*  9:   */import java.util.HashMap;
/* 10:   */import java.util.Map;
/* 11:   */import javax.servlet.http.HttpServletRequest;
/* 12:   */import org.springframework.stereotype.Controller;
/* 13:   */import org.springframework.ui.ModelMap;
/* 14:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 15:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 16:   */
/* 17:   */@Controller
/* 18:   */public class SummaryReportAction extends GenericAction
/* 19:   */{
/* 20:   */  @RequestMapping({"/report/getAPPie.do"})
/* 21:   */  @ResponseBody
/* 22:   */  public Map<String, Object> getAPPie(HttpServletRequest request, ModelMap model)
/* 23:   */  {
/* 24:24 */    Map<String, String> paras = new HashMap();
/* 25:25 */    paras.put("tag", getTimeTag(request));
/* 26:26 */    paras.put("flag", request.getParameter("flag"));
/* 27:27 */    paras.put("branchId", request.getParameter("branchId"));
/* 28:28 */    APPieReporter rpt = new APPieReporter();
/* 29:29 */    return bornData(rpt.doReport(paras));
/* 30:   */  }
/* 31:   */  
/* 32:   */  @RequestMapping({"/report/getTrafficBar.do"})
/* 33:   */  @ResponseBody
/* 34:34 */  public Map<String, Object> getTrafficBar(HttpServletRequest request, ModelMap model) { Map<String, String> paras = new HashMap();
/* 35:35 */    paras.put("tag", getTimeTag(request));
/* 36:36 */    paras.put("flag", request.getParameter("flag"));
/* 37:37 */    paras.put("branchId", request.getParameter("branchId"));
/* 38:38 */    TrafficBarReporter rpt = new TrafficBarReporter();
/* 39:39 */    return bornData(rpt.doReport(paras));
/* 40:   */  }
/* 41:   */  
/* 42:   */  @RequestMapping({"/report/getWifiUsedBar.do"})
/* 43:   */  @ResponseBody
/* 44:44 */  public Map<String, Object> getWifiUsedBar(HttpServletRequest request, ModelMap model) { Map<String, String> paras = new HashMap();
/* 45:45 */    paras.put("tag", getTimeTag(request));
/* 46:46 */    paras.put("flag", request.getParameter("flag"));
/* 47:47 */    paras.put("branchId", request.getParameter("branchId"));
/* 48:48 */    paras.put("topn", "10");
/* 49:49 */    WifiUsedBarReporter rpt = new WifiUsedBarReporter();
/* 50:50 */    return bornData(rpt.doReport(paras));
/* 51:   */  }
/* 52:   */  
/* 53:   */  @RequestMapping({"/report/getVisitPie.do"})
/* 54:   */  @ResponseBody
/* 55:55 */  public Map<String, Object> getVisitPie(HttpServletRequest request, ModelMap model) { Map<String, String> paras = new HashMap();
/* 56:56 */    paras.put("tag", getTimeTag(request));
/* 57:57 */    paras.put("flag", request.getParameter("flag"));
/* 58:58 */    VisitPieReporter rpt = new VisitPieReporter();
/* 59:59 */    return bornData(rpt.doReport(paras));
/* 60:   */  }
/* 61:   */  
/* 62:   */  @RequestMapping({"/report/getEndUserBar.do"})
/* 63:   */  @ResponseBody
/* 64:64 */  public Map<String, Object> getEndUserBar(HttpServletRequest request, ModelMap model) { Map<String, String> paras = new HashMap();
/* 65:65 */    paras.put("tag", getTimeTag(request));
/* 66:66 */    paras.put("flag", request.getParameter("flag"));
/* 67:67 */    paras.put("branchId", request.getParameter("branchId"));
/* 68:68 */    EndUserBarReporter rpt = new EndUserBarReporter();
/* 69:69 */    return bornData(rpt.doReport(paras));
/* 70:   */  }
/* 71:   */  
/* 72:   */  private String getTimeTag(HttpServletRequest request) {
/* 73:73 */    String tag = request.getParameter("tag");
/* 74:74 */    if (tag == null) tag = "today";
/* 75:75 */    if ("custom".equals(tag))
/* 76:76 */      tag = request.getParameter("startTime") + " 00:00:00#" + request.getParameter("endTime") + " 23:59:59";
/* 77:77 */    return tag;
/* 78:   */  }
/* 79:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.report.web.SummaryReportAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */