<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#sign-btn{
		color:#fff;
	}
</style>
</head>
<body>
	<div class="all-container">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
		<form action="/member/register" method="post">
			<div class="position-absolute top-50 start-50 translate-middle input-box">
				<div class="title"><h2>회원가입</h2></div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">E-MAIL(ID)</span>
				  <input type="text" class="form-control" placeholder="E-MAIL" name="email" aria-describedby="basic-addon1">
				</div>
				
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">PW</span>
				  <input type="text" class="form-control" placeholder="PASSWORD" name="pwd" aria-describedby="basic-addon1">
				</div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">닉네임</span>
				  <input type="text" class="form-control" placeholder="닉네임" name="nickName" aria-describedby="basic-addon1">
				</div>
				
				<div class="button-box">
					<button type="submit" id="sign-btn" class="btn btn-outline-secondary">회원가입</button>
				</div>
				
			</div>
		</form>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
</html>