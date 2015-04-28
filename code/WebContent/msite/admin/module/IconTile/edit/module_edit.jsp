<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
orderjs.config("shim", {
	"open.jsrender.jsrender" : {
		deps : ["open.jquery.jquery"]
	},
	"open.spectrum.spectrum" : {
		deps : ["open.jquery.jquery", "css>open.spectrum.spectrum"]
	}
});

orderjs(
	"open.jquery.jquery",
	(typeof JSON == "object") ? "" : "open.json.json2",
	"cloudac:SortableCardsEditor"
);

orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		SortableCardsEditor = orderjs("cloudac:SortableCardsEditor");
	//rl.console.showOnReady();

	function updateTileFields(){
		var tiles = tilesEditor.getAllValues();
		rl.dir(tiles, this + "updateTileFields() tiles", "d");
		
		jQuery("#IconTile_tilesField").val(JSON.stringify(tiles));
	}
	
	function getItemWdith(cols){
		if(cols >= 1){
			var padding = 12;//6 * 2
			return cols * 100 + (cols - 1) * padding + "px";
		}
	}
	
	var tiles = JSON.parse(jQuery("#IconTile_tilesField").val() || "[]");
	
	rl.dir(tiles, "tiles", "d");
	
	rl.each(tiles, function(values){
		if(!values.itemWidth) values.itemWidth = getItemWdith(values.cols);
	});
	
	var tilesEditor = new SortableCardsEditor({
		target : "#IconTile_editor",
		iniValues : tiles,
		itemTpl : "#IconTile_tileTpl",
		updateItemView : function(itemEle, values){
			rl.dir(values, this + "updateItemView (): values", "d");
			
			jQuery(itemEle).width(values.itemWidth);
			jQuery(".tile", itemEle).css("background-color", values.bgColor);
			jQuery(".tile .text", itemEle).text(values.text);
			jQuery(".tile .icon", itemEle).css("background-image", "url(" + values.icon + ")");
		}
	});
	tilesEditor.on("itemEdit", updateTileFields);
	tilesEditor.on("itemsChange", updateTileFields);
	
	orderjs(
		"cloudac:CardEditor",
		"open.spectrum.spectrum"
	);
	orderjs(function(){
		var CardEditor = orderjs("cloudac:CardEditor");
		
		function updateLinkSection(){
			var linkType = jQuery("input[name='IconTile_editingLinkType']:checked").val();
			rl.debug(this + 'updateLinkSection(): linkType = ' + linkType + '');
			if(linkType){
				jQuery("#IconTile_editingLinkSections")
					.removeClass("show_id_cat show_id_url")
					.addClass("show_id_" + linkType);
			}
			else{
				rl.debug(this + 'updateLinkSection() Invalid linkType = ' + linkType, "err");
			}
		}
		
		orderjs.ready(function(){
			jQuery("input[name='IconTile_editingLinkType']")
				.on("click", updateLinkSection);
			
			jQuery("#IconTile_editingBgColor").spectrum({
				cancelText: "取消",
				chooseText: "选 择",
				clearText: "清除当前选择",
				preferredFormat : "hex",
				showInput: true,
				allowEmpty:true
			});
		});
		var tileEditor = new CardEditor({
			owner : tilesEditor,
			editDialogContent : "IconTile_tileEditor",
			getEditingValues : function(){
				var values, cols,
					linkType = jQuery("input[name='IconTile_editingLinkType']:checked").val();
				if(!rl.isNonNullStr(linkType)){
					rl.debug(this + ' Invalid linkType = ' + linkType, "err");
					return null;
				}
				
				cols = parseInt(jQuery("#IconTile_editingCols").val());
				values = {
					linkType : linkType,
					cols : cols,
					itemWidth : getItemWdith(cols),
					bgColor : jQuery("#IconTile_editingBgColor").spectrum("get").toHexString(),
					icon : jQuery("#IconTile_editingCatIcon a.active img").attr("src")
				};
				
				if(linkType == "url"){
					values.url = jQuery("#IconTile_editingLink_url").val();
					values.text = jQuery("#IconTile_editingLink_text").val();
				}
				else{//linkType == "cat"
					values.cat = jQuery("#IconTile_editingCat").val();
					values.url = "${base}wifiant/list/view.do?branchId=${branchId}&pid=${pid}&cid=" + values.cat;
					values.text = jQuery("#IconTile_editingCat").find("option:selected").text();
				}
				
				rl.dir(values, this + "getEditingValues() values", "d");
				
				return values;
			},
			setEditingValues : function(values){
				rl.dir(values, "setEditingValues() values");
				if(!values) values = {};
				
				if(values.linkType){
					jQuery("input[name='IconTile_editingLinkType'][value='" + values.linkType + "']")
						.prop("checked", true);
				}
				
				if(values.linkType == "url"){
					jQuery("#IconTile_editingLink_url").val(values.url || "http://");
					jQuery("#IconTile_editingLink_text").val(values.text || "");
				}
				else{
					jQuery("#IconTile_editingLink_url").val("http://");
					jQuery("#IconTile_editingLink_text").val("");
					jQuery("#IconTile_editingCat").val(values.cat);
				}
				
				jQuery("#IconTile_editingCols").val(values.cols);
				jQuery("#IconTile_editingBgColor").spectrum("set", values.bgColor || "#F90");
				jQuery("#IconTile_editingCatIcon a.active").removeClass("active");
				if(rl.isNonNullStr(values.icon)){
					var fileName = values.icon.substring(values.icon.lastIndexOf("/") + 1),
						activeIcon = jQuery("#IconTile_editingCatIcon a img[src$='" + fileName + "']")
							.closest("a");
					
					if(activeIcon[0]){
						activeIcon
							.addClass("active")
							.get(0)
							.scrollIntoView()
					}
				}
			}
		});
		tilesEditor.itemEditor = tileEditor;
		tileEditor.on("startAdd", function(){
			jQuery("input[name='IconTile_editingLinkType'][value='cat']")
				.prop("checked", true);
			updateLinkSection();
			jQuery("#IconTile_editingCat")[0].selectedIndex = 0;
			jQuery("#IconTile_editingCols")[0].selectedIndex = 0;
		});
		tileEditor.on("startEdit", updateLinkSection);
	});
});
</script>
<link href="${cssPath}editable_cards.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.link_sections .link_section{
	display:none;
}
.show_id_cat .id_cat,
.show_id_url .id_url{
	display:block;
}
</style>
<input id="IconTile_tilesField" type="hidden" name="IconTile_tiles" value='${IconTile_tiles}' />
<div id="IconTile_tileEditor" class="soof_page" style=" display:none; width:600px; height:400px; overflow:auto;">
    <div style="padding:1em;">
        <div class="section first">
            <h3>磁贴链接</h3>
            <p class="p10">
                <input type="radio" class="field_selector" name="IconTile_editingLinkType" id="IconTile_editingLinkType_cat" value="cat" checked="checked" /><label class="selector_label" for="IconTile_editingLinkType_cat">链接至商讯列表页</label>
                &nbsp;&nbsp;
                <input type="radio" class="field_selector" name="IconTile_editingLinkType" id="IconTile_editingLinkType_url" value="url" /><label class="selector_label" for="IconTile_editingLinkType_url">链接至指定 URL</label>
            </p>
            <div class="link_sections" id="IconTile_editingLinkSections">
                <div class="p10 link_section id_cat">
                    请选择商讯列表页关联的商讯分类:
                    <select id="IconTile_editingCat" class="field s">
                    <c:forEach items="${categories}" var="cate">   
                        <option value="${cate.id}"> ${cate.name} </option>
                    </c:forEach>
                    </select>
                </div>
                <div class="p10 link_section id_url">
                    <p>请输入 URL:</p>
                    <p class="p5"><input id="IconTile_editingLink_url" class="field l" value="http://" /></p>
                    <p class="p10">请输入磁贴文字:</p>
                    <p class="p5"><input id="IconTile_editingLink_text" class="field s" /></p>
                </div>
            </div>
        </div>
        <div class="section">
            <h3>磁贴图标</h3>
            <p class="p5">
            	为磁贴选择一个图标，点击以下图标进行选择：
            </p>
            <p class="p10">
            	<div class="soof_icon_tiles_ctn" id="IconTile_editingCatIcon">
                    <ul class="soof_icon_tiles">
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/businessman1_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/businessmen26.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/person25.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/diamons.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/vip2.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/buy3_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="active"><img src="${base}resources/image/default/caticon/2442_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/call36_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/credit31_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/earth53.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/delivery2.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/delivery28_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/delivery38.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/pie27.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/seo15.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/new1.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/free1.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/off1.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/tag1.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/piggy9.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/chicken7_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/cooker1_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/dining_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/glass4_white.png" /></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="${base}resources/image/default/caticon/plate7_white.png" /></a>
                        </li>
                    </ul>
                    <div class="clearer"></div>
                    <script type="text/javascript">
					orderjs("open.jquery.jquery");
					orderjs(function(){
						var jQuery = orderjs("open.jquery.jquery");
						
						jQuery("#IconTile_editingCatIcon")
							.on("click", function(event){
							var activeItem = jQuery("#IconTile_editingCatIcon a.active"),
								evtItem = jQuery(event.target).closest("a");
							if(evtItem.hasClass("active")) return;
							
							activeItem.removeClass("active");
							evtItem.addClass("active");
							activeItem = evtItem;
						});
					});
					</script>
                </div>
            </p>
        </div>
        <div class="section">
            <h3>磁贴背景颜色</h3>
            <p class="p10">
            	请选择颜色值：<input class="field s" id="IconTile_editingBgColor" value="#F90" />
            </p>
        </div>
        <div class="section">
            <h3>磁贴宽度</h3>
            <p class="p5">
            	磁贴布局将一行分成 3 列，一个磁贴最多可占 3 列。 请选择该磁贴所占的列宽:
            </p>
            <p class="p10">
            	<select id="IconTile_editingCols" class="field s">
                	<option value="1"> 1 列</option>
                	<option value="2"> 2 列</option>
                	<option value="3"> 3 列</option>
                </select>
            </p>
        </div>
    </div>
