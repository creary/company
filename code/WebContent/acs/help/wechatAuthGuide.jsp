<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<%
   if(request.getParameter("guideType") != null)
       request.setAttribute("guideType", request.getParameter("guideType"));    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>免费 WiFi 上网</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="icon" href="${imagePath}wifi_favicon.ico" type="image/x-icon"/>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${jsPath}rl/src/open/jQuery-idTabs/jquery_idtabs.css"/>
<style type="text/css">
body{
	font-size:14px;
}
h2{
	font-size:20px;
}
.header{
	background-color:#f8f8f8;
	border-bottom:solid 1px #ddd;
}
.header .title{
	padding:2em 0 1em;
	text-align:center;
}
.header .subtitle{
	margin-top:.5em;
	line-height:20px;
}
.content{
	line-height:20px;
	padding:2em 1em;
}
.section{
	border-top:solid 1px #eee;
}
.section.first{
	border-top:none;
}
.img_ctn{
	padding:1em;
	text-align:center;
}
.img_ctn img{
	border:solid 1px #eee;
	border-top:none;
}
/* override jqidtabs defaults */
.jquery_idtabs.fullfit .tabbar a{
	padding:15px 12px;
}
.jquery_idtabs.fullfit .tabbar a.selected{
	border-color:#0C0;
	color:#0C0;
}

</style>
<script type="text/javascript" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs.config("shim", {
	"open.jquery.jquery" : {
		exports : "jQuery"
	}
});
orderjs(
	"open.jquery.jquery",
	"open.jQuery-idTabs.idTabs-ordered"
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery");
	orderjs.ready(function() {
		jQuery("#guideContent").idTabs();
	});
});

</script>
</head>
<body>
<div class="main">
    <div class="mbody_wrapper">
        <c:if test="${guideType eq 'custom'}">
            ${customGuide}
        </c:if>
        <c:if test="${guideType eq 'default'}">
            <div class="header">
                <div class="title">
                    <h2>欢迎使用本店免费 WiFi!</h2>
                    <div class="subtitle">
                        我们支持以下三种微信认证方式，<br />
                        请任选一种，通过认证后即可快速上网！
                    </div>
                </div>
            </div>
            <div id="guideContent" class="jquery_idtabs fullfit">
                <table class="tabbar" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <a class="selected" href="#section_scan">扫一扫上网</a>
                        </td>
                        <td>
                            <a href="#section_onekey">一键上网</a>
                        </td>
                        <td>
                            <a href="#section_input">回复即上网</a>
                        </td>
                    </tr>
                </table>
                <div class="content" id="section_scan">
                    <div class="section first">
                    	<p>扫一扫上网是指：您只需用微信扫一扫我们的二维码，关注后，即可上网。详细步骤如下：</p>
                    </div>
                    <div class="section">
                        <p>1. 打开微信，扫一扫本店提供的二维码，关注我们即可上网；如已是粉丝，扫一扫后可直接上网。</p>
                        <div class="img_ctn">
                            <img src="${base}resources/image/default/help/wechat_auth_steps_follow.jpg" width="200" height="356" />
                        </div>
                    </div>
                    <div class="section">
                        <p>2. 显示欢迎图片，验证通过！如是电脑上网，请在电脑端的登录界面输入返回的用户名和密码。</p>
                        <div class="img_ctn">
                            <img src="${base}resources/image/default/help/wechat_auth_succes.jpg" width="200" height="356" />
                        </div>
                    </div>
                </div>
                <div class="content" id="section_onekey">
                    <div class="section first">
                    	一键上网是指：在关注我们的微信公众号后，您只需在我们微信公众号的菜单中，点击“一键上网”菜单项，即可上网。详细步骤如下：
                    </div>
                    <div class="section">
                        <p>1. 打开微信，扫一扫本店提供的二维码，关注我们，如已是粉丝，可跳过此步。</p>
                        <div class="img_ctn">
                            <img src="${base}resources/image/default/help/wechat_auth_steps_follow.jpg" width="200" height="356" />
                        </div>
                    </div>
                    <div class="section">
                        <p>2. 点击“我要上网”菜单！</p>
                        <div class="img_ctn">
                            <img src="${base}resources/image/default/help/wechat_auth_steps_clickonekey.jpg" width="200" height="356" />
                        </div>
                    </div>
                    <div class="section">
                        <p>3. 显示欢迎图片，验证通过！如是电脑上网，请在电脑端的登录界面输入返回的用户名和密码。</p>
                        <div class="img_ctn">
                            <img src="${base}resources/image/default/help/wechat_auth_succes.jpg" width="200" height="356" />
                        </div>
                    </div>
                </div>
                <div class="content" id="section_input">
                    <div class="section first">
                    	<p>回复即上网是指：在关注我们的微信公众号后，您只需在我们微信公众号中，回复"wifi"即可上网。详细步骤如下：</p>
                    </div>
                    <div class="section">
                        <p>1. 打开微信，扫一扫本店提供的二维码，关注我们，如已是粉丝，可跳过此步。</p>
                        <div class="img_ctn">
                            <img src="${base}resources/image/default/help/wechat_auth_steps_follow.jpg" width="200" height="356" />
                        </div>
                    </div>
                    <div class="section">
                        <p>2. 发送消息：wifi</p>
                        <div class="img_ctn">
                            <img src="${base}resources/image/default/help/wechat_auth_steps_sendmsg.jpg" width="200" height="356" />
                        </div>
                    </div>
                    <div class="section">
                        <p>3. 显示欢迎图片，验证通过！如是电脑上网，请在电脑端的登录界面输入返回的用户名和密码。</p>
                        <div class="img_ctn">
                            <img src="${base}resources/image/default/help/wechat_auth_succes.jpg" width="200" height="356" />
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
</div>
</body>
</html>