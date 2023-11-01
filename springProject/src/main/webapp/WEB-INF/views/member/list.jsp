<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="all-container">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	
	<sec:authentication property="principal.mvo.email" var="authEmail" />
	<sec:authentication property="principal.mvo.nickName" var="authNick" />
	<sec:authentication property="principal.mvo.authList" var="auths" />
	
		<div class="inner-container">
			<div class="position-absolute top-50 start-50 translate-middle">
				<div class="table-box">
					<div class="title">
						<h2>멤버 목록</h2>
					</div>
					
					<table id="table" class="table table-dark table-hover">
						<c:forEach items="${mlist}" var="mvo">
						<tr>
							<th>E-mail</th>
							<td>${mvo.email}</td>
							<th>Nick Name</th>
							<td>${mvo.nickName}</td>
							<th>가입일자</th>
							<td>${mvo.regAt}</td>
						</tr>
						</c:forEach>
						
					</table>

				</div>

			</div>


		</div>

		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
</html>