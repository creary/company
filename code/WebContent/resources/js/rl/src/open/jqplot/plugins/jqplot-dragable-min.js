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
!function(e){function r(){e.jqplot.GenericCanvas.call(this),this.isDragging=!1,this.isOver=!1,this._neighbor,this._cursors=[]}function a(r,a){var s=r.series[a.seriesIndex],i=s.plugins.dragable,t=s.markerRenderer,n=i.markerRenderer;if(n.style=t.style,n.lineWidth=t.lineWidth+2.5,n.size=t.size+5,!i.color){var o=e.jqplot.getColorComponents(t.color),g=[o[0],o[1],o[2]],l=o[3]>=.6?.6*o[3]:o[3]*(2-o[3]);i.color="rgba("+g[0]+","+g[1]+","+g[2]+","+l+")"}n.color=i.color,n.init();var p=a.pointIndex>0?a.pointIndex-1:0,d=a.pointIndex+2;i._gridData=s.gridData.slice(p,d)}function s(e,r,a,s,i){if(i.plugins.dragable.dragCanvas.isDragging){var t=i.plugins.dragable.dragCanvas,n=t._neighbor,o=i.series[n.seriesIndex],g=o.plugins.dragable,l=(o.gridData,"y"==g.constrainTo?n.gridData[0]:r.x),p="x"==g.constrainTo?n.gridData[1]:r.y,d=o._xaxis.series_p2u(l),h=o._yaxis.series_p2u(p),u=t._ctx;u.clearRect(0,0,u.canvas.width,u.canvas.height),n.pointIndex>0?g._gridData[1]=[l,p]:g._gridData[0]=[l,p],i.series[n.seriesIndex].draw(t._ctx,{gridData:g._gridData,shadow:!1,preventJqPlotSeriesDrawTrigger:!0,color:g.color,markerOptions:{color:g.color,shadow:!1},trendline:{show:!1}}),i.target.trigger("jqplotSeriesPointChange",[n.seriesIndex,n.pointIndex,[d,h],[l,p]])}else if(null!=s){var c=i.series[s.seriesIndex];if(c.isDragable){var t=i.plugins.dragable.dragCanvas;t.isOver||(t._cursors.push(e.target.style.cursor),e.target.style.cursor="pointer"),t.isOver=!0}}else if(null==s){var t=i.plugins.dragable.dragCanvas;t.isOver&&(e.target.style.cursor=t._cursors.pop(),t.isOver=!1)}}function i(e,r,s,i,t){var n=t.plugins.dragable.dragCanvas;if(n._cursors.push(e.target.style.cursor),null!=i){var o=t.series[i.seriesIndex],g=o.plugins.dragable;o.isDragable&&!n.isDragging&&(n._neighbor=i,n.isDragging=!0,a(t,i),g.markerRenderer.draw(o.gridData[i.pointIndex][0],o.gridData[i.pointIndex][1],n._ctx),e.target.style.cursor="move",t.target.trigger("jqplotDragStart",[i.seriesIndex,i.pointIndex,r,s]))}else{var l=n._ctx;l.clearRect(0,0,l.canvas.width,l.canvas.height),n.isDragging=!1}}function t(e,r,a,s,i){if(i.plugins.dragable.dragCanvas.isDragging){var t=i.plugins.dragable.dragCanvas,n=t._ctx;n.clearRect(0,0,n.canvas.width,n.canvas.height),t.isDragging=!1;var o=t._neighbor,g=i.series[o.seriesIndex],l=g.plugins.dragable,p="y"==l.constrainTo?o.data[0]:a[g.xaxis],d="x"==l.constrainTo?o.data[1]:a[g.yaxis];g.data[o.pointIndex][0]=p,g.data[o.pointIndex][1]=d,i.drawSeries({preventJqPlotSeriesDrawTrigger:!0},o.seriesIndex),t._neighbor=null,e.target.style.cursor=t._cursors.pop(),i.target.trigger("jqplotDragStop",[r,a])}}e.jqplot.Dragable=function(r){this.markerRenderer=new e.jqplot.MarkerRenderer({shadow:!1}),this.shapeRenderer=new e.jqplot.ShapeRenderer,this.isDragging=!1,this.isOver=!1,this._ctx,this._elem,this._point,this._gridData,this.color,this.constrainTo="none",e.extend(!0,this,r)},r.prototype=new e.jqplot.GenericCanvas,r.prototype.constructor=r,e.jqplot.Dragable.parseOptions=function(r,a){var s=a||{};this.plugins.dragable=new e.jqplot.Dragable(s.dragable),this.isDragable=e.jqplot.config.enablePlugins},e.jqplot.Dragable.postPlotDraw=function(){this.plugins.dragable&&this.plugins.dragable.highlightCanvas&&(this.plugins.dragable.highlightCanvas.resetCanvas(),this.plugins.dragable.highlightCanvas=null),this.plugins.dragable={previousCursor:"auto",isOver:!1},this.plugins.dragable.dragCanvas=new r,this.eventCanvas._elem.before(this.plugins.dragable.dragCanvas.createElement(this._gridPadding,"jqplot-dragable-canvas",this._plotDimensions,this));this.plugins.dragable.dragCanvas.setContext()},e.jqplot.preParseSeriesOptionsHooks.push(e.jqplot.Dragable.parseOptions),e.jqplot.postDrawHooks.push(e.jqplot.Dragable.postPlotDraw),e.jqplot.eventListenerHooks.push(["jqplotMouseMove",s]),e.jqplot.eventListenerHooks.push(["jqplotMouseDown",i]),e.jqplot.eventListenerHooks.push(["jqplotMouseUp",t])}(jQuery);