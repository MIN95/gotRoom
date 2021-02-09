<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script> 
	<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css" /> 
<style type="text/css">
	#loginWrap {
		width: 100%;
    	height: 326px;
	    position: absolute;
	    top: 37%;
	    left: 50%;
	    transform: translate(-50%,-50%);
	}
	#logo {
		width: 250px;
    	height: 64px;
    	background-image: url("/resources/img/mainLogo.png");
	    background-size: cover;
	    background-position: center;
	    box-shadow: 1px 1px 2px 1px #808080;
	    overflow: hidden;
	    display: inline-block;
	}
	.warning {  
		color: red; 
		margin: 0 auto;
	}
	#naver_id_login {
		width: 100%;
	}
	#naver_id_login #img {
		background-size: cover;
    	background-position: center;
	    overflow: hidden;
    	width: 100%;
	    height: 74px;
   		display: block;
	}
	#loginBtn {
		height: 55px;
		font-size: 20px;
	}  
</style>	
</head>
<body>
<div class="container">	
	<div class="row" id="loginWrap">
		<div class="text-center col-md-12 p-5" > 
			<a href="/"><div id="logo"></div></a>	
		</div>	  
		<div class="input-group col-md-4 offset-md-4 col-sm-8 offset-sm-2 col-8 offset-2">
			<div style="width:100%">
				<form id="loginForm" class="form-signin" method="post" action="/login/default"> 
					<div>
						<input type="text" id="userName" name="userName" class="form-control" placeholder="Id">
				    	<input type="password" id="password" name="password" class="form-control" placeholder="Password">
					</div>
					<div id="btnWrap">
						<button id="loginBtn" class="btn btn-primary btn-block" type="button"><i class="fas fa-sign-in-alt"></i>로그인</button>
					</div>
					<p class="warning"><c:out value="${warning ne null?warning:null}"></c:out></p>
					<input type="hidden" name="snsType" value="default">
				</form> 
			</div>
			<div id="naver_id_login">
			<a href="${url}" id="img" class="rounded" style="background-image: url('/resources/img/naver_Bn_Green.PNG');">   
			</a>
			</div>
<%-- 			<img src="${pageContext.request.contextPath}/resources/img/naver_Bn_Green.PNG"/></a></div> --%>
		</div> 
	</div> 
</div>
<script type="text/javascript">
$(document).ready(function(){

});
$("body").keydown(function(key) {
	if(key.keyCode == 13) {
		fn_login();
	}
});

$("#loginBtn").on("click",function(){
	fn_login();
});
function fn_login(){
	$(".warning").remove();
	let id = $("#userName").val();
	let pw = $("#password").val();
	if($.trim(id)==""){
		 $("#userName").after("<p class='warning'>아이디를 입력해주세요</p>");
		 return;
	}
	if($.trim(pw)==""){
		 $("#password").after("<p class='warning'>비밀번호를 입력해주세요</p>");
		 return;
	}
	$("#loginForm").submit();	
}
</script>
</body> 
</html>