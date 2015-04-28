orderjs.define("lib.data.Calendar",["lib.data.engine","lib.lang.Date"],function(){rl.data.Calendar=rl.createClass({parent:rl.util.EventProvider,construct:function(t){rl.isPrototyping(arguments[0])||(rl.data.Calendar.parent.call(this,arguments),!rl.isObject(t)&&arguments.length&&(t={iniDate:Array.slice(arguments)}),rl.ext(this,t),this.initialize())},members:{defaultFormat:"%Y-%m-%d %H:%M:%S",iniDate:void 0,startDay:0,maxDate:null,minDate:null,dayGrid:null,initialize:function(){var t=this.iniDate;this._dateTime=t instanceof Date?t:new Date,rl.isArray(t)?this._updateDateTime.apply(this,t):(rl.isNumber(t)||rl.isNonNullStr(t))&&this._dateTime.setTime(t),this.dayGrid={},this._updateDayGrid()},getDateTime:function(){return this._dateTime},setDateTime:function(){var t=arguments.length;if(!t)return this;var e=this._dateTime.clone();return this._updateDateTime.apply(this,arguments),this._compareChange(e),this},_updateDateTime:function(){var t=arguments.length,e=this._dateTime;if(1===t)e.setTime(arguments[0]);else if(t>1){e.setFullYear.apply(e,arguments);var a=Array.slice(arguments,3);a.length>0&&e.setHours.apply(e,a)}},getStartDay:function(){return this.startDay},setStartDay:function(t){var e=this.StartDay;return e!==t&&(this.startDay=t,this._updateDayGrid(),this.fireEvent("startdaychange",t,e)),this},previous:function(t){return this.add(t,-1)},next:function(t){return this.add(t,1)},goToday:function(){var t=new Date;return this.setDateTime(t.getTime())},clone:function(){return new this.constructor({iniDate:this._dateTime.getTime(),defaultFormat:this.defaultFormat,startDay:this.startDay})},mapDays:function(t,e){var a,i,r=(this._dateTime,this.dayGrid),n=r.prevMonthDateTime,s=n.getDaysInMonth(),l=r.activeStart,o=r.activeEnd,d=o+1,u=new Date,h=u.getFullYear(),g=u.getMonth(),c=u.getDate();for(dayDetail={},addMonth=function(){12===dayDetail.month?(dayDetail.month=1,dayDetail.year+=1):dayDetail.month+=1},result=[],rl.ext(dayDetail,{month:n.getMonth(),year:n.getFullYear(),posFlag:-1}),a=0,i=s-l+1;l>a;a++,i++)result.push(t.call(e,a,i,dayDetail));addMonth(),dayDetail.posFlag=0;var y=dayDetail.year===h&&dayDetail.month===g?function(){dayDetail.isToday=c===i,result.push(t.call(e,a,i,dayDetail))}:function(){result.push(t.call(e,a,i,dayDetail))};for(a=l,i=1;d>a;a++,i++)y();for(delete dayDetail.isToday,addMonth(),dayDetail.posFlag=1,a=d,i=1;42>a;a++,i++)result.push(t.call(e,a,i,dayDetail));return result},_compareChange:function(t){var e=this._dateTime;if(t.getTime()!==e.getTime()){var a=t.getFullYear()!==e.getFullYear();a&&this.fireEvent("yearchange"),(a||t.getMonth()!==e.getMonth())&&(this._updateDayGrid(),this.fireEvent("monthchange")),this.fireEvent("change",e,t)}},_updateDayGrid:function(){var t=this._dateTime,e=t.getFirstDateOfMonth().getDay(),a=this.startDay,i=e-a;a>=i&&(i+=7),rl.ext(this.dayGrid,{prevMonthDateTime:t.clone().add(Date.MONTH,-1),activeStart:i,activeEnd:i+(t.getDaysInMonth()-1)})},toString:function(){return String(this._dateTime)}}}),rl.ext(rl.data.Calendar,{addDateMethods:function(){function t(t){var e=this._dateTime,a=e.clone(),i=Array.slice(arguments,1);return t.apply(e,i),this._compareChange(a),this}function e(t){var e=Array.slice(arguments,1);return t.apply(this._dateTime,e)}function a(a,l){rl.isNonNullStr(a)&&(a=[a]),Array.each(function(a){if(i=n[a],r=s[a],rl.isEmpty(r)&&rl.isFunction(i)){var o="get"===l?e:"set"===l?t:"";o&&(s[a]=Function.curry(o,i))}},a)}var i,r,n=Date.prototype,s=rl.data.Calendar.prototype;return a}()}),function(){var t=rl.data.Calendar.addDateMethods,e=["getDate","getDay","getFullYear","getHours","getMilliseconds","getMinutes","getMonth","getSeconds","getTime","getTimezoneOffset","getUTCDate","getUTCDay","getUTCFullYear","getUTCHours","getUTCMilliSeconds","getUTCMinutes","getUTCMonth","getUTCSeconds","toGMTString","toLocaleString","toUTCString","format"],a=["setDate","setFullYear","setHours","setMilliSeconds","setMinutes","setMonth","setSeconds","setTime","setUTCDate","setUTCFullYear","setUTCHours","setUTCMilliseconds","setUTCMinutes","setUTCMonth","setUTCSeconds","add"];t(e,"get"),t(a,"set")}()});