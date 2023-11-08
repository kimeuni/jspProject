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
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<form name="loginForm" method="post" action="loginOk.lo">
		<table class="table talbe-bordered">
			<tr>
				<th colspan="2" class="text-center">회원 로그인</th>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="mid" id="mid" value="<%= cMid %>" autofocus required class="form-control" />
					<input type="checkbox" name="idCheck" value="save" checked >ID저장
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
					<%-- <input type="button" value="회원가입" onclick="location.href='${ctp}/login/join.lo';" class="btn btn-info "/> --%>
					<input type="button" value="회원가입" onclick="location.href='join.lo';" class="btn btn-info "/>  
					<!-- $ {ctp}/join.lo 이런식으로 안적고 그냥 join.lo만 적어도 된다... 하지만 이렇게 적으려면 /를 적어면 안됨! 왜냐하면 주소를 읽어올때(url을 보면) 제일마지막에 /가 자동으로 붙기 때문에 여기에 /join.lo로 적으면 url에는 //join.lo로 나오기 때문이다. -->
					<!-- /를 적고 싶을 경우에는 $ {ctp}/join.lo이렇게 적어야 한다. -->
					<!-- 확장자 패턴일 경우만 가능(디렉토리 패턴은 $ {ctp}/join.lo로 적어줘야함) 이렇게 적을 수 있는건 LoginController.java에서 substring으로 잘라냈기 때문임 -->
				</th>
			</tr>
		</table>
	</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>