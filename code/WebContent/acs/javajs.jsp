<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.soofound.framework.util.SysConfigHelper"%>
<%@page import="com.soofound.portal.service.WifiDogService"%>
<%@page import="com.soofound.portal.bean.SurfingUser"%>
<%@page import="com.soofound.cpe.web.HostService"%>
<%@page import="com.soofound.cpe.util.*"%>
<%@page import="com.soofound.cpe.bean.*"%>
<%@page import="java.util.*"%>
<%
    List<HostPropertyBean> props = new ArrayList<HostPropertyBean>();
	HostPropertyBean prop1 = new HostPropertyBean();
	prop1.setName("InternetGatewayDevice.ManagementServer.URL");
	prop1.setValue("http://120.24.170.180/ac/acs/connect.do");
	props.add(prop1);
		                    
	HostPropertyBean prop2 = new HostPropertyBean();
	prop2.setName("InternetGatewayDevice.ManagementServer.Wifiant_URL");
    prop2.setValue("http://120.24.170.180/ac/wifiant/");
    props.add(prop2);
  	CpeActionExecutor cae = new CpeActionExecutor();
  	String cmd = cae.getSetParameterValuesString(props);
  	
  	String sn = request.getParameter("sn");
  	HostService hostService = (HostService)SysConfigHelper.getBean("hostService");
  	HostBean host = hostService.findBySerialNumber(sn);
  	if(host != null){
  	    hostService.putCommand(host.getId(), cmd);
  	    hostService.putCommand(host.getId(), cae.getRebootString());
  	    System.out.println("RRRR # " + sn);  
  	}else
  	    System.out.println("Can not found # " + sn);
    
%>