package com.moon.www.repository;

import java.util.List;

import com.moon.www.domain.CommentVO;

public interface CommentDAO {

	int registerComment(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int update(CommentVO cvo);

	int deleteComment(int cno);

	int upLikeCount(int cno);

}
