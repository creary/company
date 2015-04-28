<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${share.title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="viewport" content="width=device-width,height=device-height,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="icon" href="${imagePath}wifi_favicon.ico?_v=${cacheBuster}" type="image/x-icon"/>
<link href="${base}resources/css/common.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${cssPath}msite/main.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js?_v=${cacheBuster}"></script>
</head>
<body>
    <div class="soof_main">
    	<div class="page_viewport">
            <div class="soof_article">
                <div class="rtc_head">
                    <h2 class="title">${share.title}</h2>
                </div>
                <div class="rtc_main">
                    <div class="media">
                        <img src="${share.cover}" class="cover" />
                    </div>
                    <div class="content">
                        ${share.content}
                    </div>
                </div>
            </div>
        </div>
    </div>    
    <div class="soof_foot">
        <div class="page_viewport">
            <div class="copyright">
                ${msite_copyright}
            </div>
        </div>
    </div>
</body>
</html>