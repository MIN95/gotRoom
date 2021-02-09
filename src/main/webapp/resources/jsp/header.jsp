<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css"/>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/> 
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
	<link href='/resources/css/commons.css' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="/resources/js/jquery-1.12.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js" integrity="sha512-tL4PIUsPy+Rks1go4kQG8M8/ItpRMvKnbBjQm4d2DQnFwgcBYRRN00QdyQnWSCwNMsoY/MfJY8nHp2CzlNdtZA==" crossorigin="anonymous"></script>
	<script type="text/javascript" src="/resources/js/commons.js"></script>
</head>
<body>
<div class="row" > 
	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 p-0" id="logoWrap">
		<div class="xsBlock" style="float: left;"> 
			<a href="javascript:" class="aTag menu" data-toggle="modal" data-target="#sideModal"><i class="fas fa-bars fa-2x"></i></a>
		</div>
		<div class="modal fade" id="sideModal" tabindex="-1" role="dialog">				
			<div class="modal-dialog" role="document" style="margin: 0">  
			     <div class="modal-content bd-sidebar"> 
					<div class="nav" id="sideLogo">  
						<input type="image" src="/resources/img/mainLogo.png">  
					</div>
					<c:if test="${empty sessionScope.loginUser}"> 
						<div class="nav btnWrap">  
							<button type="button" class="btn btn-outline-primary" href="/login">로그인</button>
					    	<button type="button" class="btn btn-outline-primary" href="/join">회원가입</button>
						</div>
					</c:if>  
					<ul class="nav">
						<li><a href="javascript:"><i class="fas fa-home fa-lg"></i>집구경</a></li>
						<li><a href="/board/list"><i class="far fa-smile-wink fa-lg"></i>커뮤니티</a></li> 
						<li><a href="javascript:"><i class="far fa-question-circle fa-lg"></i>Q&A</a></li>
						<li><a href="javascript:"><i class="far fa-list-alt fa-lg"></i>마이페이지</a></li>
					</ul>
				</div>   
			</div> 
		</div> 
		<div id="logo">    
			<a href="/" id="logoImg"><span class="blind">gotRoom</span></a>
		</div>
		<c:if test="${empty sessionScope.loginUser}">
			<div class="xsBlock" style="float: right;">
				<a href="/login" class="aTag"><i class="fas fa-user fa-2x"></i></a>   
			</div> 
		</c:if>
		<div class="xsBlock" style="float: right;">
			<a href="javascript:" class="aTag" data-toggle="modal" data-target="#searchModal">
				<i class="fas fa-search fa-2x"></i>
			</a>   
		</div> 
	</div>
	<div class="modal fade" id="searchModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document" style="margin: 0">  
		     <div class="modal-content sh-sidebar">  
				<div style="text-align: center;">
					<div id="arrowLeft" style="display: inline-block;"> 
						<a href="javascript:" class="aTag">
							<i class="fas fa-arrow-left fa-2x"></i>
						</a>
					</div>   
					<div id="inputWrap">
						<div class="input-group"> 
					  		<form style="width: 100%;">
					  		<div>
						    	<input type="text" class="form-control" id="query"placeholder="지역명,단지명,필터조건으로 검색" autocomplete="off" maxlength="255">
				        		<button class="btn btn-outline-primary" id="submitBtn" type="submit">검색</button>
			        		</div>
			      			</form>
						</div>
					</div> 
				</div>  
				<div class="searchListWrap">
					<div>
					<span class="layout-navigation-search__list__history-header__title">최근 검색어</span>
					</div>
					<div class="searchList history-empty">최근 검색한 내역이 없습니다.</div>
				</div>
			</div> 
		</div> 
	</div>
	<div class="col-lg-6 col-md-5 p-0 xsNone">
		<div class="input-group" style="margin-top: 3px;"> 
	  		<form style="width: 100%;">
	  		<div>
		    	<input type="text" class="form-control" id="query"placeholder="지역명,단지명,필터조건으로 검색" autocomplete="off" maxlength="255">
        		<button class="btn btn-outline-primary" id="submitBtn" type="submit">검색</button>
       		</div>
     			</form>
		</div>
	</div>
	<div class="text-right col-lg-3 col-md-4 pl-0 xsNone" id="menuWrap">
		<c:choose>  
		    <c:when test="${not empty sessionScope.loginUser }">    
				<div> 
			        <a class="aTag" href="javascrpt:"><span class="badge badge-light">${sessionScope.loginUser }님</span></a>
			        <a class="aTag" href="/user/mymsg"><span class="badge badge-light">내쪽지<i class="fas fa-envelope"></i></span></a>  
			        <a class="aTag" id="logout" style="cursor: pointer;">로그아웃</a>
			    </div>
		    </c:when>  
		    <c:otherwise>
			    <div id="menubar">
			    	<a class="aTag" id="loginBtn" href="/login">로그인</a>
			    	<a class="aTag" href="/join">회원가입</a>
			    </div> 	
		    </c:otherwise>
		</c:choose>
	</div>
</div>
<div class="row">
	<nav class="nav nav-pills nav-justified xsNone" id="navMenu">
	  <a class="nav-item nav-link" href="javascript:">집구경</a>
	  <a class="nav-item nav-link" href="/board/list">커뮤니티</a>
	  <a class="nav-item nav-link" href="javascript:">Q&A</a>
	  <a class="nav-item nav-link" href="javascript:">마이페이지</a>
	</nav>
</div>

