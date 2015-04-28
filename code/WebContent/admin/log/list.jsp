<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>操作日志</title>
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
    renderTarget : "start_log_time",
	autoRenderOnReady : true,
	name : "startLogTime",
    width:153,
    showTime : true 
});

var date2 = new rl.gui.form.DateSelect({
    renderTarget : "end_log_time",
	autoRenderOnReady : true,
	name : "endLogTime",
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
		dataUrl : "${base}admin/logList.do?branchId=${branchId}",
		dataFields : ["id","branch","username","fullname","operation","ip","location","logTime"],
		dataPrimaryKey : "id",
		columns : [
            {caption: "商家",width: "250",name: "branch"},
            {caption: "用户名",width: "100",name: "username"},
            {caption: "姓名",width: "100",name: "fullname"},			      
	        {caption: "操作", width: "250", name: "operation"},
	        {caption: "IP地址",width: "100", name: "ip"},
	        <security:authorize ifAnyGranted="ROLE_SOOFOUND">
	            {caption: "地点",width: "200", name: "location"},
	        </security:authorize> 
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
          <th>用户名</th>
          <td>
              <input type="text" class="t" name="username" />
          </td>
          <th>姓名</th>
          <td>
               <input type="text" class="t" name="fullname" />
          </td>            
      </tr>
      <tr>
          <th>时间</th>
          <td id="start_log_time"></td>
          <th>--</th>
          <td id="end_log_time"></td>
      </tr>                
    </table>
</form>
</body>
</html>