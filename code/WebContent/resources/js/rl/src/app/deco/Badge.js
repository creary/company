/**
 * @fileOverview Badge module.
 * Note: this is a deco module, you should define the badge target style.
 * @change
 	#1 by prcjack @2014/01/14 Creates file.
 */
orderjs.define("app.deco.Badge",["open.jquery.jquery","lib.rpc.JqAjax","app.deco.engine"],function(){var t=this.track,r=orderjs("open.jquery.jquery"),e=orderjs("app.deco.engine"),i=orderjs("lib.rpc.JqAjax"),n=rl.createClass({parent:e.DecoratorBase,construct:function(){rl.isPrototyping(arguments[0])||n.parent.apply(this,arguments)},members:{autoStart:!0,checkInterval:60,direct:null,directOptions:null,max:9,maxRenderer:function(t){return String(t)+"+"},retriedCount:0,retryMax:10,url:void 0,urlAttr:"data-url",initialize:function(){return n.parent.prototype.initialize.apply(this,arguments),this.initDirect(),this.autoStart&&rl.defer(this.startCheck,this),this},createDirect:function(){return i(r.extend({owner:this,success:function(t){t>0?this.owner.show(t):this.owner.hide(),this.owner.retriedCount=0},error:function(){this.owner.retriedCount++},complete:function(){var t=this.owner;if(t.checkInterval>0){if(t.retriedCount>t.retryMax)return void rl.debug(this+" retry expires!","warn");window.clearTimeout(t.checkUnreadsTimer),t.checkUnreadsTimer=rl.delay(t.startCheck,1e3*t.checkInterval,t)}}},this.directOptions))},hide:function(){return this.jq.hide(),this},initDirect:function(){return this.direct?this.direct.owner=this:this.direct=this.createDirect(),this},initTarget:function(){return rl.isString(this.url)||(this.url=this.jq.attr(this.urlAttr)),this},show:function(t){return t=parseInt(t),t>0&&(t>this.max&&(t=this.maxRenderer(this.max)),this.jq.text(t).show()),this},startCheck:function(){return rl.isString(this.url)||rl.raiseError("this.url is undefined!",t(this,"startCheck")),this.direct.request({url:this.url}),this},toString:function(){return"[object Badge]"}}});return e.makeJqDeco(n)});