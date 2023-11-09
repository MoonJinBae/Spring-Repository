<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />
	<div class="all-container">
	
		<form action="/board/modify" method="post" enctype="multipart/form-data">
			<div class="inner-container">
				<div><h2>Board Edit</h2></div>
				
				<div class="table-box">
					<table>
						<tr>
							<th>#</th>
							<td><input type="text" name="bno" value="${bvo.bno}" readonly="readonly"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" name="writer" value="${bvo.writer}" readonly="readonly"></td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" value="${bvo.title}"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="content">${bvo.content}</textarea></td>
						</tr>
						
						<tr>
							<th>첨부파일</th>
							<td>
								<!-- 파일목록 표시 영역 -->
								<div class="detail-files">
								</div>
							</td>
						</tr>
						
						<!-- 파일 업로드 -->
						<tr>
							<th>
								<button id="file-trigger" class="file-btn" type="button">파일첨부</button>
							</th>
							<td>
								<!-- 첨부파일 표시 영역 -->
								<div id="files-zone">
								</div>
							</td>
						</tr>
						
					</table>
					
					<!-- display:none 버튼 클릭으로 오픈 -->
					<input type="file" id="files" name="files" style="display: none" class="file-input" multiple="multiple">
					
					<input type="hidden" name="regDate" value="${bvo.regDate}">
					<input type="hidden" name="modDate" value="${bvo.modDate}">
					<input type="hidden" name="hit" value="${bvo.hit}">
				</div>
				
				<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open -->
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.mvo.email" var="authEmail" />
					<sec:authentication property="principal.mvo.authList" var="auths" />
					
					<c:if test="${authEmail eq bvo.writer}">
						<div>
							<button class="button post-btn" type="submit">완료</button>
							<a href="/board/remove/${bvo.bno}/${bvo.writer}"><button class="button" type="button">글삭제</button></a>
						</div>
					</c:if>
				</sec:authorize>
				
			</div>
		</form>
		
	</div>	
</body>
<script type="text/javascript" src="/resources/JS/boardRegister.js"></script>
<script type="text/javascript" src="/resources/JS/boardFile.js"></script>
<script type="text/javascript">
	let error = `<c:out value="${Error}" />`;
	if(error == 2){
		alert("작성자가 아닙니다.");
	}
	let bnoVal = `<c:out value="${bvo.bno}" />`;
	let email = `<c:out value="${authEmail}" />`;
	getFileList(bnoVal);
</script>
</html>