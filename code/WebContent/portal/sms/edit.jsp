<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="edit" /></title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"gui.form.DateSelect",
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"x:mti.popupFormPage"
);

orderjs(function(){
	rlx.mti.createPopupFormPage({
		content : "#mainContent",
		actionBar : [
			{text : "<spring:message code="ok" />", action : doAction},
			{text : "<spring:message code="cancel" />", action : function(){rl.page.close();}}
		]
	});
	
	function doAction() {
	    var mainForm = document.mainForm;
		if (rlx.mti.validate(mainForm)) {
			mainForm.action = "${base}admin/updateSms.do";
			rlx.mti.ajaxSubmit("mainForm","closeAndRefreshOpenerGrid");
		}
	}
	
	
});
</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
   <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
     <input type="hidden" name="id" value="${dto.branchId}" />
       <div class="group">
           <table border="0" class="grid_fields grid_fields_cols4">
               <tr>
                  <th class="label">已用短信</th>
                  <td class="field_ctn">${dto.smsUsed}</td>
               </tr>
               <tr>                  
                  <th class="label">剩余短信</th>
                  <td class="field_ctn">
                      <input class="field" name="smsRemain" dataType="Number" value="${dto.smsRemain}" msg="必须是数字" style="width:100px"/>
                      <font color="red">*</font>                      
                  </td>
               </tr>                                                                                                                                    
           </table>
       </div>
   </form>
</div>
</body>
</html>