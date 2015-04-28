orderjs.define("gui.form.FormEase",["lib.dom.DomWrap","gui.form.Validator","css>rl:default"],function(){rl.createNamespace("rl.gui.form"),rl.gui.form.FormEase=rl.createClass({parent:rl.util.EventProvider,construct:function(e){rl.isPrototyping(arguments[0])||(rl.gui.form.FormEase.parent.call(this,e),rl.ext(this,e),this.initialize(e))},members:{form:null,autoObserveOnReady:!0,validator:null,submitter:null,messages:null,itemValidationCss:"",errMsgCss:"rl_error_msg",initialize:function(){this._itemsErrMsgTypeStore={},this._itemsErrMsgCtnStore={},this.initValidator(),this.autoObserveOnReady&&rl.onReady(Function.bind(this.observe,this))},initValidator:function(){var e=this.validator;e&&(e.on("ipass",this.hndItemValidateSuccess,this),e.on("ifail",this.hndItemValidateFailure,this))},observe:function(){var e=this.getForm(),r=this.validator;e&&r&&rl.$(e).on(e.addEventListener?"blur":"focusout",this.checkTargetValidation,this,!0)},checkTargetValidation:function(e){var r=this.getForm(),t=this.validator;if(r&&t&&t.rules){var i=rl.ew.init(e).getTarget();if(rl.isElement(i)){var s=i.name,a=t.rules[s];if(i.form!==r)return;i=r[s],Function.delay(t.validateItem,200,t,i,a,s)}}},submitForm:function(){var e=this.getForm();if(e){var r=this.validator,t=this.submitter;if(!r||(r.form=e,r.validate()))return t?t.submit():void e.submit()}},getForm:function(){var e=this.form;if(rl.isNonNullStr(e)){if(e=document.forms[e],rl.isElement(e))return e;e=rl.getDom(e)}return rl.$(e).isTag("form")?e:null},isSameItemErrMsg:function(e,r,t){var i=this._itemsErrMsgTypeStore[t];return i===r.errType},getItemErrMsgCtn:function(e,r,t){var i=this._itemsErrMsgCtnStore[t];return i||(i=this.createItemErrMsg(e,r,t),this._itemsErrMsgCtnStore[t]=i),i},getItemErrMsgContent:function(e,r,t){var i,s,a=r.errType,n=this.messages,l=rl.gui.form.FormEase.defaultErrMessages;if("required"===a)s=l.required;else{if(n&&(i=n[t],rl.isNonNullStr(i)?s=i:rl.isObject(i)&&(s=i.error)),!rl.isNonNullStr(s)){var l=rl.gui.form.FormEase.defaultErrMessages;rl.isNonNullStr(a)&&l[a]||(a="unknown"),s=l[a]||""}s=rl.format(s,r)}return s},createItemErrMsg:function(e,r,t){var i=document.createElement("span");rl.isNonNullStr(this.errMsgCss)&&(i.className=this.errMsgCss);var s=this.getItemErrMsgPlace(e,r,t);return rl.isElement(s)?s.appendChild(i):rl.dom.insertElements("afterend",i,e),i},showItemErrMsg:function(e,r,t){var i=this._itemsErrMsgCtnStore[t];i&&(i.style.display="")},hideItemErrMsg:function(e,r,t){var i=this._itemsErrMsgCtnStore[t];i&&(i.style.display="none")},hasItemErrMsgShown:function(e,r,t){var i=this._itemsErrMsgCtnStore[t];return i?rl.$(i).displayed():void 0},hndItemValidateFailure:function(e,r,t){if(!this.isSameItemErrMsg(e,r,t)){var i=this.getItemErrMsgCtn(e,r,t),s=this.getItemErrMsgContent(e,r,t);i.innerHTML=s,this._itemsErrMsgTypeStore[t]=r.errType}this.hasItemErrMsgShown(e,r,t)||this.showItemErrMsg(e,r,t),rl.$(e).addClass(this.itemValidationCss),rl.debug("this.itemValidationCss = "+this.itemValidationCss)},hndItemValidateSuccess:function(e,r,t){this.hasItemErrMsgShown(e,r,t)&&(this.hideItemErrMsg(e,r,t),rl.$(e).removeClass(this.itemValidationCss))},getItemErrMsgPlace:function(e,r,t){var i,s=this.messages;if(s){var a=s[t];rl.debug("name = "+t),a&&a.place&&(i=rl.getDom(a.place))}return i},toString:function(){return"[object rl.gui.form.FormEase]"}}}),rl.gui.form.FormEase.defaultErrMessages={required:"This field is required!",email:"Please input a valid email address!",phone:"Please input a valid phone number",mobile:"Please input a valid mobile number",url:"Please input a valid url",currency:"Please input a valid currency",number:"Can only input numbers",zip:"Please input a valid zip code",qq:"Please input a valid qq number",english:"Can only input english characters",chinese:"Can only input chinese characters",multiple_min:"Please select at least {min} item(s)!",multiple_max:"Please select at most {max} item(s)!",multiple_min_max:"Please select {min} ~ {max} item(s)!",unknown:"Please input a valid value!"}});