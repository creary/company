<%@page language="java" contentType="text/html; charset=UTF-8"%>
<link href="${jsPath}rl/src/open/SlidesJS/css/slidesjs.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.slidesjs_slides a img{
	width:100%;
}
</style>
<div id="BannerSlide" class="slidesjs_slides" style="margin-top:0;">
    ${BannerSlide_bannersHtml}
</div>
<script type="text/javascript">
orderjs.config("shim", {
	"open.jquery.jquery" : {
		exports : "jQuery"
	},
	"open.SlidesJS.jquery-slides" : {
		deps : ["open.jquery.jquery"]
	}
});
orderjs(
	"open.jquery.jquery",
	"open.SlidesJS.jquery-slides"
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery");
	
	orderjs.ready(function(){
		var slideSize = "${BannerSlide_size}".split(",")
			width = parseInt(slideSize[0]),
			height = parseInt(slideSize[1]);
		
		if(!(width > 0)) width = 900;
		if(!(height > 0)) height = 400;
		jQuery('.slidesjs_slides').slidesjs({
			width: width,
			height: height,
			navigation: {
				active: false,
				effect: "slide"
			},
			pagination : {
				active: false
			},
			play: {
				active: false,
				auto: true,
				interval: 4000,
				swap: true
			}
		});
		
	});

});
</script>
