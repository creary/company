<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>配置管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}cpe/softReadyEdit.do?tag=2&id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'branchReadyEdit\', {modal : true, width : 800, height: 500});"><img src="${imagePath}edit.gif" border="0"/></a>';    
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
					rl.page.open("${base}cpe/softReadyAdd.do?tag=2", "add", rl.gui.winDlgOpt);
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
		dataUrl : "${base}cpe/softList.do?tag=2&branchId=${branchId}&version=${version}",
		dataFields : ["id","descr","productModel","versionText","fileName","uploadTime","branch"],
		dataPrimaryKey : "id",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
            {caption: "商家", width: "300", name: "branch"},
            {caption: "名称", width: "200", name: "descr"},	        
	        {caption: "型号", width: "200", name: "productModel"},
	        {caption: "创建时间", width: "150", name: "uploadTime"},
	        {caption: "类型", width: "80", name: "versionText"},
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
         <th>型号</th>
         <td>
            <select name="productModel" style="width:200px">
                <option value="">不限</option>   
                <c:forEach items="${productModel}" var="pm">  
                    <option value="${pm}">${pm}</option>  
                </c:forEach> 
            </select>	
         </td>
         <th>名称</th>
         <td>
            <input type="text" class="t" name="descr" />
         </td>
      </tr>       
   </table>
</form>
</body>
</html>