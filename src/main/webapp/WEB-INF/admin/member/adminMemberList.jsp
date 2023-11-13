<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>adminMemberList.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict';
		function levelChange(e){
			let ans = confirm("선택한 회원의 등급을 변경하시겠습니까?")
			
			if(!ans) {
				location.reload();
				return false;
			}
			let items = e.value.split("/");
			let query = {
				idx : items[1],
				level : items[0]
			}
			
			$.ajax({
				url : "adminMemberLevelChange.ad",
				type : "post",
				datd : query,
				success : function(res){
					if(res != "0"){
						alert("등급 수정 완료!")
						location.reload();
					}
					else {
						alert("등급 수정 실패~")
					}
				},
				error : function(){
					alert("전송 오류~");
				}
			});
		}
		
		
		function levelSearch(e){  // 집에서 한 거... (서블릿을 새로 만들어서 넘김)
			let level = e.value;
			
			location.href = "adminMemberLevelSearch.ad?level="+level;
		}
		
		function levelItemCheck(){
			let level = $("#levelItem").val();
			location.href = "adminMemberList.ad?level="+level;  // 서블릿 파일을 새로 안만들고 기존에 있던 멤버리스트에 만들기..(학원에서 함)
		}
		
		// 탈퇴신청회원 실제로 제거하기
		function memberDeleteOk(idx){
			let ans =confirm("선택된 회원을 삭제하시겠습니까?");
			if(!ans) return false;
			
			$.ajax({
				url : "memberDeleteOk.mem",
				type : "post",
				data : {idx:idx},
				success : function(res){
					alert("회원삭제 완료!!");
					location.reload();
				},
				error : function(){
					alert("전송오류~");
				}
			});
		}
		
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2 class="text-center">${strLevel} 리스트</h2>
	<table class="table">
		<tr>
			<td>
				<div>등급별검색   <!-- onchange를 누르면 리스트에 출력되는게 바뀌도록..처리... ( -->
					<!-- <select name="levelItem" id="levelItem" onchange="levelItemCheck()"> -->
					<select name="levelS" onchange="levelSearch(this)">
						<option value="99" ${level== 99 ? 'selected' : '' }>전체검색</option>
						<option value="0" ${level== 0 ? 'selected' : '' }>관리자</option>
						<option value="1" ${level== 1 ? 'selected' : '' }>준회원</option>
						<option value="2" ${level== 2 ? 'selected' : '' }>정회원</option>
						<option value="3" ${level== 3 ? 'selected' : '' }>우수회원</option>
					</select>
				</div>
			</td>
		</tr>
	</table>
	<table class="table table-hover">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>아이디</th>
			<th>닉네임</th>
			<th>성명</th>
			<th>공개여부</th>
			<th>오늘방문횟수</th>
			<th>탈퇴신청</th>
			<th>레벨</th>
		</tr>
		<tr>
			<c:forEach var="vo" items="${vos}" varStatus="st">
				<tr>
					<td>${startNo }</td>
					<!-- adminMemberInfor.ad로 갔다가 자신의 위치로 돌아오기 위해서는 &pageSu=${pageSu}&pageSize=${pageSize}&level=${level}까지 붙들고 다녀가 돌아가기를 눌렀을 때 있었던 페이지로 돌아갈 수 있음.. 이렇게 안적으면 무조건 1페이지로 돌아감. -->
					<td><a href="adminMemberInfor.ad?idx=${vo.idx}&pageSu=${pageSu}&pageSize=${pageSize}&level=${level}">${vo.mid }</a></td>
					<td>${vo.nickName }</td>
					<td>${vo.name }</td>
					<td>${vo.userInfor }</td>
					<td>${vo.todayCnt }</td>
					<td>
						<c:if test="${vo.userDel == 'OK'}"><font color="red"><b>탈퇴신청</b></font>
							<!-- 탈퇴신청한지 30일이상이면.. 나와라.. / 해당값을 삭제하기 위해서 vo.idx(고유번호)를 넘긴다. -->
							<c:if test="${vo.deleteDiff >=30}">(<a href="javascript:memberDeleteOk(${vo.idx})" title="30일경과">x</a>)</c:if>
						</c:if>
						<c:if test="${vo.userDel != 'OK'}">활동중</c:if>
					</td>
					<td>
						<form name="levelForm">
							<select name="level" onchange="levelChange(this)"> <!-- this : 현재상황을 넘김 -->
								<option value="0/${vo.idx}" ${vo.level== 0 ? 'selected' : '' }>관리자</option>
								<option value="1/${vo.idx}" ${vo.level== 1 ? 'selected' : '' }>준회원</option>
								<option value="2/${vo.idx}" ${vo.level== 2 ? 'selected' : '' }>정회원</option>
								<option value="3/${vo.idx}" ${vo.level== 3 ? 'selected' : '' }>우수회원</option>
							</select>
						</form>
					</td>
				</tr>
				<c:set var="startNo" value="${startNo-1}"/>
			</c:forEach>
		</tr>
		<tr><td colspan="8" class="m-0 p-0"></td></tr>
	</table>
	<br/>
	<div class="text-center">
		<ul class="pagination justify-content-center">
		<c:if test="${pageSu > 1}"><li class="page-item"><a class="page-link text-secondary " href="${ctp}/adminMemberLevelSearch.ad?level=${level}&pageSu=1&pageSize=${pageSize}">첫페이지</a></li></c:if>
		<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/adminMemberLevelSearch.ad?level=${level}&pageSu=${(curBlock-1)*blockSize+1}&pageSize=${pageSize}">이전블록</a></li></c:if>
		<c:if test="${pageSu > 1}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/adminMemberLevelSearch.ad?level=${level}&pageSu=${pageSu-1}&pageSize=${pageSize}">◀</a></li></c:if>
		<c:forEach var="i" begin="${(curBlock * blockSize)+1}" end="${(curBlock * blockSize)+blockSize}" varStatus="st">
			<c:if test="${i <= totPage && pageSu == i}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="${ctp}/adminMemberLevelSearch.ad?level=${level}&pageSu=${i}&pageSize=${pageSize}">${i}</a></c:if>
			<c:if test="${i <= totPage && pageSu != i}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/adminMemberLevelSearch.ad?level=${level}&pageSu=${i}&pageSize=${pageSize}">${i}</a></c:if>
		</c:forEach>
		<c:if test="${pageSu < totPage }"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/adminMemberLevelSearch.ad?level=${level}&pageSu=${pageSu+1}&pageSize=${pageSize}">▶</a></li></c:if>
		<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/adminMemberLevelSearch.ad?level=${level}&pageSu=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></li></c:if>
		<c:if test="${pageSu < totPage}"><li class="page-item"><a class="page-link text-secondary" href="${ctp}/adminMemberLevelSearch.ad?level=${level}&pageSu=${totPage}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
		</ul>
	</div>
</div>
<p><br/></p>
</body>
</html>