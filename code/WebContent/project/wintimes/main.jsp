<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="fullscreen">
<head>
<title>${pcfg.product}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<link rel="icon" href="${base}${pcfg.favicon}" type="image/x-icon"/> 
<link href="${cssPath}mui.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.upgrade_warning{
	display:block;
	position:absolute; 
	z-index:100;
	top:18px; 
	left:30%; 
	border:solid 1px #666; 
	background-color:#FFC; 
	padding:1em;
}
@media only screen{
	.upgrade_warning{
		display:none;
	}
}
</style>
</head>
<body class="fullscreen soof_mui theme_darkblue">
    <div class="upgrade_warning">
        对不起，您的浏览器版本太低，部分功能将无法使用，请升级您的浏览器！<a href="${base}common/errors/upgradeTip.jsp">查看详情</a>
    </div>
    <div id="muiHeader" class="mui_header">
        <div class="logo_wrap">
            <img src="${base}${pcfg.logo2}" border="0" />
        </div>
        <div class="user_bar">
            <a id="link_userInfo" class="btn" href="javascript:void(0);">
            <img src="${base}resources/image/default/mui/icon_user.gif" align="absmiddle" /> ${CURRENT_USER.username}</a>
            <a class="btn" href="${base}<c:url value='j_spring_security_logout' />"><img src="${base}resources/image/default/mui/icon_exit.gif" align="absmiddle" /> 退出</a>
        </div>
    </div>
    <div id="muiMainWrapper" class="mui_main_wrapper">
        <div class="mui_side">
            <div class="soof_mui_menu" id="mainMenu">
                <ul class="menu_holder">
                    <li>
                        <img src="${base}resources/image/default/mix/blank.gif" class="indent_icon" border="0" />
                        <a id="startpage" href="${base}home/soofacHome.do" target="mainFrame" class="ctg icon_home"><div class="text">平台概览</div></a>
                    </li>
                    <li>
                        <img src="${base}resources/image/default/mix/blank.gif" class="fold_icon fold_icon_collapsed" border="0" />
                        <a id="admin_merchantMgmt" href="javascript:void(0);" class="ctg icon_bizmgmt"><div class="text">商家管理</div></a>
                        <ul class="sub_list" style="display:none;">	
                            <li><a id="admin_merchant" href="${base}admin/merchant.do?id=${branchId}" target="mainFrame">商家信息</a></li>
                            <li><a id="admin_branchList" href="${base}admin/branchListJsp.do?rich=1" target="mainFrame">商家管理</a></li>
                            <li><a id="admin_branchUserList" href="${base}admin/userListJsp.do" target="mainFrame">帐号管理</a></li>                     
                            <li><a id="admin_payCenter" href="${base}portal/readyPayCenter.do?id=${branchId}" target="mainFrame">充值中心</a></li>
                        </ul>
                    </li>
                    <li><img src="${base}resources/image/default/mix/blank.gif" class="fold_icon fold_icon_collapsed" border="0" />
                        <a id="cpe_mgmt" href="javascript:void(0);" class="ctg icon_wifi_device"><div class="text">设备管理</div></a>
                        <ul class="sub_list" style="display:none;">	
                            <li><a id="cpe_hostList" href="${base}cpe/richHostListJsp.do" target="mainFrame">设备列表</a></li>
                            <li><a id="cpe_logList" href="${base}cpe/deviceLogListJsp.do" target="mainFrame">设备日志</a></li>
                            <li><a id="cpe_configFileList" href="${base}cpe/softListJsp.do?tag=2" target="mainFrame">配置管理</a></li>  
                        </ul>
                    </li>
                    <li><img src="${base}resources/image/default/mix/blank.gif" class="fold_icon fold_icon_collapsed" border="0" />
                        <a id="marketing_mgmt" href="javascript:void(0);" class="ctg icon_msite"><div class="text">营销管理</div></a>
                        <ul class="sub_list" style="display:none;">	
                            <li><a id="marketing_portalList" href="${base}portal/instanceListJsp.do" target="mainFrame">Portal</a></li>
                            <li><a id="marketing_policyList" href="${base}portal/policyListJsp.do" target="mainFrame">认证策略</a></li>
                            <li><a id="marketing_articleList" href="${base}portal/advertiseListJsp.do" target="mainFrame">商讯管理</a></li>
                            <li><a id="marketing_articleGroupList" href="${base}portal/adCateListJsp.do" target="mainFrame">商讯分类</a></li>
                            <li><a id="marketing_sendWechat" href="${base}portal/readySendWechat.do" target="mainFrame">微信群发</a></li>
                            <li><a id="marketing_sendSms" href="${base}portal/readySendSms.do?branchId=${branchId}" target="mainFrame">短信群发</a></li>
                            <li><a id="admin_smsSendList" href="${base}portal/smsListJsp.do" target="mainFrame">已发短信</a></li>
                        </ul>
                    </li>            
                    <li><img src="${base}resources/image/default/mix/blank.gif" class="fold_icon fold_icon_collapsed" border="0" />
                        <a id="surfing_mgmt" href="javascript:void(0);" class="ctg icon_wifi_user"><div class="text">用户管理</div></a>
                        <ul class="sub_list" style="display:none;">	
                            <li><a id="surfing_accountList" href="${base}portal/surfingAccountListJsp.do" target="mainFrame">用户列表</a></li>
                            <li><a id="surfing_sessionList" href="${base}portal/sessionListJsp.do" target="mainFrame">上网日志(在线)</a></li>
                            <li><a id="surfing_sessionList" href="${base}portal/sessionListJsp.do?history=1" target="mainFrame">上网日志(历史)</a></li>
                            <li><a id="surfing_traceList" href="${base}detect/traceFlowListJsp.do" target="mainFrame">溯源信息</a></li>
                            <li><a id="surfing_blackList" href="${base}portal/blackWhiteListJsp.do?bw=2" target="mainFrame">黑名单</a></li>
                            <li><a id="surfing_whiteList" href="${base}portal/blackWhiteListJsp.do?bw=1" target="mainFrame">白名单</a></li>
                        </ul>
                    </li>
                    <li><img src="${base}resources/image/default/mix/blank.gif" class="fold_icon fold_icon_collapsed" border="0" />
                        <a id="report_mgmt" href="javascript:void(0);" class="ctg icon_stat"><div class="text">运行统计</div></a>
                        <ul class="sub_list" style="display:none;">	
                            <li><a id="rpt_apSummary" href="${base}report/apSummary.jsp" target="mainFrame">AP统计</a></li>
                            <li><a id="rpt_apEndUserSummary" href="${base}report/apEndUserSummary.jsp" target="mainFrame">AP接入用户数统计</a></li>
                            <li><a id="rpt_merchantEndUserSummary" href="${base}report/merchantEndUserSummary.jsp" target="mainFrame">商家接入用户数统计</a></li>
                            <li><a id="rpt_apTrafficSummary" href="${base}report/apTrafficSummary.jsp" target="mainFrame">AP流量统计</a></li>
                            <li><a id="rpt_merchantTrafficSummary" href="${base}report/merchantTrafficSummary.jsp" target="mainFrame">商家流量统计</a></li>
                            <li><a id="rpt_endUserBrand" href="${base}report/brandSummary.jsp" target="mainFrame">用户终端统计</a></li>
                            <li><a id="rpt_wifiUsed" href="${base}report/wifiUsedSummary.jsp" target="mainFrame">用户使用统计</a></li>
                            <li><a id="rpt_offlineHosts" href="${base}operation/host/offlineHosts.jsp" target="mainFrame">离线设备</a></li>
                        </ul>
                    </li>
                    <li><img src="${base}resources/image/default/mix/blank.gif" class="fold_icon fold_icon_collapsed" border="0" />
                        <a id="admin_systemMgmt" href="javascript:void(0);" class="ctg icon_setting"><div class="text">平台管理</div></a>
                        <ul class="sub_list" style="display:none;">	                    
                            <li><a id="admin_licenseList" href="${base}admin/licenseListJsp.do" target="mainFrame">序列号管理</a></li>
                            <li><a id="admin_logList" href="${base}admin/logListJsp.do" target="mainFrame">平台日志</a></li>                            
                        </ul>
                    </li>                                
                </ul>
            </div>
        </div>
        <div class="mui_main">
        	<iframe class="main_frame" id="mainFrame" name="mainFrame" frameborder="0" ></iframe>
        </div>
    </div>
