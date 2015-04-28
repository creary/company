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
!function(i){function s(s,a,l){l=l||{},l.axesDefaults=i.extend(!0,{pad:0},l.axesDefaults),l.legend=i.extend(!0,{placement:"outside"},l.legend);var t=!1;if(l.seriesDefaults.renderer==i.jqplot.BezierCurveRenderer)t=!0;else if(l.series)for(var e=0;e<l.series.length;e++)l.series[e].renderer==i.jqplot.BezierCurveRenderer&&(t=!0);t&&(l.axesDefaults.renderer=i.jqplot.BezierAxisRenderer)}i.jqplot.BezierCurveRenderer=function(){i.jqplot.LineRenderer.call(this)},i.jqplot.BezierCurveRenderer.prototype=new i.jqplot.LineRenderer,i.jqplot.BezierCurveRenderer.prototype.constructor=i.jqplot.BezierCurveRenderer,i.jqplot.BezierCurveRenderer.prototype.setGridData=function(i){var s=this._xaxis.series_u2p,a=this._yaxis.series_u2p,l=this.data;this.gridData=[],this._prevGridData=[];var t=this.index;if(2==l.length)if(0==t)this.gridData=[[s.call(this._xaxis,l[0][0]),a.call(this._yaxis,l[0][1])],[s.call(this._xaxis,l[1][0]),a.call(this._yaxis,l[1][1]),s.call(this._xaxis,l[1][2]),a.call(this._yaxis,l[1][3]),s.call(this._xaxis,l[1][4]),a.call(this._yaxis,l[1][5])],[s.call(this._xaxis,l[1][4]),a.call(this._yaxis,this._yaxis.min)],[s.call(this._xaxis,l[0][0]),a.call(this._yaxis,this._yaxis.min)]];else{var e=i.series[t-1].data;this.gridData=[[s.call(this._xaxis,l[0][0]),a.call(this._yaxis,l[0][1])],[s.call(this._xaxis,l[1][0]),a.call(this._yaxis,l[1][1]),s.call(this._xaxis,l[1][2]),a.call(this._yaxis,l[1][3]),s.call(this._xaxis,l[1][4]),a.call(this._yaxis,l[1][5])],[s.call(this._xaxis,e[1][4]),a.call(this._yaxis,e[1][5])],[s.call(this._xaxis,e[1][2]),a.call(this._yaxis,e[1][3]),s.call(this._xaxis,e[1][0]),a.call(this._yaxis,e[1][1]),s.call(this._xaxis,e[0][0]),a.call(this._yaxis,e[0][1])]]}else if(0==t)this.gridData=[[s.call(this._xaxis,l[0][0]),a.call(this._yaxis,l[0][1])],[s.call(this._xaxis,l[1][0]),a.call(this._yaxis,l[1][1]),s.call(this._xaxis,l[2][0]),a.call(this._yaxis,l[2][1]),s.call(this._xaxis,l[3][0]),a.call(this._yaxis,l[3][1])],[s.call(this._xaxis,l[3][1]),a.call(this._yaxis,this._yaxis.min)],[s.call(this._xaxis,l[0][0]),a.call(this._yaxis,this._yaxis.min)]];else{var e=i.series[t-1].data;this.gridData=[[s.call(this._xaxis,l[0][0]),a.call(this._yaxis,l[0][1])],[s.call(this._xaxis,l[1][0]),a.call(this._yaxis,l[1][1]),s.call(this._xaxis,l[2][0]),a.call(this._yaxis,l[2][1]),s.call(this._xaxis,l[3][0]),a.call(this._yaxis,l[3][1])],[s.call(this._xaxis,e[3][0]),a.call(this._yaxis,e[3][1])],[s.call(this._xaxis,e[2][0]),a.call(this._yaxis,e[2][1]),s.call(this._xaxis,e[1][0]),a.call(this._yaxis,e[1][1]),s.call(this._xaxis,e[0][0]),a.call(this._yaxis,e[0][1])]]}},i.jqplot.BezierCurveRenderer.prototype.makeGridData=function(i,s){var a=this._xaxis.series_u2p,l=this._yaxis.series_u2p,t=[],e=this.index;if(2==i.length)if(0==e)t=[[a.call(this._xaxis,i[0][0]),l.call(this._yaxis,i[0][1])],[a.call(this._xaxis,i[1][0]),l.call(this._yaxis,i[1][1]),a.call(this._xaxis,i[1][2]),l.call(this._yaxis,i[1][3]),a.call(this._xaxis,i[1][4]),l.call(this._yaxis,i[1][5])],[a.call(this._xaxis,i[1][4]),l.call(this._yaxis,this._yaxis.min)],[a.call(this._xaxis,i[0][0]),l.call(this._yaxis,this._yaxis.min)]];else{var x=s.series[e-1].data;t=[[a.call(this._xaxis,i[0][0]),l.call(this._yaxis,i[0][1])],[a.call(this._xaxis,i[1][0]),l.call(this._yaxis,i[1][1]),a.call(this._xaxis,i[1][2]),l.call(this._yaxis,i[1][3]),a.call(this._xaxis,i[1][4]),l.call(this._yaxis,i[1][5])],[a.call(this._xaxis,x[1][4]),l.call(this._yaxis,x[1][5])],[a.call(this._xaxis,x[1][2]),l.call(this._yaxis,x[1][3]),a.call(this._xaxis,x[1][0]),l.call(this._yaxis,x[1][1]),a.call(this._xaxis,x[0][0]),l.call(this._yaxis,x[0][1])]]}else if(0==e)t=[[a.call(this._xaxis,i[0][0]),l.call(this._yaxis,i[0][1])],[a.call(this._xaxis,i[1][0]),l.call(this._yaxis,i[1][1]),a.call(this._xaxis,i[2][0]),l.call(this._yaxis,i[2][1]),a.call(this._xaxis,i[3][0]),l.call(this._yaxis,i[3][1])],[a.call(this._xaxis,i[3][1]),l.call(this._yaxis,this._yaxis.min)],[a.call(this._xaxis,i[0][0]),l.call(this._yaxis,this._yaxis.min)]];else{var x=s.series[e-1].data;t=[[a.call(this._xaxis,i[0][0]),l.call(this._yaxis,i[0][1])],[a.call(this._xaxis,i[1][0]),l.call(this._yaxis,i[1][1]),a.call(this._xaxis,i[2][0]),l.call(this._yaxis,i[2][1]),a.call(this._xaxis,i[3][0]),l.call(this._yaxis,i[3][1])],[a.call(this._xaxis,x[3][0]),l.call(this._yaxis,x[3][1])],[a.call(this._xaxis,x[2][0]),l.call(this._yaxis,x[2][1]),a.call(this._xaxis,x[1][0]),l.call(this._yaxis,x[1][1]),a.call(this._xaxis,x[0][0]),l.call(this._yaxis,x[0][1])]]}return t},i.jqplot.BezierCurveRenderer.prototype.draw=function(i,s,a){if(i.save(),s.length&&this.showLine){i.save();var l=null!=a?a:{};i.fillStyle=l.fillStyle||this.color,i.beginPath(),i.moveTo(s[0][0],s[0][1]),i.bezierCurveTo(s[1][0],s[1][1],s[1][2],s[1][3],s[1][4],s[1][5]),i.lineTo(s[2][0],s[2][1]),2==s[3].length?i.lineTo(s[3][0],s[3][1]):i.bezierCurveTo(s[3][0],s[3][1],s[3][2],s[3][3],s[3][4],s[3][5]),i.closePath(),i.fill(),i.restore()}i.restore()},i.jqplot.BezierCurveRenderer.prototype.drawShadow=function(){},i.jqplot.BezierAxisRenderer=function(){i.jqplot.LinearAxisRenderer.call(this)},i.jqplot.BezierAxisRenderer.prototype=new i.jqplot.LinearAxisRenderer,i.jqplot.BezierAxisRenderer.prototype.constructor=i.jqplot.BezierAxisRenderer,i.jqplot.BezierAxisRenderer.prototype.init=function(s){i.extend(!0,this,s);for(var a=this._dataBounds,l=0;l<this._series.length;l++){var t=this._series[l],e=t.data;if(4==e.length)for(var x=0;x<e.length;x++)"xaxis"==this.name||"x2axis"==this.name?((e[x][0]<a.min||null==a.min)&&(a.min=e[x][0]),(e[x][0]>a.max||null==a.max)&&(a.max=e[x][0])):((e[x][1]<a.min||null==a.min)&&(a.min=e[x][1]),(e[x][1]>a.max||null==a.max)&&(a.max=e[x][1]));else if("xaxis"==this.name||"x2axis"==this.name){(e[0][0]<a.min||null==a.min)&&(a.min=e[0][0]),(e[0][0]>a.max||null==a.max)&&(a.max=e[0][0]);for(var x=0;5>x;x+=2)(e[1][x]<a.min||null==a.min)&&(a.min=e[1][x]),(e[1][x]>a.max||null==a.max)&&(a.max=e[1][x])}else{(e[0][1]<a.min||null==a.min)&&(a.min=e[0][1]),(e[0][1]>a.max||null==a.max)&&(a.max=e[0][1]);for(var x=1;6>x;x+=2)(e[1][x]<a.min||null==a.min)&&(a.min=e[1][x]),(e[1][x]>a.max||null==a.max)&&(a.max=e[1][x])}}},i.jqplot.preInitHooks.push(s)}(jQuery);