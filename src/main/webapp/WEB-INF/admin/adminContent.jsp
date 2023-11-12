<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>adminContent.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<style>
	/* 글자 깜빡임 효과 */
		@keyframes blink-effect {
			50%{
				opacity:0;
			}
		}
		
		.blink{
			animation: blink-effect 1s step-end infinite;
		}
	</style>
</head>
<body>
<p><br/></p>
<div class="container">
	<h3>관리자 메인화면</h3>
	<hr/>
	<p>방명록 새글 : ??? </p>
	<p>게시글 새글 : ??? </p>
	<p>신규등록회원(준회원) :
	<c:if test="${junHoiwonCnt>1}"> <a href="adminMemberLevelSearch.ad?level=1">${junHoiwonCnt}명</a><span class="blink"><sup ><font color="red">new</font></sup></span></c:if>  <!-- 오늘 등록한 회원을 확인하는 법: 최초가입일이 오늘인사람..(이렇게 하면 만약 매일 들어오지 않으면 신규등록 회원을 볼 수 없다는 단점이 있음) or 가입만하면 레벨이 <준회원>으로 되어있는데 준회원인 사람은 신규등록회원으로 친다.. -->
	<c:if test="${junHoiwonCnt==0}"><a href="adminMemberLevelSearch.ad?level=1">${junHoiwonCnt}명</a></c:if>
	</p>
	
</div>
<p><br/></p>
</body>
</html>