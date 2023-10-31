<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- loginOk.jsp -->
<%
	String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
	String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
	String flag = mid.substring(0,1).toUpperCase(); // 아이디에 맨 앞글자가 I , C , 그외가 오는 것을 확인하여 I는 인사과화면, C는 총무과 화면, 그외는 Res화면으로 보낸다.
	//이런걸로 응용해서? 보완코드?도 할 수 있다고 함...
%>
<jsp:include page="/include/bs4.jsp"/>
<div class="container">
	<h2>회원 정보(loginOk.jsp)</h2>
	<p>아이디 : <%= mid %></p>
	<p>비밀번호 : <%= pwd %></p>
	<p><a href="login.jsp" class="btn btn-success">돌아가기</a></p>


<% if(flag.equals("I")){ %>
	<!-- 이거 사용하는거임! 알아두기! 서블릿에서 하기 귀찮은 간단간단?한거 이렇게 넘김..~? -->
	<jsp:forward page="loginResI.jsp">
		<jsp:param value="<%= mid %>" name="mid"/>
		<jsp:param value="<%= pwd %>" name="pwd"/>
	</jsp:forward> <!-- dispatcher forwad와 같은 거임!/page= 종착지 위치 -->
<%} else if(flag.equals("C")){ %>
	<jsp:forward page="loginResC.jsp">
		<jsp:param value="<%= mid %>" name="mid"/>
		<jsp:param value="<%= pwd %>" name="pwd"/>
	</jsp:forward> 
<%} else{ %>
	<jsp:forward page="loginRes.jsp">
		<jsp:param value="<%= mid %>" name="mid"/>
		<jsp:param value="<%= pwd %>" name="pwd"/>
	</jsp:forward> 
<%} %>
	
</div>