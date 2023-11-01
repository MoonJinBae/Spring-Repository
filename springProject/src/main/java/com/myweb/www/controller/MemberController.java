package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class MemberController {
	
	@Inject
	private BCryptPasswordEncoder bcEncoder;
	
	@Inject
	private MemberService msv;
	
	@GetMapping("register")
	public void register() {}
	
	@PostMapping("register")
	public String register(MemberVO mvo) {
		
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isOk = msv.register(mvo);
		return "index";
	}
	
	@GetMapping("login")
	public void login() {}
	
	@PostMapping("login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		// 로그인 실패시 다시 로그인 페이지로 돌아와 오류 메시지 전송
		// 다시 로그인 유도
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		
		
		return "redirect:/member/login";
	}
	
	@GetMapping("list")
	public void getMemberList(Model model) {
		List<MemberVO> mlist = msv.getMemberList();
		model.addAttribute("mlist", mlist);
	}
	
	@GetMapping("detail/{authEmail}")
	public String getMemberInfo(@PathVariable("authEmail")String email, Model model) {
		MemberVO mvo = msv.getMember(email);	
		log.info("detail mvo >>>>>>>>>>>>>>>>>>" + mvo);
		model.addAttribute("mvo", mvo);
		return "/member/detail";
	}
	
	@GetMapping("modify")
	public void getModify(@RequestParam("email")String email, Model model) {
		MemberVO mvo = msv.getMember(email);
		model.addAttribute("mvo", mvo);
	}
	
	@PostMapping("modify")
	public String postModify(MemberVO mvo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes re) {
		if(mvo.getPwd() == null || mvo.getPwd().equals("")) {
			re.addFlashAttribute("pwdError", 2);
			return "redirect:/member/modify?email=" + mvo.getEmail();
		}else {
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
			int isOk = msv.modify(mvo);
			log.info("modify >> " + (isOk > 0 ? "success" : "fail"));
			
			//사용자 정보를 찾는 인자 ?
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "index";
	}
	
	@GetMapping("remove/{authEmail}")
	public String removeMember(@PathVariable("authEmail")String email, HttpServletRequest request, HttpServletResponse response) {
		int isOk = msv.remove(email);
		log.info("remove >> " + (isOk > 0 ? "success" : "fail"));
		
		// 시큐리티 로그아웃 호출
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, auth);
		return "index";
	}
	
	
}
