orderjs.define("lib.data.bridge.ArrayListVisitor",["lib.data.bridge.ListVisitor"],function(){rl.data.ArrayListVisitor=rl.createClass({parent:rl.data.ListVisitor,construct:function(r){rl.isPrototyping(arguments[0])||rl.data.ArrayListVisitor.parent.call(this,r)},members:{setFields:function(r){rl.isArray(r)||(r=[]);for(var t,i,a=r.length||this.colsNum||(this.records||[]).length,s=this.fields=[],e=this._mapping={},n=0;a>n;n++)i=r[n],rl.isNonNullStr(i)?t={name:i}:i&&(t=rl.ext({},i)),t.name||(t.name=n),e[t.name]=t.mapping||n,s.push(t)},getValue:function(r){var t=this.readingRecord;if(t){var i=r;return rl.isNonNullStr(r)&&(i=this._mapping[r]),t[i]}},getValues:function(){var r=this.readingRecord;if(r){for(var t={},i=0,a=r.length;a>i;i++)t[i]=r[i];return t}},toString:function(){return"[object rl.data.ArrayListVisitor]"}}})});