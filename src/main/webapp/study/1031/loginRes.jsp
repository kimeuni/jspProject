<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- loginRes.jsp -->
<%
	String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
	String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
%>
<jsp:include page="/include/bs4.jsp"/>
<div class="container">
	<h2>회원 정보(loginRes.jsp)</h2>
	<p>아이디 : <%= mid %></p>
	<p>비밀번호 : <%= pwd %></p>
	<p><a href="login.jsp" class="btn btn-success">돌아가기</a></p>
</div>