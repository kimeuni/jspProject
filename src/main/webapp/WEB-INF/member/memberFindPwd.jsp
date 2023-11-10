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
		
		function pwdS(){
			let str = "";
			let mid = myform.mid.value.trim();
			let email = myform.email.value.trim();
			
			if(mid == ""){
				alert("아이디를 입력해주세요.")
				myform.mid.focus();
				return;
			}
			else if(email == ""){
				alert("이메일을 입력해주세요.")
				myform.email.focus();
				return;
			}
			else {
				let query = {
					mid : mid,
					email : email
				}
				$.ajax({
					url : "${ctp}/memberFindPwdOk.mem",
					type : "post",
					data : query,
					success : function(res){
						if(res == "0"){
							alert("아이디 혹은 비밀번호를 다시 확인해주세요.")
						}
						else {
							str += "<hr/>";
							str += "임시 비밀번호 : <b>" + res + "</b>"; 
							
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
	<h2 style="text-align:center;">비 밀 번 호 찾 기</h2>
	<p><br/></p>
	<div class="container">
		<form name="myform">
			<div id="stl">
				<div><h3>회원 비밀번호찾기</h3></div>
				<hr/>
				<div style="text-align:left">아이디</div>
				<div >
					<input type="text" name="mid" id="mid" autofocus required class="form-control"/>
				</div>
				<div style="text-align:left">이메일</div>
				<div >
					<input type="email" name="email" id="email" required class="form-control"/>
				</div>
					<hr/>
				<div>
					<input type="button" name="btn1" onclick="pwdS()" value="비밀번호찾기" class="btn btn-success"/>
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