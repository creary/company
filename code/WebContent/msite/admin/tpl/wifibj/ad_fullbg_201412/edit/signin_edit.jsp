<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="section first">
    <h3>关于认证页</h3>
    <p class="p5">
        用户在该页面输入认证信息，认证通过后方能上网。
    </p>
</div>
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
		var uploadbutton_adOverSignIn_img = K.uploadbutton({
			button : K('#choose_adOverSignIn_img')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#field_adOverSignIn_img').val(url);
					K('#holder_adOverSignIn_img').attr("src", url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadbutton_adOverSignIn_img.fileBox.change(function(e) {
			uploadbutton_adOverSignIn_img.submit();
		});
		
	}); 
});

</script>
<style type="text/css">
#adOverSignIn_imgWrap .soof_img_viewer{
	width:200px;
	height:150px;
}
</style>
<div class="section">
    <h3>登录区顶部广告</h3>
    <p class="p5">
        位于登录区顶部的广告。
    </p>
    <div class="fields_box">
        <p class="p5">
            广告图片，格式为 png、jpg、gif；设计师建议的大小为：宽 200 像素，高 150 像素。
        </p>
        <div class="p10" id="adOverSignIn_imgWrap">
            <div class="soof_img_viewer">
                <input id="field_adOverSignIn_img" type="hidden" name="adOverSignIn_img" value="${adOverSignIn_img}" />
                <img src="${adOverSignIn_img}" id="holder_adOverSignIn_img" class="img_holder" />
                <div class="mask">图片</div>
            </div>
            <div class="soof_img_viewer_upload_wrap">
                <span><input type="button" id="choose_adOverSignIn_img" class="btn white" value="选择图片文件"></span>
            </div>
            <div class="clearer"></div>
        </div>
        <p class="p10">
            图片链接 URL <span class="desc">(可不填)</span>
        </p>
        <p class="p5">用户点击图片时，打开的页面 URL（以 http:// 或 https:// 开头）。</p>
        <p class="p10">
        	<input class="field" name="adOverSignIn_href" dataType="Custom" require="false" regexp="^http(s)?:\/\/.*?$" value="${adOverSignIn_href}" />
        </p>
        <p class="warn p5">由于用户此时尚未通过认证，还不能上外网，因此您需要将 URL 中的域名添加到设备的白名单，否则当用户点击链接时，该链接会被拦截，无法正常访问。</p>
    </div>
</div>     

<link href="${cssPath}editable_cards.css" rel="stylesheet" type="text/css" />
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
	"open.jsrender.jsrender",
	"cloudac:SortableCardsEditor",
	"cloudac:CardEditor",
	"cloudac:AdsEditor",
	"open.kindeditor.kindeditor",
	"open.kindeditor.lang.zh_CN"
);
</script>

<style type="text/css">
#sideAdsEditSection .fields_box{
	padding-bottom:0;
	width:130px;
}
#sideAdsEditSection .soof_editable_cards li{
	width:120px;
	height:120px;
}
#sideAdsEditSection .soof_editable_cards .card .btn div{
	padding-top:50px;
}
</style>
<script type="text/javascript">
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		AdsEditor = orderjs("cloudac:AdsEditor");
	
	var sideAdsEditor = new AdsEditor({
		dataField : "#sideAdsField",
		listEditorOptions : {
			target : "#sideAds_editor",
			iniValues : JSON.parse(jQuery("#sideAdsField").val() || "[]"),
			itemTpl : "#sideAdsImageTpl",
		},
		listItemEditorOptions : {
			editDialogContent : "sideAdsImageEditor",
			editingImageHolder : "#editingSideAdImage",
			editingLinkField : "#editingSideAdImageHref",
		},
		listItemEditorUploaderOptions : {
			button : jQuery('#uploadSideAdImage')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
		}
	});
});
</script>
<div id="sideAdsEditSection">
    <input id="sideAdsField" type="hidden" name="sideAds" value='${sideAds}' />
    <div id="sideAdsImageEditor" class="soof_page" style=" display:none; width:600px; height:250px; overflow:auto;">
        <div style="padding:2em;">
            <div class="section first">
                <h3>图片文件</h3>
                <p class="p5">
                    文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：120 * 120 像素。
                </p>
                <p class="p10">
                    <div class="soof_img_viewer" style="width:120px; height:120px;">
                        <input type="hidden" id="field_file" name="file" />
                        <img src="" id="editingSideAdImage" class="img_holder" style="display:none;" />
                        <div class="mask" style="line-height:120px;">图片</div>
                    </div>
                    <div class="soof_img_viewer_upload_wrap">
                        <span><input type="button" id="uploadSideAdImage" class="btn white" value="上传图片"></span>
                    </div>
                    <div class="clearer"></div>
                </p>
            </div>
            <div class="section">
                <h3>图片链接 URL <span class="desc">(可不填)</span></h3>
                <p class="p5">用户点击图片时，打开的页面 URL（以 http:// 或 https:// 开头）。</p>
                <p class="p10">
                    <input class="field" id="editingSideAdImageHref" />
                </p>
                <p class="warn p5">由于用户此时尚未通过认证，还不能上外网，因此您需要将 URL 中的域名添加到设备的白名单，否则当用户点击链接时，该链接会被拦截，无法正常访问。</p>
            </div>
        </div>
    </div>
    <script id="sideAdsImageTpl" type="text/x-jsrender">
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
        <h3>侧边栏广告列表</h3>
        <p class="p5">
            侧边栏广告列表，建议放 4 幅大小为 120 * 120 像素的图片，拖动图片可排序。
        </p>
        <div class="fields_box">
            <ul id="sideAds_editor" class="soof_editable_cards">
                <li class="static_item trigger_add_item">
                    <div class="card static_card">
                        <a class="btn" href="javascript:void(0);">
                            <div>添加广告</div>
                        </a>
                    </div>
                </li>
            </ul>
            <div class="clearer"></div>
        </div>
    </div>
</div>


<jsp:include page="${moduleRoot}SignInTabPanel_s/edit/module_edit.jsp" />
