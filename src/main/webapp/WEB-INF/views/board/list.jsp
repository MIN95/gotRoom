<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charSset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<style type="text/css"> 
	.hiddenBox {
		border: 1px solid rgba(0,0,0,.12)!important;
		background: #fff;
		padding : 7px;   
    	top: 14px;
    	right: -35px;
		z-index: 9999; 
		display: none;
	}
	.hiddenBox ul {
		list-style: none;
		padding: 0;
		margin: 0; 
	}
	
	.hiddenBox li{
		border-bottom: 1px solid #f1f1f1 !important;
	}
	.boardList {
		min-height: 480px;
	}
	.boardList .contents { 
		border-bottom: 1px solid #dee2e6 ;
	}
	.boardList .contents .item {
		display: inline-block;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	    overflow: hidden !important;
	}
	.writerWrap {
		width: 20%;
		cursor: pointer;
	}
	</style>
</head>
<body>
<div class="container min-vh-100 border">
	<jsp:include page="/resources/jsp/header.jsp"></jsp:include>
	<div class="row border p-2">
		<c:if test="${map.search ne null and fn:length(list) > 0 }">
			<div class="text-left col-md-12 border-bottom p-1"> 
				<span class="font-weight-bold text-truncate" style="max-width: 150px;"><c:out value="${map.search }"/></span>
				<span>
				(
				<c:if test="${map.selectOption eq 's'}">제목+내용</c:if>
				<c:if test="${map.selectOption eq 't'}">글제목</c:if>
				<c:if test="${map.selectOption eq 'w'}">글작성자</c:if>
				)
				<c:out value="에 대한 검색 결과 (총${pageMaker.totalCount }건)"/>
				</span>
			</div>
		</c:if>
		<c:if test="${fn:length(list) == 0 }">
			<div class="text-center col-md-12 border-bottom p-1">
				<p>검색결과가 존재하지 않습니다˚‧º·(˚ ˃̣̣̥⌓˂̣̣̥ )‧º·˚</p>
			</div>
		</c:if> 
		<div class="col-md-12 col-12 boardList p-0">
			<c:forEach var="list" items="${list}">
			<div class="contents">
		     	<div class="text-center item aTag" style="width: 12%"><c:out value="${list.boardNo}"/></div>
		     	<div class="text-left item" style="width: 31%;"> 
			     	<a class="aTag" href="/board/detail/${list.boardNo }">
		     			<c:if test="${map.selectOption eq 's' or map.selectOption eq 't'}">
				     		<c:choose>
				     			<c:when test="${ fn:containsIgnoreCase(list.title, map.search) }">
		 							<span>${fn:replace(fn:replace(list.title, map.search, '<span class="font-weight-bold">map.search</span>'), 'map.search',map.search)}</span>
				     			</c:when>  
				     			<c:otherwise><span><c:out value="${list.title}"/></span></c:otherwise>
				     		</c:choose>
		     			</c:if>
		     			<c:if test="${map.selectOption eq 'w' or map.selectOption eq null}"><c:out value="${list.title}"/>  </c:if>
		     		</a>
		     	</div>
		     	<div class="text-center writerWrap item aTag">
			     	<c:choose>
				     	<c:when test="${map.selectOption eq 'w'}">
				     		<c:if test="${ fn:containsIgnoreCase(list.writer, map.search) }">
				     			${fn:replace(fn:replace(list.writer, map.search, 'map.search'), 'map.search',map.search)}
				     		</c:if> 
				     	</c:when>
				     	<c:otherwise>
				     		<c:out value="${list.writer}"/>
				     	</c:otherwise>
			     	</c:choose>
			     	<div class="hiddenBox position-absolute">
			     		<ul>
			     			<li><a class="aTag" href="javascript:void(0)">회원정보</a></li>
			     			<li><a class="aTag" href="/board/list?selectOption=w&search=${list.writer}">작성글 보기</a></li>
			     			<c:if test="${sessionScope.loginUser ne list.writer}">
			     				<li><a class="aTag" href="javascript:sendWebMsg('${list.writer}')">쪽지 보내기<i class="fas fa-envelope"></i></a></li>
			     			</c:if>
			     		</ul> 
			     	</div> 
		     	</div>
		     	<div class="text-center aTag item" style="width: 20%">   
			     	<jsp:useBean id="now" class="java.util.Date" /><fmt:formatDate value="${now}" var="today" /><fmt:formatDate value="${list.writeDate }" var="writeDate"/>
					<c:choose> 
						<c:when test="${today eq writeDate}"><fmt:formatDate value="${list.writeDate }" pattern="HH:mm"/></c:when>  
						<c:otherwise><fmt:formatDate value="${list.writeDate }" pattern="yyyy-MM-dd" /></c:otherwise> 
					</c:choose> 
		     	</div> 
			  	<div class="text-center aTag item" style="width: 12%"><c:out value="${list.view}"/></div>
		  	</div>
			</c:forEach>
			<a class="btn btn-primary" href="javascript:" type="button" style="float: right;margin: 10px;">글작성</a>
		</div>
		<form id="searchForm" method="get" action="/board/list" style="width: 100%;"> 
			<div class="row px-3" style="border: none;padding-top: 3px">
				<div class="input-group-append col-md-2 p-0">
					<select class="form-select" name="selectOption" style="width: 100%;">
					  <option value="s"<c:out value="${map.selectOption eq 's' or '' ? 'selected':''}"/>>제목+내용</option>
					  <option value="t"<c:out value="${map.selectOption eq 't' ? 'selected':''}"/>>글제목</option>   
					  <option value="w"<c:out value="${map.selectOption eq 'w' ? 'selected':''}"/>>글작성자</option> 
					</select>  
				</div>
				<div class="col-md-8 p-0"> 
				    <input type="text" id="search" name="search" class="form-control" placeholder="검색어 입력" value="${map.search eq null ? '':map.search}" aria-label="검색어 입력" aria-describedby="searchBtn">
				</div>
			  	<div class="input-group-append col-md-2 p-0">
			    	<a id="searchBtn" class="btn btn-outline-secondary" type="button" style="width: 100%;">search</a>
			  	</div>
			</div>
		</form>
	</div>
	<div class="row border mt-4">  
		<div class="col-md-12">
			<ul class="pagination" style="justify-content: center;margin: revert;">
			    <c:if test="${pageMaker.prev }">
				    <li class="page-item"> 
				    	<a class="page-link" href='<c:url value="/board/list/${pageMaker.startPage-1 }${pageMaker.makeSearch() }"/>' aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					        <span class="sr-only">Previous</span>
					    </a> 
				    </li>
			    </c:if>
			    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
				    <c:choose>
						<c:when test="${pageMaker.cri.page eq pageNum }"> 
							<li class="page-item active">
						        <a class="page-link" href='<c:url value="/board/list/${pageNum }${pageMaker.makeSearch() }"/>'>${pageNum }</a>
						    </li> 
						</c:when>  
						<c:otherwise>  
							<li class="page-item">
						        <a class="page-link" href='<c:url value="/board/list/${pageNum }${pageMaker.makeSearch() }"/>'>${pageNum }</a>
						    </li>    
						</c:otherwise> 
					</c:choose>
			    </c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item">
						<a class="page-link" href='<c:url value="/board/list/${pageMaker.endPage+1 }${pageMaker.makeSearch() }"/>' aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					        <span class="sr-only">Next</span>
				      	</a> 
					</li>
				</c:if>
			</ul> 
		</div>
	</div>
	<jsp:include page="/resources/jsp/footer.jsp"></jsp:include>
</div>	
<script type="text/javascript">
	$(document).ready(function(){
	}); 
	$("#searchBtn").click(function(){
		if($.trim($("#search").val())!=""){
			$("#searchForm").submit();
		}
	});
	$(document).click(function(e){ 
	    if (!$(e.target).is('.writerWrap')) { 
	    	$('.hiddenBox').css( 'display', 'none');
	    }
	});
	$(".writerWrap").click(function(){
		var isVisible = $(this).children(".hiddenBox").is(':visible');
		 if(isVisible == true){   
			 $('.hiddenBox').css( 'display', 'none');
		 }else{  
			$('.hiddenBox').not(this).css( 'display', 'none');
			$(this).children(".hiddenBox").css('display','block'); 
		 }
	});
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
            	if(data != 0){
            		var option = "width = 400, height = 492, top = 100, left = 200, location = no";
            		window.open('/user/msgRoom/'+data+'?targetUserid='+targetUserid, "msgRoom",option);
            	}else {
            		alert("자신에게는 쪽지를 보낼 수 없습니다");
            	}
            },
            error: function(){
                alert("err");
            }
        });
	}
</script>
</body>
</html>