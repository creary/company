<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.soofound.framework.util.SysConfigHelper"%>
<%@include file="/common/nosession_path.jsp"%>
<%    
   session.setAttribute("SPRING_SECURITY_CONTEXT", null); 
   session.setAttribute("CURRENT_USER", null); 
   request.setAttribute("pcfg",SysConfigHelper.getProjectConfig());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="position: relative; min-height: 100%;">
<head>
    <title>工商银行 WiFi 账户生成系统</title>
    <link rel="shortcut icon" href="${base}${pcfg.favicon}" type="image/x-icon"/> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
    <link href="${cssPath}login.css" rel="stylesheet" type="text/css" />
    <link href="${base}ucs/fly/portal/icbcDeyang/resources/css/mui.css" rel="stylesheet" type="text/css" />
    <link href="${base}ucs/fly/portal/icbcDeyang/resources/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body class="soof_mui_icbc soof_login">
    <div class="soof_upgrade_warning">
        对不起，您的浏览器版本太低，部分功能将无法使用，请升级您的浏览器！<a href="${base}common/upgradeTip.jsp">查看详情</a>
    </div>
    <div class="head head_bordered">
        <div class="logo_wrap">
            <img src="${base}ucs/fly/portal/icbcDeyang/resources/image/logo_titled.png" border="0" class="logo" />
        </div>
    </div>
    <div class="main">
        <div class="login">
            <form name="login" class="login_viewport" action="${base}ucs/fly/portal/login.do" method="post" onsubmit="return check_form()">
                <input type="hidden" name="branch" value="icbcDeyang" />
                <div class="title">请登录</div>
                <c:if test="${error ne null}">	
                    <div class="err_tip">
                        <img src="${imagePath}warning.gif" align="absMiddle" />&nbsp;${error}
                    </div>
                </c:if>
                <div id="inputs" class="inputs">
                    <div class="inp inputline show_mask"> 
                        <img class="icon icon_user" src="${base}resources/image/default/mix/blank.gif" border="0" />
                        <input name="username" id="login_id" tabindex="1" />
                        <label class="mask" for="login_id">用户名</label>
                    </div>
                    <div class="inp inputline show_mask">
                        <img class="icon icon_psw" src="${base}resources/image/default/mix/blank.gif" border="0" />
                        <input name="password" id="password_id" type="password" tabindex="2" autocomplete="off" />
                        <label class="mask" for="password_id">密码</label>
                    </div>                    
                    <div class="inputline submit_wrap">
                        <button type="submit" class="btn primary">登 录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="foot">
    	<div class="copyright">中国工商银行 版权所有</div>
    </div>
</body>
</html>
<script type="text/javascript" src="${base}resources/js/rl/src/open/jquery/jquery.js"></script>
<script type="text/javascript">
document.getElementById('login_id').focus();

function check_form(){
	var login_fld = document.getElementById("login_id");
	var password_fld = document.getElementById("password_id");
	var captcha_fld = document.getElementById("captcha_id");
	if(login_fld.value.length==0){
		alert("用户名不能为空");
		login_fld.focus();
		return false;
	}
	if(password_fld.value.length==0){
		alert("密码不能为空");
		password_fld.focus();
		return false;
	}	
	return true;
}

var maskedInputs = jQuery("input[name='username'], input[name='password'], input[name='captcha']");

function checkMaskDisplay(inp){
	var closestInputCtn = jQuery(inp).closest(".inputline");	
	setTimeout(function(){//defer to get the input value.
		if(inp.value === "")
			closestInputCtn.addClass("show_mask");
		else
			closestInputCtn.removeClass("show_mask");
	}, 10);
}

function checkAllMaskDisplay(){
	maskedInputs.each(function(){
		checkMaskDisplay(this);
	});
}

setTimeout(checkAllMaskDisplay, 100);
jQuery("form[name='login']").on("keyup input", checkAllMaskDisplay);

jQuery(window).on("load", function(){
	setTimeout(function(){
		var preloader = jQuery("#rlPreloader");
		preloader.attr("src", preloader.attr("data-src"));
	}, 500);
});

jQuery("#captcha, #tg_changeCaptcha")
	.on("click", function(){
		jQuery("#captcha")
			.attr("src", '${base}acs/getCheckCode.do?' + (new Date).getTime());
	});
</script>
<script id="rlPreloader" type="text/javascript" defer="defer" async="async" data-main="RealLight" data-rlconfig="${rlConfig}" data-src="${jsPath}rl/src/order.js"></script>
<!-- end: preload rl for caching -->