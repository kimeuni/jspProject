<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>apiTest.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		
		const API_KEY = 'NsvYWH%2FFmKRBpFFbTtpddD9wX8fiiwrv7nMM0dmOvAbvJ4ycycKmrSjzFwizZMlEOa900usEcGHjdtXe5Ovkiw%3D%3D';
		
		// await를 사용할 때 async를 무조건 사용해야 한다.
		async function getCrimeDate(){
			/*  주소 오류
			let apiYear = "/15084592/v1/uddi:5e08264d-acb3-4842-b494-b08f318aa14c";
			
			let url = "https://api.odcloud.kr/api/";
			url += apiYear;
			url += "?serviceKey="+API_KEY;   //serviceKey는 예약어..
			url += "&page=1&perPage=200"; 
			*/
			
			// select 를 선택했을 시, 값을 가져올 수 있도록 해서 년도별로 볼 수 있도록 만든다.
			let year = document.getElementById("year").value;
			let apiYear = "";
			
			if(year == 2015){
				apiYear = "/15084592/v1/uddi:fbbfd36d-d528-4c8e-aa9b-d5cdbdeec669";
			}
			else if(year == 2016){
				apiYear = "/15084592/v1/uddi:21ec6fa1-a033-413b-b049-8433f5b446ff";
			}
			else if(year == 2017){
				apiYear = "/15084592/v1/uddi:67117bd9-5ee1-4e07-ae4a-bfe0861ee116";
			}
			else if(year == 2018){
				apiYear = "/15084592/v1/uddi:2d687e27-b5c3-4bdb-9b77-c644dcafcbc7";
			}
			else if(year == 2019){
				apiYear = "/15084592/v1/uddi:b6cc7731-181b-48e1-9a6c-ae81388e46b0";
			}
			else if(year == 2020){
				apiYear = "/15084592/v1/uddi:b6cc7731-181b-48e1-9a6c-ae81388e46b0";
			}
			else if(year == 2021){
				apiYear = "/15084592/v1/uddi:943e757d-462b-4b3a-ab9f-9a8553637ca2";
			}
			else if(year == 2022){
				apiYear = "/15084592/v1/uddi:5e08264d-acb3-4842-b494-b08f318aa14c";
			}
	    	
	    	let url = "https://api.odcloud.kr/api";
	    	url += apiYear;
	    	url += "?serviceKey="+API_KEY;
	    	url += "&page=1&perPage=200";
			
			let response = await fetch(url);

			let res = await response.json();
			console.log("response :", response);
			
			let str = res.data.map((item,i) => [
				(i+1) + "."
				+ "발생년도 : " + item.발생년도 + "년"
				+ ",경찰서 : " + item.경찰서
				+ ",강도 : " + item.강도 
				+ ",살인 : " + item.살인 
				+ ",절도 : " + item.절도 
				+ ",폭력 : " + item.폭력
				+ "<br/>"
			]);
			$("#demo").html(str);
		}
		async function saveCrimeDate(){
			// select 를 선택했을 시, 값을 가져올 수 있도록 해서 년도별로 볼 수 있도록 만든다.
			let year = document.getElementById("year").value;
			let apiYear = "";
			
			if(year == 2015){
				apiYear = "/15084592/v1/uddi:fbbfd36d-d528-4c8e-aa9b-d5cdbdeec669";
			}
			else if(year == 2016){
				apiYear = "/15084592/v1/uddi:21ec6fa1-a033-413b-b049-8433f5b446ff";
			}
			else if(year == 2017){
				apiYear = "/15084592/v1/uddi:67117bd9-5ee1-4e07-ae4a-bfe0861ee116";
			}
			else if(year == 2018){
				apiYear = "/15084592/v1/uddi:2d687e27-b5c3-4bdb-9b77-c644dcafcbc7";
			}
			else if(year == 2019){
				apiYear = "/15084592/v1/uddi:b6cc7731-181b-48e1-9a6c-ae81388e46b0";
			}
			else if(year == 2020){
				apiYear = "/15084592/v1/uddi:b6cc7731-181b-48e1-9a6c-ae81388e46b0";
			}
			else if(year == 2021){
				apiYear = "/15084592/v1/uddi:943e757d-462b-4b3a-ab9f-9a8553637ca2";
			}
			else if(year == 2022){
				apiYear = "/15084592/v1/uddi:5e08264d-acb3-4842-b494-b08f318aa14c";
			}
	    	
	    	let url = "https://api.odcloud.kr/api";
	    	url += apiYear;
	    	url += "?serviceKey="+API_KEY;
	    	url += "&page=1&perPage=200";
			
			let response = await fetch(url);

			let res = await response.json();
			console.log("response :", response);
			
			let str = res.data.map((item,i) => [
				(i+1) + "."
				+ "발생년도 : " + item.발생년도 + "년"
				+ ",경찰서 : " + item.경찰서
				+ ",강도 : " + item.강도 
				+ ",살인 : " + item.살인 
				+ ",절도 : " + item.절도 
				+ ",폭력 : " + item.폭력
				+ "<br/>"
			]);
			$("#demo").html(str);
			
			// 화면에 출력된 자료들을 모두 DB에 저장시켜준다.
			let query = "";
			for(let i=0; i<res.data.length; i++){
				//null이 아닌 정상적인 자료가 들어가도록 null체크를 해준다
				if(res.data[i].경찰서 != null){
					query = {
						year : year,
						police : res.data[i].경찰서,
						robbery : res.data[i].강도,
						murder : res.data[i].살인,
						theft : res.data[i].절도,
						violence : res.data[i].폭력
					}
				}
				//for문 안에서 ajax를 실행해서 1세트씩 데이터베이스에 저장 가능
				// success 를 만들어서 alert를 만들면 들어가는 데이터만큼 메세기자 뜨기 때문에 for문 밖에 넣어서 한번만 실행되도록 함. (현재 들어가는 데이터는 200개 이기 때문에 200번 메세지가 뜸...)
				$.ajax({
					url : "saveCrimeData.st",
					type : "post",
					data : query,
					error : function(){
						alert("전송오류")
					}
				});
			}
			alert(year+"년도 자료가 DB에 저장되었습니다.")
		}
		
		function deleteCrimeDate(){
			let year = document.getElementById("year").value;
			let ans = confirm(year + "년도의 자료를 삭제하시겠습니까?");
			if(!ans) return false;
			
			$.ajax({
				url : "deleteCrimeData.st",
				type : "post",
				data : {year:year},
				success : function(res){
					if(res == "1"){
						alert(year + "년도 자료 삭제 완료");
						location.reload();
					}
					else alert(res);
				},
				error : function(){
					alert("전송오류");
				}
			});
		}
		
		// DB의 자료 출력하기 (전체자료중에서 합계,평균)
		function listCrimeDate(){
			myform.action = "listCrimeDate.st";
			myform.submit();
		}
		// DB의 경찰서 지역별 자료 출력하기
		function policeCheck(){
			// submit으로 값이 넘어가기 때문에 값을 let으로 선언해서 넘기지 않아도 된다.
			myform.action = "policeCheck.st";
			myform.submit();
		}
		// 경찰서별 + 년도별..DB 자료 출력
		function yearPoliceCheck(){
			myform.action = "yearPoliceCheck.st";
			myform.submit();
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2>경찰청 전국 경찰서별 강력범죄 발생 현황</h2>
	<hr/>
	<form name="myform" method="post">
		<div>
			<select name="year" id="year">
				<c:forEach var="i" begin="2015" end="2022">
					<option value="${i}" ${year== i ? 'selected': '' }>${i}년도</option>
				</c:forEach>
			</select>
			<input type="button" value="강력범죄자조회" onclick="getCrimeDate()" class="btn btn-success"/>
			<input type="button" value="범죄자 DB저장" onclick="saveCrimeDate()" class="btn btn-primary"/>
			<input type="button" value="범죄자 DB삭제" onclick="deleteCrimeDate()" class="btn btn-danger"/>
			<input type="button" value="범죄자 DB출력" onclick="listCrimeDate()" class="btn btn-info"/>
		</div>
		<div class="mt-3"> 
			경찰서 지역명 : 
			<select name="police" id="police" onchange="policeCheck()">
				<option ${police=='서울'? 'selected' : '' }>서울</option>
				<option ${police=='경기'? 'selected' : '' }>경기</option>
				<option ${police=='강원'? 'selected' : '' }>강원</option>
				<option ${police=='충북'? 'selected' : '' }>충북</option>
				<option ${police=='충남'? 'selected' : '' }>충남</option>
				<option ${police=='전북'? 'selected' : '' }>전북</option>
				<option ${police=='전남'? 'selected' : '' }>전남</option>
				<option ${police=='경북'? 'selected' : '' }>경북</option>
				<option ${police=='경남'? 'selected' : '' }>경남</option>
				<option ${police=='제주'? 'selected' : '' }>제주</option>
			</select> &nbsp;&nbsp;
			: 정렬순서 : 
			<input type="radio" name="yearOrder" value="a" checked/>오름사순
			<input type="radio" name="yearOrder" value="d"/>내림차순 :
			<input type="button" value="년도/경찰서별 출력" onclick="yearPoliceCheck()" class="btn btn-secondary"/>
		</div>
	</form>
	<hr/>
	<div id="demo"></div>
	<hr/>
	<!-- 공백이 아니면 출력(자료가 있으면 출력) -->
	<c:if test="${!empty vos}">
		<h3>범죄 분석 통계(<font color="red"><b>합계</b></font>)</h3>
		<table class="table table-hover">
			<tr class="table-dark text-dark">
				<th>구분</th>
				<th>년도</th>
				<th>강도</th>
				<th>살인</th>
				<th>절도</th>
				<th>폭력</th>
			</tr>
			<tr>
				<td>총계</td>
				<td>${analyzeVo.year}</td>
				<td>${analyzeVo.totRobbery}</td>
				<td>${analyzeVo.totMurder}</td>
				<td>${analyzeVo.totTheft}</td>
				<td>${analyzeVo.totViolence}</td>
			</tr>
		</table>
		<hr/>
		<h3>범죄 분석 통계(<font color="red"><b>평균</b></font>)</h3>
		<table class="table table-hover">
			<tr class="table-dark text-dark">
				<th>구분</th>
				<th>년도</th>
				<th>강도</th>
				<th>살인</th>
				<th>절도</th>
				<th>폭력</th>
			</tr>
			<tr>
				<td>총계</td>
				<td>${analyzeVo.year}</td>
				<td>${analyzeVo.avgRobbery}</td>
				<td>${analyzeVo.avgMurder}</td>
				<td>${analyzeVo.avgTheft}</td>
				<td>${analyzeVo.avgViolence}</td>
			</tr>
		</table>
		<hr/>
		<h3><font color="blue"><b>${year}</b></font>년 범죄 통계 현황(총 : <font color="blue"><b>${fn:length(vos)}</b></font>건</h3>
		<c:if test="${!empty vos}">
			경찰서별 : 
		</c:if>
		<table class="table table-hover">
			<tr class="table-dark text-dark">
				<th>구분</th>
				<th>년도</th>
				<th>경찰서</th>
				<th>강도</th>
				<th>살인</th>
				<th>절도</th>
				<th>폭력</th>
			</tr>
			<c:forEach var="vo" items="${vos}" varStatus="st">
				<tr>
					<td>${st.count}</td>
					<td>${vo.year}</td>
					<td>${vo.police}</td>
					<td>${vo.robbery}</td>
					<td>${vo.murder}</td>
					<td>${vo.theft}</td>
					<td>${vo.violence}</td>
				</tr>
			</c:forEach>
			<tr><td colspan="7" class="m-0 p-0"></td></tr>
		</table>
	</c:if>	
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>