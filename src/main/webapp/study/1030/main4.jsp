<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- main, nav2,guest2,board2,pds2 파일 한세트  // main이 실행파일 -->
<%
	String flag = request.getParameter("flag")==null? "" : request.getParameter("flag");

	// ${param.mid} 이런식으로 적으면 아래에 있는 거 안만들어도 사용이 가능하다...
/* 	String mid = request.getParameter("mid")==null? "" : request.getParameter("mid");
	String mSw = request.getParameter("mSw")==null? "" : request.getParameter("mSw");
	String nick = request.getParameter("nick")==null? "" : request.getParameter("nick");
	pageContext.setAttribute("mSw", mSw);   이걸 적어줘야 EL표기법으로 값을 같을 수 있다.
	pageContext.setAttribute("mid", mid);  
	pageContext.setAttribute("nick", nick);  */
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>main4.jsp</title>
	<jsp:include page="/include/bs4.jsp"/> <!-- jsp액션태그의 처음의 '/'은 ContextPath의 root명으로 간주된다. -->
	<script>
		'use strict'
		
		let viewPage = "";
		if('${param.mSw}' == 'No'|| ${empty param.nick}){   
			alert("회원정보를 확인해 보시고 다시 로그인 하세요.");
			location.href = "login4.jsp";
		}  // empty : null or 공백
		else if('${param.mSw}' == 'Ok') {
			alert("${param.nick}회원님~ 방문을 환영합니다.");
		}
	</script>
</head>
<body>
<div class="container">
	<!-- 헤더영역(로그/메뉴/Top Image 등등... -->
	<div id="header" class="text-center" style="background-color: skyblue">
		<%@ include file="nav4.jsp" %>   <!-- 같은 경로에 있기 때문에 nav.jsp라고만 적어도 된다 -->
		<p class="text-right">${param.nick}님 로그인 중이십니다.</p>
	</div>
	<p><br/></p>
	<div class="container" style="text-align:center">
	<% if(flag.equals("guest")){ %>
		<jsp:include page="guest2.jsp"></jsp:include>
	<% } else if(flag.equals("board")){ %>
		<jsp:include page="board2.jsp"></jsp:include>
	<% } else if(flag.equals("pds")){ %>
		<jsp:include page="pds2.jsp"></jsp:include>
	<% } else{ %>
		<h2>이곳은 메인 화면(Home)입니다.</h2>
		<hr/>
		<p><img src= "../../images/1.png" width="600px"/></p>
		<hr/>
	<%} %>
	</div>
	<p><br/></p>
	<!-- 푸터영역(Copyright/Email Address/사업자등록증.. Imag 등등... -->
	<div id="footer" class="text-center">
		<%@ include file="footer.jsp" %>   <!-- 같은 경로에 있기 때문에 nav.jsp라고만 적어도 된다 -->
	</div>
</div>  
</body>
</html>