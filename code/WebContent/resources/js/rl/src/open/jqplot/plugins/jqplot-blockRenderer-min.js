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
!function(t){t.jqplot.BlockRenderer=function(){t.jqplot.LineRenderer.call(this)},t.jqplot.BlockRenderer.prototype=new t.jqplot.LineRenderer,t.jqplot.BlockRenderer.prototype.constructor=t.jqplot.BlockRenderer,t.jqplot.BlockRenderer.prototype.init=function(s){this.css={padding:"2px",border:"1px solid #999",textAlign:"center"},this.escapeHtml=!1,this.insertBreaks=!0,this.varyBlockColors=!1,t.extend(!0,this,s),this.css.backgroundColor?this.color=this.css.backgroundColor:this.css.background?this.color=this.css.background:this.varyBlockColors||(this.css.background=this.color),this.canvas=new t.jqplot.BlockCanvas,this.shadowCanvas=new t.jqplot.BlockCanvas,this.canvas._plotDimensions=this._plotDimensions,this.shadowCanvas._plotDimensions=this._plotDimensions,this._type="block",this.moveBlock=function(t,s,e,o){var i=this.canvas._elem.children(":eq("+t+")");this.data[t][0]=s,this.data[t][1]=e,this._plotData[t][0]=s,this._plotData[t][1]=e,this._stackData[t][0]=s,this._stackData[t][1]=e,this.gridData[t][0]=this._xaxis.series_u2p(s),this.gridData[t][1]=this._yaxis.series_u2p(e);var n=i.outerWidth(),a=i.outerHeight(),l=this.gridData[t][0]-n/2+"px",r=this.gridData[t][1]-a/2+"px";o?(parseInt(o,10)&&(o=parseInt(o,10)),i.animate({left:l,top:r},o)):i.css({left:l,top:r}),i=null}},t.jqplot.BlockRenderer.prototype.draw=function(s,e,o){this.plugins.pointLabels&&(this.plugins.pointLabels.show=!1);var i,n,a,e,l,r,h,p,c,d,u=new t.jqplot.ColorGenerator(this.seriesColors);for(this.canvas._elem.empty(),i=0;i<this.gridData.length;i++)a=this.data[i],e=this.gridData[i],l="",r={},"string"==typeof a[2]?l=a[2]:"object"==typeof a[2]&&(r=a[2]),"object"==typeof a[3]&&(r=a[3]),this.insertBreaks&&(l=l.replace(/ /g,"<br />")),r=t.extend(!0,{},this.css,r),n=t('<div style="position:absolute;margin-left:auto;margin-right:auto;"></div>'),this.canvas._elem.append(n),this.escapeHtml?n.text(l):n.html(l),delete r.position,delete r.marginRight,delete r.marginLeft,r.background||r.backgroundColor||r.backgroundImage||(r.background=u.next()),n.css(r),h=n.outerWidth(),p=n.outerHeight(),c=e[0]-h/2+"px",d=e[1]-p/2+"px",n.css({left:c,top:d}),n=null},t.jqplot.BlockCanvas=function(){t.jqplot.ElemContainer.call(this),this._ctx},t.jqplot.BlockCanvas.prototype=new t.jqplot.ElemContainer,t.jqplot.BlockCanvas.prototype.constructor=t.jqplot.BlockCanvas,t.jqplot.BlockCanvas.prototype.createElement=function(s,e,o){this._offsets=s;var i="jqplot-blockCanvas";void 0!=e&&(i=e);var n;n=this._elem?this._elem.get(0):document.createElement("div"),void 0!=o&&(this._plotDimensions=o);var a=this._plotDimensions.width-this._offsets.left-this._offsets.right+"px",l=this._plotDimensions.height-this._offsets.top-this._offsets.bottom+"px";return this._elem=t(n),this._elem.css({position:"absolute",width:a,height:l,left:this._offsets.left,top:this._offsets.top}),this._elem.addClass(i),this._elem},t.jqplot.BlockCanvas.prototype.setContext=function(){return this._ctx={canvas:{width:0,height:0},clearRect:function(){return null}},this._ctx}}(jQuery);