<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>schedule.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<!-- 폰트어썸 -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
	<style>
		/*달력의 제일 왼쪽은 일요일로 정하여 글자색을 빨간색으로 출력되도록 한다.*/
		#td1, #td8, #td15, #td22, #td29, #td36{
			color: red;
		} 
		/* 달력의 제일 오른쪽은 토요일로 정하여 글자색을 파란색으로 출력되도록 한다. */ 
		#td7, #td14, #td21, #td28, #td35{
			color : blue;
		}
		
		/* 오늘 날짜를 표시하기 위함 */		
		.today{
			background-color:pink;
			color: #fff;
			font-weight : bolder;
			text-align:left; 
		}
	</style>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<div class="text-center">
		<button type="button" onclick="location.href='schedule.sc?yy=${yy-1}&mm=${mm}'" class="btn btn-secondary btn-sm" title="이전년도">◁◁</button>
		<button type="button" onclick="location.href='schedule.sc?yy=${yy}&mm=${mm-1}'" class="btn btn-secondary btn-sm" title="이전달">◁</button>
		<font size="5">${yy}년 ${mm+1}월</font>
		<button type="button" onclick="location.href='schedule.sc?yy=${yy}&mm=${mm+1}'" class="btn btn-secondary btn-sm" title="다음달">▷</button>
		<button type="button" onclick="location.href='schedule.sc?yy=${yy+1}&mm=${mm}'" class="btn btn-secondary btn-sm" title="다음년도">▷▷</button>
		<button type="button" onclick="location.href='schedule.sc';" class="btn btn-secondary btn-sm" title="홈"><i class='fas fa-home' style='font-size:24px'></i></button>
	</div>
	<br/>
	<div class="text-center">
		<table class="table table-bordered" style="height:450px">
			<tr class="table-dark text-dark">
				<th style="width:14%; vertical-align:middle; color:red">일</th>
				<th style="width:14%; vertical-align:middle;">월</th>
				<th style="width:14%; vertical-align:middle;">화</th>
				<th style="width:14%; vertical-align:middle;">수</th>
				<th style="width:14%; vertical-align:middle;">목</th>
				<th style="width:14%; vertical-align:middle;">금</th>
				<th style="width:14%; vertical-align:middle; color:blue">토</th>
			</tr>	
			<tr>
				<!-- 시작일 이전을 공백을 이전달의 날짜로 채워준다. -->
				<c:forEach var="prevDay" begin="${prevLastDay-(startWeek-2) }" end="${prevLastDay}" varStatus="st">
				<td style="font-size:0.6em; color:#ccc; text-align:left;">${prevYear}-${prevMonth+1}-${prevDay}</td>
				</c:forEach>
				<!-- 해당월의 1일을 해당 startWeek위치부터 출력(날짜는 1씩 증가시켜주고, 7칸이 될때 행을 변경처리한다. -->
				<c:set var="cell" value="${startWeek}"/>
				<c:forEach begin="1" end="${lastDay}" varStatus="st">
					<!-- 스윗칭 기법을 통하여 오늘날짜를 찾아 style을 준 class를 넣어준다. -->
					<c:set var="todaySw" value="${toYear == yy && toMonth == mm && toDay == st.count ? 1 : 0}"/>
					<td id="td${cell}" ${todaySw == 1 ? 'class=today' : '' } class="text-left">
						<c:set var="ymd" value="${yy}-${mm+1}-${st.count}"/>
						<a href="scheduleMenu.sc?ymd=${ymd}">${st.count}</a><br/> <!-- 클릭하는 해당 날짜에대한 년월일을 가지고 간다. -->
						<!-- 해당날짜에 일정이 있다면 part를 출력한다. -->
						<c:forEach var="vo" items="${vos}">
							<c:if test="${fn:substring(vo.sDate,8,10)== st.count}"> <!-- 날짜 -->
								<%-- ${vo.part} <br/> --%>
								<!-- 모아서 출력하는 거 해보기... 분류가 같은 날짜에 기타,기타,모임 이렇게 있으면 기타(2),모임 이런식으로 출력되도록 -->
								<c:if test="${fn:length(vo.part)>1}">${vo.part}(${fn:length(vo.part)})<br/></c:if>
								<c:if test="${fn:length(vo.part)<1}">${vo.part}<br/></c:if>
							</c:if>
						</c:forEach>
					</td>
					<c:if test="${cell % 7 ==0 }"></tr><tr></c:if>
					<c:set var="cell" value="${cell + 1 }" />
				</c:forEach>
				<!--  마지막일 이후를 다음달의 시작일자부터 채워준다. -->
				<!-- cell-1을 적은 이류는 위에서 (67번라인) cell+1을 하고 나왔기 때문이다. -->
				<c:if test="${cell-1 % 7 !=0}">
					<c:forEach var="nextDay" begin="${nextStartWeek }" end="7" varStatus="st">
						<td style="font-size:0.6em; color:#ccc; text-align:left;">${nextYear}-${nextMonth+1}-${st.count}</td>
					</c:forEach>
				</c:if>
			</tr>	
		</table>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>