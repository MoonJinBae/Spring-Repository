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
	
	<div class="all-container">
		
		<div class="inner-container">
			<div><h2>Member Info</h2></div>
			
			<div class="table-box table-mid">
				<table>
					<tr>
						<th>E-MAIL</th>
						<td>${mvo.email}</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>${mvo.nickName}</td>
					</tr>
					<tr>
						<th>가입일자</th>
						<td>${mvo.regDate}</td>
					</tr>
					<tr>
						<th>최근접속일</th>
						<td>${mvo.lastLogin}</td>
					</tr>
				</table>
			</div>
			
			<div>
				<a href="/member/modify/${mvo.email}"><button class="button" type="button">정보수정</button></a>
				<a href="/member/remove/${mvo.email}"><button class="button" type="button">회원탈퇴</button></a>
			</div>
			
		</div>
		
	</div>	

	
</body>
</html>