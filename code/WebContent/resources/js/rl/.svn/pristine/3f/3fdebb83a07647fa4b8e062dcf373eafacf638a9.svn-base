/**
 * @fileOverview Shim uploadify.
 * @change
 	#1 by prcjack @2014/04/28 Creates file.
 */
orderjs.define("open.uploadify.order-shim",["RealLight"],function(){var e="open.uploadify.jquery-uploadify";rl.makeShim(e,{deps:["open.jquery.jquery","css>open.uploadify.uploadify"],exports:"jQuery"});var n,r={"en-us":"browse_btn_en.gif","zh-cn":"browse_btn_cn.gif"};return{getOptions:function(e){return n||(n=r[rl.getG11nDefaultLanguage()]||r["en-us"]),rl.ext({buttonImage:rl.mapPath("res/image/default/inputs/"+n),swf:rl.mapPath("open/uploadify/uploadify.swf")},e)}}});