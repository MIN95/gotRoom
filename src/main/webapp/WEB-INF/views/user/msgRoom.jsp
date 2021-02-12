<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head> 
	<title>message</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
	<script type="text/javascript" src="/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js" integrity="sha512-tL4PIUsPy+Rks1go4kQG8M8/ItpRMvKnbBjQm4d2DQnFwgcBYRRN00QdyQnWSCwNMsoY/MfJY8nHp2CzlNdtZA==" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.0/locale/ko.js"></script>
	<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css" /> 
<style type="text/css">
	body {
		overflow: hidden;    
	}
	.bWrap {
		max-width: 270px;
		min-height: 50px;   
		position: relative;
		margin-bottom: 22px;
		clear: both;
	} 
	.bubble { 
		max-width: 200px;
		padding: 13px;
		background: #d9ddff; 
		border-radius: 10px;
		word-break:break-all;
		display: inline-block;     
		overflow: hidden;
	} 
	#chatName {
		clear: both;
		color: #7b8084;
		font-size: 14px; 
	}
	  
	.me {
		float: right;
	}
	.chatTime { 
	    color: #7b8084;
	    font-size: 14px;
	    display: inline-block;
	    vertical-align: bottom;
	} 
	.you { 
		float: left;
	} 
	#targetUser {
		padding: 7px;
		background-color: #d9fff2;
	}  
	#messageArea {
		height: 450px;   
 		overflow: auto;
		background-color:#d9fff2;
		padding-top: 15px; 
	} 
	#message { 
		width : 100%;
	}
	#sendBtn {
		width : 100%;
	}
	/* 작은 기기들 (태블릿, 768px 이상) */
	@media (min-width: @screen-sm-min) { ... }
	
	/* 중간 기기들 (데스크탑, 992px 이상) */
	@media (min-width: @screen-md-min) { ... }
	
	/* 큰 기기들 (큰 데스크탑, 1200px 이상) */
	@media (min-width: @screen-lg-min) { ... }
</style>
</head> 
<body>
<div class="container-fluid">
<div class="row"> 
	<div id="targetUser" class="text-center col-md-12" >
		<c:out value="${targetUserid}"/>
	</div> 
	<div class="col-md-12" id="messageArea">
		<c:forEach var="list" items="${list}"> 
			<div id="chatName"><c:out value="${list.speaker eq id ?'': targetUserid }"/></div>
			<c:if test="${list.speaker eq id }">
				<div class="bWrap me"> 
		     		<div class="chatTime"><fmt:formatDate value="${list.msgDate }" type="time" timeStyle="short"/></div>
					<div class="bubble"> 
						<c:out value="${list.msgLog }"/>
		     		</div>  
	     		</div>  
			</c:if>  
			<c:if test="${list.speaker ne id }">
				<div class="bWrap you"> 
					<div class="bubble"> 
						<c:out value="${list.msgLog }"/> 
		     		</div>  
		     		<div class="chatTime"><fmt:formatDate value="${list.msgDate }" type="time" timeStyle="short"/></div>
		     	</div> 
			</c:if>
<%-- 			<c:out value="${list.msgLog }"/> --%>
<%-- 	     	<jsp:useBean id="now" class="java.util.Date" />   --%>
<%-- 			<fmt:formatDate value="${now}" var="today" />    --%>
<%-- 			<fmt:formatDate value="${list.msgDate }" var="msgDate"/> --%>
<%-- 			<c:if test="${today eq msgDate}"> --%>
<!-- 	     	<div class="bWrap2"></div> -->
<!-- 	     	</div>   -->
<%-- 	     	<div class="chatTime"><fmt:formatDate value="${list.msgDate }" type="time" timeStyle="short"/></div> --%>
		</c:forEach>
	</div>
	<div class="col-md-10 p-0"> 
		<input type="text" id="message" maxlength="255" autocomplete="off"/>
	</div>
	<div class="col-md-2 p-0"> 
		<a class="btn btn-outline-primary" id="sendBtn" role="button">보내기</a>
	</div>
</div>
</div>

<script type="text/javascript">
var sock = null;
var stompClient = null;
$(document).ready(function(){ 
	connect();
	GetMessage();
}); 

var pageMax = (Math.ceil("${count}"/10)* 10);
var page = 0;
$("#messageArea").scroll(function(){
	if(page < pageMax){
	    if($("#messageArea").scrollTop() == 0){     
	    	GetMessage();  
	    }  
	} 
});
function GetMessage() { 
	var form = { 
			"id" : "${id }",
			"targetUserid" : "${targetUserid }",
			"page" : page,
			"mymsgId" : "${mymsgId}"
    }  
    $.ajax({ 
        url: "/user/getMsg", 
        type: "POST",
        contentType:"application/json;charset=UTF-8",
        data: JSON.stringify(form),  
        success: function(data){ 
       		$('#messageArea').prepend(data);  
       	 	page = page + 10;
       	 	$("#messageArea").animate({scrollTop: (450)},100);
        },
        error: function(){
            console.log("err");
        }
    });	
} 

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

var sesessionId = "";
function connect() {
	var socket = new SockJS('/chat');
	stompClient = Stomp.over(socket);
	// SockJS와 stomp client를 통해 연결을 시도.
	stompClient.connect({},function(frame){
		let url = socket._transport.url;
		let arr = url.split('/');
		sessionId = arr[5];
		stompClient.subscribe('/topic/messages/${mymsgId}',function(response){
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
}

function sendChat() {
	var message = $.trim($("#message").val());
	stompClient.send("/app/chat", {}, JSON.stringify({'sessionId': sessionId,'name': '${sessionScope.loginUser}','mymsgId': '${mymsgId}','message': message}));
	$('#message').val('');
}

function showChat(chat) {
	var now = new Date(chat.time);
	var mmt = moment(now);
	var chatTime = mmt.format('LT');   
	if(chat.name == "${sessionScope.loginUser}"){      
		$("#messageArea").append('<div class="bWrap me"><div class="chatTime">'+chatTime+'&nbsp;</div><div class="bubble">'+chat.message+'</div></div></div>');
	}else {   
		$("#messageArea").append('<div class="bWrap you"> <div id="chatName">'+chat.name+'</div><div class="bubble">'+chat.message+'</div><div class="chatTime">'+chatTime +'&nbsp;</div></div></div>');
	} 
	$("#messageArea").scrollTop($("#messageArea")[0].scrollHeight);
}
</script>
</body> 
</html>