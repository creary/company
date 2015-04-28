/**
 * @fileOverview PrerollAdPlayer class.
 * @change
 	#1 by prcjack @2014/09/06 Creates file.
 */

orderjs.define("cloudac:PrerollAdPlayer", [
	"open.jquery.jquery"
], function(){
var jQuery = orderjs("open.jquery.jquery");

var re = /\{([\w-]+)(?:\:([\w\.]*)(?:\((.*?)?\))?)?\}/g,
	format = function(source, values){
		return (source.replace(re, 
			function(m, m1) {
				var v = values[m1];
				if(v === undefined)v = "";
				return v;
			}
		));
	};
/**
 * @class
 * @constructor
 * @description Play ad source within countdown.
 * @param {Object} [config]
 */
function PrerollAdPlayer(config){
	this.init.apply(this, arguments);
}
jQuery.extend(PrerollAdPlayer.prototype, {
	/** @lends PrerollAdPlayer# */
	/**
	 * (null) Action executed on end.
	 * @type Function
	 */
	action : null,
	
	/**
	 * ("") Empty element ready for being filled with preroll ad content.
	 * @type CssSelector | HTMLElement
	 * @requried
	 */
	adCtn : "",
	
	/**
	 * (false) auto restore on end.
	 */
	autoRestore : false,
	
	/**
	 * ("") Top dom element (board ) of player, always be a fullscreen layer.
	 * @type CssSelector | HTMLElement
	 * @requried
	 */
	boardEle : "",
	
	/**
	 * (30), seconds of countdown.
	 * @type Number
	 */
	countdown : 30,
	
	/**
	 * (0) End of countdown.
	 * @type Number
	 */
	countdownEnd : 0,
	
	/**
	 * (0) A trap (a fake end) during countdown. 
	 * When this trap event occurs, it will call this doFakeEndAction().
	 */
	countdownFakeEnd : 0,
	
	/**
	 * Tip for countdown.
	 * @type String
	 */
	countdownTip : "正在处理，请稍后 {left}s",
	
	/**
	 * Indicates ad has ever been played to the end, so it wont be show again.
	 * @type Boolean
	 */
	ended : false,
	
	/**
	 * Tip set on end.
	 * @type String
	 */
	endedTip : "",
	
	/**
	 * Action executed on fake end.
	 */
	fakeEndAction : null,
	
	/**
	 * Indicates player is playing.
	 */
	playing : false,
	
	/**
	 * (true) Auto load source, only effective when source is provided.
	 * @type Boolean
	 */
	preload : true,
	
	/**
	 * (null) Ad source.
	 * @type AdSource
	 */
	source : null,
	
	/**
	 * ("") Countdown tip ctn element.
	 * @type CssSelector | HTMLElement
	 * @requried
	 */
	tipCtn : "",
	
	init : function(options){
		this.setOptions(options);
		if(this.preload) this.load();
	},
	
	doAction : function(){
		if(jQuery.isFunction(this.action)) this.action();
	},
	
	doFakeEndAction : function(){
		if(jQuery.isFunction(this.fakeEndAction)) this.fakeEndAction();
	},
	
	/**
	 * End playing.
	 */
	end : function(){
		var source = this.source;
		
		this.endCountdown();
		if(source && source.animatable) source.end();
		if(this.autoRestore) this.restoreBoard();
		else if(this.endedTip) this.updateTip(this.endedTip);
		
		this.doAction();
		this.ended = true;
		this.playing = false;
		
		return this;
	},
	
	/**
	 * End countdown.
	 */
	endCountdown : function(){
		this.counting = false;
		window.clearInterval(this._countdownTimer);
		return this;
	},
	
	/**
	 * Load source.
	 */
	load : function(){
		if(this.source) this.source.load();
		
		return this;
	},
	
	prepareBoard : function(){
		var boardJq = jQuery(this.boardEle),
			oHeight = boardJq.height(),
			winHeight = jQuery(window).height();
		
		boardJq
			.attr("data-oheight", oHeight)
			.height(jQuery(window).height())
			.show();
		jQuery(document.body)
			.addClass("prerolling")
			.height(winHeight)
			.parent()
			.addClass("prerolling")
			.height(winHeight);
		
		setTimeout(function(){jQuery(document.body).scrollTop(0);}, 1);
		
		return this;
	},
	
	restoreBoard : function(){
		var boardJq = jQuery(this.boardEle);
		
		boardJq
			.height(boardJq.attr("data-oheight"))
			.hide();
		
		jQuery(document.body)
			.removeClass("prerolling")
			.parent()
			.removeClass("prerolling");
		
		return this;
	},
	
	setOptions : function(options){
		jQuery.extend(this, options);
		if(jQuery.type(this.source) == "object"){
			this.source.owner = this;
		}
	},
	
	setSource : function(source){
		if(jQuery.type(source) != "object") return this;
		if(this.source){
			if(this.source == source)return this;
			delete this.source.owner;
		}
		
		this.source = source;
		source.owner = this;
		
		return this;
	},
	
	/**
	 * Start playing.
	 */
	start : function(){
		if(this.playing) return this;
		else if(this.ended){
			this.doAction();
			return this;
		}
		
		var source = this.source;
		
		this.prepareBoard()
			.startCountdown();
		
		if(source && source.animatable) source.start();
		this.playing = true;
		
		return this;
	},
	
	/**
	 * Start countdown.
	 */
	startCountdown : function(){
		if(this.counting) return this;
		this.counting = true;
		
		var me = this,
			left = parseInt(this.countdown),
			end = parseInt(this.countdownEnd),
			fakeEnd = parseInt(this.countdownFakeEnd);
		
		if(!(end > 0)) end = 0;
		if(!(left > end)) left = end + 1;
		
		me.updateCountdown(left);
		this._countdownTimer = window.setInterval(function(){
			left --;
			me.updateCountdown(left);
			
			if(fakeEnd > end &&
			   left == fakeEnd){
				fakeEnd = end;//force to break the fakeEnd check.
				me.doFakeEndAction();
			}
			
			if(left <= end) me.end();
		}, 1000);
		
		return this;
	},
	
	/**
	 * Update countdown tip.
	 * @param {String} left Left seconds of countdown.
	 */
	updateCountdown : function(left){
		var tip = format(this.countdownTip, {left : left});
		this.updateTip(tip);
		
		return this;
	},
	
	updateTip : function(tip){
		jQuery(this.boardEle)
			.find(this.tipCtn)
			.html(tip);
		
		return this;
	},
	
	toString : function(){
		return "[object PrerollAdPlayer]";
	}
});


return PrerollAdPlayer;
});
