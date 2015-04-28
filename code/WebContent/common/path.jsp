<%@page language="java" pageEncoding="utf-8"%>
<%@page import="com.soofound.admin.bean.UserDto"%>
<%@page import="java.util.Locale"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%
	UserDto curUser = (UserDto)request.getSession().getAttribute("CURRENT_USER");
	String url = request.getRequestURL().toString();
	if(curUser == null && !url.endsWith("/") && url.indexOf("login.jsp") == -1){
		response.sendRedirect("../login.jsp");
	    return;
	}   
	String lang = "zh-cn";
	String theme = "blue";	
	if(curUser != null){
	    request.setAttribute("user", curUser); 
	    request.setAttribute("branchId",curUser.getBranchId());	    
	}    
	String rootPath = request.getContextPath() + "/";
    String imagePath= rootPath + "resources/image/default/icon/";
	String cssPath= rootPath + "resources/css/";
	String jsPath= rootPath + "resources/js/";
	String cacheBuster = "20150322";
	
	StringBuilder rlConfig = new StringBuilder();
	rlConfig.append("{");
	rlConfig.append("projectName : 'cloudac',");
	rlConfig.append("debugMode : true,");
	rlConfig.append("disableCache : '").append(cacheBuster).append("',");	
	rlConfig.append("lang : '").append(lang).append("',");
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
	
	String langImagePath = rootPath + "resources/image/lang/" + lang + "/";
	request.setAttribute("langImagePath",  langImagePath);
%>