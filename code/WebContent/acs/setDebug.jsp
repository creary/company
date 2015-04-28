<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.net.*"%>
<%@page import="java.util.*"%>
<%@page import="com.soofound.cpe.web.*"%>
<%@page import="com.soofound.portal.action.*"%>
<%@page import="com.soofound.portal.service.*"%>
<%@page import="com.soofound.framework.util.*"%>
<%@page import="com.soofound.portal.dao.*"%>
<%@page import="com.soofound.cpe.util.*"%>
<%
    String mac = request.getParameter("mac");
    String reboot = request.getParameter("reboot");
    if(reboot == null){
	    CPEConnectAction cca = (CPEConnectAction)SysConfigHelper.getBean("CPEConnectAction");
	    if("".equals(mac))
	        cca.setDebugApmac(null);
	    else
	        cca.setDebugApmac(mac);
    }else{
	    WifiDogController wdc = (WifiDogController)SysConfigHelper.getBean("wifiDogController");
	    wdc.setRebootApmac(mac);   
    }
%>
