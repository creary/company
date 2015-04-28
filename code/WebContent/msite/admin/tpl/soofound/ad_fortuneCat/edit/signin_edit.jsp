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
	"open.jquery.jquery",
	"open.kindeditor.lang.zh_CN",
	"cloudac:SectionSwitcher"
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery"),
		K = orderjs("open.kindeditor.kindeditor"),	
		SectionSwitcher = orderjs("cloudac:SectionSwitcher");
	
	rl.onReady(function(){
		function updateEnablePreRollSection(){
			var method = (jQuery("input[name='enablePreRoll']:checked").val() == 1) ? "show" : "hide";
			jQuery("#enablePreRollSection")[method]();
		}
		jQuery("input[name='enablePreRoll']")
			.on("click", updateEnablePreRollSection);
		updateEnablePreRollSection();
		
		var uploadPicAdImageBtn = K.uploadbutton({
			button : K('#uploadPicAdImageBtn')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#picAdImageHolder').attr("src", url);
					K('#picAdImageField').val(url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		uploadPicAdImageBtn.fileBox.change(function(e) {
			uploadPicAdImageBtn.submit();
		});
		uploadPicAdImageBtn.div.appendTo("#uploadPicAdImageBtnCtn");
	});
	
});
</script>
<div class="hidden_box">
	<input type="button" id="uploadPicAdImageBtn" class="btn white" value="上传图片">
</div>
<div class="section">
    <h3>贴片广告配置</h3>
    <p class="p5">
        用户点击“登录”后，您可设置一段时间的全屏图片广告，让用户观看后才能上网。
    </p>
    <div class="fields_box">
        <p>
            是否启用贴片广告
        </p>
        <p class="p5">
            <input type="radio" name="enablePreRoll" id="enablePreRollField" class="field_selector" value="1" ${enablePreRoll == "1" ? "checked='checked'" : ""} />
            <label for="enablePreRollField" class="lable_selector">启用（默认）</label>&nbsp;&nbsp;&nbsp;
            <input type="radio" name="enablePreRoll" id="disablePreRollField" class="field_selector" value="0" ${enablePreRoll == "0" ? "checked='checked'" : ""} />
            <label for="disablePreRollField" class="lable_selector">关闭</label>
        </p>
        <div id="enablePreRollSection" class="switchable_section">
            <p class="p10">
                广告时长，单位为秒。
            </p>
            <p class="p5">
                <input name="preRollDuration" class="field xs" value="${preRollDuration}" />
            </p>
            <div id="picPreRollTypeSection">
                <p class="p10">
                    选择广告图片
                </p>
                <p class="p5">文件大小不超过 1MB；格式为 png、jpg、gif；图片大小建议为 600像素 * 900像素。</p>
                <p class="p5">
                    <input id="picAdImageField" type="hidden" name="picAdImage" value="${picAdImage}" />
                    <img id="picAdImageHolder" src="${picAdImage}" style="width:150px; height:200px;" align="top" />
                    <span id="uploadPicAdImageBtnCtn"></span>
                </p>
            </div>
        </div>
   	</div>
</div>
<jsp:include page="${moduleRoot}SignInTabPanel/edit/module_edit.jsp" />
