<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>分组管理</title>
<link rel="rl-page-icon" href="${imagePath}edit.gif" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
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
	"cloudac:groupTreeEditUtil"
);

orderjs(function(){
   	//rl.console.showOnReady();
	var jQuery = orderjs("open.jquery.jquery"),
		JqueryAjaxDirect = orderjs("lib.rpc.JqueryAjaxDirect"),
		editUtil = orderjs("cloudac:groupTreeEditUtil");
	
	jQuery.ajaxSetup({
		dataType : "json",
		type : "post",
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		cache : false
	});
	
	var updateCurGroupDevices,
		groupTreeDirect = new JqueryAjaxDirect({
		msgs : {
			"query.ajax.error" : "加载分组数据错误！"
		},
		services : {
			create : function(options, pid, name){
				var url = rl.format("${base}group/{1}/create.do", arguments);
				jQuery.ajax(rl.ext(options, { url : url, data : "name=" + name}));
			},
			update : function(options, pid, name){
				var url = rl.format("${base}group/{1}/update.do", arguments);
				jQuery.ajax(rl.ext(options, { url : url, data : "name=" + name}));
			},
			remove : function(options, id){
				var url = rl.format("${base}group/{1}/delete.do", arguments);
				jQuery.ajax(rl.ext(options, { url : url}));
			},
			query : function(options, id){
				var url = "${base}group/query.do";
				jQuery.ajax(rl.ext(options, { url : url}));
			}
		}
	});
	
	function notifyGroupChangeToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.groupDataObserver)){
			opener.groupDataObserver.fireEvent("change");
		}
	}
	
	var lastSelectedNode;
	function onSelectedChange(){
		var selectedNode = window.groupTree.getSelectedNodes()[0];
		if(selectedNode == lastSelectedNode) return;
		
		lastSelectedNode = selectedNode;
		
		if(selectedNode && updateCurGroupDevices){
			updateCurGroupDevices(selectedNode.id);
		}
		else{
			jQuery("#groupEditSection").hide();
			jQuery("#groupEditTipSection").show();
		}
	}
	
	function initTree(nodes){
		if(window.groupTree) return;
		window.groupTree = jQuery.fn.zTree.init(jQuery("#groupTreeId"), editUtil.setting, nodes);
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
			notifyGroupChangeToOpener();
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
		jQuery("#groupTreeId").html('<div class="desc notice">正在加载数据...</div>');
		
		groupTreeDirect.invoke("query", {
			showErrMsg : function(msg){
				msg += '<a href="javascript:void(0);" onclick="loadTree();">点此重试</a>';
				jQuery("#groupTreeId").html('<div class="warn notice">' + msg + '</div>');
			},
			success : function(nodes){
				treeLoading = false;
				if(rl.isArray(nodes)){
					initTree(nodes);
					
					if(nodes.length == 0){
						var tip = '<div id="noGroupTip" class="desc notice">' + 
							'当前尚未创建分组！请点击右上角的 + 号开始创建分组！</div>';
						jQuery("#groupTreeId").html(tip);
					}
					jQuery("#addRootGroup").on("click", function(){
						editUtil.addGroup("groupTreeId");
					});
				}
				else return "";
			}, 
			error : function(){
				treeLoading = false;
			}
		});
	}
	
	window.loadTree = loadTree;
	
	orderjs.ready(function(){
		loadTree();
		jQuery("#groupTreeCtn")
			.on("mouseover", function(){jQuery(this).css("overflow", "auto");})
			.on("mouseout", function(){jQuery(this).css("overflow", "hidden");});
	});
	
	orderjs(
		"x:mti.ajaxSubmit",
		"cloudac:hourglassSelect"
	);
	
	orderjs(function(){
		var filterInputMaskValue = "输入名称或序列号";
		window.filterInput1.maskValue = window.filterInput2.maskValue = filterInputMaskValue;		
		var deviceDirect = new JqueryAjaxDirect({
			msgs : {
				"ajax.error" : "请求该组设备数据失败，请检查网络是否正常或刷新页面后重试!",
				"ajax.dataerror" : "读取该组设备数据失败：服务器返回数据错误！"
			},
			services : {
				query : function(options, gid){
					var url = rl.format("${base}cpe/getHosts.do?gid={1}&branchId=${branchId}", arguments);
					jQuery.ajax(rl.ext(options, { url : url}));
				}
			}
		});
		
		updateCurGroupDevices = function (groupId){
			jQuery("#gidField").val(groupId);
			deviceDirect.invoke("query", {
				showErrMsg : function(msg){
					jQuery("#groupEditTipSection")
						.show()
						.html('<div class="warn">' + msg + '</div>');
					jQuery("#groupEditSection").hide();
				},
				success : function(nodes){
					if(rl.isArray(nodes)){
						jQuery("#groupEditTipSection").hide()
						jQuery("#groupEditSection").show();
						buildSelectNodes(jQuery("#selectedItems").get(0), nodes);
						
						updateNodesFilter();
						updateSelectableDevices(jQuery("#selectableGroups").val());
						jQuery("#filterCtn1 input, #filterCtn2 input").val(filterInputMaskValue);
					}
					else return "";
				}
			}, groupId);
		};
		
	
		function updateSelectableDevices(groupId){
			deviceDirect.invoke("query", {
				showErrMsg : function(msg){
					jQuery("#selectableItemsTip")
						.show()
						.html('<div class="warn notice">' + msg + '</div>');
					jQuery("#selectableItemsCtn").hide();
				},
				success : function(nodes){
					if(rl.isArray(nodes)){
						jQuery("#selectableItemsTip").hide()
						jQuery("#selectableItemsCtn").show();
						buildSelectNodes(jQuery("#selectableItems").get(0), nodes);
					}
					else return "";
				}
			}, groupId);
		}
		
		function updateNodesFilter(){
			if(window.groupTree){
				jQuery("#selectableGroupsCtn").html(toTreeSelectHtml(window.groupTree));
				jQuery("#selectableGroups").on("change", function(){updateSelectableDevices(this.value);});
			}
		}
		
		function toTreeSelectHtml(tree){
			function getParentPad(node){
				if(!node) return "";
				var html = [];
				var p = node.getParentNode();
				
				while(p){
					html.unshift(p.isLastNode ? "&nbsp;&nbsp;&nbsp;" : ("┃&nbsp;")  );
					p = p.getParentNode();
				}
				return html.join("");
			}
			function getGroupSelectOptionsHtml(nodes){
				var html = [];				
				rl.each(nodes, function(node, index){ 
					var arrow = node.isLastNode ? "┗" : "┣",
						pad = getParentPad(node) + arrow;
					html.push('<option value="' + node.id + '">' + pad + node.name + '</option>');					
					if(node.children && node.children.length > 0){
						html.push(getGroupSelectOptionsHtml(node.children));
					}
				});				
				return html.join("");
			}
			
			var html = ['<select id="selectableGroups" style="float:right; width:150px;">'];			
			html.push('<option value="ungrouped">┏未分组</option>');
			html.push(getGroupSelectOptionsHtml(groupTree.getNodes()));			
			html.push('</select>');
			
			return html.join("");
		}
		
		function buildSelectNodes(selObj, nodes){
			if(!(jQuery(selObj).is("select") && rl.isArray(nodes)) ) return null;
			var selOptions = selObj.options;
			
			selOptions.length = 0;
			
			rl.each(nodes, function(node){
				var optText = [node.name, " [", node.serialNumber, "]"].join(""),
					optEle = new Option(optText , node.id);
				
				optEle.title = optText;
				selOptions.add(optEle);
			});
			
			return selOptions;
		}
		
		function save() {
			var mainForm = document.mainForm;
			jQuery("option", mainForm.selectedItems).prop("selected", true);
			mainForm.action = "${base}group/save.do?branchId=${branchId}";
			rlx.mti.ajaxSubmit("mainForm");
			notifyGroupChangeToOpener();
			return false;
		}
		
		window.save = save;	
	});	
});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}form.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.cols2_layout{
	width:100%;
	vertical-align:top;
}
.edit_cols_box{
	width:100%;
	height:500px;
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
	height:470px;
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
.edit_cols_box .edit_main{
	vertical-align:top;
}
.edit_cols_box .edit_main .bar{
	padding-left:2em;
}
.edit_cols_box .main_wrap{
	padding:0 2em 2em 2em;
}
.edit_cols_box .main_wrap .header{
	padding:1em 0;
	border-bottom:solid 1px #ddd;
}
.edit_cols_box .main_wrap .notice{
	padding:2em 1em;
	text-align:center;
}
.ztree_groupleaf li span.button.ico_docu{
	background-position:-112px 0px !important;
}

</style>
</head>
<body class="theme">
<div class="soof_page" style="padding:5px; padding-bottom:0;">
    <table class="cols2_layout" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="mc_col">
                <div class="mc_wrapper">
                    <form name="mainForm" id="mainForm" method="post" onsubmit="return false;">
                        <input type="hidden" name="gid" id="gidField" />
                        <table class="edit_cols_box" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="edit_side">
                                    <div class="bar">
                                        <a id="addRootGroup" href="javascript:void(0);" class="btn" title="创建一级分组">
                                            <span class="icon"></span>
                                        </a>
                                        <span>分组管理</span>
                                    </div>
                                    <div id="groupTreeCtn" class="soof_popup_tip_wrap">
                                    	<ul id="groupTreeId" class="ztree ztree_groupleaf"></ul>
                                        <div class="soof_popup_tip" id="groupChangedTip" style="left:20%;">
                                            <span class="tip">分组数据已保存！</span>
                                        </div>
                                    </div>
                                </td>
                                <td class="edit_main">
                                    <div class="bar">
                                        编辑分组设备
                                    </div>
                                    <div class="main_wrap">
                                        <div id="groupEditTipSection" class="sections_desc">
                                            请从左侧分组菜单中选定一个分组进行编辑。
                                        </div>
                                        <div id="groupEditSection" style="display:none;">
                                            <div class="section" style="border-top:none;">
                                                <p class="p5"> 请从下方左侧的【可选设备】列表中选择分组的设备，双击即可选择。 </p>
                                                <p class="p10">
                                                    <table class="soof_hourglass_select" border="0" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                            <th class="label" style="width:250px; text-align:left;">
                                                                <div id="selectableGroupsCtn"></div>
                                                                可选设备</th>
                                                            <th class="label" style="width:120px;">&nbsp;</th>
                                                            <th class="label" style="width:250px; text-align:left;">已选设备</th>
                                                        </tr>
                                                        <tr>
                                                            <td class="field_ctn">
                                                                <div id="selectableItemsTip" style="border:solid 1px #ccc; WIDTH:250px; height:250px; margin-top:5px; display:none;"></div>
                                                                <div id="selectableItemsCtn">
                                                                    <select id="selectableItems" style="WIDTH:250px; height:250px; margin-top:5px; " multiple="multiple" name="selectableItems"  ondblclick="addSelectedTo('selectableItems', 'selectedItems', true);">
                                                                    </select>
                                                                    <br />
                                                                    <span id="filterCtn1"></span>
                                                                </div>
                                                            </td>
                                                            <td class="field_ctn col_buttons"><button type="button" onclick="addSelectedTo('selectableItems', 'selectedItems', true);">-&gt;</button>
                                                                <br />
                                                                <button type="button" onclick="removeSelected('selectedItems');">&lt;-</button>
                                                                <br />
                                                                <button type="button" onclick="addAllTo('selectableItems', 'selectedItems');">全部&gt;</button>
                                                            </td>
                                                            <td class="field_ctn">
                                                                <select id="selectedItems" style="WIDTH:250px; height:250px; margin-top:5px;" multiple="multiple" name="selectedItems" ondblclick="removeSelected('selectedItems');"></select>
                                                                <br />
                                                                <span id="filterCtn2"></span>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </p>
                                            </div>
                                            <div class="soof_action_bar">
                                                <button type="button" class="btn primary" onclick="save()">保 存</button>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>