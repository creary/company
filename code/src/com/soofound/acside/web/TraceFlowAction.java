/*   1:    */package com.soofound.acside.web;
/*   2:    */
/*   3:    */import com.soofound.framework.util.CommonUtil;
/*   4:    */import com.soofound.framework.util.DateUtil;
/*   5:    */import com.soofound.framework.web.BaseAction;
/*   6:    */import com.soofound.portal.bean.SurfingSession;
/*   7:    */import com.soofound.portal.service.SurfingSessionService;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.List;
/*  10:    */import java.util.Map;
/*  11:    */import javax.servlet.http.HttpServletResponse;
/*  12:    */import org.springframework.beans.factory.annotation.Autowired;
/*  13:    */import org.springframework.stereotype.Controller;
/*  14:    */import org.springframework.ui.ModelMap;
/*  15:    */import org.springframework.util.StringUtils;
/*  16:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  17:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  18:    */
/*  19:    */@Controller
/*  20:    */public class TraceFlowAction extends BaseAction<TraceFlowService>
/*  21:    */{
/*  22:    */  private static final String EMPTY_TABLE = "trace_flow_empty";
/*  23:    */  @Autowired
/*  24:    */  private SurfingSessionService sss;
/*  25:    */  
/*  26:    */  @RequestMapping({"/detect/traceFlowListJsp.do"})
/*  27:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, ModelMap model)
/*  28:    */  {
/*  29: 29 */    StringBuilder pstr = new StringBuilder(100);
/*  30: 30 */    String id = request.getParameter("id");
/*  31: 31 */    SurfingSession dto = (SurfingSession)this.sss.findByID(id);
/*  32: 32 */    if (dto == null)
/*  33: 33 */      return "/acside/trace/queryTrace.jsp";
/*  34: 34 */    pstr.append("?mac=").append(dto.getMac());
/*  35: 35 */    pstr.append("&tableName=trace_flow_").append(dto.getApmac().replace(":", ""));
/*  36: 36 */    pstr.append("&startTime=").append(dto.getStartTime().substring(0, 19));
/*  37: 37 */    pstr.append("&endTime=");
/*  38: 38 */    if (CommonUtil.isEmpty(dto.getStopTime())) {
/*  39: 39 */      pstr.append(DateUtil.getCurrentDateTime());
/*  40:    */    } else
/*  41: 41 */      pstr.append(dto.getStopTime().substring(0, 19));
/*  42: 42 */    if (StringUtils.hasText(request.getParameter("url")))
/*  43: 43 */      pstr.append("&url=").append(request.getParameter("url"));
/*  44: 44 */    model.addAttribute("pstr", pstr.toString());
/*  45: 45 */    model.addAttribute("mac", dto.getMac());
/*  46: 46 */    return "/acside/trace/macTrace.jsp";
/*  47:    */  }
/*  48:    */  
/*  49:    */  @RequestMapping({"/detect/traceFlowList.do"})
/*  50:    */  @ResponseBody
/*  51: 51 */  public Map<String, Object> list(javax.servlet.http.HttpServletRequest request, ModelMap model) { Map<String, Object> results = new HashMap();
/*  52:    */    try {
/*  53: 53 */      int pp = getPerPageRowTotal(request);
/*  54: 54 */      int cp = getCurrentPage(request);
/*  55: 55 */      results.put("data", ((TraceFlowService)this.service).listByPage(pp, cp, getOptions(request)));
/*  56: 56 */      results.put("page", ((TraceFlowService)this.service).getPagination());
/*  57:    */    } catch (Exception e) {
/*  58: 58 */      e.printStackTrace();
/*  59:    */    }
/*  60: 60 */    return results;
/*  61:    */  }
/*  62:    */  
/*  63:    */  @RequestMapping({"/detect/traceFlowExport.do"})
/*  64:    */  public void export(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {
/*  65: 65 */    String fileName = ((TraceFlowService)this.service).doExport(getOptions(request));
/*  66: 66 */    super.downloadFile(response, fileName);
/*  67:    */  }
/*  68:    */  
/*  69:    */  private Map<String, String> getOptions(javax.servlet.http.HttpServletRequest request) {
/*  70: 70 */    Map<String, String> options = new HashMap();
/*  71: 71 */    String tableName = request.getParameter("tableName");
/*  72: 72 */    String mac = request.getParameter("mac");
/*  73: 73 */    String[] times = (String[])null;
/*  74: 74 */    if (tableName == null) {
/*  75: 75 */      String timeType = request.getParameter("timeType");
/*  76: 76 */      if ("custom".equals(timeType)) {
/*  77: 77 */        times = new String[] { request.getParameter("startTime"), request.getParameter("endTime") };
/*  78:    */      } else
/*  79: 79 */        times = getTimePoints(timeType);
/*  80: 80 */      List<String> tbls = ((TraceFlowService)this.service).getTraceTables(mac, times[0], times[1]);
/*  81: 81 */      if (CommonUtil.isEmpty(tbls)) {
/*  82: 82 */        tableName = "trace_flow_empty";
/*  83:    */      } else
/*  84: 84 */        tableName = (String)tbls.get(0);
/*  85: 85 */      options.put("startTime", times[0]);
/*  86: 86 */      options.put("endTime", times[1]);
/*  87:    */    } else {
/*  88: 88 */      if (!((TraceFlowService)this.service).hasTable(tableName))
/*  89: 89 */        tableName = "trace_flow_empty";
/*  90: 90 */      options.put("startTime", request.getParameter("startTime"));
/*  91: 91 */      options.put("endTime", request.getParameter("endTime"));
/*  92:    */    }
/*  93: 93 */    options.put("mac", mac);
/*  94: 94 */    options.put("tableName", tableName);
/*  95: 95 */    if (StringUtils.hasText(request.getParameter("url")))
/*  96: 96 */      options.put("url", request.getParameter("url"));
/*  97: 97 */    return options;
/*  98:    */  }
/*  99:    */  
/* 100:    */  private String[] getTimePoints(String timeType) {
/* 101:101 */    String[] times = new String[2];
/* 102:102 */    if ("yesterday".equals(timeType)) {
/* 103:103 */      times[0] = (DateUtil.getLastDay() + " 00:00:00");
/* 104:104 */      times[1] = (DateUtil.getLastDay() + " 23:59:59");
/* 105:105 */    } else if ("7day".equals(timeType)) {
/* 106:106 */      times[0] = (DateUtil.getDay(-7) + " 00:00:00");
/* 107:107 */      times[1] = (DateUtil.getCurrentDate() + " 23:59:59");
/* 108:    */    } else {
/* 109:109 */      times[0] = (DateUtil.getCurrentDate() + " 00:00:00");
/* 110:110 */      times[1] = (DateUtil.getCurrentDate() + " 23:59:59");
/* 111:    */    }
/* 112:112 */    return times;
/* 113:    */  }
/* 114:    */  
/* 116:    */  @Autowired
/* 117:    */  protected void setService(TraceFlowService service)
/* 118:    */  {
/* 119:119 */    this.service = service;
/* 120:    */  }
/* 121:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.web.TraceFlowAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */