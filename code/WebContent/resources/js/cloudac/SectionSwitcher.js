/**
 * @fileOverview Provide fns to switch sections by user events.
 * @change
 	#1 by prcjack @2014/08/30 Creates file.
 */

orderjs.define("cloudac:SectionSwitcher", [
	"open.jquery.jquery"
], function(){
var jQuery = orderjs("open.jquery.jquery");

/**
 * @class SectionSwitcher
 * @constructor
 * @description Provide fns to switch sections by user events.
 * @param {Object} [config]
 */
var SectionSwitcher = function(options){
	this.init.apply(this, arguments);
};
jQuery.extend(SectionSwitcher.prototype, {
	active : "",
	iniActive : "",
	map : null,
	
	/**
	 * Trigger to trigge event to change selection.
	 * @type CssSelector
	 */
	trigger : "",
	
	init : function(options){
		this.map = {};
		this.initOptions(options);
		this.initTrigger();
		this.setActive(this.iniActive);
		rl.dir(this);
	},
	
	applyActive : function(section){
		jQuery(section).show();
		return this;
	},
	
	applyInActive : function(section){
		jQuery(section).hide();
		return this;
	},
	
	initOptions : function(options){
		if(jQuery.type(options) == "string") options = {trigger : options};
		jQuery.extend(this, options);
	},
	
	initTrigger : function(){
		if(this.trigger){
			var me = this,
				triggerJq = jQuery(this.trigger);
			
			if(triggerJq.is("input[type='radio']")){
				rl.dir(triggerJq.filter(":checked"));
				if(!this.iniActive){
					this.iniActive = triggerJq.filter(":checked").val();
				}
				
				triggerJq.on("click", function(){
					rl.debug(this + ' str = ' + this.value + '');
					me.setActive(this.value);
				});
				
				triggerJq.each(function(){
					var val = jQuery(this).val(),
						linkSection = jQuery(this).attr("data-linkSection")
					
					if(linkSection){
						me.map[val] = linkSection;
					}
				});
			}
		}
	},
	
	setActive : function(value){
		if(this.active == value) return this;
		var oldActive = this.active,
			oldSection = this.map[oldActive],
			newSection = this.map[value];
		
		if(!newSection){
			//rl.debug(this + 'setActive(): Cant find section by value = ' + value + '', "err");
			return this;
		}
		
		if(oldSection) this.applyInActive(oldSection);
		this.applyActive(newSection);
		this.active = value;
		
		return this;
	},
	
	toString : function(){
		return "[object SectionSwitcher]";
	}
});

return SectionSwitcher;
});
