<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t3_ApplicationSave.jsp -->
<%
	//save에서 글자가 깨짐...
	request.setCharacterEncoding("utf-8");
	
	String mid = request.getParameter("mid")== null ? "guest" : request.getParameter("mid");
	String nickName = request.getParameter("nickName")== null ? "손님" : request.getParameter("nickName");
	String name = request.getParameter("name")== null ? "방문자" : request.getParameter("name");
	
	application.setAttribute("aMid", mid);  //우리끼리의 약속 sesstion의 변수이기 때문에 앞에 s를 붙임.. (어떤 변수인지 헷갈리기 때문)
	application.setAttribute("aNickName", nickName);
	application.setAttribute("aName", name);
%>
<script>
	alert("어플리케이션에 저장되었습니다.")
	location.href="t3_ApplicationMain.jsp";
</script>