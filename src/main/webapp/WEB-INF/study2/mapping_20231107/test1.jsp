<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>test1.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		function test4() {
			/*
			let su1 = document.getElementById("su1").value;
			let su1 = myform.su1.value;
			let su2 = document.getElementById("su2").value;
			
			location.href="${ctp}/mapping/test4?su1="+su1+"&su2="+su2
			*/
			
			//myform.submit();
			
			// form태그에 액션태그를 사용하지 못할 상황이라면? 자바스크립트에서 action 명령으로 이동할 곳의 경로를 지정해중다.
			myform.action = "${ctp}/mapping/test4";
			myform.submit();
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container" style="height:600px; overflow:auto">
	<h2>URL 매핑(Directory Mapping)패턴</h2>
	<p>이곳은 test1.jsp입니다.</p>
	<hr/>
	<img src="${ctp}/images/1.jpg" width="400px"/>
	<hr/>
	<div>
		<input type="button" value="test2.jsp" onclick="location.href='${ctp}/mapping/test2';" class="btn btn-success m-3"/>
		<input type="button" value="test3.jsp" onclick="location.href='${ctp}/mapping/test3';" class="btn btn-primary m-3"/>
		<input type="button" value="test4.jsp" onclick="test4()" class="btn btn-info m-3"/>
	</div>
	<hr/>
	<div>
		<div>
			<form name="myform" method="post">
			<input type="number" name="su1" id="su1" value='<c:if test="${empty su1}">0</c:if><c:if test="${!empty su1}">${su1}</c:if>' class="form-control m-3"/>
			<!-- EL 표기법으로 3항연산자를 이용하여 su1에 사용한 내용을 줄일 수 있다. -->
			<input type="number" name="su2" id="su2" value="${empty su2 ? 0 : su2}" class="form-control m-3"/>
			</form>
		</div>
		<div>두수의 합 : ${hap}</div>
		<div>두수의 차 : ${cha}</div>
	</div>
	<hr/>
	<div>
		<pre>
			test1.jsp/test2.jsp/test3.jsp 파일에 접속시키고 해당 파일이 맞는지 화면에 파일명을 출력하시오.
			test4.jsp 클릭시 test1.jsp 화면에 입력된 su1, su2의 내용에 해당하는 '합/차'를 구해서 test1.jsp에 출력시키시오.
			(단, test4컨트롤러는 이동지시만 하고, 계산은 Test4Service객체를 사용하시오.)
		</pre>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>