/*  1:   */package com.soofound.portal.action;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseAction;
/*  4:   */import com.soofound.portal.bean.AdvertiseCategoryBean;
/*  5:   */import com.soofound.portal.service.AdvertiseCategoryService;
/*  6:   */import java.util.Map;
/*  7:   */import javax.servlet.http.HttpServletRequest;
/*  8:   */import org.springframework.beans.factory.annotation.Autowired;
/*  9:   */import org.springframework.stereotype.Controller;
/* 10:   */import org.springframework.ui.ModelMap;
/* 11:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 12:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 13:   */
/* 14:   */@Controller
/* 15:   */public class AdvertiseCategoryAction
/* 16:   */  extends BaseAction<AdvertiseCategoryService>
/* 17:   */{
/* 18:   */  @RequestMapping({"/portal/adCateListJsp.do"})
/* 19:   */  public String listJsp(HttpServletRequest request, ModelMap model)
/* 20:   */  {
/* 21:21 */    return "/cpe/struct/categoryList.jsp";
/* 22:   */  }
/* 23:   */  
/* 24:   */  @RequestMapping({"/portal/adCateList.do"})
/* 25:   */  @ResponseBody
/* 26:26 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 27:   */  
/* 30:   */  @RequestMapping({"/portal/adCateReadyAdd.do"})
/* 31:31 */  public String readyAdd(HttpServletRequest request, ModelMap model) { return "/cpe/struct/categoryAdd.jsp"; }
/* 32:   */  
/* 33:   */  @RequestMapping({"/portal/adCateSave.do"})
/* 34:   */  @ResponseBody
/* 35:   */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) {
/* 36:36 */    String name = request.getParameter("name");
/* 37:37 */    if (((AdvertiseCategoryService)this.service).findByName(name) != null) {
/* 38:38 */      return getFailedResult("[" + name + "]已经存在");
/* 39:   */    }
/* 40:40 */    String branchId = request.getParameter("branchId");
/* 41:41 */    Map<String, Object> result = super.save(request, model);
/* 42:42 */    ((AdvertiseCategoryService)this.service).removeBranchCategories(branchId);
/* 43:43 */    return result;
/* 44:   */  }
/* 45:   */  
/* 46:   */  @RequestMapping({"/portal/adCateReadyEdit.do"})
/* 47:   */  public String readyEdit(HttpServletRequest request, ModelMap model) {
/* 48:48 */    String id = request.getParameter("id");
/* 49:49 */    model.addAttribute("dto", ((AdvertiseCategoryService)this.service).findByID(id));
/* 50:50 */    return "/cpe/struct/categoryEdit.jsp";
/* 51:   */  }
/* 52:   */  
/* 53:   */  @RequestMapping({"/portal/adCateUpdate.do"})
/* 54:   */  @ResponseBody
/* 55:55 */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) { String id = request.getParameter("id");
/* 56:56 */    String name = request.getParameter("name");
/* 57:57 */    AdvertiseCategoryBean pcb = ((AdvertiseCategoryService)this.service).findByName(name);
/* 58:58 */    if ((pcb != null) && (pcb.getId() != Integer.parseInt(id))) {
/* 59:59 */      return getFailedResult("[" + name + "]已经存在");
/* 60:   */    }
/* 61:61 */    Map<String, Object> result = super.update(request, model);
/* 62:   */    try {
/* 63:63 */      String bid = request.getParameter("branchId");
/* 64:64 */      ((AdvertiseCategoryService)this.service).removeBranchCategories(bid);
/* 65:   */    } catch (Exception e) {
/* 66:66 */      e.printStackTrace();
/* 67:   */    }
/* 68:68 */    return result;
/* 69:   */  }
/* 70:   */  
/* 71:   */  @RequestMapping({"/portal/adCateDelete.do"})
/* 72:   */  @ResponseBody
/* 73:73 */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) { return deleteByIDs(request, model); }
/* 74:   */  
/* 75:   */  @Autowired
/* 76:   */  protected void setService(AdvertiseCategoryService service)
/* 77:   */  {
/* 78:78 */    this.service = service;
/* 79:   */  }
/* 80:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.AdvertiseCategoryAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */