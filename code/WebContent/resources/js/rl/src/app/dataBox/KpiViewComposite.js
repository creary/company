orderjs.define("app.dataBox.KpiViewComposite",["gui.engine","app.dataBox.engine"],function(){rl.dataBox.KpiViewComposite=rl.createClass({parent:rl.gui.ControlBase,construct:function(t){rl.isPrototyping(arguments[0])||(rl.dataBox.KpiViewComposite.parent.call(this,t),this.initialize())},members:{comps:null,compOptionList:null,compCommonOptions:null,compDataList:null,autoAppendCompSeperator:!0,compSeperatorHtml:'<div class="rl_databox_panel_sep_line_h"></div>',autoRenderOnReady:!1,_htmlTpl:new rl.HtmlTpl('<div id="{id}"></div>'),initialize:function(){this.comps=[],this.dw=new rl.dom.DomWrap,this.compOptionList&&this.initComps(),this.autoRenderOnReady&&rl.onReady(this.render,this)},initComps:function(){var t=this.compOptionList,i=this.comps;if(rl.isArray(t))Array.each(function(t,e){i.push(this.createComp(t,String(e)))},t,this);else if(rl.isObject(t))for(var e in t){var r=t[e];i.push(this.createComp(r,e))}else{var n=this+"-> initComps(): Invalid compOptionList = : "+t;rl.debug(n,"err")}},createComp:function(t,i){if(!rl.isObject(t))return null;var e,r;if(e=rl.ext({id:i},t),r=e.ctype,delete e.ctype,rl.isNonNullStr(r)&&(r=rl.dataBox[r]),rl.isFunction(r)){var n={owner:this};return new r(rl.fill(n,e))}return rl.debug(this+"->createComp(): Invalid ctype= "+r,"err"),null},render:function(t){if(this._rendered)return this;t=this.getRenderTarget(t);var i=this._htmlTpl.renderTo(t,{id:this.id},this.renderPosition);return this.dw.setTarget(i),this._renderComps(i),this._rendered=!0,this.fireEvent("render"),this},_renderComps:function(t){var i,e=this.comps,r=this.compDataList;Array.each(function(e){if(!e._rendered)if(i=r[e.id])e.renderTarget=t,e.loadData(i),this.hndCompRender(e);else{var n=this+"-> _renderComps(): Cant found "+e+"(id ="+e.id+")'s correspond data";rl.debug(n,"err")}},e,this)},loadData:function(t){if(!rl.isObject(t)||!rl.isObject(t.compDataList)){var i=this+"-> loadData(): Invalid data = "+t;throw{description:i}}this.comps.length>1||(this.compOptionList=t.compOptionList,this.initComps()),this.compDataList=t.compDataList,this._rendered||this.render()},hndCompRender:function(t){this.autoAppendCompSeperator&&rl.dom.insertHtml("beforeend",this.compSeperatorHtml,t.renderTarget),this.fireEvent("comprender",t)},updateComps:function(){},toString:function(){return"[object rl.dataBox.KpiViewComposite]"}}})});