<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>溯源信息</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"lib.data.engine",
	"gui.form.Input",
	"gui.form.Combox",
	"gui.form.DateSelect",
	"x:mti.listPage"
);

orderjs(function(){
	
	function doSearch(){
		var bar = rlx.mti.listPageToolbar;
		var inp_mac = bar.getItemById("inp_mac"),
			select_timeType = bar.getItemById("select_timeType"),
			select_startTime = bar.getItemById("select_startTime"),
			select_endTime = bar.getItemById("select_endTime");
		
		inp_mac.setValue(rl.trim(inp_mac.value));
		if(!inp_mac.value){
			alert("请输入MAC！");
			return false;
		}		
		if(select_timeType.value == "custom"){
			if(!select_startTime.value){
				alert("请输入开始时间！");
				return false;
			}
			if(!select_endTime.value){
				alert("请输入结束时间！");
				return false;
			}
			
			var s = Date.parseDate(select_startTime.value, select_startTime.format),
				e = Date.parseDate(select_endTime.value, select_endTime.format);
			
			if(!(e > s)){
				alert("开始时间须小于结束时间！");
				return false;
			}
		}
		
		rlx.mti.listPageGrid.load();
		hideStartTip();
	}
	rlx.mti.createListPage({
		searchDialog : false,
		toolbar : {
			items : [						
				{
					id : "inp_mac",
					ctype : "input",
					valueListener : "field_mac",
					label : "用户 MAC ",
					maskValue : "MAC 地址"
				},
				{
					ctype : "MenuSplit",
					splitType : "blank"
				},
				{
					id : "select_timeType",
					ctype : "Combox",
					valueListener : "field_timeType",
					label : "时间 ",
					items : [
						{ text : "近一小时", value : "lastHour"},
						{ text : "今天", value : "today"},
						{ text : "自定义", value : "custom"}
					],
					action : function(val){
						var bar = rlx.mti.listPageToolbar;
						var select_startTime = bar.getItemById("select_startTime"),
							select_endTime = bar.getItemById("select_endTime");						
						if(val == "custom"){
							select_startTime.show();
							select_endTime.show();
						}
						else{
							select_startTime.hide();
							select_endTime.hide();
						}
					}
				},
				{
					id : "select_startTime",
					ctype : "DateSelect",
					format : "%Y-%m-%d %H:%M",
					valueListener : "field_startTime",
					label : "&nbsp;开始",
					iniVisible : false,
					width : 130,
					showTime : true
				},
				{
					id : "select_endTime",
					ctype : "DateSelect",
					format : "%Y-%m-%d %H:%M",
					valueListener : "field_endTime",
					label : "&nbsp;结束",
					iniVisible : false,
					width : 130,
					showTime : true
				},
				{
					text : "查询",
					icon : "${imagePath}search.gif", 
					action : doSearch
				},
				{
					ctype : "MenuSplit"
				},
			]
		},	
		grid :{
			dataUrl : "${base}detect/getCustomerPath.do?branchId=${branchId}",
			dataFields : ["alias","logTime","stayLong"],
			loadDataBeforeRender : false,
			columns : [
				{caption: "所在铺位AP", name: "alias"},
				{caption: "时间", name: "logTime"},
				{caption: "停留时间", name: "stayLong"}				            	            	
			]
		}
	});
	
	rl.each(["inp_mac", "inp_url"], function(id){
		var bar = rlx.mti.listPageToolbar,
			inp = bar.getItemById(id);
		
		if(inp){
			inp.on("enter", doSearch);
		}
	});
	
	rl.onReady(function(){
		var bar = rlx.mti.listPageToolbar,
			grid = rlx.mti.listPageGrid,
			inp_mac = bar.getItemById("inp_mac"),
			iniSearchKey = inp_mac.value,
			startup = rl.isEmpty(iniSearchKey) ? showStartTip : doSearch;
		
		function hndGridRender(){
			startup();
			grid.un("render", startup);
		}
		
		if(grid._rendered){
			startup();
		}
		else{
			grid.on("render", hndGridRender);
		}
	});
	
	function showStartTip(){
		rl.$("startTip").show();
	}
	function hideStartTip(){
		rl.$("startTip").hide();
	}
});
</script>
</head>
<body>
<div id="startTip" style="position:absolute; z-index:10; top:30px; width:100%; height:100%; padding-top:100px;text-align:center; font-size:16px; color:#666; display:none; ">
	请在工具栏中输入待查询用户的 MAC 地址，然后点击【查询】按钮开始查询。
</div>
<form id="queryForm" name="queryForm" method="post" style="display:none;">
	<input id="field_mac" type="hidden" name="mac" />
	<input id="field_timeType" type="hidden" name="timeType" />
	<input id="field_startTime" type="hidden" name="startTime" />
	<input id="field_endTime" type="hidden" name="endTime" />
</form>
</body>
</html>