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
orderjs(function(){rl.page.callTop("focusOnMenuItem", "admin_branchList");});
orderjs(
	"x:mti.listPage"
);

orderjs(function(){

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}admin/branchReadyEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'branchReadyEdit\', rl.gui.paneDlgOpt);"><i class="soof_icon edit"></i></a>&nbsp;';    
}

function deviceGroup(cellText, colIndex, rowIndex, dataSource){
    var text, icon,
		url = "${base}cpe/readySelectDeviceGroup.do?id=" + cellText,		
		linkedGroup = dataSource.getValue("linkedGroupName");
	
	if(linkedGroup){
		text = linkedGroup;
		icon = "link_edit";
	}
	else{
		text = '未关联';
		icon = "link_add";
	}
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'readySelectDeviceGroup\', rl.gui.paneDlgOpt);" class="btn ilink tight" title="点击修改"><i class="soof_icon ' + icon + '"></i><span class="text">' + text + '</span></a>';    
}
 
window.branchDeviceGroupDataObserver = new rl.util.EventProvider;
branchDeviceGroupDataObserver.on("change", function(){
	rlx.mti.listPageGrid.load();
});

rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [				
			{
				text : "增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}admin/branchReadyAdd.do","readyAdd", rl.gui.paneDlgOpt);
				}
			},
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}admin/branchDelete.do";
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
		dataUrl : "${base}admin/branchList.do?id=${branchId}",
		dataFields : ["id","name","parent","gradeName", "linkedGroupName"],
		dataPrimaryKey : "id",
		enbaleMultiSelect : false,
		pbOptions : {
			recordsTotalTextRender : function(total){
				return "共 " + total + " 位商家";
			}
		},
		columns : [
			{ctype : "RowSelector", multiple:false, selectorName : "radio"},	
	        {caption: "名称", width: "200", name: "name"},
            {caption: "父商家", width: "350", name: "parent"}, 
            {caption: "级别", name: "gradeName"},
            <c:if test="${rich == 1}">
               {caption: "关联设备分组", name: "id", convert : deviceGroup},
            </c:if>        
            {caption: "编辑", name: "id", convert : doEdit} 
		]
	}
});

});
</script>
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}icons.css" rel="stylesheet" type="text/css" />
</head>
<body class="soof_page">
<form id="queryForm" name="queryForm" method="post" class="rlx_mti_list_query_form" style="display:none;">
   <table class="fields_layout">
      <tr>
		  <th>名称</th>
		  <td>
			 <input type="text" class="t" name="name" />
		  </td>
		  <th>父商家</th>
		  <td>
			 <input type="text" class="t" name="parent" />
		  </td>
	  </tr>
   </table>
</form>
</body>
</html>