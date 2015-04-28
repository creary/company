<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>短信充值</title>
<link rel="rl-page-icon" href="${imagePath}sms.gif" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.ajaxSubmit",
	"x:mti.popupFormPage",
	"open.jquery.jquery"
);

orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery");	
	orderjs.ready(function(){
		function updateFinalPrice(){
			var quantity = jQuery("input[name='quantity']:checked").val();
			jQuery("#finalPrice").text(quantity * 0.1);
		}		
		jQuery("input[name='quantity']").on("click", updateFinalPrice);
		updateFinalPrice();
	});
	
	rlx.mti.createPopupFormPage({
		content : "#mainContent",
		actionBar : [
			{text : "确定", id : "ok", action : doAction},
			{text : "取消", id : "cancel", action : function(){rl.page.close();}}
		]
	});
	
	function doAction() {
	    var mainForm = document.mainForm,
			payway = jQuery("input[name='payway']").val();
		
		switch (payway) {
			case "2" : {
				var url_zhifubao = "${base}portal/doPay.do?quantity=" + jQuery("input[name='quantity']:checked").val();
				window.open(url_zhifubao, "");
				
				var bar = rlx.mti.actionBar;
				var okBtn = bar.getItemById("ok"),
					cancelBtn = bar.getItemById("cancel"),
					onBtn = bar.getItemById("cancel");
				
				okBtn.hide();
				cancelBtn.setText("已完成支付");
				cancelBtn.action = function(){
					var opener = rl.page.getOpener();
					if(opener && opener.location){
						opener.location.reload();
					}
					rl.page.close();
				};
				
				break;
			}
			default : {//1
				mainForm.action = "${base}portal/doPay.do";
				rlx.mti.ajaxSubmit("mainForm", "closeAndRefreshOpener");
			}
		}		
	}
});
</script>
<style type="text/css">
.prices_list{
	
}
.prices_list a.item{
	margin-right:12px;
	padding:6px 12px;
	border:solid 1px #ddd;
	text-decoration:none;
	color:#000;
}
.prices_list a:hover.item{
	background-color:#f8f8f8;
}
.prices_list a.active{
	border:solid 2px #09C;
}
</style>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
    <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
    	<div class="group">
            <table border="0" class="grid_fields">
                <tr>
                    <th class="label">数量:</th>
                    <td class="field_ctn">
                    	<input type="radio" name="quantity" id="q100" value="100" /><label for="q100">100</label>&nbsp;&nbsp;
                    	<input type="radio" name="quantity" id="q500" value="500" /><label for="q500">500</label>&nbsp;&nbsp;
                    	<input type="radio" name="quantity" id="q1000" value="1000" checked="checked" /><label for="q1000">1000</label>&nbsp;&nbsp;
                    	<input type="radio" name="quantity" id="q5000" value="5000" /><label for="q5000">5000</label>
                    </td>
                </tr>
                <tr>
                    <th class="label">价格(元):</th>
                    <td class="field_ctn"><span id="finalPrice" style="color:red;"></span></td>
                </tr>
                <tr>
                    <th class="label">付款方式:</th>
                    <td class="field_ctn">
                    	
                        <input type="radio" name="payway" value="2" id="zhifubaoPaymentField" checked="checked" />
                        <label for="zhifubaoPaymentField">支付宝</label><br />
                        <div style="padding:0 0 1em 1.5em; color:gray;">
                        点击[确定]按钮后, 系统将打开支付宝的支付页面，请在该页面付款。付款后再重新打开充值中心，您可以看到充值的短信自动到账。如支付过程发生错误，请关闭本对话框，重新充值。
                        </div>
                        <input type="radio" name="payway" value="3" disabled="disabled" id="wangyinPaymentField" />
                        <label for="wangyinPaymentField">网银(暂不支持)</label><br /><br />
                        <input type="radio" name="payway" value="1" id="offlinePaymentField" />
                        <label for="offlinePaymentField">线下支付</label><br />
                        <div style="padding:0 0 1em 1.5em; color:gray;">
                        点击[确定]按钮后, 系统将生成充值订单,您需要在 10 天之内完成线下支付并通知我们确认收款,否则系统将自动取消该笔订单。充值订单待管理员确认收款后，充值的短信将自动到账。
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
</body>
</html>