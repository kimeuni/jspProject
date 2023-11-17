<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>fileUpload4.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		function fCheck() {
			let fName = document.getElementById("file").value;
	    	let ext1 = fName.substring(fName.lastIndexOf(".")+1).toLowerCase();
	    	let maxSize = 1024 * 1024 * 10;   // 1KByte=1024Byte=10^3Byte=2^10Byte, 1MByte=2^20Byte=10^6Byte, 1GByte=2^30Byte=10^9Byte, 1TByte=2^40Byte=10^12Byte)
	    	
	    	if(fName.trim() == "") {
	    		alert("업로드할 파일을 선택하세요!");
	    		return false;
	    	}
	    	
	    	let fileSize1 = document.getElementById("file").files[0].size;
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
		
		$(function() {
			$("#file").on("change", function(e) {
				// 그림파일인지 체크
				//alert("e : " +e);
				let files = e.target.files;
				// 파일에 들어온 내용을 배열개념으로 꺼내서 담는다.
				let filesArr = Array.prototype.slice.call(files);
				//console.log("filesArr", filesArr);
				
				filesArr.forEach(function(f){
					//타입확인 이미지 타입인가?
					if(!f.type.match("image.*")) {
						alert("업로드할 파일은 이미지 파일만 가능합니다.")
					}
				});
				
				//멀티파일 이미지 미리보기
				let i = e.target.files.length;
				for(let image of files) {
					let img = document.createElement("img");
					let reader = new FileReader();
					reader.onload = function(e) {
						img.setAttribute("src", e.target.result);
						img.setAttribute("width", 200); //기본이 픽셀단위임
					}
					reader.readAsDataURL(e.target.files[--i]);
					document.querySelector(".demo").append(img);
				}
				
			});
		});
		
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2>파일 업로드 연습(멀티파일처리)</h2>
	<p>COS라이브러리를 이용한 파일 업로드 (회사에서 이거 사용하는 곳도 많다고 함)</p>
	<div class="mb-3">COS 라이브러리 다운로드 주소 : <a href="http://www.servlets.com/cos/" target="_blank">http://www.servlets.com/cos/</a></div>
	<form name="myform" method="post" action="fileUpload4Ok.st" enctype="multipart/form-data">  <!-- 1. 파일 업로드를 하기 위해서  enctype="multipart/form-data 이것을 무조건 적어주어야 한다.-->
		파일명:
		<div>
			<input type="file" name="fName" id="file" multiple class="form-control-file mb-2 border"/>
		</div>
		<input type="button" value="전송" onclick="fCheck()" class="btn btn-success form-control"/>
	</form>
	<div class="demo"></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>