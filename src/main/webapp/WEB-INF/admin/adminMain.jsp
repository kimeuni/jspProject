<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>adminMain.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<frameset rows="150, *">
		<frame src="adminHeader.ad" name="adminHeader" frameborder="0"/>
		<frameset cols="130px, *">
			<frame src="adminLeft.ad" name="adminLeft" frameborder="0"/>
			<frame src="adminContent.ad" name="adminContent" frameborder="0"/>
		</frameset>
	</frameset>
</head>
</html>