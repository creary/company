orderjs.define("gui.indicator.ProgressBar",["lib.util.Range","gui.engine","gui.indicator.IndicatorBase","css>rl:indicator_bar"],function(){rl.gui.indicator.ProgressBar=rl.createClass({parent:rl.gui.indicator.IndicatorBase,construct:function(t,r){rl.isPrototyping(arguments[0])||((rl.isNonNullStr(t)||!isNaN(t))&&(t={progress:t,renderTarget:r}),rl.gui.indicator.ProgressBar.parent.call(this,t))},members:{progress:0,text:void 0,textRenderer:null,textShow:"auto",barSkin:"",barSkinRule:null,autoHide:!1,width:"",height:"",iniTextStyle:null,_barWrapIdPostfix:"_barWrap",_textCtnPostfix:"_textCtn",_progressCtnIdPostfix:"_progressCtn",_htmlTpl:new rl.HtmlTpl('<table class="rl_comp rl_progressbar"  style="width:{width};" border="0" cellspacing="0" cellpadding="0">{textTop}<tr class="main_wrapper">{textLeft}<td class="pb_bar_wrap {barSkin}" id="{barWrapId}"  style=" height:{height}; line-height:{height};"><div class="pb_bar"><div class="pb_progress" id="{progressCtnId}"  style="width:{progress};">&nbsp;</div></div></td>{textRight}</tr>{textBottom}</table>'),onInitialize:function(){var t=this.id,r=rl.gui.indicator.ProgressBar;this.barWrapId=t+this._barWrapIdPostfix,this.progressCtnId=t+this._progressCtnIdPostfix,this.textCtnId=t+this._textCtnPostfix,this.progress=r.toProgressNum(this.progress),rl.isNonNullStr(this.barSkinRule)&&this.parseBarSkinRule()},render:function(t){if(this._rendered)return this;t=this.getRenderTarget(t);var r=rl.dom.insertHtml(this.renderPosition,this.getRenderHtml(),t);return this.dw.setTarget(r),this.iniStyle&&this.dw.setStyle(this.iniStyle),this.iniVisible||this.hide(),this.iniTextStyle&&this.setTextStyle(this.iniTextStyle),this._rendered=!0,this.fireEvent("render"),this},getRenderHtml:function(){var t,r=rl.dom.addUnit,i=rl.gui.indicator.ProgressBar,e=this.getLimitedBarSkin();return t={id:this.id,barWrapId:this.barWrapId,progressCtnId:this.progressCtnId,textCtnId:this.textCtnId,barSkin:e,progress:i.toProgressText(this.progress),width:r(this.width),height:r(this.height)},rl.ext(t,this._getTextDetails()),this._htmlTpl.assign(t)},getLimitedBarSkin:function(){var t,r=rl.gui.indicator.ProgressBar;if(rl.isFunction(this.barSkinRule)){var i=r.toProgressNum(this.progress);t=this.barSkinRule(i)}return t||this.barSkin||r.defaultBarSkin},parseBarSkinRule:function(){if(rl.isNonNullStr(this.barSkinRule)){var t=[];Array.each(function(r){if(rl.isNonNullStr(r)&&(r.trim(),r)){var i=r.split(":"),e=new rl.util.Range(i[1]);e.skin=i[0],t.push(e)}},this.barSkinRule.split(";")),t.length>0&&(this.barSkinRule=function(r){var i=Array.find(function(t){return t.contains(r)},t);return i?i.skin:void 0})}},_getTextDetails:function(){var t='<td class="pb_text"><div id="'+this.textCtnId+'">'+this.getText()+"</div></td>",r=String(this.textShow).toLowerCase().capitalize(),i={};switch(r){case"Auto":case"None":break;case"Right":case"Left":i["text"+r]=t;break;case"Right":case"Top":i["text"+r]="<tr>"+t+"</tr>"}return i},setBarSkin:function(t){var r=rl.gui.indicator.ProgressBar,i=this.barSkin||r.defaultBarSkin;if(t==i||!rl.isNonNullStr(t))return this;var e=rl.getDom(this.barWrapId);return rl.isElement(e)&&rl.$(e).removeClass(i).addClass(t),this.barSkin=t,this},setTextStyle:function(t){var r=rl.getDom(this.textCtnId);if(rl.isElement(r))try{rl.$(r).setStyle(t)}catch(i){rl.dir(i,this+" -> setTextStyle: Error")}return this},getContent:function(){return this.progress},getText:function(){var t=this.text,r=this.progress,i=rl.gui.indicator.ProgressBar;if(!rl.isNonNullStr(t)){var e=this.textRenderer;rl.isFunction(e)&&(t=e(r)),rl.isNonNullStr(t)||(t=i.toProgressText(r))}return t},updateContent:function(t){var r=rl.gui.indicator.ProgressBar,i=rl.getDom(this.progressCtnId),e=rl.getDom(this.textCtnId);return this.progress=r.toProgressNum(t),t=r.toProgressText(t),rl.isElement(i)&&(i.style.width=t),rl.isElement(e)&&rl.$(e).setText(this.getText()),rl.isFunction(this.barSkinRule)&&this.setBarSkin(this.getLimitedBarSkin()),this.autoHide&&"100%"==t&&Function.defer(this.hide,this),this},toString:function(){return"[object rl.gui.indicator.ProgressBar]"}}}),rl.gui.ctype2Classes.add("ProgressBar",rl.gui.indicator.ProgressBar),rl.ext(rl.gui.indicator.ProgressBar,{defaultBarSkin:"green",toProgressText:function(t){return String(rl.gui.indicator.ProgressBar.toProgressNum(t))+"%"},toProgressNum:function(t){return/%/.test(t)&&(t=Number(t.replace("%",""))),t=Math.ceil(t),isNaN(t)||0>t?t=0:t>100&&(t=100),t}})});