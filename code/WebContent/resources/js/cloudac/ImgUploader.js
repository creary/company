/**
 * @fileOverview Img uploader.
 * Warning: this is a deco module, you should define the target style(defaults to "css>cloudac:info").
 * Besides, this moudle requires following declares:
 * <code>
	orderjs.config("shim", {
		"open.kindeditor.kindeditor" : {
			deps : ["css>open.kindeditor.themes.default.default"],
			exports : "KindEditor"
		},
		"open.kindeditor.lang.zh_CN" : {
			deps : ["open.kindeditor.kindeditor", "css>open.kindeditor.themes.default.default"],
			exports : "KindEditor"
		}
	});
 * </code>
 * @change
 	#1 by prcjack @2015/04/08 Creates file.
 */

orderjs.define("cloudac:ImgUploader", [
	"open.jquery.jquery",
	"open.kindeditor.lang.zh_CN",
	"app.deco.engine"
], function(){
var track = this.track;
var jQuery = orderjs("open.jquery.jquery"),
	K = orderjs("open.kindeditor.kindeditor"),
	decoUtil = orderjs("app.deco.engine");

/**
 * @class ImgUploader
 * Img uploader decorator, formed of 2 parts: img holder and img upload button.
 * @usage
 * <code>
	//html code:
	&lt;div class="p10" id="fooImgUploader">
		&lt;input type="hidden" name="fooImg" value="${fooImg}" />
		&lt;div class="soof_img_viewer">
			&lt;img src="foo.jpg" class="img_holder" />
			&lt;div class="mask">图片&lt;/div>
		&lt;/div>
		&lt;div class="soof_img_viewer_upload_wrap">
			&lt;span>&lt;input type="button" class="btn white uploader" value="上传新图片">&lt;/span>
		&lt;/div>
		&lt;div class="clearer">&lt;/div>
	&lt;/div>
	
	//js code:
	orderjs(
		"open.kindeditor.lang.zh_CN",
		"cloudac:ImgUploader"
	);
	orderjs(function(){
		var ImgUploader = orderjs("cloudac:ImgUploader");
		
		orderjs.ready(function(){
			ImgUploader("#fooImgUploader");
		});
	});
 * </code>
 */    
var ImgUploader = rl.createClass({
    parent : decoUtil.FormContorl.decorator,
    
    construct : function(config){
        if(rl.isPrototyping(arguments[0])) return ;
        
        ImgUploader.parent.apply(this, arguments);
    },
    
	members : {
        /** @lends to ImgUploader# */
		/**
		 * (".img_holder") img holder selector
		 */
		imgHolderSelector : ".img_holder",
		
		/**
		 * (".img_holder") upload button selector
		 */
		uploadButtonSelector : ".uploader",
		
		/**
		 * (null) upload button ini options.
		 */
		uploadButtonOptions : null,
		
		/**
		 * upload button.
		 * @type K.uploadbutton
		 */
		uploadButton : null,
		
		/** 
		 * ("") upload url. You can specify it via the uploader's data-url attr.
		 */
		url : "",
		
		afterUpload : function(data) {    
			if (data.error === 0) {
				var url = K.formatUrl(data.url, 'absolute');
				this.updateField(url)
					.updateImgHolder(url);
			} else {
				alert(data.message);
			}
		},
        
		afterUploadError : function(str) {
			alert('抱歉！上传文件发生错误: ' + str);
		},
		
		afterUploadFileBoxChange : function() {
			this.uploadButton.submit();
		},
		
        initTarget : function(){
			this.initField()
				.updateImgHolder(this.fieldJq.val())
				.initUploadButton();
			
			return this;
        },
        
        initUploadButton : function(){
			var btnSelector = this.uploadButtonSelector,
				btnEle = this.jq.find(btnSelector)[0];
			if(!rl.isElement(btnEle)){
				rl.raiseError("Cant find uploadButton element by uploadButtonSelector = " + btnSelector,
					track(this, "initUploadButton"));
			}
			
			try{
				var btn = this.uploadButton = K.uploadbutton(rl.ext({
					button : btnEle,
					url : this.url || jQuery(btnEle).attr("data-url"),
					afterUpload : rl.bind(this.afterUpload, this),
					afterError : rl.bind(this.afterUploadError, this)
				}, this.uploadButtonOptions));
				btn.fileBox.change(rl.bind(this.afterUploadFileBoxChange, this));
			}catch(err){
				rl.raiseError(err, track(this, "initUploadButton"),
					"Error occurs on K.uploadbutton()");
			}
			
			return this;
        },
        
        updateImgHolder : function(url){
			var img = this.jq.find(this.imgHolderSelector);
			
			if(rl.isNonNullStr(url)){
				img.attr("src", url)
					.show();
			}
			else img.hide();
			
			return this;
        },
        
		toString : function(){
			return "[object ImgUploader]";
		}
	}
});

return decoUtil.makeJqDeco(ImgUploader);
});
