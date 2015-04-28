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
    
	rl.onReady(function() {
		var uploadbutton = K.uploadbutton({
			button : K('#chooseTopAD')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#topADUrlField').val(url);
					K('#topAD').attr("src", url);
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
    <h3>关于欢迎页</h3>
    <p class="p5">
        未通过认证的用户连接 WiFi 后，打开浏览器访问任意网页都会被强制跳转至该页面，用户只有点击页面上的跳转按钮才能继续。<br />
        如果当前认证策略的“免认证”已开启，则用户点击跳转按钮后，无需认证，待页面跳转至认证后页以后，即可开始上网；<br />
		如果当前认证策略的“免认证”已关闭，则用户点击跳转按钮后，将跳转至认证页，通过认证后，才能开始上网。
    </p>
</div>
<div class="section">
    <h3>顶部广告条</h3>
    <p class="p5">
    	显示在页面顶部的广告图片。文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：宽 500 像素，高 100 像素。
        <input id="topADUrlField" type="hidden" name="topADUrl" value="${topADUrl}" />
        <input type="button" id="chooseTopAD" class="btn white" value="选择广告图片">
	</p>
    <p class="p10">
        <img id="topAD" src="${topADUrl}" />
    </p>
</div>
<div class="section">
    <h3>上网操作提示</h3>
    <p class="p5">
    	文字将自动居中，请用"&lt;br /&gt;"换行。
    </p>
    <p class="p10">
    	<input class="field" name="startTip" value="${startTip}" />
    </p>
</div>
<div class="section">
    <h3>WiFi 用户协议</h3>
    <p class="p5">
    	请用"&lt;br /&gt;"换行。
    </p>
    <p class="p10">
        <textarea class="field" style="height:300px;" name="wifiAgreement">${wifiAgreement}</textarea>
    </p>
</div>
<div class="section">
    <h3>跳转按钮文字</h3>
    <p class="p10">
        <input class="field" name="jumpBtnText" value="${jumpBtnText}" />
    </p>
</div>


<script type="text/javascript">
orderjs.config("shim", {
	"open.jsrender.jsrender" : {
		deps : ["open.jquery.jquery"]
	},
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
	"open.jquery.jquery",
	(typeof JSON == "object") ? "" : "open.json.json2",
	"cloudac:SortableCardsEditor"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		SortableCardsEditor = orderjs("cloudac:SortableCardsEditor");
	//rl.console.showOnReady();
	var adImages = JSON.parse(jQuery("#adImagesField").val() || "[]");
	
	function updateAdImageFields(){
		var adImages = adImagesEditor.getAllValues();
		rl.debug(this + ' adImages.length = ' + adImages.length + '');
		
		var adImagesHtml = rl.map(adImages, function(ad){
			var href = ad.href ? (' href="' + ad.href + '"') : '';
			return '<a' + href + '>' +
						'<img class="ad" src="' + ad.src + '" />' + 
					'</a>';
		}).join("");
		
		jQuery("#adImagesField").val(JSON.stringify(adImages));
		jQuery("#adImagesHtmlField").val(adImagesHtml);
	}
	
	var adImagesEditor = new SortableCardsEditor({
		target : "#adImages_editor",
		iniValues : adImages,
		itemTpl : "#adImageTpl",
		updateItemView : function(itemEle, values){
			jQuery(".thumbnail", itemEle)
				.attr({
					"src" : values.src,
					"data-href" : values.href
				});
		}
	});
	adImagesEditor.on("itemEdit", updateAdImageFields);
	adImagesEditor.on("itemsChange", updateAdImageFields);
	
	orderjs(
		"open.kindeditor.lang.zh_CN",
		"cloudac:CardEditor"
	);
	orderjs(function(){
		var K = orderjs("open.kindeditor.kindeditor"),
			CardEditor = orderjs("cloudac:CardEditor");
		
		var adImageEditor = new CardEditor({
			owner : adImagesEditor,
			editDialogContent : "adImageEditor",
			getEditingValues : function(){
				return {
					src : jQuery("#editingAdImage").attr("src"),
					href : jQuery("#editingAdImageHref").val()
				};
			},
			setEditingValues : function(values){
				jQuery("#editingAdImage").attr("src", values.src || "");
				jQuery("#editingAdImageHref").val(values.href || "");
			}
		});
		adImagesEditor.itemEditor = adImageEditor;
		
		var uploadAdImage = K.uploadbutton({
			button : K('#uploadAdImage')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					rl.debug(this + ' url = ' + url + '');
					K('#editingAdImage').attr("src", url);
					rl.debug(this + ' K("#editingAdImage").attr("src") = ' + K('#editingAdImage').attr("src") + '');
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadAdImage.fileBox.change(function(e) {
			uploadAdImage.submit();
		});
	});
});
</script>
<link href="${cssPath}editable_cards.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.ad_images_ctn {
	width:160px;
	margin:1em 0;
	border:solid 1px #ddd;
}
.ad_images_ctn .soof_editable_cards li {
	float:none;
	width:140px;
	margin:6px auto;
}
</style>

<input id="adImagesField" type="hidden" name="adImages" value='${adImages}' />
<input id="adImagesHtmlField" type="hidden" name="adImagesHtml" value='${adImagesHtml}' />
<div id="adImageEditor" class="soof_page" style=" display:none; width:600px; height:250px; overflow:auto;">
    <div style="padding:2em;">
        <div class="section first">
            <h3>图片文件</h3>
            <p class="p5">
            	文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：宽 150 像素，高度不限，但各图片间应尽量保持一致。
            </p>
            <p class="p10">
                <img id="editingAdImage" style="width:200px; height:100px;" align="top" />
                <input type="button" id="uploadAdImage" class="btn white" value="上传图片">
            </p>
        </div>
        <div class="section">
            <h3>图片链接 URL</h3>
            <p class="p5">用户点击图片时，打开的页面 URL。</p>
            <p class="p10">
            	<input class="field" id="editingAdImageHref" /> <span class="desc">(可不填)</span>
            </p>
        </div>
    </div>
</div>
<script id="adImageTpl" type="text/x-jsrender">
    <li>
        <div class="card">
            <img class="overlay trigger_delete_item" src="${imagePath}circle_close.gif" alt="删除" title="删除" />
            <img class="thumbnail" src="{{>src}}" data-href="{{>href}}" />
            <a class="btn" href="javascript:void(0);">
                <div>编辑</div>
            </a>
        </div>
    </li>
</script>
<div class="section">
    <h3>右侧边栏广告</h3>
    <p class="p5">
    	显示在右侧边栏的广告图片，可添加多个，拖动图片可排序。
	</p>
    <div class="ad_images_ctn">
        <ul id="adImages_editor" class="soof_editable_cards">
            <li class="static_item trigger_add_item">
                <div class="card static_card">
                    <a class="btn" href="javascript:void(0);">
                        <div>添加图片</div>
                    </a>
                </div>
            </li>
        </ul>
        <div class="clearer"></div>
    </div>
</div>
