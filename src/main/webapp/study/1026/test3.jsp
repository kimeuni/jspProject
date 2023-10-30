<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>title</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script>
    	'use strict'
    	
    	function fCheck(){
    		let name = myform.name.value;
    		let age = document.getElementById("age").value;
    		
    		if(name.trim() == ""){
    			alert("성명을 입력하세여.")
    			myform.name.focus();
    		}
    		else if(age.trim() == ""){
    			alert("나이를 입력하세여.")
    			document.getElementById("age").focus();
    		}
    		else {
    			/* alert("정상처리 되었습니다.")
    			location.href = "test2Ok.jsp?name="+name+"&age="+age; */  /* location을 적을 시 무조건 get방식 // 이렇게 적을 시 하나하나 다 처리해야하기 때문에 밑에 있는 myform.submit()으로 넘긴다. */
    			
    			/* location.href = `test2Ok.jsp?name=${name}&age=&{age}`; : 백앤드 처리시 오류 발생 ★★★★값을 넘길 때 ``(백팁) 사용 불가.. ★★★★ */
    		
    			myform.submit();  /* ★★★form에 있는 내용을 전부 넘긴다!!! */
    		}
    	}
    </script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2>값의 전송(GET/POST) - servlet으로 전송하기 </h2>
	<!-- post 방식 : 비밀번호같은 걸 넘길 때는 무조건 Post방식을 사용한다. -->
	<!-- <form name="myform" method="get" action="../../Test3OkGet"> -->  <!-- / 는 현재위치부터 시작하기 때문에.. webapp까지 올라가야 servlet에 있는 Test3OkGet(url mapping주소가 적혀있는) 파일을 읽을 수 있다. -->    
	<%-- <form name="myform" method="get" action="<%= request.getContextPath() %>/Test3OkGet"> --%>  
	<form name="myform" method="post" action="<%= request.getContextPath() %>/Test3OkPost">  <!--  ★★★★ 경로 매우매우매우 중요!!!★★★★★ -->
		<div>성명 :
			<input type="text" name="name" class="form-control mb-3" autofocus />
		</div>
		<div>나이 :
			<input type="number" name="age" id="age" class="form-control mb-3" />
		</div>
		<div>
			<input type="button" value="전송" onclick="fCheck()" class="btn btn-primary form-control " />
		</div>
		<div>
			<input type="hidden" name="flag" value="Ok~~~~"/>
		</div>
	</form>
</div>
<p><br/></p>
</body>
</html>