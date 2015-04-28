/**
 * @fileOverview Gui iframe component module.
 * @change
 	#1 by prcjack Creates file.
 */
orderjs.define("gui.Iframe",["gui.engine"],function(){rl.gui.Iframe=rl.createClass({parent:rl.gui.ComponentBase,construct:function(t){rl.isPrototyping(arguments[0])||(rl.gui.Iframe.parent.call(this,t),this.dw=new rl.dom.DomWrap,this.autoRenderOnReady&&rl.onReady(Function.bind(this.render,this)))},members:{src:"",name:"",contentWindowListener:null,autoRenderOnReady:!1,id:void 0,width:"100%",height:"100%",_htmlTpl:new rl.HtmlTpl('<iframe class="rl_comp rl_iframe"  id="{id}" src="{src}" frameborder="0" name="{name}" ></iframe>'),render:function(t){if(this._rendered)return this;t=this.getRenderTarget(t);var n,i=rl.isNonNullStr(this.id)?this.id:this.autoId(),e="about:blank",r=rl.dom.addUnit;return n=this._htmlTpl.renderTo(t,{id:i,src:e,name:this.name,width:r(this.width),height:r(this.height)},this.renderPosition),this.dw.setTarget(n),this.initEvents(),n.src=this.src,this._rendered=!0,this.fireEvent("render"),this},initEvents:function(){var t=(rl.dom.addEventListener,this.getContentWindow());rl.debug(this+" initEvents(): getContentWindow() = "+t),t&&this.doListenContentWindow()},open:function(t){if(!rl.isString(t))return this;this.src=t;var n=this.dw.dom;return n&&(n.src=t),this},getWidth:function(){var t=this.dw.dom;return rl.isElement(t)?t.offsetWidth:"100%"},getHeight:function(){var t=this.dw.dom;return rl.isElement(t)?t.offsetHeight:"100%"},setWidth:function(){return this.width="100%",this},setHeight:function(){return this.height="100%",this},getContentWindow:function(){var t=null;try{t=this.dw.dom.contentWindow}catch(n){t=!1,rl.debug(this+" arguments.callee.caller = "+arguments.callee.caller),rl.dir(n,this+" getContentWindow()")}return t},hndContentWindowLoad:function(){var t=this.contentWindowListener;rl.isFunction(t)?t(this):rl.isObject(t)&&rl.isFunction(t.handler)&&t.handler.call(t.scope,this)},hndContentWindowUnload:function(){var t=rl.dom.removeEventListener,n=this.getContentWindow();n&&(t(n,"load",this.hndContentWindowLoad,this),t(n,"unload",this.hndContentWindowUnload,this))},doListenContentWindow:function(){var t=this.contentWindowListener,n=this.dw.dom;if(!this._contentWindowListened&&t&&n){var i=t,e=this;!rl.isFunction(t)&&rl.isObject(t)&&(i=t.handler,e=t.scope),rl.isFunction(i)&&(rl.dom.addEventListener(n,"load",i,e),this._contentWindowListened=!0)}},onDispose:function(){this.dw.dispose(),delete this.dw},toString:function(){return"[object rl.gui.Iframe]"}}}),rl.gui.ctype2Classes.add("Iframe",rl.gui.Iframe)});