orderjs.define("x:mti.TabsDecorator",["lib.dom.DomWrap","css>x:mti.css.tabs_decorator"],function(){rl.createNamespace("rlx.mti"),rlx.mti.TabsDecorator=rl.createClass({parent:rl.util.EventProvider,construct:function(t){rl.isPrototyping(arguments[0])||(rlx.mti.TabsDecorator.parent.call(this,t),rl.ext(this,t),this.initialize())},members:{autoDecorate:!0,target:"",iniActive:0,activeSwitchEventName:"mousedown",_activeEle:null,_tabCss:"tab",initialize:function(){this.autoDecorate&&rl.onReady(Function.bind(this.decorate,this))},decorate:function(){this.initEvents(),this.initFirstActive()},initEvents:function(){var t,e,i=rl.getDom(this.target),s=rl.dom.cleanWhitespace;s(i),t=this.tabbarEle=i.firstChild,e=this.bodyStackEle=t.nextSibling,rl.$(t).on(this.activeSwitchEventName,this.hndActiveSwitch,this)},initFirstActive:function(){var t,e=rl.isNumber(this.iniActive)?this.iniActive:0,i=this.tabbarEle.getElementsByTagName("li");t=i[e],this.setActive(t)},hndActiveSwitch:function(t){var e=this.getTabByEle(rl.ew.init(t).getTarget());this.setActive(e)},getTabByEle:function(t){return rl.isElement(t)?rl.dom.findParentBy(t,this._isTabEle,this):void 0},_isTabEle:function(t){return rl.dom.hasClass(t,this._tabCss)},_getChildNodeIndex:function(t){var e=-1;return rl.isElement(t)&&t.parentNode&&(e=Array.indexOf(function(e){return e==t},t.parentNode.childNodes)),e},getActive:function(){return this._activeEle},setActive:function(t){if(rl.isElement(t)&&this._activeEle!=t){var e,i,s,n=this._activeEle,r=rl.dom;if(n){if(e=this._getChildNodeIndex(n),i=this.bodyStackEle.childNodes[e],!i)return void rl.debug("cant found last corresponding body,index = "+e+"last.className"+n.className,"err");r.removeClass(n,"active"),r.removeClass(i,"active")}return e=this._getChildNodeIndex(t),(s=this.bodyStackEle.childNodes[e])?(r.addClass(t,"active"),r.addClass(s,"active"),this._activeEle=t,this.fireEvent("activechange",t),this):void rl.debug("cant found corresponding activ body,index = "+e+"last.className"+n.className,"err")}},onDispose:function(){delete this.tabbarEle,delete this.bodyStackEle,delete this._activeEle},dispose:function(){return this._disposed?this:(rl.isFunction(this.onDispose)&&this.onDispose(),this._disposed=!0,this)},toString:function(){return"[object rlx.mti.TabsDecorator]"}}})});