package com.moon.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moon.www.domain.BoardDTO;
import com.moon.www.domain.BoardVO;
import com.moon.www.domain.FileVO;
import com.moon.www.handler.FileHandler;
import com.moon.www.handler.PagingHandler;
import com.moon.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService bsv;

	private final FileHandler fh;
	
	@GetMapping("register")
	public void register() {
	}

	@PostMapping("register")
	public String regoster(BoardVO bvo, @RequestParam(value="files", required = false)MultipartFile[] files
			, RedirectAttributes re) {
		List<FileVO> flist = null;
		if (bvo.getTitle().isEmpty()) {
			re.addFlashAttribute("titleEmpty", 2); // 제목 미입력 시 오류 메시지 2 전달
			return "redirect:/board/register";
		} else {
			if(files[0].getSize() > 0) {
				// 파일이 null 이 아니면 파일 핸들러로 업로드후 리스트에 저장
				flist = fh.uploadFiles(files);
			}
			int isOk = bsv.registerBoardDTO(new BoardDTO(bvo, flist));
		}
		return "redirect:/board/list";
	}

	@GetMapping("list")
	public void boardList(Model model, PagingHandler ph) {
		List<BoardVO> bvoList = bsv.getBoardList(ph);

		model.addAttribute("bvoList", bvoList);
		int totalCount = bsv.getTotalCount(ph);
		ph = new PagingHandler(ph.getPageNo(), totalCount, ph.getType(), ph.getKeyword());
		model.addAttribute("ph", ph);
		
	}

	@GetMapping("detail")
	public void boardDetail(@RequestParam("bno")int bno, Model model) {
		BoardDTO bdto = bsv.getDetailAndFile(bno);
		model.addAttribute("bvo", bdto.getBvo());
		model.addAttribute("flist", bdto.getFvo());
	}
	
	@GetMapping("modify")
	public void getModify(@RequestParam("bno")int bno, Model model){
		BoardDTO bdto = bsv.getModifyAndFile(bno);
		model.addAttribute("bvo", bdto.getBvo());
		model.addAttribute("flist", bdto.getFvo());
	}
	
	@PostMapping("modify")
	public String postModify(BoardVO bvo, HttpServletRequest request,@RequestParam(value="files", required = false)MultipartFile[] files
			,RedirectAttributes re) {
		String email = request.getUserPrincipal().getName();
		List<FileVO> flist = null; // 기본 null 값으로 셋팅
		if(email.equals(bvo.getWriter())) {
			if(files[0].getSize() > 0) {
				// 첨부파일이 존재하면 파일 저장
				flist = fh.uploadFiles(files);
			}
			int isOk = bsv.editBoard(new BoardDTO(bvo, flist));
		} else {
			re.addFlashAttribute("Error", 2);
			return "redirect:/board/modify?bno=" + bvo.getBno();
		}
		return "redirect:/board/list";
	}
	
	@GetMapping(value="spreadFile/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BoardDTO> spreadFileList(@PathVariable("bno")int bno) {
		BoardDTO bdto = bsv.getModifyAndFile(bno);
		return new ResponseEntity<BoardDTO>(bdto, HttpStatus.OK);
	}
	
	@GetMapping("remove/{bno}/{writer}")
	public String boardDelete(@PathVariable("bno")int bno,@PathVariable("writer")String writer
			, HttpServletRequest request, RedirectAttributes re) {
		String email = request.getUserPrincipal().getName();
		log.info("principal Email >>>>> " + email);
		if(email.equals(writer)) {
			int isOk = bsv.remove(bno);
		} else {
			re.addFlashAttribute("Error", 2);
			return "redirect:/board/modify?bno=" + bno;
		}
		return "index";
	}
	
	@DeleteMapping(value="removeFile/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid){
		int isOk = bsv.deleteFile(uuid);
		
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
