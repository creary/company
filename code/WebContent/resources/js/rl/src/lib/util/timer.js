orderjs.define("lib.util.timer",function(){rl.util.timer=function(){var t={},s=0,r='<table border="1" cellspacing="2" style="border-collapse:collapse"><thead><caption>timer</caption><tr><th style="padding:2px 5px;">From</th><th style="padding:2px 5px;">To</th><th style="padding:2px 5px;">Cost(ms)</th></tr></thead>rows</table>';return{clock:function(r){rl.isNonNullStr(r)||(r="t_"+ ++s);var n=t[r.toLowerCase()]=this.now();return n},count:function(t,s){return rl.isNonNullStr(t)&&rl.isNonNullStr(s)?this.get(s)-this.get(t):void 0},countThese:function(){var t,s=this,r=arguments[0],n=[],i=this.now();return Array.each(function(i){t=new rl.util.timer.Msg(r,i,s.count(r,i)),n.push(t),r=i},arguments,null,1),t=new rl.util.timer.Msg(r,"Now",this.passed(r,i)),n.push(t),t=new rl.util.timer.Msg(arguments[0],"Now",this.passed(arguments[0],i)),n.push(t),n},get:function(s){return rl.isNonNullStr(s)?t[s.toLowerCase()]:void 0},now:function(){return(new Date).getTime()},passed:function(t,s){var r=s||this.now();return r-this.get(t)},reset:function(){t={},s=0},showClocks:function(){var t=this.sort();this.showThese.apply(this,t)},showCount:function(t,s){var r=new rl.util.timer.Msg(t,s,this.count(t,s));this.showMsg(r)},showPassed:function(t,s){this.showMsg(new rl.util.timer.Msg(t,"Now",this.passed(t,s)))},showMsg:function(t){t=t.join?t.join(""):t,rl.debug(r.replace("rows",t))},showThese:function(){var t=this.countThese.apply(this,arguments);this.showMsg(t)},sort:function(){var s=[],r=this;for(var n in t)s.push(n);return s=s.sort(function(t,s){return r.get(t)-r.get(s)})},toString:function(){return"[object rl.util.timer]"}}}(),rl.util.timer.Msg=function(t,s,r){this.start=t,this.end=s,this.cost=r},rl.util.timer.Msg.prototype.toString=function(){return"<tr><td>"+this.start+"</td><td>"+this.end+"</td><td>"+this.cost+"</td></tr>"}});