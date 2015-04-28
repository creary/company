<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<%  
    response.setHeader("Cache-Control", "no-cache, no-store"); //HTTP 1.1    
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0    
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${msite_title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="icon" href="${imagePath}wifi_favicon.ico" type="image/x-icon"/>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="${moduleRoot}WelcomeBox/view/Module.jsp" />
</body>
</html>