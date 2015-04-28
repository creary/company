<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>首页</title>
<link rel="rl-page-icon" href="${imagePath}house.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(function(){rl.page.callTop("focusOnMenuItem", "startpage");});
orderjs(
    "open.jquery.jquery"
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery");
	window.jqAjaxErrMsg = {
		timeout : "请求超时",
		parsererror : "数据解析错误",
		error : "服务器内部错误"
	};

	window.PlotLoadTip = function(options){
		this.init.apply(this, arguments);
	};
	jQuery.extend(PlotLoadTip.prototype, {
		ctn : "",
		plot : null,
		init : function(options, plot){
			if(rl.isNonNullStr(options)) options = {ctn:options, plot: plot};
			
			jQuery.extend(this, options);
			this.dw = jQuery(this.ctn);
		},
		showTip : function(tip){
			this.plot.hide();
			if(rl.isString(tip)) this.dw.html(tip);
			this.dw.show();
		},
		showPlot : function(){
			this.dw.hide();
			this.plot.show();
		}
	});
	
	window.ajaxPlotData = function(url, plot, loadTip, options){
		jQuery.ajax(jQuery.extend({
			url : url,
			cache : false,
			dataType : "json",
			success : function(rsp, msg){
				if(rsp && rsp.status == 1){
					if(rl.isArray(rsp.data) && rsp.data.length > 0){
						try{
							rl.debug(this + ' ajaxPlotData() url = ' + url + '');
							loadTip.showPlot();
							if(plot.plotter){
								plot.plotter.replot({
									data : rsp.data
								});
							}
							else{
								plot.loadData({data : rsp.data});
							}
						}catch(err){
							loadTip.showTip("当前无数据！")
							rl.raiseError(err);
						}
					}
					else{
						loadTip.showTip("当前无数据！")
					}
				}
				else{
					loadTip.showTip("当前无数据！");
				}
			},
			error : function(req, textStatus){
				loadTip.showTip("<div class='warn'>加载数据失败: " + jqAjaxErrMsg[textStatus] + "！</div>");
			}
		}, options));
	};
});
//add shim module as base dep:
orderjs.addBase("open.jqplot.order-shim");
orderjs(
    "app.dataBox.KpiViewPlot"
);
</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}home.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.cols2_layout{
}
.cols2_layout .col_side{
	float:right;
	width:280px;
}
.cols2_layout .col_side .chart_wrapper{
	margin-top:6px;
}
.cols2_layout .col_main{
	margin-right:305px;
	padding-right:25px;
	border-right:solid 1px #eee;
}
.cols2_layout .col_main .desc,
.cols2_layout .col_main .warn{
	font-size:14px;
}
/* override jqplot defaults */
.soof_page_home table.jqplot-table-legend,
.soof_page_home table.jqplot-cursor-legend{
	border:0;
}
.soof_page_home .jqplot-table-legend-label{
	padding:2px 5px 2px 3px;
}
.jquery_idtabs.hat ul.tabbar {
	height:25px;
	border-bottom:solid 1px #e7e7e7;
}
.jquery_idtabs.hat ul.tabbar li {
	float:left;
	list-style:none;
	margin:0;
	padding:0;
}
.jquery_idtabs.hat ul.tabbar a {
	display: block;
	margin-right:6px;
	padding:6px 18px;
	height:13px;
	color: #000;
	text-decoration: none;
}
.jquery_idtabs.hat ul.tabbar a:hover {
	background-color: #f2f2f2;
}
.jquery_idtabs.hat ul.tabbar a.selected {
	background-color:#f2f2f2;
	border:solid 1px #e7e7e7;
	border-bottom:none;
	padding:5px 17px 6px 17px;
}
.chart_wrapper{
	position:relative;
	top:0;
}
.loading_tip{
	position:absolute;
	top:0;
	width:100%;
	text-align:center;
	padding-top:10%;
}
.lined_list{
	list-style:none;
}
.lined_list li{
	padding:5px 0;
	text-decoration:none;
}
.lined_list a{
	text-decoration:none;
}
.lined_list a:hover{
	color:#F90;
}
.lined_list .line{
	margin-top:3px;
	height:2px;
	background-color:#0085CC;
}

