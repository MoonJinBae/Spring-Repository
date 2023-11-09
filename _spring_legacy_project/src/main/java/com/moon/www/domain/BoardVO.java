package com.moon.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardVO {
	
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String regDate;
	private String modDate;
	private int hit; // 조회수
	private int fileCount; // 첨부파일 개수
	private int cmtCount; // 댓글 개수
	
}
