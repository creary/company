/**
 * @fileOverview groupTree edit util.
 * @change
 	#1 by prcjack @2014/09/24 Creates file.
 */

orderjs.define("cloudac:groupTreeEditUtil", [
	"open.jquery.jquery"
], function(){
var track = this.track;
var jQuery = orderjs("open.jquery.jquery");

var groupTreeEditUtil = {
	addGroup : function(treeId, pNode){
		var name = window.prompt("请输入分组名称！", "");
		name = rl.trim(name);
		if(!rl.isNonNullStr(name)) return false;
		var groupTree = jQuery.fn.zTree.getZTreeObj(treeId);
		if(!groupTree){
			rl.raiseError(' Cant find groupTree by specified treeId = ' + treeId, track(this, "addGroup"));
		}
		var pid = pNode ? pNode.id : 0;
		
		groupTree.direct.invoke("create", {
			success : function(id){
				if(!rl.isDefined(id)) return "";
				groupTree.addNodes(pNode,  {id:id, pid:pid, name: name });
				groupTree.setting.eventsMgr.fireEvent("change", "create", treeId, id);
			}
		}, pid, name);
	},
	setting : {
		eventsMgr : new rl.util.EventProvider,
		view: {
			addHoverDom: function(treeId, treeNode) {
				var sObj = jQuery("#" + treeNode.tId + "_span");
				if (treeNode.editNameFlag || jQuery("#addBtn_"+treeNode.tId).length>0) return;
				var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
					+ "' title='add node' onfocus='this.blur();'></span>";
				sObj.after(addStr);
				var btn = jQuery("#addBtn_"+treeNode.tId);
				if (btn) btn.bind("click", function(){
					groupTreeEditUtil.addGroup(treeId, treeNode);
					return false;
				});
			},
			removeHoverDom: function(treeId, treeNode) {
				jQuery("#addBtn_"+treeNode.tId).unbind().remove();
			},
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true
		},
		data: {
			simpleData: {
				pIdKey : "pid",
				enable: true
			}
		},
		callback : {
			beforeRename : function(treeId, treeNode, newName, isCancel){
				var groupTree = this.getZTreeObj(treeId);
				
				if(isCancel) return true;
				groupTree.direct.invoke("update", {
					success : function(){
						groupTree.cancelEditName(newName);
						groupTree.setting.eventsMgr.fireEvent("change", "rename", treeId, treeNode, newName);
					}
				}, treeNode.id, newName);
				
				return false;
			},
			beforeRemove : function(treeId, treeNode){
				var msg = "确认删除该分组及其子分组？\n该操作不会删除组内的设备，组内设备将被自动划分至未分组。";
				if(!confirm(msg)) return false;
				var groupTree = this.getZTreeObj(treeId);
				
				groupTree.direct.invoke("remove", {
					success : function(){
						groupTree.removeNode(treeNode);
						groupTree.setting.eventsMgr.fireEvent("change", "remove", treeId, treeNode);
					}
				}, treeNode.id);
				
				return false;
			},
			beforeDrag : function(){return false;},
			onClick : function(event, treeId, treeNode){
				var groupTree = this.getZTreeObj(treeId);
				groupTree.setting.eventsMgr.fireEvent("nodeClick", event, treeId, treeNode);
			},
			onNodeCreated : function(event, treeId, treeNode){
				var groupTree = this.getZTreeObj(treeId);
				groupTree.setting.eventsMgr.fireEvent("nodeCreated", event, treeId, treeNode);
			}
		}
	
	}
};
return groupTreeEditUtil;
});
