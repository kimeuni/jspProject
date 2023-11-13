<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>JavaProject(index.jsp)</title>
	<jsp:include page="/include/bs4.jsp"/>
	<style>
	  .fakeimg {
	    height: 250px;
	    background: #aaa;
	  }
	  .animeImage{
	  	width:100%; height:250px;
	  	background:url("${ctp}/images/51431.jpg") 0 center / 450px repeat-x;
	  	animation: movebg 6s linear infinite;
	  }
	  @keyframes movebg{
	  	0% {background-position:0 center;}
	  	100% {background-position:-450px center;}
	  }
	  
  	</style>
</head>
<body>
<jsp:include page="/include/header.jsp"/>

<div class="container" style="margin-top:30px">
  <div class="row">
    <div class="col-sm-4">
      <h2>About Me</h2>
      <h5>Photo of me:</h5>
      <div class="fakeimg">Fake Image</div>
      <p>Some text about me in culpa qui officia deserunt mollit anim..</p>
      <h3>Some Links</h3>
      <p>Lorem ipsum dolor sit ame.</p>
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link active" href="#">Active</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#">Disabled</a>
        </li>
      </ul>
      <hr class="d-sm-none">
    </div>
    <div class="col-sm-8">
      <h2>TITLE HEADING</h2>
      <h5>Title description, Dec 7, 2017</h5>
      <!-- <div class="fakeimg">Fake Image</div> -->
      <div class="fakeimg">
	      <div class="animeImage"></div>
      </div>
      <p>Some text..</p>
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
      <br>
      <h2>TITLE HEADING</h2>
      <h5>Title description, Sep 2, 2017</h5>
      <div class="fakeimg">
      	<!-- 이미지를 1~5 랜덤으로 출력되도록 컨트롤러를 타고 넘어왔다. -->
      	<img src ="${ctp}/images/${mainImage}.jpg" width="100%" height="100%"/>
      </div>
      <p>Some text..</p>
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    </div>
  </div>
</div>
<jsp:include page="/include/footer.jsp"></jsp:include>

</body>
</html>