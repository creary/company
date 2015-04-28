﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信认证回复消息</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"open.jquery.jquery",
	"lib.rpc.JqAjax"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		JqAjax = orderjs("lib.rpc.JqAjax");
	
	function doAction() {
	    var mainForm = document.mainForm;
		if (rlx.mti.validate(mainForm)) {
			if(!rl.isNonNullStr(mainForm.welcMsgCover.value)){
				alert("必须上传消息封面图片!");
				return false;
			}
			JqAjax.request({
				sendForm : mainForm,
				url : "${base}admin/updateWechatResponse.do",
				beforeSend : function(){
					jQuery("#btn_action")
						.prop("disabled", true)
						.addClass("disabled")
						.text("正在保存...");
				},
				complete : function(){
					jQuery("#btn_action")
						.prop("disabled", false)
						.removeClass("disabled")
						.text("保 存");
				},
				success : function(data){
					alert("保存成功！");
				},
				error : function(msg){
					alert(msg);
				}
			});
		}
	}
	
	rl.onReady(function(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
	});
});

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
	"open.kindeditor.lang.zh_CN",
	"cloudac:ImgUploader"
);
orderjs(function(){
	var ImgUploader = orderjs("cloudac:ImgUploader");
	
	orderjs.ready(function(){
		ImgUploader("#welcMsgCoverUploader");
	});
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>微信认证回复消息</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <div class="section first">
                                    <h3>关于微信认证回复消息</h3>
                                    <p class="p5" style="float:right; text-align:center;">
                                        <img src="${base}resources/image/default/mix/wechat_reply_min.jpg" />
                                    </p>
                                    <p class="p5" style="margin-right:200px;">
                                    	为了给用微信认证的 WiFi 用户更友好的用户体验，您可以在此页面配置公众号的回复消息内容。当用户发送认证请求时，将回复该内容。
                                    </p>
                                    <div class="clearer"></div>
                                </div>
                                <div class="section">
                                    <h3>系统默认回复</h3>
                                    <p class="p5">
                                        系统默认回复给用户的欢迎图文消息。
                                    </p>
                                    <div class="fields_box">
                                        <div class="section first">
                                            <h5>消息标题</h5>
                                            <p class="p10">
                                                <input name="welcMsgTitle" dataType="Require" class="field" value="${welcMsgTitle}" /> <span class="warn">*</span>
                                            </p>
                                            <p class="p5">
                                            	<input type="checkbox" name="showPCAuth" class="field_selector" id="field_showPCAuth" ${showPCAuth=='1'?"checked":""}  /><label class="selector_label" for="field_showPCAuth">标题后，显示电脑上网的用户名和密码。</label> <span class="desc">如需支持用户的电脑上网，请勾选此项。</span>
                                            </p>
                                        </div>
                                        <div class="section">
                                            <h5>消息封面图片</h5>
                                            <p class="p5">
                                            	封面图片，上传文件大小不超过 1M，格式为 png、jpg、gif，尺寸建议为：宽 360 像素、高 200 像素。
                                            </p>
                                            <div class="p10" id="welcMsgCoverUploader">
                                                <input type="hidden" name="welcMsgCover" value="${welcMsgCover}" />
                                                <div class="soof_img_viewer" style="height: 150px;">
                                                    <img src="${base}resources/image/default/mix/wechat_welcome.jpg" class="img_holder" />
                                                    <div class="mask">图片</div>
                                                </div>
                                                <div class="soof_img_viewer_upload_wrap">
                                                    <span><input type="button" class="btn white uploader" value="上传新图片" data-url="${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/"></span>
                                                </div>
                                                <div class="clearer"></div>
                                            </div>
                                        </div>
                                        <div class="section">
                                            <h5>消息链接</h5>
                                            <p class="p5">
                                            	点击标题或图片将打开该链接指向的页面。
                                            </p>
                                            <p class="p10">
                                                <input name="welcMsgHref" dataType="Require" class="field" id="field_welcMsgHref" value="${welcMsgHref}"/> <span class="warn">*</span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="section">
                                    <h3>IP 回复策略</h3>
                                    <p class="p5">
                                        除了系统默认的回复，您还可以根据用户所在网络的出口 IP 来定义个性化的回复消息，从而实现用户在不同地点（根据 IP 判断）进行请求时，显示不同的欢迎消息。注：该功能适用于具有固定出口 IP 的网络。
                                    </p>
                                    <p class="p10">
                                    	<button class="btn green" onclick="rl.page.open('${base}admin/branch/wechatResponsePolicy.jsp', 'wechatResponsePolicy', rl.gui.winDlgOpt);">配置 IP 回复策略</button>
                                    </p>
                                </div>
                                <div class="soof_action_bar">
                                    <button id="btn_action" type="button" class="btn primary">保 存</button>
                                    <button type="button" class="btn white" onclick="rl.page.close();">取消</button>
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