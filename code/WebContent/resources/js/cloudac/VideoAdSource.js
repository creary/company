/**
 * @fileOverview VideoAdSource class.
 * @change
 	#1 by prcjack @2014/08/30 Creates file.
 */

orderjs.define("cloudac:VideoAdSource", [
	"open.jquery.jquery",
	"app.media.video.ckplayer-adapter",
	"cloudac:AdSource"
], function(){
var jQuery = orderjs("open.jquery.jquery"),
	ckplayerAdapter = orderjs("app.media.video.ckplayer-adapter"),
	AdSource = orderjs("cloudac:AdSource");

/**
 * @class
 * @constructor
 * @description Ad source with video.
 * @param {Object} config
 * @param {String} config.src
 * @param {CssSelector | HTMLElement} config.videoEle
 * @param {CssSelector | HTMLElement} config.videoCtn
 */
function VideoAdSource(config){
	this.init.apply(this, arguments);
}
VideoAdSource.prototype = new AdSource;
jQuery.extend(VideoAdSource.prototype, {
	/** @lends VideoAdSource# */
	animatable : true,
	
	/**
	 * ("") the video proxy id, only accessable on this useHtml5 is false.
	 * @type String
	 */
	proxyId : "",
	
	/**
	 * (null) Extra options for video proxy, only effective on this useHtml5 is false.
	 * @type Object
	 */
	proxyOptions : null,
	
	/**
	 * Video src url.
	 */
	src : "",
	
	useHtml5Video : document.createElement('video').canPlayType,
	
	/**
	 * ("") Video element.
	 * @type CssSelector | HTMLElement
	 * @requried
	 */
	videoEle : "",
	
	/**
	 * ("") Video ctn element.
	 * @type CssSelector | HTMLElement
	 * @requried
	 */
	videoCtn : "",
	
	init : function(options){
		this.initOptions(options);
	},
	
	end : function(){
		if(!this._loaded) return false;
		if(this.useHtml5Video){
			var ele = jQuery(this.videoEle).get(0);
			ele.load();
			ele.currentTime = 0;
		}
	},
	
	initOptions : function(options){
		jQuery.extend(this, options);
	},
	
	load : function(){
		if(this.useHtml5Video){
			jQuery(this.videoEle).get(0).load();
		}
		else{
			var owner = this.owner;
			if(!owner) throw new Error(this + ">load(): this owner is undefined!");
			
			jQuery(this.owner.adCtn)
				.html('<div id=' + this.videoCtn + '></div>');
			
			this.proxyId = ckplayerAdapter.create(jQuery.extend({
				src : this.src,
				ctn : this.videoCtn,
				width : "100%",
				height : "100%",
				params : {
					//An bug: hidding the video dom may case a bug while the video is fullscreen.
					//so fullscreen is set to false.
					allowFullScreen : false
				}
			}, this.proxyOptions));
		}
	
		this._loaded = true;
		
		return this;
	},
	
	start : function(){
		if(!this._loaded) return this;
		if(this.useHtml5Video){
			var ele = jQuery(this.videoEle).get(0);
			ele.play();
			ele.currentTime = 0;
		}
	},
	
	toString : function(){
		return "[object VideoAdSource]";
	}
});


return VideoAdSource;
});
