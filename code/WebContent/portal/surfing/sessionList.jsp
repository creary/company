<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户访问日志</title>
<link rel="rl-page-icon" href="${imagePath}view_detail.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
    "gui.menu.PopupMenu",
	"x:mti.listPage"
);

orderjs(function(){

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
					location.href = "${base}portal/surfingSessionExport.do?branchId=${branchId}&history=${history}";
				}
			},			
			rlx.mti.searchResetBtnOptions
		]
	},	
	grid :{
		dataUrl : "${base}portal/sessionList.do?branchId=${branchId}&bid=${bid}&username=${username}&history=${history}",
		dataFields : ["sessionId","branch","username","apName","apmac","ipAddress","mac","startTime","stopTimeText","rxText","txText","upTimeText","snrText"],
		dataPrimaryKey : "sessionId",
		dataOptions : {localSort : false},
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
            {caption: "商家", width: "180", name: "branch"},	
            {caption: "用户名", width: "100", name: "username"},	
            {caption: "IP地址", width: "90", name: "ipAddress"},	
	        {caption: "MAC", width: "110", name: "mac"},
	        {caption: "设备", width: "120", name: "apName"},
	        {caption: "设备序列号", width: "110", name: "apmac"},
			{caption: "开始时间", width: "130", name: "startTime"},		       
	        {caption: "结束时间", width: "130", name: "stopTimeText"},   	       
	        {caption: "在线时间", width: "80", name: "upTimeText"},
	        {caption: "上行流量", width: "50", name: "txText"},
            {caption: "下行流量", width: "50", name: "rxText"},
            <c:if test="${history eq null}">
               {caption: "场强", width: "50", name: "snrText"},	
            </c:if>
            {caption: "溯源", width: "30", name: "sessionId", convert : toTrace}
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
		  <th>IP地址</th>
		  <td>
			 <input type="text" class="t" name="ip" />
		  </td>
		  <th>MAC</th>
		  <td>
			 <input type="text" class="t" name="mac" />
		  </td>		
      </tr>
      <tr>
	      <th>用户名</th>
		  <td>
			  <input type="text" class="t" name="username2" />
		  </td>
	      <th>设备</th>
		  <td>
			  <input type="text" class="t" name="apName" />
		  </td>
	  </tr>
   </table>
</form>
</body>
</html>