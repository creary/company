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
!function(e){e.jqplot.EnhancedLegendRenderer=function(){e.jqplot.TableLegendRenderer.call(this)},e.jqplot.EnhancedLegendRenderer.prototype=new e.jqplot.TableLegendRenderer,e.jqplot.EnhancedLegendRenderer.prototype.constructor=e.jqplot.EnhancedLegendRenderer,e.jqplot.EnhancedLegendRenderer.prototype.init=function(s){this.numberRows=null,this.numberColumns=null,this.seriesToggle="normal",this.seriesToggleReplot=!1,this.disableIEFading=!0,e.extend(!0,this,s),this.seriesToggle&&e.jqplot.postDrawHooks.push(t)},e.jqplot.EnhancedLegendRenderer.prototype.draw=function(t,n){if(this.show){var l,o=this._series,i="position:absolute;";i+=this.background?"background:"+this.background+";":"",i+=this.border?"border:"+this.border+";":"",i+=this.fontSize?"font-size:"+this.fontSize+";":"",i+=this.fontFamily?"font-family:"+this.fontFamily+";":"",i+=this.textColor?"color:"+this.textColor+";":"",i+=null!=this.marginTop?"margin-top:"+this.marginTop+";":"",i+=null!=this.marginBottom?"margin-bottom:"+this.marginBottom+";":"",i+=null!=this.marginLeft?"margin-left:"+this.marginLeft+";":"",i+=null!=this.marginRight?"margin-right:"+this.marginRight+";":"",this._elem=e('<table class="jqplot-table-legend" style="'+i+'"></table>'),this.seriesToggle&&this._elem.css("z-index","3");var d,a,r=!1,h=!1;this.numberRows?(d=this.numberRows,a=this.numberColumns?this.numberColumns:Math.ceil(o.length/d)):this.numberColumns?(a=this.numberColumns,d=Math.ceil(o.length/this.numberColumns)):(d=o.length,a=1);var p,g,c,m,u,b,f,w,q,j=0;for(p=o.length-1;p>=0;p--)(1==a&&o[p]._stack||o[p].renderer.constructor==e.jqplot.BezierCurveRenderer)&&(h=!0);for(p=0;d>p;p++){for(c=e(document.createElement("tr")),c.addClass("jqplot-table-legend"),h?c.prependTo(this._elem):c.appendTo(this._elem),g=0;a>g;g++){if(j<o.length&&(o[j].show||o[j].showLabel)&&(l=o[j],b=this.labels[j]||l.label.toString())){var C=l.color;if(r=h?p==d-1?!1:!0:p>0?!0:!1,f=r?this.rowSpacing:"0",m=e(document.createElement("td")),m.addClass("jqplot-table-legend jqplot-table-legend-swatch"),m.css({textAlign:"center",paddingTop:f}),w=e(document.createElement("div")),w.addClass("jqplot-table-legend-swatch-outline"),q=e(document.createElement("div")),q.addClass("jqplot-table-legend-swatch"),q.css({backgroundColor:C,borderColor:C}),m.append(w.append(q)),u=e(document.createElement("td")),u.addClass("jqplot-table-legend jqplot-table-legend-label"),u.css("paddingTop",f),this.escapeHtml?u.text(b):u.html(b),h?(this.showLabels&&u.prependTo(c),this.showSwatches&&m.prependTo(c)):(this.showSwatches&&m.appendTo(c),this.showLabels&&u.appendTo(c)),this.seriesToggle){var v;("string"==typeof this.seriesToggle||"number"==typeof this.seriesToggle)&&(e.jqplot.use_excanvas&&this.disableIEFading||(v=this.seriesToggle)),this.showSwatches&&(m.bind("click",{series:l,speed:v,plot:n,replot:this.seriesToggleReplot},s),m.addClass("jqplot-seriesToggle")),this.showLabels&&(u.bind("click",{series:l,speed:v,plot:n,replot:this.seriesToggleReplot},s),u.addClass("jqplot-seriesToggle")),!l.show&&l.showLabel&&(m.addClass("jqplot-series-hidden"),u.addClass("jqplot-series-hidden"))}r=!0}j++}m=u=w=q=null}}return this._elem};var s=function(s){var t=s.data,n=t.series,l=t.replot,o=t.plot,i=t.speed,d=n.index,a=!1;(n.canvas._elem.is(":hidden")||!n.show)&&(a=!0);var r=function(){if(l){var s={};if(e.isPlainObject(l)&&e.extend(!0,s,l),o.replot(s),a&&i){var t=o.series[d];t.shadowCanvas._elem&&t.shadowCanvas._elem.hide().fadeIn(i),t.canvas._elem.hide().fadeIn(i),t.canvas._elem.nextAll(".jqplot-point-label.jqplot-series-"+t.index).hide().fadeIn(i)}}else{var t=o.series[d];t.canvas._elem.is(":hidden")||!t.show?(("undefined"==typeof o.options.legend.showSwatches||o.options.legend.showSwatches===!0)&&o.legend._elem.find("td").eq(2*d).addClass("jqplot-series-hidden"),("undefined"==typeof o.options.legend.showLabels||o.options.legend.showLabels===!0)&&o.legend._elem.find("td").eq(2*d+1).addClass("jqplot-series-hidden")):(("undefined"==typeof o.options.legend.showSwatches||o.options.legend.showSwatches===!0)&&o.legend._elem.find("td").eq(2*d).removeClass("jqplot-series-hidden"),("undefined"==typeof o.options.legend.showLabels||o.options.legend.showLabels===!0)&&o.legend._elem.find("td").eq(2*d+1).removeClass("jqplot-series-hidden"))}};n.toggleDisplay(s,r)},t=function(){if(this.legend.renderer.constructor==e.jqplot.EnhancedLegendRenderer&&this.legend.seriesToggle){var s=this.legend._elem.detach();this.eventCanvas._elem.after(s)}}}(jQuery);