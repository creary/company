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
<link rel="icon" href="${imagePath}wifi_favicon.ico?_v=${cacheBuster}" type="image/x-icon"/>
<link href="${currentTplContext}view/theme/default/main.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<style type="text/css">
body.soof_fortunecat_welcome{
	font-family:"微软雅黑";
}
.soof_fortunecat_welcome .viewport{
	max-width:500px; 
	margin:0 auto;
	background-color:#eee;
}
.soof_fortunecat_welcome .header{
	height:45px;
	border-bottom:solid 1px #ccc;
}
.soof_fortunecat_welcome .logo{
	margin:8px 0 0 8px;
	height:30px;
}
.soof_fortunecat_welcome .section_jump{
	padding:20px 0;
	border-top:solid 1px #ccc;
	text-align:center;
}
.soof_fortunecat_welcome .agreement_ctn{
	padding-top:6px;
	color:#999;
}
.soof_fortunecat_welcome .agreement_ctn a{
	color:#999;
}
.soof_fortunecat_welcome .jump_btn{
	height:40px;
	width:200px;
	opacity:1;
	border:solid 1px #ccc;
	border-radius:5px;
	background-color:#09C;
	color:#fff;
	font-size:14px;
}
.soof_fortunecat_welcome .jump_btn:hover{
	opacity:0.8;
}
.soof_fortunecat_welcome .copyright{
	padding:24px 0;
	line-height:24px;
	text-align:center;
}
@media (min-width: 700px) {
.soof_fortunecat_welcome .viewport{
	border-left:solid 1px #ddd;
	border-right:solid 1px #ddd;
}
.soof_fortunecat_welcome .header{
	height:60px;
}
.soof_fortunecat_welcome .logo{
	margin:12px 0 0 12px;
	height:36px;
}
	
}


/* soof_tiles */
.soof_tiles{
}
.soof_tiles .tile{
	float:left;
	display:block;
	width:50%;
	text-decoration:none;
}
.soof_tiles .tile img{
	width:100%;
	height:100px;
}
.soof_tiles .col2{
	width:100%;
}
@media (min-width: 400px) {
.soof_tiles .tile img{
	height:100px;
}	
}
@media (min-width: 500px) {
.soof_tiles .tile img{
	height:125px;
}	
}
@media (min-width: 600px) {
.soof_tiles .tile img{
	height:150px;
}	
}
@media (min-width: 700px) {
.soof_tiles .tile img{
	height:175px;
}	
}

</style>
<script type="text/javascript" src="${jsPath}rl/src/order.js?_v=${cacheBuster}"></script>
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

function getRandomItems(arr, len){
	if(!(arr && arr.constructor === Array)) return [];
	var items = [],
		tmpArr = arr.concat();
	len = Math.min(tmpArr.length, parseInt(len));
	
	while(items.length < len){
		items.push(pullRandom(tmpArr));
	}
	
	return items;
}

function pullRandom(arr){
	var i = parseInt(arr.length * Math.random()),
		item = arr.splice(i, 1)[0];
	return item;
}

</script>            	
</head>
<body class="soof_fortunecat_welcome">
    <div class="viewport">
        <div class="header">
            <div class="logo_wrap">
                <img class="logo" src="${msite_logo}" />
            </div>
        </div>
        <div>
            <div id="bannerCtn" style="border-bottom:solid 1px #ddd;"></div>
            <script type="text/javascript">
            orderjs(function(){
                var jQuery = orderjs("open.jquery.jquery");
                try{
                    var bannerImages = JSON.parse('${bannerImages}' || "null");
                    if(bannerImages && bannerImages.length > 0){
                        var banner = getRandomItems(bannerImages, 1)[0];
                        jQuery("#bannerCtn")
                            .html('<a' + (banner.href ? (' href="' + banner.href + '"') : '') + '><img src="' + banner.src + '" style="width:100%;" /></a>');
                    }
                }
                catch(err){
                    jQuery("#bannerCtn")
                        .html('<div style="text-align:center; padding: 2em;">横幅图片加载错误：数据解析失败！');
                }
            });
            </script>            	
        </div>
        <div>
            <div id="imageAdTile_tiles" class="soof_tiles">
                <div class="soof_clearer"></div>
            </div>
            <script id="imageAdTile_tileTpl" type="text/x-jsrender">
                <a class="tile" {{if href}} href="{{>href}}" {{/if}}>
                    <img src="{{>src}}" />
                </a>
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
                        var picWallImages = JSON.parse('${picWallImages}' || "null");
                        if(picWallImages && picWallImages.length > 0){
                            var cnt,
                                tiles = picWallImages.length > 4 ? 
                                    getRandomItems(picWallImages, 4) :
                                    picWallImages;
                            cnt = jQuery("#imageAdTile_tileTpl").render(tiles);
                            
                            jQuery("#imageAdTile_tiles").prepend(cnt)
                        }
                    }
                    catch(err){
                        jQuery("#imageAdTile_tiles")
                            .html('<div style="text-align:center; padding: 2em;">图片墙加载错误：数据解析失败！');
                    }
                });
            </script>            	
        </div>
        <div class="section_jump">
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
            <c:if test="${preview eq null}">
                <c:if test="${auth == false}">    
                    <button class="jump_btn" onclick="jumpTo('${base}wifiant/freeAuth.do?${queryString}', this)">${jumpBtnText}</button>
                </c:if>
                <c:if test="${auth}">    
                    <button class="jump_btn" onclick="jumpTo('${base}wifiant/signin/view.do?${queryString}', this)">${jumpBtnText}</button>
                </c:if>
            </c:if>            
            <c:if test="${preview ne null}">
                <button class="jump_btn" onclick="alert('当前为预览模式，请点击页面左上角的下拉列表进行页面切换！')">${jumpBtnText}</button>
            </c:if>
            <div class="agreement_ctn">
                <input type="checkbox" id="acceptAgreementChk" checked="checked" />
                <label for="acceptAgreementChk">
                    我已阅读并接受</label> <a href="${base}acs/toWifiUserAgreement.do" style="text-decoration:underline; color:#090;" target="userAgreement" tabindex="-1">《免费Wi-Fi使用协议》</a>
            </div>
        </div>
        <div class="foot" style="background-color:#fff; border-top:solid 1px #ddd;">
            <div class="copyright">
                ${msite_copyright}
            </div>
        </div>
    </div>
</body>
</html>