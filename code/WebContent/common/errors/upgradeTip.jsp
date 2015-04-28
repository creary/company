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
<title>升级提示</title>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="cloudac_theme_std cloudac_limit_std">
	<div class="cloudac_head">
    	<div class="head_wrapper">
        	<table border="0" style="width:100%;" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="bottom"><a href="${base}"><img src="${base}<%=SysConfigHelper.getProjectConfig().getLogo1()%>" border="0" align="baseline" class="logo" /></a> <span style="color:gray; font-size:16px;">| 升级提示</span></td>
                </tr>
            </table>
        </div>
    </div>
    <div class="cloudac_sep_line"></div>
    <div class="cloudac_mbody">
    	<div class="mbody_wrapper" style="font-size:14px; line-height:1.5em; margin-top:2em;">
        	<p>
            	您当前的浏览器是：<span id="uaInfo" style="font-weight:bold;"></span>
                <script type="text/javascript">
					var userAgent = navigator.userAgent,   
						rMsie = /(msie\s|trident.*rv:)([\w.]+)/,   
						rFirefox = /(firefox)\/([\w.]+)/,   
						rOpera = /(opera).+version\/([\w.]+)/,   
						rChrome = /(chrome)\/([\w.]+)/,   
						rSafari = /version\/([\w.]+).*(safari)/;  
						var browser;  
						var version;  
						var ua = userAgent.toLowerCase();  
						function uaMatch(ua) {  
							var match = rMsie.exec(ua);  
							if (match != null) {  
								return { browser : "IE", version : match[2] || "0" };  
							}  
							var match = rFirefox.exec(ua);  
							if (match != null) {  
								return { browser : match[1] || "", version : match[2] || "0" };  
							}  
							var match = rOpera.exec(ua);  
							if (match != null) {  
								return { browser : match[1] || "", version : match[2] || "0" };  
							}  
							var match = rChrome.exec(ua);  
							if (match != null) {  
								return { browser : match[1] || "", version : match[2] || "0" };  
							}  
							var match = rSafari.exec(ua);  
							if (match != null) {  
								return { browser : match[2] || "", version : match[1] || "0" };  
							}  
							if (match != null) {  
								return { browser : "", version : "0" };  
							}  
						}  
						var browserMatch = uaMatch(userAgent.toLowerCase()),
							browserInfo = document.getElementById("uaInfo");  
						
						browserInfo.innerHTML = (browserMatch && browserMatch.browser) ?
							browserMatch.browser + " " + browserMatch.version : 
							"未知";
				</script>
            </p>
            <p style="padding-top:10px;">
            	本系统对浏览器要求的是：IE9 及以上版本，火狐 Firefox4 及以上版本， 谷歌 Chorme10 及以上版本， 苹果 Safari5 及以上版本。
            </p>
            <p style="padding-top:10px;">
                如您使用的是国产浏览器（如QQ浏览器、360浏览器、傲游浏览器等），由于这些浏览器的内核（<a href="http://baidu.com/s?word=什么是浏览器内核" target="_blank">什么是浏览器内核?</a>）依然是 IE ，您应先升级您的 IE 浏览器至 IE9 或以上，才能正常使用本系统。
                如升级 IE 后使用这些浏览器依然不能正常访问，请直接使用 IE！
            </p>
    	</div>
    </div>
</div>
</body>
</html>