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
    	<div class="mbody_wrapper" style="font-size:14px; line-height:1.5em; margin-top:2em;margin-bottom:2em;">
        	<div class="article">
            	<div class="rtc_head" style="padding:2em 0 1em;">
                	<h1 class="title">微信朋友圈分享认证说明</h1>
                </div>
                <div class="rtc_body" style="margin-top:2em;">
                	<div class="section first">
                        <p class="p5">
                            本篇帮助主要内容有：
                            <ol class="p5" style="line-height:2em;">
                            	<li><a href="#nch_about">朋友圈分享认证简述</a></li>
                            	<li><a href="#nch_flow">朋友圈分享认证流程</a></li>
                            	<li><a href="#nch_setting">如何开启朋友圈分享认证</a></li>
                            	<li><a href="#nch_notice">您需了解的几个说明</a></li>
                            </ol>
                        </p>
                    </div>
                	<div class="section">
                        <h3 id="nch_about">朋友圈分享认证简述</h3>
                        <p class="p5">
                        	朋友圈分享认证是指用户将商讯（如促销、抽奖、产品宣传等信息）分享到其微信朋友圈，从而获得上网的权限；间接替商家二次传播，实现口碑营销。本认证方式具有以下优势：用户无需输入用户名和密码，整个流程只需简单几次点击即可完成，一看即懂，无需培训；另外，商家可以无需申请微信公众号。
                        <p>
                        <p class="p10">
                            分享的力量，可以使您的品牌、优惠、热卖、互动每天数以万次的传播，并形成几何增长趋势,最炫最贴近时代的营销！
                        </p>
                    </div>
                	<div class="section">
                        <h3 id="nch_flow">朋友圈分享认证流程</h3>
                        <p class="p5">
                        	用户手机在认证之前，微信基本功能（包括朋友圈）可以使用，但当用户在朋友圈打开文章链接时将被拦截，并跳转至 Portal 的分享页，仅当用户将分享页的文章或图片分享至朋友圈后，才能正常上网。流程图如下：
                        </p>
                        <p class="p10">
                        	<img src="${base}resources/image/default/help/wechat_share_flow.jpg?_v=${cacheBuster}" width="814" height="921" />
                        </p>
                    </div>
                	<div class="section">
                        <h3 id="nch_setting">如何开启朋友圈分享认证</h3>
                        <p class="p5">
                        	如需启用分享认证，请参考以下操作说明：
                        </p>
                        <ol class="p5">
                            <li>将设备的固件升级至 v1.4.16 及以上版本，建议升至最新。
                            	<p>
                                	具体操作步骤：在系统主菜单中，选择【设备管理】&gt;【设备列表】，选择您要用作测试的设备，点击【操作】列中的下拉按钮，选择“更新固件”，在打开的固件更新页中，选择 v1.4.16 及以上版本更新，等待更新完成后继续下一步。
                                </p>
                            </li>
                            <li>打开设备的认证策略，在认证策略里启用分享认证。
                            	<p>
                                	具体操作步骤：打开以上升级后的设备的配置页 &gt; 选择【运营策略】标签页 &gt; 在设备的 SSID 列表中，打开设备的认证策略，在认证策略页中，开启分享认证，选择微信朋友圈分享，点击保存，继续下一步。
                                </p>
                            </li>
                            <li>打开设备的 Portal 设置，在分享页设置里投放商讯。
                            	<p>
                                	具体操作步骤：打开以上升级后的设备的配置页 &gt; 选择【运营策略】标签页 &gt; 在设备的 SSID 列表中，打开设备的 Portal链接，在 Portal 设置页中，选择【分享页设置】，在【分享内容】项中点击【添加投放】即可。如果您尚未添加商讯，请添加后再投放。
                                </p></li>
                        </ol>
                        <p class="p5">
                        至此分享认证的操作完成。接下来请用您的手机，验证设置是否成功。验证步骤请参考以上流程图。
                        </p>
                    </div>
                	<div class="section">
                        <h3 id="nch_notice">您需了解的几个说明</h3>
                        <p class="p5">
                        	为提高用户的分享积极性，同时也避免过多分享对用户的干扰，平台对分享认证的机制做如下规范：
                        </p>
                        <ol class="p5">
                            <li>一条内容被用户分享后，将不再重复展示给该用户；当用户再次进行分享认证时，系统将展示新内容，直至用户分享完所有内容。</li>
                            <li>当用户不需分享认证（如已全部分享完）时，平台自动执行当前认证策略所设置的认证方式。</li>
                            <li>用户如选择不分享，可以其它方式认证。</li>
                        </ol>
                    </div>
                </div>
            </div>
    	</div>
    </div>
</div>
</body>
</html>