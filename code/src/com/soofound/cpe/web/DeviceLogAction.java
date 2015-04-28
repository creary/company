/*  1:   */package com.soofound.cpe.web;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseAction;
/*  4:   */import java.util.Map;
/*  5:   */import javax.servlet.http.HttpServletRequest;
/*  6:   */import org.springframework.beans.factory.annotation.Autowired;
/*  7:   */import org.springframework.stereotype.Controller;
/*  8:   */import org.springframework.ui.ModelMap;
/*  9:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 10:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 11:   */
/* 12:   */@Controller
/* 13:   */public final class DeviceLogAction
/* 14:   */  extends BaseAction<DeviceLogService>
/* 15:   */{
/* 16:   */  @RequestMapping({"/cpe/deviceLogListJsp.do"})
/* 17:   */  public String listJsp(HttpServletRequest request, ModelMap model)
/* 18:   */  {
/* 19:19 */    return "/cpe/device/deviceLog.jsp";
/* 20:   */  }
/* 21:   */  
/* 22:   */  @RequestMapping({"/cpe/deviceLogList.do"})
/* 23:   */  @ResponseBody
/* 24:24 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 25:   */  
/* 26:   */  @RequestMapping({"/cpe/deviceLogDelete.do"})
/* 27:   */  @ResponseBody
/* 28:   */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) {
/* 29:29 */    return super.deleteByIDs(request, model);
/* 30:   */  }
/* 31:   */  
/* 32:   */  @Autowired
/* 33:   */  protected void setService(DeviceLogService service) {
/* 34:34 */    this.service = service;
/* 35:   */  }
/* 36:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.DeviceLogAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */