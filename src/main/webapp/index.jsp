<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 이런식으로 하면 컨트롤러를 탈 수 있기 때문에 DB에 있는 데이터를 가져와서 사용할 수 있다. -->


<!--  방법 1. script로 main.ad를 만들어 컨트롤러에 넣어서 부른다. (adminController에 넣음/ 실무사용x)
<script>
	location.href="main.ad";
</script>
 -->
 
 <!-- 방법2. jsp:forward를 통해서간다 (보통은 태그 안에서 사용하는 방법이기 때문에 실무사용x)
 <jsp:forward page="main.ad"/>
  -->
 <%
 	//response.sendRedirect("main.ad");
 
 
 	// 가장 깔끔한 방법
 	pageContext.forward("main.ad");
 %>