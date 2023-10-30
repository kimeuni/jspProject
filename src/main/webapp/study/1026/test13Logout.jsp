<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- test13Logout.jsp -->
<%
	String name = request.getParameter("name"); /* 이렇게만 적으면 꺼내기만 하고 저장소에 저장되어있지 않아서 출력이 안된 것. */
	pageContext.setAttribute("name", name);   
	/* 꼭 pageContext를 적어야 하는 건 아님.. */
%>

<script>
	alert("${name}님 로그아웃 되었습니다.(MVC패턴)")
	/* location.href = "test13.jsp"; */  /* 현재는 같은 곳에 있기 때문에 이렇게 적어도 됨. */
	<%-- location.href = "<%= request.getContextPath() %>/study/1026/test13.jsp"; --%>  
	/* 헷갈리면 전부 적기 */
	
	location.href = "${pageContext.request.contextPath}/study/1026/test13.jsp";  
	
	/* ${pageContext.request.contextPath} : < % = request.getContextPath() %> 를 EL표기법으로 바꾼 것. */
</script>