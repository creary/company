<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/path.jsp"%>
<%@page import="com.soofound.framework.util.ProjectConfig"%>
<%@page import="com.soofound.framework.util.SysConfigHelper"%>
<%
    String id = (String)request.getAttribute("id");
    StringBuilder pageUrl = new StringBuilder(100);
    pageUrl.append("portal/").append(id).append("/welcome/preview.do");	
    request.setAttribute("pcfg",SysConfigHelper.getProjectConfig());
    request.setAttribute("pageUrl",pageUrl.toString());    
    request.setAttribute("preview", true);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>页面预览</title>
<link rel="icon" href="${base}${pcfg.favicon}" type="image/x-icon"/> 
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs("lib.dom.engine");
orderjs(function(){
	function previewPage(pageId){
		var url = "${base}portal/${id}/" + pageId + "/preview.do";
		rl.getDom("viewerFrame").src = url || "about:blank";
	}
	rl.onReady(function(){
		var pageId = "welcome";
			url = rl.trim("${pageUrl}");		
		var pageSel = rl.$("pageSelector"),
			targetOption = rl.find(pageSel.dom.options, function(option){
				return (option.value == pageId);
			});
		
		if(targetOption){
			targetOption.selected = true;
			pageSel.on("change", function(){
				previewPage(pageSel.dom.value);
			});
			previewPage(pageId);
		}
	});
});
</script>

<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.soof_preview_page{
	background:#666;
}
.soof_preview_page .page_viewport{
	width:1000px;
	margin:0 auto;
}
.soof_preview_page .header{
	padding:12px;
	background-color:#444;
}
.soof_preview_page .header .title{
	font-size:24px;
	color:#fff;
}
.soof_preview_page .viewer{
	margin:20px auto;
	/*border:solid 1px #444;*/
}
.soof_preview_page .viewer_phone{
	width:400px;
	height:772px;
}
.soof_preview_page .viewer_phone .frame{
	margin-top:93px;
	margin-left:18px;
	width:361px;
	height:609px;
	background-color:#fff;
}
.soof_preview_page .viewer_tablet,
.soof_preview_page .viewer_tablet .frame{
	width:900px;
	height:500px;
}
.soof_preview_page .viewer_pc,
.soof_preview_page .viewer_pc .frame{
	width:90%;
	height:600px;
}
</style>
</head>
<body class="soof_preview_page">
    <div class="header">
        <span class="title">页面预览</span>        
        <select id="pageSelector" class="page_sel">
            <c:forEach items="${pts}" var="p">  
	            <c:if test="${p.previewable}">
	                 <option value="${p.id}">${p.name}</option>
	            </c:if>   
            </c:forEach>
        </select>
    </div>
    <div class="viewer viewer_phone" style="background:url(${base}resources/image/default/msite/phone_black_bg.png) no-repeat;">
        <iframe id="viewerFrame" class="frame" frameborder="0"></iframe>
    </div>
</body>
</html>