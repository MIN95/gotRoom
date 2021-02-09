<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charSset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>board</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
	<script type="text/javascript" src="/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script> 
	<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css"/>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/> 
	<style type="text/css">
	/* 매우 작은 기기들 (모바일폰, 768px 보다 작은) */
	/* 부트스트랩에서 이것은 기본이므로 미디어쿼리가 없습니다. */
	
	/* 작은 기기들 (태블릿, 768px 이상) */
	@media (min-width: @screen-sm-min) { ... }
	
	/* 중간 기기들 (데스크탑, 992px 이상) */
	@media (min-width: @screen-md-min) { ... }
	
	/* 큰 기기들 (큰 데스크탑, 1200px 이상) */
	@media (min-width: @screen-lg-min) { ... }
	</style>
</head>
<body>
	<div class="container">
		<div class="row">
	     	<div class="col-md-12"><c:out value="${map.TITLE}"/></div>
	    </div>
	    <div class="text-left row">
	     	<div class="col-md-2"><c:out value="${map.WRITER}"/></div> 
		  	<div class="text-center col-md-2"><c:out value="조회수 ${map.VIEW}"/></div>
	     	<div class="text-center col-md-2">   
		     	<jsp:useBean id="now" class="java.util.Date" />  
				<fmt:formatDate value="${now}" var="today" />  
				<fmt:formatDate value="${map.WRITE_DATE }" var="writeDate"/>
				<c:choose>     
					<c:when test="${today eq writeDate}">  
						<fmt:formatDate value="${map.WRITE_DATE }" pattern="HH:mm"/>
					</c:when>  
					<c:otherwise>  
						<fmt:formatDate value="${map.WRITE_DATE }" pattern="yyyy-MM-dd" />   
					</c:otherwise> 
				</c:choose> 
	     	</div>   
	     	<div class="text-center col-md-2"><c:out value="댓글 12"/></div> 
		</div>
		<div class="row">
			<div class="col-md-12"><c:out value="${map.CONTENTS }"/></div> 
		</div>
		<div class="row">
			<div class="text-right col-md-12">
				<a class="btn btn-outline-primary" href="/board/delete/${map.BOARD_NO}" role="button">삭제</a>
			</div>
		</div>
	</div>		
</body>
</html>