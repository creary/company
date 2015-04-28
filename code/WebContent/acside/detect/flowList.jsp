<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>探测流水</title>
<link rel="rl-page-icon" href="${imagePath}info_log.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"gui.form.DateSelect",
	"x:mti.listPage"
);

orderjs(function(){

var date1 = new rl.gui.form.DateSelect({
    renderTarget : "startTimeCtn",
	autoRenderOnReady : true,
	name : "startTime",
    width:153,
    showTime : true 
});

var date2 = new rl.gui.form.DateSelect({
    renderTarget : "endTimeCtn",
	autoRenderOnReady : true,
	name : "endTime",
    width:153,
    showTime : true  
});

rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [			
			{
				text : "查询",
				icon : "${imagePath}search.gif", 
				action : function(){
					rlx.mti.listPageSearchDialog.show();
				}
			},
			rlx.mti.searchResetBtnOptions
		]
	},	
	grid :{
		dataUrl : "${base}detect/detectFlowList.do?branchId=${branchId}",
		dataFields : ["alias","apmac","mac","","snr","brand","logTime"],
		dataPrimaryKey : "id",
		columns : [
            {caption: "AP名称",width: "150",name: "alias"},
            {caption: "AP序列号",width: "150",name: "apmac"},
            {caption: "MAC",width: "150",name: "mac"},            
            {caption: "场强(dbm)",width: "100",name: "snr"},			      
	        {caption: "终端厂商", width: "150", name: "brand"},
	        {caption: "时间", width: "150",name: "logTime"}	        
		]
	}
});
});
</script>
</head>
<body>
<form id="queryForm" name="queryForm" method="post" class="rlx_mti_list_query_form" style="display:none;">
   <table class="fields_layout">
     <tr>
         <th>AP名称或序列号</th>
         <td>
            <input type="text" class="t" name="alias" />
         </td>
         <th>MAC</th>
         <td>
            <input type="text" class="t" name="mac" />
         </td>
      </tr>  
      <tr>
          <th>时间</th>
          <td id="startTimeCtn"></td>
          <th>--</th>
          <td id="endTimeCtn"></td>
      </tr>                
    </table>
</form>
</body>
</html>