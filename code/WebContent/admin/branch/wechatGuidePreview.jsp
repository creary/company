<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<%
    request.setAttribute("guideType", request.getParameter("guideType"));    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信指引页预览</title>
<link rel="icon" href="${base}${pcfg.favicon}" type="image/x-icon"/> 
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs("lib.dom.engine");
orderjs(function(){
	function previewPage(guideType){
		var url = "${base}admin/help/wechatGuide.jsp?guideType=" + guideType;
		rl.getDom("viewerFrame").src = url;
		rl.debug(this + ' previewPage(): url = ' + url + '');
	}
	rl.onReady(function(){
		previewPage("default");
		//暂不支持自定义指引预览，因预览内容未被保存
		/*function previewSelected(){
			previewPage(rl.getDom("pageSelector").value);
		}
		
		rl.$("pageSelector")
			.on("change", previewSelected);
		
		previewSelected();*/
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
        <span class="title">系统默认指引页预览</span>        
        <!-- 暂不支持自定义指引预览，因预览内容未被保存
        <select id="pageSelector" class="page_sel">
             <option value="default" ${guideType eq 'default'? "selected":""}>系统默认指引</option>
             
             <option value="custom" ${guideType eq 'custom'? "selected":""}>自定义指引</option>
        </select>
         -->
    </div>
    <div class="viewer viewer_phone" style="background:url(${base}resources/image/default/msite/phone_black_bg.png) no-repeat;">
        <iframe id="viewerFrame" class="frame" frameborder="0"></iframe>
    </div>
</body>
</html>