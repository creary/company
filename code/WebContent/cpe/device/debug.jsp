﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>调试设备</title>
<link rel="rl-page-icon" href="${imagePath}application_xp_terminal.png" />
<script type="text/javascript" data-main="RealLight" data-rlconfig="${rlConfig}" src="${jsPath}rl/src/order.js"></script>
<script type="text/javascript">
orderjs(
	"open.jquery.jquery"
);
orderjs(function(){
var jQuery = orderjs("open.jquery.jquery");

function getRequestInfo(options){
	if(!(rl.isObject(options) && rl.isNonNullStr(options.url))) return "";
	var url = options.url,
		data = options.data;
	
	if(rl.isObject(data)) data = jQuery.param(data);
	if(rl.isNonNullStr(data)){
		url += (url.indexOf("?") > -1 ? "&" : "?") + data;
	}
	
	return rl.format('<a href="{0}" target="_blank">{0}</a>', [url]);
}

function executeRemoteControl(){
	var port = prompt("请输入端口:", 9001);
	if(port){
		port = parseInt(port);
		if(rl.isNumber(port)){
			jQuery("#field_remoteControlPort").val(port);
			executeCmd('remoteControl', 'def');
			return true;
		}
		else{
			alert("输入错误，端口只能为数字！");
			return executeRemoteControl();
		}
	}
}

var debugConsole = {
	cmdInput : "#debugCommandInp",
	cmdTypeInput : "#field_cmdType",
	cmdServiceUrl : "${base}cpe/doActionForDebug.do",
	dataForm : "#mainForm",
	resultBox : {
		boxEle : "#debugResultBox",
		cmdMsgTpl : '' + 
			'<div class="msg_item">' +
				'&gt; {cmd} <span class="status">...</span>' + 
			'</div>',
		cmdResultTpl : '' + 
			'<div class="msg_item">' +
				'<div class="tg">&gt; {cmd}</div>' + 
				'<div class="result">{result}</div>' + 
			'</div>',
		addMsg : function(msg){
			var jqBox = jQuery(this.boxEle)
				.append(msg);
			
			rl.defer(function(){//scroll to end
				jqBox.prop("scrollTop", jqBox.prop("scrollHeight"));
			});
		},
		clear : function(){
			jQuery(this.boxEle).empty();
		},
		showCmd : function(cmd){
			var msg = rl.format(this.cmdMsgTpl, {cmd : cmd});
			this.addMsg(msg);
		},
		showCmdResult : function(cmd, result){
			var msg = rl.format(this.cmdResultTpl, {cmd : cmd, result : "<pre>" + result + "</pre>"});
			this.addMsg(msg);
		}
	},
	clearCmdInput : function(){
		jQuery(this.cmdInput).val("");
	},
	disableControls : function(){
		jQuery(".cmd.rpc", "#mainForm")
			.prop("disabled", true)
			.addClass("wait");
	},
	enableControls : function(){
		jQuery(".cmd.rpc", "#mainForm")
			.prop("disabled", false)
			.removeClass("wait");
	},
	execute : function(cmd, type){
		if(!type) type = "cmd";
		jQuery(this.cmdTypeInput).val(type);
		
		if(rl.isNonNullStr(cmd)) jQuery(this.cmdInput).val(cmd);
		else cmd = jQuery(this.cmdInput).val();
		
		if(!rl.isNonNullStr(cmd)){
			this.resultBox.addMsg("<div class='msg_item' style='color:red'>请输入命令!</div>");
			rl.defer(this.clearCmdInput, this);
			return false;
		}
		
		this.resultBox.showCmd(cmd);
		this.remoteExecute(cmd);
		
		this.clearCmdInput();
		rl.defer(this.clearCmdInput, this);
	},
	genCmdUrl : function(cmd){
		return this.cmdServiceUrl;
	},
	initCmdInput : function(){
		var me = this;
		jQuery(this.cmdInput)
			.on("keypress", function(event){
				if(event.which == 13){
					me.execute();
				}
			});
	},
	remoteExecute : function(cmd){
		var me = this,
			url = this.genCmdUrl(cmd),
			data = this.dataForm ? jQuery(this.dataForm).serialize() : null;
		
		rl.debug(this + 'remoteExecute(): request = ' + getRequestInfo({url : url, data : data}) );
		
		this.disableControls();
		jQuery.ajax({
			url : url,
			data : data,
			cache : false,
			dataType : "json",
			timeout : 60 * 1000,
			type : "post",
			success : function(rsp, msg){
				if(rsp){
					var msg = (rsp.status == 1) ? rsp.data : rsp.msg;
					rl.debug(debugConsole + ' remoteExecute(): success msg = ' + msg + '');
					me.resultBox.showCmdResult(cmd, msg);
				}
				else{
					me.resultBox.showCmdResult(cmd, "加载数据失败: 服务器返回数据错误");
				}
			},
			error : function(req, textStatus){
				rl.debug(debugConsole + 'remoteExecute(): error: cmd = ' + cmd + ' textStatus = ' + textStatus + '');
				me.resultBox.showCmdResult(cmd, "加载数据失败: " + textStatus);
			},
			complete : function(){
				me.enableControls();
			}
		});
	},
	toString : function(){return "[object debugConsole]";}
};
debugConsole.initCmdInput();

window.executeCmd = rl.bind(debugConsole.execute, debugConsole);
window.clearConsole = rl.bind(debugConsole.resultBox.clear, debugConsole.resultBox);

rl.onReady(function(){
	jQuery("#tg_remoteControl")
		.on("click", executeRemoteControl);
});

});

