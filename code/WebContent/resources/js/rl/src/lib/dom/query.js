orderjs.define("lib.dom.query",["lib.dom.engine"],function(){!function(){function e(e,t,n,r,o,i){for(var u=0,a=r.length;a>u;u++){var l=r[u];if(l){var c=!1;for(l=l[e];l;){if(l.sizcache===n){c=r[l.sizset];break}if(1!==l.nodeType||i||(l.sizcache=n,l.sizset=u),l.nodeName.toLowerCase()===t){c=l;break}l=l[e]}r[u]=c}}}function t(e,t,n,r,o,i){for(var u=0,a=r.length;a>u;u++){var l=r[u];if(l){var f=!1;for(l=l[e];l;){if(l.sizcache===n){f=r[l.sizset];break}if(1===l.nodeType)if(i||(l.sizcache=n,l.sizset=u),"string"!=typeof t){if(l===t){f=!0;break}}else if(c.filter(t,[l]).length>0){f=l;break}l=l[e]}r[u]=f}}}var n=/((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^\[\]]*\]|['"][^'"]*['"]|[^\[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g,r=0,o=Object.prototype.toString,i=!1,u=!0,a=/\\/g,l=/\W/;[0,0].sort(function(){return u=!1,0});var c=function(e,t,r,i){r=r||[],t=t||document;var u=t;if(1!==t.nodeType&&9!==t.nodeType)return[];if(!e||"string"!=typeof e)return r;var a,l,d,p,h,g,y,N,b=!0,T=c.isXML(t),E=[],C=e;do if(n.exec(""),a=n.exec(C),a&&(C=a[3],E.push(a[1]),a[2])){p=a[3];break}while(a);if(E.length>1&&s.exec(e))if(2===E.length&&f.relative[E[0]])l=v(E[0]+E[1],t);else for(l=f.relative[E[0]]?[t]:c(E.shift(),t);E.length;)e=E.shift(),f.relative[e]&&(e+=E.shift()),l=v(e,l);else if(!i&&E.length>1&&9===t.nodeType&&!T&&f.match.ID.test(E[0])&&!f.match.ID.test(E[E.length-1])&&(h=c.find(E.shift(),t,T),t=h.expr?c.filter(h.expr,h.set)[0]:h.set[0]),t)for(h=i?{expr:E.pop(),set:m(i)}:c.find(E.pop(),1!==E.length||"~"!==E[0]&&"+"!==E[0]||!t.parentNode?t:t.parentNode,T),l=h.expr?c.filter(h.expr,h.set):h.set,E.length>0?d=m(l):b=!1;E.length;)g=E.pop(),y=g,f.relative[g]?y=E.pop():g="",null==y&&(y=t),f.relative[g](d,y,T);else d=E=[];if(d||(d=l),d||c.error(g||e),"[object Array]"===o.call(d))if(b)if(t&&1===t.nodeType)for(N=0;null!=d[N];N++)d[N]&&(d[N]===!0||1===d[N].nodeType&&c.contains(t,d[N]))&&r.push(l[N]);else for(N=0;null!=d[N];N++)d[N]&&1===d[N].nodeType&&r.push(l[N]);else r.push.apply(r,d);else m(d,r);return p&&(c(p,u,r,i),c.uniqueSort(r)),r};c.uniqueSort=function(e){if(g&&(i=u,e.sort(g),i))for(var t=1;t<e.length;t++)e[t]===e[t-1]&&e.splice(t--,1);return e},c.matches=function(e,t){return c(e,null,null,t)},c.matchesSelector=function(e,t){return c(t,null,null,[e]).length>0},c.find=function(e,t,n){var r;if(!e)return[];for(var o=0,i=f.order.length;i>o;o++){var u,l=f.order[o];if(u=f.leftMatch[l].exec(e)){var c=u[1];if(u.splice(1,1),"\\"!==c.substr(c.length-1)&&(u[1]=(u[1]||"").replace(a,""),r=f.find[l](u,t,n),null!=r)){e=e.replace(f.match[l],"");break}}}return r||(r="undefined"!=typeof t.getElementsByTagName?t.getElementsByTagName("*"):[]),{set:r,expr:e}},c.filter=function(e,t,n,r){for(var o,i,u=e,a=[],l=t,s=t&&t[0]&&c.isXML(t[0]);e&&t.length;){for(var d in f.filter)if(null!=(o=f.leftMatch[d].exec(e))&&o[2]){var p,m,h=f.filter[d],g=o[1];if(i=!1,o.splice(1,1),"\\"===g.substr(g.length-1))continue;if(l===a&&(a=[]),f.preFilter[d])if(o=f.preFilter[d](o,l,n,a,r,s)){if(o===!0)continue}else i=p=!0;if(o)for(var y=0;null!=(m=l[y]);y++)if(m){p=h(m,o,y,l);var v=r^!!p;n&&null!=p?v?i=!0:l[y]=!1:v&&(a.push(m),i=!0)}if(void 0!==p){if(n||(l=a),e=e.replace(f.match[d],""),!i)return[];break}}if(e===u){if(null!=i)break;c.error(e)}u=e}return l},c.error=function(e){throw"Syntax error, unrecognized expression: "+e};var f=c.selectors={order:["ID","NAME","TAG"],match:{ID:/#((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,CLASS:/\.((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,NAME:/\[name=['"]*((?:[\w\u00c0-\uFFFF\-]|\\.)+)['"]*\]/,ATTR:/\[\s*((?:[\w\u00c0-\uFFFF\-]|\\.)+)\s*(?:(\S?=)\s*(?:(['"])(.*?)\3|(#?(?:[\w\u00c0-\uFFFF\-]|\\.)*)|)|)\s*\]/,TAG:/^((?:[\w\u00c0-\uFFFF\*\-]|\\.)+)/,CHILD:/:(only|nth|last|first)-child(?:\(\s*(even|odd|(?:[+\-]?\d+|(?:[+\-]?\d*)?n\s*(?:[+\-]\s*\d+)?))\s*\))?/,POS:/:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^\-]|$)/,PSEUDO:/:((?:[\w\u00c0-\uFFFF\-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/},leftMatch:{},attrMap:{"class":"className","for":"htmlFor"},attrHandle:{href:function(e){return e.getAttribute("href")},type:function(e){return e.getAttribute("type")}},relative:{"+":function(e,t){var n="string"==typeof t,r=n&&!l.test(t),o=n&&!r;r&&(t=t.toLowerCase());for(var i,u=0,a=e.length;a>u;u++)if(i=e[u]){for(;(i=i.previousSibling)&&1!==i.nodeType;);e[u]=o||i&&i.nodeName.toLowerCase()===t?i||!1:i===t}o&&c.filter(t,e,!0)},">":function(e,t){var n,r="string"==typeof t,o=0,i=e.length;if(r&&!l.test(t)){for(t=t.toLowerCase();i>o;o++)if(n=e[o]){var u=n.parentNode;e[o]=u.nodeName.toLowerCase()===t?u:!1}}else{for(;i>o;o++)n=e[o],n&&(e[o]=r?n.parentNode:n.parentNode===t);r&&c.filter(t,e,!0)}},"":function(n,o,i){var u,a=r++,c=t;"string"!=typeof o||l.test(o)||(o=o.toLowerCase(),u=o,c=e),c("parentNode",o,a,n,u,i)},"~":function(n,o,i){var u,a=r++,c=t;"string"!=typeof o||l.test(o)||(o=o.toLowerCase(),u=o,c=e),c("previousSibling",o,a,n,u,i)}},find:{ID:function(e,t,n){if("undefined"!=typeof t.getElementById&&!n){var r=t.getElementById(e[1]);return r&&r.parentNode?[r]:[]}},NAME:function(e,t){if("undefined"!=typeof t.getElementsByName){for(var n=[],r=t.getElementsByName(e[1]),o=0,i=r.length;i>o;o++)r[o].getAttribute("name")===e[1]&&n.push(r[o]);return 0===n.length?null:n}},TAG:function(e,t){return"undefined"!=typeof t.getElementsByTagName?t.getElementsByTagName(e[1]):void 0}},preFilter:{CLASS:function(e,t,n,r,o,i){if(e=" "+e[1].replace(a,"")+" ",i)return e;for(var u,l=0;null!=(u=t[l]);l++)u&&(o^(u.className&&(" "+u.className+" ").replace(/[\t\n\r]/g," ").indexOf(e)>=0)?n||r.push(u):n&&(t[l]=!1));return!1},ID:function(e){return e[1].replace(a,"")},TAG:function(e){return e[1].replace(a,"").toLowerCase()},CHILD:function(e){if("nth"===e[1]){e[2]||c.error(e[0]),e[2]=e[2].replace(/^\+|\s*/g,"");var t=/(-?)(\d*)(?:n([+\-]?\d*))?/.exec("even"===e[2]&&"2n"||"odd"===e[2]&&"2n+1"||!/\D/.test(e[2])&&"0n+"+e[2]||e[2]);e[2]=t[1]+(t[2]||1)-0,e[3]=t[3]-0}else e[2]&&c.error(e[0]);return e[0]=r++,e},ATTR:function(e,t,n,r,o,i){var u=e[1]=e[1].replace(a,"");return!i&&f.attrMap[u]&&(e[1]=f.attrMap[u]),e[4]=(e[4]||e[5]||"").replace(a,""),"~="===e[2]&&(e[4]=" "+e[4]+" "),e},PSEUDO:function(e,t,r,o,i){if("not"===e[1]){if(!((n.exec(e[3])||"").length>1||/^\w/.test(e[3]))){var u=c.filter(e[3],t,r,!0^i);return r||o.push.apply(o,u),!1}e[3]=c(e[3],null,null,t)}else if(f.match.POS.test(e[0])||f.match.CHILD.test(e[0]))return!0;return e},POS:function(e){return e.unshift(!0),e}},filters:{enabled:function(e){return e.disabled===!1&&"hidden"!==e.type},disabled:function(e){return e.disabled===!0},checked:function(e){return e.checked===!0},selected:function(e){return e.parentNode&&e.parentNode.selectedIndex,e.selected===!0},parent:function(e){return!!e.firstChild},empty:function(e){return!e.firstChild},has:function(e,t,n){return!!c(n[3],e).length},header:function(e){return/h\d/i.test(e.nodeName)},text:function(e){var t=e.getAttribute("type"),n=e.type;return"input"===e.nodeName.toLowerCase()&&"text"===n&&(t===n||null===t)},radio:function(e){return"input"===e.nodeName.toLowerCase()&&"radio"===e.type},checkbox:function(e){return"input"===e.nodeName.toLowerCase()&&"checkbox"===e.type},file:function(e){return"input"===e.nodeName.toLowerCase()&&"file"===e.type},password:function(e){return"input"===e.nodeName.toLowerCase()&&"password"===e.type},submit:function(e){var t=e.nodeName.toLowerCase();return("input"===t||"button"===t)&&"submit"===e.type},image:function(e){return"input"===e.nodeName.toLowerCase()&&"image"===e.type},reset:function(e){var t=e.nodeName.toLowerCase();return("input"===t||"button"===t)&&"reset"===e.type},button:function(e){var t=e.nodeName.toLowerCase();return"input"===t&&"button"===e.type||"button"===t},input:function(e){return/input|select|textarea|button/i.test(e.nodeName)},focus:function(e){return e===e.ownerDocument.activeElement}},setFilters:{first:function(e,t){return 0===t},last:function(e,t,n,r){return t===r.length-1},even:function(e,t){return t%2===0},odd:function(e,t){return t%2===1},lt:function(e,t,n){return t<n[3]-0},gt:function(e,t,n){return t>n[3]-0},nth:function(e,t,n){return n[3]-0===t},eq:function(e,t,n){return n[3]-0===t}},filter:{PSEUDO:function(e,t,n,r){var o=t[1],i=f.filters[o];if(i)return i(e,n,t,r);if("contains"===o)return(e.textContent||e.innerText||c.getText([e])||"").indexOf(t[3])>=0;if("not"===o){for(var u=t[3],a=0,l=u.length;l>a;a++)if(u[a]===e)return!1;return!0}c.error(o)},CHILD:function(e,t){var n=t[1],r=e;switch(n){case"only":case"first":for(;r=r.previousSibling;)if(1===r.nodeType)return!1;if("first"===n)return!0;r=e;case"last":for(;r=r.nextSibling;)if(1===r.nodeType)return!1;return!0;case"nth":var o=t[2],i=t[3];if(1===o&&0===i)return!0;var u=t[0],a=e.parentNode;if(a&&(a.sizcache!==u||!e.nodeIndex)){var l=0;for(r=a.firstChild;r;r=r.nextSibling)1===r.nodeType&&(r.nodeIndex=++l);a.sizcache=u}var c=e.nodeIndex-i;return 0===o?0===c:c%o===0&&c/o>=0}},ID:function(e,t){return 1===e.nodeType&&e.getAttribute("id")===t},TAG:function(e,t){return"*"===t&&1===e.nodeType||e.nodeName.toLowerCase()===t},CLASS:function(e,t){return(" "+(e.className||e.getAttribute("class"))+" ").indexOf(t)>-1},ATTR:function(e,t){var n=t[1],r=f.attrHandle[n]?f.attrHandle[n](e):null!=e[n]?e[n]:e.getAttribute(n),o=r+"",i=t[2],u=t[4];return null==r?"!="===i:"="===i?o===u:"*="===i?o.indexOf(u)>=0:"~="===i?(" "+o+" ").indexOf(u)>=0:u?"!="===i?o!==u:"^="===i?0===o.indexOf(u):"$="===i?o.substr(o.length-u.length)===u:"|="===i?o===u||o.substr(0,u.length+1)===u+"-":!1:o&&r!==!1},POS:function(e,t,n,r){var o=t[2],i=f.setFilters[o];return i?i(e,n,t,r):void 0}}},s=f.match.POS,d=function(e,t){return"\\"+(t-0+1)};for(var p in f.match)f.match[p]=new RegExp(f.match[p].source+/(?![^\[]*\])(?![^\(]*\))/.source),f.leftMatch[p]=new RegExp(/(^(?:.|\r|\n)*?)/.source+f.match[p].source.replace(/\\(\d+)/g,d));var m=function(e,t){return e=Array.prototype.slice.call(e,0),t?(t.push.apply(t,e),t):e};try{Array.prototype.slice.call(document.documentElement.childNodes,0)[0].nodeType}catch(h){m=function(e,t){var n=0,r=t||[];if("[object Array]"===o.call(e))Array.prototype.push.apply(r,e);else if("number"==typeof e.length)for(var i=e.length;i>n;n++)r.push(e[n]);else for(;e[n];n++)r.push(e[n]);return r}}var g,y;document.documentElement.compareDocumentPosition?g=function(e,t){return e===t?(i=!0,0):e.compareDocumentPosition&&t.compareDocumentPosition?4&e.compareDocumentPosition(t)?-1:1:e.compareDocumentPosition?-1:1}:(g=function(e,t){if(e===t)return i=!0,0;if(e.sourceIndex&&t.sourceIndex)return e.sourceIndex-t.sourceIndex;var n,r,o=[],u=[],a=e.parentNode,l=t.parentNode,c=a;if(a===l)return y(e,t);if(!a)return-1;if(!l)return 1;for(;c;)o.unshift(c),c=c.parentNode;for(c=l;c;)u.unshift(c),c=c.parentNode;n=o.length,r=u.length;for(var f=0;n>f&&r>f;f++)if(o[f]!==u[f])return y(o[f],u[f]);return f===n?y(e,u[f],-1):y(o[f],t,1)},y=function(e,t,n){if(e===t)return n;for(var r=e.nextSibling;r;){if(r===t)return-1;r=r.nextSibling}return 1}),c.getText=function(e){for(var t,n="",r=0;e[r];r++)t=e[r],3===t.nodeType||4===t.nodeType?n+=t.nodeValue:8!==t.nodeType&&(n+=c.getText(t.childNodes));return n},function(){var e=document.createElement("div"),t="script"+(new Date).getTime(),n=document.documentElement;e.innerHTML="<a name='"+t+"'/>",n.insertBefore(e,n.firstChild),document.getElementById(t)&&(f.find.ID=function(e,t,n){if("undefined"!=typeof t.getElementById&&!n){var r=t.getElementById(e[1]);return r?r.id===e[1]||"undefined"!=typeof r.getAttributeNode&&r.getAttributeNode("id").nodeValue===e[1]?[r]:void 0:[]}},f.filter.ID=function(e,t){var n="undefined"!=typeof e.getAttributeNode&&e.getAttributeNode("id");return 1===e.nodeType&&n&&n.nodeValue===t}),n.removeChild(e),n=e=null}(),function(){var e=document.createElement("div");e.appendChild(document.createComment("")),e.getElementsByTagName("*").length>0&&(f.find.TAG=function(e,t){var n=t.getElementsByTagName(e[1]);if("*"===e[1]){for(var r=[],o=0;n[o];o++)1===n[o].nodeType&&r.push(n[o]);n=r}return n}),e.innerHTML="<a href='#'></a>",e.firstChild&&"undefined"!=typeof e.firstChild.getAttribute&&"#"!==e.firstChild.getAttribute("href")&&(f.attrHandle.href=function(e){return e.getAttribute("href",2)}),e=null}(),document.querySelectorAll&&!function(){var e=c,t=document.createElement("div"),n="__sizzle__";if(t.innerHTML="<p class='TEST'></p>",!t.querySelectorAll||0!==t.querySelectorAll(".TEST").length){c=function(t,r,o,i){if(r=r||document,!i&&!c.isXML(r)){var u=/^(\w+$)|^\.([\w\-]+$)|^#([\w\-]+$)/.exec(t);if(u&&(1===r.nodeType||9===r.nodeType)){if(u[1])return m(r.getElementsByTagName(t),o);if(u[2]&&f.find.CLASS&&r.getElementsByClassName)return m(r.getElementsByClassName(u[2]),o)}if(9===r.nodeType){if("body"===t&&r.body)return m([r.body],o);if(u&&u[3]){var a=r.getElementById(u[3]);if(!a||!a.parentNode)return m([],o);if(a.id===u[3])return m([a],o)}try{return m(r.querySelectorAll(t),o)}catch(l){}}else if(1===r.nodeType&&"object"!==r.nodeName.toLowerCase()){var s=r,d=r.getAttribute("id"),p=d||n,h=r.parentNode,g=/^\s*[+~]/.test(t);d?p=p.replace(/'/g,"\\$&"):r.setAttribute("id",p),g&&h&&(r=r.parentNode);try{if(!g||h)return m(r.querySelectorAll("[id='"+p+"'] "+t),o)}catch(y){}finally{d||s.removeAttribute("id")}}}return e(t,r,o,i)};for(var r in e)c[r]=e[r];t=null}}(),function(){var e=document.documentElement,t=e.matchesSelector||e.mozMatchesSelector||e.webkitMatchesSelector||e.msMatchesSelector;if(t){var n=!t.call(document.createElement("div"),"div"),r=!1;try{t.call(document.documentElement,"[test!='']:sizzle")}catch(o){r=!0}c.matchesSelector=function(e,o){if(o=o.replace(/\=\s*([^'"\]]*)\s*\]/g,"='$1']"),!c.isXML(e))try{if(r||!f.match.PSEUDO.test(o)&&!/!=/.test(o)){var i=t.call(e,o);if(i||!n||e.document&&11!==e.document.nodeType)return i}}catch(u){}return c(o,null,null,[e]).length>0}}}(),function(){var e=document.createElement("div");e.innerHTML="<div class='test e'></div><div class='test'></div>",e.getElementsByClassName&&0!==e.getElementsByClassName("e").length&&(e.lastChild.className="e",1!==e.getElementsByClassName("e").length&&(f.order.splice(1,0,"CLASS"),f.find.CLASS=function(e,t,n){return"undefined"==typeof t.getElementsByClassName||n?void 0:t.getElementsByClassName(e[1])},e=null))}(),c.contains=document.documentElement.contains?function(e,t){return e!==t&&(e.contains?e.contains(t):!0)}:document.documentElement.compareDocumentPosition?function(e,t){return!!(16&e.compareDocumentPosition(t))}:function(){return!1},c.isXML=function(e){var t=(e?e.ownerDocument||e:0).documentElement;return t?"HTML"!==t.nodeName:!1};var v=function(e,t){for(var n,r=[],o="",i=t.nodeType?[t]:t;n=f.match.PSEUDO.exec(e);)o+=n[0],e=e.replace(f.match.PSEUDO,"");e=f.relative[e]?e+"*":e;for(var u=0,a=i.length;a>u;u++)c(e,i[u],r);return c.filter(o,r)};rl.dom.queryNodes=rl.sizzle=c}()});