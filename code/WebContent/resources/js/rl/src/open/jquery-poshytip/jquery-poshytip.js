/*
 * Poshy Tip jQuery plugin v1.2
 * http://vadikom.com/tools/poshy-tip-jquery-plugin-for-stylish-tooltips/
 * Copyright 2010-2013, Vasil Dinkov, http://vadikom.com/
 */
!function(t){function i(){t.each(s,function(){this.refresh(!0)})}var s=[],e=/^url\(["']?([^"'\)]*)["']?\);?$/i,o=/\.png$/i,h=!!window.createPopup&&"undefined"==document.documentElement.currentStyle.minWidth;t(window).resize(i),t.Poshytip=function(i,s){this.$elm=t(i),this.opts=t.extend({},t.fn.poshytip.defaults,s),this.$tip=t(['<div class="',this.opts.className,'">','<div class="tip-inner tip-bg-image"></div>','<div class="tip-arrow tip-arrow-top tip-arrow-right tip-arrow-bottom tip-arrow-left"></div>',"</div>"].join("")).appendTo(document.body),this.$arrow=this.$tip.find("div.tip-arrow"),this.$inner=this.$tip.find("div.tip-inner"),this.disabled=!1,this.content=null,this.init()},t.Poshytip.prototype={init:function(){s.push(this);var i=this.$elm.attr("title");if(this.$elm.data("title.poshytip",void 0!==i?i:null).data("poshytip",this),"none"!=this.opts.showOn)switch(this.$elm.bind({"mouseenter.poshytip":t.proxy(this.mouseenter,this),"mouseleave.poshytip":t.proxy(this.mouseleave,this)}),this.opts.showOn){case"hover":"cursor"==this.opts.alignTo&&this.$elm.bind("mousemove.poshytip",t.proxy(this.mousemove,this)),this.opts.allowTipHover&&this.$tip.hover(t.proxy(this.clearTimeouts,this),t.proxy(this.mouseleave,this));break;case"focus":this.$elm.bind({"focus.poshytip":t.proxy(this.showDelayed,this),"blur.poshytip":t.proxy(this.hideDelayed,this)})}},mouseenter:function(t){return this.disabled?!0:(this.eventX=t.pageX,this.eventY=t.pageY,this.$elm.attr("title",""),"focus"==this.opts.showOn?!0:void this.showDelayed())},mouseleave:function(t){if(this.disabled||this.asyncAnimating&&(this.$tip[0]===t.relatedTarget||jQuery.contains(this.$tip[0],t.relatedTarget)))return!0;if(!this.$tip.data("active")){var i=this.$elm.data("title.poshytip");null!==i&&this.$elm.attr("title",i)}return"focus"==this.opts.showOn?!0:void this.hideDelayed()},mousemove:function(t){return this.disabled?!0:(this.eventX=t.pageX,this.eventY=t.pageY,void(this.opts.followCursor&&this.$tip.data("active")&&(this.calcPos(),this.$tip.css({left:this.pos.l,top:this.pos.t}),this.pos.arrow&&(this.$arrow[0].className="tip-arrow tip-arrow-"+this.pos.arrow))))},show:function(){this.disabled||this.$tip.data("active")||(this.reset(),this.update(),this.content&&(this.display(),this.opts.timeOnScreen&&this.hideDelayed(this.opts.timeOnScreen)))},showDelayed:function(i){this.clearTimeouts(),this.showTimeout=setTimeout(t.proxy(this.show,this),"number"==typeof i?i:this.opts.showTimeout)},hide:function(){!this.disabled&&this.$tip.data("active")&&this.display(!0)},hideDelayed:function(i){this.clearTimeouts(),this.hideTimeout=setTimeout(t.proxy(this.hide,this),"number"==typeof i?i:this.opts.hideTimeout)},reset:function(){this.$tip.queue([]).detach().css("visibility","hidden").data("active",!1),this.$inner.find("*").poshytip("hide"),this.opts.fade&&this.$tip.css("opacity",this.opacity),this.$arrow[0].className="tip-arrow tip-arrow-top tip-arrow-right tip-arrow-bottom tip-arrow-left",this.asyncAnimating=!1},update:function(t,i){if(!this.disabled){var s=void 0!==t;if(s){if(i||(this.opts.content=t),!this.$tip.data("active"))return}else t=this.opts.content;var e=this,o="function"==typeof t?t.call(this.$elm[0],function(t){e.update(t)}):"[title]"==t?this.$elm.data("title.poshytip"):t;this.content!==o&&(this.$inner.empty().append(o),this.content=o),this.refresh(s)}},refresh:function(i){if(!this.disabled){if(i){if(!this.$tip.data("active"))return;var s={left:this.$tip.css("left"),top:this.$tip.css("top")}}this.$tip.css({left:0,top:0}).appendTo(document.body),void 0===this.opacity&&(this.opacity=this.$tip.css("opacity"));var a=this.$tip.css("background-image").match(e),p=this.$arrow.css("background-image").match(e);if(a){var r=o.test(a[1]);h&&r?(this.$tip.css("background-image","none"),this.$inner.css({margin:0,border:0,padding:0}),a=r=!1):this.$tip.prepend('<table class="tip-table" border="0" cellpadding="0" cellspacing="0"><tr><td class="tip-top tip-bg-image" colspan="2"><span></span></td><td class="tip-right tip-bg-image" rowspan="2"><span></span></td></tr><tr><td class="tip-left tip-bg-image" rowspan="2"><span></span></td><td></td></tr><tr><td class="tip-bottom tip-bg-image" colspan="2"><span></span></td></tr></table>').css({border:0,padding:0,"background-image":"none","background-color":"transparent"}).find(".tip-bg-image").css("background-image",'url("'+a[1]+'")').end().find("td").eq(3).append(this.$inner),r&&!t.support.opacity&&(this.opts.fade=!1)}p&&!t.support.opacity&&(h&&o.test(p[1])&&(p=!1,this.$arrow.css("background-image","none")),this.opts.fade=!1);var n=this.$tip.find("> table.tip-table");if(h){this.$tip[0].style.width="",n.width("auto").find("td").eq(3).width("auto");var l=this.$tip.width(),d=parseInt(this.$tip.css("min-width")),c=parseInt(this.$tip.css("max-width"));!isNaN(d)&&d>l?l=d:!isNaN(c)&&l>c&&(l=c),this.$tip.add(n).width(l).eq(0).find("td").eq(3).width("100%")}else n[0]&&n.width("auto").find("td").eq(3).width("auto").end().end().width(document.defaultView&&document.defaultView.getComputedStyle&&parseFloat(document.defaultView.getComputedStyle(this.$tip[0],null).width)||this.$tip.width()).find("td").eq(3).width("100%");if(this.tipOuterW=this.$tip.outerWidth(),this.tipOuterH=this.$tip.outerHeight(),this.calcPos(),p&&this.pos.arrow&&(this.$arrow[0].className="tip-arrow tip-arrow-"+this.pos.arrow,this.$arrow.css("visibility","inherit")),i&&this.opts.refreshAniDuration){this.asyncAnimating=!0;var u=this;this.$tip.css(s).animate({left:this.pos.l,top:this.pos.t},this.opts.refreshAniDuration,function(){u.asyncAnimating=!1})}else this.$tip.css({left:this.pos.l,top:this.pos.t})}},display:function(i){var s=this.$tip.data("active");if(!(s&&!i||!s&&i)){if(this.$tip.stop(),(this.opts.slide&&this.pos.arrow||this.opts.fade)&&(i&&this.opts.hideAniDuration||!i&&this.opts.showAniDuration)){var e={},o={};if(this.opts.slide&&this.pos.arrow){var h,a;"bottom"==this.pos.arrow||"top"==this.pos.arrow?(h="top",a="bottom"):(h="left",a="right");var p=parseInt(this.$tip.css(h));e[h]=p+(i?0:this.pos.arrow==a?-this.opts.slideOffset:this.opts.slideOffset),o[h]=p+(i?this.pos.arrow==a?this.opts.slideOffset:-this.opts.slideOffset:0)+"px"}this.opts.fade&&(e.opacity=i?this.$tip.css("opacity"):0,o.opacity=i?0:this.opacity),this.$tip.css(e).animate(o,this.opts[i?"hideAniDuration":"showAniDuration"])}if(i?this.$tip.queue(t.proxy(this.reset,this)):this.$tip.css("visibility","inherit"),s){var r=this.$elm.data("title.poshytip");null!==r&&this.$elm.attr("title",r)}this.$tip.data("active",!s)}},disable:function(){this.reset(),this.disabled=!0},enable:function(){this.disabled=!1},destroy:function(){this.reset(),this.$tip.remove(),delete this.$tip,this.content=null,this.$elm.unbind(".poshytip").removeData("title.poshytip").removeData("poshytip"),s.splice(t.inArray(this,s),1)},clearTimeouts:function(){this.showTimeout&&(clearTimeout(this.showTimeout),this.showTimeout=0),this.hideTimeout&&(clearTimeout(this.hideTimeout),this.hideTimeout=0)},calcPos:function(){var i,s,e,o,h,a,p={l:0,t:0,arrow:""},r=t(window),n={l:r.scrollLeft(),t:r.scrollTop(),w:r.width(),h:r.height()};if("cursor"==this.opts.alignTo)i=s=e=this.eventX,o=h=a=this.eventY;else{var l=this.$elm.offset(),d={l:l.left,t:l.top,w:this.$elm.outerWidth(),h:this.$elm.outerHeight()};i=d.l+("inner-right"!=this.opts.alignX?0:d.w),s=i+Math.floor(d.w/2),e=i+("inner-left"!=this.opts.alignX?d.w:0),o=d.t+("inner-bottom"!=this.opts.alignY?0:d.h),h=o+Math.floor(d.h/2),a=o+("inner-top"!=this.opts.alignY?d.h:0)}switch(this.opts.alignX){case"right":case"inner-left":p.l=e+this.opts.offsetX,this.opts.keepInViewport&&p.l+this.tipOuterW>n.l+n.w&&(p.l=n.l+n.w-this.tipOuterW),("right"==this.opts.alignX||"center"==this.opts.alignY)&&(p.arrow="left");break;case"center":p.l=s-Math.floor(this.tipOuterW/2),this.opts.keepInViewport&&(p.l+this.tipOuterW>n.l+n.w?p.l=n.l+n.w-this.tipOuterW:p.l<n.l&&(p.l=n.l));break;default:p.l=i-this.tipOuterW-this.opts.offsetX,this.opts.keepInViewport&&p.l<n.l&&(p.l=n.l),("left"==this.opts.alignX||"center"==this.opts.alignY)&&(p.arrow="right")}switch(this.opts.alignY){case"bottom":case"inner-top":p.t=a+this.opts.offsetY,p.arrow&&"cursor"!=this.opts.alignTo||(p.arrow="top"),this.opts.keepInViewport&&p.t+this.tipOuterH>n.t+n.h&&(p.t=o-this.tipOuterH-this.opts.offsetY,"top"==p.arrow&&(p.arrow="bottom"));break;case"center":p.t=h-Math.floor(this.tipOuterH/2),this.opts.keepInViewport&&(p.t+this.tipOuterH>n.t+n.h?p.t=n.t+n.h-this.tipOuterH:p.t<n.t&&(p.t=n.t));break;default:p.t=o-this.tipOuterH-this.opts.offsetY,p.arrow&&"cursor"!=this.opts.alignTo||(p.arrow="bottom"),this.opts.keepInViewport&&p.t<n.t&&(p.t=a+this.opts.offsetY,"bottom"==p.arrow&&(p.arrow="top"))}this.pos=p}},t.fn.poshytip=function(i){if("string"==typeof i){var s=arguments,e=i;return Array.prototype.shift.call(s),"destroy"==e&&(this.die?this.die("mouseenter.poshytip").die("focus.poshytip"):t(document).undelegate(this.selector,"mouseenter.poshytip").undelegate(this.selector,"focus.poshytip")),this.each(function(){var i=t(this).data("poshytip");i&&i[e]&&i[e].apply(i,s)})}var o=t.extend({},t.fn.poshytip.defaults,i);if(t("#poshytip-css-"+o.className)[0]||t(['<style id="poshytip-css-',o.className,'" type="text/css">',"div.",o.className,"{visibility:hidden;position:absolute;top:0;left:0;}","div.",o.className," table.tip-table, div.",o.className," table.tip-table td{margin:0;font-family:inherit;font-size:inherit;font-weight:inherit;font-style:inherit;font-variant:inherit;vertical-align:middle;}","div.",o.className," td.tip-bg-image span{display:block;font:1px/1px sans-serif;height:",o.bgImageFrameSize,"px;width:",o.bgImageFrameSize,"px;overflow:hidden;}","div.",o.className," td.tip-right{background-position:100% 0;}","div.",o.className," td.tip-bottom{background-position:100% 100%;}","div.",o.className," td.tip-left{background-position:0 100%;}","div.",o.className," div.tip-inner{background-position:-",o.bgImageFrameSize,"px -",o.bgImageFrameSize,"px;}","div.",o.className," div.tip-arrow{visibility:hidden;position:absolute;overflow:hidden;font:1px/1px sans-serif;}","</style>"].join("")).appendTo("head"),o.liveEvents&&"none"!=o.showOn){var h,a=t.extend({},o,{liveEvents:!1});switch(o.showOn){case"hover":h=function(i){var s=t(this);s.data("poshytip")||s.poshytip(a).poshytip("mouseenter",i)},this.live?this.live("mouseenter.poshytip",h):t(document).delegate(this.selector,"mouseenter.poshytip",h);break;case"focus":h=function(){var i=t(this);i.data("poshytip")||i.poshytip(a).poshytip("showDelayed")},this.live?this.live("focus.poshytip",h):t(document).delegate(this.selector,"focus.poshytip",h)}return this}return this.each(function(){new t.Poshytip(this,o)})},t.fn.poshytip.defaults={content:"[title]",className:"tip-yellow",bgImageFrameSize:10,showTimeout:500,hideTimeout:100,timeOnScreen:0,showOn:"hover",liveEvents:!1,alignTo:"cursor",alignX:"right",alignY:"top",offsetX:-22,offsetY:18,keepInViewport:!0,allowTipHover:!0,followCursor:!1,fade:!0,slide:!0,slideOffset:8,showAniDuration:300,hideAniDuration:300,refreshAniDuration:200}}(jQuery);