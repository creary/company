<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Unifi管理</title>
<link rel="rl-page-icon" href="${imagePath}view_detail.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.listPage"
);

orderjs(function(){

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}cpe/unifiReadyEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'branchReadyEdit\', rl.gui.paneDlgOpt);"><img src="${imagePath}edit.gif" border="0"/></a>';    
}
 
rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [
			{
				text : "增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}cpe/unifiReadyAdd.do", "add", rl.gui.paneDlgOpt);
				}
			},
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}cpe/unifiDelete.do?branchId=${branchId}";
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
			{
				text : "分配",
				icon : "${imagePath}state4.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}cpe/unifiReadyActive.do";
					if(!len){
						alert("请选择你要分配的序列号");
						return;
					}					
					var qs = grid.body.encodeSelectedDataIdList2QS();
					url += "?" + qs;
                    rl.page.open(url, "licenseActive", rl.gui.paneDlgOpt);					
				}
			},
			rlx.mti.searchResetBtnOptions
		]
	},	
	grid :{
		dataUrl : "${base}cpe/unifiList.do?branchId=${branchId}",
		dataFields : ["id","acip","port","site","branch"],
		dataPrimaryKey : "id",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
	        {caption: "IP地址", width: "150", name: "acip"},
	        {caption: "端口号", width: "100", name: "port"},
	        {caption: "站点名", width: "200", name: "site"},
	        {caption: "商家", width: "200", name: "branch"},
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
         <th>IP地址</th>
         <td>
            <input type="text" class="t" name="acip" />
         </td>
         <th>站点名</th>
         <td>
            <input type="text" class="t" name="site" />
         </td>
      </tr> 
   </table>
</form>
</body>
</html>