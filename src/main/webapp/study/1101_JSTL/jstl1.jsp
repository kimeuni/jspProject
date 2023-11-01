<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>jstl1.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>JSTL(Java Standard Tag Library)</h2>
	<table class="table table-bordered">
		<tr>
			<th>라이브러리명</th>
			<th>주소(URI)</th>
			<th>접두어</th>
			<th>기본문법</th>
		</tr>
		<tr>
			<td>Core</td>
			<td>http://java.sun.com/jsp/jstl/core</td>
			<td>c</td>
			<td>< c : 태그명....></td>  <!-- jsp 액션태그 ex) < jsp:include...> 처럼 사용 --> 
		</tr>
		<tr>
			<td>Formatting</td>
			<td>http://java.sun.com/jsp/jstl/fmt</td>
			<td>fmt</td>
			<td>< fmt : 태그명....></td>  <!-- jsp 액션태그 ex) < jsp:include...> 처럼 사용 --> 
		</tr>
		<tr>
			<td>Function</td> <!-- 함수이기 때문에 독립적인 태그로 사용x EL안에서 사용한다. -->
			<td>http://java.sun.com/jsp/jstl/function</td>
			<td>fn</td>
			<td>$ {fn : 태그명....}</td>   
		</tr>
		<tr>
			<td>SQL</td>
			<td>http://java.sun.com/jsp/jstl/sql</td>
			<td>sql</td>
			<td>< sql : 태그명....></td>  <!-- jsp 액션태그 ex) < jsp:include...> 처럼 사용 --> 
		</tr>
	</table>
	<hr/>
	<p><b>★ 위의 라이브러리를 사용할 경우에는 상단에 jsp지시자(taglib)를 이용하여 먼저 선언 후 사용할 수 있다. ★</b></p>
	<hr/>
	<h3>Core라이브러리 : 변수제어(선언/값할당/출력/제거), 제어문(조건, 반복문)</h3>
	<pre>
		변수선언 : < c : set var="변수명" value="값"/>
		변수출력 : < c : out value="변수/값/수식" />  또는 EL로 사용... $ {변수}
		변수제거 : < c : remove var="변수명" />
	</pre>
	<hr/>
	<p>사용 예</p>
	su1변수를 선언후 초기값으로 100을 할당? <c:set var="su1" value="100"/><br/> <!-- 엄청 많이 사용한다고 함~~  기억하기~ -->
	su1변수의 값을 출력? <c:out value="100+200"></c:out> / ${su1}<br/>  <!-- c:out은 있는 그대로의 출력(연산x) -->
	su1변수의 수식을 출력? <c:out value="${100+200}"></c:out> / ${100+200}<br/>  <!-- 연산을 하기 위해서는 EL로 묶어주면 된다. $ {100+200} -->
	스크립틀릿 출력? <c:out value='<%= "아톰" %>'></c:out><br/>  <!-- value='< % = "아톰" %> : 이런식으로 사용할 일은 없다고 함(?) -->
	<hr/>
	<h3>JSTL 제어문(core라이브러리와 EL을 함께 활용)</h3>
	<p>사용법 : < c : if test="$ {조건식 }"> 조건식에 처리할 내용 < / c : if></p>  <!-- 예는 </ c : if>를 꼭 적어서 닫아줘야한다. -->
	<div>단점1 : jspl의 숫자비교는 문자형식으로 비교한다. (해결:숫자형문자변수 + 0) </div>
	<div>단점2 : else문장이 없다.(베타적으로 비교한다. 또는 다중조건비교를 수행할 수 있다.) </div>
	
	<p>사용 예(su3=300, su4=400)</p>
	<c:set var="su3" value="100"></c:set>
	<c:set var="su4" value="20"></c:set>
	<div>su3 : ${su3} / su4 ${su4}</div>
	<div>1. su3 == su4 : <c:if test="${su3 == su4}">su3와 su4는 같다.</c:if> </div>
	<div>2. su3 != su4 : <c:if test="${su3 != su4}">su3와 su4는 다르다.</c:if> </div>
	<!-- 이렇게 비교하면 숫자비교가 아닌, 문자비교가 된다. -->
	<div>3. su3 > su4 : <c:if test="${su3 > su4}">su3는 su4보다 크다.</c:if> </div> 
	<div>4. su3 < su4 : <c:if test="${su3 < su4}">su3는 su4보다 작다.</c:if> </div>
	<!-- 숫자형문자변수 + 0 을 넣으면 숫자로 비교한다. -->
	<div>5. su3 > su4 : <c:if test="${su3+0 > su4+0}">su3는 su4보다 크다.</c:if> </div> 
	<div>6. su3 < su4 : <c:if test="${su3+0 < su4+0}">su3는 su4보다 작다.</c:if> </div>
	<div>7. su3 > su4 : <c:if test="${su3+0 >= su4+0}">su3는 su4보다 크거나 같다.</c:if> </div> 
	<div>8. su3 < su4 : <c:if test="${su3+0 <= su4+0}">su3는 su4보다 작거나 같다.</c:if> </div>
	<div>9. su3 gt su4 : <c:if test="${su3+0 gt su4+0}">su3는 su4보다 크다.</c:if> </div> 
	<div>10. su3 lt su4 : <c:if test="${su3+0 lt su4+0}">su3는 su4보다 작다.</c:if> </div>
	<div>9. su3 ge su4 : <c:if test="${su3+0 ge su4+0}">su3는 su4보다 크거나 같다.</c:if> </div> 
	<div>10. su3 le su4 : <c:if test="${su3+0 le su4+0}">su3는 su4보다 작거나 같다.</c:if> </div>
	
	<div>예제 : URL에 jumsu를 입력받아서 학점을 구하시오?</div>
	<div> <!-- 이걸 자유롭게 만들줄 알아야 함. -->
		<c:set var="jum" value="${param.jumsu}"/>
		<c:if test="${jum+0 < 60}"><c:set var="grade" value="F"/></c:if>  
		<c:if test="${jum+0 >= 60}"><c:set var="grade" value="D"/></c:if>  
		<c:if test="${jum+0 >= 70}"><c:set var="grade" value="C"/></c:if>  
		<c:if test="${jum+0 >= 80}"><c:set var="grade" value="B"/></c:if>  
		<c:if test="${jum+0 >= 90}"><c:set var="grade" value="A"/></c:if><!-- 값을 변수에 담음 -->
		
		<%-- <c:if test="${jum+0 >= 90}"><c:set var="grade" value="A"/></c:if>  <!-- 값을 변수에 담음 -->
		<c:if test="${jum+0 >= 80}"><c:set var="grade" value="B"/></c:if>  
		<c:if test="${jum+0 >= 70}"><c:set var="grade" value="C"/></c:if>  
		<c:if test="${jum+0 >= 60}"><c:set var="grade" value="D"/></c:if>  
		<c:if test="${jum+0 < 60}"><c:set var="grade" value="F"/></c:if> --%>  
		<!-- else if가 없기 때문에 if문을 다 수행해서 ?jumsu=90 으로 값을 넘겨도 제일 아래 있는 60점(학점D)까지 수행해서 A가 아닌 D가 결과같으로 나온다. 그렇기 때문에 역순으로 적어준다.  -->
		학점은 : ${grade}
	</div>
	<h3>다중조건비교 : choose ~ when</h3>
	<pre>
		사용법 : 
		< c : choose >
			< c : when test="$ {조건식1 }">수행할 내용1< / c : when>
			< c : when test="$ {조건식2 }">수행할 내용2< / c : when>
			< c : when test="$ {조건식3 }">수행할 내용3< / c : when>
			< c : when test="$ {조건식4 }">수행할 내용4< / c : when>
			~~~
			< c : otherwise > 기타 수행할 내용< / c : otherwise>
		< / c : choose >
	</pre>
	<div>학점은?
		<c:choose>
			<c:when test="${jum >= 90}">A</c:when>
			<c:when test="${jum >= 80}">B</c:when>
			<c:when test="${jum >= 70}">C</c:when>
			<c:when test="${jum >= 60}">D</c:when>
			<c:otherwise>F</c:otherwise>
		</c:choose>
	</div>
</div>
<p><br/></p>
</body>
</html>