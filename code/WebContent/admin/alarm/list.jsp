<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<link rel="rl-page-icon" href="${imagePath}${icon}" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.listPage"
);
orderjs(function(){
	//observe device data.
	window.announcementDataObserver = new rl.util.EventProvider;
	announcementDataObserver.on("change", function(){
		rlx.mti.listPageGrid.load();
	});

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}admin/announcementReadyEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'alarmReadyEdit\', rl.gui.winDlgOpt);"><img src="${imagePath}edit.gif" title="编辑" border="0"/></a>';    
}

function genTitle(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}admin/announcementReadyEdit.do?id=" + dataSource.getValue("id");
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'alarmReadyEdit\', rl.gui.winDlgOpt);">' + cellText + '</a>';    
}

function notifyUnreadsChangeToTop(){
	var top = window.top;
	if(rl.isObject(top) &&
		rl.isObject(top.unreadsDataObserver) &&
		rl.isFunction(top.unreadsDataObserver.fireEvent)){
		top.unreadsDataObserver.fireEvent("change");
	}
}
	
rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [
			{
				text : "增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}admin/announcementReadyAdd.do","readyAdd", rl.gui.winDlgOpt);
				}
			},
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}admin/announcementDelete.do";
					if(!len){
						alert("请选择要删除的记录");
						return;
					}					
					if(len && confirm("确定要删除这 " + len + " 条记录?")){
						var qs = grid.body.encodeSelectedDataIdList2QS();
						url += (url.contains("?") ? "&" : "?") + qs;						
						rl.rpc.sdtGet(url, function(status, msg){
							grid.load();
							notifyUnreadsChangeToTop();
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
		dataUrl : "${base}admin/announcementList.do",
		dataFields : ["id","title","createTime"],
		dataPrimaryKey : "id",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
	        {caption: "标题", width: 500, name: "title", convert : genTitle},
			//{caption: "内容", width: "300", name: "title"},
	        {caption: "创建时间", name: "createTime"},
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
         <th>标题</th>
         <td>
            <input type="text" class="t" name="title" />
         </td>
         <th>正文</th>
         <td>
            <input type="text" class="t" name="content" />
         </td>
      </tr> 
   </table>
</form>
</body>
</html>