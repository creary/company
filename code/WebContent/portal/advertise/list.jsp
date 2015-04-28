<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商讯列表</title>
<link rel="rl-page-icon" href="${imagePath}doc.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.listPage"
);

orderjs(function(){
	//observe device data.
	window.articleDataObserver = new rl.util.EventProvider;
	articleDataObserver.on("change", function(){
		articleDataObserver.changed = true;
	});
	window.openArticleDlg = function(url, name){
		var dlg = rl.page.open(url, name, rl.gui.winDlgOpt);
		dlg.on("close", function(){
			if(articleDataObserver.changed){
				//reset articleDataObserver
				articleDataObserver.changed = false;
				rlx.mti.listPageGrid.load();
			}
		});
	};
	
	function genNameCol(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/advertiseReadyEdit.do?id=" + dataSource.getValue("id");
		return '<a href="javascript:void(0);" onclick="openArticleDlg(\'' + url + '\', \'advertiseReadyEdit\');">' + cellText + '</a>';    
	}
	
	function doEdit(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/advertiseReadyEdit.do?id=" + cellText;
		return '<a href="javascript:void(0);" onclick="openArticleDlg(\'' + url + '\', \'advertiseReadyEdit\');"><i class="soof_icon edit"></i></a>&nbsp;';
	}
	
	rlx.mti.createListPage({
		searchDialog : true,
		toolbar : {
			items : [
				{
					text : "增加",
					icon : "${imagePath}add.gif", 
					action : function(){
						openArticleDlg("${base}portal/advertiseReadyAdd.do","advertiseReadyAdd");
					}
				},
				{
					text : "删除",
					icon : "${imagePath}remove.gif", 
					action : function(){
						var grid = rlx.mti.listPageGrid,
							len = grid.getSelectedRows().length,
							url = "${base}portal/advertiseDelete.do";
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
			dataUrl : "${base}portal/advertiseList.do?branchId=${branchId}",
			dataFields : ["id","branch","title","category","createTime"],
			dataPrimaryKey : "id",
			pbOptions : {
				recordsTotalTextRender : function(total){
					return "共 " + total + " 篇商讯";
				}
			},
			columns : [
				{ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
				{caption: "标题", width: "300", name: "title", convert : genNameCol},	        
				{caption: "分类", width: "80", name: "category"},
				{caption: "创建时间", width: "150", name: "createTime"},
				{caption: "商家", width: "200", name: "branch"},
				{caption: "编辑", width: "50", name: "id", convert : doEdit, sortable:false}
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
         <th>标题</th>
         <td>
            <input type="text" class="t" name="title" />
         </td>
      </tr> 
   </table>
</form>
</body>
</html>