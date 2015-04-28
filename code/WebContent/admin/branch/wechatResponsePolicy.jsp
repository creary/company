﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信认证 IP 回复策略管理</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs.config("shim", {
	"open.jsrender.jsrender" : {
		deps : ["open.jquery.jquery"]
	}
});

orderjs(
	"open.jquery.jquery",
	"lib.rpc.JqAjax",
	"cloudac:SortableCardsEditor"
);

orderjs(function(){
    //rl.console.showOnReady();
	var jQuery = orderjs("open.jquery.jquery"),
		JqAjax = orderjs("lib.rpc.JqAjax"),
		SortableCardsEditor = orderjs("cloudac:SortableCardsEditor");
    
	//observe policies data.
	window.wechatRspPoliciesObserver = new rl.util.EventProvider;
	wechatRspPoliciesObserver.on("change", function(){
		wechatRspPoliciesObserver.changed = true;
	});
	
	JqAjax.request({
		url : "${base}admin/getWechatIPPolicies.do",
		success : function(data){
			rl.onReady(function(){
				var policiesEditor = new SortableCardsEditor({
					ascendingItems : false,
					target : "#wechatRspPolicies_editor",
					iniValues : data,
					itemTpl : "#wechatRspPolicies_coverTpl",
					deleteItem : function(listItem){
						var me = this,
							values = this.getItemValues(listItem);
						if(this.fireEvent("beforeDeleteItem", values) === false) return false;
						
						if(confirm(this.deleteItemConfirmMsg)){
							return JqAjax.request({
								url : "${base}admin/wechatIPPolicyDelete.do?id=" + values.id,
								success : function(data){
									jQuery(listItem).remove();
									me.fireEvent("itemsChange", "delete", values);
								},
								error : function(msg){
									alert(msg);
								}
							});
						}						
						return false;
					},
					itemEditor : {
						startAdd : function(){
							policiesEditor.cancelAddItem();
							var url = "${base}admin/readyEditWechatIPPolicy.do",
								dlg = rl.page.open(url, "addWechatRspPolicy", rl.gui.winDlgOpt);
							
							dlg.on("close", function(){
								if(wechatRspPoliciesObserver.changed) location.reload();
							});
						},
						startEdit : function(values){
							policiesEditor.cancelEditItem();
							var url = "${base}admin/readyEditWechatIPPolicy.do?id=" + values.id,
								dlg = rl.page.open(url, "editWechatRspPolicy", rl.gui.winDlgOpt);
							
							dlg.on("close", function(){
								policiesEditor.cancelAddItem();
								if(wechatRspPoliciesObserver.changed) location.reload();
							});
						}
					},
					initEvents : function(){
						var me = this;
						
						this.jq
							.on("mouseover", function(event){
								jQuery(event.target)
									.closest(me.itemSelector + " .card", this)
									.addClass(me.cardHoverCss);
							})
							.on("mouseout", function(event){
								jQuery(event.target)
									.closest(me.itemSelector + " .card", this)
									.removeClass(me.cardHoverCss);
							})
							.on("click", function(event){
								var trigger_del = jQuery(event.target).closest(me.triggerOfDeleteItem, this)[0],
									listItem = jQuery(event.target).closest(me.itemSelector, this)[0];
								
								if(listItem){
									if(trigger_del){
										me.deleteItem(listItem);
										event.stopPropagation();
									}
									else{
										me.startEditItem(listItem);
									}
								}
								else{
									var trigger_add = jQuery(event.target).closest(me.triggerOfAddItem, this)[0];
									if(trigger_add){
										me.startAddItem();
									}
								}
							});
						
						return this;
					}
				});
			});
		},
		error : function(msg){
			alert(msg);
		}
	});
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}editable_cards.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}icons.css" rel="stylesheet" type="text/css" />
</head>
<body class="theme">
<div class="soof_page">
	<div class="soof_page_viewport">
    	<div class="soof_header">
        	<h1>微信认证 IP 回复策略管理</h1>
        </div>
        <div class="soof_body">
        	<table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="mc_col">
                        <div class="mc_wrapper">
							<script id="wechatRspPolicies_coverTpl" type="text/x-jsrender">
                                <li>
                                    <div class="card">
                                        <img class="overlay trigger_delete_item" src="${imagePath}circle_close.gif" alt="删除" title="删除" />
                                        <div class="title">{{:name}}</div>
                                        <div class="cnt_wrap">
                                            <img class="thumbnail" src="{{>cover}}" />
                                            <a class="btn" href="javascript:void(0);">
                                                <div>编 辑</div>
                                            </a>
                                        </div>
                                    </div>
                                </li>
                            </script>
                            <div class="section first">
                                <p class="p5">
                                    当用户在微信公众号发送认证请求时，除了系统默认的回复，您还可以根据用户所在网络的出口 IP 来定义个性化的回复消息，从而实现用户在不同地点（根据 IP 判断）进行请求时，显示不同的欢迎消息。注：该功能适用于具有固定出口 IP 的网络。
                                </p>
                                <div class="p10">
                                    <ul id="wechatRspPolicies_editor" class="soof_editable_cards titled_cards">
                                        <li class="static_item trigger_add_item">
                                            <div class="card static_card white">
                                                <a class="btn" href="javascript:void(0);">
                                                    <div style="padding-top:60px;">
                                                        <span class="soof_icon plus_gray s32"></span>
                                                        <p class="p10">添加策略</p>
                                                    </div>
                                                </a>
                                            </div>
                                        </li>
                                    </ul>
                                    <div class="clearer"></div>
                                </div>
                            </div>
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