<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的帐号</title>
<link rel="rl-page-icon" href="${imagePath}user_16.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"x:mti.ajaxSubmit"
);

orderjs(function(){
	function doUpdateAccount() {
		var form = document.mainForm;
		if (rlx.mti.validate(form, 3)) {
			form.action = "${base}/admin/userUpdatePerson.do";
			rlx.mti.ajaxSubmit("mainForm","close");
		}
	}
	
	function doUpdatePassword() {
		var form = document.passwordForm;
		if (rlx.mti.validate(form, 3)) {
			form.action = "${base}/admin/userUpdatePerson.do";
			rlx.mti.ajaxSubmit(form, function(status, msg){
				alert(msg);
				form.reset();//avoid browser auto-remember mechanism.
			});
		}
	}
	
	var currentCardEditorId = "";
	function showCardEditor(cardId){
		if(!rl.isNonNullStr(cardId) || currentCardEditorId == cardId) return;
		var ele = rl.getDom(cardId);
		if(!ele) return;
		
		if(rl.isNonNullStr(currentCardEditorId)){
			rl.$(currentCardEditorId).hide();
		}	
		currentCardEditorId = cardId;
		rl.$(cardId).show();
	}
	
	rl.onReady(function(){
		showCardEditor("editAccount");	
	});
	
	//expose local to global
	window.doUpdateAccount = doUpdateAccount;
	window.doUpdatePassword = doUpdatePassword;
	window.showCardEditor = showCardEditor;
});
</script>
<style type="text/css">
.info_card{
	width:480px;
	margin:0 auto;
	line-height:20px;
}
.info_card .columns_layout{
	width:100%;
}
.info_card .gravatar{
	width:90px;
	height:50px;
}
.info_card .gravatar .wrapper{
	width:36px;
	height:36px;
	margin:0 auto;
	border:solid 1px #eee;
	text-align:center;
}
.info_card .gravatar .icon{
	width:32px;
	height:32px;
	margin-top:2px;
	border:none;
}
.info_card .fields_layout{
	margin:0 auto;
}
.info_card .fields_layout td,
.info_card .fields_layout th{
	height:35px;
}
.info_card .fields_layout th{
	width:60px;
	text-align:right;
	padding-right:5px;
	font-weight:normal;
}
.info_card .split_line{
	height:1px;
	font-size:1px;
	border-bottom:solid 1px #eee;
	margin:1em auto;
}
.info_card .field{
	width:60%;
	height:20px;
}
.info_card .bottom_bar{
	margin-top:1.5em;
	text-align:center;
}
.info_card .desc{
	color:gray;
}

#editPassword{
	display:none;
}
#editPassword .fields_layout th{
	width:100px;
}
</style>
</head>
<body>
<div id="editAccount" class="info_card" style="margin-top:1em;">
	<div class="wrapper">
    	<form name="mainForm" id="mainForm" method="post">
            <input type="hidden" name="username" value="${dto.username}" />
            <input type="hidden" name="branchId" value="${dto.branchId}" />
            <table class="columns_layout" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="gravatar">
                    	<div class="wrapper">
                        	<img src="${imagePath}user_32.gif" class="icon" />
                        </div>
                    </td>
                    <td style="font-size:14px;">
                    	${dto.username}
                    </td>
                    <td>
                    	<button type="button" style="float:right;" onclick="showCardEditor('editPassword');">修改密码</button>
                    </td>
                </tr>
            </table>
            <div class="split_line"></div>
            <table class="fields_layout" border="0" cellpadding="0" cellspacing="0" style="width:90%;">            
                <tr>
                     <th>商家:</th>
                     <td>${dto.branch}</td>
                </tr>                            
                <tr>
                     <th>姓名:</th>
                     <td><input type="text" class="field" name="fullname" value="${dto.fullname}" dataType="Require" msg="必填项" /></td>
                </tr> 
                <tr>
                    <th>手机:</th>
                    <td><input name="phone" class="field" type="text" value="${dto.phone}" dataType="Mobile" msg="必须是手机号" /></td>
                </tr>            
                <tr>
                    <th>Email:</th>
                    <td><input type="text" class="field" name="email" value="${dto.email}" dataType="Email" msg="必须是Email" /></td>
                </tr>                
            </table>
            <div class="split_line"></div>
            <div class="bottom_bar">
            	<button type="button" onclick="doUpdateAccount();">确定</button>
                &nbsp;&nbsp;&nbsp;
            	<button type="button" onclick="rl.page.close();">取消</button>
            </div>
		</form>
    </div>
</div>
<div id="editPassword" class="info_card" style="margin-top:2em;">
	<div class="wrapper">
    	<form name="passwordForm" id="passwordForm" method="post" autocomplete="off">
            <input type="hidden" name="username" value="${dto.username}" />
            <input type="hidden" name="fullname" value="${dto.fullname}" />
            <input type="hidden" name="branchId" value="${dto.branchId}" />            
            <table class="fields_layout" border="0" cellpadding="0" cellspacing="0" style="width:90%;">
                <tr>
                    <th><font color="red">*</font>旧密码:</th>
                    <td><input name="oldPwd" type="password" class="field" dataType="Require" msg="必填项"/></td>
                </tr>
                <tr>
                    <th><font color="red">*</font>新密码:</th>
                    <td><input name="password" type="password" class="field" dataType="Require" msg="必填项"/></td>
                </tr>
                <tr>
                    <th><font color="red">*</font>确认密码:</th>
                    <td><input name="password0" type="password" class="field" dataType="Repeat" to="password" msg="两次密码不一致"/></td>
                </tr>
            </table>
            <div class="split_line"></div>
            <div class="bottom_bar">
            	<button type="button" onclick="doUpdatePassword();">确定</button>
                &nbsp;&nbsp;&nbsp;
            	<button type="button" onclick="showCardEditor('editAccount');">取消</button>
            </div>
		</form>
    </div>
</div>
</body>
</html>