<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>jstl5Res.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
</head>
<body>
<p><br/></p>
<div class="container">
	<table class="table table-hover text-center">
		<tr class="table-dark text-dark">
			<th>성명</th>
			<th>나이</th>
			<th>성별</th>
			<th>직업</th>
			<th>주소</th>
		</tr>
		<c:forEach var="vo" items="${vos}" varStatus="st">
			<tr>
				<td>${vo.name}</td>
				<td>${vo.age}</td>
				<td>${vo.gender}</td>
				<td>${vo.job}</td>
				<td>${vo.address}</td>
			</tr>
		</c:forEach>
		<tr><td colspan="5" class="m-0 p-0"></td></tr>
	</table>
	<%-- <table class="table table-hover text-center">
		<tr class="table-dark text-dark">
			<th>성명</th>
			<th>나이</th>
			<th>성별</th>
			<th>직업</th>
			<th>주소</th>
		</tr>
			<c:forEach var="i" begin="0" end="${fn:length(vos)-1}" varStatus="st">
				<tr>
					<td>${vos[i].name}</td>
					<td>${vos[i].age}</td>
					<td>${vos[i].gender}</td>
					<td>${vos[i].job}</td>
					<td>${vos[i].address}</td>
				</tr>
			</c:forEach>
		<tr><td colspan="5" class="m-0 p-0"></td></tr> <!-- 마지막에 table선이 나오지 않아서 하나 더 추가.. 대신 테이블이 나오지 않도록 margin과 padding을 0으로 줌.. -->
	</table> --%>
	<hr/>
	<br/>
	<p><a href="${ctp}/study/1101_JSTL/jstl5.jsp" class="btn btn-success">돌아가기(학원수업)</a></p>
	<p><a href="${ctp}/study/1101_JSTL/ex5_jstl.jsp" class="btn btn-success">돌아가기(숙제)</a></p>
</div>
<p><br/></p>
</body>
</html>