<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<link rel="rl-page-icon" href="${imagePath}user_16.gif" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.listPage"
);

orderjs(function(){

function doEdit(cellText, colIndex, rowIndex, dataSource){
    var url = "${base}admin/userReadyEdit.do?id=" + cellText;
	return '<a href="javascript:void(0);" onclick="rl.page.open(\'' + url + '\', \'branchReadyEdit\', rl.gui.paneDlgOpt_l);"><i class="soof_icon edit"></i></a>&nbsp;';    
}
 
rlx.mti.createListPage({
	searchDialog : true,
	toolbar : {
		items : [
			{
				text : "增加",
				icon : "${imagePath}add.gif", 
				action : function(){
					rl.page.open("${base}admin/userReadyAdd.do", "userReadyAdd", rl.gui.paneDlgOpt_l);
				}
			},
			{
				text : "删除",
				icon : "${imagePath}remove.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}admin/userDelete.do";
					if(!len){
						alert("请选择要删除的记录");
						return;
					}					
					if(len && confirm("确定要删除这 " + len + " 条记录?")){
						var qs = grid.body.encodeSelectedDataIdList2QS();
						url += (url.contains("?") ? "&" : "?") + qs;						
						rl.rpc.sdtGet(url, function(status, msg){
							grid.load();
						}, function(status, msg){
							alert(msg);
						});
					}
				}
			},
			{
				text : "查询",
				icon : "${imagePath}search.gif", 
				action : function(){
					rlx.mti.listPageSearchDialog.show();
				}
			},
            {
				text : "发送通知",
				icon : "${imagePath}0303.gif", 
				action : function(){
					var grid = rlx.mti.listPageGrid,
						len = grid.getSelectedRows().length,
						url = "${base}admin/readySendEmailToUser.do";
					if(!len){
						alert("请选择一个用户");
						return;
					}
					if(len > 1){
						alert("只能选择一个用户");
						return;
					}
					if(len){					
						var qs = grid.body.encodeSelectedDataIdList2QS();
						url += (url.contains("?") ? "&" : "?") + qs;	
						rl.page.open(url, "batchOperation", rl.gui.paneDlgOpt_l);
					}
				}
			},			
			rlx.mti.searchResetBtnOptions,
		]
	},	
	grid :{
		dataUrl : "${base}admin/userList.do?branchId=${branchId}",
		dataFields : ["username","fullname","branch","phone","email"],
		dataPrimaryKey : "username",
		columns : [
            {ctype : "RowSelector", multiple:true, selectorName : "checkbox"},	
            {caption: "商家", width: "300", name: "branch"},
	        {caption: "用户名", width: "100", name: "username"},	        
	        {caption: "姓名", width: "100", name: "fullname"},
	        {caption: "手机", width: "150", name: "phone"},
	        {caption: "Email", width: "150", name: "email"},	        
	        {caption: "编辑", width: "30", name: "username", convert : doEdit}	        
		]
	}
});
});
</script>
<link href="${cssPath}icons.css" rel="stylesheet" type="text/css" />
</head>
<body class="soof_page">
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
            <th>商家</th>
            <td>
            	 <input type="text" class="t" name="branch" />
            </td>
            <th>手机号</th>
            <td>
            	 <input type="text" class="t" name="phone" />
            </td>
        </tr>         
    </table>
</form>
</body>
</html>