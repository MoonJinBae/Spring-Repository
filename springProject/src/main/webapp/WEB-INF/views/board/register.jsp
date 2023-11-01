<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
<style type="text/css">
.fileupload-btn-box{
	display: flex;
	justify-content: center;
	margin-bottom: 15px;
}

</style>
<link rel="stylesheet" href="/resources/css/input.css">
</head>
<body>
	<div class="all-container">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
		
		<sec:authentication property="principal.mvo.email" var="authEmail" />
		<sec:authentication property="principal.mvo.nickName" var="authNick" />
						
		<form action="/board/register" method="post" enctype="multipart/form-data">
			<div class="position-absolute top-50 start-50 translate-middle input-box">
				<div class="title"><h2>글쓰기</h2></div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">제목</span>
				  <input type="text" class="form-control" placeholder="Title" name="title" aria-label="Username" aria-describedby="basic-addon1">
				</div>
				
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">작성자</span>
				  <input type="text" class="form-control" name="writer" value="${authNick}" aria-describedby="basic-addon1">
				</div>
				
				<div class="input-group">
				  <span class="input-group-text">내용</span>
				  <textarea class="form-control" aria-label="With textarea" name="content"></textarea>
				</div>
				
				<!-- 파일 업로드 영역 -->
				<div class="input-group mb-3">
				  <input type="file" class="form-control" multiple="multiple" style="display:none" id="files" name="files" aria-describedby="basic-addon1">
				  <!-- input button trigger 용도 -->
				</div>
				<div class="fileupload-btn-box">
					<button type="button" id="trigger" class="btn btn-secondary">파일첨부</button>
				</div>
				
				<!-- 첨부파일 표시 영역 -->
				<div class="input-group mb-3" id="file-zone">
				</div>
				
				<div class="button-box">
					<button type="submit" id="button" class="btn btn-outline-secondary">등록</button>
				</div>
			</div>
		</form>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
</html>