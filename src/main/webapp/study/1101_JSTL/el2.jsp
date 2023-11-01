<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>el2.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>폼을 통해서 넘어온 자료의 출력</h2>
	<p>
		성명 : ${name}<br/>
		취미 : ${hobby}<br/>
		취미들 : ${hobbys}<br/>
		취미1/2/3 : ${hobbys[0]}/${hobbys[1]}/${hobbys[2]}/${hobbys[3]}<br/> <!-- EL은 값이 없어도 바운더리익섹션 에러가 나오지 않는다.. -->
	</p>
	<p><a href="${pageContext.request.contextPath}/study/1101_JSTL/el2_form.jsp" class="btn btn-success">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>