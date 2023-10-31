<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_SessionNameCheck.jsp -->
<jsp:include page="/include/bs4.jsp"></jsp:include>
<p><br/></p>
<div class="container">
<%
	Enumeration names = session.getAttributeNames();  // 객체형 배열  //여기 들어가있는 자료는 열겨형으로 들어가 있음.
	
	// 자료가 있을 때 까지 가져와야하기 때문에 while문을 사용
	while(names.hasMoreElements()){
		String sName = (String)names.nextElement();
		out.println("세션명 : " + sName + "<br/>");
	}
%>
<hr/>
<p><a href="t2_SessionMain.jsp" class="btn btn-success">돌아가기</a></p>
</div>