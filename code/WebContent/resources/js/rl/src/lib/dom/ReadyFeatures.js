orderjs.define("lib.dom.ReadyFeatures",["lib.dom.engine"],function(){rl.onReady(function(){var e=rl.dom.domWarehouse,n=document.createElement("div"),r=document.createElement("div"),d=document.createTextNode(),t=function(e){return e.nodeValue};e.appendChild(n),e.appendChild(r),r.appendChild(d),rl.createNamespace("rl.dom.ReadyFeatures",{domBuilder:n,escapeHtml:function(e){return d.data=e,r.innerHTML},unescapeHtml:function(e){if(rl.isNonNullStr(e)){n.innerHTML=e;var r=Array.map(t,n.childNodes);return r.join("")}return String(e)}})})});