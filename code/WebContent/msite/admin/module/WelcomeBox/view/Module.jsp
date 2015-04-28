<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
body{
	background-color:#666;
}
.soof_welcome_page .page_viewport{
	max-width:500px;
	margin:0 auto;
	background-color:#fff;
}
.soof_welcome_page .section_jump{
	border-top:solid 1px #999;
	text-align:center;
	font-family:"微软雅黑";
	background-color:#eee;
}
.soof_welcome_page .welcome_img{
	width:100%;
}
.soof_welcome_page .welcome_text{
	padding-top:18px;
	font-size:18px;
}
.soof_welcome_page .agreement_ctn{
	padding-top:6px;
	color:#999;
}
.soof_welcome_page .agreement_ctn a{
	color:#999;
}
.soof_welcome_page .jump_btn{
	height:60px;
	line-height:60px;
	width:250px;
	margin-top:18px;
	opacity:1;
	border:solid 1px #ccc;
	border-radius:5px;
	background-color:#09C;
	color:#fff;
	font-size:24px;
}
.soof_welcome_page .jump_btn:hover{
	opacity:0.8;
}
.soof_welcome_page .soof_foot{
	margin-top:1em;
}
.soof_welcome_page .soof_foot .copyright{
	line-height:24px;
	padding:12px 6px;
	background-color:#eee;
	text-align:center;
}
@media (min-height: 600px){
.soof_welcome_page .welcome_text{
	padding-top:24px;
}
.soof_welcome_page .jump_btn{
	margin-top:36px;
}
}

@media (min-height: 750px){
.soof_welcome_page{
	font-size:14px;
}
.soof_welcome_page .welcome_text{
	padding-top:36px;
	font-size:24px;
}
.soof_welcome_page .agreement_ctn{
	padding-top:12px;
	font-size:14px;
}
.soof_welcome_page .jump_btn{
	margin-top:56px;
	margin-bottom:36px;
	width:300px;
	height:80px;
	line-height:80px;
	font-size:30px;
}
}

</style>
<script type="text/javascript">
	function jumpTo(url, btn){
		if(!document.getElementById('acceptAgreementChk').checked){
			alert("您必须接受《免费Wi-Fi用户使用协议》才能继续使用 Wi-Fi！");
			return;
		}
		location.href=url;
		if(btn) btn.innerHTML = "请稍后...";
	}
</script>
<div class="soof_welcome_page">
	<div class="page_viewport">
    	<img class="welcome_img" src="${WelcomeBox_welcomePic}" />
        <div class="section_jump">
            <div class="welcome_text">${WelcomeBox_welcomeText}</div>  
            <div class="agreement_ctn">
            	<input type="checkbox" id="acceptAgreementChk" checked="checked" />
                <label for="acceptAgreementChk">
                	我已阅读并接受</label> <a href="${base}acs/toWifiUserAgreement.do" style="text-decoration:underline; color:#090;" target="userAgreement" tabindex="-1">《免费Wi-Fi使用协议》</a>
            </div>
            <c:if test="${preview eq null}">
	            <c:if test="${!auth}">    
		            <button class="jump_btn" style=" background-color:${WelcomeBox_jumpBtnBgColor}; color:${WelcomeBox_jumpBtnTextColor};" onclick="jumpTo('${base}wifiant/freeAuth.do?${queryString}', this)">${WelcomeBox_jumpBtnText}</button>
		        </c:if>
		        <c:if test="${auth}">    
		            <button class="jump_btn" style=" background-color:${WelcomeBox_jumpBtnBgColor}; color:${WelcomeBox_jumpBtnTextColor};" onclick="jumpTo('${base}wifiant/signin/view.do?${queryString}', this)">${WelcomeBox_jumpBtnText}</button>
		        </c:if>            
	        </c:if>            
            <c:if test="${preview ne null}">
		        <button class="jump_btn" style=" background-color:${WelcomeBox_jumpBtnBgColor}; color:${WelcomeBox_jumpBtnTextColor};" onclick="alert('当前为预览模式，请点击左上角的下拉列表进行页面切换！')">${WelcomeBox_jumpBtnText}</button>
	        </c:if>            

			<div class="soof_foot">
                <div class="copyright">
                    ${msite_copyright}
                </div>
            </div>
        </div>
    </div>
</div>