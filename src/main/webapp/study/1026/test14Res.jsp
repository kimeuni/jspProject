<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
		
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>test14Res.jsp</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script>
	'use strict'
	
	alert('${vo.name}' + "님 회원가입 성공!!")  /* if문으로 null이 아닌경우에 메세지를 띄워라.. 라고 적는 게 좋다! */
	
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>회원 메인창</h2>
	<%-- EL 표기법 ==> ${} --%>
	<p> ${vo.name}님 로그인 중이십니다.</p>

	<!-- 예쁘게.. 희원가입 정보를 띄워준다. -->
	<p>나이 : ${vo.age}</p>	
	<p>성별 : ${vo.gender}</p>	
	<p>취미 : ${vo.hobby}</p>	
	<p>직업 : ${vo.job}</p>	
	
	
	<p>
		<input type="button" value="로그아웃(button)" onclick="logoutCheck()" class="btn btn-info mr-3">
		<!-- <a href="javascript:logoutCheck()" class="btn btn-warning">로그아웃(a)</a> -->  <!-- href안에 자바스크립트 명령을 적고 싶을 때는 javascript : 을 적어주어야 한다. -->
		<a href="<%= request.getContextPath() %>/j1026/test14Logout?name=${vo.name}" class="btn btn-warning">로그아웃(a)</a> 
	</p>
</div>
<p><br/></p>
</body>
</html>