<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/nosession_path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>关于本产品</title>
<link rel="rl-page-icon" href="${imagePath}wireless.png" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.groupHeaderCtrl"
);
</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
    <div class="page_wrapper" style="width:500px;">
     <div class="group" style="margin-top:0;">
         <fieldset class="wrapper">
             <legend>关于本产品</legend>
             <table cellpadding="0" cellspacing="0" border="0" class="g_body data_fields">
                 <tr>
                     <th class="label">产品名称</th>
                     <td class="field_ctn">${lic.productName}</td>
                 </tr>
                 <tr>
                     <th class="label">产品ID</th>
                     <td class="field_ctn">${lic.productID}</td>
                 </tr>
                 <tr>
                     <th class="label">产商</th>
                     <td class="field_ctn">${lic.vendor}</td>
                 </tr>
                 <tr>
                     <th class="label">版本</th>
                     <td class="field_ctn">${lic.version}</td>
                 </tr>
                 <tr>
                     <th class="label">发布日期</th>
                     <td class="field_ctn">${lic.releaseDate}</td>
                 </tr>
                 <tr>
                     <th class="label">授权客户</th>
                     <td class="field_ctn">${lic.customer}</td>
                 </tr>
                 <tr>
                     <th class="label">授权接入设备数</th>
                     <td class="field_ctn">${lic.deviceNumber}</td>
                 </tr>
                 <tr>
                     <th class="label">服务器ID</th>
                     <td class="field_ctn">${machineID}</td>
                 </tr>
                 <tr>
                     <th class="label">试用剩余天数</th>
                     <td class="field_ctn">${lic.expiryDate}</td>
                 </tr>
             </table>
         </fieldset>
     </div>
    <div class="group">
         <fieldset class="wrapper">
             <legend>Tomcat</legend>
             <table cellpadding="0" cellspacing="0" border="0" class="g_body data_fields">
                 <tr align="left">
                     <th class="label">Tomcat 版本</th>
                     <td class="field_ctn">${tomcatInfo}</td>
                 </tr>
                 <tr>
                     <th class="label">Tomcat 路径</th>
                     <td class="field_ctn">${tomcatPath}</td>
                 </tr>
                 <tr>
                     <th class="label">JDK 版本</th>
                     <td class="field_ctn">${jdkVersion}</td>
                 </tr>
                 <tr>
                     <th class="label">操作系统</th>
                     <td class="field_ctn">${tomcatOS}</td>
                 </tr>
             </table>
         </fieldset>
     </div>
     <div class="group">
         <fieldset class="wrapper">
             <legend>MySQL</legend>
             <table cellpadding="0" cellspacing="0" border="0" class="g_body data_fields">
                 <tr>
                     <th class="label">MySQL 路径</th>
                     <td class="field_ctn">${mysql_install_path}</td>
                 </tr>
                 <tr>
                     <th class="label">MySQL 数据路径</th>
                     <td class="field_ctn">${mysql_data_path}</td>
                 </tr>
                 <tr>
                     <th class="label">MySQL 版本</th>
                     <td class="field_ctn">${mysql_version}</td>
                 </tr>
                 <tr>
                     <th class="label">操作系统</th>
                     <td class="field_ctn">${mysql_os}</td>
                 </tr>
                 <tr>
                     <th class="label">数据大小</th>
                     <td class="field_ctn">${mysql_data_size}</td>
                 </tr>
             </table>
         </fieldset>
     </div>
    </div>
</div>
</body>
</html>
