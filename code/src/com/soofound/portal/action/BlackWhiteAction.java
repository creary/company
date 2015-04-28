/*  1:   */package com.soofound.portal.action;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.UserDto;
/*  4:   */import com.soofound.framework.util.DateUtil;
/*  5:   */import com.soofound.framework.web.BaseAction;
/*  6:   */import com.soofound.portal.bean.BlackWhiteDto;
/*  7:   */import com.soofound.portal.service.BlackWhiteService;
/*  8:   */import java.util.Map;
/*  9:   */import javax.servlet.http.HttpServletRequest;
/* 10:   */import org.springframework.beans.factory.annotation.Autowired;
/* 11:   */import org.springframework.stereotype.Controller;
/* 12:   */import org.springframework.ui.ModelMap;
/* 13:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 14:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 15:   */
/* 16:   */@Controller
/* 17:   */public class BlackWhiteAction
/* 18:   */  extends BaseAction<BlackWhiteService>
/* 19:   */{
/* 20:   */  @RequestMapping({"/portal/blackWhiteListJsp.do"})
/* 21:   */  public String listJsp(HttpServletRequest request, ModelMap model)
/* 22:   */  {
/* 23:23 */    model.addAttribute("bw", request.getParameter("bw"));
/* 24:24 */    if ("1".equals(request.getParameter("bw"))) {
/* 25:25 */      model.addAttribute("title", "白名单");
/* 26:   */    } else
/* 27:27 */      model.addAttribute("title", "黑名单");
/* 28:28 */    return "/portal/surfing/blackWhiteList.jsp";
/* 29:   */  }
/* 30:   */  
/* 31:   */  @RequestMapping({"/portal/blackWhiteList.do"})
/* 32:   */  @ResponseBody
/* 33:33 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 34:   */  
/* 35:   */  @RequestMapping({"/portal/blackWhiteReadyAdd.do"})
/* 36:   */  public String readyAdd(HttpServletRequest request, ModelMap model)
/* 37:   */  {
/* 38:38 */    model.addAttribute("bw", request.getParameter("bw"));
/* 39:39 */    return "/portal/surfing/blackWhiteAdd.jsp";
/* 40:   */  }
/* 41:   */  
/* 42:   */  @RequestMapping({"/portal/blackWhiteReadyEdit.do"})
/* 43:   */  public String readyEdit(HttpServletRequest request, ModelMap model) {
/* 44:44 */    String id = request.getParameter("id");
/* 45:45 */    model.addAttribute("dto", ((BlackWhiteService)this.service).findByID(id));
/* 46:46 */    model.addAttribute("curTime", DateUtil.getCurrentDateTime());
/* 47:47 */    return "/portal/surfing/blackWhiteEdit.jsp";
/* 48:   */  }
/* 49:   */  
/* 50:   */  @RequestMapping({"/portal/blackWhiteSave.do"})
/* 51:   */  @ResponseBody
/* 52:52 */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) { UserDto user = super.getCurrentUser(request);
/* 53:53 */    int bw = Integer.parseInt(request.getParameter("bw"));
/* 54:54 */    String mac = request.getParameter("mac");
/* 55:55 */    BlackWhiteDto dto = ((BlackWhiteService)this.service).findByMac(user.getBranchId(), mac, bw);
/* 56:56 */    if (dto != null) {
/* 57:57 */      return getFailedResult("[" + mac + "]已经存在");
/* 58:   */    }
/* 59:59 */    return super.save(request, model);
/* 60:   */  }
/* 61:   */  
/* 62:   */  @RequestMapping({"/portal/blackWhiteUpdate.do"})
/* 63:   */  @ResponseBody
/* 64:64 */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) { UserDto user = super.getCurrentUser(request);
/* 65:65 */    int bw = Integer.parseInt(request.getParameter("bw"));
/* 66:66 */    String mac = request.getParameter("mac");
/* 67:67 */    String id = request.getParameter("id");
/* 68:68 */    BlackWhiteDto bwd = ((BlackWhiteService)this.service).findByMac(user.getBranchId(), mac, bw);
/* 69:69 */    if ((bwd != null) && (bwd.getId() != Integer.parseInt(id))) {
/* 70:70 */      return getFailedResult("[" + mac + "]已经存在");
/* 71:   */    }
/* 72:72 */    return super.update(request, model);
/* 73:   */  }
/* 74:   */  
/* 75:   */  @RequestMapping({"/portal/blackWhiteDelete.do"})
/* 76:   */  @ResponseBody
/* 77:77 */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) { return super.deleteByIDs(request, model); }
/* 78:   */  
/* 79:   */  @RequestMapping({"/portal/blackWhiteBatchAdd.do"})
/* 80:   */  @ResponseBody
/* 81:   */  public Map<String, Object> blackListAdd(HttpServletRequest request, ModelMap model) {
/* 82:   */    try {
/* 83:83 */      int bw = Integer.parseInt(request.getParameter("bw"));
/* 84:84 */      String macstr = request.getParameter("mac");
/* 85:85 */      String[] macs = macstr.split(",");
/* 86:86 */      UserDto user = super.getCurrentUser(request);
/* 87:87 */      ((BlackWhiteService)this.service).setBlackWhites(macs, user.getBranchId(), bw);
/* 88:88 */      return super.getSucceedResult("操作成功");
/* 89:   */    } catch (Exception e) {
/* 90:90 */      e.printStackTrace(); }
/* 91:91 */    return super.getSucceedResult("操作失败");
/* 92:   */  }
/* 93:   */  
/* 94:   */  @Autowired
/* 95:   */  protected void setService(BlackWhiteService service)
/* 96:   */  {
/* 97:97 */    this.service = service;
/* 98:   */  }
/* 99:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.BlackWhiteAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */