package com.moon.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moon.www.domain.CommentVO;
import com.moon.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	private final CommentDAO cdao;

	@Override
	public int registerComment(CommentVO cvo) {
		return cdao.registerComment(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		return cdao.getList(bno);
	}

	@Override
	public int modifyComment(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	public int deleteComment(int cno) {
		return cdao.deleteComment(cno);
	}

	@Override
	public int likeCount(int cno) {
		return cdao.upLikeCount(cno);
	}
	
}
