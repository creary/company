<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>充值消费记录</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">

orderjs(
	"x:mti.listPage"
);

var status2Text = {
	"1" : "处理中",
	"2" : "充值成功",
	"3" : "充值失败",
};
function getStatus(status, index, row, ds){
	return status2Text[status];
}

orderjs(function(){

rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [
			{
				text : "确认收款",
				icon : "${imagePath}apply.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}portal/confirmPay.do";
					if(!len){
						alert("请选择要确认的记录");
						return;
					}					
					if(len && confirm("是否确认收款?")){
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
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}portal/tradeDelete.do";
					if(!len){
						alert("请选择要删除的记录");
						return;
					}					
					if(len && confirm("确认删除此条记录吗?")){
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
		dataUrl : "${base}portal/tradeList.do?branchId=${branchId}",
		dataFields : ["branch","orderNo","summary","fee","payWayText","state","logTime"],
		dataPrimaryKey : "orderNo",
		enbaleMultiSelect : false,
		columns : [
            {ctype : "RowSelector", multiple:false, selectorName : "radio"},
	        {caption: "商家", width: "200", name: "branch"},
	        {caption: "订单号", width: "100", name: "orderNo"},
	        {caption: "创建时间", width: "150", name: "logTime"},
	        {caption: "消费项目", width: "150", name: "summary"},
	        {caption: "金额(元)", width: "100", name: "fee"},	   
	        {caption: "支付方式", width: "100", name: "payWayText"},
            {caption: "状态", name: 'state', convert : getStatus},          
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
         <th>订单号</th>
         <td>
            <input type="text" class="t" name="orderNo" />
         </td>
      </tr> 
      <tr>
         <th>消费项目</th>
         <td>
            <input type="text" class="t" name="summary" />
         </td>
      </tr>      
   </table>
</form>
</body>
</html>