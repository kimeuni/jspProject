<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>memberPwdCheck.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		
		// demo가 나오지 않도록 숨김..
		$(function() {
			$("#demo").hide();
		});
		function pwdReCheck(){
			let pwd= document.myform.pwd.value.trim();
			
			if(pwd == ""){
				alert("비밀번호를 입력하세요.");
				myform.pwd.focus();
				return;
			}
			
			$.ajax({
				url : "memberPwdChange.mem",
				type : "post",
				data : {pwd:pwd},
				success : function(res){
					if(res == "1"){
						$("#demo").show();
						return;
					}
					else{
						alert("현재 비밀번호를 다시 확인해주세요.")
						$("#demo").hide();
					}
				},
				error : function(){
					alert("전송오류");					
				}
			});
		}
		
		function pwdCheck(){
			let pwd1= document.cform.pwd1.value.trim();
			let pwd2= document.cform.pwd2.value.trim();
			
			if(pwd1 == ""){
				alert("변경할 비밀번호를 입력하세요.");
				cform.pwd1.focus();
				return;
			}
			else if(pwd2 == ""){
				alert("확인 비밀번호를 입력하세요.");
				cform.pwd2.focus();
				return;
			}
			else if(pwd1 != pwd2){
				alert("변경할 비밀변호화 일치하지 않습니다.")
				cform.pwd1.focus();
				return;
			}
			let query = {
				pwd1 : pwd1,
				pwd2 : pwd2
			}
			
			$.ajax({
				url : "memberPwdChangeOk.mem",
				type : "post",
				data : query,
				success : function(res){
					if(res == "1"){
						alert("비밀번호가 정상적으로 수정되었습니다.")
						location.reload();
					}
					else{
						alert("비밀번호 변경에 실패하였습니다.")
					}
				},
				error : function(){
					alert("전송오류");					
				}
			});
			
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<form name="myform" method="post" action="memberPwdCheckOk.mem">
		<table class="table table-bordered " style="width:800px; margin:0 auto;">
			<tr>
				<th colspan="2" class="text-center">
					<h3>비밀번호 확인</h3>
					<div>(회원 정보 수정을 하기위해 기존의 비밀번호를 확인합니다.)</div>
				</th>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd" id="pwd" value="1234" required class="form-control" /></td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<input type="button" onclick="pwdReCheck()" value="비밀번호 변경" class="btn btn-success mr-3"/>
					<input type="submit" value="회원정보 변경" class="btn btn-primary mr-3"/>
					<input type="reset" value="다시입력" class="btn btn-warning mr-3"/>
					<input type="button" value="돌아가기" onclick="location.href='memberMain.mem';" class="btn btn-info "/>
				</td>
			</tr>
		</table>
	</form>
	<div id="demo">
		<form name="cform">
		<hr/>
		<div> 변경할 비밀번호
			<input type="password" name="pwd1" id="pwd1" class="form-control" />
		</div>
		<div> 비밀번호 확인
			<input type="password" name="pwd2" id="pwd2" class="form-control" />
		</div>
		<div style="margin-top:10px;">
			<input type="button" onclick="pwdCheck()" value="비밀번호 변경" class="btn btn-success"/>
		</div>
		</form>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>