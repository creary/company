/**
 * @fileOverview ToggleButton class.
 * Note: this is a deco module, a css module is required (defaults to "css>rl:ToggleButton").
 * @change
 	#1 by prcjack @2014/11/18 Creates file.
 */
orderjs.define("app.deco.ToggleButton",["open.jquery.jquery","app.deco.engine"],function(){var e=(this.track,orderjs("open.jquery.jquery"),orderjs("app.deco.engine")),t=rl.createClass({parent:e.FormContorl.decorator,construct:function(){rl.isPrototyping(arguments[0])||t.parent.apply(this,arguments)},members:{checked:!1,toggleEventName:"click",trigger:"",uncheckedCss:"disabled",initChecked:function(){return this.fieldJq&&(this.checked=0!=this.fieldJq.val()),this.updateView(this.checked),this},initEvents:function(){var e=rl.isNonNullStr(this.trigger)?this.jq.find(this.trigger):this.jq;return e.on(this.toggleEventName,rl.bind(this.toggle,this)),this},initTarget:function(){return this.initField().initChecked().initEvents(),this},setChecked:function(e){return this.checked==e?this:(this.updateField(e).updateView(e).prop("checked",e).fireEvent("change",e),this)},toggle:function(){return this.setChecked(!this.checked),this},updateField:function(e){return this.fieldJq&&this.fieldJq.val(e?1:0),this},updateView:function(e){return this.jq[e?"removeClass":"addClass"](this.uncheckedCss),this},toString:function(){return"[object ToggleButton]"}}});return e.makeJqDeco(t)});