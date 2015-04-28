<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>策略管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">

orderjs(
	"x:mti.listPage"
);

orderjs(function(){
	//observe policy data.
	window.authPolicyDataObserver = new rl.util.EventProvider;
	authPolicyDataObserver.on("change", function(){
		rlx.mti.listPageGrid.load();
	});
	
	function genBasicEditCol(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/policyReadyEdit.do?id=" + cellText;
		return '<a href="javascript:void(0);" title=\"编辑策略基本信息\" onclick="rl.page.open(\'' + url + '\', \'policyReadyEdit\', rl.gui.winDlgOpt);"><img src="${imagePath}edit_info.gif" border="0" align="absmiddle" /> </a>';    
	}
	
	function genEditCol(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/policyReadyEditAuth.do?id=" + cellText;
		return '<a href="javascript:void(0);" title=\"编辑策略详细\" onclick="rl.page.open(\'' + url + '\', \'policyReadyEditAuth\', rl.gui.winDlgOpt);"><img src="${imagePath}edit.gif" border="0" align="absmiddle" /> </a>';
	}
	
	function genNameCol(cellText, colIndex, rowIndex, dataSource){
		var name = dataSource.getValue("name");
		var url = "${base}portal/policyReadyEditAuth.do?id=" + cellText;
		return '<a href="javascript:void(0);" title=\"打开策略详细\" onclick="rl.page.open(\'' + url + '\', \'policyReadyEditAuth\', rl.gui.winDlgOpt);">' + name + '</a>';    
	} 
	rlx.mti.createListPage({
		searchDialog : true,
		toolbar : {
			items : [						
				{
					text : "增加认证策略",
					icon : "${imagePath}add.gif", 
					action : function(){
						rl.page.open("${base}portal/policyReadyEdit.do","policyReadyAdd", rl.gui.winDlgOpt);
					}
				},
				{
					text : "删除",
					icon : "${imagePath}remove.gif", 
					action : function(){
						var grid = rlx.mti.listPageGrid,
							len = grid.getSelectedRows().length,
							url = "${base}portal/policyDelete.do";
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
					text : "微信认证配置",
					icon : "${imagePath}weixin.png", 
					action : function(){
						rl.page.open('${base}admin/readyEditWechatBase.do?branchId=${branchId}', 'wechatAuth', rl.gui.winDlgOpt);
					}
				}
			]
		},	
		grid :{
			dataUrl : "${base}portal/policyList.do?branchId=${branchId}",
			dataFields : ["id","branch","name","defaultTag"],
			dataPrimaryKey : "id",
			columns : [
				{ctype : "RowSelector", multiple:true, selectorName : "checkbox"},
				<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_SOOFOUND">   
					{caption: "商家", width: "150", name: "branch"}, 
				</security:authorize> 
				{caption: "名称", width: 300, name: "id",convert: genNameCol},
				{caption: "默认", name: "defaultTag"},
				{caption: "编辑详细设置", name: "id", convert : genEditCol, sortable:false},
				{caption: "编辑基本信息", name: "id", convert : genBasicEditCol, sortable:false}
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
         <th>名称</th>
         <td>
            <input type="text" class="t" name="name" />
         </td>
      </tr> 
   </table>
</form>
</body>
</html>