<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>分配序列号</title>
<link rel="rl-page-icon" href="${imagePath}apply.gif" />
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
			{text : "确定", action : doAction},
			{text : "取消", action : function(){rl.page.close();}}
		]
	});
	
	function doAction() {
	    var mainForm = document.mainForm;
		if (rlx.mti.validate(mainForm)) {
			mainForm.action = "${base}admin/licenseActive.do";
			rlx.mti.ajaxSubmit("mainForm","closeAndRefreshOpenerGrid");
		}
	}
});
</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
   <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
       <input type="hidden" name="macs" value="${macs}" />
       <div class="group">
           <table border="0" class="grid_fields grid_fields_cols4">
              <tr>
                  <th class="label">将这${num}序列号分派给以下代理商或商家</th>
              </tr>  
              <tr>    
                  <td class="field_ctn">
                      <select name="branchId" style="width:300px"> 
	                      <c:forEach items="${branchs}" var="branch">  
			                   <option value="${branch.id}">${branch.name}</option>  
			              </c:forEach> 
		              </select>	
                  </td>
               </tr>                                                                                                                       
           </table>
       </div>
   </form>
</div>
</body>
</html>