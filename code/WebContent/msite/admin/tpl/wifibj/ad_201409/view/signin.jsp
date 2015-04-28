<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${msite_title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<link rel="icon" href="${imagePath}wifi_favicon.ico" type="image/x-icon"/>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${currentTplContext}view/theme/default/login.css" rel="stylesheet" type="text/css" />
</head>
<body class="smart_layout">
<div class="head">
	<img src="${msite_logo}" border="0" class="logo" />
</div>
<div class="main">
    <div class="login">
    	<div class="login_viewport">
            <span style="font-size:18px;">登录${wechat_public_name}WiFi</span>
            <jsp:include page="${moduleRoot}SignInTabPanel/view/Module.jsp" />
        </div>
    </div>
</div>
<div class="foot">${msite_copyright}</div>
</body>
</html>