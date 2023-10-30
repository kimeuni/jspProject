<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/bs4.jsp" %>
<%-- <%@ include file=" <%=request.getContextPath()%>/include/bs4.jsp" %> --%>  <!-- (사용불가: %안에는 %를 다시 사용할 수 없음) -->
<%-- <jsp:include page="<%=request.getContextPath()%>/include/bs4.jsp"></jsp:include> --%>  <!-- jsp액션 태그 -->
<!-- <div class="container"> -->
<div style="height: 180px; text-aling: center;">
	<p><br/></p>
	<a href="home.jsp" class="btn btn-outline-primary">Home</a> |
	<a href="guest.jsp" class="btn btn-outline-primary">Guset</a> |
	<a href="board.jsp" class="btn btn-outline-primary">Board</a> |
	<a href="pds.jsp" class="btn btn-outline-primary">Pds</a>
</div>