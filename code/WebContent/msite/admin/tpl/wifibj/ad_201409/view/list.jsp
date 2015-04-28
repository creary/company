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
orderjs.config("shim", {
	"open.jquery.jquery" : {
		exports : "jQuery"
	}
});
orderjs(
	"open.jquery.jquery"
);
orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery");
	orderjs.ready(function() {
		var topbar = jQuery("#topbar"),
			navListToggler = jQuery("#navListToggler"),
			popupNavList = jQuery("#popupNavList"),
			userInfoPaneToggler = jQuery("#userInfoPaneToggler"),
			userInfoPane = jQuery("#userInfoPane"),
			tgPopupEventName = typeof window.TouchEvent == "object" ? "touchend" : "click";
		
		//hide on click to none menu area.
		navListToggler.on(tgPopupEventName, function(event){
			event.stopPropagation();
			popupNavList
				.toggle()
				.css("top", (navListToggler.position().top + navListToggler[0].offsetHeight) );
		});
		//hide on resize to larger screen width
		jQuery(window).on("resize", function(){
			if(navListToggler.css("display") == "none"){
				popupNavList.hide();
			}			 
			if(userInfoPaneToggler.css("display") == "none"){
				userInfoPane.hide();
			}			 
		});
		userInfoPaneToggler.on(tgPopupEventName, function(event){
			event.stopPropagation();
			userInfoPane
				.toggle()
				.css("top", topbar.height());
		});
		
		//hide on click to none menu area.
		jQuery(document).on(tgPopupEventName, function(event){
			if(!jQuery(event.target).closest("#popupNavList").length){
				popupNavList.hide();
			}
			if(!jQuery(event.target).closest("#userInfoPane").length){
				userInfoPane.hide();
			}
		});
		
	});
});

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
                <c:if test="${wifiUser != null}">
                    <td class="r">
                        <div class="user_center">
                            <a id="userInfoPaneToggler" href="javascript:void(0);"><img src="${currentTplContext}view/theme/default/img/user2.gif" class="icon_user" align="absmiddle" alt="我的账户" /> 
                            <span class="name">${wifiUser.username}</span></a>
                        </div>
                        <div id="userInfoPane" class="popup_nav_wrapper popup_pane" style="right:0;">	                        	
                            <ul class="nav">
                                <li>
                                    <div class="user_stat">
                                        <p class="name">${wifiUser.username}</p>
                                        <div>当前上网时长</div>
                                        <div>
                                            <span class="total">${surfingTimeLimit} 分钟</span>
                                            <span class="used">${surfingTime} 分钟</span>
                                        </div>
                                        <div class="bar">
                                            <div class="bar_used" style="width:${percentage}%"></div>
                                        </div>
                                    </div>
                                </li>
                                <li><a href="${wifidogLogout}">退出</a></li>
                            </ul>
                        </div>
                    </td>
                </c:if>
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
                        	    <li><a href="${base}msite/AuthorizedView.do?branchId=${branchId}">首页</a></li>
                            	<c:forEach items="${categories}" var="cate">  
                            	    <li ${currentCategory.id==cate.id?"class='active'":""}><a href="${base}msite/ListView.do?branchId=${branchId}&cid=${cate.id}">${cate.name}</a></li> 
			                    </c:forEach>
                            </ul>
                        </div>
                    	<div class="inline_nav_wrapper">
                        	<ul class="nav">
                        	    <li><a href="${base}msite/AuthorizedView.do?branchId=${branchId}">首页</a></li>
                            	<c:forEach items="${categories}" var="cate">  
                            	    <li ${currentCategory.id==cate.id?"class='active'":""}><a href="${base}msite/ListView.do?branchId=${branchId}&cid=${cate.id}">${cate.name}</a></li> 
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
            <div class="soof_panel">
            <c:if test="${currentCategory ne null}">
                <div class="p_head">
                    <h2>${currentCategory.name}</h2>
                </div>
                <ul id="articleList" class="soof_article_list"></ul>
                <div style="padding:1em;" id="articleLoaderCtn"></div>
				<script id="articleLinkTpl" type="text/x-jsrender">
					<li>
						<a href="${base}msite/ArticleView.do?branchId=${branchId}&cid=${cid}&aid={{>id}}">
							<div class="l">
								<img class="cover" src="{{>cover}}" />
							</div>
							<div class="r">
								<h3>{{>title}}</h3>
								<p class="p10">{{>summary}}</p>
							</div>
						</a>
					</li>
                </script>
                <script type="text/javascript">
				orderjs.config("shim", {
					"open.jquery.jquery" : {
						exports : "jQuery"
					},
					"open.jsrender.jsrender" : {
						deps : ["open.jquery.jquery"]
					}
				});
				orderjs("open.jRecordLoader.jRecordLoader");
				orderjs(function(){
					var jRecordLoader = orderjs("open.jRecordLoader.jRecordLoader");
					jRecordLoader("#articleLoaderCtn")
						.init({
							dataUrl : "${base}msite/getArticles.do?branchId=${branchId}&cid=${cid}&count=10",
							msgAllLoaded : "已全部加载，共 {total} 篇",
							recordsCtn : "#articleList",
							recordHtmlTpl : "#articleLinkTpl",
						})
						.render()
						.loadRecords();
				});
				</script>
            </c:if>
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
    <span style="display:none;">
		<script type="text/javascript">
        if(!/127\.0\.0\.1|localhost/i.test(String(location.host))){
            var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
            document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fe8f02f1992768475cc8e1b6635415c3c' type='text/javascript'%3E%3C/script%3E"));
        }
        </script>
    </span>
</body>
</html>
