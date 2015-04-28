/*  1:   */package com.soofound.cpe.web;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.UserDto;
/*  4:   */import com.soofound.admin.web.BranchService;
/*  5:   */import com.soofound.framework.web.BaseAction;
/*  6:   */import java.util.Map;
/*  7:   */import org.springframework.beans.factory.annotation.Autowired;
/*  8:   */import org.springframework.stereotype.Controller;
/*  9:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 10:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 11:   */
/* 12:   */@Controller
/* 13:   */public final class UpgradeAction extends BaseAction<UpgradeService>
/* 14:   */{
/* 15:   */  @Autowired
/* 16:   */  private BranchService branchService;
/* 17:   */  
/* 18:   */  @RequestMapping({"/cpe/upgradeListJsp.do"})
/* 19:   */  public String listJsp(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 20:   */  {
/* 21:21 */    UserDto user = super.getCurrentUser(request);
/* 22:22 */    if (user.getBranchId().equals("0"))
/* 23:23 */      return "/cpe/device/upgradeList.jsp";
/* 24:24 */    return "/cpe/upgradeReadyEdit.do?id=" + user.getBranchId();
/* 25:   */  }
/* 26:   */  
/* 27:   */  @RequestMapping({"/cpe/upgradeList.do"})
/* 28:   */  @ResponseBody
/* 29:29 */  public Map<String, Object> list(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.listByPage(request, model); }
/* 30:   */  
/* 31:   */  @RequestMapping({"/cpe/upgradeReadyAdd.do"})
/* 32:   */  public String readyAdd(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 33:   */  {
/* 34:34 */    model.addAttribute("branchs", this.branchService.findAll());
/* 35:35 */    return "/cpe/device/upgradeAdd.jsp";
/* 36:   */  }
/* 37:   */  
/* 38:   */  @RequestMapping({"/cpe/upgradeUpdate.do"})
/* 39:   */  @ResponseBody
/* 40:40 */  public Map<String, Object> update(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.update(request, model); }
/* 41:   */  
/* 42:   */  @RequestMapping({"/cpe/upgradeReadyEdit.do"})
/* 43:   */  public String readyEdit(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model)
/* 44:   */  {
/* 45:45 */    String id = request.getParameter("id");
/* 46:46 */    model.addAttribute("dto", ((UpgradeService)this.service).findByID(id));
/* 47:47 */    return "/cpe/device/upgradeEdit.jsp";
/* 48:   */  }
/* 49:   */  
/* 50:   */  @RequestMapping({"/cpe/upgradeDelete.do"})
/* 51:   */  @ResponseBody
/* 52:52 */  public Map<String, Object> delete(javax.servlet.http.HttpServletRequest request, org.springframework.ui.ModelMap model) { return super.deleteByIDs(request, model); }
/* 53:   */  
/* 56:   */  @Autowired
/* 57:   */  protected void setService(UpgradeService service)
/* 58:   */  {
/* 59:59 */    this.service = service;
/* 60:   */  }
/* 61:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.UpgradeAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */