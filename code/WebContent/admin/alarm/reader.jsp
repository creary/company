<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统公告</title>
<link rel="rl-page-icon" href="${imagePath}bell_blue.gif?_v=${cacheBuster}" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs.config("shim", {
	"open.jsrender.jsrender" : {
		deps : ["open.jquery.jquery"]
	}
});
orderjs(
	"open.jquery.jquery",
	"open.jRecordLoader.jRecordLoader",
	(typeof JSON == "object") ? "" : "open.json.json2",
	"lib.dom.engine",
	"lib.lang.Date",
	"lib.rpc.JqAjax"
);
orderjs(function(){
	//rl.console.showOnReady();
	var jQuery = orderjs("open.jquery.jquery"),
		jRecordLoader = orderjs("open.jRecordLoader.jRecordLoader"),
		JqAjax = orderjs("lib.rpc.JqAjax");
	
	var quickReadUpdateDelay = 5000;
	
	function toggleDetail(id){
		var feedJq = jQuery("#feed_" + id);
		
		rl.dir(feedJq);
		feedJq.toggleClass("collapsed");
		if(feedJq.hasClass("unread")){
			updateToReaded(id);
		}
	}
	
	function feedsFilter(records){
		return rl.map(records, function(record){
			var ct = Date.parseDate(record.createTime, "%Y-%m-%d %H:%M:%S");
			
			record.domId = "feed_" + record.id;
			record.month = Date.getShortMonthName(ct.getMonth());
			record.date = ct.getDate();
			record.createTimeText = "" + ct.getFullYear() + "-" + (ct.getMonth() + 1) + "-" + ct.getDate();
			
			return record;
		});
	}
	
	function notifyUnreadsChangeToTop(){
		var top = window.top;
		if(rl.isObject(top) &&
			rl.isObject(top.unreadsDataObserver) &&
			rl.isFunction(top.unreadsDataObserver.fireEvent)){
			top.unreadsDataObserver.fireEvent("change");
		}
	}
	
	function updateUnreadsInfo(){
		JqAjax.request({
			url : "${base}admin/getUnreadAnnouncementsCount.do",
			success : function(data){
				rl.debug(this + ' data = ' + data + '');
				var title = data > 0 ? rl.format("系统公告({0}条未读)", [data]) : "系统公告";
				rl.page.setTitle(title);
			},
			error : function(){
				rl.dir(arguments, "updateUnreadsInfo args");
			}
		});
	}
	
	function onRead(ids){
		updateReadedFeedsView(ids);
		updateUnreadsInfo();
		notifyUnreadsChangeToTop();
	}
	
	function updateToReaded(ids){
		if(rl.isEmpty(ids)) return false;
		if(!rl.isArray(ids)) ids = [ids];
		else if(!ids.length) return false;
		var url = "${base}admin/updateBatchAnnouncements.do",
			data = [];
			
		rl.each(ids, function(id){
			if(rl.isDefined(id)){
				data.push({
					"id" : id,
					"readFlag" : 1
				});
			}
		});
		if(!data.length) return false;
		
		JqAjax.request({
			url : url,
			data : "data=" + JSON.stringify(data),
			success : function(data){
				onRead(ids);
			},
			error : function(msg, status, jqXHR, data){
				rl.debug(this + ' updateToReaded() error msg = ' + msg + 
						' update failed ids = ' + data, "err");
				if(rl.isDefined(data)){
					var errIds = data.replace(/\s/g, "").split(","),
						updatedIds = [];
					
					updatedIds = rl.filter(ids, function(id){
						return !errIds.contains(id);
					});
					
					rl.debug(this + ' updateToReaded() updatedIds = ' + updatedIds + '');
					
					onRead(updatedIds);
				}
			}
		});
		
		return true;
	}
	
	function updateReadedFeedsView(ids){
		if(rl.isEmpty(ids)) return false;
		if(!rl.isArray(ids)) ids = [ids];
		else if(!ids.length) return false;
		
		jQuery("#feeds .feed")
			.each(function(){
				var id = jQuery(this).attr("data-id");
				if(id && ids.contains(id)){
					jQuery(this).removeClass("unread");
				}
			});
		
	}
	
	function updateAllQuickReads(){
		var ids = jQuery("#feeds .unread.quick_read")
			.map(function(){
				return jQuery(this).attr("data-id");
			})
			.get();
		updateToReaded(ids);
	}
	
	function initFeeds(){
		jQuery("#feeds .uninitialized")
			.each(function(){
				var ruler = jQuery(".content > .cnt_iwrap > .cnt_ruler", this),
					h = ruler.innerHeight();
				
				jQuery(this).removeClass("uninitialized");
				
				if(h > 72){
					jQuery(this)
						.addClass("expandable");
				}
				else{
					jQuery(this)
						.removeClass("collapsed")
						.addClass("quick_read");
				}
			});
		
		rl.delay(updateAllQuickReads, quickReadUpdateDelay);
	}
	
	orderjs.ready(function(){
		updateUnreadsInfo();
		jRecordLoader("#feedsLoaderCtn")
			.init({
				dataUrl : "${base}admin/getAnnouncements.do?count=10",
				msgAllLoaded : "已全部加载，共 {total} 条",
				onload : function(){
					initFeeds();
				},
				recordsCtn : "#feeds",
				recordHtmlTpl : "#feedTpl",
				recordsFilter : feedsFilter
			})
			.render()
			.loadRecords();
	});
	
	//expose
	window.toggleDetail = toggleDetail;
});

</script>
<link href="${cssPath}common.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${cssPath}icons.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
<link href="${cssPath}feeds.css?_v=${cacheBuster}" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="soof_page">
	<div class="soof_page_viewport">
		<script id="feedTpl" type="text/x-jsrender">
            <div id="{{>domId}}" data-id="{{>id}}" class="feed collapsed uninitialized {{if readFlag == 0}} unread {{/if}}">
                <div class="nav_date">
                    <div class="date">{{>date}}</div>
                    <div class="month">{{>month}}</div>
                </div>
                <div class="main">
                    <h1>{{>title}}</h1>
                    <div class="meta">
                        <span class="date">{{>createTimeText}}</span>
                    </div>
                    <div class="content">
						<div class="cnt_iwrap">
							<div class="cnt_ruler">{{:content}}</div>
						</div>
						<div class="bar" onclick="toggleDetail('{{>id}}')">
							<button class="btn white tg_expand">
								<span class="soof_icon arrow_down"></span> 显示全部
							</button>
							<button class="btn white tg_collapse">
								<span class="soof_icon arrow_up"></span> 收起
							</button>
						</div>
					</div>
                </div>
                <div class="clearer"></div>
            </div>
        </script>
        <div class="soof_feeds">
        	<div id="feeds"></div>
            <div style="padding:1.7em;" id="feedsLoaderCtn"></div>
        </div>
    </div>
</div>
</body>
</html>