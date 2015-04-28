<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>增加 SSID 策略</title>
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
	function notifyChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.ssidPolicyDataObserver)){
			opener.ssidPolicyDataObserver.fireEvent("change");
		}
	}
	
	function doAction() {
	    var mainForm = document.mainForm;
		if (rlx.mti.validate(mainForm)) {
			mainForm.action = "${base}cpe/ssidCodeSave.do";
			rlx.mti.ajaxSubmit("mainForm", function(status, msg){
				alert(msg);
				notifyChangeToOpener();
				rl.page.close();
			});
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
       <div class="group">
           <table border="0" class="grid_fields grid_fields_cols4">
               <tr>
                   <th class="label">策略名称</th>
                   <td class="field_ctn">
                       <input class="field" name="name" dataType="Require" msg="必填项" /> <font color="red">*</font>
                   </td>                   
               </tr>
               <tr>
                   <th class="label">SSID 名称</th>
                   <td class="field_ctn">
                   		支持中英文，中间不能有空格。<br />
                        <span><input name="ssid" id="field_ssid" dataType="Require" class="field" /> <font color="red">*</font> </span> <br />
                        <font color="gray">例： 广州酒家-上下九店</font>
                   </td>
               </tr>
           </table>
       </div>
   </form>
</div>
</body>
</html>