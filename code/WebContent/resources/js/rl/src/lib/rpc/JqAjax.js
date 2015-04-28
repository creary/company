/**
 * @fileOverview JqAjax class.
 * @change
	#1 by prcjack @2014/11/28 Creates file.
 */
orderjs.define("lib.rpc.JqAjax",["open.jquery.jquery"],function(){function r(r,t){return rl.isArray(r)||(r=[r]),r.concat(rl.slice(t))}function t(r){if(!rl.isObject(r)||!rl.isNonNullStr(r.url))return"";var t=r.url,s=r.data;return rl.isObject(s)&&(s=e.param(s)),rl.isNonNullStr(s)&&(t+=(t.indexOf("?")>-1?"&":"?")+s),rl.format('<a href="{0}" target="_blank">{0}</a>',[t])}var e=(this.track,orderjs("open.jquery.jquery")),s=rl.createClass({parent:rl.util.EventProvider,construct:function(r){return this instanceof s?void(rl.isPrototyping(arguments[0])||(s.parent.apply(this,arguments),this.initialize.apply(this,arguments))):new s(r)},members:{reqOptions:null,enableSDT:!0,ignoreAbortError:!0,sendForm:null,initialize:function(r){this.setOptions(r),rl.isObject(this.reqOptions)||(this.reqOptions={}),rl.fill(this.reqOptions,s.defaultReqOptions)},hndDataError:function(e,s){var i=this.requestingOptions,n=i.error||this.error;rl.config.debugMode&&rl.debug(this+" hndDataError() request = "+t(this.requestingOptions)+" msg = "+e+" status = "+s,"err"),rl.isFunction(n)&&n.apply(this,arguments),this.fireEvent.apply(this,r("error",arguments))},hndReqError:function(r,t){if(0!=r.readyState||!this.ignoreAbortError){var e=s.defaultMsgs[t];return this.hndDataError(e,t,r)}},hndReqSuccess:function(t,e,i){var n,a=this.requestingOptions,o=a.success||this.success,u=null;try{if(this.enableSDT)if(rl.isObject(t)||(t={}),u=t.data,1==t.status){{r([u],arguments)}rl.isFunction(o)&&(n=o.apply(this,r([u],arguments)))}else n=t.msg||s.defaultMsgs.dataerror;else rl.isFunction(o)&&(n=o.apply(this,arguments))}catch(l){n=s.defaultMsgs.dataerror}n!==!1&&(rl.isDefined(n)?this.hndDataError(n,e,i,u):this.fireEvent.apply(this,r(this.enableSDT?["success",u]:["success"],arguments)))},invoke:function(){return this.request.apply(this,arguments)},request:function(r){rl.isObject(r)||(r={}),this.requestingOptions=rl.fill(r,this.reqOptions),r.url||(r.url=this.url);var s=this.sendForm||r.sendForm;return!r.data&&e(s).is("form")&&(r.data=e(s).serialize()),rl.config.debugMode&&rl.debug(this+" load() Start request = "+t(this.requestingOptions)),this.jqXHR&&this.jqXHR.abort(),this.jqXHR=e.ajax(rl.fill({beforeSend:rl.bind(this.hndReqBeforeSend,this),success:rl.bind(this.hndReqSuccess,this),error:rl.bind(this.hndReqError,this),complete:rl.bind(this.hndReqComplete,this)},r)),this},setOptions:function(r){return rl.ext(this,r),this},toString:function(){return"[object JqAjax]"}}});return rl.each(["beforeSend","complete"],function(t){s.prototype["hndReq"+t.capitalize()]=function(){var e,s,i=this.requestingOptions,n=i[t]||this[t];return rl.isFunction(n)&&(e=n.apply(this,arguments)),s=this.fireEvent.apply(this,r(t,arguments)),e===!1?!1:s}}),s.request=function(){var r=new s;return r.request.apply(r,arguments)},s.defaultReqOptions={cache:!1,dataType:"json",type:"post"},s.defaultMsgs={error:"连接服务器失败！",timeout:"连接服务器超时！",parsererror:"数据解析错误！",notmodified:"数据未修改！",dataerror:"数据读取错误！"},s});