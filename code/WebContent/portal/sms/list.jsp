<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>短信发送统计</title>
<link rel="rl-page-icon" href="${imagePath}stat.gif" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
   <form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
       <div class="group">
           <table border="0" class="grid_fields grid_fields_cols4">                
               <tr>
                  <th class="label">商家</th>
			      <th class="label">已发短信数</th>			      
				  <th class="label">剩余短信数</th>
               </tr>  
               <c:forEach items="${dtos}" var="dto">
	               <tr>
				      <td class="field_ctn">${dto.branch}</td>
				      <td class="field_ctn">${dto.smsUsed}</td>
					  <td class="field_ctn">${dto.smsRemain}</td>
	               </tr>  
               </c:forEach>  
           </table>
       </div>
   </form>
</div>
</body>
</html>