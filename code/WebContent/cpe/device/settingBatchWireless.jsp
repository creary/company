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
	"x:mti.Validator",
	"open.jquery.jquery",
    "open.jQuery-idTabs.idTabs-ordered",
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
			doCommand : function(options, command){
				var url = rl.format("${base}cpe/doAction.do?command={1}", arguments),
					data = jQuery("#mainForm").serialize();
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			}
		}
	});
	
	function openReboot(){
		var url = "${base}cpe/readyReboot.do?autoStart=1&id=${id}&batchMode=1";		
		openObservedDlg(url, "cpe.readyReboot", rl.gui.winDlgOpt);
	}
	
	function openObservedDlg(){
		var dlg = rl.page.open.apply(rl.page, arguments);
		dlg.on("close", function(){
			if(deviceDataObserver.changed){
				location.reload();
			}
		});
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
	
	function doAction() {
	    var mainForm = document.mainForm;
			
		if (rlx.mti.validate(mainForm)) {
			oprDirect.invoke("doCommand", {
				beforeSend : setAjaxStartView,
				success : function(result){
					if(rl.isObject(result) && result.needRestart){
						if(confirm("参数设置已完成，需要重启设备才能生效，是否立即重启？")) openReboot();
					}
					else{
						alert("参数设置成功！");
					}
					
					notifyDeviceChangeToOpener();
				},
				complete : setAjaxEndView
			}, "setParameterValues");
		}
		else{//show first err tab
			jQuery(".jquery_idtabs .tbody")
				.each(function(i){
					//rl.dir(this);
					var vErrs = jQuery(this).find(".rl_span_error");
					rl.debug(this + ' vErrs.length = ' + vErrs.length + '');
					if(vErrs.length){
						var tbodyId = this.id,
							tab = jQuery(".jquery_idtabs a[href='#" + tbodyId + "']");
						
						if(tab.length){
							//select tab by tbodyId.
							tab.trigger("click");
							//focus on the error input
							rl.defer(function(){
								vErrs.first().parent().find("input.field[dataType]").focus();
							});
						}
						else{
							rl.debug(this + ' Cant find corr tab for tbody id = ' + tbodyId + '', "err");
						}						
						return false;
					}
				});
		}
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
	
    rl.onReady(function(){
		jQuery(".jquery_idtabs").idTabs();
		jQuery("#btn_action").on("click", doAction);
    });
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${jsPath}rl/src/open/jQuery-idTabs/jquery_idtabs.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
            <h1>批量配置 ${deviceNum} 个设备</h1>
            <jsp:include page="/cpe/device/tabs.jsp" >
                 <jsp:param name="tab" value="device" />
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
                                    <h3>无线参数</h3>
                                    <p class="p5">无线参数包括通用参数和射频相关参数。</p>
                                    <div class="p10">
                                        <div class="jquery_idtabs tbox">
                                            <ul class="tabbar">
                                                <li><a class="selected" href="#tbox_tab1">通用参数</a></li>
                                                <li><a href="#tbox_tab2">2.4G 参数</a></li>
                                                <c:if test="${has5G}"> 
                                                    <li><a href="#tbox_tab3">5.8G 参数</a></li>
                                                </c:if>
                                            </ul>
                                            <div class="tbody" id="tbox_tab1" style="display:block;">
                                                
                                                <div class="section first">
                                                    <h5>微信放行</h5>
                                                    <p class="p5">开启后无需认证即可使用微信与 QQ 发送信息等基本功能，方便用户关注公众号。</p>
                                                    <p class="p10">
                                                        <input type="radio" name="weixing_pass" class="field_selector" id="wechatFreeOnField" value="NULL" checked/>
                                                        <label class="selector_label" for="wechatFreeOnField">不修改</label>&nbsp;&nbsp;
                                                        <input type="radio" name="weixing_pass" class="field_selector" id="wechatFreeOffField" value="0" />
                                                        <label class="selector_label" for="wechatFreeOffField">关闭（默认）</label>
                                                        <input type="radio" name="weixing_pass" class="field_selector" id="wechatFreeOnField" value="1" />
                                                        <label class="selector_label" for="wechatFreeOnField">放行，但禁用朋友圈</label>&nbsp;&nbsp;
                                                        <input type="radio" name="weixing_pass" class="field_selector" id="wechatFreeOnField" value="2" />
                                                        <label class="selector_label" for="wechatFreeOnField">完全放行</label>&nbsp;&nbsp;
                                                        <input type="radio" name="weixing_pass" class="field_selector" id="wechatFreeOnField" value="3" />
                                                        <label class="selector_label" for="wechatFreeOnField">完全放行 + 自动跳转</label>&nbsp;&nbsp;
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>AC 无效时开放 WiFi</h5>
                                                    <p class="p5">当 AC 不在线时，关闭 WiFi 或直接放行（用户连接 WiFi 即可上网）。</p>
                                                    <p class="p10">
                                                        <input type="radio" name="free_no_auth" class="field_selector" id="turnOnWifiOfAcDownField" value="NULL" checked/><label class="selector_label" for="turnOnWifiOfAcDownField">不修改</label>&nbsp;&nbsp;
                                                        <input type="radio" name="free_no_auth" class="field_selector" id="turnOnWifiOfAcDownField" value="1" /><label class="selector_label" for="turnOnWifiOfAcDownField">直接放行</label>&nbsp;&nbsp;
                                                        <input type="radio" name="free_no_auth" class="field_selector" id="turnOffWifiOfAcDownField" value="0" /><label class="selector_label" for="turnOffWifiOfAcDownField">关闭 WiFi（默认）</label>
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>白名单</h5>
                                                    <p class="p5">用户访问以下IP地址或域名时，无需认证。支持 3 个IP地址或域名。如果是域名，必须以/结尾，例：www.baidu.com/ ，无需输入http://。</p>
                                                    <p class="p10">
                                                        <input name="white_list1" class="field s" />&nbsp;&nbsp;&nbsp;
                                                        <input name="white_list2" class="field s" />&nbsp;&nbsp;&nbsp;
                                                        <input name="white_list3" class="field s" />
                                                    </p>
                                                </div>                                                                                              
                                            </div>
                                            <div class="tbody" id="tbox_tab2">
                                                <div class="section first">
                                                    <h5>最大并发连接数</h5>
                                                    <p class="p5">无线终端最大并发连接数，当连接的终端数超过此值时，后续连接的终端将被自动切换至其它 AP。</p>
                                                    <p class="p10">
                                                        <select name="MaxStaNum" class="field s">
	                                                        <option value="">不修改</option>
	                                                        <option value="16">16</option>
	                                                        <option value="32">32</option>
	                                                        <option value="64">64</option>
                                                        </select>
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>接入阀值</h5>
                                                    <p class="p5">移动终端在设定的信号强度以内才允许接入。</p>
                                                    <p class="p10">
                                                        - <input name="AssocReqRssiThres" class="field s" /> dbm
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>认证阀值</h5>
                                                    <p class="p5">移动终端在设定的信号强度以内才允许认证。</p>
                                                    <p class="p10">
                                                        - <input name="AuthRssiThres" class="field s" /> dbm
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>漫游阀值</h5>
                                                    <p class="p5">移动终端在设定的信号强度以外才允许漫游到另一个接入点。</p>
                                                    <p class="p10">
                                                        - <input name="KickStaRssiLow" class="field s" /> dbm
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>无线信道</h5>
                                                    <p class="p5">通信的通道，信号传输的媒介。</p>
                                                    <p class="p10">
                                                        <select name="wireless_channel" class="field s">
                                                            <option value="">不修改</option>
                                                            <option value="auto">自动</option>
                                                            <option value="1">1</option>
                                                            <option value="2">2</option>
                                                            <option value="3">3</option>
                                                            <option value="4">4</option>
                                                            <option value="5">5</option>
                                                            <option value="6">6</option>
                                                            <option value="7">7</option>
                                                            <option value="8">8</option>
                                                            <option value="9">9</option>
                                                            <option value="10">10</option>
                                                            <option value="11">11</option>
                                                        </select>
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>无线输出功率</h5>
                                                    <p class="p5">发射信号的强度，即最大功率的百分比。</p>
                                                    <p class="p10">
                                                        <select name="wireless_tx_power" class="field s">
                                                            <option value="">不修改</option>
                                                            <option value="1">1</option>
                                                            <option value="10">10</option>
                                                            <option value="20">20</option>
                                                            <option value="30">30</option>
                                                            <option value="40">40</option>
                                                            <option value="50">50</option>
                                                            <option value="60">60</option>
                                                            <option value="70">70</option>
                                                            <option value="80">80</option>
                                                            <option value="90">90</option>
                                                            <option value="100">100</option>
                                                        </select> %
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>无线终端隔离</h5>
                                                    <p class="p5">无线终端之间完全隔离，只能访问AP接入的固定网络。</p>
                                                    <p class="p10">
                                                        <input type="radio" name="wireless_terminal_isolate" class="field_selector" id="terminalIsolateOffField" value="" checked/><label class="selector_label" for="terminalIsolateOffField">不修改</label>&nbsp;&nbsp;
                                                        <input type="radio" name="wireless_terminal_isolate" class="field_selector" id="terminalIsolateOffField" value="0"/><label class="selector_label" for="terminalIsolateOffField">关闭（默认）</label>&nbsp;&nbsp;
                                                        <input type="radio" name="wireless_terminal_isolate" class="field_selector" id="terminalIsolateOnField" value="1"/><label class="selector_label" for="terminalIsolateOnField">开启</label>
                                                    </p>
                                                </div>
                                            </div>
                                            <c:if test="${has5G}"> 
                                            <div class="tbody" id="tbox_tab3">                                                
                                                <div class="section first">
                                                    <h5>最大并发连接数</h5>
                                                    <p class="p5">无线终端最大并发连接数，当连接的终端数超过此值时，后续连接的终端将被自动切换至其它 AP。</p>
                                                    <p class="p10">
                                                        <select name="MaxStaNum_5G" class="field s">
	                                                        <option value="">不修改</option>
	                                                        <option value="16">16</option>
	                                                        <option value="32">32</option>
	                                                        <option value="64">64</option>
                                                        </select>
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>漫游阀值</h5>
                                                    <p class="p5">移动终端在设定的信号强度以外才允许漫游到另一个接入点。</p>
                                                    <p class="p10">
                                                        - <input name="KickStaRssiLow_5G" dataType="Number" class="field s" /> dbm
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>无线信道</h5>
                                                    <p class="p5">通信的通道，信号传输的媒介。</p>
                                                    <p class="p10">
                                                        <select name="wireless_channel_5G" class="field s">
                                                            <option value="">不修改</option>
                                                            <option value="36">36(5.180 GHz)</option>
                                                            <option value="40">40(5.200 GHz)</option>
                                                            <option value="44">44(5.220 GHz)</option>
                                                            <option value="48">48(5.240 GHz)</option>
                                                            <option value="149">149(5.745 GHz)</option>
                                                            <option value="153">153(5.765 GHz)</option>
                                                            <option value="157">157(5.785 GHz)</option>
                                                            <option value="161">161(5.805 GHz)</option>
                                                            <option value="165">165(5.825 GHz)</option>
                                                        </select>
                                                    </p>
                                                </div>
                                                <div class="section">
                                                    <h5>无线输出功率</h5>
                                                    <p class="p5">发射信号的强度，即最大功率的百分比。所选设备最大功率为 20 dBm。</p>
                                                    <p class="p10">
                                                        <select name="wireless_tx_power_5G" class="field s">
                                                            <option value="">不修改</option>
                                                            <option value="1">1</option>
                                                            <option value="10">10</option>
                                                            <option value="20">20</option>
                                                            <option value="30">30</option>
                                                            <option value="40">40</option>
                                                            <option value="50">50</option>
                                                            <option value="60">60</option>
                                                            <option value="70">70</option>
                                                            <option value="80">80</option>
                                                            <option value="90">90</option>
                                                            <option value="100">100</option>
                                                        </select> %
                                                    </p>
                                                </div>
                                            </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="soof_action_bar">
                                    <button type="button" id="btn_action" class="btn primary" autocomplete="off">保 存</button>
                                    <button type="button" class="btn white" onclick="rl.page.close()">取消</button>
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