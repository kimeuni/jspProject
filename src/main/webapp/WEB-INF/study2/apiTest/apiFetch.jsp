<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>apiFetch.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		function fetchCheck1(){
			//체이닝 기법 사용
			fetch("https://infuser.odcloud.kr/oas/docs?namespace=15084592/v1") 
			.then(function(res) {
				return res.json();  
				//여기있는 json은 명령어
			})
			//여기 있는 json은 변수명
			.then(function(json) {  
				console.log(json)
			});
		}
		
		function fetchCheck2(){
			fetch("https://jsonplaceholder.typicode.com/posts") 
			.then(function(res) {
				return res.json();  
				//여기있는 json은 명령어
			})
			//여기 있는 json은 변수명
			.then(function(json) {  
				console.log(json)
			});
		};
		
		// 화살표 함수를 사용해서 적음
		function fetchCheck3(){
			fetch("https://jsonplaceholder.typicode.com/posts") 
			.then(res => res.json())
			.then(json => console.log(json));
		};
		
		//map으로 바꾸기
		function fetchCheck4(){
			// await : 앞에 있는 작업이 끝날때까지 기다려달라는 명령어(동기식으로 바꿔줌) (fetch는 비동기식이기 때문에.. 비동기식은 데이터가 다 넘어올 때까지 기다리지 않아 에러가 떠서 이걸 사용해야 한다(?))
			let res = await fetch("https://jsonplaceholder.typicode.com/posts") 
			.then(res => res.json());
			.then(json => console.log(json));
			
			/* console.log(res);
			
			let str = res.data.map((item,i)=> [
				(i+1) + "."
				+ "유저아이디 : " + item.userId
				+ "제목" + item.title
				+ "<br/>"
			]); */
			$("#demo").html(str);
		};
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2>Fetch API 연습</h2>
	<pre>
		Fetch : 자바스크립트를 이용한 비동기식 요청을 사용할 수 있게 하는 API
		fetch는 주로 Web의 API를 호출하고 응답데이터를 받아올 수 있게 한다.
		방식 : fetch(URL)
	</pre>
	<h4>Fetch를 사용한 JSON자료 호출</h4>
	<input type="button" value="fetch연습1" onclick="fetchCheck1()" class="btn btn-success mr-3"/>
	<input type="button" value="fetch연습2" onclick="fetchCheck2()" class="btn btn-primary mr-3"/>
	<input type="button" value="fetch연습3" onclick="fetchCheck3()" class="btn btn-primary"/>
	<input type="button" value="fetch연습4" onclick="fetchCheck4()" class="btn btn-primary"/>
	<input type="button" value="돌아가기" onclick="location.href='api1.st'" class="btn btn-primary"/>
</div>
<hr/>
<div id="demo"></div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>