<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- test12Msg.jsp -->
<script>
	alert("회원가입 되었습니다. \n 회원메인창으로 이동합니다.");
	location.href="<%= request.getContextPath() %>/study/1026/test12Res.jsp";  // response.
</script>