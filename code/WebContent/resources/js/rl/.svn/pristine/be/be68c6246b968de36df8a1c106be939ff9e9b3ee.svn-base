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
!function(t){t.jqplot.PointLabels=function(e){this.show=t.jqplot.config.enablePlugins,this.location="n",this.labelsFromSeries=!1,this.seriesLabelIndex=null,this.labels=[],this._labels=[],this.stackedValue=!1,this.ypadding=6,this.xpadding=6,this.escapeHTML=!0,this.edgeTolerance=-5,this.formatter=t.jqplot.DefaultTickFormatter,this.formatString="",this.hideZeros=!1,this._elems=[],t.extend(!0,this,e)};var e={nw:0,n:1,ne:2,e:3,se:4,s:5,sw:6,w:7},s=["se","s","sw","w","nw","n","ne","e"];t.jqplot.PointLabels.init=function(e,s,i,a){var r=t.extend(!0,{},i,a);r.pointLabels=r.pointLabels||{},this.renderer.constructor!==t.jqplot.BarRenderer||"horizontal"!==this.barDirection||r.pointLabels.location||(r.pointLabels.location="e"),this.plugins.pointLabels=new t.jqplot.PointLabels(r.pointLabels),this.plugins.pointLabels.setLabels.call(this)},t.jqplot.PointLabels.prototype.setLabels=function(){var e,s=this.plugins.pointLabels;if(e=null!=s.seriesLabelIndex?s.seriesLabelIndex:this.renderer.constructor===t.jqplot.BarRenderer&&"horizontal"===this.barDirection?0:0===this._plotData.length?0:this._plotData[0].length-1,s._labels=[],0===s.labels.length||s.labelsFromSeries)if(s.stackedValue){if(this._plotData.length&&this._plotData[0].length)for(var i=0;i<this._plotData.length;i++)s._labels.push(this._plotData[i][e])}else{var a=this.data;if(this.renderer.constructor===t.jqplot.BarRenderer&&this.waterfall&&(a=this._data),a.length&&a[0].length)for(var i=0;i<a.length;i++)s._labels.push(a[i][e]);a=null}else s.labels.length&&(s._labels=s.labels)},t.jqplot.PointLabels.prototype.xOffset=function(t,e,s){e=e||this.location,s=s||this.xpadding;var i;switch(e){case"nw":i=-t.outerWidth(!0)-this.xpadding;break;case"n":i=-t.outerWidth(!0)/2;break;case"ne":i=this.xpadding;break;case"e":i=this.xpadding;break;case"se":i=this.xpadding;break;case"s":i=-t.outerWidth(!0)/2;break;case"sw":i=-t.outerWidth(!0)-this.xpadding;break;case"w":i=-t.outerWidth(!0)-this.xpadding;break;default:i=-t.outerWidth(!0)-this.xpadding}return i},t.jqplot.PointLabels.prototype.yOffset=function(t,e,s){e=e||this.location,s=s||this.xpadding;var i;switch(e){case"nw":i=-t.outerHeight(!0)-this.ypadding;break;case"n":i=-t.outerHeight(!0)-this.ypadding;break;case"ne":i=-t.outerHeight(!0)-this.ypadding;break;case"e":i=-t.outerHeight(!0)/2;break;case"se":i=this.ypadding;break;case"s":i=this.ypadding;break;case"sw":i=this.ypadding;break;case"w":i=-t.outerHeight(!0)/2;break;default:i=-t.outerHeight(!0)-this.ypadding}return i},t.jqplot.PointLabels.draw=function(i,a,r){var l=this.plugins.pointLabels;l.setLabels.call(this);for(var o=0;o<l._elems.length;o++)l._elems[o].emptyForce();if(l._elems.splice(0,l._elems.length),l.show){var n="_"+this._stackAxis+"axis";l.formatString||(l.formatString=this[n]._ticks[0].formatString,l.formatter=this[n]._ticks[0].formatter);for(var h,p,d=this._plotData,c=(this._prevPlotData,this._xaxis),b=this._yaxis,o=0,g=l._labels.length;g>o;o++){var u=l._labels[o];l.hideZeros&&0==parseInt(l._labels[o],10)&&(u=""),null!=u&&(u=l.formatter(l.formatString,u)),p=document.createElement("div"),l._elems[o]=t(p),h=l._elems[o],h.addClass("jqplot-point-label jqplot-series-"+this.index+" jqplot-point-"+o),h.css("position","absolute"),h.insertAfter(i.canvas),l.escapeHTML?h.text(u):h.html(u);var f=l.location;(this.fillToZero&&d[o][1]<0||this.fillToZero&&"bar"===this._type&&"horizontal"===this.barDirection&&d[o][0]<0||(this.waterfall&&parseInt(u,10))<0)&&(f=s[e[f]]);var _=c.u2p(d[o][0])+l.xOffset(h,f),k=b.u2p(d[o][1])+l.yOffset(h,f);this._stack&&!l.stackedValue&&("vertical"===this.barDirection?k=(this._barPoints[o][0][1]+this._barPoints[o][1][1])/2+r._gridPadding.top-.5*h.outerHeight(!0):_=(this._barPoints[o][2][0]+this._barPoints[o][0][0])/2+r._gridPadding.left-.5*h.outerWidth(!0)),this.renderer.constructor==t.jqplot.BarRenderer&&("vertical"==this.barDirection?_+=this._barNudge:k-=this._barNudge),h.css("left",_),h.css("top",k);var v=_+h.width(),L=k+h.height(),m=l.edgeTolerance,w=t(i.canvas).position().left,x=t(i.canvas).position().top,j=i.canvas.width+w,q=i.canvas.height+x;(w>_-m||x>k-m||v+m>j||L+m>q)&&h.remove(),h=null,p=null}}},t.jqplot.postSeriesInitHooks.push(t.jqplot.PointLabels.init),t.jqplot.postDrawSeriesHooks.push(t.jqplot.PointLabels.draw)}(jQuery);