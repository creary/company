<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>AP统计</title>
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
//add shim module as base dep:
orderjs.addBase("open.jqplot.order-shim");
orderjs(
    "open.jquery.jquery",
    "app.deco.engine",
	"app.deco.RadioButton",
	"app.dataBox.KpiViewPlot",
	"app.dataBox.jqplotPies",
	"cloudac:reportUtil"
);
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
        decoUtil = orderjs("app.deco.engine"),
        reportUtil = orderjs("cloudac:reportUtil");

    rl.onReady(function(){
		decoUtil.decoRoot();
		reportUtil.createLoader({
			url : "${base}report/getAPPie.do?flag=model&branchId=${branchId}",
			view : "#detectBrandsStatWrapper"
		},{
			url : "${base}report/getAPPie.do?flag=version&branchId=${branchId}",
			view : "#enterBrandsStatWrapper"
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
                <h1 style="display:inline-block;">AP统计</h1>
                <p class="p10 desc">对AP的型号和固件版本进行统计。</p>
            </div>
            <div class="soof_body">
                <div class="section section_unite fluid sp_md">
                	<div class="row">
                        <div class="col span3">
                            <div class="cell">
                                <div class="s_header">
                                    <span class="title">按型号统计</span>
                                </div>
                                <div id="detectBrandsStatWrapper" rl-deco="KpiViewTipWrap" class="chart_wrapper">
                                    <div rl-deco="Notifier" rl-deco-embedded="notifier"
                                     rl-deco-options="{holder : '.tip_holder'}"
                                     class="loading_tip desc"><div class="tip_holder">数据加载中...</div></div>
                                    <div rl-deco="Plot" rl-deco-embedded="comp" 
                                     rl-deco-options=" {
                                        shapeType : 'pie',
                                        plotOptions : {
                                            legend:{
                                                show:true,
                                                rendererOptions: {
                                                    numberColumns: 3
                                                },
                                                location:'s'
                                            },
                                            highlighter : {
                                                show:true,
                                                showTooltip : true,
                                                tooltipLocation: 's',
                                                useAxesFormatters: false,
                                                formatString:'%s, %P'
                                            }
                                        }
                                    }" style="height:100%;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col span3">
                            <div class="cell">
                                <div class="s_header">
                                    <span class="title">按固件版本统计</span>
                                </div>
                                <div id="enterBrandsStatWrapper" rl-deco="KpiViewTipWrap" class="chart_wrapper">
                                    <div rl-deco="Notifier" rl-deco-embedded="notifier"
                                     rl-deco-options="{holder : '.tip_holder'}"
                                     class="loading_tip desc"><div class="tip_holder">数据加载中...</div></div>
                                    <div rl-deco="Plot" rl-deco-embedded="comp" 
                                     rl-deco-options=" {
                                        shapeType : 'pie',
                                        plotOptions : {
                                            legend:{
                                                show:true,
                                                rendererOptions: {
                                                    numberColumns: 3
                                                },
                                                location:'s'
                                            },
                                            highlighter : {
                                                show:true,
                                                showTooltip : true,
                                                tooltipLocation: 's',
                                                useAxesFormatters: false,
                                                formatString:'%s, %P'
                                            }
                                        }
                                    }" style="height:100%;"></div>
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