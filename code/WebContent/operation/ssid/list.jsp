<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商家管理</title>
<link rel="rl-page-icon" href="${imagePath}dept_16.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(function(){rl.page.callTop("focusOnMenuItem", "cpe_ssidCode");});
orderjs(
	"x:mti.listPage"
);

orderjs(function(){
	//observe policy data.
	window.ssidPolicyDataObserver = new rl.util.EventProvider;
	ssidPolicyDataObserver.on("change", function(){
		rlx.mti.listPageGrid.load();
	});

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}cpe/ssidCodeReadyEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'ssidCodeReadyEdit\', rl.gui.paneDlgOpt);"><img src="${imagePath}edit.gif" border="0"/></a>';    
}
	
function genNameCol(cellText, colIndex, rowIndex, dataSource){
	var name = dataSource.getValue("name");
	var url = "${base}cpe/ssidCodeReadyEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" title=\"打开策略详细\" onclick="rl.page.open(\'' + url + '\', \'ssidCodeReadyEdit\', rl.gui.paneDlgOpt);">' + name + '</a>';    
} 
 
rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [				
			{
				text : "增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}cpe/ssidCodeReadyAdd.do","ssidCodeReadyAdd", rl.gui.paneDlgOpt);
				}
			},
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}cpe/ssidCodeDelete.do";
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
		dataUrl : "${base}cpe/ssidCodeList.do",
		dataFields : ["id","name","ssid"],
		dataPrimaryKey : "id",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
	        {caption: "名称", width: 200, name: "id", convert : genNameCol},
	        {caption: "SSID", width: 200, name: "ssid"},
	        {caption: "编辑", width: "30", name: "id", convert : doEdit}
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
         <th>名称</th>
         <td>
            <input type="text" class="t" name="name" />
         </td>
         <th>SSID</th>
         <td>
            <input type="text" class="t" name="ssid" />
         </td>
      </tr> 
   </table>
</form>
</body>
</html>