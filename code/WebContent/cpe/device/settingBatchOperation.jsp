﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
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
	
	function openObservedDlg(){
		var dlg = rl.page.open.apply(rl.page, arguments);
		dlg.on("close", function(){
			if(deviceDataObserver.changed){
				location.reload();
			}
		});
	}
	
	function editNetwork(networkId){
		var url = rl.format("${base}cpe/readyEditSsid.do?id=${id}&ife={0}&batchMode=1", arguments);			
		openObservedDlg(url, "cpe.readyEditSsid", rl.gui.winDlgOpt);
	}
	
	window.editNetwork = editNetwork;
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
                 <jsp:param name="tab" value="operation" />
                 <jsp:param name="batchMode" value="1" />
            </jsp:include>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <div class="section first">
                                <p class="p5">
                                    设置您的 WiFi 网络的 SSID （即网络名称），认证策略和 Portal。所选设备支持多个 SSID，即您可以为所选设备设置多个 WiFi 网络，并为其设置不同的认证策略和 Portal，以实现用户的差异化管理。
                                </p>
                                <div class="clearer"></div>
                                <div class="sections_box p10">
                                    <div class="section first">
                                        <table class="rich_row" border="0" cellspacing="0" cellpadding="0">
                                           <tr>
                                             <td class="icon_ctn"><i class="soof_icon wifi_rf_2_4g"></i></td>
                                             <td class="detail_ctn">SSID 0<br /></td>
                                             <td class="action_ctn"><button class="btn white" onclick="editNetwork('0')">修改配置</button></td>
                                           </tr>
                                        </table>
                                    </div>
                                    <div class="section">
                                        <table class="rich_row" border="0" cellspacing="0" cellpadding="0">
                                           <tr>
                                             <td class="icon_ctn"><i class="soof_icon wifi_rf_2_4g"></i></td>
                                             <td class="detail_ctn">SSID 1<br /></td>
                                             <td class="action_ctn"><button class="btn white" onclick="editNetwork('1')">修改配置</button></td>
                                           </tr>
                                        </table>
                                    </div>
                                    <div class="section">
                                        <table class="rich_row" border="0" cellspacing="0" cellpadding="0">
                                           <tr>
                                             <td class="icon_ctn"><i class="soof_icon net_lan"></i></td>
                                             <td class="detail_ctn">LAN<br /></td>
                                             <td class="action_ctn"><button class="btn white" onclick="editNetwork('255')">修改配置</button></td>
                                           </tr>
                                        </table>
                                    </div>
                                    <c:if test="${has5G}">
	                                    <div class="section">
	                                        <table class="rich_row" border="0" cellspacing="0" cellpadding="0">
	                                           <tr>
	                                             <td class="icon_ctn"><i class="soof_icon wifi_rf_5_8g"></i></td>
	                                             <td class="detail_ctn">SSID 2<br /></td>
	                                             <td class="action_ctn"><button class="btn white" onclick="editNetwork('2')">修改配置</button></td>
	                                           </tr>
	                                        </table>
	                                    </div>
	                                    <div class="section">
	                                        <table class="rich_row" border="0" cellspacing="0" cellpadding="0">
	                                           <tr>
	                                             <td class="icon_ctn"><i class="soof_icon wifi_rf_5_8g"></i></td>
	                                             <td class="detail_ctn">SSID 3<br /></td>
	                                             <td class="action_ctn"><button class="btn white" onclick="editNetwork('3')">修改配置</button></td>
	                                           </tr>
	                                        </table>
	                                    </div>
                                    </c:if>
                                </div>
                                <p class="p5 desc">
                                    <c:if test="${!has5G}">所选设备为 2.4G 单射频，支持 2 个 SSID。</c:if>
                                    <c:if test="${has5G}">所选设备为 2.4G & 5.8G 双射频，支持 4 个 SSID。</c:if>
                                </p>
                            </div>
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