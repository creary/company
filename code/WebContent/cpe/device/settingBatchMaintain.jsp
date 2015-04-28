﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>批量配置设备</title>
<link rel="rl-page-icon" href="${imagePath}wrench-orange.png" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js?_v=${cacheBuster}"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"open.jquery.jquery",
	"lib.rpc.JqueryAjaxDirect",
	"app.deco.WeekHourSelect"
);

orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect"),
		WeekHourSelect = orderjs("app.deco.WeekHourSelect");
	
	jQuery.ajaxSetup({
		dataType : "json",
		type : "post",
		cache : false
	});
	
	var oprDirect = new JqueryAjaxDirect({
		services : {
			doCommand : function(options, command){
				var url = rl.format("${base}cpe/settingMaintain.do?command={1}", arguments),
					data = jQuery("#mainForm").serialize();
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			}
		}
	});	
	
	function setAjaxStartView(){
		jQuery("#btn_action")
			.prop("disabled", true)
			.addClass("disabled")
			.text("正在应用...");
	}
	function setAjaxEndView(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("应 用");
	}
	
	//observe device data.
	window.deviceDataObserver = rl.ext(new rl.util.EventProvider, {
		changed : false
	});
	deviceDataObserver.on("change", function(){
		deviceDataObserver.changed = true;
		notifyDeviceChangeToOpener();
	});
	
	function notifyDeviceChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.deviceDataObserver)){
			opener.deviceDataObserver.fireEvent("change");
		}
	}
	
	function doAction() {
	    var mainForm = document.mainForm,
			isAutoRebootOn = jQuery("input[name='autoReboot']:checked").val() == "1";
		
		if(isAutoRebootOn){
			var whSelect = WeekHourSelect("#weekHourCtn").getDecorator(0);
			if(whSelect.isNonSelect()){
				alert("请设置重启时间！");
				return false;
			}
		}
		
		oprDirect.invoke("doCommand", {
			beforeSend : setAjaxStartView,
			success : function(result){
				if(rl.isObject(result) && result.needRestart){
					if(confirm("配置已完成，需要重启设备才能生效，是否立即重启？")) openReboot();
				}
				else{
					alert("应用成功！");
				}
				
				notifyDeviceChangeToOpener();
			},
			complete : setAjaxEndView
		}, "settingMaintain");
	}
	
    orderjs.ready(function(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
		
		function updateAutoRebootOnSection(){
			var visible = jQuery("input[name='autoReboot']:checked").val() == "1";
			jQuery("#autoRebootOnSection")[visible ? "show" : "hide"]();
		}
		jQuery("input[name='autoReboot']")
			.on("click", updateAutoRebootOnSection);
		updateAutoRebootOnSection();
        
		WeekHourSelect("#weekHourCtn");
    });
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}entity.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>批量配置  ${deviceNum} 个设备</h1>
            <jsp:include page="/cpe/device/tabs.jsp" >
                 <jsp:param name="tab" value="maintain" />
                 <jsp:param name="batchMode" value="1" />
            </jsp:include>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${id}" />
                                <input type="hidden" name="pid" value="${pid}" />
                                <div class="section first">
                                    <h3>自动重启开关</h3>
                                    <p class="p5">设置设备在指定的时间（周期）自动重启。</p>
                                    <p class="p10">
                                        <input type="radio" name="autoReboot" class="field_selector" value="0" id="field_autoReboot_off" /><label class="selector_label" for="field_autoReboot_off">关闭（默认）</label>&nbsp;&nbsp;
                                        <input type="radio" name="autoReboot" class="field_selector" value="1" id="field_autoReboot_on" /><label class="selector_label" for="field_autoReboot_on">开启</label>
                                    </p>
                                </div>
                                <div id="autoRebootOnSection" style="display:none;">
                                    <div class="section">
                                        <h3>重启时间</h3>
                                        <p class="p5">
                                        设置每周的重启时间，每个小格表示30分钟。单击小格进行选择，按住 Ctrl 键反选，拖拽可连选。
                                        </p>
                                        <div class="p10" id="weekHourCtn">
                                        	<input type="hidden" name="rebootWeekHour" value="" />
                                        </div>
                                    </div>
                                </div>
                                <div class="soof_action_bar">
                                    <button id="btn_action" type="button" class="btn primary" autocomplete="off">应 用</button>
                                    <button type="button" class="btn white" onclick="rl.page.close();">取消</button>
                                </div>
                                <div class="widthholder"></div>
                            </form>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>