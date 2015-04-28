/*  1:   */package com.soofound.portal.action;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.UserDto;
/*  4:   */import com.soofound.framework.util.DateUtil;
/*  5:   */import com.soofound.framework.web.BaseAction;
/*  6:   */import com.soofound.portal.bean.TradeDto;
/*  7:   */import com.soofound.portal.service.TradeService;
/*  8:   */import java.util.Map;
/*  9:   */import javax.servlet.http.HttpServletRequest;
/* 10:   */import org.springframework.beans.factory.annotation.Autowired;
/* 11:   */import org.springframework.stereotype.Controller;
/* 12:   */import org.springframework.ui.ModelMap;
/* 13:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 14:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 15:   */
/* 16:   */@Controller
/* 17:   */public final class TradeAction
/* 18:   */  extends BaseAction<TradeService>
/* 19:   */{
/* 20:   */  @RequestMapping({"/portal/readyPayCenter.do"})
/* 21:   */  public String readyPayCenter(HttpServletRequest request, ModelMap model)
/* 22:   */  {
/* 23:23 */    return "/portal/alipay/payCenter.jsp";
/* 24:   */  }
/* 25:   */  
/* 26:   */  @RequestMapping({"/portal/tradeListJsp.do"})
/* 27:   */  public String listJsp(HttpServletRequest request, ModelMap model) {
/* 28:28 */    return "/portal/alipay/tradeList.jsp";
/* 29:   */  }
/* 30:   */  
/* 31:   */  @RequestMapping({"/portal/tradeList.do"})
/* 32:   */  @ResponseBody
/* 33:33 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/* 34:   */  
/* 35:   */  @RequestMapping({"/portal/tradeDelete.do"})
/* 36:   */  @ResponseBody
/* 37:   */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) {
/* 38:38 */    return super.deleteByIDs(request, model);
/* 39:   */  }
/* 40:   */  
/* 41:   */  @RequestMapping({"/portal/tradeRead.do"})
/* 42:   */  public String read(HttpServletRequest request, ModelMap model) {
/* 43:43 */    String id = request.getParameter("id");
/* 44:44 */    model.addAttribute("dto", ((TradeService)this.service).findByID(id));
/* 45:45 */    return "/cpe/device/deviceLogRead.jsp";
/* 46:   */  }
/* 47:   */  
/* 48:   */  @RequestMapping({"/portal/confirmPay.do"})
/* 49:   */  @ResponseBody
/* 50:   */  public Map<String, Object> confirmPay(HttpServletRequest request, ModelMap model) {
/* 51:51 */    try { String orderNo = request.getParameter("radio");
/* 52:52 */      int result = ((TradeService)this.service).confirmPay(orderNo);
/* 53:53 */      if (result == -1)
/* 54:54 */        return super.getFailedResult("该订单已经确认过");
/* 55:55 */      return super.getSucceedResult("充值成功");
/* 56:   */    } catch (Exception e) {
/* 57:57 */      e.printStackTrace(); }
/* 58:58 */    return super.getFailedResult("充值失败");
/* 59:   */  }
/* 60:   */  
/* 61:   */  @RequestMapping({"/portal/payForSms.do"})
/* 62:   */  @ResponseBody
/* 63:   */  public Map<String, Object> payForSms(HttpServletRequest request, ModelMap model) {
/* 64:   */    try {
/* 65:65 */      String quantity = request.getParameter("quantity");
/* 66:66 */      String payway = request.getParameter("payway");
/* 67:67 */      TradeDto dto = new TradeDto();
/* 68:68 */      UserDto user = super.getCurrentUser(request);
/* 69:69 */      dto.setBranchId(user.getBranchId());
/* 70:70 */      dto.setAmount(Integer.parseInt(quantity));
/* 71:71 */      dto.setFee(dto.getAmount() / 10);
/* 72:72 */      dto.setOrderNo(DateUtil.getCurrentDateTime2());
/* 73:73 */      dto.setPayWay(Integer.parseInt(payway));
/* 74:74 */      dto.setLogTime(DateUtil.getCurrentDateTime());
/* 75:75 */      dto.setState(1);
/* 76:76 */      dto.setSummary("购买" + quantity + "条短信");
/* 77:77 */      ((TradeService)this.service).save(dto);
/* 78:78 */      return super.getSucceedResult("充值成功");
/* 79:   */    } catch (Exception e) {
/* 80:80 */      e.printStackTrace(); }
/* 81:81 */    return super.getFailedResult("充值失败");
/* 82:   */  }
/* 83:   */  
/* 84:   */  @Autowired
/* 85:   */  protected void setService(TradeService service)
/* 86:   */  {
/* 87:87 */    this.service = service;
/* 88:   */  }
/* 89:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.TradeAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */