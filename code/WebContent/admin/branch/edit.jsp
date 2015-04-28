<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"x:mti.popupFormPage"
);

orderjs(function(){
	function doAction() {
	    var mainForm = document.mainForm;
		if (Validator.Validate(mainForm, 3)) {
			mainForm.action = "${base}admin/branchUpdate.do";
			rlx.mti.ajaxSubmit("mainForm","closeAndRefreshOpenerGrid");
		}
	}
	
	rlx.mti.createPopupFormPage({
		content : "#mainContent",
		actionBar : [
			{text : "确定", action : doAction},
			{text : "取消", action : function(){rl.page.close();}}
		]
	});
});
</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
   <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
       <input type="hidden" name="id" value="${dto.id}" />
       <div class="group">
           <table border="0" class="grid_fields grid_fields_cols4">
               <tr>
                   <th class="label">名称</th>
                   <td class="field_ctn"><input class="field" name="name" dataType="Require" msg="必填项" value="${dto.name}"/><font color="red">*</font></td>                   
               </tr>
           </table>
       </div>
   </form>
</div>
</body>
</html>