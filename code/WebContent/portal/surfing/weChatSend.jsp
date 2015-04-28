<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信群发</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"open.jquery.jquery",
	"x:mti.Validator",
	"x:mti.ajaxSubmit"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery");
	function bulkSendWeChat() {
	    var mainForm = document.mainForm;
		if (rlx.mti.validate(mainForm)) {
			var title = jQuery("select[name='adId']").find("option:selected").text();
			if(confirm("确认群发该篇商讯：" + title + "?")){
				mainForm.action = "${base}portal/advertiseSend.do";
				rlx.mti.ajaxSubmit("mainForm");
			}
		}
	}
	
	window.bulkSendWeChat = bulkSendWeChat;	
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}form.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
    <div class="soof_page_viewport">
        <div class="soof_header">
            <h1>微信群发</h1>
        </div>
        <div class="soof_body">
            <table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                    	<div class="mc_wrapper">
                            <form name="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="type" value="${type}" />
                                <div class="section first">
                                    <h3>群发说明</h3>
                                    <p class="p5">
                                    	微信群发可把商讯直接发送至微信公众号，公众号的粉丝都可收到该文章消息。
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>选择要发送的商讯</h3>
                                    <p class="p10">
                                    <select name="adId" class="field l">
                                        <c:forEach items="${ads}" var="ad">  
                                            <option value="${ad.id}">${ad.title}</option>  
                                        </c:forEach> 
                                    </select>	                    
                                    </p>
                                </div>
                                <div class="soof_action_bar">
                                    <button class="btn primary" onclick="bulkSendWeChat()">发 送</button>
                                </div>
                            </form>
                            <div class="widthholder"></div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>