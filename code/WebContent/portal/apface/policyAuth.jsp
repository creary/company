﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>认证策略【${dto.name}】</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js?_v=${cacheBuster}"></script>
<script type="text/javascript">
orderjs(
	"open.jquery.jquery",
	"app.deco.ToggleButton",
	"x:mti.ajaxSubmit",
	"x:mti.Validator"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		ToggleButton = orderjs("app.deco.ToggleButton");
    
	rl.onReady(function() {
		//selectors behaviours
		function updateRedirectSection(){
			var visible = jQuery("input[name='redirect']:checked").val() == "1";
			rl.debug(this + 'updateAuthSection(): visible = ' + visible + '');
			jQuery("#redirectSection")
				.css("display", (visible ? "block" : "none"));
		}
		jQuery("#redirectOnField, #redirectOffField").on("click", updateRedirectSection);
		updateRedirectSection();
		
		function updateAuthSection(){
			var visible = jQuery("input[name='auth']:checked").val() == "1";
			rl.debug(this + 'updateAuthSection(): visible = ' + visible + '');
			jQuery("#section_auth")
				.css("display", (visible ? "block" : "none"));
		}
		jQuery("#field_auth_off, #field_auth_on").on("click", updateAuthSection);
		updateAuthSection();
		
		ToggleButton("#section_auth .soof_icard, #section_shareAuth .soof_icard", null, {
			trigger : ".btn.tg_enable_card",
			updateView : function(checked){
				var card = this.jq,
					icon = card.find(".icon_ctn .soof_icon"),
					btn = card.find(".bar .tg_enable_card");
				
				if(checked){
					btn.find("span").text("禁 用");
					card.removeClass("disabled");
					icon.removeClass("disabled");
				}
				else{
					btn.find("span").text("启 用");
					card.addClass("disabled");
					icon.addClass("disabled");
				}
				return this;
			}
		});
		
		function updateShareAuthSection(){
			var visible = jQuery("input[name='shareAuth']:checked").val() == "1";
			jQuery("#section_shareAuth")
				.css("display", (visible ? "block" : "none"));
		}
		jQuery("#field_shareAuth_off, #field_shareAuth_on").on("click", updateShareAuthSection);
		updateShareAuthSection();
		
		
		function updateJumptoUrlSection(){
			var visible = jQuery("#jumptoUrlField").prop("checked");
			rl.debug(this + 'updateJumptoUrlSection(): visible = ' + visible + '');
			jQuery("#jumptoUrlSection")
				.css("display", (visible ? "inline" : "none"));
		}
		jQuery("#jumptoMsiteField, #jumptoUrlField, #jumptoUserUrlField")
			.on("click", updateJumptoUrlSection);
		updateJumptoUrlSection();
		
		orderjs(
			"css>open.jquery-poshytip.tip-lightgray.tip-lightgray",
			"open.jquery-poshytip.jquery-poshytip"
		);
		orderjs(function(){
			jQuery(".soof_need_tooltip").poshytip({
				className: "tip-lightgray"
			});
		});
	}); 

	function saveForm() {
		var mainForm = document.mainForm;
		//check redirect
		if(jQuery("input[name='redirect']:checked").val() == "1"){
			//check auth
			if (jQuery("input[name='auth']:checked").val() == "1"){
				var fields = jQuery("#section_auth .soof_icard .card_enable_state"),
					hasCheckedAuth = fields.is(function(){
						return jQuery(this).val() == 1;
					});
				
				if(!hasCheckedAuth){
					alert("请至少选择一种认证方式！");
					fields.closest(".soof_icard")[0].scrollIntoView();
					return false;
				}
			}
			//check shareAuth
			if (jQuery("input[name='shareAuth']:checked").val() == "1"){
				var fields = jQuery("#section_shareAuth .soof_icard .card_enable_state"),
					hasCheckedAuth = fields.is(function(){
						return jQuery(this).val() == 1;
					});
				
				if(!hasCheckedAuth){
					alert("请至少选择一种分享目标！");
					fields.closest(".soof_icard")[0].scrollIntoView();
					return false;
				}
			}
			
			//check jumpto
			if(jQuery("input[name='jumpTo']:checked").val() == "2"){
				var urlField = jQuery("input[name='jumpUrl']"),	
					rUrl = /^http(s)?:\/\/.*?$/;				
				//trim
				urlField.val(rl.trim(urlField.val()));
				
				if(!rUrl.test(urlField.val())){
					alert("请输入以 http:// 或 https:// 开头的 URL！");
					urlField[0].focus();					
					return false;
				}
			}
		}
		
		if(rlx.mti.validate(mainForm)){
			mainForm.action = "${base}portal/policySaveOrUpdate.do";
			rlx.mti.ajaxSubmit("mainForm", "closeAndRefreshOpenerGrid");
		}
	}	
	window.saveForm = saveForm;	
});

