/*   1:    */package com.soofound.acside.web;
/*   2:    */
/*   3:    */import com.soofound.acside.bean.DetectFlowDao;
/*   4:    */import com.soofound.acside.bean.DetectFlowDto;
/*   5:    */import com.soofound.acside.bean.FeatureSummaryBean;
/*   6:    */import com.soofound.acside.bean.TraceFlowDao;
/*   7:    */import com.soofound.cpe.bean.HostBean;
/*   8:    */import com.soofound.cpe.dao.HostDao;
/*   9:    */import com.soofound.framework.util.CommonUtil;
/*  10:    */import com.soofound.framework.util.DateUtil;
/*  11:    */import com.soofound.framework.web.BaseService;
/*  12:    */import java.sql.ResultSet;
/*  13:    */import java.sql.SQLException;
/*  14:    */import java.util.ArrayList;
/*  15:    */import java.util.HashMap;
/*  16:    */import java.util.List;
/*  17:    */import java.util.Map;
/*  18:    */import org.apache.commons.beanutils.BasicDynaBean;
/*  19:    */import org.apache.commons.beanutils.BasicDynaClass;
/*  20:    */import org.apache.commons.beanutils.DynaProperty;
/*  21:    */import org.apache.log4j.Logger;
/*  22:    */import org.springframework.beans.factory.annotation.Autowired;
/*  23:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  24:    */import org.springframework.dao.DataAccessException;
/*  25:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  26:    */import org.springframework.jdbc.core.ResultSetExtractor;
/*  27:    */import org.springframework.stereotype.Service;
/*  28:    */import org.springframework.util.StringUtils;
/*  29:    */
/*  31:    */@Service
/*  32:    */public final class DetectFlowService
/*  33:    */  extends BaseService<DetectFlowDao>
/*  34:    */{
/*  35: 35 */  protected final Logger loger = Logger.getLogger(DetectFlowService.class);
/*  36:    */  private static final String EMPTY_TABLE = "select a.*,b.alias from detect_flow_empty a left join ap_custom b on a.ap_mac=b.ap_mac";
/*  37: 37 */  private static final String[] visitHeads = { "1次", "2次", "3次", "4次", "5次", "6-10次", "10-15次", "15次以上" };
/*  38:    */  private static final String SQL_STAY_LONG = "SELECT a.mac,a.ap_mac,a.start_time,a.end_time,TIMESTAMPDIFF(SECOND,a.start_time,a.end_time) AS diffsec,b.alias FROM detect_stay_long a LEFT JOIN ap_custom b ON a.ap_mac=b.ap_mac";
/*  39:    */  @Autowired
/*  40:    */  @Qualifier("acsideJdbcTemplate")
/*  41:    */  private JdbcTemplate acsideJdbc;
/*  42:    */  
/*  43:    */  public List<DetectFlowDto> listByPage(int perPage, int currentPage, Map<String, String> params) {
/*  44: 44 */    return ((DetectFlowDao)this.dao).listByPage(perPage, currentPage, getQuerySQL(params));
/*  45:    */  }
/*  46:    */  
/*  47:    */  private String getQuerySQL(Map<String, String> params) {
/*  48: 48 */    String branchId = (String)params.get("branchId");
/*  49: 49 */    String alias = (String)params.get("alias");
/*  50: 50 */    List<HostBean> hosts = null;
/*  51: 51 */    if (StringUtils.hasText(alias)) {
/*  52: 52 */      HostBean host = this.hdao.findByName(branchId, alias);
/*  53: 53 */      if (host != null) {
/*  54: 54 */        hosts = new ArrayList();
/*  55: 55 */        hosts.add(host);
/*  56:    */      }
/*  57:    */    } else {
/*  58: 58 */      hosts = this.hdao.findByBranch(branchId, null);
/*  59:    */    }
/*  60: 60 */    StringBuilder fullSql = new StringBuilder(200);
/*  61: 61 */    if (CommonUtil.isEmpty(hosts)) {
/*  62: 62 */      fullSql.append("select a.*,b.alias from detect_flow_empty a left join ap_custom b on a.ap_mac=b.ap_mac");
/*  63:    */    } else {
/*  64: 64 */      StringBuilder sqlText = new StringBuilder(200);
/*  65: 65 */      for (HostBean host : hosts) {
/*  66: 66 */        String tbl = "detect_flow_" + host.getSerialNumber().replace(":", "");
/*  67: 67 */        if (this.tfdao.hasTable(tbl))
/*  68:    */        {
/*  69: 69 */          sqlText.append(" union (select '").append(host.getSerialNumber()).append("' AS ap_mac,mac,snr,log_time from ").append(tbl);
/*  70: 70 */          sqlText.append(" where snr < -1");
/*  71: 71 */          if (StringUtils.hasText((String)params.get("startTime"))) {
/*  72: 72 */            sqlText.append(" and log_time >= '").append((String)params.get("startTime"));
/*  73: 73 */            sqlText.append("' and log_time <= '").append((String)params.get("endTime")).append("'");
/*  74:    */          }
/*  75: 75 */          if (StringUtils.hasText((String)params.get("mac"))) {
/*  76: 76 */            sqlText.append(" and mac like '%").append((String)params.get("mac")).append("%'");
/*  77:    */          }
/*  78: 78 */          sqlText.append(" order by log_time desc limit 10000)");
/*  79:    */        } }
/*  80: 80 */      if (sqlText.length() == 0) {
/*  81: 81 */        fullSql.append("select a.*,b.alias from detect_flow_empty a left join ap_custom b on a.ap_mac=b.ap_mac");
/*  82:    */      } else
/*  83: 83 */        fullSql.append("select a.*,b.alias from (").append(sqlText.substring(6)).append(") a left join ap_custom b on a.ap_mac=b.ap_mac ORDER BY log_time DESC");
/*  84:    */    }
/*  85: 85 */    this.loger.info("-----listByPage-------\n" + fullSql.toString());
/*  86: 86 */    return fullSql.toString();
/*  87:    */  }
/*  88:    */  
/*  89:    */  public String getApmacs(String branchId) {
/*  90: 90 */    List<HostBean> hosts = this.hdao.findByBranch(branchId, null);
/*  91: 91 */    if (!hosts.isEmpty()) {
/*  92: 92 */      StringBuilder apmacs = new StringBuilder(100);
/*  93: 93 */      for (HostBean host : hosts) {
/*  94: 94 */        apmacs.append(",'").append(host.getSerialNumber()).append("'");
/*  95:    */      }
/*  96: 96 */      return apmacs.substring(1);
/*  97:    */    }
/*  98: 98 */    return "''";
/*  99:    */  }
/* 100:    */  
/* 102:    */  public int getDetectTotal(String apmacs, String flag, String tag)
/* 103:    */  {
/* 104:104 */    StringBuilder sqlText = new StringBuilder(200);
/* 105:105 */    sqlText.append("SELECT COUNT(DISTINCT mac) FROM detect_ap_mac WHERE ").append(DateUtil.getTimeSQL(tag)).append(" AND ap_mac in (");
/* 106:106 */    sqlText.append(apmacs).append(")").append(getSQLByFlag(flag));
/* 107:    */    try
/* 108:    */    {
/* 109:109 */      return ((Integer)this.acsideJdbc.queryForObject(sqlText.toString(), Integer.class)).intValue();
/* 110:    */    }
/* 111:    */    catch (Exception ex) {}
/* 112:112 */    return 0;
/* 113:    */  }
/* 114:    */  
/* 117:    */  public Map<String, Object> getFeatureSummary(String apmacs, String tag)
/* 118:    */  {
/* 119:119 */    FeatureSummaryBean fsb1 = new FeatureSummaryBean();
/* 120:120 */    FeatureSummaryBean fsb2 = new FeatureSummaryBean();
/* 121:121 */    fsb1.setEnterTotal(getDetectTotal(apmacs, "old", tag));
/* 122:122 */    fsb2.setEnterTotal(getDetectTotal(apmacs, "new", tag));
/* 123:123 */    if (fsb1.getEnterTotal() + fsb2.getEnterTotal() > 0) {
/* 124:124 */      fsb1.setPercentage(fsb1.getEnterTotal() * 100 / (fsb1.getEnterTotal() + fsb2.getEnterTotal()));
/* 125:125 */      fsb2.setPercentage(fsb2.getEnterTotal() * 100 / (fsb1.getEnterTotal() + fsb2.getEnterTotal()));
/* 126:    */    }
/* 127:127 */    if (fsb1.getPercentage() == 0) {
/* 128:128 */      fsb1.setStayLong(0);
/* 129:    */    } else
/* 130:130 */      fsb1.setStayLong(getCustomerStayLong(apmacs, tag, 0));
/* 131:131 */    if (fsb2.getPercentage() == 0) {
/* 132:132 */      fsb2.setStayLong(0);
/* 133:    */    } else
/* 134:134 */      fsb2.setStayLong(getCustomerStayLong(apmacs, tag, 1));
/* 135:135 */    fsb1.setAuthTotal(getDetectTotal(apmacs, "auth", tag));
/* 136:136 */    fsb2.setAuthTotal(getDetectTotal(apmacs, "new_auth", tag));
/* 137:    */    
/* 138:138 */    List data = new ArrayList();
/* 139:139 */    data.add(beanToMap(fsb2));
/* 140:140 */    data.add(beanToMap(fsb1));
/* 141:141 */    Map<String, Object> result = new HashMap();
/* 142:142 */    result.put("status", Integer.valueOf(1));
/* 143:143 */    result.put("data", data);
/* 144:144 */    return result;
/* 145:    */  }
/* 146:    */  
/* 147:    */  private Map<String, Object> beanToMap(FeatureSummaryBean fsb) {
/* 148:148 */    Map<String, Object> map = new HashMap();
/* 149:149 */    map.put("userCount", Integer.valueOf(fsb.getEnterTotal()));
/* 150:150 */    map.put("userCountRatio", fsb.getPercentage() + "%");
/* 151:151 */    map.put("authCount", Integer.valueOf(fsb.getAuthTotal()));
/* 152:152 */    map.put("avgStay", DateUtil.secondToTimeString(fsb.getStayLong()));
/* 153:153 */    return map;
/* 154:    */  }
/* 155:    */  
/* 156:    */  private int getCustomerStayLong(String apmacs, String timeType, int flag) {
/* 157:157 */    StringBuilder sqlText = new StringBuilder(200);
/* 158:158 */    sqlText.append("SELECT ROUND(SUM(TIMESTAMPDIFF(SECOND,start_time,end_time))/COUNT(DISTINCT mac)) AS stay_long FROM detect_stay_long WHERE flag=");
/* 159:159 */    sqlText.append(flag).append(" AND ap_mac in (").append(apmacs).append(") AND ").append(DateUtil.getTimeSQL(timeType).replace("log_time", "start_time"));
/* 160:160 */    this.loger.info("-----getCustomerStayLong-------\n" + sqlText.toString());
/* 161:    */    try {
/* 162:162 */      return ((Integer)this.acsideJdbc.queryForObject(sqlText.toString(), Integer.class)).intValue();
/* 163:    */    } catch (Exception ex) {}
/* 164:164 */    return 0;
/* 165:    */  }
/* 166:    */  
/* 167:    */  private String getSQLByFlag(String flag)
/* 168:    */  {
/* 169:169 */    if ("auth".equals(flag))
/* 170:170 */      return " AND auth = 2";
/* 171:171 */    if ("enter".equals(flag))
/* 172:172 */      return " AND auth > 0";
/* 173:173 */    if ("new_auth".equals(flag))
/* 174:174 */      return " AND fresh = 1 AND auth = 2";
/* 175:175 */    if ("new".equals(flag))
/* 176:176 */      return " AND fresh = 1 AND auth > 0";
/* 177:177 */    if ("old".equals(flag))
/* 178:178 */      return " AND fresh = 0 AND auth > 0";
/* 179:179 */    return "";
/* 180:    */  }
/* 181:    */  
/* 182:    */  public List<BasicDynaBean> getHotShop(String apmacs, String tag, String flag) {
/* 183:183 */    StringBuilder sqlText = new StringBuilder(200);
/* 184:184 */    sqlText.append("SELECT b.alias,a.ap_mac,a.cc FROM(SELECT ap_mac,COUNT(DISTINCT mac) cc FROM detect_ap_mac WHERE ").append(DateUtil.getTimeSQL(tag));
/* 185:185 */    sqlText.append(getSQLByFlag(flag)).append(" AND ap_mac in (").append(apmacs).append(") GROUP BY ap_mac) a LEFT JOIN ap_custom b ON a.ap_mac=b.ap_mac ORDER BY a.cc desc");
/* 186:    */    
/* 187:187 */    final BasicDynaClass dynaClass = getSimpleItemDynaClass();
/* 188:188 */    List<BasicDynaBean> fsbs = (List)this.acsideJdbc.query(sqlText.toString(), new ResultSetExtractor() {
/* 189:    */      public List<BasicDynaBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 190:190 */        List<BasicDynaBean> items = new ArrayList();
/* 191:191 */        while (rs.next()) {
/* 192:    */          try {
/* 193:193 */            BasicDynaBean item = (BasicDynaBean)dynaClass.newInstance();
/* 194:194 */            if (rs.getString("alias") != null) {
/* 195:195 */              item.set("entity", rs.getString("alias"));
/* 196:196 */              item.set("value", Integer.valueOf(rs.getInt("cc")));
/* 197:197 */              items.add(item);
/* 198:    */            }
/* 199:    */          } catch (Exception ex) {
/* 200:200 */            ex.printStackTrace();
/* 201:    */          }
/* 202:    */        }
/* 203:203 */        return items;
/* 204:204 */      } } );
/* 205:205 */    return fsbs;
/* 206:    */  }
/* 207:    */  
/* 208:    */  public List<BasicDynaBean> getHotShopByStayLong(String apmacs, String timeType) {
/* 209:209 */    StringBuilder sqlText = new StringBuilder(200);
/* 210:210 */    sqlText.append("SELECT b.alias,a.ap_mac,a.stay_long FROM (SELECT ap_mac,ROUND(SUM(TIMESTAMPDIFF(SECOND,start_time,end_time))/COUNT(DISTINCT mac)) AS stay_long FROM detect_stay_long WHERE ap_mac IN (");
/* 211:211 */    sqlText.append(apmacs).append(") AND ").append(DateUtil.getTimeSQL(timeType).replace("log_time", "start_time")).append(" GROUP BY ap_mac) a LEFT JOIN ap_custom b ON a.ap_mac=b.ap_mac ORDER BY a.stay_long DESC");
/* 212:    */    
/* 213:213 */    final BasicDynaClass dynaClass = getSimpleItemDynaClass();
/* 214:214 */    List<BasicDynaBean> fsbs = (List)this.acsideJdbc.query(sqlText.toString(), new ResultSetExtractor() {
/* 215:    */      public List<BasicDynaBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 216:216 */        List<BasicDynaBean> items = new ArrayList();
/* 217:217 */        while (rs.next()) {
/* 218:    */          try {
/* 219:219 */            BasicDynaBean item = (BasicDynaBean)dynaClass.newInstance();
/* 220:220 */            if (rs.getString("alias") != null) {
/* 221:221 */              item.set("entity", rs.getString("alias"));
/* 222:222 */              item.set("value", Integer.valueOf(rs.getInt("stay_long")));
/* 223:223 */              items.add(item);
/* 224:    */            }
/* 225:    */          } catch (Exception ex) {
/* 226:226 */            ex.printStackTrace();
/* 227:    */          }
/* 228:    */        }
/* 229:229 */        return items;
/* 230:230 */      } } );
/* 231:231 */    return fsbs;
/* 232:    */  }
/* 233:    */  
/* 234:    */  public List<DetectFlowDto> getCustomerPath(String mac, String tag, String branchId) {
/* 235:    */    try {
/* 236:236 */      boolean realTime = false;
/* 237:237 */      if ("lastHour".equals(tag)) {
/* 238:238 */        realTime = true;
/* 239:239 */      } else if (tag.indexOf("#") != -1) {
/* 240:240 */        String[] times = tag.split("#");
/* 241:241 */        long diffMins = DateUtil.getDiffMinutes(times[0] + ":00", DateUtil.getCurrentDateTime());
/* 242:242 */        if ((diffMins > 0L) && (diffMins < 60L))
/* 243:243 */          realTime = true;
/* 244:    */      }
/* 245:245 */      StringBuilder sqlText = new StringBuilder(200);
/* 246:246 */      if (realTime) {
/* 247:247 */        List<String> tbls = new ArrayList();
/* 248:248 */        List<HostBean> hosts = this.hdao.findByBranch(branchId, null);
/* 249:249 */        if (hosts.isEmpty()) {
/* 250:250 */          tbls.add("select a.*,b.alias from detect_flow_empty a left join ap_custom b on a.ap_mac=b.ap_mac");
/* 251:    */        } else {
/* 252:252 */          StringBuilder sqlstr = new StringBuilder(200);
/* 253:253 */          for (HostBean host : hosts) {
/* 254:254 */            String tbl = "detect_flow_" + host.getSerialNumber().replace(":", "");
/* 255:255 */            if (this.tfdao.hasTable(tbl)) {
/* 256:256 */              sqlstr.append(" union(select '").append(host.getSerialNumber()).append("' AS ap_mac,log_time from ").append(tbl);
/* 257:257 */              sqlstr.append(" where mac='").append(mac).append("' and ").append(DateUtil.getTimeSQL(tag)).append(")");
/* 258:    */            }
/* 259:    */          }
/* 260:260 */          if (sqlstr.length() > 0) {
/* 261:261 */            sqlText.append("select b.alias,min(log_time) start_time,TIMESTAMPDIFF(SECOND,min(log_time),max(log_time)) diffsec from (");
/* 262:262 */            sqlText.append(sqlstr.substring(6)).append(") a left join ap_custom b on a.ap_mac=b.ap_mac group by a.ap_mac");
/* 263:    */          }
/* 264:    */        }
/* 265:    */      } else {
/* 266:266 */        sqlText.append("SELECT a.mac,a.ap_mac,a.start_time,a.end_time,TIMESTAMPDIFF(SECOND,a.start_time,a.end_time) AS diffsec,b.alias FROM detect_stay_long a LEFT JOIN ap_custom b ON a.ap_mac=b.ap_mac").append(" where mac='").append(mac).append("' and ");
/* 267:267 */        sqlText.append(DateUtil.getTimeSQL(tag).replace("log_time", "start_time")).append(" order by start_time desc");
/* 268:    */      }
/* 269:    */      
/* 270:270 */      (List)this.acsideJdbc.query(sqlText.toString(), new ResultSetExtractor() {
/* 271:    */        public List<DetectFlowDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 272:272 */          List<DetectFlowDto> dfds = new ArrayList();
/* 273:273 */          while (rs.next()) {
/* 274:274 */            if (rs.getLong("diffsec") > 60L) {
/* 275:275 */              DetectFlowDto dfd = new DetectFlowDto();
/* 276:276 */              dfd.setAlias(rs.getString("alias"));
/* 277:277 */              dfd.setLogTime(rs.getString("start_time"));
/* 278:278 */              dfd.setStayLong(DateUtil.secondToTimeString(rs.getLong("diffsec")));
/* 279:279 */              dfds.add(dfd);
/* 280:    */            }
/* 281:    */          }
/* 282:282 */          return dfds;
/* 283:    */        }
/* 284:    */      });
/* 285:    */    } catch (Exception e) {
/* 286:286 */      e.printStackTrace();
/* 287:    */    }
/* 288:288 */    return null;
/* 289:    */  }
/* 290:    */  
/* 291:    */  public Map<String, Object> getVisitSummary(String apmacs, String tag) {
/* 292:292 */    StringBuilder sqlText = new StringBuilder(200);
/* 293:293 */    sqlText.append("SELECT times,COUNT(mac) persons FROM(SELECT mac,COUNT(cc) times FROM(SELECT mac,DATE_FORMAT(start_time,'%Y-%m-%d'),COUNT(*) cc FROM detect_stay_long WHERE");
/* 294:294 */    sqlText.append(DateUtil.getTimeSQL(tag).replace("log_time", "start_time")).append(" AND ap_mac in (").append(apmacs).append(") GROUP BY mac,DATE_FORMAT(start_time,'%Y-%m-%d')) t1 GROUP BY mac) t2 GROUP BY times");
/* 295:    */    
/* 296:296 */    List<FeatureSummaryBean> fsbs = (List)this.acsideJdbc.query(sqlText.toString(), new ResultSetExtractor() {
/* 297:    */      public List<FeatureSummaryBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 298:298 */        List<FeatureSummaryBean> items = new ArrayList();
/* 299:299 */        while (rs.next()) {
/* 300:300 */          FeatureSummaryBean item = new FeatureSummaryBean();
/* 301:301 */          item.setPercentage(rs.getInt("times"));
/* 302:302 */          item.setEnterTotal(rs.getInt("persons"));
/* 303:303 */          items.add(item);
/* 304:    */        }
/* 305:305 */        return items;
/* 306:    */      }
/* 307:307 */    });
/* 308:308 */    Object[][] data = new Object[visitHeads.length][2];
/* 309:309 */    for (int i = 0; i < visitHeads.length; i++) {
/* 310:310 */      data[i][0] = visitHeads[i];
/* 311:311 */      data[i][1] = Integer.valueOf(0);
/* 312:    */    }
/* 313:313 */    for (FeatureSummaryBean fsb : fsbs) {
/* 314:314 */      if (fsb.getPercentage() < 6) {
/* 315:315 */        data[(fsb.getPercentage() - 1)][1] = Integer.valueOf(fsb.getEnterTotal());
/* 316:316 */      } else if ((fsb.getPercentage() > 5) && (fsb.getPercentage() < 11)) {
/* 317:317 */        data[4][1] = Integer.valueOf(fsb.getEnterTotal());
/* 318:318 */      } else if ((fsb.getPercentage() > 10) && (fsb.getPercentage() < 16)) {
/* 319:319 */        data[5][1] = Integer.valueOf(fsb.getEnterTotal());
/* 320:    */      } else
/* 321:321 */        data[6][1] = Integer.valueOf(fsb.getEnterTotal());
/* 322:    */    }
/* 323:323 */    Map<String, Object> result = new HashMap();
/* 324:324 */    result.put("status", Integer.valueOf(1));
/* 325:325 */    result.put("data", new Object[][][] { data });
/* 326:326 */    return result;
/* 327:    */  }
/* 328:    */  
/* 329:    */  private BasicDynaClass getSimpleItemDynaClass() {
/* 330:330 */    DynaProperty[] props = new DynaProperty[3];
/* 331:331 */    props[0] = new DynaProperty("entity", String.class);
/* 332:332 */    props[1] = new DynaProperty("value", Integer.class);
/* 333:333 */    props[2] = new DynaProperty("color", String.class);
/* 334:334 */    BasicDynaClass bdc = new BasicDynaClass("SimpleItem", BasicDynaBean.class, props);
/* 335:335 */    return bdc;
/* 336:    */  }
/* 337:    */  
/* 339:    */  @Autowired
/* 340:    */  private TraceFlowDao tfdao;
/* 341:    */  
/* 342:    */  @Autowired
/* 343:    */  private HostDao hdao;
/* 344:    */  @Autowired
/* 345:    */  protected void setDao(DetectFlowDao dao)
/* 346:    */  {
/* 347:347 */    this.dao = dao;
/* 348:    */  }
/* 349:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.web.DetectFlowService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */