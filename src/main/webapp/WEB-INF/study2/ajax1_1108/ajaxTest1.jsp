<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>title</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		// 일반 아이디 검색 (ajax 아직 사용x)
		function idCheck() {
			/* let mid = document.myform.value;
			let mid = myform.value;
			let mid = document.getElementById("mid").value; */
			let mid = $("#mid").val();  // 제이쿼리 방식
			
			if(mid.trim()== ""){
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			location.href = "ajaxTest1Ok.st?mid="+mid;
		}
		
		// ajax로 아이디 검색1(문자열 처리)
		function idCheck1() {
			let mid = $("#mid").val();  // 제이쿼리 방식
			
			if(mid.trim()== ""){
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			// 주소는 디렉토리 패턴을 사용하기 때문에 url을 적을 때 $ {ctp}/...~~  로 절대정로를 적어줘야 한다
			// 비동기식으로 서버에 다녀온다.
			$.ajax({
				url  : "${ctp}/ajaxTest1",
				type : "get",
				data : {mid:mid},  // 서버에서받는 변수명 : 클라이언트값을 담은 변수
				contextType : "application/json", //기본값이랑 생략가능
				charset : "utf-8", //기본값이랑 생략가능
				success : function (res) { //성공했을 때 어떻게 할래?    // res는 담는 그릇(변수)
					//alert("서버에 성공적으로 다녀왔습니다. ==> " + res);
					/* demo.innerHTML = res; */
					$("#demo").html(res);
					
					// success : function (res) {} 안에서는 콜백함수이기 때문에 자바스크립트 문법을 사용한다 
				},
				error : function(){  //전송이 잘못되었을 때
					alert("전송 오류~~~");
				
					// error : function () {} 안에서는 콜백함수이기 때문에 자바스크립트 문법을 사용한다 
				}
			});
		}
		
		// 아이디 체크2 (문자열 처리)
		function idCheck2() {
			let mid = $("#mid").val();  // 제이쿼리 방식
			
			if(mid.trim()== ""){
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			// data에 넣을 변수가 많을 시, 이런식으로 위에 빼서 변수에 넣은 후 처리하면 된다.
			let query = {
				mid : mid
			}
			
			$.ajax({
				url : "${ctp}/ajaxTest2",
				type : "post",
				data : query,
				success : function(res){
					$("#demo").html(res);
					let str = res.split("/");  // "/"를 기준으로 잘라낸다.  잘라낸 값을 배열로 들어오기 때문에 밑에처럼 작성한다..
					$("#tMid").html(str[0]);
					$("#name").html(str[1]);
					$("#point").html(str[2]);
					$("#todayCount").html(str[3]);
					
				},
				error : function(){
					alert("전송오류!")
				}
			});
		}
		
		// 아이디 검색3(문자열 처리 / aJax는 객체로 값을 넘길 수 없다.)
		function idCheck3(){
			let mid = $("#mid").val(); 
			
			if(mid.trim()== ""){
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			let query = {
				mid : mid
			}
			$.ajax ({
				url : "${ctp}/ajaxTest3",
				type : "post",
				data : query,
				success : function (res){
					$("#demo").html(res);
					
					$("#tMid").html(res.substring(res.indexOf("mid=")+4,res.indexOf(",",res.indexOf("mid="))));
					$("#name").html(res.substring(res.indexOf("name=")+5,res.indexOf(",",res.indexOf("name="))));
					$("#point").html(res.substring(res.indexOf("point=")+6,res.indexOf(",",res.indexOf("point="))));
					$("#todayCount").html(res.substring(res.indexOf("todayCount=")+11, res.indexOf("]")));
				},
				error : function (){
					alert("전송오류")
				}
			});
		}
		function idCheck4() {
			let mid = $("#mid").val(); 
			
			if(mid.trim()== ""){
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			$.ajax({
				url : "${ctp}/ajaxTest4",
				type : "post",
				data : {mid,mid},
				success : function (res){
					console.log("res : " +res);
					$("#demo").html(res);
					
					//js코드로 파싱 (자바스크립트에서 했음.. 이전거 확인해보기...)
					let js = JSON.parse(res);  //자바스크립트 형식으로 바꿈 (자바스크립트의 객체형식)
					console.log("js : " , js) 
					
					$("#tMid").html(js.mid);
					$("#name").html(js.name);
					$("#point").html(js.point);
					$("#todayCount").html(js.todayCount);
				},
				error : function() {
					alert("전송 오류!");
				}
			});
		}
			
			// 아이디체크5 (vos형식으로 처리... (JSON으로 처리하는 것이지만 vos처럼 여러개 값이 넘어올 때 처리하는 방식))
		function idCheck5() {
			let mid = $("#mid").val(); 
			
			if(mid.trim()== ""){
				alert("아이디를 입력하세요.");
				$("#mid").focus();
				return false;
			}
			$.ajax({
				url : "${ctp}/ajaxTest5",
				type : "post",
				data : {mid : mid},
				success : function (res) {
					$("#demo").html(res);
					let js = JSON.parse(res);
					console.log("js : ", js);
					
					// 향상된 for문 사용 
					let tMid = "",name="",point="",todayCount="";
					for(let j of js ){
						tMid += j.mid + "/";
						name += j.name + "/";
						point += j.point + "/";
						todayCount += j.todayCount + "/";
					}
					$("#tMid").html(tMid);
					$("#name").html(name);
					$("#point").html(point);
					$("#todayCount").html(todayCount);
					//값 출력하는거는 table로 해서 출력해도 된다...
				},
				error : function () {
					alert("전송오류")
				}
			});
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2>AJax 연습</h2>
	<hr/>
	<form name="myform">
		아이디: 
		<div class="input-group">
			<input type="text" name="mid" id="mid" class="form-control"/>
			<div class="input-grup-append"><input type="button" value="아이디일반검색" onclick="idCheck()"  class="btn btn-secondary"/></div>
		</div>
		<hr/>
		aJax검색 : <br/>
		<div>  <!-- ajax는 button으로 처리해야 한다? -->
			<input type="button" value="아이디검색1" onclick="idCheck1()" class="btn btn-success"/>
			<input type="button" value="아이디검색2" onclick="idCheck2()" class="btn btn-primary"/>
			<input type="button" value="아이디검색3" onclick="idCheck3()" class="btn btn-primary"/>
			<input type="button" value="아이디검색4" onclick="idCheck4()" class="btn btn-warning"/>
			<input type="button" value="아이디검색5" onclick="idCheck5()" class="btn btn-warning"/>
		</div>
		<hr/>
		<div>출력결과 : <font color="red"><b><span id="demo"></span></b></font></div>
		<hr/>
		<div>
			<div>아이디 : <font color="blue"><b><span id="tMid"></span></b></font></div>
			<div>성명 : <font color="blue"><b><span id="name"></span></b></font></div>
			<div>포인트 : <font color="blue"><b><span id="point"></span></b></font></div>
			<div>오늘 방문카운트 : <font color="blue"><b><span id="todayCount"></span></b></font></div>
		</div>
	</form>
	<hr/>
	<div><a href="javascript:location.href='${ctp}/';" class="btn btn-success">돌아가기</a></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>