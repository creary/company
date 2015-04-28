<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Portal 设置【${portal.name}】</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"open.jquery.jquery",
	"x:mti.Validator",
	"x:mti.ajaxSubmit"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery");
    
	function saveForm() {
	   var mainForm = document.mainForm;
	   if (rlx.mti.validate(mainForm)) {
		   mainForm.action = "${base}portal/${pid}/signin/save.do";
		   rlx.mti.ajaxSubmit("mainForm");
	   }
	}
	
	window.saveForm = saveForm;
	
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>Portal 设置【${portal.name}】</h1>
            <jsp:include page="tab.jsp" >
	            <jsp:param name="tab" value="signin" />
	        </jsp:include>	                        
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                    	<div class="mc_wrapper">
                        	<form name="mainForm" method="post" onsubmit="return false;">
                                <jsp:include page="${currentTplRoot}edit/signin_edit.jsp" />
                                <div class="soof_action_bar">
                                    <button type="button" class="btn primary" onclick="saveForm()">保 存</button>
                                    <button class="btn white" onclick="rl.page.close()">取消</button>
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
