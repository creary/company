﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑 LAN 网络</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"open.jquery.jquery",
	"lib.rpc.JqueryAjaxDirect"
);

orderjs(function(){
    var track = this.track;
	var jQuery = orderjs("open.jquery.jquery"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect");
	
	jQuery.ajaxSetup({
		dataType : "json",
		type : "post",
		cache : false
	});
	
	var oprDirect = new JqueryAjaxDirect({
		services : {
			editSsid : function(options){
				var url = "${base}cpe/updateLan.do",
					data = jQuery("#mainForm").serialize();				
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			}
		}
	});
	
	function openReboot(){
		var url = "${base}cpe/readyReboot.do?autoStart=1&id=${id}&batchMode=0";		
		rl.page.open(url, "cpe.readyReboot", rl.gui.winDlgOpt);
	}
	
	function setAjaxStartView(){
		jQuery("#btn_action")
			.prop("disabled", true)
			.addClass("disabled")
			.text("正在保存...");
	}
	function setAjaxEndView(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("保 存");
	}
	
	function notifyDeviceChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.deviceDataObserver)){
			opener.deviceDataObserver.fireEvent("change");
		}
	}
	
	function doAction() {
	    var mainForm = document.mainForm,
			enable = jQuery("input[name='enable']:checked").val() == "1";
			
		if ((enable && rlx.mti.validate(mainForm)) || !enable) {
			oprDirect.invoke("editSsid", {
				beforeSend : setAjaxStartView,
				success : function(result){
					if(rl.isObject(result) && result.needRestart){
						if(confirm("操作已完成，需要重启设备才能生效，是否立即重启？")) openReboot();
					}
					else{
						alert("操作成功！");
					}
					
					notifyDeviceChangeToOpener();
					rl.page.close();
				},
				complete : setAjaxEndView
			});
		}
	}
	
    rl.onReady(function(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
		jQuery("#btn_openAuthPolicy").on("click", function(){
			var id = jQuery("#sel_authPolicy").val(),
				url = "${base}portal/policyReadyEditAuth.do?id=" + id;
			rl.page.open(url, "policyReadyEdit", rl.gui.winDlgOpt);
		});
		jQuery("#btn_openPortal").on("click", function(){
			var id = jQuery("#sel_portal").val(),
				url = "${base}portal/" + id + "/global/edit.do";
			rl.page.open(url, "portalDetailReadyEdit", rl.gui.winDlgOpt);
		});
		
		function updateNetEnabledSection(){
			var enable = jQuery("input[name='enable']:checked").val() == "1";			
			jQuery("#netEnabledSection").css("display", (enable ? "block" : "none"));
		}
		jQuery("#field_enableNet, #field_disableNet").on("click", updateNetEnabledSection);
		updateNetEnabledSection();
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
        	<h1>编辑 LAN 网络</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${id}" />
                                <input type="hidden" name="batchMode" value="0" />
                                <input type="hidden" name="ife" value="${ife}" />
                                <div class="section first">
                                	编辑设备【${host.name}】的LAN 网络配置。
                                </div>
                                <div class="section">
                                    <h3>网络开关</h3>
                                    <p class="p5">
                                    	开启或关闭该网络。
                                    </p>
                                    <p class="p10">
                                        <input type="radio" name="enable" id="field_enableNet" value="1" class="field_selector" ${ssid.enable==1?"checked":""} /><label class="selector_label" for="field_enableNet">开启</label>&nbsp;&nbsp;&nbsp;
                                        <input type="radio" name="enable" id="field_disableNet" value="0" class="field_selector"  ${ssid.enable==0?"checked":""} /><label class="selector_label" for="field_disableNet">关闭</label>
                                    </p>
                                </div> 
                                <div id="netEnabledSection">                                    
                                    <div class="section">
                                        <h3>认证策略</h3>
                                        <p class="p5">设置用户访问您的 WiFi 时的认证策略：如是否需要认证，认证方式等。</p>
                                        <p class="p10">
                                            <select id="sel_authPolicy" name="policyId" class="field">
                                                <c:forEach items="${policies}" var="p">
                                                    <option value="${p.id}" ${p.id==ssid.policyId?"selected":""} >${p.name}</option>
                                                </c:forEach>
                                            </select> 
                                            <a id="btn_openAuthPolicy" href="javascript:void(0);" class="btn">查看所选</a>
                                        </p>
                                    </div> 
                                    <div class="section">
                                        <h3>Portal</h3>
                                        <p class="p5">设置用户访问您的 WiFi 时强制弹出的门户网站。一个 Portal 通常包含了欢迎页，认证前页，认证后页等。</p>
                                        <p class="p10">
                                            <select id="sel_portal" name="portalId" class="field">
                                                <c:forEach items="${portals}" var="p">
                                                    <option value="${p.id}" ${p.id==ssid.portalId?"selected":""}>${p.name}</option>
                                                </c:forEach>
                                            </select> 
                                            <a id="btn_openPortal" href="javascript:void(0);" class="btn">查看所选</a>
                                        </p>
                                    </div> 
                                </div>
                                <div class="soof_action_bar">
                                    <button id="btn_action" type="button" class="btn primary" autocomplete="off">保 存</button>
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