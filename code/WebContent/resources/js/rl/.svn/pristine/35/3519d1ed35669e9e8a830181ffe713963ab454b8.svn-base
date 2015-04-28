/**
 * jqPlot
 * Pure JavaScript plotting plugin using jQuery
 *
 * Version: 1.0.4
 * Revision: 1120
 *
 * Copyright (c) 2009-2012 Chris Leonello
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
 */
!function(t){t.jqplot.OHLCRenderer=function(){t.jqplot.LineRenderer.call(this),this.candleStick=!1,this.tickLength="auto",this.bodyWidth="auto",this.openColor=null,this.closeColor=null,this.wickColor=null,this.fillUpBody=!1,this.fillDownBody=!0,this.upBodyColor=null,this.downBodyColor=null,this.hlc=!1,this.lineWidth=1.5,this._tickLength,this._bodyWidth},t.jqplot.OHLCRenderer.prototype=new t.jqplot.LineRenderer,t.jqplot.OHLCRenderer.prototype.constructor=t.jqplot.OHLCRenderer,t.jqplot.OHLCRenderer.prototype.init=function(o){o=o||{},this.lineWidth=o.lineWidth||1.5,t.jqplot.LineRenderer.prototype.init.call(this,o),this._type="ohlc";var e=this._yaxis._dataBounds,r=this._plotData;if(r[0].length<5){this.renderer.hlc=!0;for(var i=0;i<r.length;i++)(r[i][2]<e.min||null==e.min)&&(e.min=r[i][2]),(r[i][1]>e.max||null==e.max)&&(e.max=r[i][1])}else for(var i=0;i<r.length;i++)(r[i][3]<e.min||null==e.min)&&(e.min=r[i][3]),(r[i][2]>e.max||null==e.max)&&(e.max=r[i][2])},t.jqplot.OHLCRenderer.prototype.draw=function(o,e,r){{var i,l,d,n,h,s,a,c,p,y=this.data,w=this._xaxis.min,u=this._xaxis.max,C=0,k=y.length,f=this._xaxis.series_u2p,x=this._yaxis.series_u2p,R=this.renderer,g=void 0!=r?r:{};void 0!=g.shadow?g.shadow:this.shadow,void 0!=g.fill?g.fill:this.fill,void 0!=g.fillAndStroke?g.fillAndStroke:this.fillAndStroke}if(R.bodyWidth=void 0!=g.bodyWidth?g.bodyWidth:R.bodyWidth,R.tickLength=void 0!=g.tickLength?g.tickLength:R.tickLength,o.save(),this.show){for(var _,L,v,m,B,i=0;i<y.length;i++)y[i][0]<w?C=i:y[i][0]<u&&(k=i+1);var b=this.gridData[k-1][0]-this.gridData[C][0],W=k-C;try{var j=Math.abs(this._xaxis.series_u2p(parseInt(this._xaxis._intervalStats[0].sortedIntervals[0].interval,10))-this._xaxis.series_u2p(0))}catch(q){var j=b/W}R.candleStick?R._bodyWidth="number"==typeof R.bodyWidth?R.bodyWidth:Math.min(20,j/1.65):R._tickLength="number"==typeof R.tickLength?R.tickLength:Math.min(10,j/3.5);for(var i=C;k>i;i++)_=f(y[i][0]),R.hlc?(L=null,v=x(y[i][1]),m=x(y[i][2]),B=x(y[i][3])):(L=x(y[i][1]),v=x(y[i][2]),m=x(y[i][3]),B=x(y[i][4])),p={},R.candleStick&&!R.hlc?(s=R._bodyWidth,a=_-s/2,L>B?(R.wickColor?p.color=R.wickColor:R.downBodyColor&&(p.color=R.upBodyColor),d=t.extend(!0,{},g,p),R.shapeRenderer.draw(o,[[_,v],[_,B]],d),R.shapeRenderer.draw(o,[[_,L],[_,m]],d),p={},n=B,h=L-B,R.fillUpBody?p.fillRect=!0:(p.strokeRect=!0,s-=this.lineWidth,a=_-s/2),R.upBodyColor&&(p.color=R.upBodyColor,p.fillStyle=R.upBodyColor),c=[a,n,s,h]):B>L?(R.wickColor?p.color=R.wickColor:R.downBodyColor&&(p.color=R.downBodyColor),d=t.extend(!0,{},g,p),R.shapeRenderer.draw(o,[[_,v],[_,L]],d),R.shapeRenderer.draw(o,[[_,B],[_,m]],d),p={},n=L,h=B-L,R.fillDownBody?p.fillRect=!0:(p.strokeRect=!0,s-=this.lineWidth,a=_-s/2),R.downBodyColor&&(p.color=R.downBodyColor,p.fillStyle=R.downBodyColor),c=[a,n,s,h]):(R.wickColor&&(p.color=R.wickColor),d=t.extend(!0,{},g,p),R.shapeRenderer.draw(o,[[_,v],[_,m]],d),p={},p.fillRect=!1,p.strokeRect=!1,a=[_-s/2,L],n=[_+s/2,B],s=null,h=null,c=[a,n]),d=t.extend(!0,{},g,p),R.shapeRenderer.draw(o,c,d)):(l=g.color,R.openColor&&(g.color=R.openColor),R.hlc||R.shapeRenderer.draw(o,[[_-R._tickLength,L],[_,L]],g),g.color=l,R.wickColor&&(g.color=R.wickColor),R.shapeRenderer.draw(o,[[_,v],[_,m]],g),g.color=l,R.closeColor&&(g.color=R.closeColor),R.shapeRenderer.draw(o,[[_,B],[_+R._tickLength,B]],g),g.color=l)}o.restore()},t.jqplot.OHLCRenderer.prototype.drawShadow=function(){},t.jqplot.OHLCRenderer.checkOptions=function(t,o,e){e.highlighter||(e.highlighter={showMarker:!1,tooltipAxes:"y",yvalues:4,formatString:'<table class="jqplot-highlighter"><tr><td>date:</td><td>%s</td></tr><tr><td>open:</td><td>%s</td></tr><tr><td>hi:</td><td>%s</td></tr><tr><td>low:</td><td>%s</td></tr><tr><td>close:</td><td>%s</td></tr></table>'})}}(jQuery);