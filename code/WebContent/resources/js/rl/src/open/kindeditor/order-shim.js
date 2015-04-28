/**
 * @fileOverview Shim kindeditor.
 * @change
 	#1 by prcjack @2014/04/28 Creates file.
 */
orderjs.define("open.kindeditor.order-shim",["RealLight"],function(){var e="open.kindeditor.lang.",n={"en-us":e+"en","zh-cn":e+"zh_CN"},d=n[rl.getG11nDefaultLanguage()];rl.makeShim(d,{deps:["open.kindeditor.kindeditor","css>open.kindeditor.themes.default.default"],exports:"KindEditor"})});