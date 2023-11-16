<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>fileUpload1.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		function fCheck() {
			//업로드 프론트 체크
			let fName = document.getElementById("file").value;
			let ext = fName.substring(fName.lastIndexOf(".")+1).toLowerCase()
			// 10MB  (1024 = 1KB / 1024*1024 = 1MB / 1MB*10 = 10MB)
			let maxSize = 1024*1024*10; //1KByte = 1024Byte =10^3Byte = 2^10Byte, 1MByte = 2^20Byte = 10^6Byte, 1GByte = 2^30Byte = 10^9Byte, 1TByte = 2^40Byte = 10^12Byte
			
			if(fName.trim() == ""){
				alert("업로드할 파일을 선택하세요!")
				return false;
			}
			// 파일 업로드가 되어있어
			let fileSize = document.getElementById("file").files[0].size;  //파일의 첫번째 정보 사이즈를 가져옴
			// 확장자 체크를 꼭 해야함 (.exe 같은 실행파일이 못올라오도록(바이러스가 있을 수도 있기 때문))
			// 허용할 파일만 적는다.
			if(ext != 'jpg' && ext != 'gif'&& ext != 'png'&& ext != 'zip'&& ext != 'hwp' && ext != 'ppt'&& ext != 'pptx'&& ext != 'xlsx'){
				alert("업로드 가능한 파일은 'jpg/gif/png/zip/hwp/ppt/pptx/xlsx'만 가능합니다.")
			}
			// 파일 용량 체크
			else if(fileSize > maxSize) {
				alert("업로드할 파일의 최대용량은 10MByte입니다.");
			}
			else {
				myform.submit();
			}
		}
		
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2>파일 업로드 연습(싱글파일처리)</h2>
	<p>COS라이브러리를 이용한 파일 업로드 (회사에서 이거 사용하는 곳도 많다고 함)</p>
	<div class="mb-3">COS 라이브러리 다운로드 주소 : <a href="http://www.servlets.com/cos/" target="_blank">http://www.servlets.com/cos/</a></div>
	<form name="myform" method="post" action="fileUpload1Ok.st" enctype="multipart/form-data">  <!-- 1. 파일 업로드를 하기 위해서  enctype="multipart/form-data 이것을 무조건 적어주어야 한다.-->
		파일명:
		<input type="file" name="fName" id="file" class="form-control-file mb-2 border"/>
		<input type="button" value="전송" onclick="fCheck()" class="btn btn-success form-control"/>
	</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>