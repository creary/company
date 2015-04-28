<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${msite_title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="icon" href="${imagePath}wifi_favicon.ico" type="image/x-icon"/>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${currentTplContext}view/theme/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs.regRootNS("cloudac", {
	js : "${jsPath}cloudac/",
	css : "${cssPath}"
});
orderjs.config("shim", {
	"open.jquery.jquery" : {
		exports : "jQuery"
	},
	"open.navigator-detect.navigator-detect" : {
		exports : "NavigatorDetect"
	}
});
orderjs(
	"open.jquery.jquery",
	"cloudac:msite.topMenu"
);
</script>
</head>
<body>
	<div id="topbar" class="soof_topbar">
    	<div class="page_viewport">
        	<table class="cols_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="l shortcuts">
                    	<a class="title" href="javascript:void(0);" onclick="self.history.back(-1);"><img src="${currentTplContext}view/theme/default/img/arrow_left.png" align="absmiddle" alt="返回" /> ${msite_title}</a>
                    </td>
                </tr>                
            </table>
        </div>
    </div>
    <div class="soof_header">
    	<div class="page_viewport">
        	<table class="h_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="logo_col">
                    	<div class="logo_wrapper">
                        	<img src="${msite_logo}" />
                        </div>
                    </td>
                    <td class="nav_col">
                        <div id="navListToggler" class="menu_toggler">
                            <img src="${currentTplContext}view/theme/default/img/menu_list.gif" align="absmiddle" alt="菜单" />
                        </div>
                        <div id="popupNavList" class="popup_nav_wrapper">	                        	
                            <ul class="nav">
                        	    <li><a href="${base}wifiant/authorized/view.do?branchId=${branchId}&pid=${pid}">首页</a></li>
                            	<c:forEach items="${categories}" var="cate">  
                            	    <li ${currentCategory.id==cate.id?"class='active'":""}><a href="${base}wifiant/list/view.do?branchId=${branchId}&cid=${cate.id}&pid=${pid}">${cate.name}</a></li> 
			                    </c:forEach>
                            </ul>
                        </div>
                    	<div class="inline_nav_wrapper">
                        	<ul class="nav">
                        	    <li><a href="${base}wifiant/authorized/view.do?branchId=${branchId}&pid=${pid}">首页</a></li>
                            	<c:forEach items="${categories}" var="cate">  
                            	    <li ${currentCategory.id==cate.id?"class='active'":""}><a href="${base}wifiant/authorized/view.do?branchId=${branchId}&pid=${pid}&cid=${cate.id}">${cate.name}</a></li> 
			                    </c:forEach>
                            </ul>
                        </div>                   
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="soof_main">
    	<div class="page_viewport">
            <table class="soof_article_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="main_col">
                        <div class="soof_article">
                            <div class="rtc_head">
                                <h2 class="title">${currentArticle.title}</h2>
                            </div>
                            <div class="rtc_main">
                                <div class="media">
                                    <img class="cover" src="${currentArticle.cover}" />
                                </div>
                                <div class="content">
                                    ${currentArticle.content}                                
                                </div>
                            </div>
                        </div>
                    </td>
                    <td class="side_col"></td>
                </tr>
            </table>
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