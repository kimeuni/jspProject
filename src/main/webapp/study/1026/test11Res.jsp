<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/* String flag = request.getParameter("flag"); */
	
	/* 이런식으로 전부 다 값을 넘길 수 있다. */
	String name = request.getParameter("name");
	int age= Integer.parseInt(request.getParameter("age"));
	String gender = request.getParameter("gender");
	String hobby = request.getParameter("hobby");
	String job = request.getParameter("job");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>test10Res.jsp</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script>
	'use strict'
	
	<%-- < % =flag % > 는 자바 변수 --%>
	<%-- if('<%=flag%>' != '' && '<%=flag%>' != 'null'){ --%> 	
	if('<%=name%>' != '' && '<%=name%>' != 'null'){ 	
		alert('<%=name%>' + "님 회원가입이 성공적으로 되었습니다.\n\n 회원 메인창으로 이동합니다.");
	}
	else {
/* 		location.href='test11.jsp?flag=no'; */
		location.href='test11.jsp?flag=no';
	}
	
	function logoutCheck() {
		let ans = confirm("로그아웃 하시겠습니까?")
		if(ans) {
<%-- 			alert("'<%= flag %>'님 로그아웃 되셨습니다.") --%>
			alert("'<%= name %>'님 로그아웃 되셨습니다.")
			location.href="test11.jsp";
		}
		else {
			alert("^^~")
		}
	}
	
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>회원 메인창</h2>
	<%-- <p><%= flag %>님 로그인 중이십니다.</p> --%>
	<p><%= name %>님 로그인 중이십니다.</p>

	<!-- 예쁘게.. 희원가입 정보를 띄워준다. -->
	<p>나이 : <%= age %></p>	
	<p>성별 : <%= gender %></p>	
	<p>취미 : <%= hobby %></p>	
	<p>직업 : <%= job %></p>	
	
	
	<p>
		<input type="button" value="로그아웃(button)" onclick="logoutCheck()" class="btn btn-info mr-3">
		<a href="javascript:logoutCheck()" class="btn btn-warning">로그아웃(a)</a>  <!-- href안에 자바스크립트 명령을 적고 싶을 때는 javascript : 을 적어주어야 한다. -->
	</p>
</div>
<p><br/></p>
</body>
</html>