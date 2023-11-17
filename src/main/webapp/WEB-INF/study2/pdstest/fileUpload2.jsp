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
			let fileSize1 = document.getElementById("file1").files[0].size;
	    	let fileSize2 = document.getElementById("file2").files[0].size;
	    	let fileSize3 = document.getElementById("file3").files[0].size;
	    	let fileSize = fileSize1 + fileSize2 + fileSize3;
	    	if(ext1 != 'jpg' && ext1 != 'gif' && ext1 != 'png' && ext1 != 'zip' && ext1 != 'hwp' && ext1 != 'ppt' && ext != 'pptx' && ext != 'xlsx') {
	    		alert("업로드 가능한 파일은 'jgp/gif/png/zip/hwp/ppt/pptx/xlsx' 만 가능합니다.");
	    	}
	    	else if(ext2 != 'jpg' && ext2 != 'gif' && ext2 != 'png' && ext2 != 'zip' && ext2 != 'hwp' && ext2 != 'ppt' && ext != 'pptx' && ext != 'xlsx') {
	    		alert("업로드 가능한 파일은 'jgp/gif/png/zip/hwp/ppt/pptx/xlsx' 만 가능합니다.");
	    	}
	    	else if(ext3 != 'jpg' && ext3 != 'gif' && ext3 != 'png' && ext3 != 'zip' && ext3 != 'hwp' && ext3 != 'ppt' && ext != 'pptx' && ext != 'xlsx') {
	    		alert("업로드 가능한 파일은 'jgp/gif/png/zip/hwp/ppt/pptx/xlsx' 만 가능합니다.");
	    	}
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
	<form name="myform" method="post" action="fileUpload2Ok.st" enctype="multipart/form-data">  <!-- 1. 파일 업로드를 하기 위해서  enctype="multipart/form-data 이것을 무조건 적어주어야 한다.-->
		파일명:
		<input type="file" name="fName1" id="file1" class="form-control-file mb-2 border"/>
		<input type="file" name="fName2" id="file2" class="form-control-file mb-2 border"/>
		<input type="file" name="fName3" id="file3" class="form-control-file mb-2 border"/>
		<input type="button" value="전송" onclick="fCheck()" class="btn btn-success form-control"/>
	</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>