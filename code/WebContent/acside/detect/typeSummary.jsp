<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新老顾客</title>
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
//add shim module as base dep:
orderjs.addBase("open.jqplot.order-shim");
orderjs(
    "open.jquery.jquery",
    "app.deco.engine",
	"app.deco.RadioButton",
	"app.deco.UnderlinePlot",
	"cloudac:reportUtil"
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery"),
        decoUtil = orderjs("app.deco.engine"),
        reportUtil = orderjs("cloudac:reportUtil");
	
	window.typeSummaryViewOptions = {
		hideTip : function(){
			this.jq
				.find(".inline_loading_tip")
				.hide();
		},
		showTip : function(tip){
			this.jq
				.find(".inline_loading_tip")
				.html(tip);
		},
		updateError : function(msg){
			this.showTip(rl.format("<span class='warn'>{0}</span>", [msg]));
		},
		update : function(data){
			if(rl.isObject(data)){
				var newCustomers, oldCustomers,
					ncRaw = data[0],
					ocRaw = data[1],
					fields = ["userCountRatio", "userCount", "authCount", "avgStay"];
	
				newCustomers = rl.map(fields, function(field){return ncRaw[field];});
				oldCustomers = rl.map(fields, function(field){return ocRaw[field];});
				
				this.jq
					.find("td[class*='val']")
					.filter(".l")
					.each(function(i){
						jQuery(this).html(newCustomers[i]);
					})
					.end()
					.filter(".r")
					.each(function(i){
						jQuery(this).html(oldCustomers[i]);
					});
				this.hideTip();
			}
			else return "";
		}
	};
	
    rl.onReady(function(){
		decoUtil.decoRoot();
		reportUtil.initTimeTypeSelector();
		
		var loaders = reportUtil.createLoader({
			url : "${base}detect/getFeatureSummary.do?branchId=${branchId}",
			view : "#typeSummaryStat"
		},{
			url : "${base}detect/getHotShopByNewCustomer.do?branchId=${branchId}",
			view : "#nc_stopStatWrapper"
		},{
			url : "${base}detect/getHotShopByOldCustomer.do?branchId=${branchId}",
			view : "#oc_stopStatWrapper"
		});
		
		reportUtil.search();
    });
});
</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}report.css" rel="stylesheet" type="text/css" />
<link href="${jsPath}rl/src/open/jQuery-idTabs/jquery_idtabs.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="soof_page soof_report bright_gray">
	<div class="soof_page_viewport">
    	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
            <div class="soof_header">
                <h1 style="display:inline-block;">新老顾客</h1>
                <p class="p10 desc">分析新老顾客的比例及其偏好，以作为营销活动或忠诚管理的决策依据。</p>
            </div>
            <div class="soof_body">
                <div class="section first">
                    <div class="search_bar">
                        <div rl-deco="RadioButton" id="timeTypeSeletor" class="jquery_idtabs radio_btn ctrl">
                            <input type="hidden" name="tag" value="today" autoComplete="off" />
                            <ul class="tabbar">
                                <li><a href="#today">今天</a></li>
                                <li><a href="#yesterday">昨天</a></li>
                                <li><a href="#7day">近7天</a></li>
                                <li><a href="#30day">近30天</a></li>
                                <li><a href="#custom">自定义</a></li>
                            </ul>
                        </div>
                        <div id="timeType_custom_ctrl" class="ctrl" style="padding-top:5px; margin-left:1em; display:none;">
                            <span id="startTimeCtn"></span>
                            <span id="endTimeCtn"></span>
                        </div>
                        <div class="clearer"></div>
                    </div>
                </div>
                <div class="section section_unite">
                    <table id="typeSummaryStat" rl-deco="DataBox"
                     rl-deco-options="typeSummaryViewOptions"
                     class="soof_contrast_table bright_gray" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="avatar l">
                                <img src="${base}resources/image/default/mix/user_blue.jpg" alt="new" />
                                <div class="name">新顾客</div>
                            </td>
                            <td class="fields">
                                <div class="inline_loading_tip">数据加载中...</div>
                                <table border="0" cellspacing="0" cellpadding="0">
                                    <tr class="large">
                                        <td class="val l" data-field="">-</td>
                                        <td>&nbsp;</td>
                                        <td class="val r">-</td>
                                    </tr>
                                    <tr>
                                        <td class="val l">-</td>
                                        <td class="label_wrap">
                                            <div class="label">人数</div>
                                        </td>
                                        <td class="val r">-</td>
                                    </tr>
                                    <tr>
                                        <td class="val l">-</td>
                                        <td class="label_wrap">
                                            <div class="label">认证数</div>
                                        </td>
                                        <td class="val r">-</td>
                                    </tr>
                                    <tr>
                                        <td class="val l">-</td>
                                        <td class="label_wrap">
                                            <div class="label">平均停留时长</div>
                                        </td>
                                        <td class="val r">-</td>
                                    </tr>
                                </table>
                            </td>
                            <td class="avatar r gray">
                                <img src="${base}resources/image/default/mix/user_gray.jpg" alt="old" />
                                <div class="name">老顾客</div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="section fluid sp_md">
                    <div class="row">
                        <div class="col span3 bright">
                            <div class="cell">
                                <div class="s_header">
                                    <span class="title">新顾客热衷铺位（停留人数前 5）</span>
                                </div>
                                <div id="nc_stopStatWrapper" rl-deco="CompTipWrap" class="chart_wrapper_auto">
                                    <div rl-deco="Notifier" rl-deco-embedded="notifier"
                                     class="loading_tip desc">数据加载中...</div>
                                    <div rl-deco="UnderlinePlot" rl-deco-embedded="comp"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col span3 gray">
                            <div class="cell">
                                <div class="s_header">
                                    <span class="title">老顾客热衷铺位（停留人数前 5）</span>
                                </div>
                                <div id="oc_stopStatWrapper" rl-deco="CompTipWrap" class="chart_wrapper_auto">
                                    <div rl-deco="Notifier" rl-deco-embedded="notifier"
                                     class="loading_tip desc">数据加载中...</div>
                                    <div rl-deco="UnderlinePlot" rl-deco-embedded="comp"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>