﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑 WiFi 网络</title>
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
				var url = "${base}cpe/updateRosSsid.do?batchMode=1",
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
	    var mainForm = document.mainForm;
			
		if (rlx.mti.validate(mainForm)) {
			oprDirect.invoke("editSsid", {
				beforeSend : setAjaxStartView,
				success : function(result){					
					alert("操作成功！");
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
        	<h1>编辑 WiFi 网络</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${id}" />
                                <div class="section first">
                                	编辑 ${deviceNum}个设备的网络配置。
                                </div>
                                
                                <div id="netEnabledSection">                                   
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
                                            <a id="btn_openAuthPolicy" href="javascript:void(0);" class="btn">查看所选</a>
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