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
<body class="full_bg welcome">
	<div class="viewport">
        <div class="logo_wrap">
            <img class="logo" src="${msite_logo}" />
        </div>
        <div class="mc">
        	<div class="iwrap">
                <div class="main_tip">${startTip}</div>
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
        		<div>
                    <div class="s_list" id="sImages">
                        <div class="clearer"></div>
                    </div>
                    <script id="imageTpl" type="text/x-jsrender">
						<div class="cell {{>align}}">
							<a {{if href}} href="{{>href}}" {{/if}}>
								<img class="s" src="{{>src}}" />
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
                                var html = [],
									picWallImages = JSON.parse('${picWallImages}' || "null");
                                
								jQuery.each(picWallImages, function(index){
									var isNewRow = (index % 2 == 0);
									
									this.align = isNewRow ? "l" : "r";
									if(isNewRow){
										html.push('<div class="row">');
									}
									html.push(jQuery("#imageTpl").render(this));
									if(!isNewRow){
										html.push('</div>');
									}
								});
								
								jQuery("#sImages").prepend(html.join(""));
                            }
                            catch(err){
                                jQuery("#sImages")
                                    .html('<div style="text-align:center; padding: 2em;">图片加载错误：数据解析失败！');
                            }
                        });
                    </script>
                </div>
                <div class="p10">
                    <script type="text/javascript">
						function jumpTo(url, btn){
							if(!document.getElementById('acceptAgreementChk').checked){
								alert("您必须接受《免费Wi-Fi用户使用协议》才能继续使用 Wi-Fi！");
								return;
							}
							location.href=url;
							if(btn) btn.innerHTML = "请稍后...";
						}
					</script>
                    <noscript>
                        <span style="color:red">您的浏览器禁用了 Javascript，无法使用本系统上网，请在浏览器设置中开启后重试!</span>
                    </noscript>
                    <div class="agreement_ctn">
                        <input type="checkbox" id="acceptAgreementChk" checked="checked" />
                        <label for="acceptAgreementChk">
                            我已阅读并接受</label> <a href="${base}acs/toWifiUserAgreement.do" style="text-decoration:underline; color:#090;" target="userAgreement" tabindex="-1">《免费Wi-Fi使用协议》</a>
                    </div>
                    <div class="bar p10">
                        <c:if test="${preview eq null}">
                            <c:if test="${auth == false}">    
                                <button class="primary" onclick="jumpTo('${base}wifiant/freeAuth.do?${queryString}', this)">${jumpBtnText}</button>
                            </c:if>
                            <c:if test="${auth}">    
                                <button class="primary" onclick="jumpTo('${base}wifiant/signin/view.do?${queryString}', this)">${jumpBtnText}</button>
                            </c:if>
                        </c:if>            
                        <c:if test="${preview ne null}">
                            <button class="primary" onclick="alert('当前为预览模式，请点击页面左上角的下拉列表进行页面切换！')">${jumpBtnText}</button>
                        </c:if>
                    </div>
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