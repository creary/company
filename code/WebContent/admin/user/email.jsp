<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>发送通知</title>
<link rel="rl-page-icon" href="${imagePath}0303.gif" />
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
			mainForm.action = "${base}admin/sendEmailToUser.do";
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
        <input type="hidden" name="email" value="${dto.email}" />
        <input type="hidden" name="phone" value="${dto.phone}" />    
        <div class="group">
           <table border="0" class="grid_fields grid_fields_cols4">
              <tr>
                  <th class="label">用户名称</th>
                  <td class="field_ctn">${dto.username}</td>
                  <th class="label"></th>
                  <td class="field_ctn"></td>
              </tr>
              <tr>
                  <th class="label">Email</th>
                  <td class="field_ctn">${dto.email}</td>
                  <th class="label">手机</th>
                  <td class="field_ctn">${dto.phone}</td>
              </tr>
              <tr>
                  <th class="label">内容</th>
                  <td class="field_ctn" colspan="3">
                     <textarea class="field" style="height:100px; width:90%;" name="content" dataType="Require" msg="必填项">${content}</textarea>
                  </td>
              </tr>
           </table>
        </div>
    </form>
</div>
</body>
</html>