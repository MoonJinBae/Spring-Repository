package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pagingVO);

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	int remove(int bno);

	BoardDTO getModify(int bno);

	int getTotalCount(PagingVO pagingVO);

	int register(BoardDTO boardDTO);

	BoardDTO getDetailFile(int bno);

	int deleteFile(String uuid);

	int update(BoardDTO boardDTO);



}
