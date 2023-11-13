<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>mList.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict';
		function pageSizeCheck(){
			let pageSize = $("#pageSize").val();
			location.href="mList.mem?pageSize="+pageSize;
		}
		
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2 class="text-center">전체회원 리스트</h2>
	<table class="table">
		<tr>
			<td>
				<div>등급별검색   
					<select name="pageSize" id="pageSize" onchange="pageSizeCheck()">
						<option value="3" ${pageSize == 3 ? 'selected' : '' }>3</option>
						<option value="5" ${pageSize == 5 ? 'selected' : '' }>5</option>
						<option value="10" ${pageSize == 10 ? 'selected' : '' }>10</option>
						<option value="15" ${pageSize == 15 ? 'selected' : '' }>15</option>
						<option value="20" ${pageSize == 20 ? 'selected' : '' }>20</option>
					</select>
				</div>
			</td>
		</tr>
	</table>
	<table class="table table-hover text-center">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>아이디</th>
			<th>닉네임</th>
			<th>성명</th>
			<th>성별</th>
			<th>공개여부</th>
			<th>오늘방문횟수</th>
			<th>회원등급</th>
		</tr>
		<tr>
			<c:forEach var="vo" items="${vos}" varStatus="st">
				<tr>
					<td>${startNo }</td>
					<!-- adminMemberInfor.ad로 갔다가 자신의 위치로 돌아오기 위해서는 &pageSu=${pageSu}&pageSize=${pageSize}&level=${level}까지 붙들고 다녀가 돌아가기를 눌렀을 때 있었던 페이지로 돌아갈 수 있음.. 이렇게 안적으면 무조건 1페이지로 돌아감. -->
					<td>
						<!-- userInfor가 "공개"일 경우, 하이퍼링크를 통해 개인정보 확인 가능 / 비공개일 경우 하이퍼링크 표시 없이 하기.. -->
						<c:if test="${vo.userInfor == '공개' || vo.mid == sMid || sLevel == 0}"><a href="mInfor.mem?idx=${vo.idx}">${vo.mid }</a></c:if>
						<c:if test="${vo.userInfor != '공개' && vo.mid != sMid && sLevel != 0}">${vo.mid }</c:if>
					</td>
					<td>${vo.nickName }</td>
					<!-- 
					<td>
						<c:if test="${vo.userInfor == '공개'}">${vo.name }</c:if>
						<c:if test="${vo.userInfor != '공개'}">****</c:if>
					</td>
					-->
					<c:if test="${vo.userInfor == '공개' || vo.mid == sMid || sLevel == 0}">  <!-- inserInfor가 공개이거나, 로그인한 아이디(sesstion)가 같거나 , 로그인한 계정이 관리자일 경우 정보 볼 수 있음. -->
						<td>${vo.name}</td>
						<td>${vo.gender }</td>
						<td>${vo.userInfor }</td>
						<td>${vo.todayCnt }</td>
						<td>
							<c:choose>
								<c:when test="${vo.level == 0 }">관리자</c:when>
								<c:when test="${vo.level == 1 }">준회원</c:when>
								<c:when test="${vo.level == 2 }">정회원</c:when>
								<c:when test="${vo.level == 3 }">우수회원</c:when>
								<%-- <c:otherwise>우수회원</c:otherwise> --%>
							</c:choose>
						</td>
					</c:if>
					<c:if test="${vo.userInfor != '공개' && vo.mid != sMid && sLevel != 0}">
						<td colspan="5">비공개</td>
					</c:if>
				</tr>
				<c:set var="startNo" value="${startNo-1}"/>
			</c:forEach>
		</tr>
		<tr><td colspan="8" class="m-0 p-0"></td></tr>
	</table>
	<br/>
	<div class="text-center">
		<ul class="pagination justify-content-center">
		<c:if test="${pageSu > 1}"><li class="page-item"><a class="page-link text-secondary " href="mList.mem?pageSu=1&pageSize=${pageSize}">첫페이지</a></li></c:if>
		<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pageSu=${(curBlock-1)*blockSize+1}&pageSize=${pageSize}">이전블록</a></li></c:if>
		<c:if test="${pageSu > 1}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pageSu=${pageSu-1}&pageSize=${pageSize}">◀</a></li></c:if>
		<c:forEach var="i" begin="${(curBlock * blockSize)+1}" end="${(curBlock * blockSize)+blockSize}" varStatus="st">
			<c:if test="${i <= totPage && pageSu == i}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="mList.mem?pageSu=${i}&pageSize=${pageSize}">${i}</a></c:if>
			<c:if test="${i <= totPage && pageSu != i}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pageSu=${i}&pageSize=${pageSize}">${i}</a></c:if>
		</c:forEach>
		<c:if test="${pageSu < totPage }"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pageSu=${pageSu+1}&pageSize=${pageSize}">▶</a></li></c:if>
		<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pageSu=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></li></c:if>
		<c:if test="${pageSu < totPage}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pageSu=${totPage}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
		</ul>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>