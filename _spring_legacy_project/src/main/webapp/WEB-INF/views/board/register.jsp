<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />
	
	<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open -->
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.mvo.email" var="authEmail" />
		<sec:authentication property="principal.mvo.nickName" var="authNick" />
		<sec:authentication property="principal.mvo.regDate" var="authRegDate" />
		<sec:authentication property="principal.mvo.lastLogin" var="authLastLogin" />
		<sec:authentication property="principal.mvo.authList" var="auths" />
	</sec:authorize>	
	
	<div class="all-container">
		<form action="/board/register" method="post" enctype="multipart/form-data">
			<div class="inner-container">
				<div><h2>Board Register</h2></div>
				
				<div class="table-box">
					<table>
						<tr>
							<th>작성자</th>
							<td><input type="text" name="writer" value="${authEmail}" readonly="readonly"></td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="content"></textarea></td>
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
					
				</div>
				<div>
					<button id="register-btn" class="button post-btn" type="submit">등록</button>
				</div>
				
			</div>
		</form>
		
	</div>	
</body>
<script type="text/javascript" src="/resources/JS/boardRegister.js"></script>
</html>