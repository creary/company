<%@page language="java" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
orderjs.config("shim", {
	"open.spectrum.spectrum" : {
		deps : ["open.jquery.jquery", "css>open.spectrum.spectrum"]
	},
	"open.kindeditor.kindeditor" : {
		deps : ["css>open.kindeditor.themes.default.default"],
		exports : "KindEditor"
	},
	"open.kindeditor.lang.zh_CN" : {
		deps : ["open.kindeditor.kindeditor"],
		exports : "KindEditor"
	}
});

orderjs(
	"open.jquery.jquery",
    "open.kindeditor.lang.zh_CN",
    "open.spectrum.spectrum"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		K = orderjs("open.kindeditor.kindeditor");
    
	rl.onReady(function() {
		var logoUploadBtn = K.uploadbutton({
			button : K('#chooseLogo')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&id=msite_img&folder=/acs/upload/',
			afterUpload : function(data) {			    
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					K('#welcomePicUrl').val(url);
					K('#welcomePic').attr("src", url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('抱歉！上传文件发生错误: ' + str);
			}
		});
		logoUploadBtn.fileBox.change(function(e) {
			logoUploadBtn.submit();
		});
		
		jQuery("input[name='WelcomeBox_jumpBtnBgColor']").spectrum({
			cancelText: "取消",
			chooseText: "选 择",
			clearText: "清除当前选择",
			preferredFormat : "hex",
			showInput: true,
			allowEmpty:true
		});
		jQuery("input[name='WelcomeBox_jumpBtnTextColor']").spectrum({
			cancelText: "取消",
			chooseText: "选 择",
			clearText: "清除当前选择",
			preferredFormat : "hex",
			showInput: true,
			allowEmpty:true
		});
	}); 
	
});

</script>
<div class="section">
    <h3>欢迎页图片</h3>
    <p class="p5">
        <input id="welcomePicUrl" type="hidden" name="WelcomeBox_welcomePic" value="${WelcomeBox_welcomePic}" />
        以大图的形式向用户展示商家的品牌形象。文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：宽 400 像素，高 400 像素。 
    </p>
    <p class="p10">
        <img id="welcomePic" class="img_field" src="${WelcomeBox_welcomePic}" align="top" />
        <input type="button" id="chooseLogo" class="btn white" value="选择图片文件">
    </p>
</div>
<div class="section">
    <h3>欢迎文字</h3>
    <p class="p5">
    	文字将自动居中，请用"&lt;br /&gt;"换行。
    </p>
    <p class="p10">
    	<textarea class="field" style="height:40px;" name="WelcomeBox_welcomeText">${WelcomeBox_welcomeText}</textarea>
    </p>
</div>
<div class="section">
    <h3>跳转按钮文字</h3>
    <p class="p10">
        <input class="field" name="WelcomeBox_jumpBtnText" value="${WelcomeBox_jumpBtnText}" />
    </p>
</div>
<div class="section section_unite">
    <h3>跳转按钮背景颜色</h3>
    <p class="p10">
        请选择颜色值：<input class="field s" name="WelcomeBox_jumpBtnBgColor" value="${WelcomeBox_jumpBtnBgColor}" />
    </p>
</div>
<div class="section section_unite">
    <h3>跳转按钮字体颜色</h3>
    <p class="p10">
        请选择颜色值：<input class="field s" name="WelcomeBox_jumpBtnTextColor" value="${WelcomeBox_jumpBtnTextColor}" />
    </p>
</div>
