orderjs.define("app.deco.TabsDecorator",["lib.dom.DomWrap","gui.engine"],function(){rl.createNamespace("rl.deco"),rl.deco.TabsDecorator=rl.createClass({parent:rl.gui.ComponentBase,construct:function(t){rl.isPrototyping(arguments[0])||(rl.deco.TabsDecorator.parent.call(this,t),(rl.isNonNullStr(t)||rl.isElement(t))&&(t={target:t}),rl.ext(this,t),this.initialize())},members:{activeCss:"active",autoDecorate:!0,activeIndex:-1,activeSwitchEventName:"mousedown",iniActive:0,target:"",initialize:function(){this.dw=new rl.dom.DomWrap,this.autoDecorate&&rl.onReady(Function.bind(this.decorate,this))},decorate:function(t){return this._decorated?this:(this.initView(t),this.initEvents(),this.initFirstActive(),this._decorated=!0,this.fireEvent("decorate"),this)},initView:function(t){rl.isDefined(t)||(t=this.target);var i=rl.getDom(t),e=rl.dom.cleanWhitespace;if(!rl.isElement(i))throw{description:this+"->initView(): Invalid target: "+t};e(i),this.dw.setTarget(i),this.tabbarEle=i.firstChild,this.tabsCtnEle=this.tabbarEle.getElementsByTagName("ul")[0],this.bodyStackEle=this.tabbarEle.nextSibling,e(this.tabsCtnEle),e(this.bodyStackEle)},initEvents:function(){rl.$(this.tabsCtnEle).on(this.activeSwitchEventName,this.hndActiveSwitch,this)},initFirstActive:function(){var t=rl.isNumber(this.iniActive)?this.iniActive:0;this.setActive(t)},hndActiveSwitch:function(t){var i=this.getTabByEle(rl.ew.init(t).getTarget()),e=rl.$(i).getIndex();this.setActive(e)},getTabByEle:function(t){return rl.isElement(t)?rl.dom.findParentBy(t,this._isTabEle,this):void 0},_isTabEle:function(t){return rl.isElement(t)&&t.parentNode==this.tabsCtnEle},setActive:function(t){var i=this._lastActiveIndex;return t>-1||(t=0),t===i?this:(i>-1&&(rl.$(this.tabsCtnEle.childNodes[i]).removeClass(this.activeCss),rl.$(this.bodyStackEle.childNodes[i]).removeClass(this.activeCss)),rl.$(this.tabsCtnEle.childNodes[t]).addClass(this.activeCss),rl.$(this.bodyStackEle.childNodes[t]).addClass(this.activeCss),this.activeIndex=this._lastActiveIndex=t,this.fireEvent("activechange",t),this)},onDispose:function(){rl.$(this.tabsCtnEle).un(this.activeSwitchEventName,this.hndActiveSwitch,this),delete this.tabbarEle,delete this.tabsCtnEle,delete this.bodyStackEle},dispose:function(){return this._disposed?this:(rl.isFunction(this.onDispose)&&this.onDispose(),this._disposed=!0,this)},toString:function(){return"[object rl.deco.TabsDecorator]"}}})});