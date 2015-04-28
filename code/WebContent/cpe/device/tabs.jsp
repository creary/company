<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/common/path.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
   String tab = request.getParameter("tab");
   String batchMode = request.getParameter("batchMode");
   if(tab == null) tab = "base";
   if(batchMode == null) batchMode = "0";
   
   request.setAttribute("tab",tab);
   request.setAttribute("batchMode",batchMode);   
%>
<div class="nav">
	<ul class="list">
        <li ${tab=='base'?"class='active'":""}><a href="${base}cpe/readySetting.do?id=${id}&batchMode=${batchMode}">基本信息</a></li>
        <li ${tab=='operation'?"class='active'":""}><a href="${base}cpe/readySettingOperation.do?id=${id}&batchMode=${batchMode}">运营策略</a></li>
        <li ${tab=='maintain'?"class='active'":""}><a href="${base}cpe/readySettingMaintain.do?id=${id}&batchMode=${batchMode}">维护策略</a></li>
        <li ${tab=='device'?"class='active'":""}><a href="${base}cpe/readySettingWireless.do?id=${id}&batchMode=${batchMode}">无线参数</a></li>
        <c:if test="${hasDetect}">
          <li ${tab=='detect'?"class='active'":""}><a href="${base}cpe/readySettingDetect.do?id=${id}&batchMode=${batchMode}">探测配置</a></li>
        </c:if>
        <li ${tab=='trace'?"class='active'":""}><a href="${base}cpe/readySettingTrace.do?id=${id}&batchMode=${batchMode}">溯源配置</a></li>
        <li ${tab=='qos'?"class='active'":""}><a href="${base}cpe/readySettingQos.do?id=${id}&batchMode=${batchMode}">QOS 配置</a></li>
        <li>|</li>
        <li>
        	<a href="javascript:void(0);" class="tg_dropdown" id="tg_showOprDropdown">设备操作<b class="soof_caret"></b></a>
        </li>
	</ul>
</div>
<div id="oprDropdown" class="soof_drop_menu"> 
    <a class="item" href="javascript:void(0);" data-key="updateFirmware">更新固件</a>
    <a class="item" href="javascript:void(0);" data-key="updateConfig">更新配置</a>
    <a class="item" href="javascript:void(0);" data-key="reboot">重启</a>
    <a class="item" href="javascript:void(0);" data-key="reset">恢复出厂配置</a>
    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_SOOFOUND">
    <a class="item" href="javascript:void(0);" data-key="debug">调试</a>
    </security:authorize> 
</div>
<script type="text/javascript">
orderjs(function(){
	rl.onReady(function(){
		orderjs(
			"lib.dom.engine",
			"lib.dom.DomWrap"
		);
		orderjs(function(){
			var track = this.track;
			var jQuery = orderjs("open.jquery.jquery");
			
			function showOprDropDown(){
				rl.$("oprDropdown")
					.show()
					.alignTo(this, "", true);
			}
			
			function openObservedDlg(){
				var dlg = rl.page.open.apply(rl.page, arguments);
				dlg.on("close", function(){
					if(deviceDataObserver.changed){
						//TODO: just reload ssids section but not page
						location.reload();
					}
				});
			}
			
			var dlgOptions = rl.gui.winDlgOpt;
			var oprActions = {
				"updateFirmware" : function(){
					var url = "${base}cpe/readyUpdateFirmware.do?id=${id}&batchMode=${batchMode}"
					openObservedDlg(url, "cpe.readyUpdateFirmware", dlgOptions);
				},
				"updateConfig" : function(){
					var url = "${base}cpe/readyUpdateConfig.do?id=${id}&batchMode=${batchMode}"
					openObservedDlg(url, "cpe.readyUpdateConfig", dlgOptions);
				},
				"reboot" : function(){
					var url = "${base}cpe/readyReboot.do?id=${id}&batchMode=${batchMode}"
					openObservedDlg(url, "cpe.readyReboot", dlgOptions);
				},
				"reset" : function(){
					var url = "${base}cpe/readyReset.do?id=${id}&batchMode=${batchMode}"
					openObservedDlg(url, "cpe.readyReset", dlgOptions);
				},
				"debug" : function(){
					var url = "${base}cpe/readyDebug.do?id=${id}";
					openObservedDlg(url, "cpe.readyDebug", dlgOptions);
				},
				"scheduleReboot" : function(){
					var url = "${base}cpe/readySetScheduleReboot.do?id=${id}&batchMode=${batchMode}";
					rl.page.open(url, "cpe.readySetScheduleReboot", rl.gui.winDlgOpt);
				}
			};
			function doDropdownItemAction(name){
				var f = oprActions[name];
				if(!rl.isFunction(f)){
					alert("页面脚本发生错误，请刷新页面重试！");
					rl.raiseError("Invalid action name: " + name, track(this, "doDropdownItemAction"));
				}
				f();
			};
			
			rl.onReady(function(){
				//init dropdown
				jQuery("#oprDropdown")
					.on("click", function(){
						jQuery(this).hide();
					})
					.find("a.item")
					.on("click", function(){
						doDropdownItemAction(jQuery(this).attr('data-key'));
					});
				jQuery("#tg_showOprDropdown")
					.on("click", showOprDropDown);
				jQuery(document)
					.on("click", function(evt){
						var tg = jQuery(evt.target).closest(".tg_dropdown");
						if(!tg.length) jQuery("#oprDropdown").hide();
					});
			});
		});
	});
});
</script>

