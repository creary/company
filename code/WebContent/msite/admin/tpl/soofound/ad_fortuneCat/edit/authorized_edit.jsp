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
    "open.kindeditor.lang.zh_CN"
);
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		K = orderjs("open.kindeditor.kindeditor");
    
	//rl.console.showOnReady();
	rl.onReady(function() {
		var uploadTopAdImage = K.uploadbutton({
			button : K('#btnChooseTopAdImage')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#topAdImageField').val(url);
					K('#topAdImageHolder').attr("src", url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadTopAdImage.fileBox.change(function(e) {
			uploadTopAdImage.submit();
		});
		
		var uploadBigAdImage = K.uploadbutton({
			button : K('#btnChooseBigAdImage')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#bigAdImageField').val(url);
					K('#bigAdImageHolder').attr("src", url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadBigAdImage.fileBox.change(function(e) {
			uploadBigAdImage.submit();
		});
	}); 	
	
});

</script>
<div class="section first">
    <h3>关于认证后页</h3>
    <p class="p5">
    	认证后页即用户通过验证后显示的页面。
	</p>
</div>
<div class="section">
    <h3>验证通过提示文字</h3>
    <p class="p5">
    	文字将自动居中，请用"&lt;br /&gt;"换行。
    </p>
    <p class="p10">
    	<input class="field" name="passTip" value="${passTip}" />
    </p>
</div>

<div class="section">
    <h3>顶部广告条</h3>
    <p class="p5">
    	显示在页面顶部的广告图片。
	</p>
    <div class="fields_box">
        <p>图片文件</p>
        <p class="p5">
            文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：宽 500 像素，高 100 像素。
        </p>
        <p class="p10">
            <input id="topAdImageField" type="hidden" name="topAdImage" value="${topAdImage}" />
            <img id="topAdImageHolder" src="${topAdImage}" style="width:300px; height:80px;" align="top" />
            <input type="button" id="btnChooseTopAdImage" class="btn white" value="上传图片">
        </p>
        <p class="p10">图片链接 URL</p>
        <p class="p5">用户点击图片时，打开的页面 URL。</p>
        <p class="p10">
            <input class="field" id="topAdHrefField" name="topAdHref" value="${topAdHref}" />
        </p>
    </div>
</div>
<div class="section">
    <h3>大图广告</h3>
    <p class="p5">
    	显示在 logo 下方的广告图片。
	</p>
    <div class="fields_box">
        <p>图片文件</p>
        <p class="p5">
            文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：宽 500 像素，高度不限。
        </p>
        <p class="p10">
            <input id="bigAdImageField" type="hidden" name="bigAdImage" value="${bigAdImage}" />
            <img id="bigAdImageHolder" src="${bigAdImage}" style="width:200px;" align="top" />
            <input type="button" id="btnChooseBigAdImage" class="btn white" value="上传图片">
        </p>
        <p class="p10">图片链接 URL</p>
        <p class="p5">用户点击图片时，打开的页面 URL。</p>
        <p class="p10">
            <input class="field" id="bigAdHrefField" name="bigAdHref" value="${bigAdHref}" />
        </p>
    </div>
</div>
