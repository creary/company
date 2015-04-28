<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="section first">
	<script type="text/javascript">
		orderjs(
			"open.jquery.jquery",
			"cloudac:fx"
		);
		orderjs(function(){
			orderjs.ready(function(){
				var jQuery = orderjs("open.jquery.jquery"),
					ZoomViewer = orderjs("cloudac:fx").ZoomViewer;
				
				ZoomViewer(".soof_img_zoomviewer");
			});
		});
	</script>
    <h3>关于分享页</h3>
    <div class="soof_img_zoomviewer">
    	<div class="iwrap">
            <div class="bar"><i class="soof_icon zoom_gray"></i></div>
            <img class="main" src="${base}resources/image/default/help/wechat_share_page.jpg" />
        </div>
    </div>
    <div class="p5" style="margin-right:200px;">
        分享页用于引导用户将商讯（如促销、抽奖、产品宣传等信息）分享到其社交圈（如微信朋友圈），以达到口碑营销的目的。<br />
        分享页由 2 部分组成：分享内容区域 + 分享上网指引（如右图所示）；分享内容区域用于显示商讯，您可以在【营销管理】 > 【商讯管理】模块中对其进行管理。为了建立更好的分享体验，平台对分享认证的机制进行了规范，<a href="${base}acs/help/wechatShareAuth.jsp#nch_notice" target="_blank">点此了解更多。</a>
    </div>
    <div class="clearer"></div>
</div>
<jsp:include page="${moduleRoot}ShareArticleCovers/edit/module_edit.jsp" />
