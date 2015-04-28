﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>更新配置</title>
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
			}
		}
	});
	
	function openReboot(){
		var url = "${base}cpe/readyReboot.do?autoStart=1&batchMode=${batchMode}&id=${id}";
		
		rl.page.open(url, "cpe.readyReboot", rl.gui.winDlgOpt);
	}
	
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
	    var fileIds = jQuery("input[name='fileId']");
		if(fileIds.length > 0){
			var has = fileIds.filter(":checked").length > 0;
			if(!has){
				alert("必须选择一个配置");
				return false;
			}	    
		}
		
		oprDirect.invoke("doCommand", {
			beforeSend : setAjaxStartView,
			success : function(result){
				if(rl.isObject(result) && result.needRestart){
					if(confirm("配置更新已完成，需要重启设备才能生效，是否立即重启？")) openReboot();
				}
				else{
					alert("配置更新成功！");
				}
				
				notifyDeviceChangeToOpener();
				rl.page.close();
			},
			complete : setAjaxEndView
		}, "updateConfig");
	}
	
	rl.onReady(function(){
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
        	<c:if test="${batchMode==1}">
                <h1>批量更新配置</h1>
            </c:if>
        	<c:if test="${batchMode==0}">
                <h1>更新配置</h1>
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
                                                                                                   为 ${deviceNum} 个设备更新配置。</h1>
                                    </c:if>
                                    <c:if test="${batchMode==0}">
                                                                                                    为设备【${host.name}】更新配置。
                                    </c:if>
                                </div>
                                <div class="section">
                                    <h3>选择配置</h3>
                                    <p class="p5">
                                        <table border="0" class="soof_grid_fields">
                                        <tr>
                                            <th class="label">名称</th>
                                            <th class="label">产品型号</th>
                                            <th class="label">创建时间</th>
                                        </tr>                                            
                                        <c:if test="${notNull}">
                                            <c:forEach items="${dtos}" var="dto">
                                                <tr>
                                                    <td class="field_ctn">
                                                        <input type="radio" name="fileId" value="${dto.id}" id="radio_${dto.id}" class="field_selector" /><label for="radio_${dto.id}" class="selector_label">${dto.descr}</label>
                                                    </td>
                                                    <td class="field_ctn">${dto.productModel}</td>
                                                    <td class="field_ctn">${dto.uploadTime}</td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${!notNull}">
                                            <tr>
                                                <td class="field_ctn" colspan="3">
                                                    <p style="text-align:center; padding:.5em;">当前没有可用的配置</p>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </table>
                                    </p>
                                </div>
                                <div class="soof_action_bar">
                                    <c:if test="${notNull}">
                                        <button id="btn_action" type="button" class="btn primary" autocomplete="off">开始更新</button>
                                    </c:if>
                                    <button type="button" class="btn white" onclick="rl.page.close();">取消</button>
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