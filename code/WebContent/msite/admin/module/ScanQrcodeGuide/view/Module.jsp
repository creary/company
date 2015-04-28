<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function openWechat(url, btn){
		if(!document.getElementById('ScanQrcodeGuide_acceptAgreementChk').checked){
			alert("您必须接受《免费Wi-Fi用户使用协议》才能继续使用 Wi-Fi！");
			return;
		}
		//location.href="weixin://profile/gh_ef6a7e1945e4";
		location.href="weixin://";
		//location.href="http://weixin.qq.com/q/S0gnNEnllrfvOeo6j2Bm";
	}
</script>
<div class="soof_welcome_freego">
    <div class="page_viewport">
        <img class="welcome_img" src="${ScanQrcodeGuide_guidePic}" />
        <div class="section_jump">
            <div class="welcome_text">${ScanQrcodeGuide_guideText}</div>  
            <div class="agreement_ctn">
                <input type="checkbox" id="ScanQrcodeGuide_acceptAgreementChk" checked="checked" />
                <label for="acceptAgreementChk">
                    我已阅读并接受</label> <a href="${base}acs/toWifiUserAgreement.do" style="text-decoration:underline; color:#090;" target="userAgreement" tabindex="-1">《免费Wi-Fi使用协议》</a>
            </div>
            <button class="jump_btn" style=" background-color:${ScanQrcodeGuide_openBtnBgColor}; color:${ScanQrcodeGuide_openBtnTextColor};" onclick="openWechat()">${ScanQrcodeGuide_openBtnText}</button>
            <p class="p5" style="color:#999;">
                如无法直接打开微信，请手动打开。
            </p>
            <div class="soof_foot">
                <div class="copyright">
                    ${msite_copyright}
                </div>
            </div>
        </div>
    </div>
</div>
