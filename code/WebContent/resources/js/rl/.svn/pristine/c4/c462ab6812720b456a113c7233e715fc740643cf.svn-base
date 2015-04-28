/**
 * @fileOverview idTabs, the whatever plugin
 * It makes adding tabs into a website super simple.
 * But it can also open the door to endless possiblities.
 * @author Sean Catchpole - Version 2.2 - MIT/GPL
 * @change
 	#1 by prcjack @2014/09/22
		Build module in orderable form, based on idTabs. Removed init() and auto-run feature.
 	#2 by prcjack @2014/10/12
		Fix the leak of "i" global variant;
		Replaced the for statement to array.
 */
orderjs.define("open.jQuery-idTabs.idTabs-ordered",["open.jquery.jquery"],function(){var e=orderjs("open.jquery.jquery");!function(e){e.fn.idTabs=function(){for(var t={},s=0,r=arguments.length;r>s;++s){var n=arguments[s];switch(n.constructor){case Object:e.extend(t,n);break;case Boolean:t.change=n;break;case Number:t.start=n;break;case Function:t.click=n;break;case String:"."==n.charAt(0)?t.selected=n:"!"==n.charAt(0)?t.event=n:t.start=n}}return"function"==typeof t["return"]&&(t.change=t["return"]),this.each(function(){e.idTabs(this,t)})},e.idTabs=function(t,s){var r=e.metadata?e(t).metadata():{},n=e.extend({},e.idTabs.settings,r,s);"."==n.selected.charAt(0)&&(n.selected=n.selected.substr(1)),"!"==n.event.charAt(0)&&(n.event=n.event.substr(1)),null==n.start&&(n.start=-1);var a=function(){if(e(this).is("."+n.selected))return n.change;var s="#"+this.href.split("#")[1],r=[],a=[];return e("a",t).each(function(){this.href.match(/#/)&&(r.push(this),a.push("#"+this.href.split("#")[1]))}),n.click&&!n.click.apply(this,[s,a,t,n])?n.change:(e.each(r,function(t,s){e(s).removeClass(n.selected)}),e.each(a,function(t,s){e(s).hide()}),e(this).addClass(n.selected),e(s).show(),n.change)},c=e("a[href*='#']",t).unbind(n.event,a).bind(n.event,a);c.each(function(){e("#"+this.href.split("#")[1]).hide()});var i=!1;return(i=c.filter("."+n.selected)).length||"number"==typeof n.start&&(i=c.eq(n.start)).length||"string"==typeof n.start&&(i=c.filter("[href*='#"+n.start+"']")).length,i&&(i.removeClass(n.selected),i.trigger(n.event)),n},e.idTabs.settings={start:0,change:!1,click:null,selected:".selected",event:"!click"},e.idTabs.version="2.2"}(e)});