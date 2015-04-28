<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
orderjs.config("shim", {
	"open.jquery.jquery" : {
		exports : "jQuery"
	}
});
orderjs(
	"open.jquery.jquery"
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery");
	
	var debugMode = false;
	window.debug = function(msg){
		if(!debugMode) return;
		var t = new Date(),
			msgHtml = ["<div>&gt; ", t.getMinutes(), ":", t.getSeconds(), " ", msg, "</div>"].join("");
		jQuery("#debugInfo").append(msgHtml);
	};
	
	var ua = navigator.userAgent.toLowerCase(),
		isWechat = ua.indexOf("micromessenger") > -1;
	
	debug(this + ' isWechat = ' + isWechat + '');
	function showPopupGuide(){
		var clipHieght,
			winHeight = jQuery(window).height(),
			guideHeight = jQuery("#ShareGuide_popupGuide").height(),
			padHeight = parseInt(jQuery(document.body).css("padding-bottom"));
		
		clipHieght = ((winHeight > guideHeight) ? guideHeight : winHeight) - padHeight;
		/*alert(" winHeight = " + winHeight + 
			  "\n guideHeight= " + guideHeight+ 
			  "\n clipHieght= " + clipHieght);*/
		jQuery(document.body)
			.scrollTop(0)
			.addClass("share_guide_fullscreen")
			.height(clipHieght)
			.parent()
			.addClass("share_guide_fullscreen")
			.height(clipHieght);
		jQuery("#ShareGuide_popupGuide")
			.show();
	}
	
	function hidePopupGuide(){
		jQuery(document.body)
			.removeClass("share_guide_fullscreen")
			.height("auto")
			.parent()
			.removeClass("share_guide_fullscreen")
			.height("auto");
		jQuery("#ShareGuide_popupGuide")
			.hide();
	}
	
	function jumpTo(url){
		if(!document.getElementById('ShareGuide_acceptAgreementChk').checked){
			alert("您必须接受《免费WiFi用户使用协议》才能继续使用 WiFi！");
			return;
		}
		location.href=url;
	}
	
	function onBridgeReady() {
		isWechat = true;
	}
	
	if(typeof WeixinJSBridge != "undefined"){
		onBridgeReady();
	}
	else{
		document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	}
	
	var recheckDelay = 1000,
		successMsg = "感谢您的分享，关闭本页面后即可上网！",
		url_checkShared = "${base}wifiant/checkShared.do?sta=${sta}&ssid=${ssid}";
	function recheckShared(){
		setTimeout(checkShared, recheckDelay);
	}
	function checkShared(){
		jQuery.ajax({
			url : url_checkShared,
			dataType : "json",
			cache : false,
			success : function(rspData){
				var data = rspData.data;
				
				if(rspData.status == 1){
					var shared = data.shared,
						url = data.url;
					
					if(shared){
						if(jQuery.type(url) == "string") window.location.replace(url);
						else alert(successMsg);
					}
					else{
						recheckShared();
					}
				}
			},
			error : function(status){
				recheckShared();
			}
		});
	}
	orderjs.ready(function(){
		jQuery(document.body)
			.addClass("share_guide_padding");
		jQuery("#ShareGuide_link_anotherWay")
			.on("click", function(){
				jumpTo(jQuery(this).attr("data-href"));
			});
		jQuery("#ShareGuide_btn_showGuide")
			.on("click", showPopupGuide)
			.html("分享上网");
		
		if(isWechat){//prepare to share
			jQuery("#ShareGuide_popupGuide")
				.on("click", hidePopupGuide)
				.find(".menu_guide")
				.show();
			
			checkShared();
		}
		else{//prepare to show wechat share guide
			jQuery("#ShareGuide_btn_hidePopup")
				.on("click", hidePopupGuide);
			jQuery("#ShareGuide_popupGuide .app_guide")	
				.show();
		}
	});
});
</script>
<link rel="stylesheet" type="text/css" href="${moduleContext}ShareGuide/view/css/style.css?_v=${cacheBuster}"/>
<div id="ShareGuide" class="share_guide">
    <div class="action_bar">
        <div class="action_iwrap">
            <p class="p5 tip">
                您需要分享本文章到朋友圈，才能正常上网。
            </p>
            <div class="p10">
                <button class="btn primary" id="ShareGuide_btn_showGuide">正在加载，请稍后...</button>
            </div>
        </div>
        <div class="tip p5">
            如暂不分享，<a href="javascript:void(0);" id="ShareGuide_link_anotherWay" data-href="${base}${authString}">请点击此处使用其它方式登录</a>
        </div>
        <div class="tip p5">
            <input type="checkbox" id="ShareGuide_acceptAgreementChk" checked="checked" />
            <label for="ShareGuide_acceptAgreementChk"> 我已阅读并接受</label> <a href="${base}acs/toWifiUserAgreement.do"  target="userAgreement" tabindex="-1">《免费WiFi使用协议》</a>
        </div>
    </div>
    <div id="ShareGuide_popupGuide" class="popup_guide">
        <div class="guide_content menu_guide">
            <p class="pointer">
                <img src="${base}resources/image/default/help/wechat_menu_pointer.png" border="0" />
            </p>
            <p class="p10 text">
                点击右上角菜单，选择"分享至朋友圈"后，即可直接上网，无需登录！
            </p>
            <p class="p10">
                <button class="btn primary">我知道了</button>
            </p>
        </div>
        <div class="guide_content app_guide">
            <div class="text">
            	<button class="btn white right" id="ShareGuide_btn_hidePopup">取消</button>
                请按如下步骤操作：
                <ol class="p10" style="padding-left:1.5em; list-style-type:decimal;">
                	<li>打开微信，点击底部“发现”，再点击“购物”;</li>
                    <li>此时微信将打开文章分享页，<br />分享该文章至朋友圈，即可上网!</li>
                </ol>
            </div>
            <p class="p10" style="margin-top:1em;">
                <a href="weixin://"><button class="btn primary">打开微信</button></a>
            </p>
            <p class="p5 tip">
            	如无法直接打开微信，请手动打开。
            </p>
        </div>
    </div>
</div>