</script>
<link href="${cssPath}common.css" rel="stylesheet" type="text/css" />
<link href="${cssPath}info.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.field_ctn{
	vertical-align:top !important;
}
.quick_acess button.cmd{
	padding:3px 0;
	width:80px;
}
.quick_acess .row{
	padding-top:3px;
	clear:both;
}
.quick_acess .col{
	float:left;
	width:50%;
}
.quick_acess .cell{
}
.debug_result_box{
	width:700px;
	height:350px;
	overflow:auto;
	font-size:14px;
}
.debug_command_inp{
	width:700px;
}
.debug_result_box .msg_item{
	padding:.5em 0;
}
.debug_result_box .result{
	padding-left:1em;
}
.cmd.wait{
	cursor:wait;
}

</style>
</head>
<body class="theme">
<div class="soof_page" style="padding:10px;">
	<form id="mainForm" name="mainForm" method="post" onsubmit="return false;">
        <input type="hidden" name="id" value="${host.id}" />
        <!-- cmdType: 
        	cmd : user input command line;
            def : system defined api.
        -->
        <input id="field_cmdType" type="hidden" name="cmdType" value="cmd" />
        <input id="field_remoteControlPort" type="hidden" name="remoteControlPort" />
        <table border="0" class="soof_grid_fields">
             <tr>
                 <th class="label" style="width:60px;">基本信息</th>
                 <td class="field_ctn">设备：${host.serialNumber}<br/>STUN：${host.stun}</td>
                 <td class="field_ctn" rowspan="3" style="width:200px;">
                        <div class="section first">常用命令：
                            <div class="quick_acess">
                            	<div class="row">
                                	<div class="col"><button class="cmd rpc" onclick="executeCmd('ifcfg','def')">有线</button></div>
                                	<div class="col"><button class="cmd rpc" onclick="executeCmd('iwcfg','def')">无线</button></div>
                                </div>
                            	<div class="row">
                                	<div class="col"><button class="cmd rpc" onclick="executeCmd('redirect','def')">认证跳转</button></div>
                                	<div class="col"><button class="cmd rpc" onclick="executeCmd('ping','def')">Ping</button></div>
                                </div>
                            	<div class="row">
                                	<div class="col"><button class="cmd rpc" onclick="executeCmd('netstat','def')">查看端口</button></div>
                                	<div class="col"><button class="cmd rpc" onclick="executeCmd('route','def')">系统</button></div>
                                </div>
                            	<div class="row">
                                	<div class="col"><button class="cmd rpc" id="tg_remoteControl">远程控制</button></div>
                                </div>
                                <div class="clearer"></div>
                            </div>
                        </div>
                        <div class="section">
                            <div>返回值操作：<br />
                                <div class="quick_acess">
                                    <div class="row">
                                        <div class="col"><button class="cmd" onclick="clearConsole();">清空</button></div>
                                    </div>
                                    <div class="clearer"></div>
                                </div>
                            </div>
                        </div>
                 </td>
             </tr>
             <tr>
                 <th class="label">命令</th>
                 <td class="field_ctn"><textarea id="debugCommandInp" name="debugCommand" rows=2 class="debug_command_inp cmd rpc">${debugCommand}</textarea></td>
             </tr>
             <tr>
                 <th class="label">返回值</th>
                 <td class="field_ctn">
                    <div id="debugResultBox" class="debug_result_box">
                    </div>
                 </td>
             </tr>
         </table>
    </form>
</div>
</body>
</html>