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
				<td class="text-left">
					<a href="boardContent.bo?idx=${vo.idx}&pageSu=${pageSu}&pageSize=${pageSize}">${vo.title}</a>
					<c:if test="${vo.hour_diff<=24}"><img src = "${ctp}/images/new.gif" /></c:if>
				</td>
				<td>${vo.nickName}</td>
				<!-- new.gif(24시간이내 작성 글)가 표시된 글은 시간만 표시시켜주고, 그렇지 않은 자료는 일자만(년,월,일) 표시시켜주시오. -->
				<td>
					<!-- 24시간 이내 and 글적은 날짜와 오늘 날짜가 같을 때 == > 시간만 표시 -->
					<c:if test="${vo.hour_diff<=24 && fn:substring(vo.wDate,0,10) == strToday}">${fn:substring(vo.wDate,10,16)}</c:if>
					<!-- 24시간 이내 and 글적은 날짜와 오늘 날짜가 다를 때 == > 날짜+시간 표시 -->
					<c:if test="${vo.hour_diff<=24 && fn:substring(vo.wDate,0,10) != strToday}">${fn:substring(vo.wDate,5,16)}</c:if>
					<!-- 24시간 이후는 날짜만 보이도록처리 -->
					<c:if test="${vo.hour_diff > 24}">${fn:substring(vo.wDate,0,10)}</c:if>
				</td>
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
<!-- 블록페이지 끝 -->

<!--  검색기 처리 -->
<div class="container text-center">
	<form name="searchForm" method="post" action="boardSearch.bo">
		<b>검색 : </b>
		<select name="search" id="search">
			<option value="title" selected>글제목</option> <!-- value를 DB에 저장한 변수명으로 적어야 사용가능 value를 안 넣으면 <글제목>이라고 한글로 넘어가기 때문에 의미 없음 -->
			<option value="nickName">글쓴이</option>
			<option value="content">글내용</option>
		</select>
		<input type="text" name="searchString" id="searchString" />
		<input type="submit" value="검색" onclick="searchCheck()" class="btn btn-secondary btn-sm"/> <!-- 원래라면 button으로 넘겨서 공백처를 등을 하고 값을 넘겨야 한다. -->
		<!-- 쓸지 안쓸지는 모르지만, 혹시 다시 돌아오는 것을 처리하게 된다면 pageSu와 pageSize를 넘겨줘야 현재 페이지를 볼 수 있기 때문에 값을 넘겨준다. -->
		<input type="hidden" name="pageSu" value="${pageSu}"/>  
		<input type="hidden" name="pageSize" value="${pageSize}"/>
	</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>