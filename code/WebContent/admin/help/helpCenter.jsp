<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统信息</title>
<link rel="rl-page-icon" href="${imagePath}wifi_favicon.ico" />
<link href="${jsPath}rl/x/mti/css/info.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"x:mti.groupHeaderCtrl",
	"x:mti.popupFormPage"
);
orderjs(function() {
	rlx.mti.createPopupFormPage({
		content: "#mainContent",
		actionBar: [{
			text: "关闭",
			action: function(){rl.page.close();}
		}]
	});

});
</script>
</head>
<body>
<div id="mainContent" class="rlx_mti_info">
    <div class="page_wrapper" style="width:500px;">
        <div class="group" style="margin-top:0;">
            <fieldset class="wrapper">
                <legend>系统信息</legend>
                <table cellpadding="0" cellspacing="0" border="0" class="g_body data_fields">
                    <tr>
                        <th class="label">产品ID</th>
                        <td class="field_ctn">${lic.productID}</td>
                    </tr>
                    <tr>
                        <th class="label"><spring:message code="vendor" /></th>
                        <td class="field_ctn">${lic.vendor}</td>
                    </tr>
                    <tr>
                        <th class="label"><spring:message code="version" /></th>
                        <td class="field_ctn">${lic.version}</td>
                    </tr>
                    <tr>
                        <th class="label"><spring:message code="release_date" /></th>
                        <td class="field_ctn">${lic.releaseDate}</td>
                    </tr>
                    <tr>
                        <th class="label"><spring:message code="license_customer" /></th>
                        <td class="field_ctn">${lic.customer}</td>
                    </tr>
                    <tr>
                        <th class="label"><spring:message code="device_number" /></th>
                        <td class="field_ctn">${lic.deviceNumber}</td>
                    </tr>
                    <tr>
                        <th class="label"><spring:message code="machine_id" /></th>
                        <td class="field_ctn">${machineID}</td>
                    </tr>
                    <c:if test="${lic.licenseType eq 'Trial'}">
                        <tr>
                            <th class="label"><spring:message code="expiry_days" /></th>
                            <td class="field_ctn">${lic.expiryDate}</td>
                        </tr>
                    </c:if>
                </table>
            </fieldset>
        </div>
        <div class="group">
            <fieldset class="wrapper">
                <legend>许可证更新</legend>
                <div class="g_body">
                	系统授权接入的设备数是一定的，当您需要增加接入新的设备时，您需要先更新系统的许可证，否则新设备无法接入系统。
                	<div style="padding-top:12px;">
                    <button style="height:35px; width:200px;" onclick="rl.page.open('${base}admin/help/soofacLicense.jsp', 'License', rl.gui.paneDlgOpt)">更新许可证</button>
                    </div>
                </div>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>
