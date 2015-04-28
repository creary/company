﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>短信群发</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"open.jquery.jquery",
	"x:mti.Validator",
	"x:mti.ajaxSubmit",
	"cloudac:hourglassSelect"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery");    
	function bulkSendSms() {
	    var mainForm = document.mainForm;
		jQuery("option", mainForm.selectedItems).prop("selected", true);
		if (rlx.mti.validate(mainForm)) {
			if(confirm("确认群发该短信？")){
				mainForm.action = "${base}portal/advertiseSend.do?type=sms";
				rlx.mti.ajaxSubmit("mainForm");
			}
		}
	}
	
	window.bulkSendSms = bulkSendSms;	
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
            <h1>短信群发</h1>
        </div>
        <div class="soof_body">
            <table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                    	<div class="mc_wrapper">
                            <form name="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${dto.id}" />
                                <div class="section first">
                                    <h3>选择发送对象</h3>
                                    <p class="p5"> 请从左侧的通讯录中选择发送对象，双击手机号码即可选择。 </p>
                                    <p class="p10">
                                    <table class="soof_hourglass_select" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <th class="label" style="width:195px; text-align:left;">通讯录</th>
                                            <th class="label" style="width:120px;">&nbsp;</th>
                                            <th class="label" style="width:195px; text-align:left;">发送对象</th>
                                        </tr>
                                        <tr>
                                            <td class="field_ctn">
                                                <select id="selectableItems" style="WIDTH:195px; height:200px; margin-top:5px;" multiple="multiple" name="selectableItems"  ondblclick="moveSelectedTo('selectableItems', 'selectedItems');">
                                                    <c:forEach items="${accounts}" var="a">
	                                                    <option value="${a.username}">${a.username}</option>
                                                    </c:forEach> 
                                                </select>
                                                <br />
                                                <span id="filterCtn1"></span></td>
                                            <td class="field_ctn col_buttons"><button type="button" onclick="moveSelectedTo('selectableItems', 'selectedItems');">-&gt;</button>
                                                <br />
                                                <button type="button" onclick="moveSelectedTo('selectedItems', 'selectableItems');">&lt;-</button>
                                                <br />
                                                <button type="button" onclick="moveAllTo('selectableItems', 'selectedItems');">全部&gt;</button></td>
                                            <td class="field_ctn"><select id="selectedItems" style="WIDTH:195px; height:200px; margin-top:5px;" multiple="multiple" name="selectedItems" ondblclick="moveSelectedTo('selectedItems', 'selectableItems');">
                                                </select>
                                                <br />
                                                <span id="filterCtn2"></span></td>
                                        </tr>
                                    </table>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>发送内容</h3>
                                    <p class="p5" style="color:red;">请确保发送的短信内容符合现行法律法规要求。</p>
                                    <p class="p10">
                                        <textarea class="field" style="height:80px; width:505px;" name="smsContent" dataType="Require"></textarea>
                                    </p>
                                </div>
                                <div class="soof_action_bar">
                                    <button class="btn primary" onclick="bulkSendSms()">发 送</button>
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