<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>adminComplaintList.jsp</title>
	<%@ include file="../../include/bs4.jsp" %>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2 style="text-align:center;">신고 관리</h2>
	<table class="table table-hover text-center">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>신고항목</th>
			<th>신고글 번호</th>
			<th>신고자 ID</th>
			<th>신고사유</th>
			<th>신고날짜</th>
			<!-- <th>상세보기</th> -->
		</tr>
		<c:forEach var="vo" items="${vos}" varStatus="st">
			<tr>
				<td>${vo.idx }</td>
				<td>${vo.part }</td>
				<td>${vo.partIdx }</td>
				<td>${vo.cpMid }</td>
				<td>${vo.cpContent }</td>
				<c:if test="${vo.hour_diff > 24 }"><td>${fn:substring(vo.cpDate,0,10)}</td></c:if>
				<c:if test="${vo.hour_diff <= 24}"><td>${vo.date_diff == 0 ? fn: substring(vo.cpDate,11,16) : fn: substring(vo.cpDate,5,16)}</td></c:if>
				<%-- <td><a href ="javascript:location.href='adminCpContent.ad?idx${vo.partIdx}'" class="btn btn-secondary btn-sm">O</a></td> --%>
			</tr>
		</c:forEach>
	</table>
</div>
<p><br/></p>
</body>
</html>