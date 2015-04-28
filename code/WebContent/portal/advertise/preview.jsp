<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${dto.title}</title>
<link rel="rl-page-icon" href="${imagePath}wifi.gif" />
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>

<style type="text/css">
.lake_ctn {
	width:380px;
	height:515px;
	margin:1px auto 0 auto;
	overflow:hidden;
}
.ad_ctn {
	width:300px;
	height:200px;
	margin:0 auto;
	border:dashed 1px red;
	overflow:hidden;
}
.desc {
	margin:85px auto 0 auto;
	width:300px;
	padding:.5em 0;
}

</style>
</head>
<body>
<div id="mainContent" class="lake_ctn lake_account">
    <div class="desc"></div>
        <div class="ad_ctn">${dto.content}</div>
        <script type="text/javascript">
        (function(){
            //hide default embeds in editor, to avoid showing unnecessary plugin views(like plugin load tip).
            var embedObj,
                editorEmbeds = document.getElementsByTagName("embed"),
                len = editorEmbeds.length;
            if(len){
                for(var i=0, len=editorEmbeds.length; i<len; i++){
                    embedObj = editorEmbeds[i];
                    embedObj.style.display = "none";
                }					
                //replace all video embed object with CKobject.
                orderjs(
                    "app.media.video.ckplayer-adapter"
                );
                orderjs(function(){
                    var adapter = orderjs("app.media.video.ckplayer-adapter");
                    
                    rl.onReady(function(){
                        var embeds = document.getElementsByTagName("embed");
                        rl.each(embeds, function(item){
							item.width = Math.min(item.width, 300);
							item.height = Math.min(item.height, 200);
							adapter.replace(item);
						});
                    });
                });
            }
        })();        
        </script>
</div>
</body>
</html>