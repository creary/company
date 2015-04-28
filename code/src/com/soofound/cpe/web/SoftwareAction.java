/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.soofound.admin.web.BranchService;
/*   4:    */import com.soofound.cpe.bean.ConfigParamBean;
/*   5:    */import com.soofound.cpe.util.CpeUtils;
/*   6:    */import com.soofound.framework.license.DESedeCoder;
/*   7:    */import com.soofound.framework.util.DateUtil;
/*   8:    */import java.io.File;
/*   9:    */import java.util.ArrayList;
/*  10:    */import org.springframework.beans.factory.annotation.Autowired;
/*  11:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  12:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  13:    */import org.springframework.stereotype.Controller;
/*  14:    */import org.springframework.util.StringUtils;
/*  15:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  16:    */
/*  17:    */@Controller
/*  18:    */public final class SoftwareAction extends com.soofound.framework.web.BaseAction<SoftwareService>
/*  19:    */{
/*  20:    */  @Autowired
/*  21:    */  private BranchService branchService;
/*  22:    */  @Autowired
/*  23:    */  @Qualifier("defaultJdbcTemplate")
/*  24:    */  private JdbcTemplate jdbcTemplate;
/*  25:    */  @Autowired
/*  26:    */  private com.soofound.cpe.util.SoofacACS sfacs;
/*  27:    */  @Autowired
/*  28:    */  private HostService hostService;
/*  29:    */  @Autowired
/*  30:    */  private PropertyService propService;
/*  31:    */  
/*  32:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/softListJsp.do"})
/*  33:    */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/*  34:    */  {
/*  35: 35 */    model.addAttribute("productModel", this.hostService.getHardwares());
/*  36: 36 */    if ("2".equals(request.getParameter("tag"))) {
/*  37: 37 */      com.soofound.admin.bean.UserDto user = super.getCurrentUser(request);
/*  38: 38 */      if (user.getBranchId().equals("0")) {
/*  39: 39 */        model.addAttribute("version", "");
/*  40:    */      } else
/*  41: 41 */        model.addAttribute("version", "nonAccess");
/*  42: 42 */      return "/cpe/device/configFileList.jsp";
/*  43:    */    }
/*  44: 44 */    return "/cpe/device/firmwareFileList.jsp";
/*  45:    */  }
/*  46:    */  
/*  47:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/softList.do"})
/*  48:    */  @ResponseBody
/*  49: 49 */  public java.util.Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.listByPage(request, model); }
/*  50:    */  
/*  51:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/softDelete.do"})
/*  52:    */  @ResponseBody
/*  53:    */  public java.util.Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  54: 54 */    return super.deleteByIDs(request, model);
/*  55:    */  }
/*  56:    */  
/*  57:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/softReadyAdd.do"})
/*  58:    */  public String readyAdd(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  59: 59 */    java.util.List<String> pms = this.hostService.getHardwares();
/*  60: 60 */    if (pms.isEmpty()) {
/*  61: 61 */      pms.add("RW2400NGHSC-A");
/*  62: 62 */      pms.add("RW2400NGHSC");
/*  63:    */    }
/*  64: 64 */    String selectedModel = request.getParameter("productModel");
/*  65: 65 */    if (selectedModel == null)
/*  66: 66 */      selectedModel = (String)pms.get(0);
/*  67: 67 */    model.addAttribute("productModel", pms);
/*  68: 68 */    model.addAttribute("selectedModel", selectedModel);
/*  69: 69 */    if ("2".equals(request.getParameter("tag"))) {
/*  70: 70 */      java.util.List<com.soofound.cpe.bean.PropertyBean> props = null;
/*  71: 71 */      com.soofound.admin.bean.UserDto user = super.getCurrentUser(request);
/*  72: 72 */      if (user.getBranchId().equals("0")) {
/*  73: 73 */        model.addAttribute("branchs", this.branchService.findByBranch(user.getBranchId()));
/*  74: 74 */        props = this.propService.findByConfigable(selectedModel, true);
/*  75:    */      } else {
/*  76: 76 */        props = this.propService.findByConfigable(selectedModel, false); }
/*  77: 77 */      model.addAttribute("props", props);
/*  78: 78 */      for (com.soofound.cpe.bean.PropertyBean prop : props) {
/*  79: 79 */        String boxName = "value_" + prop.getId();
/*  80: 80 */        String[] temps = prop.getName().split("\\.");
/*  81: 81 */        if (temps.length == 3)
/*  82:    */        {
/*  83: 83 */          String temp = this.sfacs.getCpePropertySelectBox(temps[2], "", boxName);
/*  84: 84 */          if (temp == null) {
/*  85: 85 */            prop.setBoxString(this.sfacs.getCpePropertyTextBox(boxName, "", prop.getTag()));
/*  86:    */          } else
/*  87: 87 */            prop.setBoxString(temp);
/*  88:    */        } }
/*  89: 89 */      return "/cpe/device/configFileAdd.jsp";
/*  90:    */    }
/*  91: 91 */    return "/cpe/device/firmwareFileAdd.jsp";
/*  92:    */  }
/*  93:    */  
/*  94:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/softReadyEdit.do"})
/*  95:    */  public String readyEdit(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/*  96: 96 */    String id = request.getParameter("id");
/*  97: 97 */    com.soofound.cpe.bean.SoftwareBean bean = (com.soofound.cpe.bean.SoftwareBean)((SoftwareService)this.service).findByID(id);
/*  98: 98 */    model.addAttribute("dto", bean);
/*  99: 99 */    java.util.List<String> pms = this.hostService.getHardwares();
/* 100:100 */    if (pms.isEmpty()) {
/* 101:101 */      pms.add("RW2400NGHSC-A");
/* 102:102 */      pms.add("RW2400NGHSC");
/* 103:    */    }
/* 104:104 */    String selectedModel = request.getParameter("productModel");
/* 105:105 */    if (selectedModel == null)
/* 106:106 */      selectedModel = bean.getProductModel();
/* 107:107 */    model.addAttribute("productModel", pms);
/* 108:108 */    model.addAttribute("selectedModel", selectedModel);
/* 109:109 */    if ("2".equals(request.getParameter("tag"))) {
/* 110:110 */      java.util.List<ConfigParamBean> cpbs = ((SoftwareService)this.service).getConfigParams(Integer.parseInt(id));
/* 111:111 */      java.util.List<com.soofound.cpe.bean.PropertyBean> props = null;
/* 112:112 */      com.soofound.admin.bean.UserDto user = super.getCurrentUser(request);
/* 113:113 */      if (user.getBranchId().equals("0")) {
/* 114:114 */        props = this.propService.findByConfigable("", true);
/* 115:    */      } else
/* 116:116 */        props = this.propService.findByConfigable("1,2,3,4", false);
/* 117:117 */      model.addAttribute("props", props);
/* 118:118 */      for (com.soofound.cpe.bean.PropertyBean prop : props) {
/* 119:119 */        prop.setChecked("");
/* 120:120 */        String boxName = "value_" + prop.getId();
/* 121:121 */        String[] temps = prop.getName().split("\\.");
/* 122:122 */        if (temps.length == 3)
/* 123:    */        {
/* 124:124 */          String value = "";
/* 125:125 */          for (ConfigParamBean cpb : cpbs) {
/* 126:126 */            if (cpb.getId() == prop.getId()) {
/* 127:127 */              prop.setChecked("checked");
/* 128:128 */              value = cpb.getValue();
/* 129:129 */              break;
/* 130:    */            }
/* 131:    */          }
/* 132:132 */          String temp = this.sfacs.getCpePropertySelectBox(temps[2], value, boxName);
/* 133:133 */          if (temp == null) {
/* 134:134 */            prop.setBoxString(this.sfacs.getCpePropertyTextBox(boxName, value, prop.getTag()));
/* 135:    */          } else
/* 136:136 */            prop.setBoxString(temp);
/* 137:    */        } }
/* 138:138 */      return "/cpe/device/configFileEdit.jsp";
/* 139:    */    }
/* 140:140 */    return "/cpe/device/firmwareFileEdit.jsp";
/* 141:    */  }
/* 142:    */  
/* 143:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/softUpdate.do"})
/* 144:    */  @ResponseBody
/* 145:    */  public java.util.Map<String, Object> softUpdate(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 146:146 */    try { com.soofound.cpe.bean.SoftwareBean bean = new com.soofound.cpe.bean.SoftwareBean();
/* 147:147 */      bean.setId(Integer.parseInt(request.getParameter("id")));
/* 148:148 */      bean.setTag(1);
/* 149:149 */      bean.setDescr(request.getParameter("descr"));
/* 150:150 */      bean.setProductModel(request.getParameter("productModel"));
/* 151:151 */      bean.setVersion(request.getParameter("version"));
/* 152:152 */      bean.setVersionCode(CpeUtils.getSoftVersionCode(bean.getVersion()));
/* 153:153 */      bean.setFileName(getFileName(request.getParameter("bin")));
/* 154:154 */      File mf = new File(com.soofound.cpe.util.SoofacACS.PATH_FULL_FIRMWARE + bean.getFileName());
/* 155:155 */      bean.setSize(mf.length());
/* 156:156 */      bean.setUploadTime(DateUtil.getCurrentDateTime());
/* 157:157 */      bean.setMd5(DESedeCoder.getFileMD5String(mf));
/* 158:158 */      ((SoftwareService)this.service).update(bean);
/* 159:    */      
/* 160:160 */      return super.getSucceedResult("更新成功");
/* 161:    */    } catch (Exception e) {
/* 162:162 */      e.printStackTrace(); }
/* 163:163 */    return super.getSucceedResult("更新失败");
/* 164:    */  }
/* 165:    */  
/* 166:    */  private String getFileName(String fullFileName)
/* 167:    */  {
/* 168:168 */    int loc = fullFileName.lastIndexOf("/") + 1;
/* 169:169 */    return fullFileName.substring(loc);
/* 170:    */  }
/* 171:    */  
/* 172:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/softSave.do"})
/* 173:    */  @ResponseBody
/* 174:    */  public java.util.Map<String, Object> softSave(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 175:175 */    try { com.soofound.cpe.bean.SoftwareBean bean = new com.soofound.cpe.bean.SoftwareBean();
/* 176:176 */      bean.setTag(1);
/* 177:177 */      bean.setFileName(getFileName(request.getParameter("bin")));
/* 178:178 */      File mf = new File(com.soofound.cpe.util.SoofacACS.PATH_FULL_FIRMWARE + bean.getFileName());
/* 179:179 */      bean.setSize(mf.length());
/* 180:180 */      bean.setUploadTime(DateUtil.getCurrentDateTime());
/* 181:181 */      bean.setMd5(DESedeCoder.getFileMD5String(mf));
/* 182:    */      
/* 183:183 */      java.util.Map<String, String> pfs = CpeUtils.parseFirmware(com.soofound.cpe.util.SoofacACS.PATH_FULL_FIRMWARE + bean.getFileName());
/* 184:184 */      bean.setProductModel((String)pfs.get("productModel"));
/* 185:185 */      bean.setVersion((String)pfs.get("version"));
/* 186:186 */      bean.setVersionCode(CpeUtils.getSoftVersionCode(bean.getVersion()));
/* 187:187 */      bean.setDescr((String)pfs.get("version") + "(" + (String)pfs.get("createTime") + ")");
/* 188:    */      
/* 189:189 */      ((SoftwareService)this.service).save(bean);
/* 190:190 */      logOperation(request, "增加固件:" + bean.getDescr());
/* 191:    */      
/* 192:192 */      return super.getSucceedResult("增加成功");
/* 193:    */    } catch (Exception e) {
/* 194:194 */      e.printStackTrace(); }
/* 195:195 */    return super.getSucceedResult("增加失败");
/* 196:    */  }
/* 197:    */  
/* 198:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/configSave.do"})
/* 199:    */  @ResponseBody
/* 200:    */  public java.util.Map<String, Object> configSave(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 201:201 */    com.soofound.admin.bean.UserDto user = getCurrentUser(request);
/* 202:    */    try {
/* 203:203 */      java.util.List<com.soofound.cpe.bean.PropertyBean> pbs = this.propService.findAll();
/* 204:204 */      com.soofound.cpe.bean.SoftwareBean bean = new com.soofound.cpe.bean.SoftwareBean();
/* 205:205 */      bean.setTag(2);
/* 206:206 */      bean.setDescr(request.getParameter("descr"));
/* 207:207 */      bean.setProductModel(request.getParameter("productModel"));
/* 208:208 */      if (request.getParameter("branchId") == null) {
/* 209:209 */        bean.setBranchId(user.getBranchId());
/* 210:    */      } else
/* 211:211 */        bean.setBranchId(request.getParameter("branchId"));
/* 212:212 */      bean.setUploadTime(DateUtil.getCurrentDateTime());
/* 213:213 */      String[] propIds = request.getParameterValues("props");
/* 214:214 */      java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = null;
/* 215:215 */      boolean access = false;
/* 216:216 */      props = new ArrayList();
/* 217:217 */      for (String propId : propIds) {
/* 218:218 */        String prefix = "value_" + propId;
/* 219:219 */        com.soofound.cpe.bean.HostPropertyBean prop = new com.soofound.cpe.bean.HostPropertyBean();
/* 220:220 */        prop.setId(Integer.parseInt(propId));
/* 221:221 */        String val = request.getParameter(prefix);
/* 222:222 */        String val2 = request.getParameter(prefix + "_checkbox");
/* 223:223 */        if ("1".equals(val2))
/* 224:224 */          val = request.getParameter(prefix + "_text");
/* 225:225 */        if (StringUtils.hasText(val)) {
/* 226:226 */          prop.setValue(val.trim());
/* 227:    */        } else
/* 228:228 */          prop.setValue("N/A");
/* 229:229 */        if (prop.getValue().indexOf(" ") > 0)
/* 230:230 */          return super.getFailedResult("错误:输入值有空格");
/* 231:231 */        String enName = getPropertyEnName(pbs, prop.getId());
/* 232:232 */        if ((CpeUtils.isThreshold(enName)) && (prop.getValue().startsWith("-")))
/* 233:233 */          return super.getFailedResult("错误:阀值不能是负数");
/* 234:234 */        if (enName.indexOf("ACS") >= 0)
/* 235:235 */          access = true;
/* 236:236 */        props.add(prop);
/* 237:    */      }
/* 238:238 */      if (access) {
/* 239:239 */        bean.setVersion("access");
/* 240:240 */      } else if (request.getParameter("version") == null) {
/* 241:241 */        bean.setVersion("0");
/* 242:    */      } else
/* 243:243 */        bean.setVersion(request.getParameter("version"));
/* 244:244 */      ((SoftwareService)this.service).save(bean);
/* 245:245 */      logOperation(request, "增加配置:" + bean.getDescr());
/* 246:    */      
/* 247:247 */      java.util.List<String> sqls = new ArrayList();
/* 248:248 */      for (com.soofound.cpe.bean.HostPropertyBean prop : props) {
/* 249:249 */        StringBuilder sqlText = new StringBuilder();
/* 250:250 */        sqlText.append("insert into cpe_configuration(cfg_id,attribute_id,attribute_value)values(");
/* 251:251 */        sqlText.append(bean.getId()).append(",'").append(prop.getId()).append("','").append(prop.getValue()).append("')");
/* 252:252 */        sqls.add(sqlText.toString());
/* 253:    */      }
/* 254:254 */      String[] sqlarrs = new String[sqls.size()];
/* 255:255 */      sqls.toArray(sqlarrs);
/* 256:256 */      this.jdbcTemplate.batchUpdate(sqlarrs);
/* 257:    */      
/* 258:258 */      return super.getSucceedResult("更新成功");
/* 259:    */    } catch (Exception e) {
/* 260:260 */      e.printStackTrace(); }
/* 261:261 */    return super.getFailedResult("更新失败");
/* 262:    */  }
/* 263:    */  
/* 264:    */  @org.springframework.web.bind.annotation.RequestMapping({"/cpe/configUpdate.do"})
/* 265:    */  @ResponseBody
/* 266:    */  public java.util.Map<String, Object> configUpdate(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) {
/* 267:267 */    String cfgId = request.getParameter("id");
/* 268:268 */    int id = Integer.parseInt(cfgId);
/* 269:    */    try {
/* 270:270 */      java.util.List<com.soofound.cpe.bean.PropertyBean> pbs = this.propService.findAll();
/* 271:271 */      com.soofound.cpe.bean.SoftwareBean bean = new com.soofound.cpe.bean.SoftwareBean();
/* 272:272 */      bean.setId(id);
/* 273:273 */      bean.setTag(2);
/* 274:274 */      bean.setDescr(request.getParameter("descr"));
/* 275:275 */      bean.setProductModel(request.getParameter("productModel"));
/* 276:276 */      bean.setBranchId(request.getParameter("branchId"));
/* 277:277 */      bean.setUploadTime(DateUtil.getCurrentDateTime());
/* 278:278 */      String[] propIds = request.getParameterValues("props");
/* 279:279 */      java.util.List<com.soofound.cpe.bean.HostPropertyBean> props = new ArrayList();
/* 280:280 */      boolean access = false;
/* 281:281 */      for (String propId : propIds) {
/* 282:282 */        String prefix = "value_" + propId;
/* 283:283 */        com.soofound.cpe.bean.HostPropertyBean prop = new com.soofound.cpe.bean.HostPropertyBean();
/* 284:284 */        prop.setId(Integer.parseInt(propId));
/* 285:285 */        String val = request.getParameter(prefix);
/* 286:286 */        String val2 = request.getParameter(prefix + "_checkbox");
/* 287:287 */        if ("1".equals(val2))
/* 288:288 */          val = request.getParameter(prefix + "_text");
/* 289:289 */        if (StringUtils.hasText(val)) {
/* 290:290 */          prop.setValue(val.trim());
/* 291:    */        } else
/* 292:292 */          prop.setValue("N/A");
/* 293:293 */        if (prop.getValue().indexOf(" ") > 0)
/* 294:294 */          return super.getFailedResult("错误:输入值有空格");
/* 295:295 */        String enName = getPropertyEnName(pbs, prop.getId());
/* 296:296 */        if ((CpeUtils.isThreshold(enName)) && (prop.getValue().startsWith("-")))
/* 297:297 */          return super.getFailedResult("错误:阀值不能是负数");
/* 298:298 */        props.add(prop);
/* 299:299 */        if (enName.indexOf("ACS") >= 0)
/* 300:300 */          access = true;
/* 301:    */      }
/* 302:302 */      if (access) {
/* 303:303 */        bean.setVersion("access");
/* 304:304 */      } else if (request.getParameter("version") == null) {
/* 305:305 */        bean.setVersion("0");
/* 306:    */      } else
/* 307:307 */        bean.setVersion(request.getParameter("version"));
/* 308:308 */      ((SoftwareService)this.service).update(bean);
/* 309:309 */      java.util.List<String> sqls = new ArrayList();
/* 310:310 */      sqls.add("delete from cpe_configuration where cfg_id=" + cfgId);
/* 311:311 */      for (com.soofound.cpe.bean.HostPropertyBean prop : props) {
/* 312:312 */        StringBuilder sqlText = new StringBuilder();
/* 313:313 */        sqlText.append("insert into cpe_configuration(cfg_id,attribute_id,attribute_value)values(");
/* 314:314 */        sqlText.append(bean.getId()).append(",'").append(prop.getId()).append("','").append(prop.getValue()).append("')");
/* 315:315 */        sqls.add(sqlText.toString());
/* 316:    */      }
/* 317:317 */      String[] sqlarrs = new String[sqls.size()];
/* 318:318 */      sqls.toArray(sqlarrs);
/* 319:319 */      this.jdbcTemplate.batchUpdate(sqlarrs);
/* 320:    */      
/* 321:321 */      return super.getSucceedResult("更新成功");
/* 322:    */    } catch (Exception e) {
/* 323:323 */      e.printStackTrace(); }
/* 324:324 */    return super.getFailedResult("更新失败");
/* 325:    */  }
/* 326:    */  
/* 327:    */  private String getPropertyEnName(java.util.List<com.soofound.cpe.bean.PropertyBean> pbs, int pid)
/* 328:    */  {
/* 329:329 */    for (com.soofound.cpe.bean.PropertyBean pb : pbs) {
/* 330:330 */      if (pb.getId() == pid)
/* 331:331 */        return pb.getEnName();
/* 332:    */    }
/* 333:333 */    return "";
/* 334:    */  }
/* 335:    */  
/* 346:    */  @Autowired
/* 347:    */  protected void setService(SoftwareService service)
/* 348:    */  {
/* 349:349 */    this.service = service;
/* 350:    */  }
/* 351:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.SoftwareAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */