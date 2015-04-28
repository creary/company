﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>批量编辑 WiFi 网络</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif?_v=${cacheBuster}" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js?_v=${cacheBuster}"></script>
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
	
	function reload(){
		window.location.reload();
	}
	
	//observe portal data.
	window.portalDataObserver = new rl.util.EventProvider;
	portalDataObserver.on("change", reload);
	
	//observe policy data.
	window.authPolicyDataObserver = new rl.util.EventProvider;
	authPolicyDataObserver.on("change", reload);
	
	//observe policy data.
	window.ssidPolicyDataObserver = new rl.util.EventProvider;
	ssidPolicyDataObserver.on("change", reload);
	
	var oprDirect = new JqueryAjaxDirect({
		services : {
			editSsid : function(options){
				var url = "${base}cpe/updateSsid.do?batchMode=1",
					data = jQuery("#mainForm").serialize();				
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			}
		}
	});
	
	function openReboot(){
		var url = "${base}cpe/readyReboot.do?autoStart=1&id=${id}&batchMode=1";		
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
		jQuery("#btn_addSsidPolicy").on("click", function(){
			var url = "${base}cpe/ssidCodeReadyAdd.do";
			rl.page.open(url, "ssidCodeReadyAdd", rl.gui.paneDlgOpt);
		});
		jQuery("#btn_addAuthPolicy").on("click", function(){
			var url = "${base}portal/policyReadyEdit.do";
			rl.page.open(url, "policyReadyAdd", rl.gui.winDlgOpt);
		});
		jQuery("#btn_openAuthPolicy").on("click", function(){
			var id = jQuery("#sel_authPolicy").val();
			if(id=="-1"){
				alert("该选项表示不修改此配置，请选择其它选项后查看！");
				return;
			}
			var url = "${base}portal/policyReadyEditAuth.do?id=" + id;
			rl.page.open(url, "policyReadyEdit", rl.gui.winDlgOpt);
		});
		jQuery("#btn_addPortal").on("click", function(){
			var url = "${base}portal/instanceReadyAdd.do";
			rl.page.open(url, "portalReadyAdd", rl.gui.winDlgOpt);
		});
		jQuery("#btn_openPortal").on("click", function(){
			var id = jQuery("#sel_portal").val();
			if(id=="-1"){
				alert("该选项表示不修改此配置，请选择其它选项后查看！");
				return;
			}
			var url = "${base}portal/" + id + "/global/edit.do";
			rl.page.open(url, "portalDetailReadyEdit", rl.gui.winDlgOpt);
		});
		
		function updateNetEnabledSection(){
			var enable = jQuery("input[name='enable']:checked").val() == "1";			
			jQuery("#netEnabledSection").css("display", (enable ? "block" : "none"));
		}
		jQuery("#field_enableNet, #field_disableNet").on("click", updateNetEnabledSection);
		updateNetEnabledSection();

		function updateUseSsidPolicySection(){
			var use = jQuery("input[name='useSsidPolicy']").prop("checked");
			jQuery("#section_useSsidPolicy")[use ? "show" : "hide"]();
		}
		function updateSsidFieldEnable(){
			var use = jQuery("input[name='useSsidPolicy']").prop("checked");
			jQuery("#field_ssid").prop("disabled", use);
		}
		function updateSsidFieldValue(){
			var use = jQuery("input[name='useSsidPolicy']").prop("checked"),
				ssidFieldJq = jQuery("#field_ssid"),
				manualSsidAttr = "manualSsid";
			
			if(use){
				var policySsid = jQuery("#sel_ssidPolicy option:selected").attr("data-ssid");
				
				if(!rl.isDefined(ssidFieldJq.data(manualSsidAttr))){//cache manual set value.
					ssidFieldJq.data(manualSsidAttr, ssidFieldJq.val());
				}
				ssidFieldJq.val(policySsid);
			}
			else{//restore manual set value
				var manualSsid = ssidFieldJq.data(manualSsidAttr);
				if(rl.isDefined(manualSsid)){
					ssidFieldJq
						.val(manualSsid)
						.removeData(manualSsidAttr);
				}
			}
		}
		function updateUseSsidPolicyFields(){
			updateSsidFieldEnable();
			updateUseSsidPolicySection();
			updateSsidFieldValue();
		}
		
		jQuery("input[name='useSsidPolicy']")
			.on("click", updateUseSsidPolicyFields);
		jQuery("#sel_ssidPolicy")
			.on("change", updateSsidFieldValue);
		updateUseSsidPolicyFields();
		
		orderjs(
			"css>open.jquery-poshytip.tip-lightgray.tip-lightgray",
			"open.jquery-poshytip.jquery-poshytip"
		);
		orderjs(function(){
			jQuery(".soof_need_tooltip").poshytip({
				className: "tip-lightgray"
			});
		});		
	});
});

