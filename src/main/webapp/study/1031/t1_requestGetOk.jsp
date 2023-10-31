<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_requestGetOk.jsp -->
<%
	request.setCharacterEncoding("utf-8");
	
	String[] hobbys = request.getParameterValues("hobby");
	
	String hobby = "";
	for(String h : hobbys){
		hobby += h + "/";
	}
	hobby = hobby.substring(0,hobby.length()-1);
%>
<p> 전송된 값 </p>
<p>성명 : <%= request.getParameter("name") %></p>
<p>취미 : <%= hobby %></p>
<p>호스트Ip1 : <%= request.getParameter("hostIp") %> </p> 
<p>호스트Ip2 : <%= request.getRemoteAddr() %> </p> <!-- hidden으로 넘기지 않아도 똑같이 뜨지만.. hidden으로 넘기면 DB에 저장이 가능하여 hidden으로 ip주소를 넘기는게 좋다. (누가 접속했는지 확인 가능) -->
<p>전송방식 : <%= request.getMethod() %></p>
<p>접속 프로토콜 : <%= request.getProtocol() %></p>
<p>접속 서버 : <%= request.getServerName() %></p>  <!-- 서버 이름을 따로 주지 않으면 ip가 뜬다. -->
<p>접속 서버포트 : <%= request.getServerPort() %></p>
<p>접속 Context명 : <%= request.getContextPath() %></p>
<p>접속 URL : <%= request.getRequestURL() %></p>
<p>접속 URI : <%= request.getRequestURI() %></p>  <!-- 좀 더 큰 의미가 URI -->
<p></p>
<p></p>
<p><input type="button" value="돌아가기" onclick="history.back()"/></p>