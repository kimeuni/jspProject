<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String imgName = request.getParameter("imgName");
	System.out.println(imgName);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>test7.jsp</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>콤보상자에서 그림파일을 선택하면 선택된 그림을 화면에 출력시켜주세요.</h2>
	<form name="myform" method="get" action="<%= request.getContextPath() %>/1026/test7">
	<select id="choi" onchange="choiImg()">
		<option value="">그림을 선택하세요.</option>
		<option >그림1</option>
		<option >그림2</option>
		<option >그림3</option>
		<option >그림4</option>
	</select>
	<hr/>
	<div id="demo">이미지 출력하는 곳.</div>
	</form>
</div>
<p><br/></p>
</body>
<script>
	'use strict'
	
	
	function choiImg() {
		location.href = '<%= request.getContextPath() %>/1026/test7?name=' + document.getElementById("choi").value;
		
	}
	if('<%=imgName%>' == '' || '<%=imgName%>' == 'null') {
		alert("이미지를 찾을 수 없습니다.");
	}
	else {
		demo.innerHTML = '<img src="../../images/<%=imgName%>" width="500px"/>'
	}
</script>
</html>