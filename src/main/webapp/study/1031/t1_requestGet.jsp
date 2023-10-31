<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>t1_requestGet.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
</head>
<body>
<p><br/></p>
<div class="container">
	<form name="myform" method="post" action="t1_requestGetOk.jsp">
	<h2>전송된 값과 환경을 서버에서 확인해오기</h2>
		<div class="border p-3">
			<div> 성명 : 
				<input type="text" name="name" id="name" value="홍길동" class="form-control mb-3" autofocus required/>
			</div>
			<div class="mb-3"> 취미 : 
				<input type="checkbox" name="hobby" id="hobby" value="등산" checked />등산
				<input type="checkbox" name="hobby" id="hobby" value="낚시" />낚시
				<input type="checkbox" name="hobby" id="hobby" value="바둑" />바둑
				<input type="checkbox" name="hobby" id="hobby" value="싸이클" />싸이클
				<input type="checkbox" name="hobby" id="hobby" value="수영" />수영
				<input type="checkbox" name="hobby" id="hobby" value="독서" />독서
			</div>
			<div class="text-center mb-3"> 
				<input type="submit" value="전송" class="btn btn-success"/>
				<input type="reset" value="취소" class="btn btn-warning"/>
			</div>
		</div>
		<input type="hidden" name="hostIp" value="<%= request.getRemoteAddr() %>"/> <!-- request.getRemoteAddr() : 내 컴퓨터(접속한 컴퓨터)의 ip주소 보내기 -->
	</form>
</div>
<p><br/></p>
</body>
</html>