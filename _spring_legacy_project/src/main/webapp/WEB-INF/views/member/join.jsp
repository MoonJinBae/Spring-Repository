<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="all-container">
	
		<jsp:include page="../common/nav.jsp" />
		
		<form action="/member/join" method="post">
			<div class="inner-container input-box">
				<div><h2>SIGNUP</h2></div>
				<div>
					<span>E-MAIL</span>
					<input type="text" name="email">
				</div>
				<div>
					<span>비밀번호</span>
					<input type="text" name="pwd">
				</div>
				<div>
					<span>닉네임</span>
					<input type="text" name="nickName">
				</div>
				<div>
					<button class="button" type="submit">가입</button>
				</div>
			</div>
		</form>
	</div>
	
</body>
</html>