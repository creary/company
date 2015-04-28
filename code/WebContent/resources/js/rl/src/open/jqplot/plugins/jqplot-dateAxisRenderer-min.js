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
!function(t){function i(t,i,e){for(var s,a,n,r=Number.MAX_VALUE,h=0,l=m.length;l>h;h++)s=Math.abs(e-m[h]),r>s&&(r=s,a=m[h],n=o[h]);return[a,n]}t.jqplot.DateAxisRenderer=function(){t.jqplot.LinearAxisRenderer.call(this),this.date=new t.jsDate};var e=1e3,s=60*e,a=60*s,n=24*a,r=7*n,h=30.4368499*n,l=365.242199*n,o=["%M:%S.%#N","%M:%S.%#N","%M:%S.%#N","%M:%S","%M:%S","%M:%S","%M:%S","%H:%M:%S","%H:%M:%S","%H:%M","%H:%M","%H:%M","%H:%M","%H:%M","%H:%M","%a %H:%M","%a %H:%M","%b %e %H:%M","%b %e %H:%M","%b %e %H:%M","%b %e %H:%M","%v","%v","%v","%v","%v","%v","%v"],m=[.1*e,.2*e,.5*e,e,2*e,5*e,10*e,15*e,30*e,s,2*s,5*s,10*s,15*s,30*s,a,2*a,4*a,6*a,8*a,12*a,n,2*n,3*n,4*n,5*n,r,2*r];t.jqplot.DateAxisRenderer.prototype=new t.jqplot.LinearAxisRenderer,t.jqplot.DateAxisRenderer.prototype.constructor=t.jqplot.DateAxisRenderer,t.jqplot.DateTickFormatter=function(i,e){return i||(i="%Y/%m/%d"),t.jsDate.strftime(e,i)},t.jqplot.DateAxisRenderer.prototype.init=function(i){this.tickOptions.formatter=t.jqplot.DateTickFormatter,this.tickInset=0,this.drawBaseline=!0,this.baselineWidth=null,this.baselineColor=null,this.daTickInterval=null,this._daTickInterval=null,t.extend(!0,this,i);for(var e,s,a,n,r,h,l,o=this._dataBounds,m=0;m<this._series.length;m++){e={intervals:[],frequencies:{},sortedIntervals:[],min:null,max:null,mean:null},s=0,a=this._series[m],n=a.data,r=a._plotData,h=a._stackData,l=0;for(var c=0;c<n.length;c++)"xaxis"==this.name||"x2axis"==this.name?(n[c][0]=new t.jsDate(n[c][0]).getTime(),r[c][0]=new t.jsDate(n[c][0]).getTime(),h[c][0]=new t.jsDate(n[c][0]).getTime(),(null!=n[c][0]&&n[c][0]<o.min||null==o.min)&&(o.min=n[c][0]),(null!=n[c][0]&&n[c][0]>o.max||null==o.max)&&(o.max=n[c][0]),c>0&&(l=Math.abs(n[c][0]-n[c-1][0]),e.intervals.push(l),e.frequencies.hasOwnProperty(l)?e.frequencies[l]+=1:e.frequencies[l]=1),s+=l):(n[c][1]=new t.jsDate(n[c][1]).getTime(),r[c][1]=new t.jsDate(n[c][1]).getTime(),h[c][1]=new t.jsDate(n[c][1]).getTime(),(null!=n[c][1]&&n[c][1]<o.min||null==o.min)&&(o.min=n[c][1]),(null!=n[c][1]&&n[c][1]>o.max||null==o.max)&&(o.max=n[c][1]),c>0&&(l=Math.abs(n[c][1]-n[c-1][1]),e.intervals.push(l),e.frequencies.hasOwnProperty(l)?e.frequencies[l]+=1:e.frequencies[l]=1)),s+=l;if(a.renderer.bands){if(a.renderer.bands.hiData.length)for(var u=a.renderer.bands.hiData,c=0,k=u.length;k>c;c++)"xaxis"===this.name||"x2axis"===this.name?(u[c][0]=new t.jsDate(u[c][0]).getTime(),(null!=u[c][0]&&u[c][0]>o.max||null==o.max)&&(o.max=u[c][0])):(u[c][1]=new t.jsDate(u[c][1]).getTime(),(null!=u[c][1]&&u[c][1]>o.max||null==o.max)&&(o.max=u[c][1]));if(a.renderer.bands.lowData.length)for(var u=a.renderer.bands.lowData,c=0,k=u.length;k>c;c++)"xaxis"===this.name||"x2axis"===this.name?(u[c][0]=new t.jsDate(u[c][0]).getTime(),(null!=u[c][0]&&u[c][0]<o.min||null==o.min)&&(o.min=u[c][0])):(u[c][1]=new t.jsDate(u[c][1]).getTime(),(null!=u[c][1]&&u[c][1]<o.min||null==o.min)&&(o.min=u[c][1]))}for(var v in e.frequencies)e.sortedIntervals.push({interval:v,frequency:e.frequencies[v]});e.sortedIntervals.sort(function(t,i){return i.frequency-t.frequency}),e.min=t.jqplot.arrayMin(e.intervals),e.max=t.jqplot.arrayMax(e.intervals),e.mean=s/n.length,this._intervalStats.push(e),e=s=a=n=r=h=null}o=null},t.jqplot.DateAxisRenderer.prototype.reset=function(){this.min=this._options.min,this.max=this._options.max,this.tickInterval=this._options.tickInterval,this.numberTicks=this._options.numberTicks,this._autoFormatString="",this._overrideFormatString&&this.tickOptions&&this.tickOptions.formatString&&(this.tickOptions.formatString=""),this.daTickInterval=this._daTickInterval},t.jqplot.DateAxisRenderer.prototype.createTicks=function(e){{var s,a,r,o,m=this._ticks,c=this.ticks,u=this.name,k=this._dataBounds,v=(this._intervalStats,"x"===this.name.charAt(0)?this._plotDimensions.width:this._plotDimensions.height),d=30,T=1;this.tickInterval}s=null!=this.min?new t.jsDate(this.min).getTime():k.min,a=null!=this.max?new t.jsDate(this.max).getTime():k.max;var w=e.plugins.cursor;w&&w._zoom&&w._zoom.zooming&&(this.min=null,this.max=null);var x=a-s;if(null!=this.tickOptions&&this.tickOptions.formatString||(this._overrideFormatString=!0),c.length){for(o=0;o<c.length;o++){var g=c[o],f=new this.tickRenderer(this.tickOptions);g.constructor==Array?(f.value=new t.jsDate(g[0]).getTime(),f.label=g[1],this.showTicks?this.showTickMarks||(f.showMark=!1):(f.showLabel=!1,f.showMark=!1),f.setTick(f.value,this.name),this._ticks.push(f)):(f.value=new t.jsDate(g).getTime(),this.showTicks?this.showTickMarks||(f.showMark=!1):(f.showLabel=!1,f.showMark=!1),f.setTick(f.value,this.name),this._ticks.push(f))}this.numberTicks=c.length,this.min=this._ticks[0].value,this.max=this._ticks[this.numberTicks-1].value,this.daTickInterval=[(this.max-this.min)/(this.numberTicks-1)/1e3,"seconds"]}else if(null==this.min&&null==this.max&&k.min==k.max){var p=t.extend(!0,{},this.tickOptions,{name:this.name,value:null}),M=3e5;this.min=k.min-M,this.max=k.max+M,this.numberTicks=3;for(var o=this.min;o<=this.max;o+=M){p.value=o;var f=new this.tickRenderer(p);this._overrideFormatString&&""!=this._autoFormatString&&(f.formatString=this._autoFormatString),f.showLabel=!1,f.showMark=!1,this._ticks.push(f)}this.showTicks&&(this._ticks[1].showLabel=!0),this.showTickMarks&&(this._ticks[1].showTickMarks=!0)}else if(null==this.min&&null==this.max){var _,D,b=t.extend(!0,{},this.tickOptions,{name:this.name,value:null});if(this.tickInterval||this.numberTicks)this.tickInterval?D=this.tickInterval:this.numberTicks&&(_=this.numberTicks,D=(a-s)/(_-1));else{var I=Math.max(v,d+1),j=115;this.tickRenderer===t.jqplot.CanvasAxisTickRenderer&&this.tickOptions.angle&&(j=115-40*Math.abs(Math.sin(this.tickOptions.angle/180*Math.PI))),_=Math.ceil((I-d)/j+1),D=(a-s)/(_-1)}if(19*n>=D){var S=i(s,a,D),q=S[0];this._autoFormatString=S[1],s=Math.floor(s/q)*q,s=new t.jsDate(s),s=s.getTime()+s.getUtcOffset(),_=Math.ceil((a-s)/q)+1,this.min=s,this.max=s+(_-1)*q,this.max<a&&(this.max+=q,_+=1),this.tickInterval=q,this.numberTicks=_;for(var o=0;_>o;o++)b.value=this.min+o*q,f=new this.tickRenderer(b),this._overrideFormatString&&""!=this._autoFormatString&&(f.formatString=this._autoFormatString),this.showTicks?this.showTickMarks||(f.showMark=!1):(f.showLabel=!1,f.showMark=!1),this._ticks.push(f);T=this.tickInterval}else if(9*h>=D){this._autoFormatString="%v";var F=Math.round(D/h);1>F?F=1:F>6&&(F=6);var y=new t.jsDate(s).setDate(1).setHours(0,0,0,0),H=new t.jsDate(a),R=new t.jsDate(a).setDate(1).setHours(0,0,0,0);H.getTime()!==R.getTime()&&(R=R.add(1,"month"));var O=R.diff(y,"month");_=Math.ceil(O/F)+1,this.min=y.getTime(),this.max=y.clone().add((_-1)*F,"month").getTime(),this.numberTicks=_;for(var o=0;_>o;o++)b.value=0===o?y.getTime():y.add(F,"month").getTime(),f=new this.tickRenderer(b),this._overrideFormatString&&""!=this._autoFormatString&&(f.formatString=this._autoFormatString),this.showTicks?this.showTickMarks||(f.showMark=!1):(f.showLabel=!1,f.showMark=!1),this._ticks.push(f);T=F*h}else{this._autoFormatString="%v";var F=Math.round(D/l);1>F&&(F=1);var y=new t.jsDate(s).setMonth(0,1).setHours(0,0,0,0),R=new t.jsDate(a).add(1,"year").setMonth(0,1).setHours(0,0,0,0),A=R.diff(y,"year");_=Math.ceil(A/F)+1,this.min=y.getTime(),this.max=y.clone().add((_-1)*F,"year").getTime(),this.numberTicks=_;for(var o=0;_>o;o++)b.value=0===o?y.getTime():y.add(F,"year").getTime(),f=new this.tickRenderer(b),this._overrideFormatString&&""!=this._autoFormatString&&(f.formatString=this._autoFormatString),this.showTicks?this.showTickMarks||(f.showMark=!1):(f.showLabel=!1,f.showMark=!1),this._ticks.push(f);T=F*l}}else{if(v="xaxis"==u||"x2axis"==u?this._plotDimensions.width:this._plotDimensions.height,null!=this.min&&null!=this.max&&null!=this.numberTicks&&(this.tickInterval=null),null!=this.tickInterval)if(Number(this.tickInterval))this.daTickInterval=[Number(this.tickInterval),"seconds"];else if("string"==typeof this.tickInterval){var L=this.tickInterval.split(" ");1==L.length?this.daTickInterval=[1,L[0]]:2==L.length&&(this.daTickInterval=[L[0],L[1]])}if(s==a){var N=432e5;s-=N,a+=N}x=a-s;{var z,B;2+parseInt(Math.max(0,v-100)/100,10)}if(z=null!=this.min?new t.jsDate(this.min).getTime():s-x/2*(this.padMin-1),B=null!=this.max?new t.jsDate(this.max).getTime():a+x/2*(this.padMax-1),this.min=z,this.max=B,x=this.max-this.min,null==this.numberTicks)if(null!=this.daTickInterval){var P=new t.jsDate(this.max).diff(this.min,this.daTickInterval[1],!0);this.numberTicks=Math.ceil(P/this.daTickInterval[0])+1,this.max=new t.jsDate(this.min).add((this.numberTicks-1)*this.daTickInterval[0],this.daTickInterval[1]).getTime()}else this.numberTicks=v>200?parseInt(3+(v-200)/100,10):2;T=x/(this.numberTicks-1)/1e3,null==this.daTickInterval&&(this.daTickInterval=[T,"seconds"]);for(var o=0;o<this.numberTicks;o++){var s=new t.jsDate(this.min);r=s.add(o*this.daTickInterval[0],this.daTickInterval[1]).getTime();var f=new this.tickRenderer(this.tickOptions);this.showTicks?this.showTickMarks||(f.showMark=!1):(f.showLabel=!1,f.showMark=!1),f.setTick(r,this.name),this._ticks.push(f)}}this.tickInset&&(this.min=this.min-this.tickInset*T,this.max=this.max+this.tickInset*T),null==this._daTickInterval&&(this._daTickInterval=this.daTickInterval),m=null}}(jQuery);