﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告素材</title>
<link rel="rl-page-icon" href="${imagePath}${image}" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"lib.data.Table",
	"lib.dnd.engine",
	"lib.rpc.engine",
	"lib.rpc.OneoffSdtHelperMgr",
	"lib.data.bridge.JsonListVisitor",
	"gui.grid.Grid",
	"x:mti.listPage"
);
orderjs(function(){
	function genEdit(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/materialReadyEdit.do?id=" + cellText;
		return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'imageMaterialEdit\', rl.gui.winDlgOpt);"><img src="${imagePath}edit.gif" border="0"/></a>';
	}
	
	//observe device data.
	window.materialDataObserver = rl.ext(new rl.util.EventProvider, {
		changed : false
	});
	materialDataObserver.on("change", function(){
		rlx.mti.listPageGrid.load();
	});
	
	rlx.mti.createListPage({
		pageLayouted : true,
		searchDialog : true,
		toolbar : {
			autoRenderOnReady : true,
			renderTarget : "materialListBar",
			noMenuSplit : true,
			items : [		
				{
					text : "增加",
					icon : "${imagePath}add.gif", 
					action : function(){
						rl.page.open("${base}portal/advertise/imageMaterialAdd.jsp", "imageMaterialAdd", rl.gui.winDlgOpt);
					}
				},
				{
					text : "删除",
					icon : "${imagePath}remove.gif", 
					action : function(){
						var grid = rlx.mti.listPageGrid,
							len = grid.getSelectedRows().length,
							url = "${base}portal/materialDelete.do";
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
			dataUrl : "${base}portal/materialList.do?branchId=${branchId}",
			dataFields : ["id","name", "file","description","link","linkTarget","createTime"],
			dataPrimaryKey : "id",
			autoRenderOnReady : true,
			renderTarget : "materialList",
			theme : "lightgray",
			pagingLimit : 15,
			pbOptions : {
				showPagingLimit : false
			},
			columns : [
				{ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
				{caption: "名称", name: 'name'},
				{caption: "描述", width: 200, name: 'description'},
				{caption: "链接", width: 300, name: 'link'},
				{caption: "创建时间", name: 'createTime'},
				{caption: "编辑", name: 'id', convert : genEdit}
			]
		}
	});
	
	rlx.mti.listPageGrid.on("render", function(){
		rl.$("mainPanel").addClass("colored");
	});
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#mainPanel{
	border:solid 1px #ddd;
	border-top:none;
	padding:0 5px 2px 5px;
}
#mainPanel.colored{
	background-color:#D8EAF8;
}
.toolbar_viewport{
	height:40px;
	border-bottom:none;
}
#materialListBar{
	padding:8px 0;
}
</style>
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_gridpage_viewport">
    	<div class="soof_header">
        	<h1>广告素材</h1>
            <div class="nav">
                <ul class="list">
                    <li class='active'><a href="javascript:void(0);">图片素材</a></li>
                    <li><a href="${base}portal/advertise/videoMaterialList.jsp">视频素材</a></li>
                </ul>
            </div>
        </div>
        <div id="mainPanel">
            <div class="toolbar_viewport">
            	<div id="materialListBar"></div>
            </div>
            <div id="materialList" style="height:430px;"></div>
        </div>
    </div>
</div>
<form id="queryForm" name="queryForm" method="post" class="rlx_mti_list_query_form" style="display:none;">
    <table class="fields_layout">
        <tr>
            <th>名称</th>
            <td><input type="text" class="t" name="name" /></td>
            <th>描述</th>
            <td><input type="text" class="t" name="description" /></td>
        </tr>
    </table>
</form>
</body>
</html>