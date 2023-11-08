<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>userList.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<style>
		th{
			text-align: center;
			background-color: #eee;
		}
	</style>
	<script>
		'use stript'
		
		// user 등록하기
		function userInput(){
			let mid = $("#mid").val().trim();
			let name = $("#name").val().trim();
			let age = $("#age").val().trim();
			let address = $("#address").val().trim();
			
			if(mid == "") {
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			else if(name == "") {
				alert("성명을 입력하세요.");
				$("#name").focus();
				return false;
			}
			else if(age == "") {
				alert("나이를 입력하세요.");
				$("#age").focus();
				return false;
			}
			else if(address == "") {
				alert("주소를 입력하세요.");
				$("#address").focus();
				return false;
			}
			else {
				let query = {
					mid : mid,
					name : name,
					age : age,
					address : address
				}
				$.ajax ({
					url : "${ctp}/userInput.us",  // form 태그의 action과 같은 역할
					type : "post",  // form태그의 method와 같은 역할
					data : query,
					success : function (res){
						if(res == "1"){
							alert("회원 등록되었습니다.");
							location.reload();  // 부분 reload()도 가능하다고 함.. (인터넷 찾아보기)
						}
						else {
							alert(res)
							$("#mid").focus();
						}
					},
					error : function () {
						alert("전송오류")
					}
				});
			}
		}
		
		// 아이디 개별검색
		function userSearch(idx){
			$.ajax({
				url : "${ctp}/userSearch.us",
				type : "post",
				data : {idx : idx},
				success : function (res) {
					let str = res.split("/");
					$("#idx").val(str[0]);
					$("#mid").val(str[1]);
					$("#name").val(str[2]);
					$("#age").val(str[3]);
					$("#address").val(str[4]);
					$("#mid").attr("readonly",true) // 개별검색을 눌렀을 시, 아이디는 수정되지 않도록 함.
				},
				error : function() {
					alert("전송오류~~~");
				}
			});
		}
		
		// 회원 정보 수정하기
		function userUpdate(){
			let mid = $("#mid").val().trim();
			let name = $("#name").val().trim();
			let age = $("#age").val().trim();
			let address = $("#address").val().trim();
			
			if(mid == "") {
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			else if(name == "") {
				alert("성명을 입력하세요.");
				$("#name").focus();
				return false;
			}
			else if(age == "") {
				alert("나이를 입력하세요.");
				$("#age").focus();
				return false;
			}
			else if(address == "") {
				alert("주소를 입력하세요.");
				$("#address").focus();
				return false;
			}
			else {
				let query = {
						mid : mid,
						name : name,
						age : age,
						address : address
					}
				$.ajax({
					url : "${ctp}/userUpdate.us",
					type : "post",
					data : query,
					success : function(res) {
						if(res == "1"){
							alert("수정처리 되었습니다.")
							location.reload();  // reload()(새로고침)을 해서 데이터베이스에 저장된 값이 화면에 바로 노출되도록 한다.
						}
						else {
							alert("수정처리가 실패 되었습니다.");
							$("#mid").focus();
						}
					},
					error : function (){
						alert("전송오류~~");
					}
				});
			}
		}
		
		function userDel(idx) {
			let ans = confirm("선택된 회원을 삭제처리 하시겠습니까?")
			
			if(ans){
				$.ajax({
					url : "${ctp}/userDel.us",
					type : "post",
					data : {idx:idx},
					success : function(res){
						if(res == "1"){
						alert("삭제처리 되었습니다.")
						location.reload();
						}
						else {
							alert("삭제처리에 실패했습니다.")
						}
					},
					error : function (){
						alert("전송 오류~");
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
	<h2>AJax를 이용한 CRUD 구현하기</h2>
	<form>
		<table class="table table-bordered">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="mid" id="mid" autofocus class="form-control"/></td>
			</tr>
			<tr>
				<th>성명</th>
				<td><input type="text" name="name" id="name" class="form-control"/></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" name="age" id="age" class="form-control"/></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address" id="address" class="form-control"/></td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<input type="button" value="user등록" onclick="userInput()" class="btn btn-success mr-2"/>
					<input type="reset" value="다시입력" class="btn btn-success mr-2"/>
					<input type="button" value="user수정" onclick="userUpdate()" class="btn btn-success mr-2"/>
					<input type="button" value="전체보기" onclick="location.href='userList.us'" class="btn btn-success mr-2"/>
				</td>
			</tr>
		</table>
		<input type="hidden" name="idx" id="idx" />
	</form>
	<hr/>
	<h2>User 전체 리스트</h2>
	<table class="table table-hover text-center">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>아이디</th>
			<th>성명</th>
			<th>나이</th>
			<th>주소</th>
			<th>비고</th>
		</tr>
		<c:forEach var="vo" items="${vos}" varStatus="st">
			<tr>
				<td>${vo.idx }</td>
				<td>${vo.mid }</td>
				<td>${vo.name }</td>
				<td>${vo.age }</td>
				<td>${vo.address }</td>
				<td>
					<a href="javascript:userSearch(${vo.idx})" class="btn btn-success btn-sm">개별조회</a>
					<a href="javascript:userDel(${vo.idx})" class="btn btn-danger btn-sm">삭제</a>
				</td>
			</tr>
		</c:forEach>
		<tr><td colspan="6" style="margin:0px; padding:0px;"></td></tr>
	</table>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>