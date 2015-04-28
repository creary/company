<%@page language="java" contentType="text/html; charset=UTF-8"%>
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
#bigShowEditSection .fields_box{
	padding-bottom:0;
}
#bigShowEditSection .soof_editable_cards li{
	width:200px;
	height:200px;
}
#bigShowEditSection .soof_editable_cards .card .btn div{
	padding-top:80px;
}
#bigShowImageEditor .soof_img_viewer{
	width:150px;
	height:150px;
}
</style>
<script type="text/javascript">
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		AdsEditor = orderjs("cloudac:AdsEditor");
	
	var bigShowsEditor = new AdsEditor({
		dataField : "#bigShowImagesField",
		listEditorOptions : {
			target : "#bigShowImages_editor",
			iniValues : JSON.parse(jQuery("#bigShowImagesField").val() || "[]"),
			itemTpl : "#bigShowImageTpl",
		},
		listItemEditorOptions : {
			editDialogContent : "bigShowImageEditor",
			editingImageHolder : "#editingBigShowImage",
			editingLinkField : "#editingBigShowImageHref",
		},
		listItemEditorUploaderOptions : {
			button : jQuery('#uploadBigShowImage')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
		}
	});
});
</script>
<div id="bigShowEditSection">
    <input id="bigShowImagesField" type="hidden" name="bigShows" value='${bigShows}' />
    <div id="bigShowImageEditor" class="soof_page" style=" display:none; width:600px; height:250px; overflow:auto;">
        <div style="padding:2em;">
            <div class="section first">
                <h3>图片文件</h3>
                <p class="p5">
                    文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：宽 500 像素，高度不限。
                </p>
                <p class="p10">
                    <div class="soof_img_viewer">
                        <input type="hidden" id="field_file" name="file" />
                        <img src="" id="editingBigShowImage" class="img_holder" style="display:none;" />
                        <div class="mask">图片</div>
                    </div>
                    <div class="soof_img_viewer_upload_wrap">
                        <span><input type="button" id="uploadBigShowImage" class="btn white" value="上传图片"></span>
                    </div>
                    <div class="clearer"></div>
                </p>
            </div>
            <div class="section">
                <h3>图片链接 URL</h3>
                <p class="p5">用户点击图片时，打开的页面 URL（以 http:// 或 https:// 开头）。</p>
                <p class="p10">
                    <input class="field" id="editingBigShowImageHref" /> <span class="desc">(可不填)</span>
                </p>
            </div>
        </div>
    </div>
    <script id="bigShowImageTpl" type="text/x-jsrender">
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
        <h3>大图广告</h3>
        <p class="p5">
            显示在提示文字下方的广告图片。如添加多个则随机显示。
        </p>
        <div class="fields_box">
            <ul id="bigShowImages_editor" class="soof_editable_cards">
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
