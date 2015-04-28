﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
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
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.data_fields .cell{ 
	padding:6px 12px;
	text-align:left;
}
.data_fields th.cell{
	width:120px;
	padding-left:0;
}
</style>
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>批量配置  ${deviceNum} 个设备</h1>
            <jsp:include page="/cpe/device/tabs.jsp" >
                 <jsp:param name="tab" value="base" />
                 <jsp:param name="batchMode" value="1" />
            </jsp:include>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <div class="section first">
                                <p class="p5" style="float:right;">
                                    <img src="${base}resources/image/default/entity/${host.image}" />
                                </p>
                                <div class="p5" style="margin-right:200px;">
                                    <table cellspacing="0" cellpadding="0" border="0" class="data_fields">
                                        <tbody>
	                                        <tr>
	                                            <th class="cell">产品类型</th>
	                                            <td class="cell">${productClass}</td>
	                                        </tr>
	                                        <tr>
	                                            <th class="cell">产品型号</th>
	                                            <td class="cell">${productModel}</td>
	                                        </tr>
	                                        <tr>
	                                            <th class="cell">生产厂家</th>
	                                            <td class="cell">${producter}</td>
	                                        </tr>
	                                        <tr>
	                                            <th class="cell">产品描述</th>
	                                            <td class="cell">${productDescr}</td>
	                                        </tr>	                                        
                                        </tbody>
                                    </table>
                                </div>
                                <div class="clearer"></div>
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