/*  1:   */package com.soofound.portal.action;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseAction;
/*  4:   */import com.soofound.portal.service.CityService;
/*  5:   */import com.soofound.portal.service.ProvinceService;
/*  6:   */import java.util.Map;
/*  7:   */import javax.servlet.http.HttpServletRequest;
/*  8:   */import org.springframework.beans.factory.annotation.Autowired;
/*  9:   */import org.springframework.stereotype.Controller;
/* 10:   */import org.springframework.ui.ModelMap;
/* 11:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 12:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 13:   */
/* 16:   */@Controller
/* 17:   */public class CityAction
/* 18:   */  extends BaseAction<CityService>
/* 19:   */{
/* 20:   */  @Autowired
/* 21:   */  private ProvinceService provinceService;
/* 22:   */  
/* 23:   */  @RequestMapping({"/portal/cityListJsp.do"})
/* 24:   */  public String listJsp(HttpServletRequest request, ModelMap model)
/* 25:   */  {
/* 26:26 */    return "/portal/region/cityList.jsp";
/* 27:   */  }
/* 28:   */  
/* 29:   */  @RequestMapping({"/portal/cityList.do"})
/* 30:   */  @ResponseBody
/* 31:31 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 32:   */  
/* 33:   */  @RequestMapping({"/portal/cityReadyAdd.do"})
/* 34:   */  public String readyAdd(HttpServletRequest request, ModelMap model)
/* 35:   */  {
/* 36:36 */    model.addAttribute("provinces", this.provinceService.findAll());
/* 37:37 */    return "/portal/region/cityAdd.jsp";
/* 38:   */  }
/* 39:   */  
/* 40:   */  @RequestMapping({"/portal/citySave.do"})
/* 41:   */  @ResponseBody
/* 42:42 */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) { return super.save(request, model); }
/* 43:   */  
/* 44:   */  @RequestMapping({"/portal/cityReadyEdit.do"})
/* 45:   */  public String readyEdit(HttpServletRequest request, ModelMap model)
/* 46:   */  {
/* 47:47 */    String id = request.getParameter("id");
/* 48:48 */    model.addAttribute("dto", ((CityService)this.service).findByID(id));
/* 49:49 */    model.addAttribute("provinces", this.provinceService.findAll());
/* 50:50 */    return "/portal/region/cityEdit.jsp";
/* 51:   */  }
/* 52:   */  
/* 53:   */  @RequestMapping({"/portal/cityUpdate.do"})
/* 54:   */  @ResponseBody
/* 55:55 */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) { return super.update(request, model); }
/* 56:   */  
/* 57:   */  @RequestMapping({"/portal/cityDelete.do"})
/* 58:   */  @ResponseBody
/* 59:   */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) {
/* 60:60 */    return super.deleteByIDs(request, model);
/* 61:   */  }
/* 62:   */  
/* 65:   */  @Autowired
/* 66:   */  protected void setService(CityService service)
/* 67:   */  {
/* 68:68 */    this.service = service;
/* 69:   */  }
/* 70:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.CityAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */