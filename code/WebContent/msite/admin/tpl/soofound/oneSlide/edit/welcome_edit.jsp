<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="section first">
    <h3>关于欢迎页</h3>
    <p class="p5">
        未通过认证的用户连接 WiFi 后，打开浏览器访问任意网页都会被强制跳转至该页面，用户只有点击页面上的跳转按钮才能继续。<br />
        如果当前认证策略的“免认证”已开启，则用户点击跳转按钮后，无需认证，待页面跳转至认证后页以后，即可开始上网；<br />
		如果当前认证策略的“免认证”已关闭，则用户点击跳转按钮后，将跳转至认证页，通过认证后，才能开始上网。
    </p>
</div>

<link href="${cssPath}editable_cards.css?dd=1" rel="stylesheet" type="text/css" />
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
#slideEditSection .fields_box{
	padding-bottom:0;
}
#slideEditSection .soof_editable_cards li{
	height:300px;
}
#slideImageEditor .soof_img_viewer{
	width:200px;
	height:300px;
}
</style>
<script type="text/javascript">
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		AdsEditor = orderjs("cloudac:AdsEditor");
	
	var slidesEditor = new AdsEditor({
		dataField : "#slideImagesField",
		listEditorOptions : {
			target : "#slideImages_editor",
			iniValues : JSON.parse(jQuery("#slideImagesField").val() || "[]"),
			itemTpl : "#slideImageTpl",
		},
		listItemEditorOptions : {
			editDialogContent : "slideImageEditor",
			editingImageHolder : "#editingSlideImage"
		},
		listItemEditorUploaderOptions : {
			button : jQuery('#uploadSlideImage')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
		}
	});
	
	var listEditor = slidesEditor.listEditor;
	listEditor.on("beforeAddItem", function(listItem){
		var len = listEditor.getItems().length;
		if(len >= 10){
			alert("轮播图片最多只能有 10 张，请先删除其它图片再增加!");
			return false;
		}
	});
	listEditor.on("beforeDeleteItem", function(listItem){
		var len = listEditor.getItems().length;
		if(len <= 2){
			alert("图片至少保留 2 张才能轮播，请先增加其它图片再删除!");
			return false;
		}
	});
});
</script>
<div id="slideEditSection">
    <input id="slideImagesField" type="hidden" name="slideImages" value='${slideImages}' />
    <div id="slideImageEditor" class="soof_page" style=" display:none; width:600px; height:350px; overflow:auto;">
        <div style="padding:2em;">
            <div class="section first">
                <h3>图片文件</h3>
                <p class="p5">
                    文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：600 * 1000 像素。
                </p>
                <div class="p10">
                    <div class="soof_img_viewer">
                        <input type="hidden" id="field_file" name="file" />
                        <img src="" id="editingSlideImage" class="img_holder" style="display:none;" />
                        <div class="smartmask">
                            <div class="vplaceholder"></div>图片
                        </div>
                    </div>
                    <div class="soof_img_viewer_upload_wrap">
                        <span><input type="button" id="uploadSlideImage" class="btn white" value="上传图片"></span>
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </div>
    </div>
    <script id="slideImageTpl" type="text/x-jsrender">
        <li>
            <div class="card">
                <img class="overlay trigger_delete_item" src="${imagePath}circle_close.gif" alt="删除" title="删除" />
                <img class="thumbnail" src="{{>src}}" data-href="{{>href}}" />
                <a class="btn" href="javascript:void(0);">
					<div class="smartmask">
						<div class="vplaceholder"></div>编辑
					</div>
                </a>
            </div>
        </li>
    </script>
    <div class="section">
        <h3>轮播广告</h3>
        <p class="p5">
            近乎全屏轮播的广告图片。您可以设置图片及其轮播次序（可拖动排序），图片数量限制在 2~10 张。
        </p>
        <div class="fields_box">
            <ul id="slideImages_editor" class="soof_editable_cards">
                <li class="static_item trigger_add_item">
                    <div class="card static_card">
                        <a class="btn" href="javascript:void(0);">
                            <div class="smartmask">
                                <div class="vplaceholder"></div>添加广告
                            </div>
                        </a>
                    </div>
                </li>
            </ul>
            <div class="clearer"></div>
        </div>
    </div>
</div>
<div class="section">
    <h3>轮播广告总时长</h3>
    <p class="p5">设置轮播广告展示的总时长。</p>
    <p class="p10">
        <input class="field xs" name="duration" value="${duration}" dataType="Number" /> 秒
    </p>
</div>

