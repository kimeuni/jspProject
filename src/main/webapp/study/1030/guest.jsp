<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>guedt.jsp</title>
	<%@ include file="../../include/bs4.jsp" %>
	
</head>
<body>
<!-- 헤더영역(로그/메뉴/Top Image 등등... -->
<div id="header" class="text-center" style="background-color: skyblue">
	<%@ include file="nav.jsp" %>   <!-- 같은 경로에 있기 때문에 nav.jsp라고만 적어도 된다 -->
</div>
<p><br/></p>
<div class="container" style="text-align:center">
	<h2>이곳은 방명록(Guest)입니다.</h2>
	<hr/>
	<p><img src= "../../images/2.jpg" width="600px"/></p>
	<hr/>
</div>
<p><br/></p>
<!-- 푸터영역(Copyright/Email Address/사업자등록증.. Imag 등등... -->
<div id="footer" class="text-center">
	<%@ include file="footer.jsp" %>   <!-- 같은 경로에 있기 때문에 nav.jsp라고만 적어도 된다 -->
</div>
</body>
</html>