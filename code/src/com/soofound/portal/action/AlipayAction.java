/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.UserDto;
/*   4:    */import com.soofound.cpe.util.SoofacACS;
/*   5:    */import com.soofound.framework.util.DateUtil;
/*   6:    */import com.soofound.framework.web.GenericAction;
/*   7:    */import com.soofound.portal.bean.TradeDto;
/*   8:    */import com.soofound.portal.service.TradeService;
/*   9:    */import com.soofound.protocol.alipay.AlipayConfig;
/*  10:    */import com.soofound.protocol.alipay.AlipayNotify;
/*  11:    */import com.soofound.protocol.alipay.AlipaySubmit;
/*  12:    */import java.io.PrintStream;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.Iterator;
/*  15:    */import java.util.Map;
/*  16:    */import java.util.Set;
/*  17:    */import javax.servlet.http.HttpServletRequest;
/*  18:    */import org.apache.log4j.Logger;
/*  19:    */import org.springframework.beans.factory.annotation.Autowired;
/*  20:    */import org.springframework.stereotype.Controller;
/*  21:    */import org.springframework.ui.ModelMap;
/*  22:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  23:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  24:    */
/*  25:    */@Controller
/*  26:    */public final class AlipayAction extends GenericAction
/*  27:    */{
/*  28: 28 */  private Logger loger = Logger.getLogger(AlipayAction.class);
/*  29: 29 */  private Map<String, TradeDto> trades = new HashMap();
/*  30:    */  
/*  31:    */  @RequestMapping({"/portal/doPay.do"})
/*  32:    */  @ResponseBody
/*  33:    */  public String doPay(HttpServletRequest request, ModelMap model) {
/*  34: 34 */    try { AlipayConfig cfg = new AlipayConfig();
/*  35:    */      
/*  36: 36 */      String payment_type = "1";
/*  37:    */      
/*  38: 38 */      String notify_url = this.acs.getAcsURL() + "portal/alipayNotify.do";
/*  39: 39 */      String return_url = this.acs.getAcsURL() + "portal/alipayReturn.do";
/*  40:    */      
/*  41: 41 */      String out_trade_no = DateUtil.getCurrentDateTime2();
/*  42:    */      
/*  44: 44 */      String subject = new String("充值,买短信".getBytes("ISO-8859-1"), "UTF-8");
/*  45: 45 */      int quantity = Integer.parseInt(request.getParameter("quantity"));
/*  46:    */      
/*  47: 47 */      float total_fee = quantity * 0.1F;
/*  48: 48 */      String content = "买短信:" + request.getParameter("quantity") + "条";
/*  49:    */      
/*  50: 50 */      String body = new String(content.getBytes("ISO-8859-1"), "UTF-8");
/*  51:    */      
/*  52: 52 */      String show_url = "http://www.wifiant.cn";
/*  53:    */      
/*  55: 55 */      String anti_phishing_key = DateUtil.getCurrentDateTime();
/*  56:    */      
/*  58: 58 */      String exter_invoke_ip = request.getRemoteAddr();
/*  59:    */      
/*  62: 62 */      Map<String, String> paras = new HashMap();
/*  63: 63 */      paras.put("service", "create_direct_pay_by_user");
/*  64: 64 */      paras.put("partner", cfg.getPartner());
/*  65: 65 */      paras.put("seller_email", cfg.getSeller());
/*  66:    */      
/*  67: 67 */      paras.put("_input_charset", AlipayConfig.input_charset);
/*  68: 68 */      paras.put("payment_type", payment_type);
/*  69: 69 */      paras.put("notify_url", notify_url);
/*  70: 70 */      paras.put("return_url", return_url);
/*  71:    */      
/*  72: 72 */      paras.put("out_trade_no", out_trade_no);
/*  73: 73 */      paras.put("subject", subject);
/*  74: 74 */      paras.put("total_fee", total_fee);
/*  75:    */      
/*  76: 76 */      paras.put("body", body);
/*  77: 77 */      paras.put("show_url", show_url);
/*  78: 78 */      paras.put("anti_phishing_key", anti_phishing_key);
/*  79: 79 */      paras.put("exter_invoke_ip", exter_invoke_ip);
/*  80:    */      
/*  81: 81 */      TradeDto dto = new TradeDto();
/*  82: 82 */      UserDto user = super.getCurrentUser(request);
/*  83: 83 */      dto.setOrderNo(out_trade_no);
/*  84: 84 */      dto.setBranchId(user.getBranchId());
/*  85: 85 */      dto.setAmount(quantity);
/*  86: 86 */      dto.setFee(total_fee);
/*  87: 87 */      dto.setState(1);
/*  88: 88 */      dto.setPayWay(2);
/*  89: 89 */      dto.setLogTime(DateUtil.getCurrentDateTime());
/*  90: 90 */      dto.setSummary("购买" + quantity + "条短信");
/*  91: 91 */      this.loger.info(user.getFullname() + " 增加一条订单 :" + dto.getOrderNo() + dto.getSummary());
/*  92: 92 */      this.trades.put(out_trade_no, dto);
/*  93:    */      
/*  94: 94 */      return AlipaySubmit.buildRequest(paras, "get", "确认");
/*  95:    */    } catch (Exception ex) {
/*  96: 96 */      ex.printStackTrace(); }
/*  97: 97 */    return "do pay failed.";
/*  98:    */  }
/*  99:    */  
/* 100:    */  @RequestMapping({"/portal/alipayNotify.do"})
/* 101:    */  @ResponseBody
/* 102:    */  public String alipayNotify(HttpServletRequest request, ModelMap model) {
/* 103:103 */    System.out.println(request.getRemoteAddr() + "-----alipayNotify.do----");
/* 104:    */    
/* 105:105 */    Map<String, String> params = new HashMap();
/* 106:106 */    Map requestParams = request.getParameterMap();
/* 107:107 */    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
/* 108:108 */      String name = (String)iter.next();
/* 109:109 */      String[] values = (String[])requestParams.get(name);
/* 110:110 */      String valueStr = "";
/* 111:111 */      for (int i = 0; i < values.length; i++) {
/* 112:112 */        valueStr = valueStr + values[i] + ",";
/* 113:    */      }
/* 114:    */      
/* 116:116 */      params.put(name, valueStr);
/* 117:    */    }
/* 118:118 */    TradeDto dto = null;
/* 119:    */    try {
/* 120:120 */      String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
/* 121:    */      
/* 123:123 */      String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
/* 124:124 */      dto = (TradeDto)this.trades.remove(out_trade_no);
/* 125:125 */      if (dto == null) {
/* 126:126 */        System.out.println(out_trade_no + " trade fail since no cache");
/* 127:127 */        return "fail";
/* 128:    */      }
/* 129:    */      
/* 131:131 */      String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
/* 132:    */      
/* 133:133 */      if (AlipayNotify.verify(params))
/* 134:    */      {
/* 136:136 */        if (trade_status.equals("TRADE_FINISHED")) {
/* 137:137 */          System.out.println(out_trade_no + " TRADE_FINISHED");
/* 138:138 */          dto.setTradeNo(trade_no);
/* 139:139 */          dto.setState(2);
/* 146:    */        }
/* 147:147 */        else if (trade_status.equals("TRADE_SUCCESS"))
/* 148:    */        {
/* 153:153 */          System.out.println(out_trade_no + " TRADE_FINISHED");
/* 154:154 */          dto.setTradeNo(trade_no);
/* 155:155 */          dto.setState(2);
/* 156:    */        }
/* 157:157 */        return "success";
/* 158:    */      }
/* 159:159 */      dto.setTradeNo(trade_no);
/* 160:160 */      dto.setState(3);
/* 161:    */      
/* 162:162 */      this.service.save(dto);
/* 163:    */    } catch (Exception e) {
/* 164:164 */      e.printStackTrace();
/* 165:    */    }
/* 166:166 */    return "fail";
/* 167:    */  }
/* 168:    */  
/* 169:    */  @RequestMapping({"/portal/alipayReturn.do"})
/* 170:    */  public String alipayReturn(HttpServletRequest request, ModelMap model) {
/* 171:171 */    System.out.println("-------alipayReturn.do------");
/* 172:    */    try
/* 173:    */    {
/* 174:174 */      Map<String, String> params = new HashMap();
/* 175:175 */      Map requestParams = request.getParameterMap();
/* 176:176 */      for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
/* 177:177 */        String name = (String)iter.next();
/* 178:178 */        String[] values = (String[])requestParams.get(name);
/* 179:179 */        String valueStr = "";
/* 180:180 */        for (int i = 0; i < values.length; i++) {
/* 181:181 */          valueStr = 
/* 182:182 */            valueStr + values[i] + ",";
/* 183:    */        }
/* 184:    */        
/* 185:185 */        valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
/* 186:186 */        params.put(name, valueStr);
/* 187:    */      }
/* 188:188 */      TradeDto dto = null;
/* 189:    */      
/* 191:191 */      String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
/* 192:    */      
/* 193:193 */      String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
/* 194:    */      
/* 195:195 */      String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
/* 196:    */      
/* 198:198 */      dto = (TradeDto)this.trades.remove(out_trade_no);
/* 199:199 */      if (dto == null) {
/* 200:200 */        System.out.println(out_trade_no + " trade fail since no cache");
/* 201:201 */        return "fail";
/* 202:    */      }
/* 203:203 */      boolean verify_result = AlipayNotify.verify(params);
/* 204:204 */      if (verify_result)
/* 205:    */      {
/* 206:206 */        if (!trade_status.equals("TRADE_FINISHED")) { trade_status.equals("TRADE_SUCCESS");
/* 207:    */        }
/* 208:    */        
/* 211:211 */        System.out.println(out_trade_no + " TRADE_FINISHED");
/* 212:212 */        dto.setTradeNo(trade_no);
/* 213:213 */        dto.setState(2);
/* 214:214 */        this.service.save(dto);
/* 215:215 */        StringBuilder info = new StringBuilder(100);
/* 216:216 */        info.append("订单:").append(out_trade_no).append(",交易号:").append(trade_no).append(",交易成功<br />");
/* 217:217 */        model.addAttribute("message", info.toString());
/* 218:    */      }
/* 219:    */      else {
/* 220:220 */        dto.setTradeNo(trade_no);
/* 221:221 */        dto.setState(3);
/* 222:222 */        this.service.save(dto);
/* 223:223 */        model.addAttribute("error", "交易失败.");
/* 224:    */      }
/* 225:    */    } catch (Exception e) {
/* 226:226 */      e.printStackTrace();
/* 227:    */    }
/* 228:228 */    return "/common/hint.jsp";
/* 229:    */  }
/* 230:    */  
/* 231:    */  @Autowired
/* 232:    */  private TradeService service;
/* 233:    */  @Autowired
/* 234:    */  private SoofacACS acs;
/* 235:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.AlipayAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */