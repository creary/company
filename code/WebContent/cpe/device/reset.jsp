﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>恢复出厂设置</title>
<link rel="rl-page-icon" href="${imagePath}refresh.gif" />
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
			},
			getCommandStatus : function(options, command){
				var url = rl.format("${base}cpe/getHostStatus.do?command={1}", arguments),
					data = jQuery("#mainForm").serialize();
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			}
		}
	});
	
	var commandStatusView = {
		"ready" : function(){
			jQuery("#processingSection").hide();
			jQuery("#readySection").show();
		},
		"processing" : function(){
			jQuery("#processingSection").show();
			jQuery("#readySection").hide();
		}
	};
	
	function updateSatus(commandStatus){
		updateSatusSection(commandStatus);
		if(commandStatus == "processing"){
			commandStatusObserver.start();
		}
	}
	
	function updateSatusSection(commandStatus){
		(commandStatusView[commandStatus] || commandStatusView["ready"])();
	}
	
	var commandStatusObserver = {
		observeInteval : 5 * 1000,
		start : function(){
			if(this.observing) return;
			rl.debug(this + ' start(): observe commnad status...');
			this.observing = true;
			this._observe();
		},
		stop : function(){
			if(!this.observing) return;
			rl.debug(this + ' stop(): observe commnad status.');
			this.observing = false;
			window.clearTimeout(this._timer);
		},
		_observe : function(){
			var me = this;
			this._timer = rl.delay(function(){
				oprDirect.invoke("getCommandStatus", {
					success : function (commandStatus){
						if(!rl.isObject(commandStatus)){
							commandStatusObserver.stop();
							return "";
						}
						if(commandStatus.status == "ready"){
							alert("恢复出厂设置完成!");
							
							rl.page.close();
						}
						else{
							if(me.observing) commandStatusObserver._observe();
						}
					},
					error : function(commandStatus){
						commandStatusObserver.stop();
					}
				}, "reset");
			}, this.observeInteval);
		},
		toString : function(){return "[object commandStatusObserver]";}
	};
	
	function setAjaxStartView(){
		jQuery("#btn_action")
			.prop("disabled", true)
			.addClass("disabled")
			.text("正在恢复...");
	}
	function setAjaxEndView(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("开始恢复");
	}
	
	function notifyDeviceChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.deviceDataObserver)){
			opener.deviceDataObserver.fireEvent("change");
		}
	}
	
	function doAction() {
		oprDirect.invoke("doCommand", {
			beforeSend : setAjaxStartView,
			success : function(){
				notifyDeviceChangeToOpener();
				updateSatus("processing");
			},
			complete : setAjaxEndView
		}, "reset");
	}	
	rl.onReady(function(){
		updateSatus("${host.status}");
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
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
        	<h1>恢复出厂设置</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${id}" />
                                <input type="hidden" name="batchMode" value="${batchMode}" />
                                <div class="section first">
                                    <c:if test="${batchMode==0}">
                                	        将设备【${host.name}】恢复出厂设置。
                                	</c:if>
                                    <c:if test="${batchMode==1}">
                                	       将 ${deviceNum} 个设备恢复出厂设置。
                                	</c:if>
                                </div>
                                <div id="readySection" style="display:none;">
                                    <div class="soof_action_bar">
                                        <button id="btn_action" type="button" class="btn primary" autocomplete="off">开始恢复</button>
                                        <button type="button" class="btn white" onclick="rl.page.close();">取消</button>
                                    </div>
                                </div>
                                <div id="processingSection" style="display:none;">
                                    <div class="section" style="padding:3em 0;">
                                        <div class="processing_bar"></div>
                                        <p class="p10">正在“恢复出厂设置”，可能需要几分钟，请稍后...</p>
                                    </div>
                                    <div class="soof_action_bar">
                                        <button type="button" class="btn primary" style="margin-right: 1em;" onclick="rl.page.close();">后台运行</button> <span class="desc">(关闭当前窗口，让后台自动执行。)</span>
                                    </div>
                                </div>
                            </form>
                            <div class="widthholder"></div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>