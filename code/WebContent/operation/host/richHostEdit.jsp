<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改设备属性</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
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
	
	function notifyDeviceChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.deviceDataObserver)){
			opener.deviceDataObserver.fireEvent("change");
		}
	}
	
	function doAction() {
		var mainForm = document.mainForm;
		if (rlx.mti.validate(mainForm)) {
			mainForm.action = "${base}cpe/richHostUpdate.do";
			rlx.mti.ajaxSubmit("mainForm", function(status, msg){
				alert(msg);
				notifyDeviceChangeToOpener();
				rl.page.close();
			});
		}
	}		
});
</script>
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
   <form id="mainForm" name="mainForm" method="post" onsubmit="return false;" >
      <input type="hidden" name="id" value="${host.id}" />
      <div class="group">
           <table border="0" class="grid_fields grid_fields_cols4">
			   <tr>
			      <th class="label" style="width:100px">MAC</th>
				  <td class="field_ctn">${host.serialNumber}</td>
			      <th class="label" style="width:100px">名称</th>
				  <td class="field_ctn">
					  <input class="field" name="name" dataType="Require" msg="必填项" value="${host.name}" style="width:200px" />	 
				  </td>
			   </tr>   
			   <tr>
			      <th class="label" style="width:100px">设备编号</th>
				  <td class="field_ctn">
					  <input class="field" name="code" value="${host.code}" style="width:200px" />	 
				  </td>
			      <th class="label" style="width:100px">安装位置</th>
				  <td class="field_ctn">
					  <input class="field" name="location" value="${host.location}" style="width:200px" />	 
				  </td>
			   </tr>   
			   <tr>
			      <th class="label" style="width:100px">是否开通</th>
				  <td class="field_ctn">
					  <select name="used" style="width:200px">  
			              <option value="0" <c:if test="${host.used==0}">(未启用)</c:if>>未开通</option>  
			              <option value="1" <c:if test="${host.used==1}">(未启用)</c:if>>已开通</option>
			              <option value="2" <c:if test="${host.used==2}">(未启用)</c:if>>维修</option> 
				      </select>	 
				  </td>
				  <th class="label" style="width:100px"></th>
				  <td class="field_ctn"></td>
			   </tr>                                                                     
           </table>
      </div>
   </form>
</div>
</body>
</html>