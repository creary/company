<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>增加</title>
<link rel="rl-page-icon" href="${imagePath}add.gif" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs.config("shim", {
	"open.kindeditor.kindeditor" : {
		deps : ["css>open.kindeditor.themes.default.default"],
		exports : "KindEditor"
	}
});
orderjs.config("shim", {
	"open.kindeditor.lang.zh_CN" : {
		deps : ["open.kindeditor.kindeditor", "css>open.kindeditor.themes.default.default"],
		exports : "KindEditor"
	}
});

orderjs(
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"x:mti.popupFormPage",
	"open.jquery.jquery",
    "open.kindeditor.lang.zh_CN"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		K = orderjs("open.kindeditor.kindeditor");
    
	rlx.mti.createPopupFormPage({
		content : "#mainContent",
		actionBar : [
			{text : "确定", action : doAction},
			{text : "取消", action : function(){rl.page.close();}}
		]
	});
	
	function doAction() {
	   var mainForm = document.mainForm;
	   if (rlx.mti.validate(mainForm)) {
		   mainForm.action = "${base}cpe/softSave.do";
		   rlx.mti.ajaxSubmit("mainForm","closeAndRefreshOpenerGrid");
	   }
	}
	rl.onReady(function() {
		var qrcodeUploadBtn = K.uploadbutton({
			button : K('#chooseBin')[0],
			url : '${base}cpe/device/uploadJson.jsp?dir=bin&folder=/acs/wesite/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#binUrl').val(url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		qrcodeUploadBtn.fileBox.change(function(e) {
			qrcodeUploadBtn.submit();
		});
	}); 
	
	window.saveForm = saveForm;	

});

</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
    <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
        <div class="group">
            <table border="0" class="grid_fields grid_fields_cols4">
				<tr>
	                <th class="label">固件</th>
	                <td class="field_ctn">
	                    <input id="binUrl" class="field" name="bin" style="width:200px" dataType="Require" msg="必填项"/>
	                	<input type="button" id="chooseBin" class="btn white" value="选择BIN文件" />
	                </td>                
		        </tr>	
            </table>
        </div>
    </form>
</div>
</body>
</html>