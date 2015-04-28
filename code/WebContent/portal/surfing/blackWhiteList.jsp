<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<link rel="rl-page-icon" href="${imagePath}dept_16.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.listPage"
);

orderjs(function(){

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}portal/blackWhiteReadyEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'surfingAccountEdit\', rl.gui.paneDlgOpt);"><img src="${imagePath}edit.gif" border="0"/></a>';    
}
 
rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [
			{
				text : "增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}portal/blackWhiteReadyAdd.do?bw=${bw}","blackWhiteReadyAdd", rl.gui.paneDlgOpt);
				}
			},
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}portal/blackWhiteDelete.do";
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
		dataUrl : "${base}portal/blackWhiteList.do?branchId=${branchId}&bw=${bw}",
		dataFields : ["id","branch","mac","reason","bwText"],
		dataPrimaryKey : "id",
		columns : [
			{ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
            {caption: "商家", width: "200", name: "branch"},
            {caption: "MAC", width: "150", name: "mac"},
	        {caption: "备注", width: "150", name: "reason"},
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
		  <th>MAC</th>
		  <td>
			 <input type="text" class="t" name="mac" />
		  </td>
	  </tr>
   </table>
</form>
</body>
</html>