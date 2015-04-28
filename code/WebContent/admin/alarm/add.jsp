﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>增加公告</title>
<link rel="rl-page-icon" href="${imagePath}add.gif" />
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
	
	var announcementDirect = new JqueryAjaxDirect({
		services : {
			save : function(options){
				var url = "${base}admin/announcementSave.do",
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
	
	function notifyUnreadsChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.announcementDataObserver)){
			opener.announcementDataObserver.fireEvent("change");
		}
	}

	function notifyUnreadsChangeToTop(){
		var top = window.top;
		if(rl.isObject(top) &&
			rl.isObject(top.unreadsDataObserver) &&
			rl.isFunction(top.unreadsDataObserver.fireEvent)){
			top.unreadsDataObserver.fireEvent("change");
		}
	}
		
	var contentEditor;
	function doAction() {
	    var mainForm = document.mainForm;
		
		if (rlx.mti.validate(mainForm)) {
			var ctHtml = rl.trim(contentEditor.html());
			if(ctHtml == ""){
				alert("内容不能为空");
				return false;
			}				
			rl.getDom("field_content").value = ctHtml;
			
			announcementDirect.invoke("save", {
				beforeSend : setAjaxStartView,
				success : function(){
					alert("保存成功！");
					notifyUnreadsChangeToOpener();
					notifyUnreadsChangeToTop();
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
		
		contentEditor = K.create('textarea[name="content"]', {
				height : 300,
				width : 800,
				resizeType : 1,
				uploadJson : "${base}portal/advertise/upload_json.jsp?folder=/acs/upload/&tag=${branchId}_ad",
				items : [
					'fontsize', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
					'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
					'insertunorderedlist', '|', 'image', 'link']
			});
		
	});
});
</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>增加公告</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" autocomplete="off" onsubmit="return false;">
                                <input type="hidden" name="createTime" value="${createTime}" />
                                <div class="section first">
                                    <h3>标题</h3>
                                    <p class="p10">
                                    	公告标题，限 100 字。
                                    </p>
                                    <p class="p10">
                                    	<input name="title" maxlength="100" class="field l" dataType="Require" /> <font color="red">*</font>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>正文</h3>
                                    <p class="p10">
                                    	<textarea name="content" id="field_content" style=" height:300px; width:800px;"></textarea>
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