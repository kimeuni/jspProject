<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ex5_jstl.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function nameCheck() {
    	let name = myform.name.value;
    	if(name.trim() == "") {
    		alert("검색할 성명을 입력하세요");
    		myform.name.focus();
    	}
    	else {
    		//myform.submit();
    		location.href = "${ctp}/j1101/ex5NameOk?name="+name;
    	}
    }
    
    function genderCheck() {
    	let gender = myform.gender.value;
    	location.href = "${ctp}/j1101/ex5GenderOk?gender="+gender;
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>회원 자료 출력하기</h2>
  <form name="myform" method="post" action="${ctp}/j1101/jstl5Ok">
  	<input type="submit" value="회원전체리스트" class="btn btn-success" />
  	<hr/>
  	검색할 회원명을 입력하세요?
  	<input type="text" name="name" class="form-control" />
  	<input type="button" value="개별회원검색" onclick="nameCheck()" class="btn btn-primary" />
  	<hr/>
  	검색할 성별을 입력하세요?
  	<input type="radio" name="gender" value="남자" checked/>남자
  	<input type="radio" name="gender" value="여자" />여자
  	<input type="button" value="성별검색" onclick="genderCheck()" class="btn btn-info" />
  </form>
</div>
<p><br/></p>
</body>
</html>