/**
 * @fileOverview KpiViewPlot
 *  Note: This moudle requires following add base deps calling:
 *  orderjs.addBase("open.jqplot.order-shim");
 * @change
    #1 by prcjack @2014/04/11 Creates file.
	#2 by prcjack @2014/06/03 
		Change orginal "plotter" to "plotFactory",
		use new "plotter" to reference to plot.
	#3 by prcjack @2014/07/01
		Add axesDefaults for KpiViewPlot.plotOptions.
 */
orderjs.define("app.dataBox.KpiViewPlot",["gui.engine","app.dataBox.engine","open.jquery.jquery","open.jqplot.jquery-jqplot"],function(){var t=this.track,i=orderjs("open.jquery.jquery"),o=rl.dataBox.KpiViewPlot=rl.createClass({parent:rl.gui.ControlBase,construct:function(){rl.isPrototyping(arguments[0])||(o.parent.apply(this,arguments),this.initialize())},members:{autoRenderOnReady:!0,data:null,height:"100%",holdHeight:300,plotFactory:"jqplot",plotOptions:null,plotter:null,replotOptions:null,shapeType:"basic",width:"100%",_htmlTpl:new rl.HtmlTpl('<div id="{id}" style="width:{width}; height:{height};"></div>'),initialize:function(){rl.isNonNullStr(this.id)||(this.id=this.autoId()),this.dw=new rl.dom.DomWrap,this.initPlotFactory(),this.autoRenderOnReady&&rl.onReady(this.render,this)},adjustHighlighter:function(){var t=this.plotOptions||{};/pie/i.test(this.shapeType)&&t.highlighter&&t.highlighter.showTooltip&&i("#"+this.id).on("jqplotDataHighlight",function(t){var o=i(this).offset(),r=Math.round(t.pageX-o.left);i(".jqplot-highlighter-tooltip",this).css({left:r})})},initPlotFactory:function(){rl.isNonNullStr(this.plotFactory)?this.plotFactory=o.getPlotFactory(this.plotFactory):rl.isFunction(this.plotFactory)||(this.plotFactory={plot:this.plotFactory}),this.plotFactory&&rl.isFunction(this.plotFactory.plot)||rl.raiseError("This plotFactory is invalid!",t(this+"initPlotFactory")),this._plot=this.plotFactory.plot;var r=this.plotFactory.shapes;r&&(this._shapePlotOptions=i.extend(!0,{},r[this.shapeType.toLowerCase()]))},loadData:function(i){rl.isObject(i)?(this.data=rl.isArray(i)?i:i.data,this._rendered?this.plot():(this.render(),this.plot())):rl.raiseError("Invalid viewData!",t(this,"loadData",{viewData:i}))},plot:function(o,r){this._rendered||rl.raiseError("Not rendered!",t(this,"plot")),o=o||this.data,o||rl.raiseError("No data!",t(this,"plot"));try{if(this.plotter)this.plotter.replot(rl.fill({data:o},this.replotOptions));else{var e=i.extend(!0,{},this._shapePlotOptions);i.extend(!0,e,r||this.plotOptions),this.plotter=this._plot(this.id,o,e),this.adjustHighlighter()}this.fireEvent("plot")}catch(s){rl.raiseError("Plot error: "+s,t(this,"plot",{data:o}))}return this},render:function(t){if(this._rendered)return this;t=this.getRenderTarget(t);var o=this.height;!/%/.test(o)||t.offsetHeight>0||(o=this.holdHeight);var r=this._htmlTpl.renderTo(t,{id:this.id,width:rl.dom.addUnit(this.width),height:rl.dom.addUnit(o)},this.renderPosition);return this.dw.setTarget(r),this.dq=i(r),this._rendered=!0,this.fireEvent("render"),this},toString:function(){return"[object rl.dataBox.KpiViewPlot]"}}}),r={};rl.ext(o,{plotOptions:{basic:{animate:!i.jqplot.use_excanvas,animateReplot:!i.jqplot.use_excanvas,seriesDefaults:{pointLabels:{formatString:"%d",show:!0},rendererOptions:{shadowAlpha:"0.05",shadowDepth:2,animation:{speed:1500}}},axesDefaults:{padMin:0,tickOptions:{formatString:"%d"}},axes:{yaxis:{min:0}},grid:{shadow:!1,drawBorder:!1}},legend:{legend:{show:!0,location:"e",placement:"outsideGrid"}},highlighter:{highlighter:{show:!0,sizeAdjust:7.5}}},addPlotFactory:function(i,o){rl.isString(i)&&o&&(rl.isFunction(o)?o={plot:o}:rl.isFunction(o.plot)||rl.raiseError("Invalid plotFactory!",t(this,"addPlotFactory"),{plotFactory:o}),r[i.toLowerCase()]=o)},dump:function(){var t={};return rl.each(arguments,function(o){i.extend(!0,t,o)}),t},fixTooltipWithDataTick:function(t,i,o,r){return r.data[i][o].join(",")},fixTooltipWithOptionTick:function(t,i,o,r){var e=t.split(",");return e[0]=r.options.axes.xaxis.ticks[o],e.join(",")},getPlotFactory:function(t){return rl.isString(t)?r[t.toLowerCase()]:void 0}});var e=o.plotOptions,s=o.dump;o.addPlotFactory("jqplot",{plot:function(t,o,r){return i.jqplot(t,o,r)},shapes:{basic:s(e.basic),misc:s(e.basic,e.legend)}})});