<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>boardList.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		function pageSizeCheck(){
			let pageSize = $("#pageSize").val();
			
			location.href="boardList.bo?pageSize="+pageSize;
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<table class="table table-borderless m-0">
		<tr>
			<td colspan="2"><h2 class="text-center">게 시 판 리 스 트</h2></td>
		</tr>
		<tr>
			<td>
				<!-- 준회원이 아닐경우 글쓰기 작성 가능 -->
				<c:if test="${sLevel != 1}"><a href="boardInput.bo" class="btn btn-success btn-sm">글쓰기</a></c:if>
			</td>
			<td class="text-right">
				<select name="pageSize" id="pageSize" onchange="pageSizeCheck()">
					<option ${pageSize== '3'? 'selected': '' }>3</option>
					<option ${pageSize== '5'? 'selected': '' }>5</option>
					<option ${pageSize== '10'? 'selected': '' }>10</option>
					<option ${pageSize== '15'? 'selected': '' }>15</option>
					<option ${pageSize== '20'? 'selected': '' }>20</option>
				</select> 건
			</td>
		</tr>
	</table>
	<table class="table table-hover text-center">
		<tr class="table-dark text-dark">
			<th>글번호</th>
			<th>글제목</th>
			<th>글쓴이</th>
			<th>근쓴날짜</th>
			<th>조회수(좋아요)</th>
		</tr>
		<c:forEach var="vo" items="${vos}" varStatus="st">
			<tr>
				<td>${startNo}</td>
				<!-- 게시글 상세보기를 들어가기 위해서 해당 게시판의 idx(고유번호)를 같이 넘겨야 한다. (상세보기에서 돌아가기 화면에서 원래 있던 화면으로 돌아가기 위해 pageSu와 pageSize를 넘긴다.) -->
				<td class="text-left"><a href="boardContent.bo?idx=${vo.idx}&pageSu=${pageSu}&pageSize=${pageSize}">${vo.title}</a></td>
				<td>${vo.nickName}</td>
				<td>${fn:substring(vo.wDate,0,16)}</td>
				<td>${vo.readNum}(${vo.good})</td>
			</tr>
			<tr><td colspan="5" class="m-0 p-0"></td></tr>
			<c:set var="startNo" value="${startNo-1}" />
		</c:forEach>
	</table>
</div>
<!-- 페이징 처리(1블로의 크기를 3개(3Page)로 한다. 한페이지는 기본은 5개 -->
<div class="text-center">
	<ul class="pagination justify-content-center">
	<c:if test="${pageSu > 1}"><li class="page-item"><a class="page-link text-secondary " href="boardList.bo?pageSu=1&pageSize=${pageSize}">첫페이지</a></li></c:if>
	<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="boardList.bo?pageSu=${(curBlock-1)*blockSize+1}&pageSize=${pageSize}">이전블록</a></li></c:if>
	<c:if test="${pageSu > 1}"><li class="page-item"><a class="page-link text-secondary" href="boardList.bo?pageSu=${pageSu-1}&pageSize=${pageSize}">◀</a></li></c:if>
	<c:forEach var="i" begin="${(curBlock * blockSize)+1}" end="${(curBlock * blockSize)+blockSize}" varStatus="st">
		<c:if test="${i <= totPage && pageSu == i}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="boardList.bo?pageSu=${i}&pageSize=${pageSize}">${i}</a></c:if>
		<c:if test="${i <= totPage && pageSu != i}"><li class="page-item"><a class="page-link text-secondary" href="boardList.bo?pageSu=${i}&pageSize=${pageSize}">${i}</a></c:if>
	</c:forEach>
	<c:if test="${pageSu < totPage }"><li class="page-item"><a class="page-link text-secondary" href="boardList.bo?pageSu=${pageSu+1}&pageSize=${pageSize}">▶</a></li></c:if>
	<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="boardList.bo?pageSu=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></li></c:if>
	<c:if test="${pageSu < totPage}"><li class="page-item"><a class="page-link text-secondary" href="boardList.bo?pageSu=${totPage}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
	</ul>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>