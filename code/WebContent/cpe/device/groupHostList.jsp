<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备列表</title>
<link rel="rl-page-icon" href="${imagePath}wireless.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(function(){rl.page.callTop("focusOnMenuItem", "cpe_hostList");});
orderjs.config("shim", {
	"open.zTree.jquery-ztree-core" : {
		deps : ["open.jquery.jquery"]
	}
});

orderjs(
	"open.jquery.jquery",
    "open.zTree.jquery-ztree-core",
    "css>open.zTree.css.zTreeStyle",
	"lib.util.StateObserver",
	"lib.util.OneshotEventProvider",
	"lib.rpc.JqAjax",
	"gui.box.Div",
    "gui.box.Box",
	"x:mti.zTreeListPage"
);
orderjs(function(){
	//rl.console.showOnReady();
	var track = this.track;
	var jQuery = orderjs("open.jquery.jquery"),
		JqAjax = orderjs("lib.rpc.JqAjax");
	
	JqAjax.request({
		url : "${base}cpe/getGroupTree.do",
		success : function(nodes){
			if(!(nodes && nodes.length > 0)){
				return("服务器返回分组数据错误！");
			}
			rlx.mti.listPageTreeNodes = nodes;
			rl.debug('Group tree nodes load, length = ' + nodes.length + '');
			rlx.mti.processEventMgr.fireEvent("treeNodesReady");
		},
		error : function(msg, status, jqXHR){
			alert("页面加载错误：" + msg);
		}
	});
	
	function doEdit(cellText, colIndex, rowIndex, dataSource){
		var name = dataSource.getValue("name");
		var url = "${base}cpe/readyEditName.do?id=" + cellText;
		return '<a href="javascript:void(0);" title=\"修改设备名称\" onclick="rl.page.open(\'' + url + '\', \'readyEditName\', rl.gui.paneDlgOpt);">' + name + '</a>';    
	} 
	
	function doAction(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}cpe/readySetting.do?id=" + cellText;
		return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'cpe.readySetting\',rl.gui.winDlgOpt);"><img src="${imagePath}wrench-orange.png" border="0"/></a>';
	}
	
	function genUsersInfo(cellText, colIndex, rowIndex, ds){
		var nonSupport = /^(Ubnt|MikroTik)$/i.test(ds.getValue("mode"));
		
		if(nonSupport) return "";
		var userInfo = ds.getValue("userNum") + " / " + (ds.getValue("maxStaNum") || "-");
		
		if(ds.getValue("online") == "1"){
			var link = '' + 
				'<a href="javascript:void(0);"' +
				' onclick="rl.page.open(\'{url}\', \'viewOnlineUsers\', rl.gui.winDlgOpt);">{userInfo}</a>';
			
			return rl.format(link, {
				url : "${base}portal/sessionListJsp.do?apid=" + ds.getValue("id"),
				userInfo : userInfo
			});
		}else{
			return userInfo;
		}
	}
	
	function setPrimaryColCellWidth(cellEle, value){
		jQuery(cellEle.firstChild)
			.width(value)
			.find(".clip")
			.width(value - 30);
	}
	
	function getPrimaryColCellContentWidth(cellEle){
		return jQuery(".clip .holder", cellEle).width() + 30;
	}
	
	var oprColHtml = '' + 
			'<span>&nbsp;<span class="ctrl soof_icon arrow_down tg_dropdown" onclick="showDropDown(\'{rowIndex}\', this);" title="操作"></span></span>';
	function genOprCol(val, colIndex, rowIndex, ds){
		return rl.format(oprColHtml, {
			rowIndex : rowIndex,
			url : "${base}cpe/readySetting.do?id=" + val
		});;
	}
	
	var nameColHtml = '' + 
			'<a class="holder" href="javascript:void(0);" title=\"配置\" onclick="rl.page.open(\'{url}\', \'cpe.readySetting\', rl.gui.winDlgOpt);">{name}</a>';
	function genNameCol(val, colIndex, rowIndex, ds){
		return rl.format(nameColHtml, {
			name : val,
			url : "${base}cpe/readySetting.do?id=" + ds.getValue("id")
		});;
	}
	
	function toRssiText(cellText, colIndex, rowIndex, dataSource){
		return rl.isDefined(cellText) ? '-' + cellText + ' dbm' : "-";
	}
	
	function getRow(index){
		return rlx.mti.listPageGrid.dataSource.rows[index];
	}
		
	var dropdownItemRowIndex;
	window.showDropDown = function(rowIndex, ele){
		var row = getRow(rowIndex);
		var nonSupports = (/^(Ubnt|MikroTik)$/i.test(row.getValue("mode")) ?
			"updateFirmware, updateConfig, reboot, reset, debug" : "").split(",");
		
		jQuery("#primaryColDropdown a.item")
			.each(function(){
				var key = rl.trim(jQuery(this).attr("data-key")).toLowerCase(),
					eq = function(name){
						name = rl.trim(name).toLowerCase();
						return (name == key);
					};
				if(rl.some(nonSupports, eq)){
					jQuery(this).hide();
				}
				else jQuery(this).show();
			});
		
		dropdownItemRowIndex = rowIndex;
		rl.$("primaryColDropdown").showAtEvent(null, 0, 0, true);
	};
	
	var dropdownItemActions = {
		"rename" : function(){
			var row = getRow(dropdownItemRowIndex);
			var url = "${base}cpe/readyEditName.do?id=" + row.getValue("id");
			rl.page.open(url, "cpe.readyEditName", rl.gui.paneDlgOpt);
		},
		"updateFirmware" : function(){
			var row = getRow(dropdownItemRowIndex);
			var url = "${base}cpe/readyUpdateFirmware.do?batchMode=0&id=" + row.getValue("id");
			rl.page.open(url, "cpe.readyUpdateFirmware", rl.gui.winDlgOpt);
		},
		"updateConfig" : function(){
			var row = getRow(dropdownItemRowIndex);
			var url = "${base}cpe/readyUpdateConfig.do?batchMode=0&id=" + row.getValue("id");
			rl.page.open(url, "cpe.readyUpdateConfig", rl.gui.winDlgOpt);
		},
		"reboot" : function(){
			var row = getRow(dropdownItemRowIndex);
			var url = "${base}cpe/readyReboot.do?batchMode=0&id=" + row.getValue("id");
			rl.page.open(url, "cpe.readyReboot", rl.gui.winDlgOpt);
		},
		"reset" : function(){
			var row = getRow(dropdownItemRowIndex);
			var url = "${base}cpe/readyReset.do?batchMode=0&id=" + row.getValue("id");
			rl.page.open(url, "cpe.readyReset", rl.gui.winDlgOpt);
		},
		"debug" : function(){
			var row = getRow(dropdownItemRowIndex);
			var url = "${base}cpe/readyDebug.do?id=" + row.getValue("id");
			rl.page.open(url, "cpe.readyDebug", rl.gui.winDlgOpt);
		}
	};
	
	function doDropdownItemAction(name){
		var f = dropdownItemActions[name];
		if(!rl.isFunction(f)){
			alert("页面脚本发生错误，请刷新页面重试！");
			rl.raiseError("Invalid action name: " + name, track(this, "doDropdownItemAction"));
		}
		f();
	};
	
	rl.onReady(function(){
		//init dropdown
		jQuery("#primaryColDropdown")
			.on("click", function(){
				jQuery(this).hide();
			})
			.find("a.item")
			.on("click", function(){
				doDropdownItemAction(jQuery(this).attr('data-key'));
			});
		jQuery(document)
			.on("click", function(evt){
				var tg = jQuery(evt.target).closest(".tg_dropdown");
				if(!tg.length) jQuery("#primaryColDropdown").hide();
			});
	});
	
	//observe device data.
	window.deviceDataObserver = new rl.util.EventProvider;
	deviceDataObserver.on("change", function(){
		deviceDataObserver.changed = true;
		rlx.mti.listPageGrid.load();
	});
	
	//observe group data.
	window.groupDataObserver = rl.ext(new rl.util.EventProvider, {
		changed : false
	});
	groupDataObserver.on("change", function(){
		groupDataObserver.changed = true;
	});
	window.openDeviceGroupEdit = function (){
		var url = '${base}group/readyTree.do';
		var dlg = rl.page.open(url, 'deviceGroupEdit', rl.gui.winDlgOpt);
		dlg.on("close", function(){
			if(groupDataObserver.changed){
				location.reload();
			}
		});
	}
	function toggleGroupTree(){
		var btn, 
			bar = rlx.mti.listPageToolbar,
			bodyLayout = rl.body.layout,
			treeWrap = bodyLayout.getWrap("west");
		
		if(bar) btn = bar.getItemById("btn_toggleGroupTree");
		
		if(treeWrap.hidden){
			bodyLayout.showLayoutComponent("west");
			if(btn){
				btn.setText("隐藏分组");
				btn.setIcon("${imagePath}application_side_tree_contract.png");
			}
		}
		else{
			bodyLayout.hideLayoutComponent("west");
			if(btn){
				btn.setText("显示分组");
				btn.setIcon("${imagePath}application_side_tree_expand.png");
			}
		}
	}
	
	rlx.mti.processEventMgr.on("mainLayoutReady", function(){
		jQuery("#groupTreeCtn")
			.on("mouseover", function(){jQuery(this).css("overflow", "auto");})
			.on("mouseout", function(){jQuery(this).css("overflow", "hidden");});
		jQuery(window)
			.on("resize", function(){
				var total = jQuery("#groupTreeCtn").parent().height(),
					barHeight = jQuery("#groupTreeCtn").prev().height();
				jQuery("#groupTreeCtn").height(total - barHeight - 3);
			})
			.resize();
	});
		
	rlx.mti.processEventMgr.on("listBoxReady", function(){
		orderjs("open.jQuery-idTabs.idTabs-ordered");
		orderjs(function(){
			function showGridCtgColumns(tag){
				var grid = rlx.mti.listPageGrid,
					cols = grid.cols;
				
				tag = rl.trim(tag);
				rl.each(cols, function(col, colIndex){
					var colTag = col.colEntityTag;
					if(rl.isNonNullStr(colTag)){
						var has = rl.find(colTag.split(","), function(oneTag){
								return rl.trim(oneTag) == tag;
							});
						grid[(has ? "showCol" : "hideCol")](colIndex);
					}
				});
			}
		
			var btn_batchOpr = rlx.mti.listPageToolbar.getItemById("cmdSep");
			jQuery("#deviceColumnsSeletor")
				.idTabs()
				.insertBefore(btn_batchOpr.dw.dom)
				.find("a")
				.on("click", function(){
					var type,
						href = jQuery(this).attr("href");
					
					if(rl.isNonNullStr(href)){
						type = rl.trim(href).replace("#deviceColumns_", "");
						showGridCtgColumns(type);
					}
				});
		});
	});
		
	rlx.mti.createTreeListPage({
		searchDialog : true,
		sideBox : {
			content : '' +
				<c:if test="${adminable}">
					'<div class="ztree_bar">' +
						'<div class="iwrap">' +
							'<a href="javascript:void(0);" onclick="openDeviceGroupEdit()" class="btn">' + 
								'<img src="${imagePath}folder_edit.gif" align="absmiddle" />管理' +
							'</a>' +
							'<span>分组</span>' +
						'</div>' +
					'</div>' + 
				</c:if>
				'<div id="groupTreeCtn" style="height:100%; overflow:hidden;">' + 
					'<ul id="groupTree" class="ztree ztree_groupleaf" style="margin-bottom:50px;"></ul>' +
				'</div>'
		},
		tree : {
			treeId : "#groupTree",
			direct : rlx.mti.listPageTreeDirect,
			loadGridByNode : function(id){
				jQuery("#field_groupId").val(id);
				var url = rl.format("${base}cpe/richHostList.do?branchId=${branchId}", arguments);
				rlx.mti.resetListPageGridSearch(url);
			}
		},
		toolbar : {
			items : [		
				{
					id : "btn_toggleGroupTree",
					text : "隐藏分组",
					icon : "${imagePath}application_side_tree_contract.png", 
					action : toggleGroupTree
				},	
				{id : "cmdSep", ctype : "MenuSplit"},
				{
					text : "批量配置",
					icon : "${imagePath}wrench-orange.png", 
					action : function(){
						var grid = rlx.mti.listPageGrid,
							len = grid.getSelectedRows().length,
							url = "${base}cpe/readySetting.do?batchMode=1";
						if(!len){
							alert("请选择要配置的设备");
							return;
						}
						var idList = grid.body.getSelectedItemsDataIdList();
						url = [url, url.contains("?") ? "&" : "?", "id=", idList.join(",")].join("");
						rl.page.open(url, "cpe.readyBatchSetting", rl.gui.winDlgOpt);
					}
				},
				{
					text : "刷新",
					icon : "${imagePath}refresh.gif", 
					action : function(){
						rlx.mti.listPageGrid.load();
					}
				},
				{
					text : "删除",
					icon : "${imagePath}remove.gif", 
					action : function(){
						var grid = rlx.mti.listPageGrid,
							len = grid.getSelectedRows().length,
							url = "${base}cpe/hostDelete.do";
						if(!len){
							alert("请选择要删除的记录");
							return;
						}					
						if(len && confirm("确定要删除这 " + len + " 条记录?")){
							var qs = grid.body.encodeSelectedDataIdList2QS();
							url += (url.contains("?") ? "&" : "?") + qs;						
							rl.rpc.sdtGet(url, function(status, msg){
								grid.load();
							}, function(status, msg){
								alert(msg);
							});
						}
					}
				},
				"search",
				"searchReset"
			]
		},	
		grid :{
			dataFields : ["id","branch","name","serialNumber","hardwareVersion","softwareVersion","ipAddress","ssid","userNum","online","channel",
			"mode","description","trace","detect","kickStaRssiLow","assocReqRssiThres","authRssiThres","wirelessTxPower","maxStaNum","internetIP"],
			dataPrimaryKey : "id",
			dataOptions : {localSort : false},
			rowDblClickAction : function(row){
				var url = "${base}cpe/readySetting.do?id=" + row.dataSource.getValue("id");				
				rl.page.open(url, 'cpe.readySetting', rl.gui.winDlgOpt);
			},
			pbOptions : {
				recordsTotalTextRender : function(total){
					return "共 " + total + " 台设备";
				}
			},
			columns : [
				{ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
				{caption: "名称", width: 180, name: "name", convert : genNameCol},
				{caption: "序列号(MAC)", name: "serialNumber"},
				{caption: "状态", name: "description"},	
				{caption: "接入用户", name: "userNum", convert : genUsersInfo, colEntityTag : "basic"},
				{caption: "IP地址", name: "ipAddress", colEntityTag : "basic"},
				{caption: "公网IP", name: "internetIP", colEntityTag : "basic"},
				{caption: "产品型号", name: "hardwareVersion", colEntityTag : "basic"},
				{caption: "软件版本", name: "softwareVersion", colEntityTag : "basic"},				
		        {caption: "商家", name: "branch", colEntityTag : "basic"},
				{caption: "SSID", name: "ssid", visible : false, colEntityTag : "wireless"},				
				{caption: "信道", name: "channel", visible : false, colEntityTag : "wireless"},
				{caption: "漫游阀值", width:80, name: "kickStaRssiLow",convert : toRssiText, visible : false, colEntityTag : "wireless"},
				{caption: "接入阀值", width:80, name: "assocReqRssiThres", convert : toRssiText, visible : false, colEntityTag : "wireless"},
				{caption: "认证阀值", width:80, name: "authRssiThres",convert : toRssiText, visible : false, colEntityTag : "wireless"},
				{caption: "操作", name: "id", convert : genOprCol, sortable:false}
			]
		}
	});
	
});
</script>
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}entity.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}icons.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${jsPath}rl/src/open/jQuery-idTabs/jquery_idtabs.css"/>
<style type="text/css">
.ztree_groupleaf li span.button.ico_docu{
	background-position:-112px 0px !important;
}
.ztree_layout{
	border-collapse:collapse;
}
.ztree_layout td{
	/*border:solid 1px red;*/
	vertical-align:top;
}
.ztree_bar{
	height:30px;
	border-bottom:solid 1px #ddd;
	background-color:#f8f8f8; 
}
.ztree_bar .iwrap{
	padding:7px;
}
.ztree_bar a.btn{
	float:right;
	text-decoration:none;
	color:#000;
}
.ztree_bar a.btn:hover{
	color:#F90;
}
.ztree_bar a.btn img{
	border:none;
	margin-right:4px;
}
.tree_ctn{
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar{
	height:22px;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar a {
	padding:3px 12px;
	border-color:#A6C2E6;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar a:hover{
	padding:3px 12px;
	border-color:#A6C2E6;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar a:hover {
    background-color: #ECF5FC;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar a.selected {
	background-color:#ECF5FC;
	border-color:#A6C2E6;
}
.rl_menu .jquery_idtabs.radio_btn ul.tabbar li:first-child a,
.rl_menu .jquery_idtabs.radio_btn ul.tabbar li:last-child a{
	border-color:#A6C2E6;
}

.rl_grid .cell .ctrl{
	cursor:pointer;
}
</style>
</head>
<body>
<div id="primaryColDropdown" class="soof_drop_menu">
	<a class="item" href="javascript:void(0);" data-key="rename">重命名</a>
    <div class="sep"></div>
	<a class="item" href="javascript:void(0);" data-key="updateFirmware">更新固件</a>
	<a class="item" href="javascript:void(0);" data-key="updateConfig">更新配置</a>
	<a class="item" href="javascript:void(0);" data-key="reboot">重启</a>
	<a class="item" href="javascript:void(0);" data-key="reset">恢复出厂配置</a>
    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_SOOFOUND">
	<a class="item" href="javascript:void(0);" data-key="debug">调试</a>
    </security:authorize> 
</div>
<div style="display:none;">
    <div id="deviceColumnsSeletor" class="rl_btn jquery_idtabs radio_btn">
        <ul class="tabbar">
            <li><a class="selected" title="显示设备的基本信息" href="#deviceColumns_basic">基本信息</a></li>
            <li><a title="显示设备的运营信息" href="#deviceColumns_wireless">无线信息</a></li>
        </ul>
    </div>
</div>
<form id="queryForm" name="queryForm" method="post" class="rlx_mti_list_query_form" style="display:none;">
	<input type="hidden" name="gid" id="field_groupId" />
   <table class="fields_layout">
      <tr>
         <th>商家</th>
         <td>
            <input type="text" class="t" name="inputBranch" />
         </td>
         <th>名称</th>
         <td>
            <input type="text" class="t" name="name" />
         </td>
      </tr> 
      <tr>
         <th>序列号</th>
         <td>
            <input type="text" class="t" name="serialNumber" />
         </td>
         <th>IP地址</th>
         <td>
            <input type="text" class="t" name="ipAddress" />
         </td>
      </tr>   
      <tr>
         <th>型号</th>
         <td>
            <input type="text" class="t" name="hardwareVersion" />
         </td>
         <th>软件版本</th>
         <td>
            <input type="text" class="t" name="softwareVersion" />
         </td>
      </tr>   
   </table>
</form>
</body>
</html>