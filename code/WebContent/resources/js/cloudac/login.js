/**
 * @fileOverview Login util js.
 * @change
 	#1 by prcjack @2015/04/27 Creates file.
 */

document.getElementById('login_id').focus();

function check_form(){
	var login_fld = document.getElementById("login_id");
	var password_fld = document.getElementById("password_id");
	var captcha_fld = document.getElementById("captcha_id");
	if(login_fld.value.length==0){
		alert("用户名不能为空");
		login_fld.focus();
		return false;
	}
	if(password_fld.value.length==0){
		alert("密码不能为空");
		password_fld.focus();
		return false;
	}
	if(captcha_fld.value.length==0){
		alert("验证码不能为空");
		captcha_fld.focus();
		return false;
	}
	return true;
}

var maskedInputs = jQuery("input[name='j_username'], input[name='j_password'], input[name='j_captcha']");

function checkMaskDisplay(inp){
	var closestInputCtn = jQuery(inp).closest(".inputline");	
	setTimeout(function(){//defer to get the input value.
		if(inp.value === "")
			closestInputCtn.addClass("show_mask");
		else
			closestInputCtn.removeClass("show_mask");
	}, 10);
}

function checkAllMaskDisplay(){
	maskedInputs.each(function(){
		checkMaskDisplay(this);
	});
}

jQuery("form[name='login']").on("keyup input", checkAllMaskDisplay);

jQuery(window).on("load", function(){
	setTimeout(checkAllMaskDisplay, 100);
	setTimeout(function(){
		var preloader = jQuery("#rlPreloader");
		preloader.attr("src", preloader.attr("data-src"));
	}, 500);
});

jQuery("#captcha, #tg_changeCaptcha")
	.on("click", function(){
		jQuery("#captcha")
			.attr("src", jQuery("#captcha").attr("data-src") + "?_=" + (new Date).getTime());
	});
