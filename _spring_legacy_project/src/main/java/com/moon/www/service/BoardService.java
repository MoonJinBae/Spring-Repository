package com.moon.www.service;

import java.util.List;

import com.moon.www.domain.BoardDTO;
import com.moon.www.domain.BoardVO;
import com.moon.www.handler.PagingHandler;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getBoardList(PagingHandler ph);

	int getTotalCount(PagingHandler ph);

	BoardVO getDetail(int bno);

	int Modify(BoardVO bvo);

	int remove(int bno);

	int registerBoardDTO(BoardDTO bdto);

	BoardDTO getDetailAndFile(int bno);

	BoardDTO getModifyAndFile(int bno);

	int deleteFile(String uuid);

	int editBoard(BoardDTO bdto);

}