</body>
</html>
<script type="text/javascript" src="${base}resources/js/rl/src/open/jquery/jquery.js"></script>
<script type="text/javascript">
(function(){
	function openUrl(url){
		jQuery("#mainFrame").attr("src", url);
	}
	
	function openMenuLink(id){
		var node = jQuery("#" + id);
		if(node.is("a")){
			focusOnMenuItem(id);
			openUrl(node.attr("href"));
		}
	}

	function focusOnMenuItem(id){
		var node = jQuery("#" + id);
		if(node.is("a")){
			node.click();
		}
	}

	function updateMuiSize(){
		var total = jQuery(window).height(),
			headerHeight = jQuery("#muiHeader").height();
		jQuery("#muiMainWrapper").height(total - headerHeight);
	}
	//init menu behaviours
	var lastActiveMenuItem,
		mainMenu = jQuery("#mainMenu");
		
	function collaspeAll(except){
		mainMenu.find("ul.sub_list").each(function(){
			if(except && except[0] == jQuery(this)[0]) return;
			
			collaspeSubMenu(jQuery(this));
		});
	}
		
	function collaspeSubMenu(subMenu){
		var subMenuCtn = subMenu.closest("li");
		
		subMenu.slideUp("fast");
		subMenuCtn.find(".fold_icon").addClass("fold_icon_collapsed");
	}
		
	function expandSubMenu(subMenu){
		var subMenuCtn = subMenu.closest("li");
		
		subMenu.slideDown("fast");
		subMenuCtn.find(".fold_icon").removeClass("fold_icon_collapsed");
	}
	
	mainMenu.on("mouseover",function(){
			mainMenu.addClass("soof_mui_menu_scrollable");
		})
		.on("mouseout",function(){
			mainMenu.removeClass("soof_mui_menu_scrollable");
		});
	mainMenu.find(".ctg, .fold_icon")
		.on("click", function(){
			var subMenu = jQuery(this).parent().find("ul.sub_list");
			
			if(subMenu.css("display") == "none"){
				expandSubMenu(subMenu);
			}
			else{
				collaspeSubMenu(subMenu);
			}
		});
	mainMenu.find("a[target]")
		.on("click", function(){
			if(lastActiveMenuItem && lastActiveMenuItem.removeClass) lastActiveMenuItem.removeClass("active");
			lastActiveMenuItem = jQuery(this).addClass("active");
			
			var subMenu = jQuery(this).closest("ul.sub_list");
			
			collaspeAll(subMenu);
			if(subMenu.css("display") == "none"){
				expandSubMenu(subMenu);
			}
		});
	
	window.openMenuLink = openMenuLink;
	window.focusOnMenuItem = focusOnMenuItem;
	
	//init startup onReady
	jQuery(function(){
		//wait orderjs is loaded, then start open page
		//open start page
		openMenuLink("startpage");
		
		jQuery(window)
			.on("resize", updateMuiSize)
			.resize();
		
		//prepare dialog
		orderjs("gui.dialog.Dialog", function(){
			rl.page.setWindowsMgr(rl.gui.dialog.dialogMgr);
			jQuery("#link_userInfo")
				.on("click", function(){
					var url = '${base}admin/userReadyEditPerson.do?username=${CURRENT_USER.username}';
					rl.page.open(url, 'currentUserInfo', rl.gui.makeDlgOpt(350))
				});
		});
	});
})();
</script>
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>