<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${msite_title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="icon" href="${imagePath}wifi_favicon.ico" type="image/x-icon"/>
<link href="${currentTplContext}view/theme/default/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.soof_oneslide.authed .main{
	background-color:#fff;
}
.soof_oneslide .m_wrapper{
	width:92%;
	padding:1em 0;
	margin:0 auto;
}
.soof_oneslide .brand_area{
	text-align:center;
}
.soof_oneslide .brand_area .tip{
	padding:1em 0;
	text-align:center;
	background-color:#f2f2f2;
}
.soof_oneslide .big_show{
	padding-top:1em;
}

</style>
<script type="text/javascript" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs.config("shim", {
	"open.jquery.jquery" : {
		exports : "jQuery"
	}
});
orderjs(
	(typeof JSON == "object") ? "" : "open.json.json2",
	"open.jquery.jquery"
);

function getRandomItems(arr, len){
	if(!(arr && arr.constructor === Array)) return [];
	var items = [],
		tmpArr = arr.concat();
	len = Math.min(tmpArr.length, parseInt(len));
	
	while(items.length < len){
		items.push(pullRandom(tmpArr));
	}
	
	return items;
}

function pullRandom(arr){
	var i = parseInt(arr.length * Math.random()),
		item = arr.splice(i, 1)[0];
	return item;
}

</script>
</head>
<body class="soof_oneslide authed">
    <div class="viewport">
        <div class="header">
            <div class="logo_wrap">
                <img class="logo" src="${msite_logo}" />
            </div>
        </div>
		<div class="main">
        	<div class="m_wrapper">
                <div class="brand_area">
                    <div class="tip">${passTip}</div>
                </div>
                <div id="bigShowCtn" class="big_show"></div>
                <script type="text/javascript">
                orderjs(function(){
                    var jQuery = orderjs("open.jquery.jquery");
                    try{
                        var bigShows = JSON.parse('${bigShows}' || "null");
                        if(bigShows && bigShows.length > 0){
                            var bigShow = getRandomItems(bigShows, 1)[0];
                            jQuery("#bigShowCtn")
                                .html('<a' + (bigShow.href ? (' href="' + bigShow.href + '"') : '') + '><img src="' + bigShow.src + '" style="width:100%;" /></a>');
                        }
                    }
                    catch(err){
                        alert(err);
                        jQuery("#bigShowCtn")
                            .html('<div style="text-align:center; padding: 2em;">大图片加载错误：数据解析失败！');
                    }
                });
                </script>
            </div>
        </div>
        <div class="foot">
            <div class="copyright">
                ${msite_copyright}
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
    </div>
</body>
</html>