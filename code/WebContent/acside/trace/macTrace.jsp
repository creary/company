<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>[${mac}]溯源记录</title>
<link rel="rl-page-icon" href="${imagePath}view_detail.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.listPage"
);

orderjs(function(){

rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [						
			{
				text : "查询",
				icon : "${imagePath}search.gif", 
				action : function(){
					rlx.mti.listPageSearchDialog.show();
				}
			},			
			rlx.mti.searchResetBtnOptions
		]
	},	
	grid :{
		dataUrl : "${base}detect/traceFlowList.do${pstr}",
		dataFields : ["logTime","url"],
		dataPrimaryKey : "sessionId",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	            
            {caption: "时间", width: "150", name: "logTime"},
            {caption: "URL", width: "600", name: "url"}	            	
		]
	}
});	
});
</script>
</head>
<body>
<form id="queryForm" name="queryForm" method="post" class="rlx_mti_list_query_form" style="display:none;">
   <table class="fields_layout">
      <tr>
		  <th>URL</th>
		  <td>
			 <input type="text" class="t" name="url" />
		  </td>
      </tr>      
   </table>
</form>
</body>
</html>