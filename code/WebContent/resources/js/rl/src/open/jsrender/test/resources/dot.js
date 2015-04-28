// doT.js
// 2011, Laura Doktorova
// https://github.com/olado/doT
//
// doT is a custom blend of templating functions from jQote2.js
// (jQuery plugin) by aefxx (http://aefxx.com/jquery-plugins/jqote2/)
// and underscore.js (http://documentcloud.github.com/underscore/)
// plus extensions.
//
// Licensed under the MIT license.
//
!function(){function resolveDefs(define,use,str,defs){return str.replace(define,function(e,t,n){return t in defs||(defs[t]=n),""}).replace(use,function(match,code){var value;with(defs)try{value=eval(code)}catch(e){value=""}return value?resolveDefs(define,use,value.toString(),defs):value})}var doT={version:"0.1.5"};"undefined"!=typeof module&&module.exports?module.exports=doT:this.doT=doT,doT.templateSettings={evaluate:/\{\{([\s\S]+?)\}\}/g,interpolate:/\{\{=([\s\S]+?)\}\}/g,encode:/\{\{!([\s\S]+?)\}\}/g,use:/\{\{#([\s\S]+?)\}\}/g,define:/\{\{#\s*([\w$]+)\s*\:([\s\S]+?)#\}\}/g,varname:"it",strip:!0,append:!0},doT.template=function(e,t,n){t=t||doT.templateSettings;var r=t.append?"'+(":"';out+=(",o=t.append?")+'":");out+='",a=t.use||t.define?resolveDefs(t.define,t.use,e,n||{}):e;a=("var out='"+(t.strip?a.replace(/\s*<!\[CDATA\[\s*|\s*\]\]>\s*|[\r\n\t]|(\/\*[\s\S]*?\*\/)/g,""):a).replace(/\\/g,"\\\\").replace(/'/g,"\\'").replace(t.interpolate,function(e,t){return r+t.replace(/\\'/g,"'").replace(/\\\\/g,"\\").replace(/[\r\t\n]/g," ")+o}).replace(t.encode,function(e,t){return r+t.replace(/\\'/g,"'").replace(/\\\\/g,"\\").replace(/[\r\t\n]/g," ")+").toString().replace(/&(?!\\w+;)/g, '&#38;').split('<').join('&#60;').split('>').join('&#62;').split('\"').join('&#34;').split(\"'\").join('&#39;').split('/').join('&#x2F;'"+o}).replace(t.evaluate,function(e,t){return"';"+t.replace(/\\'/g,"'").replace(/\\\\/g,"\\").replace(/[\r\t\n]/g," ")+"out+='"})+"';return out;").replace(/\n/g,"\\n").replace(/\t/g,"\\t").replace(/\r/g,"\\r").split("out+='';").join("").split("var out='';out+=").join("var out=");try{return new Function(t.varname,a)}catch(l){throw"undefined"!=typeof console&&console.log("Could not create a template function: "+a),l}}}();