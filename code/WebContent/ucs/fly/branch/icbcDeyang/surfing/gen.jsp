<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<%@page import="com.soofound.framework.util.SysConfigHelper"%>
<% 
   request.setAttribute("pcfg",SysConfigHelper.getProjectConfig());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="position: relative; min-height: 100%;">
<head>
    <title>工商银行 WiFi 账户生成系统</title>
    <link rel="shortcut icon" href="${base}${pcfg.favicon}" type="image/x-icon"/> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
    <link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
    <link href="${base}ucs/fly/portal/icbcDeyang/resources/css/mui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${jsPath}rl/src/open/jQuery-idTabs/jquery_idtabs.css"/>
    <style type="text/css">
	#genPanel{
		margin:40px auto;
		width:500px;
		font-size:14px;
	}
	#genPanel .fields_wrap{
		margin-top:30px;
		padding:0 2em;
	}
	#genPanel .tabbar a.selected{
		border-color:#D80100;
		color:#D80100;
	}
	#genPanel div.tbody{
		padding:0;
	}
	@media (max-width:780px){
		#genPanel{
			width:400px;
		}
	}
	@media (max-width:500px){
		#genPanel{
			width:300px;
		}
	}
	</style>
    <script type="text/javascript" data-main="RealLight" data-rlconfig="{ autoLoadLang:false, debugMode:true}" src="${jsPath}rl/src/order.js"></script>
    <script type="text/javascript">
	orderjs.regRootNS("cloudac", {
		js : "${jsPath}cloudac/",
		css : "${cssPath}"
	});
    orderjs(
        "open.jquery.jquery",
        "open.jQuery-idTabs.idTabs-ordered",
		"lib.rpc.JqueryAjaxDirect",
		"cloudac:SectionSwitcher"
    );
    orderjs(function(){
        //rl.console.showOnReady();
		var jQuery = orderjs("open.jquery.jquery"),
			JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect"),
			SectionSwitcher = orderjs("cloudac:SectionSwitcher");
		
		jQuery.ajaxSetup({
			dataType : "json",
			method : "post",
			cache : false
		});
	
		var mainDirect = new JqueryAjaxDirect({
			services : {
				"gen" : function(options, params){
					var url = rl.format("${base}portal/getSequenceAccount.do?branchId=${branchId}&{1}", arguments);
					rl.debug(this + ' url = ' + url + '');
					jQuery.ajax(rl.ext(options, { url : url}));
				}
			}
		});
		
		function updateGenResult(ctn, result){
			var html = '<p class="p10">' + 
					'帐号: <b>{user}</b><br />' + 
					'密码: <b>{password}</b>' +
				'</p>' +
				'<p class="p10">' +
					'操作流水: {sn}<br />' + 
					'操作时间: {ctime}' +
				'</p>';
			html = rl.format(html, result);
			
			jQuery(ctn).html(html);
		}
		
		function genByOnekey(){
			mainDirect.invoke("gen", {
				beforeSend : function(){
					jQuery("#btn_genByOnekey")
						.prop("disabled", true)
						.addClass("disabled")
						.text("正在生成密码...");
				},
				success : function(result){
					ss_sectionOnekey.setActive("result");
					updateGenResult("#genResult_onekey", result);
				},
				complete : function setAjaxEndView(){
					jQuery("#btn_genByOnekey")
						.prop("disabled", false)
						.removeClass("disabled")
						.text("一键开户");
				}
			});
		}
		
		function genByPhone(num){
			mainDirect.invoke("gen", {
				beforeSend : function(){
					jQuery("#btn_genByPhone")
						.prop("disabled", true)
						.addClass("disabled")
						.text("正在生成密码...");
				},
				success : function(result){
					ss_sectionPhone.setActive("result");
					updateGenResult("#genResult_phone", result);
				},
				complete : function setAjaxEndView(){
					jQuery("#btn_genByPhone")
						.prop("disabled", false)
						.removeClass("disabled")
						.text("生成密码");
				}
			}, "phone=" + num);
		}
		
		function genByInputNum(){
			var fieldJQ = jQuery("#field_phone");
			fieldJQ.val(rl.trim(fieldJQ.val()));
			var num = fieldJQ.val(),
				mobileRE = /^((\(\d{3}\))|(\d{3}\-))?1\d{10}$/;
			
			if(!num){
				alert("请输入手机号码！");
				fieldJQ.focus();
				return false;
			}
			if(!mobileRE.test(num)){
				alert("请输入正确的手机号码！");
				fieldJQ.focus();
				return false;
			}
			
			return genByPhone(num);
		}
		
		function showGenResult(){
			
		}
		
		var ss_sectionOnekey, ss_sectionPhone;
        orderjs.ready(function(){
            jQuery("#btn_genByOnekey")
				.on("click", genByOnekey);
			jQuery("#btn_backGenByOnekey")
				.on("click", function(){ss_sectionOnekey.setActive("opr");});
			
            jQuery("#btn_genByPhone")
				.on("click", genByInputNum);
			jQuery("#btn_backGenByPhone")
				.on("click", function(){
					ss_sectionPhone.setActive("opr");
					jQuery("#field_phone")
						.focus()
						.select();
				});
			
			jQuery("#genPanel").idTabs();
			jQuery("#tg_section_phone_opr").on("click", function(){
				jQuery("#field_phone").focus();
			});
			
			ss_sectionOnekey = new SectionSwitcher({
				iniActive : "opr",
				map : {
					"opr" : "#section_onekey_opr",
					"result" : "#section_onekey_result"
				}
			});
			ss_sectionPhone = new SectionSwitcher({
				iniActive : "opr",
				map : {
					"opr" : "#section_phone_opr",
					"result" : "#section_phone_result"
				}
			});
		});
    });
    </script>
