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
			let fName1 = document.getElementById("file1").value;
	    	let ext1 = fName1.substring(fName1.lastIndexOf(".")+1).toLowerCase();
	    	let maxSize = 1024 * 1024 * 10;   // 1KByte=1024Byte=10^3Byte=2^10Byte, 1MByte=2^20Byte=10^6Byte, 1GByte=2^30Byte=10^9Byte, 1TByte=2^40Byte=10^12Byte)
	    	
	    	if(fName1.trim() == "") {
	    		alert("업로드할 파일을 선택하세요!");
	    		return false;
	    	}
	    	
	    	let fileSize1 = document.getElementById("file1").files[0].size;
	    	let fileSize = fileSize1;
	    	if(ext1 != 'jpg' && ext1 != 'gif' && ext1 != 'png' && ext1 != 'zip' && ext1 != 'hwp' && ext1 != 'ppt' && ext != 'pptx' && ext != 'xlsx') {
	    		alert("업로드 가능한 파일은 'jgp/gif/png/zip/hwp/ppt/pptx/xlsx' 만 가능합니다.");
	    	}
	    	else if(fileSize > maxSize) {
	    		alert("업로드할 파일의 최대용량은 10MByte입니다.");
	    	}
	    	else {
	    		myform.submit();
	    	}
		}
		
		let cnt = 1;
		// 파일박스 추가하기
		function fileBoxAppend(){
			cnt++;
			let fileBox = '';
			fileBox += '<div id = "fBox'+cnt+'" class="input-group">';
			fileBox += '<input type="file" name="fName'+cnt+'" id="file'+cnt+'" class="form-control-file mb-2 border" style="width:89%"/>'; /* name의 이름을 계속 바꾸기 위해서 cnt 변수를 선언했다. */
			fileBox += '<input type="button" value="삭제" onclick="deleteBox('+cnt+')" class="btn btn-danger ml-2 mb-2" style="width:10%"/>'; //어디를 삭제해야 할지 알아야하기 때문에 fBOX 옆에 적힐 cnt변수만 가져감 (fBOX는 고정이기 때문에 deletBox쪽에서 추가한다..)
			fileBox += '</div>';
			$("#fileBox").append(fileBox);
		}
		
		// 파일 박스 삭제
		function deleteBox(cnt){
			$("#fBox"+cnt).remove();
		}
		
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2>파일 업로드 연습(멀티파일처리)</h2>
	<p>COS라이브러리를 이용한 파일 업로드 (회사에서 이거 사용하는 곳도 많다고 함)</p>
	<div class="mb-3">COS 라이브러리 다운로드 주소 : <a href="http://www.servlets.com/cos/" target="_blank">http://www.servlets.com/cos/</a></div>
	<form name="myform" method="post" action="fileUpload3Ok.st" enctype="multipart/form-data">  <!-- 1. 파일 업로드를 하기 위해서  enctype="multipart/form-data 이것을 무조건 적어주어야 한다.-->
		파일명:
		<div>
			<input type="button" value="파일박스추가" onclick="fileBoxAppend()" class="btn btn-primary mb-2"/>
			<input type="file" name="fName1" id="file1" class="form-control-file mb-2 border"/>
		</div>
		<div id="fileBox"></div>	
		<input type="button" value="전송" onclick="fCheck()" class="btn btn-success form-control"/>
	</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>