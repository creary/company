﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信认证</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
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
		K = orderjs("open.kindeditor.kindeditor"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect"),
		SectionSwitcher = orderjs("cloudac:SectionSwitcher");
	
	jQuery.ajaxSetup({
		dataType : "json",
		method : "post",
		cache : false
	});
	
	var mainDirect = new JqueryAjaxDirect({
		services : {
			save : function(options){
				var url = "${base}admin/saveWechatBase.do?branchId=${branchId}",
					data = jQuery("#mainForm").serialize();				
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			}
		}
	});
	
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
			mainDirect.invoke("save", {
				beforeSend : setAjaxStartView,
				success : function(){
					alert("保存成功！");
				},
				complete : setAjaxEndView
			});
		}
	}
	
	rl.onReady(function(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
		var ss_itfUsed = new SectionSwitcher({
			iniActive : jQuery("input[name='itfUsed']:checked").val(),
			map : {
				"0" : "#section_itfUsed_0",
				"1" : "#section_itfUsed_1"
			}
		});
		jQuery("input[name='itfUsed']")
			.on("click", function(){
				ss_itfUsed.setActive(this.value);
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
        	<h1>微信认证</h1>
            <div class="nav">
                <ul class="list">
                    <li class='active'><a href="javascript:void(0);">公众号对接</a></li>
                    <li><a href="${base}admin/readyWechatAuth.do?branchId=${branchId}">认证配置</a></li>
                </ul>
            </div>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <div class="section first">
                                    <h3>关于公众号对接</h3>
                                    <p class="p5">
                                    	本平台需要与微信公众平台对接才能实现微信认证和群发微信等功能。请在本页面填写您微信公众号的基本信息。
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>授权信息</h3>
                                    <p class="p5">
                                    	填写您公众号的授权相关信息，请参考以下步骤进行配置：
                                        <ol class="p5">
                                            <li>登录微信公众平台的管理界面。 <a href="https://mp.weixin.qq.com/" target="_blank">点击此处去登录&gt;&gt;</a></li>
                                            <li>在微信平台主界面左侧菜单中查找并打开【公众号设置】和【开发者中心】页面，查找公众号的以下信息，并将其填写到此处。找不到信息在哪？<a href="${base}acs/help/wechatAuthSetting_fields.jsp" target="_blank">点此查看图示帮助&gt;&gt;</a></li>
                                        </ol>
                                    </p>
                                    <div class="sections_box">
                                        <div class="section first">
                                            <h5>微信公众号</h5>
                                            <p class="p5">
                                                <input class="field" name="publicName" dataType="Require" value="${dto.publicName}"/> <font color="red">*</font>
                                            </p>
                                        </div>
                                        <div class="section first">
                                            <h5>微信 App ID</h5>
                                            <p class="p5">
                                                <input class="field" name="appId" dataType="Require" value="${dto.appId}"/> <font color="red">*</font>
                                            </p>
                                        </div>
                                        <div class="section">
                                            <h5>微信 Secret</h5>
                                            <p class="p5">
                                                <input class="field" name="appSecret" dataType="Require" value="${dto.appSecret}"/> <font color="red">*</font>
                                            </p>
                                        </div>
                                        <div class="section">
                                            <h5>微信原始 ID</h5>
                                            <p class="p5">
                                                <input class="field" name="openId" dataType="Require" value="${dto.openId}"/> <font color="red">*</font>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="section">
                                    <h3>配置开发接口</h3>
                                    <p class="p5">
                                    	在微信公众平台配置的开发接口（即服务器配置）。由于基于微信公众平台开发的第三方公司众多，您的开发接口可能已被第三方占用，请根据实际情况进行选择。
                                    </p>
                                    <p class="p10">
                                        <input type="radio" name="itfUsed" class="field_selector" id="field_itfUsed_0" value="0" checked="checked" /><label class="selector_label" for="field_itfUsed_0">开发接口未被占用</label>&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="itfUsed" class="field_selector" id="field_itfUsed_1" value="1" /><label class="selector_label" for="field_itfUsed_1">开发接口已被占用</label>
                                    </p>
                                    <div class="fields_box">
                                        <div id="section_itfUsed_0" class="switchable_section">
                                            <p>在公众平台主界面的【开发者中心】&gt;【服务器配置】中，填写以下配置。找不到在哪配置？<a href="${base}acs/help/wechatAuthSetting_itf.jsp" target="_blank">点此查看图示帮助&gt;&gt;</a>
                                            </p>
                                            <p class="p10"><label class="width_label s" for="field_connectUrl">URL：</label><input class="field" value="${acsURL}acs/wechat.do" id="field_connectUrl" readonly="readonly" /></p>
                                            <p class="p10"><label class="width_label s" for="field_connectToken">Token：</label><input class="field" id="field_connectToken" value="mywifi" readonly="readonly" /></p>
                                        </div>
                                        <div id="section_itfUsed_1" class="switchable_section">
                                            <p>请让您的微信开发者根据以下说明文档对代码进行调整:</p>
                                            <div class="p10">
                                                <h5>响应用户的关注事件。</h5>
                                                <p class="p5">
                                                    1. 回复一个外链图片，即该图片 src 必须是商家外网 URL，且该 URL 的域名必须不在商家设备的域名白名单中。<br />
                                                    2. 外链图片的 URL 还应携带以下参数: wechatOpenId={openid}其中， {openid} 为粉丝对商家的 OpenId。
                                                </p>
                                            </div>
                                            <div class="p10">
                                                <p>如代码无法调整，那么本平台支持的微信认证的 3 种方式（扫一扫上网，一键上网和回复即上网）只有“一键上网”可用。
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="soof_action_bar">
                                    <button id="btn_action" type="button" class="btn primary">保 存</button>
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