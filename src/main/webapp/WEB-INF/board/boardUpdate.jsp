<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>boardInput.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<style>
		th{
			text-align:center;
			background-color:#eee;
		}
	</style>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2 class="text-center">게 시 판 글 수 정</h2>
	<form name="myform" method="post" action="boardUpdateOk.bo" >
		<table class="table table-bordered">
			<tr>
				<th>글쓴이</th>
				<td>${sNickName}</td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><input type="text" name="title" id="title" value="${vo.title }" required autofocus class="form-control"/></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email" id="email" value="${vo.email}"  class="form-control"/></td>
			</tr>
			<tr>
				<th>홈페이지</th>
				<td><input type="text" name="homePage" id="homePage" value="${vo.homePage }" class="form-control"/></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><textarea rows="8" name="content" id="content" class="form-control" required >${vo.content }</textarea></td>
			</tr>
			<tr>
				<th>공개여부</th>
				<td>
					<input type="radio" name="openSw" value="OK" <c:if test="${vo.openSw == 'OK'}">checked</c:if>/>공개 &nbsp;
					<input type="radio" name="openSw" value="NO" <c:if test="${vo.openSw == 'NO'}">checked</c:if>/>비공개
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
				<!-- 수업에서는 submit으로 했지만, button타입으로해서 검사하고 넘길 것.. -->
					<input type="submit" value="글 수정하기" class="btn btn-success"/>
					<input type="reset" value="다시입력" class="btn btn-warning"/>
					<input type="button" value="돌아가기" onclick="location.href='boardContent.bo?idx=${vo.idx}&pageSu=${pageSu}&pageSize=${pageSize}';" class="btn btn-info"/>
				</td>
			</tr>
		</table>
		<!-- hostIp 넘기기 -->
		<input type="hidden" name="hostIp" value="${pageContext.request.remoteAddr}"/>
		<!-- idx 넘기기 -->
		<input type="hidden" name="idx" value="${vo.idx}"/>
		<!-- hidden으로 pageSu와 PageSize를 넘기지 않으면 boardUpdateOk.bo에서 pageSu와 pageSize의 값을 읽지 못하기 때문에 넘겨야 한다.... -->
		<input type="hidden" name="pageSu" value="${pageSu}"/>
		<input type="hidden" name="pageSize" value="${pageSize}"/>
	</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>