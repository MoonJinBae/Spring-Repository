package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO bdao;
	
	private int isOk;
	
	@Inject
	private FileDAO fdao;
	
	@Override
	public int register(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.register(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pagingVO) {
		return bdao.getList(pagingVO);
	}

	@Transactional
	@Override
	public BoardVO getDetail(int bno) {
		// TODO Auto-generated method stub
		isOk = bdao.readCount(bno);
		log.info("조회수 >> " + (isOk > 0 ? "UP" : "ERROR"));
		return bdao.getDetail(bno);
	}
	

	@Override
	public BoardDTO getModify(int bno) {
		// TODO Auto-generated method stub
		return new BoardDTO(bdao.getDetail(bno), fdao.getFileList(bno));
	}
	
	@Override
	public int update(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		// TODO Auto-generated method stub
		return bdao.remove(bno);
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pagingVO);
	}

	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		// 기존 메서드 활용
		int isUp = bdao.register(bdto.getBvo());
		if(bdto.getFlist() == null) {
			isUp *= 1;
		}else {
			// bvo insert 후, 파일도 있다면...
			if(isUp > 0 && bdto.getFlist().size() > 0) {
				long bno = bdao.selectOneBno(); // 가장 마지막에 등록된 bno
				// 모든 파일에 bno set
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isUp *= fdao.insertFile(fvo);
				}
			}
		}
		return isUp;
	}

	@Override
	public BoardDTO getDetailFile(int bno) {
//		BoardDTO bdto = new BoardDTO();
//		bdto.setBvo(bdao.getDetail(bno));
//		List<FileVO> flist = fdao.getFileList(bno);
//		bdto.setFlist(flist);
//		log.info(" flist >> " + flist.get(0));
		return new BoardDTO(getDetail(bno), fdao.getFileList(bno));
	}

	@Override
	public int deleteFile(String uuid) {
		return fdao.deleteFile(uuid);
	}

	// 파일 까지 수정
	@Transactional
	@Override
	public int update(BoardDTO bdto) {
		int isUp = bdao.update(bdto.getBvo());
		if(bdto.getFlist() == null) {
			isUp *= 1;
		}else {
			// bvo update 후, 파일도 있다면...
			if(isUp > 0 && bdto.getFlist().size() > 0) {
				long bno = bdto.getBvo().getBno(); // bdto에서 받아온 bno로 셋팅
				// 모든 파일에 bno set
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isUp *= fdao.insertFile(fvo);
				}
			}
		}
		return isUp;
	}



}
