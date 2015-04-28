﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<link href="${jsPath}rl/src/open/jQuery-idTabs/jquery_idtabs.css" rel="stylesheet" type="text/css" />
<link href="${moduleContext}SignInTabPanel_s/view/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
/* override defaults with user setting */
.soof_SignInTabPanel_s .btn{
	background-color:${SignInTabPanel_s_btnBgColor}; 
	color:${SignInTabPanel_s_btnTextColor};
}
.soof_SignInTabPanel_s.hat ul.tabbar a{
	padding:8px 10px;
	line-height:1em;
}
.soof_SignInTabPanel_s.hat ul.tabbar a.selected{
	padding:7px 9px 8px 9px;
}
</style>
<div>
    <div id="SignInTabPanel_s" class="soof_SignInTabPanel_s jquery_idtabs hat">
        <ul class="tabbar">
            <c:if test="${smsAuth}">
                <li><a class="selected" href="#tbox_tab1">手机</a></li>
            </c:if>
            <c:if test="${wechatAuth}">
                <li><a href="#tbox_tab2">微信</a></li>
            </c:if>
            <c:if test="${pwdAuth}">
                <li><a href="#tbox_tab3">账户</a></li>
            </c:if>
        </ul>
        <c:if test="${smsAuth}">
            <div class="tbody" id="tbox_tab1" style="display:block;">
                <form name="phone" class="deco_tab_body" method="post" onsubmit="return submitForm(this);" autocomplete="off">
                	<input type="hidden" name="captcha" id="field_captcha_phone" />
                    <p>输入手机号码，然后点击【获取密码】按钮，系统将把密码发送到您输入的手机。</p>
                    <div class="inp inputline show_mask">
                        <span class="icon icon_phone"></span>
                        <input name="username" id="field_username_phone" autocomplete="off" />
                        <label class="mask" for="field_username_phone">手机号码</label>
                    </div>	                            
                    <div class="inp inputline show_mask">
                        <button type="button" id="sendPasswordBtn" class="btn unite_btn" onclick="sendPasswordToPhone();" tabindex="-1">
                            <span>获取密码</span><span id="resendCounter"></span>
                        </button>
                        <span class="icon icon_psw"></span>
                        <input name="password" id="field_password_phone" type="password" maxlength="4" style="width:60px;" autocomplete="off" />
                        <label class="mask" for="field_password_phone">密码</label>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${wechatAuth}">
            <div class="tbody" id="tbox_tab2">
                <p>请关注我们的微信公众号（长按文字可复制、长按图片可下载）： <b>${branch.publicName}</b></p>
                <p class="p5" style="text-align:center;">
                    <img src="${branch.qrCode}" style="max-width:150px; max-height:150px;" border="0" />
                </p>
                <p class="p5 ">${SignInTabPanel_s_wechatStepTip}</p>
                <form name="wechat" method="post" onsubmit="return submitForm(this);" autocomplete="off">
                	<input type="hidden" name="captcha" id="field_captcha_wechat" />
                    <div class="inp inputline show_mask">
                        <span class="icon icon_user"></span>
                        <input name="username" id="field_username_wechat" autocomplete="off" />
                        <label class="mask" for="field_username_wechat">用户名</label>
                    </div>	                            
                    <div class="inp inputline show_mask">
                        <span class="icon icon_psw"></span>
                        <input name="password" id="field_password_wechat" type="password" maxlength="4" autocomplete="off" />
                        <label class="mask" for="field_password_wechat">密码</label>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${pwdAuth}">
            <div class="tbody" id="tbox_tab3">
                <form name="db" class="deco_tab_body" method="post" onsubmit="return submitForm(this);" autocomplete="off">
                	<input type="hidden" name="captcha" id="field_captcha_account" />
                    <p>请输入用户名和密码。</p>
                    <div class="inp inputline show_mask">
                        <span class="icon icon_user"></span>
                        <input name="username" id="field_username_account" autocomplete="off" />
                        <label class="mask" for="field_username_account">用户名</label>
                    </div>	                            
                    <div class="inp inputline show_mask">
                        <span class="icon icon_psw"></span>
                        <input name="password" id="field_password_account" type="password" autocomplete="off" />
                        <label class="mask" for="field_password_account">密码</label>
                    </div>
                </form>
            </div>
        </c:if>
        <div class="inputline show_mask">
            <div class="inp inp_captcha">
                <span class="icon icon_arrow"></span>
                <input id="field_captcha" style="width:60px;" maxlength="4" autocomplete="off" />
                <label class="mask" for="field_captcha">验证码</label>
            </div>
            <img id="img_captcha" class="captcha" src="${base}acs/getCheckCode.do" data-src="${base}acs/getCheckCode.do" title="点击切换" align="absMiddle" />
            <div class="clearer"></div>
        </div>
        <div class="spacer"></div>
        <div class="bar">
            <button id="btn_submit" class="primary">登 录</button>
        </div>
    </div>
    <span id="debugInfo"></span>
