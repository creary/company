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
!function(i){i.jqplot.PyramidGridRenderer=function(){i.jqplot.CanvasGridRenderer.call(this)},i.jqplot.PyramidGridRenderer.prototype=new i.jqplot.CanvasGridRenderer,i.jqplot.PyramidGridRenderer.prototype.constructor=i.jqplot.PyramidGridRenderer,i.jqplot.CanvasGridRenderer.prototype.init=function(t){this._ctx,this.plotBands={show:!1,color:"rgb(230, 219, 179)",axis:"y",start:null,interval:10},i.extend(!0,this,t);var s={lineJoin:"miter",lineCap:"round",fill:!1,isarc:!1,angle:this.shadowAngle,offset:this.shadowOffset,alpha:this.shadowAlpha,depth:this.shadowDepth,lineWidth:this.shadowWidth,closePath:!1,strokeStyle:this.shadowColor};this.renderer.shadowRenderer.init(s)},i.jqplot.PyramidGridRenderer.prototype.draw=function(){function t(t,e,r,o,h){s.save(),h=h||{},(null==h.lineWidth||0!=h.lineWidth)&&(i.extend(!0,s,h),s.beginPath(),s.moveTo(t,e),s.lineTo(r,o),s.stroke()),s.restore()}this._ctx=this._elem.get(0).getContext("2d");var s=this._ctx,e=this._axes,r=e.xaxis.u2p,o=(e.yMidAxis.u2p,e.xaxis.max/1e3),h=r(0),a=r(o),d=["xaxis","yaxis","x2axis","y2axis","yMidAxis"];if(s.save(),s.clearRect(0,0,this._plotDimensions.width,this._plotDimensions.height),s.fillStyle=this.backgroundColor||this.background,s.fillRect(this._left,this._top,this._width,this._height),this.plotBands.show){s.save();var l=this.plotBands;s.fillStyle=l.color;var n,_,b,p,x;if("x"===l.axis.charAt(0)?e.xaxis.show&&(n=e.xaxis):"y"===l.axis.charAt(0)&&(e.yaxis.show?n=e.yaxis:e.y2axis.show?n=e.y2axis:e.yMidAxis.show&&(n=e.yMidAxis)),void 0!==n){var c=l.start;null===c&&(c=n.min);for(var k=c;k<n.max;k+=2*l.interval)"y"===n.name.charAt(0)&&(_=this._left,b=k+l.interval<n.max?n.series_u2p(k+l.interval)+this._top:n.series_u2p(n.max)+this._top,p=this._right-this._left,x=n.series_u2p(c)-n.series_u2p(c+l.interval),s.fillRect(_,b,p,x))}s.restore()}s.save(),s.lineJoin="miter",s.lineCap="butt",s.lineWidth=this.gridLineWidth,s.strokeStyle=this.gridLineColor;for(var w,f,m,u,k=5;k>0;k--){var y=d[k-1],n=e[y],g=n._ticks,W=g.length;if(n.show){if(n.drawBaseline){var C={};switch(null!==n.baselineWidth&&(C.lineWidth=n.baselineWidth),null!==n.baselineColor&&(C.strokeStyle=n.baselineColor),y){case"xaxis":e.yMidAxis.show?(t(this._left,this._bottom,h,this._bottom,C),t(a,this._bottom,this._right,this._bottom,C)):t(this._left,this._bottom,this._right,this._bottom,C);break;case"yaxis":t(this._left,this._bottom,this._left,this._top,C);break;case"yMidAxis":t(h,this._bottom,h,this._top,C),t(a,this._bottom,a,this._top,C);break;case"x2axis":e.yMidAxis.show?(t(this._left,this._top,h,this._top,C),t(a,this._top,this._right,this._top,C)):t(this._left,this._bottom,this._right,this._bottom,C);break;case"y2axis":t(this._right,this._bottom,this._right,this._top,C)}}for(var v=W;v>0;v--){var M=g[v-1];if(M.show){var S=Math.round(n.u2p(M.value))+.5;switch(y){case"xaxis":if(M.showGridline&&this.drawGridlines&&(!M.isMinorTick||n.showMinorTicks)&&t(S,this._top,S,this._bottom),M.showMark&&M.mark&&(!M.isMinorTick||n.showMinorTicks)){m=M.markSize,u=M.mark;var S=Math.round(n.u2p(M.value))+.5;switch(u){case"outside":w=this._bottom,f=this._bottom+m;break;case"inside":w=this._bottom-m,f=this._bottom;break;case"cross":w=this._bottom-m,f=this._bottom+m;break;default:w=this._bottom,f=this._bottom+m}this.shadow&&this.renderer.shadowRenderer.draw(s,[[S,w],[S,f]],{lineCap:"butt",lineWidth:this.gridLineWidth,offset:.75*this.gridLineWidth,depth:2,fill:!1,closePath:!1}),t(S,w,S,f)}break;case"yaxis":if(M.showGridline&&this.drawGridlines&&(!M.isMinorTick||n.showMinorTicks)&&t(this._right,S,this._left,S),M.showMark&&M.mark&&(!M.isMinorTick||n.showMinorTicks)){m=M.markSize,u=M.mark;var S=Math.round(n.u2p(M.value))+.5;switch(u){case"outside":w=this._left-m,f=this._left;break;case"inside":w=this._left,f=this._left+m;break;case"cross":w=this._left-m,f=this._left+m;break;default:w=this._left-m,f=this._left}this.shadow&&this.renderer.shadowRenderer.draw(s,[[w,S],[f,S]],{lineCap:"butt",lineWidth:1.5*this.gridLineWidth,offset:.75*this.gridLineWidth,fill:!1,closePath:!1}),t(w,S,f,S,{strokeStyle:n.borderColor})}break;case"yMidAxis":if(M.showGridline&&this.drawGridlines&&(!M.isMinorTick||n.showMinorTicks)&&(t(this._left,S,h,S),t(a,S,this._right,S)),M.showMark&&M.mark&&(!M.isMinorTick||n.showMinorTicks)){m=M.markSize,u=M.mark;var S=Math.round(n.u2p(M.value))+.5;w=h,f=h+m,this.shadow&&this.renderer.shadowRenderer.draw(s,[[w,S],[f,S]],{lineCap:"butt",lineWidth:1.5*this.gridLineWidth,offset:.75*this.gridLineWidth,fill:!1,closePath:!1}),t(w,S,f,S,{strokeStyle:n.borderColor}),w=a-m,f=a,this.shadow&&this.renderer.shadowRenderer.draw(s,[[w,S],[f,S]],{lineCap:"butt",lineWidth:1.5*this.gridLineWidth,offset:.75*this.gridLineWidth,fill:!1,closePath:!1}),t(w,S,f,S,{strokeStyle:n.borderColor})}break;case"x2axis":if(M.showGridline&&this.drawGridlines&&(!M.isMinorTick||n.showMinorTicks)&&t(S,this._bottom,S,this._top),M.showMark&&M.mark&&(!M.isMinorTick||n.showMinorTicks)){m=M.markSize,u=M.mark;var S=Math.round(n.u2p(M.value))+.5;switch(u){case"outside":w=this._top-m,f=this._top;break;case"inside":w=this._top,f=this._top+m;break;case"cross":w=this._top-m,f=this._top+m;break;default:w=this._top-m,f=this._top}this.shadow&&this.renderer.shadowRenderer.draw(s,[[S,w],[S,f]],{lineCap:"butt",lineWidth:this.gridLineWidth,offset:.75*this.gridLineWidth,depth:2,fill:!1,closePath:!1}),t(S,w,S,f)}break;case"y2axis":if(M.showGridline&&this.drawGridlines&&(!M.isMinorTick||n.showMinorTicks)&&t(this._left,S,this._right,S),M.showMark&&M.mark&&(!M.isMinorTick||n.showMinorTicks)){m=M.markSize,u=M.mark;var S=Math.round(n.u2p(M.value))+.5;switch(u){case"outside":w=this._right,f=this._right+m;break;case"inside":w=this._right-m,f=this._right;break;case"cross":w=this._right-m,f=this._right+m;break;default:w=this._right,f=this._right+m}this.shadow&&this.renderer.shadowRenderer.draw(s,[[w,S],[f,S]],{lineCap:"butt",lineWidth:1.5*this.gridLineWidth,offset:.75*this.gridLineWidth,fill:!1,closePath:!1}),t(w,S,f,S,{strokeStyle:n.borderColor})}}}}M=null}n=null,g=null}if(s.restore(),this.shadow)if(e.yMidAxis.show){var R=[[this._left,this._bottom],[h,this._bottom]];this.renderer.shadowRenderer.draw(s,R);var R=[[a,this._bottom],[this._right,this._bottom],[this._right,this._top]];this.renderer.shadowRenderer.draw(s,R);var R=[[h,this._bottom],[h,this._top]];this.renderer.shadowRenderer.draw(s,R)}else{var R=[[this._left,this._bottom],[this._right,this._bottom],[this._right,this._top]];this.renderer.shadowRenderer.draw(s,R)}0!=this.borderWidth&&this.drawBorder&&(e.yMidAxis.show?(t(this._left,this._top,h,this._top,{lineCap:"round",strokeStyle:e.x2axis.borderColor,lineWidth:e.x2axis.borderWidth}),t(a,this._top,this._right,this._top,{lineCap:"round",strokeStyle:e.x2axis.borderColor,lineWidth:e.x2axis.borderWidth}),t(this._right,this._top,this._right,this._bottom,{lineCap:"round",strokeStyle:e.y2axis.borderColor,lineWidth:e.y2axis.borderWidth}),t(this._right,this._bottom,a,this._bottom,{lineCap:"round",strokeStyle:e.xaxis.borderColor,lineWidth:e.xaxis.borderWidth}),t(h,this._bottom,this._left,this._bottom,{lineCap:"round",strokeStyle:e.xaxis.borderColor,lineWidth:e.xaxis.borderWidth}),t(this._left,this._bottom,this._left,this._top,{lineCap:"round",strokeStyle:e.yaxis.borderColor,lineWidth:e.yaxis.borderWidth}),t(h,this._bottom,h,this._top,{lineCap:"round",strokeStyle:e.yaxis.borderColor,lineWidth:e.yaxis.borderWidth}),t(a,this._bottom,a,this._top,{lineCap:"round",strokeStyle:e.yaxis.borderColor,lineWidth:e.yaxis.borderWidth})):(t(this._left,this._top,this._right,this._top,{lineCap:"round",strokeStyle:e.x2axis.borderColor,lineWidth:e.x2axis.borderWidth}),t(this._right,this._top,this._right,this._bottom,{lineCap:"round",strokeStyle:e.y2axis.borderColor,lineWidth:e.y2axis.borderWidth}),t(this._right,this._bottom,this._left,this._bottom,{lineCap:"round",strokeStyle:e.xaxis.borderColor,lineWidth:e.xaxis.borderWidth}),t(this._left,this._bottom,this._left,this._top,{lineCap:"round",strokeStyle:e.yaxis.borderColor,lineWidth:e.yaxis.borderWidth}))),s.restore(),s=null,e=null}}(jQuery);