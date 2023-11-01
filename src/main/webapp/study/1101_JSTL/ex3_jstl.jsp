<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ex3_jstl.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<style>
		input {margin:10px;}
		
		#gugu {
			display: inline-block;
			border: 1px solid gray;
			width: 150px;
			text-align:center;
			margin:2px;
			padding:3px
		}
	</style>
	<script>
		'use strict'
		
		/* 7줄이 넘어가면 화면에 맞지 않아 자동으로 내려가서 8줄부터는 적을 수 없도록 함. */
		if(${param.su >=8}){
			alert("8줄 이상 넣을 수 없습니다.");
			location.href="ex3_jstl.jsp";
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h3>구구단 출력 숙제(한줄에 출력될 개수를 입력 받아 구구단 출력)</h3>
	<form>
		<div>시작단 : <input type="number" name="startDan" value="2" autofocus class="form-control"/></div>
		<div>끝단 : <input type="number" name="endDan" value="8" class="form-control"/></div>
		<div>한줄에 출력될 개수 : <input type="number" name="su" value="3" class="form-control"/></div>
		<div><input type="submit" value="계산" class="btn btn-success"/></div>
	</form>
	<hr/>
	<div id="demo">
		<!-- submit 버튼을 눌렀을 때 param에 값이 바뀔때마다 값을 읽어와서 저장. -->
		<c:set var="sDan" value="${param.startDan}"></c:set>
		<c:set var="eDan" value="${param.endDan}"></c:set>
		<c:set var="suDan" value="${param.su}"></c:set>

		<!-- 시작단과 끝단이 공백 or null 값이 아니면 실행 -->
		<c:if test="${!empty sDan && !empty eDan}">
			<!-- 만약 시작단이 끝단보다 크면 값을 바꿈 -->
			<c:if test="${sDan+0 > eDan+0}">
				<c:set var="sDan" value="${param.endDan}"/>
				<c:set var="eDan" value="${param.startDan}"/>
			</c:if>
			<!-- 구구단 출력 (이중for문) -->
			<c:forEach var="i" begin="${sDan}" end="${eDan}" varStatus="st">
				<div id="gugu">* ${i} 단 * <br/> 
				<c:forEach var="j" begin="1" end="9">
					${i} * ${j} = ${i * j}<br/>
				</c:forEach>
				</div>
				<!-- 만약 count의 수가 suDan과 나눴을 때 0이 나온다면 한줄 내린다. -->
				<c:if test="${st.count % suDan == 0}"><br/></c:if>
			</c:forEach>
		</c:if>
	</div>
	
</div>
<p><br/></p>
</body>
</html>