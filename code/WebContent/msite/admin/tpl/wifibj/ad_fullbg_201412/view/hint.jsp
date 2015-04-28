<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提示</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="icon" href="${imagePath}wifi_favicon.ico" type="image/x-icon"/>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${currentTplContext}view/theme/default/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.soof_pagetip{
	margin:2em auto;
	padding:2em;
	border:solid 1px #ddd;
	text-align:center;
	font-size:16px;
	background-color:#f8f8f8;
}
</style>
</head>
<body>
    <div class="soof_main">
    	<div class="page_viewport">
            <div class="soof_pagetip">
           ${hint}
            </div>
        </div>
    </div>
    <div class="soof_foot">
        <div class="page_viewport">
            <div class="copyright">
                ${msite_copyright}
            </div>
        </div>
    </div>
</body>
</html>