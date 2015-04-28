<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.soofound.framework.util.ProjectConfig"%>
<%@page import="com.soofound.framework.util.DateUtil"%>
<%@page import="com.soofound.framework.util.SysConfigHelper"%>
<%@include file="/common/nosession_path.jsp"%>
<% 
   if(request.getParameter("error") != null){
	   Exception ex = (Exception)session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
	   if(ex != null){ 
		   if("Bad credentials".equals(ex.getMessage()))
		       request.setAttribute("error", "用户名或密码错误");
		   else if("Captcha is incorrect".equals(ex.getMessage()))
		       request.setAttribute("error", "校验码错误");
	   }   
   }
   request.setAttribute("pcfg",SysConfigHelper.getProjectConfig());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="position: relative; min-height: 100%;">
<head>
<title>${pcfg.product}</title>
<link rel="icon" href="${base}${pcfg.favicon}" type="image/x-icon"/> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<script type="text/javascript">
if(window.top != window){
	window.top.location.href = location.href;
}
</script>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}resources/css/login.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.upgrade_warning{
	display:block;
	position:absolute; 
	z-index:100;
	top:18px; 
	left:30%; 
	border:solid 1px #666; 
	background-color:#FFC; 
	padding:1em;
}
@media only screen{
	.upgrade_warning{
		display:none;
	}
}

/* override defaults */
.main .banner_wrapper{
	background-image:url(${base}resources/project/ehulian/login_banner_bg.jpg);
}
</style>
</head>
<body style="margin-bottom:60px;">
<div class="upgrade_warning">
    对不起，您的浏览器版本太低，部分功能将无法使用，请升级您的浏览器！<a href="${base}common/errors/upgradeTip.jsp">查看详情</a>
</div>
<div class="head">
	<img src="${base}${pcfg.logo1}" border="0" class="logo" />
</div>
<div class="main">
	<div class="banner_wrapper">
        <img class="banner" src="${base}resources/project/ehulian/login_banner.jpg" border="0" />
    </div>
    <div class="login">
        <form name="login" class="login_viewport" action="<c:url value='j_spring_security_check' />" method="post" onsubmit="return check_form()">
            <span class="title">登入</span>
            <div class="err_tip">
            <c:if test="${error ne null}">	
                <img src="${imagePath}warning.gif" align="absMiddle" />&nbsp;${error}
            </c:if>
            </div>
            <div id="inputs" class="inputs">
                <div class="inp inputline show_mask"> 
                    <img class="icon icon_user" src="${base}resources/image/default/mix/blank.gif" border="0" />
                    <input name="j_username" id="login_id" tabindex="1" />
                    <label class="mask" for="login_id">用户名</label>
                </div>
                <div class="inp inputline show_mask">
                    <img class="icon icon_psw" src="${base}resources/image/default/mix/blank.gif" border="0" />
                    <input name="j_password" id="password_id" type="password" tabindex="2" />
                    <label class="mask" for="password_id">密码</label>
                </div>
                <div class="inputline show_mask" style="height:40px;">
                    <div class="inp inp_captcha">
                        <img class="icon icon_arrow" src="${base}resources/image/default/mix/blank.gif" border="0" />
                        <input name="j_captcha" id="captcha_id" tabindex="3" autocomplete="off"  />
                        <label class="mask" for="captcha_id">验证码</label>
                    </div>
                    <img id="captcha" class="captcha" src="${base}acs/getCheckCode.do" data-src="${base}store/getCheckCode.do" title="点击切换" align="absMiddle" /> <a href="javascript:void(0);" id="tg_changeCaptcha">切换图片</a>
                </div>
                <div class="inputline" style="margin-top:40px;">
                    <input type="image" tabindex="5" src="${langImagePath}login/btn_login.gif" />
                </div>
            </div>
        </form>
    </div>
</div>
<div class="foot">${pcfg.copyRight}</div>
<span style="display:none;"><script type="text/javascript">
if(!/127\.0\.0\.1|localhost/i.test(String(location.host))){
	var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
	document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fe8f02f1992768475cc8e1b6635415c3c' type='text/javascript'%3E%3C/script%3E"));
}
</script></span>
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
	if(captcha_fld.value.length==0){
		alert("验证码不能为空");
		captcha_fld.focus();
		return false;
	}
	return true;
}

var maskedInputs = jQuery("input[name='j_username'], input[name='j_password'], input[name='j_captcha']");

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

jQuery(checkAllMaskDisplay);
jQuery("form[name='login']").on("keyup input", checkAllMaskDisplay);

jQuery(window).on("load", function(){
	setTimeout(function(){
		var preloader = jQuery("#rlPreloader");
		preloader.attr("src", preloader.attr("data-src"));
	}, 500);
});

jQuery("#captcha, #tg_changeCaptcha")
	.on("click", function(){
		var imgUrl = jQuery("#captcha").attr("data-src");
		imgUrl += (imgUrl.indexOf("?") > -1 ? "&" : "?" ) + "_r=" + (new Date).getTime();
		jQuery("#captcha")
			.attr("src",  imgUrl);
	});
</script>
<script id="rlPreloader" type="text/javascript" defer="defer" async="async" data-main="RealLight" data-rlconfig="${rlConfig}" data-src="${jsPath}rl/src/order.js"></script>
<!-- end: preload rl for caching -->
