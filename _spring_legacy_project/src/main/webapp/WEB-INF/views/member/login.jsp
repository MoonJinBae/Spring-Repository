<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="all-container">
	
		<jsp:include page="../common/nav.jsp" />
		
		<form action="/member/login" method="post">
			<div class="inner-container">
				<div><h2>LOGIN</h2></div>
				
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
				
				<div>
					<span>E-MAIL</span>
					<input type="text" name="email">
				</div>
				<div>
					<span>비밀번호</span>
					<input type="text" name="pwd">
				</div>
				<div>
					<button class="button" type="submit">로그인</button>
				</div>
			</div>
		</form>
		
	</div>
	
</body>
</html>