/**
 * @fileOverview Editor for card.
 * @change
    #1 by prcjack @2014/05/27 Create file.
 */

orderjs.define("cloudac:CardEditor", [
	"gui.engine",
	"gui.dialog.Dialog"
], function(){
var jQuery = orderjs("open.jquery.jquery");
/**
 * @class Editor for card, an itemEditor for SortableCardsEditor.
 * @extends rl.util.EventProvider
 * @constructor
 * @description Construct a instance by specified config.
 * @param {Object} [config]
 */
var CardEditor = rl.createClass({
    parent : rl.util.EventProvider,
    
    construct : function(config){
        if(rl.isPrototyping(arguments[0])) return ;
        
        CardEditor.parent.apply(this, arguments);
		this.initialize.apply(this, arguments);
    },
    
    members : {
        /** @lends CardEditor# */
		
		cancelAction : null,
        
		editDialog : null,
		
		editDialogContent : "",
		
		editDialogIconOfAddMode : rl.gui.mapIcon("add.gif"),
		
		editDialogIconOfEditMode : rl.gui.mapIcon("edit.gif"),
		
		editDialogOptions : null,
		
		editDialogTitleOfAddMode : "增加",
		
		editDialogTitleOfEditMode : "编辑",
		
		/**
		 * @type Function
		 */
		getEditingValues : null,
		
		iniEditMode : "edit",
		
		okAction : null,
		
		owner : null,
		
		/**
		 * @type Function
		 */
		setEditingValues : null,
		
        initialize : function(config){
			this.setOptions(config);
			this.initEditDialog();
			this.setEditMode(this.iniEditMode);
        },
        
        cancelEdit : function(){
			return this.endEdit(true);
        },
        
        endEdit : function(canceled){
			if(!this.editing || !this.owner) return false;
			var editMethod = this.editMode == "add" ? "AddItem" : "EditItem";
			
			if(canceled){
				this.owner["cancel" + editMethod]();
			}
			else{
				if(rl.isFunction(this.validation) && !this.validation()) return false;
				this.owner["end" + editMethod](this.getEditingValues());
			}
			this.hideEditDialog();
			this.editing = false;
			
			return true;
        },
        
        hideEditDialog : function(){
			if(this.editDialog) this.editDialog.hide();
        },
        
        initEditDialog : function(){
			this.editDialog = new rl.gui.dialog.Dialog(rl.ext({
				contentType : "dom",
				content : this.editDialogContent,
				iniVisible : false,
				modal : true,
				shadowOffset : 4,
				bottomBarItems : [
					{
						text : "取消",
						action : this.cancelAction || rl.bind(this.cancelEdit, this)
					},
					{
						text : "确 定",
						width : 150,
						action : this.okAction || rl.bind(this.endEdit, this)
					}
				],
				biCloseBtnOptions : {
					action : rl.bind(this.cancelEdit, this)
				}
			}, this.editDialogOptions));
        },
        
        scrollEditDialogContentToTop : function(){
			var cnt = rl.getDom(this.editDialogContent);
			if(rl.isElement(cnt)) cnt.scrollTop = 0;
			
			return this;
        },
        
        setEditMode : function(mode){
			if(!rl.isString(mode)) return this;
			mode = mode.toLowerCase();
			if(mode && mode != this.editMode){
				var dlg = this.editDialog;
				
				if(mode == "add"){
					dlg.setTitle(this.editDialogTitleOfAddMode);
					dlg.setIcon(this.editDialogIconOfAddMode);
				}
				else{
					dlg.setTitle(this.editDialogTitleOfEditMode);
					dlg.setIcon(this.editDialogIconOfEditMode);
				}
				this.editMode = mode;
				
				this.fireEvent("editModeChange", mode);
			}
			return this;
        },
        
        setOptions : function(options){
			rl.ext(this, options);
        },
		
        showEditDialog : function(){
			if(this.editDialog) this.editDialog.show();
			return this;
        },
		
        startAdd : function(){
			if(this.editing) return false;
			
			this.setEditMode("add");
			this.editing = true;
			this.setEditingValues({});
			this.showEditDialog();
			this.scrollEditDialogContentToTop();
			
			this.fireEvent("startAdd");
			
			return true;
        },
        
        startEdit : function(values){
			if(this.editing) return false;
			
			rl.dir(values, "startEdit(): values");
			
			this.setEditMode("edit");
			this.editing = true;
			this.setEditingValues(values);
			this.showEditDialog();
			this.scrollEditDialogContentToTop();
			
			this.fireEvent("startEdit");
			
			return true;
        },
        
        toString : function(){return "[object CardEditor]";}
    }
});

return CardEditor;
});
