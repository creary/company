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
		var url = rl.format("${base}cpe/readyEditSsid.do?id=${id}&ife={0}", arguments);		
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
        	<h1>设备配置【${host.name}】</h1>
            <jsp:include page="/cpe/device/tabs.jsp" >
                 <jsp:param name="tab" value="operation" />
                 <jsp:param name="batchMode" value="0" />
            </jsp:include>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <div class="section first">
                                <p class="p5">
                                    设置您的 WiFi 网络的 SSID （即网络名称）和 LAN口，认证策略和 Portal。当前设备支持多个 SSID，即您可以为一个设备设置多个 WiFi 网络，并为其设置不同的认证策略和 Portal，以实现用户的差异化管理。
                                </p>
                                <div class="clearer"></div>
                                <div class="sections_box p10">
                                    <c:forEach items="${ssids}" var="ssid" varStatus="item">
                                        <div class="section <c:if test="${item.index==0}">first</c:if>">
                                            <table class="rich_row <c:if test="${ssid.enable==0}">disabled</c:if>" border="0" cellspacing="0" cellpadding="0">
	                                            <tr>
	                                                <td class="icon_ctn">
	                                                    <c:if test="${ssid.ife==0||ssid.ife==1}">
	                                                        <i class="soof_icon wifi_rf_2_4g <c:if test="${ssid.enable==0}"> disabled</c:if>"></i>
	                                                    </c:if>
	                                                    <c:if test="${ssid.ife==2||ssid.ife==3}">
	                                                        <i class="soof_icon wifi_rf_5_8g <c:if test="${ssid.enable==0}"> disabled</c:if>"></i>
	                                                    </c:if>
	                                                    <c:if test="${ssid.ife==255}">
	                                                        <i class="soof_icon net_lan <c:if test="${ssid.enable==0}"> disabled</c:if>"></i>
	                                                    </c:if>
	                                                </td>
	                                                <td class="detail_ctn">
	                                                    <c:if test="${ssid.ife < 4}">
	                                                        SSID&nbsp;${ssid.ife + 1}&nbsp;<c:if test="${ssid.enable==1}">名称: ${ssid.name}</c:if><br />  	                                                                                                                                                                                             	                                                        
	                                                    </c:if>    
	                                                    <c:if test="${ssid.ife==255}">
	                                                        LAN<br />
	                                                    </c:if>
	                                                    <c:if test="${ssid.enable==0}">(未启用)</c:if>
	                                                    <c:if test="${ssid.enable==1}">
	                                                                                                                                            认证策略: <a href="javascript:void(0);" title="查看" onclick="rl.page.open('${base}portal/policyReadyEditAuth.do?id=${ssid.policyId}', 'authPolicyDetail', rl.gui.winDlgOpt)">${ssid.policyName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
		                                                    Portal: <a href="javascript:void(0);" title="查看" onclick="rl.page.open('${base}portal/${ssid.portalId}/global/edit.do', 'portalDetail', rl.gui.winDlgOpt)">${ssid.portalName}</a>
	                                                    </c:if>
	                                                </td>
	                                                <td class="action_ctn">
	                                                    <button class="btn white" onclick="editNetwork('${ssid.ife}')">修改配置</button>
	                                                </td>
	                                            </tr>
	                                        </table>
	                                    </div>
                                    </c:forEach>                                    
                                </div>
                                <p class="p5 desc">
                                    <c:if test="${!has5G}">当前设备为 2.4G 单射频，支持 2 个 SSID。</c:if>
                                    <c:if test="${has5G}">当前设备为 2.4G & 5.8G 双射频，支持 4 个 SSID。</c:if>                                
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