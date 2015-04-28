<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>AP接入用户数统计</title>
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
	"open.jqplot.plugins.jqplot-pieRenderer",
    "app.deco.engine",
	"app.deco.RadioButton",
	"app.dataBox.KpiViewPlot",
	"app.dataBox.jqplotBars",
	"cloudac:reportUtil"
);
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
        decoUtil = orderjs("app.deco.engine"),
        reportUtil = orderjs("cloudac:reportUtil");

    rl.onReady(function(){
		decoUtil.decoRoot();
		reportUtil.initTimeTypeSelector();
		
		reportUtil.createLoader({
			url : "${base}report/getEndUserBar.do?flag=ap&branchId=${branchId}",
			view : "#mainStatWrapper"
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
<div class="soof_page soof_report">
	<div class="soof_page_viewport">
    	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
            <div class="soof_header">
                <h1 style="display:inline-block;">AP接入用户数统计</h1>
                <p class="p10 desc">对AP接入用户（MAC地址）数进行统计。</p>
            </div>
            <div class="soof_body">
                <div class="section first">
                    <div class="search_bar">
                        <div rl-deco="RadioButton" id="timeTypeSeletor" class="jquery_idtabs radio_btn ctrl">
                            <input type="hidden" id="field_tag" name="tag" value="today" autoComplete="off" />
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
                    <div class="s_header">
                        <span class="title">AP接入用户数Top10</span>
                    </div>
                    <div id="mainStatWrapper" rl-deco="KpiViewTipWrap" class="chart_wrapper">
                        <div rl-deco="Notifier" rl-deco-embedded="notifier"
                         rl-deco-options="{holder : '.tip_holder'}"
                         class="loading_tip desc"><div class="tip_holder">数据加载中...</div></div>
                        <div rl-deco="Plot" rl-deco-embedded="comp" 
                         rl-deco-options=" {
                            shapeType : 'bar',
                            plotOptions : {
                                axes: {
                                    xaxis: {
                                        renderer: jQuery.jqplot.CategoryAxisRenderer,
                                        labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer,
                                        tickRenderer: jQuery.jqplot.CanvasAxisTickRenderer
                                    }
                                },
                                seriesDefaults: {
                                    rendererOptions: {
                                        varyBarColor: true,
                                        smooth: true
                                    }
                                },
                                highlighter : {
                                    tooltipContentEditor : rl.dataBox.KpiViewPlot.fixTooltipWithDataTick
                                }
                            }
                        }" style="height:100%;"></div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>