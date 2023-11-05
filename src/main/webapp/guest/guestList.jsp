<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>guestList.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		function btnD(idx){
			let ans = confirm("정말로 삭제하시겠습니까?");
			
			if(ans){
				location.href="${ctp}/guest/guestDe?idx="+idx;
			}
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"></jsp:include>
<p><br/></p>
<div class="container">
	<h2 class="text-center">방 명 록 리 스 트</h2>
	<table class="table table-borderless">
		<tr>
			<td>관리자</td>
			<td class="text-right"><a href="${ctp}/guest/guestInput.jsp" class="btn btn-success">글쓰기</a></td>
		</tr>
	</table>
	<hr/>
	<c:forEach var="vo" items="${vos}" varStatus="st">
		<table class="table table-borderless">
			<tr>
				<!-- 역순 출력 실패.. -->
				<%-- <c:set var="cnt" value="0"/>
				<c:forEach var="i" begin="${fn:length(vos)-1}" end="0" step="-1">
					<c:if test="${vo.idx !=null }">
						<c:set var="cnt" value="${fn:length(vos)-i}"/>
						<td class="text-left">번호 : ${cnt}</td>
					</c:if> 
				</c:forEach> --%>
				<td class="text-left">번호 : ${vo.idx}</td>
				<td class="text-right">
					방문IP : ${vo.hostIp}
					<input type="button" name="deleteBtn" onclick="btnD(${vo.idx})" value="글 삭제" class="btn btn-danger"/>
				</td>
			</tr>
		</table>
		<table class="table table-bordered">
			<tr>
				<th>성명</th>
				<td>${vo.name}</td>
				<th>방문일자</th>
				<td>${vo.visitDate}</td>
			</tr>
			<tr >
				<th>메일주소</th>
				<td colspan="3">${vo.email}</td>
			</tr>
			<tr>
				<th>홈페이지</th>
				<td colspan="3">${vo.homePage}</td>
			</tr>
			<tr>
				<th>방문소감</th>
				<td colspan="3">${vo.content}</td>
			</tr>
		</table>
	</c:forEach>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>