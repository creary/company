orderjs.define("gui.indicator.IndicatorBase",["gui.engine"],function(){rl.gui.indicator.IndicatorBase=rl.createClass({parent:rl.gui.ComponentBase,construct:function(i){rl.isPrototyping(arguments[0])||(rl.gui.indicator.IndicatorBase.parent.call(this,i),this.initialize())},members:{autoRenderOnReady:!0,iniVisible:!0,iniStyle:null,hidden:!1,toString:function(){return"[object rl.gui.indicator.IndicatorBase]"}}}),rl.fill(rl.gui.indicator.IndicatorBase.prototype,rl.gui.itf.iIndicator)});