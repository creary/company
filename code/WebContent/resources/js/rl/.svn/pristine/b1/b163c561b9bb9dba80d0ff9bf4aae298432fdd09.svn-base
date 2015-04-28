/**
 * @fileOverview Shim jQuery to fix jQuery(fn) ready event bug 
 * when it is async load after DOMContentLoad.
 * @usage
 * <code>
 	//Order this module together with jQuery.
 	orderjs(
		"open.jquery.jquery",
		"open.jquery.readyFix"
	);
	//then use jQuery(fn) as usual.
	orderjs(function(){
		var jQuery = orderjs("open.jquery.jquery");
		jQuery(function(){
			//TODO: 
		});
	});
 * </code>
 * @change
 	#1 by prcjack @2015/01/7 Creates file.
 */
orderjs.define("open.jquery.readyFix",["open.jquery.jquery"],function(){var e=orderjs("open.jquery.jquery");e.isReady||orderjs.isReady&&e.ready()});