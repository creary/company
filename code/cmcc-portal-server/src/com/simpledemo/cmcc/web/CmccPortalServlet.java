package com.simpledemo.cmcc.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Param;
import org.apache.log4j.Logger;

import com.simpledemo.cmcc.portal.UserLoginProcess;
import com.simpledemo.cmcc.portal.protocol.AbstractProcess.ParamsObject;

/**
 * 
 * Servlet implementation class PortalServlet
 * 
 */
public class CmccPortalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CmccPortalServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Logger log= Logger.getLogger(CmccPortalServlet.class);
		// 参数：wlanacip wlanuserip wlanusermac usermac ssid vlan
		// 参数由AC进行配置
		// 跳转到login页面
		String ssid=request.getParameter("ssid");
		String wlanacname=request.getParameter("wlanacname");
		String wlanuserip=request.getParameter("wlanuserip");
		String wlanusermac=request.getParameter("wlanusermac");
		String apmac=request.getParameter("apmac");
		log.debug("wlanacname 参数:"+wlanacname);
		log.debug("wlanuserip 参数 ："+wlanuserip);
		log.debug("wlanusermac 参数 ："+wlanusermac);
		log.debug("apmac 参数 ："+apmac);
		log.debug("ssid 参数: "+ssid);
		response.sendRedirect(request.getContextPath() + "/portal.jsp?ssid="+ssid+"&wlanacname="+wlanacname+"&wlanuserip="+wlanuserip+"&wlanusermac="+wlanusermac+"&apmac="+apmac);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	static final	Logger log= Logger.getLogger(CmccPortalServlet.class);
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 执行登录
		String ip=request.getParameter("ip");
		int port=Integer.parseInt(request.getParameter("port"));
		String wlanuserip=request.getParameter("wlanuserip");
		log.debug("request.getParameterMap()  ip参数："+ip);
		log.debug("request.getParameterMap()  port参数："+port);
		log.debug("request.getParameterMap()  wlanuserip参数："+wlanuserip);
		ParamsObject params = new ParamsObject();
		log.debug("请求的参数是："+params);
		try {
			params.ip = ip;//"218.204.128.231";
			params.username = new Random().nextInt()+"1";
			params.password = "123456";
			params.userip = wlanuserip;
			params.port = port;
			UserLoginProcess process = new UserLoginProcess(params);
			Object result = process.doHandler();
			log.debug("处理结果:"+result);
			//response.getWriter().print(result);
			if (result == null) {
				response.getWriter().print("OK");
				return;
			}else if("已经验证,不需要再验证".equals(result.toString().trim())){
				response.getWriter().print("OK");
				return;
			}
		}catch (Exception e) {
		//	e.printStackTrace();
			log.debug("异常"+e.getMessage());
		}
		response.getWriter().print("Error");
	}
}
