/*  1:   */package com.soofound.portal.action;
/*  2:   */
/*  3:   */import com.alibaba.fastjson.JSON;
/*  4:   */import com.soofound.framework.util.DateUtil;
/*  5:   */import com.soofound.framework.web.BaseAction;
/*  6:   */import com.soofound.portal.bean.PortalTemplate;
/*  7:   */import com.soofound.portal.bean.PortalTemplateDto;
/*  8:   */import com.soofound.portal.service.PortalTemplateService;
/*  9:   */import com.soofound.portal.util.WifiDogUtils;
/* 10:   */import java.util.Map;
/* 11:   */import javax.servlet.http.HttpServletRequest;
/* 12:   */import org.springframework.beans.factory.annotation.Autowired;
/* 13:   */import org.springframework.stereotype.Controller;
/* 14:   */import org.springframework.ui.ModelMap;
/* 15:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 16:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 17:   */
/* 26:   */@Controller
/* 27:   */public class PortalTemplateAction
/* 28:   */  extends BaseAction<PortalTemplateService>
/* 29:   */{
/* 30:   */  @RequestMapping({"/portal/templateListJsp.do"})
/* 31:   */  public String listJsp(HttpServletRequest request, ModelMap model)
/* 32:   */  {
/* 33:33 */    return "/portal/apface/templateList.jsp";
/* 34:   */  }
/* 35:   */  
/* 36:   */  @RequestMapping({"/portal/templateList.do"})
/* 37:   */  @ResponseBody
/* 38:38 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 39:   */  
/* 42:   */  @RequestMapping({"/portal/templateReadyAdd.do"})
/* 43:43 */  public String readyAdd(HttpServletRequest request, ModelMap model) { return "/portal/apface/templateAdd.jsp"; }
/* 44:   */  
/* 45:   */  @RequestMapping({"/portal/templateSave.do"})
/* 46:   */  @ResponseBody
/* 47:   */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) {
/* 48:   */    try {
/* 49:49 */      String id = request.getParameter("id");
/* 50:50 */      ((PortalTemplateService)this.service).delete(id);
/* 51:   */      
/* 52:52 */      String templatePath = WifiDogUtils.getTemplatePath(id);
/* 53:53 */      String defineFile = templatePath + "/define/tpl_define.json";
/* 54:54 */      String defJson = WifiDogUtils.getMsiteJsonContent(defineFile);
/* 55:55 */      Map<String, Object> jsondata = (Map)JSON.parseObject(defJson, Map.class);
/* 56:56 */      PortalTemplateDto dto = new PortalTemplateDto();
/* 57:57 */      dto.setId(id);
/* 58:58 */      dto.setName(jsondata.get("name").toString());
/* 59:59 */      dto.setDescr(WifiDogUtils.getRidOfHTMLTag(jsondata.get("desc").toString()));
/* 60:   */      
/* 61:61 */      PortalTemplate pt = new PortalTemplate(id);
/* 62:62 */      dto.setCover(pt.getCover());
/* 63:63 */      dto.setCreateTime(DateUtil.getCurrentDateTime());
/* 64:64 */      ((PortalTemplateService)this.service).save(dto);
/* 65:65 */      ((PortalTemplateService)this.service).addPortalTemplate(pt);
/* 66:   */      
/* 67:67 */      return super.getSucceedResult("导入成功");
/* 68:   */    } catch (Exception ex) {
/* 69:69 */      if (ex.getMessage() == null)
/* 70:70 */        return super.getFailedResult("导入失败:模板不存在");
/* 71:71 */      return super.getFailedResult("导入失败:" + ex.getMessage());
/* 72:   */    }
/* 73:   */  }
/* 74:   */  
/* 75:   */  @RequestMapping({"/portal/templateDelete.do"})
/* 76:   */  @ResponseBody
/* 77:77 */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) { return super.deleteByIDs(request, model); }
/* 78:   */  
/* 79:   */  @Autowired
/* 80:   */  protected void setService(PortalTemplateService service)
/* 81:   */  {
/* 82:82 */    this.service = service;
/* 83:   */  }
/* 84:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.PortalTemplateAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */