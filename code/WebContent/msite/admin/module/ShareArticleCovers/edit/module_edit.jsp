<%@page language="java" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
orderjs.config("shim", {
	"open.jsrender.jsrender" : {
		deps : ["open.jquery.jquery"]
	}
});

orderjs(
	"open.jquery.jquery",
	"lib.rpc.JqAjax",
	"cloudac:SortableCardsEditor"
);

orderjs(function(){
    //rl.console.showOnReady();
	var jQuery = orderjs("open.jquery.jquery"),
		JqAjax = orderjs("lib.rpc.JqAjax"),
		SortableCardsEditor = orderjs("cloudac:SortableCardsEditor");
    
	//observe shares data.
	window.shareSelectionObserver = new rl.util.EventProvider;
	shareSelectionObserver.on("change", function(){
		shareSelectionObserver.changed = true;
	});
	
	rl.onReady(function(){
		JqAjax.request({
			url : "${base}portal/getShares.do?pid=${pid}",
			success : function(data){
				function updateShareFields(){
					var shares = sharesEditor.getAllValues(),
						ids = rl.map(shares, function(item){return item.id;});
					
					jQuery("#field_shares").val(String(ids));
				}
	
				var sharesEditor = new SortableCardsEditor({
					ascendingItems : false,
					target : "#ShareArticleCovers_editor",
					iniValues : data,
					itemTpl : "#ShareArticleCovers_coverTpl",
					deleteItem : function(listItem){
						var me = this,
							values = this.getItemValues(listItem);
						if(this.fireEvent("beforeDeleteItem", values) === false) return false;
						
						if(confirm(this.deleteItemConfirmMsg)){
							return JqAjax.request({
								url : "${base}portal/deleteShare.do?id=" + values.id,
								success : function(data){
									jQuery(listItem).remove();
									me.fireEvent("itemsChange", "delete", values);
								},
								error : function(msg){
									alert(msg);
								}
							});
						}
						
						return false;
					},
					itemEditor : {
						startAdd : function(){
							var url = "${base}portal/readyChooseShare.do?pid=${pid}",
								dlg = rl.page.open(url, "select_articles", rl.gui.paneDlgOpt_l);
							
							dlg.on("close", function(){
								sharesEditor.cancelAddItem();
								if(shareSelectionObserver.changed) location.reload();
							});
						},
						startEdit : function(values){
							rl.dir(values, "startEdit(): values");
							sharesEditor.cancelEditItem();
							var url = "${base}wifiant/readyShare.do?preview=true&sid=" + values.id;
							rl.page.open(url, "shareReader", {modal:true, width:350, height:550});
						}
					},
					initEvents : function(){
						var me = this;
						
						this.jq
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
							.on("click", function(event){
								var trigger_del = jQuery(event.target).closest(me.triggerOfDeleteItem, this)[0],
									listItem = jQuery(event.target).closest(me.itemSelector, this)[0];
								
								if(listItem){
									if(trigger_del){
										me.deleteItem(listItem);
										event.stopPropagation();
									}
									else{
										me.startEditItem(listItem);
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
					}
				});
			},
			error : function(msg){
				alert(msg);
			}
		});
	});
});

</script>
<link href="${cssPath}editable_cards.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}icons.css" rel="stylesheet" type="text/css" />
<script id="ShareArticleCovers_coverTpl" type="text/x-jsrender">
	<li>
		<div class="card">
			<img class="overlay trigger_delete_item" src="${imagePath}circle_close.gif" alt="删除" title="删除" />
			<div class="title">{{:title}}</div>
			<div class="cnt_wrap">
				<img class="thumbnail" src="{{>cover}}" />
				<a class="btn" href="javascript:void(0);">
					<div>查 看</div>
				</a>
			</div>
		</div>
	</li>
</script>
<div class="section">
	<input type="hidden" name="ShareArticleCovers_shares" id="field_shares" value="${ShareArticleCovers_shares}" />
    <h3>分享内容</h3>
    <p class="p5">
        设置要投放到本 Portal 的商讯。<span style="color:#090;">提示：添加和删除的操作将自动保存，无须手动保存。</span>
    </p>
    <div class="p10">
        <ul id="ShareArticleCovers_editor" class="soof_editable_cards titled_cards">
            <li class="static_item trigger_add_item">
                <div class="card static_card white">
                    <a class="btn" href="javascript:void(0);">
                        <div style="padding-top:60px;">
                        	<span class="soof_icon plus_gray s32"></span>
                        	<p class="p10">添加投放</p>
                        </div>
                    </a>
                </div>
            </li>
        </ul>
        <div class="clearer"></div>
    </div>
</div>
