<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.table-box{
		width: 500px;
	}
	#delBtn{
		margin-left:10px;
	}
</style>
</head>
<body>
	<div class="all-container">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	
		<div class="inner-container">
			<form action="/member/modify" method="post">
				<div class="position-absolute top-50 start-50 translate-middle">
				
					<div class="table-box">
						<div class="title">
							<h2>회원 정보 수정</h2>
						</div>
						<table id="table" class="table table-dark table-hover">
							<tr>
								<th>E-mail</th>
								<td><input type="text" name="email" value="${mvo.email}" readonly="readonly"></td>
							</tr>
							<tr>
								<th>PassWord</th>
								<td><input type="password" name="pwd" placeholder="변경할 PassWord 입력"></td>
							</tr>
							<tr>
								<th>Nick Name</th>
								<td><input type="text" name="nickName" value="${mvo.nickName}"></td>
							</tr>
							
						</table>
						<div class="button-box">
							<button type="submit" id="modBtn" class="btn btn-outline-secondary">수정</button>
							<a href="/member/remove/${mvo.email}"><button type="button" id="delBtn" class="btn btn-outline-secondary">탈퇴</button></a>
						</div>
						
					</div>
	
				</div>
			</form>


		</div>

		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript">
	let pwdError = `<c:out value="${pwdError}" />`;
	if(pwdError == 2){
		alert("비밀번호를 입력 하세요.");
	}
</script>
</html>