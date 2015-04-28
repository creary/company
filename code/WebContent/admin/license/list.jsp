<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>AP序列号管理</title>
<link rel="rl-page-icon" href="${imagePath}credence.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.listPage"
);

orderjs(function(){

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}admin/licenseReadyEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'branchReadyEdit\', rl.gui.paneDlgOpt);"><img src="${imagePath}edit.gif" border="0"/></a>';    
}

rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [
			<security:authorize ifAnyGranted="ROLE_SOOFOUND,ROLE_ADMIN">
			{
				text : "增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}admin/licenseReadyAdd.do","readyAdd", rl.gui.paneDlgOpt);
				}
			}, 
			{
				text : "批量增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}admin/licenseReadyBatchAdd.do","readyBatchAdd", rl.gui.makeDlgOpt(400));
				}
			},  
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}admin/licenseDelete.do";
					if(!len){
						alert("请选择要删除的记录.");
						return;
					}					
					if(len && confirm("确认要删除这" + len + "条记录?")){
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
		    </security:authorize>			
			{
				text : "分配",
				icon : "${imagePath}apply.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}admin/licenseReadyActive.do";
					if(!len){
						alert("请选择你要分配的序列号");
						return;
					}					
					var qs = grid.body.encodeSelectedDataIdList2QS();
					url += "?" + qs;
                    rl.page.open(url, "licenseActive", rl.gui.paneDlgOpt);					
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
		dataUrl : "${base}admin/licenseList.do?branchId=${branchId}",
		dataFields : ["mac","branch","flashId","apkey","gradeName","logTime"],
		dataPrimaryKey : "mac",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
	        {caption: "序列号", width: "150", name: "mac"},
	        <security:authorize ifAnyGranted="ROLE_SOOFOUND">
	            {caption: "FlashID", width: "150", name: "flashId"},
	            {caption: "apkey", width: "200", name: "apkey"},
	        </security:authorize>  
	        {caption: "商家", width: "300", name: "branch"},
	        {caption: "级别", width: "80", name: "gradeName"},
	        {caption: "分配时间", width: "150", name: "logTime"},
	        {caption: "编辑", width: "30", name: "mac", convert : doEdit}	
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
         <th>序列号</th>
         <td>
            <input type="text" class="t" name="mac" />
         </td>
         <th>商家</th>
         <td>
            <input type="text" class="t" name="branch" />
         </td>
      </tr> 
   </table>
</form>
</body>
</html>