<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_CookiesSave.jsp -->
<% 
	// 프로젝트당 저장이 가능하다.. (다른 프로젝트에서 만든 쿠키는 현재 프로젝트에서 삭제할 수 없음 ex)현재 프로젝트는 javaProject인데, zavaProject에서 만든 쿠키는 삭제할 수 없음(브라우저에는 다른 프로젝트에 저장되어있는 쿠키도 보임)
	String mid = "hkd1234";
	/* Cookie cookieMid = new Cookie("cMid", mid); */ //지금 상태로는 브라우저 키고 닫으면 쿠키가 사라짐  //Cookie("저장소", 값); 앞에가 저장소, 뒤에가 값.
	Cookie cookieMid = new Cookie("cMid", mid);
	cookieMid.setMaxAge(60*60*24); //쿠키의 만료시간(초) : 60*60*24 - 1일

	String pwd = "1234";
	Cookie cookiePwd = new Cookie("cPwd", pwd);
	/* cookiePwd.setMaxAge(60*60*24); */ //쿠키의 만료시간(초) : 60*60*24 - 1일
	cookiePwd.setMaxAge(60); //쿠키의 만료시간(초) : 60 - 1분

	String tel = "010-1234-5678";
	Cookie cookieTel = new Cookie("cTel", tel);
	cookieTel.setMaxAge(60*60*24); //쿠키의 만료시간(초) : 60*60*24 - 1일
	
	// 쿠키 저장
	response.addCookie(cookieMid);
	response.addCookie(cookiePwd);
	response.addCookie(cookieTel);
%>
<script>
	alert("쿠키가 저장되었습니다.")
	location.href="t1_CookiesMain.jsp";
</script>