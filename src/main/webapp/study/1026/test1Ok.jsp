<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 한글처리는 제일 위에다가 하는 것이 좋다. request.setCharacterEncoding("utf-8"); : 한글처리 -->
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>test1Ok.jsp</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<p><br/></p>
<div class="container">
	<p> 성명 : <%= request.getParameter("name") %> </p>   <!-- request : 요청 response : 응답 -->
	<p> 나이 : <%= request.getParameter("age") %></p>  
	<hr/>
	<%
		String name = request.getParameter("name");
		int age =  Integer.parseInt(request.getParameter("age"));     /* 화면에 넘어오는 것은 문자형식이기 때문에 int로 바꿔주어야 한다. */
	
		out.println("성명2 : " + name + " : ");
		out.println("나이2 : " + age);
	%>
	<hr/>
	<p>성명3 : <%= name %></p>
	<p>나이3 : <%= age %></p>
	<p><a href="test1.jsp" class="btn btn-success">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>