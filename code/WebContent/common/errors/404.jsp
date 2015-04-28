<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%response.setStatus(404);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="rl-page-error" content="hint" />
<title>404-页面没有找到</title>
<link rel="rl-page-icon" href="${imagePath}info.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<link href="${cssPath}info.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<style type="text/css">
body{
	margin:0;
	padding:0;
	background-color:#f7f7f7 !important;
}
.soof_msg_page{
	font-size:14px;
}
.soof_msg_page .page_viewport{
	width:700px;
	margin:90px 150px;
}
.soof_msg_page .title{
	font-size:36px;
	color:#09C;
}
.soof_msg_page .detail{
	margin-top:2em;
	line-height:25px;
	color:#666;
}
.soof_msg_page .actions{
	margin-top:2em;
}
</style>
</head>
<body>
	<div class="soof_page soof_msg_page">
    	<div class="page_viewport">
            <div class="title">很抱歉，页面出错了</div>
            <div class="detail">
                页面出错：页面没有找到！
            </div>
            <div id="excptions" class="exceptions" style="display:none;">
                <c:if test="${!empty requestScope['javax.servlet.error.message']}"><p class="message">信息：${requestScope["javax.servlet.error.message"]}</p></c:if>
            </div>
            <div class="actions">
            	<button class="btn primary" onclick="rl.page.close(); rl.delay(function(){try{history.back();}catch(err){}}, 1000);">确 定</button>
            </div>
        </div>
    </div>
</body>
</html>