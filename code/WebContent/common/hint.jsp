<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@include file="/common/nosession_path.jsp"%>
<% 
   Exception ex = (Exception)request.getAttribute("exception");
   if(ex != null)
       ex.printStackTrace();
   
   String error = request.getParameter("error");
   if(error == null)
       error = (String)request.getAttribute("error"); 
   if("MaxUploadSizeExceededException".equals(error)){
	   error = "上传的文件超过50 MB.";
   }else if("timeout".equals(error)){
	   error = "登录超时,请重新登录.";
   }
   request.setAttribute("error",error);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="rl-page-error" content="hint" />
<title>提 示</title>
<link rel="rl-page-icon" href="${imagePath}info.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<link href="${cssPath}info.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<style type="text/css">
body{
	margin:0;
	padding:0;
	background-color:#f7f7f7 !important;
}
.soof_msg_page{
	font-size:14px;
}
.soof_msg_page .page_viewport{
	width:700px;
	margin:90px 150px;
}
.soof_msg_page .title{
	font-size:36px;
	color:#09C;
}
.soof_msg_page .detail{
	margin-top:2em;
	line-height:25px;
	color:#666;
}
.soof_msg_page .actions{
	margin-top:2em;
}
</style>
</head>
<body>
	<div class="soof_page soof_msg_page">
    	<div class="page_viewport">
        	<c:if test="${not empty message}">
                <div class="title">提示</div>
                <div class="detail">
                    ${message}
                </div>
            </c:if>
        	<c:if test="${not empty error}">
                <div class="title">提示</div>
                <div class="detail">
                    ${error}
                </div>
            </c:if>
            <div class="actions">
            	<button class="btn primary" onclick="rl.page.close(); rl.delay(function(){try{history.back();}catch(err){}}, 1000);">确 定</button>
            </div>
        </div>
    </div>
</body>
</html>