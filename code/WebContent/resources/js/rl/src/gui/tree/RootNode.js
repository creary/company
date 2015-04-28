orderjs.define("gui.tree.RootNode",["gui.engine","gui.tree.Node","css>rl:tree"],function(){rl.gui.tree.RootNode=rl.createClass({parent:rl.gui.tree.Node,construct:function(e){rl.isPrototyping(arguments[0])||(rl.gui.tree.RootNode.parent.call(this,e),this._domIdPrefixRe=new RegExp("^"+this.ownerComp.id+"_"))},members:{defaultDataFields:["text","tip","icon","actionData"],_htmlTpl:new rl.HtmlTpl('<div class="root_wrap"><ul class="root" id={id}>{nodesHtml}</ul></div>'),load:function(){for(var e,t,r,i,n,d,l,o={},s=this.tree,a=s.dataSource,h=this._getDataFields(),u=this.ownerComp.id+"_",c=a.rows,f=(rl.isNonNullStr,{}),g=0,p=c.length;p>g;g++)d=c[g],t=d.getValue("id")||g,i=d.getValue("pid"),r=u+t,l=this._getNodeConfig(d,h),e=s.createNode(rl.fill({dataSource:d,id:t,domId:r},l)),o[t]=e,n=o[i],n||(f[t]=e,n=this),n.appendChild(e);var m,N,i,_;for(m in f)N=f[m],i=N.pid,_=o[i],_&&_.appendChild(N)},render:function(e){return this._rendered?this:(e=this.getRenderTarget(e),e.innerHTML=this.getRenderHtml(this.tree.lazyRender),this.afterRender(),this)},_getNodeConfig:function(e,t){var r={};return Array.each(function(t){r[t]=e.getValue(t)},t),r},_getDataFields:function(){try{var e=this.tree.dataSource.dataVisitor.fields;return Array.map(function(e){return rl.isObject(e)?e.name:e},e)}catch(t){return this.defaultDataFields}},reRender:function(e){return this._rendered=!1,this.render(e)},afterRender:function(){this._bindDom(),this.tree.lazyRender?this.renderChildren():(this.each(this._callChildAfterRender),this._childrenRendered=!0),this._rendered=!0,this.fireEvent("render")},getRenderHtml:function(e){var t=e?"":this.getDescendantsHtml(!0);return this._htmlTpl.assign({id:this.id,nodesHtml:t})},_bindDom:function(){this.dw.setTarget(this.id),this.childNodesCtn=this.dw.dom},getNodeFromNodeEle:function(e){if(rl.isElement(e)&&e.id){var t=e.id.replace(this._domIdPrefixRe,"");return this.findByIdInAll(t)}return null},findInAll:function(e,t,r){var i=r||this,t=t||this;if(!i.find||!rl.isArray(i.childNodes))return null;for(var n,d,l=i.childNodes,o=l.length,s=0;o>s;s++){if(n=l[s],e.call(t,n))return n;if(d=this.findInAll(e,t,n))return d}},findByIdInAll:function(e,t,r){return rl.isNonNullStr(e)?(e=e.trim(),this.findInAll(function(t){return t.id==e},null,r)):null},openTo:function(e){if(!rl.isObject(e))return this;for(var t=e,r=e.owner,i=[];r&&r!=this;)i.push(r),r=r.owner;for(;r=i.pop();)r.expand();return t!=this&&rl.isFunction(t.open)&&t.open(),this},setWidth:function(e){this.dw.setWidth(e)},afterItemSelectChange:null,toString:function(){return"[object rl.gui.tree.RootNode]"}}})});