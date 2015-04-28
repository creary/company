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
!function(e){e.jqplot.ciParser=function(r){function t(e,r){var t;return null!=r?r.toString().indexOf("Date")>=0&&(t=/^\/Date\((-?[0-9]+)\)\/$/.exec(r))?parseInt(t[1],10):r:void 0}var n,i,o,a,c,f=[];if("string"==typeof r)r=e.jqplot.JSON.parse(r,t);else{if("object"!=typeof r)return null;for(a in r)for(o=0;o<r[a].length;o++)for(c in r[a][o])r[a][o][c]=t(c,r[a][o][c])}for(var s in r){switch(n=[],i=r[s],s){case"PriceTicks":for(o=0;o<i.length;o++)n.push([i[o].TickDate,i[o].Price]);break;case"PriceBars":for(o=0;o<i.length;o++)n.push([i[o].BarDate,i[o].Open,i[o].High,i[o].Low,i[o].Close])}f.push(n)}return f}}(jQuery);