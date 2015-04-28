<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="section first">
    <h3>关于 Portal</h3>
    <p class="p5" style="float:right; text-align:center;">
        <a href="${base}portal/readyPreview.do?id=${pid}" target="sitePreview" title="预览 Portal"><img src="${base}msite/admin/global/edit/img/cover_msite.jpg" width="165" height="71" /></a><br /><br />
        <button class="btn white" onclick="window.open('${base}portal/readyPreview.do?id=${pid}', 'sitePreview')">预览 Portal</button>
    </p>
    <p class="p5" style="margin-right:200px;">
        本 Portal 为微信扫一扫上网专用 Portal，只有一个页面：欢迎页，用于指引用户进行扫一扫上网。因此，仅能用于只开通微信扫一扫认证方式的策略。
    </p>
    <div class="clearer"></div>
</div>
<div class="section">
    <h3>网站标题</h3>
    <p class="p10">
        <input class="field" name="msite_title" value="${msite_title}" />
    </p>
</div>
<div class="section">
    <h3>版权信息</h3>
    <p class="p5">
        显示在页脚的版权信息。文字将自动居中，请用"&lt;br /&gt;"换行。
    </p>
    <p class="p10">
        <textarea class="field l" style="height:50px;" name="msite_copyright">${msite_copyright}</textarea>
    </p>
</div>
