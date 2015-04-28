/*  1:   */package com.soofound.operation.web;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.UserDto;
/*  4:   */import com.soofound.framework.web.BaseAction;
/*  5:   */import com.soofound.operation.bean.SsidCodeDto;
/*  6:   */import java.util.Map;
/*  7:   */import javax.servlet.http.HttpServletRequest;
/*  8:   */import org.springframework.beans.factory.annotation.Autowired;
/*  9:   */import org.springframework.stereotype.Controller;
/* 10:   */import org.springframework.ui.ModelMap;
/* 11:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 12:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 13:   */
/* 14:   */@Controller
/* 15:   */public class SsidCodeAction
/* 16:   */  extends BaseAction<SsidCodeService>
/* 17:   */{
/* 18:   */  @RequestMapping({"/cpe/ssidCodeListJsp.do"})
/* 19:   */  public String ssidCodeListJsp(HttpServletRequest request, ModelMap model)
/* 20:   */  {
/* 21:21 */    return "/operation/ssid/list.jsp";
/* 22:   */  }
/* 23:   */  
/* 24:   */  @RequestMapping({"/cpe/ssidCodeList.do"})
/* 25:   */  @ResponseBody
/* 26:26 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 27:   */  
/* 28:   */  @RequestMapping({"/cpe/ssidCodeReadyAdd.do"})
/* 29:   */  public String readyAdd(HttpServletRequest request, ModelMap model)
/* 30:   */  {
/* 31:31 */    return "/operation/ssid/add.jsp";
/* 32:   */  }
/* 33:   */  
/* 34:   */  @RequestMapping({"/cpe/ssidCodeReadyEdit.do"})
/* 35:   */  public String readyEdit(HttpServletRequest request, ModelMap model) {
/* 36:36 */    String id = request.getParameter("id");
/* 37:37 */    model.addAttribute("dto", ((SsidCodeService)this.service).findByID(id));
/* 38:38 */    return "/operation/ssid/edit.jsp";
/* 39:   */  }
/* 40:   */  
/* 41:   */  @RequestMapping({"/cpe/ssidCodeSave.do"})
/* 42:   */  @ResponseBody
/* 43:   */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) {
/* 44:44 */    try { UserDto user = super.getCurrentUser(request);
/* 45:45 */      String ssid = request.getParameter("ssid");
/* 46:46 */      SsidCodeDto dto = ((SsidCodeService)this.service).findBySSID(ssid);
/* 47:47 */      if (dto != null)
/* 48:48 */        return getFailedResult("SSID[" + ssid + "]已经存在");
/* 49:49 */      dto = new SsidCodeDto();
/* 50:50 */      dto.setName(request.getParameter("name"));
/* 51:51 */      dto.setBranchId(user.getBranchId());
/* 52:52 */      dto.setSsid(ssid);
/* 53:53 */      ((SsidCodeService)this.service).save(dto);
/* 54:54 */      return getSucceedResult("增加成功");
/* 55:   */    } catch (Exception ex) {
/* 56:56 */      ex.printStackTrace(); }
/* 57:57 */    return getSucceedResult("增加失败");
/* 58:   */  }
/* 59:   */  
/* 60:   */  @RequestMapping({"/cpe/ssidCodeUpdate.do"})
/* 61:   */  @ResponseBody
/* 62:   */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) {
/* 63:   */    try {
/* 64:64 */      int id = Integer.parseInt(request.getParameter("id"));
/* 65:65 */      String ssid = request.getParameter("ssid");
/* 66:66 */      SsidCodeDto dto = (SsidCodeDto)((SsidCodeService)this.service).findByID(request.getParameter("id"));
/* 67:67 */      if ((dto != null) && (dto.getId() != id)) {
/* 68:68 */        return getFailedResult("SSID[" + ssid + "]已经存在");
/* 69:   */      }
/* 70:70 */      dto = new SsidCodeDto();
/* 71:71 */      dto.setId(id);
/* 72:72 */      dto.setName(request.getParameter("name"));
/* 73:73 */      dto.setSsid(request.getParameter("ssid"));
/* 74:74 */      ((SsidCodeService)this.service).update(dto);
/* 75:75 */      return getSucceedResult("更新成功");
/* 76:   */    } catch (Exception ex) {
/* 77:77 */      ex.printStackTrace(); }
/* 78:78 */    return getSucceedResult("更新失败");
/* 79:   */  }
/* 80:   */  
/* 81:   */  @RequestMapping({"/cpe/ssidCodeDelete.do"})
/* 82:   */  @ResponseBody
/* 83:   */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) {
/* 84:84 */    String[] codes = request.getParameterValues("checkbox");
/* 85:85 */    for (String code : codes) {
/* 86:86 */      if ("1".equals(code))
/* 87:87 */        return getFailedResult("不能删除[默认代码]");
/* 88:   */    }
/* 89:89 */    return deleteByIDs(request, model);
/* 90:   */  }
/* 91:   */  
/* 92:   */  @Autowired
/* 93:   */  protected void setService(SsidCodeService service) {
/* 94:94 */    this.service = service;
/* 95:   */  }
/* 96:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.web.SsidCodeAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */