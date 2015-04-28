﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>认证策略【${dto.name}】</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<c:if test="${dto.redirect == 1}">
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"open.jquery.jquery"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery");
	var urlRe = /(\w+):\/\/([^/:]+)(?::(\d*))?/;
    
	//rl.console.showOnReady();
	rl.onReady(function() {
		//selectors behaviours
		function updateXPortalSection(){
			var enable = jQuery("input[name='cmcc']:checked").val() == "1",
				xportalFields = "input[name='portalIP'], input[name='portalPort'], input[name='portalUrl']";
			
			//rl.debug(this + 'updateXPortalSection(): enable = ' + enable + '');
			jQuery("#xPortalSection")
				.css("display", (enable ? "block" : "none"));
			
			jQuery(xportalFields)
				.prop("disabled", !enable);
			
		}
		jQuery("#disableXPortalField, #enableXPortalField").on("click", updateXPortalSection);
		updateXPortalSection();
		
		function fillDefaults(){
			var url = jQuery("input[name='portalUrl']").val(),
			    ip = jQuery("input[name='portalIP']").val(),
				m = url.match(urlRe);			
			if(ip == '' && m){
				var ip = m[2],port = m[3] || 80;				
				jQuery("input[name='portalIP']").val(ip);
				jQuery("input[name='portalPort']").val(port);
			}
		}
		jQuery("input[name='portalUrl']")
			.on("change keyup", fillDefaults);		
		fillDefaults();		
	}); 

	function saveForm() {
		var mainForm = document.mainForm;		
		if (rlx.mti.validate(mainForm)) {
			mainForm.action = "${base}portal/policySaveOrUpdate.do";
			rlx.mti.ajaxSubmit("mainForm");
		}		
		return false;
	}
	
	window.saveForm = saveForm;	
});

</script>
</c:if>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>认证策略【${dto.name}】</h1>
            <div class="nav">
                <ul class="list">
                    <li><a href="${base}portal/policyReadyEditAuth.do?id=${dto.id}">基本设置</a></li>
                    <li class='active'><a href="javascript:void(0);">高级设置</a></li>
                </ul>
            </div>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${dto.id}" />
                                <input type="hidden" name="tag" value="2" />
                                <c:if test="${dto.redirect == 1}">
                                    <div class="section first">
                                        <h3>第三方 Portal</h3>
                                        <p class="p5">
                                            配置符合中国移动 WLAN 业务 PORTAL 协议规范的第三方 Portal。注意：开启第三方 Portal 后，WiFi 用户的认证管理将由该 Portal 系统接管，即 WiFi 用户上网的认证页面、认证方式、跳转自定义等均由该 Portal 代管，您需要登录该系统才能进行相关设置。
                                        </p>
                                        <p class="p10">
                                            <input type="radio" name="cmcc" class="field_selector" id="disableXPortalField" value="0" ${dto.cmcc==0?"checked":""}/><label class="selector_label" for="disableXPortalField">关闭（默认）</label>&nbsp;&nbsp;
                                            <input type="radio" name="cmcc" class="field_selector" id="enableXPortalField" value="1" ${dto.cmcc==1?"checked":""} /><label class="selector_label" for="enableXPortalField">开启</label>
                                        </p>
                                        <div class="p10" id="xPortalSection" style="display:none;">
                                            <div class="fields_box">
                                                <p>
                                                    Portal 服务器 URL
                                                </p>
                                                <p class="p5">
                                                    <span><input name="portalUrl" class="field l" dataType="Custom" regexp="^http(s)?:\/\/.*?$" value="${dto.portalUrl}" /> <font color="red">*</font> </span> <span class="desc">(URL必须以 http:// 或 https:// 开头)</span>
                                                </p>
                                                <p class="p10">
                                                    Portal 服务器IP
                                                </p>
                                                <p class="p5">
                                                    <span><input name="portalIP" class="field s" value="${dto.portalIP}" /> <font color="red">*</font> </span> <span class="desc">(<font color='red'>该IP 必须放在设备的白名单中</font>)</span>
                                                </p>
                                                <p class="p10">
                                                    Portal 服务器端口号
                                                </p>
                                                <p class="p5">
                                                    <span><input name="portalPort" class="field s" dataType="Number" value="${dto.portalPort}" /> <font color="red">*</font> </span> <span class="desc">(必须与 URL 中的端口号一致，如 URL 中无端口号，则默认为 80)</span>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="soof_action_bar">
                                        <button type="button" class="btn primary" onclick="saveForm()">保 存</button>
                                        <button class="btn white" onclick="rl.page.close()">取消</button>
                                    </div>
                                </c:if>
                                <c:if test="${dto.redirect == 0}">
                                    <div class="section first">
                                    当前 Portal 已关闭，请在<a href="${base}portal/policyReadyEditAuth.do?id=${dto.id}">【基本设置】</a>中开启后再继续高级设置。
                                    </div>
                                    <div class="soof_action_bar">
                                        <button class="btn white" onclick="rl.page.close()">取消</button>
                                    </div>
                                </c:if>
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