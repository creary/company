/**
 * @fileOverview Defines standard tree list page.
 * @change
 	#1 by prcjack @2014/05/28 Creates file.
	#2 by prcjack @2014/05/29 Defer to load dialog module.
 */
orderjs.define("x:mti.treeListPage",["gui.tree.Tree","gui.grid.Grid","x:mti.listPage"],function(){rl.createNamespace("rlx.mti",{createTree:function(e){var r=(rlx.mti,rl.ext({},e)),a=new rl.data.JsonListVisitor({fields:r.dataFields,data:r.data}),t=rl.isArray(r.data),i=new rl.data.Table({uniqueCheck:rl.isDefined(r.dataUniqueCheck)?r.dataUniqueCheck:!!r.dataPrimaryKey,dataVisitor:a}),o=new rl.Tree(rl.ext({autoRenderOnReady:!1,loadDataBeforeRender:t,dataSource:i},r));return!t&&rl.isNonNullStr(r.dataUrl)&&o.on("render",function(){rl.rpc.sdtGet(r.dataUrl,function(e,r,t){a.data=t,i.load(),o.initFirstOpenTo()},function(e,r){alert(r)})}),o},createTreeListPage:function(e){var r=rlx.mti;e.searchDialog&&(orderjs("gui.dialog.Dialog"),orderjs(function(){var a=rl.ext({},e.searchDialog);r.listPageSearchDialog=r.createSearchDialog(a),a.autoSearchOnPressEnter!==!1&&r.addAutoSearchToSearchDialog()})),r.listPageTree=r.createTree(e.tree),e.toolbar&&(r.listPageToolbar=r.createToolbar(e.toolbar)),r.listPageGrid=r.createGrid(rl.ext(e.grid,{loadDataBeforeRender:!1})),r.listBox=new rl.gui.box.Box({layoutType:rl.gui.layout.BorderLayout,id:"listBox",autoRenderOnReady:!1,layoutConfig:{north:e.toolbar?{iniSize:30,comp:r.listPageToolbar}:null,center:{margin:"0 2 0 5",comp:r.listPageGrid}}}),rl.layoutBody({west:{iniSize:200,comp:r.listPageTree},center:{comp:r.listBox}}),rl.onReady(function(){rlx.mti.cancelListPageGridSearchFormSubmit()})}})});