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
orderjs(
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"x:mti.popupFormPage"
);

orderjs(function(){
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
		   mainForm.action = "${base}cpe/unifiSave.do";
		   rlx.mti.ajaxSubmit("mainForm","closeAndRefreshOpenerGrid");
	   }
	}
	
});
</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
  <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
     <input type="hidden" name="branchId" value="${branchId}" />
     <div class="group">
        <table border="0" class="grid_fields grid_fields_cols4">
           <tr>
               <th class="label">IP地址或域名</th>
               <td class="field_ctn">
                   <input class="field" name="acip" dataType="Require" msg="必填项" /><font color="red">*</font>
               </td>
               <th class="label">端口</th>
               <td class="field_ctn">
                   <input class="field" name="port" dataType="Number" msg="必须是数字" value="8443"/><font color="red">*</font>
               </td>
           </tr>                                                        
           <tr>
               <th class="label">站点名</th>
               <td class="field_ctn" colspan="3">
                   <input class="field" name="site" style="width:250px" />(多个以;分隔)
               </td>
           </tr>
           <tr>
               <th class="label">用户名</th>
               <td class="field_ctn">
                   <input class="field" name="username" dataType="Require" msg="必填项" /><font color="red">*</font>
               </td>
               <th class="label">密码</th>
               <td class="field_ctn">
                   <input class="field" name="password" dataType="Require" msg="必填项" /><font color="red">*</font>
               </td>
           </tr>   
        </table>
     </div>
  </form>
</div>
</body>
</html>