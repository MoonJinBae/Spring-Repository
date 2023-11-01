<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/input.css">
<link rel="stylesheet" href="/resources/css/table.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="/">Home</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					

						<!-- 인증된 사용자만 -->
						${principal}
						
						<!-- 로그인 전 상 상태에서 open 되어야 할 메뉴 -->
						<sec:authorize access="isAnonymous()">
						<li class="nav-item">
							<a class="nav-link" href="/member/register">Sign Up</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/member/login">LogIn</a>
						</li>
						</sec:authorize>

						<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open -->
						<!-- 사용자 정보 principal -->
						<sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal.mvo.email" var="authEmail" />
							<sec:authentication property="principal.mvo.nickName" var="authNick" />
							<sec:authentication property="principal.mvo.regAt" var="authRegAt" />
							<sec:authentication property="principal.mvo.lastLogin" var="authLastLogin" />
							<sec:authentication property="principal.mvo.authList" var="auths" />

							<c:choose>
								<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
									<li class="nav-item">
										<a class="nav-link" href="#">*ADMIN ${authNick}(${authEmail})</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" href="/member/list">Member List</a>
									</li>

								</c:when>
								<c:otherwise>
								
								</c:otherwise>
							</c:choose>

							<!-- 로그인 해야 open 되는 메뉴들... -->
	
							<form action="/member/logout" method="post" id="logoutForm">
								<input type="hidden" name="email" value="${authEmail}">
							</form>
							<li class="nav-item">
								<a class="nav-link" id="logoutLink" style="cursor: pointer">LogOut</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="/member/detail/${authEmail}">Member Info</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="/board/register">Board Register</a>
							</li>
						</sec:authorize>
						<li class="nav-item">
							<a class="nav-link" href="/board/list">Board List</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
</body>
<script type="text/javascript">
	document.getElementById('logoutLink').addEventListener('click', (e)=>{
		e.preventDefault();
		document.getElementById('logoutForm').submit();
	})
	
</script>
</html>