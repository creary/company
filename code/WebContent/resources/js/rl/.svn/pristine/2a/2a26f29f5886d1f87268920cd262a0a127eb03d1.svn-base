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
!function(i){function e(e,t,s){s=s||{},s.axesDefaults=s.axesDefaults||{},s.grid=s.grid||{},s.legend=s.legend||{},s.seriesDefaults=s.seriesDefaults||{};var h=!1;if(s.seriesDefaults.renderer===i.jqplot.PyramidRenderer)h=!0;else if(s.series)for(var r=0;r<s.series.length;r++)s.series[r].renderer===i.jqplot.PyramidRenderer&&(h=!0);h&&(s.axesDefaults.renderer=i.jqplot.PyramidAxisRenderer,s.grid.renderer=i.jqplot.PyramidGridRenderer,s.seriesDefaults.pointLabels={show:!1})}function t(){this.plugins.pyramidRenderer&&this.plugins.pyramidRenderer.highlightCanvas&&(this.plugins.pyramidRenderer.highlightCanvas.resetCanvas(),this.plugins.pyramidRenderer.highlightCanvas=null),this.plugins.pyramidRenderer={highlightedSeriesIndex:null},this.plugins.pyramidRenderer.highlightCanvas=new i.jqplot.GenericCanvas,this.eventCanvas._elem.before(this.plugins.pyramidRenderer.highlightCanvas.createElement(this._gridPadding,"jqplot-pyramidRenderer-highlight-canvas",this._plotDimensions,this)),this.plugins.pyramidRenderer.highlightCanvas.setContext(),this.eventCanvas._elem.bind("mouseleave",{plot:this},function(i){h(i.data.plot)})}function s(i,e,t,s){var h=i.series[e],r=i.plugins.pyramidRenderer.highlightCanvas;r._ctx.clearRect(0,0,r._ctx.canvas.width,r._ctx.canvas.height),h._highlightedPoint=t,i.plugins.pyramidRenderer.highlightedSeriesIndex=e;var l={fillStyle:h.highlightColors[t],fillRect:!1};h.renderer.shapeRenderer.draw(r._ctx,s,l),h.synchronizeHighlight!==!1&&i.series.length>=h.synchronizeHighlight&&h.synchronizeHighlight!==e&&(h=i.series[h.synchronizeHighlight],l={fillStyle:h.highlightColors[t],fillRect:!1},h.renderer.shapeRenderer.draw(r._ctx,h._barPoints[t],l)),r=null}function h(i){var e=i.plugins.pyramidRenderer.highlightCanvas;e._ctx.clearRect(0,0,e._ctx.canvas.width,e._ctx.canvas.height);for(var t=0;t<i.series.length;t++)i.series[t]._highlightedPoint=null;i.plugins.pyramidRenderer.highlightedSeriesIndex=null,i.target.trigger("jqplotDataUnhighlight"),e=null}function r(i,e,t,r,l){if(r){var a=[r.seriesIndex,r.pointIndex,r.data],n=jQuery.Event("jqplotDataMouseOver");if(n.pageX=i.pageX,n.pageY=i.pageY,l.target.trigger(n,a),l.series[a[0]].highlightMouseOver&&(a[0]!=l.plugins.pyramidRenderer.highlightedSeriesIndex||a[1]!=l.series[a[0]]._highlightedPoint)){var o=jQuery.Event("jqplotDataHighlight");o.which=i.which,o.pageX=i.pageX,o.pageY=i.pageY,l.target.trigger(o,a),s(l,r.seriesIndex,r.pointIndex,r.points)}}else null==r&&h(l)}void 0===i.jqplot.PyramidAxisRenderer&&i.ajax({url:i.jqplot.pluginLocation+"jqplot.pyramidAxisRenderer.js",dataType:"script",async:!1}),void 0===i.jqplot.PyramidGridRenderer&&i.ajax({url:i.jqplot.pluginLocation+"jqplot.pyramidGridRenderer.js",dataType:"script",async:!1}),i.jqplot.PyramidRenderer=function(){i.jqplot.LineRenderer.call(this)},i.jqplot.PyramidRenderer.prototype=new i.jqplot.LineRenderer,i.jqplot.PyramidRenderer.prototype.constructor=i.jqplot.PyramidRenderer,i.jqplot.PyramidRenderer.prototype.init=function(e,s){e=e||{},this._type="pyramid",this.barPadding=10,this.barWidth=null,this.fill=!0,this.highlightMouseOver=!0,this.highlightMouseDown=!1,this.highlightColors=[],this.highlightThreshold=2,this.synchronizeHighlight=!1,this.offsetBars=!1,e.highlightMouseDown&&null==e.highlightMouseOver&&(e.highlightMouseOver=!1),this.side="right",i.extend(!0,this,e),this._highlightThreshold="left"===this.side?[[-this.highlightThreshold,0],[-this.highlightThreshold,0],[0,0],[0,0]]:[[0,0],[0,0],[this.highlightThreshold,0],[this.highlightThreshold,0]],this.renderer.options=e,this._highlightedPoint=null,this._dataColors=[],this._barPoints=[],this.fillAxis="y",this._primaryAxis="_yaxis",this._xnudge=0;var h={lineJoin:"miter",lineCap:"butt",fill:this.fill,fillRect:this.fill,isarc:!1,strokeStyle:this.color,fillStyle:this.color,closePath:this.fill,lineWidth:this.lineWidth};this.renderer.shapeRenderer.init(h);var l=e.shadowOffset;null==l&&(l=this.lineWidth>2.5?1.25*(1+.6*(Math.atan(this.lineWidth/2.5)/.785398163-1)):1.25*Math.atan(this.lineWidth/2.5)/.785398163);var a={lineJoin:"miter",lineCap:"butt",fill:this.fill,fillRect:this.fill,isarc:!1,angle:this.shadowAngle,offset:l,alpha:this.shadowAlpha,depth:this.shadowDepth,closePath:this.fill,lineWidth:this.lineWidth};if(this.renderer.shadowRenderer.init(a),s.postDrawHooks.addOnce(t),s.eventListenerHooks.addOnce("jqplotMouseMove",r),"left"===this.side)for(var n=0,o=this.data.length;o>n;n++)this.data[n][1]=-Math.abs(this.data[n][1])},i.jqplot.PyramidRenderer.prototype.setGridData=function(){{var i=this._xaxis.series_u2p,e=this._yaxis.series_u2p,t=this._plotData;this._prevPlotData}this.gridData=[],this._prevGridData=[];var s,h=t.length,r=!1;for(s=0;h>s;s++)t[s][1]<0&&(this.side="left");for("yMidAxis"===this._yaxis.name&&"right"===this.side&&(this._xnudge=this._xaxis.max/2e3,r=!0),s=0;h>s;s++)null!=t[s][0]&&null!=t[s][1]?this.gridData.push([i(t[s][1]),e(t[s][0])]):null==t[s][0]?this.gridData.push([i(t[s][1]),null]):null==t[s][1]&&this.gridData.push(null,[e(t[s][0])]),0===t[s][1]&&r&&(this.gridData[s][0]=i(this._xnudge))},i.jqplot.PyramidRenderer.prototype.makeGridData=function(i){var e,t=this._xaxis.series_u2p,s=this._yaxis.series_u2p,h=[],r=i.length,l=!1;for(e=0;r>e;e++)i[e][1]<0&&(this.side="left");for("yMidAxis"===this._yaxis.name&&"right"===this.side&&(this._xnudge=this._xaxis.max/2e3,l=!0),e=0;r>e;e++)null!=i[e][0]&&null!=i[e][1]?h.push([t(i[e][1]),s(i[e][0])]):null==i[e][0]?h.push([t(i[e][1]),null]):null==i[e][1]&&h.push([null,s(i[e][0])]),0===i[e][1]&&l&&(h[e][0]=t(this._xnudge));return h},i.jqplot.PyramidRenderer.prototype.setBarWidth=function(){var i=0,e=this[this._primaryAxis];i=e.max-e.min;var t=(e.numberTicks,0===this.barPadding?1:0);this.barWidth="xaxis"==e.name||"x2axis"==e.name?(e._offsets.max-e._offsets.min)/i-this.barPadding+t:this.fill?(e._offsets.min-e._offsets.max)/i-this.barPadding+t:(e._offsets.min-e._offsets.max)/i},i.jqplot.PyramidRenderer.prototype.draw=function(e,t,s){{var h,r=i.extend({},s),l=void 0!=r.shadow?r.shadow:this.shadow,a=void 0!=r.showLine?r.showLine:this.showLine;void 0!=r.fill?r.fill:this.fill,this._xaxis.series_u2p,this._yaxis.series_u2p}this._dataColors=[],this._barPoints=[],null==this.renderer.options.barWidth&&this.renderer.setBarWidth.call(this);var n,o,d=[];if(a){var g=new i.jqplot.ColorGenerator(this.negativeSeriesColors),p=new i.jqplot.ColorGenerator(this.seriesColors),u=g.get(this.index);this.useNegativeColors||(u=r.fillStyle);for(var f,y=r.fillStyle,x=this._xaxis.series_u2p(this._xnudge),_=this._yaxis.series_u2p(this._yaxis.min),c=this._yaxis.series_u2p(this._yaxis.max),v=this.barWidth,m=v/2,d=[],R=this.offsetBars?m:0,h=0,C=t.length;C>h;h++)if(null!=this.data[h][0])if(f=t[h][1],this._plotData[h][1]<0?this.varyBarColor&&!this._stack&&(r.fillStyle=this.useNegativeColors?g.next():p.next()):r.fillStyle=this.varyBarColor&&!this._stack?p.next():y,this.fill){this._plotData[h][1]>=0?(n=t[h][0]-x,o=this.barWidth,d=[x,f-m-R,n,o]):(n=x-t[h][0],o=this.barWidth,d=[t[h][0],f-m-R,n,o]),this._barPoints.push([[d[0],d[1]+o],[d[0],d[1]],[d[0]+n,d[1]],[d[0]+n,d[1]+o]]),l&&this.renderer.shadowRenderer.draw(e,d);var j=r.fillStyle||this.color;this._dataColors.push(j),this.renderer.shapeRenderer.draw(e,d,r)}else if(0===h)d=[[x,_],[t[h][0],_],[t[h][0],t[h][1]-m-R]];else if(C-1>h)d=d.concat([[t[h-1][0],t[h-1][1]-m-R],[t[h][0],t[h][1]+m-R],[t[h][0],t[h][1]-m-R]]);else{d=d.concat([[t[h-1][0],t[h-1][1]-m-R],[t[h][0],t[h][1]+m-R],[t[h][0],c],[x,c]]),l&&this.renderer.shadowRenderer.draw(e,d);var j=r.fillStyle||this.color;this._dataColors.push(j),this.renderer.shapeRenderer.draw(e,d,r)}}if(0==this.highlightColors.length)this.highlightColors=i.jqplot.computeHighlightColors(this._dataColors);else if("string"==typeof this.highlightColors){this.highlightColors=[];for(var h=0;h<this._dataColors.length;h++)this.highlightColors.push(this.highlightColors)}},i.jqplot.preInitHooks.push(e)}(jQuery);