<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_SessionSave.jsp -->
<%
	// 자바코드를 사용하고 있기 때문에 <서블릿>에서도 똑같이 사용 가능 but session이나 cookie를 import해서 사용해야함 (jsp에서는 내장객체기 때문에 improt안해도 됨)
	// 세션값 저장하기.... session.setAttribute("세션변수",값)
	
	//save에서 글자가 깨짐...
	request.setCharacterEncoding("utf-8");
	
	String mid = request.getParameter("mid")== null ? "guest" : request.getParameter("mid");
	String nickName = request.getParameter("nickName")== null ? "손님" : request.getParameter("nickName");
	String name = request.getParameter("name")== null ? "방문자" : request.getParameter("name");
	
	session.setAttribute("sMid", mid);  //우리끼리의 약속 sesstion의 변수이기 때문에 앞에 s를 붙임.. (어떤 변수인지 헷갈리기 때문)
	session.setAttribute("sNickName", nickName);
	session.setAttribute("sName", name);
%>
<script>
	alert("세션이 저장되었습니다.")
	location.href="t2_SessionMain.jsp";
</script>