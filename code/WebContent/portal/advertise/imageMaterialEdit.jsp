﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑图片素材</title>
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
	var jQuery = orderjs("open.jquery.jquery"),
		K = orderjs("open.kindeditor.kindeditor"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect");
	
	jQuery.ajaxSetup({
		dataType : "json",
		type : "post",
		cache : false
	});
	
	var materialDirect = new JqueryAjaxDirect({
		services : {
			update : function(options){
				var url = "${base}portal/materialUpdate.do",
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
	
	function notifyDeviceChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.materialDataObserver)){
			opener.materialDataObserver.fireEvent("change");
		}
	}
	
	var materialUploader;
	function doAction() {
	    var mainForm = document.mainForm;
		
		if(!mainForm.file.value){
			alert("必须上传图片文件!");
			return false;
		}
		if (rlx.mti.validate(mainForm)) {
			materialDirect.invoke("update", {
				beforeSend : setAjaxStartView,
				success : function(){
					alert("保存成功！");
					notifyDeviceChangeToOpener();
					rl.page.close();
				},
				complete : setAjaxEndView
			});
		}
	}
	
	rl.onReady(function(){
		jQuery("#btn_saveForm")
			.prop("disabled", false)
			.on("click", doAction);
		
		materialUploader = K.uploadbutton({
			button : K('#btn_uploadMaterial')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/&tag=${branchId}_imageMaterial',
			afterUpload : function(data) {
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#field_file').val(url);
					K('#imgHolder')
						.attr("src", url)
						.css("display", "inline");
					
					jQuery("#uploadingTip")
						.html("上传成功!")
						.delay(1000)
						.fadeOut();
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				jQuery("#uploadingTip").hide();
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		materialUploader.fileBox.change(function(e) {
			jQuery("#uploadingTip")
				.html("正在上传: " + this.value)
				.show();
			materialUploader.submit();
		});
		
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
        	<h1>编辑图片素材</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${dto.id}" />
                                <input type="hidden" name="branchId" value="${dto.branchId}" />
                                <input type="hidden" name="createTime" value="${createTime}" />
                                <div class="section first">
                                    <h3>素材名称</h3>
                                    <p class="p10">
                                    	<input name="name" value="${dto.name}" class="field" dataType="Require" />
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>图片文件</h3>
                                    <p class="p5">
                                        文件大小不超过 1 MB；格式为: gif, jpg, png。
                                    </p>
                                    <div class="p10">
                                        <div class="soof_img_viewer">
                                            <input type="hidden" id="field_file" name="file" />
                                            <img src="${dto.file}" id="imgHolder" class="img_holder" />
                                            <div class="mask">图片</div>
                                        </div>
                                        <div class="soof_img_viewer_upload_wrap">
                                            <span><input type="button" id="btn_uploadMaterial" class="btn white" value="上传图片"></span>
                                            <span id="uploadingTip"></span>
                                        </div>
                                        <div class="clearer"></div>
                                    </div>
                                </div>
                                <div class="section">
                                    <h3>图片链接 <span class="desc">(可不填)</span></h3>
                                    <p class="p10">
                                    	点击图片打开的链接。如果将该广告投放在欢迎页，您应该将该链接的域名添加至设备的域名白名单。
                                    </p>
                                    <p class="p10">
                                    	<input name="link" class="field l" value="${dto.link}"/>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>链接目标窗口</h3>
                                    <p class="p10">
                                    	选择图片链接的打开方式。
                                    </p>
                                    <p class="p10">
                                        <input type="radio" name="linkTarget" ${dto.linkTarget=='_blank'?"checked":""} value="_blank" class="field_selector" id="field_linkTarget_blank" checked="checked" /><label class="selector_label" for="field_linkTarget_blank">新窗口（默认）</label>&nbsp;&nbsp;&nbsp;
                                        <input type="radio" name="linkTarget" ${dto.linkTarget=='_self'?"checked":""} value="_self" class="field_selector" id="field_linkTarget_self"  /><label class="selector_label" for="field_linkTarget_self">原窗口</label>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>描述 <span class="desc">(可不填)</span></h3>
                                    <p class="p10">
                                    	<textarea name="description" id="field_description" class="field l" onfocus="this.style.height='50px';" onblur="this.style.height='';">${dto.description}</textarea>
                                    </p>
                                </div>
                                <div class="soof_action_bar">
                                    <button id="btn_saveForm" type="button" class="btn primary">保 存</button>
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