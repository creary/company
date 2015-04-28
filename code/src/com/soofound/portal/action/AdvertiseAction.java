/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.BranchDto;
/*   4:    */import com.soofound.admin.bean.UserDto;
/*   5:    */import com.soofound.admin.web.BranchService;
/*   6:    */import com.soofound.cpe.util.SoofacACS;
/*   7:    */import com.soofound.framework.util.DateUtil;
/*   8:    */import com.soofound.framework.util.SysConfigHelper;
/*   9:    */import com.soofound.framework.web.BaseAction;
/*  10:    */import com.soofound.portal.bean.AdvertiseCategoryBean;
/*  11:    */import com.soofound.portal.bean.AdvertiseDto;
/*  12:    */import com.soofound.portal.custom.CustomFactory;
/*  13:    */import com.soofound.portal.custom.SmsSender;
/*  14:    */import com.soofound.portal.service.AdvertiseCategoryService;
/*  15:    */import com.soofound.portal.service.AdvertiseService;
/*  16:    */import com.soofound.protocol.wechat.WeChatAPI;
/*  17:    */import com.soofound.protocol.wechat.message.UploadNews;
/*  18:    */import java.io.File;
/*  19:    */import java.util.ArrayList;
/*  20:    */import java.util.HashMap;
/*  21:    */import java.util.List;
/*  22:    */import java.util.Map;
/*  23:    */import javax.servlet.http.HttpServletRequest;
/*  24:    */import org.springframework.beans.factory.annotation.Autowired;
/*  25:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  26:    */import org.springframework.stereotype.Controller;
/*  27:    */import org.springframework.ui.ModelMap;
/*  28:    */import org.springframework.util.StringUtils;
/*  29:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  30:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  31:    */
/*  32:    */@Controller
/*  33:    */public final class AdvertiseAction
/*  34:    */  extends BaseAction<AdvertiseService>
/*  35:    */{
/*  36: 36 */  public static final String URL_UPLOAD = SysConfigHelper.CONTEXT_PATH + "acs/msite/";
/*  37: 37 */  public static final String PATH_UPLOAD = SysConfigHelper.getAttribute("sysPath") + "/acs/msite/";
/*  38:    */  
/*  39:    */  @RequestMapping({"/portal/advertiseListJsp.do"})
/*  40:    */  public String listJsp(HttpServletRequest request, ModelMap model) {
/*  41: 41 */    return "/portal/advertise/list.jsp";
/*  42:    */  }
/*  43:    */  
/*  44:    */  @RequestMapping({"/portal/advertiseList.do"})
/*  45:    */  @ResponseBody
/*  46: 46 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/*  47:    */  
/*  48:    */  @RequestMapping({"/portal/advertiseReadyAdd.do"})
/*  49:    */  public String readyAdd(HttpServletRequest request, ModelMap model)
/*  50:    */  {
/*  51: 51 */    UserDto user = super.getCurrentUser(request);
/*  52: 52 */    model.addAttribute("cates", this.pcs.getADCategories(user.getBranchId()));
/*  53: 53 */    model.addAttribute("id", DateUtil.getCurrentTimeAsID());
/*  54: 54 */    return "/portal/advertise/add.jsp";
/*  55:    */  }
/*  56:    */  
/*  57:    */  @RequestMapping({"/portal/advertiseSave.do"})
/*  58:    */  @ResponseBody
/*  59: 59 */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) { String cid = request.getParameter("categoryId");
/*  60:    */    try {
/*  61: 61 */      UserDto user = super.getCurrentUser(request);
/*  62: 62 */      AdvertiseDto dto = new AdvertiseDto();
/*  63: 63 */      dto.setId(((AdvertiseService)this.service).getNextID());
/*  64: 64 */      dto.setCategoryId(Integer.parseInt(cid));
/*  65: 65 */      dto.setSummary(request.getParameter("summary"));
/*  66: 66 */      dto.setCover(request.getParameter("cover"));
/*  67: 67 */      dto.setTitle(request.getParameter("title"));
/*  68: 68 */      dto.setCreator(user.getFullname());
/*  69: 69 */      dto.setCreateTime(DateUtil.getCurrentDateTime());
/*  70: 70 */      dto.setBranchId(user.getBranchId());
/*  71: 71 */      dto.setContent(request.getParameter("clobs_text"));
/*  72: 72 */      ((AdvertiseService)this.service).save(dto);
/*  73: 73 */      return super.getSucceedResult("增加成功");
/*  74:    */    } catch (Exception e) {
/*  75: 75 */      e.printStackTrace(); }
/*  76: 76 */    return super.getFailedResult("增加失败");
/*  77:    */  }
/*  78:    */  
/*  79:    */  @RequestMapping({"/portal/advertiseReadyEdit.do"})
/*  80:    */  public String readyEdit(HttpServletRequest request, ModelMap model)
/*  81:    */  {
/*  82: 82 */    String id = request.getParameter("id");
/*  83: 83 */    model.addAttribute("dto", ((AdvertiseService)this.service).findByID(id));
/*  84: 84 */    UserDto user = super.getCurrentUser(request);
/*  85: 85 */    model.addAttribute("cates", this.pcs.getADCategories(user.getBranchId()));
/*  86: 86 */    return "/portal/advertise/edit.jsp";
/*  87:    */  }
/*  88:    */  
/*  89:    */  @RequestMapping({"/portal/advertiseUpdate.do"})
/*  90:    */  @ResponseBody
/*  91: 91 */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) { String cid = request.getParameter("categoryId");
/*  92: 92 */    String id = request.getParameter("id");
/*  93:    */    try {
/*  94: 94 */      AdvertiseDto dto = new AdvertiseDto();
/*  95: 95 */      dto.setId(new Integer(id).intValue());
/*  96: 96 */      dto.setCategoryId(Integer.parseInt(cid));
/*  97: 97 */      dto.setSummary(request.getParameter("summary"));
/*  98: 98 */      dto.setCover(request.getParameter("cover"));
/*  99: 99 */      dto.setTitle(request.getParameter("title"));
/* 100:100 */      dto.setCreateTime(DateUtil.getCurrentDateTime());
/* 101:101 */      dto.setContent(request.getParameter("clobs_text"));
/* 102:102 */      ((AdvertiseService)this.service).update(dto);
/* 103:103 */      return super.getSucceedResult("更新成功");
/* 104:    */    } catch (Exception e) {
/* 105:105 */      e.printStackTrace(); }
/* 106:106 */    return super.getFailedResult("更新失败");
/* 107:    */  }
/* 108:    */  
/* 109:    */  @RequestMapping({"/portal/advertiseDelete.do"})
/* 110:    */  @ResponseBody
/* 111:    */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) {
/* 112:112 */    return deleteByIDs(request, model);
/* 113:    */  }
/* 114:    */  
/* 115:    */  @RequestMapping({"/portal/readySendWechat.do"})
/* 116:    */  public String readySendWechat(HttpServletRequest request, ModelMap model)
/* 117:    */  {
/* 118:118 */    UserDto user = super.getCurrentUser(request);
/* 119:119 */    model.addAttribute("ads", ((AdvertiseService)this.service).getArticles(user.getBranchId()));
/* 120:120 */    return "/portal/surfing/weChatSend.jsp";
/* 121:    */  }
/* 122:    */  
/* 123:    */  @RequestMapping({"/portal/advertiseSend.do"})
/* 124:    */  @ResponseBody
/* 125:125 */  public Map<String, Object> doSend(HttpServletRequest request, ModelMap model) { UserDto user = super.getCurrentUser(request);
/* 126:126 */    String type = request.getParameter("type");
/* 127:    */    try {
/* 128:128 */      if ("sms".equals(type)) {
/* 129:129 */        if (this.custom.getSmsSender() == null) {
/* 130:130 */          return super.getFailedResult("没有短信接口,发送失败.");
/* 131:    */        }
/* 132:132 */        String[] phones = request.getParameterValues("selectedItems");
/* 133:133 */        StringBuilder phonestr = new StringBuilder(100);
/* 134:134 */        for (String phone : phones) {
/* 135:135 */          phonestr.append(",").append(phone);
/* 136:    */        }
/* 137:137 */        this.custom.getSmsSender().sendSMS(user.getBranchId(), phonestr.substring(1), request.getParameter("smsContent"));
/* 138:138 */        return super.getSucceedResult("成功发送" + phones.length + "条短信");
/* 139:    */      }
/* 140:    */      
/* 141:141 */      AdvertiseDto ad = (AdvertiseDto)((AdvertiseService)this.service).findByID(request.getParameter("adId"));
/* 142:142 */      BranchDto bd = (BranchDto)this.branchService.findByID(user.getBranchId());
/* 143:143 */      WeChatAPI wechat = null;
/* 144:144 */      if ((StringUtils.hasText(bd.getAppId())) && (StringUtils.hasText(bd.getAppSecret()))) {
/* 145:145 */        wechat = new WeChatAPI(bd.getAppId(), bd.getAppSecret());
/* 146:146 */        String coverFile = ad.getCover().substring(ad.getCover().indexOf("acs") - 1);
/* 147:147 */        String mid = wechat.uploadMedia("image", new File(SysConfigHelper.getAttribute("sysPath") + coverFile));
/* 148:148 */        UploadNews article = new UploadNews();
/* 149:    */        
/* 150:150 */        List<UploadNews> articles = new ArrayList();
/* 151:151 */        article.setThumb_media_id(mid);
/* 152:152 */        article.setAuthor(bd.getName());
/* 153:153 */        article.setTitle(ad.getTitle());
/* 154:154 */        article.setContent_source_url(this.acs.getAcsURL() + "/msite/ArticleView.do?aid=" + ad.getId() + "&branchId=" + ad.getBranchId());
/* 155:155 */        article.setContent(ad.getTitle());
/* 156:156 */        article.setDigest(ad.getTitle());
/* 157:157 */        articles.add(article);
/* 158:158 */        mid = wechat.uploadNews(articles);
/* 159:159 */        int result = wechat.massSendAll("0", mid, "mpnews");
/* 160:160 */        if (result == 0) {
/* 161:161 */          return super.getSucceedResult("成功群发微信");
/* 162:    */        }
/* 163:163 */        return super.getFailedResult("发送失败:" + result);
/* 164:    */      }
/* 165:    */    }
/* 166:    */    catch (Exception ex) {
/* 167:167 */      ex.printStackTrace();
/* 168:    */    }
/* 169:169 */    return super.getFailedResult("发送失败");
/* 170:    */  }
/* 171:    */  
/* 172:    */  @RequestMapping({"/portal/getShareArticles.do"})
/* 173:    */  @ResponseBody
/* 174:174 */  public Map<String, Object> getShareArticles(HttpServletRequest request, ModelMap model) { Map<String, Object> results = new HashMap();
/* 175:    */    try {
/* 176:176 */      String branchId = request.getParameter("branchId");
/* 177:177 */      String portalId = request.getParameter("portalId");
/* 178:178 */      String count = request.getParameter("count");
/* 179:179 */      String start = request.getParameter("start");
/* 180:180 */      if (count == null)
/* 181:181 */        count = "10";
/* 182:182 */      if (start == null)
/* 183:183 */        start = "1";
/* 184:184 */      int c = Integer.parseInt(count);
/* 185:185 */      int s = (Integer.parseInt(start) - 1) * c;
/* 186:186 */      List<AdvertiseDto> dtos = ((AdvertiseService)this.service).getShareArticles(branchId, portalId, s, c);
/* 187:187 */      Map<String, Integer> total = new HashMap();
/* 188:188 */      total.put("total", Integer.valueOf(((AdvertiseService)this.service).getShareArticleTotal(portalId)));
/* 189:189 */      results.put("meta", total);
/* 190:190 */      results.put("records", dtos);
/* 191:    */    } catch (Exception e) {
/* 192:192 */      e.printStackTrace();
/* 193:    */    }
/* 194:194 */    return results;
/* 195:    */  }
/* 196:    */  
/* 197:    */  @RequestMapping({"/msite/getArticles.do"})
/* 198:    */  @ResponseBody
/* 199:199 */  public Map<String, Object> getArticles(HttpServletRequest request, ModelMap model) { String branchId = request.getParameter("branchId");
/* 200:200 */    String cid = request.getParameter("cid");
/* 201:201 */    String order = request.getParameter("order");
/* 202:202 */    String count = request.getParameter("count");
/* 203:203 */    String start = request.getParameter("start");
/* 204:204 */    if (cid == null) {
/* 205:205 */      List<AdvertiseCategoryBean> pcbs = this.pcs.getADCategories(branchId);
/* 206:206 */      if (!pcbs.isEmpty())
/* 207:207 */        cid = ((AdvertiseCategoryBean)pcbs.get(0)).getId();
/* 208:    */    }
/* 209:209 */    if (order == null)
/* 210:210 */      order = "";
/* 211:211 */    if (count == null)
/* 212:212 */      count = "10";
/* 213:213 */    if ((start == null) || ("0".equals(start)))
/* 214:214 */      start = "1";
/* 215:215 */    int c = Integer.parseInt(count);
/* 216:216 */    int s = (Integer.parseInt(start) - 1) * c;
/* 217:217 */    List<AdvertiseDto> dtos = ((AdvertiseService)this.service).getArticles(branchId, cid, order, s, c);
/* 218:218 */    Map<String, Object> results = new HashMap();
/* 219:219 */    Map<String, Integer> total = new HashMap();
/* 220:220 */    total.put("total", Integer.valueOf(((AdvertiseService)this.service).getArticleTotal(branchId, cid)));
/* 221:221 */    results.put("meta", total);
/* 222:222 */    results.put("records", dtos);
/* 223:223 */    return results;
/* 224:    */  }
/* 225:    */  
/* 226:    */  @Autowired
/* 227:    */  private SoofacACS acs;
/* 228:    */  @Autowired
/* 229:    */  private BranchService branchService;
/* 230:    */  @Autowired
/* 231:    */  @Qualifier("customFactory")
/* 232:    */  private CustomFactory custom;
/* 233:    */  @Autowired
/* 234:    */  private AdvertiseCategoryService pcs;
/* 235:    */  @Autowired
/* 236:    */  protected void setService(AdvertiseService service) {
/* 237:237 */    this.service = service;
/* 238:    */  }
/* 239:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.AdvertiseAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */