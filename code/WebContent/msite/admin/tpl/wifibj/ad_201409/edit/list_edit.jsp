<%@page language="java" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
orderjs.config("shim", {
	"open.kindeditor.kindeditor" : {
		deps : ["css>open.kindeditor.themes.default.default"],
		exports : "KindEditor"
	},
	"open.kindeditor.lang.zh_CN" : {
		deps : ["open.kindeditor.kindeditor", "css>open.kindeditor.themes.default.default"],
		exports : "KindEditor"
	}
});

orderjs(
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"open.jquery.jquery",
    "open.kindeditor.lang.zh_CN"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		K = orderjs("open.kindeditor.kindeditor");
    
	rl.onReady(function() {
		var uploadbutton = K.uploadbutton({
			button : K('#chooseBanner')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#bannerUrl').val(url);
					K('#banner').attr("src", url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadbutton.fileBox.change(function(e) {
			uploadbutton.submit();
		});
	});
});

</script>
<div class="section first">
    <h3>关于商讯列表页</h3>
    <p class="p5">
        系统为每一个商讯分类生成一个页面用于显示该分类的商讯列表，该页面即为商讯列表页。
    </p>
</div>
<div class="section">
    <h3>海报图片</h3>
    <p class="p5">
        商讯列表顶部显示的海报图片。文件大小不超过 1MB；格式为: gif, jpg, png；设计师建议的大小为：宽 500 像素，高 150 像素。
        <input type="button" id="chooseBanner" class="btn white" value="选择图片文件">
        <input type="hidden" id="bannerUrl" name="banner" value="${banner}" />
    </p>
    <div class="p10">
    	<img id="banner" class="img_field" name="banner" src="${banner}" border="0" />
    </div>
</div>
