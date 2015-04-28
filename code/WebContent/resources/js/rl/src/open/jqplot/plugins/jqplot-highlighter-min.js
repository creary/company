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
!function(t){function i(i,e){var o=i.plugins.highlighter,r=i.series[e.seriesIndex],s=r.markerRenderer,l=o.markerRenderer;l.style=s.style,l.lineWidth=s.lineWidth+o.lineWidthAdjust,l.size=s.size+o.sizeAdjust;var h=t.jqplot.getColorComponents(s.color),a=[h[0],h[1],h[2]],n=h[3]>=.6?.6*h[3]:h[3]*(2-h[3]);l.color="rgba("+a[0]+","+a[1]+","+a[2]+","+n+")",l.init(),l.draw(r.gridData[e.pointIndex][0],r.gridData[e.pointIndex][1],o.highlightCanvas._ctx)}function e(i,e,o){var h=i.plugins.highlighter,a=h._tooltipElem,n=e.highlighter||{},g=t.extend(!0,{},h,n);if(g.useAxesFormatters){for(var p,d=e._xaxis._ticks[0].formatter,f=e._yaxis._ticks[0].formatter,u=e._xaxis._ticks[0].formatString,c=e._yaxis._ticks[0].formatString,v=d(u,o.data[0]),x=[],m=1;m<g.yvalues+1;m++)x.push(f(c,o.data[m]));if("string"==typeof g.formatString)switch(g.tooltipAxes){case"both":case"xy":x.unshift(v),x.unshift(g.formatString),p=t.jqplot.sprintf.apply(t.jqplot.sprintf,x);break;case"yx":x.push(v),x.unshift(g.formatString),p=t.jqplot.sprintf.apply(t.jqplot.sprintf,x);break;case"x":p=t.jqplot.sprintf.apply(t.jqplot.sprintf,[g.formatString,v]);break;case"y":x.unshift(g.formatString),p=t.jqplot.sprintf.apply(t.jqplot.sprintf,x);break;default:x.unshift(v),x.unshift(g.formatString),p=t.jqplot.sprintf.apply(t.jqplot.sprintf,x)}else switch(g.tooltipAxes){case"both":case"xy":p=v;for(var m=0;m<x.length;m++)p+=g.tooltipSeparator+x[m];break;case"yx":p="";for(var m=0;m<x.length;m++)p+=x[m]+g.tooltipSeparator;p+=v;break;case"x":p=v;break;case"y":p=x.join(g.tooltipSeparator);break;default:p=v;for(var m=0;m<x.length;m++)p+=g.tooltipSeparator+x[m]}}else{var p;"string"==typeof g.formatString?p=t.jqplot.sprintf.apply(t.jqplot.sprintf,[g.formatString].concat(o.data)):"both"==g.tooltipAxes||"xy"==g.tooltipAxes?p=t.jqplot.sprintf(g.tooltipFormatString,o.data[0])+g.tooltipSeparator+t.jqplot.sprintf(g.tooltipFormatString,o.data[1]):"yx"==g.tooltipAxes?p=t.jqplot.sprintf(g.tooltipFormatString,o.data[1])+g.tooltipSeparator+t.jqplot.sprintf(g.tooltipFormatString,o.data[0]):"x"==g.tooltipAxes?p=t.jqplot.sprintf(g.tooltipFormatString,o.data[0]):"y"==g.tooltipAxes&&(p=t.jqplot.sprintf(g.tooltipFormatString,o.data[1]))}t.isFunction(g.tooltipContentEditor)&&(p=g.tooltipContentEditor(p,o.seriesIndex,o.pointIndex,i)),a.html(p);var j={x:o.gridData[0],y:o.gridData[1]},_=0,w=.707;1==e.markerRenderer.show&&(_=(e.markerRenderer.size+g.sizeAdjust)/2);var q=r;switch(e.fillToZero&&e.fill&&o.data[1]<0&&(q=l),q[s[g.tooltipLocation]]){case"nw":var y=j.x+i._gridPadding.left-a.outerWidth(!0)-g.tooltipOffset-w*_,S=j.y+i._gridPadding.top-g.tooltipOffset-a.outerHeight(!0)-w*_;break;case"n":var y=j.x+i._gridPadding.left-a.outerWidth(!0)/2,S=j.y+i._gridPadding.top-g.tooltipOffset-a.outerHeight(!0)-_;break;case"ne":var y=j.x+i._gridPadding.left+g.tooltipOffset+w*_,S=j.y+i._gridPadding.top-g.tooltipOffset-a.outerHeight(!0)-w*_;break;case"e":var y=j.x+i._gridPadding.left+g.tooltipOffset+_,S=j.y+i._gridPadding.top-a.outerHeight(!0)/2;break;case"se":var y=j.x+i._gridPadding.left+g.tooltipOffset+w*_,S=j.y+i._gridPadding.top+g.tooltipOffset+w*_;break;case"s":var y=j.x+i._gridPadding.left-a.outerWidth(!0)/2,S=j.y+i._gridPadding.top+g.tooltipOffset+_;break;case"sw":var y=j.x+i._gridPadding.left-a.outerWidth(!0)-g.tooltipOffset-w*_,S=j.y+i._gridPadding.top+g.tooltipOffset+w*_;break;case"w":var y=j.x+i._gridPadding.left-a.outerWidth(!0)-g.tooltipOffset-_,S=j.y+i._gridPadding.top-a.outerHeight(!0)/2;break;default:var y=j.x+i._gridPadding.left-a.outerWidth(!0)-g.tooltipOffset-w*_,S=j.y+i._gridPadding.top-g.tooltipOffset-a.outerHeight(!0)-w*_}a.css("left",y),a.css("top",S),g.fadeTooltip?a.stop(!0,!0).fadeIn(g.tooltipFadeSpeed):a.show(),a=null}function o(t,o,r,s,l){var h=l.plugins.highlighter,a=l.plugins.cursor;if(h.show)if(null==s&&h.isHighlighting){var n=jQuery.Event("jqplotHighlighterUnhighlight");l.target.trigger(n);var g=h.highlightCanvas._ctx;g.clearRect(0,0,g.canvas.width,g.canvas.height),h.fadeTooltip?h._tooltipElem.fadeOut(h.tooltipFadeSpeed):h._tooltipElem.hide(),h.bringSeriesToFront&&l.restorePreviousSeriesOrder(),h.isHighlighting=!1,h.currentNeighbor=null,g=null}else if(null!=s&&l.series[s.seriesIndex].showHighlight&&!h.isHighlighting){var n=jQuery.Event("jqplotHighlighterHighlight");n.which=t.which,n.pageX=t.pageX,n.pageY=t.pageY;var p=[s.seriesIndex,s.pointIndex,s.data,l];l.target.trigger(n,p),h.isHighlighting=!0,h.currentNeighbor=s,h.showMarker&&i(l,s),!h.showTooltip||a&&a._zoom.started||e(l,l.series[s.seriesIndex],s),h.bringSeriesToFront&&l.moveSeriesToFront(s.seriesIndex)}else if(null!=s&&h.isHighlighting&&h.currentNeighbor!=s&&l.series[s.seriesIndex].showHighlight){var g=h.highlightCanvas._ctx;g.clearRect(0,0,g.canvas.width,g.canvas.height),h.isHighlighting=!0,h.currentNeighbor=s,h.showMarker&&i(l,s),!h.showTooltip||a&&a._zoom.started||e(l,l.series[s.seriesIndex],s),h.bringSeriesToFront&&l.moveSeriesToFront(s.seriesIndex)}}t.jqplot.eventListenerHooks.push(["jqplotMouseMove",o]),t.jqplot.Highlighter=function(i){this.show=t.jqplot.config.enablePlugins,this.markerRenderer=new t.jqplot.MarkerRenderer({shadow:!1}),this.showMarker=!0,this.lineWidthAdjust=2.5,this.sizeAdjust=5,this.showTooltip=!0,this.tooltipLocation="nw",this.fadeTooltip=!0,this.tooltipFadeSpeed="fast",this.tooltipOffset=2,this.tooltipAxes="both",this.tooltipSeparator=", ",this.tooltipContentEditor=null,this.useAxesFormatters=!0,this.tooltipFormatString="%.5P",this.formatString=null,this.yvalues=1,this.bringSeriesToFront=!1,this._tooltipElem,this.isHighlighting=!1,this.currentNeighbor=null,t.extend(!0,this,i)};var r=["nw","n","ne","e","se","s","sw","w"],s={nw:0,n:1,ne:2,e:3,se:4,s:5,sw:6,w:7},l=["se","s","sw","w","nw","n","ne","e"];t.jqplot.Highlighter.init=function(i,e,o){var r=o||{};this.plugins.highlighter=new t.jqplot.Highlighter(r.highlighter)},t.jqplot.Highlighter.parseOptions=function(){this.showHighlight=!0},t.jqplot.Highlighter.postPlotDraw=function(){this.plugins.highlighter&&this.plugins.highlighter.highlightCanvas&&(this.plugins.highlighter.highlightCanvas.resetCanvas(),this.plugins.highlighter.highlightCanvas=null),this.plugins.highlighter&&this.plugins.highlighter._tooltipElem&&(this.plugins.highlighter._tooltipElem.emptyForce(),this.plugins.highlighter._tooltipElem=null),this.plugins.highlighter.highlightCanvas=new t.jqplot.GenericCanvas,this.eventCanvas._elem.before(this.plugins.highlighter.highlightCanvas.createElement(this._gridPadding,"jqplot-highlight-canvas",this._plotDimensions,this)),this.plugins.highlighter.highlightCanvas.setContext();var i=document.createElement("div");this.plugins.highlighter._tooltipElem=t(i),i=null,this.plugins.highlighter._tooltipElem.addClass("jqplot-highlighter-tooltip"),this.plugins.highlighter._tooltipElem.css({position:"absolute",display:"none"}),this.eventCanvas._elem.before(this.plugins.highlighter._tooltipElem)},t.jqplot.preInitHooks.push(t.jqplot.Highlighter.init),t.jqplot.preParseSeriesOptionsHooks.push(t.jqplot.Highlighter.parseOptions),t.jqplot.postDrawHooks.push(t.jqplot.Highlighter.postPlotDraw)}(jQuery);