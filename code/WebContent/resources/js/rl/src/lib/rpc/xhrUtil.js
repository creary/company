orderjs.define("lib.rpc.xhrUtil",["lib.rpc.engine"],function(){rl.ext(rl.rpc,function(){var getRsp=function(r,e,n){var t=rl.ext({method:"get",listen:!1,cache:!1},n);t.async=!1;try{var s=rl.rpc.xhr(e,t);return s[r]}catch(o){}};return{getResponseText:Function.curry(getRsp,"responseText"),getResponseXml:Function.curry(getRsp,"responseXML"),getResponseJson:function(url,options){return eval("("+getRsp("responseText",url,options)+")")},confirmResponse:function(r,e,n){try{var t=rl.rpc.getResponseText(r,n).trim().toLowerCase();return t==e.trim().toLowerCase()}catch(s){return!1}}}}()),rl.ext(rl.rpc,function(){var r=function(r,e,n){var t=rl.ext(n||{},{async:!0,onSuccess:e});try{return rl.rpc.xhr(r,t)}catch(s){}};return{onResponseText:function(e,n,t){var s=function(r){n(r.responseText)};return r(e,s,t)},onResponseXml:function(e,n,t){var s=function(r){n(r.responseXML)};return r(e,s,t)},onResponseJson:function(url,successHandler,options){var h=function(req){successHandler(eval("("+req.responseText+")"))};return r(url,h,options)}}}())});