</style>
</head>
<body>
<div class="soof_page_home">
    <div class="main_wrapper">
        <div class="mc_wrapper">
            <div class="num_cols">
            	<table class="layout" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="cell">
                        	<a class="cell_link" title="点击查看用户列表" href="${base}portal/surfingAccountListJsp.do">
                                <table class="canvas icon_canvas" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td rowspan="2">
                                            <i class="soof_icon ctg_48 user"></i>
                                        </td>
                                        <td class="num">${userOnline} / ${userTotal}</td>
                                    </tr>
                                    <tr>
                                        <td class="name">用户数 (在线/总数)</td>
                                    </tr>
                                </table>
                            </a>
                        </td>
                        <td class="cell">
                        	<a class="cell_link" title="点击查看设备列表" href="${base}cpe/hostListJsp.do">
                                <table class="canvas icon_canvas" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td rowspan="2">
                                            <i class="soof_icon ctg_48 wifi_device"></i>
                                        </td>
                                        <td class="num">${deviceOnline} / ${deviceTotal}</td>
                                    </tr>
                                    <tr>
                                        <td class="name">设备数 (在线/总数)</td>
                                    </tr>
                                </table>
                            </a>
                        </td>
                        <td class="cell">
                        	<a class="cell_link" title="点击查看商家列表" href="${base}admin/branchListJsp.do?rich=1">
                                <table class="canvas icon_canvas" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td rowspan="2">
                                            <i class="soof_icon ctg_48 merchant"></i>
                                        </td>
                                        <td class="num">${branchTotal}</td>
                                    </tr>
                                    <tr>
                                        <td class="name">商家总数</td>
                                    </tr>
                                </table>
                            </a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mc_viewport">
                <div class="setion setion_first">
                	<div class="cols2_layout">
                    	<div class="col_side">
                            <div class="s_header">
                                <span class="title">用户特征分析 |</span>
                                <select id="timeSelect_userFeature">
                                    <option value="today">今天</option>
                                    <option value="yesterday">昨天</option>
                                    <option value="7day">近7天</option>
                                </select>
                            </div>
                            <div id="userFeatureStatSelector" class="jquery_idtabs hat p10">
                                <ul class="tabbar">
                                    <li><a class="selected" data-featuretype="brand" href="javascript:void(0);">终端品牌</a></li>
                                    <li><a data-featuretype="duration" href="javascript:void(0);">单日使用时长</a></li>
                                </ul>
                                <div id="userFeatureStatCtn" class="chart_wrapper">
                                    <div class="loading_tip desc">数据加载中...</div>
                                    <div id="userFeatureStatHolder" style="height:100%;"></div>
                                </div>
                            </div>
							<script type="text/javascript">
                                orderjs(
                                    "app.dataBox.jqplotPies",
									"open.jqplot.plugins.jqplot-highlighter"
                                );
                                orderjs(function(){
                                    var jQuery = orderjs("open.jquery.jquery");
									
									//init tab selectors behaviours
									jQuery("#userFeatureStatSelector .tabbar a")
										.on("click", function(){
											var tab = jQuery(this);
											if(!tab.hasClass("selected")){
												jQuery("a", tab.closest(".tabbar")[0]).removeClass("selected");
												tab.addClass("selected");
												updatePlot_userFeature();
											}
										});
									
                                    var userFeaturePlot = new rl.dataBox.KpiViewPlot({
                                        renderTarget : "userFeatureStatHolder",
                                        shapeType : "pie",
										plotOptions : {
											legend:{
												show:true,
												//placement: 'outside',
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
                                    });
									var loadTip = new PlotLoadTip("#userFeatureStatCtn .loading_tip", userFeaturePlot);
									
                                    var kpiDataUrls = {
                                        "brand" : {
                                            "today" : "${base}report/getBrandPie.do?tag=today&branchId=${branchId}",
                                            "yesterday" : "${base}report/getBrandPie.do?tag=yesterday&branchId=${branchId}",
                                            "7day" : "${base}report/getBrandPie.do?tag=7day&branchId=${branchId}"
                                        },
                                        "duration" : {
                                            "today" : "${base}home/getStayLongPie.do?tag=today&branchId=${branchId}",
                                            "yesterday" : "${base}home/getStayLongPie.do?tag=yesterday&branchId=${branchId}",
                                            "7day" : "${base}home/getStayLongPie.do?tag=7day&branchId=${branchId}"
                                        }
                                    };
									
                                    function updatePlot_userFeature(){
                                        var kpiType = jQuery("#userFeatureStatSelector .tabbar a[class*='selected']").attr("data-featuretype"),
                                            timeType = jQuery("#timeSelect_userFeature").val();
                                        if(!rl.isNonNullStr(kpiType) || !rl.isNonNullStr(timeType)) return false;
                                        kpiType = kpiType.toLowerCase();
                                        timeType = timeType.toLowerCase();
                                        rl.debug(this + ' updatePlot_userFeature(): kpiType = ' + kpiType +' timeType = ' + timeType + '');
                                        
                                        var dataUrls = kpiDataUrls[kpiType];
                                        if(!dataUrls) return false;
                                        var dataUrl = dataUrls[timeType];
                                        rl.debug(this + ' updatePlot_userFeature(): dataUrl = ' + dataUrl + '');
                                        if(!dataUrl) return false;
                                        
										ajaxPlotData(dataUrl, userFeaturePlot, loadTip);
                                    }
									jQuery("#timeSelect_userFeature")
										.on("change", updatePlot_userFeature)
										.change();
                                });
                            </script>
                        </div>
                    	<div class="col_main">
                            <div class="s_header">
                                <span class="title">在线用户数趋势 |</span>
                                <select id="timeSelect_userCount">
                                    <option value="today">今天</option>
                                    <option value="yesterday">昨天</option>
                                    <option value="7day">近7天</option>
                                </select>
                            </div>
                            <div id="userCountStatCtn" class="chart_wrapper">
                                <div class="loading_tip desc">数据加载中...</div>
                                <div id="userCountStatHolder" style="height:100%;"></div>
                            </div>
                            <script type="text/javascript">
                                orderjs(
                                    "app.dataBox.jqplotLines",
                                    "open.jqplot.plugins.jqplot-categoryAxisRenderer",
                                    "open.jqplot.plugins.jqplot-canvasTextRenderer",
                                    "open.jqplot.plugins.jqplot-canvasAxisLabelRenderer",
                                    "open.jqplot.plugins.jqplot-canvasAxisTickRenderer",
                                    "open.jqplot.plugins.jqplot-pieRenderer"
                                );
                                orderjs(function(){
                                    var jQuery = orderjs("open.jquery.jquery");
                                    var userCountPlot = new rl.dataBox.KpiViewPlot({
                                        renderTarget : "userCountStatHolder",
                                        shapeType : "lines",
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
													smooth: true
												}
											},
                                            highlighter : {
                                                tooltipContentEditor : rl.dataBox.KpiViewPlot.fixTooltipWithDataTick
                                            },
											legend:{
												renderer : jQuery.jqplot.PieLegendRenderer,
												labels : ["在线用户数","活动用户数","帐号认证","微信认证", "手机认证", "免认证", "第三方"],
												rendererOptions: {
													numberRows: 1,
													numberColumns: 7
												},
												location:'s'
											}
                                        }
                                    });
                                    
									var loadTip = new PlotLoadTip("#userCountStatCtn .loading_tip", userCountPlot);
									
                                    var dataUrls = {
                                        "today" : "${base}report/getEndUserCountTrend.do?tag=today&branchId=${branchId}",
                                        "yesterday" : "${base}report/getEndUserCountTrend.do?tag=yesterday&branchId=${branchId}",
                                        "7day" : "${base}report/getEndUserCountTrend.do?tag=7day&branchId=${branchId}"
                                    };
									
                                    function updatePlot_userCount(){
                                        var timeType = jQuery("#timeSelect_userCount").val();
                                        timeType = timeType.toLowerCase();
                                        var dataUrl = dataUrls[timeType];
                                        rl.debug(this + ' updatePlot_userCount(): dataUrl = ' + dataUrl + '');
                                        if(!dataUrl) return false;
                                        
										ajaxPlotData(dataUrl, userCountPlot, loadTip);
                                    }
									jQuery("#timeSelect_userCount")
										.on("change", updatePlot_userCount)
										.change();
                                });
                            </script>
                        </div>
                    </div>
                </div>
                <div class="setion">
                	<div class="cols2_layout">
                        <div class="col_side">
                            <div class="s_header">
                                <span class="title">设备负荷排行 |</span>
                                <select id="timeSelect_deviceLoad">
                                    <option value="today">今天</option>
                                    <option value="yesterday">昨天</option>
                                    <option value="7day">近7天</option>
                                </select>
                            </div>
                            <div id="deviceLoadStatSelector" class="jquery_idtabs hat p10">
                                <ul class="tabbar">
                                    <li><a class="selected" data-featuretype="netflow" href="javascript:void(0);">流量 (前10)</a></li>
                                    <li><a data-featuretype="accessusers" href="javascript:void(0);">接入用户数 (前10)</a></li>
                                </ul>
                                <div id="deviceLoadStatCtn" class="chart_wrapper">
                                    <div class="loading_tip desc">数据加载中...</div>
                                    <div id="deviceLoadStatHolder" style="height:100%;"></div>
                                </div>
                            </div>
							<script type="text/javascript">
                                orderjs(
                                    "app.dataBox.jqplotPies",
									"open.jqplot.plugins.jqplot-highlighter"
                                );
                                orderjs(function(){
                                    var jQuery = orderjs("open.jquery.jquery");
									
									//init tab selectors behaviours
									jQuery("#deviceLoadStatSelector .tabbar a")
										.on("click", function(){
											var tab = jQuery(this);
											if(!tab.hasClass("selected")){
												jQuery("a", tab.closest(".tabbar")[0]).removeClass("selected");
												tab.addClass("selected");
												updatePlot_deviceLoad();
											}
										});
									
                                    var deviceLoadPlot = {
										detailUrl : "${base}cpe/readySetting.do?id=",
										renderTarget : "deviceLoadStatHolder",
										unitLabel : "",
										getRenderHtml : function(){
											var html, 
												max = 0,
												data = this.data;
											
											rl.each(data, function(item){
												max = Math.max(parseInt(item.value), max);
											});
											
											var detailUrl = this.detailUrl;
											html = rl.map(data, function(item, index){
												var width = max == 0 ? "0" : parseInt((Math.max(parseInt(item.value), 0) / max) * 100) + "%",
													url = detailUrl + item.id;
												return  '<li>' +
															'<span style="float:right;">' + item.value + '</span>' +
															'<a title="配置" href="javascript:void(0);" onclick="' + 
															'rl.page.open(\'' + url + '\', \'cpe.readySetting\', rl.gui.winDlgOpt);' +
															'">' + item.name + '</a>' +
															'<div class="line" style="width:' + width + ';"></div>' +
														'</li>';
											});
											
											return html.join("");
										},
										hide : function(){
											rl.$(this.renderTarget).hide();
										},
										loadData : function(viewData){
											if(rl.isObject(viewData) && rl.isObject(viewData.data)){
												this.data = viewData.data;
												this.unitLabel = viewData.unitLabel;
												this.render();
											}
											else{
												var msg = this + '-> loadData(): Invalid data = ' + viewData + '';
												throw new Error(msg);
											}
										},
										render : function(){
											var html = [
												this.unitLabel ? 
													'<div style="text-align:right;">' + this.unitLabel + '</div>' :
													'',
												'<ul id="deviceLoadStatCtn" class="lined_list">',
												this.getRenderHtml(),
												'</ul>'
											];
												
											rl.$(this.renderTarget).setInnerHTML(html.join(""));
										},
										show : function(){
											rl.$(this.renderTarget).show();
										}
									};
									var loadTip = new PlotLoadTip("#deviceLoadStatCtn .loading_tip", deviceLoadPlot);
                                    var kpiDataUrls = {
                                        "netflow" : {
                                            "today" : "${base}report/getAPLoadTopn.do?tag=today&flag=traffic&branchId=${branchId}",
                                            "yesterday" : "${base}report/getAPLoadTopn.do?tag=yesterday&flag=traffic&branchId=${branchId}",
                                            "7day" : "${base}report/getAPLoadTopn.do?tag=7day&flag=traffic&branchId=${branchId}"
                                        },
                                        "accessusers" : {
                                            "today" : "${base}report/getAPLoadTopn.do?tag=today&flag=connect&branchId=${branchId}",
                                            "yesterday" : "${base}report/getAPLoadTopn.do?tag=yesterday&flag=connect&branchId=${branchId}",
                                            "7day" : "${base}report/getAPLoadTopn.do?tag=7day&flag=connect&branchId=${branchId}"
                                        }
                                    };

                                    function updatePlot_deviceLoad(){
                                        var kpiType = jQuery("#deviceLoadStatSelector .tabbar a[class*='selected']").attr("data-featuretype"),
                                            timeType = jQuery("#timeSelect_deviceLoad").val();
                                        if(!rl.isNonNullStr(kpiType) || !rl.isNonNullStr(timeType)) return false;
                                        kpiType = kpiType.toLowerCase();
                                        timeType = timeType.toLowerCase();
                                        
                                        var dataUrls = kpiDataUrls[kpiType];
                                        if(!dataUrls){
                                            rl.debug(this + 'updatePlot_deviceLoad(): Invalid kpiType = ' + kpiType + '', "err");
                                            return false;
                                        }
                                        var dataUrl = dataUrls[timeType];
                                        if(!dataUrl){
                                            rl.debug(this + 'updatePlot_deviceLoad(): Invalid timeType = ' + timeType + '', "err");
                                            return false;
                                        }
                                        rl.debug(this + ' updatePlot_deviceLoad(): dataUrl = ' + dataUrl + '');
										
										var unitLabel = kpiType == "netflow" ? "(单位：MB)" : "(单位：人)";
                                        
										ajaxPlotData(dataUrl, deviceLoadPlot, loadTip, {
                                            success : function(rsp, msg){
												if(rsp && rsp.status == 1){
													if(rl.isArray(rsp.data) && rsp.data.length > 0){
														try{
															loadTip.showPlot();
															deviceLoadPlot.loadData({unitLabel : unitLabel, data : rsp.data});
														}catch(err){
															loadTip.showTip("<div class='warn'>绘图失败: 服务器返回数据错误！</div>")
															rl.raiseError(err);
														}
													}
													else{
														rl.debug(this + ' loadTip 当前无数据= ' + 123456 + '');
														loadTip.showTip("当前无数据！")
													}
												}
												else{
													loadTip.showTip("<div class='warn'>加载数据失败: 服务器返回数据错误！</div>");
												}
                                            }
										});
                                    }
									jQuery("#timeSelect_deviceLoad")
										.on("change", updatePlot_deviceLoad)
										.change();
                                });
                            </script>
                        </div>
                    	<div class="col_main">
                            <div class="s_header">
                                <span class="title">设备流量趋势 |</span>
                                <select id="timeSelect_net">
                                    <option value="today">今天</option>
                                    <option value="yesterday">昨天</option>
                                    <option value="7day">近7天</option>
                                </select>
                            </div>
                            <div id="trafficStatCtn" class="chart_wrapper">
                                <div class="loading_tip desc">数据加载中...</div>
                                <div id="trafficStatHolder" style="height:100%;"></div>
                            </div>
                            <script type="text/javascript">
                                orderjs(
                                    "app.dataBox.jqplotLines",
                                    "open.jqplot.plugins.jqplot-categoryAxisRenderer",
                                    "open.jqplot.plugins.jqplot-canvasTextRenderer",
                                    "open.jqplot.plugins.jqplot-canvasAxisLabelRenderer",
                                    "open.jqplot.plugins.jqplot-canvasAxisTickRenderer"
                                );
                                orderjs(function(){
                                    var jQuery = orderjs("open.jquery.jquery");
                                    var trafficPlot = new rl.dataBox.KpiViewPlot({
                                        renderTarget : "trafficStatHolder",
                                        shapeType : "lines",
                                        plotOptions : {
											title: {
												text : "(单位：MB)",
												textAlign : "right"
											},
                                            axes: {
                                                xaxis: {
                                                    renderer: jQuery.jqplot.CategoryAxisRenderer,
                                                    labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer,
                                                    tickRenderer: jQuery.jqplot.CanvasAxisTickRenderer
                                                }
                                            },
											series: [
												{
													rendererOptions: {
														smooth: true
													}
												}
											],
                                            highlighter : {
                                                tooltipContentEditor : rl.dataBox.KpiViewPlot.fixTooltipWithDataTick
                                            },
                                            legend : false
                                        }
                                    }); 
									
									var loadTip = new PlotLoadTip("#trafficStatCtn .loading_tip", trafficPlot);
									
                                    var dataUrls = {
                                        "today" : "${base}report/getAPTrafficTrend.do?tag=today&branchId=${branchId}",
                                        "yesterday" : "${base}report/getAPTrafficTrend.do?tag=yesterday&branchId=${branchId}",
                                        "7day" : "${base}report/getAPTrafficTrend.do?tag=7day&branchId=${branchId}"
                                    };                            
									
                                    function updatePlot_net(){
                                        var timeType = jQuery("#timeSelect_net").val();
                                        timeType = timeType.toLowerCase();
                                        var dataUrl = dataUrls[timeType];
                                        rl.debug(this + ' updatePlot_net(): dataUrl = ' + dataUrl + '');
                                        if(!dataUrl) return false;
                                        
										ajaxPlotData(dataUrl, trafficPlot, loadTip);
                                    }														
									jQuery("#timeSelect_net")
										.on("change", updatePlot_net)
										.change();
                                });
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>        
</div>
</body>
</html>