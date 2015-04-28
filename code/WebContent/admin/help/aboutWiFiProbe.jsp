<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>探测管理功能说明</title>
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>探测管理功能说明</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <div class="section first">
                                <h3>WiFi 探测是什么</h3>
                                <p class="p5" style="float:right; text-align:center;">
                                    <img src="${base}resources/image/default/entity/wifi_probe.png" />
                                </p>
                                <p class="p5" style="margin-right:200px;">
                                    WiFi 探测，是指基于 WiFi 探测技术、移动互联网和云计算等先进技术识别商铺附近的智能手机或者WiFi终端，并分析挖掘隐藏在客流数据中的价值，从而帮助商家真正了解顾客，优化商业策略并提升收益。它的特点是即使手机等终端未接入商家的 WiFi 网络，依然可被探测。
                                </p>
                            </div>
                            <div class="section section_unite">
                                <h3>主要功能</h3>
                                <p class="p5">
                                    作为移动互联网时代的营销利器，您可以用它实现以下功能：
                                    <ol style="margin-top:1em; padding-left:1.5em; list-style-type:decimal; line-height:1.5em;">
                                    	<li>客流统计：对顾客数进行统计分析驻留时长、进店率等的比例；</li>
                                    	<li>顾客特征分析：分析新老顾客的比例及其偏好，以作为营销活动或忠诚管理的决策依据；</li>
                                    	<li>顾客到访频率：用户在近一周、一个月、三个月到店的频率；</li>
                                    	<li>终端品牌统计：分析顾客的所持手机的品牌，据此推测顾客的消费能力和消费倾向；</li>
                                    	<li>热点铺位分析：分析顾客聚集度较高的商铺。</li>
                                    	<li>更多功能期待您的建议...</li>
                                    </ol>
                                </p>
                            </div>
                            <div class="section">
                                <h3>启用条件</h3>
                                <p class="p5">
                                    探测功能需要硬件的探测模块支持，在使用之前，您需要确保您的 ap 已支持该功能。<br />
                                </p>
                            </div>
                            <div class="section">
                                <button class="btn primary disabled" disabled="disabled" onclick="saveForm()">启 用</button>
                                <span class="desc">目前该功能正开发测试中，敬请期待...</span>
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