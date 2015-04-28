﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信认证指引页设置</title>
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
	"x:mti.ajaxSubmit",
	"open.jquery.jquery",
	"lib.rpc.JqueryAjaxDirect",
	"open.kindeditor.lang.zh_CN"
);

orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery"),
		K = orderjs("open.kindeditor.kindeditor"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect");
	
	jQuery.ajaxSetup({
		dataType : "json",
		method : "post",
		cache : false
	});
	
	var guideDirect = new JqueryAjaxDirect({
		services : {
			save : function(options){
				var url = "${base}admin/updateWechatGuide.do",
					data = jQuery("#mainForm").serialize();
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			}
		}
	});
	
	function setAjaxStartView(){
		jQuery("#btn_saveForm")
			.prop("disabled", true)
			.addClass("disabled")
			.text("正在保存...");
	}
	function setAjaxEndView(){
		jQuery("#btn_saveForm")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("保 存");
	}
	
	window.contentEditor = null;
	function saveForm() {
	    var mainForm = document.mainForm,
			guideType = jQuery("input[name='guideType']:checked").val();
		
		if(guideType == "custom"){
			var ctHtml = rl.trim(contentEditor.html());
			if(ctHtml == ""){
				alert("自定义指引的内容不能为空！");
				return false;
			}
			jQuery("#field_customGuide").html(ctHtml);
		}
		
		guideDirect.invoke("save", {
			beforeSend : setAjaxStartView,
			success : function(){
				alert("保存成功！");
			},
			complete : setAjaxEndView
		});
	}
	
	rl.onReady(function(){
		window.contentEditor = K.create('textarea[name="customGuide"]', {
			height : 500,
			minWidth : 400,
			width : 400,
			resizeType : 1,
			allowImageRemote : false,
			allowImageUpload : true,
			uploadJson : "${base}portal/advertise/upload_json.jsp?folder=/acs/upload/&tag=${branchId}_ad",
			items : [
				'fontsize', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'image']
		});
		
		jQuery("#btn_saveForm")
			.prop("disabled", false)
			.on("click", saveForm);
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
        	<h1>微信认证指引页设置</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <div class="section first">
                                    <h3>关于微信认证指引页</h3>
                                    <p class="p5">
                                    	为了让初次使用的用户更快的熟悉微信认证的操作步骤，我们提供了微信认证指引页。当用户的手机或平板连接 Wifi 打开浏览器访问网页后，将被强制访问该指引页而非 Portal 的欢迎页（注意：只有认证策略里启用了显示指引页，才有效，否则依旧访问 Portal 原有的欢迎页）。
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>指引内容</h3>
                                    <p class="p10">
                                       <input type="radio" name="guideType" class="field_selector" id="field_guideType_default" value ="default" ${dto.flag=="default"?"checked":""}/><label class="selector_label" for="field_guideType_default">系统默认指引</label>
                                    </p>
                                   <p class="p5" style="padding-left:20px;">系统为 3 种上网认证方式的提供了默认指引内容。<a href="${base}admin/branch/wechatGuidePreview.jsp?guideType=default" target="wechatGuidePreview">点此查看&gt;&gt;</a></p>
                                    <p class="p10">
                                       <input type="radio" name="guideType" class="field_selector" id="field_guideType_custom" value ="custom" ${dto.flag=="custom"?"checked":""}/><label class="selector_label" for="field_guideType_custom">自定义指引</label>
                                    </p>
                                    <div class="p5" style="padding-left:20px;">
                                    自定义您的微信认证指引内容。您可以图文并茂的方式介绍您所采用的认证方式的详细操作步骤，图片宽度控制在 400 像素之内，以便手机阅读，文件大小不超过 1MB；格式为 png、jpg、gif。
                                        <p class="p10">
                                            <textarea name="customGuide" id="field_customGuide" style=" height:500px; width:400px;">${dto.pageValue}</textarea>
                                        </p>
                                    </div>
                                </div>
                                <div class="soof_action_bar">
                                    <button id="btn_saveForm" type="button" class="btn primary">保 存</button>
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