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
<body class="full_bg signin">
	<div class="viewport">
        <div class="logo_wrap">
            <img class="logo" src="${msite_logo}" />
        </div>
        <div class="mc">
        	<div class="iwrap">
            	<div class="mc_side">
                    <div>
                        <div id="sidePromos" class="s_list">
                        </div>
                        <script id="sidePromoTpl" type="text/x-jsrender">
                            <a {{if href}} href="{{>href}}" {{/if}}>
                                <img class="s" src="{{>src}}" />
                            </a>
                        </script>
                        <script type="text/javascript">
							//defer ads
							orderjs(function(){
								var jQuery = orderjs("open.jquery.jquery");
								orderjs.ready(function(){
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
											var sidePromos = JSON.parse('${sideAds}' || "null");
											if(sidePromos && sidePromos.length > 0){
												jQuery("#sidePromos").html(jQuery("#sidePromoTpl").render(sidePromos));
											}
										}
										catch(err){
											jQuery("#banners")
												.html('<div style="text-align:center; padding: 2em;">图片加载错误：数据解析失败！');
										}
									});
								});
							});
                        </script>
                    </div>
                </div>
            	<div class="mc_main">
                	<div class="s_m">
                        <c:if test="${adOverSignIn_href ne ''}">
                        	<a href="${adOverSignIn_href}">
                            	<img class="s" src="${adOverSignIn_img}" />
                            </a>
                        </c:if>
                        <c:if test="${adOverSignIn_href eq ''}">
                            <img class="s" src="${adOverSignIn_img}" />
                        </c:if>
                    </div>
                    <div class="login">
                    <jsp:include page="${moduleRoot}SignInTabPanel_s/view/Module.jsp" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>