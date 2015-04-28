/*  1:   */package com.soofound.portal.action;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseAction;
/*  4:   */import com.soofound.portal.service.CityService;
/*  5:   */import com.soofound.portal.service.CountyService;
/*  6:   */import java.util.Map;
/*  7:   */import javax.servlet.http.HttpServletRequest;
/*  8:   */import org.springframework.beans.factory.annotation.Autowired;
/*  9:   */import org.springframework.stereotype.Controller;
/* 10:   */import org.springframework.ui.ModelMap;
/* 11:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 12:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 13:   */
/* 16:   */@Controller
/* 17:   */public class CountyAction
/* 18:   */  extends BaseAction<CountyService>
/* 19:   */{
/* 20:   */  @Autowired
/* 21:   */  private CityService cityService;
/* 22:   */  
/* 23:   */  @RequestMapping({"/portal/countyListJsp.do"})
/* 24:   */  public String listJsp(HttpServletRequest request, ModelMap model)
/* 25:   */  {
/* 26:26 */    return "/portal/region/countyList.jsp";
/* 27:   */  }
/* 28:   */  
/* 29:   */  @RequestMapping({"/portal/countyList.do"})
/* 30:   */  @ResponseBody
/* 31:31 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 32:   */  
/* 33:   */  @RequestMapping({"/portal/countyReadyAdd.do"})
/* 34:   */  public String readyAdd(HttpServletRequest request, ModelMap model)
/* 35:   */  {
/* 36:36 */    model.addAttribute("cities", this.cityService.findAll());
/* 37:37 */    return "/portal/region/countyAdd.jsp";
/* 38:   */  }
/* 39:   */  
/* 40:   */  @RequestMapping({"/portal/countySave.do"})
/* 41:   */  @ResponseBody
/* 42:42 */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) { String hexCode = request.getParameter("hexCode");
/* 43:43 */    if (((CountyService)this.service).findByID(hexCode) != null) {
/* 44:44 */      return getFailedResult("[" + hexCode + "]已经存在");
/* 45:   */    }
/* 46:46 */    Map<String, Object> result = super.save(request, model);
/* 47:47 */    return result;
/* 48:   */  }
/* 49:   */  
/* 50:   */  @RequestMapping({"/portal/countyReadyEdit.do"})
/* 51:   */  public String readyEdit(HttpServletRequest request, ModelMap model) {
/* 52:52 */    String id = request.getParameter("id");
/* 53:53 */    model.addAttribute("dto", ((CountyService)this.service).findByID(id));
/* 54:54 */    model.addAttribute("cities", this.cityService.findAll());
/* 55:55 */    return "/portal/region/countyEdit.jsp";
/* 56:   */  }
/* 57:   */  
/* 58:   */  @RequestMapping({"portal/countyUpdate.do"})
/* 59:   */  @ResponseBody
/* 60:60 */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) { return super.update(request, model); }
/* 61:   */  
/* 62:   */  @RequestMapping({"/portal/countyDelete.do"})
/* 63:   */  @ResponseBody
/* 64:   */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) {
/* 65:65 */    return super.deleteByIDs(request, model);
/* 66:   */  }
/* 67:   */  
/* 69:   */  @Autowired
/* 70:   */  protected void setService(CountyService service)
/* 71:   */  {
/* 72:72 */    this.service = service;
/* 73:   */  }
/* 74:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.CountyAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */