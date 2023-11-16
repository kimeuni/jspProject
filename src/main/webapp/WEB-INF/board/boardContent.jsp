<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>boardContent.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<style>
		th{
			text-align:center;
			background-color:#eee;
		}
		/* 신고창 */
		#complaint{
			position: fixed;
			top:25%;
			/* 가운데 정렬 */
			left: 50%;
    		transform: translate(-50%, 0);
			width: 450px;
			border: 1px solid gray;
			border-radius: 25px;
			background-color:#fff;
		}
		
		#cpstyle{
			text-align:center;
			padding: 30px;
		}
	</style>
	<script>
		'use strict'
		
		// 좋아요 조회수 증가 ajax처리
		function goodCheck(){
			$.ajax({
				url : "boardGoodCheck.bo",
				type : "post",
				data : {idx: ${vo.idx}},
				success : function(res){
					if(res == "0") alert("이미 좋아요 버튼을 클릭하셨습니다.")
					else location.reload();
				},
				error : function(){
					alert("전송오류~");
				}
			});
		}
		
		// 좋아요 조회수 증가 (중복 허용... 숙제 )
		function goodCheckPlus(){
			$.ajax({
				url : "boardGoodCheckPlus.bo",
				type : "post",
				data : {idx: ${vo.idx}},
				success : function(res){
					location.reload();
				},
				error : function(){
					alert("전송오류~");
				}
			});
		}
		
		// 좋아요 조회수 감소 (중복 허용... 숙제 )
		function goodCheckMinus(){
			$.ajax({
				url : "boardGoodCheckMinus.bo",
				type : "post",
				data : {idx: ${vo.idx}},
				success : function(res){
					location.reload();
				},
				error : function(){
					alert("전송오류~");
				}
			});
		}
		
		// 게시글 삭제 (어차피 삭제하고 다른 페이지로 보내야하기 때문에, ajax는 의미가 없다.)
		function boardDelete(){
			let ans = confirm("현재 게시글을 삭제하시겠습니까?");
			if(ans){
				location.href = "baordDelete.bo?idx=${vo.idx}";
			}
		}
		
		// 댓글 달기 
		function replyCheck(){
			let content = $("#content").val();
			if(content.trim() == ""){
				alert("댓글을 입력하세요.");
				$("#content").focus();
				return false;
			}
			// idx, wDate 는 자동으로 들어가기 때문에 값을 안 넘져줘도 된다.
			let query = {
				boardIdx : ${vo.idx},
				mid : '${sMid}',   // mid는 문자이기 때문에 따옴표를 적어줘야함 (아니면 에러나옴)
				nickName : '${sNickName}',
				hostIp : '${pageContext.request.remoteAddr}',
				content : content
			}
			$.ajax({
				url : "boardReplyInput.bo",
				type : "post",
				data : query,
				success : function(res){
					if(res == "1") {
						alert("댓글이 입력되었습니다.")
						location.reload();
					}
					else alert("댓글 입력 실패")
				},
				error : function(){
					alert("전송 오류~")
				}
			});
		}
		
		// 댓글 삭제하기 (여기서 가져온 idx는 Reply의 고유번호이다.)
		function replyDelete(idx){
			let ans = confirm("선택한 댓글을 삭제하시겠습니까?");
			if(!ans) return false;
			
			$.ajax({
				url : "boardReplyDelete.bo",
				type : "post",
				data : {idx : idx},
				success : function(res){
					if(res ="1"){
						alert("댓글이 삭제되었습니다.")
						location.reload();					
					}
					else alert("댓글 삭제 실패.")
				},
				error : function(){
					alert("전송오류")
				}
			});
		}
		
		//게시판 들어왔을 때 신고창 숨기기 및 신고기타사유 적는 곳 숨기기
		$(function() {
			$("#complaint").hide();
			$("#cpWhyOther").hide();
		});
		
		// 신고버튼 눌렀을 시, 신고창 뜨게 하기
		function cpCheck(){
			$("#complaint").show();
		}
		
		// "기타"를 선택했을 시, 화면 보이기 혹은 가리기
		function cpWhyCheck(){
			let cpWhy = $("#cpWhy").val();
			if(cpWhy == '기타'){
				$("#cpWhyOther").show();
			}
			else {
				$("#cpWhyOther").hide();
			}
		};
		
		// 신고창에서 취소 버튼
		function cCheck(){
			
			$("#complaint").hide();
			$("#cpWhyOther").hide();
		}
		
		// 신고창 신고하기 버튼
		function cpCheckOk() {
			let cpWhy = $("#cpWhy").val();
			let otherWhy =$("#otherWhy").val();
			
			if(cpWhy.trim() == "" ){
				alert("신고 사유를 선택해주세요.")
				return false;
			}
			else if(cpWhy == "기타" && otherWhy.trim() == ""){
				alert("신고 사유를 선택해주세요.")
				$("#otherWhy").focus();
				return false;
			}
			else {
				let cpWhy = $("#cpWhy").val();
				let otherWhy = $("#otherWhy").val();
				
				let query = {
					part : "board",
					partIdx : ${vo.idx},
					cpMid : "${sMid}",
					cpContent : cpWhy,
					cpContOther : otherWhy
				}
				$.ajax({
					url : "complaint.bo",
					type : "post",
					data : query,
					success : function(res){
						if(res == "1") {
							alert("신고가 완료되었습니다.")
							location.reload();
						}
						else alert("신고에 실패하였습니다.")
					},
					error : function(){
						alert("전송 오류");
					}
				});
			}
			
		}
		
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<table class="table table-borderless m-0">
		<tr>
			<td><h2 class="text-center">글 내 용 보 기</h2></td>
		</tr>
	</table>
	<table class="table table-bordered">
		<tr>
			<th>글쓴이</th>
			<td>${vo.nickName}</td>
			<th>글쓴날짜</th>
			<td>${fn: substring(vo.wDate,0,16) }</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${vo.title }</td>
		</tr>
		<tr>
			<th>전자메일</th>
			<td>
				<c:if test="${empty vo.email}">없음</c:if>
				<c:if test="${!empty vo.email}">${vo.email }</c:if>
			</td>
			<th>조회수</th>
			<td>${vo.readNum}</td>
		</tr>
		<tr>
			<th>홈페이지</th>
			<td>
				<c:if test="${empty vo.homePage || (fn: indexOf(vo.homePage,'http://')== -1 && fn: indexOf(vo.homePage,'https://')==-1) || fn:length(vo.homePage) <= 10 }">없음</c:if>
				<!-- indexOf 부분에 () 로 묵은 이유는 http:// 또는 https://가 들어오면 주소가 맞기 때문이다. -->
				<c:if test="${!empty vo.homePage && (fn: indexOf(vo.homePage,'http://')!= -1 || fn: indexOf(vo.homePage,'https://')!=-1) && fn:length(vo.homePage) > 10 }"><a href="${vo.homePage }" target="_blank">${vo.homePage }</a></c:if>
			</td>
			<th>좋아요</th>
			<td><font color="red"><a href="javascript:goodCheck()">❤</a></font>(${vo.good }) / <a href="javascript:goodCheckPlus()">👍</a> <a href="javascript:goodCheckMinus()">👎</a> </td>
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3" style="height:220px;">${fn:replace(vo.content,newLine,"<br/>")}</td>
		</tr>
	</table>
	<table class="table table-borderless">
		<tr>
			<td class="text-left">
				<c:if test="${flag != 'search'}">
					<input type="button" value="돌아가기" onclick="location.href='boardList.bo?pageSu=${pageSu}&pageSize=${pageSize}'" class="btn btn-warning"/>
				</c:if>
				<c:if test="${flag == 'search'}"> <!-- 수정페이지에서 Content로 들어오고 나가면 다시 검색했던 리스트로 가야하기 때문에 if문을 통하여 넘겨야한다. 그리고 다시 돌아가려면 search와 searchString의 값을 추가로 가지고 넘어가야한다. (BoardContentCommad에서 값을 넘김) -->
					<input type="button" value="돌아가기" onclick="location.href='boardSearch.bo?pageSu=${pageSu}&pageSize=${pageSize}&search=${search}&searchString=${searchString}'" class="btn btn-warning"/>
				</c:if>
				<c:if test="${sMid == vo.mid}"> <!-- 로그인한 아이디와 게시글에 등록한 사람 id가 같으면 수정버튼 보이기 -->
				<!-- 수정 후에도, pageSu와 PageSize를 넘겨야 수정후에도 돌아가기 버튼을 누르면 원래 있던 페이지로 돌아갈 수 있다. -->
					<!-- 검색창에서 검색을 했는지 안했는지를 확인하기 위해 flg변수에 search가 넘어왔는지 if문으로 검사 후 주소를 보낸다. -->
					<c:if test="${flag != 'search'}">
						<input type="button" value="수정" onclick="location.href='boardUpdate.bo?idx=${vo.idx}&pageSu=${pageSu}&pageSize=${pageSize}'" class="btn btn-info"/>
					</c:if>
					<c:if test="${flag == 'search'}"> <!-- 검색해서 수정에 들어왔을 경우 -->
						<input type="button" value="수정" onclick="location.href='boardUpdate.bo?idx=${vo.idx}&pageSu=${pageSu}&pageSize=${pageSize}&flag=search&search=${search}&searchString=${searchString}'" class="btn btn-info"/>
					</c:if>
					
				</c:if>  
				<c:if test="${sMid == vo.mid || sLevel == 0}">
				<input type="button" value="삭제" onclick="boardDelete()" class="btn btn-danger"/>
				</c:if>  
			</td>
			<td class="text-right" colspan=""><a href="javascript:cpCheck()" class="btn btn-danger">신고하기</a></td>
		</tr>
	</table>
	<br/>
	<!-- 이전글/다음글 처리 -->
	<table class="table table-borderless text-center">
		<tr>
			<td>
				<!-- 비교는 !empty nextVo.title 혹은 nextVo.title != null로 비교하면 된다. -->
				<c:if test="${nextVo.title != null}"><a href="boardContent.bo?idx=${nextVo.idx}&pageSu=${pageSu}&pageSize=${pageSize}">다음글 : ${nextVo.title}</a><br/></c:if>
				<c:if test="${preVo.title != null}"><a href="boardContent.bo?idx=${preVo.idx}&pageSu=${pageSu}&pageSize=${pageSize}">이전글 : ${preVo.title}</a><br/></c:if>
			</td>
		</tr>
	</table>
