orderjs.define("app.dataBox.loadDynamicGrid",["lib.data.engine","lib.data.bridge.ArrayListVisitor","lib.data.bridge.JsonListVisitor","lib.rpc.engine","gui.grid.Grid"],function(){rl.createNamespace("rl.dataBox",{loadDynamicGrid:function(e,r,i,n){var a,t=rl.ext({responseType:"json",async:!1,successHandler:function(e){viewInGrid(e.columns,e.data,r,n)},failureHandler:function(){rl.$(r).setInnerHTML("load failed!")}},i);return t.url=e,a=new rl.rpc.XHRequest(t),a.request(),a},viewInGrid:function(e,r,i,n){if(!rl.isArray(e)||!e.length)return null;var a=rl.ext({},n),t=Array.map(function(e){return e.name},e),l=new rl.data[a.visitorType||"ListVisitor"]({fields:t,data:r}),d=new rl.data.Table({dataVisitor:l,uniqueCheck:a.uniqueCheck});if(delete a.uniqueCheck,!a.noRowNumberer){var u,o,s;if(u=r.length,u>0){var o=String(u).length;o>2&&(s=Math.max(Math.min(10*o,40),20))}e.unshift(new rl.gui.grid.RowNumberer({width:s||20})),delete a.noRowNumberer}var c=new rl.Grid(rl.ext({dataSource:d,columns:e,renderTarget:i,showPagingBar:!1,enbaleMultiSelect:!0,clickToSelect:!0,deferRenderBody:!0},a));return c}})});