package com.moon.www.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PagingHandler {
	
	private int qty = 7; // 한 페이지에 보여질 게시글 수
	private int pageQty = 10; // 한 페이지에 보여질 페이지 개수
	private int pageNo; // 선택한 페이지 번호
	private int startPageNo; // 첫 번째 페이지 넘버
	private int endPageNo; // 마지막 페이지 넘버
	private boolean prev, next; // 이전, 다음 버튼
	private int totalCount; // 총 게시글 수
	
	private String type; // 검색 유형
	private String keyword; // 검색어
	
	public PagingHandler(){
		this.pageNo = 1;
	}
	
	public PagingHandler(int pageNo, int totalCount){
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		
		this.endPageNo = (int)Math.ceil(this.pageNo / 10.0) * this.pageQty;
		this.startPageNo = this.endPageNo - (this.pageQty - 1);
		
		int realEndPage = (int)Math.ceil(totalCount / (double)qty);
		
		// endPageNo가 실제 마지막페이지 보다 크면 바꿔줌
		if(this.endPageNo > realEndPage) this.endPageNo = realEndPage;
		
		this.prev = this.startPageNo > 1;
		this.next = (this.endPageNo < realEndPage);
	}
	public PagingHandler(int pageNo, int totalCount, String type, String keyword){
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.type = type;
		this.keyword = keyword;
		
		this.endPageNo = (int)Math.ceil(this.pageNo / 10.0) * this.pageQty;
		this.startPageNo = this.endPageNo - (this.pageQty - 1);
		
		int realEndPage = (int)Math.ceil(totalCount / (double)qty);
		
		// endPageNo가 실제 마지막페이지 보다 크면 바꿔줌
		if(this.endPageNo > realEndPage) this.endPageNo = realEndPage;
		
		this.prev = this.startPageNo > 1;
		this.next = (this.endPageNo < realEndPage);
	}
	
	// limit 시작, qty : 시작 페이지 번호 구하기
	// pageNo 1 2 3 4
	// 0,10 / 10,10 / 20,10 / 30,10 ...
	public int getPageStart() {
		return (this.pageNo - 1) * qty; // DB에서 찾아올 limit 스타트 넘버
	}
	
	// 타입의 형태를 여러가지 형태로 복합적인 검색을 하기 위해서
	// 타입의 키워드 t, c, w, tc, tw, cw, tcw
	// 복합타입을 베열로 나누기 위해 사용
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	
	
	
}
