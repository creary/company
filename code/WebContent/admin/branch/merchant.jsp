﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商家信息</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"gui.form.FollowedSelect",
	"x:mti.Validator",
	"open.jquery.jquery",
	"lib.rpc.JqueryAjaxDirect"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect");
	
	jQuery.ajaxSetup({
		dataType : "json",
		type : "post",
		cache : false
	});
	
	var mainDirect = new JqueryAjaxDirect({
		services : {
			save : function(options){
				var url = "${base}admin/saveMerchant.do",
					data = jQuery("#mainForm").serialize();				
				jQuery.ajax(rl.ext(options, { url : url, data : data}));
			}
		}
	});
	
	function setAjaxStartView(){
		jQuery("#btn_action")
			.prop("disabled", true)
			.addClass("disabled")
			.text("正在保存...");
	}
	function setAjaxEndView(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.removeClass("disabled")
			.text("保 存");
	}
	
	function doAction() {
	    var mainForm = document.mainForm;
		if (rlx.mti.validate(mainForm)) {
			mainDirect.invoke("save", {
				beforeSend : setAjaxStartView,
				success : function(){
					alert("保存成功！");
				},
				complete : setAjaxEndView
			});
		}
	}
	
	rl.gui.docoFollowedSelects();
	rl.onReady(function(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
	});
});
</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>商家信息</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${dto.id}" />
                                <div class="section first">
                                    <h3>商家名称</h3>
                                    <p class="p10">
                                        <span><input class="field" name="name" value="${dto.name}" dataType="Require" /> <font color="red">*</font> </span> <span class="desc">例：广州白云大酒店</span>
                                    </p>
                                </div>
                                <div class="section section_unite">
                                    <h3>商家简称</h3>
                                    <p class="p10">
                                        <span><input class="field" name="shortName" value="${dto.shortName}" dataType="Require" /> <font color="red">*</font> </span> <span class="desc">例：白云酒店</span>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>所在地</h3>
                                    <p class="p10">
                                        ${provinceBox}&nbsp;${cityBox}&nbsp;${countyBox}
                                    </p>
                                </div>
                                <div class="section section_unite">
                                    <h3>详细地址</h3>
                                    <p class="p10">
                                        <span><input class="field" name="address" dataType="Require" value="${dto.address}"/> <font color="red">*</font> </span> <span class="desc">例：珠江新城 海月路12号(不需要重复录入所在地) </span>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>联系人</h3>
                                    <p class="p10">
                                        <span><input class="field" name="contact_man" dataType="Require" value="${dto.contactMan}"/> <font color="red">*</font> </span> <span class="desc">例：陈先生 </span>
                                    </p>
                                </div>
                                <div class="section section_unite">
                                    <h3>联系电话</h3>
                                    <p class="p10">
                                        <span><input class="field" name="contact_phone" dataType="Custom" regexp="((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)" msg="电话格式错误" value="${dto.contactPhone}"/>  <font color="red">*</font> </span> <span class="desc">例：020-90009000 或 400-0082458 或 13690009000</span>
                                    </p>
                                </div>
                                <div class="section">
                                    <h3>所在行业</h3>
                                    <p class="p10">
                                        ${industryBox}&nbsp;${subIndustryBox}
                                    </p>
                                </div>

                                <div class="soof_action_bar">
                                    <button id="btn_action" type="button" class="btn primary">保 存</button>
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