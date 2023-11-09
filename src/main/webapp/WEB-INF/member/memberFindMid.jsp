<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>title</title>
	<%@ include file="../../include/bs4.jsp" %>
	<style>
		body {
			padding: 0px;
		}
		#stl{
			margin: 0 auto;
			border: 1px solid gray;
			width: 600px;
			padding: 10px;
			text-align:center;
							
		}
	</style>
	<script>
		'use strict'
		
		let str = "";
		function midS(){
			let email = myform.email1.value.trim();
			/* alert(email); */
			
			if(email1 == ""){
				alert("이메일을 입력해주세요.")
				myform.email1.focus();
				return;
			}
			else {
				$.ajax({
					url : "${ctp}/memberFindMidOk.mem",
					type : "post",
					data : {email : email},
					success : function(res){
						if(res == "0"){
							alert("이메일을 다시 확인해주세요.");
						}
						else {
							let resArr = res.split("/");
							str += "<hr/>";
							str += "<div style='text-align:center;  border: 1px solid gray; padding:10px'";
							str += "<hr/>";
							str += "<h2 class='text-center'>검색 결과</h2><br/>";
							for(let r of resArr){
								let re = "";
								for(let i=0; i<r.length; i++){
									if((i+1)%3==0) re += "*";
									else re += r.charAt(i);
								}
								str += "<b>" + re + "</b><br/>";
							}
							str += "</div>"
							
							demo.innerHTML = str;
						}
					},
					error : function(){
						alert("전송오류~");
					}
				});
			}
			
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2 style="text-align:center;">아 이 디 찾 기</h2>
	<p><br/></p>
	<div class="container">
		<form name="myform">
			<div id="stl">
				<div><h3>회원 아이디찾기</h3></div>
				<hr/>
				<div style="text-align:left">이메일</div>
				<div class="input-group">
					<input type="email" name="email1" id="email1" autofocus required class="form-control"/>
				</div>
					<hr/>
				<div>
					<input type="button" name="btn1" onclick="midS()" value="아이디찾기" class="btn btn-success"/>
					<input type="reset" value="다시작성" class="btn btn-warning"/>
					<input type="button" value="돌아가기" onclick="location.href='memberLogin.mem'" class="btn btn-info"/>
				</div>
			</div>
		</form>
		<div id="demo"></div>
	</div>
	<p><br/></p>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>