<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>投放商讯分享【${portal.name}】</title>
<link rel="rl-page-icon" href="${imagePath}doc.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"open.jquery.jquery",
	"lib.rpc.engine",
	"lib.rpc.OneoffSdtHelperMgr",
	"lib.data.bridge.JsonListVisitor",
	"gui.grid.Grid"
);


orderjs(function(){
	var jQuery = orderjs("open.jquery.jquery");
	
	//observe device data.
	window.articleDataObserver = new rl.util.EventProvider;
	articleDataObserver.on("change", function(){
		articleDataObserver.changed = true;
	});
	window.openArticleDlg = function(url, name){
		var dlg = rl.page.open(url, name, rl.gui.winDlgOpt);
		dlg.on("close", function(){
			if(articleDataObserver.changed){
				//reset articleDataObserver
				articleDataObserver.changed = false;
				mainGrid.load();
			}
		});
	};
	
	function genNameCol(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/advertiseReadyEdit.do?id=" + dataSource.getValue("id");
		return '<a href="javascript:void(0);" onclick="openArticleDlg(\'' + url + '\', \'advertiseReadyEdit\');">' + cellText + '</a>';    
	}
	
	function doEdit(cellText, colIndex, rowIndex, dataSource){
		var url = "${base}portal/advertiseReadyEdit.do?id=" + cellText;
		return '<a href="javascript:void(0);" onclick="openArticleDlg(\'' + url + '\', \'advertiseReadyEdit\');"><i class="soof_icon edit"></i></a>&nbsp;';
	}
	
	function notifySelectionToOpener(){
		var opener = rl.page.getOpener();
		if(rl.isObject(opener) && rl.isObject(opener.shareSelectionObserver)){
			opener.shareSelectionObserver.fireEvent("change");
		}
	}
	
	var orders = new rl.data.Table({
		primaryKey : "id",
		uniqueCheck : false,
		reqPagingLimit : "count",
        reqPagingPosition : "start",
		pagingLimit : 10,
		httpRequest : new rl.rpc.XHRequest({
			url : "${base}portal/getShareArticles.do?portalId=${portal.id}&branchId=${branchId}",
			responseType : "json",
			async : true
		}),
		dataVisitor : new rl.data.JsonListVisitor({
			fields : ["id","title","category","createTime"],
			rspMeta : "meta",
			rspRecords : "records",
			rspRecordsTotal : "total",
			rspPagesTotal : "pagesTotal"
		})
	});
	
	var mainGrid = new rl.Grid({
		dataSource : orders,
		renderTarget : "shareListCtn",
		theme : "lightgray",
		placeHolder : "gridLoadTip",
		enbaleMultiSelect: false,
		clickToSelect: true,
		deferRenderBody : true,
		showPagingBar : true,
		pbOptions : {
			showPagingLimit : false
		},
		columns : [
			{ctype : "RowSelector", multiple:false, selectorName : "radio"},	
			{caption: "标题", width: "400", name: "title", convert : genNameCol},	        
			{caption: "分类", width: "100", name: "category"},
			{caption: "创建时间", name: "createTime"},
			{caption: "编辑", name: "id", convert : doEdit, sortable:false}
		]
	});
		
	function doAction() {
		var len = mainGrid.getSelectedRows().length,
			url = "${base}portal/addShare.do?portalId=${portal.id}";
		if(!len){
			alert("请选择要投放的商讯");
			return;
		}
		var qs = mainGrid.body.encodeSelectedDataIdList2QS();
		url += (url.contains("?") ? "&" : "?") + qs;						
		rl.rpc.sdtGet(url, function(status, msg){
			notifySelectionToOpener();
			rl.page.close();
		}, function(status, msg){
			alert(msg);
		});
	}
	
	orderjs.ready(function(){
		jQuery("#btn_saveForm")
			.prop("disabled", false)
			.on("click", doAction);
	});
});
</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}icons.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="soof_page soof_selectbox_page">
	<div class="soof_page_viewport">
        <div class="soof_body">
            <div style="line-height:22px;">
                <button class="btn green s" style="float:right;" onclick='openArticleDlg("${base}portal/advertiseReadyAdd.do", "advertiseReadyAdd")'>添加商讯</button>
                <span class="desc">选择要投放到 Portal 【${portal.name}】分享页的商讯。</span>
                <div class="clearer"></div>
            </div>
            <div style="height:250px;" class="p10" id="shareListCtn">
                <div id="gridLoadTip" style="height:100%; border:solid 1px #ddd;">
                    <span class="desc" style="position:relative; top:40%; left:43%;"><img class="icon" src="${imagePath}wait.gif" /> 正在加载...</span>
                </div>
            </div>
        </div>
    </div>
    <div class="soof_selectbox_bar">
    	<div class="iwrap">
            <button id="btn_saveForm" type="button" class="btn primary">确 定</button>
            <button class="btn white" onclick="rl.page.close()">取消</button>
        </div>
    </div>
</div>
</body>
</html>