</head>

<body class="soof_mui_icbc">
    <div class="soof_upgrade_warning">
        对不起，您的浏览器版本太低，部分功能将无法使用，请升级您的浏览器！<a href="${base}common/upgradeTip.jsp">查看详情</a>
    </div>
    <div class="head">
        <div class="logo_wrap">
            <img src="${base}ucs/fly/portal/icbcDeyang/resources/image/logo_titled.png" border="0" class="logo" />
        </div>
        <span>(${user.branch})</span>
        <div class="menubar">
            <div class="user_bar">
                <a id="link_userInfo" class="btn_link" href="javascript:void(0);">
                <img src="${base}resources/image/default/mui/icon_user.gif" align="absmiddle" /> ${CURRENT_USER.username}</a>
                <a class="btn_link" href="${base}ucs/fly/portal/logout.do?branch=icbcDeyang"><img src="${base}resources/image/default/mui/icon_exit.gif" align="absmiddle" /> 退出</a>
            </div>
        	<div class="pagetabs">
            	<a class="active" href="javascript:void(0);">快速开户</a>
            	<a href="${base}custom/readySurfingAccountList.do?branchId=${branchId}">用户管理</a>
            </div>
            <div class="clearer"></div>
        </div>
    </div>
    <div class="main">
        <div id="genPanel" class="jquery_idtabs fullfit">
        	<table class="tabbar" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                    	<a class="selected" href="#section_onekey">一键开户</a>
                    </td>
                    <td>
                    	<a id="tg_section_phone_opr" href="#section_phone">手机号开户</a>
                    </td>
                </tr>
            </table>
            <div class="tbody" id="section_onekey">
            	<div id="section_onekey_opr" class="switchable_section">
                	<div class="fields_wrap">
                        <p>点击以下按钮，生成随机 WiFi 用户名和密码。</p>
                    </div>
                    <p style="margin-top:50px; text-align:center;">
                        <button id="btn_genByOnekey" class="btn primary" style="width:100%;">一键开户</button>
                    </p>
                </div>
            	<div id="section_onekey_result" class="switchable_section">
                	<div class="fields_wrap">
                        <p>操作成功! 以下为本次生成的账户信息:</p>
                        <div id="genResult_onekey"></div>
                    </div>
                    <p style="margin-top:30px; text-align:center;">
                        <button id="btn_backGenByOnekey" class="btn">返回</button>
                    </p>
                </div>
            </div>
            <div class="tbody" id="section_phone">
            	<div id="section_phone_opr" class="switchable_section">
                    <form name="phoneForm" onsubmit="return false;">
                        <div class="fields_wrap">
                            <p>以顾客的手机号为用户名，生成随机密码。</p>
                            <p class="p10">
                                <label for="field_phone">请输入手机号:</label>
                                <input class="field s" name="phone" id="field_phone" />
                            </p>
                        </div>
                        <p style="margin-top:50px; text-align:center;">
                            <button type="submit" id="btn_genByPhone" class="btn primary" style="width:100%;">生成密码</button>
                        </p>
                    </form>
                </div>
            	<div id="section_phone_result" class="switchable_section">
                	<div class="fields_wrap">
                        <p>操作成功! 以下为本次生成的账户信息:</p>
                        <div id="genResult_phone"></div>
                    </div>
                    <p style="margin-top:30px; text-align:center;">
                        <button id="btn_backGenByPhone" class="btn">返回</button>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="foot">
    	<div class="copyright">中国工商银行 版权所有</div>
    </div>
</body>
</html>