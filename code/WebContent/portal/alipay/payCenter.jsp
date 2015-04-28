<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>充值中心</title>
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"lib.rpc.engine",
	"lib.rpc.OneoffSdtHelperMgr",
	"lib.data.bridge.JsonListVisitor",
	"gui.grid.Grid"
);
orderjs(function(){
/**
 * order status values:
 * 1 : processing
 * 2 : success
 * 3 : failure
 * 4 : canceled
 */
var status2Text = {
	"1" : "处理中",
	"2" : "充值成功",
	"3" : "充值失败",
	"4" : "已取消"
};
function getStatus(status, colIndex, rowIndex, dataSource){
	return status2Text[status];
}

var orders = new rl.data.Table({
    primaryKey : "number",
    uniqueCheck : false,
	pagingLimit : 10,
	httpRequest : new rl.rpc.XHRequest({
		url : "${base}portal/tradeList.do?branchId=${branchId}",
		responseType : "json",
		async : true
	}),
    dataVisitor : new rl.data.JsonListVisitor({
		fields : ["orderNo","summary", "fee","payWayText","state","logTime"],
		rspPagesTotal : "page.total",
		rspStatus : "message",
		rspRecords : "data"
	})
});

var ordersGrid = new rl.Grid({
    dataSource : orders,
    autoRenderOnReady : true,
	renderTarget : "ordersGridCtn",
	theme : "lightgray",
    enbaleMultiSelect: false,
    clickToSelect: true,
	deferRenderBody : true,
	showPagingBar : true,
	pbOptions : {
		showPagingLimit : false
	},
    columns : [
		{caption: "创建时间", name: 'logTime'},
		{caption: "订单号", name: 'orderNo'},
		{caption: "消费项目", width: "200", name: 'summary'},
		{caption: "金额(元)", name: 'fee'},
		{caption: "支付方式", name: 'payWayText'},
		{caption: "状态", name: 'state', convert : getStatus}
    ]
});

});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>充值中心</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                    	<div class="mc_wrapper">
                            <div class="section first">
                                <div class="mc_wrapper">
                                    <div class="num_stat num_stat_cols2">
                                        <div class="cell">
                                            <table class="canvas" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="num">${dto.smsRemain}</td>
                                                    <td class="name">
                                                    	剩余短信
                                                        <a class="btn" href="javascript:void(0);" onclick="rl.page.open('${base}portal/alipay/smsCharge.jsp', 'smsCharge', rl.gui.winDlgOpt)">立即充值</a>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="cell">
                                            <table class="canvas" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="num">${dto.smsUsed}</td>
                                                    <td class="name">
                                                    	已发短信
                                                        <a class="btn" href="javascript:void(0);" onclick="rl.page.open('${base}portal/smsSummaryListJsp.do?branchId=${branchId}', 'smsSend', rl.gui.winDlgOpt)">查看发送记录</a>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="clearer"></div>
                                    </div>
                                    
                                    <div class="widthholder"></div>
                                </div>
                            </div>
                            <div class="section section_unite">
                                <h3>充值订单列表</h3>
                                <div class="p10" id="ordersGridCtn" style="height:300px;"></div>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>