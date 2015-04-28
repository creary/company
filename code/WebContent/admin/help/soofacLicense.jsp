<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>许可证更新</title>
<link rel="rl-page-icon" href="${imagePath}syslog.gif" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"x:mti.popupFormPage"
);

orderjs(function(){
	rlx.mti.createPopupFormPage({
		content : "#mainContent",
		actionBar : [
			{text : "确 定", action : doAction},
			{text : "取消", action : function(){rl.page.close();}}
		]
	});
	
	function doAction() {
		var mainForm = document.mainForm,
			xmlField = rl.getDom("xmlField");
		
		if (!xmlField.value) {
			alert("请选择许可证文件！");
			return false;
		}
		mainForm.action = "${base}admin/updateLicense.do";
		mainForm.submit();
	}

	function acceptXML(obj) {
		var mainForm = document.mainForm;
		var fileName = obj.value;
		if (rl.isNonNullStr(fileName)) {
			if (!fileName.endsWith(".xml")) {
				alert("许可证文件格式必须为 xml！");
				mainForm.reset();
				return false;
			}
		}
	}
	//expose local to global
	window.acceptXML = acceptXML;
});
</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
    <form id="mainForm" name="mainForm" method="post" enctype="multipart/form-data">
        <div class="group">
        	<div class="g_body">
            	请选择许可证文件，文件格式必须为 xml：
                <div style="padding-top:12px;">
					<input type="file" name="xml" id="xmlField" size="30" onchange="acceptXML(this)"/>
                </div>
                <c:if test="${message ne null}">
                    <div style="padding-top:12px;">${message}</div>
                </c:if>
            </div>
        </div>
    </form>
</div>
</body>
</html>