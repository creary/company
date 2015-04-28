<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>为商家【${branch.name}】指定关联的设备分组</title>
<link rel="rl-page-icon" href="${imagePath}link.png" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
/**
 * Demo requires:
 * groupTreeDirect.services urls
 * initTree() iniSelectedId
 * unlink() url
 * save() url
 */


orderjs.config("shim", {
	"open.zTree.jquery-ztree-core" : {
		deps : ["open.jquery.jquery"]
	},
	"open.zTree.jquery-ztree-exedit" : {
		deps : ["open.zTree.jquery-ztree-core"]
	}
});

orderjs(
	"open.jquery.jquery",
    "open.zTree.jquery-ztree-core",
    "open.zTree.jquery-ztree-exedit",
    "css>open.zTree.css.zTreeStyle",
	"lib.rpc.JqueryAjaxDirect",
	"lib.rpc.JqAjax",
	"cloudac:groupTreeEditUtil"
);

orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect"),
		JqAjax = orderjs("lib.rpc.JqAjax"),
		editUtil = orderjs("cloudac:groupTreeEditUtil");
	
	jQuery.ajaxSetup({
		dataType : "json",
		type : "post",
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		cache : false
	});
	
	var groupTreeDirect = new JqueryAjaxDirect({
		msgs : {
			"query.ajax.error" : "加载分组数据错误！"
		},
		services : {
			create : function(options, pid, name){
				var url = rl.format("${base}cpe/deviceGroupOperation.do", arguments);
				jQuery.ajax(rl.ext(options, { url : url, data : "name=" + name}));
			},
			update : function(options, pid, name){
				var url = rl.format("${base}cpe/deviceGroupOperation.do", arguments);
				jQuery.ajax(rl.ext(options, { url : url, data : "name=" + name}));
			},
			remove : function(options, id){
				var url = rl.format("${base}cpe/deviceGroupOperation.do", arguments);
				jQuery.ajax(rl.ext(options, { url : url}));
			},
			query : function(options, id){
				var url = "${base}group/query.do";
				jQuery.ajax(rl.ext(options, { url : url}));
			}
		}
	});
	
	function notifyBranchDeviceGroupChange(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.branchDeviceGroupDataObserver)){
			opener.branchDeviceGroupDataObserver.fireEvent("change");
		}
	}
	
	function onSelectedChange(){
		var selectedNode = window.groupTree.getSelectedNodes()[0];		
		if(selectedNode) jQuery("#field_gid").val(selectedNode.id);
	}
	
	function initTree(nodes){
		if(window.groupTree) return;
		window.groupTree = jQuery.fn.zTree.init(jQuery("#groupTreeId"), editUtil.setting, nodes);
		var iniSelectedId = "${groupId}";
			
		if(iniSelectedId){
			groupTree.selectNode(groupTree.getNodeByParam("id", iniSelectedId));
			onSelectedChange();
		}
		
		groupTree.direct = groupTreeDirect;
		var eventsMgr = groupTree.setting.eventsMgr;
		eventsMgr.on("nodeCreated", function(event, treeId, treeNode){
			jQuery("#noGroupTip").hide();
		});
		eventsMgr.on("nodeClick", function(event, treeId, treeNode, clickFlag){
			onSelectedChange();
		});	
		eventsMgr.on("change", function(){
			onSelectedChange();
			notifyBranchDeviceGroupChange();
			jQuery("#groupChangedTip")
				.stop()
				.fadeIn("fast")
				.delay(1000)
				.fadeOut();
		});
	}
	
	var treeLoading = false;
	function loadTree(){
		if(window.groupTree || treeLoading ) return;
		treeLoading = true;
		jQuery("#groupTreeCtn .desc.notice").show();
		
		groupTreeDirect.invoke("query", {
			showErrMsg : function(msg){
				msg += '<a href="javascript:void(0);" onclick="loadTree();">点此重试</a>';
				jQuery("#groupTreeId").html('<div class="warn notice">' + msg + '</div>');
			},
			success : function(nodes){
				treeLoading = false;
				jQuery("#groupTreeCtn .desc.notice").hide();
				
				if(rl.isArray(nodes)){
					initTree(nodes);
					
					if(nodes.length == 0){
						var tip = '<div id="noGroupTip" class="desc notice">当前尚未创建分组！</div>';
						jQuery("#groupTreeId").html(tip);
					}
					jQuery("#btn_addRootGroup").on("click", function(){
						editUtil.addGroup("groupTreeId");
					});
				}
				else return "";
			}, 
			error : function(){
				treeLoading = false;
				jQuery("#groupTreeCtn .desc.notice").hide();
			}
		});
	}	
	window.loadTree = loadTree;
	
	function unlink(){
		JqAjax.request({
			url : "${base}cpe/removeBranchDeviceGroup.do?branchId=${branch.id}",
			beforeSend : function(){
				jQuery("#btn_unlink")
					.prop("disabled", true)
					.addClass("disabled")
					.text("正在解除...");
			},
			complete : function(){
				jQuery("#btn_unlink")
					.prop("disabled", false)
					.removeClass("disabled")
					.text("解除关联");
			},
			success : function(data, rspData){
				if(rl.isNonNullStr(rspData.msg)) alert(rspData.msg);
				notifyBranchDeviceGroupChange();
				rl.page.close();
			},
			error : function(msg){
				alert(msg);
			}
		});
	}
	
	function save() {
		JqAjax.request({
			url : "${base}cpe/saveBranchDeviceGroup.do?branchId=${branch.id}",
			data : jQuery("#mainForm").serialize(),
			beforeSend : function(){
				jQuery("#btn_action")
					.prop("disabled", true)
					.addClass("disabled")
					.text("正在保存...");
			},
			complete : function(){
				jQuery("#btn_action")
					.prop("disabled", false)
					.removeClass("disabled")
					.text("保 存");
			},
			success : function(data, rspData){
				if(rl.isNonNullStr(rspData.msg)) alert(rspData.msg);
				notifyBranchDeviceGroupChange();
				rl.page.close();
			},
			error : function(msg){
				alert(msg);
			}
		});
	}
	orderjs.ready(function(){
		loadTree();
		
		jQuery("#groupTreeCtn")
			.on("mouseover", function(){jQuery(this).css("overflow", "auto");})
			.on("mouseout", function(){jQuery(this).css("overflow", "hidden");});
		
		jQuery("#btn_action")
			.on("click", save);
		jQuery("#btn_unlink")
			.on("click", unlink);
	});
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.cols2_layout{
	width:100%;
	vertical-align:top;
}
.edit_cols_box{
	width:100%;
	border:solid 1px #ddd;
}
.edit_cols_box .edit_side{
	width:230px;
	border-right:solid 1px #ddd;
	vertical-align:top;
}
.edit_cols_box .bar{
	height:30px;
	line-height:30px;
	padding:0 6px;
	border-bottom:solid 1px #eee;
	background-color:#f8f8f8;
}
#groupTreeCtn{
	height:150px;
	overflow:hidden;
}
.edit_cols_box a.btn{
	text-decoration:none;
}
.edit_cols_box a.btn .icon{
	display:inline-block; 
	width:16px; 
	height:16px;
	background:url(${imagePath}add.gif) no-repeat;
}
.edit_cols_box .edit_side .bar .btn{
	float:right;
	padding-top:6px;
}
.edit_cols_box .notice{
	padding:2em 1em;
	text-align:center;
}
.ztree_groupleaf li span.button.ico_docu{
	background-position:-112px 0px !important;
}
</style>
</head>
<body class="theme">
<div class="soof_page soof_selectbox_page">
	<div class="soof_page_viewport">
        <div class="soof_body">
        	<div style="line-height:12px;">
                <span class="desc">为商家指定关联的设备分组，以便该商家查看。</span>
                <div class="clearer"></div>
            </div>
            <div class="p10">
                <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                    <input type="hidden" name="gid" id="field_gid" />
                    <table class="edit_cols_box" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="edit_side">
                                <div id="groupTreeCtn" class="soof_popup_tip_wrap">
                                    <div class="desc notice">正在加载数据...</div>
                                    <ul id="groupTreeId" class="ztree ztree_groupleaf"></ul>
                                    <div class="soof_popup_tip" id="groupChangedTip" style="left:20%;">
                                        <span class="tip">分组数据已保存！</span>
                                    </div>
                                </div>
                            </td>                                
                        </tr>
                    </table>                    
                </form>
            </div>
        </div>
    </div>
    <div class="soof_selectbox_bar">
    	<div class="iwrap">
            <button id="btn_action" type="button" class="btn primary">保 存</button>
            <c:if test="${groupId ne null}">
                <button id="btn_unlink" type="button" class="btn green">解除关联</button>
            </c:if>
            <button type="button" class="btn white" onclick="rl.page.close();">取消</button>
        </div>
    </div>
</div>
</body>
</html>