</script>
<link href="${cssPath}common.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${cssPath}icons.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>批量编辑 WiFi 网络</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${id}" />
                                <input type="hidden" name="ife" value="${ife}" />
                                <div class="section first">
                                	编辑 ${deviceNum}个设备的 SSID ${ife+1} 网络配置。
                                </div>
                                <div class="section">
                                    <h3>网络开关</h3>
                                    <p class="p5">
                                    	开启或关闭该网络。
                                    </p>
                                    <p class="p10">
                                        <input type="radio" name="enable" id="field_enableNet" value="1" class="field_selector" checked /><label class="selector_label" for="field_enableNet">开启</label>&nbsp;&nbsp;&nbsp;
                                        <input type="radio" name="enable" id="field_disableNet" value="0" class="field_selector"  /><label class="selector_label" for="field_disableNet">关闭</label>
                                    </p>
                                </div> 
                                <div id="netEnabledSection">
                                    <div class="section">
                                        <h3>SSID 名称</h3>
                                        <p class="p5">
                                            WiFi 网络的名称。支持中英文，中间不能有空格。
                                        </p>
                                        <p class="p10">
                                        	<!--
                                            Note: autocomplete for "ssid" input is important: its value wont be auto set to the value 
                                            which linked to "sel_ssidPolicy" on window reload, so that "manualSsid" get the right val.
                                            -->
                                            <span><input name="ssid" id="field_ssid" class="field" value="${ssid.name}" autocomplete="off" /> </span> <span class="desc">例： 广州酒家-上下九店</span>
                                        </p>
                                        <p class="p10">
                                           <input type="checkbox" name="useSsidPolicy" class="field_selector" id="field_useSsidPolicy" value="1" /><label class="selector_label" for="field_useSsidPolicy">使用 SSID 策略 <span class="soof_need_tooltip" title="SSID 名称在策略里定义，当更新某一个策略的 SSID 名称时，所有应用该策略的设备的 SSID 名称都同步更新。"><i class="soof_icon help"></i></span></label>
                                        </p>
                                        <div id="section_useSsidPolicy" style="display:none;">
                                            <p class="p10">
                                                <select id="sel_ssidPolicy" name="codeId" class="field">
                                                    <c:forEach items="${scds}" var="scd">  
                                                        <option value="${scd.id}" ${ssid.codeId==scd.id?"selected":""} data-ssid="${scd.ssid}">${scd.name}</option>
                                                    </c:forEach> 
                                                </select>
                                                <a id="btn_addSsidPolicy" href="javascript:void(0);" class="btn">新建 SSID 策略</a>
                                            </p>
                                        </div>
                                    </div> 
                                    <div class="section">
                                        <h3>认证策略</h3>
                                        <p class="p5">
                                            设置用户访问您的 WiFi 时的认证策略：如是否需要认证，认证方式等。
                                        </p>
                                        <p class="p10">
                                            <select id="sel_authPolicy" name="policyId" class="field">                                            
                                                <option value="-1" >不修改</option>
                                                <c:forEach items="${policies}" var="p">
                                                    <option value="${p.id}">${p.name}</option>
                                                </c:forEach>
                                            </select> 
                                            <a id="btn_openAuthPolicy" href="javascript:void(0);" class="btn">查看所选</a>&nbsp;&nbsp;
                                            <a id="btn_addAuthPolicy" href="javascript:void(0);" class="btn">新建认证策略</a>
                                        </p>
                                    </div> 
                                    <div class="section">
                                        <h3>Portal</h3>
                                        <p class="p5">
                                            设置用户访问您的 WiFi 时强制弹出的门户网站。一个 Portal 通常包含了欢迎页，认证前页，认证后页等。
                                        </p>
                                        <p class="p10">
                                            <select id="sel_portal" name="portalId" class="field">
                                                <option value="-1" >不修改</option>
                                                <c:forEach items="${portals}" var="p">
                                                    <option value="${p.id}" >${p.name}</option>
                                                </c:forEach>
                                            </select> 
                                            <a id="btn_openPortal" href="javascript:void(0);" class="btn">查看所选</a>&nbsp;&nbsp;
                                            <a id="btn_addPortal" href="javascript:void(0);" class="btn">新建 Portal</a>
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