</div>

<!-- 댓글 처리 -->
<div class="container">
	<!-- 댓글 리스트 보여주기 -->
	<table class="table table-hover text-center">
		<tr>
			<th >작성자</th>
			<th class="text-left">댓글내용</th>
			<th>작성일자</th>
			<th>접속IP</th>
		</tr>
		<c:forEach var="replyVO" items="${replyVOS}" varStatus="st">
			<tr>
				<td>${replyVO.nickName}
					<c:if test="${replyVO.mid == sMid || sLevel == 0 }">
						(<a href="javascript:replyDelete(${replyVO.idx})" >x</a>) <!-- reply의 고유번호(idx)를 넘겨야 해당  -->
					</c:if>
				</td>
				<td class="text-left">${fn: replace(replyVO.content,newLine,"<br/>")}</td>
				<td>${fn: substring(replyVO.wDate,0,10)}</td>
				<td>${replyVO.hostIp}</td>
			</tr>
		</c:forEach>
		<tr><td colspan="4" class="m-0 p-0"></td></tr>
	</table>
	<br/>
	
	<!-- 댓글 입력창 -->
	<form name = "replyForm">
		<table class="table table-center">
			<tr>
				<td style="width:85%;" class="text-left">
					글내용 : 
					<textarea rows="4" name="content" id="content" class="form-control"></textarea>
				</td>
				<td style="width:15%;">
					<br/>
					<p style="font-size:13px;">작성자 : ${sNickName}</p>
					<p> <input type="button" value="댓글달기" onclick="replyCheck()" class="btn btn-info btn-sm"/></p>
					
				</td>
			</tr>
		</table>
	</form>
</div>	
<!-- 게시글 신고창 -->
	<div id="complaint">
		<div style="text-align:center; background-color:#252525; color:#fff; height:50px; line-height:50px;">신고하기</div>
		<div id="cpstyle">
			<div>신고항목 : board 게시판</div>
			<div>신고 게시판 : ${vo.title}  /  고유번호 : ${vo.idx}</div>
			<div>신고자 : ${sMid}</div>
			<div>
				<select name="cpWhy" id="cpWhy" class="form-control" onchange="cpWhyCheck()">
					<option value="">신고 사유</option>
					<option value="영리목적/홍보성">영리목적/홍보성</option>
					<option value="개인 정보 노출">개인 정보 노출</option>
					<option value="불법 정보">불법 정보</option>
					<option value="음란성/선정성">음란성/선정성</option>
					<option value="기타">기타</option>
				</select>
			</div>
			<div id="cpWhyOther" class="mt-2">
				<hr/>
				<textarea rows="4" id="otherWhy" class="form-control"></textarea>
			</div>
			<div class="text-center mt-3">
				<a href="javascript:cpCheckOk()" class="btn btn-success">신고하기</a>
				<a href="javascript:cCheck()" class="btn btn-warning">취소하기</a>
			</div>
		</div>
	</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>