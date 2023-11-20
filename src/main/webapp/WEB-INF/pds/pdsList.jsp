<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>pdsList.jsp</title>
	<jsp:include page="/include/bs4.jsp"/>
	<script>
		'use strict'
		//part선택 (전체보기,part관련 보기) 리스트 보기
		function partCheck(){
			let part = $("#part").val();
			location.href="pdsList.pds?part="+part
		}
		
		// 파일 삭제
		function pdsDeleteCheck(idx,fSName){
			let ans = confirm("선택된 자료파일을 삭제하시겠시겠습니까?");
			if(!ans) return false;
			
			let pwd= prompt("비밀번호를 입력하세요.");
			
			let query = {
				idx : idx,
				fSName : fSName,
				pwd : pwd
			}
			$.ajax({
				url : "pdsDeleteOk.pds",
				type : "post",
				data : query,
				success : function(res){
					if(res == "1") {
						alert("삭제되었습니다.");
						location.reload();
					}
					else alert("삭제 실패");
				},
				error : function(){
					alert("전송오류");
				}
			});
		}
		
		//다운로드 수 증가시키기
		function downNumCheck(idx){
			$.ajax({
				url : "pdsDownNumCheck.pds",
				type : "post",
				data : {idx:idx},
				success : function(res){
					location.reload();
				},
				error : function(){
					
				}
			});
		}
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<h2 class="text-center">자 료 실 리 스 트(${part})</h2>
	<br/>
	<table class="table table-borderless m-0">
		<tr>
			<td style="width:20%" class="text-left">
				<form name="partForm">
					<select name="part" id="part" onchange="partCheck()" class="form-control">
						<option ${part =="전체" ? "selected" : "" }>전체</option>
						<option ${part =="학습" ? "selected" : "" }>학습</option>
						<option ${part =="여행" ? "selected" : "" }>여행</option>
						<option ${part =="음식" ? "selected" : "" }>음식</option>
						<option ${part =="기타" ? "selected" : "" }>기타</option>
					</select>
				</form>
			</td>
			<td style="width:10%" class="text-left">
				<select name="pageSize" id="pageSize" onchange="pageSizeCheck()" class="form-control">
					<option ${part =="3" ? "selected" : "" }>3</option>
					<option ${part =="5" ? "selected" : "" }>5</option>
					<option ${part =="10" ? "selected" : "" }>10</option>
					<option ${part =="15" ? "selected" : "" }>15</option>
					<option ${part =="20" ? "selected" : "" }>20</option>
				</select>건
			</td>
			<td class="text-right">
				<a href="pdsInput.pds?part=${part}" class="btn btn-success">자료올리기</a>
			</td>
		</tr>
	</table>
	<table class="table table-hover text-center">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>자료제목</th>
			<th>올린이</th>
			<th>올린날짜</th>
			<th>분류</th>
			<th>파일명(크기)</th>
			<th>다운수</th>
			<th>비고</th>
		</tr>
		<c:set var="pdsCnt" value="${fn:length(vos)}"/>
		<c:forEach var="vo" items="${vos}" varStatus="st">
			<tr>
				<td>${pdsCnt}</td>
				<c:if test="${vo.hour_diff <=24}"><td>${vo.title }<img src="${ctp}/images/new.gif"/></td></c:if>
				<c:if test="${vo.hour_diff >24}"><td>${vo.title }</td></c:if>
				<td>${vo.nickName }</td>
				<!-- 날짜 처리 -->
				<c:if test="${vo.hour_diff > 24}"><td>${fn:substring(vo.fDate,0,10)}</td></c:if>
				<c:if test="${vo.hour_diff <=24}"><td>${vo.day_diff == 0 ? fn:substring(vo.fDate,10,16) : fn:substring(vo.fDate,5,16)}</td></c:if>
				<td>${vo.part }</td>
				<td>
					<%-- ${vo.fName} --%>
					<c:set var="fNames" value="${fn:split(vo.fName,'/')}"/>
					<c:set var="fSNames" value="${fn:split(vo.fSName,'/')}"/>
					<c:forEach var="fName" items="${fNames}" varStatus="st">
						<a href="${ctp}/images/pds/${fSNames[st.index]}" download="${fName}" onclick="downNumCheck(${vo.idx})">${fName}</a><br/> <!-- $ {fSName} : 다운받는 실제 파일 이름 $ {fName} : 화면에 보여주는 이름 -->
					</c:forEach>
					<br/>
					(<fmt:formatNumber value="${vo.fSize/1024}" pattern="#,##0"/> KByte) 
				</td>
				<td>${vo.downNum }</td>
				<c:if test="${vo.mid == sMid || sLevel == 0}">
					<td><a href="javascript:pdsDeleteCheck('${vo.idx}','${vo.fSName}')" class="btn btn-danger btn-sm">삭제</a></td> <!-- 숫자,문자 같이 넘어가면 에러날 수 있기 때문에 둘다 문자로 넘기는게 좋다. -->
				</c:if>
			</tr>
			<c:set var="pdsCnt" value="${pdsCnt-1}"/>
			<tr><td colspan="8" class="m-0 p-0"></td></tr>
		</c:forEach>
	</table>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>