<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>固件管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}cpe/softReadyEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'branchReadyEdit\', rl.gui.paneDlgOpt);"><img src="${imagePath}edit.gif" border="0"/></a>';    
}
 
orderjs(
	"x:mti.listPage"
);

orderjs(function(){

rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [			
			{
				text : "增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}cpe/softReadyAdd.do", "add", rl.gui.paneDlgOpt);
				}
			},
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}cpe/softDelete.do";
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
		dataUrl : "${base}cpe/softList.do?tag=1",
		dataFields : ["id","descr","productModel","version","fileName","uploadTime","size","md5"],
		dataPrimaryKey : "id",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
	        {caption: "描述", width: "150", name: "descr"},
	        {caption: "型号", width: "150", name: "productModel"},
	        {caption: "软件版本", width: "80", name: "version"},
	        {caption: "文件名", width: "200", name: "fileName"},
	        {caption: "上传时间", width: "150", name: "uploadTime"},
	        {caption: "大小(B)", width: "80", name: "size"},
	        {caption: "MD5", width: "200", name: "md5"}
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
         <th>型号</th>
         <td>
            <input type="text" class="t" name="productModel" />
         </td>
         <th>版本</th>
         <td>
            <input type="text" class="t" name="version" />
         </td>
      </tr> 
   </table>
</form>
</body>
</html>