</div>
<script type="text/javascript">
orderjs.regRootNS("cloudac", {
	js : "${jsPath}cloudac/",
	css : "${cssPath}"
});
orderjs.config("shim", {
	"open.jquery.jquery" : {
		exports : "jQuery"
	},
	"open.navigator-detect.navigator-detect" : {
		exports : "NavigatorDetect"
	}
});

orderjs(
	"open.jquery.jquery",
    "open.jQuery-idTabs.idTabs-ordered",
	"cloudac:sendPasswordToPhone",
	(typeof window.TouchEvent == "function") ? "open.navigator-detect.navigator-detect" : ""
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery"),
		sendUtil = orderjs("cloudac:sendPasswordToPhone");
	
	var debugMode = false;
	window.debug = function(msg){
		if(!debugMode) return;
		var t = new Date(),
			msgHtml = ["<div>&gt; ", t.getMinutes(), ":", t.getSeconds(), " ", msg, "</div>"].join("");
		jQuery("#debugInfo").append(msgHtml);
	};
	
	debug("module ready!");
	
	
	sendUtil.setOptions({
		errorMsg : "密码发送错误:\n" + "服务器错误!",
		invalidMobileTip : "无效手机号",
		sendToPageSuccessTip : "您的登录密码为：{password}，已自动输入！",
		sendingMsg : "发送中，请稍后...",
		url : "${base}acs/getPhonePassword.do?branchId=${branch.id}&ssid=${ssid}&mobile=",
		urlOnSendToPage : "${base}acs/getPhonePassword.do?branchId=${branch.id}&ssid=${ssid}&smsFlag=2&mobile="
	});
	
	/**
	 * Form validations, indexed with form's name.
	 */
	var rPassword = /\d{4}/,
		rMobile = /^((\(\d{3}\))|(\d{3}\-))?1\d{10}$/;
	var formValidations = {
		"wechat" : function(form){
			if(!form.username.value){
				alert("用户名不能为空!");
				form.username.focus();
				return false;
			}
			if(!rPassword.test(form.password.value)){
				alert("请输入由 4 位数字组成的密码!");
				form.password.focus();
				return false;
			}
		},
		"phone" : function(form){
			if(!rMobile.test(form.username.value)){
				alert("请输入正确手机号码!");
				form.username.focus();
				return false;
			}
			if(!rPassword.test(form.password.value)){
				alert("请输入由 4 位数字组成的密码!");
				form.password.focus();
				return false;
			}
		},
		"db" : function(form){
			if(!form.username.value){
				alert("用户名不能为空!");
				form.username.focus();
				return false;
			}
			if(!form.password.value){
				alert("密码不能为空!");
				form.password.focus();
				return false;
			}
		}
	};
	
	function setAjaxStartView(){
		jQuery("button[type='submit']")
			.prop("disabled", true)
			.addClass("disabled")
			.text("正在登录...");
	}
	function setAjaxEndView(){
		refreshCaptcha();
		clearCaptcha();
		jQuery("button[type='submit']")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("登 录");
	}
	
	function submitForm(form){
		var name = form.name,
			v = formValidations[name],
			defaultLoginErrorMsg = "登录失败:\n服务器错误",
			url = "${base}wifiant/portalAuth.do?${queryString}";
		
		if(v){
			if(v(form) === false) return false;
		}
		
		if(jQuery("#field_captcha").length){
			if(!jQuery("#field_captcha").val()){
				alert("验证码不能为空");
				jQuery("#field_captcha").focus();
				return false;
			}
			form.captcha.value = jQuery("#field_captcha").val();
		}
		
		//alert("signin url = " + url);
		jQuery.ajax({
			url : url,
			type : "post",
			dataType:"json",
			cache : false,
			data : jQuery(form).serialize(),
			beforeSend : setAjaxStartView,
			success : function(rspData, response){
				var msg;
				if(rspData){
					var status = rspData.status;
					if(status == 1){			    
						window.location.replace(rspData.wifidog);
						return false;
					}
					else if(status == 2.1){//v error
						clearPassword();
					}
					if(rspData.msg) msg = rspData.msg;
				}			
				alert(msg || defaultLoginErrorMsg);
				setAjaxEndView();
			},
			error : function(rsp, response){
				alert(defaultLoginErrorMsg);
				setAjaxEndView();
			}
		});
		
		return false;
	}	
	
	function submitActiveForm(){
		var active = 
			jQuery("#SignInTabPanel_s")
				.find(".tabbar a.selected")
				.attr("href");
		debug(this + ' active = ' + active + '');
		var activeForm = jQuery(active).find("form")[0];
		if(activeForm){
			submitForm(activeForm);
		}
		else{
			alert("抱歉，页面脚本发生错误，请刷新页面重试！");
		}
	}
	
	function submitActiveFormOnEnter(event){
		if(event.keyCode == 13){//enter
			submitActiveForm();
		}
	}
	
	function refreshCaptcha(){
		var captchaImg = jQuery("#img_captcha");
		var imgUrl = captchaImg.attr("data-src");
		imgUrl += (imgUrl.indexOf("?") > -1 ? "&" : "?" ) + "_r=" + (new Date).getTime();
		captchaImg
			.attr("src",  imgUrl);
	}
	
	function clearCaptcha(){
		jQuery("#field_captcha").val("");
	}
	
	function clearPassword(){
		jQuery("input[name='username']").val("");
	}
	
	(function(){
		debug("dom ready!");
		
		jQuery("#SignInTabPanel_s").idTabs();
		
		var maskedInputs = jQuery("input[name='username'], input[name='password'], #field_captcha");	
		function checkMaskDisplay(inp){
			var closestInputCtn = jQuery(inp).closest(".inputline");	
			setTimeout(function(){//defer to get the input value.
				debug(this + ' inp.value = ' + inp.value + '');
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
			
		orderjs.ready(checkAllMaskDisplay);
		jQuery("form")
			.on("keyup input", checkAllMaskDisplay)
			.on("keypress", submitActiveFormOnEnter);
		
		jQuery("#img_captcha")
			.on("click", refreshCaptcha);
			
		jQuery("#btn_submit")
			.on("click", submitActiveForm);
		jQuery("#field_captcha")
			.on("keyup input", checkAllMaskDisplay)
			.on("keypress", submitActiveFormOnEnter);
	
		if(typeof window.TouchEvent == "function"){
			var NavigatorDetect = orderjs("open.navigator-detect.navigator-detect");
			if(NavigatorDetect){
				var uaDetect = new NavigatorDetect(navigator.userAgent);
				uaDetect.init();
				if(!(uaDetect.isMobile() || uaDetect.isTablet())) return;
			}
			jQuery("input").on("focus", function(){
				jQuery(document.body).addClass("keyboard_padding");
				var visualAdjust = 50;//make inputs in middle(bt tip and login btn).
				setTimeout(function(){
					jQuery(document.body).scrollTop(jQuery("input:visible").offset().top - visualAdjust);
				}, 10);
			});
			jQuery("input").on("blur", function(){
				jQuery(document.body).removeClass("keyboard_padding");
			});
		}
	})();
	
	var isFakeSend = ${smsFlag==2};
	function sendPassword(){
		return isFakeSend ? sendUtil.sendPasswordToPage(onsendPasswordToPage) : sendUtil.send();
	}
	function onsendPasswordToPage(rspData){
		var form = jQuery("form[name='phone']");
		
		if(!jQuery("#field_captcha").length){
			window.submitForm(form[0]);
		}
	}
	
	jQuery("#sendPasswordBtn")
		.on("click", sendPassword)
	
	window.submitForm = submitForm;
	window.formValidations = formValidations;
	
});
</script>
