<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>t2_SessionMain.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<style>
		div {margin:10px}
	</style>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>세션연습 메인메뉴</h2>
	<hr/>
	<form name="myform" method="post" action="t2_SessionSave.jsp">
		<div> 아이디 :
			<input type="text" name="mid" value="${sMid}" autofocus required class="form-control"/>
		</div>
		<div> 닉네임 :
			<input type="text" name="nickName" value="${sNickName}" required class="form-control"/>
		</div>
		<div> 성명 :
			<input type="text" name="name" value="${sName}" required class="form-control"/>
		</div>
		<div>세션 ID : <%= session.getId() %></div>
		<hr/>
		<div class="row">
			<div class="col"><button type="submit" class="btn btn-success form-control">세션 저장</button></div>
			<div class="col"><a href="t2_SessionNameCheck.jsp" class="btn btn-primary form-control">세션명 확인</a></div>
			<div class="col"><a href="t2_SessionCheck.jsp" class="btn btn-primary form-control">세션값 확인</a></div>
			<div class="col"><a href="t2_SessionDelete.jsp" class="btn btn-info form-control">전체 세션 삭제</a></div>
			<div class="col"><a href="t2_SessionMidDelete.jsp" class="btn btn-warning form-control">아이디 세션 삭제</a></div>
		</div>
	</form>
</div>
<p><br/></p>
</body>
</html>