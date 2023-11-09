<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int level = session.getAttribute("sLevel")== null ? 99 : (int)session.getAttribute("sLevel");
	pageContext.setAttribute("level", level);
%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="http://192.168.50.61:9090/javaProject">Home</a>
  <!-- 햄버거 버튼 -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="${ctp}/GuestList">Guset</a>
      </li>
      <c:if test="${level <= 4}"> <!-- 회원일 경우만 -->
	      <li class="nav-item">
	        <a class="nav-link" href="${ctp}/BoardList">Board</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${ctp}/PdsList">Pds</a>
	      </li>    
	      <li class="nav-item ml mr-2">
	        <div class="dropdown">
			    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">Study1</button>
			    <div class="dropdown-menu">
			      <a class="dropdown-item" href="${ctp}/study/password/passForm.jsp">암호화연습</a>
			      <a class="dropdown-item" href="${ctp}/mapping/test1">디렉토리패턴</a>
			      <a class="dropdown-item" href="${ctp}/mapping/test5.do">확장자패턴1</a>
			      <a class="dropdown-item" href="${ctp}/mapping/test5.re">확장자패턴2</a>
			      <a class="dropdown-item" href="${ctp}/login/login.lo">로그인 연습</a>
			      <a class="dropdown-item" href="ajaxTest1.st">AJax 연습1</a>
			      <a class="dropdown-item" href="userList.us">AJax 연습2</a>
			      <a class="dropdown-item" href="${ctp}/uuidProcess.st">UUID 연습</a>
			    </div>
			  </div>
	      </li>
      </c:if>
      <li class="nav-item">
        <c:if test="${level > 4}"><a class="nav-link" href="memberLogin.mem">Login</a></c:if> <!-- 회원이 아니면 or 로그인이 안되어있을시 -->
        <c:if test="${level <= 4}"><a class="nav-link" href="memberLogoutOk.mem">Logout</a></c:if> <!-- 회원일시, 로그인 되어있을시 -->
      </li>        
      <li class="nav-item">
        <c:if test="${level > 4}"><a class="nav-link" href="memberJoin.mem">Join</a></c:if>
      </li>        
    </ul>
  </div>  
</nav>