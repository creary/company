/**
 * @fileOverview AdSource class.
 * @change
 	#1 by prcjack @2014/09/06 Creates file.
 */

orderjs.define("cloudac:AdSource", [
	"open.jquery.jquery"
], function(){
var jQuery = orderjs("open.jquery.jquery");

/**
 * @class
 * @constructor
 * @description Ad source with html content.
 * @param {Object} config
 * @param {String} config.owner
 * @param {String} config.content
 */
function AdSource(config){
	this.init.apply(this, arguments);
}
jQuery.extend(AdSource.prototype, {
	/** @lends AdSource# */
	
	/**
	 * Indicates this ad is an animation.
	 * If animatable, the layer should call start and end at the right time.
	 */
	animatable : false,
	
	/**
	 * Ad source content.
	 * @type String
	 */
	content : "",
	
	/**
	 * Source owner which should implement:
	 * {CssSelector | HTMLElement} adCtn Dom ctn for this.
	 */
	owner : null,
	
	init : function(options){
		this.initOptions(options);
	},
	
	load : function(){
		var owner = this.owner;
		if(!owner) throw new Error(this + ">load(): this owner is undefined!");
		
		jQuery(this.owner.adCtn).html(this.content);
		
		this._loaded = true;
		return this;
	},
	
	initOptions : function(options){
		jQuery.extend(this, options);
	},
	
	toString : function(){
		return "[object AdSource]";
	}
});

return AdSource;
});
