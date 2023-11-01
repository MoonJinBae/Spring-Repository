<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#join-btn{
		color:#fff;
	}
</style>
</head>
<body>
	<div class="all-container">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
		<form action="/member/login" method="post">
			<div class="position-absolute top-50 start-50 translate-middle input-box">
				<div class="title"><h2>로그인</h2></div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">ID</span>
				  <input type="text" class="form-control" placeholder="E-MAIL" name="email" aria-describedby="basic-addon1">
				</div>
				
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">PW</span>
				  <input type="text" class="form-control" placeholder="PASSWORD" name="pwd" aria-describedby="basic-addon1">
				</div>
				
				<c:if test="${not empty param.errMsg}">
				<div class="text-danger mb-3">
					<c:choose>
						<c:when test="${param.errMsg eq 'Bad credentials'}">
							<c:set var="errText" value="이메일 & 비밀번호가 일치하지 않습니다."></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="errText" value="관리자에게 문의해 주세요."></c:set>
						</c:otherwise>
					</c:choose>
					${errText}
				</div>
				</c:if>
				
				<div class="button-box">
					<button type="submit" id="join-btn" class="btn btn-outline-secondary">로그인</button>
				</div>
				
			</div>
		</form>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
</html>