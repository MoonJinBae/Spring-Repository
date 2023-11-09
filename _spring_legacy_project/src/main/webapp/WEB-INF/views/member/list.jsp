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
			<div><h2>Member List</h2></div>
			
			<div class="table-box">
				<table>
					<tr>
						<th>E-MAIL</th>
						<th>닉네임</th>
						<th>가입일자</th>
						<th>최근접속일</th>
					</tr>
					<c:forEach items="${mvoList}" var="mvo">
					<tr>
						<td>${mvo.email}</td>
						<td>${mvo.nickName}</td>
						<td>${mvo.regDate}</td>
						<td>${mvo.lastLogin}</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
		
	</div>	
	
</body>
</html>