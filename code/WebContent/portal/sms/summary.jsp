<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>短信发送情况</title>
<link rel="rl-page-icon" href="${imagePath}view_detail.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}home.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="soof_page_home">
	<div class="page_viewport">
        <div class="main_wrapper">
            <div class="mc_wrapper">
                <div class="num_stat cols2">
                    <div class="cell">
                        <table class="canvas" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="num">${dto.smsRemain}</td>
                                <td class="name">剩余短信</td>
                            </tr>
                        </table>
                    </div>
                    <div class="cell">
                        <table class="canvas" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="num">${dto.smsUsed}</td>
                                <td class="name">已发送短信</td>
                            </tr>
                        </table>
                    </div>
                    <div class="clearer"></div>
                </div>                
            </div>
        </div>        
    </div>
</div>
</body>
</html>