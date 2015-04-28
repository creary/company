/*   1:    */package com.soofound.acside.web;
/*   2:    */
/*   3:    */import com.soofound.acside.bean.DetectFlowDto;
/*   4:    */import com.soofound.cpe.dao.RichHostDao;
/*   5:    */import com.soofound.framework.jdbc.Pagination;
/*   6:    */import com.soofound.framework.web.BaseAction;
/*   7:    */import com.soofound.portal.util.OuiFactory;
/*   8:    */import com.soofound.report.concrete.DetectBrandPieReporter;
/*   9:    */import com.soofound.report.concrete.DetectTrendReporter;
/*  10:    */import java.util.ArrayList;
/*  11:    */import java.util.HashMap;
/*  12:    */import java.util.List;
/*  13:    */import org.apache.commons.beanutils.BasicDynaBean;
/*  14:    */import org.springframework.beans.factory.annotation.Autowired;
/*  15:    */import org.springframework.stereotype.Controller;
/*  16:    */import org.springframework.ui.ModelMap;
/*  17:    */import org.springframework.util.StringUtils;
/*  18:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  19:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  20:    */
/*  21:    */@Controller
/*  22:    */public class DetectFlowAction extends BaseAction<DetectFlowService>
/*  23:    */{
/*  24:    */  @Autowired
/*  25:    */  private RichHostDao rdao;
/*  26:    */  @Autowired
/*  27:    */  private OuiFactory ouiFac;
/*  28:    */  
/*  29:    */  @RequestMapping({"/detect/getFlowSummaryTotal.do"})
/*  30:    */  @ResponseBody
/*  31:    */  public java.util.Map<String, Object> getFlowSummaryTotal(javax.servlet.http.HttpServletRequest request, ModelMap model)
/*  32:    */  {
/*  33: 33 */    String tag = getTimeTag(request);
/*  34: 34 */    String apmacs = getApmacs(request);
/*  35: 35 */    java.util.Map<String, Object> data = new HashMap();
/*  36: 36 */    data.put("detectTotal", Integer.valueOf(((DetectFlowService)this.service).getDetectTotal(apmacs, "detect", tag)));
/*  37: 37 */    data.put("enterTotal", Integer.valueOf(((DetectFlowService)this.service).getDetectTotal(apmacs, "enter", tag)));
/*  38: 38 */    data.put("authTotal", Integer.valueOf(((DetectFlowService)this.service).getDetectTotal(apmacs, "auth", tag)));
/*  39:    */    
/*  40: 40 */    java.util.Map<String, Object> result = new HashMap();
/*  41: 41 */    result.put("status", Integer.valueOf(1));
/*  42: 42 */    result.put("data", data);
/*  43:    */    
/*  44: 44 */    return result;
/*  45:    */  }
/*  46:    */  
/*  47:    */  @RequestMapping({"/detect/getDetectFlowTrend.do"})
/*  48:    */  @ResponseBody
/*  49:    */  public java.util.Map<String, Object> getDetectFlowTrend(javax.servlet.http.HttpServletRequest request, ModelMap model)
/*  50:    */  {
/*  51: 51 */    java.util.Map<String, Object> options = new HashMap();
/*  52: 52 */    options.put("apmacs", getApmacs(request));
/*  53: 53 */    options.put("tag", getTimeTag(request));
/*  54:    */    
/*  55: 55 */    DetectTrendReporter rpt = new DetectTrendReporter();
/*  56: 56 */    Object[][][] objarr = rpt.doReport(options);
/*  57: 57 */    java.util.Map<String, Object> result = new HashMap();
/*  58: 58 */    result.put("status", Integer.valueOf(1));
/*  59: 59 */    if (request.getParameter("table") == null) {
/*  60: 60 */      result.put("data", objarr);
/*  61: 61 */    } else if (objarr != null) {
/*  62: 62 */      List<HashMap> list = new ArrayList();
/*  63: 63 */      for (int j = 0; j < objarr[0].length; j++) {
/*  64: 64 */        HashMap row = new HashMap();
/*  65: 65 */        row.put("index", Integer.valueOf(j + 1));
/*  66: 66 */        if (((String)objarr[0][j][0]).length() == 2) {
/*  67: 67 */          row.put("time", objarr[0][j][0] + ":00");
/*  68:    */        } else
/*  69: 69 */          row.put("time", objarr[0][j][0]);
/*  70: 70 */        row.put("detectTotal", objarr[0][j][1]);
/*  71: 71 */        row.put("enterTotal", objarr[1][j][1]);
/*  72: 72 */        if (((Long)objarr[0][j][1]).longValue() > 0L) {
/*  73: 73 */          row.put("enterRatio", ((Long)objarr[1][j][1]).longValue() * 100L / ((Long)objarr[0][j][1]).longValue() + "%");
/*  74:    */        } else
/*  75: 75 */          row.put("enterRatio", "0%");
/*  76: 76 */        row.put("authTotal", objarr[2][j][1]);
/*  77: 77 */        if (((Long)objarr[1][j][1]).longValue() > 0L) {
/*  78: 78 */          row.put("authRatio", ((Long)objarr[2][j][1]).longValue() * 100L / ((Long)objarr[1][j][1]).longValue() + "%");
/*  79:    */        } else
/*  80: 80 */          row.put("authRatio", "0%");
/*  81: 81 */        list.add(row);
/*  82:    */      }
/*  83: 83 */      result.put("data", list);
/*  84:    */    }
/*  85: 85 */    return result;
/*  86:    */  }
/*  87:    */  
/*  88:    */  @RequestMapping({"/detect/getFeatureSummary.do"})
/*  89:    */  @ResponseBody
/*  90:    */  public java.util.Map<String, Object> getFeatureSummary(javax.servlet.http.HttpServletRequest request, ModelMap model)
/*  91:    */  {
/*  92: 92 */    String branchId = request.getParameter("branchId");
/*  93: 93 */    String tag = getTimeTag(request);
/*  94: 94 */    String apmacs = ((DetectFlowService)this.service).getApmacs(branchId);
/*  95: 95 */    return ((DetectFlowService)this.service).getFeatureSummary(apmacs, tag);
/*  96:    */  }
/*  97:    */  
/*  98:    */  @RequestMapping({"/detect/getHotShopByNewCustomer.do"})
/*  99:    */  @ResponseBody
/* 100:    */  public java.util.Map<String, Object> getHotShopByNewCustomer(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 101:    */  {
/* 102:102 */    String tag = getTimeTag(request);
/* 103:103 */    String apmacs = ((DetectFlowService)this.service).getApmacs(request.getParameter("branchId"));
/* 104:104 */    java.util.Map<String, Object> map = new HashMap();
/* 105:105 */    map.put("status", Integer.valueOf(1));
/* 106:106 */    List<BasicDynaBean> bdbs = ((DetectFlowService)this.service).getHotShop(apmacs, tag, "new");
/* 107:107 */    if (bdbs.size() > 0) {
/* 108:108 */      Object[][] items = new Object[bdbs.size()][2];
/* 109:109 */      for (int i = 0; i < bdbs.size(); i++) {
/* 110:110 */        BasicDynaBean bdb = (BasicDynaBean)bdbs.get(i);
/* 111:111 */        items[i][0] = bdb.get("entity");
/* 112:112 */        items[i][1] = bdb.get("value");
/* 113:    */      }
/* 114:114 */      map.put("data", items);
/* 115:    */    }
/* 116:116 */    return map;
/* 117:    */  }
/* 118:    */  
/* 119:    */  @RequestMapping({"/detect/getHotShopByOldCustomer.do"})
/* 120:    */  @ResponseBody
/* 121:    */  public java.util.Map<String, Object> getHotShopByOldCustomer(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 122:    */  {
/* 123:123 */    String tag = getTimeTag(request);
/* 124:124 */    String apmacs = ((DetectFlowService)this.service).getApmacs(request.getParameter("branchId"));
/* 125:125 */    List<BasicDynaBean> bdbs = ((DetectFlowService)this.service).getHotShop(apmacs, tag, "old");
/* 126:126 */    java.util.Map<String, Object> map = new HashMap();
/* 127:127 */    map.put("status", Integer.valueOf(1));
/* 128:128 */    if (bdbs.size() > 0) {
/* 129:129 */      Object[][] items = new Object[bdbs.size()][2];
/* 130:130 */      for (int i = 0; i < bdbs.size(); i++) {
/* 131:131 */        BasicDynaBean bdb = (BasicDynaBean)bdbs.get(i);
/* 132:132 */        items[i][0] = bdb.get("entity");
/* 133:133 */        items[i][1] = bdb.get("value");
/* 134:    */      }
/* 135:135 */      map.put("data", items);
/* 136:    */    }
/* 137:137 */    return map;
/* 138:    */  }
/* 139:    */  
/* 140:    */  @RequestMapping({"/detect/getVisitSummary.do"})
/* 141:    */  @ResponseBody
/* 142:    */  public java.util.Map<String, Object> getVisitSummary(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 143:    */  {
/* 144:144 */    String branchId = request.getParameter("branchId");
/* 145:145 */    String tag = getTimeTag(request);
/* 146:146 */    String apmacs = ((DetectFlowService)this.service).getApmacs(branchId);
/* 147:147 */    return ((DetectFlowService)this.service).getVisitSummary(apmacs, tag);
/* 148:    */  }
/* 149:    */  
/* 150:    */  @RequestMapping({"/detect/getHotShopByTotal.do"})
/* 151:    */  @ResponseBody
/* 152:    */  public java.util.Map<String, Object> getHotShopByTotal(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 153:    */  {
/* 154:154 */    String tag = getTimeTag(request);
/* 155:155 */    String apmacs = ((DetectFlowService)this.service).getApmacs(request.getParameter("branchId"));
/* 156:156 */    java.util.Map<String, Object> map = new HashMap();
/* 157:157 */    map.put("status", Integer.valueOf(1));
/* 158:158 */    List<BasicDynaBean> bdbs = ((DetectFlowService)this.service).getHotShop(apmacs, tag, "enter");
/* 159:159 */    if (bdbs.size() > 0) {
/* 160:160 */      Object[][] items = new Object[bdbs.size()][2];
/* 161:161 */      for (int i = 0; i < bdbs.size(); i++) {
/* 162:162 */        BasicDynaBean bdb = (BasicDynaBean)bdbs.get(i);
/* 163:163 */        items[i][0] = bdb.get("entity");
/* 164:164 */        items[i][1] = bdb.get("value");
/* 165:    */      }
/* 166:166 */      map.put("data", items);
/* 167:    */    }
/* 168:168 */    return map;
/* 169:    */  }
/* 170:    */  
/* 171:    */  @RequestMapping({"/detect/getHotShopByStayLong.do"})
/* 172:    */  @ResponseBody
/* 173:    */  public java.util.Map<String, Object> getHotShopByStayLong(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 174:    */  {
/* 175:175 */    String tag = getTimeTag(request);
/* 176:176 */    String apmacs = ((DetectFlowService)this.service).getApmacs(request.getParameter("branchId"));
/* 177:177 */    java.util.Map<String, Object> map = new HashMap();
/* 178:178 */    map.put("status", Integer.valueOf(1));
/* 179:179 */    List<BasicDynaBean> bdbs = ((DetectFlowService)this.service).getHotShopByStayLong(apmacs, tag);
/* 180:180 */    if (bdbs.size() > 0) {
/* 181:181 */      Object[][] items = new Object[bdbs.size()][2];
/* 182:182 */      for (int i = 0; i < bdbs.size(); i++) {
/* 183:183 */        BasicDynaBean bdb = (BasicDynaBean)bdbs.get(i);
/* 184:184 */        items[i][0] = bdb.get("entity");
/* 185:185 */        items[i][1] = bdb.get("value");
/* 186:    */      }
/* 187:187 */      map.put("data", items);
/* 188:    */    }
/* 189:189 */    return map;
/* 190:    */  }
/* 191:    */  
/* 192:    */  @RequestMapping({"/detect/getBrandSummary.do"})
/* 193:    */  @ResponseBody
/* 194:    */  public java.util.Map<String, Object> getBrandSummary(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 195:    */  {
/* 196:196 */    String apmacs = ((DetectFlowService)this.service).getApmacs(request.getParameter("branchId"));
/* 197:197 */    java.util.Map<String, String> paras = new HashMap();
/* 198:198 */    paras.put("apmacs", apmacs);
/* 199:199 */    paras.put("tag", getTimeTag(request));
/* 200:200 */    paras.put("flag", request.getParameter("flag"));
/* 201:201 */    DetectBrandPieReporter rpt = new DetectBrandPieReporter();
/* 202:202 */    return bornData(rpt.doReport(paras));
/* 203:    */  }
/* 204:    */  
/* 205:    */  @RequestMapping({"/detect/getCustomerPath.do"})
/* 206:    */  @ResponseBody
/* 207:    */  public java.util.Map<String, Object> getCustomerPath(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 208:    */  {
/* 209:209 */    String mac = request.getParameter("mac");
/* 210:210 */    String tag = request.getParameter("timeType");
/* 211:211 */    String branchId = request.getParameter("branchId");
/* 212:212 */    if ("custom".equals(tag))
/* 213:213 */      tag = request.getParameter("startTime") + "#" + request.getParameter("endTime");
/* 214:214 */    java.util.Map<String, Object> results = new HashMap();
/* 215:215 */    List<DetectFlowDto> dfds = ((DetectFlowService)this.service).getCustomerPath(mac, tag, branchId);
/* 216:216 */    Pagination page = new Pagination(100, 1, dfds.size());
/* 217:217 */    results.put("data", dfds);
/* 218:218 */    results.put("page", page);
/* 219:219 */    return results;
/* 220:    */  }
/* 221:    */  
/* 223:    */  @RequestMapping({"/detect/detectFlowListJsp.do"})
/* 224:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 225:    */  {
/* 226:226 */    return "/acside/detect/flowList.jsp";
/* 227:    */  }
/* 228:    */  
/* 229:    */  @RequestMapping({"/detect/detectFlowList.do"})
/* 230:    */  @ResponseBody
/* 231:    */  public java.util.Map<String, Object> list(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 232:    */  {
/* 233:233 */    java.util.Map<String, Object> results = new HashMap();
/* 234:234 */    int pp = getPerPageRowTotal(request);
/* 235:235 */    int cp = getCurrentPage(request);
/* 236:236 */    List<DetectFlowDto> dtos = ((DetectFlowService)this.service).listByPage(pp, cp, getParas(request));
/* 237:237 */    for (DetectFlowDto dto : dtos) {
/* 238:238 */      dto.setBrand(this.ouiFac.getBrand(dto.getMac()));
/* 239:    */    }
/* 240:240 */    results.put("data", dtos);
/* 241:241 */    results.put("page", ((DetectFlowService)this.service).getPagination());
/* 242:242 */    return results;
/* 243:    */  }
/* 244:    */  
/* 245:    */  private String getApmacs(javax.servlet.http.HttpServletRequest request) {
/* 246:246 */    String apmacs = null;
/* 247:247 */    String branchId = request.getParameter("branchId");
/* 248:248 */    String groupId = request.getParameter("groupId");
/* 249:249 */    String mac = request.getParameter("mac");
/* 250:250 */    if (StringUtils.hasText(mac)) {
/* 251:251 */      if ("all".equals(mac)) {
/* 252:252 */        apmacs = null;
/* 253:    */      } else
/* 254:254 */        apmacs = "'" + mac + "'";
/* 255:255 */    } else if (StringUtils.hasText(groupId)) {
/* 256:256 */      apmacs = this.rdao.getApmacsByGroup(groupId);
/* 257:257 */    } else if (StringUtils.hasText(branchId))
/* 258:258 */      apmacs = this.rdao.getApmacsByBranch(branchId);
/* 259:259 */    return apmacs;
/* 260:    */  }
/* 261:    */  
/* 262:    */  private String getTimeTag(javax.servlet.http.HttpServletRequest request) {
/* 263:263 */    String tag = request.getParameter("tag");
/* 264:264 */    if (tag == null) tag = "today";
/* 265:265 */    if ("custom".equals(tag))
/* 266:266 */      tag = request.getParameter("startTime") + " 00:00:00#" + request.getParameter("endTime") + " 23:59:59";
/* 267:267 */    return tag;
/* 268:    */  }
/* 269:    */  
/* 273:    */  @Autowired
/* 274:    */  protected void setService(DetectFlowService service)
/* 275:    */  {
/* 276:276 */    this.service = service;
/* 277:    */  }
/* 278:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.web.DetectFlowAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */