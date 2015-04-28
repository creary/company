﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<%@page import="com.soofound.framework.util.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信认证</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs.config("shim", {
	"open.kindeditor.kindeditor" : {
		deps : ["css>open.kindeditor.themes.default.default"],
		exports : "KindEditor"
	},
	"open.kindeditor.lang.zh_CN" : {
		deps : ["open.kindeditor.kindeditor", "css>open.kindeditor.themes.default.default"],
		exports : "KindEditor"
	}
});

orderjs(
	"x:mti.Validator",
	"open.jquery.jquery",
	"lib.rpc.JqueryAjaxDirect",
	"open.kindeditor.lang.zh_CN"
);

orderjs(function(){
	//rl.console.showOnReady();
	var jQuery = orderjs("open.jquery.jquery"),
		K = orderjs("open.kindeditor.kindeditor"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect"),	
		SectionSwitcher = orderjs("cloudac:SectionSwitcher");
	
	var mainDirect = new JqueryAjaxDirect({
		services : {
			save : function(options){
				var url = "${base}admin/saveWechat.do",
					data = jQuery("#mainForm").serialize();
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			},
			genQrcodeImage : function(options, logo){
				var url = rl.format("${base}admin/getQRCode.do?branchId=${branchId}", arguments);
				jQuery.ajax(rl.ext(options, { url : url}));
			}
		}
	});
	
	jQuery.ajaxSetup({
		dataType : "json",
		method : "post",
		cache : false
	});
	
	function setAjaxStartView(){
		jQuery("#btn_action")
			.prop("disabled", true)
			.addClass("disabled")
			.text("正在保存...");
	}
	function setAjaxEndView(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("保 存");
	}
	function showGenQrcodeTip(tip){
		jQuery("#genQrcodeTip")
			.html(tip)
			.show();
	}
	function hideGenQrcodeTip(delay){
		var tipJq = jQuery("#genQrcodeTip");
		
		if(delay) tipJq.delay(isNaN(delay) ? 1000 : delay).hide();
		else tipJq.hide();
	}
	
	function genQrcode(logo){
		if(!rl.isNonNullStr(logo)){
			return false;
		}		
		mainDirect.invoke("genQrcodeImage", {
			beforeSend : function(){
				showGenQrcodeTip("正在生成二维码...");
			},
			success : function(url){
				showGenQrcodeTip("二维码已生成！");
				hideGenQrcodeTip(true);
				
				rl.debug(mainDirect + 'genQrcodeImage success(): url = ' + url + '');
				url += (url.indexOf("?") > -1 ? "&" : "?" ) + "_=" + new Date().getTime();
				jQuery('#qrcodeHolder')
					.attr("src", url)
					.show();
			}
		}, logo);
	}
	
	function doAction() {
	    var mainForm = document.mainForm;		
		var qrCodeVal = mainForm.qrCode.value;
		if(!qrCodeVal){
			alert("必须上传 Logo 以生成二维码!");
			return false;
		}
		
		if(rlx.mti.validate(mainForm)) {
			mainDirect.invoke("save", {
				beforeSend : setAjaxStartView,
				success : function(){
					alert("保存成功！");
				},
				complete : setAjaxEndView
			});
		}
	}
	
	rl.onReady(function(){		
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
			
		var logoUploader = K.uploadbutton({
			button : K('#btn_uploadLogo')[0],
			url : '${base}admin/uploadLogo.do?branchId=${branchId}',
			afterUpload : function(data) {
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#field_qrcode').val(url);
					rl.debug(this + ' afterUpload(): url = ' + url + '');
					showGenQrcodeTip("上传成功！");
					genQrcode(url);
				} else {
					rl.dir(data, logoUploader + ' afterUpload(): data');
					hideGenQrcodeTip();
					alert(data.message);
				}
			},
			afterError : function(str) {
				hideGenQrcodeTip();
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		logoUploader.fileBox.change(function(e) {
			showGenQrcodeTip("正在上传: " + this.value);
			logoUploader.submit();
		});
		
	});
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}entity.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>微信认证</h1>
            <div class="nav">
                <ul class="list">
                    <li><a href="${base}admin/readyEditWechatBase.do?branchId=${branchId}">公众号对接</a></li>
                    <li class='active'><a href="javascript:void(0);">认证配置</a></li>
                </ul>
            </div>
        </div>
        <div class="soof_body">
        	<%--<c:if test="${error ne null}">
        	    <font color='red'>公众号对接配置信息不正确，请配置成功后再进入本页面!</font>
        	</c:if>
        	<c:if test="${error eq null}">
            </c:if>--%>
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <div class="section first">
                                	<h3>认证方式</h3>
                                	<p class="p5">在完成与微信公众号对接后，本平台支持 3 种微信认证方式。其中“一键上网”方式，您需要额外对微信菜单进行配置才能生效。</p>
                                    <div class="sections_box p10">
                                        <div class="section first">
                                            <table class="rich_row" border="0" cellspacing="0" cellpadding="0">
	                                            <tr>
	                                                <td class="icon_ctn">
	                                                    <i class="soof_icon wechat_auth_scan"></i>
	                                                </td>
	                                                <td class="detail_ctn">
	                                                    <h5>扫一扫上网</h5>
                                                        <p class="p5">用户只需用微信扫一扫您的微信二维码，关注后，即可上网。</p>
	                                                </td>
	                                                <td class="action_ctn">	                                                    
	                                                </td>
	                                            </tr>
	                                        </table>
	                                    </div>
                                        <div class="section">
                                            <table class="rich_row" border="0" cellspacing="0" cellpadding="0">
	                                            <tr>
	                                                <td class="icon_ctn">
	                                                    <i class="soof_icon wechat_auth_onekey"></i>
	                                                </td>
	                                                <td class="detail_ctn">
	                                                    <h5>一键上网</h5>
                                                        <p class="p5">用户在您的微信公众号的菜单中，点击“一键上网”菜单项，即可上网。
	                                                </td>
	                                                <td class="action_ctn">                                                        
                                                        <div id="section_turnOnOnekey" class="p10">
                                                        	<a href="javascript:void(0)" onclick="rl.page.open('${base}admin/branch/wechatMenu.jsp', 'wechatMenu', rl.gui.winDlgOpt);">配置“一键上网”菜单</a>
                                                        </div>
	                                                </td>
	                                            </tr>
	                                        </table>
	                                    </div>
                                        <div class="section">
                                            <table class="rich_row" border="0" cellspacing="0" cellpadding="0">
	                                            <tr>
	                                                <td class="icon_ctn">
	                                                    <i class="soof_icon wechat_auth_input"></i>
	                                                </td>
	                                                <td class="detail_ctn">
	                                                    <h5>回复即上网</h5>
                                                        <p class="p5">用户在您的微信公众号回复"wifi"后，即可上网。</p>
	                                                </td>
	                                                <td class="action_ctn">
	                                                </td>
	                                            </tr>
	                                        </table>
	                                    </div>
                                    </div>
                                </div>
                                <div class="section">
                                    <h3>二维码</h3>
                                    <p class="p5">
                                    	请上传 Logo 图片，系统将自动生成二维码。Logo 图片格式为 png、jpg、gif，文件大小不超过 1 M，图片大小建议为 100 * 100 像素。<br />
                                        在打印二维码供用户扫描关注时，请使用该二维码，使用其它平台（包括微信公众平台）生成的二维码，扫一扫上网功能将失效。<font color='red'>此功能仅限于认证过的公众号</font>
                                    </p>
                                    <div class="p10">
                                        <div class="soof_img_viewer" style="width:200px; height:200px;">
                                            <input id="field_qrcode" type="hidden" name="qrCode" value="${dto.qrCode}" />
                                            <img src="${dto.qrCode}?r=<%=DateUtil.getCurrentTimeAsID()%>" id="qrcodeHolder" class="img_holder" />
                                            <div class="mask" style="line-height:200px;">二维码</div>
                                        </div>
                                        <div class="soof_img_viewer_upload_wrap soof_fix" style="padding-top:180px;">
                                            <span><input type="button" id="btn_uploadLogo" class="btn white" value="上传 Logo 并生成二维码" /></span> <span id="genQrcodeTip" style="display:none;"></span>
                                        </div>
                                        <div class="clearer"></div>
                                    </div>
                                </div>
                                <div class="section">
                                    <h3>回复消息</h3>
                                    <p class="p5">
                                    	配置用户通过验证后的回复消息。如您是初次接触本系统，此步可忽略，采用系统默认配置即可（系统默认回复欢迎图片和电脑上网用户名和密码）。
                                    </p>
                                    <p class="p10">
                                        <button class="btn green" onclick="rl.page.open('${base}admin/readyEditWechatResponse.do', 'wechatMsgSetting', rl.gui.winDlgOpt);">配置回复消息</button>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>用户指引页</h3>
                                    <p class="p5">
                                    	配置用户初次使用时的认证操作指引页。如您是初次接触本系统，此步可忽略，采用系统默认配置即可。
                                    </p>
                                    <p class="p10">
                                        <button class="btn green" onclick="rl.page.open('${base}admin/readyEditWechatGuide.do', 'wechatGuide', rl.gui.winDlgOpt);">配置用户指引页</button>
                                    </p>
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