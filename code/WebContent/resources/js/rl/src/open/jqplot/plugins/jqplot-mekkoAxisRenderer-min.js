/**
 * jqPlot
 * Pure JavaScript plotting plugin using jQuery
 *
 * Version: 1.0.4r1120
 *
 * Copyright (c) 2009-2011 Chris Leonello
 * jqPlot is currently available for use in all personal or commercial projects 
 * under both the MIT (http://www.opensource.org/licenses/mit-license.php) and GPL 
 * version 2.0 (http://www.gnu.org/licenses/gpl-2.0.html) licenses. This means that you can 
 * choose the license that best suits your project and use it accordingly. 
 *
 * Although not required, the author would appreciate an email letting him 
 * know of any substantial use of jqPlot.  You can reach the author at: 
 * chris at jqplot dot com or see http://www.jqplot.com/info.php .
 *
 * If you are feeling kind and generous, consider supporting the project by
 * making a donation at: http://www.jqplot.com/donate.php .
 *
 * sprintf functions contained in jqplot.sprintf.js by Ash Searle:
 *
 *     version 2007.04.27
 *     author Ash Searle
 *     http://hexmen.com/blog/2007/03/printf-sprintf/
 *     http://hexmen.com/js/sprintf.js
 *     The author (Ash Searle) has placed this code in the public domain:
 *     "This code is unrestricted: you are free to use it however you like."
 *
 * included jsDate library by Chris Leonello:
 *
 * Copyright (c) 2010-2011 Chris Leonello
 *
 * jsDate is currently available for use in all personal or commercial projects 
 * under both the MIT and GPL version 2.0 licenses. This means that you can 
 * choose the license that best suits your project and use it accordingly.
 *
 * jsDate borrows many concepts and ideas from the Date Instance 
 * Methods by Ken Snyder along with some parts of Ken's actual code.
 * 
 * Ken's origianl Date Instance Methods and copyright notice:
 * 
 * Ken Snyder (ken d snyder at gmail dot com)
 * 2008-09-10
 * version 2.0.2 (http://kendsnyder.com/sandbox/date/)     
 * Creative Commons Attribution License 3.0 (http://creativecommons.org/licenses/by/3.0/)
 *
 * jqplotToImage function based on Larry Siden's export-jqplot-to-png.js.
 * Larry has generously given permission to adapt his code for inclusion
 * into jqPlot.
 *
 * Larry's original code can be found here:
 *
 * https://github.com/lsiden/export-jqplot-to-png
 * 
 * 
 */
