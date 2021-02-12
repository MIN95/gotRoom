<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</c:forEach>