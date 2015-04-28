﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑设备分布图</title>
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
	
	var articleDirect = new JqueryAjaxDirect({
		services : {
			update : function(options){
				var url = "${base}cpe/saveDeviceLayout.do?gid=${dto.id}",
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
		if(rl.isObject(opener) && rl.isObject(opener.articleDataObserver)){
			opener.articleDataObserver.fireEvent("change");
		}
	}
	
	var contentEditor, uploadCover;
	function doAction() {
	    var mainForm = document.mainForm;
		
		if(!mainForm.cover.value){
			alert("必须上传封面图片!");
			return false;
		}
		if (rlx.mti.validate(mainForm)) {			
			articleDirect.invoke("update", {
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
		
		contentEditor = K.create('textarea[name="clobs_text"]', {
				height : 400,
				width : 800,
				resizeType : 1,
				allowImageUpload : true,
				uploadJson : "${base}portal/advertise/upload_json.jsp?folder=/acs/upload/&tag=${branchId}_ad",
				items : [
					'fontsize', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
					'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
					'insertunorderedlist', '|', 'image'/*, 'media'*/, 'link']
			});
		
		uploadCover = K.uploadbutton({
			button : K('#uploadCover')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/&tag=${branchId}_cover',
			afterUpload : function(data) {
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#coverField').val(url);
					K('#cover')
						.attr("src", url)
						.css("display", "inline");
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadCover.fileBox.change(function(e) {
			uploadCover.submit();
		});
		
	});
});
</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.warn{
	margin-top:.5em;
	color:red;
}
.cover_img_ctn{
	float:left;
	width:240px;
	height:120px;
	text-align:center;
	background-color:#f8f8f8;
	border:solid 1px #ccc;
	overflow:hidden;
}
.cover{
	vertical-align:middle;
	width:100%;
	height:100%;
}
.cover_default{
	line-height:120px;
	font-size:18px;
	color:gray;
}
.cover_upload_wrap{
	float:left;
	padding:100px 0 0 12px;
}
</style>
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
        
             <form name="mainForm" id="mainForm" method="post" autocomplete="off" onsubmit="return false;">                            
                 <p class="p10">
                                                       当前分组：${dto.name}
                 </p>
                 <div class="section">
                     <p class="p5">文件大小不超过 1 MB；格式为: gif, jpg, png。
                     </p>
                     <div class="p10">
                         <div class="cover_img_ctn">
                             <input type="hidden" id="coverField" name="cover" value="${img}" />
                              <a  href="javascript:window.open ('${img}', 'newwindow', 'height=300;width=500;toolbar= no;menubar=no;status=no;top=150;left=150')" >
                              <img src="${img}" id="cover" class="cover" /></a>
                             <div class="cover_default">封面图片</div>
                         </div>
                         <div class="cover_upload_wrap">
                             <input type="button" id="uploadCover" class="btn white" value="上传图片">
                         </div>
                         <div class="clearer"></div>
                     </div>
                 </div>                                
                 <div class="soof_action_bar">
                     <button id="btn_saveForm" type="button" class="btn primary">保 存</button>
                     <button class="btn white" onclick="rl.page.close()">取消</button>
                 </div>
             </form>
             <div class="widthholder"></div>                    
        </div>
    
</div>
</body>
</html>