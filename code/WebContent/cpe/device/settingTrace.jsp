﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备配置【${host.name}】</title>
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
			isTraceOn = jQuery("input[name='MonURLType']:checked").val() == "trace";
		
		if(isTraceOn){
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
		}, "settingTrace");
	}
	
	rl.onReady(function(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
		
		function updateTraceOnSection(){
			var MonURLType = jQuery("input[name='MonURLType']:checked").val(),
				visible = /trace/i.test(MonURLType);
			rl.debug(this + 'updateTraceOnSection(): visible = ' + visible + '');
			jQuery("#traceOnSection")
				.css("display", (visible ? "block" : "none"));
		}
		jQuery("input[name='MonURLType']").on("click", updateTraceOnSection);
		updateTraceOnSection();
		
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
        	<h1>设备配置【${host.name}】</h1>
            <jsp:include page="/cpe/device/tabs.jsp" >
                 <jsp:param name="tab" value="trace" />
                 <jsp:param name="batchMode" value="0" />
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
                                    <p class="p5">溯源即对用户上网记录（如所浏览的网站等）进行追踪和记录。</p>
                                </div>
                                <div class="section">
                                    <h3>溯源开关</h3>
                                    <p class="p5">溯源技术可实现广告过滤，用来屏蔽常用网站的广告，以提升 WiFi 用户的体验。溯源功能会占用一定带宽，请酌情使用。</p>
                                    <p class="p10">
                                        <input type="radio" name="MonURLType" class="field_selector" value="off" ${host.trace eq 'off'?"checked":""} id="field_MonURLType_off" /><label class="selector_label" for="field_MonURLType_off">关闭（默认）</label>&nbsp;&nbsp;
                                        <input type="radio" name="MonURLType" class="field_selector" value="trace" ${trace?"checked":""} id="field_MonURLType_trace" /><label class="selector_label" for="field_MonURLType_trace">开启溯源</label>&nbsp;&nbsp;
                                    </p>
                                </div>
                                <div id="traceOnSection" style="display:none;">
                                    <div class="section">
                                        <h3>接收服务器</h3>
                                        <p class="p5">
                                        	溯源数据的接收服务器。请输入 IP 或域名，格式如：120.24.77.206 或 www.abc.com 。</p>
                                        <p class="p10">
                                            <input name="toServer" id="field_dataServer" dataType="Custom" regexp="^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$|^([a-zA-Z0-9]([a-zA-Z0-9\-]{0,61}[a-zA-Z0-9])?\.)+[a-zA-Z]{2,6}$" class="field" value="${toServer}" /> <font color="red">*</font>
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