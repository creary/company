<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑</title>
<link rel="rl-page-icon" href="${imagePath}user_16.gif" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"x:mti.popupFormPage"
);

orderjs(function(){
	function doAction() {  
	    var mainForm = document.mainForm;
	    if (rlx.mti.validate(mainForm)) {
			if(mainForm.password.value != mainForm.password0.value){
				alert("两次密码不一致");
				return false;
			} 
			mainForm.action = "${base}admin/userUpdate.do";
			rlx.mti.ajaxSubmit("mainForm","closeAndRefreshOpenerGrid");
	    }
	}	
	rlx.mti.createPopupFormPage({
		content : "#mainContent",
		actionBar : [
			{text : "确定", action : doAction},
			{text : "取消", action : function(){rl.page.close();}}
		]
	});
});

</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
    <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
        <input type="hidden" name="username" value="${dto.username}" />
        <div class="group">
            <table border="0" class="grid_fields grid_fields_cols4">
                <tr>
                    <th class="label">用户名</th>
                    <td class="field_ctn">
                        ${dto.username}                   
                    </td>
                    <th class="label">姓名</th>
                    <td class="field_ctn">
                        <input class="field" name="fullname" dataType="Require" msg="必填项" value="${dto.fullname}"/>
                        <font color="red">*</font>
                    </td>
                </tr>                
                <tr>
                    <th class="label">密码</th>
                    <td class="field_ctn">
                        <input class="field" name="password" type="password"  />
                    </td>
                    <th class="label">确认密码</th>
                    <td class="field_ctn">
                        <input class="field" name="password0" type="password" />
                    </td>
                </tr>
                <tr>
                    <th class="label">手机号</th>
                    <td class="field_ctn"><input class="field" name="phone" value="${dto.phone}" dataType="Mobile" msg="必须是手机号" /><font color="red">*</font></td>
                    <th class="label">Email</th>
                    <td class="field_ctn"><input class="field" name="email" value="${dto.email}" dataType="Email" msg="必须是Email" /><font color="red">*</font></td>
                </tr>                                
            </table>
        </div>
    </form>
</div>
</body>
</html>