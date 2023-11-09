<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#clicked{
		color:red;
	}
</style>
</head>
<body>
	<div class="all-container">
		<jsp:include page="../common/nav.jsp" />
		
		<div class="inner-container">
			<div><h2>Board Register</h2></div>
			
			<!-- search -->
			<form action="/board/list">
				<div class="search-box">
				<c:set value="${ph.type}" var="typed" />
					<select class="select" name="type">
						<option ${empty typed ? 'selected' : ''}>TYPE</option>
						<option value="t" ${typed eq 't' ? 'selected' : ''}>제목</option>
						<option value="w" ${typed eq 'w' ? 'selected' : ''}>작성자</option>
						<option value="c" ${typed eq 'c' ? 'selected' : ''}>내용</option>
						<option value="tw" ${typed eq 'tw' ? 'selected' : ''}>제목+작성자</option>
						<option value="tc" ${typed eq 'tc' ? 'selected' : ''}>제목+내용</option>
						<option value="wc" ${typed eq 'wc' ? 'selected' : ''}>작성자+내용</option>
						<option value="twc" ${typed eq 'twc' ? 'selected' : ''}>ALL</option>
					</select>
					
					<input type="hidden" name="pageNo" value="1" >
					<input type="text" class="search-input" name="keyword" value="${ph.keyword}" placeholder="검색어 입력">
					<button class="button search-button" type="submit">검색</button>
				</div>
			</form>
			
			
			<div class="table-box table-large">
				<table>
					<tr>
						<th>#</th>
						<th>작성자</th>
						<th>제목</th>
						<th>작성일</th>
						<th>수정일</th>
						<th>파일수</th>
						<th>조회수</th>
						<th>댓글수</th>
					</tr>
					<c:forEach items="${bvoList}" var="bvo">
					<tr style="cursor:pointer" data-bno="${bvo.bno}">
						<td><a id="bno${bvo.bno}" href="/board/detail?bno=${bvo.bno}">${bvo.bno}</a></td>
						<td>${bvo.writer}</td>
						<td>${bvo.title}</td>
						<td>${bvo.regDate}</td>
						<td>${bvo.modDate}</td>
						<td>${bvo.fileCount}</td>
						<td>${bvo.hit}</td>
						<td>${bvo.cmtCount}</td>
					</tr>
					</c:forEach>

				</table>
				
			</div>
			
			<!-- 페이지네이션 -->
			<div id="pagenation">
				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<li class="page-item ${(ph.prev eq false) ? 'disabled' : ''}">
							<a class="page-link" href="/board/list?pageNo=${ph.startPageNo-1}&type=${ph.type}&keyword=${ph.keyword}">Prev</a>
						</li>

						<c:forEach begin="${ph.startPageNo}" end="${ph.endPageNo}" var="i">
							<li class="page-item" id="li">
								<a class="page-link" id="${ph.pageNo eq i ? 'clicked' : ''}" href="/board/list?pageNo=${i}&type=${ph.type}&keyword=${ph.keyword}">${i}</a>
							</li>
						</c:forEach>

						<li class="page-item ${(ph.next eq false) ? 'disabled' : ''}">
							<a class="page-link" href="/board/list?pageNo=${ph.endPageNo+1}&type=${ph.type}&keyword=${ph.keyword}">Next</a>
						</li>
					</ul>
				</nav>
			</div>

		</div>
		
	</div>	
</body>

<script type="text/javascript" src="/resources/JS/list.js"></script>
</html>