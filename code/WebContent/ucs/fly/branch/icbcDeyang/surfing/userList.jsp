<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<%@page import="com.soofound.framework.util.SysConfigHelper"%>
<% 
	request.setAttribute("pcfg",SysConfigHelper.getProjectConfig());	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="position: relative; min-height: 100%;">
<head>
    <title>工商银行 WiFi 账户生成系统</title>
    <link rel="shortcut icon" href="${base}${pcfg.favicon}" type="image/x-icon"/> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
    <link href="${base}ucs/fly/portal/icbcDeyang/resources/css/mui.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
	.soof_mui_icbc .main_wrap{
		margin:20px;
	}
	.soof_flex_grid{
	}
	.soof_flex_grid .g_head{
		border-bottom:solid 1px #ccc;
	}
	.soof_flex_grid .g_body{
	}
	.soof_flex_grid .g_body .row{
		padding: 1em;
		border-bottom:dashed 1px #ccc;
	}
	.soof_flex_grid .g_body .row_layout{
		width:100%;
	}
	.soof_flex_grid .cols_group .cell{
		float:left;
	}
	.soof_flex_grid .cols3 .cell{
		width:33%;
	}
	.soof_flex_grid .g_foot{
		padding:2em 0;
		text-align:center;
	}
	.soof_flex_grid .g_head .bar{
		padding:10px 0;
	}
	.user_list .g_head .bar .btn,
	.user_list .g_head .bar .soof_search{
		height:25px;
		vertical-align:middle;
	}
	.soof_flex_grid .g_head .filters{
		height:40px;
		line-height:40px;
	}
	.soof_flex_grid .g_head .filters a{
		display:inline-block;
		margin-right:2em;
		height:30px;
		line-height:30px;
	}
	.soof_flex_grid .filters a.selected{
		font-weight:bold;
	}
	.soof_progressbar{
		position:relative;
		left:0;
		top:0;
		height:20px;
		border:solid 1px #ccc;
	}
	.soof_progressbar .progress{
		height:100%;
	}
	.soof_progressbar .progress.disabled{
		background-color:#eee;
	}
	.soof_progressbar .text{
		position:absolute;
		z-index:1;
		left:0;
		top:0;
		width:100%;
		height:100%;
		vertical-align:middle;
		text-align:center;
	}
	.soof_icon{
		display:inline-block;
		vertical-align:middle;
		background-repeat:no-repeat;
	}
	.soof_icon.user_48{
		width:48px;
		height:48px;
		background-image:url(${base}resources/image/default/mix/ctg_icons.png);
	}
	.soof_icon.user_48.blue{
		background-position:-48px 0;
	}
	.soof_icon.user_48.gray{
		background-position:0 0;
	}
	.user_list .user_icon{
		width:60px;
	}
	.user_list .username{
		height:30px;
		line-height:30px;
	}
	.user_list .actions{
		float:right;
	}
	.user_list .progress.used{
		background-color:#C6ECFF;
	}
	.user_list .rxText{
		padding-right:1em;
	}
	.user_list .actions .btn{
		margin-top:-3px;
		height:25px;
	}
	.user_list .sel_opr{
		width:100px;
	}
	
	.user_list .offline{
		color:gray;
	}
	.user_list .offline .user_48{
		background-position:0 0;
	}
	.user_list .offline .soof_progressbar .progress.used{
		background-color:#eee;
	}
	.user_list .opr_tip{
		color:gray;
	}
	
	input::-ms-clear{display:none;}
	.soof_search{
		display:inline-block;
		height:25px;
		border:solid 1px #ccc;
		border-radius:5px;
	}
	.soof_search .s_inp{
		float:left;
		margin:1px 5px;
		width:200px;
		height:22px;
		border:none;
	}
	.soof_search input::-ms-clear{display:none;}
	.soof_search .ctrls{
		float:left;
		height:100%;
		word-spacing: 0;
	}
	.soof_search .ctrls .btn{
		height:100%;
		margin:0;
		margin-left:3px;
		padding:2px 12px;
		border:none;
		border-radius:0 5px 5px 0;
		background-color:#f2f2f2;
	}
	.soof_search .ctrls .btn:hover{
		background-color:#f8f8f8;
	}
	.soof_search .ctrls .s_reset{
		display:inline-block;
		visibility:hidden;
		width:20px;
		height:100%;
		cursor:pointer;
		background:url(${base}resources/image/default/icon/input_clear.gif) no-repeat center;
	}
	
	
	@media (max-width:600px){
		.user_list .actions{
			width:100%;
			padding-top:5px;
		}
		.user_list .user_stat{
			display:none;
		}
		.soof_flex_grid .cols3 .cell{
			margin-bottom:5px;
			float:none;
			width:auto;
		}
		.soof_progressbar .text{
			text-align:left;
		}
	}
	</style>
    <script type="text/javascript" data-main="RealLight" data-rlconfig="{ autoLoadLang:false, debugMode:true}" src="${jsPath}rl/src/order.js"></script>
    <script type="text/javascript">
	orderjs.config("shim", {
		"open.jsrender.jsrender" : {
			deps : ["open.jquery.jquery"]
		}
	});
    orderjs(
        "open.jquery.jquery",
		"open.jsrender.jsrender",
		"open.jRecordLoader.jRecordLoader",
		"lib.rpc.JqueryAjaxDirect"
    );
	orderjs(function(){
        //rl.console.showOnReady();
		var jQuery = orderjs("open.jquery.jquery"),
			jRecordLoader = orderjs("open.jRecordLoader.jRecordLoader"),
			JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect");
		
		jQuery.ajaxSetup({
			dataType : "json",
			method : "post",
			cache : false
		});
		
		var mainDirect = new JqueryAjaxDirect({
			services : {
				"reactivate" : function(options, userid){
					var url = rl.format("${base}custom/reactivate.do?branchId=${branchId}&id={1}", arguments);
					jQuery.ajax(rl.ext(options, { url : url}));
				},
				"remark" : function(options, userid, remark){
					var url = rl.format("${base}custom/updateNickname.do?branchId=${branchId}&id={1}", arguments);
					jQuery.ajax(rl.ext(options, { url : url, data : {remark : remark}}));
				},
				"remove" : function(options, userid){
					var url = rl.format("${base}portal/surfingAccountDelete.do?id={1}", arguments);
					jQuery.ajax(rl.ext(options, { url : url}));
				},
				"resetPassword" : function(options, userid){
					var url = rl.format("${base}portal/surfingAccountResetPassword.do?id={1}", arguments);
					jQuery.ajax(rl.ext(options, { url : url}));
				}
			}
		});
		
		var userSearch,
			usersLoader = jRecordLoader("#userListLoaderCtn").init({
				  /**
				   * Url params:
				   * @param {Number} count Specified returned records count.
				   * @param {String} filter Filter result with online status or expire time,
				   *  Valid values: "none", "online", "expiring"
				   * @param {Number} start Specified the start record index.
				   *  This param is auto appended before request.
				   */
				dataUrl : "${base}custom/surfingAccountList.do?branchId=${branchId}&count=20",
				recordsCtn : "#usersRowsCtn",
				recordHtmlTpl : "#userRowTpl"
			});
		
		function loadUsers(){
			var opt = {
				data : {key : userSearch.val()}
			};
			usersLoader.clear();
			usersLoader.loadRecords(opt);
		}
		
		function decoSearchCtrl(selector, options){
			var searchCtrl = jQuery(selector).first();
			if(!(searchCtrl && searchCtrl.length)) return;
			
			if(rl.isFunction(options)) options = {action: options};
			else if(!rl.isObject(options)) options = {};
			
			var inputCtrl = searchCtrl.find(".s_inp"),
				ctrlsCtn = searchCtrl.find(".ctrls"),
				resetCtrl = searchCtrl.find(".s_reset"),
				triggerCtrl = searchCtrl.find(".s_trigger");
			
			searchCtrl.inputCtrl = inputCtrl;
			searchCtrl.resetCtrl = resetCtrl;
			searchCtrl.triggerCtrl = triggerCtrl;
			
			searchCtrl.val = function(){
				var inputCtrl = this.inputCtrl;
				if(inputCtrl.length){
					return inputCtrl.val.apply(inputCtrl, arguments);
				}
			};
			
			if(inputCtrl.length){
				inputCtrl
					.on("keyup input", function(){
						var curSearchCtrl = jQuery(this).closest(".soof_search"),
							resetCtrl = curSearchCtrl.find(".s_reset");
						if(!resetCtrl.length) return;
						var val = jQuery(this).val();
						
						if(val.length == 0) resetCtrl.css("visibility", "hidden");
						else resetCtrl.css("visibility", "visible");
					})
					.keyup();
				
				if(rl.isFunction(options.action)){
					inputCtrl
						.on("keypress", function(event){
							if(event.keyCode == 13){//"enter" key
								options.action();
							}
						});
				}
			}
			
			if(ctrlsCtn.length){
				ctrlsCtn
					.on("click", function(event){
						if(event.target == this){
							var curSearchCtrl = jQuery(this).closest(".soof_search"),
								inpCtrl = curSearchCtrl.find(".s_inp");
							if(!inpCtrl.length) return;
							
							inpCtrl.focus();
						}
					});
			}
			
			if(resetCtrl.length){
				resetCtrl
					.on("click", function(){
						var curSearchCtrl = jQuery(this).closest(".soof_search"),
							inpCtrl = curSearchCtrl.find(".s_inp");
						if(!inpCtrl.length) return;
						
						inpCtrl.val("");
						jQuery(this).css("visibility", "hidden");
						if(rl.isFunction(options.onClear)){
							options.onClear();
						}
					});
			}
			
			if(triggerCtrl.length){
				triggerCtrl
					.on("click", function(){
						var curSearchCtrl = jQuery(this).closest(".soof_search"),
							inpCtrl = curSearchCtrl.find(".s_inp");
						if(!inpCtrl.length) return;
						
						if(inpCtrl.val()){
							if(rl.isFunction(options.action)) options.action();
						}
						else inpCtrl.focus();
					});
			}
			
			return searchCtrl;
		}
		
		function getRow(id){
			if(!rl.isNonNullStr(id)){
				throw new Error(this + " updateRow(): Invalid id: " + id);
			}
			return jQuery("#usersRowsCtn").children("[data-rowid='" + id + "']");
		}
		
		function updateRow(id, data){
			if(! rl.isObject(data)){
				throw new Error(this + " updateRow(): Invalid data: " + data);
			}
			var row = getRow(id);
			if(!row.length) throw new Error("Cant find correspond row for id: " + id);
			
			if(data.onlineText){
				row.removeClass("offline online")
					.addClass(data.onlineText);
			}
			
			var userNameHtml = data.username ;
			if(data.remark) userNameHtml += (" (<span class='remark'>" + data.remark + "</span>)");
			row.find(".username").html(userNameHtml);
			
			var usedBar = row.find(".soof_progressbar");
			usedBar.children(".used").width(data.usedTimeWidth);
			usedBar.children(".text").html(data.usedBarText);
			
			row.find(".rxText").html(data.rxText);
			
			if(!data.isRandom){
				var selOpr = row.find(".sel_opr"),
					reactivateOpt = selOpr.children("[value='reactivate']");
				
				//option style "display:none" not works under ie or ipad
				//so we need to add/remove option on demand.
				if(reactivateOpt.length){
					if(!data.expired) reactivateOpt.remove();
				}
				else{
					if(data.expired){
						var reactivateOptHtml = '<option value="reactivate">重新开通</option>',
							refTarget = selOpr.children("[value='remove']");
						if(refTarget.length){
							refTarget.before(reactivateOptHtml);
						}
						else{
							selOpr.append(reactivateOptHtml);
						}
					}
				}
				
				selOpr.children("[value='remark']").text(data.remark ? "修改备注" : "添加备注");
			}
			
			rl.dir(data, "updateRow(): with row data");
		}
		
		var selOprations = {
			"reactivate" : function(userid, sel){
				var row = getRow(userid);
				if(!row.length) throw new Error("Cant find correspond row for id: " + id);
				
				var username = row.find(".username").text().replace(/\s/g, "");
				if(confirm("确认重新开通该用户 【" + username + "】?")){
					mainDirect.invoke("reactivate", {
						beforeSend : rl.curry(hndAjaxBeforeSend, sel),
						success : function(data){
							if(!rl.isObject(data)) return "操作失败，服务器返回数据错误！";
							updateRow(userid, data);
						},
						complete : rl.curry(hndAjaxComplete, sel)
					}, userid);
				}
			},
			"remark" : function(userid, sel){
				var row = getRow(userid);
				if(!row.length) throw new Error("Cant find correspond row for id: " + id);
				
				var remark,
					curRemark = row.find(".username .remark").text();
				if(remark = prompt("请输入备注：", curRemark || "")){
					mainDirect.invoke("remark", {
						beforeSend : rl.curry(hndAjaxBeforeSend, sel),
						success : function(data){
							if(!rl.isObject(data)) return "操作失败，服务器返回数据错误！";
							updateRow(userid, data);
						},
						complete : rl.curry(hndAjaxComplete, sel)
					}, userid, remark);
				}
			},
			"remove" : function(userid, sel){
				var row = getRow(userid);
				if(!row.length) throw new Error("Cant find correspond row for id: " + id);
				
				var username = row.find(".username").text().replace(/\s/g, "");
				if(confirm("确认删除该用户 【" + username + "】?")){
					mainDirect.invoke("remove", {
						beforeSend : rl.curry(hndAjaxBeforeSend, sel),
						success : function(){
							location.reload();
						},
						complete : rl.curry(hndAjaxComplete, sel)
					}, userid);
				}
			},
			"resetPassword" : function(userid, sel){
				var row = getRow(userid);
				if(!row.length) throw new Error("Cant find correspond row for id: " + id);
				
				var username = row.find(".username").text().replace(/\s/g, "");
				if(confirm("确认重置该用户 【" + username + "】 的密码?")){
					mainDirect.invoke("resetPassword", {
						beforeSend : rl.curry(hndAjaxBeforeSend, sel),
						success : function(result){
							rl.dir(arguments, "arguments");
							alert("重置密码成功！用户 【" + username + "】 的新密码为：" + result);
						},
						complete : rl.curry(hndAjaxComplete, sel)
					}, userid);
				}
			}
		};
		
		function doOperationOnChange(userid, sel){
			var oprType = sel.value,
				opr = selOprations[oprType];
			
			if(!rl.isFunction(opr)) throw new Error("Invalid oprType: " + oprType + " of " + sel);
			
			//defer to let the change event finish to wait for the select dropdown to hide.
			//so that it wont cause the focus conlict with the prompt() or confirm() under some browsers.
			setTimeout(function(){
				opr(userid, sel);
				jQuery(sel).prop("selectedIndex", 0);
			}, 1);
		}
		
		function hndAjaxBeforeSend(sel){
			jQuery(sel)
				.val(0)
				.hide();
			showOprTip(sel, "操作中...");
		}
		
		function hndAjaxComplete(sel){
			jQuery(sel)
				.show();
			hideOprTip(sel);
		}
		
		function showOprTip(sel, tip){
			var tipCtn = jQuery(sel).next();
			tipCtn
				.html(tip)
				.show();
		}
		
		function hideOprTip(sel){
			var tipCtn = jQuery(sel).next();
			tipCtn.hide();
		}
		
		window.doOperationOnChange = doOperationOnChange;
		
		rl.onReady(function(){
			userSearch = decoSearchCtrl("#userSearch", {
				action: loadUsers,
				onClear : loadUsers
			});
			usersLoader.render();
			loadUsers();
		});
	});
    </script>
