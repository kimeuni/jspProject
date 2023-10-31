<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- nav4.jsp -->
<%@ include file="../../include/bs4.jsp" %>
<script>
	function logoutCheck(){
		let ans = confirm("로그아웃 하시겠습니까?");
		
		if(ans){
			location.href = "${pageContext.request.contextPath}/j1030/logoutOk4?mid=${param.mid}";
		}
	}
</script>

<div style="height: 180px; text-aling: center;">
	<p><br/></p>
	<a href="main4.jsp?mid=${param.mid}&nick=${param.nick}" class="btn btn-outline-primary">Home</a> |
	<a href="main4.jsp?flag=guest&mid=${param.mid}&nick=${param.nick}" class="btn btn-outline-primary">Guset</a> |
	<a href="main4.jsp?flag=board&mid=${param.mid}&nick=${param.nick}" class="btn btn-outline-primary">Board</a> |
	<a href="main4.jsp?flag=pds&mid=${param.mid}&nick=${param.nick}" class="btn btn-outline-primary">Pds</a> |
	<a href="javascript: logoutCheck()" class="btn btn-outline-warning">Logout</a>
</div>
