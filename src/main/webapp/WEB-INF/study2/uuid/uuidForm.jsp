<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>uuidForm.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		
		let str = ""
		function uuidCheck() {
			$.ajax ({
				url : "${ctp}/uuidProcess",
				type : "post",
				success : function(res){
					//demo.innerHTML +=  res + '<br/>'+ demo.innerHTML;
					str += res + '<br/>';
					demo.innerHTML = str;
				},
				error : function(){
					alert("전송오류~");
				}
			});
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2>UUID란</h2>
	<p>
		UUID(Univerally Unique Identifier) 네트워크상에서 고유성이 보장되는 ID를 만들기 위한 규약<br/>
		128Bit로, 16진수 32자로 표현된다. <br/>
		예) 1242345-afs1-2345-3dfa-s23034568321
	</p>
	<div>
		<form name="myform">
			<input type="button" value="UUID생성" onclick="uuidCheck()" class="btn btn-success"/>
			<input type="button" value="다시하기" onclick="location.reload()" class="btn btn-warning"/>
		</form>
	</div>
	<hr/>
	출력결과<br/>
	<div id="demo"></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>