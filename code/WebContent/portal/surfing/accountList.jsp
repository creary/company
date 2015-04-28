<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<link rel="rl-page-icon" href="${imagePath}audit2.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(function(){rl.page.callTop("focusOnMenuItem", "surfing_accountList");});
orderjs(
	"gui.menu.PopupMenu",
	"x:mti.listPage"
);

orderjs(function(){

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}portal/surfingAccountEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'surfingAccountEdit\', rl.gui.paneDlgOpt);"><img src="${imagePath}edit.gif" title="编辑" border="0"/></a>';    
}

function genNameCol(cellText, colIndex, rowIndex, dataSource){
	var url = "${base}portal/sessionListJsp.do?branchId=" + dataSource.getValue("branchId") + "&username=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'ReadyEdit\', rl.gui.winDlgOpt);">' + cellText + '</a>';    
}

function genStatusImageCol(cellText, colIndex, rowIndex, dataSource){
	return cellText + '&nbsp;';    
}

function toTrace(cellText, colIndex, rowIndex, dataSource){
	var url = "${base}detect/traceFlowListJsp.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'ReadyEdit\', rl.gui.winDlgOpt);"><img src="${imagePath}view_detail.gif" border="0"/></a>';    
}

function connLimit(url, options){
	options = options || {};
	var grid = rlx.mti.listPageGrid,
		rows = grid.getSelectedRows(),
		len = rows.length;
	
	if(!len){
		alert("请选择要操作的记录");
		return;
	}
	
	if(confirm("确定要将这 " + len + " 条记录加入" + (options.name || "") + "吗?")){
		var macList = rl.map(rows, function(row){
			return row.dataSource.getValue("mac");
		});
		rl.debug(this + ' url = ' + url + '');
		url += (url.contains("?") ? "&" : "?") + "mac=" + macList.join(",");
		
		rl.rpc.sdtGet(url, function(status, msg){
			grid.load();
		}, function(status, msg){
			alert(msg);
		});
	}
}

var connLimitMenu = new rl.gui.menu.PopupMenu({
	items : [
		{
			text : "列入黑名单",
			action : rl.curry(connLimit, "${base}portal/blackWhiteBatchAdd.do?bw=2", {name : "黑名单"})
		},
		{
			text : "列入白名单",
			action : rl.curry(connLimit, "${base}portal/blackWhiteBatchAdd.do?bw=1", {name : "白名单"})
		}
	]
});


rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [			
			{
				text : "增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}portal/surfingAccountReadyAdd.do","surfingAccountReadyAdd", rl.gui.paneDlgOpt);
				}
			},
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}portal/surfingAccountDelete.do";
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
			"searchReset",			
			{ctype : "MenuSplit"},
            {
				text : "强制下线",
				icon : "${imagePath}network-offline.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}portal/forceOffline.do";
					if(!len){
						alert("请选择要强制下线的用户");
						return;
					}					
					if(len && confirm("确定要强制这些用户下线吗?")){
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
				text : "上网控制",
				icon : "${imagePath}forb_user.gif", 
				popup : connLimitMenu
			},
            {ctype : "MenuSplit"},
			{
				text : "导出列表",
				icon : "${imagePath}export_excel.gif", 
				action : function(){
					location.href = "${base}portal/surfingAccountExport.do";
				}
			}
		]
	},	
	grid :{
		dataUrl : "${base}portal/surfingAccountList.do?branchId=${branchId}",
		dataFields : ["id","branch","branchId","usernameText","times","statusImage","flagText","mac","username"],		
		dataPrimaryKey : "id",
		pbOptions : {
			recordsTotalTextRender : function(total){
				return "共 " + total + " 位用户";
			}
		},
		columns : [
			{ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
	        {caption: "用户名", width: "200", name: "usernameText", convert:genNameCol }, 
	        {caption: "在线状态", name: "statusImage", convert:genStatusImageCol},
	        {caption: "类型", name: "flagText"},
	        {caption: "登录次数", name: "times"},
	        {caption: "MAC", width:120, name: "mac"},
	        {caption: "商家", width: "200", name: "branch"},	
	        {caption: "编辑", name: "id", convert : doEdit}
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
		  <th>用户名</th>
		  <td>
			 <input type="text" class="t" name="username" />
		  </td>
		  <th>MAC</th>
		  <td>
			 <input type="text" class="t" name="mac" />
		  </td>
	  </tr>
      <tr>
		  <th>商家</th>
		  <td>
			 <input type="text" class="t" name="branch" />
		  </td>
		  <th>类型</th>
		  <td>
			 <select name="flag" style="width:150px"> 
			     <option value=""></option>   
			     <option value="db">会员帐号</option>
			     <option value="mobile">手机</option>  
			     <option value="wechat">微信</option>
			     <option value="free">免认证</option>
			     <option value="thirdParty">第三方</option>
			 </select>
		  </td>
	  </tr>	  
   </table>
</form>
</body>
</html>