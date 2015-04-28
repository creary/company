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
!function(t){t.jqplot.PyramidAxisRenderer=function(){t.jqplot.LinearAxisRenderer.call(this)},t.jqplot.PyramidAxisRenderer.prototype=new t.jqplot.LinearAxisRenderer,t.jqplot.PyramidAxisRenderer.prototype.constructor=t.jqplot.PyramidAxisRenderer,t.jqplot.PyramidAxisRenderer.prototype.init=function(i){this.position=null,this.drawBaseline=!0,this.baselineWidth=null,this.baselineColor=null,this.tickSpacingFactor=25,this._type="pyramid",this._splitAxis=!1,this._splitLength=null,this.category=!1,this._autoFormatString="",this._overrideFormatString=!1,t.extend(!0,this,i),this.renderer.options=i,this.resetDataBounds=this.renderer.resetDataBounds,this.resetDataBounds()},t.jqplot.PyramidAxisRenderer.prototype.resetDataBounds=function(){var t=this._dataBounds;t.min=null,t.max=null;for(var i,e=0;e<this._series.length;e++)for(var s=this._series[e],h=s._plotData,n=0,a=h.length;a>n;n++)"x"===this.name.charAt(0)?(i=h[n][1],(null!==i&&i<t.min||null===t.min)&&(t.min=i),(null!==i&&i>t.max||null===t.max)&&(t.max=i)):(i=h[n][0],(null!==i&&i<t.min||null===t.min)&&(t.min=i),(null!==i&&i>t.max||null===t.max)&&(t.max=i))},t.jqplot.PyramidAxisRenderer.prototype.draw=function(i,e){if(this.show){this.renderer.createTicks.call(this,e);if(this._elem&&(this._elem.emptyForce(),this._elem=null),this._elem=t(document.createElement("div")),this._elem.addClass("jqplot-axis jqplot-"+this.name),this._elem.css("position","absolute"),"xaxis"==this.name||"x2axis"==this.name?this._elem.width(this._plotDimensions.width):this._elem.height(this._plotDimensions.height),this.labelOptions.axis=this.name,this._label=new this.labelRenderer(this.labelOptions),this._label.show){var s=this._label.draw(i,e);s.appendTo(this._elem),s=null}for(var h,n=this._ticks,a=0;a<n.length;a++)h=n[a],h.show&&h.showLabel&&!h.isMinorTick&&this._elem.append(h.draw(i,e));h=null,n=null}return this._elem};for(var i=[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,883,887,907,911,919,929,937,941,947,953,967,971,977,983,991,997],e={},s=0,h=i.length;h>s;s++)e[i[s]]=i[s];t.jqplot.PyramidAxisRenderer.prototype.createTicks=function(i){var s,h,n,a,r,l,o,c,m,_,p,u,x,d,k,g,f,b,v=this.ticks,w=this._dataBounds,R=(this.min,this.max,null);if(v.length){for(r=0,l=v.length;l>r;r++)c=v[r],m=new this.tickRenderer(this.tickOptions),t.isArray(c)?(m.value=c[0],m.label=c[1],m.setTick(c[0],this.name),this._ticks.push(m)):t.isPlainObject(c)?(t.extend(!0,m,c),m.axis=this.name,this._ticks.push(m)):(f="string"==typeof c?r+i.defaultAxisStart:c,m.value=f,m.label=c,m.axis=this.name,this._ticks.push(m));if(this.numberTicks=v.length,this.min=this._ticks[0].value,this.max=this._ticks[this.numberTicks-1].value,this.tickInterval=(this.max-this.min)/(this.numberTicks-1),this._options.tickInterval){var A=this._options.tickInterval;for(r=0;r<this.numberTicks;r++)r%A!==0&&(this._ticks[r].isMinorTick=!0)}else if(s="x"===this.name.charAt(0)?this._plotDimensions.width:this._plotDimensions.height,g=Math.round(2+s/this.tickSpacingFactor),this.numberTicks>g){for(b=this.numberTicks-1,r=2;b>r;r++)if(b%r===0&&g>b/r){R=r-1;break}if(null!==R){var y=1;for(r=1,l=this._ticks.length;l>r;r++)R>=y?(this._ticks[r].show=!1,y+=1):y=1}}if(b=[],this.category){for(this._ticks[0].showGridline=!1,this._ticks[0].showMark=!1,r=this._ticks.length-1;r>0;r--)m=new this.tickRenderer(this.tickOptions),m.value=this._ticks[r-1].value+this.tickInterval/2,m.label="",m.showLabel=!1,m.axis=this.name,this._ticks[r].showGridline=!1,this._ticks[r].showMark=!1,this._ticks.splice(r,0,m);m=new this.tickRenderer(this.tickOptions),m.value=this._ticks[0].value-this.tickInterval/2,m.label="",m.showLabel=!1,m.axis=this.name,this._ticks.unshift(m),m=new this.tickRenderer(this.tickOptions),m.value=this._ticks[this._ticks.length-1].value+this.tickInterval/2,m.label="",m.showLabel=!1,m.axis=this.name,this._ticks.push(m),this.tickInterval=this.tickInterval/2,this.numberTicks=this._ticks.length,this.min=this._ticks[0].value,this.max=this._ticks[this._ticks.length-1].value}}else{if("x"===this.name.charAt(0)){s=this._plotDimensions.width;var M=Math.max(w.max,Math.abs(w.min)),T=Math.min(w.min,-M);h=T,n=M,a=n-h,null!=this.tickOptions&&this.tickOptions.formatString||(this._overrideFormatString=!0),_=30,p=Math.max(s,_+1),u=(p-_)/300,x=t.jqplot.LinearTickGenerator(h,n,u),d=h+a*(this.padMin-1),k=n-a*(this.padMax-1),(d>h||n>k)&&(d=h-a*(this.padMin-1),k=n+a*(this.padMax-1),x=t.jqplot.LinearTickGenerator(d,k,u)),this.min=x[0],this.max=x[1],this.numberTicks=x[2],this._autoFormatString=x[3],this.tickInterval=x[4]}else if(s=this._plotDimensions.height,h=w.min,n=w.max,o=this._series[0],this._ticks=[],a=n-h,e[a]&&(a+=1,n+=1),this.max=n,this.min=h,g=Math.round(2+s/this.tickSpacingFactor),g>=a+1)this.numberTicks=a+1,this.tickInterval=1;else for(var r=g;r>1;r--)if(a/(r-1)===Math.round(a/(r-1))){this.numberTicks=r,this.tickInterval=a/(r-1);break}this._overrideFormatString&&""!=this._autoFormatString&&(this.tickOptions=this.tickOptions||{},this.tickOptions.formatString=this._autoFormatString);var j;for(r=0;r<this.numberTicks;r++)this.tickOptions.axis=this.name,j=this.min+this.tickInterval*r,"x"===this.name.charAt(0)&&(j=Math.abs(j)),this.tickOptions.value=this.min+this.tickInterval*r,m=new this.tickRenderer(this.tickOptions),m.label=m.prefix+m.formatter(m.formatString,j),this._ticks.push(m),"x"===this.name.charAt(0)&&i.axes.yMidAxis.show&&0===this.tickOptions.value&&(this._splitAxis=!0,this._splitLength=i.axes.yMidAxis.getWidth(),m=new this.tickRenderer(this.tickOptions),this._ticks.push(m),m.value=this.max/2e3);m=null}},t.jqplot.PyramidAxisRenderer.prototype.set=function(){var i,e,s,n,a=0,r=0,l=0,o=null==this._label?!1:this._label.show;if(this.show){for(s=this._ticks,h=s.length,e=0;h>e;e++)n=s[e],!n._breakTick&&n.show&&n.showLabel&&!n.isMinorTick&&(i="x"===this.name.charAt(0)?n._elem.outerHeight(!0):n._elem.outerWidth(!0),i>a&&(a=i));if("yMidAxis"===this.name)for(e=0;h>e;e++)n=s[e],n._elem&&(i=(a-n._elem.outerWidth(!0))/2,n._elem.css("left",i));if(n=null,s=null,o&&(r=this._label._elem.outerWidth(!0),l=this._label._elem.outerHeight(!0)),"xaxis"===this.name)a+=l,this._elem.css({height:a+"px",left:"0px",bottom:"0px"});else if("x2axis"===this.name)a+=l,this._elem.css({height:a+"px",left:"0px",top:"0px"});else if("yaxis"===this.name)a+=r,this._elem.css({width:a+"px",left:"0px",top:"0px"}),o&&this._label.constructor==t.jqplot.AxisLabelRenderer&&this._label._elem.css("width",r+"px");else if("yMidAxis"===this.name){var i=a/2-r/2;this._elem.css({width:a+"px",top:"0px"}),o&&this._label.constructor==t.jqplot.AxisLabelRenderer&&this._label._elem.css({width:r,left:i,top:0})}else a+=r,this._elem.css({width:a+"px",right:"0px",top:"0px"}),o&&this._label.constructor==t.jqplot.AxisLabelRenderer&&this._label._elem.css("width",r+"px")}},t.jqplot.PyramidAxisRenderer.prototype.pack=function(i,e){i=i||{},e=e||this._offsets;var s=this._ticks,h=this.max,n=this.min,a=e.max,r=e.min,l=null==this._label?!1:this._label.show;for(var o in i)this._elem.css(o,i[o]);this._offsets=e;var c=a-r,m=h-n,_=this._splitLength;if(this._splitAxis?(c-=this._splitLength,this.p2u=function(t){return(t-r)*m/c+n},this.u2p=function(t){return 0>=t?(t-n)*c/m+r:(t-n)*c/m+r+_},this.series_u2p=function(t){return 0>=t?(t-n)*c/m:(t-n)*c/m+_},this.series_p2u=function(t){return t*m/c+n}):(this.p2u=function(t){return(t-r)*m/c+n},this.u2p=function(t){return(t-n)*c/m+r},"x"===this.name.charAt(0)?(this.series_u2p=function(t){return(t-n)*c/m},this.series_p2u=function(t){return t*m/c+n}):(this.series_u2p=function(t){return(t-h)*c/m},this.series_p2u=function(t){return t*m/c+h})),this.show)if("x"===this.name.charAt(0)){for(var p=0;p<s.length;p++){var u=s[p];if(u.show&&u.showLabel){var x;if(u.constructor==t.jqplot.CanvasAxisTickRenderer&&u.angle){var d="xaxis"==this.name?1:-1;switch(u.labelPosition){case"auto":x=d*u.angle<0?-u.getWidth()+u._textRenderer.height*Math.sin(-u._textRenderer.angle)/2:-u._textRenderer.height*Math.sin(u._textRenderer.angle)/2;break;case"end":x=-u.getWidth()+u._textRenderer.height*Math.sin(-u._textRenderer.angle)/2;break;case"start":x=-u._textRenderer.height*Math.sin(u._textRenderer.angle)/2;break;case"middle":x=-u.getWidth()/2+u._textRenderer.height*Math.sin(-u._textRenderer.angle)/2;break;default:x=-u.getWidth()/2+u._textRenderer.height*Math.sin(-u._textRenderer.angle)/2}}else x=-u.getWidth()/2;var k=this.u2p(u.value)+x+"px";u._elem.css("left",k),u.pack()}}if(l){var g=this._label._elem.outerWidth(!0);this._label._elem.css("left",r+c/2-g/2+"px"),"xaxis"==this.name?this._label._elem.css("bottom","0px"):this._label._elem.css("top","0px"),this._label.pack()}}else{for(var p=0;p<s.length;p++){var u=s[p];if(u.show&&u.showLabel&&!u.isMinorTick){var x;if(u.constructor==t.jqplot.CanvasAxisTickRenderer&&u.angle){var d="yaxis"==this.name?1:-1;switch(u.labelPosition){case"auto":case"end":x=d*u.angle<0?-u._textRenderer.height*Math.cos(-u._textRenderer.angle)/2:-u.getHeight()+u._textRenderer.height*Math.cos(u._textRenderer.angle)/2;break;case"start":x=u.angle>0?-u._textRenderer.height*Math.cos(-u._textRenderer.angle)/2:-u.getHeight()+u._textRenderer.height*Math.cos(u._textRenderer.angle)/2;break;case"middle":x=-u.getHeight()/2;break;default:x=-u.getHeight()/2}}else x=-u.getHeight()/2;var k=this.u2p(u.value)+x+"px";u._elem.css("top",k),u.pack()}}if(l){var f=this._label._elem.outerHeight(!0);"yMidAxis"!==this.name&&this._label._elem.css("top",a-c/2-f/2+"px"),"yaxis"==this.name?this._label._elem.css("left","0px"):"yMidAxis"!==this.name&&this._label._elem.css("right","0px"),this._label.pack()}}s=null}}(jQuery);