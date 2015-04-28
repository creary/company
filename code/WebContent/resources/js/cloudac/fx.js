/**
 * @fileOverview View fx.
 * Note: this is a deco module, you define and load css module by manual.
 * @change
 	#1 by prcjack @2015/03/10 Creates file.
 */

orderjs.define("cloudac:fx", [
	"open.jquery.jquery",
	"app.deco.engine"
], function(){
var track = this.track;
var jQuery = orderjs("open.jquery.jquery"),
	decoUtil = orderjs("app.deco.engine");

/**
 * @class Clone and zoom in a img.
 * @extends DecoratorBase
 * @constructor
 * @description Construct a instance by specified config.
 * @param {Object} config Decorator config, if specified a string value, it's treated to be "target".
 * @param {HTMLElement} config.target
 *  Dom comps config can be overrided by defining the target attrs:
 *  ("img.cloned") data-clonedimgselector: cloned img selector.
 *  (".iwrap") data-clonedimgctnselector: cloned img ctn selector.
 *  ("cloned") data-clonedimgcss: cloned img css;
 *  ("img.main") data-mainimgselector: main img selector;
 * @example
 * <code>
	//script code:
	ZoomViewer(".soof_img_zoomviewer");
	//html code:
	&lt;div class="soof_img_zoomviewer" data-clonedimgcss="clone">
		&lt;div class="iwrap">
			&lt;div class="bar">&lt;i class="soof_icon zoom_gray">&lt;/i>&lt;/div>
			&lt;img class="main" src="zoomable.jpg" />
		&lt;/div>
	&lt;/div>
 * </code>
 */
var ZoomViewer = rl.createClass({
	parent : decoUtil.DecoratorBase,
	
	construct : function(config){
		if(rl.isPrototyping(arguments[0])) return ;
		
		ZoomViewer.parent.apply(this, arguments);
	},
	
	members : {
		/** @lends ZoomViewer# */
		
		initTarget : function(){
			this.initEvents();
			
			return this;
		},
		
		initEvents : function(){
			this.jq
				.on("mouseover", rl.bind(this.hndMouseover, this))
				.on("mouseout", rl.bind(this.hndMouseout, this));
			
			return this;
		},
		
		hndMouseover : function(){
			var cloned = this.jq.find(this.jq.attr("data-clonedimgselector") || "img.cloned");
			if(!cloned.length){
				cloned = this.jq
					.find(this.jq.attr("data-mainimgselector") || "img.main")
					.clone()
					.addClass(this.jq.attr("data-clonedimgcss") || "cloned")
					.appendTo(this.jq.attr("data-clonedimgctnselector") || ".iwrap");
			}
			cloned.show();
		},
		
		hndMouseout : function(){
			var cloned = this.jq.find(this.jq.attr("data-clonedimgselector") || "img.cloned");
			if(cloned.length){
				cloned.hide();
			}
		},
		
		toString : function(){return "[object ZoomViewer]";}
	}
});

var fx = {
	ZoomViewer : decoUtil.makeJqDeco(ZoomViewer)
};
return fx;
});
