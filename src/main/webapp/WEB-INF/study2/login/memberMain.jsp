<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<%-- <%@ include file="/include/memberCheck.jsp" %> --%>
<!DOCTYPE html>
<script>
	'use strict'
	
	//개별조회버튼 클릭시 demo에 입력폼을 보여주고 입력을 받을 수 있도록 처리한다.
	function searchCheck(){
		let str = '';
		str += '검색할 아이디 : ';
		str += '<input type="text" name="sid" id="sid" size="15">'; //form이 없기 때문에 id가 있어야 한다.
		str += '<input type="submit" onclick="memberS()" value="검색" class="btn btn-success ml-3">';
		demo.innerHTML = str;
		let sid = document.getElementById("sid").value;
	}
	function memberS(){
		let sid = document.getElementById("sid").value;
		location.href="${ctp}/database/ex_memberSer?sid="+sid;
	}
	
	function mLogout() {
		let ans = confirm("정말로 로그아웃 하시겠습니까?");
		if(ans){
			location.href="${ctp}/database/memberLogout?mid=+${sMid}";
		}
	}
	
	function deleteCheck(){
		let ans = confirm("정말로 회원탈퇴 하시겠습니까?");
		if(ans){
			location.href="${ctp}/database/deleteOk";
		}
	}
</script>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>memberMain.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container text-center">
	<h2>회원 메인 화면</h2>
	<div>${sName}님 로그인 중이십니다.</div>
	<hr/>
	
	<p>현재 보유중인 포인트 : ${sPoint}</p>
	<p>최종 접속일 : ${sLastDate}</p>
	<p>오늘 접속횟수 : ${sTodayCount}회</p>
	<hr/>
	<c:set var="random"><%= (int)(Math.random()*5)+1 %></c:set>
	<p><img src="${ctp}/images/${random}.jpg" width="300px"></p>
	<hr/>
	<div class="row">
		<div class="col"></div>
		<div class="col"><a href="javascript:searchCheck()" class="btn btn-success form-control">개별조회</a></div>
		<div class="col"><a href="${ctp}/database/memberList" class="btn btn-primary form-control">전체조회</a></div>
		<div class="col"><a href="${ctp}/study/database/update.jsp" class="btn btn-info form-control">정보수정</a></div>
		<div class="col"><input type="button" onclick="mLogout()" value="로그아웃" class="btn btn-warning form-control"></div>
		<div class="col"><a href="javascript:deleteCheck()" class="btn btn-danger form-control">회원탈퇴</a></div>
		<%-- <div class="col"><a href="${ctp}/database/memberLogout" class="btn btn-danger form-control">로그아웃</a></div> --%>
		<div class="col"></div>
	</div>
	<hr/>
	<div id="demo"></div>
	<div>
		<c:if test="${vo != null}">
		<table class="table table-hover text-center">
			<tr class="table-dark text-dark">
				<th>아이디</th>
				<th>비밀번호</th>
				<th>성명</th>
				<c:if test="${sMid == 'admin' || sMid == vo.mid}"> <!-- <admin>으로 접속했거나... 검색한 단어(vo.mid)와 접속한 아이디(sMid)가 같으면 보이도록 -->
					<th>포인트</th>  
					<th>최종 접속일</th>
					<th>오늘 방문횟수</th>
				</c:if>
			</tr>
			<tr>
				<td>${vo.mid}</td>
				<td>${vo.pwd}</td>
				<td>${vo.name}</td>
				<c:if test="${sMid == 'admin' || sMid == vo.mid}">
					<td>${vo.point}</td>
					<td>${vo.lastDate}</td>
					<td>${vo.todayCount}</td>
				</c:if>
			</tr>
			<tr><td colspan="6" style="margin:0px; padding:0px;"></td></tr>
		</table>
		</c:if>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>