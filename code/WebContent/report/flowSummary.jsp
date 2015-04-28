<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户终端数统计</title>
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
//add shim module as base dep:
orderjs.addBase("open.jqplot.order-shim");
orderjs(
    "open.jquery.jquery",
	"open.jqplot.plugins.jqplot-categoryAxisRenderer",
	"open.jqplot.plugins.jqplot-canvasTextRenderer",
	"open.jqplot.plugins.jqplot-canvasAxisLabelRenderer",
	"open.jqplot.plugins.jqplot-canvasAxisTickRenderer",
	"open.jqplot.plugins.jqplot-dateAxisRenderer",
	"open.jqplot.plugins.jqplot-pieRenderer",
    "app.deco.engine",
	"app.deco.RadioButton",
	"app.deco.DataBinding",
	"app.dataBox.KpiViewPlot",
	"app.dataBox.jqplotLines",
	"app.dataBox.KpiViewTable",
	"cloudac:reportUtil"
);
orderjs(function(){
    //rl.console.showOnReady();
	var jQuery = orderjs("open.jquery.jquery"),
        decoUtil = orderjs("app.deco.engine"),
        reportUtil = orderjs("cloudac:reportUtil");

	decoUtil.makeJqDeco(rl.dataBox.KpiViewTable, "Table", "renderable");
	window.flowSummaryViewOptions = {
		updateError : function(msg){
			this.jq.find(".num").html("N/A");
		},
		updateNoData : function(msg){
			this.jq.find(".num").html(msg);
		}
	};
    rl.onReady(function(){
		decoUtil.decoRoot();
		reportUtil.initTimeTypeSelector();
		
		reportUtil.createLoader({
			url : "${base}detect/getFlowSummaryTotal.do?branchId=${branchId}",
			view : "#flowSummaryStat"
		},{
			url : "${base}detect/getDetectFlowTrend.do?branchId=${branchId}",
			view : "#mainStatWrapper"
		}
		,{
			url : "${base}detect/getDetectFlowTrend.do?table=1&branchId=${branchId}",
			view : "#mainStatTableWrapper"
		});
		
		reportUtil.search();
		jQuery("#btn_search").on("click", function(){reportUtil.search();});
    });
});
</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}report.css" rel="stylesheet" type="text/css" />
<link href="${jsPath}rl/src/open/jQuery-idTabs/jquery_idtabs.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="soof_page soof_report">
	<div class="soof_page_viewport">
    	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
            <div class="soof_header">
                <h1 style="display:inline-block;">用户数统计</h1>
                <p class="p10 desc">对用户终端数进行统计分析。其中探测数为探测到的终端设备数；连接数为所有连接至商家网络的终端设备数；认证数为连接商家网络并通过认证的终端设备数。</p>
            </div>
            <div class="soof_body">
                <div class="section first">
                	<div class="search_bar">
                        <table class="fields_layout" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="label_ctn">
                                    指定设备Mac:
                                </td>
                                <td class="ctrls_ctn">
                                    <input class="field s" name="mac" />
                                    <button class="btn white" id="btn_search">查询</button>
                                </td>
                            </tr>
                            <tr>
                                <td class="label_ctn">
                                    选择时间:
                                </td>
                                <td class="ctrls_ctn">
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
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="section section_unite fluid sp_md">
                	<div class="row">
                        <div class="col span4">
                            <div class="cell">
                                <div class="s_header">
                                    <span class="title">用户终端数变化趋势</span>
                                </div>
                                <div id="mainStatWrapper" rl-deco="KpiViewTipWrap" class="chart_wrapper">
                                    <div rl-deco="Notifier" rl-deco-embedded="notifier"
                                     rl-deco-options="{holder : '.tip_holder'}"
                                     class="loading_tip desc"><div class="tip_holder">数据加载中...</div></div>
                                    <div rl-deco="Plot" rl-deco-embedded="comp" 
                                     rl-deco-options=" {
                                        shapeType : 'lines',
                                        plotOptions : {
                                            axes: {
                                                xaxis: {
                                                    //renderer: jQuery.jqplot.DateAxisRenderer,
                                                    renderer: jQuery.jqplot.CategoryAxisRenderer,
                                                    labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer,
                                                    tickRenderer: jQuery.jqplot.CanvasAxisTickRenderer/*,
                                                    tickOptions:{
                                                        formatString:'%#m-%#d'
                                                    } */
                                                }
                                            },
                                            seriesDefaults: {
                                                rendererOptions: {
                                                    smooth: true
                                                }
                                            },
                                            highlighter : {
                                                tooltipContentEditor : rl.dataBox.KpiViewPlot.fixTooltipWithDataTick
                                            },
                                            legend:{
                                                renderer : jQuery.jqplot.PieLegendRenderer,
                                                labels : ['探测数','连接数', '认证数'],
                                                rendererOptions: {
                                                    numberRows: 1,
                                                    numberColumns: 3
                                                },
                                                location:'s'
                                            }
                                        }
                                    }" style="height:100%;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col span2">
                            <div class="cell">
                                <div class="s_header">
                                    <span class="title">&nbsp;</span>
                                </div>
                                <div class="chart_wrapper">
                                    <div class="nested_circles" id="flowSummaryStat"
                                     rl-deco="dataBinding"
                                     rl-deco-options="flowSummaryViewOptions">
                                        <div class="circle" style="width:250px; height:250px; background-color:#09C;">
                                            <div class="text">探测数</div>
                                            <div class="num" data-field="detectTotal">-</div>
                                            <div class="circle_wrap">
                                                <div class="circle" style="width:175px; height:175px; background-color:#FC0;">
                                                    <div class="text">连接数</div>
                                                    <div class="num" data-field="enterTotal">-</div>
                                                    <div class="circle_wrap">
                                                        <div class="circle" style="width:100px; height:100px; background-color:#6C0;">
                                                            <div class="text">认证数</div>
                                                            <div class="num" data-field="authTotal">-</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>                        
                    </div>
                    <div class="clearer"></div>
                    <div id="mainStatTableWrapper" rl-deco="KpiViewTipWrap"
                     rl-deco-options="{hideCompOnTip : true}"
                     class="chart_wrapper_auto">
                        <div rl-deco="Notifier" rl-deco-embedded="notifier"
                         class="loading_tip desc">数据加载中...</div>
                        <div rl-deco="Table" rl-deco-embedded="comp" 
                         rl-deco-options="{
                            dynamicColumns : false,
                            columns : [
                                {caption: '#', width:30, name: 'index'},
                                {caption: '时间', name: 'time'},
                                {caption: '探测数', name: 'detectTotal'},
                                {caption: '连接数', name: 'enterTotal'},
                                {caption: '连接率', name: 'enterRatio'},
                                {caption: '认证数', name: 'authTotal'},
                                {caption: '认证率', name: 'authRatio'}
                            ]
                         }"></div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>