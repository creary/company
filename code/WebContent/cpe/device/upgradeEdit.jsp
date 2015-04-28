<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑自动升级</title>
<link rel="rl-page-icon" href="${imagePath}add.gif" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"x:mti.popupFormPage"
);

function doChange(){
   mainForm.action = "${base}cpe/upgradeReadyAdd.do";
   mainForm.submit();
}

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
		   mainForm.action = "${base}cpe/upgradeUpdate.do";
		   rlx.mti.ajaxSubmit("mainForm","closeAndRefreshOpenerGrid");
	   }
	}	
});

</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
  <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
     <input type="hidden" name="branchId" value="${dto.branchId}" />
     <div class="group">
        <table border="0" class="grid_fields grid_fields_cols4">
           <tr>
               <th class="label">商家</th>
               <td class="field_ctn" >${dto.branch}</td>                             
           </tr>                                            
           <tr>
               <th class="label">自动升级</th>
               <td class="field_ctn">
                   <input name="flag" type="radio" value="1" ${dto.flag==1?"checked":""}/>是&nbsp;&nbsp;<input name="flag" type="radio" value="0" ${dto.flag==0?"checked":""}/>否
                   <br/>
                   <div style="padding-left:10px; color:gray;"> 
					    如果选择“是”，则当固件有新版本时，系统将自动为设备升级固件。
				   </div>
               </td>               
           </tr>               
        </table>
     </div>
  </form>
</div>
</body>
</html>