orderjs.define("app.dataBox.engine",["lib.rpc.engine","gui.engine","css>rl:databox"],function(){var t=rl.getG11nSource("rl.dataBox.CompPanel")||{};rl.createNamespace("rl.dataBox",{createPanels:function(t,i,e){rl.isObject(t)?rl.isArray(t)||(t=[t]):t=[{}],(rl.isNonNullStr(i)||rl.isElement(i))&&(i={renderTarget:i}),e?rl.isNonNullStr(e)&&(e=rl.dataBox[e]):e=rl.dataBox.CompPanel;var r=Array.map(function(t){return new e(rl.ext(t||{},i))},t);return r.length>1?r:r[0]},truncateText:function(t,i,e){if(!rl.isNonNullStr(t))return"";rl.isString(e)||(e=e===!1?"":"...");var r=e.length;return i>r||(i=r+1),t.bLen()>i&&(t=t.bSubstring(0,i-r)+e),t}}),rl.dataBox.CompPanel=rl.createClass({parent:rl.gui.ControlBase,construct:function(){rl.isPrototyping(arguments[0])||(rl.dataBox.CompPanel.parent.apply(this,arguments),this.initialize())},members:{data:null,dataUrl:"",dataExpectRspType:"json",dataView:null,dataViewType:"",dataViewOptions:null,caption:"",captionPosition:"tl",captionCss:"",autoRenderOnReady:!0,autoLoadOnRender:!0,autoShowRetryOnError:!0,loadingMsg:t.loadingMsg||"Loading...",noDataMsg:t.noDataMsg||"No data to display.",dataLoadErrMsg:t.dataLoadErrMsg||"Load data error.",dataRequestFailureMsg:t.dataRequestFailureMsg||"Request data failed.",dataRequestTimeoutMsg:t.dataRequestTimeoutMsg||"Request data timeout.",retryButtonText:t.retryButtonText||"Retry",colspan:2,minColspan:2,maxColspan:6,rowspan:2,minRowspan:2,maxRowspan:4,_captionAlign2Css:{l:"caption_left",c:"caption_center",r:"caption_right"},_htmlTpl:new rl.HtmlTpl('<div class="rl_databox_comp_panel {css}" id="{id}" style="{width}" ><table class="main_wrapper" border="0" cellspacing="0" cellpadding="0">{captionTopRowHtml}<tr><td id="{contentWrapperId}"><div class="p_tip_pos_wrapper"><div class="p_tip_wrapper" id="{tipWrapperId}"><div class="p_tip_shim"></div><div class="p_tip" id="{tipCtnId}">{tip}</div></div></div><div id="{compCtnId}" class="comp_ctn"></div></td></tr>{captionBottomRowHtml}</table></div>'),_captionRowTpl:new rl.HtmlTpl('<tr><td id="{ctnId}" class="p_caption {css}">{caption}</td></tr>'),_retryButtonTpl:new rl.HtmlTpl('{tip} <a href="javascript:void(0);" onclick="{retryAction}">{retryButtonText}</a>'),initialize:function(){rl.isNonNullStr(this.id)||(this.id=this.autoId()),this.captionCtnId=this.id+"_captionCtn",this.contentWrapperId=this.id+"_contentWrapper",this.tipCtnId=this.id+"_tipCtn",this.tipWrapperId=this.id+"_tipWrapper",this.compCtnId=this.id+"_compCtn",this.dw=new rl.dom.DomWrap,this.initDataView(),this.initHttpRequest(),this.autoRenderOnReady&&rl.onReady(this.render,this)},render:function(t){if(this._rendered)return this;t=this.getRenderTarget(t);var i=this._getCaptionHtmlDetail(),e=this._htmlTpl.renderTo(t,{id:this.id,contentWrapperId:this.contentWrapperId,tipCtnId:this.tipCtnId,tipWrapperId:this.tipWrapperId,compCtnId:this.compCtnId,css:this.css,captionTopRowHtml:i.tHtml,captionBottomRowHtml:i.bHtml,width:this.width?"width : "+rl.dom.addUnit(this.width)+";":""},this.renderPosition);return this.dw.setTarget(e),this._rendered=!0,this.showTip(this.noDataMsg),this.autoLoadOnRender&&this.load(),this.fireEvent("render"),this},_getCaptionHtmlDetail:function(){if(!rl.isNonNullStr(this.caption))return{};var t,i,e,r={},a=this.captionPosition.toLowerCase()||"tl";return i=a.charAt(0),e=a.charAt(1),t=this._captionAlign2Css[e],r[i+"Html"]=this._captionRowTpl.assign({caption:this.caption,ctnId:this.captionCtnId,css:this.captionCss+" "+t}),r},initDataView:function(){this.dataView||(this.dataView=this.createDataView())},createDataView:function(){var t=rl.dataBox[this.dataViewType];if(t){var i={owner:this,autoRenderOnReady:!1,renderTarget:this.compCtnId};return new t(rl.fill(i,this.dataViewOptions))}rl.debug(this+"->createDataView(): Invalid dataViewType= "+this.dataViewType,"err")},initHttpRequest:function(){this.httpRequest||(this.httpRequest=this.createHttpRequest());var t=this.httpRequest;if(!(t instanceof rl.rpc.AbstractHR))throw{description:this+"->initialize(): Invalid httpRequest!"};t.on("success",this.hndDataRequestSuccess,this),t.on("failure",this.hndDataRequestFailure,this),t.on("timeout",this.hndTimeoutRequest,this)},createHttpRequest:function(){return new rl.rpc.XHRequest({url:this.dataUrl,responseType:this.dataExpectRspType,disableCache:!0,async:!0})},load:function(t){return this.showTip(this.loadingMsg),this._lastRequestOptions=t,this._lastRequestData=this.data,delete this.data,this.httpRequest.request(t),this},reload:function(){return this.load(this._lastRequestOptions)},setDataUrl:function(t){return this.dataUrl=t,this.httpRequest.url=t,this},showTip:function(t){var i=rl.getDom(this.contentWrapperId),e=rl.getDom(this.tipWrapperId).parentNode,r=i.scrollHeight;return r>0?(rl.$(e).setHeight("auto"),rl.$(this.tipWrapperId).setHeight(r)):(rl.$(e).setHeight("30px"),rl.$(this.tipWrapperId).setHeight("100%")),rl.$(e).show(),rl.$(this.tipCtnId).setInnerHTML(t),this},hideTip:function(){var t=rl.getDom(this.tipWrapperId).parentNode;return rl.$(t).hide(),this},showRetry:function(t){var i=this._retryButtonTpl.assign({tip:t,retryAction:"",retryButtonText:this.retryButtonText});return this.showTip(i),this},hndDataRequestSuccess:function(t,i){this.hideTip();try{this.data=t,this.dataView.loadData(t),this.fireEvent("DataLoad")}catch(e){this.fireEvent("DataLoadError",e)!==!0&&this.autoShowRetryOnError&&(rl.dir(e,"["+(this.caption||this.dataUrl)+"] "+this.dataLoadErrMsg),this.showRetry(this.dataLoadErrMsg))}this.fireEvent("DataRequestSuccess",t,i)},hndDataRequestFailure:function(){this.fireEvent("DataRequestFailure",err)!==!0&&this.autoShowRetryOnError&&this.showRetry(this.dataRequestFailureMsg)},hndTimeoutRequest:function(){this.fireEvent("DataRequestTimeout",err)!==!0&&this.autoShowRetryOnError&&this.showRetry(this.dataRequestTimeoutMsg)},toString:function(){return"[object rl.dataBox.CompPanel]"}}}),rl.dataBox.KpiViewHtml=rl.createClass({parent:rl.gui.ControlBase,construct:function(t){rl.isPrototyping(arguments[0])||(rl.dataBox.KpiViewHtml.parent.call(this,t),this.initialize())},members:{contentHtml:"",autoRenderOnReady:!1,width:void 0,height:void 0,_htmlTpl:new rl.HtmlTpl('<div id="{id}" style="{widthStyle} {heightStyle}">{contentHtml}</div>'),initialize:function(){rl.isNonNullStr(this.id)||(this.id=this.autoId()),this.dw=new rl.dom.DomWrap,this.autoRenderOnReady&&rl.onReady(this.render,this)},render:function(t){if(this._rendered)return this;t=this.getRenderTarget(t);var i=rl.dom.insertHtml(this.renderPosition,this.getRenderHtml(),t);return this.dw.setTarget(i),this.iniStyle&&this.dw.setStyle(this.iniStyle),this._rendered=!0,this.fireEvent("render"),this},getRenderHtml:function(){return this._htmlTpl.assign({id:this.id,widthStyle:this.width?"width : "+rl.dom.addUnit(this.width)+";":"",heightStyle:this.height?"height : "+rl.dom.addUnit(this.height)+";":"",contentHtml:this.contentHtml})},loadData:function(t){if(!rl.isObject(t)||!rl.isNonNullStr(t.contentHtml)){var i=this+"-> loadData(): Invalid data = "+t;throw{description:i}}this.contentHtml=t.contentHtml,this._rendered?this.dw.setInnerHTML(this.contentHtml):this.render()},toString:function(){return"[object rl.dataBox.KpiViewHtml]"}}})});