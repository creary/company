orderjs.define("gui.form.engine",function(){rl.createNamespace("rl.gui.form",{getForm:function(e){if(rl.isNonNullStr(e)){if(e=document.forms[e],rl.isElement(e))return e;e=rl.getDom(e)}return rl.isElement(e)&&"form"===e.tagName.toLowerCase()?e:null},getFormItemValue:function(e,t){if(!e||rl.isElement(e)&&e.disabled)return void 0;var r,i=rl.gui.form;if(i.isItemList(e))r=[],Array.each(function(e){if(!e.disabled&&e.checked){var t="checkbox"==e.type.toLowerCase()?e.value||"on":e.value;r.push(t)}},e);else if(e.options){r=[];var n=!e.multiple&&t?1:0;Array.each(function(e){!e.disabled&&e.selected&&r.push(i.getOptionItemValue(e))},e.options,this,n)}else r=e.value;return rl.debug(i.isItemList(e)?this+"formItem.item(0).type = "+e.item(0).type:this+" formItem.type = "+e.type),r},getOptionItemValue:rl.IE?function(e){return e.attributes.value.specified?e.value:e.text}:function(e){return e.hasAttribute("value")?e.value:e.text},isItemList:function(e){try{return!(rl.isElement(e)||!e.length||e.item(0)!==e[0])}catch(t){return!1}}})});