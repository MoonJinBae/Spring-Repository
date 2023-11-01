package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

	@Inject
	private CommentDAO cdao;

	@Override
	public int addComment(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.addComment(cvo);
	}

//	@Override
//	public List<CommentVO> getList(int bno) {
//		// TODO Auto-generated method stub
//		return cdao.getList(bno);
//	}

	@Override
	public int delete(int cno) {
		// TODO Auto-generated method stub
		return cdao.delete(cno);
	}

	@Override
	public int update(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}

	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// totalCount 가져오기
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		// Comment List 가져오기
		List<CommentVO> list = cdao.selectListPaging(bno, pgvo);
		// PagingHandler 값 완성해서 return
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}
	
	
}
