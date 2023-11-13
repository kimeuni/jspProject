<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>adminMemberInfor.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2><font color="blue"><${vo.nickName}></font>회원님 상세 정보 보기</h2>
	<div style="width:30%; float:left">
		<div style="padding:10px">
			<h4>회원 사진</h4>
			<img src="${ctp}/images/member/${vo.photo}" width="100%" />
		</div>
	</div>
	<div style="width:70%; float:left">
		<table class="table table-bordered">
			<tr><td>아이디 : ${vo.mid }</td></tr>
			<tr><td>닉네임 : ${vo.nickName}</td></tr>
			<tr><td>성명 : ${vo.name}</td></tr>
			<tr><td>성별 : ${vo.gender}</td></tr>
			<tr><td>생일 : ${vo.birthday}</td></tr>
			<tr><td>전화번호 : ${vo.tel}</td></tr>
			<tr><td>주소 : ${vo.address}</td></tr>
			<tr><td>이메일 : ${vo.email}</td></tr>
			<tr><td>홈페이지 : ${vo.homePage}</td></tr>
			<tr><td>직업 : ${vo.job}</td></tr>
			<tr><td>취미 : ${vo.hobby}</td></tr>
			<tr><td>자기소개 : ${vo.content}</td></tr>
			<tr><td>정보공개유무 : ${vo.userInfor}</td></tr>
			<tr><td>탈퇴신청여부 : ${vo.userDel}</td></tr>
			<tr><td>포인트 : ${vo.point}</td></tr>
			<tr><td>등급 : ${strLevel}</td></tr>
			<tr><td>총방문횟수 : ${vo.visitCnt}</td></tr>
			<tr><td>최초가입일 : ${vo.startDate }</td></tr>
			<tr><td>최종접속일 : ${vo.lastDate }</td></tr>
			<tr><td>오늘접속횟수 : ${vo.todayCnt}</td></tr>
		</table>
	</div>
	<!-- 원래 있던 곳으로 돌아가기 위해서 pagesu, pageSize,level등을 같이 넘겨야 원래 있던 화면으로 돌아갈 수 있다. (안넘기면 무조건 1페이지로 간다) -->
	<div style="clear:both"><a href="${ctp}/adminMemberLevelSearch.ad?level=${level}&pageSu=${pageSu}&pageSize=${pageSize}" class="btn btn-warning">돌아가기</a></div>
</div>
<p><br/></p>
</body>
</html>