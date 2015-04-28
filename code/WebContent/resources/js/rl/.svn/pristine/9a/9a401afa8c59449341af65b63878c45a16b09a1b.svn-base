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
!function(t){function e(e,s,i){i=i||{},i.axesDefaults=i.axesDefaults||{},i.legend=i.legend||{},i.seriesDefaults=i.seriesDefaults||{};var o=!1;if(i.seriesDefaults.renderer==t.jqplot.MekkoRenderer)o=!0;else if(i.series)for(var h=0;h<i.series.length;h++)i.series[h].renderer==t.jqplot.MekkoRenderer&&(o=!0);o&&(i.axesDefaults.renderer=t.jqplot.MekkoAxisRenderer,i.legend.renderer=t.jqplot.MekkoLegendRenderer,i.legend.preDraw=!0)}t.jqplot.MekkoRenderer=function(){this.shapeRenderer=new t.jqplot.ShapeRenderer,this.borderColor=null,this.showBorders=!0},t.jqplot.MekkoRenderer.prototype.init=function(e,s){this.fill=!1,this.fillRect=!0,this.strokeRect=!0,this.shadow=!1,this._xwidth=0,this._xstart=0,t.extend(!0,this.renderer,e);var i={lineJoin:"miter",lineCap:"butt",isarc:!1,fillRect:this.fillRect,strokeRect:this.strokeRect};this.renderer.shapeRenderer.init(i),s.axes.x2axis._series.push(this),this._type="mekko"},t.jqplot.MekkoRenderer.prototype.setGridData=function(t){var e=this._xaxis.series_u2p,s=(this._yaxis.series_u2p,this._plotData);this.gridData=[],this._xwidth=e(this._sumy)-e(0),this.index>0&&(this._xstart=t.series[this.index-1]._xstart+t.series[this.index-1]._xwidth);for(var i,o,h=this.canvas.getHeight(),r=0,l=0;l<s.length;l++)null!=s[l]&&(r+=s[l][1],i=h-r/this._sumy*h,o=s[l][1]/this._sumy*h,this.gridData.push([this._xstart,i,this._xwidth,o]))},t.jqplot.MekkoRenderer.prototype.makeGridData=function(t){for(var e,s,i=(this._xaxis.series_u2p,this.canvas.getHeight()),o=0,h=[],r=0;r<t.length;r++)null!=t[r]&&(o+=t[r][1],e=i-o/this._sumy*i,s=t[r][1]/this._sumy*i,h.push([this._xstart,e,this._xwidth,s]));return h},t.jqplot.MekkoRenderer.prototype.draw=function(e,s,i){var o,h=void 0!=i?i:{},r=void 0!=h.showLine?h.showLine:this.showLine,l=new t.jqplot.ColorGenerator(this.seriesColors);if(e.save(),s.length&&r)for(o=0;o<s.length;o++)h.fillStyle=l.next(),h.strokeStyle=this.renderer.showBorders?this.renderer.borderColor:h.fillStyle,this.renderer.shapeRenderer.draw(e,s[o],h);e.restore()},t.jqplot.MekkoRenderer.prototype.drawShadow=function(){},t.jqplot.MekkoLegendRenderer=function(){},t.jqplot.MekkoLegendRenderer.prototype.init=function(e){this.numberRows=null,this.numberColumns=null,this.placement="outside",t.extend(!0,this,e)},t.jqplot.MekkoLegendRenderer.prototype.draw=function(){if(this.show){var e=this._series,s="position:absolute;";s+=this.background?"background:"+this.background+";":"",s+=this.border?"border:"+this.border+";":"",s+=this.fontSize?"font-size:"+this.fontSize+";":"",s+=this.fontFamily?"font-family:"+this.fontFamily+";":"",s+=this.textColor?"color:"+this.textColor+";":"",this._elem=t('<table class="jqplot-table-legend" style="'+s+'"></table>');var i,o,h=!1,r=!0,l=e[0],n=new t.jqplot.ColorGenerator(l.seriesColors);if(l.show){var a=l.data;this.numberRows?(i=this.numberRows,o=this.numberColumns?this.numberColumns:Math.ceil(a.length/i)):this.numberColumns?(o=this.numberColumns,i=Math.ceil(a.length/this.numberColumns)):(i=a.length,o=1);var f,p,d,m,_,c,g,b,u=0;for(f=0;i>f;f++)for(d=r?t('<tr class="jqplot-table-legend"></tr>').prependTo(this._elem):t('<tr class="jqplot-table-legend"></tr>').appendTo(this._elem),p=0;o>p;p++)u<a.length&&(c=this.labels[u]||a[u][0].toString(),b=n.next(),h=r?f==i-1?!1:!0:f>0?!0:!1,g=h?this.rowSpacing:"0",m=t('<td class="jqplot-table-legend" style="text-align:center;padding-top:'+g+';"><div><div class="jqplot-table-legend-swatch" style="border-color:'+b+';"></div></div></td>'),_=t('<td class="jqplot-table-legend" style="padding-top:'+g+';"></td>'),this.escapeHtml?_.text(c):_.html(c),r?(_.prependTo(d),m.prependTo(d)):(m.appendTo(d),_.appendTo(d)),h=!0),u++;d=null,m=null,_=null}}return this._elem},t.jqplot.MekkoLegendRenderer.prototype.pack=function(t){if(this.show){var e={_top:t.top,_left:t.left,_right:t.right,_bottom:this._plotDimensions.height-t.bottom};if("insideGrid"==this.placement)switch(this.location){case"nw":var s=e._left+this.xoffset,i=e._top+this.yoffset;this._elem.css("left",s),this._elem.css("top",i);break;case"n":var s=(t.left+(this._plotDimensions.width-t.right))/2-this.getWidth()/2,i=e._top+this.yoffset;this._elem.css("left",s),this._elem.css("top",i);break;case"ne":var s=t.right+this.xoffset,i=e._top+this.yoffset;this._elem.css({right:s,top:i});break;case"e":var s=t.right+this.xoffset,i=(t.top+(this._plotDimensions.height-t.bottom))/2-this.getHeight()/2;this._elem.css({right:s,top:i});break;case"se":var s=t.right+this.xoffset,i=t.bottom+this.yoffset;this._elem.css({right:s,bottom:i});break;case"s":var s=(t.left+(this._plotDimensions.width-t.right))/2-this.getWidth()/2,i=t.bottom+this.yoffset;this._elem.css({left:s,bottom:i});break;case"sw":var s=e._left+this.xoffset,i=t.bottom+this.yoffset;this._elem.css({left:s,bottom:i});break;case"w":var s=e._left+this.xoffset,i=(t.top+(this._plotDimensions.height-t.bottom))/2-this.getHeight()/2;this._elem.css({left:s,top:i});break;default:var s=e._right-this.xoffset,i=e._bottom+this.yoffset;this._elem.css({right:s,bottom:i})}else switch(this.location){case"nw":var s=this._plotDimensions.width-e._left+this.xoffset,i=e._top+this.yoffset;this._elem.css("right",s),this._elem.css("top",i);break;case"n":var s=(t.left+(this._plotDimensions.width-t.right))/2-this.getWidth()/2,i=this._plotDimensions.height-e._top+this.yoffset;this._elem.css("left",s),this._elem.css("bottom",i);break;case"ne":var s=this._plotDimensions.width-t.right+this.xoffset,i=e._top+this.yoffset;this._elem.css({left:s,top:i});break;case"e":var s=this._plotDimensions.width-t.right+this.xoffset,i=(t.top+(this._plotDimensions.height-t.bottom))/2-this.getHeight()/2;this._elem.css({left:s,top:i});break;case"se":var s=this._plotDimensions.width-t.right+this.xoffset,i=t.bottom+this.yoffset;this._elem.css({left:s,bottom:i});break;case"s":var s=(t.left+(this._plotDimensions.width-t.right))/2-this.getWidth()/2,i=this._plotDimensions.height-t.bottom+this.yoffset;this._elem.css({left:s,top:i});break;case"sw":var s=this._plotDimensions.width-e._left+this.xoffset,i=t.bottom+this.yoffset;this._elem.css({right:s,bottom:i});break;case"w":var s=this._plotDimensions.width-e._left+this.xoffset,i=(t.top+(this._plotDimensions.height-t.bottom))/2-this.getHeight()/2;this._elem.css({right:s,top:i});break;default:var s=e._right-this.xoffset,i=e._bottom+this.yoffset;this._elem.css({right:s,bottom:i})}}},t.jqplot.preInitHooks.push(e)}(jQuery);