﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>更新固件</title>
<link rel="rl-page-icon" href="${imagePath}refresh.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"open.jquery.jquery",
	"lib.rpc.JqueryAjaxDirect",
	"cloudac:SectionSwitcher"
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect"),
		SectionSwitcher = orderjs("cloudac:SectionSwitcher");
	
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
							alert("固件更新完成!");
							
							rl.page.close();
						}
						else{
							if(me.observing) commandStatusObserver._observe();
						}
					},
					error : function(commandStatus){
						commandStatusObserver.stop();
					}
				}, "updateFirmware");
			}, this.observeInteval);
		},
		toString : function(){return "[object commandStatusObserver]";}
	};
	
	function setAjaxStartView(){
		jQuery("#btn_action")
			.prop("disabled", true)
			.addClass("disabled")
			.text("正在更新...");
	}
	function setAjaxEndView(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("开始更新");
	}
	
	function notifyDeviceChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.deviceDataObserver)){
			opener.deviceDataObserver.fireEvent("change");
		}
	}
	
	function doAction() {
	    var sourceType = jQuery("input[name='sourceType']:checked").val();
		if(sourceType == "customized"){
			if (!rlx.mti.validate(mainForm)) return false;
		}
		else{
			var fileIds = jQuery("input[name='fileId']");
			if(fileIds.length > 0){
				var has = fileIds.filter(":checked").length > 0;
				if(!has){
					alert("必须选择一个固件");
					return false;
				}
			}
		}
		oprDirect.invoke("doCommand", {
			beforeSend : setAjaxStartView,
			success : function(){
				notifyDeviceChangeToOpener();
				updateSatus("processing");
			},
			complete : setAjaxEndView
		}, "updateFirmware");
	}
	
	rl.onReady(function(){
		updateSatus("${host.status}");
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
			
		new SectionSwitcher({
			trigger : "input[name='sourceType']"
		});
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
        	<c:if test="${batchMode==1}">
                <h1>批量更新固件</h1>
            </c:if>
        	<c:if test="${batchMode==0}">
                <h1>更新固件</h1>
            </c:if>
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
                                    <c:if test="${batchMode==1}">
                                                                                                   为 ${deviceNum} 个设备更新固件。</h1>
                                    </c:if>
                                    <c:if test="${batchMode==0}">
                                                                                                    为设备【${host.name}】更新固件。
                                    </c:if>
                                </div>
                                <div id="readySection" style="display:none;">
                                    <div class="section">
                                        <h3>选择固件</h3>                                        
                                        <p class="p10 selectors_row">
                                        	<input type="radio" name="sourceType" id="field_sourceType_predefined" value="predefined" class="field_selector" data-linkSection="#section_sourceType_predefined" checked="checked" /><label class="selector_label" for="field_sourceType_predefined">平台预定义固件</label>
                                            <security:authorize ifAnyGranted="ROLE_SOOFOUND,ROLE_ADMIN">
                                                <input type="radio" name="sourceType" id="field_sourceType_customized" value="customized" data-linkSection="#section_sourceType_customized" class="field_selector"  /><label class="selector_label" for="field_sourceType_customized">自定义固件</label>
                                            </security:authorize>
                                        </p>                                        
                                        <div class="p10 switchable_section" id="section_sourceType_predefined">
                                            <table border="0" class="soof_grid_fields">
                                                <tr>
                                                    <th class="label">固件名称</th>
                                                    <th class="label">产品型号</th>
                                                    <th class="label">版本</th>
                                                    <th class="label">文件名</th>
                                                    <th class="label">创建时间</th>
                                                </tr>
                                                <c:if test="${notNull}">
                                                    <c:forEach items="${dtos}" var="dto">
                                                        <tr>
                                                            <td class="field_ctn">
                                                                <input type="radio" name="fileId" value="${dto.id}" id="radio_${dto.id}" class="field_selector" /><label for="radio_${dto.id}" class="selector_label">${dto.descr}</label>
                                                            </td>
                                                            <td class="field_ctn">${dto.productModel}</td>
                                                            <td class="field_ctn">${dto.version}</td>
                                                            <td class="field_ctn">${dto.fileName}</td>
                                                            <td class="field_ctn">${dto.uploadTime}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${!notNull}">
                                                    <tr>
                                                        <td class="field_ctn" colspan="5">
                                                            <p style="text-align:center; padding:.5em;">当前没有可用的固件</p>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </table>
                                        </div>
                                        <security:authorize ifAnyGranted="ROLE_SOOFOUND,ROLE_ADMIN">
	                                        <div class="p10 fields_box switchable_section" id="section_sourceType_customized" style="display:none;">
	                                        	<div class="section first">
	                                                <p class="p10">
	                                                    固件 URL:<span><input class="field" dataType="Custom" regexp="^http?:\/\/.+?$" name="fileUrl" /> <font color="red">*</font></span> <span class="desc">(链接必须以 http:// 开头)</span>
	                                                </p>
	                                                <p class="p10">
	                                                    固件 MD5:<span><input class="field" dataType="Require" name="fileMd5" /> <font color="red">*</font></span>
	                                                </p>
	                                                <p class="p10">
	                                                   固件版本:<span><input class="field" dataType="Require" name="fileVersion" /> <font color="red">*</font></span>
	                                                </p>
	                                                <p class="p10">
	                                                   固件大小:<span><input class="field" dataType="Require" name="fileSize" /> <font color="red">*</font></span>
	                                                </p>
	                                            </div>
	                                        </div>
                                        </security:authorize>
                                    </div>
                                    <div class="soof_action_bar">
                                        <c:if test="${notNull}">
                                            <button id="btn_action" type="button" class="btn primary" autocomplete="off">开始更新</button>
                                        </c:if>
                                        <button type="button" class="btn white" onclick="rl.page.close();">取消</button>
                                    </div>
                                </div>
                                <div id="processingSection" style="display:none;">
                                    <div class="section" style="padding:3em 0;">
                                        <div class="processing_bar"></div>
                                        <p class="p10">正在“更新固件”，可能需要几分钟，请稍后...</p>
                                    </div>
                                    <div class="soof_action_bar">
                                        <button type="button" class="btn primary" style="margin-right: 1em;" onclick="rl.page.close();">后台运行</button> <span class="desc">(关闭当前窗口，让后台自动执行更新。)</span>
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