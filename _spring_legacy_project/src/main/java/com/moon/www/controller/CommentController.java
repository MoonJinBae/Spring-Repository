package com.moon.www.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moon.www.domain.CommentVO;
import com.moon.www.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment/*")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService csv;
	
	@GetMapping(value="list/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVO>> commentList(@PathVariable("bno")int bno){
		return new ResponseEntity<List<CommentVO>>(csv.getList(bno), HttpStatus.OK);
	}
	
	@PostMapping(value="post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> registerComment(@RequestBody CommentVO cvo){
		int isOk = csv.registerComment(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value="put", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> putComment(@RequestBody CommentVO cvo){
		int isOk = csv.modifyComment(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="delete/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteComment(@PathVariable("cno")int cno){
		int isOk = csv.deleteComment(cno);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="like/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> upLikeCount(@PathVariable("cno")int cno){
		int isOk = csv.likeCount(cno);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
