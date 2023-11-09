package com.moon.www.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {
	// 500 에러
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		log.info(">>>>>>>>>>>> Exception Exception Exception >> " + ex);
		model.addAttribute("errMsg", "500 Server ERROR"); 
		return "customError";
	}
	@ResponseStatus(HttpStatus.NOT_FOUND) // 400에러
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handler400(NoHandlerFoundException ex, Model model) {
		log.info(">>>>>>>>>>>> Exception Exception Exception >> " + ex);
		model.addAttribute("errMsg", "400 Request ERROR"); 
		return "customError";
	}
}
