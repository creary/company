<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}认证策略</title>
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
	
	jQuery.ajaxSetup({
		dataType : "json",
		type : "post",
		cache : false
	});
	
	var portalDirect = new JqueryAjaxDirect({
		services : {
			doAction : function(options){
				var url = "${base}portal/policySimpleSaveOrUpdate.do",
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
	
	function openEditPolicyDetail(id){
		var url = "${base}portal/policyReadyEditAuth.do?id=" + id;
		rl.page.open(url, "policyReadyEditAuth", rl.gui.winDlgOpt);
	}
	
	function notifyChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.authPolicyDataObserver)){
			opener.authPolicyDataObserver.fireEvent("change");
		}
	}
	
	function doAction() {
	    var mainForm = document.mainForm;
			
		if (rlx.mti.validate(mainForm)){
			portalDirect.invoke("doAction", {
				beforeSend : setAjaxStartView,
				success : function(id){
					if(id){
						if(confirm("${title}成功，是否立即编辑策略详细设置？")) openEditPolicyDetail(id);
					}
					else{
						alert("${title}成功！");
					}					
					notifyChangeToOpener();
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
	margin-right:36px;
	cursor:pointer;
}
</style>
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>${title}认证策略</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
                            <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                                <input type="hidden" name="id" value="${dto.id}" />
                                <div class="section first">
                                	认证配置，即对访问 WiFi 网络的用户，设置是否需要认证、认证方式、断开时间等。为了便于管理，我们将以上多个认证配置保存为一个策略，而设置 WiFi 网络的认证配置时，只需指定其策略。
                                </div>
                                <div class="section">
                                    <h3>策略名称</h3>
                                    <p class="p10">
                                        <input name="name" dataType="Require" class="field" value="${dto.name}"/> <font color="red">*</font>
                                    </p>
                                    <div class="p10">
                                        <input type="checkbox" name="tag" id="field_tag" class="field_selector" value="1" ${dto.tag==1?"checked":""}/><label class="selector_label" for="field_tag">设为默认策略</label>
                                        <p class="desc" style="padding-left:19px;">当新设备接入时，使用该策略作为默认认证策略。</p>
                                    </div>
                                </div> 
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