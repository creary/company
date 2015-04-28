/// <reference path="../qunit/qunit.js" />
/// <reference path="../../jsrender.js" />
!function(e,t,l){"use strict";!function(){function e(e){var t="";if(this.tagCtx.props.reverse)for(var l=e.length;l;l--)t+=this.tagCtx.render(e[l-1]);else t+=this.tmpl.render(e);return t}var m=window.attachEvent&&!window.addEventListener,r={name:"Jo"},a=[{name:"Jo"},{name:"Bill"}],n="A_{{:name}}_B";module("api"),test("templates",16,function(){equal(t.templates("#myTmpl2").render(),m?"\n' \" \\ \\' \\\"":"' \" \\ \\' \\\"","correct treatment of ' \" and ' in template declared in script block"),equal(t.templates("' \" \\ \\' \\\"").render(),"' \" \\ \\' \\\"","correct treatment of ' \" and ' in template compiled from string"),t.templates("myTmpl",n),equal(t.render.myTmpl(r),"A_Jo_B",'Compile a template and then render it: $.templates("myTmpl", tmplString); $.render.myTmpl(data);'),t.templates({myTmpl2:n}),equal(t.render.myTmpl2(r),"A_Jo_B",'Compile and register templates: $.templates({ "myTmpl", tmplString, ...  }); $.render.myTmpl(data);'),equal(t.templates.myTmpl2.render(r),"A_Jo_B","Get named template: $.templates.myTmpl.render(data);"),equal(t.templates(n).render(r),"A_Jo_B","Compile without registering as named template: $.templates(tmplString).render(person);");var e=t.templates("#myTmpl");equal(t.trim(e.render(r)),"A_Jo_B",'var tmpl = $.templates("#myTmpl"); returns compiled template for script element'),t.templates({myTmpl3:{markup:"#myTmpl"}}),equal(t.trim(t.render.myTmpl3(r)),"A_Jo_B",'Named template for template object with selector: { markup: "#myTmpl" }');var e=t.templates("#myTmpl");equal(t.trim(e.render(r)),"A_Jo_B",'var tmpl = $.templates("#myTmpl"); returns compiled template for script element');var a=t.templates("",{markup:"#myTmpl"});equal(t.trim(a.render(r)),"A_Jo_B",'Compile from template object with selector, without registering: { markup: "#myTmpl" }'),equal(t.templates("#myTmpl").fn===e.fn&&e.fn===a.fn,!0,'$.templates("#myTmpl") caches compiled template, and does not recompile each time;'),equal(e===t.templates("","#myTmpl"),!0,'$.templates("#myTmpl") and $.templates("", "#myTmpl") are equivalent');var p=t.templates("cloned","#myTmpl");equal(p!==e&&"cloned"==p.tmplName,!0,'$.templates("cloned", "#myTmpl") will clone the cached template'),t.templates({cloned:"#myTmpl"}),equal(t.templates.cloned!==e&&"cloned"==t.templates.cloned.tmplName,!0,'$.templates({ cloned: "#myTmpl" }) will clone the cached template'),t.templates("myTmpl",null),equal(t.templates.myTmpl,l,'Remove a named template:  $.templates("myTmpl", null);');var a=t.templates({scriptTmpl:{markup:"#myTmpl",debug:!0},tmplFromString:{markup:"testDebug",debug:!0}});equal(t.templates.tmplFromString.fn.toString().indexOf("debugger;")>0&&t.templates.scriptTmpl.fn.toString().indexOf("debugger;")>0,!0,"Debug a template:  set debug:true on object")}),test("render",5,function(){equal(t.trim(t("#myTmpl").render(r)),"A_Jo_B","$(tmplSelector).render(data);");t.templates("myTmpl4",n);equal(t.render.myTmpl4(r),"A_Jo_B","$.render.myTmpl(object);"),equal(t.render.myTmpl4(a),"A_Jo_BA_Bill_B","$.render.myTmpl(array);");var e=t.templates.myTmpl4;equal(e.render(a),"A_Jo_BA_Bill_B","var tmplObject = $.templates.myTmpl; tmplObject.render(data);"),t.templates("myTmpl5","A_{{for}}inner{{:name}}content{{/for}}_B"),equal(t.templates.myTmpl5.tmpls[0].render(r),"innerJocontent","Nested template objects: $.templates.myTmpl.tmpls")}),test("converters",3,function(){function e(e){switch(e){case"desktop":return"bureau"}}t.views.converters({loc:e}),equal(t.templates("{{loc:#data}}:{{loc:'desktop'}}").render("desktop"),"bureau:bureau","$.views.converters({ loc: locFunction })"),t.views.converters("loc2",e),equal(t.views.converters.loc2===e,!0,"locFunction === $.views.converters.loc"),t.views.converters({loc2:null}),equal(t.views.converters.loc2,l,"Remove a registered converter: $.views.converters({ loc: null })")}),test("tags",3,function(){t.views.tags({sort:e}),equal(t.templates("{{sort people reverse=true}}{{:name}}{{/sort}}").render({people:a}),"BillJo","$.views.tags({ sort: sortFunction })"),t.views.tags("sort2",e),equal(t.views.tags.sort.render===e,!0,"sortFunction === $.views.tags.sort"),t.views.tags("sort2",null),equal(t.views.tags.sort2,l,"Remove a registered tag: $.views.tag({ sor: null })")}),test("helpers",3,function(){function e(){return"".concat.apply("",arguments)}t.views.helpers({not:function(e){return!e},concat:e}),equal(t.templates("{{:~concat(a, 'b', ~not(false))}}").render({a:"aVal"}),"aValbtrue","$.views.helpers({ concat: concatFunction })"),t.views.helpers({concat2:e}),equal(t.views.helpers.concat===e,!0,"concatFunction === $.views.helpers.concat"),t.views.helpers("concat2",null),equal(t.views.helpers.concat2,l,"Remove a registered helper: $.views.helpers({ concat: null })")}),test("template encapsulation",1,function(){t.templates({myTmpl6:{markup:"{{sort reverse=true people}}{{:name}}{{/sort}}",tags:{sort:e}}}),equal(t.render.myTmpl6({people:a}),"BillJo",'$.templates("myTmpl", tmplObjWithNestedItems);')})}()}(this,this.jQuery);