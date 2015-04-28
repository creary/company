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
!function(t){t.jqplot.LogAxisRenderer=function(){t.jqplot.LinearAxisRenderer.call(this),this.axisDefaults={base:10,tickDistribution:"power"}},t.jqplot.LogAxisRenderer.prototype=new t.jqplot.LinearAxisRenderer,t.jqplot.LogAxisRenderer.prototype.constructor=t.jqplot.LogAxisRenderer,t.jqplot.LogAxisRenderer.prototype.init=function(e){this.drawBaseline=!0,this.minorTicks="auto",this._scalefact=1,t.extend(!0,this,e),this._autoFormatString="%d",this._overrideFormatString=!1;for(var i in this.renderer.axisDefaults)null==this[i]&&(this[i]=this.renderer.axisDefaults[i]);this.resetDataBounds()},t.jqplot.LogAxisRenderer.prototype.createTicks=function(){var e,i,s,a,h,r=(this._ticks,this.ticks),n=(this.name,this._dataBounds),o="x"===this.name.charAt(0)?this._plotDimensions.width:this._plotDimensions.height,l=30;if(this._scalefact=(Math.max(o,l+1)-l)/300,r.length){for(h=0;h<r.length;h++){var c=r[h],u=new this.tickRenderer(this.tickOptions);c.constructor==Array?(u.value=c[0],u.label=c[1],this.showTicks?this.showTickMarks||(u.showMark=!1):(u.showLabel=!1,u.showMark=!1),u.setTick(c[0],this.name),this._ticks.push(u)):t.isPlainObject(c)?(t.extend(!0,u,c),u.axis=this.name,this._ticks.push(u)):(u.value=c,this.showTicks?this.showTickMarks||(u.showMark=!1):(u.showLabel=!1,u.showMark=!1),u.setTick(c,this.name),this._ticks.push(u))}this.numberTicks=r.length,this.min=this._ticks[0].value,this.max=this._ticks[this.numberTicks-1].value}else if(null==this.min&&null==this.max){if(i=n.min*(2-this.padMin),s=n.max*this.padMax,i==s){var m=.05;i*=1-m,s*=1+m}if(null!=this.min&&this.min<=0)throw"log axis minimum must be greater than 0";if(null!=this.max&&this.max<=0)throw"log axis maximum must be greater than 0";var g,k;g=Math.pow(this.base,Math.floor(Math.log(i)/Math.log(this.base))),k=Math.pow(this.base,Math.ceil(Math.log(s)/Math.log(this.base)));var _=Math.round(Math.log(g)/Math.LN10);null!=this.tickOptions&&this.tickOptions.formatString||(this._overrideFormatString=!0),this.min=g,this.max=k;var x,d=(this.max-this.min,"auto"===this.minorTicks?0:this.minorTicks);if(null==this.numberTicks)if(o>140){if(x=Math.round(Math.log(this.max/this.min)/Math.log(this.base)+1),2>x&&(x=2),0===d){var p=o/(x-1);d=100>p?0:190>p?1:250>p?3:600>p?4:9}}else x=2,0===d&&(d=1),d=0;else x=this.numberTicks;if(_>=0&&3!==d)this._autoFormatString="%d";else if(0>=_&&3===d){var p=-(_-1);this._autoFormatString="%."+Math.abs(_-1)+"f"}else if(0>_){var p=-_;this._autoFormatString="%."+Math.abs(_)+"f"}else this._autoFormatString="%d";for(var u,f,M,b,e,h=0;x>h;h++)if(a=Math.pow(this.base,h-x+1)*this.max,u=new this.tickRenderer(this.tickOptions),this._overrideFormatString&&(u.formatString=this._autoFormatString),this.showTicks?this.showTickMarks||(u.showMark=!1):(u.showLabel=!1,u.showMark=!1),u.setTick(a,this.name),this._ticks.push(u),d&&x-1>h){M=Math.pow(this.base,h-x+2)*this.max,b=M-a,e=M/(d+1);for(var w=d-1;w>=0;w--)f=M-e*(w+1),u=new this.tickRenderer(this.tickOptions),this._overrideFormatString&&""!=this._autoFormatString&&(u.formatString=this._autoFormatString),this.showTicks?this.showTickMarks||(u.showMark=!1):(u.showLabel=!1,u.showMark=!1),u.setTick(f,this.name),this._ticks.push(u)}}else if(null!=this.min&&null!=this.max){var v,R,T=t.extend(!0,{},this.tickOptions,{name:this.name,value:null});if(null==this.numberTicks&&null==this.tickInterval){var S=Math.max(o,l+1),F=Math.ceil((S-l)/35+1),L=t.jqplot.LinearTickGenerator.bestConstrainedInterval(this.min,this.max,F);this._autoFormatString=L[3],v=L[2],R=L[4];for(var h=0;v>h;h++)T.value=this.min+h*R,u=new this.tickRenderer(T),this._overrideFormatString&&""!=this._autoFormatString&&(u.formatString=this._autoFormatString),this.showTicks?this.showTickMarks||(u.showMark=!1):(u.showLabel=!1,u.showMark=!1),this._ticks.push(u)}else if(null!=this.numberTicks&&null!=this.tickInterval){v=this.numberTicks;for(var h=0;v>h;h++)T.value=this.min+h*this.tickInterval,u=new this.tickRenderer(T),this._overrideFormatString&&""!=this._autoFormatString&&(u.formatString=this._autoFormatString),this.showTicks?this.showTickMarks||(u.showMark=!1):(u.showLabel=!1,u.showMark=!1),this._ticks.push(u)}}},t.jqplot.LogAxisRenderer.prototype.pack=function(e,i){var s=parseInt(this.base,10),a=this._ticks,h=function(t){return Math.log(t)/Math.log(s)},r=function(t){return Math.pow(Math.E,Math.log(s)*t)},n=h(this.max),o=h(this.min),l=i.max,c=i.min,u=null==this._label?!1:this._label.show;for(var m in e)this._elem.css(m,e[m]);this._offsets=i;var g=l-c,k=n-o;if(this.p2u=function(t){return r((t-c)*k/g+o)},this.u2p=function(t){return(h(t)-o)*g/k+c},"xaxis"==this.name||"x2axis"==this.name?(this.series_u2p=function(t){return(h(t)-o)*g/k},this.series_p2u=function(t){return r(t*k/g+o)}):(this.series_u2p=function(t){return(h(t)-n)*g/k},this.series_p2u=function(t){return r(t*k/g+n)}),this.show)if("xaxis"==this.name||"x2axis"==this.name){for(var _=0;_<a.length;_++){var x=a[_];if(x.show&&x.showLabel){var d;if(x.constructor==t.jqplot.CanvasAxisTickRenderer&&x.angle)switch(x.labelPosition){case"auto":d=x.angle<0?-x.getWidth()+x._textRenderer.height*Math.sin(-x._textRenderer.angle)/2:-x._textRenderer.height*Math.sin(x._textRenderer.angle)/2;break;case"end":d=-x.getWidth()+x._textRenderer.height*Math.sin(-x._textRenderer.angle)/2;break;case"start":d=-x._textRenderer.height*Math.sin(x._textRenderer.angle)/2;break;case"middle":d=-x.getWidth()/2+x._textRenderer.height*Math.sin(-x._textRenderer.angle)/2;break;default:d=-x.getWidth()/2+x._textRenderer.height*Math.sin(-x._textRenderer.angle)/2}else d=-x.getWidth()/2;var p=this.u2p(x.value)+d+"px";x._elem.css("left",p),x.pack()}}if(u){var f=this._label._elem.outerWidth(!0);this._label._elem.css("left",c+g/2-f/2+"px"),"xaxis"==this.name?this._label._elem.css("bottom","0px"):this._label._elem.css("top","0px"),this._label.pack()}}else{for(var _=0;_<a.length;_++){var x=a[_];if(x.show&&x.showLabel){var d;if(x.constructor==t.jqplot.CanvasAxisTickRenderer&&x.angle)switch(x.labelPosition){case"auto":case"end":d=x.angle<0?-x._textRenderer.height*Math.cos(-x._textRenderer.angle)/2:-x.getHeight()+x._textRenderer.height*Math.cos(x._textRenderer.angle)/2;break;case"start":d=x.angle>0?-x._textRenderer.height*Math.cos(-x._textRenderer.angle)/2:-x.getHeight()+x._textRenderer.height*Math.cos(x._textRenderer.angle)/2;break;case"middle":d=-x.getHeight()/2;break;default:d=-x.getHeight()/2}else d=-x.getHeight()/2;var p=this.u2p(x.value)+d+"px";x._elem.css("top",p),x.pack()}}if(u){var M=this._label._elem.outerHeight(!0);this._label._elem.css("top",l-g/2-M/2+"px"),"yaxis"==this.name?this._label._elem.css("left","0px"):this._label._elem.css("right","0px"),this._label.pack()}}}}(jQuery);