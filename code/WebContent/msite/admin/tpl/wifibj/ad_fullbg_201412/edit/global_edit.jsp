<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
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
    
	rl.onReady(function() {
		var uploadbutton_msiteLogo = K.uploadbutton({
			button : K('#choose_msite_logo')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#field_msite_logo').val(url);
					K('#img_msite_logo').attr("src", url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadbutton_msiteLogo.fileBox.change(function(e) {
			uploadbutton_msiteBg.submit();
		});
		
		var uploadbutton_msiteBg = K.uploadbutton({
			button : K('#choose_msite_bg')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#field_msite_bg').val(url);
					K('#img_msite_bg').attr("src", url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadbutton_msiteBg.fileBox.change(function(e) {
			uploadbutton_msiteBg.submit();
		});
		
	}); 
});

</script>
<style type="text/css">
#msiteLogoWrap .soof_img_viewer{
	height:80px;
}
#msiteBgWrap .soof_img_viewer{
	width:180px;
	height:250px;
}
</style>
<div class="section first">
    <h3>关于 Portal</h3>
    <p class="p5" style="float:right; text-align:center;">
        <a href="${base}portal/readyPreview.do?id=${pid}" target="sitePreview" title="预览 Portal"><img src="${base}msite/admin/global/edit/img/cover_msite.jpg" width="165" height="71" /></a><br /><br />
        <button class="btn white" onclick="window.open('${base}portal/readyPreview.do?id=${pid}', 'sitePreview')">预览 Portal</button>
    </p>
    <p class="p5" style="margin-right:200px;">
        Portal 指为用户提供 WiFi 上网认证功能的微型门户网站。一般是由欢迎页、认证页、认证后页等页面构成。您可以在各页面设置广告或商讯等内容。
    </p>
    <div class="clearer"></div>
</div>
<div class="section">
    <h3>网站标题</h3>
    <p class="p10">
        <input class="field" name="msite_title" value="${msite_title}" />
    </p>
</div>
<div class="section">
    <h3>网站 Logo</h3>
    <p class="p5">
        选择网站的 logo 图片，为更好地与背景图片搭配，请使用透明的 png 图片；设计师建议的大小为：宽 300 像素，高 80 像素。
    </p>
    <div class="p10" id="msiteLogoWrap">
        <div class="soof_img_viewer">
            <input id="field_msite_logo" type="hidden" name="msite_logo" value="${msite_logo}" />
            <img src="${msite_logo}" id="img_msite_logo" class="img_holder" />
            <div class="mask">图片</div>
        </div>
        <div class="soof_img_viewer_upload_wrap">
            <span><input type="button" id="choose_msite_logo" class="btn white" value="选择图片文件"></span>
        </div>
        <div class="clearer"></div>
    </div>
</div>     
<div class="section">
    <h3>网站背景图</h3>
    <p class="p5">
        网站各页面通用的背景图，格式为 png、jpg、gif；设计师建议的大小为：宽 500 像素，高 800 像素。
    </p>
    <div class="p10" id="msiteBgWrap">
        <div class="soof_img_viewer">
            <input id="field_msite_bg" type="hidden" name="msite_bg" value="${msite_bg}" />
            <img src="${msite_bg}" id="img_msite_bg" class="img_holder" />
            <div class="mask">图片</div>
        </div>
        <div class="soof_img_viewer_upload_wrap">
            <span><input type="button" id="choose_msite_bg" class="btn white" value="选择图片文件"></span>
        </div>
        <div class="clearer"></div>
    </div>
</div>