</script>
<link href="${cssPath}common.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${cssPath}entity.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${cssPath}icons.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>认证策略【${dto.name}】</h1>
            <div class="nav">
                <ul class="list">
                    <li class='active'><a href="javascript:void(0);">基本设置</a></li>
                    <li><a href="${base}portal/policyReadyEditAdvanced.do?id=${dto.id}">高级设置</a></li>
                </ul>
            </div>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${dto.id}" />
                                <input type="hidden" name="branchId" value="${dto.branchId}" />
                                <input type="hidden" name="tag" value="1" />
                                <div class="section first">
                                    <h3>Portal 开关</h3>
                                    <p class="p5">
                                    	关闭后，用户手机连接 WiFi 即可直接上网，不会被强制跳转到指定页面，不再需需认证。
                                    </p>
                                    <p class="p10 selectors_row">
                                        <input type="radio" name="redirect" class="field_selector" id="redirectOnField" value="1" ${dto.redirect==1?"checked":""}/><label class="selector_label" for="redirectOnField">开启（默认）</label>
                                        <input type="radio" name="redirect" class="field_selector" id="redirectOffField" value="0" ${dto.redirect==0?"checked":""}/><label class="selector_label" for="redirectOffField">关闭</label>
                                    </p>
                                </div>
                                <div id="redirectSection" style='${dto.redirect==1?"":"display:none;"} '>
                                    <div class="section">
                                        <h3>免认证</h3>
                                        <p class="p5">
                                            免认证是指用户连接 WiFi 被强制访问 Portal 的欢迎页后，只需在欢迎页中点击"马上体验"或类似按钮即可上网，无需认证。
                                        </p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="auth" class="field_selector" id="field_auth_off" value="0" ${dto.auth==0?"checked":""}/><label class="selector_label" for="field_auth_off">开启（默认）</label>
                                            <input type="radio" name="auth" class="field_selector" id="field_auth_on" value="1" ${dto.auth==1?"checked":""}/><label class="selector_label" for="field_auth_on">关闭</label>
                                        </p>
                                        <div id="section_auth" style='${dto.auth==1?"":"display:none;"}' class="p10">
                                            <p>请从以下选项中选择至少一种认证方式：</p>
                                            <div class="p10">
                                                <div class="soof_icard">
                                                	<input class="card_enable_state" autocomplete="off" type="hidden" name="smsAuth" value="${dto.smsAuth}" />
                                                    <div class="cnt_wrap">
                                                        <table class="cnt" border="0" cellspacing="0" cellpadding="0">
                                                            <tr>
                                                                <td class="icon_ctn">
                                                                    <i class="soof_icon sms_auth_48"></i>
                                                                </td>
                                                                <td class="detail_ctn">
                                                                    <h5><span class="soof_need_tooltip" title="用户输入手机号码，系统将发送动态密码至用户手机。&lt;br /&gt; 短信策略：为节省短信费用，平台支持虚拟短信，即当用户点&quot;获取密码&quot;按钮后，系统直接把密码发送到页面并自动登录。&lt;br /&gt;密码有效期： 在短信密码有效期内，手机上网无需再申请。">短信认证 <i class="soof_icon help"></i></span></h5>
                                                                    <table class="tight_fields" border="0" cellspacing="0" cellpadding="0">
                                                                        <tr>
                                                                            <td>短信策略</td>
                                                                            <td class="field_ctn">
                                                                                <select name="smsFlag" class="field xs">
                                                                                    <option value="1" ${dto.smsFlag == 1?"selected":""}>真实短信</option>
                                                                                    <option value="2" ${dto.smsFlag == 2?"selected":""}>虚拟短信</option>
                                                                                </select>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>密码有效期</td>
                                                                            <td class="field_ctn">
                                                                                <select name="validity" class="field xs">
                                                                                    <option value="0" ${dto.validity == 0?"selected":""}>无限制</option>
                                                                                    <option value="30" ${dto.validity == 30?"selected":""}>30分钟</option>
                                                                                    <option value="1440" ${dto.validity == 1440?"selected":""}>24小时</option> 
                                                                                    <option value="43200" ${dto.validity == 43200?"selected":""}>30天</option>  
                                                                                </select>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <table class="bar" border="0" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                            <td>
                                                                <a class="btn primary tg_enable_card" href="javascript:void(0);">
                                                                    <span>禁 用</span>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="soof_icard">
                                                	<input class="card_enable_state" autocomplete="off" type="hidden" name="wechatAuth" value="${dto.wechatAuth}" />
                                                    <div class="cnt_wrap">
                                                        <table class="cnt" border="0" cellspacing="0" cellpadding="0">
                                                            <tr>
                                                                <td class="icon_ctn">
                                                                    <i class="soof_icon wechat_auth_48"></i>
                                                                </td>
                                                                <td class="detail_ctn">
                                                                    <h5><span class="soof_need_tooltip" title="用户手机在认证之前，仅微信可以正常访问网络。&lt;br /&gt;用户通过微信的“扫一扫”或点击“一键上网”菜单&lt;br /&gt;等方式与商家公众号交互，实现认证上网。">微信认证 <i class="soof_icon help"></i></span></h5>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!-- if wechatAuthAvailable -->
                                                    <div class="warn_box" style="display:none;">
                                                        您尚未完成微信公众号的配置，
                                                        此认证方式暂不可用，<a href="javascript:void(0);" onclick="rl.page.open('${base}admin/readyEditWechatBase.do?branchId=${branchId}', 'wechatAuth', rl.gui.winDlgOpt);">点击此处配置公众号</a>。
                                                    </div>
                                                    <!-- end if wechatAuthAvailable -->
                                                    <table class="bar" border="0" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                            <td>
                                                                <a class="btn primary tg_enable_card" href="javascript:void(0);">
                                                                    <span>禁 用</span>
                                                                </a>
                                                            </td>
                                                            <td>
                                                                <a class="btn" href="javascript:void(0);" onclick="rl.page.open('${base}admin/readyEditWechatBase.do?branchId=${branchId}', 'wechatAuth', rl.gui.winDlgOpt);">
                                                                    <span>配置微信认证</span>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="soof_icard">	
                                                	<input class="card_enable_state" autocomplete="off" type="hidden" name="pwdAuth" value="${dto.pwdAuth}" />
                                                    <div class="cnt_wrap">
                                                        <table class="cnt" border="0" cellspacing="0" cellpadding="0">
                                                            <tr>
                                                                <td class="icon_ctn">
                                                                    <i class="soof_icon account_auth_48"></i>
                                                                </td>
                                                                <td class="detail_ctn">
                                                                    <h5><span class="soof_need_tooltip" title="用户直接输入商家提供的用户名和密码。&lt;br /&gt;您可以在【用户管理】 > 【用户列表】 模块中&lt;br /&gt;点击工具栏的 【增加】 按钮来创建账户。">帐户认证 <i class="soof_icon help"></i></span></h5>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <table class="bar" border="0" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                            <td>
                                                                <a class="btn primary tg_enable_card" href="javascript:void(0);">
                                                                    <span>禁 用</span>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="clearer"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="section">
                                        <h3>首次认证微信指引页</h3>
                                        <p class="p5">
                                            当用户第一次进行认证时，如使用浏览器认证，则系统显示微信指引页代替欢迎页，以引导用户使用微信进行认证。
                                        </p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="wechatGuide" class="field_selector" id="field_guide_off" value="0" ${dto.wechatGuide == 0?"checked":""} /><label class="selector_label" for="field_guide_off">关闭（默认）</label>
                                            <input type="radio" name="wechatGuide" class="field_selector" id="field_guide_on" value="1" ${dto.wechatGuide == 1?"checked":""} /><label class="selector_label" for="field_guide_on">开启</label>
                                        </p>
                                    </div>
                                    <div class="section">
                                        <h3>分享认证</h3>
                                        <p class="p5">
                                            用户连接 WiFi 被强制访问 Portal 的分享页后，只需将页面内容（如图片）分享至社交网络（如朋友圈、微博等）后即可上网。用户如不分享，系统将自动切换至以上设置的认证方式。
                                        </p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="shareAuth" class="field_selector" id="field_shareAuth_off" value="0" ${dto.wechatShare == 0?"checked":""} /><label class="selector_label" for="field_shareAuth_off">关闭（默认）</label>
                                            <input type="radio" name="shareAuth" class="field_selector" id="field_shareAuth_on" value="1" ${dto.wechatShare == 1?"checked":""} /><label class="selector_label" for="field_shareAuth_on">开启</label>
                                        </p>
                                        <div id="section_shareAuth" class="p10">
                                            <p>请从以下选项中选择至少一种分享目标：</p>
                                            <div class="p10">
                                                <div class="soof_icard">
                                                	<input class="card_enable_state" autocomplete="off" type="hidden" name="wechatShare" value="${dto.wechatShare}" />
                                                    <div class="cnt_wrap">
                                                        <table class="cnt" border="0" cellspacing="0" cellpadding="0">
                                                            <tr>
                                                                <td class="icon_ctn">
                                                                    <i class="soof_icon share_auth_pengyouquan_48"></i>
                                                                </td>
                                                                <td class="detail_ctn">
                                                                    <h5><span class="soof_need_tooltip" title="用户手机在认证之前，微信基本功能（包括朋友圈）可以使用，&lt;br /&gt;但当用户在朋友圈打开文章链接时将被拦截，并跳转至 Portal 的分享页，&lt;br /&gt;仅当用户将分享页的文章或图片分享至朋友圈后，才能正常上网。&lt;br /&gt;&lt;a href='${base}acs/help/wechatShareAuth.jsp' target='_blank'&gt;点击此处了解更多&lt;/a&gt; ">微信朋友圈 <i class="soof_icon help"></i></span></h5>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <table class="bar" border="0" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                            <td>
                                                                <a class="btn primary tg_enable_card" href="javascript:void(0);">
                                                                    <span>禁 用</span>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="soof_icard disabled">
                                                	<input class="card_enable_state" autocomplete="off" type="hidden" name="shareAuthWeibo" value="0" />
                                                    <div class="cnt_wrap">
                                                        <table class="cnt" border="0" cellspacing="0" cellpadding="0">
                                                            <tr>
                                                                <td class="icon_ctn">
                                                                    <i class="soof_icon share_auth_weibo_48 disabled"></i>
                                                                </td>
                                                                <td class="detail_ctn">
                                                                    <h5><span class="soof_need_tooltip" title="用户打开浏览器访问任意网页&lt;br /&gt;都会被强制跳转至 Portal 的分享页，&lt;br /&gt;只需将页面的文章分享至新浪微博后即可上网。">新浪微博 <i class="soof_icon help"></i></span></h5>
                                                                    <div class="p10">暂未支持</div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="bar"></div>
                                                </div>
                                                <div class="soof_icard disabled">
                                                	<input class="card_enable_state" autocomplete="off" type="hidden" name="shareAuthQzone" value="0" />
                                                    <div class="cnt_wrap">
                                                        <table class="cnt" border="0" cellspacing="0" cellpadding="0">
                                                            <tr>
                                                                <td class="icon_ctn">
                                                                    <i class="soof_icon share_auth_qzone_48 disabled"></i>
                                                                </td>
                                                                <td class="detail_ctn">
                                                                    <h5><span class="soof_need_tooltip" title="用户打开浏览器访问任意网页&lt;br /&gt;都会被强制跳转至 Portal 的分享页，&lt;br /&gt;只需将页面的文章分享至 QQ 空间后即可上网。">QQ空间 <i class="soof_icon help"></i></span></h5>
                                                                    <div class="p10">暂未支持</div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="bar"></div>
                                                </div>
                                                <div class="clearer"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="section">
                                        <h3>二次访问体验优化</h3>
                                        <p class="p5">用户第一次认证通过后，再次来访使用网络时的认证体验优化策略。</p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="secondFree" class="field_selector" id="field_secondFreeOff" value="0" ${dto.secondFree==0?"checked":""}/><label class="selector_label" for="field_secondFreeOff">无（默认）</label>
                                        </p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="secondFree" class="field_selector" id="field_secondFreeOn_portalOff" value="1" ${dto.secondFree==1?"checked":""}/><label class="selector_label" for="field_secondFreeOn_portalOff">二次无感知，即再次访问时，连接网络即可上网，无需其它任何操作。</label>
                                        </p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="secondFree" class="field_selector" id="field_secondFreeOn_authOff" value="2" ${dto.secondFree==2?"checked":""}/><label class="selector_label" for="field_secondFreeOn_authOff">二次免认证，即再次访问时，执行免认证策略。</label>
                                        </p>
                                    </div>
                                    <div class="section">
                                        <h3>跳转自定义</h3>
                                        <p class="p5">用户通过认证后跳转的页面。
                                        </p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="jumpTo" class="field_selector" id="jumptoMsiteField" value="1" ${dto.jumpTo==1?"checked":""}/><label class="selector_label" for="jumptoMsiteField">跳转至 Portal 认证后页（默认）</label>
                                        </p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="jumpTo" class="field_selector" id="jumptoUserUrlField" value="3" ${dto.jumpTo==3?"checked":""} /><label class="selector_label" for="jumptoUserUrlField">跳转至用户认证前访问的网页</label>
                                        </p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="jumpTo" class="field_selector" id="jumptoUrlField" value="2" ${dto.jumpTo==2?"checked":""} /><label class="selector_label" for="jumptoUrlField">跳转至指定 URL</label>
                                            <span id="jumptoUrlSection" style="display:none;">
                                            	<input name="jumpUrl" class="field" value="${dto.jumpUrl}" />
                                                <span class="desc">(Url必须以 http:// 或 https:// 开头)</span>
                                        </span>
                                        </p>
                                        
                                    </div>
                                    <div class="section">
                                        <h3>Portal 隔断</h3>
                                        <p class="p5">开启后，用户在 Portal 不同的设备间切换时，将被下线</p>
                                        <p class="p10 selectors_row">
                                            <input type="radio" name="separatePortal" class="field_selector" ${dto.separatePortal==0?"checked":""} id="field_portalIsolation_off" value="0" /><label class="selector_label" for="field_portalIsolation_off">关闭（默认）</label>
                                            <input type="radio" name="separatePortal" class="field_selector" ${dto.separatePortal==1?"checked":""} id="field_portalIsolation_on" value="1" /><label class="selector_label" for="field_portalIsolation_on">开启</label>
                                        </p>
                                    </div>
                                </div>
                                <div class="section">
                                    <h3>强制断开时间</h3>
                                    <p class="p5">
                                        用户一次上网时间到达此值时，将被强制下线，需要重新认证才能上网。
                                    </p>
                                    <p class="p10">
                                        <select name="surfingTime" class="field xs">
                                            <option value="0" ${dto.surfingTime == 0?"selected":""}>无限制</option>
                                            <option value="5" ${dto.surfingTime == 5?"selected":""}>5分钟</option>
                                            <option value="30" ${dto.surfingTime == 30?"selected":""}>30分钟</option>                                                
                                            <option value="60" ${dto.surfingTime == 60?"selected":""}>1小时</option>
                                            <option value="120" ${dto.surfingTime == 120?"selected":""}>2小时</option>                                        	
                                        </select> 
                                    </p>
                                </div> 
                                <div class="section">
                                    <h3>空闲等待时间</h3>
                                    <p class="p5">用户登录后，如超过此时间无网络数据（如离开 WiFi 覆盖区域），将自动下线。
                                    </p>
                                    <p class="p10">
                                        <select name="idleTime" class="field xs">
                                            <option value="5" ${dto.idleTime == 5?"selected":""}>5分钟</option>
                                            <option value="30" ${dto.idleTime == 30?"selected":""}>30分钟</option>
                                            <option value="60" ${dto.idleTime == 60?"selected":""}>1小时</option>
                                            <option value="120" ${dto.idleTime == 120?"selected":""}>2小时</option>
                                        </select>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>单日上网时长</h3>
                                    <p class="p5">用户一天内上网时长累计达到该值后，不能再上网。
                                    </p>
                                    <p class="p10">
                                        <select name="stayTime" class="field xs">
                                            <option value="0" ${dto.stayTime == 0?"selected":""}>无限制</option>
                                            <option value="30" ${dto.stayTime == 30?"selected":""}>30分钟</option>
                                            <option value="60" ${dto.stayTime == 60?"selected":""}>1小时</option>
                                            <option value="120" ${dto.stayTime == 120?"selected":""}>2小时</option>                                      	
                                        </select>
                                    </p>
                                </div> 
                                <div class="section">
                                    <h3>多终端登入</h3>
                                    <p class="p5">允许一个帐号在多个终端登入。
                                    </p>
                                    <p class="p10 selectors_row">
                                        <input type="radio" name="oneAccountDevice" class="field_selector" id="field_oneAccountDevice_off" value="0" ${dto.oneAccountDevice==0?"checked":""}/><label class="selector_label" for="field_oneAccountDevice_off">允许（默认）</label>
                                        <input type="radio" name="oneAccountDevice" class="field_selector" id="field_oneAccountDevice_on" value="1" ${dto.oneAccountDevice==1?"checked":""}/><label class="selector_label" for="field_oneAccountDevice_on">禁止</label>
                                    </p>
                                </div>                                    
                                <div class="section">
                                    <h3>带宽限制</h3>
                                    <p class="p5">限制用户上网上下行带宽。(单位换算公式：1Mbps = 1024kbps = 128KB/S)</p>
                                    <p class="p10 selectors_row">
                                        <label class="width_label s">下行带宽</label>
                                        <input type="radio" name="downLimit" class="field_selector first" id="field_downLimit_off" value="0" ${dto.downSpeed==0?"checked":""} /><label class="selector_label" for="field_downLimit_off">不限</label>
                                        <input type="radio" name="downLimit" class="field_selector" id="field_downLimit_on" value="1" ${dto.downSpeed>0?"checked":""} /><label class="selector_label" for="field_downLimit_on">限至</label> <input name="downSpeed" value="${dto.downSpeed}" dataType="Number" class="field xs" id="field_downSpeed" />&nbsp;kbps                                            
                                    </p>
                                    <p class="p10 selectors_row">
                                        <label class="width_label s">上行带宽</label>
                                        <input type="radio" name="upLimit" class="field_selector first" id="field_upLimit_off" value="0" ${dto.upSpeed==0?"checked":""} /><label class="selector_label" for="field_upLimit_off">不限</label>
                                        <input type="radio" name="upLimit" class="field_selector" id="field_upLimit_on" value="1" ${dto.upSpeed>0?"checked":""} /><label class="selector_label" for="field_upLimit_on">限至</label> <input name="upSpeed" value="${dto.upSpeed}" dataType="Number" class="field xs" id="field_upSpeed" />&nbsp;kbps
                                        <script type="text/javascript">
                                        orderjs(function(){
                                            var jQuery = orderjs("open.jquery.jquery");
                                            
                                            function updateDownSpeedField(){
                                                var disabled = jQuery("input[name='downLimit']:checked").val() != 1;
                                                jQuery("#field_downSpeed, #field_downSpeedUnit").prop("disabled", disabled);
                                            }
                                            jQuery("input[name='downLimit']")
                                                .on("click", updateDownSpeedField);
                                            updateDownSpeedField();
                                            
                                            function updateUpSpeedField(){
                                                var disabled = jQuery("input[name='upLimit']:checked").val() != 1;
                                                jQuery("#field_upSpeed, #field_upSpeedUnit").prop("disabled", disabled);
                                            }
                                            jQuery("input[name='upLimit']")
                                                .on("click", updateUpSpeedField);
                                            updateUpSpeedField()
                                            
                                        });
                                        </script>
                                    </p>
                                </div>
                                <div class="soof_action_bar">
                                    <button type="button" class="btn primary" onclick="saveForm()">保 存</button>
                                    <button class="btn white" onclick="rl.page.close()">取消</button>
                                </div>
                            </form>
                            <div class="widthholder"></div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>