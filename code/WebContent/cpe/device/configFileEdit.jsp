<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑配置</title>
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
		   var selectbox = document.getElementsByName('props');
		   if(selectbox.length > 0){
				var has = false;
				for(var selIndex=0; selIndex<selectbox.length; selIndex++){
					if(selectbox[selIndex].checked){
						has = true;
						break;
					}
				}
				if(!has){
					alert("必须选择一项");
					return false;
				}	    
		   }
		   mainForm.action = "${base}cpe/configUpdate.do";
		   rlx.mti.ajaxSubmit("mainForm","closeAndRefreshOpenerGrid");
	   }
	}
	
	function changeModel() {
	   var mainForm = document.mainForm;
	   mainForm.action = "${base}cpe/softReadyAdd.do?tag=2";
	   mainForm.submit();
	}   	
	window.changeModel = changeModel;
});
</script>
<style type="text/css">
.field_ctn{
	vertical-align:top !important;
}
</style>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
   <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
       <input type="hidden" name="id" value="${dto.id}" />
       <input type="hidden" name="branchId" value="${dto.branchId}" />
       <div class="group">
           <table border="0" class="grid_fields grid_fields_cols4">                
               <tr>
			      <th class="label" style="width:100px">名称</th>
				  <td class="field_ctn">
					  <input class="field" name="descr" style="width:190px" value="${dto.descr}" dataType="Require" msg="必填项" /><font color="red">*</font> 
				  </td>
			   </tr>
               <tr>
                    <th class="label" style="width:150px">型号</th>
                    <td class="field_ctn">
				        <select name="productModel" style="width:200px" onchange="changeModel()">  
			                <c:forEach items="${productModel}" var="pm">  
			                    <option value="${pm}" ${pm==selectedModel?"selected":""}>${pm}</option>  
			                </c:forEach> 
				        </select>
                    </td>
               </tr> 
               <tr>
			      <th class="label" style="width:100px">默认配置</th>
				  <td class="field_ctn">
					  <input name="version" type="checkbox" value="default" ${dto.version=='default'?"checked":""}/>
					  <div style="padding-left:20px; color:gray;"> 
					          勾选则设置此配置为该型号设备的默认配置，设备上电时会自动下发这个配置。默认配置只能有一个。
			            <security:authorize ifAnyGranted="ROLE_SOOFOUND,ROLE_ADMIN">
			                 <br/>如果选择了acs或wifiant相关配置项，则为“接入配置”。
			            </security:authorize>  					          
					  </div>	 
				  </td>
			   </tr>               
			   <tr>
			        <th class="label" style="width:100px">参数</th>
					<td class="field_ctn">
                    	<div style="padding:1em 2em;">
                        	提示：在修改任一项参数之前，请先勾选该参数前的复选框，否则该项修改无效。
                        </div>
					    <table border="0" class="grid_fields">
                            <c:forEach items="${props}" var="prop" varStatus="item">
	                            <tr>
	                                <td class="field_ctn" style="width:20px"> ${item.index + 1} </td>
	                                <td class="field_ctn">
                                           <input type="checkbox" class="field_selector" name="props" id="propField_${item.index}" value="${prop.id}" ${prop.checked} />
                                           <label for="propField_${item.index}" class="selector_label">${prop.cnName}</label>
                                           <div style="padding-left:20px; color:gray;">
                                               ${prop.descr}
                                           </div>
	                                </td>
	                                <td class="field_ctn" style="width:200px"> 
                                      <c:choose>
	                                      <c:when test="${prop.enName=='AuthRssiThres'||prop.enName=='AssocReqRssiThres'||prop.enName=='KickStaRssiLow'}">
		                                     -
		                                  </c:when>	   
		                                  <c:otherwise>
                                          &nbsp;
                                          </c:otherwise>
                                         </c:choose>                                   
                                      ${prop.boxString}
                                      <c:if test="${prop.enName=='wireless tx power'||prop.enName=='wireless tx power for 5G'}">
	                                      %
	                                  </c:if>	                                      
                                      <c:if test="${prop.enName=='wshaper uplink'||prop.enName=='wshaper downlink'}">
	                                      KB
	                                  </c:if>
                                      <c:if test="${prop.enName=='AuthRssiThres'||prop.enName=='AssocReqRssiThres'||prop.enName=='KickStaRssiLow'||prop.enName=='KickStaRssiLow_5G'}">
	                                     dbm
	                                  </c:if>	                                      
                                       </td>	                                    
	                            </tr>
                            </c:forEach>
                        </table> 
					</td>
				</tr>                                                                         
           </table>
       </div>
   </form>
</div>
</body>
</html>