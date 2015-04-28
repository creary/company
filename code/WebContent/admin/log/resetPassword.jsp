<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="reset_password" /></title>
<link href="${base}resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}resources/css/form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator"
);
orderjs(function(){
	function checkForm() {
	    var mainForm = document.mainForm;
	    if (rlx.mti.validate(mainForm)) {
	        mainForm.submit();
	    }		
		return false;
	}
    window.checkForm = checkForm;
});
</script>
</head>
<body>
<div class="cloudac_theme_std cloudac_limit_std">
	<div class="cloudac_head">
    	<div class="head_wrapper">
        	<table border="0" style="width:100%;" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="bottom"><a href="${base}"><img src="${base}${pcfg.logo1}" border="0" align="baseline" class="logo" /></a> <span style="color:gray; font-size:16px;">| 重置密码</span></td>
                </tr>
            </table>
        </div>
    </div>
    <div class="cloudac_sep_line"></div>
    <div class="cloudac_mbody">
    	<div class="mbody_wrapper" style="font-size:14px; line-height:1.5em; margin-top:4em;">
            <form name="mainForm" class="cloudac_form cloudac_reg_form" action="${base}acs/resetPassword.do" method="post" onsubmit="return checkForm();" style="width:800px;" autocomplete="off">
                <input type="hidden" name="username" value="${username}" />
                <input type="hidden" name="key" value="${key}" />
                <div id="inputs" class="inputs">
                    <div class="inputline indent_to_field">
                        <span class="desc"><spring:message code="reset_password_input_tip" /></span>
                    </div>
                    <div class="inputline">
                        <label for="password_id" class="selector_label">密码</label>
                        <input class="field" name="password" id="password_id" type="password" dataType="Require"  /><font color="red">*</font>
                    </div>
                    <div class="inputline">
                        <label for="password_confirm_id" class="selector_label">确认密码</label>
                        <input class="field" name="password0" id="password_confirm_id" type="password" dataType="Repeat" to="password" /><font color="red">*</font>
                    </div>
                    <div class="inputline indent_to_field">
                        <input type="image" src="${langImagePath}login/btn_submit.gif" />
                    </div>
                </div>
            </form>
    	</div>
    </div>
</div>
</body>
</html>