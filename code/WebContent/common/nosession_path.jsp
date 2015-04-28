<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%	
	String clientLang = "zh-cn";
	String theme = "blue";	

	String rootPath = request.getContextPath() + "/";
    String imagePath= rootPath + "resources/image/default/icon/";
	String cssPath= rootPath + "resources/css/";
	String jsPath= rootPath + "resources/js/";
	String cacheBuster = "20150414-3";
	
	StringBuilder rlConfig = new StringBuilder();
	rlConfig.append("{");
	rlConfig.append("projectName : 'cloudac',");
	rlConfig.append("debugMode : false,");
	rlConfig.append("disableCache : '").append(cacheBuster).append("',");	
	rlConfig.append("lang : '").append(clientLang).append("',");
	rlConfig.append("autoLoadLang : false,");	
	rlConfig.append("theme : '").append(theme).append("',");	
	rlConfig.append("jsRoot : '../../cloudac/',");
	rlConfig.append("cssRoot : '../../../css/',");
	rlConfig.append("pageInc : 'cloudac:pageInc'");
	rlConfig.append("}");
	
	request.setAttribute("base",rootPath);
	request.setAttribute("imagePath",imagePath);
	request.setAttribute("cssPath",cssPath);
	request.setAttribute("jsPath",jsPath);	
	request.setAttribute("cacheBuster",cacheBuster);	
	request.setAttribute("rlConfig",rlConfig.toString());
	
	String langImagePath = rootPath + "resources/image/lang/" + clientLang + "/";
    request.setAttribute("langImagePath",  langImagePath);  
%>
