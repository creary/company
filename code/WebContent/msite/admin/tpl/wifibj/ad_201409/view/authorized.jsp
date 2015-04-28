<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${msite_title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="icon" href="${imagePath}wifi_favicon.ico" type="image/x-icon"/>
<link href="${currentTplContext}view/theme/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
</head>
<body class="msite_page_authorized">
    <div class="soof_ad_tlayout">
        <div class="header">
            <img class="banner" alt="" src="${topADUrl}" />
        </div>
        <div class="mc">
            <div class="brand_area">
                <img class="logo" alt="logo" src="${msite_logo}" />
                <div class="tip">${passTip}</div>
            </div>
        	<div class="ad_list">
                ${adImagesHtml}
            </div>
            <div class="clearer"></div>
        </div>
        <div class="foot">
        	<div class="copyright">${msite_copyright}</div>
        </div>
    </div>
    <span style="display:none;">
		<script type="text/javascript">
        if(!/127\.0\.0\.1|localhost/i.test(String(location.host))){
            var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
            document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fe8f02f1992768475cc8e1b6635415c3c' type='text/javascript'%3E%3C/script%3E"));
        }
        </script>
    </span>
</body>
</html>