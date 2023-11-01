package com.myweb.www.handler;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingHandler {
	
	// 1~10, 11~20, 21~30 ...
	private int startPage; // 화면에 보여지는 시작 페이지네이션 번호
	private int endPage; // 화면에 보여지는 마지막 페이지네이션 번호
	private int pageQty = 10; // 화면에 보여질 페이지네이션 번호 개수
	private boolean prev, next; // 이전, 다음 버튼 존재여부
	
	private int totalCount; // 총 글수
	private PagingVO pgvo;
	
	private List<CommentVO> cmtList;
	
	// 현재 페이지 값 가져오는 용도 / totalCount DB에서 조회 매개변수로 입력받기
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		int realEndPage; // 실제 마지막 번호
		
		// 1페이지부터 10페이지까지 어떤 페이지가 선택되도 endPage는 10
		// 1 ~ 10까지 해당값을 10으로 나누어서 나머지를 ceil로 올려서 * pageQty
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / 10.0) * pageQty;
		this.startPage = this.endPage - (pageQty - 1);
		
		// 전체 글수에서 / 한페이지에 표시되는 게시글수 ceil로 올림
		realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
		
	}
	
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList){
		this(pgvo, totalCount);
		this.cmtList = cmtList;
		
	}
	
}
