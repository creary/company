<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${msite_title}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="{autoLoadLang:false}" src="${jsPath}rl/src/order.js?_v=${cacheBuster}"></script>
<link rel="icon" href="${imagePath}wifi_favicon.ico?_v=${cacheBuster}" type="image/x-icon"/>
<link href="${base}resources/css/common.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${currentTplContext}view/theme/default/login.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
</head>
<body class="smart_layout">
<div class="head">
	<img src="${msite_logo}" border="0" class="logo" />
</div>
<div class="main">
    <div class="login">
    	<div class="login_viewport">
            <span style="font-size:18px;">登录 WiFi</span>
            <jsp:include page="${moduleRoot}SignInTabPanel/view/Module.jsp" />
        </div>
    </div>
</div>
<c:if test="${enablePreRoll ne null}">
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
    orderjs(function(){
        //rl.console.showOnReady();
        var enablePreRoll = ${enablePreRoll};
        if(!enablePreRoll) return ;
        
        orderjs(
            "open.jquery.jquery",
            "cloudac:PrerollAdPlayer",
            "cloudac:AdSource"
        );
        orderjs(function(){
            var jQuery = orderjs("open.jquery.jquery"),
                PrerollAdPlayer = orderjs("cloudac:PrerollAdPlayer"),
                AdSource = orderjs("cloudac:AdSource"),
                formValidations = window.formValidations;
            
            function submitForm(form){
                if(!jQuery(form).is("form")) return false;
                var name = form.name;
				var v = formValidations[name];
				if(v){
					if(v(form) === false) return false;
				}
				
                var errMsg = "验证失败:\n服务器错误",
					checkUserUrl = "${base}wifiant/checkUser.do?${queryString}" + "&authType=" + name,
                    authUrl = "${base}wifiant/authenticate.do?${queryString}" + "&authType=" + name,
                    data = jQuery(form).serialize();
                
				jQuery.ajax({
					url : checkUserUrl,
					type : "post",
					dataType:"json",
					cache : false,
					data : data,
					success : function(data, status, jqXHR){
						if(data){
							if(data.status == 1){
								prerollPlayer.fakeEndAction = function(){
									submitForm.prevFn(form);
								};
								
								//blur input to hide the cursor pointer on the phone 
								//which use third party input method.
								jQuery("input", form).blur();
								prerollPlayer.start();
								return;
							}
							else if(data.msg) errMsg = data.msg;
						}
						alert(errMsg);
					},
					error : function(){
						alert(errMsg);
					}
				});
                
                return false;
            }
            
            var prerollPlayer = new PrerollAdPlayer({
                adCtn : "#prerollCtn",
                boardEle : "#prerollBoard",
                countdown : parseInt("${preRollDuration}"),//use parseInt to avoid the dynamic var being empty.
				countdownFakeEnd : 1,
                countdownTip : "您的请求已接收，请稍等 {left}s",
				endedTip : "正在为您开通网络...",
                tipCtn : ".tip .i_wrap"
            });
            
			var cnt;
			try{
				var fullscreenImages = JSON.parse('${fullscreenImages}' || "null");
				if(fullscreenImages && fullscreenImages.length > 0){
					var img = getRandomItems(fullscreenImages, 1)[0];
					cnt = '<a' + (img.href ? (' href="' + img.href + '"') : '') + '>' +
							'<img class="img_preroll_holder" src="' + img.src + '" />' +
						'</a>';
				}
			}
			catch(err){
				cnt = '<div style="text-align:center; padding: 2em;">图片加载错误：数据解析失败！';
			}
			prerollPlayer
				.setSource(new AdSource({
					content : cnt
				}))
				.load();
            
            //expose, save old submitForm for renference.
            submitForm.prevFn = window.submitForm;
            window.submitForm = submitForm;
        });
    
    });
    </script>
    <link href="${currentTplContext}view/theme/default/preroll_board.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
    <div id="prerollBoard" class="preroll_board">
        <div class="tip">
            <div class="i_wrap"></div>
        </div>
        <div class="preroll_ctn" id="prerollCtn">
        </div>
    </div>
</c:if>
<div class="foot">${msite_copyright}</div>

</body>
</html>