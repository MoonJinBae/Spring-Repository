package com.myweb.www.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment/*")
public class CommentController {

	@Inject
	private CommentService csv;
	
	@PostMapping(value = "post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		int isOk = csv.addComment(cvo);
		return isOk > 0 ? new ResponseEntity<String> ("1", HttpStatus.OK) :new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping(value = "{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> spreadCommentList(@PathVariable("bno")long bno, @PathVariable("page")int page){
		
		log.info("bno >> " + bno + " page >> " + page);
		PagingVO pgvo = new PagingVO(page, 5);
		
		return  new ResponseEntity<PagingHandler>(csv.getList(bno, pgvo), HttpStatus.OK);
	}
	
	@DeleteMapping(value ="{cno}/{writer}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> delete(@PathVariable("cno")int cno, @PathVariable("writer")String writer
			, HttpServletRequest request, Principal principal){
		log.info("댓글 삭제 cvo >> " + cno + " principal Name >> " + principal.getName());
		int isOK = 0;
		if(writer.equals(principal.getName())) {
			isOK = csv.delete(cno);
		}
		return isOK > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR); 
		
	}
	
	@PutMapping(value="{cno}/{writer}", consumes="application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@RequestBody CommentVO cvo, Principal principal){
		log.info("댓글 수정 cvo >> " + cvo + " principal Name >> " + principal.getName());
		int isOk = 0;
		if(cvo.getWriter().equals(principal.getName())) {
			isOk = csv.update(cvo);
		}
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
}
