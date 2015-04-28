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
<script type="text/javascript" src="${jsPath}rl/src/order.js?_v=${cacheBuster}"></script>
<style type="text/css">
.soof_fortunecat_welcome{}
.soof_fortunecat_welcome .viewport{
	max-width:700px; 
	margin:0 auto;
}
.soof_fortunecat_welcome .logo_wrap{
	width:300px;
	height:120px;
	margin:0 auto;
	background:url(${currentTplContext}view/theme/default/img/hemisphere_n.jpg?_v=${cacheBuster}) center 0 no-repeat;
	text-align:center;
}
.soof_fortunecat_welcome .logo{
	margin-top:8%;
	max-width:300px;
	max-height:60px;
}
.soof_fortunecat_welcome .start_tip{
	margin:1em auto 0 auto;
	max-width:80%; 
	padding:2px 0;
	border-radius:5px; 
	background-color:#F60; 
	color:#fff;
}

.soof_fortunecat_welcome .section_jump{
	border-top:solid 1px #999;
	text-align:center;
	font-family:"微软雅黑";
	background-color:#eee;
}
.soof_fortunecat_welcome .agreement_ctn{
	padding-top:12px;
	color:#999;
}
.soof_fortunecat_welcome .agreement_ctn a{
	color:#999;
}
.soof_fortunecat_welcome .jump_btn{
	height:60px;
	line-height:60px;
	width:250px;
	margin-top:12px;
	opacity:1;
	border:solid 1px #ccc;
	border-radius:5px;
	background-color:#09C;
	color:#fff;
	font-size:24px;
}
.soof_fortunecat_welcome .jump_btn:hover{
	opacity:0.8;
}
.soof_fortunecat_welcome .copyright{
	padding:24px 0;
	line-height:24px;
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
}
.soof_tiles .col2{
	width:100%;
}
</style>
</head>
<body>
    <div class="soof_fortunecat_welcome">
        <div class="viewport">
            <div class="header">
                <div class="logo_wrap">
                	<img class="logo" src="${msite_logo}" />
                    <div class="start_tip">${startTip}</div>
                </div>
            </div>
            <div class="">
                <div id="imageAdTile_tiles" class="soof_tiles">
                    <div class="soof_clearer"></div>
                </div>
                <script id="imageAdTile_tileTpl" type="text/x-jsrender">
                    <a class="tile col{{>cols}}" 
                        href="{{>link}}">
                        <img src="{{>src}}" />
                    </a>
                </script>
                <script type="text/javascript">
                orderjs.config("shim", {
					"open.jquery.jquery" : {
						exports : "jQuery"
					},
                    "open.jsrender.jsrender" : {
                        deps : ["open.jquery.jquery"]
                    }
                });
                orderjs(
                    "open.jsrender.jsrender"
                );
                orderjs(function(){
                    var jQuery = orderjs("open.jquery.jquery");
                    var cnt,
                        tiles = JSON.parse('${adImages}' || "null");
                        
                    cnt = tiles ? 
                        jQuery("#imageAdTile_tileTpl").render(tiles) : "";
                    
                    jQuery("#imageAdTile_tiles").prepend(cnt);
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
                <div class="agreement_ctn">
                    <input type="checkbox" id="acceptAgreementChk" checked="checked" />
                    <label for="acceptAgreementChk">
                        我已阅读并接受</label> <a href="${base}acs/toWifiUserAgreement.do" style="text-decoration:underline; color:#090;" target="userAgreement" tabindex="-1">《免费Wi-Fi使用协议》</a>
                </div>
                <c:if test="${preview eq null}">
                    <c:if test="${auth == false}">    
                        <button class="jump_btn" onclick="jumpTo('${base}wifiant/freeAuth.do?${queryString}', this)">${jumpBtnText}</button>
                    </c:if>
                    <c:if test="${auth}">    
                        <button class="jump_btn" onclick="jumpTo('${base}wifiant/signin/view.do?${queryString}', this)">${jumpBtnText}</button>
                    </c:if>
                </c:if>            
                <c:if test="${preview ne null}">
                    <button class="jump_btn" onclick="alert('当前为预览模式，请点击左上角的下拉列表进行页面切换！')">${jumpBtnText}</button>
                </c:if>            
                <div class="copyright">
                    ${msite_copyright}
                </div>
            </div>
        </div>
    </div>
</body>
</html>