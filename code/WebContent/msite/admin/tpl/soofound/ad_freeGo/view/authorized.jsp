<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${msite_title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<script type="text/javascript" src="${jsPath}rl/src/order.js"></script>
<link rel="icon" href="${imagePath}wifi_favicon.ico" type="image/x-icon"/>
<link href="${base}resources/css/common.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${currentTplContext}view/theme/default/main.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
</head>
<body class="soof_msite">
	<div class="page_viewport">
        <div class="soof_head">
            <img src="${msite_logo}" border="0" class="logo" />
        </div>
        <div class="soof_main">
            <jsp:include page="${moduleRoot}BannerSlide/view/Module.jsp" />
            <jsp:include page="${moduleRoot}RandomImage/view/Module.jsp" />
        </div>
        <div class="soof_foot">
        	<div class="copyright">${msite_copyright}</div>
        </div>
        <span style="display:none;">
            <script type="text/javascript">
            if(!/127\.0\.0\.1|localhost/i.test(String(location.host))){
                var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
                document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fe8f02f1992768475cc8e1b6635415c3c' type='text/javascript'%3E%3C/script%3E"));
            }
            </script>
        </span>
    </div>
</body>
</html>