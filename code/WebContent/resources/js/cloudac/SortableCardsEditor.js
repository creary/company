/**
 * @fileOverview Editor for sortable cards.
 * @change
    #1 by prcjack @2014/05/27 Create file.
 */

orderjs.define("cloudac:SortableCardsEditor", [
	"open.jquery.jquery",
	"open.jsrender.jsrender",
	"open.jquery-ui.jquery-ui"
], function(){
var jQuery = orderjs("open.jquery.jquery");
/**
 * @class Editor for sortable cards.
 * @extends rl.util.EventProvider
 * @constructor
 * @description Construct a instance by specified config.
 * @param {Object} [config]
 */
var SortableCardsEditor = rl.createClass({
    parent : rl.util.EventProvider,
    
    construct : function(config){
        if(rl.isPrototyping(arguments[0])) return ;
        
        SortableCardsEditor.parent.apply(this, arguments);
		this.initialize.apply(this, arguments);
    },
    
    members : {
        /** @lends SortableCardsEditor# */
        
		autoDeco : true,
		
		cardHoverCss : "card_hover",
		
		deleteItemConfirmMsg : "确认删除？",
		
		editing : false,
		
		editingItem : null,
		
		/**
		 * Ini items values, if provided, items would be auto rendered.
		 */
		iniValues : null,
		
		/**
		 * Sort items in ascending order: the new comes are at the end.
		 */
		ascendingItems : true,
		
		itemEditor : null,
		
		itemSelector : "li:not(.static_item)",
		
		/**
		 * Item element template( jsrender template).
		 */
		itemTpl : "",
		
		jq : null,
		
		onSortUpdate : null,
		
		sortableOptions : null,
		
		target : "",
		
		triggerOfAddItem : "li.trigger_add_item",
		
		triggerOfDeleteItem : ".trigger_delete_item",
		
		/**
		 * Update item view on edit. This function accepts one param:
		 * {HTMLElement} itemEle Item element.
		 * {Object} values Values for item view.
		 * @type Function
		 */
		updateItemView : null,
		
        initialize : function(config){
			this.setOptions(config);
			if(this.autoDeco) rl.onReady(this.deco, this);
        },
        
		/**
		 * Add new item with specified values, invoked on endAddItem.
		 * @param {Object} values Ini values for item fields.
		 * @return {jQuery | null} Added item jquery object.
		 */
		addItem : function(values){
			if(!rl.isObject(values)) return null;
			var item, refTarget,
				content = jQuery(this.itemTpl).render(values),
				staticItem = this.jq.find(this.triggerOfAddItem),
				items = this.jq.find(this.itemSelector);
			
			rl.dir(values, this + "addItem() values");
			
			refTarget = this.ascendingItems ? staticItem : items.first();
			
			if(refTarget.length){
				item = refTarget
					.before(content)
					.prev()
					.data("values", values);
			}
			else{
				item = this.jq
					.append(content)
					.children()
					.last()
					.data("values", values);
			}
				
			return item;
		},
		
        cancelAddItem : function(){
			return this.endAddItem(null, true);
        },
        
        cancelEditItem : function(){
			return this.endEditItem(null, true);
        },
        
        deco : function(){
			this.jq = jQuery(this.target);
			
			rl.debug(this + ' this.initEvents = ' + this.initEvents + '');
			//rl.dir(this, this + "> deco", "a");
			
			try{
			this.initEvents()
				.renderItems()
				.fireEvent("deco");
			}catch(err){
				rl.dir(err);
			}
			
			return this;
        },
        
        deleteItem : function(listItem){
			var values = this.getItemValues(listItem);
			if(this.fireEvent("beforeDeleteItem", values) === false) return false;
			if(confirm(this.deleteItemConfirmMsg)){
				jQuery(listItem).remove();
				this.fireEvent("itemsChange", "delete", values);
			}
        },
        
        endAddItem : function(values, canceled){
			if(!this.editing ||
			   !this.itemEditor ||
			   this.fireEvent("beforeEndAddItem", values) === false) return false;
            
			if(!canceled){
				this.addItem(values);
				this.fireEvent("itemsChange", "add", values);
			}
			this.editing = false;
			
			return true;
        },
        
        endEditItem : function(values, canceled){
			if(!this.editing ||
			   !this.itemEditor ||
			   this.fireEvent("beforeEndEditItem", values) === false) return false;
            
			if(!canceled){
				jQuery(this.editingItem).data("values", values);
				this.updateItemView(this.editingItem, values);
				this.fireEvent("itemEdit", values);
			}
			this.editing = false;
			delete this.editingItem;
			
			return true;
        },
        
		getAllValues : function(){
			var me = this;
			return this.jq
				.find(this.itemSelector)
				.map(function(index, itemEle){
					return me.getItemValues(itemEle);
				})
				.get();
		},
		
		getItems : function(){
			return this.jq.find(this.itemSelector).get();
		},
		
		/**
		 * Get item values which are used as card editor ini values.
		 * This function should return an object.
		 * @type Function
		 */
		getItemValues : function(itemEle){
			if(!rl.isElement(itemEle)) return null;
			return jQuery(itemEle).data("values");
		},
		
        hndSortUpdate : function(event, ui){
			this.fireEvent("itemsChange", "sort");
        },
        
        initEvents : function(){
			var me = this;
			
			this.jq
				.sortable(rl.fill({
					items: this.itemSelector,
					update : rl.bind(this.hndSortUpdate, this),
					start : function(){
						me._itemDragged = true;
					},
					stop : function(){
						rl.delay(function(){
							me._itemDragged = false;
						}, 10);
					}
				}, this.sortableOptions))
				.on("mouseover", function(event){
					jQuery(event.target)
						.closest(me.itemSelector + " .card", this)
						.addClass(me.cardHoverCss);
				})
				.on("mouseout", function(event){
					jQuery(event.target)
						.closest(me.itemSelector + " .card", this)
						.removeClass(me.cardHoverCss);
				})
				.on("mousedown", function(event){
					var trigger_del = jQuery(event.target).closest(me.triggerOfDeleteItem, this)[0];
					if(trigger_del) event.stopPropagation();
				})
				.on("click", function(event){
					var trigger_del = jQuery(event.target).closest(me.triggerOfDeleteItem, this)[0],
						listItem = jQuery(event.target).closest(me.itemSelector, this)[0];
					
					if(listItem){
						if(trigger_del){
							event.stopPropagation();
							me.deleteItem(listItem);
						}
						else{
							if(!me._itemDragged) me.startEditItem(listItem);
						}
					}
					else{
						var trigger_add = jQuery(event.target).closest(me.triggerOfAddItem, this)[0];
						if(trigger_add){
							me.startAddItem();
						}
					}
				});
			
			return this;
        },
        
        renderItems : function(){
			var iniValues = this.iniValues;
			if(iniValues && iniValues.length){
				this.jq[this.ascendingItems ? "prepend" : "append"](
					jQuery( this.itemTpl ).render(iniValues)
				);
				var items = this.getItems();
				rl.each(iniValues, function(values, index){
					jQuery(items[index]).data("values", values);
				}, this);
			}
			
			return this;
        },
        
        startAddItem : function(){
			if(this.editing || !this.itemEditor || this.fireEvent("beforeStartAddItem") === false) return false;
			
			this.editing = true;
            this.itemEditor.startAdd();
			
			return true;
        },
        
        startEditItem : function(listItem){
			if(this.editing ||
			   !this.itemEditor ||
			   !jQuery(listItem).is("li"))return false;
			var values = this.getItemValues(listItem);
			if(this.fireEvent("beforeStartEditItem", values) === false) return false;
			
			this.editingItem = listItem;
			this.editing = true;
            this.itemEditor.startEdit(values);
			
			return true;
        },
        
        setOptions : function(options){
			rl.ext(this, options);
        },
        
        toString : function(){return "[object SortableCardsEditor]";}
    }
});

return SortableCardsEditor;
});
