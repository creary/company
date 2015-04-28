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
	"lib.rpc.JqueryAjaxDirect"
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
			isQosOn = jQuery("input[name='qos']:checked").val() == "1";
		
		if(isQosOn){
			var qosTx = mainForm.qosTx,
				qosRx = mainForm.qosRx;
			
			qosTx.value = rl.trim(qosTx.value);
			qosRx.value = rl.trim(qosRx.value);
			
			var qosTxVal = parseInt(qosTx.value),
				qosRxVal = parseInt(qosRx.value);
			
			if(isNaN(qosTxVal)){
				alert("上行带宽应输入数字");
				qosTx.focus();
				return false;
			}
			if(isNaN(qosRxVal)){
				alert("下行带宽应输入数字");
				qosRxVal.focus();
				return false;
			}
			
			if(!(qosTxVal >= 64)){
				alert("上行带宽最小不低于 64");
				qosTx.focus();
				return false;
			}
			if(!(qosRxVal >= 64)){
				alert("下行带宽最小不低于 64");
				qosRx.focus();
				return false;
			}
			rl.debug(this + ' qosTxVal = ' + qosTxVal + ' qosRxVal = ' + qosRxVal + '');
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
		}, "settingQos");
	}
	
	rl.onReady(function(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
		
		function updateQosOnSection(){
			var visible = jQuery("input[name='qos']:checked").val() == "1";
			rl.debug(this + 'updateQosOnSection(): visible = ' + visible + '');
			jQuery("#qosOnSection")
				.css("display", (visible ? "block" : "none"));
		}
		jQuery("input[name='qos']").on("click", updateQosOnSection);
		updateQosOnSection();
		
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
                 <jsp:param name="tab" value="qos" />
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
                                <input type="hidden" name="pid" value="${pid}" />
                                <div class="section first">
                                    <p class="p5">QOS 能有效协调您的网络数据优先级，如能让 QQ 和微信等应用的速度优先，网页访问次之，而其它如 bt 下载等速度最慢，从而确保网络繁忙时，不影响 QQ 和微信等常用应用。</p>
                                </div>
                                <div class="section">
                                    <h3>QOS 开关</h3>
                                    <p class="p10">
                                        <input type="radio" name="qos" class="field_selector" value="0" ${!turnOnQos?"checked":""} id="field_turnOnQos" /><label class="selector_label" for="field_turnOnQos">关闭（默认）</label>&nbsp;&nbsp;
                                        <input type="radio" name="qos" class="field_selector" value="1" ${turnOnQos?"checked":""} id="field_turnOffQos" /><label class="selector_label" for="field_turnOffQos">开启</label>
                                    </p>
                                </div>
                                <div id="qosOnSection" style="display:none;">
                                    <div class="section">
                                        <h3>带宽设置</h3>
                                        <p class="p5">为了让 QOS 能准确限速，请填写准确的网络带宽。<b>(1024kbps = 1Mbps，1Mbps = 100KB下载速度)</b></p>
                                        <p class="p10">
                                            <font color="red">*</font> 上行带宽： 
                                            <input name="qosTx" value="${qosTx}" class="field s" /> kbps 
                                            <span class="desc">（最小值为 64）</span>
                                        </p>
                                        <p class="p10">
                                            <font color="red">*</font> 下行带宽： 
                                            <input name="qosRx" value="${qosRx}" class="field s" /> kbps 
                                            <span class="desc">（最小值为 64）</span>
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