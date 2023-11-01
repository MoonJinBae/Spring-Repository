<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.table-box{
		width: 900px;
	}
	.button-box{
		margin-bottom:15px;
	}
	#comment-table>tbody>tr>th{
	text-align: center;
	}
	#comment-table>tbody>tr>td{
		text-align: center;
	}
	#comment-input{
		margin-bottom:15px;
	}
	.delBtn, .modBtn, #file-mod-bt{
		margin-right:5px;
	}
	.file-div{
		display: flex;
		justify-content: space-between;
	}
	.file-div>div>span{
		margin-right:15px;
	}
	.tumbnail>img{
		border-radius: 5px;
		width: 25px;
		height:25px;
	}
</style>

</head>
<body>
	<div class="all-container">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<sec:authorize access="isAuthenticated()">
		${principal}
		<sec:authentication property="principal.mvo.email" var="authEmail" />
		<sec:authentication property="principal.mvo.nickName" var="authNick" />
	</sec:authorize>
	
		<div class="inner-container">
			<div class="position-absolute top-50 start-50 translate-middle">
				<div class="table-box">
					<div class="title">
						<h2>게시글</h2>
					</div>
					<table id="table" class="table table-dark table-hover">
						<tr>
							<th>#</th>
							<td>${bvo.bno}</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>${bvo.title}</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${bvo.writer}</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>${bvo.content}</td>
						</tr>
						<tr>
							<th>조회수</th>
							<td>${bvo.readCount}</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td>${bvo.hasFile}</td>
						</tr>
						
						<!-- 등록파일 목록 -->
						<tr>
							<td colspan="2">
								<dl id="files-ul">
								</dl>
							</td>
						</tr>
						
					</table>
					<c:if test="${authEmail eq bvo.writer}">
						<div class="button-box">
							<a href="/board/modify?bno=${bvo.bno}"><button type="button" id="button" class="btn btn-outline-secondary">글수정</button></a>
						</div>
					</c:if>
					
					<!-- 댓글 영역 -->
					<div class="comment-box">
						<div class="input-group flex-nowrap" id="comment-input">
							<c:if test="${not empty authEmail}">
							<span class="input-group-text" id="cmtWriter">
								${authEmail}
							</span> 
							<input type="text" class="form-control" id="cmtContent" placeholder="댓글 입력" aria-label="Username" aria-describedby="addon-wrapping">
							<button class="btn btn-outline-secondary" type="button" id="cmtPostBtn">댓글등록</button>
							</c:if>
						</div>
						<div class="title">
							<h3>댓글</h3>
						</div>
						<table id="comment-table" class="table table-dark table-hover">
						</table>
					</div>
					
					<!-- 댓글 더보기 영역 -->
					<div>
						<div>
							<button type="button" id="moreBtn" data-page="1" class="btn btn-secondary" style="visibility:hidden">MORE</button>
						</div>
					</div>

				</div>

			</div>

			<!-- 모달창 -->
			<div class="modal" id="comment-modal" tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">댓글수정</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						
						<div class="modal-body">
							<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon1">${bvo.writer}</span> 
								<input type="text" id="modal-text" class="form-control" placeholder="댓글 입력" aria-label="Username" aria-describedby="basic-addon1">
							</div>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-primary update-comment">수정</button>
						</div>
					</div>
				</div>
			</div>

		</div>

		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript">
	let isOk = `<c:out value="${isOk}" />`;
	if (parseInt(isOk)) {
		alert("수정 성공!!");
	}
	let bnoVal = `<c:out value="${bvo.bno}" />`;
	let email = `<c:out value="${authEmail}" />`;
	let writer = `<c:out value="${bvo.writer}" />`;
	
	console.log(bnoVal);
</script>
<script type="text/javascript" src="/resources/js/boardFile.js?ver=5" ></script>
<script type="text/javascript" src="/resources/js/boardComment.js?ver=5" ></script>
<script type="text/javascript">
	getCommentList(bnoVal);
	getFileList(bnoVal);
</script>
</html>