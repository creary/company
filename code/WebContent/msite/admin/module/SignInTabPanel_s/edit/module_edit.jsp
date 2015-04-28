<%@page language="java" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
orderjs.config("shim", {
	"open.spectrum.spectrum" : {
		deps : ["open.jquery.jquery", "css>open.spectrum.spectrum"]
	}
});

orderjs(
	"open.jquery.jquery",
    "open.spectrum.spectrum"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery");
    
	rl.onReady(function() {
		jQuery("input[name='SignInTabPanel_s_btnBgColor'], input[name='SignInTabPanel_s_btnTextColor']")
			.spectrum({
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
    <h3>微信登录步骤说明</h3>
    <p class="p5">
        对微信的登录步骤进行说明，用"&lt;br /&gt;"换行。
    </p>
    <p class="p10">
        <textarea class="field l" style="height:100px; " name="SignInTabPanel_s_wechatStepTip">${SignInTabPanel_s_wechatStepTip}</textarea>
    </p>
</div>
<!--<div class="section">
    <h3>按钮样式</h3>
    <p class="p5">
    	对登录等按钮的样式进行设置。
    </p>
    <p class="p10">
        按钮背景颜色：<input class="field s" name="SignInTabPanel_s_btnBgColor" value="${SignInTabPanel_s_btnBgColor}" />
    </p>
    <p class="p10">
        按钮字体颜色：<input class="field s" name="SignInTabPanel_s_btnTextColor" value="${SignInTabPanel_s_btnTextColor}" />
    </p>
</div>-->