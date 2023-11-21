<%@page import="member.MemberChatVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("utf-8");
	MemberDAO dao = new MemberDAO();
	ArrayList<MemberChatVO> vos = dao.getMemberMassage();
	pageContext.setAttribute("vos", vos); //EL로 사용하기 위해...
	// 여기다 채팅 알람 N를 할 update DAO를 적어주면 채팅창에 들어와있을 때 알람이 안뜨도록 설정할 수 있음..?
%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>memberMessage.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		
		setTimeout("location.reload()",1000*2); //현재는 2초마다 refresh 한다.
		
		/* $(document).ready(function() {
			
		}); */
		$(function(){
			document.body.scrollIntoView(false); //스크롤바를 강제로 body태그 마지막으로 보낸다.
		});
		
		
	</script>
</head>
<body> 
	<div style="background-color:#eee"> 
		<c:forEach var="vo" items="${vos}" varStatus="st">    
		<div style="width: 100%; heigh:100%; line-height:100%;">   
			<c:if test="${vo.nickName == sNickName}"><div class="text-right" style="background-color:#eee; color:blue; border:1px solid red; max-width:250px; float:right; display: inline-block; margin:2px; padding:5px 0px;">${vo.nickName} : ${vo.chat}</div></c:if>
			<c:if test="${vo.nickName != sNickName}"><div class="text-left" style="float:left">${vo.nickName} : ${vo.chat}<br/></div></c:if>
		</div>   
		<div style="clear:both;"></div>
		</c:forEach>
	</div>
</body>
</html>