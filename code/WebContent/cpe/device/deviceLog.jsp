<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备日志</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.listPage"
);

orderjs(function(){

function doEdit(cellText, colIndex, rowIndex, dataSource){
	var url = "${base}cpe/deviceLogRead.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'ReadyEdit\', rl.gui.winDlgOpt);"><img src="${imagePath}view_info_log.gif" border="0"/></a>';    
}

rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [			
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}cpe/deviceLogDelete.do";
					if(!len){
						alert("请选择要删除的记录");
						return;
					}					
					if(len && confirm("确定要删除这 " + len + " 条记录?")){
						var qs = grid.body.encodeSelectedDataIdList2QS();
						url += (url.contains("?") ? "&" : "?") + qs;						
						rl.rpc.sdtGet(url, function(status, msg){
							grid.load();
						}, function(status, msg){
							alert(msg);
						});
					}
				}
			},
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
		dataUrl : "${base}cpe/deviceLogList.do?branchId=${branchId}",
		dataFields : ["id","branch","name","serialNumber","message","operator","logTime"],
		dataPrimaryKey : "id",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
            {caption: "时间", width: "150", name: "logTime"},
	        {caption: "设备名称", width: "200", name: "name"},
	        {caption: "设备序列号", width: "150", name: "serialNumber"},
            {caption: "日志", width: "400", name: "message"},
            {caption: "操作者", width: "100", name: "operator"},
            {caption: "商家", width: "200", name: "branch"}	
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
         <th>设备名称</th>
         <td>
             <input type="text" class="t" name="name" />
         </td>
         <th>设备序列号</th>
         <td>
             <input type="text" class="t" name="mac" />
         </td>         
      </tr> 
      <tr>
         <th>商家</th>
         <td>
             <input type="text" class="t" name="branch" />
         </td>
         <th>日志</th>
         <td>
             <input type="text" class="t" name="message" />
         </td>
      </tr> 
   </table>
</form>
</body>
</html>