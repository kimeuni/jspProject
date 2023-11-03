<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<%
	String cMid = "";
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(int i=0; i<cookies.length; i++){
			if(cookies[i].getName().equals("cMid")){
				cMid = cookies[i].getValue();
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>login.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
</head>
<body>
<p><br/></p>
<div class="container">
	<form name="loginForm" method="post" action="${ctp}/database/loginOk">
		<table class="table talbe-bordered">
			<tr>
				<th colspan="2" class="text-center">회원 로그인</th>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="mid" id="mid" value="<%= cMid %>" autofocus required class="form-control" />
					<input type="checkbox" name="idCheck" value="save">ID저장
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd" id="pwd" value="1234" required class="form-control" /></td>
			</tr>
			<tr>
				<th colspan="2" class="text-center">
					<input type="submit" value="로그인" class="btn btn-success mr-3"/>
					<input type="reset" value="다시입력" class="btn btn-warning "/>
					<input type="button" value="회원가입" onclick="location.href='join.jsp';" class="btn btn-info "/>
				</th>
			</tr>
		</table>
	</form>
</div>
<p><br/></p>
</body>
</html>