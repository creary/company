/**
 * @fileOverview Engine for RealLight framework
 * @author prcjack
 * @change
 	#1 by prcjack Creates file.
	#2 by prcjack Fix rl.util.Event fire() bug.
	#3 by prcjack @2014/06/24 
		Removed overriding orderjs.root config.
		Removed the rootNS "open".
		Use new rootNS "rl" to load css modules: "css>rl:button";
	#4 by prcjack @2014/07/07 Fix ie detection for ie11.
 */
orderjs.define("RealLight",function(){function gEval(code){return eval(code)}!function(){function r(r,t){if(!r||"object"!=typeof t)return r;for(var n in t)r[n]=t[n];return r}function t(r,t){if(!r||"object"!=typeof t)return r;for(var n in t)l(r[n])||(r[n]=t[n]);return r}function n(r){return!(!r||r.constructor!==Array)}function e(r){return r&&1==r.nodeType}function i(r){return"function"==typeof r}function o(r){return r&&"object"==typeof r}function u(r){return"string"==typeof r||r&&r.constructor===String}function s(r){var t=typeof r;return("number"===t||r&&r.constructor===Number)&&isFinite(r)}function a(r){return"string"==typeof r?!!r:r instanceof String&&""!==String(r)}function l(r){var t;return null!==r&&r!==t}function c(r){var t;return null===r||r===t||""===r}function f(r){return rl.isString(r)?r.replace(B,""):r}function p(r,t,n){if(o(r))if(L&&r.forEach===L)r.forEach(t,n);else if(r.length>-1){for(var e=0,i=r.length;i>e;e++)if(t.call(n,r[e],e,r)===!1)return}else for(var e in r)if(t.call(n,r[e],e,r)===!1)return}function g(r,t,n){var e=[];return o(r)?O&&r.map===O?r.map(t,n):(p(r,function(i,o){e.push(t.call(n,i,o,r))}),e):e}function h(r,t,n){var e=[];return o(r)?T&&r.filter===T?r.filter(t,n):(p(r,function(i,o){t.call(n,i,o,r)&&e.push(i)}),e):e}function d(r,t,n){var e=!0;return o(r)?_&&r.every===_?r.every(t,n):(p(r,function(i,o){return t.call(n,i,o,r)?void 0:(e=!1,!1)}),e):e}function v(r,t,n){var e;return o(r)?(p(r,function(i,o){return t.call(n,i,o,r)?(e=o,!1):void 0}),e):e}function m(r,t,n){var e=!0;return o(r)?P&&r.some===P?r.some(t,n):(e=v(r,t,n),isNaN(e)?!!e:e>-1):e}function y(r,t,n){var e=null;return o(r)?(e=v(r,t,n),r[e]):e}function N(r,t,n){isNaN(t)&&(t=0);var e=[t];return isNaN(n)||e.push(n),F.apply(r,e)}function S(r,t){if(C&&r.bind===C)return C.apply(r,rl.slice(arguments,1));var n=rl.slice(arguments,2);return function(){return r.apply(t,n.concat(rl.slice(arguments)))}}function E(r){var t=rl.slice(arguments,1);return function(){return r.apply(this,t.concat(rl.slice(arguments)))}}function w(r,t){return rl.delay(r,0,t)}function b(r,t,n){function e(){r.apply(n,i)}var i=rl.slice(arguments,3);return rl.isNonNullStr(r)&&(r=new Function(r)),t>=0?setTimeout(e,t):(e(),0)}function j(r,t,n){function e(){r.apply(n,i)}if(isNaN(t))return 0;rl.isNonNullStr(r)&&(r=new Function(r));var i=rl.slice(arguments,3);return setInterval(e,t)}function A(){return syncXmlHttp||(syncXmlHttp=rl.createXHRTransport()),syncXmlHttp}if("undefined"==typeof rl){var R=Array.prototype,x=Function.prototype,L=R.forEach,O=R.map,T=R.filter,_=R.every,P=R.some,F=R.slice,C=x.bind,M=new Date,H={},q=[],I=new Function,X={},B=/(^\s+)|\s+$/g,k=0,D={cfgModule:"",debugMode:!1,disableCache:!1,lang:"auto",autoLoadLang:!0,theme:"",projectName:"",pageInc:"",jsRoot:"",cssRoot:"",imageRoot:""};window.rl={version:"2.2.67",loadedStart:M,config:D,raisingErr:null,raisingErrStack:[],EMPTY_OBJECT:H,EMPTY_ARRAY:q,EMPTY_FUNCTION:I,BREAK_EACH:H,_debugMsgStore:[],ext:r,fill:t,createClass:function(r){var t,n=r||{},e=n.parent,i=n.members,o=rl.isFunction(n.construct)?n.construct:new Function;if(rl.isFunction(e)){var u=new e(X);o.prototype=u,u.constructor=o,o.parent=e}return t=o.prototype,i&&(rl.ext(t,i),rl.IE&&i.toString!=H.toString&&(t.toString=i.toString)),o},isPrototyping:function(r){return r==X},createNamespace:function(r,t){if(!rl.isNonNullStr(r))return null;for(var n,e=r.split("."),i=window,o={}.toString,u=0,s=e.length;s>u;u++)n=e[u],i[n]||(i[n]={toString:function(r){return function(){return"[object "+r+"]"}}(e.slice(0,u+1).join("."))}),i=i[n];if(t)for(var u in t)"toString"==u&&t.toString!==o?i.toString=t.toString:i[u]=t[u];return i},createNamespaces:function(){rl.each(arguments,rl.createNamespace)},getDom:function(r){var t;return r?t="string"==typeof r?document.getElementById(r):r:null},autoId:function(r){return r=r||"rl_",r+ ++k},isArray:n,isElement:e,isFunction:i,isObject:o,isString:u,isNumber:s,isNonNullStr:a,isDefined:l,isEmpty:c,trim:f,each:p,map:g,filter:h,some:m,find:y,every:d,firstFit:v,slice:N,bind:S,curry:E,partial:E,defer:w,delay:b,interval:j,debug:function(r,t){rl._debugMsgStore.push([r,t])},dir:I,raiseError:function(r,t,n){throw r instanceof Error||(r=new Error(""+r)),rl.raisingErr==r?rl.raisingErrStack.push(t):(rl.raisingErrStack=[(n?n+" : ":"")+(r.message||r.description),t],rl.raisingErr=r),r},syncLoadText:function(r){if(!rl.isNonNullStr(r))return"";var t=A();if(!t)throw{description:"Non-support XMLHttpRequest!"};var n="";t.open("GET",r,!1);try{t.send(null)}catch(e){throw t.abort(),e}return 4==t.readyState&&rl.isRequestOk(t)&&(n=t.responseText),n},createXHRTransport:function(){if("undefined"!=typeof XMLHttpRequest)return new XMLHttpRequest;for(var r,t,n=["MSXML2.XMLHTTP.3.0","MSXML2.XMLHTTP","Microsoft.XMLHTTP"],e=0;e<n.length;e++){t=n[e];try{if(r=new ActiveXObject(t))return XMLHttpRequest=function(){return new ActiveXObject(t)},r}catch(i){}}},isRequestOk:function(r){var t=r.status||0;return t>=200&&300>t||304==t||1223==t||!t&&("file:"==location.protocol||"chrome:"==location.protocol)},onReady:orderjs.ready,onCssReady:orderjs.ready,getTopRLWindow:function(){var r,t=window.top;try{if(t.rl)return t;for(var n=window.parent;n;){if(n.rl){r=n;break}if(n==t)break;n=n.parent}}catch(e){}return r||(r=window),r},isTopRLWindow:function(){return rl.getTopRLWindow()==window},toString:function(){return"[Object RealLight JS Framework]"}},rl.ext(rl,function(){var r=navigator.userAgent.toLowerCase(),t=/(msie\s|trident.*rv:)([\w.]+)/,n=t.exec(r),e=!!n,i=n?parseFloat(n[2]):-1,o=!!(r.indexOf("safari")>-1),u=!!window.opera;return{IE:e,OPERA:u,GECKO:!(!(r.indexOf("gecko")>-1)||o),SAFARI:o,VER_IE:i,support:{}}}()),window.onerror=function(r,t,n){var e=["ErrorMsg: "+r,"ErrorURL: "+t,"ErrorLine: "+n];return rl.debug(e.join("<br />"),"err"),!0}}}(),rl.createNamespaces("rl.data","rl.rpc","rl.util","rl.util.nyuWa","rl.fx","rl.dom","rl.gui","rlx"),rl.fill(String.prototype,{bLen:function(){return this.replace(/[^\x00-\xff]/g,"aa").length},bSubstring:function(r,t){var n=lenB=0;if(isNaN(r)&&(r=0),isNaN(t)&&(t=this.bLen()),r>t){var e=t;t=r,r=e}for(;r>lenB;)lenB+=this.charCodeAt(n)>255?2:1,n++;for(r=n;t>lenB;)lenB+=this.charCodeAt(n)>255?2:1,n++;return t=n,this.substring(r,t)},camelize:function(){var r,t,n=this.split("-"),e=n[0];for(r=1;r<n.length;r++)t=n[r].charAt(0),e+=n[r].replace(t,t.toUpperCase());return e},capitalize:function(){return this.charAt(0).toUpperCase()+this.substr(1)},contains:function(r){return-1!==this.indexOf(r)},equalsIgnoreCase:function(r){return rl.isString(r)?this.toLowerCase()==r.toLowerCase():!1},endsWith:function(r){var t=this.length;return this.substring(t-r.length,t)==r},startsWith:function(r){return 0==this.indexOf(r)},times:function(r){return r=parseInt(r),isNaN(r)&&(r=0),new Array(r+1).join(this)},trim:function(){return this.replace(/(^\s+)|\s+$/g,"")}}),rl.ext(String,{ScriptFragment:"<script[^>]*>([\\S\\s]*?)</script>",HtmlFragment:"<html[\\S\\s]*<\\/html>",isHtml:function(r){return rl.isNonNullStr(r)&&new RegExp(String.HtmlFragment,"im").test(r)},stripScripts:function(r){return r.replace(new RegExp(String.ScriptFragment,"img"),"")},extractScripts:function(r){var t=new RegExp(String.ScriptFragment,"img"),n=new RegExp(String.ScriptFragment,"im");return Array.map(function(r){return(r.match(n)||["",""])[1]},r.match(t)||[])},evalScripts:function(r){var t=rl.VER_IE>0&&rl.VER_IE<9?window.execScript:window.eval;return Array.map(function(r){return t(r)},String.extractScripts(r))}}),rl.fill(Array.prototype,{contains:function(r){return-1!=this.indexOf(r)},concatAt:function(r,t){if(!r)return this;if(isNaN(t)&&(t=0),rl.isObject(r)&&rl.isDefined(r.length)&&!rl.isString(r))for(var n=0,e=r.length;e>n;n++)this.insertAt(r[n],n+t);else this.insertAt(r,t);return this},indexOf:function(r,t){var n=this.length;isNaN(t)?t=0:0>t&&(t=Math.max(0,n+t));for(var e=t;n>e;e++)if(this[e]===r)return e;return-1},insertAt:function(r,t){return this.splice(t,0,r),this},insertBefore:function(r,t){var n=this.indexOf(t);return-1==n?this.push(r):this.splice(n,0,r),this},lastIndexOf:function(r,t){var n=this.length;isNaN(t)?t=n-1:0>t&&(t=Math.max(0,n+t));for(var e=t;e>=0;--e)if(this[e]===r)return e;return-1},push:function(){for(var r=arguments.length,t=0;r>t;t++)this[this.length]=arguments[t];return this.length},remove:function(r){var t=this.indexOf(r);return-1!=t&&this.splice(t,1),r},removeAt:function(r){return this.splice(r,1)[0]},shift:function(){return this.splice(0,1)}}),rl.ext(Array,{each:function(r,t,n,e){if(t&&rl.isFunction(r))try{for(var i=e||0,o=t.length;o>i;i++)r.call(n,t[i],i,t)}catch(u){if(u!=rl.BREAK_EACH)throw u}},every:function(r,t,n,e){if(!t||!rl.isFunction(r))return!1;for(var i=e||0,o=t.length;o>i;i++)if(!r.call(n,t[i],i,t))return!1;return!0},filter:function(r,t,n,e){if(!t||!rl.isFunction(r))return[];for(var i=[],o=e||0,u=t.length;u>o;o++)r.call(n,t[o],o,t)&&i.push(t[o]);return i},map:function(r,t,n,e){if(t&&rl.isFunction(r)){for(var i=[],o=e||0,u=t.length;u>o;o++)i.push(r.call(n,t[o],o,t));return i}},indexOf:function(r,t,n,e){if(t&&rl.isFunction(r)){for(var i=e||0,o=t.length;o>i;i++)if(r.call(n,t[i],i,t))return i;return-1}},find:function(r,t,n,e){var i=Array.indexOf(r,t,n,e);return i>-1?t[i]:void 0},some:function(r,t,n,e){var i=Array.indexOf(r,t,n,e);return!!(i>-1)},invoke:function(r,t){var n=Array.slice(arguments,2);return Array.map(function(t){return t[r].apply(t,n)},t)},slice:function(r,t,n){isNaN(t)&&(t=0);var e=[t];return isNaN(n)||e.push(n),Array.prototype.slice.apply(r,e)}}),rl.ext(Function,{bind:function(r,t){if(void 0===t)return r;var n=Array.slice(arguments,2);return function(){return r.apply(t,n.concat(Array.slice(arguments)))}},fancyCurry:function(r,t){isNaN(t)&&(t=0);var n=Array.slice(arguments,2);return function(){return r.apply(this,Array.slice(arguments).concatAt(n,t))}},delay:function(r,t,n){function e(){r.apply(n,i)}var i=Array.slice(arguments,3);return rl.isNonNullStr(r)&&(r=new Function(r)),t>=0?setTimeout(e,t):(e(),0)},interval:function(r,t,n){function e(){r.apply(n,i)}if(isNaN(t))return 0;rl.isNonNullStr(r)&&(r=new Function(r));var i=Array.slice(arguments,3);return setInterval(e,t)}}),Function.curry=Function.fancyCurry(Function.fancyCurry,1,0),Function.defer=Function.fancyCurry(Function.delay,1,0),rl.util.Event=rl.createClass({construct:function(){rl.isPrototyping(arguments[0])||(this._scopes=[],this._handlers=[])},members:{addHandler:function(r,t){if(!rl.isFunction(r))return this;var n=t||null;return this._handlers.push(r),this._scopes.push(n),this},removeHandler:function(r,t){if(!rl.isFunction(r))return this;var n=this._handlers,e=this._scopes;return rl.each(n,function(i,o){return i==r&&e[o]==t?(n.removeAt(o),e.removeAt(o),!1):void 0},this),this},clear:function(){return this._scopes.length=0,this._handlers.length=0,this},fire:function(){var r,t,n=arguments,e=this._handlers.concat([]),i=this._scopes.concat([]);return rl.each(e,function(e,o){r=e.apply(i[o],n),r===!1&&(t=!1)},this),t},toString:function(){return"[object rl.util.Event]"}}}),rl.util.EventProvider=rl.createClass({construct:function(){rl.isPrototyping(arguments[0])||(this._events={})},members:{addEventListener:function(r,t,n){if(!rl.isNonNullStr(r)||!rl.isFunction(t))return this;r=r.toLowerCase();var e=this._events[r]||new rl.util.Event;return e.addHandler(t,n),this._events[r]=e,this},removeEventListener:function(r,t,n){var e=this.getEvent(r);return e&&e.removeHandler(t,n),this},clearEventListener:function(r){var t=this.getEvent(r);return t&&t.clear(),this},clearAllEvents:function(){for(var r in this._events)this.clearEventListener(r);return this},getEvent:function(r){return rl.isNonNullStr(r)?this._events[r.toLowerCase()]:void 0},fireEvent:function(r){var t=rl.slice(arguments,1),n=this.getEvent(r);return n?n.fire.apply(n,t):void 0},toString:function(){return"[object rl.util.EventProvider]"}}});var epProto=rl.util.EventProvider.prototype;epProto.on=epProto.addEventListener,epProto.un=epProto.removeEventListener,rl.sysEventMgr=rl.util.sysEventMgr=new rl.util.EventProvider,rl.util.PathMapping=rl.createClass({construct:function(r){rl.isPrototyping(arguments[0])||this.setRoot(r)},members:{_relPathRe:/\.\.\//,setRoot:function(r){if(!rl.isNonNullStr(r)){var t=location.pathname;rl.IE&&rl.VER_IE<7&&(t=t.replace(/\\/g,"/")),r=location.protocol+"//"+location.host+t}var n=r.split("/");return n.pop(),this.root=n.join("/")+"/",this},mapping:function(r){if(!rl.isNonNullStr(r))return this.root;if(r.startsWith("/"))return location.protocol+"//"+location.host+r;if(r.contains(":"))return r;var t=this.root.split("/"),n=this._relPathRe;for(t.pop();n.test(r);)t.pop(),r=r.replace(n,"");return t.join("/")+"/"+r},toString:function(){return this.root}}}),function(){var r,t={};rl.createNamespace("rl.util.g11n",{setDefaultLanguage:function(t){rl.isNonNullStr(t)&&(r=t.toLowerCase())},getDefaultLanguage:function(){return r},addSource:function(n,e){rl.isNonNullStr(e)||(e=r),e=e.toLowerCase();var i=t[e];return rl.isObject(i)?rl.ext(i,n):t[e]=n,!0},getSource:function(n,e){rl.isNonNullStr(e)||(e=r),e=e.toLowerCase();var i=t[e]||{};return i[n]}}),rl.getG11nSource=rl.util.g11n.getSource,rl.getG11nDefaultLanguage=rl.util.g11n.getDefaultLanguage}(),function(){rl.util.Template=rl.createClass({construct:function(r){rl.isPrototyping(arguments[0])||rl.isNonNullStr(r)&&(this.source=r)},members:{source:"",_re:/\{([\w-]+)(?:\:([\w\.]*)(?:\((.*?)?\))?)?\}/g,assign:function(r){return this.source.replace(this._re,function(t,n){var e=r[n];return void 0===e&&(e=""),e})},toString:function(){return"[object rl.util.Template]"}}}),rl.format=function(r,t){var n=rl.format.fly;return n.source=r,n.assign(t)},rl.format.fly=new rl.util.Template}(),function(){function r(r){if(rl.isObject(r)){{var t;rl.config}rl.each(["shim","paths","debugMode","disableCache"],function(t){rl.isDefined(r[t])&&orderjs.config(t,r[t])}),r.debugMode&&(orderjs.toString=function(){return"[object orderjs]"}),t=r.projectName,rl.isNonNullStr(t)&&(orderjs.regRootNS(t,{js:rl.mapPath(r.jsRoot),css:rl.mapPath(r.cssRoot)}),rl.createNamespace(t))}}function t(){var r,t=window.navigator,e=[n],i=rl.config,o=i.lang;rl.isNonNullStr(o)&&(o=rl.trim(o).toLowerCase(),r="auto"==o?(t.language||t.userLanguage||"zh-cn").toLowerCase():o,rl.util.g11n.setDefaultLanguage(r),i.autoLoadLang&&e.unshift("lib.util.g11n."+r)),orderjs.addBase(e)}function n(){var r=[],t=rl.config,n=t.pageInc;rl.IE&&rl.VER_IE<7&&window.top!=window&&r.push("lib.util.browser.ie6"),t.debugMode?r.push("lib.util.console"):rl.debug=rl.EMPTY_FUNCTION,rl.isNonNullStr(n)&&r.push(r.length?function(){orderjs.addBase(n)}:n),orderjs.addBase(r)}try{var e,i=orderjs.orderjsNode.getAttribute("data-rlconfig");rl.isNonNullStr(i)&&(i=rl.trim(i),e=0==i.indexOf("{")?gEval("("+i+")"):rl.createNamespace(i),rl.ext(rl.config,e))}catch(o){rl.debug(this+' Access script attribute "data-rlconfig" error: '+o,"err")}rl.ROOT_PATH=orderjs.absolutizePath("./"),rl.XROOT_PATH=orderjs.absolutizePath("../x/"),rl.pm=new rl.util.PathMapping,rl.mapPath=function(r,t){return orderjs.absolutizePath(r,t||rl.ROOT_PATH)},orderjs.config({shim:{"open.backbone.backbone":{deps:["open.underscore.underscore","open.jquery.jquery"],exports:"Backbone"},"open.jquery.jquery":{exports:"jQuery"},"open.jquery-poshytip.jquery-poshytip":{deps:["open.jquery.jquery"],exports:"jQuery"},"open.jquery-ui.jquery-ui":{deps:["open.jquery.jquery"],exports:"jQuery"},"open.raphael.raphael":{exports:"Raphael"},"open.underscore.underscore":{exports:"_"},"open.uploadify.jquery-uploadify":{deps:["open.jquery.jquery"],exports:"jQuery"},"css>rl:menu":{deps:["css>rl:button"]},"css>rl:tabpanel":{deps:["css>rl:menu"]},"css>rl:muqtle":{deps:["css>rl:tabpanel"]}}}),rl.makeShim=function(r,t){if(rl.isNonNullStr(r)&&t){var n={};return n[r]=t,orderjs.config("shim",n)}},orderjs.regRootNS("rl",{js:rl.ROOT_PATH,css:rl.ROOT_PATH+"res/css/"}),orderjs.regRootNS("x",{js:rl.XROOT_PATH,css:rl.XROOT_PATH}),rl.applyConfig=function(t){rl.ext(rl.config,t),r(t)};var u=[t],s=rl.config,a=s.cfgModule;r(s),rl.isNonNullStr(a)&&u.unshift(a),orderjs.addBase(u)}()});