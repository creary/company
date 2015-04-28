﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<link href="${jsPath}rl/src/open/jQuery-idTabs/jquery_idtabs.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${moduleContext}SignInTabPanel/view/style.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<style type="text/css">
/* override defaults with user setting */
.soof_SignInTabPanel .btn{
	background-color:${SignInTabPanel_btnBgColor}; 
	color:${SignInTabPanel_btnTextColor};
}
.soof_SignInTabPanel.hat ul.tabbar a{
	padding:8px 10px;
	line-height:1em;
}
.soof_SignInTabPanel.hat ul.tabbar a.selected{
	padding:7px 9px 8px 9px;
}
</style>
<div class="p10">
    <div id="SignInTabPanel" class="soof_SignInTabPanel jquery_idtabs hat">
        <ul class="tabbar">
            <c:if test="${smsAuth}">
                <li><a class="selected" href="#tbox_tab1">手机登录</a></li>
            </c:if>
            <c:if test="${wechatAuth}">
                <li><a href="#tbox_tab2">微信登录</a></li>
            </c:if>
            <c:if test="${pwdAuth}">
                <li><a href="#tbox_tab3">账户登录</a></li>
            </c:if>
        </ul>
        <c:if test="${smsAuth}">
            <div class="tbody" id="tbox_tab1" style="display:block;">
                <form name="phone" class="deco_tab_body" method="post" onsubmit="return submitForm(this);" autocomplete="off">
                    <div id="inputs" class="inputs">
                        <p>输入手机号码，然后点击【获取密码】按钮，系统将把密码发送到您输入的手机。</p>
                        <div class="inp inputline show_mask">
                            <button type="button" id="sendPasswordBtn" class="btn unite_btn" onclick="sendPasswordToPhone();" tabindex="2">
                                <span>获取密码</span><span id="resendCounter"></span>
                            </button>
                            <img class="icon icon_phone" src="${base}resources/image/default/mix/blank.gif?_v=${cacheBuster}" border="0" />
                            <input name="username" id="field_username_phone" tabindex="1" style="width:150px; " autocomplete="off" />
                            <label class="mask" for="field_username_phone">请输入手机号码</label>
                        </div>	                            
                        <div class="inp inputline show_mask">
                            <img class="icon icon_psw" src="${base}resources/image/default/mix/blank.gif?_v=${cacheBuster}" border="0" />
                            <input name="password" id="field_password_phone" type="password" tabindex="3" autocomplete="off" />
                            <label class="mask" for="field_password_phone">请输入密码</label>
                        </div>
                        <div class="spacer"></div>
                        <div class="inputline">
                            <button type="submit" class="btn primary">登 录</button>
                        </div>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${wechatAuth}">
            <div class="tbody" id="tbox_tab2">
                <p>本店微信公众号为： <b>${branch.publicName}</b>
                    <p class="p5" style="text-align:center;">
                        <img src="${branch.qrCode}" style="max-width:200px; max-height:200px;" border="0" />
                    </p>
                </p>
                <p class="p10">请按照以下步骤登录：</p>
                <p class="p5 ">${SignInTabPanel_wechatStepTip}</p>
                <form name="wechat" method="post" onsubmit="return submitForm(this);" autocomplete="off">
                    <div class="inp inputline show_mask">
                        <img class="icon icon_user" src="${base}resources/image/default/mix/blank.gif?_v=${cacheBuster}" border="0" />
                        <input name="username" id="field_username_wechat" autocomplete="off" />
                        <label class="mask" for="field_username_wechat">请输入用户名</label>
                    </div>	                            
                    <div class="inp inputline show_mask">
                        <img class="icon icon_psw" src="${base}resources/image/default/mix/blank.gif?_v=${cacheBuster}" border="0" />
                        <input name="password" id="field_password_wechat" type="password" autocomplete="off" />
                        <label class="mask" for="field_password_wechat">请输入密码</label>
                    </div>
                    <div class="spacer"></div>
                    <div class="inputline">
                        <button type="submit" class="btn primary">登 录</button>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${pwdAuth}">
            <div class="tbody" id="tbox_tab3">
                <form name="db" class="deco_tab_body" method="post" onsubmit="return submitForm(this);" autocomplete="off">
                    <div id="inputs" class="inputs">
                        <p>请输入用户名和密码。</p>
                        <div class="inp inputline show_mask">
                            <img class="icon icon_user" src="${base}resources/image/default/mix/blank.gif?_v=${cacheBuster}" border="0" />
                            <input name="username" id="field_username_account" autocomplete="off" />
                            <label class="mask" for="field_username_account">请输入用户名</label>
                        </div>	                            
                        <div class="inp inputline show_mask">
                            <img class="icon icon_psw" src="${base}resources/image/default/mix/blank.gif?_v=${cacheBuster}" border="0" />
                            <input name="password" id="field_password_account" type="password" autocomplete="off" />
                            <label class="mask" for="field_password_account">请输入密码</label>
                        </div>
                        <div class="spacer"></div>
                        <div class="inputline">
                            <button type="submit" class="btn primary">登 录</button>
                        </div>
                    </div>
                </form>
            </div>
        </c:if>
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
	
	sendUtil.setOptions({
		errorMsg : "密码发送错误:\n" + "服务器错误!",
		invalidMobileTip : "无效手机号",
		sendToPageSuccessTip : "您的登录密码为：{password}，系统将自动登录!",
		sendingMsg : "发送中，请稍后...",
		url : "${base}acs/getPhonePassword.do?branchId=${branch.id}&ssid=${ssid}&mobile=",
		urlOnSendToPage : "${base}acs/getPhonePassword.do?branchId=${branch.id}&ssid=${ssid}&smsFlag=2&mobile="
	});
	
	/**
	 * Form validations, indexed with form's name.
	 */
	var formValidations = {
		"wechat" : function(form){
			if(!form.username.value){
				alert("用户名不能为空!");
				form.username.focus();
				return false;
			}
			if(!form.password.value){
				alert("动态密码不能为空!");
				form.password.focus();
				return false;
			}
		},
		"phone" : function(form){
			var rMobile = /^((\(\d{3}\))|(\d{3}\-))?1\d{10}$/;
			if(!rMobile.test(form.username.value)){
				alert("请输入正确手机号码!");
				form.username.focus();
				return false;
			}
			if(!form.password.value){
				alert("动态密码不能为空!");
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
		jQuery("button[type='submit']")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("登 录");
	}
	
	function submitForm(form){
		var name = form.name,
			v = formValidations[name],
			defaultLoginErrorMsg = "登录失败:\n服务器错误",
			url = "${base}wifiant/authenticate.do?${queryString}";
		
		if(v){
			if(v(form) === false) return false;
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
	
	function clearPassword(){
		jQuery("input[name='username']").val("");
	}
	
	//jQuery(fn) ready event not works well(takes too much time) if some imgs not load.
	//so we move dom operations under required elements.
	(function(){
		jQuery("#SignInTabPanel").idTabs();
		
		var maskedInputs = jQuery("input[name='username'], input[name='password']");	
		function checkMaskDisplay(inp){
			var closestInputCtn = jQuery(inp).closest(".inp");		
			if(inp.value === ""){
				closestInputCtn.addClass("show_mask");
			}else{
				closestInputCtn.removeClass("show_mask");
			}
		}	
		function checkAllMaskDisplay(){
			maskedInputs.each(function(){
				checkMaskDisplay(this);
			});
		}	
		orderjs.ready(checkAllMaskDisplay);
		jQuery("form").on("keyup input", checkAllMaskDisplay);
			
		var debugMode = false;
		window.debug = function(msg){
			if(!debugMode) return;
			var t = new Date(),
				msgHtml = ["<div>&gt; ", t.getMinutes(), ":", t.getSeconds(), " ", msg, "</div>"].join("");
			jQuery("#debugInfo").append(msgHtml);
		};
		
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
		
		window.submitForm(form[0]);
	}
	
	jQuery("#sendPasswordBtn")
		.on("click", sendPassword)
	
	window.submitForm = submitForm;
	window.formValidations = formValidations;
	
});
</script>
