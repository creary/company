<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>批量配置设备</title>
<link rel="rl-page-icon" href="${imagePath}wrench-orange.png" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"open.jquery.jquery",
	"lib.rpc.JqueryAjaxDirect",
	"x:mti.Validator"
);

orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect");
	
	jQuery.ajaxSetup({
		dataType : "json",
		type : "post",
		cache : false
	});
	
	var oprDirect = new JqueryAjaxDirect({
		services : {
			doCommand : function(options, command){
				var url = rl.format("${base}cpe/doAction.do?command={1}", arguments),
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
			isMonitorOn = jQuery("input[name='monitor']:checked").val() == "on";
		
		if(isMonitorOn){
			var toServer = mainForm.toServer;
			
			toServer.value = rl.trim(toServer.value);
			
			if(!rlx.mti.validate(mainForm)) return false;
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
		}, "settingDetect");
	}
	
	rl.onReady(function(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
		
		function updateMonitorOnSection(){
			var visible = jQuery("input[name='monitor']:checked").val() == "on";
			rl.debug(this + 'updateMonitorOnSection(): visible = ' + visible + '');
			jQuery("#monitorOnSection")
				.css("display", (visible ? "block" : "none"));
		}
		jQuery("input[name='monitor']").on("click", updateMonitorOnSection);
		updateMonitorOnSection();
		
	});
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
            <h1>批量配置  ${deviceNum} 个设备</h1>
            <jsp:include page="/cpe/device/tabs.jsp" >
                 <jsp:param name="tab" value="detect" />
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
                                <div class="section first">
                                    <p class="p5">探测，是指基于 WiFi 探测技术、移动互联网和云计算等先进技术识别商铺附近的智能手机或者WiFi终端，即使手机等终端未接入商家的 WiFi 网络，依然可被探测。</p>
                                </div>
                                <div class="section">
                                    <h3>探测开关</h3>
                                    <p class="p10">
                                        <input type="radio" name="monitor" class="field_selector" value="off" checked id="field_turnOnMonitor" /><label class="selector_label" for="field_turnOnMonitor">关闭（默认）</label>&nbsp;&nbsp;
                                        <input type="radio" name="monitor" class="field_selector" value="on" id="field_turnOffMonitor" /><label class="selector_label" for="field_turnOffMonitor">开启</label>
                                    </p>
                                </div>
                                <div id="monitorOnSection" style="display:none;">
                                    <div class="section">
                                        <h3>接收服务器</h3>
                                        <p class="p5">
                                        	探测数据的接收服务器。请输入 IP 或域名，格式如：120.24.77.206 或 www.abc.com 。</p>
                                        <p class="p10">
                                            <input name="toServer" id="field_dataServer" dataType="Custom" regexp="^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$|^([a-zA-Z0-9]([a-zA-Z0-9\-]{0,61}[a-zA-Z0-9])?\.)+[a-zA-Z]{2,6}$" class="field" value="${toServer}" /> <font color="red">*</font>
                                        </p>
                                    </div>
                                    <div class="section">
                                        <h3>进店场强</h3>
                                        <p class="p5">
                                        	当场强大于该值，认为顾客进入店内，默认为-70 (dbm)。</p>
                                        <p class="p10">
                                            <input name="enterSnr" id="field_dataServer" class="field" />
                                        </p>
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