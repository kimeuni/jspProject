<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<%@ include file="/include/memberCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>update.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    function fCheck(){
    	let pwd = document.getElementById("pwd").value;
    	let name = document.getElementById("name").value;
    	
    	if(pwd.trim() == "" || name.trim() == ""){
    		alert("수정할 정보를 입력하세요.");
    		document.getElementById("pwd").focus();
    	}
    	else {
    		myform.submit();
    	}
    }
  </script>
  <style>
    th {
      background-color: #eee;
      text-align: center;
    }
  </style>
</head>
<body>
<p><br/></p>
<div class="container text-center">
<form name="myform" method="post" action="${ctp}/database/updateOk">
  <h2>회원 정보 수정</h2>
  <table class="table table-bordered">
    <tr>
      <th>아이디</th>
      <%-- <td><input type="text" name="mid" id="mid" value="${sMid}" readonly class="form-control"></td> --%>
      <td class="text-left">${sMid}</td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td><input type="password" name="pwd" id="pwd" required autofocus class="form-control"></td>
    </tr>
    <tr>
      <th>성명</th>
      <td><input type="text" name="name" id="name" value="${sName}" required class="form-control"></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="button" value="정보수정" onclick="fCheck()" class="btn btn-success" />
        <input type="reset" value="다시입력" class="btn btn-warning" />
        <input type="button" value="돌아가기" onclick="location.href='${ctp}/study/database/memberMain.jsp';" class="btn btn-primary" />
      </td>
    </tr>
  </table>
  <input type="hidden" name="mid" value="${sMid}"/>  <!-- 43번라인처럼 적으면 submit으로 넘어가지 않기 때문에 hidden에 담아서 넘기면 눈에 보이진 않지만 값이 넘어간다. -->
</form>
</div>
<p><br/></p>
</body>
</html>