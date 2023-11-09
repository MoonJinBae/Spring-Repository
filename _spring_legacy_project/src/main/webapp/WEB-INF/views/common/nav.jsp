<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="/resources/CSS/notice.css">
</head>
<body>
	<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open -->
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.mvo.email" var="authEmail" />
		<sec:authentication property="principal.mvo.nickName" var="authNick" />
		<sec:authentication property="principal.mvo.regDate" var="authRegDate" />
		<sec:authentication property="principal.mvo.lastLogin" var="authLastLogin" />
		<sec:authentication property="principal.mvo.authList" var="auths" />
	</sec:authorize>
	
	<div class="nav-container">
		<div class="nav-board-menu">
			
			<div>
				<h2><a href="/">HOME</a></h2>
			</div>
			<div>
				<a href="/board/list">게시글목록</a>
			</div>
			<!-- 로그인 후 상태에서 open 되어야 핳 메뉴 -->
			<sec:authorize access="isAuthenticated()">
			<div>
				<a href="/board/register">게시글작성</a>
			</div>
			</sec:authorize>
		</div>
		
		<div class="nav-member-menu">
		
			<!-- 로그인 후 상태에서 open 되어야 핳 메뉴 -->
			<sec:authorize access="isAuthenticated()">
			<c:choose>
				<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
				<div><a href="/member/list"><button type="button" class="nav-admin">회원목록(관리자*${authEmail})</button></a></div>
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
			</c:choose>
				<div><a href="/member/info/${authEmail}"><button type="button" class="nav-button button">회원정보</button></a></div>
				<div>
					<form action="/member/logout" method="post">
						<button type="submit" class="nav-button button">로그아웃</button>
					</form>
				</div>
			</sec:authorize>
			
			<!-- 로그인 전 상 상태에서 open 되어야 할 메뉴 -->
			<sec:authorize access="isAnonymous()">
			<div><a href="/member/join"><button type="button" class="nav-button button">회원가입</button></a></div>
			<div><a href="/member/login"><button type="button" class="nav-button button">로그인</button></a></div>
			</sec:authorize>
		
		</div>
		
	</div>
</body>

</html>