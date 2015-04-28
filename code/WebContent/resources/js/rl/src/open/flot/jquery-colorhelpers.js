/* Plugin for jQuery for working with colors.
 * 
 * Version 1.1.
 * 
 * Inspiration from jQuery color animation plugin by John Resig.
 *
 * Released under the MIT license by Ole Laursen, October 2009.
 *
 * Examples:
 *
 *   $.color.parse("#fff").scale('rgb', 0.25).add('a', -0.5).toString()
 *   var c = $.color.extract($("#mydiv"), 'background-color');
 *   console.log(c.r, c.g, c.b, c.a);
 *   $.color.make(100, 50, 25, 0.4).toString() // returns "rgba(100,50,25,0.4)"
 *
 * Note that .scale() and .add() return the same modified object
 * instead of making a new one.
 *
 * V. 1.1: Fix error handling so e.g. parsing an empty string does
 * produce a color rather than just crashing.
 */
!function(r){r.color={},r.color.make=function(e,a,n,t){var o={};return o.r=e||0,o.g=a||0,o.b=n||0,o.a=null!=t?t:1,o.add=function(r,e){for(var a=0;a<r.length;++a)o[r.charAt(a)]+=e;return o.normalize()},o.scale=function(r,e){for(var a=0;a<r.length;++a)o[r.charAt(a)]*=e;return o.normalize()},o.toString=function(){return o.a>=1?"rgb("+[o.r,o.g,o.b].join(",")+")":"rgba("+[o.r,o.g,o.b,o.a].join(",")+")"},o.normalize=function(){function r(r,e,a){return r>e?r:e>a?a:e}return o.r=r(0,parseInt(o.r),255),o.g=r(0,parseInt(o.g),255),o.b=r(0,parseInt(o.b),255),o.a=r(0,o.a,1),o},o.clone=function(){return r.color.make(o.r,o.b,o.g,o.a)},o.normalize()},r.color.extract=function(e,a){var n;do{if(n=e.css(a).toLowerCase(),""!=n&&"transparent"!=n)break;e=e.parent()}while(e.length&&!r.nodeName(e.get(0),"body"));return"rgba(0, 0, 0, 0)"==n&&(n="transparent"),r.color.parse(n)},r.color.parse=function(a){var n,t=r.color.make;if(n=/rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(a))return t(parseInt(n[1],10),parseInt(n[2],10),parseInt(n[3],10));if(n=/rgba\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]+(?:\.[0-9]+)?)\s*\)/.exec(a))return t(parseInt(n[1],10),parseInt(n[2],10),parseInt(n[3],10),parseFloat(n[4]));if(n=/rgb\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*\)/.exec(a))return t(2.55*parseFloat(n[1]),2.55*parseFloat(n[2]),2.55*parseFloat(n[3]));if(n=/rgba\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\s*\)/.exec(a))return t(2.55*parseFloat(n[1]),2.55*parseFloat(n[2]),2.55*parseFloat(n[3]),parseFloat(n[4]));if(n=/#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(a))return t(parseInt(n[1],16),parseInt(n[2],16),parseInt(n[3],16));if(n=/#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])/.exec(a))return t(parseInt(n[1]+n[1],16),parseInt(n[2]+n[2],16),parseInt(n[3]+n[3],16));var o=r.trim(a).toLowerCase();return"transparent"==o?t(255,255,255,0):(n=e[o]||[0,0,0],t(n[0],n[1],n[2]))};var e={aqua:[0,255,255],azure:[240,255,255],beige:[245,245,220],black:[0,0,0],blue:[0,0,255],brown:[165,42,42],cyan:[0,255,255],darkblue:[0,0,139],darkcyan:[0,139,139],darkgrey:[169,169,169],darkgreen:[0,100,0],darkkhaki:[189,183,107],darkmagenta:[139,0,139],darkolivegreen:[85,107,47],darkorange:[255,140,0],darkorchid:[153,50,204],darkred:[139,0,0],darksalmon:[233,150,122],darkviolet:[148,0,211],fuchsia:[255,0,255],gold:[255,215,0],green:[0,128,0],indigo:[75,0,130],khaki:[240,230,140],lightblue:[173,216,230],lightcyan:[224,255,255],lightgreen:[144,238,144],lightgrey:[211,211,211],lightpink:[255,182,193],lightyellow:[255,255,224],lime:[0,255,0],magenta:[255,0,255],maroon:[128,0,0],navy:[0,0,128],olive:[128,128,0],orange:[255,165,0],pink:[255,192,203],purple:[128,0,128],violet:[128,0,128],red:[255,0,0],silver:[192,192,192],white:[255,255,255],yellow:[255,255,0]}}(jQuery);