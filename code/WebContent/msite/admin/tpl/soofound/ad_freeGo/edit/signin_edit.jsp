<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="section first">
    <h3>关于认证页</h3>
    <p class="p5">
        用户在该页面输入认证信息，认证通过后方能上网。
    </p>
</div>
<jsp:include page="${moduleRoot}SignInTabPanel/edit/module_edit.jsp" />
<jsp:include page="${moduleRoot}RandomImage/edit/module_edit.jsp">
    <jsp:param name="sectionDesc" value="显示在登录按钮下的大图广告，如添加多个则随机显示。"/>
    <jsp:param name="cardBoxScale" value="4:2"/>
    <jsp:param name="hideImgHref" value="1"/>
</jsp:include>
