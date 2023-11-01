package com.myweb.www.service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentService {

	int addComment(CommentVO cvo);

//	List<CommentVO> getList(int bno);

	int delete(int cno);

	int update(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);

}
