<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>fileDownload.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		function fileDelCheck(fileName) {
			// 제대로 넘어오는지 확인
			//alert("fileName : " +fileName);
			
			$.ajax({
				url : "tempFileDelete.st",
				type : "post",
				data : {fileName : fileName},
				success : function(res){
					if(res == "1"){
						alert("삭제완료");
						location.reload();
					}
					else {
						alert("삭제 실패~");
					}
				},
				error : function(){
					alert("전송오류");
				}
			});
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2>fileDownload.jsp(저장된 파일정보)</h2>
	<hr/>
	<c:forEach var="file" items="${files}" varStatus="st">
		<div>
			<!-- download="${ctp}/images/pdstest/ $ {file} : 이런식으로 download를 적고 경로를 적어주면 해당 파일이 다운로드된다(HTML5에서 나온 명령어) -->
			<a href="${ctp}/images/pdstest/${file}" download="${ctp}/images/pdstest/${file}">${file}</a> :
			<input type="button" value="자바다운로드" onclick="location.href='javaFileDownload.st?file=${file}'" class="btn btn-secondary"/>
			<c:set var="fNameArr" value="${fn:split(file,'.')}"/>  <!-- file을 .을 기준으로 자른다. -->
			<c:set var="exeName" value="${fn: toLowerCase(fNameArr[fn:length(fNameArr)-1]) }"/>  <!-- 자른 스프릿의 제일 마지막에 있는것이 확장자 파일명이기 때문에 이런식으로 가져온다. + 모두 소문자로 -->
			<c:if test="${exeName == 'zip'}">압축파일</c:if>
			<c:if test="${exeName == 'ppt' || exeName == 'pptx'}">파원포인트파일</c:if>
			<c:if test="${exeName == 'hwp'}">한글파일</c:if>
			<c:if test="${exeName == 'jpg' || exeName == 'png' || exeName == 'gif'}">
				<img src="${ctp}/images/pdstest/${file}" width="150px"/>
			</c:if> &nbsp; <!-- pdstest에 사진이 들어가 있지 않지만 server쪽 pdstest에는 이미지가 들어가 있기 때문에 이렇게만 적어도 경로를 가져와서 그림을 출력한다. -->
			<input type="button" value="삭제" onclick="fileDelCheck('${file}')" class="btn btn-danger"/>
		</div>
		<hr/>
	</c:forEach>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>