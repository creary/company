<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.soofound.framework.util.DateUtil"%>
<%@page import="com.soofound.protocol.alipay.AlipaySubmit"%>
<%
	Map<String, String> paras = new HashMap<String, String>();
	paras.put("service", "create_direct_pay_by_user");
	paras.put("payment_type", "1");		
	paras.put("show_url", "afunms");
	paras.put("anti_phishing_key", DateUtil.getCurrentDateTime());
	paras.put("exter_invoke_ip", request.getRemoteAddr());
	paras.put("notify_url", "http://www.wifiant.cn/wifiant/portal/alipayNotify.do");
	paras.put("return_url", "http://www.wifiant.cn/wifiant/portal/alipayReturn.do");			
	paras.put("_input_charset", "GB2312");
	paras.put("partner", "2088701558276942");
	paras.put("seller_email", "redlink99@163.com");
	
	String body = request.getParameter("quantity") + "条短信";
	paras.put("body",body);
	paras.put("out_trade_no", request.getParameter("out_trade_no"));
	paras.put("subject", "afunms@0000100001");
	float fee =  Integer.parseInt(request.getParameter("quantity")) * 0.1F;
	paras.put("total_fee",fee + "");
			
	String aliReq = AlipaySubmit.buildRequest(paras,"get","确认");
	out.println(aliReq);
%>