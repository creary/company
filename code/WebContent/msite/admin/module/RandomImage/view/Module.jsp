<%@page language="java" contentType="text/html; charset=UTF-8"%>
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
<div id="RandomImage_imagesCtn" style="margin:0 auto;"></div>
<script type="text/javascript">
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery");
	
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
	
	try{
		var bigShowImages = JSON.parse('${RandomImage_images}' || "null");
		if(bigShowImages && bigShowImages.length > 0){
			var bigShow = getRandomItems(bigShowImages, 1)[0];
			jQuery("#RandomImage_imagesCtn")
				.html('<a' + (bigShow.href ? (' href="' + bigShow.href + '"') : '') + '><img src="' + bigShow.src + '" style="width:100%;" /></a>');
		}
	}
	catch(err){
		jQuery("#RandomImage_imagesCtn")
			.html('<div style="text-align:center; padding: 2em;">图片加载错误！');
	}
});
</script>            	
