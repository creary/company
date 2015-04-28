<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>[${host.name}]用户访问日志</title>
<link rel="rl-page-icon" href="${imagePath}view_detail.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.listPage"
);

orderjs(function(){

function doView(cellText, colIndex, rowIndex, dataSource){
	var url = "${base}detect/traceFlowListJsp.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'ReadyEdit\', rl.gui.winDlgOpt);"><img src="${imagePath}view_detail.gif" border="0"/></a>';    
}

rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [						
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}portal/surfingSessionDelete.do";
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
            {
				text : "强制下线",
				icon : "${imagePath}network-offline.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}portal/surfingSessionOffline.do";
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
			{ctype : "MenuSplit"},
			{
				text : "导出列表",
				icon : "${imagePath}export_excel.gif", 
				action : function(){
					location.href = "${base}portal/surfingSessionExport.do?branchId=${branchId}";
				}
			},			
			rlx.mti.searchResetBtnOptions
		]
	},	
	grid :{
		dataUrl : "${base}portal/sessionList.do?branchId=${branchId}&apid=${apid}",
		dataFields : ["sessionId","username","ipAddress","mac","startTime","stopTime","rxText","txText","upTimeText","snrText"],
		dataPrimaryKey : "sessionId",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
            {caption: "用户名", width: "150", name: "username"},	
            {caption: "IP地址", width: "100", name: "ipAddress"},	
	        {caption: "MAC", width: "120", name: "mac"},
			{caption: "开始时间", width: "130", name: "startTime"},		       
	        {caption: "在线时间", width: "100", name: "upTimeText"},
	        {caption: "上行流量", width: "50", name: "txText"},
            {caption: "下行流量", width: "50", name: "rxText"},
            {caption: "场强", width: "50", name: "snrText"},	
            <c:if test="${host.trace == '120.24.77.206'}">
		       {caption: "溯源", width: "30", name: "sessionId", convert : doView}
            </c:if>
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
			  <input type="text" class="t" name="username2" />
		  </td>
		  <th>MAC</th>
		  <td>
			 <input type="text" class="t" name="mac" />
		  </td>		
      </tr>
   </table>
</form>
</body>
</html>