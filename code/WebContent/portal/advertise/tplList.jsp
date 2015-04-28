<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>模板列表</title>
<link rel="rl-page-icon" href="${imagePath}msb.gif" />
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
	    var selected = false;
	    if(mainForm.id.length == null){
	        selected = mainForm.id.checked;
	    }else{    
		    for(var i=0;i<mainForm.id.length;i++){
		        if(mainForm.id[i].checked){
		            selected = true;
		            break;
		        }
		    }
	    }
	    if(!selected){
	        alert("至少选择一个模板.");
	        return;
	    }
	    mainForm.action = "${base}weisite/changeTemplate.do";
		rlx.mti.ajaxSubmit("mainForm","closeAndRefreshOpenerGrid");		
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
               <security:authorize ifAnyGranted="ROLE_SOOFOUND,ROLE_ADMIN">
	               <tr>		      
					  <td colspan="2">
                          <font color='red'>对于超级管理员，切换模板则是将该模板设置为默认模板。</font>                      
                      </td>
	               </tr>  
               </security:authorize>  
               <tr>
			      <th class="label">名称</th>			      
				  <th class="label">创建时间</th>
               </tr>  
               <c:forEach items="${tpls}" var="tpl">
	               <tr>
					  <td class="field_ctn"><label><input name="id" type="radio" value="${tpl.id}" ${curTpl==tpl.id?"checked":""}/>&nbsp;${tpl.name}</label></td>
	                  <td class="field_ctn">${tpl.createTime}</td>
	               </tr>  
               </c:forEach>  
           </table>
       </div>
   </form>
</div>
</body>
</html>