!function(t){t.jqplot.MekkoAxisRenderer=function(){},t.jqplot.MekkoAxisRenderer.prototype.init=function(e){this.tickMode,this.barLabelRenderer=t.jqplot.AxisLabelRenderer,this.barLabels=this.barLabels||[],this.barLabelOptions={},this.tickOptions=t.extend(!0,{showGridline:!1},this.tickOptions),this._barLabels=[],t.extend(!0,this,e),"yaxis"==this.name&&(this.tickOptions.formatString=this.tickOptions.formatString||"%d%");var s=this._dataBounds;if(s.min=0,"yaxis"==this.name||"y2axis"==this.name)s.max=100,this.tickMode="even";else if("xaxis"==this.name){this.tickMode=null==this.tickMode?"bar":this.tickMode;for(var i=0;i<this._series.length;i++)s.max+=this._series[i]._sumy}else if("x2axis"==this.name){this.tickMode=null==this.tickMode?"even":this.tickMode;for(var i=0;i<this._series.length;i++)s.max+=this._series[i]._sumy}},t.jqplot.MekkoAxisRenderer.prototype.draw=function(e,s){if(this.show){this.renderer.createTicks.call(this);var i=document.createElement("div");this._elem=t(i),this._elem.addClass("jqplot-axis jqplot-"+this.name),this._elem.css("position","absolute"),i=null,"xaxis"==this.name||"x2axis"==this.name?this._elem.width(this._plotDimensions.width):this._elem.height(this._plotDimensions.height),this.labelOptions.axis=this.name,this._label=new this.labelRenderer(this.labelOptions),this._label.show&&this._elem.append(this._label.draw(e));var h,a,i;if(this.showTicks){h=this._ticks;for(var r=0;r<h.length;r++)a=h[r],!a.showLabel||a.isMinorTick&&!this.showMinorTicks||this._elem.append(a.draw(e))}for(r=0;r<this.barLabels.length;r++)if(this.barLabelOptions.axis=this.name,this.barLabelOptions.label=this.barLabels[r],this._barLabels.push(new this.barLabelRenderer(this.barLabelOptions)),"bar"!=this.tickMode&&(this._barLabels[r].show=!1),this._barLabels[r].show){var i=this._barLabels[r].draw(e,s);i.removeClass("jqplot-"+this.name+"-label"),i.addClass("jqplot-"+this.name+"-tick"),i.addClass("jqplot-mekko-barLabel"),i.appendTo(this._elem),i=null}}return this._elem},t.jqplot.MekkoAxisRenderer.prototype.reset=function(){this.min=this._min,this.max=this._max,this.tickInterval=this._tickInterval,this.numberTicks=this._numberTicks},t.jqplot.MekkoAxisRenderer.prototype.set=function(){var e,s=0,i=0,h=0,a=null==this._label?!1:this._label.show;if(this.show&&this.showTicks){for(var r=this._ticks,n=0;n<r.length;n++){var l=r[n];!l.showLabel||l.isMinorTick&&!this.showMinorTicks||(e="xaxis"==this.name||"x2axis"==this.name?l._elem.outerHeight(!0):l._elem.outerWidth(!0),e>s&&(s=e))}a&&(i=this._label._elem.outerWidth(!0),h=this._label._elem.outerHeight(!0)),"xaxis"==this.name?(s+=h,this._elem.css({height:s+"px",left:"0px",bottom:"0px"})):"x2axis"==this.name?(s+=h,this._elem.css({height:s+"px",left:"0px",top:"0px"})):"yaxis"==this.name?(s+=i,this._elem.css({width:s+"px",left:"0px",top:"0px"}),a&&this._label.constructor==t.jqplot.AxisLabelRenderer&&this._label._elem.css("width",i+"px")):(s+=i,this._elem.css({width:s+"px",right:"0px",top:"0px"}),a&&this._label.constructor==t.jqplot.AxisLabelRenderer&&this._label._elem.css("width",i+"px"))}},t.jqplot.MekkoAxisRenderer.prototype.createTicks=function(){var t,e,s,i,h,a,r=(this._ticks,this.ticks),n=this.name,l=this._dataBounds;if(r.length){for(a=0;a<r.length;a++){var o=r[a],i=new this.tickRenderer(this.tickOptions);o.constructor==Array?(i.value=o[0],i.label=o[1],this.showTicks?this.showTickMarks||(i.showMark=!1):(i.showLabel=!1,i.showMark=!1),i.setTick(o[0],this.name),this._ticks.push(i)):(i.value=o,this.showTicks?this.showTickMarks||(i.showMark=!1):(i.showLabel=!1,i.showMark=!1),i.setTick(o,this.name),this._ticks.push(i))}this.numberTicks=r.length,this.min=this._ticks[0].value,this.max=this._ticks[this.numberTicks-1].value,this.tickInterval=(this.max-this.min)/(this.numberTicks-1)}else{if(t="xaxis"==n||"x2axis"==n?this._plotDimensions.width:this._plotDimensions.height,null!=this.min&&null!=this.max&&null!=this.numberTicks&&(this.tickInterval=null),e=null!=this.min?this.min:l.min,s=null!=this.max?this.max:l.max,e==s){var c=.05;e>0&&(c=Math.max(Math.log(e)/Math.LN10,.05)),e-=c,s+=c}var k,m,x,_=s-e,b=[3,5,6,11,21];if("yaxis"==this.name||"y2axis"==this.name){if(this.min=0,this.max=100,this.numberTicks)this.tickInterval=_/(this.numberTicks-1);else if(this.tickInterval)this.numberTicks=3+Math.ceil(_/this.tickInterval);else{for(k=2+Math.ceil((t-(this.tickSpacing-1))/this.tickSpacing),a=0;a<b.length;a++){if(x=k/b[a],1==x){this.numberTicks=b[a];break}if(x>1)m=x;else{if(1>x){if(Math.abs(m-1)<Math.abs(x-1)){this.numberTicks=b[a-1];break}this.numberTicks=b[a];break}a==b.length-1&&(this.numberTicks=b[a])}}this.tickInterval=_/(this.numberTicks-1)}for(var a=0;a<this.numberTicks;a++)h=this.min+a*this.tickInterval,i=new this.tickRenderer(this.tickOptions),this.showTicks?this.showTickMarks||(i.showMark=!1):(i.showLabel=!1,i.showMark=!1),i.setTick(h,this.name),this._ticks.push(i)}else if("bar"==this.tickMode){for(this.min=0,this.numberTicks=this._series.length+1,i=new this.tickRenderer(this.tickOptions),this.showTicks?this.showTickMarks||(i.showMark=!1):(i.showLabel=!1,i.showMark=!1),i.setTick(0,this.name),this._ticks.push(i),k=0,a=1;a<this.numberTicks;a++)k+=this._series[a-1]._sumy,i=new this.tickRenderer(this.tickOptions),this.showTicks?this.showTickMarks||(i.showMark=!1):(i.showLabel=!1,i.showMark=!1),i.setTick(k,this.name),this._ticks.push(i);this.max=this.max||k,this.max>k&&(i=new this.tickRenderer(this.tickOptions),this.showTicks?this.showTickMarks||(i.showMark=!1):(i.showLabel=!1,i.showMark=!1),i.setTick(this.max,this.name),this._ticks.push(i))}else if("even"==this.tickMode){this.min=0,this.max=this.max||l.max;var p=2+Math.ceil((t-(this.tickSpacing-1))/this.tickSpacing);for(_=this.max-this.min,this.numberTicks=p,this.tickInterval=_/(this.numberTicks-1),a=0;a<this.numberTicks;a++)h=this.min+a*this.tickInterval,i=new this.tickRenderer(this.tickOptions),this.showTicks?this.showTickMarks||(i.showMark=!1):(i.showLabel=!1,i.showMark=!1),i.setTick(h,this.name),this._ticks.push(i)}}},t.jqplot.MekkoAxisRenderer.prototype.pack=function(e,s){var i=this._ticks,h=this.max,a=this.min,r=s.max,n=s.min,l=null==this._label?!1:this._label.show;for(var o in e)this._elem.css(o,e[o]);this._offsets=s;var c=r-n,k=h-a;if(this.p2u=function(t){return(t-n)*k/c+a},this.u2p=function(t){return(t-a)*c/k+n},"xaxis"==this.name||"x2axis"==this.name?(this.series_u2p=function(t){return(t-a)*c/k},this.series_p2u=function(t){return t*k/c+a}):(this.series_u2p=function(t){return(t-h)*c/k},this.series_p2u=function(t){return t*k/c+h}),this.show)if("xaxis"==this.name||"x2axis"==this.name){for(var m=0;m<i.length;m++){var x=i[m];if(x.show&&x.showLabel){var _;if(x.constructor==t.jqplot.CanvasAxisTickRenderer&&x.angle){var b="xaxis"==this.name?1:-1;switch(x.labelPosition){case"auto":_=b*x.angle<0?-x.getWidth()+x._textRenderer.height*Math.sin(-x._textRenderer.angle)/2:-x._textRenderer.height*Math.sin(x._textRenderer.angle)/2;break;case"end":_=-x.getWidth()+x._textRenderer.height*Math.sin(-x._textRenderer.angle)/2;break;case"start":_=-x._textRenderer.height*Math.sin(x._textRenderer.angle)/2;break;case"middle":_=-x.getWidth()/2+x._textRenderer.height*Math.sin(-x._textRenderer.angle)/2;break;default:_=-x.getWidth()/2+x._textRenderer.height*Math.sin(-x._textRenderer.angle)/2}}else _=-x.getWidth()/2;var p=this.u2p(x.value)+_+"px";x._elem.css("left",p),x.pack()}}var d;l&&(d=this._label._elem.outerWidth(!0),this._label._elem.css("left",n+c/2-d/2+"px"),"xaxis"==this.name?this._label._elem.css("bottom","0px"):this._label._elem.css("top","0px"),this._label.pack());for(var u,w,g,m=0;m<this.barLabels.length;m++)u=this._barLabels[m],u.show&&(d=u.getWidth(),w=this._ticks[m].getLeft()+this._ticks[m].getWidth(),g=this._ticks[m+1].getLeft(),u._elem.css("left",(g+w-d)/2+"px"),u._elem.css("top",this._ticks[m]._elem.css("top")),u.pack())}else{for(var m=0;m<i.length;m++){var x=i[m];if(x.show&&x.showLabel){var _;if(x.constructor==t.jqplot.CanvasAxisTickRenderer&&x.angle){var b="yaxis"==this.name?1:-1;switch(x.labelPosition){case"auto":case"end":_=b*x.angle<0?-x._textRenderer.height*Math.cos(-x._textRenderer.angle)/2:-x.getHeight()+x._textRenderer.height*Math.cos(x._textRenderer.angle)/2;break;case"start":_=x.angle>0?-x._textRenderer.height*Math.cos(-x._textRenderer.angle)/2:-x.getHeight()+x._textRenderer.height*Math.cos(x._textRenderer.angle)/2;break;case"middle":_=-x.getHeight()/2;break;default:_=-x.getHeight()/2}}else _=-x.getHeight()/2;var p=this.u2p(x.value)+_+"px";x._elem.css("top",p),x.pack()}}if(l){var f=this._label._elem.outerHeight(!0);this._label._elem.css("top",r-c/2-f/2+"px"),"yaxis"==this.name?this._label._elem.css("left","0px"):this._label._elem.css("right","0px"),this._label.pack()}}}}(jQuery);