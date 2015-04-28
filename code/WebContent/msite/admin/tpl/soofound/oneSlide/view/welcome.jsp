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
<script type="text/javascript" src="${jsPath}rl/src/order.js?_v=${cacheBuster}"></script>
<script type="text/javascript">

var debugMode = true;
window.debug = function(msg){
	if(!debugMode) return;
	var t = new Date(),
		msgHtml = ["<div>&gt; ", t.getMinutes(), ":", t.getSeconds(), " ", msg, "</div>"].join(""),
		msgEle = document.createElement("span");
	msgEle.innerHTML = msgHtml;
	document.getElementById("debugInfo").appendChild(msgEle);
};

//orderjs.config("disableCache", true);
orderjs.config("shim", {
	"open.jquery.jquery" : {
		exports : "jQuery"
	},
	"open.jsrender.jsrender" : {
		deps : ["open.jquery.jquery"]
	}
});
orderjs(
	"open.jquery.jquery",
	"open.jsrender.jsrender",
	(typeof JSON == "object") ? "" : "open.json.json2"
);
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery");
	
	orderjs.ready(function(){
		function showLoadSlideTip(msg){
			jQuery("#oneslide .swipe_wrap")
				.html('<div style="text-align:center; padding: 2em;">' + msg + '</div>');
		}
		try{
			var slideImages = JSON.parse('${slideImages}' || "null");
			if(slideImages && slideImages.length > 0){
				var cnt = jQuery("#slideImageTpl").render(slideImages);
				
				jQuery("#oneslide .swipe_wrap").html(cnt);
			}
			else{
				showLoadSlideTip("图片加载错误：无法获取图片数据！");
				return;
			}
		}
		catch(err){
			showLoadSlideTip("图片加载错误：数据解析失败！");
			return;
		}
		
		orderjs(
			"open.Swipe.Swipe"
		);
		orderjs(function(){
			var jQuery = orderjs("open.jquery.jquery");
			
			var slidesLen = slideImages.length;
			if(!(slidesLen >= 2)) return;
			if(slidesLen == 2) slidesLen = 4;//Swipe hack: special case if two slides
			
			var slideDuration = parseInt("${duration}") || 30,
				slideSpeed = 0.5,
				slideInterval = (slideDuration - (slidesLen - 1) * slideSpeed) / slidesLen,
				waitText = "请稍候 {0}s",
				endCountdownText = "正在打开...",
				previewEndCountdownText = "当前为预览模式<br />请手动切换页面",
				previewMode = Boolean(${preview}),
                <c:if test="${!auth}">    
                    jumpUrl = '${base}wifiant/freeAuth.do?${queryString}';
                </c:if>
                <c:if test="${auth}">    
                    jumpUrl = '${base}wifiant/signin/view.do?${queryString}';
                </c:if>
			
			var tplRE = /\{([\w-]+)(?:\:([\w\.]*)(?:\((.*?)?\))?)?\}/g,
				format = function(source, values){
					return (source.replace(tplRE, 
						function(m, m1) {
							var v = values[m1];
							if(v === undefined)v = "";
							return v;
						}
					));
				};
			var oneslide = jQuery('#oneslide').Swipe({
				auto: slideInterval * 1000,
				speed : slideSpeed * 1000,
				continuous: true,
				countdownTip : "#countdownTip",
				callback: function(index, element) {
					var nav = jQuery("#oneslideNav");
					nav.find("i").removeClass("active");
					nav.find("i:eq(" + index + ")")
						.addClass("active");
				},
				transitionEnd: function(index, element) {
					var sw = oneslide.data('Swipe');
					sw.stop();
					sw.begin();
				}
			});
			
			var countdownTimer = null,
				countdownNum = slideDuration;
			
			function updateCountdownTip(num){
				var text = format(waitText, [num]);
				jQuery("#countdownTip").html(text);
			}
			function startCountdown(){
				updateCountdownTip(countdownNum);
				countdown();
			}
			function onEndCountdown(){
				if(previewMode){
					jQuery("#countdownTip").html(previewEndCountdownText);
				}
				else{
					jQuery("#countdownTip").html(endCountdownText);
					location.href = jumpUrl;
				}
			}
			function countdown(){
				countdownTimer = window.setTimeout(function(){
					countdownNum -= 1;
					if(countdownNum > 0){
						updateCountdownTip(countdownNum);
						countdown();
					}
					else{
						onEndCountdown();
					}
				}, 1000);
			}
			
			function genNavItems(){
				var num = oneslide.data('Swipe').getNumSlides(),
					html = ["<i class='active'></i>"];
				html.push(new Array(num).join("<i></i>"));
				jQuery('#oneslideNav .content_wrap').html(html);
			}
			
			var elm = jQuery('#oneslideNav');
			var headerHeight = jQuery(".viewport .header").height(),
				mainHeight = jQuery(".viewport .main").height(),
				totalHeight = headerHeight + mainHeight,
				viewportHeight = jQuery(window).height();
			
			//debug(' totalHeight = ' + totalHeight + '');
			/*console.log("totalHeight = "+totalHeight);
			console.log("viewportHeight = "+viewportHeight);*/
			function fixOnScroll(){
				var scrollTop = jQuery(window).scrollTop();
				var useFixed = ((scrollTop + viewportHeight - totalHeight) < 0);
				/*console.log("useFixed = " + useFixed);
				console.log("bottom view pos = " + (scrollTop + viewportHeight));*/
				
				//debug("pos = " + (scrollTop + viewportHeight));
				jQuery(elm)
					.css('position',(useFixed ? 'fixed' : 'absolute'));
			}
			function deferFixOnScroll(){
				setTimeout(fixOnScroll, 1);
			}
			jQuery(window).on("scroll", deferFixOnScroll);
			jQuery(document).on("touchmove touchend", deferFixOnScroll);
			jQuery(window).on("resize", function(){
				viewportHeight = jQuery(window).height();
				fixOnScroll();
			});
			
			startCountdown();
			genNavItems();
			fixOnScroll();
		});
	});
});
</script>
<link rel="stylesheet" type="text/css" href="${currentTplContext}view/theme/default/main.css?_v=${cacheBuster}"/>
</head>
<body class="soof_oneslide">
    <div class="viewport">
        <div class="header">
            <div class="logo_wrap">
                <img class="logo" src="${msite_logo}" />
            </div>
        </div>
		<div class="main">
            <div class="float_tip">
                <div class="content_wrap">
                    <span id="countdownTip">数据加载中...</span>
                    <span id="debugInfo"></span>
                </div>
            </div>
        	<div id="oneslide" class="swipe">
                <div class="swipe_wrap"></div>
            </div>
            <script id="slideImageTpl" type="text/x-jsrender">
                <div>
                    <img src="{{>src}}" />
                </div>
            </script>      	
            <div id="oneslideNav" class="slides_nav">
            	<div class="content_wrap"></div>
            </div>
        </div>
        <div class="foot">
            <div class="copyright">
                ${msite_copyright}
            </div>
        </div>
    </div>
</body>
</html>