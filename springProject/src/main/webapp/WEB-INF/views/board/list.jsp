<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<jsp:include page="../common/header.jsp"></jsp:include>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		
		<div class="position-absolute top-50 start-50 translate-middle">
			<form action="/board/list">
				<div class="search-box">
					<!-- 검색 영역 -->
					<div>
					<c:set value="${ph.pgvo.type}" var="typed" />
						<select  class="form-select" aria-label="Default select example" name="type">
						  <option ${empty typed ? 'selected' : ''}>Search Type</option>
						  <option value="t" ${typed eq 't' ? 'selected' : ''}>Title</option>
						  <option value="w" ${typed eq 'w' ? 'selected' : ''}>Writer</option>
						  <option value="c" ${typed eq 'c' ? 'selected' : ''}>Content</option>
						  <option value="tw" ${typed eq 'tw' ? 'selected' : ''}>Title+Writer</option>
						  <option value="tc" ${typed eq 'tc' ? 'selected' : ''}>Title+Content</option>
						  <option value="cw" ${typed eq 'cw' ? 'selected' : ''}>Content+Writer</option>
						  <option value="twc" ${typed eq 'twc' ? 'selected' : ''}>All</option>
						</select>
					</div>
					<div>
				        <input name="keyword" value="${ph.pgvo.keyword}" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
				        <input type="hidden" name="pageNo" value="1"> <!-- type 검색시 첫 페이지 화면으로 띄우기 -->
				        <input type="hidden" name="qty" value="${ph.pgvo.qty}">
					</div>
					<div>
		        		<button class="btn btn-primary position-relative btn-outline-secondary text-bg-secondary" type="submit">Search
		        		  <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill text-bg-Light">
						    ${ph.totalCount}
						    <span class="visually-hidden">unread messages</span>
						  </span>
		        		</button>
					</div>
				</div>
			</form>
			<div class="table-box">
				<table id="table" class="table table-dark table-hover">
					<tr>
						<th>#</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>수정일</th>
						<th>조회수</th>
						<th>댓글수</th>
						<th>파일수</th>
					</tr>
					<c:forEach items="${list}" var="bvo">
					<tr data-bno="${bvo.bno}">
						<td>
							<a id="${bvo.bno}" href="/board/detail?bno=${bvo.bno}">${bvo.bno}</a>
						</td>
						<td>
							${bvo.title}
						</td>
						<td>
							${bvo.writer}
						</td>
						<td>
							${bvo.regAt}
						</td>
						<td>
							${bvo.modAt}
						</td>
						<td>
							${bvo.readCount}
						</td>
						<td>
							${bvo.cmtQty}
						</td>
						<td>
							${bvo.fileCount}
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			
			<div class="paging-box">
				<!-- 페이징 영역 -->
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  	<!-- prev -->
				    <li class="page-item ${(ph.prev eq false) ? 'disabled' : ''}" >
				      <a class="page-link" href="/board/list?pageNo=${ph.startPage - 1}&qty=${ph.pageQty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <!-- pageNo -->
				    <c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
				    <li class="page-item"><a id="${ph.pgvo.pageNo eq i ? 'clicked' : ''}" class="page-link" href="/board/list?pageNo=${i}&qty=${ph.pageQty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a></li>
				    </c:forEach>
				    
					<!-- next -->
				    <li class="page-item ${(ph.next eq false) ? 'disabled' : ''}">
				      <a class="page-link" href="/board/list?pageNo=${ph.endPage + 1}&qty=${ph.pageQty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
			</div>
			
			
		</div>
		
		
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript">
	document.addEventListener('click', (e)=>{
		let tr = e.target.closest('tr');
		let bno = tr.dataset.bno;
		console.log(bno);
		if(tr){
			document.getElementById(bno).click();
		}
	})
</script>
</html>