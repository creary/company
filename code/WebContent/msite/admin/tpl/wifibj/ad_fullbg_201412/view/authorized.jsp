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
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${currentTplContext}view/theme/default/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.full_bg{
	background-color:${msite_bgColor};
	background-image:url(${msite_bg});
}
</style>
<script type="text/javascript" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs.config("shim", {
	"open.jquery.jquery" : {
		exports : "jQuery"
	}
});
orderjs(
	(typeof JSON == "object") ? "" : "open.json.json2",
	"open.jquery.jquery"
);
</script>
</head>
<body class="full_bg authorized">
	<div class="viewport">
        <div class="logo_wrap">
            <img class="logo" src="${msite_logo}" />
        </div>
        <div class="mc">
        	<div class="iwrap">
                <div class="main_tip">${passTip}</div>
                <div>
                    <div id="banners">
                    </div>
                    <script id="bannerTpl" type="text/x-jsrender">
                        <div class="s_banner_wrap">
							<a {{if href}} href="{{>href}}" {{/if}}>
								<img class="s_banner" src="{{>src}}" />
							</a>
                        </div>
                    </script>
                    <script type="text/javascript">
                        orderjs.config("shim", {
                            "open.jsrender.jsrender" : {
                                deps : ["open.jquery.jquery"]
                            }
                        });
                        orderjs(
                            "open.jsrender.jsrender"
                        );
                        orderjs(function(){
                            var jQuery = orderjs("open.jquery.jquery");
                            try{
                                var bannerImages = JSON.parse('${bannerImages}' || "null");
                                if(bannerImages && bannerImages.length > 0){
                                    jQuery("#banners").prepend(jQuery("#bannerTpl").render(bannerImages))
                                }
                            }
                            catch(err){
                                jQuery("#banners")
                                    .html('<div style="text-align:center; padding: 2em;">图片加载错误：数据解析失败！');
                            }
                        });
                    </script>
                </div>
            </div>
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