</div>
<script id="IconTile_tileTpl" type="text/x-jsrender">
	<li style="width:{{>itemWidth}};">
		<div class="card">
			<img class="overlay trigger_delete_item" src="${imagePath}circle_close.gif" alt="删除" title="删除" />
			<a class="tile" style="background-color:{{>bgColor}};" data-cols="{{>cols}}" data-cat="{{>cat}}" data-url="{{>url}}" data-linkType="{{>linkType}}">
				<div class="icon" style="background-image:url({{>icon}});" data-src="{{>icon}}"></div>
				<div class="text">{{>text}}</div>
			</a>
			<a class="btn" href="javascript:void(0);">
				<div>编辑</div>
			</a>
		</div>
	</li>
</script>
<div class="section">
    <h3>磁贴导航</h3>
    <p class="p5">
    	磁贴即带图标的色块，用户点击磁贴后，将打开该磁贴的链接。
        磁贴可以链接至某个商讯分类，点击该磁贴则打开其关联分类下的商讯列表页。因此，在开始定制磁贴之前，我们建议您先规划好站内商讯的分类，以便导航。<a href="javascript:void(0);" id="tg_OpenCatListDlg">点击此处管理商讯分类</a>。
        点击磁贴以编辑磁贴属性，拖动可排列磁贴的次序。
		<script type="text/javascript">
        orderjs(
            "open.jquery.jquery"
        );
        orderjs(function(){
            var jQuery = orderjs("open.jquery.jquery");
            function openCatListDlg(){
                var url = "${base}portal/adCateListJsp.do";
                var catListDlg = rl.page.open(url, "propCateListJsp", rl.gui.paneDlgOpt_l);
                catListDlg.on("close", function onClose(){
                    catListDlg.un("close", onClose);
                    catListDlg = null;
                    window.location.reload();
                });
            }
            jQuery("#tg_OpenCatListDlg").on("click", openCatListDlg);
        });
        </script>
    </p>
    <div class="p10">
        <div class="soof_editable_tiles">
            <ul id="IconTile_editor" class="soof_editable_cards">
                <li class="static_item trigger_add_item" style="width:96%;">
                    <div class="card static_card">
                        <a class="btn" href="javascript:void(0);">
                            <div>添加磁贴</div>
                        </a>
                    </div>
                </li>
            </ul>
            <div class="clearer"></div>
        </div>
    </div>
</div>
