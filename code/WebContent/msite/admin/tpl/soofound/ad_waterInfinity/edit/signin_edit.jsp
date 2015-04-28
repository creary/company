<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="section first">
    <h3>关于认证页</h3>
    <p class="p5">
        用户在该页面输入认证信息，认证通过后方能上网。
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
<script type="text/javascript">
orderjs(
	"cloudac:SectionSwitcher"
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery"),
		SectionSwitcher = orderjs("cloudac:SectionSwitcher");
	
	rl.onReady(function(){
		function updateEnablePreRollSection(){
			var method = (jQuery("input[name='enablePreRoll']:checked").val() == 1) ? "show" : "hide";
			jQuery("#enablePreRollSection")[method]();
		}
		jQuery("input[name='enablePreRoll']")
			.on("click", updateEnablePreRollSection);
		updateEnablePreRollSection();
	});
	
});
</script>
<div class="section">
    <h3>贴片广告</h3>
    <p class="p5">
        用户点击“登录”后，您可设置一段时间的全屏图片广告，让用户观看后才能上网。
    </p>
    <div class="sections_box">
    	<div class="section first">
        	<h5>贴片广告开关</h5>
            <p class="p5">
                <input type="radio" name="enablePreRoll" id="enablePreRollField" class="field_selector" value="1" ${enablePreRoll == "1" ? "checked='checked'" : ""} />
                <label for="enablePreRollField" class="lable_selector">开启（默认）</label>&nbsp;&nbsp;&nbsp;
                <input type="radio" name="enablePreRoll" id="disablePreRollField" class="field_selector" value="0" ${enablePreRoll == "0" ? "checked='checked'" : ""} />
                <label for="disablePreRollField" class="lable_selector">关闭</label>
            </p>
        </div>
        <div id="enablePreRollSection" class="switchable_section">
        	<div class="section">
                <h5>广告时长</h5>
                <p class="p5">
                    <input name="preRollDuration" class="field xs" value="${preRollDuration}" /> <span class="desc">单位为秒。</span>
                </p>
            </div>
            <div id="picPreRollTypeSection">
				<style type="text/css">
                #fullscreenEditSection .fields_box{
                    padding-bottom:0;
                }
                #fullscreenEditSection .soof_editable_cards li{
                    width:150px;
                    height:200px;
                }
                #fullscreenEditSection .soof_editable_cards .card .btn div{
                    padding-top:80px;
                }
                #fullscreenImageEditor .soof_img_viewer{
                    width:150px;
                    height:150px;
                }
                </style>
                <script type="text/javascript">
                orderjs(function(){
                    var jQuery = orderjs("open.jquery.jquery"),
                        AdsEditor = orderjs("cloudac:AdsEditor");
                    
                    var fullscreensEditor = new AdsEditor({
                        dataField : "#fullscreenImagesField",
                        listEditorOptions : {
                            target : "#fullscreenImages_editor",
                            iniValues : JSON.parse(jQuery("#fullscreenImagesField").val() || "[]"),
                            itemTpl : "#fullscreenImageTpl",
                        },
                        listItemEditorOptions : {
                            editDialogContent : "fullscreenImageEditor",
                            editingImageHolder : "#editingFullscreenImage",
                            editingLinkField : "#editingFullscreenImageHref",
                        },
                        listItemEditorUploaderOptions : {
                            button : jQuery('#uploadFullscreenImage')[0],
                            url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
                        }
                    });
                });
                </script>
                <div id="fullscreenEditSection">
                    <input id="fullscreenImagesField" type="hidden" name="fullscreenImages" value='${fullscreenImages}' />
                    <div id="fullscreenImageEditor" class="soof_page" style=" display:none; width:600px; height:250px; overflow:auto;">
                        <div style="padding:2em;">
                            <div class="section first">
                                <h3>图片文件</h3>
                                <p class="p5">
                                    文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的图片大小为 600像素 * 900像素。。
                                </p>
                                <p class="p10">
                                    <div class="soof_img_viewer">
                                        <input type="hidden" id="field_file" name="file" />
                                        <img src="" id="editingFullscreenImage" class="img_holder" style="display:none;" />
                                        <div class="mask">图片</div>
                                    </div>
                                    <div class="soof_img_viewer_upload_wrap">
                                        <span><input type="button" id="uploadFullscreenImage" class="btn white" value="上传图片"></span>
                                    </div>
                                    <div class="clearer"></div>
                                </p>
                            </div>
                            <div class="section">
                                <h3>图片链接 URL</h3>
                                <p class="p5">用户点击图片时，打开的页面 URL（以 http:// 或 https:// 开头）。</p>
                                <p class="p10">
                                    <input class="field" id="editingFullscreenImageHref" /> <span class="desc">(可不填)</span>
                                </p>
                                <p class="warn p5">由于用户此时尚未通过认证，还不能上外网，因此您需要将 URL 中的域名添加到设备的白名单，否则当用户点击链接时，该链接会被拦截，无法正常访问。</p>
                            </div>
                        </div>
                    </div>
                    <script id="fullscreenImageTpl" type="text/x-jsrender">
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
                        <h5>广告图片</h5>
                        <p class="p5">如添加多个则随机显示。</p>
                        <div class="fields_box">
                            <ul id="fullscreenImages_editor" class="soof_editable_cards">
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
            </div>
        </div>
   	</div>
</div>
<jsp:include page="${moduleRoot}SignInTabPanel/edit/module_edit.jsp" />
