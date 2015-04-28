<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<%@page import="com.soofound.framework.util.SysConfigHelper"%>
<%@page import="com.soofound.framework.jdbc.*"%>
<%@page import="com.soofound.framework.util.*"%>
<%@page import="com.soofound.framework.util.*"%>
<%@page import="com.soofound.portal.dao.*"%>
<%@page import="com.alibaba.fastjson.*"%>
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
                	<h1 class="title">与本平台相关的微信公众平台字段说明</h1>
                </div>
                <div class="rtc_body" style="margin-top:2em;">
                	<div class="section first">
                        <p class="p5">
                            与本平台相关的微信字段有：微信 App ID， Secret， 原始 ID 和二维码。本帮助页用图片标记这些字段，便于您快速查找。
                        </p>
                    </div>
                	<div class="section section_unite">
                        <p class="p5">
                        1. 首先，您应登录微信公众平台。 <a href="https://mp.weixin.qq.com/" target="goWeChat">点击此处去登录&gt;&gt;</a>
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
                        3. 微信 App ID 和 Secret 的位置如下图：
                        </p>
                        <div class="media_ctn" style="padding:1em 0;">
                            <img src="../../resources/image/default/help/wechat_spp_set_appid.jpg" />
                        </div>
                    </div>
                	<div class="section">
                        <p class="p5">
                        4. 打开左侧主菜单的【公众号设置】，如下图：
                        </p>
                        <div class="media_ctn" style="padding:1em 0;">
                            <img src="../../resources/image/default/help/wechat_nav_app_set.jpg" />
                        </div>
                    </div>
                	<div class="section">
                        <p class="p5">
                        5. 微信原始 ID 和二维码的位置如下图：
                        </p>
                        <div class="media_ctn" style="padding:1em 0;">
                            <img src="../../resources/image/default/help/wechat_spp_set_openid_qr.jpg" />
                        </div>
                    </div>
                </div>
            </div>
    	</div>
    </div>
</div>
</body>
</html>