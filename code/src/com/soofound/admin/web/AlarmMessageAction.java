/*  1:   */package com.soofound.admin.web;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.AlarmMessage;
/*  4:   */import com.soofound.admin.bean.UserDto;
/*  5:   */import com.soofound.framework.util.DateUtil;
/*  6:   */import com.soofound.framework.web.BaseAction;
/*  7:   */import java.util.HashMap;
/*  8:   */import java.util.List;
/*  9:   */import java.util.Map;
/* 10:   */import javax.servlet.http.HttpServletRequest;
/* 11:   */import org.springframework.beans.factory.annotation.Autowired;
/* 12:   */import org.springframework.stereotype.Controller;
/* 13:   */import org.springframework.ui.ModelMap;
/* 14:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 15:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 16:   */
/* 17:   */@Controller
/* 18:   */public class AlarmMessageAction extends BaseAction<AlarmMessageService>
/* 19:   */{
/* 20:   */  @RequestMapping({"/admin/getAnnouncements.do"})
/* 21:   */  @ResponseBody
/* 22:   */  public Map<String, Object> getAnnouncements(HttpServletRequest request, ModelMap model)
/* 23:   */  {
/* 24:24 */    String start = request.getParameter("start");
/* 25:25 */    String count = request.getParameter("count");
/* 26:26 */    UserDto user = super.getCurrentUser(request);
/* 27:27 */    if (count == null)
/* 28:28 */      count = "10";
/* 29:29 */    if (start == null)
/* 30:30 */      start = "0";
/* 31:31 */    List<AlarmMessage> dtos = ((AlarmMessageService)this.service).getMessages(user.getUsername(), count, start);
/* 32:32 */    Map<String, Object> results = new HashMap();
/* 33:33 */    Map<String, Integer> total = new HashMap();
/* 34:34 */    total.put("total", Integer.valueOf(((AlarmMessageService)this.service).getMessageTotal()));
/* 35:35 */    results.put("meta", total);
/* 36:36 */    results.put("records", dtos);
/* 37:37 */    return results;
/* 38:   */  }
/* 39:   */  
/* 40:   */  @RequestMapping({"/admin/getUnreadAnnouncementsCount.do"})
/* 41:   */  @ResponseBody
/* 42:42 */  public Map<String, Object> getUnreadAnnouncementsCount(HttpServletRequest request, ModelMap model) { UserDto user = super.getCurrentUser(request);
/* 43:43 */    Map<String, Object> result = new HashMap();
/* 44:44 */    result.put("status", Integer.valueOf(1));
/* 45:45 */    result.put("data", Integer.valueOf(((AlarmMessageService)this.service).getUnreadMessageTotal(user.getUsername())));
/* 46:46 */    return result;
/* 47:   */  }
/* 48:   */  
/* 49:   */  @RequestMapping({"/admin/updateBatchAnnouncements.do"})
/* 50:   */  @ResponseBody
/* 51:51 */  public Map<String, Object> updateBatchAnnouncements(HttpServletRequest request, ModelMap model) { UserDto user = super.getCurrentUser(request);
/* 52:52 */    String jsonData = request.getParameter("data");
/* 53:53 */    return ((AlarmMessageService)this.service).updateBatchAnnouncements(user.getUsername(), jsonData);
/* 54:   */  }
/* 55:   */  
/* 56:   */  @RequestMapping({"/admin/announcementListJsp.do"})
/* 57:   */  public String listJsp(HttpServletRequest request, ModelMap model) {
/* 58:58 */    return "/admin/alarm/list.jsp";
/* 59:   */  }
/* 60:   */  
/* 61:   */  @RequestMapping({"/admin/announcementList.do"})
/* 62:   */  @ResponseBody
/* 63:63 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 64:   */  
/* 65:   */  @RequestMapping({"/admin/announcementReadyAdd.do"})
/* 66:   */  public String readyAdd(HttpServletRequest request, ModelMap model)
/* 67:   */  {
/* 68:68 */    model.addAttribute("createTime", DateUtil.getCurrentDateTime());
/* 69:69 */    return "/admin/alarm/add.jsp";
/* 70:   */  }
/* 71:   */  
/* 72:   */  @RequestMapping({"/admin/announcementReadyEdit.do"})
/* 73:   */  public String readyEdit(HttpServletRequest request, ModelMap model) {
/* 74:74 */    String id = request.getParameter("id");
/* 75:75 */    model.addAttribute("dto", ((AlarmMessageService)this.service).findByID(id));
/* 76:76 */    return "/admin/alarm/edit.jsp";
/* 77:   */  }
/* 78:   */  
/* 79:   */  @RequestMapping({"/admin/announcementSave.do"})
/* 80:   */  @ResponseBody
/* 81:81 */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) { return super.save(request, model); }
/* 82:   */  
/* 83:   */  @RequestMapping({"/admin/announcementUpdate.do"})
/* 84:   */  @ResponseBody
/* 85:   */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) {
/* 86:86 */    return super.update(request, model);
/* 87:   */  }
/* 88:   */  
/* 89:   */  @RequestMapping({"/admin/announcementDelete.do"})
/* 90:   */  @ResponseBody
/* 91:91 */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) { return super.deleteByIDs(request, model); }
/* 92:   */  
/* 93:   */  @Autowired
/* 94:   */  protected void setService(AlarmMessageService service)
/* 95:   */  {
/* 96:96 */    this.service = service;
/* 97:   */  }
/* 98:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.AlarmMessageAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */