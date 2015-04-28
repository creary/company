/**
 * @fileOverview report util
 * @change
 	#1 by prcjack @2014/11/25 Creates file.
 */

orderjs.define("cloudac:reportUtil", [
    "open.jquery.jquery",
	"open.jqplot.plugins.jqplot-highlighter",
    "app.deco.engine",
	"app.deco.RadioButton",
	"app.dataBox.KpiViewPlot"
], function(){
    var jQuery = orderjs("open.jquery.jquery"),
        decoUtil = orderjs("app.deco.engine");

	decoUtil.makeJqDeco(rl.dataBox.KpiViewPlot, "Plot", "renderable");
	
	var KpiViewTipWrap = rl.createClass({
		parent : decoUtil.CompTipWrap.decorator,
		
		construct : function(config){
			if(rl.isPrototyping(arguments[0])) return ;
			
			KpiViewTipWrap.parent.apply(this, arguments);
		},
		
		members : {
			/** @lends KpiViewTipWrap# */
			
			update : function(data){
				this.comp
					.show()
					.loadData(data);
				this.hideTip();
				
				return this;
			},
			
			toString : function(){return "[object KpiViewTipWrap]";}
		}
	});
	
	var util = {
		KpiViewTipWrap : decoUtil.makeJqDeco(KpiViewTipWrap),
		
		searchMgr : new rl.util.EventProvider,
		
		createLoader : function(options){
			if(arguments.length > 1){
				return rl.map(arguments, function(opt){return util.createLoader(opt);});
			}
			
			var loader = new decoUtil.ViewLoader(rl.ext({
				sendForm : "#searchForm"
			}, options));
			
			util.searchMgr.on("search", function(){loader.load();});
			
			return loader;
		},
		
		formatSeconds : function (seconds){
			var sec_num = parseInt(seconds, 10); // don't forget the second param
			var hours   = Math.floor(sec_num / 3600);
			var minutes = Math.floor((sec_num - (hours * 3600)) / 60);
			var seconds = sec_num - (hours * 3600) - (minutes * 60);
		
			if (hours   < 10) {hours   = "0"+hours;}
			if (minutes < 10) {minutes = "0"+minutes;}
			if (seconds < 10) {seconds = "0"+seconds;}
			var time = hours+':'+minutes+':'+seconds;
			
			return time;
		},
		
		initTimeTypeSelector : function(options){
			if(!rl.isObject(options)) options = {};
			jQuery(options.timeTypeSeletor || "#timeTypeSeletor")
				.getDecorator()
				.on("change", function(value){
					customCtrlSelector = options.customCtrlSelector || "#timeType_custom_ctrl";
					if(value == "custom"){
						jQuery(customCtrlSelector).show();
					}
					else{
						jQuery(customCtrlSelector).hide();
						util.search();
					}
				});
			
			orderjs("gui.form.DateSelect");
			orderjs(function(){
				function isValidRange(startTime, endTime){
					var s = Date.parseDate(startTime.value, startTime.format),
						e = Date.parseDate(endTime.value, endTime.format);
					
					return (e > s);
				}
				var date1 = new rl.gui.form.DateSelect({
					renderTarget : options.startTimeCtnId || "startTimeCtn",
					label : "开始 ",
					autoRenderOnReady : true,
					name : options.startTimeName || "startTime",
					width:100
				});
				date1.on("select", function(){
					if(date2.value == "" || !isValidRange(date1, date2)){
						date2.focus();
					}
					else{
						util.search();
					}
				});
				
				var date2 = new rl.gui.form.DateSelect({
					renderTarget : options.endTimeCtnId || "endTimeCtn",
					label : "结束 ",
					autoRenderOnReady : true,
					name : options.endTimeName || "endTime",
					width:100
				});
				date2.on("select", function(){
					if(date1.value == "" || !isValidRange(date1, date2)){
						date1.focus();
					}
					else{
						util.search();
					}
				});
			});
		},
		
		search : function(){
			util.searchMgr.fireEvent("search");
		}
	};
		
	
	return util;
});
