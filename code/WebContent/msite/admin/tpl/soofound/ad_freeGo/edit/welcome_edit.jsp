<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="section first">
    <h3>关于欢迎页</h3>
    <p class="p5">
        未通过认证的用户连接 WiFi 后，打开浏览器访问任意网页都会被强制跳转至该页面，用户只有点击页面上的跳转按钮才能继续。<br />
        如果当前认证策略的“免认证”已开启，则用户点击跳转按钮后，无需认证，待页面跳转至商家主页后，即可开始上网；<br />
		如果当前认证策略的“免认证”已关闭，则用户点击跳转按钮后，将跳转至认证页，通过认证后，才能开始上网。
    </p>
</div>
<jsp:include page="${moduleRoot}WelcomeBox/edit/module_edit.jsp" />
