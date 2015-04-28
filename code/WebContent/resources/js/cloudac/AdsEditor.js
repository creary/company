/**
 * @fileOverview Editor for ads.
 * Note: We have not specify the kindeditor lang module(like "open.kindeditor.lang.zh_CN") 
 * which should be load before current.
 * @change
    #1 by prcjack @2014/10/26 Create file.
 */

orderjs.define("cloudac:AdsEditor", [
	"open.jquery.jquery",
	(typeof JSON == "object") ? "" : "open.json.json2",
	"cloudac:SortableCardsEditor",
	"cloudac:CardEditor",
	"open.kindeditor.kindeditor"
], function(){
    var jQuery = orderjs("open.jquery.jquery"),
		SortableCardsEditor = orderjs("cloudac:SortableCardsEditor"),
		K = orderjs("open.kindeditor.kindeditor"),
		CardEditor = orderjs("cloudac:CardEditor");
	
	/**
	 * @class Editor for card, an itemEditor for SortableCardsEditor.
	 * @extends rl.util.EventProvider
	 * @constructor
	 * @description Construct a instance by specified config.
	 * @param {Object} [config]
	 */
	var AdsEditor = rl.createClass({
		parent : rl.util.EventProvider,
		
		construct : function(config){
			if(rl.isPrototyping(arguments[0])) return ;
			
			AdsEditor.parent.apply(this, arguments);
			this.initialize.apply(this, arguments);
		},
		
		members : {
			/** @lends AdsEditor# */
			
			dataField : "",
			
			/**
			 * listEditor, auto created.
			 * @type SortableCardsEditor
			 */
			listEditor : null,
			
			listEditorOptions : null,
			
			/**
			 * listEditor, auto created.
			 * @type CardEditor
			 */
			listItemEditor : null,
			
			listItemEditorOptions : null,
			
			listItemEditorUploaderOptions : null,
			
			initialize : function(config){
				this.setOptions(config);
				this.createListEditor(this.listEditorOptions);
				this.createListItemEditor(this.listItemEditorOptions);
				this.createListItemEditorUploader(this.listItemEditorUploaderOptions);
			},
			
			setOptions : function(options){
				rl.ext(this, options);
			},
		
			createListEditor : function(options){
				if(this.listEditor) return this.listEditor;
				var opt = rl.ext({
					updateItemView : rl.bind(this.updateListItemView, this)
				}, options);
				
				rl.dir(opt, "createListEditor() opt");
				
				var editor = this.listEditor = new SortableCardsEditor(opt);
				
				editor.on("itemEdit", this.updateDataField, this);
				editor.on("itemsChange", this.updateDataField, this);
				
				return editor;
			},
		
			createListItemEditor : function(options){
				var editor = this.listItemEditor;
				if(!editor){
					var opt = rl.ext({
						owner : this.listEditor,
						getEditingValues : rl.bind(this.LIE_getEditingValues, this),
						setEditingValues : rl.bind(this.LIE_setEditingValues, this),
						validation : rl.bind(this.LIE_validation, this)
					}, options);
					
					rl.dir(opt, "createListItemEditor() opt");
				
					editor = this.listItemEditor = new CardEditor(opt);
					this.listEditor.itemEditor = editor;
				}
				
				if(!rl.isNonNullStr(editor.editingImageHolder)){
					throw new Error(this + " createListItemEditor(): Invalid options: " + 
									"editingImageHolder is requried!");
				}
				
				return editor;
			},
		
			createListItemEditorUploader : function(options){
				if(this.listItemEditorUploader) return this.listItemEditorUploader;
				var opt = rl.ext({
					afterUpload : rl.bind(this.LIEU_afterUpload, this),
					afterError : rl.bind(this.LIEU_afterError, this)
				}, options);
				
				var uploader = this.listItemEditorUploader = K.uploadbutton(opt);
				uploader.fileBox.change(function(e) {
					uploader.submit();
				});
				
				return uploader;
			},
		
			updateDataField : function(){
				var ads = this.listEditor.getAllValues();
				
				jQuery(this.dataField).val(JSON.stringify(ads));
			},
			
			updateListItemView : function(itemEle, values){
				if(values.itemWidth) jQuery(itemEle).width(values.itemWidth);
				jQuery(".thumbnail", itemEle)
					.attr({
						"src" : values.src,
						"data-href" : values.href
					});
			},
			
			/**
			 * getEditingValues method for listItemEditor
			 */
			LIE_getEditingValues : function(){
				return {
					src : jQuery(this.listItemEditor.editingImageHolder).attr("src"),
					href : jQuery(this.listItemEditor.editingLinkField).val()
				};
			},
			
			/**
			 * setEditingValues method for listItemEditor
			 */
			LIE_setEditingValues : function(values){
				var editingImageHolderJq = jQuery(this.listItemEditor.editingImageHolder);
				if(rl.isNonNullStr(values.src)){
					editingImageHolderJq
						.attr("src", values.src)
						.css("display", "inline");
				}
				else{
					editingImageHolderJq
						.attr("src", "")
						.css("display", "none");
				}
				jQuery(this.listItemEditor.editingLinkField).val(values.href || "");
			},
			
			/**
			 * validation method for listItemEditor
			 */
			LIE_validation : function(){
				var editor = this.listItemEditor,
					imgJq = jQuery(editor.editingImageHolder),
					linkJq = jQuery(editor.editingLinkField);
				
				if(!rl.isNonNullStr(imgJq.attr("src"))){
					alert("必须上传图片！");
					return false;
				}
				
				var href = rl.trim(linkJq.val());
				if(rl.isNonNullStr(href)){//user specified href.
					linkJq.val(href);
					if(!/^http(s)?:\/\/.*?$/.test(href)){
						alert("请输入以 http:// 或 https:// 开头的 URL！");
						return false;
					}
				}
				
				return true;
			},
			
			/**
			 * afterUpload method for listItemEditorUploader
			 */
			LIEU_afterUpload: function(data){
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');
					rl.debug(this + ' url = ' + url + '');
					jQuery(this.listItemEditor.editingImageHolder)
						.attr("src", url)
						.css("display", "inline");
				} else {
					alert(data.message);
				}
			},
			
			/**
			 * afterError method for listItemEditorUploader
			 */
			LIEU_afterError: function(str){
				alert('抱歉！上传文件发生错误: ' + str);
			},
			
			toString : function(){return "[object AdsEditor]";}
		}
	});

	return AdsEditor;
});
