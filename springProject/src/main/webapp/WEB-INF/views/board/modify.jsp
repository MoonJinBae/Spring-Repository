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
		width: 900px;
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
	table>tbody>tr>td>input{
		width: 100%;
		text-align: center;
		border-radius: 5px;
	}
	
</style>
</head>
<body>
	<div class="all-container">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	
			<form action="/board/update" method="post" enctype="multipart/form-data">
				<div class="position-absolute top-50 start-50 translate-middle">
					<div class="table-box">
						<div class="title"><h2>글수정</h2></div>
						<table id="table" class="table table-dark table-hover">
							<tr>
								<th>#</th>
								<td>
									<input type="text" name="bno" readonly="readonly" value="${bvo.bno}">
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td>
									<input type="text" name="title" value="${bvo.title}">
								</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>
									<input type="text" readonly="readonly" name="writer" value="${bvo.writer}">
								</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<textarea class="form-control" aria-label="With textarea" name="content"></textarea>
								</td>
							</tr>
							<!-- 등록파일 목록 -->
							<tr>
								<td colspan="2">
									<dl id="files-ul">
									</dl>
								</td>
							</tr>
							
							<!-- 파일 업로드 영역 -->
							<tr>
								<td colspan="2">
									<div class="input-group mb-3">
									  <input type="file" class="form-control" multiple="multiple" style="display:none" id="files" name="files" aria-describedby="basic-addon1">
									  <!-- input button trigger 용도 -->
									</div>
									<div class="fileupload-btn-box">
										<button type="button" id="trigger" class="btn btn-secondary">파일첨부</button>
									</div>
									
									<!-- 첨부파일 표시 영역 -->
									<div class="input-group mb-3" id="file-zone">
									</div>
								</td>
							</tr>
						</table>
					</div>
					
					<div class="button-box">
						<div>
							<button type="submit" id="button" class="btn btn-outline-secondary">수정</button>
							<a href="/board/remove?bno=${bvo.bno}"><button type="button" id="button" class="btn btn-outline-secondary">삭제</button></a>
						</div>
					</div>
					
				</div>
			</form>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript">
	let bnoVal = `<c:out value="${bvo.bno}" />`;
	console.log(bnoVal);
</script>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<script type="text/javascript" src="/resources/js/boardFile.js" ></script>
<script type="text/javascript">
	getFileList(bnoVal);
</script>
</html>