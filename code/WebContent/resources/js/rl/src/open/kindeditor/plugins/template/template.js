/*******************************************************************************
* KindEditor - WYSIWYG HTML Editor for Internet
* Copyright (C) 2006-2011 kindsoft.net
*
* @author Roddy <luolonghao@gmail.com>
* @site http://www.kindsoft.net/
* @licence http://www.kindsoft.net/license.php
*******************************************************************************/
KindEditor.plugin("template",function(e){function a(a){return t+a+"?ver="+encodeURIComponent(e.DEBUG?e.TIME:e.VERSION)}var l=this,i="template",t=(l.lang(i+"."),l.pluginsPath+i+"/html/");l.clickToolbar(i,function(){var t=l.lang(i+"."),c=['<div style="padding:10px 20px;">','<div class="ke-header">','<div class="ke-left">',t.selectTemplate+" <select>"];e.each(t.fileList,function(e,a){c.push('<option value="'+e+'">'+a+"</option>")}),html=[c.join(""),"</select></div>",'<div class="ke-right">','<input type="checkbox" id="keReplaceFlag" name="replaceFlag" value="1" /> <label for="keReplaceFlag">'+t.replaceContent+"</label>","</div>",'<div class="ke-clearfix"></div>',"</div>",'<iframe class="ke-textarea" frameborder="0" style="width:458px;height:260px;background-color:#FFF;"></iframe>',"</div>"].join("");var n=l.createDialog({name:i,width:500,title:l.lang(i),body:html,yesBtn:{name:l.lang("yes"),click:function(){var a=e.iframeDoc(d);l[o[0].checked?"html":"insertHtml"](a.body.innerHTML).hideDialog().focus()}}}),r=e("select",n.div),o=e('[name="replaceFlag"]',n.div),d=e("iframe",n.div);o[0].checked=!0,d.attr("src",a(r.val())),r.change(function(){d.attr("src",a(this.value))})})});