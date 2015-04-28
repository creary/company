/*  1:   */package com.soofound.portal.action;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseAction;
/*  4:   */import com.soofound.portal.service.ProvinceService;
/*  5:   */import java.util.Map;
/*  6:   */import javax.servlet.http.HttpServletRequest;
/*  7:   */import org.springframework.beans.factory.annotation.Autowired;
/*  8:   */import org.springframework.stereotype.Controller;
/*  9:   */import org.springframework.ui.ModelMap;
/* 10:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 11:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 12:   */
/* 18:   */@Controller
/* 19:   */public class ProvinceAction
/* 20:   */  extends BaseAction<ProvinceService>
/* 21:   */{
/* 22:   */  @RequestMapping({"/portal/provinceListJsp.do"})
/* 23:   */  public String listJsp(HttpServletRequest request, ModelMap model)
/* 24:   */  {
/* 25:25 */    return "/portal/region/provinceList.jsp";
/* 26:   */  }
/* 27:   */  
/* 28:   */  @RequestMapping({"/portal/provinceList.do"})
/* 29:   */  @ResponseBody
/* 30:30 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 31:   */  
/* 32:   */  @RequestMapping({"/portal/provinceSave.do"})
/* 33:   */  @ResponseBody
/* 34:   */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) {
/* 35:35 */    return super.save(request, model);
/* 36:   */  }
/* 37:   */  
/* 38:   */  @RequestMapping({"/portal/provinceUpdate.do"})
/* 39:   */  @ResponseBody
/* 40:40 */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) { return super.update(request, model); }
/* 41:   */  
/* 42:   */  @RequestMapping({"/portal/provinceDelete.do"})
/* 43:   */  @ResponseBody
/* 44:   */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) {
/* 45:45 */    return super.deleteByIDs(request, model);
/* 46:   */  }
/* 47:   */  
/* 48:   */  @Autowired
/* 49:   */  protected void setService(ProvinceService service) {
/* 50:50 */    this.service = service;
/* 51:   */  }
/* 52:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.ProvinceAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */