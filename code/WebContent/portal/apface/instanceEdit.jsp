﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title} Portal</title>
<link rel="rl-page-icon" href="${imagePath}${image}" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.Validator",
	"open.jquery.jquery",
	"lib.rpc.JqueryAjaxDirect"
);

orderjs(function(){
    var track = this.track;
	var jQuery = orderjs("open.jquery.jquery"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect");
	
	var actionType = "${action}".toLowerCase();
	
	jQuery.ajaxSetup({
		dataType : "json",
		type : "post",
		cache : false
	});
	
	var portalDirect = new JqueryAjaxDirect({
		services : {
			doAction : function(options){
				var url = "${base}portal/instance${action}.do",
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
	
	function openEditPortalDetail(id){
		var url = "${base}portal/" + id + "/global/edit.do";
		rl.page.open(url, "portalDetailReadyEdit", rl.gui.winDlgOpt);
	}
	
	function notifyPortalChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.portalDataObserver)){
			opener.portalDataObserver.fireEvent("change");
		}
	}
	
	function doAction() {
	    var mainForm = document.mainForm;
			
		if (rlx.mti.validate(mainForm)){
			if(actionType == "save"){
				var tids = jQuery("input[name='tid']:checked");
				if(!tids.length){
					alert("请选择模板！");
					return false;
				}
			}
			portalDirect.invoke("doAction", {
				beforeSend : setAjaxStartView,
				success : function(id){
					if(id){
						if(confirm("${title}成功，是否立即编辑 Portal 详细设置？")) openEditPortalDetail(id);
					}
					else{
						alert("${title}成功！");
					}
					
					notifyPortalChangeToOpener();
					rl.page.close();
				},
				complete : setAjaxEndView
			});
		}
	}
	
    rl.onReady(function(){
		jQuery("#btn_action")
			.prop("disabled", false)
			.on("click", doAction);
		
		if(actionType == "save"){
			//auto check first
			jQuery("input[name='tid']:first").prop("checked", true);
			//bind check behaviour to tpl cards.
			jQuery("#tplList li")
				.on("click", function(){
					jQuery(this)
						.find("input[name='tid']")
						.prop("checked", true);
				});
		}
		
		orderjs(
			"css>open.jquery-poshytip.tip-twitter.tip-twitter",
			"open.jquery-poshytip.jquery-poshytip"
		);
		orderjs(function(){
			jQuery("#tplList li").poshytip({
				className: "tip-twitter",
				alignTo : "target",
				alignX : "right",
				alignY : "center"
			});
		});
	});
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}editable_cards.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.soof_editable_cards li{
	width:150px;
	height:200px;
	margin: 0 36px 36px 0;
	cursor:pointer;
}
</style>
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>${title} Portal</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${dto.id}" />
                                <input type="hidden" name="branchId" value="${dto.branchId}" />
                                <div class="section first">
                                	Portal 指为用户提供 WiFi 上网认证功能的微型门户网站。一般是由欢迎页、认证页、认证后页等页面构成。当您为 WiFi 网络设置了 Portal 之后，用户在访问您的 WiFi 时，将被强制浏览这些页面，才能上网。因此，您可以利用这些页面实现广告宣传和品牌营销。
                                </div>
                                <div class="section">
                                    <h3>Portal 名称</h3>
                                    <p class="p10">
                                        <input name="name" dataType="Require" class="field" value="${dto.name}"/> <font color="red">*</font>
                                    </p>
                                    <div class="p10">
                                        <input type="checkbox" name="tag" id="field_tag" class="field_selector" value="1" ${dto.tag==1?"checked":""}/><label class="selector_label" for="field_tag">设为默认 Portal</label>
                                        <p class="desc" style="padding-left:19px;">当新设备接入时，使用该 Portal 作为默认 Portal。</p>
                                    </div>
                                </div> 
                                <c:if test="${templates ne null}">
                                    <div class="section">
                                        <h3>选择模板</h3>
                                        <p class="p5">模板决定各页面的排版布局风格。</p>
                                        <ul id="tplList" class="soof_editable_cards p10">
                                            <c:forEach items="${templates}" var="template">  
                                                <li title="${template.descr}">
                                                    <div class="card">
                                                        <img class="thumbnail" src="${template.cover}" />
                                                    </div>
                                                    <p class="p5">
                                                        <input type="radio" name="tid" id="field_tid_${template.id}" class="field_selector" value="${template.id}" /><label class="selector_label" for="field_tid_${template.id}">${template.name}</label>
                                                    </p>
                                                </li>
                                            </c:forEach> 
                                        </ul>
                                        <div class="clearer"></div>
                                    </div> 
                                </c:if>
                                <c:if test="${templates eq null}">
                                    <div class="section">
                                        <h3>当前模板</h3>
                                        <p class="p5">模板决定各页面的排版布局风格。当前模板为：${dto.template}</p>
                                        <p class="p10">
                                            <img src="${dto.cover}" border="0" />
                                        </p>
                                    </div> 
                                </c:if>	 
                                <div class="soof_action_bar">
                                    <button id="btn_action" type="button" class="btn primary">保 存</button>
                                    <button type="button" class="btn white" onclick="rl.page.close();">取消</button>
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