/*   1:    */package com.soofound.admin.web;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.GroupDto;
/*   4:    */import com.soofound.admin.bean.UserDto;
/*   5:    */import com.soofound.framework.web.BaseAction;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Map;
/*   8:    */import javax.servlet.http.HttpServletRequest;
/*   9:    */import org.springframework.beans.factory.annotation.Autowired;
/*  10:    */import org.springframework.stereotype.Controller;
/*  11:    */import org.springframework.ui.ModelMap;
/*  12:    */import org.springframework.web.bind.annotation.PathVariable;
/*  13:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  14:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  15:    */
/*  18:    */@Controller
/*  19:    */public class GroupAction
/*  20:    */  extends BaseAction<GroupService>
/*  21:    */{
/*  22:    */  @RequestMapping({"/group/readyTree.do"})
/*  23: 23 */  public String getGroupTree(HttpServletRequest request, ModelMap model) { return "/admin/group/group.jsp"; }
/*  24:    */  
/*  25:    */  @RequestMapping({"/group/query.do"})
/*  26:    */  @ResponseBody
/*  27:    */  public Map<String, Object> getGroupByFlag(HttpServletRequest request) {
/*  28: 28 */    UserDto user = super.getCurrentUser(request);
/*  29: 29 */    return bornData(((GroupService)this.service).findByBranch(user.getBranchId()));
/*  30:    */  }
/*  31:    */  
/*  32:    */  @RequestMapping({"/group/{pid}/create.do"})
/*  33:    */  @ResponseBody
/*  34:    */  public Map<String, Object> save(@PathVariable String pid, HttpServletRequest request) {
/*  35: 35 */    try { String name = request.getParameter("name");
/*  36: 36 */      UserDto user = super.getCurrentUser(request);
/*  37: 37 */      GroupDto bgd = new GroupDto();
/*  38: 38 */      bgd.setId(((GroupService)this.service).getNextID(pid));
/*  39: 39 */      if (bgd.getId().length() >= 20) {
/*  40: 40 */        return super.getFailedResult("增加失败,分组层次不大于5");
/*  41:    */      }
/*  42: 42 */      bgd.setPid(pid);
/*  43: 43 */      bgd.setName(name);
/*  44: 44 */      bgd.setBranchId(user.getBranchId());
/*  45: 45 */      int result = ((GroupService)this.service).save(bgd);
/*  46: 46 */      if (result > 0) {
/*  47: 47 */        Map<String, Object> results = new HashMap();
/*  48: 48 */        results.put("status", Integer.valueOf(1));
/*  49: 49 */        results.put("data", bgd.getId());
/*  50: 50 */        return results;
/*  51:    */      }
/*  52: 52 */      return getFailedResult("增加失败");
/*  53:    */    } catch (Exception ex) {
/*  54: 54 */      ex.printStackTrace(); }
/*  55: 55 */    return getFailedResult("增加失败");
/*  56:    */  }
/*  57:    */  
/*  58:    */  @RequestMapping({"/group/{id}/update.do"})
/*  59:    */  @ResponseBody
/*  60:    */  public Map<String, Object> update(@PathVariable String id, HttpServletRequest request) {
/*  61:    */    try {
/*  62: 62 */      GroupDto gd = new GroupDto();
/*  63: 63 */      gd.setName(request.getParameter("name"));
/*  64: 64 */      gd.setId(id);
/*  65: 65 */      ((GroupService)this.service).update(gd);
/*  66: 66 */      return getSucceedResult("更新成功");
/*  67:    */    } catch (Exception ex) {
/*  68: 68 */      ex.printStackTrace(); }
/*  69: 69 */    return getFailedResult("更新失败");
/*  70:    */  }
/*  71:    */  
/*  72:    */  @RequestMapping({"/group/{id}/delete.do"})
/*  73:    */  @ResponseBody
/*  74:    */  public Map<String, Object> delete(@PathVariable String id, HttpServletRequest request, ModelMap model) {
/*  75:    */    try {
/*  76: 76 */      ((GroupService)this.service).delete(id);
/*  77: 77 */      return getSucceedResult(id);
/*  78:    */    } catch (Exception ex) {
/*  79: 79 */      ex.printStackTrace(); }
/*  80: 80 */    return getFailedResult("删除失败");
/*  81:    */  }
/*  82:    */  
/*  83:    */  @RequestMapping({"/group/save.do"})
/*  84:    */  @ResponseBody
/*  85:    */  public Map<String, Object> getHosts(HttpServletRequest request, ModelMap model) {
/*  86:    */    try {
/*  87: 87 */      String gid = request.getParameter("gid");
/*  88: 88 */      String branchId = request.getParameter("branchId");
/*  89: 89 */      String[] selectedItems = request.getParameterValues("selectedItems");
/*  90: 90 */      ((GroupService)this.service).updateGroup(branchId, gid, selectedItems);
/*  91: 91 */      return getSucceedResult("更新成功.");
/*  92:    */    } catch (Exception ex) {
/*  93: 93 */      ex.printStackTrace(); }
/*  94: 94 */    return getFailedResult("更新失败.");
/*  95:    */  }
/*  96:    */  
/*  97:    */  @Autowired
/*  98:    */  protected void setService(GroupService service)
/*  99:    */  {
/* 100:100 */    this.service = service;
/* 101:    */  }
/* 102:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.GroupAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */