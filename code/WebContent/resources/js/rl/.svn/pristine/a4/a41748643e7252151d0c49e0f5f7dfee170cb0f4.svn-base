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
!function(e){function n(e){var n=null;if(e.trendline&&e.trendline.show){var t=e.trendline.label.toString();t&&(n={label:t,color:e.trendline.color})}return n}function t(n,t,i,r){!this._type||"line"!==this._type&&"bar"!=this._type||(this.trendline=new e.jqplot.Trendline,r=r||{},e.extend(!0,this.trendline,{color:this.color},i.trendline,r.trendline),this.trendline.renderer.init.call(this.trendline,null))}function i(n,t){if(t=e.extend(!0,{},this.trendline,t),this.trendline&&t.show){var i,r=t.data||this.data;i=h(r,this.trendline.type);var l=t.gridData||this.renderer.makeGridData.call(this,i.data);this.trendline.renderer.draw.call(this.trendline,n,l,{showLine:!0,shadow:this.trendline.shadow})}}function r(e,n,t){var i,r,l=null==t?"linear":t,s=e.length,h=0,o=0,a=0,d=0,p=0,u=[],f=[];if("linear"==l)f=e,u=n;else if("exp"==l||"exponential"==l)for(var c=0;c<n.length;c++)n[c]<=0?s--:(f.push(e[c]),u.push(Math.log(n[c])));for(var c=0;s>c;c++)h+=f[c],o+=u[c],d+=f[c]*u[c],a+=f[c]*f[c],p+=u[c]*u[c];return i=(s*d-h*o)/(s*a-h*h),r=(o-i*h)/s,[i,r]}function l(e,n){var t;return t=r(e,n,"linear"),[t[0],t[1]]}function s(e,n){var t,i=e,l=n;t=r(i,l,"exp");var s=Math.exp(t[0]),h=Math.exp(t[1]);return[s,h]}function h(e,n){var t,i,r=null==n?"linear":n,h=[],o=[],a=[];for(d=0;d<e.length;d++)null!=e[d]&&null!=e[d][0]&&null!=e[d][1]&&(h.push(e[d][0]),o.push(e[d][1]));if("linear"==r){t=l(h,o);for(var d=0;d<h.length;d++)i=t[0]*h[d]+t[1],a.push([h[d],i])}else if("exp"==r||"exponential"==r){t=s(h,o);for(var d=0;d<h.length;d++)i=t[1]*Math.pow(t[0],h[d]),a.push([h[d],i])}return{data:a,slope:t[0],intercept:t[1]}}e.jqplot.Trendline=function(){this.show=e.jqplot.config.enablePlugins,this.color="#666666",this.renderer=new e.jqplot.LineRenderer,this.rendererOptions={marker:{show:!1}},this.label="",this.type="linear",this.shadow=!0,this.markerRenderer={show:!1},this.lineWidth=1.5,this.shadowAngle=45,this.shadowOffset=1,this.shadowAlpha=.07,this.shadowDepth=3,this.isTrendline=!0},e.jqplot.postSeriesInitHooks.push(t),e.jqplot.postDrawSeriesHooks.push(i),e.jqplot.addLegendRowHooks.push(n)}(jQuery);