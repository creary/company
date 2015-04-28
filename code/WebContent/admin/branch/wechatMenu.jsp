﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信公众号菜单配置</title>
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
	//rl.console.showOnReady();
	var jQuery = orderjs("open.jquery.jquery"),
		K = orderjs("open.kindeditor.kindeditor"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect"),
		SectionSwitcher = orderjs("cloudac:SectionSwitcher");
	
	jQuery.ajaxSetup({
		dataType : "json",
		method : "post",
		cache : false
	});
	
	var urlRe = /^http(s)?:\/\/.*?$/;
	var mainDirect = new JqueryAjaxDirect({
		services : {
			createWechatMenu : function(options){
				var url = "${base}admin/createWechatMenu.do?branchId=${branchId}&type=view",
					data = jQuery("#mainForm").serialize();
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			}
		}
	});
	
	function setAjaxStartView(){
		jQuery("#btn_createWechatMenu")
			.prop("disabled", true)
			.addClass("disabled")
			.text("正在生成菜单...");
	}
	function setAjaxEndView(){
		jQuery("#btn_createWechatMenu")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("一键生成菜单");
	}
	
	function createWechatMenu() {
		var mainForm = document.mainForm;		
		if (rlx.mti.validate(mainForm)) {
			if(confirm("确定生成微信菜单？如您的公众号菜单已存在，将被替换!")){
				mainDirect.invoke("createWechatMenu", {
					beforeSend : setAjaxStartView,
					success : function(){
						alert("生成菜单成功！");
					},
					complete : setAjaxEndView
				});
			}
		}
	}
	
	rl.onReady(function(){
		jQuery("#btn_createWechatMenu")
			.prop("disabled", false)
			.on("click", createWechatMenu);
		
		var ss_menuUsed = new SectionSwitcher({
			iniActive : jQuery("input[name='menuUsed']:checked").val(),
			map : {
				"0" : "#section_menuUsed_0",
				"1" : "#section_menuUsed_1"
			}
		});
		jQuery("input[name='menuUsed']")
			.on("click", function(){
				ss_menuUsed.setActive(this.value);
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
        	<h1>微信公众号菜单配置</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <div class="section first">
                                    <h3>公众号菜单</h3>
                                    <p class="p5">
                                    	为微信公众号菜单添加“一键上网”菜单项。如您的公众号菜单已被启用（如已在微信后台配置了公众号菜单，或者菜单已被第三方开发者启用），您可以在原有菜单中添加“一键上网”菜单项；如尚未被启用，则可以一键生成微信菜单。请根据您公众号的菜单的实际情况进行操作：
                                    </p>
                                    <p class="p10">
		                                <input type="radio" name="menuUsed" class="field_selector" id="field_menuUsed_1" value="1" checked="checked" /><label class="selector_label" for="field_menuUsed_1">在原有菜单中添加“一键上网”菜单项</label>&nbsp;&nbsp;&nbsp;
                                        <input type="radio" name="menuUsed" class="field_selector" id="field_menuUsed_0" value="0" ${dto.menuUsed=="0"?"checked":""} /><label class="selector_label" for="field_menuUsed_0">一键生成微信菜单（该菜单只有一个菜单项：一键上网）</label>
                                    </p>
                                    <div class="fields_box">
                                        <div id="section_menuUsed_1" class="switchable_section">
                                            <p>请参考以下步骤进行添加:</p>
                                            <ol class="p5">
                                                <li>登录微信公众平台的管理界面。 <a href="https://mp.weixin.qq.com/" target="_blank">点击此处去登录&gt;&gt;</a></li>
                                                <li>点击公众平台主界面的【功能】&gt;【自定义菜单】。</li>
                                                <li>添加菜单项“一键上网”，设置动作为“跳转到网页。</li>
                                                <li>网页链接设置为任意外网网址，且该网址中的域名必须不在商家设备的域名白名单中；另外，网址还应携带以下参数: wechatOpenId=public 。例如，您可以填写：http://www.baidu.com/?wechatOpenId=public ，其中 www.baidu.com 不能在设备的域名白名单，否则用户无法完成验证。</li>
                                            </ol>
                                        </div>
                                        <div id="section_menuUsed_0" class="switchable_section">
                                        	<p><b>跳转链接</b></p>
                                            <p class="p5">即用户点击“一键上网”后打开的页面链接，请设置为任意外网网址，且该网址中的域名必须不在商家设备的域名白名单中；另外，网址还应携带以下参数: wechatOpenId=public 。例如，您可以填写：http://www.baidu.com/?wechatOpenId=public ，其中 www.baidu.com 不能在设备的域名白名单，否则用户无法完成验证。</p>
                                            <p class="p10">
                                            	<span><input class="field" dataType="Custom" regexp="^http(s)?:\/\/.+?$" name="jumpToUrl" /> <font color="red">*</font></span> <span class="desc">(链接必须以 http:// 或 https:// 开头)</span>
                                            </p>
                                            <p style="padding:2em 0;">
                                            	<button id="btn_createWechatMenu" type="button" class="btn yellow">点此一键生成菜单</button> <span class="warn p5">警告：该操作将生成新的微信菜单，如您的公众号菜单已存在，将被替换。</span>
                                            </p>
                                            <p class="p5 desc">要查看新菜单，您可关注该公众号。如果您已经关注该公众号，必须重新关注，才能立即看到新生成的菜单，否则需要等待微信菜单缓存更新后（每 24 小时更新一次），才能看到。</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="soof_action_bar">
                                    <button type="button" class="btn white" onclick="rl.page.close();">取 消</button>
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