package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pagingVO);

	BoardVO getDetail(int bno);

	int readCount(int bno);

	int update(BoardVO bvo);

	int remove(int bno);

	int getTotalCount(PagingVO pagingVO);

	long selectOneBno();


}
