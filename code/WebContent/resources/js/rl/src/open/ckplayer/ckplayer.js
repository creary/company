/**
 * 说明:
 * ckplayer6.3,有问题请访问http://www.ckplayer.com
 * 请注意，该文件为UTF-8编码，不需要改变编码即可使用于各种编码形式的网站内	
	第一部分，加载插件
	以下为加载的插件部份
	插件的设置参数说明：
		1、插件名称
		2、水平对齐方式（0左，1中，2右）
		3、垂直对齐方式（0上，1中，2下）
		4、水平方向位置偏移量
		5、垂直方向位置偏移量
		6、插件的等级+竖线
		7、插件是否绑定在控制栏上，0不绑定，1绑定，当值是1的时候该插件将会随着控制栏一起隐藏或缓动
		插件名称尽量不要相同，对此的详细说明请到网站查看
 * @change 
 	#1 by prcjack @2014/03/08 Customize ckplayer: remove cpt list and marquee ad.
 */
function ckcpt(){return e;var e}function ckstyle(){var e={cpath:"",language:"",flashvars:"",setup:"1,1,1,1,1,2,0,1,0,0,0,1,200,0,2,1,0,1,1,1,2,10,3,0,1,2,3000,0,0,0,1,1,1,1,1,1,1,250,0",pm_bg:"0x000000,100,230,180",mylogo:"logo.swf",pm_mylogo:"1,1,-100,-55",logo:"cklogo.png",pm_logo:"2,0,-100,20",control_rel:"related.swf,/ckplayer/related.xml,0",control_pv:"Preview.swf,105,2000",pm_repc:"",pm_spac:"|",pm_fpac:"file->f",pm_advtime:"2,0,-110,10,0,300,0",pm_advstatus:"1,2,2,-200,-40",pm_advjp:"1,1,2,2,-100,-40",pm_padvc:"2,0,-10,-10",pm_advms:"2,2,-46,-56",pm_zip:"1,1,-20,-8,1,0,0",pm_advmarquee:"1,2,50,-60,50,18,0,0x000000,50,0,20,1,15,2000",advmarquee:escape('{a href="http://www.ckplayer.com"}{font color="#FFFFFF" size="12"}这里可以放文字广告，播放器默认使用这里设置的广告内容，如果不想在这里使用可以清空这里的内容，如果想在页面中实时定义滚动文字广告内容，可以清空这里的内容，然后在页面中设置广告函数。{/font}{/a}'),myweb:escape(""),cpt_lights:"1",cpt_share:"/ckplayer/share.xml",cpt_list:ckcpt()};return e}!function(){var e={_K_:function(e){return document.getElementById(e)},getVideo:function(e){var t="";if(e)for(var a=0;a<e.length;a++){var i=e[a].split("->");i&&i[0]&&(t+='<source src="'+i[0]+'"'),2==i.length&&i[1]&&(t+=' type="'+i[1]+'"'),t+=">"}return t},getVars:function(e,t){return e[t]?e[t]:void 0},getParams:function(e){var t="";return e&&(1==this.getVars(e,"p")&&1!=this.getVars(e,"m")&&(t+=' autoplay="autoplay"'),1==this.getVars(e,"e")&&(t+=' loop="loop"'),1==this.getVars(e,"m")&&(t+=' preload="meta"'),this.getVars(e,"i")&&(t+=' poster="'+this.getVars(e,"i")+'"')),t},browser:function(){var e=function(e){var t=new Object,a={msie:/msie/.test(e)&&!/opera/.test(e),opera:/opera/.test(e),safari:/webkit/.test(e)&&!/chrome/.test(e),firefox:/firefox/.test(e),chrome:/chrome/.test(e)},i="";for(var n in a)if(a[n]){i="safari"==n?"version":n;break}return a.version=i&&RegExp("(?:"+i+")[\\/: ]([\\d.]+)").test(e)?RegExp.$1:"0",a.ie=a.msie,a.ie6=a.msie&&6==parseInt(a.version,10),a.ie7=a.msie&&7==parseInt(a.version,10),a.ie8=a.msie&&8==parseInt(a.version,10),t.B=i,t.V=a.version,t}(window.navigator.userAgent.toLowerCase());return e},Platform:function(){var e="",t=navigator.userAgent,a=(navigator.appVersion,{iPhone:t.indexOf("iPhone")>-1||t.indexOf("Mac")>-1,iPad:t.indexOf("iPad")>-1,ios:!!t.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),android:t.indexOf("Android")>-1||t.indexOf("Linux")>-1,webKit:t.indexOf("AppleWebKit")>-1,gecko:t.indexOf("Gecko")>-1&&-1==t.indexOf("KHTML"),presto:t.indexOf("Presto")>-1,trident:t.indexOf("Trident")>-1,mobile:!!t.match(/AppleWebKit.*Mobile.*/)||!!t.match(/AppleWebKit/),webApp:-1==t.indexOf("Safari")});for(var i in a)if(a[i]){e=i;break}return e},getpath:function(e){var t=unescape(window.location.href).replace("file:///",""),a=parseInt(document.location.port),n=document.location.protocol+"//"+document.location.hostname,r="",o="",s="",l=0,c=e.split("//");c.length>0&&(r=c[0]+"//");var f="http|https|ftp|rtsp|mms|ftp|rtmp",p=f.split("|");for(80!=a&&a&&(n+=":"+a),i=0;i<p.length;i++)if(p[i]+"://"==r){l=1;break}if(0==l)if("/"==e.substr(0,1))s=n+e;else{o=t.substring(0,t.lastIndexOf("/")+1).replace("\\","/");var d=e.replace("../","./"),n=d.split("./"),u=n.length,c=d.replace("./",""),v=o.split("/"),h=v.length-u;for(i=0;h>i;i++)s+=v[i]+"/";s+=c}else s=e;return s},Flash:function(){var e=!1,t=0;if(document.all)try{var a=new ActiveXObject("ShockwaveFlash.ShockwaveFlash");e=!0;var i=a.GetVariable("$version");t=parseInt(i.split(" ")[1].split(",")[0])}catch(n){}else if(navigator.plugins&&navigator.plugins.length>0){var a=navigator.plugins["Shockwave Flash"];if(a){e=!0;for(var r=a.description.split(" "),o=0;o<r.length;++o)isNaN(parseInt(r[o]))||(t=parseInt(r[o]))}}return{f:e,v:t}},embedHTML5:function(e,t,a,i,n,r,o){var s="",l=this.browser().B,s=this.browser().V,c=s.split("."),f=c[0],p=l+s,d=l+f,u="",v=!1,h=this.Flash().f,m=!1;o||(o=["iPad","iPhone","ios"]);for(var g=0;g<o.length;g++)if(u=o[g],u.indexOf("+")>-1?(u=u.split("+")[0],m=!0):m=!1,this.Platform()==u||p==u||d==u||l==u){if(!m){v=!0;break}if(!h){v=!0;break}}v&&(s='<video controls id="'+t+'" width="'+a+'" height="'+i+'"'+this.getParams(r)+">"+this.getVideo(n)+"</video>",this._K_(e).innerHTML=s,this._K_(e).style.width=a+"px",this._K_(e).style.height=i+"px",this._K_(e).style.backgroundColor="#000")},getflashvars:function(e){var t="",a=0;if(e)for(var i in e)a>0&&(t+="&"),"f"==i&&e[i]&&!ckstyle().pm_repc&&(e[i]=this.getpath(e[i]),e[i].indexOf("&")>-1&&(e[i]=encodeURIComponent(e[i]))),"y"==i&&e[i]&&(e[i]=this.getpath(e[i])),t+=i+"="+e[i],a++;return t},getparam:function(e){var t="",a="",i={allowScriptAccess:"always",allowFullScreen:!0,quality:"high",bgcolor:"#000"};if(e)for(var n in e)i[n]=e[n];for(var r in i)t+=r+'="'+i[r]+'" ',a+='<param name="'+r+'" value="'+i[r]+'" />';return t=t.replace("movie=","src="),{w:t,v:a}},getObjectById:function(e){var t=null,a=this._K_(e),i="embed";if(a&&"OBJECT"==a.nodeName)if("undefined"!=typeof a.SetVariable)t=a;else{var n=a.getElementsByTagName(i)[0];n&&(t=n)}return t},embedSWF:function(e,t,a,i,n,r,o){a||(a="ckplayer_a1"),o||(o={});var s="undefined",l=document,c="http://www.macromedia.com/go/getflashplayer",f='<a href="'+c+'" target="_blank">请点击此处下载安装最新的flash插件</a>',p={w:"您的网页不符合w3c标准，无法显示播放器",f:"您没有安装flash插件，无法播放视频，"+f,v:"您的flash插件版本过低，无法播放视频，"+f},d=typeof l.getElementById!=s&&typeof l.getElementsByTagName!=s&&typeof l.createElement!=s,u='id="'+a+'" name="'+a+'" ',v="",h="";o.movie=e,o.flashvars=this.getflashvars(r),v+='<object  pluginspage="http://www.macromedia.com/go/getflashplayer" ',v+='classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ',v+='codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0" ',v+='width="'+i+'" ',v+='height="'+n+'" ',v+=u,v+='align="middle">',v+=this.getparam(o).v,v+="<embed ",v+=this.getparam(o).w,v+=' width="'+i+'" height="'+n+'" name="'+a+'" id="'+a+'" align="middle" '+u,v+='type="application/x-shockwave-flash" pluginspage="'+c+'" />',v+="</object>",h=d?this.Flash().f?this.Flash().v<10?p.f:v:p.f:p.w,h&&(this._K_(t).innerHTML=h,this._K_(t).style.color="#FFDD00")}};window.CKobject=e}();var swfobject=function(){function e(){if(!H){try{var e=V.getElementsByTagName("body")[0].appendChild(m("span"));e.parentNode.removeChild(e)}catch(t){return}H=!0;for(var a=$.length,i=0;a>i;i++)$[i]()}}function t(e){H?e():$[$.length]=e}function a(e){if(typeof j.addEventListener!=_)j.addEventListener("load",e,!1);else if(typeof V.addEventListener!=_)V.addEventListener("load",e,!1);else if(typeof j.attachEvent!=_)g(j,"onload",e);else if("function"==typeof j.onload){var t=j.onload;j.onload=function(){t(),e()}}else j.onload=e}function i(){M?n():r()}function n(){var e=V.getElementsByTagName("body")[0],t=m(O);t.setAttribute("type",F);var a=e.appendChild(t);if(a){var i=0;!function(){if(typeof a.GetVariable!=_){var n=a.GetVariable("$version");n&&(n=n.split(" ")[1].split(","),G.pv=[parseInt(n[0],10),parseInt(n[1],10),parseInt(n[2],10)])}else if(10>i)return i++,void setTimeout(arguments.callee,10);e.removeChild(t),a=null,r()}()}else r()}function r(){var e=K.length;if(e>0)for(var t=0;e>t;t++){var a=K[t].id,i=K[t].callbackFn,n={success:!1,id:a};if(G.pv[0]>0){var r=h(a);if(r)if(!y(K[t].swfVersion)||G.wk&&G.wk<312)if(K[t].expressInstall&&s()){var f={};f.data=K[t].expressInstall,f.width=r.getAttribute("width")||"0",f.height=r.getAttribute("height")||"0",r.getAttribute("class")&&(f.styleclass=r.getAttribute("class")),r.getAttribute("align")&&(f.align=r.getAttribute("align"));for(var p={},d=r.getElementsByTagName("param"),u=d.length,v=0;u>v;v++)"movie"!=d[v].getAttribute("name").toLowerCase()&&(p[d[v].getAttribute("name")]=d[v].getAttribute("value"));l(f,p,a,i)}else c(r),i&&i(n);else b(a,!0),i&&(n.success=!0,n.ref=o(a),i(n))}else if(b(a,!0),i){var m=o(a);m&&typeof m.SetVariable!=_&&(n.success=!0,n.ref=m),i(n)}}}function o(e){var t=null,a=h(e);if(a&&"OBJECT"==a.nodeName)if(typeof a.SetVariable!=_)t=a;else{var i=a.getElementsByTagName(O)[0];i&&(t=i)}return t}function s(){return!D&&y("6.0.65")&&(G.win||G.mac)&&!(G.wk&&G.wk<312)}function l(e,t,a,i){D=!0,S=i||null,x={success:!1,id:a};var n=h(a);if(n){"OBJECT"==n.nodeName?(E=f(n),C=null):(E=n,C=a),e.id=L,(typeof e.width==_||!/%$/.test(e.width)&&parseInt(e.width,10)<310)&&(e.width="310"),(typeof e.height==_||!/%$/.test(e.height)&&parseInt(e.height,10)<137)&&(e.height="137"),V.title=V.title.slice(0,47)+" - Flash Player Installation";var r=G.ie&&G.win?"ActiveX":"PlugIn",o="MMredirectURL="+j.location.toString().replace(/&/g,"%26")+"&MMplayerType="+r+"&MMdoctitle="+V.title;if(typeof t.flashvars!=_?t.flashvars+="&"+o:t.flashvars=o,G.ie&&G.win&&4!=n.readyState){var s=m("div");a+="SWFObjectNew",s.setAttribute("id",a),n.parentNode.insertBefore(s,n),n.style.display="none",function(){4==n.readyState?n.parentNode.removeChild(n):setTimeout(arguments.callee,10)}()}p(e,t,a)}}function c(e){if(G.ie&&G.win&&4!=e.readyState){var t=m("div");e.parentNode.insertBefore(t,e),t.parentNode.replaceChild(f(e),t),e.style.display="none",function(){4==e.readyState?e.parentNode.removeChild(e):setTimeout(arguments.callee,10)}()}else e.parentNode.replaceChild(f(e),e)}function f(e){var t=m("div");if(G.win&&G.ie)t.innerHTML=e.innerHTML;else{var a=e.getElementsByTagName(O)[0];if(a){var i=a.childNodes;if(i)for(var n=i.length,r=0;n>r;r++)1==i[r].nodeType&&"PARAM"==i[r].nodeName||8==i[r].nodeType||t.appendChild(i[r].cloneNode(!0))}}return t}function p(e,t,a){var i,n=h(a);if(G.wk&&G.wk<312)return i;if(n)if(typeof e.id==_&&(e.id=a),G.ie&&G.win){var r="";for(var o in e)e[o]!=Object.prototype[o]&&("data"==o.toLowerCase()?t.movie=e[o]:"styleclass"==o.toLowerCase()?r+=' class="'+e[o]+'"':"classid"!=o.toLowerCase()&&(r+=" "+o+'="'+e[o]+'"'));var s="";for(var l in t)t[l]!=Object.prototype[l]&&(s+='<param name="'+l+'" value="'+t[l]+'" />');n.outerHTML='<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'+r+">"+s+"</object>",R[R.length]=e.id,i=h(e.id)}else{var c=m(O);c.setAttribute("type",F);for(var f in e)e[f]!=Object.prototype[f]&&("styleclass"==f.toLowerCase()?c.setAttribute("class",e[f]):"classid"!=f.toLowerCase()&&c.setAttribute(f,e[f]));for(var p in t)t[p]!=Object.prototype[p]&&"movie"!=p.toLowerCase()&&d(c,p,t[p]);n.parentNode.replaceChild(c,n),i=c}return i}function d(e,t,a){var i=m("param");i.setAttribute("name",t),i.setAttribute("value",a),e.appendChild(i)}function u(e){var t=h(e);t&&"OBJECT"==t.nodeName&&(G.ie&&G.win?(t.style.display="none",function(){4==t.readyState?v(e):setTimeout(arguments.callee,10)}()):t.parentNode.removeChild(t))}function v(e){var t=h(e);if(t){for(var a in t)"function"==typeof t[a]&&(t[a]=null);t.parentNode.removeChild(t)}}function h(e){var t=null;try{t=V.getElementById(e)}catch(a){}return t}function m(e){return V.createElement(e)}function g(e,t,a){e.attachEvent(t,a),W[W.length]=[e,t,a]}function y(e){var t=G.pv,a=e.split(".");return a[0]=parseInt(a[0],10),a[1]=parseInt(a[1],10)||0,a[2]=parseInt(a[2],10)||0,t[0]>a[0]||t[0]==a[0]&&t[1]>a[1]||t[0]==a[0]&&t[1]==a[1]&&t[2]>=a[2]?!0:!1}function w(e,t,a,i){if(!G.ie||!G.mac){var n=V.getElementsByTagName("head")[0];if(n){var r=a&&"string"==typeof a?a:"screen";if(i&&(I=null,A=null),!I||A!=r){var o=m("style");o.setAttribute("type","text/css"),o.setAttribute("media",r),I=n.appendChild(o),G.ie&&G.win&&typeof V.styleSheets!=_&&V.styleSheets.length>0&&(I=V.styleSheets[V.styleSheets.length-1]),A=r}G.ie&&G.win?I&&typeof I.addRule==O&&I.addRule(e,t):I&&typeof V.createTextNode!=_&&I.appendChild(V.createTextNode(e+" {"+t+"}"))}}}function b(e,t){if(U){var a=t?"visible":"hidden";H&&h(e)?h(e).style.visibility=a:w("#"+e,"visibility:"+a)}}function k(e){var t=/[\\\"<>\.;]/,a=null!=t.exec(e);return a&&typeof encodeURIComponent!=_?encodeURIComponent(e):e}{var E,C,S,x,I,A,_="undefined",O="object",T="Shockwave Flash",N="ShockwaveFlash.ShockwaveFlash",F="application/x-shockwave-flash",L="SWFObjectExprInst",B="onreadystatechange",j=window,V=document,P=navigator,M=!1,$=[i],K=[],R=[],W=[],H=!1,D=!1,U=!0,G=function(){var e=typeof V.getElementById!=_&&typeof V.getElementsByTagName!=_&&typeof V.createElement!=_,t=P.userAgent.toLowerCase(),a=P.platform.toLowerCase(),i=/win/.test(a?a:t),n=/mac/.test(a?a:t),r=/webkit/.test(t)?parseFloat(t.replace(/^.*webkit\/(\d+(\.\d+)?).*$/,"$1")):!1,o=!1,s=[0,0,0],l=null;if(typeof P.plugins!=_&&typeof P.plugins[T]==O)l=P.plugins[T].description,!l||typeof P.mimeTypes!=_&&P.mimeTypes[F]&&!P.mimeTypes[F].enabledPlugin||(M=!0,o=!1,l=l.replace(/^.*\s+(\S+\s+\S+$)/,"$1"),s[0]=parseInt(l.replace(/^(.*)\..*$/,"$1"),10),s[1]=parseInt(l.replace(/^.*\.(.*)\s.*$/,"$1"),10),s[2]=/[a-zA-Z]/.test(l)?parseInt(l.replace(/^.*[a-zA-Z]+(.*)$/,"$1"),10):0);else if(typeof j.ActiveXObject!=_)try{var c=new ActiveXObject(N);c&&(l=c.GetVariable("$version"),l&&(o=!0,l=l.split(" ")[1].split(","),s=[parseInt(l[0],10),parseInt(l[1],10),parseInt(l[2],10)]))}catch(f){}return{w3:e,pv:s,wk:r,ie:o,win:i,mac:n}}();!function(){G.w3&&((typeof V.readyState!=_&&"complete"==V.readyState||typeof V.readyState==_&&(V.getElementsByTagName("body")[0]||V.body))&&e(),H||(typeof V.addEventListener!=_&&V.addEventListener("DOMContentLoaded",e,!1),G.ie&&G.win&&(V.attachEvent(B,function(){"complete"==V.readyState&&(V.detachEvent(B,arguments.callee),e())}),j==top&&!function(){if(!H){try{V.documentElement.doScroll("left")}catch(t){return void setTimeout(arguments.callee,0)}e()}}()),G.wk&&!function(){return H?void 0:/loaded|complete/.test(V.readyState)?void e():void setTimeout(arguments.callee,0)}(),a(e)))}(),function(){G.ie&&G.win&&window.attachEvent("onunload",function(){for(var e=W.length,t=0;e>t;t++)W[t][0].detachEvent(W[t][1],W[t][2]);for(var a=R.length,i=0;a>i;i++)u(R[i]);for(var n in G)G[n]=null;G=null;for(var r in swfobject)swfobject[r]=null;swfobject=null})}()}return{registerObject:function(e,t,a,i){if(G.w3&&e&&t){var n={};n.id=e,n.swfVersion=t,n.expressInstall=a,n.callbackFn=i,K[K.length]=n,b(e,!1)}else i&&i({success:!1,id:e})},getObjectById:function(e){return G.w3?o(e):void 0},embedSWF:function(e,a,i,n,r,o,c,f,d,u){var v={success:!1,id:a};G.w3&&!(G.wk&&G.wk<312)&&e&&a&&i&&n&&r?(b(a,!1),t(function(){i+="",n+="";var t={};if(d&&typeof d===O)for(var h in d)t[h]=d[h];t.data=e,t.width=i,t.height=n;var m={};if(f&&typeof f===O)for(var g in f)m[g]=f[g];if(c&&typeof c===O)for(var w in c)typeof m.flashvars!=_?m.flashvars+="&"+w+"="+c[w]:m.flashvars=w+"="+c[w];if(y(r)){var k=p(t,m,a);t.id==a&&b(a,!0),v.success=!0,v.ref=k}else{if(o&&s())return t.data=o,void l(t,m,a,u);b(a,!0)}u&&u(v)})):u&&u(v)},switchOffAutoHideShow:function(){U=!1},ua:G,getFlashPlayerVersion:function(){return{major:G.pv[0],minor:G.pv[1],release:G.pv[2]}},hasFlashPlayerVersion:y,createSWF:function(e,t,a){return G.w3?p(e,t,a):void 0},showExpressInstall:function(e,t,a,i){G.w3&&s()&&l(e,t,a,i)},removeSWF:function(e){G.w3&&u(e)},createCSS:function(e,t,a,i){G.w3&&w(e,t,a,i)},addDomLoadEvent:t,addLoadEvent:a,getQueryParamValue:function(e){var t=V.location.search||V.location.hash;if(t){if(/\?/.test(t)&&(t=t.split("?")[1]),null==e)return k(t);for(var a=t.split("&"),i=0;i<a.length;i++)if(a[i].substring(0,a[i].indexOf("="))==e)return k(a[i].substring(a[i].indexOf("=")+1))}return""},expressInstallCallback:function(){if(D){var e=h(L);e&&E&&(e.parentNode.replaceChild(E,e),C&&(b(C,!0),G.ie&&G.win&&(E.style.display="block")),S&&S(x)),D=!1}}}}();