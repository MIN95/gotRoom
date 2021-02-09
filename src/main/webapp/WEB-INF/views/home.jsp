<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>gotRoom</title>
	<link href='/resources/css/home.css' rel='stylesheet' type='text/css'>  
</head>
<body>
<div class="container border">
<jsp:include page="/resources/jsp/header.jsp"></jsp:include>
	<div class="row">
		<div class="col-lg-6 col-md-12 p-0">
			<div id="newsWrap">
				<div><h4><i class="far fa-newspaper"></i><span style="margin-left: 8px;">부동산 핫이슈</span></h4></div>
				<div class="border border-bottom-0 rounded">
					<c:forEach var="newsList" items="${newsList}">
						<div class="news border-bottom"><div class="rounded newsThumb" style="${fn:replace(newsList.thumbnail,'"',null)}"></div>
							<div class="newsItem">
								<c:out value="${newsList.title}"></c:out><div><c:out value="${newsList.source}"></c:out></div>
							</div>
							<a href="https://m2.land.naver.com/news/readNews.nhn?source=headline&prscoId=${newsList.officeid}&artiId=${newsList.articleid}&bssYmd=${newsList.date}"></a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div> 
		<div class="col-lg-6 col-md-12 p-0">
			<div id="messageWrap">
				<div><h4><i class="far fa-comments" style="color: #339af0;"></i><span style="margin-left: 8px;">gotRoom톡</span></h4></div>
				<div id="messageArea" class="border border-secondary rounded"></div>
				<input type="text" id="message" maxlength="255" autocomplete="off" placeholder="자유롭게 이야기를 나눠보세요:)" style="width: 100%"/>
				<a class="btn btn-outline-primary" id="sendBtn" role="button" style="width: 100%">보내기</a>
			</div> 
		</div>        
	</div>
	<div class="row"> 
		<c:forEach var="list" items="${list}"> 
			<div class="col-lg-3 col-md-3 col-sm-4 col-6 p-1 border border-right-0 border-bottom-0 Item"> 
				<div class="itemInner">
					<div class="thumbnail">
						<div class="rounded" style="${fn:replace(list.pic,'"',null)}"></div>
					</div>
					<div class="titleArea">
						<span class="title"><c:out value="${list.name }"/></span>
					</div>
					<div class="priceArea">
						<span class="Type"><c:out value="${list.price }"/></span>
					</div>
					<div class="infoArea">
						<p class="Info"> 
							<span class="Spec"><c:out value="${list.addr }"/></span> 
						</p> 
						<p class="info">
							<span class="spec">
								<c:out value="${list.type}"/>
							</span>
							<span class="spec">
								<c:out value="${list.totalNo }"/>
							</span>
						</p>
					</div>
					<div class="scheduleInfo">
						<span class="schedule"> 
							<span class="type">
								<c:if test="${list.scheduleType eq '분양중'}"><i class="fas fa-comments-dollar" style="color: #ff6b6b;"></i></c:if>
								<c:if test="${list.scheduleType eq '청약중'}"><i class="fas fa-comments-dollar" style="color: #896bff;"></i></c:if>
								<c:out value="${list.scheduleType}"/>
							</span>
						</span>
						<span class="info"><c:out value="${list.scheduleInfo }"/></span>
					</div>
				</div> 
					<a href="<c:out value="${list.url }"/>"></a>
			</div>
		</c:forEach>
	</div>
<jsp:include page="/resources/jsp/footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
var sock = null;
var stompClient = null;
$(document).ready(function(){
	connect();
});
	
$("body").keydown(function(key) {
    if(key.keyCode == 13) {
    	sendChat();
		$('#message').val('');   
    }
});

$("#sendBtn").click(function() { 
	sendChat();
	$('#message').val('');   
});

$("body").keydown(function(key) {
	var message = $.trim($("#message").val());
	if(message!=""){
	    if(key.keyCode == 13) {
	    	sendChat();
	    }
	}
});

$("#sendBtn").click(function() {
	var message = $.trim($("#message").val());
	if(message!=""){
	sendChat(); 
	}
});

function connect() {
	var socket = new SockJS('/chat');
	stompClient = Stomp.over(socket);
	// SockJS와 stomp client를 통해 연결을 시도.
	stompClient.connect({},function(frame){ 
		stompClient.subscribe('/topic/groupmessages',function(response){
			console.log(JSON.parse(response.body));
			showChat(JSON.parse(response.body));
		});
	}); 
}

function disconnect() {
	if (stompClient !== null) {
	  stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
	$("#messageArea").append("연결 끊김");
}

function sendChat() {
	var msg = $.trim($("#message").val());
	if(msg!=""){
		stompClient.send("/app/groupchat", {}, JSON.stringify({'name': '익명','message': msg}));
		$('#message').val('');
	}
	$('#message').focus();
}

function showChat(chat) {
	$("#messageArea").append('<div style="padding:3px;margin-top:3px;">'+chat.name+" : " + chat.message + "<br/></div>");
	$("#messageArea").scrollTop($("#messageArea")[0].scrollHeight);
}		
</script>
</body>
</html>