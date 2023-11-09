<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />
	<sec:authorize access="isAuthenticated()">
	<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open -->
		<sec:authentication property="principal.mvo.email" var="authEmail" />
		<sec:authentication property="principal.mvo.nickName" var="authNick" />
		<sec:authentication property="principal.mvo.authList" var="auths" />
		
	</sec:authorize>
	<div class="all-container">
	
		<div class="inner-container">
			<div><h2>Board Detail</h2></div>
			
			<div class="table-box table-register">
				<table>
					<tr>
						<th>#</th>
						<td><input type="text" name="bno" value="${bvo.bno}" readonly="readonly"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="writer" value="${bvo.writer}" readonly="readonly"></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="${bvo.title}" readonly="readonly"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" readonly="readonly">${bvo.content}</textarea></td>
					</tr>
					<tr>
						<th>작성일</th>
						<td><input type="text" value="${bvo.regDate}" readonly="readonly"></td>
					</tr>
					<tr>
						<th>수정일</th>
						<td><input type="text" value="${bvo.modDate}" readonly="readonly"></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td><input type="text" value="${bvo.hit}" readonly="readonly"></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<div class="detail-files">
								<table>
									<tr>
										<th>파일명</th>
										<th>파일크기</th>
										<th>업로드일자</th>
									</tr>
									
									<c:forEach items="${flist}" var="fvo">
									<tr>
										<td>
											<span><img src="/upload/${fn:replace(fvo.saveDir,'-','/')}/${fvo.uuid}_th_${fvo.fileName}">${fvo.fileName}</span>
										</td>
										<td>
											<span>${fvo.fileSize}(BYTE)</span>
										</td>
										<td>
											<span>${fvo.regDate}</span>
										</td>
									</tr>
									</c:forEach>
									
								</table>
								
							</div>
						</td>
					</tr>
				</table>
				
			</div>
			
			<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open -->
			<sec:authorize access="isAuthenticated()">
				<!-- 댓글 영역 -->
				<div>
					<table id="commentTable">
						<tr>
							<th>Comment</th>
						</tr>
						<tr>
							<td>
								<div class="input-box">
									<input type="hidden" id="bno" value="${bvo.bno}">
									<input type="hidden" id="cmtWriter" value="${authEmail}">
									<span>${authNick}</span>
									<input type="text" id="cmtContent">
									<button type="button" id="postBtn" class="button comment-btn">댓글등록</button>
								</div>								
							</td>
						</tr>
					</table>
				</div>
			</sec:authorize>
			
			<!-- 댓글 표시 영역 -->
			<div id="commentArea"></div>
			
			<sec:authorize access="isAuthenticated()">
				
				
				<c:if test="${authEmail eq bvo.writer}">
					<div>
						<a href="/board/modify?bno=${bvo.bno}"><button class="button" type="button">글수정</button></a>
					</div>
				</c:if>
			</sec:authorize>
			
		</div>
		
	</div>	
	
	<!-- 댓글 수정 영역 -->
	<div id="commentModArea" class="comment-modal" style="display:none"></div>
	
</body>
<script type="text/javascript">
	let bnoVal = `<c:out value="${bvo.bno}" />`;
	let cmtWriter = `<c:out value="${authEmail}" />`;
	let cmtNick = `<c:out value="${authNick}" />`;
</script>
<script type="text/javascript" src="/resources/JS/boardComment.js"></script>
<script type="text/javascript">
	getCommentList(bnoVal);
</script>
</html>