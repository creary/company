<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="section first">
    <h3>关于欢迎页</h3>
    <p class="p5">
        未通过认证的用户连接 WiFi 后，打开浏览器访问任意网页都会被强制跳转至该页面，用户只有点击页面上的跳转按钮才能继续。<br />
        如果当前认证策略的“免认证”已开启，则用户点击跳转按钮后，无需认证，待页面跳转至认证后页以后，即可开始上网；<br />
		如果当前认证策略的“免认证”已关闭，则用户点击跳转按钮后，将跳转至认证页，通过认证后，才能开始上网。
    </p>
</div>
<div class="section">
    <h3>欢迎文字</h3>
    <p class="p5">
    	文字将自动居中，请用"&lt;br /&gt;"换行。
    </p>
    <p class="p10">
    	<input class="field" name="startTip" value="${startTip}" />
    </p>
</div>
<div class="section">
    <h3>跳转按钮文字</h3>
    <p class="p10">
        <input class="field" name="jumpBtnText" value="${jumpBtnText}" />
    </p>
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
#bannerEditSection .fields_box{
	padding-bottom:0;
	width:360px;
}
#bannerEditSection .soof_editable_cards li{
	width:350px;
}
</style>
<script type="text/javascript">
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		AdsEditor = orderjs("cloudac:AdsEditor");
	
	var bannersEditor = new AdsEditor({
		dataField : "#bannerImagesField",
		listEditorOptions : {
			target : "#bannerImages_editor",
			iniValues : JSON.parse(jQuery("#bannerImagesField").val() || "[]"),
			itemTpl : "#bannerImageTpl",
		},
		listItemEditorOptions : {
			editDialogContent : "bannerImageEditor",
			editingImageHolder : "#editingBannerImage",
			editingLinkField : "#editingBannerImageHref",
		},
		listItemEditorUploaderOptions : {
			button : jQuery('#uploadBannerImage')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
		}
	});
});
</script>
<div id="bannerEditSection">
    <input id="bannerImagesField" type="hidden" name="bannerImages" value='${bannerImages}' />
    <div id="bannerImageEditor" class="soof_page" style=" display:none; width:600px; height:250px; overflow:auto;">
        <div style="padding:2em;">
            <div class="section first">
                <h3>图片文件</h3>
                <p class="p5">
                    文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：500 * 100 像素。
                </p>
                <p class="p10">
                    <div class="soof_img_viewer">
                        <input type="hidden" id="field_file" name="file" />
                        <img src="" id="editingBannerImage" class="img_holder" style="display:none;" />
                        <div class="mask">图片</div>
                    </div>
                    <div class="soof_img_viewer_upload_wrap">
                        <span><input type="button" id="uploadBannerImage" class="btn white" value="上传图片"></span>
                    </div>
                    <div class="clearer"></div>
                </p>
            </div>
            <div class="section">
                <h3>图片链接 URL <span class="desc">(可不填)</span></h3>
                <p class="p5">用户点击图片时，打开的页面 URL（以 http:// 或 https:// 开头）。</p>
                <p class="p10">
                    <input class="field" id="editingBannerImageHref" />
                </p>
                <p class="warn p5">由于用户此时尚未通过认证，还不能上外网，因此您需要将 URL 中的域名添加到设备的白名单，否则当用户点击链接时，该链接会被拦截，无法正常访问。</p>
            </div>
        </div>
    </div>
    <script id="bannerImageTpl" type="text/x-jsrender">
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
        <h3>横幅广告条</h3>
        <p class="p5">
            显示在欢迎文字下的横幅广告条，建议放 2 幅大小为 500 * 100 像素的图片，拖动图片可排序。
        </p>
        <div class="fields_box">
            <ul id="bannerImages_editor" class="soof_editable_cards">
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

<style type="text/css">
#picWallEditSection .fields_box{
	padding-bottom:0;
	width:430px;
}
#picWallEditSection .soof_editable_cards li{
	height:90px;
}
</style>
<script type="text/javascript">
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		AdsEditor = orderjs("cloudac:AdsEditor");
	
	var picWallEditor = new AdsEditor({
		dataField : "#picWallImagesField",
		listEditorOptions : {
			target : "#picWallImages_editor",
			iniValues : JSON.parse(jQuery("#picWallImagesField").val() || "[]"),
			itemTpl : "#picWallImageTpl",
		},
		listItemEditorOptions : {
			editDialogContent : "picWallImageEditor",
			editingImageHolder : "#editingPicWallImage",
			editingLinkField : "#editingPicWallImageHref",
		},
		listItemEditorUploaderOptions : {
			button : jQuery('#uploadPicWallImage')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
		}
	});
});
</script>
<div id="picWallEditSection">
    <input id="picWallImagesField" type="hidden" name="picWallImages" value='${picWallImages}' />
    <div id="picWallImageEditor" class="soof_page" style=" display:none; width:600px; height:250px; overflow:auto;">
        <div style="padding:2em;">
            <div class="section first">
                <h3>图片文件</h3>
                <p class="p5">
                    文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：200 * 90 像素。
                </p>
                <p class="p10">
                    <div class="soof_img_viewer" style="width:200px; height:90px;">
                        <input type="hidden" id="field_file" name="file" />
                        <img src="" id="editingPicWallImage" class="img_holder" style="display:none;" />
                        <div class="mask" style="line-height:90px;">图片</div>
                    </div>
                    <div class="soof_img_viewer_upload_wrap">
                        <span><input type="button" id="uploadPicWallImage" class="btn white" value="上传图片"></span>
                    </div>
                    <div class="clearer"></div>
                </p>
            </div>
            <div class="section">
                <h3>图片链接 URL <span class="desc">(可不填)</span></h3>
                <p class="p5">用户点击图片时，打开的页面 URL（以 http:// 或 https:// 开头）。</p>
                <p class="p10">
                    <input class="field" id="editingPicWallImageHref" />
                </p>
                <p class="warn p5">由于用户此时尚未通过认证，还不能上外网，因此您需要将 URL 中的域名添加到设备的白名单，否则当用户点击链接时，该链接会被拦截，无法正常访问。</p>
            </div>
        </div>
    </div>
    <script id="picWallImageTpl" type="text/x-jsrender">
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
        <h3>图片墙广告</h3>
        <p class="p5">
            紧贴于横幅广告之下的小幅图片墙，建议放 4 幅大小为 200 * 90 像素的图片，拖动图片可排序。
        </p>
        <div class="fields_box">
            <ul id="picWallImages_editor" class="soof_editable_cards">
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

