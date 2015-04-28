<%@page language="java" contentType="text/html; charset=UTF-8"%>
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
	var banners = JSON.parse(jQuery("#BannerSlide_bannersField").val() || "[]");
	
	function updateBannerFields(){
		var banners = bannerSlideEditor.getAllValues();
		rl.debug(this + ' banners.length = ' + banners.length + '');
		var bannersHtml = rl.map(banners, function(banner){
			var href = banner.href ? (' href="' + banner.href + '"') : '';
			return '<a' + href + '>' +
						'<img src="' + banner.src + '" />' + 
					'</a>';
		}).join("");
		
		jQuery("#BannerSlide_bannersField").val(JSON.stringify(banners));
		jQuery("#BannerSlide_bannersHtmlField").val(bannersHtml);
		
		var fisrtThumbnail = jQuery("#BannerSlide_editor .thumbnail").eq(0);
		if(fisrtThumbnail){
			var sizeCalc = fisrtThumbnail.clone().appendTo(rl.dom.domWarehouse);
			jQuery("#BannerSlide_sizeField").val("" + sizeCalc.width() + "," + sizeCalc.height());
			sizeCalc.remove();
		}
	}
	
	var bannerSlideEditor = new SortableCardsEditor({
		target : "#BannerSlide_editor",
		iniValues : banners,
		itemTpl : "#BannerSlide_bannerTpl",
		updateItemView : function(itemEle, values){
			jQuery(".thumbnail", itemEle)
				.attr({
					"src" : values.src,
					"data-href" : values.href
				});
		}
	});
	bannerSlideEditor.on("itemEdit", updateBannerFields);
	bannerSlideEditor.on("itemsChange", updateBannerFields);
	
	bannerSlideEditor.on("beforeAddItem", function(listItem){
		var len = bannerSlideEditor.getItems().length;
		if(len >= 5){
			alert("轮播图片最多只能有 5 张，请先删除其它图片再增加!");
			return false;
		}
	});
	bannerSlideEditor.on("beforeDeleteItem", function(listItem){
		var len = bannerSlideEditor.getItems().length;
		if(len <= 2){
			alert("图片至少保留 2 张才能轮播，请先增加其它图片再删除!");
			return false;
		}
	});
	
	orderjs(
		"open.kindeditor.lang.zh_CN",
		"cloudac:CardEditor"
	);
	orderjs(function(){
		var K = orderjs("open.kindeditor.kindeditor"),
			CardEditor = orderjs("cloudac:CardEditor");
		
		var bannerEditor = new CardEditor({
			owner : bannerSlideEditor,
			editDialogContent : "BannerSlide_bannerEditor",
			getEditingValues : function(){
				return {
					src : jQuery("#BannerSlide_editingBanner").attr("src"),
					href : jQuery("#BannerSlide_editingBannerHref").val()
				};
			},
			setEditingValues : function(values){
				jQuery("#BannerSlide_editingBanner").attr("src", values.src || "");
				jQuery("#BannerSlide_editingBannerHref").val(values.href || "");
			}
		});
		bannerSlideEditor.itemEditor = bannerEditor;
		
		var uploadBanner = K.uploadbutton({
			button : K('#BannerSlide_uploadBanner')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					rl.debug(this + ' url = ' + url + '');
					K('#BannerSlide_editingBanner').attr("src", url);
					rl.debug(this + ' K("#BannerSlide_editingBanner").attr("src") = ' + K('#BannerSlide_editingBanner').attr("src") + '');
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadBanner.fileBox.change(function(e) {
			uploadBanner.submit();
		});
	});
});
</script>
<link href="${cssPath}editable_cards.css" rel="stylesheet" type="text/css" />

<input id="BannerSlide_bannersField" type="hidden" name="BannerSlide_banners" value='${BannerSlide_banners}' />
<input id="BannerSlide_bannersHtmlField" type="hidden" name="BannerSlide_bannersHtml" value='${BannerSlide_bannersHtml}'  />
<input id="BannerSlide_sizeField" type="hidden" name="BannerSlide_size" value='${BannerSlide_size}' />
<div id="BannerSlide_bannerEditor" class="soof_page" style=" display:none; width:600px; height:200px;">
    <div style="padding:2em;">
        <div class="section first">
            <h3>图片文件</h3>
            <p class="p5">
            	文件大小不超过 1MB；格式为: gif, jpg, png；设计师建议的大小为：宽 500 像素，高 200 像素。<br />
                <span style="color:red;">注意：各图片间的宽高应保持一致，如不一致则部分轮播图片底部会出现空白。</span>
            </p>
            <p class="p10">
                <img id="BannerSlide_editingBanner" src="" style="width:200px; height:100px;" align="top" />
                <input type="button" id="BannerSlide_uploadBanner" class="btn white" value="上传图片">
            </p>
        </div>
        <div class="section">
            <h3>图片链接 URL</h3>
            <p class="p5">用户点击图片时，打开的页面 URL。</p>
            <p class="p10">
            	<input class="field" id="BannerSlide_editingBannerHref" /> <span class="desc">(可不填)</span>
            </p>
        </div>
    </div>
</div>
<script id="BannerSlide_bannerTpl" type="text/x-jsrender">
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
    <h3>海报图片轮播</h3>
    <p class="p5">
    	以轮播图片的形式展现商家的品牌形象。您可以设置图片及其轮播次序（可拖动排序），图片数量限制在 2~5 张。
    </p>
    <ul id="BannerSlide_editor" class="soof_editable_cards p10">
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
