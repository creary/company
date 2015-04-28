/**
 * @fileOverview Form util fns for sending password to phone.
 * @change
    #1 by prcjack @2014/04/15 Create file(Upgrade from sv project)
 */

orderjs.define("cloudac:sendPasswordToPhone", [
	"open.jquery.jquery"
], function(){
var jQuery = orderjs("open.jquery.jquery");

var re = /\{([\w-]+)(?:\:([\w\.]*)(?:\((.*?)?\))?)?\}/g,
	format = function(source, values){
		return (source.replace(re, 
			function(m, m1) {
				var v = values[m1];
				if(v === undefined)v = "";
				return v;
			}
		));
	};

var rMobile = /^((\(\d{3}\))|(\d{3}\-))?1\d{10}$/,
	sendingPassword = false,
	resendCounterTimer = null,
	opt = {
		errorMsg : "Send password failed!",
		invalidMobileTip : "Invalid mobile format.",
		sendToPageSuccessTip : "Your password is {password}!",
		phoneInputId : "#field_username_phone",
		passwordInputId : "#field_password_phone",
		resendCounterId : "#resendCounter",
		sendingMsg : "Sending, please wait...",
		sendPasswordBtnId : "#sendPasswordBtn",
		resendLimit : 60,
		fakeSendUrl : "",
		url : "",
		urlOnSendToPage : ""
	};

function setSendingPassword(sending){
	sendingPassword = sending;
	if(sending){
		jQuery(opt.sendPasswordBtnId)
			.prop("disabled", true)
			.addClass("disabled")
			.find("span:first")
			.text("请稍候...");
	}
	else{
		jQuery(opt.sendPasswordBtnId)
			.prop("disabled", false)
			.removeClass("disabled")
			.find("span:first")
			.text("获取密码");
	}
}

function startResendCounter(){
	var left = opt.resendLimit;
	
	if(resendCounterTimer) window.clearInterval(resendCounterTimer);
	
	jQuery(opt.sendPasswordBtnId + " span:first").text("重试");
	resendCounterTimer = setInterval(function(){
		if(left == 0){
			return stopResendCounter();
		}		
		setResendCounterHtml("(" + left + ")");
		left --;
	}, 1000);
}

function stopResendCounter(){
	window.clearInterval(resendCounterTimer);
	setResendCounterHtml("");
	setSendingPassword(false);
}

function setResendCounterHtml(html){
	jQuery(opt.resendCounterId).html(html);
}

function sendPasswordToPhone(){
	if(sendingPassword){
		alert(opt.sendingMsg);
		return false;
	}	
	var n = jQuery(opt.phoneInputId).val();	
	if(!rMobile.test(n)){
		alert(opt.invalidMobileTip);
		return false;
	}	
	setSendingPassword(true);
	jQuery.ajax( {
		type : "post",
		url : opt.url + n,
		cache : false,
		dataType : "json",
		success : function(rspData){
			var msg;
			if(rspData){
				if(rspData.status == 1){
					if(rspData.msg) alert(rspData.msg);
					
					startResendCounter();
					return ;
				}
				if(rspData.msg) msg = rspData.msg;
			}			
			alert(msg || opt.errorMsg);
			
			setSendingPassword(false);
		},
		error : function(){
			alert(opt.errorMsg);
			setSendingPassword(false);
		}
	});
}

function sendPasswordToPage(onSend){
	if(sendingPassword){
		alert(opt.sendingMsg);
		return false;
	}	
	var n = jQuery(opt.phoneInputId).val();	
	if(!rMobile.test(n)){
		alert(opt.invalidMobileTip);
		return false;
	}	
	setSendingPassword(true);
	
	jQuery.ajax( {
		type : "post",
		url : opt.urlOnSendToPage + n,
		cache : false,
		dataType : "json",
		success : function(rspData){
			var msg;
			if(rspData){
				if(rspData.status == 1){
					var password = rspData.data;
					msg = format(rspData.msg || opt.sendToPageSuccessTip || "", {password : password});
					
					if(msg) alert(msg);
					
					setSendingPassword(false);
					jQuery(opt.passwordInputId).val(password);
					
					jQuery(opt.sendPasswordBtnId)
						.closest("form")
						.trigger("input");
					
					if(jQuery.isFunction(onSend)){
						onSend(rspData);
					}
					
					return ;
				}
				if(rspData.msg) msg = rspData.msg;
			}
			alert(msg || opt.errorMsg);
			
			setSendingPassword(false);
		},
		error : function(){
			alert(opt.errorMsg);
			setSendingPassword(false);
		}
	});
}
	
return {
	send : sendPasswordToPhone,
	sendPasswordToPage : sendPasswordToPage,
	setOptions : function(options){
		jQuery.extend(opt, options);
	}
}
});
