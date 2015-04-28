<%@page language="java" contentType="text/html; charset=UTF-8"%>
<link href="${moduleContext}IconTile/view/style.css" rel="stylesheet" type="text/css" />
<div id="IconTile_tiles" class="soof_tiles">
    <div class="soof_clearer"></div>
</div>
<script id="IconTile_tileTpl" type="text/x-jsrender">
	<a class="tile col{{>cols}}" 
		href="{{>url}}"
		style="background-color:{{>bgColor}};">
		<div class="icon" style="background-image:url({{>icon}});"></div>
		<div class="text">{{>text}}</div>
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
	var cnt,
		tiles = JSON.parse('${IconTile_tiles}' || "null");
		
	cnt = tiles ? 
		jQuery("#IconTile_tileTpl").render(tiles) : 
		"<div style='padding:2em; text-align:center;'>磁贴未定义！</div>" ;
	
	jQuery("#IconTile_tiles").prepend(cnt);
});
</script>
