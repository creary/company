<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备列表</title>
<link rel="rl-page-icon" href="${imagePath}wireless.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(function(){rl.page.callTop("focusOnMenuItem", "rpt_offlineHosts");});
orderjs.config("shim", {
	"open.zTree.jquery-ztree-core" : {
		deps : ["open.jquery.jquery"]
	}
});

orderjs(
	"open.jquery.jquery",
    "open.zTree.jquery-ztree-core",
    "css>open.zTree.css.zTreeStyle",
	"lib.util.StateObserver",
	"lib.util.OneshotEventProvider",
	"lib.rpc.JqAjax",
	"gui.box.Div",
    "gui.box.Box",
	"x:mti.zTreeListPage"
);
orderjs(function(){
	//rl.console.showOnReady();
	var track = this.track;
	var jQuery = orderjs("open.jquery.jquery"),
		JqAjax = orderjs("lib.rpc.JqAjax");
	
	JqAjax.request({
		url : "${base}cpe/getTreeForOffline.do",
		success : function(nodes){
			if(!(nodes && nodes.length > 0)){
				return("服务器返回分组数据错误！");
			}
			rlx.mti.listPageTreeNodes = nodes;
			rl.debug('Group tree nodes load, length = ' + nodes.length + '');
			rlx.mti.processEventMgr.fireEvent("treeNodesReady");
		},
		error : function(msg, status, jqXHR){
			alert("页面加载错误：" + msg);
		}
	});
	
	rlx.mti.createTreeListPage({
		searchDialog : true,
		sideBox : {
			content : '<div id="groupTreeCtn" style="height:100%; overflow:hidden;">' + 
					  '<ul id="groupTree" class="ztree ztree_groupleaf"></ul></div>'
		},
		tree : {
			treeId : "#groupTree",
			direct : rlx.mti.listPageTreeDirect,
			loadGridByNode : function(id){
				jQuery("#field_groupId").val(id);
				var url = rl.format("${base}cpe/richHostList.do?offline=1&branchId=${branchId}", arguments);
				rlx.mti.resetListPageGridSearch(url);
			}
		},
		toolbar : {
			items : [									
				"search",
				"searchReset"
			]
		},	
		grid :{
			dataFields : ["id","branch","name","serialNumber","lastTime"],
			dataPrimaryKey : "id",
			dataOptions : {localSort : false},			
			columns : [	
				{caption: "商家", width:200, name: "branch"},
				{caption: "名称", width: 150, name: "name"},
				{caption: "序列号(MAC)", width: 150, name: "serialNumber"},
				{caption: "离线时间", width: "150", name: "lastTime"}
			]
		}
	});
	
});
</script>
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}entity.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${jsPath}rl/src/open/jQuery-idTabs/jquery_idtabs.css"/>
<style type="text/css">
.ztree_groupleaf li span.button.ico_docu{
	background-position:-112px 0px !important;
}
.ztree_layout{
	border-collapse:collapse;
}
.ztree_layout td{
	/*border:solid 1px red;*/
	vertical-align:top;
}
.ztree_bar{
	height:30px;
	border-bottom:solid 1px #ddd;
	background-color:#f8f8f8; 
}
.ztree_bar .iwrap{
	padding:7px;
}
.ztree_bar a.btn{
	float:right;
	text-decoration:none;
	color:#000;
}
.ztree_bar a.btn:hover{
	color:#F90;
}
.ztree_bar a.btn img{
	border:none;
	margin-right:4px;
}
.tree_ctn{
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar{
	height:22px;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar a {
	padding:3px 12px;
	border-color:#A6C2E6;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar a:hover{
	padding:3px 12px;
	border-color:#A6C2E6;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar a:hover {
    background-color: #ECF5FC;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar a.selected {
	background-color:#ECF5FC;
	border-color:#A6C2E6;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar li:first-child a,
.rl_menu .jquery_idtabs.radio_btn ul.tabbar li:last-child a{
	border-color:#A6C2E6;
}

.rl_grid .cell .ctrl{
	cursor:pointer;
}
</style>
</head>
<body>
<form id="queryForm" name="queryForm" method="post" class="rlx_mti_list_query_form" style="display:none;">
	<input type="hidden" name="gid" id="field_groupId" />
   <table class="fields_layout">
      <tr>
         <th>序列号</th>
         <td>
            <input type="text" class="t" name="serialNumber" />
         </td>
         <th>离线时间</th>
         <td>
            <select name="offlineTime" style="width:200px">  
                <option value="">不限</option>  
                <option value="1">1小时</option>
                <option value="2">2小时</option>
                <option value="3">3小时</option>
                <option value="4">4小时</option>
                <option value="5">5小时</option>
                <option value="6">6小时</option>
                <option value="7">7小时</option>
                <option value="8">8小时</option>                 
	        </select>	
         </td>
      </tr>        
   </table>
</form>
</body>
</html>