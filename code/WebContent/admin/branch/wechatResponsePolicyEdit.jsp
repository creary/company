﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信认证 IP 回复策略-编辑</title>
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
	
	function notifyWechatRspPoliciesChange(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.wechatRspPoliciesObserver)){
			opener.wechatRspPoliciesObserver.fireEvent("change");
		}
	}
	
	function doAction() {
	    var mainForm = document.mainForm;
		if (rlx.mti.validate(mainForm)) {
			var ipList = mainForm.ip.value.split(","),
				rIp = Validator.Ip,
				isIp = function(ip){
					ip = rl.trim(ip);
					return rIp.test(ip);
				};
			if(!rl.every(ipList, isIp)){
				alert("IP 输入有误!");
				mainForm.ip.focus();
				return false;
			}
			if(!rl.isNonNullStr(mainForm.msgCover.value)){
				alert("必须上传消息封面图片!");
				return false;
			}			
			JqAjax.request({
				sendForm : mainForm,
				url : "${base}admin/updateWechatIPPolicy.do",
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
					notifyWechatRspPoliciesChange();
					rl.page.close();
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
		ImgUploader("#msgCoverUploader");
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
        	<h1>微信认证 IP 回复策略</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${dto.id}" />
                                <div class="section first">
                                    <h3>策略名称</h3>
                                    <p class="p10">
                                        <input name="name" dataType="Require" class="field" value="${dto.name}" /> <span class="warn">*</span>
                                    </p>
                                </div>
                                <div class="section first">
                                    <h3>IP 地址</h3>
                                    <p class="p5">系统检测到用户的 IP 与此相符时，将以本策略消息替代默认的回复消息。
                                    </p>
                                    <p class="p10">
                                        <input name="ip" dataType="Require" class="field" value="${dto.ipAddress}" /> <span class="warn">*</span>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>策略消息</h3>
                                    <p class="p5">
                                    	策略对应的回复消息，其中消息的标题将与系统默认的回复消息标题保持一致，无法更改，如需更改，请在系统默认的回复消息配置页中修改。
                                    </p>
                                    <div class="fields_box">
                                        <div class="section first">
                                            <h5>消息封面图片</h5>
                                            <p class="p5">
                                            	封面图片，上传文件大小不超过 1M，格式为 png、jpg、gif，尺寸建议为：宽 360 像素、高 200 像素。
                                            </p>
                                            <div class="p10" id="msgCoverUploader">
                                                <input type="hidden" name="msgCover" value="${dto.imgUrl}" />
                                                <div class="soof_img_viewer" style="height: 150px;">
                                                    <img class="img_holder" />
                                                    <div class="smartmask">
                                                    	<div class="vplaceholder"></div>图片
                                                    </div>
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
                                                <input name="msgHref" dataType="Require" class="field" id="field_msgHref" value="${dto.postUrl}"/> <span class="warn">*</span>
                                            </p>
                                        </div>
                                    </div>
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