</head>
<body class="soof_mui_icbc">
    <div class="soof_upgrade_warning">
        对不起，您的浏览器版本太低，部分功能将无法使用，请升级您的浏览器！<a href="${base}common/upgradeTip.jsp">查看详情</a>
    </div>
    <div class="mui_viewport">
        <div class="head">
            <div class="logo_wrap">
                <img src="${base}ucs/fly/portal/icbcDeyang/resources/image/logo_titled.png" border="0" class="logo" />
            </div>
            <span>(${user.branch})</span>
            <div class="menubar">
                <div class="user_bar">
                    <a id="link_userInfo" class="btn_link" href="javascript:void(0);">
                    <img src="${base}resources/image/default/mui/icon_user.gif" align="absmiddle" /> ${CURRENT_USER.username}</a>
                    <a class="btn_link" href="${base}ucs/fly/portal/logout.do?branch=icbcDeyang"><img src="${base}resources/image/default/mui/icon_exit.gif" align="absmiddle" /> 退出</a>
                </div>
                <div class="pagetabs">
                    <a href="${base}ucs/fly/branch/icbcDeyang/surfing/gen.jsp">快速开户</a>
                    <a class="active" href="javascript:void(0);">用户管理</a>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
        <div class="main">
            <div class="main_wrap">
				<script id="userRowTpl" type="text/x-jsrender">
					<div class="row {{>onlineText}}" data-rowid="{{>id}}">
						<table class="row_layout" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="user_icon"><i class="soof_icon user_48 blue"></i></td>
								<td>
									<div class="username">
										{{>username}}
										{{if remark}}(<span class="remark">{{>remark}}</span>){{/if}}
									</div>
									<div class="cols_group cols3">
										<div class="cell">
											<span class="time">{{>timeText}}</span>
										</div>
										<div class="cell">
											<div class="soof_progressbar">
												<div class="progress used" style="width:{{>usedTimeWidth}};"></div>
												<span class="text">
													{{>usedBarText}}
												</span>
											</div>
										</div>
										<div class="cell">
											<div class="actions">
												 <span class="rxText">{{>rxText}}</span>
												 <select class="sel_opr" onchange="doOperationOnChange('{{>id}}', this)">
												 	<option value="0">操作</option>
													{{if !isRandom}}
														<option value="remark">
															{{if remark}}修改备注
															{{else}}添加备注
															{{/if}}
														</option>
														<option value="resetPassword">重置密码</option>
													{{/if}}
													
													{{if expired}}<option value="reactivate">重新开通</option>
													{{/if}}
												 	
												 	<option value="remove">删除</option>
												 </select>
												 <span class="opr_tip"></span>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
                </script>
                <div class="soof_flex_grid user_list">
                    <div class="g_head">
                        <div class="bar">
                            <span class="user_stat" style="float:right;"> 
                                在线用户数：${onlineTotal} 总用户数：${total}
                            </span>
                            <div id="userSearch" class="soof_search">
                            	<input class="s_inp" type="search" />
                                <div class="ctrls">
                                	<button class="btn s_reset" tabindex="-1"></button><button id="btn_userSearch" class="btn s_trigger"><span class="icon_search"></span><span class="text">搜索</span></button>
                                </div>
                            </div>
                            <button class="btn" onclick="location.reload();"><img src="${imagePath}refresh.gif" align="absmiddle" /> 刷新
                            </button>
                        </div>
                    </div>
                    <div class="g_body" id="usersRowsCtn">                        
                    </div>
                    <div class="g_foot">
                    	<div style="padding:1em;" id="userListLoaderCtn"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="foot">
            <div class="copyright">中国工商银行 版权所有</div>
        </div>
    </div>
    <span style="display:none;"><script type="text/javascript">
    if(!/127\.0\.0\.1|localhost/i.test(String(location.host))){
        var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fe8f02f1992768475cc8e1b6635415c3c' type='text/javascript'%3E%3C/script%3E"));
    }
    </script></span>
</body>
</html>