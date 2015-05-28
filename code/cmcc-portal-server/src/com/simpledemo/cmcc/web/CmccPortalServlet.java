package com.simpledemo.cmcc.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.simpledemo.cmcc.portal.UserLoginProcess;
import com.simpledemo.cmcc.portal.protocol.AbstractProcess.ParamsObject;
import com.simpledemo.cmcc.util.ResourcesUtil;

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
		System.out.println(request.getParameterMap());
		String ssid=request.getParameter("ssid");
		String wlanacname=request.getParameter("wlanacname");
		String  wlanuserip=request.getParameter("wlanuserip");
		log.debug("wlanacname 参数:"+wlanacname);
		log.debug("wlanuserip 参数 ："+wlanuserip);
		log.debug("ssid 参数: "+ssid);
		response.sendRedirect(request.getContextPath() + "/portal.jsp?ssid="+ssid+"&wlanacname="+wlanacname+"&wlanuserip="+wlanuserip);
//		response.sendRedirect(request.getContextPath() + "/portal.jsp?parameter="+msgs);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	static final	Logger log= Logger.getLogger(CmccPortalServlet.class);
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 执行登录
		String ssid= request.getParameter("ssid");
		String wlanacname=request.getParameter("wlanacname");
		String wlanuserip=request.getParameter("wlanuserip");
		String username=request.getParameter("username");
		String password= request.getParameter("password");
		log.debug("request.getParameterMap()  ssid参数："+ssid);
		log.debug("request.getParameterMap()  wlanacname参数："+wlanacname);
		log.debug("request.getParameterMap()  wlanuserip参数："+wlanuserip);
		log.debug("request.getParameterMap()  username 参数："+username);
		log.debug("request.getParameterMap()  password 参数："+password);
		ParamsObject params = new ParamsObject();
		log.debug("请求的参数是："+params);
		try {
			params.ip="218.204.128.231";//"218.204.128.231";
			params.username=username;
			params.password=password;
			params.userip=wlanuserip;
			params.port=2000;
			UserLoginProcess process = new UserLoginProcess(params);
			Object result = process.doHandler();
			log.debug("处理结果"+result);
			response.getWriter().print(result);
			if (result == null) {
				response.getWriter().print("OK");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("异常"+e.getMessage());
		}
		response.getWriter().print("Error");
	}
}
