<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${msite_title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="icon" href="${imagePath}wifi_favicon.ico" type="image/x-icon"/>
<link href="${currentTplContext}view/theme/default/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="soof_ad_tlayout">
        <div class="header">
            <img class="banner" alt="" src="${topADUrl}" />
        </div>
        <div class="mc cols2">
            <div class="side">
            	<div class="ad_wrapper">
                    ${adImagesHtml}
                </div>
            </div>
        	<div class="main">
            	<div class="brand_area">
                    <img class="logo" alt="logo" src="${msite_logo}" />
                    <div class="tip">${startTip}</div>
                </div>
                <div class="notice">
                    ${wifiAgreement}
                </div>
                <div>
                	<a class="btn" href="${base}/wifiant/freeAuth.do?${queryString}">${jumpBtnText}</a>
                </div>
            </div>
            <div class="clearer"></div>
        </div>
        <div class="foot">
        	<div class="copyright">${msite_copyright}</div>
        </div>
    </div>
</body>
</html>