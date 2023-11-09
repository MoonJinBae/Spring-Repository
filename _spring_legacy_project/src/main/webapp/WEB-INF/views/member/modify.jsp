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
		<form action="/member/modify" method="post">
			<div class="inner-container">
				<div><h2>Update Info</h2></div>
				
				<div class="table-box">
					<table>
						<tr>
							<th>E-MAIL</th>
							<td><input type="text" name="email" value="${authEmail}" readonly="readonly"></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="text" name="pwd"></td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td><input type="text" name="nickName" value="${authNick}"></td>
						</tr>
					</table>
					
				</div>
				<div>
					<button class="button" type="submit">수정완료</button>
					<a href="/member/remove/${authEmail}"><button class="button" type="button">회원탈퇴</button></a>
				</div>
				
			</div>
		</form>
		
	</div>	
	
</body>
</html>