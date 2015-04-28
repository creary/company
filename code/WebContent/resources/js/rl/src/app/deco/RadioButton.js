/**
 * @fileOverview RadioButton class.
 * Note: this is a deco module, a css module is required (defaults to "css>open.jQuery-idTabs.jquery_idtabs").
 * @change
 	#1 by prcjack @2014/11/19 Creates file.
 */
orderjs.define("app.deco.RadioButton",["open.jquery.jquery","open.jQuery-idTabs.idTabs-ordered","app.deco.engine"],function(){var e=(this.track,orderjs("open.jquery.jquery")),t=orderjs("app.deco.engine"),i=rl.createClass({parent:t.FormContorl.decorator,construct:function(){rl.isPrototyping(arguments[0])||i.parent.apply(this,arguments)},members:{field:"",fieldJq:null,selectedIndex:-1,selectedCss:"selected",getItemJq:function(e){return this.jq.find("a").eq(e)},getItemJqByVal:function(e){return this.jq.find("a[href='#"+e+"']").first()},getItemIndexByVal:function(e){var t=-1,i=this.getItemJqByVal(e);return i.length&&(t=i.parent().index()),t},getItemVal:function(e){var t=this.getItemJq(e);return t?t.attr("href").replace("#",""):void 0},initEvents:function(){var t=this;return this.jq.find("a").on("click",function(){t.select(e(this).parent().index())}),this},initSelected:function(){if(this.fieldJq){var e=this.fieldJq.val();this.selectedIndex=this.jq.find("a[href='#"+e+"']").parent().index()}return this.updateView(this.selectedIndex),this},initTarget:function(){return this.jq.idTabs(),this.initField().initEvents().initSelected(),this},select:function(e){if(e=parseInt(e,10),isNaN(e)&&(e=0),this.selectedIndex==e)return this;var t=this.getItemVal(e);return this.updateField(t).updateView(e).prop("selectedIndex",e).fireEvent("change",t),this},updateView:function(e){var t=this.selectedCss,i=this.getItemJq(this.selectedIndex),n=this.getItemJq(e);return i.length&&i.removeClass(t),n.addClass(t),this},val:function(e){if(0==arguments.length)return this.fieldJq?this.fieldJq.val():this.getItemVal(this.selectedIndex);var t=this.getItemIndexByVal(e);return t>-1&&this.select(t),this},toString:function(){return"[object RadioButton]"}}});return t.makeJqDeco(i)});