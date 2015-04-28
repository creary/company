<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>重置密码</title>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}resources/css/form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
</head>
<body>
<div class="cloudac_theme_std cloudac_limit_std">
	<div class="cloudac_head">
    	<div class="head_wrapper">
        	<table border="0" style="width:100%;" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="bottom"><a href="${base}"><img src="${base}${pcfg.logo1}" border="0" align="baseline" class="logo" /></a> <span style="color:gray; font-size:16px;">| 重置密码</span></td>
                </tr>
            </table>
        </div>
    </div>
    <div class="cloudac_sep_line"></div>
    <div class="cloudac_mbody">
    	<div class="mbody_wrapper" style="font-size:14px; line-height:1.5em; margin-top:4em;">
            <table class="cloudac_req_feedback" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <c:if test="${message ne null}">
                        <td class="img_ctn"><img src="${imagePath}tick_128.gif" /></td>
                        <td class="content_ctn">
                        	<h2>操作成功</h2>
                        	<div class="desc">
                        		密码重置成功，请 <a href="${base}login.jsp">登入</a>
                        	</div>
                        </td>
                    </c:if>
                    <c:if test="${error ne null}">
                        <td class="img_ctn"><img src="${imagePath}warning_128.gif" /></td>
                        <td class="content_ctn"><h2>操作成功</h2>
                            <div class="desc">
                            	${error}
                            </div>
                            <a href="javascript:void(0);" onclick="history.back(-1);">返回</a></td>
                    </c:if>
                </tr>
            </table>
    	</div>
    </div>
</div>
</body>
</html>