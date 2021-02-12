<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charSset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<style type="text/css">
		#listWrap {
			width: 100%;
			height :520px;
			padding: 20px; 
			box-shadow: 0.5px 0.5px 3px 0.5px #dbefef;
		}
		.msg {
			    font-size: 88% !important;	
		}
	</style>
</head>  
<body>
<div class="container min-vh-100 border"> 
	<jsp:include page="/resources/jsp/header.jsp"></jsp:include>
	<div class="row p-3">
		<div class="list-group border mt-4 rounded" id="listWrap">
			<c:forEach var="list" items="${list}">
				<a href="javascript:sendWebMsg('<c:out value="${sessionScope.loginUser eq list.nickname1?list.nickname2:list.nickname1}" />')" class="list-group-item list-group-item-action" aria-current="true">
				  <span class="badge badge-light msg"><c:out value="${sessionScope.loginUser eq list.nickname1?list.nickname2:list.nickname1}" />님의 쪽지 <i class="fas fa-envelope" style="color: #339af0;"></i></span> 
				</a>
			</c:forEach> 
		</div>
	</div>
	<jsp:include page="/resources/jsp/footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
function sendWebMsg(targetUserid){
	if(targetUserid=="${sessionScope.loginUser}"){
		alert("자신에게는 쪽지를 보낼 수 없습니다");
		return;
	} 
	var form = { 
			"targetUserid": targetUserid,
    }
    $.ajax({
        url: "/user/checkMsg",
        type: "POST",
        data: form, 
        success: function(data){ 
        	if(data != null){  
        		var option = "width = 400, height = 492, top = 100, left = 200, location = no";
        		window.open('/user/msgRoom/'+data+'?targetUserid='+targetUserid, "msgRoom",option);
        	}
        },
        error: function(){
            console.log("err");
        }
    });
}
</script>
</body>
</html>