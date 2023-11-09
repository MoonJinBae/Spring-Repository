package com.moon.www.service;

import java.util.List;

import com.moon.www.domain.CommentVO;

public interface CommentService {

	int registerComment(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int modifyComment(CommentVO cvo);

	int deleteComment(int cno);

	int likeCount(int cno);
	
}
