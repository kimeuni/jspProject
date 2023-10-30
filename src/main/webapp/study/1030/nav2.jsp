<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- nav2.jsp -->
<%@ include file="../../include/bs4.jsp" %>
<%-- <%@ include file=" <%=request.getContextPath()%>/include/bs4.jsp" %> --%>  <!-- (사용불가: %안에는 %를 다시 사용할 수 없음) -->
<%-- <jsp:include page="<%=request.getContextPath()%>/include/bs4.jsp"></jsp:include> --%>  <!-- jsp액션 태그 -->
<!-- <div class="container"> -->
<script>
	function logoutCheck(){
		let ans = confirm("로그아웃 하시겠습니까?");
		
		if(ans){
			location.href = "${pageContext.request.contextPath}/j1030/logoutOk?mid=${mid}";
		}
	}
</script>

<div style="height: 180px; text-aling: center;">
	<p><br/></p>
	<a href="main.jsp?mid=${mid}" class="btn btn-outline-primary">Home</a> |
	<a href="main.jsp?flag=guest&mid=${mid}" class="btn btn-outline-primary">Guset</a> |
	<a href="main.jsp?flag=board&mid=${mid}" class="btn btn-outline-primary">Board</a> |
	<a href="main.jsp?flag=pds&mid=${mid}" class="btn btn-outline-primary">Pds</a> |
	<a href="javascript: logoutCheck()" class="btn btn-outline-warning">Logout</a>
</div>
