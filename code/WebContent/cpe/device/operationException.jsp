﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提示</title>
<link rel="rl-page-icon" href="${imagePath}dialog_important.png" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_dialog_viewport">
    	<div class="soof_header">
        	<h1>提示</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <div class="section" style="padding:3em 0;">
                            	<table border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td>
                                        	<img class="main_icon" src="${imagePath}dialog_important_64.png" border="0" align="absmiddle" />
                                        </td>
                                        <td style="font-size:16px; padding-left:2em;">
                                        	<c:if test="${host ne null}">【${host.name}】</c:if>${message}
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="soof_action_bar">
                                <button type="button" class="btn primary" style="margin-right: 1em;" onclick="rl.page.close();">确 定</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>