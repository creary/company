/*******************************************************************************
* KindEditor - WYSIWYG HTML Editor for Internet
* Copyright (C) 2006-2011 kindsoft.net
*
* @author Roddy <luolonghao@gmail.com>
* @site http://www.kindsoft.net/
* @licence http://www.kindsoft.net/license.php
*******************************************************************************/
KindEditor.plugin("link",function(e){var l=this,i="link";l.plugin.link={edit:function(){var t=l.lang(i+"."),n='<div style="padding:20px;"><div class="ke-dialog-row"><label for="keUrl" style="width:60px;">'+t.url+'</label><input class="ke-input-text" type="text" id="keUrl" name="url" value="" style="width:260px;" /></div><div class="ke-dialog-row""><label for="keType" style="width:60px;">'+t.linkType+'</label><select id="keType" name="type"></select></div></div>',a=l.createDialog({name:i,width:450,title:l.lang(i),body:n,yesBtn:{name:l.lang("yes"),click:function(){var i=e.trim(o.val());return"http://"==i||e.invalidUrl(i)?(alert(l.lang("invalidUrl")),void o[0].focus()):void l.exec("createlink",i,c.val()).hideDialog().focus()}}}),d=a.div,o=e('input[name="url"]',d),c=e('select[name="type"]',d);o.val("http://"),c[0].options[0]=new Option(t.newWindow,"_blank"),c[0].options[1]=new Option(t.selfWindow,""),l.cmd.selection();var r=l.plugin.getSelectedLink();r&&(l.cmd.range.selectNode(r[0]),l.cmd.select(),o.val(r.attr("data-ke-src")),c.val(r.attr("target"))),o[0].focus(),o[0].select()},"delete":function(){l.exec("unlink",null)}},l.clickToolbar(i,l.plugin.link.edit)});