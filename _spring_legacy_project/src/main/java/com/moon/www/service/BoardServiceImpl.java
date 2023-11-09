package com.moon.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moon.www.domain.BoardDTO;
import com.moon.www.domain.BoardVO;
import com.moon.www.domain.FileVO;
import com.moon.www.handler.PagingHandler;
import com.moon.www.repository.BoardDAO;
import com.moon.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardDAO bdao;

	private final FileDAO fdao;
	@Override
	public int register(BoardVO bvo) {
		return bdao.register(bvo);
	}

//	@Override
//	public List<BoardVO> getBoardList() {
//		// TODO Auto-generated method stub
//		return bdao.getBoardList();
//	}

	@Override
	public List<BoardVO> getBoardList(PagingHandler ph) {
		return bdao.getBoardList(ph);
	}

	@Override
	public int getTotalCount(PagingHandler ph) {
		return bdao.getTotalCount(ph);
	}

	@Override
	@Transactional
	public BoardVO getDetail(int bno) {
		bdao.upHit(bno);
		return bdao.getBvo(bno);
	}

//	@Override
//	public BoardVO boardModify(int bno) {
//		return bdao.getBvo(bno);
//	}

	@Override
	public int Modify(BoardVO bvo) {
		return bdao.Modify(bvo);
	}

	@Override
	public int remove(int bno) {
		return bdao.remove(bno);
	}

	@Override
	@Transactional
	public int registerBoardDTO(BoardDTO bdto) {
		// 최종 register 글, 파일 같이 등록
		int isOk = register(bdto.getBvo());
		if(bdto.getFvo() != null) {
			int bno = bdao.getBno();
			for (FileVO fvo : bdto.getFvo()) {
				fvo.setBno(bno);
				isOk *= fdao.uploadFile(fvo);
			}
		}
		return isOk;
	}
	
	@Override
	public BoardDTO getDetailAndFile(int bno) {
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(getDetail(bno));
		bdto.setFvo(fdao.getFiles(bno));
		return bdto;
	}

	@Override
	public BoardDTO getModifyAndFile(int bno) {
		BoardDTO bdto = new BoardDTO(bdao.getBvo(bno)
				,fdao.getFiles(bno));
		return bdto;
	}

	@Override
	public int deleteFile(String uuid) {
		return fdao.deleteFile(uuid);
	}

	@Override
	@Transactional
	public int editBoard(BoardDTO bdto) {
		// 최종 modify 글, 파일 같이 수정
		if(bdto.getFvo() != null) {
			for(FileVO fvo : bdto.getFvo()) {
				fvo.setBno(bdto.getBvo().getBno());
				fdao.uploadFile(fvo);
			}
		}
		return bdao.Modify(bdto.getBvo());
	}

	
}
