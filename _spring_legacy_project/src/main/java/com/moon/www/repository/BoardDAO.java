package com.moon.www.repository;

import java.util.List;

import com.moon.www.domain.BoardVO;
import com.moon.www.handler.PagingHandler;

public interface BoardDAO {

	int register(BoardVO bvo);

	List<BoardVO> getBoardList(PagingHandler ph);

	int getTotalCount(PagingHandler ph);

	void upHit(int bno);

	BoardVO getBvo(int bno);

	int Modify(BoardVO bvo);

	int remove(int bno);

	int getBno();

	
}
