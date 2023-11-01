<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>jspl7.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>Format 라이브러리(형식을 지정...)</h2>	
	<pre>
		사용용도 : 형식문자열을 지정할때 사용함(쉼표, 화폐단뒤, 백분율...)
		사용법 : < fmt : 명령어... value="$ {값,변수}" pattern="표현패턴" type="화폐단위"/>
	</pre>
	<c:set var="won1" value="7654321"/>
	<c:set var="won2" value="7654.321"/>
	<div>
		won1: ${won1}<br/>
		won2: ${won2}<br/><br/>
		1-1 천단위 쉼표?<br/>
		<fmt:formatNumber value="1234567"/> <br/>
		<fmt:formatNumber value="${won1}"/> <br/>
		<fmt:formatNumber value="${won2}"/>  <br/>
		<fmt:formatNumber value="${won2}" pattern="0,000"/>  <br/> <!-- pattern="0,000": 정수부까지만 보겠다. -->
		<fmt:formatNumber value="${won2}" pattern="0,000.0"/>  <br/> <!-- pattern="0,000.0": 소수이하 한자리까지 -->
		<fmt:formatNumber value="${1234.567}" pattern="0,000.0"/>  <br/> <!-- 반올림도 가능... -->
		<fmt:formatNumber value="${1234.567}" pattern="0,000,000.0"/>  <br/>  <!-- pattern="0,000,000.0": 자릿수 확보.. -->
		<fmt:formatNumber value="${1234.567}" pattern="#,##0.0"/>  <br/>  <!-- # : 의미가 없는 0은 표시하지 않겠다. -->
		<fmt:formatNumber value="${34.567}" pattern="#,##0.0"/>  <br/>  <!-- # : 의미가 없는 0은 표시하지 않겠다. -->
		<hr/>
		2.화폐단위<br/>
		원화 : <fmt:formatNumber value="${won1}" type="currency"/><br/> <!-- 한국어로 되어있어서 앞에 자동으로 원화표시가 옴.. -->
		US 달러 : <fmt:formatNumber value="${won1}" type="currency" currencyCode="USD"/><br/> <!-- 한국어로 되어있어서 앞에 자동으로 원화표시가 옴.. -->
		
		3. 백분율 <br/>
		0.98765 : <fmt:formatNumber value="0.98765" type="percent"/><br/> <!-- 무조건 100을 곱한 값이 나옴 -->
		0.98765 : <fmt:formatNumber value="0.98765" type="percent" pattern="0.0%"/><br/> <!-- 무조건 100을 곱한 값이 나옴 -->
		<hr/>
		
		4. 날짜 <br/>
		오늘 날짜1 : <%= new Date() %> <br/>
		<c:set var="today" value="<%= new Date() %>"/><br/>
		오늘 날짜2 : ${today} / <fmt:formatDate value="${today}"/> <br/>	
		오늘 날짜3 : <fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/> <br/>	
		오늘 시간4 : <fmt:formatDate value="${today}" pattern="hh : mm : ss"/> <br/>	
		오늘 시간5 : <fmt:formatDate value="${today}" pattern="yyyy년MM월dd일 hh시 mm분 ss초"/> <br/>	
	</div>
</div>
<p><br/></p>
</body>
</html>