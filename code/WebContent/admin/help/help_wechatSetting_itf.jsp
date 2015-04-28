<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<%@page import="com.soofound.framework.util.SysConfigHelper"%>
<%@page import="com.soofound.framework.jdbc.*"%>
<%@page import="com.soofound.cpe.util.SoofacACS"%>
<%@page import="com.soofound.framework.util.*"%>
<%@page import="com.soofound.portal.dao.*"%>
<%@page import="com.alibaba.fastjson.*"%>
<% 
   SoofacACS acs = (SoofacACS)SysConfigHelper.getBean("soofacACS");
   request.setAttribute("acsURL", acs.getAcsURL());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>帮助 - <%=SysConfigHelper.getProjectConfig().getProduct()%></title>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="${base}<%=SysConfigHelper.getProjectConfig().getFavicon()%>" type="image/x-icon"/> 
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
</head>
<body>
<div class="cloudac_theme_std cloudac_limit_std soof_page">
	<div class="cloudac_head">
    	<div class="head_wrapper">
        	<table border="0" style="width:100%;" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="bottom"><a href="${base}"><img src="${base}<%=SysConfigHelper.getProjectConfig().getLogo1()%>" border="0" align="baseline" class="logo" /></a> <span style="color:gray; font-size:16px;">| 帮助</span></td>
                </tr>
            </table>
        </div>
    </div>
    <div class="cloudac_sep_line"></div>
    <div class="cloudac_mbody">
    	<div class="mbody_wrapper" style="font-size:14px; line-height:1.5em; margin-top:2em;">
        	<div class="article">
            	<div class="rtc_head" style="padding:2em 0 1em;">
                	<h1 class="title">微信公众平台服务器配置说明</h1>
                </div>
                <div class="rtc_body" style="margin-top:2em;">
                	<div class="section first">
                        <p class="p5">
                            当微信用户向您的公众号发送消息时，为了让本平台能自动回复，您应该在微信公众平台上对您公众号的【服务器配置】项进行设置，具体操作如下：
                        </p>
                    </div>
                	<div class="section section_unite">
                        <p class="p5">
                        1. 登录微信公众平台。 <a href="https://mp.weixin.qq.com/" target="goWeChat">点击此处去登录&gt;&gt;</a>
                        </p>
                    </div>
                	<div class="section">
                        <p class="p5">
                        2. 打开左侧主菜单的【开发者中心】，如下图：
                        </p>
                        <div class="media_ctn" style="padding:1em 0;">
                            <img src="../../resources/image/default/help/wechat_nav_dev.jpg" />
                        </div>
                    </div>
                	<div class="section">
                        <p class="p5">
                        3. 找到该页面的【服务器配置】项，如下图：
                        </p>
                        <div class="media_ctn" style="padding:1em 0;">
                            <img src="../../resources/image/default/help/wechat_dev_set_server1.jpg" />
                        </div>
                    </div>
                	<div class="section">
                        <p class="p5">
                        4. 点击【修改配置】按钮，把 URL 和 Token 值设置为：
                        </p>
                        <div class="fields_box">
                            URL： ${acsURL}acs/wechat.do <br />
                            Token： mywifi
                        </div>
                        <p class="p5">
                        设置完成后，如下图：
                        </p>
                        <div class="media_ctn" style="padding:1em 0;">
                            <img src="../../resources/image/default/help/wechat_dev_set_server2.jpg" />
                        </div>
                    </div>
                </div>
            </div>
    	</div>
    </div>
</div>
</body>
</html>