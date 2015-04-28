<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="${currentTplContext}view/theme/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function openWechat(url, btn){
		if(!document.getElementById('acceptAgreementChk').checked){
			alert("您必须接受《免费Wi-Fi用户使用协议》才能继续使用 Wi-Fi！");
			return;
		}
		location.href="weixin://profile/gh_ef6a7e1945e4";
	}
	/*var wx_url = "weixin://qr/gEx_Zm-EdTlKrSsF9xmS";
	setTimeout(function() {
		//rptk_ios_goto_weixin();
		window.location.href = wx_url;
		//alert("opened");
	}, 2500);*/
	
</script>
</head>
<body class="soof_freego">
    <div class="page_viewport">
        <img class="welcome_img" src="${welcomePic}" />
        <div class="section_jump">
            <div class="welcome_text">${welcomeText}</div>  
            <div class="agreement_ctn">
                <input type="checkbox" id="acceptAgreementChk" checked="checked" />
                <label for="acceptAgreementChk">
                    我已阅读并接受</label> <a href="${base}acs/toWifiUserAgreement.do" style="text-decoration:underline; color:#090;" target="userAgreement" tabindex="-1">《免费Wi-Fi使用协议》</a>
            </div>
            <button class="jump_btn" style=" background-color:${openBtnBgColor}; color:${openBtnTextColor};" onclick="openWechat()">${openBtnText}</button>
            <p class="p5" style="color:#999;">
                如无法直接打开微信，请手动打开。
            </p>
            <p style="line-height:3em; font-size:16px; display:none;">
            <a href="weixin://profile/gh_ef6a7e1945e4">打开微信( by profile)</a><br />
            <a href="weixin://qr/gh_ef6a7e1945e4">打开微信( by qr)</a><br />
            <a href="weixin://qr/gEx_Zm-EdTlKrSsF9xmS">打开微信( by rippletek qr)</a><br />
            <a href="weixin:">打开微信(by weixin:)</a><br />
            <a href="weixin://120.24.77.206/ac/resources/test/WeixinApi/qrcode.html">打开微信(by test url)</a><br />
            <a href="weixin://weixin.qq.com/q/S0gnNEnllrfvOeo6j2Bm">打开微信(by weixin:/q)</a><br />
            <a href="weixin://weixin.qq.com/r/S0gnNEnllrfvOeo6j2Bm">打开微信(by weixin:/r)</a><br />
            <a href="http://weixin.qq.com/q/S0gnNEnllrfvOeo6j2Bm">打开微信(by http:/q)</a><br />
            <a href="http://weixin.qq.com/r/S0gnNEnllrfvOeo6j2Bm">打开微信(by http:/r)</a><br />
            </p>
            <div class="soof_foot">
                <div class="copyright">
                    ${msite_copyright}
                </div>
            </div>
        </div>
    </div>
</body>
</html>