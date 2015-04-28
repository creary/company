orderjs.define("gui.tree.TreeSelector",["lib.data.bridge.JsonListVisitor","lib.rpc.OneoffSdtHelperMgr","gui.engine","gui.tree.Tree"],function(){rl.gui.tree.TreeSelector=rl.createClass({parent:rl.gui.tree.Tree,construct:function(t){rl.isPrototyping(arguments[0])||rl.gui.tree.TreeSelector.parent.call(this,t)},members:{name:"",dataUrl:"",valueSep:",",ctrlExtras:"",selectorType:"multi",dataFields:null,dataSourceType:rl.data.Table,dataVisitorType:rl.data.JsonListVisitor,autoLoadData:!0,loadDataBeforeRender:!1,_htmlTpl:new rl.HtmlTpl('<div id={id} class="rl_tree {css} {linesStyle}" style="width:{width}; height:{height};"><input id="{valueCtrlId}" type="hidden" name="{name}" {ctrlExtras} /><div id="{rootNodeCtnId}" style="height:100%"></div></div>'),initialize:function(){this.rootNodeCtnId=this.id+"_rootNodeCtn",this.valueCtrlId=this.id+"_valueCtrl",this.initDataSource(),rl.gui.tree.TreeSelector.parent.prototype.initialize.apply(this,arguments)},initDataSource:function(){this.dataSource||(this.dataSource=this.createDataSource());var t=this.dataSource;t.on("load",this.hndDataLoad,this),t.on("readrequesterror",this.hndDataReadErr,this)},createDataSource:function(){return new this.dataSourceType({dataVisitor:new this.dataVisitorType({fields:this.dataFields})})},render:function(t){if(this._rendered)return this;t=this.getRenderTarget(t);var e=rl.dom.insertHtml(this.renderPosition,this.getRenderHtml(),t);return this.dw.setTarget(e),this.rootNode.renderTarget=this.getRootNodeCtn(),this._rendered=!0,this.afterRender(),this.fireEvent("render"),this},getRenderHtml:function(){var t=this.showElbowLines?this.cssElbowWithLines:this.cssElbowNoLines,e=this.id,i=rl.isNonNullStr(this.css)||"",r=rl.dom.addUnit(this.width),a=rl.dom.addUnit(this.height);return this._htmlTpl.assign({id:e,valueCtrlId:this.valueCtrlId,rootNodeCtnId:this.rootNodeCtnId,name:this.name,ctrlExtras:this.ctrlExtras,css:i,width:r,height:a,linesStyle:t})},getRootNodeCtn:function(){return rl.getDom(this.rootNodeCtnId)},getScrollWidth:function(){return rl.getDom(this.rootNodeCtnId).scrollWidth},getOffsetWidth:function(){return rl.getDom(this.rootNodeCtnId).offsetWidth},afterRender:function(){this.initEvents(),this.autoLoadData&&this.doAutoLoadData()},doAutoLoadData:function(){var t=this.dataSource;t.httpRequest||t.dataVisitor.data?t.load():this.doSdtLoadData()},doSdtLoadData:function(){var t=this;rl.rpc.sdtGet(this.dataUrl,function(e,i,r){var a=t.dataSource;a.dataVisitor.data=r,a.load(),t.initFirstOpenTo()},function(e,i){t.hndDataReadErr(i)})},hndDataLoad:function(){rl.gui.tree.TreeSelector.parent.prototype.hndDataLoad.apply(this,arguments),this.updateValue()},initEvents:function(){this.on("selectchange",this.updateValue,this),rl.gui.tree.TreeSelector.parent.prototype.initEvents.apply(this,arguments)},updateValue:function(){var t=this._getSelectedValue();t!==this.value&&(rl.$(this.valueCtrlId).dom.value=this.value=t,this.updateValueListener(),this.fireEvent("change"))},_getSelectedValue:function(){var t;if("multi"===this.selectorType){var e=this.getSelectedNodes(),i=[];Array.each(function(t){i.push(t.id)},e),t=i.join(this.valueSep)}else{var r=this.getSelectedNode();t=r?r.id:""}return t},updateValueListener:function(){if(this.valueListener){var t=rl.getDom(this.valueListener);rl.isElement(t)&&(t.value=this.value)}},afterTreeStructChange:function(){this.updateValue()},toString:function(){return"[object rl.gui.tree.TreeSelector]"}}}),rl.gui.ctype2Classes.add("TreeSelector",rl.gui.tree.TreeSelector)});