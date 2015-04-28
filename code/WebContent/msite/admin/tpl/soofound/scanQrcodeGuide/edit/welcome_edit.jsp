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
    "open.spectrum.spectrum",
	"cloudac:ImgUploader"
);
orderjs(function(){
	var ImgUploader = orderjs("cloudac:ImgUploader");
	
	orderjs.ready(function(){
		ImgUploader("#welcomePicUploader");
		
		jQuery("input[name='openBtnBgColor']").spectrum({
			cancelText: "取消",
			chooseText: "选 择",
			clearText: "清除当前选择",
			preferredFormat : "hex",
			showInput: true,
			allowEmpty:true
		});
		jQuery("input[name='openBtnTextColor']").spectrum({
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
<div class="section first">
    <h3>欢迎图片</h3>
    <p class="p5">
        以大图的形式向用户展示商家的品牌形象。文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：宽 400 像素，高 400 像素。 
    </p>
    <div class="p10" id="welcomePicUploader">
        <div class="soof_img_viewer" style="width:250px; height:250px;">
            <input type="hidden" name="welcomePic" value="${welcomePic}" />
            <img src="${welcomePic}" class="img_holder" />
            <div class="smartmask">
                <div class="vplaceholder"></div>图片
            </div>
        </div>
        <div class="soof_img_viewer_upload_wrap">
            <span><input type="button" data-url="${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/" class="btn white uploader" value="上传图片"></span>
        </div>
        <div class="clearer"></div>
    </div>
</div>
<div class="section">
    <h3>欢迎文字</h3>
    <p class="p5">
    	文字将自动居中，请用"&lt;br /&gt;"换行。
    </p>
    <p class="p10">
    	<input class="field xl" name="welcomeText" value="${welcomeText}" />
    </p>
</div>
<div class="section">
    <h3>打开按钮</h3>
    <p class="p5">
    	设置打开微信按钮的样式。
    </p>
    <div class="sections_box p10">
        <div class="section first">
            <h5>按钮文字</h5>
            <p class="p10">
                <input class="field s" name="openBtnText" value="${openBtnText}" />
            </p>
        </div>
        <div class="section">
            <h5>按钮背景颜色</h5>
            <p class="p10">
                请选择颜色值：<input class="field s" name="openBtnBgColor" value="${openBtnBgColor}" />
            </p>
        </div>
        <div class="section">
            <h5>按钮字体颜色</h5>
            <p class="p10">
                请选择颜色值：<input class="field s" name="openBtnTextColor" value="${openBtnTextColor}" />
            </p>
        </div>     
    </div>
</div>
