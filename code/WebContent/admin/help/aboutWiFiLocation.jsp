<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>定位管理功能说明</title>
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>定位管理功能说明</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <div class="section first">
                                <h3>WiFi 定位是什么</h3>
                                <p class="p5" style="float:right; text-align:center;">
                                    <img src="${base}resources/image/default/entity/wifi_location.png" />
                                </p>
                                <p class="p5" style="margin-right:200px;">
                                    WiFi 定位是指基于 WiFi 探测技术、移动互联网和云计算等先进技术识别商铺附近的智能手机或者WiFi终端数据，并利用先进的定位算法分析，实现终端位置的精确定位。
                                </p>
                            </div>
                            <div class="section section_unite">
                                <h3>主要功能</h3>
                                <p class="p5">
                                    作为移动互联网时代的营销利器，您可以用它实现以下功能：
                                    <ol style="margin-top:1em; padding-left:1.5em; list-style-type:decimal; line-height:1.5em;">
                                    	<li>商场导购：自助查询周边商品信息，系统将自动规划合理路径；</li>
                                        <li>信息服务：主动推送营销、精准兴趣营销、自助查询商场的新品、打折、促销等购物信息；</li>
                                        <li>客流分析：某区域或店铺的人群流量进行准确统计，可根据时间、天气、节日、宣传活动等条件生成报表；</li>
                                        <li>寻车服务：点击商场APP的停车按钮，系统将记录停车位置，顾客购物之后，在最短时间内找到自己的车辆；</li>
                                        <li>基于位置的内容服务：商场需要开发出相应的手机APP客户端，以积分或其他手段来培养客户的忠诚度；</li>
                                    	<li>更多功能期待您的建议...</li>
                                    </ol>
                                </p>
                            </div>
                            <div class="section">
                                <h3>启用条件</h3>
                                <p class="p5">
                                    定位功能需要硬件的探测模块支持，在使用之前，您需要确保您的 ap 已支持该功能。<br />
                                </p>
                            </div>
                            <div class="section">
                                <button class="btn primary disabled" disabled="disabled" onclick="saveForm()">启 用</button>
                                <span class="desc">该功能需要二次开发</span>
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