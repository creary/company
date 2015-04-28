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
!function(t){t.jqplot.CanvasAxisLabelRenderer=function(e){this.angle=0,this.axis,this.show=!0,this.showLabel=!0,this.label="",this.fontFamily='"Trebuchet MS", Arial, Helvetica, sans-serif',this.fontSize="11pt",this.fontWeight="normal",this.fontStretch=1,this.textColor="#666666",this.enableFontSupport=!0,this.pt2px=null,this._elem,this._ctx,this._plotWidth,this._plotHeight,this._plotDimensions={height:null,width:null},t.extend(!0,this,e),null==e.angle&&"xaxis"!=this.axis&&"x2axis"!=this.axis&&(this.angle=-90);var i={fontSize:this.fontSize,fontWeight:this.fontWeight,fontStretch:this.fontStretch,fillStyle:this.textColor,angle:this.getAngleRad(),fontFamily:this.fontFamily};this.pt2px&&(i.pt2px=this.pt2px),this._textRenderer=this.enableFontSupport?t.jqplot.support_canvas_text()?new t.jqplot.CanvasFontRenderer(i):new t.jqplot.CanvasTextRenderer(i):new t.jqplot.CanvasTextRenderer(i)},t.jqplot.CanvasAxisLabelRenderer.prototype.init=function(e){t.extend(!0,this,e),this._textRenderer.init({fontSize:this.fontSize,fontWeight:this.fontWeight,fontStretch:this.fontStretch,fillStyle:this.textColor,angle:this.getAngleRad(),fontFamily:this.fontFamily})},t.jqplot.CanvasAxisLabelRenderer.prototype.getWidth=function(t){if(this._elem)return this._elem.outerWidth(!0);var e=this._textRenderer,i=e.getWidth(t),n=e.getHeight(t),s=Math.abs(Math.sin(e.angle)*n)+Math.abs(Math.cos(e.angle)*i);return s},t.jqplot.CanvasAxisLabelRenderer.prototype.getHeight=function(t){if(this._elem)return this._elem.outerHeight(!0);var e=this._textRenderer,i=e.getWidth(t),n=e.getHeight(t),s=Math.abs(Math.cos(e.angle)*n)+Math.abs(Math.sin(e.angle)*i);return s},t.jqplot.CanvasAxisLabelRenderer.prototype.getAngleRad=function(){var t=this.angle*Math.PI/180;return t},t.jqplot.CanvasAxisLabelRenderer.prototype.draw=function(e,i){this._elem&&(t.jqplot.use_excanvas&&void 0!==window.G_vmlCanvasManager.uninitElement&&window.G_vmlCanvasManager.uninitElement(this._elem.get(0)),this._elem.emptyForce(),this._elem=null);var n=i.canvasManager.getCanvas();this._textRenderer.setText(this.label,e);var s=this.getWidth(e),a=this.getHeight(e);return n.width=s,n.height=a,n.style.width=s,n.style.height=a,n=i.canvasManager.initCanvas(n),this._elem=t(n),this._elem.css({position:"absolute"}),this._elem.addClass("jqplot-"+this.axis+"-label"),n=null,this._elem},t.jqplot.CanvasAxisLabelRenderer.prototype.pack=function(){this._textRenderer.draw(this._elem.get(0).getContext("2d"),this.label)}}(jQuery);