/**
 * @fileOverview Shim jquery flot & its plugins.
 * @change
 	#1 by prcjack @2014/04/12 Creates file.
 */
orderjs.define("open.flot.order-shim",["RealLight"],function(){var e="open.flot.jquery-flot";rl.makeShim(e,{deps:[rl.VER_IE>0&&rl.VER_IE<9?"open.excanvas.excanvas":"","open.jquery.jquery"],exports:"jQuery"});var r={deps:["open.flot.jquery-flot"],exports:"jQuery"},o=["jquery-flot-canvas","jquery-flot-categories","jquery-flot-crosshair","jquery-flot-errorbars","jquery-flot-fillbetween","jquery-flot-image","jquery-flot-navigate","jquery-flot-pie","jquery-flot-resize","jquery-flot-selection","jquery-flot-stack","jquery-flot-symbol","jquery-flot-threshold","jquery-flot-time"];rl.each(o,function(e){var o="open.flot."+e;rl.makeShim(o,r)})});