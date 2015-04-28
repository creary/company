<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Portal管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">

orderjs(
	"x:mti.listPage"
);

orderjs(function(){
	//observe portal data.
	window.portalDataObserver = new rl.util.EventProvider;
	portalDataObserver.on("change", function(){
		rlx.mti.listPageGrid.load();
	});
	
	function doPreview(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/readyPreview.do?id=" + cellText;
		return '<a href="javascript:void(0);" onclick="window.open(\'' + url + '\', \'sitePreview\');"><i class="soof_icon page_magnify"></i></a>&nbsp;';    
	}
	
	function genBasicEditCol(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/instanceReadyEdit.do?id=" + cellText;
		return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'instanceReadyEdit\', rl.gui.winDlgOpt);" title="编辑 Portal 基本信息"><i class="soof_icon page_edit"></i></a>&nbsp;';
	}
	
	function genEditCol(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/" + cellText + "/global/edit.do";
		return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'portalDetailReadyEdit\', rl.gui.winDlgOpt);" title="编辑详细设置"><i class="soof_icon edit"></i></a>&nbsp;';
	}
	
	function genNameCol(cellText, colIndex, rowIndex, dataSource){
		var name = dataSource.getValue("name");
		var url = "${base}portal/" + cellText + "/global/edit.do";
		return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'portalDetailReadyEdit\', rl.gui.winDlgOpt);" title="打开详细设置">' + name + '</a>';
	} 
	
	rlx.mti.createListPage({
		searchDialog : true,
		toolbar : {
			items : [
				{
					text : "增加",
					icon : "${imagePath}add.gif", 
					action : function(){
						rl.page.open("${base}portal/instanceReadyAdd.do","portalReadyAdd", rl.gui.winDlgOpt);
					}
				},
				{
					text : "复制",
					icon : "${imagePath}edit_copy.png", 
					action : function(){
						var grid = rlx.mti.listPageGrid,
							len = grid.getSelectedRows().length,
							url = "${base}portal/instanceCopy.do";
						if(!len){
							alert("请选择要复制的记录");
							return;
						}
						if(len > 1){
							alert("一次只能复制一条记录！");
							return;
						}
						var qs = grid.body.encodeSelectedDataIdList2QS();
						url += (url.contains("?") ? "&" : "?") + qs;						
						rl.rpc.sdtGet(url, function(status, msg){
							grid.load();
						}, function(status, msg){
							alert(msg);
						});				
					}
				},					
				{
					text : "删除",
					icon : "${imagePath}remove.gif", 
					action : function(){
						var grid = rlx.mti.listPageGrid,
							len = grid.getSelectedRows().length,
							url = "${base}portal/instanceDelete.do";
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
				"search",
				"searchReset"
			]
		},	
		grid :{
			dataUrl : "${base}portal/instanceList.do?branchId=${branchId}",
			dataFields : ["id","branch","defaultTag","template","name"],
			dataPrimaryKey : "id",
			columns : [
				{ctype : "RowSelector", multiple:true, selectorName : "checkbox"},
				{caption: "名称", width: 300, name: "id", convert : genNameCol},
				{caption: "模板", name: "template"},
				{caption: "默认", name: "defaultTag"},
				<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_SOOFOUND">   
					{caption: "商家", width: "150", name: "branch"}, 
				</security:authorize> 
				{caption: "预览", name: "id", convert : doPreview, sortable:false},
				{caption: "编辑详细设置", name: "id", convert : genEditCol, sortable:false},
				{caption: "编辑基本信息", name: "id", convert : genBasicEditCol, sortable:false}
			]
		}
	});

});
</script>
<link href="${cssPath}icons.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form id="queryForm" name="queryForm" method="post" class="rlx_mti_list_query_form" style="display:none;">
   <table class="fields_layout">      
      <tr>
         <th>Portal名称</th>
         <td>
            <input type="text" class="t" name="name" />
         </td>
      </tr> 
   </table>
</form>
</body>
</html>