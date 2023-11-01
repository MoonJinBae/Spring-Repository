package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	// 폴더명 : board, mapping : board => 맵핑경로와 폴더경로가 일치하면 void로 처리가능
	// mapping => /board/register
	// 목적지 => /board/register
	@Inject
	private BoardService bsv;
	
	@Inject
	private FileHandler fh;
	
	private int isOk;
	
	@GetMapping("register")
	public void register(){
//		log.info("register page 이동");
	}
	
	@PostMapping("register")
	public String register(BoardVO bvo, RedirectAttributes re, @RequestParam(name="files", required = false)MultipartFile[] files){
		List<FileVO> flist = null;
		// file upload handler 생성
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		log.info("files >> " + flist);
//		log.info("글쓰기 >> " + (isOk > 0 ? "성공" : "실패"));
		isOk = bsv.register(new BoardDTO(bvo, flist));
		return "redirect:/board/list";
	}
	
	@GetMapping("list")
	public void getList(Model model, PagingVO pagingVO) {
//		log.info("List Page >>");
		log.info("pagingVO >>" + pagingVO);
		List<BoardVO> list = bsv.getList(pagingVO);
		model.addAttribute("list", list);
		
		// 페이징 처리
		// 총 페이지 개수 totalCount
		int totalCount = bsv.getTotalCount(pagingVO);
		PagingHandler ph = new PagingHandler(pagingVO, totalCount);
		model.addAttribute("ph", ph);
	}
	
	@GetMapping("detail")
	public void getDetail(Model model, @RequestParam("bno")int bno) {
		
		log.info("detail bno >> " + bno);
//		BoardVO bvo = bsv.getDetail(bno);
		BoardDTO bdto = bsv.getDetailFile(bno);
		model.addAttribute("bvo", bdto.getBvo());
		model.addAttribute("flist", bdto.getFlist());
	}
	
	// 화면에 비동기로 파일목록 뿌리기
	@GetMapping(value="spread/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FileVO>> spread(@PathVariable("bno")int bno){
		BoardDTO bdto = bsv.getModify(bno); // 
		return new ResponseEntity<List<FileVO>>(bdto.getFlist(), HttpStatus.OK);
	}
	
	// 파일 삭제
	@DeleteMapping(value="{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteFile(@PathVariable("uuid")String uuid){
		isOk = bsv.deleteFile(uuid);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("modify")
	public void getModify(Model model, @RequestParam("bno")int bno) {
		log.info("modify bno >> " + bno);
		BoardDTO bdto = bsv.getModify(bno);
		model.addAttribute("bvo", bdto.getBvo());
		model.addAttribute("flist", bdto.getFlist());
	}
	
	@PostMapping("update")
	public String update(BoardVO bvo, RedirectAttributes re, @RequestParam(name="files", required = false)MultipartFile[] files) {
		List<FileVO> flist = null;
		// file upload handler 생성
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
//		log.info("글수정 요청");
		isOk = bsv.update(new BoardDTO(bvo, flist));
		re.addAttribute("bno",bvo.getBno());
		re.addFlashAttribute("isOk", isOk);
//		log.info("글수정 >> " + (isOk > 0 ? "성공" : "실패"));
		return "redirect:/board/detail";
	}
	
	@GetMapping("remove")
	public String remove(@RequestParam("bno")int bno) {
		isOk = bsv.remove(bno);
		log.info("글삭제 >> " + (isOk > 0 ? "성공" : "실패"));
		return "redirect:/board/list";
	}
	
	
	
	
	
	
	
}
