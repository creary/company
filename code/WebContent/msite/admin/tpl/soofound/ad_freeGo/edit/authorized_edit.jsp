<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<div class="section first">
    <h3>关于认证后页</h3>
    <p class="p5">
    	认证后页用于展示商家的品牌形象以及为站内商讯作导航。<br />
	</p>
</div>
<jsp:include page="${moduleRoot}BannerSlide/edit/module_edit.jsp" />
<jsp:include page="${moduleRoot}RandomImage/edit/module_edit.jsp">
    <jsp:param name="sectionDesc" value="显示在海报轮播下的大图广告，如添加多个则随机显示。"/>
    <jsp:param name="cardBoxScale" value="4:4"/>
</jsp:include>
