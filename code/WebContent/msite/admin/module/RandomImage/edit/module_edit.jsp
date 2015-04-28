<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String sectionTitle = request.getParameter("sectionTitle");
	String sectionDesc = request.getParameter("sectionDesc");
	String imgAddText = request.getParameter("imgAddText");
	/**
	 * (4:4) cardBox scale(width : height), with unit 50px.
	 * determins the cardBox width( widthScale * 50) and height( heightScale * 50) 
	 * and relatived spacings.
	 */
	String cardBoxScale = request.getParameter("cardBoxScale");
	String hideImgHref = request.getParameter("hideImgHref");
	
	if(sectionTitle == null)
		sectionTitle = "大图广告";
	if(sectionDesc == null)
		sectionDesc = "大图广告。如添加多个则随机显示。";
	if(imgAddText == null)
		imgAddText = "添加广告";
	if(cardBoxScale == null)
		cardBoxScale = "4:4";
	
	
	String cardBoxScaleValues[] = cardBoxScale.split(":");
	int cardBoxWidth = new Integer(cardBoxScaleValues[0]) * 50;
	int cardBoxHeight = new Integer(cardBoxScaleValues[1]) * 50;
	int cardBoxBtnPadding = cardBoxHeight/2 - 10;
	
	request.setAttribute("hideImgHref", hideImgHref);
%>
<link href="${cssPath}editable_cards.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
orderjs.config("shim", {
	"open.jsrender.jsrender" : {
		deps : ["open.jquery.jquery"]
	},
	"open.kindeditor.kindeditor" : {
		deps : ["css>open.kindeditor.themes.default.default"],
		exports : "KindEditor"
	},
	"open.kindeditor.lang.zh_CN" : {
		deps : ["open.kindeditor.kindeditor", "css>open.kindeditor.themes.default.default"],
		exports : "KindEditor"
	}
});

orderjs(
	"open.jquery.jquery",
	"open.jsrender.jsrender",
	"cloudac:SortableCardsEditor",
	"cloudac:CardEditor",
	"cloudac:AdsEditor",
	"open.kindeditor.kindeditor",
	"open.kindeditor.lang.zh_CN"
);
</script>

<style type="text/css">
#RandomImage_editSection .fields_box{
	padding-bottom:0;
}
#RandomImage_editSection .soof_editable_cards li{
	width:<%=cardBoxWidth%>px;
	height:<%=cardBoxHeight%>px;
}
#RandomImage_editSection .soof_editable_cards .card .btn div{
	padding-top:<%=cardBoxBtnPadding%>px;
}
#RandomImage_imageEditor{
	display:none; 
	width:600px; 
	height:250px; 
	overflow:auto;
}
#RandomImage_imageEditor .soof_img_viewer{
	width:<%=cardBoxWidth%>px;
	height:<%=cardBoxHeight%>px;
}
#RandomImage_imageEditor .soof_img_viewer .mask{
	padding-top:<%=cardBoxBtnPadding%>px;
	line-height:normal;
}
</style>
<script type="text/javascript">
orderjs(function(){
    var jQuery = orderjs("open.jquery.jquery"),
		AdsEditor = orderjs("cloudac:AdsEditor");
	
	var RandomImage_editor = new AdsEditor({
		dataField : "#RandomImage_imagesField",
		listEditorOptions : {
			target : "#RandomImage_imagesEditor",
			iniValues : JSON.parse(jQuery("#RandomImage_imagesField").val() || "[]"),
			itemTpl : "#RandomImage_imageTpl",
		},
		listItemEditorOptions : {
			editDialogContent : "RandomImage_imageEditor",
			editingImageHolder : "#RandomImage_editingImage",
			editingLinkField : "#RandomImage_editingImageHref",
		},
		listItemEditorUploaderOptions : {
			button : jQuery('#RandomImage_uploadImage')[0],
			url : '${base}portal/advertise/upload_json.jsp?dir=image&folder=/acs/upload/',
		}
	});
});
</script>
<div id="RandomImage_editSection">
    <input id="RandomImage_imagesField" type="hidden" name="RandomImage_images" value='${RandomImage_images}' />
    <div id="RandomImage_imageEditor" class="soof_page">
        <div style="padding:2em;">
            <div class="section first">
                <h3>图片文件</h3>
                <p class="p5">
                    文件大小不超过 1MB；格式为 png、jpg、gif；设计师建议的大小为：宽 500 像素，高度不限。
                </p>
                <div class="p10">
                    <div class="soof_img_viewer">
                        <input type="hidden" id="field_file" name="file" />
                        <img src="" id="RandomImage_editingImage" class="img_holder" style="display:none;" />
                        <div class="mask">图片</div>
                    </div>
                    <div class="soof_img_viewer_upload_wrap">
                        <span><input type="button" id="RandomImage_uploadImage" class="btn white" value="上传图片"></span>
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
            
            <c:if test="${hideImgHref eq null}">
                <div class="section">
                    <h3>图片链接 URL</h3>
                    <p class="p5">用户点击图片时，打开的页面 URL（以 http:// 或 https:// 开头）。</p>
                    <p class="p10">
                        <input class="field" id="RandomImage_editingImageHref" /> <span class="desc">(可不填)</span>
                    </p>
                </div>
            </c:if>
        </div>
    </div>
    <script id="RandomImage_imageTpl" type="text/x-jsrender">
        <li>
            <div class="card">
                <img class="overlay trigger_delete_item" src="${imagePath}circle_close.gif" alt="删除" title="删除" />
                <img class="thumbnail" src="{{>src}}" data-href="{{>href}}" />
                <a class="btn" href="javascript:void(0);">
                    <div>编辑</div>
                </a>
            </div>
        </li>
    </script>
    <div class="section">
        <h3><%= sectionTitle %></h3>
        <p class="p5"><%= sectionDesc %></p>
        <div class="fields_box">
            <ul id="RandomImage_imagesEditor" class="soof_editable_cards">
                <li class="static_item trigger_add_item">
                    <div class="card static_card">
                        <a class="btn" href="javascript:void(0);">
                            <div><%= imgAddText %></div>
                        </a>
                    </div>
                </li>
            </ul>
            <div class="clearer"></div>
        </div>
    </div>
</div>
