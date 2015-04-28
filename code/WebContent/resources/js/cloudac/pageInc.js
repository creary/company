/**
 * @fileOverview Project page inc.
 * @change
    #1-A @2013/3/7 Create file.
*/
orderjs.define("cloudac:pageInc", [
    "app.opoa.page"
], function(){
var track = this.track;
//provide lang source
rl.util.g11n.addSource({
	//dayNames
	"sunday" : "星期日",
	"monday" : "星期一",
	"tuesday" : "星期二",
	"wednesday" : "星期三",
	"thursday" : "星期四",
	"friday" : "星期五",
	"saturday" : "星期六",
	//short dayNames
	"short.sunday" : "日",
	"short.monday" : "一",
	"short.tuesday" : "二",
	"short.wednesday" : "三",
	"short.thursday" : "四",
	"short.friday" : "五",
	"short.saturday" : "六",
	//monthNames
	"january" : "一月",
	"february" : "二月",
	"march" : "三月",
	"april" : "四月",
	"may" : "五月",
	"june" : "六月",
	"july" : "七月",
	"august" : "八月",
	"september" : "九月",
	"october" : "十月",
	"november" : "十一月",
	"december" : "十二月",
	//short monthNames
	"short.january" : "一月",
	"short.february" : "二月",
	"short.march" : "三月",
	"short.april" : "四月",
	"short.may" : "五月",
	"short.june" : "六月",
	"short.july" : "七月",
	"short.august" : "八月",
	"short.september" : "九月",
	"short.october" : "十月",
	"short.november" : "十一月",
	"short.december" : "十二月",
	
	//tmp back compatibility.
	"dayNames" : [
		"星期日",
		"星期一",
		"星期二",
		"星期三",
		"星期四",
		"星期五",
		"星期六"
	],
	"shortDayNames" : [
		"日",
		"一",
		"二",
		"三",
		"四",
		"五",
		"六"
	],
	"monthNames" : [
		"一月",
		"二月",
		"三月",
		"四月",
		"五月",
		"六月",
		"七月",
		"八月",
		"九月",
		"十月",
		"十一月",
		"十二月"
	],
	"shortMonthNames" : [
		"一月",
		"二月",
		"三月",
		"四月",
		"五月",
		"六月",
		"七月",
		"八月",
		"九月",
		"十月",
		"十一月",
		"十二月"
	],
	
	//common codes
	"clickToggleGroup" : "单击标题，折叠/展开该组",
	"confirmExit" : "确定退出系统?",
	"editDialog" : "编辑对话框",
	"emptyMsg" : "当前无数据",
	"exit" : "退出",
	"failureRequest" : "无法连接服务器，\n请检查网络连接和服务器！",
	"openMyAccount" : "打开我的帐户",
	"open" : "打开",
	"openAtNewTab" : "在新标签中打开",
	"READ_DATA_ERR" : "数据读取错误.",
	"saveButtonText" : "保&nbsp;&nbsp;存",
	"search" : "查询",
	"searchReset" : "重置查询",
	"searchDialog" : "查询对话框",
	"searchButtonText" : "查&nbsp;&nbsp;询",
	
	//comps options
	"rl.rpc.sdtStatusMessages" : {
		"1" 	: "请求成功。",
		"2" 	: "请求失败。",
		"2.01"	: "网络连接失败。",
		"2.02"	: "请求取消。",
		"2.03"	: "请求超时。",
		"2.04"	: "未找到。",
		"2.10"	: "访问失败。",
		"2.11"	: "会话过期。",
		"2.12"	: "访问未授权。",
		"2.13"	: "访问被禁止。",
		"3"	    : "内部服务器错误。",
		"3.01"	: "服务器未实现。",
		"3.02"	: "服务不可用。"
	},
	
	"rl.gui.DatePicker" : {
		"tips" : {
			"navPrevMonth" : "上一月 (可通过鼠标滚轮向上滚动来改变)",
			"navPrevDecade" : "上十年 (可通过鼠标滚轮向上滚动来改变)",
			"navNextMonth" : "下一月 (可通过鼠标滚轮向下滚动来改变)",
			"navNextDecade" : "下十年 (可通过鼠标滚轮向下滚动来改变)",
			"navToday" : "转到今天",
			"ymCtrl" : "显示/隐藏年月面板",
			"ok" : "确定"
		},
		
		"ymCtrlTextFormat" : "{year}年{month}月"		
	},
	
	"rl.gui.grid.Grid" : {
		"resizeIndicatorTip" : "双击自适应宽度",
		"emptyMessage" : "当前列表为空"
	},
	
	"rl.gui.grid.PagingBar" : {
		"displaying" : "显示",
		"posLabelL" : "&nbsp;第",
		"posLabelR" : "页&nbsp;",
		"limitLabelL" : "每页显示",
		"limitLabelR" : "条记录",
		"pageTotalTextRender" : function(text){return "共" + text + "页";},
		"recordsTotalTextRender" : function(total){return "共" + (total || 0) + "条记录";}
	},
	
	"rl.gui.tab.TabPanel" : {
		"refresh" : "刷新当前页面",
		"close" : "关闭标签",
		"closeOthers" : "关闭其它标签"
	},
	
	"rl.gui.indicator.TipMgr" : {
		"failureContent" : "对不起，加载失败!",
		"waitingContent" : "正在加载，请稍候..."
	},
	
	"rl.gui.indicator.msgBoxMgr" : {
		"okText" : "确 定",
		"cancelText" : "取消"
	},
	
	"rl.gui.indicator.ProcessingBox" : {
        "msgReadErr" : "操作失败！",
        "msgComplete" : "操作成功！"
	},
	
	"rl.gui.form.Validator.errMsgStore" : {
		"required" : "该项不能为空!",
		"required_select" : "该项必须选择!",
		"email" : "请输入正确的email 地址!",
		"phone" : "请输入正确的电话号码!",
		"mobile" : "请输入正确的手机号码!",
		"url" : "请输入正确的 url!",
		"currency" : "请输入正确的货币值!",
		"number" : "只能输入数字!",
		"zip" : "请输入正确的邮政编码!",
		"qq" : "请输入正确的qq号码!",
		"english" : "只能输入英文字符!",
		"chinese" : "只能输入中文字符!",
		"repeat" : "两次输入不一致!",
		"compare_>=" : "输入的数字必须大于或等于 {toLable} 的值!",
		"compare_<=" : "输入的数字必须小于或等于 {toLable} 的值!",
		"compare_<" : "输入的数字必须小于 {toLable} 的值!",
		"compare_>" : "输入的数字必须大于 {toLable} 的值!",
		"compare_==" : "输入的数字必须等于 {toLable} 的值!",
		"compare_=" : "输入的数字必须等于 {toLable} 的值!",
		"range_(_min" : "输入的数字必须大于 {min} !",
		"range_[_min" : "输入的数字必须大于或等于 {min} !",
		"range_max_)" : "输入的数字必须小于 {max} !",
		"range_max_]" : "输入的数字必须小于或等于 {max} !",
		"input_min" : "输入的数字必须大于或等于 {min} !",
		"input_max" : "输入的数字必须小于或等于 {max} !",
		"input_minLength" : "输入长度至少为 {minLength} 位!",
		"input_maxLength" : "输入长度至多为 {maxLength} 位!",
		"select_min" : "请至少选择 {min} 项!",
		"select_max" : "请至多选择 {max} 项!",
		"unknown" : "请输入正确值!"
	},
	
	"rl.dataBox.CompPanel" : {
		"loadingMsg" : "正在加载数据...",
		"noDataMsg" : "当前无数据",
		"dataLoadErrMsg" : "数据加载错误!",
		"dataRequestFailureMsg" : "数据请求错误!",
		"dataRequestTimeoutMsg" : "数据请求超时!",
		"retryButtonText" : "重试"
	},
	
	"rlx.mti.FormHelpCard" : {
		"closeHelp" : "关闭帮助",
		"hideHelp" : "隐藏帮助",
		"showHelp" : "显示帮助"
	},
	
	"rlx.mti.mui" : {
		"homepageTitle" : "首 页",
		"logoutFailed" : "对不起，注销失败，请检查网络是否连接或重启浏览器！",
		"loadMenuDataError" : "加载菜单数据出错"
	},
	
	"rlx.mti.treeNavPage" : {
		"loadMenuDataError" : "加载菜单数据出错:",
		"serverMenuDataHandlerError" : "服务器处理菜单数据发生错误！"
	},
	
	"x:mti.Validator" : {
		"defMsg" : {
			"Require" :"必填项",
			"Chinese" :"只允许中文",
			"English" :"只允许英文",
			"Number" :"只允许数字",
			"Integer" :"只允许整数",
			"Double" :"只允许实数",
			"Email" :"Email地址格式不对",
			"Url" :"网址格式不对",
			"Phone" :"电话号码格式不对",
			"Mobile" :"手机号码格式不对",
			"Currency" :"货币格式不对",
			"Zip" :"邮政编码不对",
			"IdCard" :"身份证号码格式不对",
			"QQ" :"QQ号格式不对",
			"Date" :"日期格式不对",
			"SafeString" :"不太安全的密码",
			"Repeat" :"重复输入不对",
			"Compare" :"不一致",
			"Ip" :"IP地址格式不对",
			"Range" :"超过范围",
			"Limit" :"超过限定长度",
			"LimitB" :"限制输入的字节长度",
			"Group" :"必选一项",
			"Custom" :"格式不对"
		},
		"vCauses" : "以下原因导致提交失败：\t\t\t\t"
	}
}, "zh-cn");

var htmlTag = /<\/?[^>]+>/img,
	errMeta = new RegExp('<meta' +
				'(?:[^>]+content\\s*=\\s*"(.*)"' +
				'[^>]+name\\s*=\\s*"rl-page-error"' + 
				'|' +
				'[^>]+name\\s*=\\s*"rl-page-error"' + 
				'[^>]+content\\s*=\\s*"(.*)")' +
				'.*?(?:\\/>|<\\/meta\\s*>)', "i"),
	errMsg = new RegExp('<div' +
				'[^>]+id\\s*=\\s*"mainInfo"' +
				'.*?>((\\s|\\S)*?)<\\/div\s*>', "i");

rl.createNamespace("cloudac", {
	failureRequestMsg : rl.getG11nSource("failureRequest") || "Request failed!",
	
	failureRequestHandler : function(){
		alert(cloudac.failureReqMsg);
	},
	
	/**
	 * read err info from source view error page html.
	 * @param {String} text Html code content.
	 * @return Object
	 *  a object with following properties:
	 *  "type" : indicates the error type. if the html code not match the
	 *			 the RegExp errMeta it returns "unknown".
	 *  "msg" : the content of error. if the html code not match the
	 *			the RegExp errMsg it returns "".
	 */
	readErrRsp : function(text){
		var err = {
			type : "unknown",
			msg : ""
		};
		
		if(rl.isNonNullStr(text)){
			var mErrMeta = text.match(errMeta);
			//rl.dir(mErrMeta, "match of errMeta");
			if(mErrMeta){
				var type = mErrMeta[1] || mErrMeta[2];
				if(rl.isNonNullStr(type)){
					err.type = type;
					var mErrMsg = text.match(errMsg);
					//rl.dir(mErrMsg, "match of mErrMsg");
					if(mErrMsg && mErrMsg[1]){
						err.msg = mErrMsg[1].replace(htmlTag, "");
					}
				}
			}
		}
		
		return err;
	},
	
	readRequestDataErrHandler : function(e){
		var ds = this.dataSource;
		if(ds){
			var req = ds.httpRequest;
			if(!req) return ;
			
			var rsp = req.getResponse("text");
			if(String.isHtml(rsp)){
				var msg = cloudac.readErrRsp(rsp).msg;
				if(rl.isNonNullStr(msg)){
					alert(msg);
					return true;
				}
			}
		}
		
	}
});

rl.createNamespace("rl.gui", {
	paneDlgOpt : {modal : true, width : 600, height : 300},
	paneDlgOpt_l : {modal : true, width : 800, height : 400},
	winDlgOpt : {modal : true, width : 1000, height : 550, scrollbars : 'yes'},
	makeDlgOpt : function(options, type){
		if(rl.isNonNullStr(type)) type = type.toLowerCase();
		else type = "pane";
		if(rl.isNumber(options)) options = {height : options};
		var defaults = rl.gui[type + "DlgOpt"];
		
		return rl.fill(options || {}, defaults);
	}
});

rl.createNamespace("rl.gui.grid", {
	gridReadDataErrHandler : cloudac.readRequestDataErrHandler
});
rl.createNamespace("rl.gui.tree", {
	treeReadDataErrHandler : cloudac.readRequestDataErrHandler
});

rl.page.callTop = function(method){
	try{
		var topWin = window.top,
			args = rl.slice(arguments, 1);
		
		if(rl.isFunction(top[method])){
			top[method].apply(null, args);
		}
	}catch(err){
		rl.raiseError(err, track(this, "callTop"));
	}
};

//add baidu stat
if(!/127\.0\.0\.1|localhost/i.test(String(location.host))){
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?e8f02f1992768475cc8e1b6635415c3c";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
}
});