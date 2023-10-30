<!-- test2Ok.jsp -->
<!-- test2Ok 는 백엔드쪽을 처리하기 위해 만들었다.
	백앤드는 넘어온 값을 여기서 체크, 데이터베이스에 넣어놓는다..
	
	프론트는 단지
	alert("회원 가입완료!!");  를 화면에 출력시키고..
	
	location.href="test2.jsp";  바로 다시 test2.jsp 화면으로 돌아간다.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 한글처리는 제일 위에다가 하는 것이 좋다. request.setCharacterEncoding("utf-8"); : 한글처리 -->
<%
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	int age =  Integer.parseInt(request.getParameter("age"));     /* 화면에 넘어오는 것은 문자형식이기 때문에 int로 바꿔주어야 한다. */
	String flag = request.getParameter("flag");
	
	System.out.println("성명 :" + name);
	System.out.println("나이 :" + age);
	System.out.println("flag :" + flag);
%>
	<hr/>
	<p>성명3 : <%= name %></p>
	<p>나이3 : <%= age %></p>
	<p>나이3 : <%= flag %></p>
	<p><a href="test2.jsp">돌아가기</a></p>

	<script>
		alert("회원 가입완료!!");
		location.href="test2.jsp";
	</script>